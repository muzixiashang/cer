package com.liyunet.service.impl;

import com.liyunet.common.constant.Constant;
import com.liyunet.common.constant.DateHelper;
import com.liyunet.common.constant.InvitationCodeGenerator;
import com.liyunet.common.enums.EmailSMSTypeEnum;
import com.liyunet.common.mail.RegOrPwdEmailHolder;
import com.liyunet.common.pushPassword.PushPasswordHash;
import com.liyunet.common.pushQueue.PushSmsListenerBus;
import com.liyunet.common.pushSms.PushSmsCodeGenerator;
import com.liyunet.common.pushSms.PushSmsHolder;
import com.liyunet.common.pushToken.PushAuthHelper;
import com.liyunet.common.pushToken.PushCodeFactory;
import com.liyunet.common.pushToken.PushCommonVoUtil;
import com.liyunet.common.queue.RegPwdEmailListenerBus;
import com.liyunet.common.queue.SmsListenerBus;
import com.liyunet.common.queue.SmsListenerBusPwd;
import com.liyunet.common.sms.SmsCodeGenerator;
import com.liyunet.common.sms.SmsHolder;
import com.liyunet.domain.*;
import com.liyunet.exception.ServiceException;
import com.liyunet.mapper.PushDealMapper;
import com.liyunet.mapper.community.PushCommonMapper;
import com.liyunet.mapper.userMapper.PushUserMapper;
import com.liyunet.service.PushRegService;
import com.liyunet.util.PushRefinedCalculation;
import com.liyunet.util.UuidUtil;
import com.liyunet.vo.user.PushUserInfoVo;
import com.liyunet.vo.user.UserInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service("pushRegService")
@Transactional
public class PushRegServiceImpl implements PushRegService {

    private final PushDealMapper pushDealMapper;
    private final PushCommonMapper pushCommonMapper;
    private final PushUserMapper pushUserMapper;
    private final String pwd = "Timetreaty_new!@#^%$*186";
    private static Logger opLogger = LogManager.getLogger("exception");

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Autowired
    public PushRegServiceImpl(PushDealMapper pushDealMapper, PushCommonMapper pushCommonMapper, PushUserMapper pushUserMapper) {
        this.pushDealMapper = pushDealMapper;
        this.pushCommonMapper = pushCommonMapper;
        this.pushUserMapper = pushUserMapper;
    }


    private boolean isemail(String account) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(account);
        return matcher.find();
    }

    private boolean isphonenum(String account) {
        return account.startsWith("1") && account.length() == 11;
    }


//	@Override
//	@Transactional(readOnly = true)
//	public BaseJSONModel isEmailRegistered(String email) {
//		if(StringUtils.isEmpty(email))
//			return BaseJSONModel.serviceError("邮箱不能为空");
//		email = email.trim();
//
//		DataJSONModel json = new DataJSONModel();
//		JSONModelState state = json.createState();
//		boolean flag = userInfoDao.getUserInfoByName(email) != null;
//		json.setData(flag);
//		state.ajaxSuccess("success");
//		return json;
//	}

    @Override
    public PushUserInfoVo doReg(String account, String pwd, String code, HttpServletRequest request,
                                HttpServletResponse response, String languageType) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (StringUtils.isEmpty(account)) {
            throw ServiceException.userException("The username can't be empty!", "账号不能为空!");
        }
//		if(StringUtils.isEmpty(TTCsite)){
//			throw ServiceException.userException("TTC address cannot be empty!", "TTC地址不能为空!");
//		}
        account = account.trim();
