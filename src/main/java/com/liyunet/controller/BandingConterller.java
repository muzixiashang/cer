package com.liyunet.controller;

import com.liyunet.domain.AntiAddiction;
import com.liyunet.exception.ServiceException;
import com.liyunet.interceptor.anno.HTTPPublicResource;
import com.liyunet.interceptor.anno.HTTPPublicToken;
import com.liyunet.service.BandingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 */

@RestController
@RequestMapping("/api/banding")
public class BandingConterller {

    private final BandingService bandingService;

    @Autowired
    public BandingConterller(BandingService bandingService) {
        this.bandingService = bandingService;
    }


    //判断是否绑定
    @RequestMapping(value = "/judgeBanding", method = RequestMethod.POST)
    public Object judgeBanding(HttpServletResponse response, HttpServletRequest request,
                               @RequestParam(required = true) String token,
                               @RequestParam(required = true) String project,
                               @RequestParam(required = false, defaultValue = "zh") String languageType

    ) {

        return bandingService.judgeBanding(token, project);

    }

    //已有靓号，绑定dapp
    @RequestMapping(value = "/getAccredityId", method = RequestMethod.POST)
    public Object getAccredityId(HttpServletResponse response, HttpServletRequest request,
                                 @RequestParam(required = true) String blockToken,//靓号token
                                 @RequestParam(required = true) String token,
                                 @RequestParam(required = true) String project,
                                 @RequestParam(required = true) String account,
                                 @RequestParam(required = false) String identityCard,
                                 @RequestParam(required = false) String userName,
                                 @RequestParam(required = false) String frontUrl,
                                 @RequestParam(required = false) String backUrl,
                                 @RequestParam(required = false) Integer status,
                                 @RequestParam(required = false) String level,
                                 @RequestParam(required = false, defaultValue = "zh") String languageType

    ) {
        AntiAddiction antiAddiction = new AntiAddiction();
        if (!"2".equals(project)) {
            antiAddiction.setIdentityCard(identityCard);
            antiAddiction.setUserName(userName);
            antiAddiction.setFrontUrl(frontUrl);
            antiAddiction.setBackUrl(backUrl);
            antiAddiction.setState(status);
            antiAddiction.setLevel(level);
        } else {
            antiAddiction = null;
        }

        return bandingService.getAccredityId(token, project, account, blockToken, request, antiAddiction);

    }

    //已有靓号，绑定dapp靓号生成蛋生
    @RequestMapping(value = "/getAccredityIds", method = RequestMethod.POST)
    public Object getAccredityIds(HttpServletResponse response, HttpServletRequest request,
                                  @RequestParam(required = true) String blockToken,//靓号token
                                  @RequestParam(required = true) String token,
                                  @RequestParam(required = true) String project,
                                  @RequestParam(required = true) String account,
                                  @RequestParam(required = false, defaultValue = "zh") String languageType

    ) {

        return bandingService.getAccredityIds(token, project, account, blockToken, request);

    }

    //根据token获取我的区块身份
    @RequestMapping(value = "/getblockId", method = RequestMethod.POST)
    public Object getAccredityId(HttpServletResponse response, HttpServletRequest request,
                                 @RequestParam(required = true) String token,
                                 @RequestParam(required = true) String project,
                                 @RequestParam(required = false, defaultValue = "zh") String languageType

    ) {

        return bandingService.getblockId(token, project);

    }


    //注册区块身份发送验证码
    @HTTPPublicResource
    @HTTPPublicToken
    @RequestMapping(value = "/phoneCode", method = RequestMethod.POST)
    public Object phoneCode(HttpServletResponse response, HttpServletRequest request,
                            @RequestParam(required = true) String phone,
                            @RequestParam(required = false) String token,
                            @RequestParam(required = true) String project,
                            @RequestParam(required = false, defaultValue = "zh") String languageType

    ) {

        bandingService.phoneCode(phone, token, project);
        return null;
    }

    //区块身份注册申请下一步
    @HTTPPublicResource
    @HTTPPublicToken
    @RequestMapping(value = "/affirmCode", method = RequestMethod.POST)
    public Object affirmCode(HttpServletResponse response, HttpServletRequest request,
                             @RequestParam(required = true) String phone,
                             @RequestParam(required = true) String code,
                             @RequestParam(required = false) String token,
                             @RequestParam(required = true) String project,
                             @RequestParam(required = false, defaultValue = "zh") String languageType

    ) {

        return bandingService.affirmCode(phone, token, project, code);
    }

