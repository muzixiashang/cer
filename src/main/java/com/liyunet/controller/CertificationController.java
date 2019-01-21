package com.liyunet.controller;

import com.liyunet.common.pushToken.PushTokenInfo;
import com.liyunet.domain.AntiAddiction;
import com.liyunet.interceptor.anno.HTTPPublicResource;
import com.liyunet.service.CertificationService;
import com.sun.org.apache.bcel.internal.generic.PUSH;
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
@RequestMapping("/api/certification")
public class CertificationController {


    private final CertificationService certificationService;

    @Autowired
    public CertificationController(CertificationService certificationService) {
        this.certificationService = certificationService;
    }

    //根据帐号获取审核状态
    @RequestMapping(value = "/getStatusByAccount", method = RequestMethod.POST)
    @HTTPPublicResource
    public Object getStatusByAccount(HttpServletResponse response, HttpServletRequest request,
                                     @RequestParam(required = true) String account,
                                     @RequestParam(required = false, defaultValue = "zh") String languageType

    ) {

        return certificationService.getStatusByAccount(account);

    }

    //根据token获取安全管理核
    @RequestMapping(value = "/getSafetyById", method = RequestMethod.POST)
    public Object getSafetyById(HttpServletResponse response, HttpServletRequest request,
                                @RequestParam(required = true) String token,
                                @RequestParam(required = true) String project,
                                @RequestParam(required = false, defaultValue = "zh") String languageType

    ) {

        return certificationService.getSafetyById(token, project);

    }

    //根据token获取区块管理核
    @RequestMapping(value = "/getStatusById", method = RequestMethod.POST)
    public Object getStatusById(HttpServletResponse response, HttpServletRequest request,
                                @RequestParam(required = true) String token,
                                @RequestParam(required = true) String project,
                                @RequestParam(required = false, defaultValue = "zh") String languageType

    ) {

        return certificationService.getStatusById(token, project);

    }

    //根据token获取授权管理核
    @RequestMapping(value = "/getAccredityId", method = RequestMethod.POST)
    public Object getAccredityId(HttpServletResponse response, HttpServletRequest request,
                                 @RequestParam(required = true) String token,
                                 @RequestParam(required = true) String project,
                                 @RequestParam(required = false, defaultValue = "zh") String languageType

    ) {

        return certificationService.getAccredityId(token, project);

    }

    //根据身份证号获取审核状态
    @RequestMapping(value = "/getStatus", method = RequestMethod.POST)
    @HTTPPublicResource
    public Object getStatus(HttpServletResponse response, HttpServletRequest request,
                            @RequestParam(required = true) String identityCard,
                            @RequestParam(required = false, defaultValue = "zh") String languageType

    ) {

        return certificationService.getStatus(identityCard);

    }


    //绑定身份认证
    @RequestMapping(value = "/bandingIdentityCard", method = RequestMethod.POST)
    public Object bandingIdentityCard(HttpServletResponse response, HttpServletRequest request,
                                      @RequestParam(required = true) String identityCard,
                                      @RequestParam(required = true) String projectName,
                                      @RequestParam(required = true) String account,
                                      @RequestParam(required = true) String blockAddress,//区块地址
                                      @RequestParam(required = false, defaultValue = "zh") String languageType

    ) {
        certificationService.bandingIdentityCard(identityCard, projectName, blockAddress, account);
        return null;
    }


    //提交身份认证
    @RequestMapping(value = "/saveIdentityCard", method = RequestMethod.POST)
    @HTTPPublicResource
    public Object saveIdentityCard(HttpServletResponse response, HttpServletRequest request,
                                   @RequestParam(required = true) String userName,
                                   @RequestParam(required = true) String userAccount,
                                   @RequestParam(required = true) String token,
                                   @RequestParam(required = true) String identityCard,
                                   @RequestParam(required = true) String frontUrl,
                                   @RequestParam(required = true) String backUrl,
                                   @RequestParam(required = true) String projectName,
                                   @RequestParam(required = false, defaultValue = "zh") String languageType

    ) {
        AntiAddiction antiAddiction = new AntiAddiction();
        antiAddiction.setUserName(userName);
        antiAddiction.setFrontUrl(frontUrl);
        antiAddiction.setIdentityCard(identityCard);
        antiAddiction.setBackUrl(backUrl);
        antiAddiction.setProjectName(projectName);
        antiAddiction.setToken(token);
        certificationService.saveIdentityCar(antiAddiction, request, userAccount);
        return null;

    }


    //通过身份认证后生成帐号
    @RequestMapping(value = "/saveUserAccount", method = RequestMethod.POST)
    @HTTPPublicResource
    public Object saveUserAccount(HttpServletResponse response, HttpServletRequest request,
                                  @RequestParam(required = true) String identityCard,
                                  @RequestParam(required = false, defaultValue = "zh") String languageType

    ) {

        certificationService.saveUserAccount(identityCard);
        return null;

    }


    //授权绑定
    @RequestMapping(value = "/getAppAuthorization", method = RequestMethod.POST)
    public Object getAppAuthorization(HttpServletResponse response, HttpServletRequest request,
                                      @RequestParam(required = true) String token,
                                      @RequestParam(required = false, defaultValue = "zh") String languageType

    ) {

        return certificationService.getAppAuthorization(token);

    }

    //查询靓号是否绑定otc
    @RequestMapping(value = "/whetherBandingOtc", method = RequestMethod.POST)
    public Object whetherBandingOtc(HttpServletResponse response, HttpServletRequest request,
                                    @RequestParam(required = true) String token,
                                    @RequestParam(required = true) String project,
                                    @RequestParam(required = false, defaultValue = "zh") String languageType

    ) {

        return certificationService.whetherBandingOtc(token, project);

    }

