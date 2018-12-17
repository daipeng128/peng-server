package com.peng.itrat.service.member.impl;

import com.peng.itrat.dao.member.ICheckinDao;
import com.peng.itrat.service.member.IScoreDetailService;
import com.peng.itrat.core.service.impl.BaseServiceImpl;
import com.peng.itrat.utils.ScoreRuleConsts;
import com.peng.itrat.core.exception.OpeErrorException;
import com.peng.itrat.core.model.Page;
import com.peng.itrat.model.member.Checkin;
import com.peng.itrat.service.member.ICheckinService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 签到
 * Created by zchuanzhao on 18/8/20.
 */
@Service
public class CheckinServiceImpl extends BaseServiceImpl<Checkin> implements ICheckinService {
    @Resource
    private ICheckinDao checkinDao;
    @Resource
    private IScoreDetailService scoreDetailService;

    @Override
    public List<Checkin> list(Page page, Integer memberId) {
        List<Checkin> list = checkinDao.list(page,memberId);
        return list;
    }

    @Override
    public List<Checkin> todayList(Page page) {
        List<Checkin> list = checkinDao.todayList(page);
        return list;
    }

    @Override
    public List<Checkin> todayContinueList() {
        return checkinDao.todayContinueList();
    }

    @Override
    public Checkin todayCheckin(Integer memberId) {
        return checkinDao.todayCheckin(memberId);
    }

    @Override
    public Checkin yesterdayCheckin(Integer memberId) {
        return checkinDao.yesterdayCheckin(memberId);
    }

    @Override
    @Transactional
    public boolean save(Integer memberId) {
        synchronized (this){
            if (null != todayCheckin(memberId)){
                throw new OpeErrorException("今天已经签到过了");
            }
            Checkin checkin = new Checkin();
            checkin.setMemberId(memberId);
            Checkin yesterdayCheckin = yesterdayCheckin(memberId);
            if (null != yesterdayCheckin){
                checkin.setContinueDay(yesterdayCheckin.getContinueDay() + 1);
            }else {
                checkin.setContinueDay(1);
            }
            boolean result = checkinDao.saveObj(checkin) == 1;
            if (result){
                scoreDetailService.scoreBonus(memberId, ScoreRuleConsts.CHECKIN, checkin.getId());
            }
            return result;
        }

    }
}
