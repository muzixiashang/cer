package com.liyunet.controller;

import com.liyunet.interceptor.anno.HTTPPublicResource;
import com.liyunet.service.AutomationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 */

@RestController
@RequestMapping("/api/automation")
public class AutomationController {


    private final AutomationService automationService;

    @Autowired
    public AutomationController(AutomationService automationService) {
        this.automationService = automationService;
    }


//    /**
//     * 蛋生绑定身份证转到区块身份
//     */
//    @HTTPPublicResource
//    @RequestMapping(value = "/transferIdentity", method = RequestMethod.GET)
//    public Object transferIdentity(
//            HttpServletRequest request, HttpServletResponse response,
//            @RequestParam(required = true, defaultValue = "zh") String languageType) {
//
//
//        automationService.transferIdentity();
//        return null;
//    }
//
//    /**
//     * 蛋生区块地址转到区块身份
//     */
//    @HTTPPublicResource
//    @RequestMapping(value = "/transferBlockId", method = RequestMethod.GET)
//    public Object transferBlockId(
//            HttpServletRequest request, HttpServletResponse response,
//            @RequestParam(required = true, defaultValue = "zh") String languageType) {
//
//
//        automationService.transferBlockId();
//        return null;
//    }
//
//
//
//    /**
//     * 蛋生绑定otc账户自动关联区块身份
//     */
//    @HTTPPublicResource
//    @RequestMapping(value = "/eggBandingOtcRelevanceBlock", method = RequestMethod.GET)
//    public Object eggBandingOtcRelevanceBlock(
//            HttpServletRequest request, HttpServletResponse response,
//            @RequestParam(required = true, defaultValue = "zh") String languageType) {
//
//
//        automationService.eggBandingOtcRelevanceBlock();
//        return null;
//    }
}
