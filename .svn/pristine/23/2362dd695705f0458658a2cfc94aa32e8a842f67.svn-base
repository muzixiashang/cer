package com.liyunet.service.impl;

import com.alibaba.fastjson.JSON;
import com.liyunet.common.AuthHelper;
import com.liyunet.common.TokenInfo;
import com.liyunet.common.blockAddress.CreateBitconAddressUtil;
import com.liyunet.common.util.IDCard;
import com.liyunet.common.util.ImgUploadUtil;
import com.liyunet.common.util.TokenUtil;
import com.liyunet.common.util.UploadResourceLocation;
import com.liyunet.controller.AndroidController;
import com.liyunet.domain.*;
import com.liyunet.exception.ServiceException;
import com.liyunet.mapper.userMapper.CertificationMapper;
import com.liyunet.service.CertificationService;
import com.liyunet.util.UuidUtil;
import com.liyunet.vo.user.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 */
@Service("certificationService")
@Transactional
public class CertificationServiceImpl implements CertificationService {


    private final CertificationMapper certificationMapper;

    @Autowired
    public CertificationServiceImpl(CertificationMapper certificationMapper) {
        this.certificationMapper = certificationMapper;
    }


    @Override
    public void saveIdentityCar(AntiAddiction antiAddiction, HttpServletRequest request, String userAccount) {

        String userId = TokenUtil.getUserId(antiAddiction.getToken());
        antiAddiction.setToken(userId);
        String identityCard = antiAddiction.getIdentityCard();
        getIdCard(identityCard);

        int i = userAccount.indexOf("@");
        if (i < 0) {
            List<UserInfo> userInfoList = certificationMapper.getUserInfoByPhone(userAccount);
            if (userInfoList != null && userInfoList.size() > 0) {
                throw ServiceException.userException("", "此手机号已经被注册!");
            }
        } else {
            List<UserInfo> userInfoList = certificationMapper.getUserInfoByEmail(userAccount);
            if (userInfoList != null && userInfoList.size() > 0) {
                throw ServiceException.userException("", "此邮箱已经被注册!");
            }
        }

        List<UserInfo> userInfoList1 = certificationMapper.getUserInfoByIdentiy(antiAddiction.getIdentityCard());
        if (userInfoList1 != null && userInfoList1.size() > 0) {
            throw ServiceException.userException("", "该证件号已被注册使用了!");
        }
        //查询该身份证是否存在表中
        List<AntiAddiction> list = certificationMapper.getAntiAddiction(identityCard);
        if (list != null && list.size() > 0) {
            //该身份证存在表中
            AntiAddiction an = list.get(0);
            int state = an.getState();
            String frontUrl = antiAddiction.getFrontUrl();//正面
            String backUrl = antiAddiction.getBackUrl();//背面
            if (1 == state) {
                //通过验证了
                throw ServiceException.userException("", "已经通过身份验证!");

            } else if (4 == state) {
                throw ServiceException.userException("", "此身份证号还在进行人工审核中，请耐心等待!");
            } else if (2 == state) {
                try {
                    if (-1 != (frontUrl.indexOf(";base64"))) {
                        frontUrl = getUrl(frontUrl, request);
                    }
                    if (-1 != (backUrl.indexOf(";base64"))) {
                        backUrl = getUrl(backUrl, request);
                    }
                } catch (Exception e) {
                    throw ServiceException.userException("Submission failed, please try again later!", "提交失败，请稍后重试!");

                }
                an.setUserId(userAccount);
                an.setFrontUrl(frontUrl);//正面
                an.setBackUrl(backUrl);//背面
                an.setUpdateTime(getCurrentTime());//重新提交时间
                an.setUserName(antiAddiction.getUserName());//姓名
                an.setIdentityCard(antiAddiction.getIdentityCard());//号码
                an.setLevel("1");
                an.setState(4);
                certificationMapper.updateAntiAddictionAll(an);
            }


        } else {
            //该身份证不存在表中
            String frontUrl = antiAddiction.getFrontUrl();
            String backUrl = antiAddiction.getBackUrl();
            try {
                if (-1 != (frontUrl.indexOf(";base64"))) {
                    frontUrl = getUrl(frontUrl, request);
                }
                if (-1 != (backUrl.indexOf(";base64"))) {
                    backUrl = getUrl(backUrl, request);
                }
            } catch (Exception e) {
                throw ServiceException.userException("Submission failed, please try again later!", "提交失败，请稍后重试!");

            }
            antiAddiction.setUserId(userAccount);//申请的帐号
            antiAddiction.setUserName(antiAddiction.getUserName());
            antiAddiction.setFrontUrl(frontUrl);
            antiAddiction.setBackUrl(backUrl);
            antiAddiction.setCreateTime(getCurrentTime());
            antiAddiction.setState(4);
            certificationMapper.saveAntiAddiction(antiAddiction);
        }

    }