//		TTCsite = TTCsite.trim();

        if (!isemail(account)) {
            throw ServiceException.userException("The mailbox format is not correct!", "邮箱格式不正确!");
        }
        int flag = pushUserMapper.getUserInfoByName(account);
        if (flag > 0) {
            throw ServiceException.userException("The username has been registered!", "该账号已经被注册过!");
        }

        if (StringUtils.isEmpty(pwd) || pwd.length() < 6 || pwd.length() > 20) {

            throw ServiceException.userException("The password length should be 6~20!", "密码长度6~20!");

        } else if (StringUtils.isEmpty(code) || (code = code.trim()).length() != 6) {
            throw ServiceException.userException("The verification code is illegal!", "验证码不合法!");
        } else {
            List<InvitationCode> pushInvitationCodes = pushCommonMapper.getByCodeByaccount(code, account);
            if (pushInvitationCodes == null || pushInvitationCodes.size() == 0) {

                throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");

            }
            InvitationCode ic = pushInvitationCodes.get(0);
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime expireTime = LocalDateTime.parse(ic.getExpireTime());
            if (ic.isUsed() || now.isAfter(expireTime)) {

                throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");

            } else {
                ic.setUsed(true);
                ic.setUseTime(now.toString());
                BlockUserInfo enpUser = new BlockUserInfo();
                enpUser.setUser_name(account);
                if (isemail(account)) {
                    enpUser.setEmail_(account);
                } else {

                    throw ServiceException.userException("The mailbox format is not correc!", "邮箱格式不正确!");

                }
                enpUser.setPwd_(PushPasswordHash.createHash(pwd));
                enpUser.setHead_pic_path("http://api.timetreaty.org/pictures/6fd31824.png");//默认头像
                enpUser.setCreate_time(DateHelper.getFormatedDateStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
                enpUser.setUser_sex(0);
                enpUser.setuId(UuidUtil.getUUid());
                enpUser.setNick_name(getEmail(account));
                enpUser.setuId(UuidUtil.getUUid());
                pushUserMapper.savePushUserInfo(enpUser);
                Integer id = enpUser.getId();
                //终端访问来源
                String reqFrom = request.getHeader("From");
                reqFrom = "pc";
                //创建一个365天过期的JSON WEB TOKEN
                String jwt = PushAuthHelper.createJsonWebToken(enpUser.getId(), reqFrom, TimeUnit.HOURS, Constant.COOKIE_EXPIRE_HOURS);
                String path = request.getContextPath();
                if (StringUtils.isEmpty(path))
                    path = "/";
                //写入cookie中
                Cookie cookie = new Cookie("jwt", jwt);
                cookie.setHttpOnly(true);
                cookie.setMaxAge((int) TimeUnit.HOURS.toSeconds(Constant.COOKIE_EXPIRE_HOURS));
                cookie.setDomain(request.getServerName());
                cookie.setPath(path);
                response.addCookie(cookie);

                LoginLog loginLog = new LoginLog();
                loginLog.setUserId(enpUser.getId());
                loginLog.fromApp();
                createLoginLog(loginLog);

                PushUserInfoVo vo = new PushUserInfoVo();
                vo.setId(enpUser.getId());
                vo.setNickName(enpUser.getNick_name());
                vo.setUsername(enpUser.getUsername());
                vo.setUserDesc(enpUser.getUserDesc() == null ? "" : enpUser.getUserDesc());
                String uesrSexStr = enpUser.getUserSex() == 0 ? "男" : "女";
                if ("en".equals(languageType)) {
                    uesrSexStr = enpUser.getUserSex() == 0 ? "male" : "female";
                } else if ("zh".equals(languageType)) {
                    uesrSexStr = enpUser.getUserSex() == 0 ? "男" : "女";
                }
                vo.setUserSexStr(uesrSexStr);
                vo.setUserSex(enpUser.getUserSex());
                vo.setUserHeadPicPath(enpUser.getHeadPicPath() == null ? "" : enpUser.getHeadPicPath());
                vo.setToken(jwt);
                vo.setPhoneNum(enpUser.getPhoneNum() == null ? "" : enpUser.getPhoneNum());
                return vo;
            }
        }
    }

    @Override
    public void postVerificationCode(String account, String languageType, HttpServletRequest request) {
        if (StringUtils.isEmpty(account)) {
            throw ServiceException.userException("The username can't be empty!", "账号不能为空！");
        }
        account = account.trim();

        int flag = pushUserMapper.getUserInfoByName(account);
        if (flag > 0) {
            throw ServiceException.userException("The username has been registered!", "该账号已经被注册过！");
        }

        if (isemail(account)) {
            try {
                handleEmail(account, RegOrPwdEmailHolder.RegOrPwdEmailType.REG, languageType);
            } catch (Exception e) {
                throw ServiceException.userException("The username has been registered!", "系统正忙着。请稍后再试！");
            }
        } else {
            throw ServiceException.userException("The mailbox format is not correct!", "请输入正确的邮箱！");
        }

    }


    private void handleEmail(String account, RegOrPwdEmailHolder.RegOrPwdEmailType type, String languageType) throws InterruptedException {
        String genCode = InvitationCodeGenerator.genCode();
        while (isCodeExists(genCode)) {
            genCode = InvitationCodeGenerator.genCode();
        }
        createInstance(genCode, account, EmailSMSTypeEnum.EMAIL);
        RegOrPwdEmailHolder holder = new RegOrPwdEmailHolder(account, genCode, type);
        holder.setLanguageType(languageType);
        RegPwdEmailListenerBus.REG_PWD_BUS.put(holder);
    }

    private void handleEmail(String account, RegOrPwdEmailHolder.RegOrPwdEmailType type, String email, String languageType) throws InterruptedException {
        String genCode = InvitationCodeGenerator.genCode();
        while (isCodeExists(genCode)) {
            genCode = InvitationCodeGenerator.genCode();
        }
        createInstance(genCode, account, 2);
        RegOrPwdEmailHolder holder = new RegOrPwdEmailHolder(account, genCode, type);
        holder.setLanguageType(languageType);
        RegPwdEmailListenerBus.REG_PWD_BUS.put(holder);
    }

    private boolean isCodeExists(String code) {
        if (StringUtils.isEmpty(code)) return true;
        int i = pushCommonMapper.getByCode(code);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    //修改密码
    private InvitationCode createInstance(String code, String account, Integer type) {
        InvitationCode ic = new InvitationCode();
        ic.setAccount(account);
        ic.setCode(code);
        ic.setExpire(TimeUnit.MINUTES, 30);
        ic.setType(2);
        int i = pushCommonMapper.saveInvitation_code(ic);
        if (i > 0) {
            return ic;
        } else {
            return null;
        }
    }

    //邮箱注册验证码
    private InvitationCode createInstance(String code, String account, EmailSMSTypeEnum type) {
        InvitationCode ic = new InvitationCode();
        ic.setAccount(account);
        ic.setCode(code);
        ic.setExpire(TimeUnit.MINUTES, 30);
        ic.setType(0);
        int i = pushCommonMapper.saveInvitation_code(ic);
        if (i > 0) {
            return ic;
        } else {
            return null;
        }
    }



    //手机注册验证码
    private InvitationCode createInstance(String code, String account, String type) {
        InvitationCode ic = new InvitationCode();
        ic.setAccount(account);
        ic.setCode(code);
        ic.setExpire(TimeUnit.MINUTES, 30);
        ic.setType(Integer.parseInt(type));
        int i = pushCommonMapper.saveInvitation_code(ic);
        if (i > 0) {
            return ic;
        } else {
            return null;
        }
    }

    //绑定手机
    private InvitationCode createInstance(String code, String account, Integer type, String uId) {
        InvitationCode ic = new InvitationCode();
        ic.setAccount(account);
        ic.setCode(code);
        ic.setExpire(TimeUnit.MINUTES, 30);
        ic.setType(1);
        ic.setUserId(uId);
        System.out.println(code);
        int i = pushCommonMapper.saveInvitation_code(ic);
        System.out.println(i);
        if (i > 0) {
            return ic;
        } else {
            return null;
        }
    }

    @Override
    public void forgetPwd(String account, String languageType) {
        if (StringUtils.isEmpty(account)) {
            throw ServiceException.userException("The username can't be empty!", "账号不能为空!");
        }
        account = account.trim();

        if (isemail(account)) {
            try {
                handleEmail(account, RegOrPwdEmailHolder.RegOrPwdEmailType.PWD, "email", languageType);
            } catch (Exception e) {
                throw ServiceException.userException("The system is busy. Please try again later!", "系统繁忙,请稍后重试!");
            }
        } else if (isphonenum(account)) {
            try {
                handleCellphones(account, "2");
            } catch (InterruptedException e) {
                throw ServiceException.userException("The system is busy. Please try again later!", "系统繁忙,请稍后重试!");
            }
        } else {
            throw ServiceException.userException("Username format is wrong!", "账号格式错误!");
        }
    }
//
    @Override
    public PushUserInfoVo chgPwd(String account, String pwd, String code, String languageType) {

        List<BlockUserInfo> enpUsers = pushUserMapper.getUserInfoByUserName(account);
        if (enpUsers == null || enpUsers.size() == 0) {

            throw ServiceException.userException("The username does not exist!", "账号不存在!");

        }
        BlockUserInfo enpUser = enpUsers.get(0);
        if (StringUtils.isEmpty(pwd) || pwd.length() < 6 || pwd.length() > 20) {

            throw ServiceException.userException("Password is illegal!", "密码至少为6位!");

        }

        List<InvitationCode> PushInvitationCodes = pushCommonMapper.getByCodeByaccount(code, account);
        if (PushInvitationCodes == null || PushInvitationCodes.size() == 0) {

            throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");

        }
        InvitationCode ic = PushInvitationCodes.get(0);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expireTime = LocalDateTime.parse(ic.getExpireTime());
        if (!(2 == ic.getType())) {
            throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");
        }
        if (ic.isUsed()) {
            throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");
        }
        if (ic.isUsed() || now.isAfter(expireTime)) {

            throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");

        } else {
            try {
                enpUser.setPwd_(PushPasswordHash.createHash(pwd.trim()));
            } catch (Exception e) {
                throw ServiceException.userException("The system is busy. Please try again later!", "系统繁忙,请稍后重试!");
            }
            ic.setUsed(true);
            ic.setUseTime(now.toString());
            int j = pushCommonMapper.updateUserInfoForPwd(enpUser);
            if (j <= 0) {
                throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");
            }
            int i = pushCommonMapper.updateInvitationCode(ic);
            if (i <= 0) {
                throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");
            }

        }

        return PushCommonVoUtil.getUserInfoVo(enpUser, languageType, "pc");

    }

    @Override
    public void ctycode(String account, String type, HttpServletRequest request) {
        if (StringUtils.isEmpty(account)) {
            throw ServiceException.userException("The username can't be empty!", "账号不能为空!");
        }
        if (!isphonenum(account)) {
            throw ServiceException.userException("The mobile phone number is not correct!", "手机号格式不正确!");
        }

        if ("0".equals(type)) {//1已有账号认证 0注册认证
            int i = pushUserMapper.getUserInfoByVo(account);
            if (i > 0) {
                throw ServiceException.userException("The username has been registered!", "该账号已经被注册过!");
            }
        } else {
            throw ServiceException.userException("Verification code sent failure!", "验证码发送失败!");
        }
        try {
            handleCellphones(account, type);
        } catch (InterruptedException e) {
            throw ServiceException.userException("Verification code sent failure!", "验证码发送失败!");
        }
    }


    @Override
    @Transactional
    public PushUserInfoVo mobileReg(String account, String pwd, String code, HttpServletRequest request,
                                HttpServletResponse response, String languageType) {
        if (StringUtils.isEmpty(account)) {
            throw ServiceException.userException("The username can't be empty!", "账号不能为空!");
        }
        account = account.trim();
        if (!isphonenum(account)) {
            throw ServiceException.userException("The mobile phone number is not correct!", "手机号格式不正确!");
        }
        int flag = pushUserMapper.getUserInfoByName(account);
        if (flag > 0) {
            throw ServiceException.userException("The username has been registered!", "该账号已经被注册过!");

        }

        if (StringUtils.isEmpty(pwd) || pwd.length() < 8 || pwd.length() > 20) {

            throw ServiceException.userException("The password length should be 8~20!", "密码长度8~20!");

        } else if (StringUtils.isEmpty(code) || (code = code.trim()).length() != 6) {

            throw ServiceException.userException("The verification code is illegal!", "验证码不合法!");

        } else {
            List<InvitationCode> pushInvitationCodes = pushCommonMapper.getByCodeByaccount(code, account);
            if (pushInvitationCodes == null || pushInvitationCodes.size() == 0) {

                throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");

            }
            InvitationCode ic = pushInvitationCodes.get(0);
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime expireTime = LocalDateTime.parse(ic.getExpireTime());
            if (ic.isUsed() || now.isAfter(expireTime)) {
                throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");

            } else {
                if (!account.equals(ic.getAccount())) {
                    throw ServiceException.userException("The current account is not consistent with the received verification code account!", "当前账号与接收验证码账号不一致!");

                }
                ic.setUsed(true);
                ic.setUseTime(now.toString());
                BlockUserInfo enpUser = new BlockUserInfo();
                enpUser.setUser_name(account);
                if (isphonenum(account)) {
                    enpUser.setPhoneNum(account);
                    String replaceAll = account.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
                    enpUser.setNick_name(replaceAll);
                } else {
                    throw ServiceException.userException("The mobile phone number is not correct!", "手机号格式不正确!");
                }
                String date = DateHelper.getFormatedDateStr(new Date(), "yyyy-MM-dd HH:mm:ss");
                String hash = null;
                try {
                    hash = PushPasswordHash.createHash(pwd);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                    throw ServiceException.userException("Registration failed!", "注册失败!");
                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                    throw ServiceException.userException("Registration failed!", "注册失败!");
                }
                enpUser.setPwd_(hash);
                enpUser.setHead_pic_path("http://api.timetreaty.org/pictures/6fd31824.png");//默认头像
                enpUser.setCreate_time(date);
                enpUser.setUser_sex(0);
                enpUser.setuId(UuidUtil.getUUid());
                pushUserMapper.savePushUserInfo(enpUser);
                Integer id = enpUser.getId();

                //终端访问来源
                String reqFrom = request.getHeader("From");
                reqFrom = "pc";
                //创建一个365天过期的JSON WEB TOKEN
                String jwt = PushAuthHelper.createJsonWebToken(enpUser.getId(), reqFrom, TimeUnit.HOURS, Constant.COOKIE_EXPIRE_HOURS);
                String path = request.getContextPath();
                if (StringUtils.isEmpty(path))
                    path = "/";
                //写入cookie中
                Cookie cookie = new Cookie("jwt", jwt);
                cookie.setHttpOnly(true);
                cookie.setMaxAge((int) TimeUnit.HOURS.toSeconds(Constant.COOKIE_EXPIRE_HOURS));
                cookie.setDomain(request.getServerName());
                cookie.setPath(path);
                response.addCookie(cookie);

                LoginLog loginLog = new LoginLog();
                loginLog.setUserId(enpUser.getId());
                loginLog.fromApp();
                createLoginLog(loginLog);

                PushUserInfoVo vo = new PushUserInfoVo();
                vo.setId(enpUser.getId());
                vo.setNickName(enpUser.getNick_name());
                vo.setUsername(enpUser.getUsername());
                vo.setUserDesc(enpUser.getUserDesc() == null ? "" : enpUser.getUserDesc());
                String uesrSexStr = enpUser.getUserSex() == 0 ? "男" : "女";
                if ("en".equals(languageType)) {
                    uesrSexStr = enpUser.getUserSex() == 0 ? "male" : "female";
                } else if ("zh".equals(languageType)) {
                    uesrSexStr = enpUser.getUserSex() == 0 ? "男" : "女";
                }
                vo.setUserSexStr(uesrSexStr);
                vo.setUserSex(enpUser.getUserSex());
                vo.setUserHeadPicPath(enpUser.getHeadPicPath() == null ? "" : enpUser.getHeadPicPath());
                vo.setToken(jwt);
                vo.setPhoneNum(enpUser.getPhoneNum());
                return vo;
            }
        }

    }


    @Override
    public void createLoginLog(LoginLog log) {
//        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        log.setDate(getCurrentTime());
        log.setTime(time.toString());
        pushCommonMapper.LoginLog(log);
    }

    //
//
//	@Override
//	public BaseJSONModel queryTreaty(String eth, String token, String languageType) {
//		TokenInfo ti= AuthHelper.verifyToken(token);
//		if(ti==null || ti.getUserId()==null){
//			if("en".equals(languageType)){
//				return BaseJSONModel.serviceError("Session is invalid, please login again!");
//			}else if("zh".equals(languageType)){
//				return BaseJSONModel.serviceError("session失效,请重新登录!");
//			}
//		}
//		//UserInfo user = userInfoDao.get(UserInfo.class, ti.getUserId());
//		//查询代码...
//		//eth
//		//查询代码...end
//		return null;
//	}
    @Override
    public void findPhonecode(String account, String id, String languageType) {

        if (StringUtils.isEmpty(account)) {
            throw ServiceException.userException("The username can't be empty!", "账号不能为空!");
        }
        if (!isphonenum(account)) {
            throw ServiceException.userException("The mobile phone number is not correct!", "手机号格式不正确!");
        }
        List<BlockUserInfo> userInfoList = pushDealMapper.getPushUserInfo(id);
        if (userInfoList != null && userInfoList.size() > 0) {
            BlockUserInfo pushUserInfo = userInfoList.get(0);
            String phoneNum = pushUserInfo.getPhoneNum();
            if (phoneNum != null && !("".equals(phoneNum))) {
                throw ServiceException.userException("You already have a binding mobile phone number!", "您已经有绑定手机号码!");
            }
            String s = pushUserInfo.getuId();
            try {
                handleCellphone(account, s);
            } catch (InterruptedException e) {
                throw ServiceException.userException("Verification code sent failure!", "验证码发送失败!");
            }
        }

    }

    @Override
    public List<BlockUserInfo> getUserByAccount(String userName) {

        List<BlockUserInfo> list = pushUserMapper.getUserInfoByUserName(userName);
        return list;
    }

    @Override
    public List<UserInfo> getUserInfo(String username) {

        List<UserInfo> userInfoList = pushUserMapper.getUserInfoByAccount(username);
        return userInfoList;
    }

    @Override
    public List<BlockUserInfo> getBlockUserInfo(String uId) {
        List<BlockUserInfo> blockUserInfoList = pushUserMapper.getBlockUserInfo(uId);
        return blockUserInfoList;
    }

    @Override
    public List<AssociatedAccount> getAntiAddiction(String uId, String project) {

        List<AssociatedAccount> antiAddictionList = pushUserMapper.getAntiAddiction(uId,project);
        return antiAddictionList;
    }

    @Override
    public List<AssociatedAccount> getAssociatedAccount(Integer id, int type) {

        List<AssociatedAccount> associatedAccountList = pushUserMapper.getAssociatedAccount(id,type);
        return associatedAccountList;
    }

    @Override
    public int saveAssociatedAccount(AssociatedAccount associatedAccount) {

        int i = pushUserMapper.saveAssociatedAccount(associatedAccount);
        return i;
    }

    private void handleCellphones(String account, String type) throws InterruptedException {
        //生成6位短信验证码
        String smsCode = SmsCodeGenerator.genSmsCode();
        while (isCodeExists(smsCode)) {
            smsCode = SmsCodeGenerator.genSmsCode();
        }
        createInstance(smsCode, account, type);
        SmsHolder holder = new SmsHolder(account, smsCode);
        SmsListenerBusPwd.REG_SMS_BUS.put(holder);
    }

    private void handleCellphone(String account) throws InterruptedException {
        //生成6位短信验证码
        String smsCode = SmsCodeGenerator.genSmsCode();
        while (isCodeExists(smsCode)) {
            smsCode = SmsCodeGenerator.genSmsCode();
        }
        createInstance(smsCode, account, EmailSMSTypeEnum.SMS);
        SmsHolder holder = new SmsHolder(account, smsCode);
        SmsListenerBus.REG_SMS_BUS.put(holder);
    }

    private void handleCellphone(String account, String uId) throws InterruptedException {
        //生成6位短信验证码
        String smsCode = PushSmsCodeGenerator.genSmsCode();
        while (isCodeExists(smsCode)) {
            smsCode = PushSmsCodeGenerator.genSmsCode();
        }
        createInstance(smsCode, account, 1, uId);
        PushSmsHolder holder = new PushSmsHolder(account, smsCode);
        PushSmsListenerBus.REG_SMS_BUS.put(holder);
    }

    public String getEmail(String email) {
        int i = email.indexOf("@");
        String substring = email.substring(0, i);
        int length = substring.length();
        double div = PushRefinedCalculation.div(2, length);
        double round = PushRefinedCalculation.round(div, 0);
        int j = (int) round;
        String s = "";
        if (j > 3) {
            s = email.substring(0, j - 3) + "****" + email.substring(j + 2);
        } else {
            s = email.substring(0, j) + "****" + email.substring(j);
        }
        return s;
    }

    //当前时间
    public String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = format.format(new Date());
        return format1;
    }
}