    //区块身份认证
    @HTTPPublicResource
    @HTTPPublicToken
    @RequestMapping(value = "/saveIdentityCard", method = RequestMethod.POST)
    public Object saveIdentityCard(HttpServletResponse response, HttpServletRequest request,
                                   @RequestParam(required = true) String userName,
                                   @RequestParam(required = true) String identityCard,
                                   @RequestParam(required = false) String token,
                                   @RequestParam(required = true) String project,
                                   @RequestParam(required = true) String account,
                                   @RequestParam(required = false, defaultValue = "zh") String languageType

    ) {
        if ("".equals(userName) || "".equals(identityCard) || userName == null || identityCard == null) {
            throw ServiceException.userException("Id number format error, please enter again!", "身份证号格式错误，请重新输入!");
        }
        AntiAddiction antiAddiction = new AntiAddiction();
        antiAddiction.setUserName(userName);
        antiAddiction.setIdentityCard(identityCard);
        antiAddiction.setProjectName(project);
        antiAddiction.setToken(token);
        return bandingService.saveIdentityCard(antiAddiction, account, request);
    }

    //设置靓号密码
    @RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
    public Object updatePwd(HttpServletResponse response, HttpServletRequest request,
                            @RequestParam(required = true) String pwd,
                            @RequestParam(required = true) String token,//靓号token
                            @RequestParam(required = true) String blockId,
                            @RequestParam(required = false, defaultValue = "zh") String languageType

    ) {

        bandingService.updatePwd(pwd, token, request, blockId);
        return null;
    }


    //找回密码判断区块id是否存在
    @HTTPPublicResource
    @RequestMapping(value = "/judgeBlockId", method = RequestMethod.POST)
    public Object judgeBlockId(HttpServletResponse response, HttpServletRequest request,
                               @RequestParam(required = true) String blockId,
                               @RequestParam(required = false, defaultValue = "zh") String languageType

    ) {

        return bandingService.judgeBlockId(blockId);
    }

    //找回靓号密码发送验证码
    @HTTPPublicResource
    @RequestMapping(value = "/blockIdPhoneCode", method = RequestMethod.POST)
    public Object blockIdPhoneCode(HttpServletResponse response, HttpServletRequest request,
                                   @RequestParam(required = true) String blockId,
                                   @RequestParam(required = false, defaultValue = "zh") String languageType

    ) {

        bandingService.blockIdPhoneCode(blockId);
        return null;
    }

    //找回靓号密码
    @HTTPPublicResource
    @RequestMapping(value = "/findPwd", method = RequestMethod.POST)
    public Object findPwd(HttpServletResponse response, HttpServletRequest request,
                          @RequestParam(required = true) String pwd,
                          @RequestParam(required = true) String code,
                          @RequestParam(required = true) String blockId,
                          @RequestParam(required = false, defaultValue = "zh") String languageType

    ) {

        bandingService.findPwd(pwd, code, blockId);
        return null;
    }

    //忘记靓号发送验证码
    @HTTPPublicResource
    @RequestMapping(value = "/findBlockIdCode", method = RequestMethod.POST)
    public Object findBlockIdCode(HttpServletResponse response, HttpServletRequest request,
                                  @RequestParam(required = true) String phone,
                                  @RequestParam(required = false, defaultValue = "zh") String languageType

    ) {

        bandingService.findBlockIdCode(phone);
        return null;
    }

    //忘记靓号
    @HTTPPublicResource
    @RequestMapping(value = "/findBlockId", method = RequestMethod.POST)
    public Object findBlockId(HttpServletResponse response, HttpServletRequest request,
                              @RequestParam(required = true) String code,
                              @RequestParam(required = true) String identityCard,
                              @RequestParam(required = true) String userName,
                              @RequestParam(required = true) String phone,
                              @RequestParam(required = false, defaultValue = "zh") String languageType

    ) {

        return bandingService.findBlockId(code, identityCard, phone, request, userName);
    }

    //区块身份注册激活OTC
    @HTTPPublicResource
    @RequestMapping(value = "/regOTC", method = RequestMethod.POST)
    public Object regOTC(HttpServletResponse response, HttpServletRequest request,
                         @RequestParam(required = true) String blockToken,//靓号token
                         @RequestParam(required = true) String project,
                         @RequestParam(required = false, defaultValue = "zh") String languageType

    ) {

        return bandingService.regOTC(blockToken,project);
    }

    //绑定区块对应Id
    @RequestMapping(value = "/bangdingBlockIndex", method = RequestMethod.POST)
    public Object bangdingBlockIndex(HttpServletResponse response, HttpServletRequest request,
                         @RequestParam(required = true) String token,
                         @RequestParam(required = true) String blockId,
                         @RequestParam(required = true) String project,
                         @RequestParam(required = false, defaultValue = "zh") String languageType

    ) {

        bandingService.bangdingBlockIndex(token,project,blockId);
        return null;
    }
}
