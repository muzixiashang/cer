package com.liyunet.controller;

import com.liyunet.common.pushToken.PushTokenInfo;
import com.liyunet.domain.dto.AppParameter;
import com.liyunet.interceptor.anno.HTTPPublicResource;
import com.liyunet.interceptor.anno.HTTPPublicToken;
import com.liyunet.service.AndroidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 */

@RestController
@RequestMapping("/app/api/content")
public class AndroidController {


    private final AndroidService androidService;

    @Autowired
    public AndroidController(AndroidService androidService) {
        this.androidService = androidService;
    }

    @RequestMapping("/getProperty")
    public Object getProperty(HttpServletRequest request, HttpServletResponse response,
                              PushTokenInfo pushTokenInfo) {
        Integer userId = pushTokenInfo.getUserId();

        return androidService.getProperty(userId);

    }


    @RequestMapping("/getOTC")
    public Object getOTC(HttpServletRequest request, HttpServletResponse response,
                         PushTokenInfo pushTokenInfo,
                         @RequestParam(required = true) Integer page,
                         @RequestParam(required = true) Integer row
    ) {
        Integer userId = pushTokenInfo.getUserId();

        return androidService.getOTC(userId, page, row);

    }

    //通过原密码修改登录密码
    @RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
    public Object updatePwd(HttpServletRequest request, HttpServletResponse response,
                            PushTokenInfo pushTokenInfo,
                            @RequestParam(required = true) String oldPwd,
                            @RequestParam(required = true) String newPwd

    ) {
        Integer userId = pushTokenInfo.getUserId();
        androidService.updatePwd(userId, oldPwd, newPwd);
        return null;
    }


    //消息中心
    @HTTPPublicResource
    @HTTPPublicToken
    @RequestMapping(value = "/getMessage", method = RequestMethod.POST)
    public Object getMessage(HttpServletRequest request, HttpServletResponse response,
                             PushTokenInfo pushTokenInfo,
                             @RequestParam(required = true) Integer page,
                             @RequestParam(required = true) Integer row
    ) {
        Integer userId = pushTokenInfo.getUserId();
        return androidService.getMessage(userId, page, row);

    }


    //查看消息
    @HTTPPublicResource
    @HTTPPublicToken
    @RequestMapping(value = "/getMessageContent", method = RequestMethod.POST)
    public Object getMessageContent(HttpServletRequest request, HttpServletResponse response,
                                    PushTokenInfo pushTokenInfo,
                                    @RequestParam(required = true) String id
    ) {
        Integer userId = pushTokenInfo.getUserId();
        return androidService.getMessageContent(userId, id);

    }

    //获取地址
    @RequestMapping(value = "getAddress", method = RequestMethod.POST)
    public Object getAddress(HttpServletRequest request, HttpServletResponse response,
                             PushTokenInfo pushTokenInfo,
                             @RequestParam(required = true) Integer page,
                             @RequestParam(required = true) Integer row,
                             @RequestParam(required = true) String type
    ) {
        Integer userId = pushTokenInfo.getUserId();
        return androidService.getAddress(userId, page, row, type);

    }

    //添加地址
    @RequestMapping(value = "saveAddress", method = RequestMethod.POST)
    public Object saveAddress(HttpServletRequest request, HttpServletResponse response,
                              PushTokenInfo pushTokenInfo,
                              @RequestParam(required = true) String type,
                              @RequestParam(required = true) String currency,
                              @RequestParam(required = true) String address,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String remark
    ) {
        Integer userId = pushTokenInfo.getUserId();
        androidService.saveAddress(userId, currency, address, name, remark, type);
        return null;
    }


    //删除地址
    @RequestMapping(value = "deleteAddress", method = RequestMethod.POST)
    public Object deleteAddress(HttpServletRequest request, HttpServletResponse response,
                                PushTokenInfo pushTokenInfo,
                                @RequestParam(required = true) String id

    ) {
        Integer userId = pushTokenInfo.getUserId();
        androidService.deleteAddress(userId, id);
        return null;
    }

    //修改地址地址
    @RequestMapping(value = "updateAddress", method = RequestMethod.POST)
    public Object updateAddress(HttpServletRequest request, HttpServletResponse response,
                                PushTokenInfo pushTokenInfo,
                                @RequestParam(required = true) String id,
                                @RequestParam(required = true) String currency,
                                @RequestParam(required = true) String address,
                                @RequestParam(required = false) String name,
                                @RequestParam(required = false) String remark

    ) {
        Integer userId = pushTokenInfo.getUserId();
        androidService.updateAddress(userId, id, currency, address, name, remark);
        return null;
    }

    //查询他人地址
    @HTTPPublicResource
    @RequestMapping(value = "selectAddress", method = RequestMethod.POST)
    public Object selectAddress(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(required = false) String blockId,
                                @RequestParam(required = false) Integer page,
                                @RequestParam(required = false) Integer row

    ) {
        return androidService.selectAddress(blockId, page, row);
    }


    //判断是否设置过资金密码
    @RequestMapping(value = "/whetherfundPwd", method = RequestMethod.POST)
    public Object whetherfundPwd(HttpServletRequest request, HttpServletResponse response,
                                 PushTokenInfo pushTokenInfo,
                                 @RequestParam(required = true, defaultValue = "zh") String languageType) {
        Integer userId = pushTokenInfo.getUserId();
        return androidService.whetherfundPwd(userId);
    }


    //设置资金密码发送验证吗
    @RequestMapping(value = "/fundPwdcode", method = RequestMethod.POST)
    public Object fundPwdcode(HttpServletRequest request, HttpServletResponse response,
                              PushTokenInfo pushTokenInfo,
                              @RequestParam(required = true, defaultValue = "zh") String languageType) {
        Integer userId = pushTokenInfo.getUserId();
        androidService.fundPwd(userId, request);
        return null;
    }


