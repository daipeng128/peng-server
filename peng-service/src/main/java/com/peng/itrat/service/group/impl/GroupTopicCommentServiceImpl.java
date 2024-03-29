package com.peng.itrat.service.group.impl;

import com.peng.itrat.dao.group.IGroupTopicCommentDao;
import com.peng.itrat.model.member.Member;
import com.peng.itrat.core.service.impl.BaseServiceImpl;
import com.peng.itrat.core.utils.ValidUtill;
import com.peng.itrat.core.consts.AppTag;
import com.peng.itrat.core.enums.MessageType;
import com.peng.itrat.core.dto.ResultModel;
import com.peng.itrat.core.model.Page;
import com.peng.itrat.model.group.GroupTopic;
import com.peng.itrat.model.group.GroupTopicComment;
import com.peng.itrat.service.group.IGroupTopicCommentService;
import com.peng.itrat.service.group.IGroupTopicService;
import com.peng.itrat.service.member.IMemberService;
import com.peng.itrat.service.member.IMessageService;
import com.peng.itrat.service.member.IScoreDetailService;
import com.peng.itrat.service.system.IActionLogService;
import com.peng.itrat.utils.ActionUtil;
import com.peng.itrat.utils.ScoreRuleConsts;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2016/12/27.
 */
@Service("groupTopicCommentService")
public class GroupTopicCommentServiceImpl extends BaseServiceImpl<GroupTopicComment> implements IGroupTopicCommentService {
    @Resource
    private IGroupTopicCommentDao groupTopicCommentDao;
    @Resource
    private IGroupTopicService groupTopicService;
    @Resource
    private IActionLogService actionLogService;
    @Resource
    private IScoreDetailService scoreDetailService;
    @Resource
    private IMessageService messageService;
    @Resource
    private IMemberService memberService;

    @Override
    public GroupTopicComment findById(int id) {
        return this.atFormat(groupTopicCommentDao.findById(id));
    }

    @Override
    public boolean save(Member loginMember, String content, Integer groupTopicId, Integer commentId) {
        GroupTopic groupTopic = groupTopicService.findById(groupTopicId,loginMember);
        ValidUtill.checkIsNull(groupTopic, "帖子不存在");
        ValidUtill.checkIsNull(content, "内容不能为空");
        GroupTopicComment groupTopicComment = new GroupTopicComment();
        groupTopicComment.setMemberId(loginMember.getId());
        groupTopicComment.setGroupTopicId(groupTopicId);
        groupTopicComment.setContent(content);
        groupTopicComment.setCommentId(commentId);
        int result = groupTopicCommentDao.saveObj(groupTopicComment);
        if(result == 1){
            //@会员处理并发送系统消息
            messageService.atDeal(loginMember.getId(),content, AppTag.GROUP, MessageType.GROUP_TOPIC_COMMENT_REFER,groupTopicComment.getId());
            messageService.diggDeal(loginMember.getId(),groupTopic.getMemberId(),content,AppTag.GROUP,MessageType.GROUP_TOPIC_REPLY,groupTopic.getId());
            if (commentId != null){
                GroupTopicComment replyGroupTopicComment = findById(commentId);
                if(replyGroupTopicComment != null){
                    messageService.diggDeal(loginMember.getId(),replyGroupTopicComment.getMemberId(),content,AppTag.GROUP,MessageType.GROUP_TOPIC_REPLY_REPLY,replyGroupTopicComment.getId());
                }
            }
            //群组帖子评论奖励
            scoreDetailService.scoreBonus(loginMember.getId(), ScoreRuleConsts.GROUP_TOPIC_COMMENTS, groupTopicComment.getId());
        }
        return result == 1;
    }

    @Override
    public ResultModel listByGroupTopic(Page page, int groupTopicId) {
        List<GroupTopicComment> list = groupTopicCommentDao.listByGroupTopic(page, groupTopicId);
        this.atFormat(list);
        ResultModel model = new ResultModel(0,page);
        model.setData(list);
        return model;
    }

    @Override
    public void deleteByTopic(int groupTopicId) {
        groupTopicCommentDao.deleteByTopic(groupTopicId);
    }

    @Override
    public boolean delete(Member loginMember, int id){
        GroupTopicComment groupTopicComment = this.findById(id);
        ValidUtill.checkIsNull(groupTopicComment, "评论不存在");
        boolean result = super.deleteById(id);
        if(result){
            //扣除积分
            scoreDetailService.scoreCancelBonus(loginMember.getId(),ScoreRuleConsts.GROUP_TOPIC_COMMENTS,id);
            actionLogService.save(loginMember.getCurrLoginIp(),loginMember.getId(), ActionUtil.DELETE_GROUP_TOPIC_COMMENT,"ID："+groupTopicComment.getId()+"，内容："+groupTopicComment.getContent());
        }
        return result;
    }

    public GroupTopicComment atFormat(GroupTopicComment groupTopicComment){
        groupTopicComment.setContent(memberService.atFormat(groupTopicComment.getContent()));
        return groupTopicComment;
    }

    public List<GroupTopicComment> atFormat(List<GroupTopicComment> groupTopicCommentList){
        for (GroupTopicComment groupTopicComment : groupTopicCommentList){
            atFormat(groupTopicComment);
        }
        return groupTopicCommentList;
    }
}
