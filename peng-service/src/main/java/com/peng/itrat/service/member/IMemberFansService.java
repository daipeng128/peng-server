package com.peng.itrat.service.member;

import com.peng.itrat.core.dto.ResultModel;
import com.peng.itrat.core.model.Page;
import com.peng.itrat.core.service.IBaseService;
import com.peng.itrat.model.member.MemberFans;


/**
 * Created by zchuanzhao on 17/2/21.
 */
public interface IMemberFansService extends IBaseService<MemberFans> {

    boolean save(Integer whoFollowId, Integer followWhoId);

    boolean delete(Integer whoFollowId, Integer followWhoId);

    ResultModel followsList(Page page, Integer whoFollowId);

    ResultModel fansList(Page page, Integer followWhoId);

    MemberFans find(Integer whoFollowId, Integer followWhoId);
}
