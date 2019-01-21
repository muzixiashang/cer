package com.liyunet.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liyunet.common.constant.Constant;
import com.liyunet.common.password.MD5Util;
import com.liyunet.common.pushPassword.PushPasswordHash;
import com.liyunet.common.pushToken.PushAuthHelper;
import com.liyunet.common.pushToken.PushTokenInfo;
import com.liyunet.common.util.IpResourceLocation;
import com.liyunet.common.util.UrlLoad;
import com.liyunet.domain.*;
import com.liyunet.exception.ServiceException;
import com.liyunet.interceptor.anno.HTTPPublicResource;
import com.liyunet.service.PushRegService;
import com.liyunet.util.UuidUtil;
import com.liyunet.vo.user.LoginVo;
import com.liyunet.vo.user.PushUserInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * 区块浏览器注册接口
 */
@RestController
@RequestMapping("/api/reg")
public class ApiRegController {
    private static Logger opLogger = LogManager.getLogger("exception");

    private final PushRegService regService;

    @Autowired
    public ApiRegController(PushRegService regService) {
        this.regService = regService;
    }

    //发送验证码
    @HTTPPublicResource
    @RequestMapping(value = "/code", method = RequestMethod.POST)
    @Transactional
    public Object accountVerfication(@RequestParam String account,
                                     HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam(required = true, defaultValue = "zh") String languageType) {
        regService.postVerificationCode(account, languageType, request);
        return null;
    }

    //邮箱注册
    @HTTPPublicResource
    @RequestMapping(method = RequestMethod.POST, value = "/userReg")
    @Transactional
    public Object reg(
            @RequestParam String account,
            @RequestParam String code,
            @RequestParam String pwd,
//            @RequestParam String TTCsite,
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(required = true, defaultValue = "en") String languageType) {

        try {
            return regService.doReg(account, pwd, code, request, response, languageType);
        } catch (NoSuchAlgorithmException e) {
            throw ServiceException.userException("The system is busy. Please try again later!", "系统繁忙,请稍后重试!");
        } catch (InvalidKeySpecException e) {
            throw ServiceException.userException("The system is busy. Please try again later!", "系统繁忙,请稍后重试!");

        }


    }


    /**
     * 通过邮件修改密码(发送验证码)
     */
    @HTTPPublicResource
    @Transactional
    @RequestMapping(value = "/findPwdVerficode", method = RequestMethod.POST)
    public Object forgetPwd(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam String account,
                            @RequestParam(required = true, defaultValue = "zh") String languageType) {
        regService.forgetPwd(account, languageType);
        return null;
    }


    /**
     * 绑定手机发送验证码
     */
    @Transactional
    @RequestMapping(value = "/findPhonecode", method = RequestMethod.POST)
    public Object findPhonecode(
            HttpServletRequest request, HttpServletResponse response,
            @RequestParam String account,
            PushTokenInfo pushTokenInfo,
            @RequestParam(required = true, defaultValue = "en") String languageType) {

        String id = pushTokenInfo.getUserId().toString();
        regService.findPhonecode(account, id, languageType);
        return null;
    }

    /**
     * 通过邮件(或手机号)验证码修改密码
     *
     * @param account
     * @param pwd
     * @return
     */
    @HTTPPublicResource
    @RequestMapping(method = RequestMethod.POST, value = "/updatePwdByAccount")
    @Transactional
    public Object chgPwd(HttpServletRequest request, HttpServletResponse response,
                         @RequestParam String account, @RequestParam String pwd,
                         @RequestParam String code,
                         @RequestParam(required = true, defaultValue = "zh") String languageType) {

        return regService.chgPwd(account, pwd, code, languageType);

    }

    //发送验证码(畅天游短信发送平台)
    @HTTPPublicResource
    @RequestMapping(value = "/ctycode", method = RequestMethod.POST)
    @Transactional
    public Object ctycode(
            @RequestParam String account,
            HttpServletRequest request,
            @RequestParam(required = true) String type,//1已有账号认证 0注册认证
            @RequestParam(required = true, defaultValue = "zh") String languageType) {

        regService.ctycode(account, type, request);
        return null;
    }


