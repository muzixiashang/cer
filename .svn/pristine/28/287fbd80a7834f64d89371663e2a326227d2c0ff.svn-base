package com.liyunet.controller;

import com.liyunet.common.pushToken.PushTokenInfo;
import com.liyunet.interceptor.anno.HTTPPublicResource;
import com.liyunet.service.ApiRegAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wuyunan on 2018/8/2.
 */

@RestController
@RequestMapping("/api/reg/address")
public class ApiRegAddressController {

    private final ApiRegAddressService apiRegAddressService;

    @Autowired
    public ApiRegAddressController(ApiRegAddressService apiRegAddressService) {
        this.apiRegAddressService = apiRegAddressService;
    }


    //发送修改邮箱验证码
    @HTTPPublicResource
    @RequestMapping(value = "/updateEmailCode", method = RequestMethod.POST)
    @Transactional
    public Object accountVerfication(@RequestParam String token,
                                     @RequestParam String project,
                                     HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam(required = true, defaultValue = "zh") String languageType) {
        apiRegAddressService.postVerificationCode(token, languageType, request,project);
        return null;
    }

    //确认修改本人邮箱
    @HTTPPublicResource
    @RequestMapping(value = "/updateEmailcodeConfirm", method = RequestMethod.POST)
    @Transactional
    public Object updateEmailcodeConfirm(@RequestParam String token,
                                     @RequestParam String project,
                                     @RequestParam String code,
                                     HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam(required = true, defaultValue = "zh") String languageType) {
        apiRegAddressService.updateEmailcodeConfirm(token,code,project);
        return null;
    }

    //发送修改邮箱验证码
    @HTTPPublicResource
    @RequestMapping(value = "/updateEmailcodeNew", method = RequestMethod.POST)
    @Transactional
    public Object updateEmailcodeNew(@RequestParam String token,
                                     @RequestParam String project,
                                     @RequestParam String email,
                                     HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam(required = true, defaultValue = "zh") String languageType) {
        apiRegAddressService.updateEmailcodeNew(token, email,project,languageType);
        return null;
    }




    //修改邮箱
    @HTTPPublicResource
    @RequestMapping(value = "/updateEmail", method = RequestMethod.POST)
    @Transactional
    public Object updateEmail(@RequestParam String token,
                                     @RequestParam String project,
                                     @RequestParam String email,
                                     @RequestParam String code,
                                     HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam(required = true, defaultValue = "zh") String languageType) {
        apiRegAddressService.updateEmail(token,project,email,code);
        return null;
    }

    /**
     * 手机发送验证码修改手机
     */
    @Transactional
    @RequestMapping(value = "/updatePhoneCode", method = RequestMethod.POST)
    public Object updatePhoneCode(
            HttpServletRequest request, HttpServletResponse response,
            @RequestParam String token,
            @RequestParam String project,
            @RequestParam(required = true, defaultValue = "en") String languageType) {

        apiRegAddressService.updatePhoneCode(token,project);
        return null;
    }

    /**
     * 手机发送验证码确认修改本人手机
     */
    @Transactional
    @RequestMapping(value = "/updatePhoneCodeConfirm", method = RequestMethod.POST)
    public Object updatePhoneCodeConfirm(
            HttpServletRequest request, HttpServletResponse response,
            @RequestParam String token,
            @RequestParam String project,
            @RequestParam String code,
            @RequestParam(required = true, defaultValue = "en") String languageType) {

        apiRegAddressService.updatePhoneCodeConfirm(token,project,code);
        return null;
    }

    /**
     * 手机发送验证码修改手机新手机
     */
    @Transactional
    @RequestMapping(value = "/updatePhoneCodeNew", method = RequestMethod.POST)
    public Object updatePhoneCodeNew(
            HttpServletRequest request, HttpServletResponse response,
            @RequestParam String phone,
            @RequestParam String project,
            @RequestParam String token,
            @RequestParam(required = true, defaultValue = "en") String languageType) {

        apiRegAddressService.updatePhoneCodeNew(phone,project,token);
        return null;
    }

    //修改手机
    @HTTPPublicResource
    @RequestMapping(value = "/updatePhone", method = RequestMethod.POST)
    @Transactional
    public Object updatePhone(@RequestParam String token,
                              @RequestParam String project,
                              @RequestParam String phone,
                              @RequestParam String code,
                              HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(required = true, defaultValue = "zh") String languageType) {
        apiRegAddressService.updatePhone(token,project,phone,code);
        return null;
    }


    //绑定
    @HTTPPublicResource
    @RequestMapping(value = "/bandingAccount", method = RequestMethod.POST)
    @Transactional
    public Object bandingAccount(@RequestParam String token,
                              @RequestParam String project,
                              @RequestParam String account,
                              HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(required = true, defaultValue = "zh") String languageType) {
        apiRegAddressService.bandingAccount(token,project,account);
        return null;
    }



    //修改靓号密码
    @HTTPPublicResource
    @RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
    @Transactional
    public Object updatePwd(@RequestParam String token,
                                 @RequestParam String project,
                                 @RequestParam String pwd,
                                 @RequestParam String pwdNew,
                                 HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(required = true, defaultValue = "zh") String languageType) {
        apiRegAddressService.updatePwd(token,project,pwd,pwdNew);
        return null;
    }

}