    @Override
    public Object getStatus(String identityCard) {

//
//        getIdCard(identityCard);
//
//        //查询该身份证是否存在表中
//        List<AntiAddiction> list = certificationMapper.getAntiAddiction(identityCard);
//        AntiAddictionVO vo = new AntiAddictionVO();
//        if (list != null && list.size() > 0) {
//            AntiAddiction antiAddiction = list.get(0);
//            int state = antiAddiction.getState();
//            if (1 == state) {
//                int projectType1 = antiAddiction.getProjectType();
//                //通过验证了
//                if (projectType1 == projectType.intValue()) {
//                    String userId1 = antiAddiction.getUserId();
//                    if (userId1.equals(userId)) {
//                        vo.setAddress(antiAddiction.getWalletAddress());
//                        vo.setStatus(state);
//                    }
//                    return vo;
//                } else {
//                    String antiAddictionProject = antiAddiction.getAntiAddictionProject();
//                    List<String> stringList = JSON.parseObject(antiAddictionProject, List.class);
//                    boolean falg = false;
//                    for (String s : stringList) {
//                        if (s.equals(projectType + "")) {
//                            falg = true;
//                        }
//                    }
//                    if (!falg) {
//                        if (0 == projectType1) {
//                            throw ServiceException.userException("", "此身份证号在蛋生的世界验证通过，请前往蛋生的世界相关帐号获取地址!");
//                        } else if (1 == projectType1) {
//                            throw ServiceException.userException("", "此身份证号在OTC验证通过，请前往OTC相关帐号获取地址!");
//                        }
//                    }
//                    String userId1 = antiAddiction.getUserId();
//                    if (userId1.equals(userId)) {
//                        vo.setAddress(antiAddiction.getWalletAddress());
//                        vo.setStatus(state);
//                    }
//                    return vo;
//
//                }
//            }
//            vo.setStatus(state);
//            vo.setRejectReason(antiAddiction.getRejectReason());
//        } else {
//            vo.setStatus(0);
//        }

//        return vo;
        return null;
    }

    @Override
    public void bandingIdentityCard(String identityCard, String projectName, String blockAddress, String account) {
//
        getIdCard(identityCard);

        //查询该身份证是否存在表中
        List<UserInfo> userInfoList = certificationMapper.getUserInfoByidentityCard(identityCard, blockAddress);
        if (userInfoList != null && userInfoList.size() > 0) {
            UserInfo userInfo = userInfoList.get(0);
            String uId = userInfo.getuId();
            List<AssociatedAccount> associatedAccountList = certificationMapper.getAssociatedAccount(uId);
            for (AssociatedAccount associatedAccount : associatedAccountList) {
                if (associatedAccount.getAppName().equals(projectName)) {
                    throw ServiceException.userException("You have bound the application", "您已经绑定过该应用");
                }
            }
            AssociatedAccount associatedAccount = new AssociatedAccount();
            associatedAccount.setAppName(projectName);
            associatedAccount.setAppUserAccount(account);
            associatedAccount.setuId(UuidUtil.getUUid());
            associatedAccount.setUserId(uId);
            associatedAccount.setCreateTime(getCurrentTime());
            certificationMapper.saveAssociatedAccount(associatedAccount);
        } else {
            throw ServiceException.userException("", "区块地址或身份证无效");
        }

    }