    //查询靓号是否绑定蛋生并返回id
    @RequestMapping(value = "/whetherBandingEgg", method = RequestMethod.POST)
    public Object whetherBandingEgg(HttpServletResponse response, HttpServletRequest request,
                                    @RequestParam(required = true) String token,
                                    @RequestParam(required = true) String project,
                                    @RequestParam(required = false, defaultValue = "zh") String languageType

    ) {

        return certificationService.whetherBandingEgg(token, project);

    }


    //查询项目是否绑定otc
    @RequestMapping(value = "/whetherProjectBandingOtc", method = RequestMethod.POST)
    public Object whetherProjectBandingOtc(HttpServletResponse response, HttpServletRequest request,
                                           @RequestParam(required = true) String token,
                                           @RequestParam(required = true) String project,
                                           @RequestParam(required = true) String ToProject,
                                           @RequestParam(required = false, defaultValue = "zh") String languageType

    ) {

        return certificationService.whetherProjectBandingOtc(token, project, ToProject);

    }

    //判断kyc认证等级
    @RequestMapping(value = "/whetherKyc", method = RequestMethod.POST)
    public Object whetherKyc(HttpServletResponse response, HttpServletRequest request,
                             @RequestParam(required = true) String token,
                             @RequestParam(required = true) String project,
                             @RequestParam(required = false, defaultValue = "zh") String languageType
    ) {

        return certificationService.whetherKyc(token, project);

    }


    //获取kyc详情
    @RequestMapping(value = "/whetherKycContent", method = RequestMethod.POST)
    public Object whetherKycContent(HttpServletResponse response, HttpServletRequest request,
                                    @RequestParam(required = true) String token,
                                    @RequestParam(required = true) String project,
                                    @RequestParam(required = true) String blockToken,
                                    @RequestParam(required = false, defaultValue = "zh") String languageType

    ) {

        return certificationService.whetherKycContent(token, project, blockToken);

    }

    //egg提交级实名认证
    @RequestMapping(value = "/eggIdentityCard", method = RequestMethod.POST)
    public Object eggIdentityCard(HttpServletResponse response, HttpServletRequest request,
                                  @RequestParam(required = true) String token,
                                  @RequestParam(required = true) String blockToken,
                                  @RequestParam(required = true) String project,
                                  @RequestParam(required = true) String identityCard,
                                  @RequestParam(required = true) String userName,
                                  @RequestParam(required = false) String frontUrl,
                                  @RequestParam(required = false) String backUrl,
                                  @RequestParam(required = false, defaultValue = "zh") String languageType

    ) {

        AntiAddiction antiAddiction = new AntiAddiction();
        antiAddiction.setIdentityCard(identityCard);
        antiAddiction.setUserName(userName);
        antiAddiction.setFrontUrl(frontUrl);
        antiAddiction.setBackUrl(backUrl);
        antiAddiction.setLevel("1");
        return certificationService.eggIdentityCard(token, project, blockToken, antiAddiction, request);
    }


    //根据靓号token获取OTC帐号
    @RequestMapping(value = "/getOTCAccountByToken", method = RequestMethod.POST)
    public Object getOTCAccountByToken(HttpServletResponse response, HttpServletRequest request,
                                       @RequestParam(required = true) String token,
                                       @RequestParam(required = false, defaultValue = "zh") String languageType

    ) {

        return certificationService.getOTCAccountByToken(token);

    }


    //根据区块地址查询帐号
    @HTTPPublicResource
    @RequestMapping(value = "/getAccountByBlockToken", method = RequestMethod.POST)
    public Object getAccountByBlockToken(HttpServletResponse response, HttpServletRequest request,
                                         @RequestParam(required = true) String blockAddress,
                                         @RequestParam(required = true) String project,
                                         @RequestParam(required = false, defaultValue = "zh") String languageType

    ) {

        return certificationService.getAccountByBlockToken(blockAddress, project);

    }


    //根据帐号Token查询区块Id
    @HTTPPublicResource
    @RequestMapping(value = "/getBlockIdByAccountOrToken", method = RequestMethod.POST)
    public Object getBlockIdByAccountOrToken(HttpServletResponse response, HttpServletRequest request,
                                             @RequestParam(required = true) String account,
                                             @RequestParam(required = true) String token,
                                             @RequestParam(required = true) String project,
                                             @RequestParam(required = false, defaultValue = "zh") String languageType

    ) {

        return certificationService.getBlockIdByAccountOrToken(token, project, account);

    }

    //根据帐号Token查询靓号
    @HTTPPublicResource
    @RequestMapping(value = "/getBlockId", method = RequestMethod.POST)
    public Object getBlockId(HttpServletResponse response, HttpServletRequest request,
                             @RequestParam(required = true) String account,
                             @RequestParam(required = true) String token,
                             @RequestParam(required = true) String project,
                             @RequestParam(required = false, defaultValue = "zh") String languageType

    ) {

        return certificationService.getBlockId(token, project, account);

    }


    //查询所有通过2级认证的
    @HTTPPublicResource
    @RequestMapping(value = "/getEggKycSuccess", method = RequestMethod.GET)
    public Object getEggKycSuccess(HttpServletResponse response, HttpServletRequest request,
                                   @RequestParam(required = true) String project,
                             @RequestParam(required = false, defaultValue = "zh") String languageType

    ) {

        return certificationService.getEggKycSuccess(project);

    }
}