    //手机号注册
    @HTTPPublicResource
    @RequestMapping(method = RequestMethod.POST, value = "/mobileReg")
    public Object mobileReg(
            @RequestParam(required = true) String account,
            @RequestParam(required = true) String code,
            @RequestParam(required = true) String pwd,
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(required = true, defaultValue = "en") String languageType) {
        return regService.mobileReg(account, pwd, code, request, response, languageType);

    }



    @HTTPPublicResource
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    @Transactional
    public Object login(@RequestParam(required = true) String pwd,
                        @RequestParam(required = true) String username,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        @RequestParam(required = true, defaultValue = "en") String languageType) {

        if (StringUtils.isBlank(username) || StringUtils.isBlank(pwd)) {
            throw ServiceException.userException("The username or password can't be empty!", "账号密码不能为空!");
        } else {
            username = username.trim();
            pwd = pwd.trim();
            BlockUserInfo dbEnpUser = null;
            int length = username.length();
            if (6 == length) {

                List<UserInfo> userInfoList = regService.getUserInfo(username);
                if (userInfoList != null && userInfoList.size() > 0) {
                    UserInfo userInfo = userInfoList.get(0);
                    try {
                        boolean b = PushPasswordHash.validatePassword(pwd, userInfo.getUserPwd());
                        if (!b) {
                            throw ServiceException.userException("Password mistake!", "密码错误!");
                        }
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                        throw ServiceException.userException("Password mistake!", "密码错误!");
                    } catch (InvalidKeySpecException e) {
                        e.printStackTrace();
                        throw ServiceException.userException("Password mistake!", "密码错误!");
                    }
                    String uId = userInfo.getuId();
                    List<BlockUserInfo> blockUserInfoList = regService.getBlockUserInfo(uId);
                    if (blockUserInfoList != null && blockUserInfoList.size() > 0) {
                        dbEnpUser = blockUserInfoList.get(0);
                    }else {
                        throw ServiceException.userException("The block status did not bind the project！", "该区块身份未绑定该项目!");
                    }

                } else {
                    throw ServiceException.userException("Username or password is wrong!", "账号或密码错误!");
                }
            } else {
                List<BlockUserInfo> dbEnpUsers = regService.getUserByAccount(username);

                if (dbEnpUsers != null && dbEnpUsers.size() > 0) {
                    dbEnpUser = dbEnpUsers.get(0);
                }
                try {
                    if (dbEnpUser == null || !PushPasswordHash.validatePassword(pwd, dbEnpUser.getPwd())) {
                        throw ServiceException.userException("Username or password is wrong!", "账号或密码错误!");
                    }
                } catch (NoSuchAlgorithmException e) {
                    throw ServiceException.userException("Username or password is wrong!", "账号或密码错误!");
                } catch (InvalidKeySpecException e) {
                    throw ServiceException.userException("Username or password is wrong!", "账号或密码错误!");
                }
            }

            //终端访问来源
            String reqFrom = request.getHeader("From");
            if (StringUtils.isNotEmpty(reqFrom) && "app".equalsIgnoreCase(reqFrom)) {
                reqFrom = "app";
            } else if (StringUtils.isNotEmpty(reqFrom) && "pc".equalsIgnoreCase(reqFrom)) {
                reqFrom = "pc";
            } else if (StringUtils.isNotEmpty(reqFrom) && "sdk".equalsIgnoreCase(reqFrom)) {
                reqFrom = "sdk";
            } else {
                reqFrom = "wap";
            }
            reqFrom = "pc";
            // 创建一个365天过期的JSON WEB TOKEN TODO
            String jwt = PushAuthHelper.createJsonWebToken(dbEnpUser.getId(), reqFrom, TimeUnit.HOURS, Constant.COOKIE_EXPIRE_HOURS);
            //写入cookie中
            Cookie cookie = new Cookie("jwt", jwt);
            cookie.setHttpOnly(true);
            cookie.setMaxAge((int) TimeUnit.HOURS.toSeconds(Constant.COOKIE_EXPIRE_HOURS));
            //cookie.setDomain(request.getServerName());
            response.addCookie(cookie);

            LoginLog loginLog = new LoginLog();
            loginLog.setUserId(dbEnpUser.getId());
            loginLog.fromApp();
            regService.createLoginLog(loginLog);
            Integer id = dbEnpUser.getId();
            PushUserInfoVo vo = new PushUserInfoVo();
            vo.setId(dbEnpUser.getId());
            vo.setNickName(dbEnpUser.getUsername());
            vo.setUsername(dbEnpUser.getUsername());
            vo.setUserDesc(dbEnpUser.getUserDesc() == null ? "" : dbEnpUser.getUserDesc());
            vo.setUserSex(dbEnpUser.getUserSex());
            vo.setUserHeadPicPath(dbEnpUser.getHeadPicPath() == null ? "" : dbEnpUser.getHeadPicPath());
            String uesrSexStr = dbEnpUser.getUserSex() == 0 ? "男" : "女";
            if ("en".equals(languageType)) {
                uesrSexStr = dbEnpUser.getUserSex() == 0 ? "male" : "female";
            } else if ("zh".equals(languageType)) {
                uesrSexStr = dbEnpUser.getUserSex() == 0 ? "男" : "女";
            }
            vo.setUserSexStr(uesrSexStr);
            vo.setToken(jwt);
            vo.setPhoneNum(dbEnpUser.getPhoneNum() == null ? "" : dbEnpUser.getPhoneNum());
            return vo;

        }

    }
    //靓号登录获取靓号
    @HTTPPublicResource
    @RequestMapping(method = RequestMethod.POST, value = "/beautifulLogin")
    @Transactional
    public Object beautifulLogin(@RequestParam(required = true) String pwd,
                                 @RequestParam(required = true) String username,
                                 HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestParam(required = true, defaultValue = "en") String languageType) {

        if (StringUtils.isBlank(username) || StringUtils.isBlank(pwd)) {
            throw ServiceException.userException("The username or password can't be empty!", "账号密码不能为空!");
        } else {
            username = username.trim();
            pwd = pwd.trim();
            LoginVo vo = new LoginVo();
            List<UserInfo> userInfoList = regService.getUserInfo(username);
            if (userInfoList != null && userInfoList.size() > 0) {
                UserInfo userInfo = userInfoList.get(0);
                try {
                    boolean b = PushPasswordHash.validatePassword(pwd, userInfo.getUserPwd());
                    if (!b) {
                        throw ServiceException.userException("Password mistake!", "密码错误!");
                    }

                    //终端访问来源
                    String reqFrom = request.getHeader("From");
                    if (StringUtils.isNotEmpty(reqFrom) && "app".equalsIgnoreCase(reqFrom)) {
                        reqFrom = "app";
                    } else if (StringUtils.isNotEmpty(reqFrom) && "pc".equalsIgnoreCase(reqFrom)) {
                        reqFrom = "pc";
                    } else if (StringUtils.isNotEmpty(reqFrom) && "sdk".equalsIgnoreCase(reqFrom)) {
                        reqFrom = "sdk";
                    } else {
                        reqFrom = "wap";
                    }
                    reqFrom = "pc";
                    // 创建一个365天过期的JSON WEB TOKEN TODO
                    String jwt = PushAuthHelper.createJsonWebToken(userInfo.getId(), reqFrom, TimeUnit.HOURS, Constant.COOKIE_EXPIRE_HOURS);
                    vo.setId(userInfo.getUserAccount());
                    vo.setPicUrl(userInfo.getHeadPicPath());
                    String userAccount = userInfo.getUserAccount();
                    vo.setBlockId(userAccount);
                    vo.setBlockAddress(userInfo.getBlockAddress());
                    vo.setToken(jwt);
                    vo.setUserId(userInfo.getuId());
                    //判断是否绑定自己
                    Integer id = userInfo.getId();
                    List<AssociatedAccount> associatedAccountList = regService.getAssociatedAccount(id,4);
                    if (associatedAccountList == null || associatedAccountList.size() < 1) {
                        AssociatedAccount associatedAccount = new AssociatedAccount();
                        associatedAccount.setuId(UuidUtil.getUUid());
                        associatedAccount.setUserId(userInfo.getuId());
                        associatedAccount.setAppName("4");
                        associatedAccount.setAppUserAccount(userInfo.getUserAccount());
                        associatedAccount.setCreateTime(getCurrentTime());
                        associatedAccount.setToken(id+"");
                        int i = regService.saveAssociatedAccount(associatedAccount);
                        if (i < 1){
                            throw ServiceException.userException("Login failed, please contact customer service!", "登录失败，请联系客服!");
                        }
                    }

                    String eggId = null;
                    String status = "0";
                    String uId = userInfo.getuId();
                    List<AssociatedAccount> antiAddictionList = regService.getAntiAddiction(uId, "1");
                    if (antiAddictionList != null && antiAddictionList.size() > 0) {
                        AssociatedAccount associatedAccount = antiAddictionList.get(0);
                        status = "1";
                        eggId = associatedAccount.getToken();
                    }
                    try {
                        UrlLoad.loadGet(IpResourceLocation.TT_EGGWORLD_IP+"/ttc-eggworld/ttc/updateUserInfoBalance?eggId="+eggId+"&blockId="+userInfo.getId()+"&address="+userInfo.getBlockAddress()+"&state="+status);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return  vo;
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                    throw ServiceException.userException("Password mistake!", "密码错误!");
                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                    throw ServiceException.userException("Password mistake!", "密码错误!");
                }
            } else {
                throw ServiceException.userException("Account or password error", "帐号或密码错误!");
            }
        }

    }
    //当前时间
    public String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = format.format(new Date());
        return format1;
    }