    @Override
    public void saveUserAccount(String identityCard) {

        //查询该身份证是否存在表中
        List<AntiAddiction> list = certificationMapper.getAntiAddiction(identityCard);
        if (list != null && list.size() > 0) {

            List<UserInfo> userInfoList = certificationMapper.getUserInfo(identityCard);
            if (userInfoList != null && userInfoList.size() > 0) {
                throw ServiceException.userException("The id number already exists!", "该证件号已被注册使用了!");
            } else {
                String uUid = UuidUtil.getUUid();
                AntiAddiction antiAddiction = list.get(0);
                AssociatedAccount associatedAccount = new AssociatedAccount();
                associatedAccount.setUserId(uUid);
                associatedAccount.setuId(UuidUtil.getUUid());
                associatedAccount.setCreateTime(getCurrentTime());
                associatedAccount.setAppUserAccount(antiAddiction.getUserId());
                associatedAccount.setAppName(antiAddiction.getProjectName());
                associatedAccount.setToken(antiAddiction.getToken());
                certificationMapper.saveAssociatedAccount(associatedAccount);

                String projectName = antiAddiction.getProjectName();
                if ("0".equals(projectName)) {
                    UserInfo userInfo = new UserInfo();

                    String account = antiAddiction.getUserId();//申请的帐号
                    int i = account.indexOf("@");
                    if (i < 0) {
                        userInfo.setPhoneNum(account);
                    } else {
                        userInfo.setEmail(account);
                    }
                    String token = antiAddiction.getToken();
                    List<BlockUserInfo> blockUserInfoList = certificationMapper.getBlockUserInfo(Integer.parseInt(token));
                    BlockUserInfo blockUserInfo = blockUserInfoList.get(0);
                    String pwd = blockUserInfo.getPwd();


                    userInfo.setUserPwd(pwd);
                    userInfo.setIdentityCard(identityCard);//身份证
                    String blockAddress = getBlockAddress();//区块地址
                    userInfo.setBlockAddress(blockAddress);
                    String userAccount = getUserAccount();//靓号
                    userInfo.setUserAccount(userAccount);
                    userInfo.setuId(uUid);
                    userInfo.setCreateTime(getCurrentTime());
                    userInfo.setHeadPicPath("http://api.timetreaty.org/pictures/6fd31824.png");
                    certificationMapper.saveUserAccount(userInfo);
                }
            }

        } else {
            throw ServiceException.userException("There is no the id card!", "不存在该身份证!");
        }


    }

    @Override
    public Object getStatusByAccount(String account) {
        return null;
    }

    @Override
    public Object getStatusById(String token, String project) {

        String userId = TokenUtil.getUserId(token);
        List<UserInfo> userInfoList = certificationMapper.getUserInfoByToken(userId, project);
        BlockManageVo vo = new BlockManageVo();
        if (userInfoList != null && userInfoList.size() > 0) {
            UserInfo userInfo = userInfoList.get(0);

            vo.setPicUrl(userInfo.getHeadPicPath());//头像
            List<UserInfoLoginLog> loginLogList = certificationMapper.getUserInfoLoginLog(userInfo.getId());
            //最后登录
            if (loginLogList != null && loginLogList.size() > 0) {
                if (loginLogList.size() == 1) {
                    UserInfoLoginLog loginLog = loginLogList.get(0);
                    vo.setLastLogin(loginLog.getDate());
                } else {
                    UserInfoLoginLog loginLog = loginLogList.get(loginLogList.size() - 1);
                    vo.setLastLogin(loginLog.getDate());
                }
            } else {
                vo.setLastLogin(getCurrentTime());
            }

            vo.setBlickAddress(userInfo.getBlockAddress());//区块地址
            vo.setBlockId(userInfo.getUserAccount());//靓号

            List<AssociatedAccount> associatedAccountList = certificationMapper.getAssociatedAccount(userInfo.getuId());
            if (associatedAccountList != null && associatedAccountList.size() > 0) {
                List<MoneyDetails> moneyDetailsList = new ArrayList<>();
                double totalMoney = 0;
                for (AssociatedAccount associatedAccount : associatedAccountList) {
                    String appName = associatedAccount.getAppName();
                    MoneyDetails moneyDetails = new MoneyDetails();
                    moneyDetails.setAppName(appName);
                    String money = getMoney(appName);//获取价格
                    moneyDetails.setMoney(money);
                    double v = Double.parseDouble(money);
                    totalMoney += v;
                    moneyDetailsList.add(moneyDetails);

                }
                vo.setMoneyDetails(moneyDetailsList);
                vo.setTotalMoney(totalMoney + "");//总资产
            }


            vo.setStatus(1);//是否认证


            UserInfoLoginLog loginLog = new UserInfoLoginLog();
            loginLog.setUserId(userInfo.getId());
            loginLog.fromApp();
            loginLog.setDate(getCurrentTime());
            certificationMapper.saveLogin(loginLog);

            return vo;

        } else {

            List<AntiAddiction> antiAddictionList = certificationMapper.getAntiAddictionByToken(userId, project);
            if (antiAddictionList != null && antiAddictionList.size() > 0) {
                AntiAddiction antiAddiction = antiAddictionList.get(0);
                vo.setStatus(antiAddiction.getState());
                vo.setRejectReason(antiAddiction.getRejectReason());
            } else {
                vo.setStatus(0);
            }

            return vo;
        }

    }

