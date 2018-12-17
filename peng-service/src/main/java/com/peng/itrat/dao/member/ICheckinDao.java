package com.peng.itrat.dao.member;

import com.peng.itrat.core.dao.BaseMapper;
import com.peng.itrat.core.model.Page;
import com.peng.itrat.model.member.Checkin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 会员签到DAO
 * Created by zchuanzhao on 18/8/20.
 */
public interface ICheckinDao extends BaseMapper<Checkin> {
    List<Checkin> list(@Param("page") Page page, @Param("memberId") Integer memberId);

    List<Checkin> todayList(@Param("page") Page page);

    List<Checkin> todayContinueList();

    Checkin todayCheckin(@Param("memberId") Integer memberId);

    Checkin yesterdayCheckin(@Param("memberId") Integer memberId);

}