    //蛋生靓号登录获取靓号
    @HTTPPublicResource
    @RequestMapping(method = RequestMethod.POST, value = "/eggLogin")
    @Transactional
    public Object eggLogin(@RequestParam(required = true) String pwd,
                                 @RequestParam(required = true) String username,
                                 @RequestParam(required = true) String project,
                                 HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestParam(required = true, defaultValue = "en") String languageType) {

        if (StringUtils.isBlank(username) || StringUtils.isBlank(pwd)) {
            throw ServiceException.userException("The username or password can't be empty!", "账号密码不能为空!");
        } else {
            username = username.trim();
            pwd = pwd.trim();
            List<UserInfo> userInfoList = regService.getUserInfo(username);
            if (userInfoList != null && userInfoList.size() > 0) {
                UserInfo userInfo = userInfoList.get(0);
                try {
                    boolean b = PushPasswordHash.validatePassword(pwd, userInfo.getUserPwd());
                    if (!b) {
                        throw ServiceException.userException("Password mistake!", "密码错误!");
                    }
                    String uId = userInfo.getuId();
                    List<AssociatedAccount> antiAddictionList = regService.getAntiAddiction(uId,project);
                    LoginVo vo = new LoginVo();

                    if (antiAddictionList != null && antiAddictionList.size() > 0) {
                        AssociatedAccount associatedAccount = antiAddictionList.get(0);
                        vo.setStatus("1");
                        vo.setAccount(associatedAccount.getAppUserAccount());
                        return vo;
                    }else {
                        //终端访问来源
                        String reqFrom = request.getHeader("From");
                        if (StringUtils.isNotEmpty(reqFrom) && "app".equalsIgnoreCase(reqFrom)) {
                            reqFrom = "app";
                        } else if (StringUtils.isNotEmpty(reqFrom) && "pc".equalsIgnoreCase(reqFrom)) {
                            reqFrom = "pc";
                        } else if (StringUtils.isNotEmpty(reqFrom) && "sdk".equalsIgnoreCase(reqFrom)) {
                            reqFrom = "sdk";
                        } else {
                            reqFrom = "wap";
                        }
                        reqFrom = "pc";
                        // 创建一个365天过期的JSON WEB TOKEN TODO
                        String jwt = PushAuthHelper.createJsonWebToken(userInfo.getId(), reqFrom, TimeUnit.HOURS, Constant.COOKIE_EXPIRE_HOURS);
                        vo.setToken(jwt);
                        vo.setBlockAddress(userInfo.getBlockAddress());//区块地址
                        vo.setIdentityCard(userInfo.getIdentityCard());
                        vo.setBlockId(userInfo.getUserAccount());//靓号
                        if (userInfo.getPhoneNum() == null) {
                            vo.setAccount(userInfo.getEmail());
                        }else {
                            vo.setAccount(userInfo.getPhoneNum());
                        }
                        vo.setStatus("0");
                        return  vo;
                    }

                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                    throw ServiceException.userException("Password mistake!", "密码错误!");
                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                    throw ServiceException.userException("Password mistake!", "密码错误!");
                }
            } else {
                throw ServiceException.userException("Account or password error", "帐号或密码错误!");
            }
        }

    }
}
