package com.liyunet.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liyunet.common.blockAddress.CreateBitconAddressUtil;
import com.liyunet.common.constant.Constant;
import com.liyunet.common.pushPassword.PushPasswordHash;
import com.liyunet.common.pushToken.PushAuthHelper;
import com.liyunet.common.queue.SmsListenerBusPwd;
import com.liyunet.common.sms.SmsCodeGenerator;
import com.liyunet.common.sms.SmsHolder;
import com.liyunet.common.util.IDCard;
import com.liyunet.common.util.IpResourceLocation;
import com.liyunet.common.util.TokenUtil;
import com.liyunet.common.util.UrlLoad;
import com.liyunet.controller.AndroidController;
import com.liyunet.domain.*;
import com.liyunet.exception.ServiceException;
import com.liyunet.mapper.community.BandingMapper;
import com.liyunet.service.BandingService;
import com.liyunet.util.UuidUtil;
import com.liyunet.vo.user.UserInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.rmi.ServerException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 */
@Service("bandingService")
@Transactional
public class BandingServiceImpl implements BandingService {

    private final BandingMapper bandingMapper;

    @Autowired
    public BandingServiceImpl(BandingMapper bandingMapper) {
        this.bandingMapper = bandingMapper;
    }

    @Override
    public Object getAccredityId(String token, String project, String account, String blockToken, HttpServletRequest request, AntiAddiction antiAddiction) {

        String userId = TokenUtil.getUserId(token);
        String blockUserId = TokenUtil.getUserId(blockToken);


        List<UserInfo> userInfoList = bandingMapper.getUserInfo(blockUserId);
        if (userInfoList == null || userInfoList.size() < 1) {
            throw ServiceException.userException("The account does not exist", "靓号不存在");
        }
        UserInfo userInfo = userInfoList.get(0);

        List<AssociatedAccount> associatedAccounts = bandingMapper.getAssociatedAccount(project, account);
        if (associatedAccounts != null && associatedAccounts.size() > 0) {
            throw ServiceException.userException("This account has been binding other account", "该账号已绑定其他区块身份！");
        }
        List<AssociatedAccount> associatedAccountList = bandingMapper.getAssociatedAccountByblockId(userInfo.getUserAccount(), project);
        if (associatedAccountList != null && associatedAccountList.size() > 0) {
            throw ServiceException.userException("This account has been binding other account", "该区块身份已绑定其他帐号！");
        }

        if (antiAddiction != null) {
            String identityCard = userInfo.getIdentityCard();
            String identityCard1 = antiAddiction.getIdentityCard();
            if (identityCard1 != null && !"".equals(identityCard1)) {
                if (!identityCard.equals(identityCard1)) {
                    throw ServiceException.userException("The application records do not match the id number and block status", "该应用记录的身份证号与区块身份不一致！");
                }
            }

            Integer state = antiAddiction.getState();
            String level = antiAddiction.getLevel();
            if (state.intValue() == 1 && "2".equals(level)) {

                List<AntiAddiction> antiAddiction1 = bandingMapper.getAntiAddiction(identityCard);
                if (antiAddiction1 == null || antiAddiction1.size() < 1) {
                    throw ServiceException.userException("Bound to fail, please contact customer service", "绑定失败，请联系客服！");
                }
                AntiAddiction antiAddiction2 = antiAddiction1.get(0);

                antiAddiction2.setBackUrl(antiAddiction.getBackUrl());
                antiAddiction2.setFrontUrl(antiAddiction.getFrontUrl());
                antiAddiction2.setLevel(antiAddiction.getLevel());
                antiAddiction2.setState(antiAddiction.getState());
                antiAddiction2.setuId(userInfo.getuId());
                bandingMapper.updateAntiAddiction(antiAddiction2);

            }
        }

        AssociatedAccount associatedAccount = new AssociatedAccount();
        associatedAccount.setToken(userId);
        associatedAccount.setuId(UuidUtil.getUUid());
        associatedAccount.setAppName(project);
        associatedAccount.setAppUserAccount(account);
        associatedAccount.setCreateTime(getCurrentTime());
        associatedAccount.setUserId(userInfo.getuId());
        bandingMapper.saveAssociatedAccount(associatedAccount);

        UserInfoVo vo = new UserInfoVo();

        vo.setAccount(account);
        String blockAddress = userInfo.getBlockAddress();
        String userAccount = userInfo.getUserAccount();
        vo.setBlockAddress(blockAddress);
        vo.setBlockId(userAccount);
        String total = getTotal(blockAddress, userAccount);
        vo.setTotalPrice(total);//总资产
        vo.setPicUrl(userInfo.getHeadPicPath());

        try {

            String appName = project;
            if ("1".equals(appName)) {

                String s = null;
                try {
                    s = UrlLoad.load(IpResourceLocation.EGG_IP + "/egg_game/api/blockinfo/getUserblockaddress",
                            "id=" + userId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                JSONObject jsonObject = JSON.parseObject(s);
                String string = jsonObject.getString("state");
                JSONObject jsonObject1 = JSON.parseObject(string);
                String code = jsonObject1.getString("code");
                if ("20000".equals(code)) {
                    String data = jsonObject.getString("data");
                    if (data != null && !"".equals(data)) {
                        String json = null;
                        try {
                            json = UrlLoad.load(IpResourceLocation.OTC_IP + "/timetreaty_push/api/certification/getAccountByBlockId",
                                    "blockId=" + data);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        JSONObject jsonObject2 = JSON.parseObject(json);
                        String string1 = jsonObject2.getString("state");
                        JSONObject jsonObject3 = JSON.parseObject(string1);
                        String code1 = jsonObject3.getString("code");
                        if ("20000".equals(code1)) {
                            String data1 = jsonObject2.getString("data");
                            if (data1 != null && !"".equals(data1)) {

                                JSONObject jsonObject5 = JSON.parseObject(data1);
                                String dataToken = jsonObject5.getString("id");
                                AssociatedAccount associatedAccount2 = new AssociatedAccount();
                                associatedAccount2.setToken(dataToken);
                                associatedAccount2.setuId(UuidUtil.getUUid());
                                associatedAccount2.setAppName("2");
                                associatedAccount2.setAppUserAccount(jsonObject5.getString("username"));
                                associatedAccount2.setCreateTime(getCurrentTime());
                                associatedAccount2.setUserId(userInfo.getuId());
                                bandingMapper.saveAssociatedAccount(associatedAccount2);

                            }

                        } else if ("20002".equals(code)) {
                            String msg = jsonObject1.getString("msg");
                            throw ServiceException.userException("A system exception, contact customer service", msg);

                        }

                    }

                } else if ("20002".equals(code)) {
                    String msg = jsonObject1.getString("msg");
                    throw ServiceException.userException("A system exception, contact customer service", msg);

                }

            }
            UrlLoad.load(IpResourceLocation.EGG_IP + "/egg_game/api/blockinfo/bindingblock",
                    "account=" + userInfo.getUserAccount() + "&phonenum=" + account + "&token=" + token + "&blockadress=" + blockAddress);


        } catch (Exception e) {
            e.printStackTrace();
        }


        return vo;
    }

    @Override
    public Object getblockId(String token, String project) {

        String userId = TokenUtil.getUserId(token);
        List<UserInfo> userInfoList = bandingMapper.getUserInfoByToken(userId, project);
        if (userInfoList != null && userInfoList.size() > 0) {
            UserInfo userInfo = userInfoList.get(0);

            List<AssociatedAccount> associatedAccount = bandingMapper.getAssociatedAccountByToken(userId, project);
            if (associatedAccount == null || associatedAccount.size() < 1) {
                throw ServiceException.userException("The query fails", "查询失败");
            }
            AssociatedAccount associatedAccount1 = associatedAccount.get(0);
            UserInfoVo vo = new UserInfoVo();
            vo.setAccount(associatedAccount1.getAppUserAccount());
            String blockAddress = userInfo.getBlockAddress();
            String userAccount = userInfo.getUserAccount();
            vo.setBlockAddress(blockAddress);
            vo.setBlockId(userAccount);
            String total = getTotal(blockAddress, userAccount);
            vo.setTotalPrice(total);//总资产
            return vo;

        } else {
            throw ServiceException.userException("The query fails", "查询失败");
        }
    }

    @Override
    public void phoneCode(String phone, String token, String project) {
        String userId = "";
        if (token != null && !token.equals("")) {
            userId = TokenUtil.getUserId(token);
        }
        if (StringUtils.isEmpty(phone)) {
            throw ServiceException.userException("The username can't be empty!", "账号不能为空!");
        }
        if (!isphonenum(phone)) {
            throw ServiceException.userException("The mobile phone number is not correct!", "手机号格式不正确!");
        }
        int i = bandingMapper.getUserInfoByPhone(phone);
        if (i > 0) {
            throw ServiceException.userException("The mobile phone number has already been registered!", "该手机号已被注册，请直接登录!");
        }
        int j = bandingMapper.getUserInfoByTokenCount(userId, project);
        if (j > 0) {
            throw ServiceException.userException("This account has been binding account!", "该帐号已经绑定过靓号!");
        }

        try {
            handleCellphones(phone, 4, userId, project);
        } catch (InterruptedException e) {
            throw ServiceException.userException("Verification code sent failure!", "验证码发送失败!");
        }

    }

    @Override
    public Object affirmCode(String phone, String token, String project, String code) {

        String userId = "";
        if (token != null && !token.equals("")) {
            userId = TokenUtil.getUserId(token);
        }
        if (StringUtils.isEmpty(code) || (code.trim()).length() != 6) {
            throw ServiceException.userException("The verification code is illegal!", "验证码不正确!");
        }
        if (StringUtils.isEmpty(phone)) {
            throw ServiceException.userException("Mobile phone number can't be empty!", "手机号不能为空!");
        }
        phone = phone.trim();
        if (!isphonenum(phone)) {
            throw ServiceException.userException("The mobile phone number is not correct!", "手机号格式不正确!");
        }
        int i = bandingMapper.getUserInfoByPhone(phone);
        if (i > 0) {
            throw ServiceException.userException("The username has been registered!", "该账号已经被注册过!");
        }
        String type = "4";
        List<InvitationUserCode> pushInvitationCodes = bandingMapper.getByCodeByaccount(code, phone, project, userId, type);
        if (pushInvitationCodes == null || pushInvitationCodes.size() == 0) {
            throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");
        }
        InvitationUserCode ic = pushInvitationCodes.get(0);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expireTime = LocalDateTime.parse(ic.getExpireTime());
        if (ic.isUsed() || now.isAfter(expireTime)) {
            throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");
        }
        ic.setUsed(true);
        ic.setUseTime(now.toString());
        bandingMapper.updateInvitationCode(ic);
        return phone;
    }

    @Override
    public Object saveIdentityCard(AntiAddiction antiAddiction, String userAccount, HttpServletRequest request) {
        String token = antiAddiction.getToken();
        String userId = "";
        if (token != null && !token.equals("")) {
            userId = TokenUtil.getUserId(token);
        }

        antiAddiction.setToken(userId);
        String identityCard = antiAddiction.getIdentityCard();
        getIdCard(identityCard);

        int i = userAccount.indexOf("@");
        if (i < 0) {
            int d = bandingMapper.getUserInfoByPhone(userAccount);
            if (d > 0) {
                throw ServiceException.userException("", "此手机号已经被注册!");
            }
        } else {
            int b = bandingMapper.getUserInfoByEmail(userAccount);
            if (b > 0) {
                throw ServiceException.userException("", "此邮箱已经被注册!");
            }
        }
        List<UserInfo> userInfoList1 = bandingMapper.getUserInfoByIdentiy(antiAddiction.getIdentityCard());
        if (userInfoList1 != null && userInfoList1.size() > 0) {
            throw ServiceException.userException("", "该证件号已被注册使用了！");
        }
        //查询该身份证是否存在表中
        List<AntiAddiction> list = bandingMapper.getAntiAddiction(identityCard);
        if (list != null && list.size() > 0) {
            throw ServiceException.userException("", "该证件号已被注册使用了！");
        }
        antiAddiction.setUserId(userAccount);//申请的帐号
        antiAddiction.setUserName(antiAddiction.getUserName());
        antiAddiction.setCreateTime(getCurrentTime());
        antiAddiction.setState(1);
        antiAddiction.setLevel("1");
        int j = bandingMapper.saveAntiAddiction(antiAddiction);
        if (j > 0) {

            String uUid = UuidUtil.getUUid();

            UserInfo userInfo = new UserInfo();

            String account = antiAddiction.getUserId();//申请的帐号
            int c = account.indexOf("@");
            if (c < 0) {
                userInfo.setPhoneNum(account);
            } else {
                userInfo.setEmail(account);
            }
            String hash = null;
            try {
                hash = PushPasswordHash.createHash("certification_!@#13569");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                throw ServiceException.userException("Registration failed!", "注册失败!");
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
                throw ServiceException.userException("Registration failed!", "注册失败!");
            }
            userInfo.setUserPwd(hash);
            userInfo.setIdentityCard(identityCard);//身份证
            String blockAddress = getBlockAddress();//区块地址
            userInfo.setBlockAddress(blockAddress);
            String userAccount1 = getUserAccount();//靓号
            userInfo.setUserAccount(userAccount1);
            userInfo.setuId(uUid);
            userInfo.setCreateTime(getCurrentTime());
            userInfo.setHeadPicPath("http://api.timetreaty.org/pictures/6fd31824.png");
            bandingMapper.saveUserAccount(userInfo);
            try {
                UrlLoad.loadGet(IpResourceLocation.TT_EGGWORLD_IP+"/ttc-eggworld/ttc/updateUserInfoBalance?eggId="+null+"&blockId="+userInfo.getId()+"&address="+userInfo.getBlockAddress()+"&state="+0);

            } catch (Exception e) {
                e.printStackTrace();
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

            UserInfoVo vo = new UserInfoVo();
            vo.setToken(jwt);
            vo.setBlockAddress(blockAddress);
            vo.setBlockId(userAccount1);
            Integer id = userInfo.getId();
            List<AssociatedAccount> associatedAccountList = bandingMapper.getAssociatedAccountByToken(id+"","4");
            if (associatedAccountList == null || associatedAccountList.size() < 1) {
                AssociatedAccount associatedAccount = new AssociatedAccount();
                associatedAccount.setuId(UuidUtil.getUUid());
                associatedAccount.setUserId(userInfo.getuId());
                associatedAccount.setAppName("4");
                associatedAccount.setAppUserAccount(userInfo.getUserAccount());
                associatedAccount.setCreateTime(getCurrentTime());
                associatedAccount.setToken(id+"");
                bandingMapper.saveAssociatedAccount(associatedAccount);
            }

            return vo;
        } else {
            throw ServiceException.userException("", "生成失败!");
        }
    }

    @Override
    public void updatePwd(String pwd, String token, HttpServletRequest request, String blockId) {

        String userId = TokenUtil.getUserId(token);
        List<UserInfo> userInfoList = bandingMapper.getUserInfoById(userId, blockId);
        if (userInfoList != null && userInfoList.size() > 0) {

            UserInfo userInfo = userInfoList.get(0);
            String hash = null;
            try {
                hash = PushPasswordHash.createHash(pwd);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                throw ServiceException.userException("Password change failed!", "密码修改失败!");
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
                throw ServiceException.userException("Password change failed!", "密码修改失败!");
            }
            userInfo.setUserPwd(hash);
            bandingMapper.updateUserInfoPwd(userInfo);

        } else {
            throw ServiceException.userException("The query fails", "查询失败");
        }

    }

    @Override
    public Object judgeBlockId(String blockId) {

        List<UserInfo> userInfoList = bandingMapper.judgeBlockId(blockId);
        if (userInfoList == null || userInfoList.size() < 1) {
            throw ServiceException.userException("The block ID does not exist", "该区块ID不存在!");
        }
        UserInfo userInfo = userInfoList.get(0);
        String phoneNum = userInfo.getPhoneNum();
        String replaceAll = phoneNum.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        return replaceAll;

    }

    @Override
    public void blockIdPhoneCode(String blockId) {

        List<UserInfo> userInfoList = bandingMapper.judgeBlockId(blockId);
        if (userInfoList == null || userInfoList.size() < 1) {
            throw ServiceException.userException("The block ID does not exist", "该区块ID不存在!");
        }
        UserInfo userInfo = userInfoList.get(0);
        String phone = userInfo.getPhoneNum();

        if (StringUtils.isEmpty(phone)) {
            throw ServiceException.userException("The username can't be empty!", "账号不能为空!");
        }
        if (!isphonenum(phone)) {
            throw ServiceException.userException("The mobile phone number is not correct!", "手机号格式不正确!");
        }

        try {
            handleCellphones(phone, 5, "", "");
        } catch (InterruptedException e) {
            throw ServiceException.userException("Verification code sent failure!", "验证码发送失败!");
        }

    }

    @Override
    public void findPwd(String pwd, String code, String blockId) {

        List<UserInfo> userInfoList = bandingMapper.judgeBlockId(blockId);
        if (userInfoList == null || userInfoList.size() < 1) {
            throw ServiceException.userException("The block ID does not exist", "该区块ID不存在!");
        }
        if (userInfoList != null && userInfoList.size() > 0) {
            UserInfo userInfo = userInfoList.get(0);
            String phone = userInfo.getPhoneNum();


            if (StringUtils.isEmpty(code) || (code.trim()).length() != 6) {
                throw ServiceException.userException("The verification code is illegal!", "验证码不正确!");
            }
            if (StringUtils.isEmpty(phone)) {
                throw ServiceException.userException("Mobile phone number can't be empty!", "手机号不能为空!");
            }
            phone = phone.trim();
            if (!isphonenum(phone)) {
                throw ServiceException.userException("The mobile phone number is not correct!", "手机号格式不正确!");
            }

            String type = "5";
            List<InvitationUserCode> pushInvitationCodes = bandingMapper.getByCodeByaccount(code, phone, "", "", type);
            if (pushInvitationCodes == null || pushInvitationCodes.size() == 0) {
                throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");
            }
            InvitationUserCode ic = pushInvitationCodes.get(0);
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime expireTime = LocalDateTime.parse(ic.getExpireTime());
            if (ic.isUsed() || now.isAfter(expireTime)) {
                throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");
            }
            ic.setUsed(true);
            ic.setUseTime(now.toString());
            bandingMapper.updateInvitationCode(ic);

            String hash = null;
            try {
                hash = PushPasswordHash.createHash(pwd);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                throw ServiceException.userException("Password change failed!", "密码修改失败!");
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
                throw ServiceException.userException("Password change failed!", "密码修改失败!");
            }

            userInfo.setUserPwd(hash);
            bandingMapper.updateUserInfoPwd(userInfo);

        } else {
            throw ServiceException.userException("The query fails", "查询失败");
        }


    }

    @Override
    public void findBlockIdCode(String phone) {


        if (StringUtils.isEmpty(phone)) {
            throw ServiceException.userException("The username can't be empty!", "账号不能为空!");
        }
        if (!isphonenum(phone)) {
            throw ServiceException.userException("The mobile phone number is not correct!", "手机号格式不正确!");
        }

        try {
            handleCellphones(phone, 6, "", "");
        } catch (InterruptedException e) {
            throw ServiceException.userException("Verification code sent failure!", "验证码发送失败!");
        }

    }

    @Override
    public Object findBlockId(String code, String identityCard, String phone, HttpServletRequest request, String userName) {


        List<AntiAddiction> antiAddictionList = bandingMapper.getAntiAddictionByName(identityCard, userName, phone);
        if (antiAddictionList != null && antiAddictionList.size() > 0) {
            AntiAddiction antiAddiction = antiAddictionList.get(0);
            String userId1 = antiAddiction.getUserId();
            List<UserInfo> userInfoList = bandingMapper.getUserInfoByUserId(userId1);

            if (StringUtils.isEmpty(code) || (code.trim()).length() != 6) {
                throw ServiceException.userException("The verification code is illegal!", "验证码不正确!");
            }
            if (StringUtils.isEmpty(phone)) {
                throw ServiceException.userException("Mobile phone number can't be empty!", "手机号不能为空!");
            }
            phone = phone.trim();
            if (!isphonenum(phone)) {
                throw ServiceException.userException("The mobile phone number is not correct!", "手机号格式不正确!");
            }

            String type = "6";
            List<InvitationUserCode> pushInvitationCodes = bandingMapper.getByCodeByaccount(code, phone, "", "", type);
            if (pushInvitationCodes == null || pushInvitationCodes.size() == 0) {
                throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");
            }
            InvitationUserCode ic = pushInvitationCodes.get(0);
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime expireTime = LocalDateTime.parse(ic.getExpireTime());
            if (ic.isUsed() || now.isAfter(expireTime)) {
                throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");
            }
            ic.setUsed(true);
            ic.setUseTime(now.toString());
            bandingMapper.updateInvitationCode(ic);

            UserInfo userInfo = userInfoList.get(0);

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
            UserInfoVo vo = new UserInfoVo();
            vo.setBlockId(userInfo.getUserAccount());
            vo.setToken(jwt);
            return vo;

        } else {
            throw ServiceException.userException("Id card or mobile phone number is not correct!", "身份证或手机号不正确!");

        }


    }

    @Override
    public Object judgeBanding(String token, String project) {
        String userId = TokenUtil.getUserId(token);
        List<AssociatedAccount> associatedAccount = bandingMapper.getAssociatedAccountByToken(userId, project);
        UserInfoVo vo = new UserInfoVo();
        if (associatedAccount != null && associatedAccount.size() > 0) {

            List<UserInfo> userInfoByTokenList = bandingMapper.getUserInfoByToken(userId, project);
            if (userInfoByTokenList != null && userInfoByTokenList.size() > 0) {
                AssociatedAccount associatedAccount1 = associatedAccount.get(0);
                UserInfo userInfo = userInfoByTokenList.get(0);
                String jwt = PushAuthHelper.createJsonWebToken(userInfo.getId(), "pc", TimeUnit.HOURS, Constant.COOKIE_EXPIRE_HOURS);
                vo.setToken(jwt);
                vo.setPicUrl(userInfo.getHeadPicPath());
                vo.setBlockId(userInfo.getUserAccount());
                vo.setBlockAddress(userInfo.getBlockAddress());
                vo.setAccount(associatedAccount1.getAppUserAccount());
                vo.setStatus(1);
                String s = "";

                try {
                    s = UrlLoad.load(IpResourceLocation.EGG_IP + "/egg_game/api/blockinfo/getUserblockaddress",
                            "id=" + associatedAccount1.getToken());

                } catch (Exception e) {
                    e.printStackTrace();
                }
                JSONObject jsonObject = JSON.parseObject(s);
                String string = jsonObject.getString("state");
                JSONObject jsonObject1 = JSON.parseObject(string);
                String code = jsonObject1.getString("code");
                if ("20000".equals(code)) {
                    String data = jsonObject.getString("data");
                    if (data != null) {
                        userInfo.setBlockAddress(data);
                        bandingMapper.updataBlockAddress(userInfo);

                    }

                }
                return vo;
            } else {
                throw ServiceException.userException("The query fails", "查询失败");
            }

        } else {
            vo.setStatus(0);
            return vo;
        }
    }

    @Override
    public Object getAccredityIds(String token, String project, String account, String blockToken, HttpServletRequest request) {
        String userId = TokenUtil.getUserId(token);
        String blockUserId = TokenUtil.getUserId(blockToken);

        List<UserInfo> userInfoList = bandingMapper.getUserInfo(blockUserId);
        if (userInfoList == null || userInfoList.size() < 1) {
            throw ServiceException.userException("The account does not exist", "靓号不存在");
        }
        UserInfo userInfo = userInfoList.get(0);
        List<AssociatedAccount> associatedAccounts = bandingMapper.getAssociatedAccount(project, account);
        if (associatedAccounts != null && associatedAccounts.size() > 0) {
            throw ServiceException.userException("This account has been binding other account", "该区块身份已绑定其他帐号！");
        }
        List<AssociatedAccount> associatedAccountList = bandingMapper.getAssociatedAccountByblockId(userInfo.getUserAccount(), project);
        if (associatedAccountList != null && associatedAccountList.size() > 0) {
            throw ServiceException.userException("This account has been binding other account", "该区块身份已绑定其他帐号！");
        }

        AssociatedAccount associatedAccount = new AssociatedAccount();
        associatedAccount.setToken(userId);
        associatedAccount.setuId(UuidUtil.getUUid());
        associatedAccount.setAppName(project);
        associatedAccount.setAppUserAccount(account);
        associatedAccount.setCreateTime(getCurrentTime());
        associatedAccount.setUserId(userInfo.getuId());
        bandingMapper.saveAssociatedAccount(associatedAccount);

        UserInfoVo vo = new UserInfoVo();

        vo.setAccount(account);
        String blockAddress = userInfo.getBlockAddress();
        String userAccount = userInfo.getUserAccount();
        vo.setBlockAddress(blockAddress);
        vo.setBlockId(userAccount);
        String total = getTotal(blockAddress, userAccount);
        vo.setTotalPrice(total);//总资产
        vo.setPicUrl(userInfo.getHeadPicPath());

        return vo;
    }

    @Override
    public Object regOTC(String blockToken, String project) {

        String blockUserId = TokenUtil.getUserId(blockToken);

        List<UserInfo> userInfoList = bandingMapper.getUserInfo(blockUserId);
        if (userInfoList != null && userInfoList.size() > 0) {
            UserInfo userInfo = userInfoList.get(0);
            String s1 = userInfo.getuId();
            List<AssociatedAccount> associatedAccountList = bandingMapper.getAssociatedAccountByuIdOrProject(s1, project);
            if (associatedAccountList != null && associatedAccountList.size() > 0) {
                throw ServiceException.userException("This account has been binding other account", "该区块身份已绑定其他帐号！");
            }
            String userPwd = userInfo.getUserPwd();
            String userAccount = userInfo.getUserAccount();
            String s = null;
            try {
                s = UrlLoad.load(IpResourceLocation.OTC_IP + "/timetreaty_push/api/push/reg/blockIdReg",
                        "account=" + userAccount + "&pwd=" + userPwd);

            } catch (Exception e) {
                e.printStackTrace();
            }
            JSONObject jsonObject = JSON.parseObject(s);
            String string = jsonObject.getString("state");
            JSONObject jsonObject1 = JSON.parseObject(string);
            String code = jsonObject1.getString("code");
            if ("20000".equals(code)) {
                String data = jsonObject.getString("data");
                if (data == null) {
                    throw ServiceException.userException("This account has been binding other account", "该区块身份已绑定其他帐号！");
                }
                JSONObject jsonObject2 = JSON.parseObject(data);
                String username = jsonObject2.getString("username");
                String token = jsonObject2.getString("token");

                String userId = TokenUtil.getUserId(token);

                AssociatedAccount associatedAccount = new AssociatedAccount();
                associatedAccount.setToken(userId);
                associatedAccount.setuId(UuidUtil.getUUid());
                associatedAccount.setAppName(project);
                associatedAccount.setAppUserAccount(username);
                associatedAccount.setCreateTime(getCurrentTime());
                associatedAccount.setUserId(userInfo.getuId());
                bandingMapper.saveAssociatedAccount(associatedAccount);

                return username;


            } else if ("20002".equals(code)) {
                String msg = jsonObject1.getString("msg");
                throw ServiceException.userException("A system exception, contact customer service", msg);

            } else {
                throw ServiceException.userException("Activation failed, contact customer service", "激活失败，请联系客服");

            }

        } else {
            throw ServiceException.userException("Activation failed, contact customer service", "激活失败，请联系客服");

        }

    }

    @Override
    public void bangdingBlockIndex(String token, String project,String blockId) {

        String id = TokenUtil.getUserId(token);
        List<AssociatedAccount> associatedAccountList = bandingMapper.getAssociatedAccountByToken(id,project);
        if (associatedAccountList != null && associatedAccountList.size() > 0) {
            AssociatedAccount associatedAccount = associatedAccountList.get(0);
            String userId = associatedAccount.getUserId();
            List<UserInfo> userInfoByUserId = bandingMapper.getUserInfoByuId(userId);
            UserInfo userInfo = userInfoByUserId.get(0);
            String userAccount = userInfo.getUserAccount();
            if (userAccount.equals(blockId)) {
                try {
                    UrlLoad.loadGet(IpResourceLocation.TT_EGGWORLD_IP+"/ttc-eggworld/ttc/updateUserInfoBalance?eggId="+id+"&blockId="+userInfo.getId()+"&address="+userInfo.getBlockAddress()+"&state="+1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public String getBlockAddress() {
        //生成区块地址
        return CreateBitconAddressUtil.createAddress();
    }

    public String getUserAccount() {
        //生成靓号
        int i = (int) ((Math.random() * 9 + 1) * 100000);
        return i + "";
    }

    //判断身份证是否有效
    public void getIdCard(String identityCard) {
        try {
            boolean b = IDCard.IDCardValidate(identityCard);
            if (!b) {
                throw ServiceException.userException("Id number format error, please enter again!", "身份证号格式错误，请重新输入!");
            }
        } catch (Exception e) {
            throw ServiceException.userException("Id number format error, please enter again!", "身份证号格式错误，请重新输入!");
        }
    }

    private void handleCellphones(String account, Integer type, String userId, String project) throws InterruptedException {
        //生成6位短信验证码
        String smsCode = SmsCodeGenerator.genSmsCode();

        createInstance(smsCode, account, type, userId, project);
        SmsHolder holder = new SmsHolder(account, smsCode);
        SmsListenerBusPwd.REG_SMS_BUS.put(holder);
    }

    //注册靓号
    private void createInstance(String code, String account, Integer type, String userId, String project) {
        InvitationUserCode ic = new InvitationUserCode();
        ic.setAccount(account);
        ic.setCode(code);
        ic.setToken(userId);
        ic.setProject(project);
        ic.setExpire(TimeUnit.MINUTES, 30);
        ic.setType(type);
        int i = bandingMapper.saveInvitationCode(ic);

    }

    //判断手机号
    private boolean isphonenum(String account) {
        return account.startsWith("1") && account.length() == 11;
    }

    //当前时间
    public String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = format.format(new Date());
        return format1;
    }


    public String getTotal(String blockAddress, String userAddress) {
        return "0";
    }
}
