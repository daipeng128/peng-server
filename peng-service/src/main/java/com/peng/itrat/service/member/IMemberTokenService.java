package com.peng.itrat.service.member;


import com.peng.itrat.model.member.MemberToken;
import com.lxinet.jeesns.core.service.IBaseService;

/**
 * Created by zchuanzhao on 2017/7/15.
 */
public interface IMemberTokenService extends IBaseService<MemberToken> {

    MemberToken getByToken(String token);

    void save(Integer memberId,String token);

}