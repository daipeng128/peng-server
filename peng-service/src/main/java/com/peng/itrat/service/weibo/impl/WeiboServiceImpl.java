package com.peng.itrat.service.weibo.impl;

import com.peng.itrat.core.utils.StringUtils;
import com.peng.itrat.core.utils.TopicUtil;
import com.peng.itrat.core.utils.ValidUtill;
import com.peng.itrat.model.member.Member;
import com.peng.itrat.model.weibo.Weibo;
import com.peng.itrat.service.member.IMessageService;
import com.peng.itrat.service.member.IScoreDetailService;
import com.peng.itrat.service.weibo.IWeiboFavorService;
import com.peng.itrat.service.weibo.IWeiboService;
import com.peng.itrat.utils.ActionLogType;
import com.peng.itrat.utils.ActionUtil;
import com.peng.itrat.utils.ConfigUtil;
import com.peng.itrat.utils.ScoreRuleConsts;
import com.peng.itrat.core.consts.AppTag;
import com.peng.itrat.core.enums.MessageType;
import com.peng.itrat.core.dto.ResultModel;
import com.peng.itrat.core.exception.OpeErrorException;
import com.peng.itrat.core.exception.ParamException;
import com.peng.itrat.core.model.Page;
import com.peng.itrat.core.service.impl.BaseServiceImpl;
import com.peng.itrat.model.weibo.WeiboTopic;
import com.peng.itrat.service.picture.IPictureService;
import com.peng.itrat.service.member.IMemberService;
import com.peng.itrat.service.system.IActionLogService;
import com.peng.itrat.dao.weibo.IWeiboDao;
import com.peng.itrat.service.weibo.IWeiboTopicService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zchuanzhao on 2016/11/25.
 */
@Service("weiboService")
public class WeiboServiceImpl extends BaseServiceImpl<Weibo> implements IWeiboService {
    @Resource
    private IWeiboDao weiboDao;
    @Resource
    private IWeiboFavorService weiboFavorService;
    @Resource
    private IActionLogService actionLogService;
    @Resource
    private IPictureService pictureService;
    @Resource
    private IScoreDetailService scoreDetailService;
    @Resource
    private IMessageService messageService;
    @Resource
    private IMemberService memberService;
    @Resource
    private IWeiboTopicService weiboTopicService;

    @Override
    public Weibo findById(int id, int memberId) {
        Weibo weibo = weiboDao.findById(id,memberId);
        return weibo;
    }

    @Override
    @Transactional
    public boolean save(HttpServletRequest request, Member loginMember, String content, String pictures) {
        if("0".equals(request.getServletContext().getAttribute(ConfigUtil.WEIBO_POST.toUpperCase()))){
            throw new OpeErrorException("微博已关闭");
        }
        ValidUtill.checkIsBlank(content, "内容不能为空");
        if(content.length() > Integer.parseInt((String) request.getServletContext().getAttribute(ConfigUtil.WEIBO_POST_MAXCONTENT.toUpperCase()))){
            throw new ParamException("内容不能超过"+request.getServletContext().getAttribute(ConfigUtil.WEIBO_POST_MAXCONTENT.toUpperCase())+"字");
        }
        //获取话题
        String topicName = TopicUtil.getTopicName(content);
        WeiboTopic weiboTopic = null;
        if (StringUtils.isNotBlank(topicName)){
            weiboTopic = weiboTopicService.findByName(topicName);
            if (weiboTopic == null){
                weiboTopic = new WeiboTopic();
                weiboTopic.setName(topicName);
                weiboTopicService.save(weiboTopic);
            }
        }
        Weibo weibo = new Weibo();
        weibo.setMemberId(loginMember.getId());
        weibo.setContent(content);
        weibo.setStatus(1);
        if(StringUtils.isEmpty(pictures)){
            //普通文本
            weibo.setType(0);
        }else {
            //图片
            weibo.setType(1);
        }
        if (weiboTopic != null){
            weibo.setTopicId(weiboTopic.getId());
        }
        int result = weiboDao.saveObj(weibo);
        if(result == 1){
            //@会员处理并发送系统消息
            messageService.atDeal(loginMember.getId(),content, AppTag.WEIBO, MessageType.WEIBO_REFER,weibo.getId());
            pictureService.update(weibo.getId(),pictures, content);
            actionLogService.save(loginMember.getCurrLoginIp(),loginMember.getId(), ActionUtil.POST_WEIBO,"", ActionLogType.WEIBO.getValue(),weibo.getId());
            //发布微博奖励
            scoreDetailService.scoreBonus(loginMember.getId(), ScoreRuleConsts.RELEASE_WEIBO, weibo.getId());
        }
        return result == 1;
    }