    @Override
    public Object getSafetyById(String token, String project) {

        String userId = TokenUtil.getUserId(token);
        List<UserInfo> userInfoList = certificationMapper.getUserInfoByToken(userId, project);
        UserInfoAddressVo vo = new UserInfoAddressVo();
        if (userInfoList != null && userInfoList.size() > 0) {
            UserInfo userInfo = userInfoList.get(0);
            vo.setIdentityCard(userInfo.getIdentityCard());
            vo.setEmail(userInfo.getEmail());
            vo.setPhoneNum(userInfo.getPhoneNum());
            return vo;
        } else {
            return vo;
        }
    }

    @Override
    public Object getAccredityId(String token, String project) {

        String userId = TokenUtil.getUserId(token);
        List<UserInfo> userInfoList = certificationMapper.getUserInfoByToken(userId, project);
        List<MoneyDetails> moneyDetails = new ArrayList<>();
        if (userInfoList != null && userInfoList.size() > 0) {
            UserInfo userInfo = userInfoList.get(0);
            String uId = userInfo.getuId();
            List<AssociatedAccount> associatedAccountL = certificationMapper.getAssociatedAccount(uId);
            if (associatedAccountL != null && associatedAccountL.size() > 0) {
                for (AssociatedAccount account : associatedAccountL) {
                    MoneyDetails moneyDetails1 = new MoneyDetails();
                    moneyDetails1.setAccount(account.getAppUserAccount());
                    moneyDetails1.setAppName(account.getAppName());
                    moneyDetails.add(moneyDetails1);
                }
            }
            return moneyDetails;

        } else {
            return moneyDetails;
        }
    }

    @Override
    public Object getAppAuthorization(String token) {

        String userId = TokenUtil.getUserId(token);
        List<UserInfo> userInfoById = certificationMapper.getUserInfoByid(userId);
        UserInfo userInfo = userInfoById.get(0);
        List<AssociatedAccount> associatedAccountL = certificationMapper.getAssociatedAccount(userInfo.getuId());
        List<String> list = new ArrayList<>();
        if (associatedAccountL != null && associatedAccountL.size() > 0) {
            for (AssociatedAccount account : associatedAccountL) {
                list.add(account.getAppName());
            }
        }

        return list;
    }

    @Override
    public Object whetherBandingOtc(String token, String project) {

        String userId = TokenUtil.getUserId(token);
        List<UserInfo> userInfoById = certificationMapper.getUserInfoByid(userId);
        UserInfo userInfo = userInfoById.get(0);
        List<AssociatedAccount> associatedAccountList = certificationMapper.getAntiAddictionByUseridOrProject(userInfo.getuId(), project);
        if (associatedAccountList != null && associatedAccountList.size() > 0) {
            AssociatedAccount associatedAccount = associatedAccountList.get(0);
            String appUserAccount = associatedAccount.getAppUserAccount();
            return appUserAccount;
        } else {
            return 0;
        }
    }

    @Override
    public Object whetherKyc(String token, String project) {

        String userId = TokenUtil.getUserId(token);
        List<AssociatedAccount> associatedAccountList = certificationMapper.getAssociatedAccountByToken(userId, project);
        if (associatedAccountList != null && associatedAccountList.size() > 0) {

            AssociatedAccount associatedAccount = associatedAccountList.get(0);
            String uId = associatedAccount.getUserId();
            List<AntiAddiction> antiAddictionList = certificationMapper.getAntiAddictionByUserUId(uId);
            if (antiAddictionList != null && antiAddictionList.size() > 0) {
                AntiAddiction antiAddiction = antiAddictionList.get(0);
                String level = antiAddiction.getLevel();
                if (level == null || "".equals(level)) {
                    return 1;
                }
                return level;

            } else {
                return 0;
            }

        } else {
            return 0;
        }

    }

