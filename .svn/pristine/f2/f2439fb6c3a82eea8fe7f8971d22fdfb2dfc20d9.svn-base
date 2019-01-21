package com.liyunet.service;

import com.liyunet.domain.AntiAddiction;

import javax.servlet.http.HttpServletRequest;

/**
 */
public interface CertificationService {

    //保存身份证
    void saveIdentityCar(AntiAddiction antiAddiction, HttpServletRequest request,String userAccount);
    //获取审核状态
    Object getStatus(String identityCard);
    //绑定身份证
    void bandingIdentityCard(String identityCard, String projectName, String blockAddress,String account);
    //通过身份认证生成帐号
    void saveUserAccount(String identityCard);

    Object getStatusByAccount(String account);
    //根据id查询审核状态
    Object getStatusById(String token,String project);

    Object getSafetyById(String token, String project);

    Object getAccredityId(String token, String project);

    Object getAppAuthorization(String token);

    Object whetherBandingOtc(String token,String project);

    Object whetherKyc(String token, String project);

    Object whetherProjectBandingOtc(String token, String project,String ToProject);

    Object getOTCAccountByToken(String token);

    Object getAccountByBlockToken(String blockAddress, String project);

    Object eggIdentityCard(String token, String project, String blockToken, AntiAddiction antiAddiction,HttpServletRequest request);

    Object whetherKycContent(String token, String project, String blockToken);

    Object getBlockIdByAccountOrToken(String token, String project, String account);

    Object getBlockId(String token, String project, String account);

    Object getEggKycSuccess(String project);

    Object whetherBandingEgg(String token, String project);
}