    @Override
    public ResultModel<Weibo> listByPage(Page page, int memberId, int loginMemberId, String key) {
        if (StringUtils.isNotBlank(key)){
            key = "%"+key.trim()+"%";
        }
        List<Weibo> list = weiboDao.list(page, memberId,loginMemberId,key);
        list = this.formatWeibo(list);
        ResultModel model = new ResultModel(0,page);
        model.setData(list);
        return model;
    }

    @Transactional
    @Override
    public boolean delete(HttpServletRequest request, Member loginMember, int id) {
        Weibo weibo = this.findById(id,loginMember.getId());
        ValidUtill.checkIsNull(weibo, "微博不存在");
        weiboDao.delete(id);
        //扣除积分
        scoreDetailService.scoreCancelBonus(loginMember.getId(),ScoreRuleConsts.RELEASE_WEIBO,id);
        pictureService.deleteByForeignId(request, id);
        actionLogService.save(loginMember.getCurrLoginIp(),loginMember.getId(), ActionUtil.DELETE_WEIBO, "ID："+weibo.getId()+"，内容："+weibo.getContent());
        return true;
    }

    @Transactional
    @Override
    public boolean userDelete(HttpServletRequest request, Member loginMember, int id) {
        Weibo weibo = this.findById(id,loginMember.getId());
        ValidUtill.checkIsNull(weibo, "微博不存在");
        if(loginMember.getIsAdmin() == 0 && (loginMember.getId().intValue() != weibo.getMember().getId().intValue())){
            throw new OpeErrorException("没有权限");
        }
        return this.delete(request, loginMember,id);
    }

    @Override
    public List<Weibo> hotList(int loginMemberId) {
        List<Weibo> hotList = weiboDao.hotList(loginMemberId);
        hotList = this.formatWeibo(hotList);
        return hotList;
    }

    @Transactional
    @Override
    public ResultModel favor(Member loginMember, int weiboId) {
        ValidUtill.checkParam(weiboId == 0);
        Weibo weibo = this.findById(weiboId,loginMember.getId());
        ResultModel resultModel = new ResultModel(0);
        if(weiboFavorService.find(weiboId,loginMember.getId()) == null){
            //增加
            weiboDao.favor(weiboId,1);
            weibo.setFavor(weibo.getFavor() + 1);
            weiboFavorService.save(weiboId,loginMember.getId());
            //发布微博奖励
            scoreDetailService.scoreBonus(loginMember.getId(), ScoreRuleConsts.WEIBO_RECEIVED_THUMBUP, weiboId);
            //点赞之后发送系统信息
            messageService.diggDeal(loginMember.getId(),weibo.getMemberId(),AppTag.WEIBO,MessageType.WEIBO_ZAN,weibo.getId());
        }else {
            //减少
            weiboDao.favor(weiboId,-1);
            weibo.setFavor(weibo.getFavor() - 1);
            weiboFavorService.delete(weiboId,loginMember.getId());
            //扣除积分
            scoreDetailService.scoreCancelBonus(loginMember.getId(),ScoreRuleConsts.WEIBO_RECEIVED_THUMBUP,weiboId);
            resultModel.setCode(1);
        }
        resultModel.setData(weibo.getFavor());
        return resultModel;
    }

    @Override
    public List<Weibo> listByCustom(int loginMemberId, String sort, int num, int day) {
        List<Weibo> list = weiboDao.listByCustom(loginMemberId,sort,num,day);
        list = this.formatWeibo(list);
        return list;
    }

    @Override
    public ResultModel<Weibo> listByTopic(Page page, int loginMemberId, String topicName) {
        WeiboTopic weiboTopic = weiboTopicService.findByName(topicName);
        List<Weibo> list;
        if (weiboTopic == null){
            weiboTopic = new WeiboTopic();
            weiboTopic.setName(topicName);
            weiboTopicService.save(weiboTopic);
            list = new ArrayList<>();
        }else {
            list = weiboDao.listByTopic(page, loginMemberId, weiboTopic.getId());
            list = this.formatWeibo(list);
        }
        ResultModel model = new ResultModel(0,page);
        model.setData(list);
        return model;
    }

    public Weibo formatWeibo(Weibo weibo){
        weibo.setContent(memberService.atFormat(weibo.getContent()));
        weibo.setContent(TopicUtil.formatTopic(weibo.getContent()));
        return weibo;
    }

    public List<Weibo> formatWeibo(List<Weibo> weiboList){
        for (Weibo weibo : weiboList){
            formatWeibo(weibo);
        }
        return weiboList;
    }

}
