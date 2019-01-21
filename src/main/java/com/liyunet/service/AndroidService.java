package com.liyunet.service;

import com.liyunet.domain.dto.AppParameter;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wuyunan on 2018/9/4.
 */
public interface AndroidService {

    Object getProperty(Integer userId);

    Object getOTC(Integer userId,Integer page,Integer row);

    Object getMessage(Integer userId,Integer page,Integer row);

    Object getMessageContent(Integer userId, String id);

    Object getAddress(Integer userId, Integer page, Integer row,String type);

    void saveAddress(Integer userId, String currency, String address, String name, String remark, String type);

    void deleteAddress(Integer userId, String id);

    void updateAddress(Integer userId, String id, String currency, String address, String name, String remark);

    void fundPwd(Integer userId, HttpServletRequest request);

    void updatePwd(Integer userId, String newPwd, String code,String type,String oldPwd);

    Object whetherfundPwd(Integer userId);

    Object getBidtWallet(Integer userId, String project,Integer page,Integer row);

    void updatePwd(Integer userId, String oldPwd, String newPwd);

    Object selectAddress(String blockId,Integer page,Integer row);

    Object mobileVersionNumber(Integer userId, AppParameter appParameter,String type);

    Object whetherKycOrFundPwd(Integer userId);

    void withdrawDeposit(Integer userId, String address, String num, String remarks,String blockId);

    void verifyFundPwd(Integer userId, String pwd);

    Object getBidtWalletContent(Integer userId, String id);

    Object getDitchId(String ditchId);

    Object iosBugGather(String reason, String detail, String systemVerson);

    Object getBlockIdAndAddress(String address);

    void androidLoginNum(String blockId);

    void androidDownloadNum();

}
