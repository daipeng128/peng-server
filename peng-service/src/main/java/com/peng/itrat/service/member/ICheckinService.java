package com.peng.itrat.service.member;

import com.peng.itrat.core.model.Page;
import com.peng.itrat.core.service.IBaseService;
import com.peng.itrat.model.member.Checkin;

import java.util.List;


/**
 * 签到
 * Created by zchuanzhao on 18/8/20.
 */
public interface ICheckinService extends IBaseService<Checkin> {

    List<Checkin> list(Page page, Integer memberId);

    List<Checkin> todayList(Page page);

    List<Checkin> todayContinueList();

    Checkin todayCheckin(Integer memberId);

    Checkin yesterdayCheckin(Integer memberId);

    boolean save(Integer memberId);
}
