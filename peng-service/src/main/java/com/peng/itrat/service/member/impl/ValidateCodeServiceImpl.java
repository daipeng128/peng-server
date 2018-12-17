package com.peng.itrat.service.member.impl;

import com.peng.itrat.model.member.ValidateCode;
import com.peng.itrat.core.service.impl.BaseServiceImpl;
import com.peng.itrat.dao.member.IValidateCodeDao;
import com.peng.itrat.service.member.IValidateCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2017/1/20.
 */
@Service("validateCodeService")
public class ValidateCodeServiceImpl extends BaseServiceImpl<ValidateCode> implements IValidateCodeService {
    @Resource
    private IValidateCodeDao validateCodeDao;

    @Override
    public boolean save(ValidateCode validateCode) {
        return validateCodeDao.save(validateCode) == 1;
    }

    @Override
    public ValidateCode valid(String email, String code, int type) {
        return validateCodeDao.valid(email,code,type);
    }

    @Override
    public boolean used(Integer id) {
        return validateCodeDao.used(id) == 1;
    }
}
