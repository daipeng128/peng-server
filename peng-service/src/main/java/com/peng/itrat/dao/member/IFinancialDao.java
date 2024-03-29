package com.peng.itrat.dao.member;

import com.peng.itrat.model.member.Financial;
import com.peng.itrat.core.dao.BaseMapper;
import com.peng.itrat.core.model.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zchuanzhao on 2018/11/28.
 */
public interface IFinancialDao extends BaseMapper<Financial> {

    List<Financial> list(@Param("page") Page page, @Param("memberId") Integer memberId);

}