    @Override
    public Object whetherProjectBandingOtc(String token, String project, String ToProject) {

        String userId = TokenUtil.getUserId(token);
        List<AssociatedAccount> associatedAccounts = certificationMapper.getAssociatedAccountByToken(userId, project);
        if (associatedAccounts != null && associatedAccounts.size() > 0) {

            AssociatedAccount associatedAccount = associatedAccounts.get(0);
            String userId1 = associatedAccount.getUserId();
            List<UserInfo> userInfoById1 = certificationMapper.getUserInfoById(userId1);
            if (userInfoById1 != null && userInfoById1.size() > 0) {
                UserInfo userInfo = userInfoById1.get(0);
                String uId = userInfo.getuId();

                List<AssociatedAccount> associatedAccountList = certificationMapper.getAntiAddictionByUseridOrProject(uId, ToProject);
                if (associatedAccountList != null && associatedAccountList.size() > 0) {
                    return 1;
                } else {
                    return 0;
                }

            } else {
                return 0;
            }
        } else {
            return 0;
        }

    }

    @Override
    public Object getOTCAccountByToken(String token) {
        String userId = TokenUtil.getUserId(token);
        List<UserInfo> userInfoByid = certificationMapper.getUserInfoByid(userId);
        if (userInfoByid != null && userInfoByid.size() > 0) {
            UserInfo userInfo = userInfoByid.get(0);
            List<AssociatedAccount> associatedAccountList = certificationMapper.getAssociatedAccountByUserIdOrproject(userInfo.getuId(), "2");
            if (associatedAccountList != null && associatedAccountList.size() > 0) {

                AssociatedAccount associatedAccount = associatedAccountList.get(0);
                String appUserAccount = associatedAccount.getAppUserAccount();
                return appUserAccount;

            } else {
                throw ServiceException.userException("The block OTC identity is not binding!", "该区块身份还未绑定OTC!");
            }

        } else {
            throw ServiceException.userException("Query the phone number failed!", "查询手机号失败!");
        }

    }

    @Override
    public Object getAccountByBlockToken(String blockAddress, String project) {

        List<AssociatedAccount> associatedAccountList = certificationMapper.getUserInfoByBlockAddress(blockAddress, project);
        if (associatedAccountList != null && associatedAccountList.size() > 0) {
            AssociatedAccount associatedAccount = associatedAccountList.get(0);
            String appUserAccount = associatedAccount.getAppUserAccount();
            return appUserAccount;

        } else {
            throw ServiceException.userException("The block status did not bind the application！", "该区块身份未绑定该应用!");
        }

    }

