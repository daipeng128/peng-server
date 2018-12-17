package com.peng.itrat.service.member;

import com.peng.itrat.core.service.IBaseService;
import com.peng.itrat.model.member.Financial;
import java.util.List;


/**
 * Created by zchuanzhao on 2018/11/28.
 */
public interface IFinancialService extends IBaseService<Financial> {

    List<Financial> list(Integer memberId);


}
