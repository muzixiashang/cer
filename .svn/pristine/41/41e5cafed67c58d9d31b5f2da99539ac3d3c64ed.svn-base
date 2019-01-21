package com.liyunet.service.impl;

import com.liyunet.common.constant.InvitationCodeGenerator;
import com.liyunet.common.enums.EmailSMSTypeEnum;
import com.liyunet.common.mail.RegOrPwdEmailHolder;
import com.liyunet.common.pushPassword.PushPasswordHash;
import com.liyunet.common.queue.RegPwdEmailListenerBus;
import com.liyunet.common.queue.SmsListenerBusPwd;
import com.liyunet.common.sms.SmsCodeGenerator;
import com.liyunet.common.sms.SmsHolder;
import com.liyunet.common.util.TokenUtil;
import com.liyunet.domain.InvitationCode;
import com.liyunet.domain.UserInfo;
import com.liyunet.exception.ServiceException;
import com.liyunet.mapper.userMapper.ApiRegAddressMapper;
import com.liyunet.service.ApiRegAddressService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 */
@Service("apiRegAddressService")
@Transactional
public class ApiRegAddressServiceImpl implements ApiRegAddressService {

    private final ApiRegAddressMapper apiRegAddressMapper;

    @Autowired
    public ApiRegAddressServiceImpl(ApiRegAddressMapper apiRegAddressMapper) {
        this.apiRegAddressMapper = apiRegAddressMapper;
    }


    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Override
    public void postVerificationCode(String token, String languageType, HttpServletRequest request,String project) {

        String userId = TokenUtil.getUserId(token);
        if (StringUtils.isEmpty(userId)) {
            throw ServiceException.userException("The username can't be empty!", "参数不能为空！");
        }
        userId = userId.trim();
        List<UserInfo> userInfoList = apiRegAddressMapper.getUserInfoByToken(userId,project);
        if (userInfoList != null && userInfoList.size() > 0) {
            UserInfo userInfo = userInfoList.get(0);
            String email = userInfo.getEmail();
            if (email == null || "".equals(email)) {
                throw ServiceException.userException("Please bind the mailbox!", "请先绑定邮箱！");
            }
            if (isemail(email)) {
                try {
                    handleEmail(email, RegOrPwdEmailHolder.RegOrPwdEmailType.REG, languageType);
                } catch (Exception e) {
                    throw ServiceException.userException("The username has been registered!", "系统正忙着。请稍后再试！");
                }
            } else {
                throw ServiceException.userException("The mailbox format is not correct!", "请输入正确的邮箱！");
            }
        }else {
            throw ServiceException.userException("Binding account first, please!", "请先绑定帐号！");
        }



    }

    @Override
    public void updateEmail(String token, String project, String email,String code) {

        String userId = TokenUtil.getUserId(token);

        List<UserInfo> userInfoList = apiRegAddressMapper.getUserInfoByToken(userId,project);
        if (userInfoList != null && userInfoList.size() > 0) {
            UserInfo userInfo = userInfoList.get(0);
            String type = "3";//邮箱修改
            List<InvitationCode> PushInvitationCodes = apiRegAddressMapper.getByCodeByaccount(code, email,type);
            if (PushInvitationCodes == null || PushInvitationCodes.size() == 0) {

                throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");

            }
            InvitationCode ic = PushInvitationCodes.get(0);
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime expireTime = LocalDateTime.parse(ic.getExpireTime());
            if (ic.isUsed()) {
                throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");
            }
            if (ic.isUsed() || now.isAfter(expireTime)) {

                throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");

            } else {

                userInfo.setEmail(email);
                apiRegAddressMapper.updateUserInfo(userInfo);

                ic.setUsed(true);
                ic.setUseTime(now.toString());
                int i = apiRegAddressMapper.updateInvitationCode(ic);
                if (i <= 0) {
                    throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");
                }

            }

        }else {
            throw ServiceException.userException("Binding account first, please!", "请先绑定帐号！");
        }

    }

    @Override
    public void updatePhoneCode(String token, String project) {

        String userId = TokenUtil.getUserId(token);
        if (StringUtils.isEmpty(userId)) {
            throw ServiceException.userException("The username can't be empty!", "参数不能为空！");
        }

        List<UserInfo> userInfoList = apiRegAddressMapper.getUserInfoByToken(userId,project);
        if (userInfoList != null && userInfoList.size() > 0) {
            UserInfo userInfo = userInfoList.get(0);
            String phoneNum = userInfo.getPhoneNum();

            if (!isphonenum(phoneNum)) {
                throw ServiceException.userException("The mobile phone number is not correct!", "手机号格式不正确!");
            }

            try {
                handleCellphones(phoneNum,"4");
            } catch (InterruptedException e) {
                throw ServiceException.userException("Verification code sent failure!", "验证码发送失败!");
            }
        }else {
            throw ServiceException.userException("Binding account first, please!", "请先绑定帐号！");
        }
    }

