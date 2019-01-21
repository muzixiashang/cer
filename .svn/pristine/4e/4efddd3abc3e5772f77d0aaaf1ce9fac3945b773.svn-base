package com.liyunet.service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wuyunan on 2018/8/2.
 */
public interface ApiRegAddressService {
    //
    void postVerificationCode(String token, String languageType, HttpServletRequest request,String project);
    //修改邮箱
    void updateEmail(String token, String project, String email,String code);

    void updatePhoneCode(String token, String project);

    void updatePhone(String token, String project, String phone, String code);

    void updatePhoneCodeNew(String phone, String project,String token);

    void updatePhoneCodeConfirm(String token, String project, String code);

    void updateEmailcodeConfirm(String token, String code, String project);

    void updateEmailcodeNew(String token, String email, String project,String languageType);

    void bandingAccount(String token, String project, String account);

    void updatePwd(String token, String project, String pwd, String pwdNew);
}
