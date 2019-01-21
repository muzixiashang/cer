package com.liyunet.service;

import com.liyunet.domain.AntiAddiction;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wuyunan on 2018/8/13.
 */
public interface BandingService {
    Object getAccredityId(String token, String project, String account,String blockToken,HttpServletRequest request,AntiAddiction antiAddiction);

    Object getblockId(String token, String project);

    void phoneCode(String phone, String token, String project);

    Object affirmCode(String phone, String token, String project, String code);

    Object saveIdentityCard(AntiAddiction antiAddiction, String account, HttpServletRequest request);

    void updatePwd(String pwd, String token,HttpServletRequest request, String blockId);

    Object judgeBlockId(String blockId);

    void blockIdPhoneCode(String blockId);

    void findPwd(String pwd, String code, String blockId);

    void findBlockIdCode(String phone);

    Object findBlockId(String code, String identityCard, String phone,HttpServletRequest request,String userName);

    Object judgeBanding(String token, String project);

    Object getAccredityIds(String token, String project, String account, String blockToken, HttpServletRequest request);

    Object regOTC(String blockToken, String project);

    void bangdingBlockIndex(String token, String project,String blockId);
}