    @Override
    public void updatePhone(String token, String project, String phone, String code) {

        String userId = TokenUtil.getUserId(token);

        List<UserInfo> userInfoList = apiRegAddressMapper.getUserInfoByToken(userId,project);
        if (userInfoList != null && userInfoList.size() > 0) {
            UserInfo userInfo = userInfoList.get(0);
//            String phoneNum = userInfo.getPhoneNum();
            String type = "4";//电话修改
            List<InvitationCode> PushInvitationCodes = apiRegAddressMapper.getByCodeByaccount(code,phone,type);
            if (PushInvitationCodes == null || PushInvitationCodes.size() == 0) {

                throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");

            }
            InvitationCode ic = PushInvitationCodes.get(0);
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime expireTime = LocalDateTime.parse(ic.getExpireTime());
            if (ic.isUsed()) {
                throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");
            }
            if (ic.isUsed() || now.isAfter(expireTime)) {

                throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");

            } else {

                userInfo.setPhoneNum(phone);
                apiRegAddressMapper.updateUserInfo(userInfo);

                ic.setUsed(true);
                ic.setUseTime(now.toString());
                int i = apiRegAddressMapper.updateInvitationCode(ic);
                if (i <= 0) {
                    throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");
                }

            }

        }else {
            throw ServiceException.userException("Binding account first, please!", "请先绑定帐号！");
        }

    }

    @Override
    public void updatePhoneCodeNew(String phone, String project,String token) {

        String userId = TokenUtil.getUserId(token);
        if (StringUtils.isEmpty(userId)) {
            throw ServiceException.userException("The username can't be empty!", "参数不能为空！");
        }
        int i =  apiRegAddressMapper.getUserinfoByPhone(phone);
        if (i > 0){
            throw ServiceException.userException("Phone number already exists!", "该手机号已经存在！");
        }
        List<UserInfo> userInfoList = apiRegAddressMapper.getUserInfoByToken(userId,project);
        if (userInfoList != null && userInfoList.size() > 0) {

            if (!isphonenum(phone)) {
                throw ServiceException.userException("The mobile phone number is not correct!", "手机号格式不正确!");
            }

            try {
                handleCellphones(phone,"4");
            } catch (InterruptedException e) {
                throw ServiceException.userException("Verification code sent failure!", "验证码发送失败!");
            }
        }else {
            throw ServiceException.userException("Binding account first, please!", "请先绑定帐号！");
        }
    }

    @Override
    public void updatePhoneCodeConfirm(String token, String project, String code) {


        String userId = TokenUtil.getUserId(token);
        List<UserInfo> userInfoList = apiRegAddressMapper.getUserInfoByToken(userId,project);
        if (userInfoList != null && userInfoList.size() > 0) {
            UserInfo userInfo = userInfoList.get(0);
            String phoneNum = userInfo.getPhoneNum();
            String type = "4";//电话修改
            List<InvitationCode> PushInvitationCodes = apiRegAddressMapper.getByCodeByaccount(code,phoneNum,type);
            if (PushInvitationCodes == null || PushInvitationCodes.size() == 0) {

                throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");

            }
            InvitationCode ic = PushInvitationCodes.get(0);
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime expireTime = LocalDateTime.parse(ic.getExpireTime());
            if (ic.isUsed()) {
                throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");
            }
            if (ic.isUsed() || now.isAfter(expireTime)) {

                throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");

            } else {
                ic.setUsed(true);
                ic.setUseTime(now.toString());
                int i = apiRegAddressMapper.updateInvitationCode(ic);
                if (i <= 0) {
                    throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");
                }
            }

        }else {
            throw ServiceException.userException("Binding account first, please!", "请先绑定帐号！");
        }
    }