    @Override
    public Object eggIdentityCard(String token, String project, String blockToken, AntiAddiction antiAddiction, HttpServletRequest request) {

        String userId = TokenUtil.getUserId(token);
        String blockUserId = TokenUtil.getUserId(blockToken);
        String frontUrl = antiAddiction.getFrontUrl();
        String backUrl = antiAddiction.getBackUrl();

        List<UserInfo> userInfoList = certificationMapper.getUserInfoByid(blockUserId);
        if (userInfoList != null && userInfoList.size() > 0) {
            UserInfo userInfo = userInfoList.get(0);
            String uId = userInfo.getuId();
            List<AssociatedAccount> associatedAccountByUserIdOrproject = certificationMapper.getAssociatedAccountByUserIdOrproject(uId, project);
            if (associatedAccountByUserIdOrproject == null || associatedAccountByUserIdOrproject.size() < 1) {
                throw ServiceException.userException("The block status did not bind the application！", "该区块身份未绑定该应用!");
            }
            AssociatedAccount associatedAccount = associatedAccountByUserIdOrproject.get(0);
            String token1 = associatedAccount.getToken();
            if (!token1.equals(userId)) {
                throw ServiceException.userException("Information save failed！", "信息保存失败!");
            }

            String identityCard = userInfo.getIdentityCard();
            List<AntiAddiction> antiAddictionList = certificationMapper.getAntiAddiction(identityCard);
            if (antiAddictionList != null && antiAddictionList.size() > 0) {
                AntiAddiction antiAddiction2 = antiAddictionList.get(0);
                String level = antiAddiction2.getLevel();
                Integer state = antiAddiction2.getState();
                if (frontUrl != null && !"".equals(frontUrl)) {

                    if (state == 1 && "2".equals(level)) {
                        throw ServiceException.userException("", "已通过实名认证!");
                    }
                    try {
                        if (-1 != (frontUrl.indexOf(";base64"))) {
                            frontUrl = getUrl(frontUrl, request);
                        }
                        if (-1 != (backUrl.indexOf(";base64"))) {
                            backUrl = getUrl(backUrl, request);
                        }
                    } catch (Exception e) {
                        throw ServiceException.userException("Submission failed, please try again later!", "提交失败，请稍后重试!");

                    }
                    antiAddiction2.setFrontUrl(frontUrl);
                    antiAddiction2.setBackUrl(backUrl);
                    antiAddiction2.setUserName(antiAddiction.getUserName());
                    antiAddiction2.setIdentityCard(antiAddiction.getIdentityCard());
                    antiAddiction2.setState(4);
                    antiAddiction2.setUpdateTime(getCurrentTime());
                    antiAddiction2.setuId(userInfo.getuId());
                    certificationMapper.updateAntiAddiction(antiAddiction2);

                } else {
                    if (state == 1 && "1".equals(level)) {
                        throw ServiceException.userException("", "已通过帐户认证!");
                    }
                    antiAddiction2.setUserName(antiAddiction.getUserName());
                    antiAddiction2.setIdentityCard(antiAddiction.getIdentityCard());
                    antiAddiction2.setState(4);
                    antiAddiction2.setUpdateTime(getCurrentTime());
                    antiAddiction2.setuId(userInfo.getuId());
                    certificationMapper.updateAntiAddiction(antiAddiction2);

                }
            } else {

                if (frontUrl != null && !"".equals(frontUrl)) {
                    try {
                        if (-1 != (frontUrl.indexOf(";base64"))) {
                            frontUrl = getUrl(frontUrl, request);
                        }
                        if (-1 != (backUrl.indexOf(";base64"))) {
                            backUrl = getUrl(backUrl, request);
                        }
                    } catch (Exception e) {
                        throw ServiceException.userException("Submission failed, please try again later!", "提交失败，请稍后重试!");

                    }


                }
                antiAddiction.setFrontUrl(frontUrl);
                antiAddiction.setBackUrl(backUrl);
                antiAddiction.setUserId(associatedAccount.getAppUserAccount());
                antiAddiction.setState(4);
                antiAddiction.setCreateTime(getCurrentTime());
                antiAddiction.setProjectName(project);
                antiAddiction.setToken(userId);
                antiAddiction.setLevel("0");
                antiAddiction.setuId(userInfo.getuId());

                certificationMapper.saveAntiAddiction(antiAddiction);
            }

            return 4;
        } else {
            throw ServiceException.userException("Block identity incorrectly！", "区块身份有误!");
        }

    }

    @Override
    public Object whetherKycContent(String token, String project, String blockToken) {


        String userId = TokenUtil.getUserId(token);
        String blockUserId = TokenUtil.getUserId(blockToken);

        List<UserInfo> userInfoList = certificationMapper.getUserInfoByid(blockUserId);
        if (userInfoList != null && userInfoList.size() > 0) {
            UserInfo userInfo = userInfoList.get(0);
            String uId = userInfo.getuId();
            List<AssociatedAccount> associatedAccountByUserIdOrproject = certificationMapper.getAssociatedAccountByUserIdOrproject(uId, project);
            if (associatedAccountByUserIdOrproject == null || associatedAccountByUserIdOrproject.size() < 1) {
                throw ServiceException.userException("The block status did not bind the application！", "该区块身份未绑定该应用!");
            }
            AssociatedAccount associatedAccount = associatedAccountByUserIdOrproject.get(0);
            String token1 = associatedAccount.getToken();
            if (!token1.equals(userId)) {
                throw ServiceException.userException("Information query failure！", "信息查询失败!");
            }
            String identityCard = userInfo.getIdentityCard();
            List<AntiAddiction> antiAddictionList = certificationMapper.getAntiAddiction(identityCard);
            if (antiAddictionList != null && antiAddictionList.size() > 0) {
                AntiAddiction antiAddiction = antiAddictionList.get(0);
                AntiAddictionAllVO vo = new AntiAddictionAllVO();
                vo.setBackUrl(antiAddiction.getBackUrl());
                vo.setFrontUrl(antiAddiction.getFrontUrl());
                vo.setIdentityCard(antiAddiction.getIdentityCard());
                vo.setLevel(antiAddiction.getLevel());
                vo.setState(antiAddiction.getState());
                vo.setUserName(antiAddiction.getUserName());
                vo.setRejectReason(antiAddiction.getRejectReason());
                return vo;

            } else {
                throw ServiceException.userException("Information query failure！", "信息查询失败!");
            }

        } else {
            throw ServiceException.userException("Information query failure！", "信息查询失败!");
        }

    }

