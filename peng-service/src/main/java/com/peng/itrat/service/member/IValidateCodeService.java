package com.peng.itrat.service.member;


import com.peng.itrat.model.member.ValidateCode;
import com.peng.itrat.core.service.IBaseService;

/**
 * 验证码Service接口
 * Created by zchuanzhao on 17/01/20.
 */
public interface IValidateCodeService extends IBaseService<ValidateCode> {

    boolean save(ValidateCode validateCode);

    /**
     * 验证，30分钟以内有效
     * @param email
     * @param code
     * @param type
     * @return
     */
    ValidateCode valid(String email, String code, int type);

    boolean used(Integer id);
}