    @Override
    public void updateEmailcodeConfirm(String token, String code, String project) {


        String userId = TokenUtil.getUserId(token);
        List<UserInfo> userInfoList = apiRegAddressMapper.getUserInfoByToken(userId,project);
        if (userInfoList != null && userInfoList.size() > 0) {
            UserInfo userInfo = userInfoList.get(0);
            String email1 = userInfo.getEmail();
            String type = "3";//邮箱修改
            List<InvitationCode> PushInvitationCodes = apiRegAddressMapper.getByCodeByaccount(code, email1,type);
            if (PushInvitationCodes == null || PushInvitationCodes.size() == 0) {

                throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");

            }
            InvitationCode ic = PushInvitationCodes.get(0);
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime expireTime = LocalDateTime.parse(ic.getExpireTime());
            if (ic.isUsed()) {
                throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");
            }
            if (ic.isUsed() || now.isAfter(expireTime)) {

                throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");

            } else {
                ic.setUsed(true);
                ic.setUseTime(now.toString());
                int i = apiRegAddressMapper.updateInvitationCode(ic);
                if (i <= 0) {
                    throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");
                }

            }

        }else {
            throw ServiceException.userException("Binding account first, please!", "请先绑定帐号！");
        }


    }

    @Override
    public void updateEmailcodeNew(String token, String email, String project,String languageType) {

        String userId = TokenUtil.getUserId(token);
        if (StringUtils.isEmpty(userId)) {
            throw ServiceException.userException("The username can't be empty!", "参数不能为空！");
        }
        userId = userId.trim();
        int i =  apiRegAddressMapper.getUserinfoByemail(email);
        if (i > 0){
            throw ServiceException.userException("Phone number already exists!", "该邮箱已经存在！");
        }
        List<UserInfo> userInfoList = apiRegAddressMapper.getUserInfoByToken(userId,project);
        if (userInfoList != null && userInfoList.size() > 0) {
            if (isemail(email)) {
                try {
                    handleEmail(email, RegOrPwdEmailHolder.RegOrPwdEmailType.REG, languageType);
                } catch (Exception e) {
                    throw ServiceException.userException("The username has been registered!", "系统正忙着。请稍后再试！");
                }
            } else {
                throw ServiceException.userException("The mailbox format is not correct!", "请输入正确的邮箱！");
            }
        }else {
            throw ServiceException.userException("Binding account first, please!", "请先绑定帐号！");
        }


    }

    @Override
    public void bandingAccount(String token, String project, String account) {

        String userId = TokenUtil.getUserId(token);
        if (StringUtils.isEmpty(userId)) {
            throw ServiceException.userException("The username can't be empty!", "参数不能为空！");
        }
        userId = userId.trim();
        List<UserInfo> userInfoList = apiRegAddressMapper.getUserInfoByToken(userId,project);
        if (userInfoList != null && userInfoList.size() > 0) {
            UserInfo userInfo = userInfoList.get(0);
            int i = account.indexOf("@");
            if (i < 0){
                userInfo.setPhoneNum(account);
            }else {
                userInfo.setEmail(account);
            }
            apiRegAddressMapper.updateUserInfo(userInfo);

        }else {
            throw ServiceException.userException("Binding account first, please!", "请先绑定帐号！");
        }

    }

    @Override
    public void updatePwd(String token, String project, String pwd, String pwdNew) {
        String userId = TokenUtil.getUserId(token);
        List<UserInfo> userInfoList = apiRegAddressMapper.getUserInfoByToken(userId,project);
        if (userInfoList != null && userInfoList.size() > 0) {
            UserInfo userInfo = userInfoList.get(0);
            String userPwd = userInfo.getUserPwd();
            try {
                boolean b = PushPasswordHash.validatePassword(pwd,userPwd);
                if (!b) {
                    throw ServiceException.userException("Password mistake!", "密码错误!");
                }
                String hash = PushPasswordHash.createHash(pwdNew);
                userInfo.setUserPwd(hash);
                apiRegAddressMapper.updateUserInfoPwd(userInfo);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                throw ServiceException.userException("Password mistake!", "密码错误!");
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
                throw ServiceException.userException("Password mistake!", "密码错误!");
            }


        }else {
            throw ServiceException.userException("Binding account first, please!", "请先绑定帐号！");
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


    //邮箱修改
    private InvitationCode createInstance(String code, String account, EmailSMSTypeEnum type) {
        InvitationCode ic = new InvitationCode();
        ic.setAccount(account);
        ic.setCode(code);
        ic.setExpire(TimeUnit.MINUTES, 30);
        ic.setType(3);
        int i = apiRegAddressMapper.saveInvitation_code(ic);
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
        int i = apiRegAddressMapper.saveInvitation_code(ic);
        if (i > 0) {
            return ic;
        } else {
            return null;
        }
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


    private boolean isCodeExists(String code) {
        if (StringUtils.isEmpty(code)) return true;
        int i = apiRegAddressMapper.getByCode(code);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isemail(String account) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(account);
        return matcher.find();
    }

    private boolean isphonenum(String account) {
        return account.startsWith("1") && account.length() == 11;
    }
}