    //设置资金密码
    @RequestMapping(value = "/saveFundPwd", method = RequestMethod.POST)
    public Object saveFundPwd(HttpServletRequest request, HttpServletResponse response,
                              PushTokenInfo pushTokenInfo,
                              @RequestParam(required = true, defaultValue = "zh") String languageType,
                              @RequestParam(required = true) String code,
                              @RequestParam(required = true) String newPwd,
                              @RequestParam(required = false) String oldPwd,
                              //0设置，1修改
                              @RequestParam(required = true) String type
    ) {
        Integer userId = pushTokenInfo.getUserId();
        androidService.updatePwd(userId, newPwd, code, type, oldPwd);
        return null;
    }


    //获取币达钱包流水
    @RequestMapping(value = "/getBidtWallet",  method = { RequestMethod.GET, RequestMethod.POST })
    public Object getBidtWallet(HttpServletRequest request, HttpServletResponse response,
                                PushTokenInfo pushTokenInfo,
                                @RequestParam(required = true) String project,
                                @RequestParam(required = true) Integer page,
                                @RequestParam(required = true) Integer row
    ) {
        Integer userId = pushTokenInfo.getUserId();
        return androidService.getBidtWallet(userId, project, page, row);
    }

    //查看流水详情
    @RequestMapping(value = "/getBidtWalletContent", method = RequestMethod.POST)
    public Object getBidtWalletContent(HttpServletRequest request, HttpServletResponse response,
                                PushTokenInfo pushTokenInfo,
                                @RequestParam(required = true) String id
    ) {
        Integer userId = pushTokenInfo.getUserId();
        return androidService.getBidtWalletContent(userId,id);
    }

    //判断ANDROID是否更新
    @HTTPPublicResource
    @HTTPPublicToken
    @RequestMapping(value = "/mobileVersionNumber", method = RequestMethod.POST)
    public Object mobileVersionNumber(HttpServletRequest request, HttpServletResponse response,
                                      PushTokenInfo pushTokenInfo,
                                      @ModelAttribute AppParameter appParameter,
                                      @RequestParam(required = false) String type

    ) {
        Integer userId = pushTokenInfo.getUserId();
        return androidService.mobileVersionNumber(userId, appParameter,type);
    }

    //判断是否二级通过和设置资金密码
    @RequestMapping(value = "/whetherKycOrFundPwd", method = RequestMethod.POST)
    public Object whetherKycOrFundPwd(HttpServletRequest request, HttpServletResponse response,
                                      PushTokenInfo pushTokenInfo
    ) {
        Integer userId = pushTokenInfo.getUserId();

        return androidService.whetherKycOrFundPwd(userId);
    }

    //提现
    @RequestMapping(value = "/withdrawDeposit", method = RequestMethod.POST)
    public Object withdrawDeposit(HttpServletRequest request, HttpServletResponse response,
                                  PushTokenInfo pushTokenInfo,
                                  @RequestParam(required = true) String address,
                                  @RequestParam(required = true) String num,
                                  @RequestParam(required = true) String remarks,
                                  @RequestParam(required = true) String blockId


    ) {
        Integer userId = pushTokenInfo.getUserId();
        androidService.withdrawDeposit(userId, address, num, remarks,blockId);
        return null;
    }

    //提现下一步返回地址和区块id
    @RequestMapping(value = "/getBlockIdAndAddress", method = RequestMethod.POST)
    public Object getBlockIdAndAddress(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam(required = true) String address


    ) {
        return androidService.getBlockIdAndAddress(address);
    }

    //提现验证资金密码
    @RequestMapping(value = "/verifyFundPwd", method = RequestMethod.POST)
    public Object verifyFundPwd(HttpServletRequest request, HttpServletResponse response,
                                PushTokenInfo pushTokenInfo,
                                @RequestParam(required = true) String pwd

    ) {
        Integer userId = pushTokenInfo.getUserId();
        androidService.verifyFundPwd(userId, pwd);
        return null;
    }

    //获取APP版本号
    @HTTPPublicResource
    @RequestMapping(value = "/getDitchId", method = RequestMethod.GET)
    public Object verifyFundPwd(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(required = true) String ditchId

    ) {
        return androidService.getDitchId(ditchId);
    }

    //iosBug收集
    @HTTPPublicResource
    @RequestMapping(value = "/iosBugGather", method = RequestMethod.GET)
    public Object iosBugGather(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(required = true) String reason,
                                @RequestParam(required = true) String detail,
                                @RequestParam(required = true) String systemVerson

    ) {
        return androidService.iosBugGather(reason,detail,systemVerson);
    }

    //android登录数量
    @HTTPPublicResource
    @RequestMapping(value = "/androidLoginNum", method = RequestMethod.POST)
    public Object androidLoginNum(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(required = true) String blockId

    ) {
        androidService.androidLoginNum(blockId);
        return null;
    }

    //android下载数量
    @HTTPPublicResource
    @RequestMapping(value = "/androidDownloadNum", method = RequestMethod.GET)
    public Object androidDownloadNum(HttpServletRequest request, HttpServletResponse response

    ) {
        androidService.androidDownloadNum();
        return null;
    }
    //点对点转账
    @HTTPPublicResource
    @RequestMapping(value = "/p2p", method = RequestMethod.GET)
    public Object p2p(HttpServletRequest request, HttpServletResponse response

    ) {
        androidService.androidDownloadNum();
         
        return null;
    }
    
    
}