    @Override
    public Object getBlockIdByAccountOrToken(String token, String project, String account) {

        String userId = TokenUtil.getUserId(token);
        List<UserInfo> userInfoList = certificationMapper.getAssociatedAccountByTokenOrAccount(userId, project, account);
        if (userInfoList != null && userInfoList.size() > 0) {
            UserInfo userInfo = userInfoList.get(0);

            return userInfo.getBlockAddress();

        } else {
            return null;

        }

    }

    @Override
    public Object getBlockId(String token, String project, String account) {
        String userId = TokenUtil.getUserId(token);
        List<UserInfo> userInfoList = certificationMapper.getAssociatedAccountByTokenOrAccount(userId, project, account);
        if (userInfoList != null && userInfoList.size() > 0) {
            UserInfo userInfo = userInfoList.get(0);

            return userInfo.getUserAccount();

        } else {
            return null;

        }
    }

    @Override
    public Object getEggKycSuccess(String project) {
        List<AntiAddiction> antiAddictionList = certificationMapper.getEggKycSuccess(project);
        List<String> stringList = new ArrayList<>();
        for (AntiAddiction antiAddiction : antiAddictionList) {
            stringList.add(antiAddiction.getToken());
        }
        return stringList;
    }

    @Override
    public Object whetherBandingEgg(String token, String project) {

        String userId = TokenUtil.getUserId(token);
        List<UserInfo> userInfoById = certificationMapper.getUserInfoByid(userId);
        UserInfo userInfo = userInfoById.get(0);
        List<AssociatedAccount> associatedAccountList = certificationMapper.getAntiAddictionByUseridOrProject(userInfo.getuId(), project);
        if (associatedAccountList != null && associatedAccountList.size() > 0) {
            AssociatedAccount associatedAccount = associatedAccountList.get(0);
            String id = associatedAccount.getToken();
            return id;
        } else {
            return null;
        }
    }

    private String getMoney(String appName) {

        if ("区块浏览器".equals(appName)) {
            return "0";
        }


        return "0";
    }

    public String getBlockAddress() {
        //生成区块地址
        return CreateBitconAddressUtil.createAddress();
    }

    public String getUserAccount() {
        //生成靓号
        int i = (int) ((Math.random() * 9 + 1) * 100000);
        int j = 0;
        while (j < 1){
            List<UserInfo> userInfoList = certificationMapper.getUserInfoByUserAccount(i);
            if (userInfoList != null && userInfoList.size() > 0 ) {
                i = (int) ((Math.random() * 9 + 1) * 100000);
            }else {
                j++;
            }
        }

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

    //当前时间
    public String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = format.format(new Date());
        return format1;
    }


    //图片上传
    public String getUrl(String url, HttpServletRequest request) {
        String serverPath = UploadResourceLocation.IDENTITYCARD_IMG_PATH;
        // 上传图片
        if ("-1".equals(url) || "".equals(url)) {
            url = "";
            return null;
        }
        if (StringUtils.isNoneBlank(url) && !"".equals(url) && !StringUtils.isBlank(url)) {
            ImgUploadUtil.ImgData img = ImgUploadUtil.decodeImg(url);
            try {
                img.saveToServer(serverPath);
                String name = img.getFullName();
                String picPath = UploadResourceLocation.getProjectPath(request) + UploadResourceLocation.IDENTITYCARD_IMG_URI
                        + name;
                return picPath;
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        return null;
    }
}
