package com.liyunet.service;

import com.liyunet.domain.*;
import com.liyunet.vo.user.PushUserInfoVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public interface PushRegService {

    //	BaseJSONModel isEmailRegistered(String email);
//
    PushUserInfoVo doReg(String account, String pwd, String code, HttpServletRequest request,
                         HttpServletResponse response, String languageType) throws NoSuchAlgorithmException, InvalidKeySpecException;

    //
//	/**
//	 * 给邮箱发送验证码
//	 * @param account	者邮箱
//	 * @param request
//	 * @return
//	 */
    void postVerificationCode(String account, String languageType, HttpServletRequest request);

    //	/**
//	 * 忘记密码
//	 * @param account
//	 * @return
//	 */
    void forgetPwd(String account, String languageType);

    //
//	/**
//	 * 修改密码
//	 * @param userId
//     * @return
//     */
//	BaseJSONModel chgPwd(Integer userId, String oldPwd, String newPwd, String languageType);
//
//	/**
//	 * 通过验证码修改密码
//	 * @param account
//	 * @param pwd
//	 * @param code
//	 * @return
//	 */
    public PushUserInfoVo chgPwd(String account, String pwd, String code, String languageType);

    //
//	//保存登录日志
    public void createLoginLog(LoginLog log);
//
//	//时间条约币查询
//	public BaseJSONModel queryTreaty(String eth, String token, String languageType);

    //发送绑定手机验证码
    void findPhonecode(String account, String id, String languageType);

    //手机号注册
    PushUserInfoVo mobileReg(String account, String pwd, String code, HttpServletRequest request,
                         HttpServletResponse response, String languageType);
    //畅天游发送短信
    void ctycode(String account, String type, HttpServletRequest request);
    //获取用户
    List<BlockUserInfo> getUserByAccount(String userName);

    List<UserInfo> getUserInfo(String username);

    List<BlockUserInfo> getBlockUserInfo(String uId);

    List<AssociatedAccount> getAntiAddiction(String uId, String project);

    List<AssociatedAccount> getAssociatedAccount(Integer id, int type);

    int saveAssociatedAccount(AssociatedAccount associatedAccount);

}
