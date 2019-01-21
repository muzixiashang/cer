package com.liyunet.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liyunet.common.util.IpResourceLocation;
import com.liyunet.common.util.TokenUtil;
import com.liyunet.common.util.UrlLoad;
import com.liyunet.domain.*;
import com.liyunet.exception.ServiceException;
import com.liyunet.mapper.automation.AutomationMapper;
import com.liyunet.service.AutomationService;
import com.liyunet.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 */

@Service("automationService")
@Transactional
public class AutomationServiceImpl implements AutomationService{

    private final AutomationMapper automationMapper;

    @Autowired
    public AutomationServiceImpl(AutomationMapper automationMapper) {
        this.automationMapper = automationMapper;
    }

    @Override
    public void transferIdentity() {

        List<AssociatedAccount> associatedAccountList = automationMapper.getAssociatedAccount();
        if (associatedAccountList != null && associatedAccountList.size() > 0) {
            for (AssociatedAccount associatedAccount : associatedAccountList) {

                String s = null;
                try {
                    s = UrlLoad.load(IpResourceLocation.EGG_IP+"/egg_game/api/blockinfo/getUserIdcardById",
                            "id="+associatedAccount.getToken());

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
                        JSONObject jsonObject2 = JSON.parseObject(data);
                        String twostate = jsonObject2.getString("twostate");
                        if ("1".equals(twostate)) {
                            List<AntiAddiction> antiAddictionList = automationMapper.getAntiAddiction(associatedAccount.getUserId());
                        if (antiAddictionList != null && antiAddictionList.size() > 0) {
                            AntiAddiction antiAddiction = antiAddictionList.get(0);
                            String level = antiAddiction.getLevel();
                            if (!"2".equals(level)) {
                                String identityCard = jsonObject2.getString("identityCard");

//                                int i = automationMapper.getAntiAddictionByidentityCard(identityCard);
                                if ("132629197807113312".equals(identityCard)) {
                                    continue;
                                }
                                UpdateAntiAddiction updateAntiAddiction = new UpdateAntiAddiction();
                                updateAntiAddiction.setLevel(antiAddiction.getLevel());
                                updateAntiAddiction.setUserName(antiAddiction.getUserName());
                                updateAntiAddiction.setIdentityCard(antiAddiction.getIdentityCard());
                                updateAntiAddiction.setFrontUrl(antiAddiction.getFrontUrl());
                                updateAntiAddiction.setBackUrl(antiAddiction.getBackUrl());
                                updateAntiAddiction.setUpdateTime(antiAddiction.getUpdateTime());
                                updateAntiAddiction.setState(antiAddiction.getState());
                                updateAntiAddiction.setuId(antiAddiction.getuId());
                                updateAntiAddiction.setAntiAddictionId(antiAddiction.getId()+"");
                                automationMapper.saveUpdateAntiAddiction(updateAntiAddiction);
                                antiAddiction.setLevel("2");
                                antiAddiction.setUserName(jsonObject2.getString("username"));

                                antiAddiction.setIdentityCard(jsonObject2.getString("identityCard"));
                                antiAddiction.setFrontUrl(jsonObject2.getString("frontUrl"));
                                antiAddiction.setBackUrl(jsonObject2.getString("backUrl"));
                                antiAddiction.setUpdateTime(getCurrentTime());
                                antiAddiction.setState(1);
                                antiAddiction.setuId(associatedAccount.getUserId());
                                automationMapper.updateAntiAddiction(antiAddiction);

                            }

                        }

                        }

                    }

                }else if("20002".equals(code)){
                    String msg = jsonObject1.getString("msg");
                    throw ServiceException.userException("A system exception, contact customer service", msg);

                }
            }

        }

    }

    @Override
    public void transferBlockId() {

        List<AssociatedAccount> associatedAccountList = automationMapper.getAssociatedAccount();
        if (associatedAccountList != null && associatedAccountList.size() > 0) {
            for (AssociatedAccount associatedAccount : associatedAccountList) {

                String s = null;
                try {
                    s = UrlLoad.load(IpResourceLocation.EGG_IP+"/egg_game/api/blockinfo/getUserblockaddress",
                            "id="+associatedAccount.getToken());

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
                        List<UserInfo> userInfoList = automationMapper.getUserInfo(associatedAccount.getUserId());
                        if (userInfoList != null && userInfoList.size() > 0) {
                            UserInfo userInfo = userInfoList.get(0);
                            UpdateBlockId updateBlockId = new UpdateBlockId();
                            updateBlockId.setBlockId(userInfo.getBlockAddress());
                            updateBlockId.setCreateTime(getCurrentTime());
                            updateBlockId.setUserId(userInfo.getuId());
                            automationMapper.saveUpdateBlockId(updateBlockId);
                            userInfo.setBlockAddress(data);
                            automationMapper.updateUserInfo(userInfo);
                        }

                    }

                }else if("20002".equals(code)){
                    String msg = jsonObject1.getString("msg");
                    throw ServiceException.userException("", msg);

                }
            }

        }



    }

    @Override
    public void eggBandingOtcRelevanceBlock() {

        List<AssociatedAccount> associatedAccountList = automationMapper.getAssociatedAccount();
        if (associatedAccountList != null && associatedAccountList.size() > 0) {
            for (AssociatedAccount associatedAccount : associatedAccountList) {

                int i = automationMapper.getAssociatedAccountCount(associatedAccount.getUserId());
                if (i < 1){

                    String s = null;
                    try {
                        s = UrlLoad.load(IpResourceLocation.EGG_IP+"/egg_game/api/blockinfo/getUserblockaddress",
                                "id="+associatedAccount.getToken());
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
                                json = UrlLoad.load(IpResourceLocation.OTC_IP+"/timetreaty_push/api/certification/getAccountByBlockId",
                                        "blockId="+data);
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
                                    JSONObject jsonObject4 = JSON.parseObject(data1);
                                    String id = jsonObject4.getString("id");
                                    String username = jsonObject4.getString("username");

                                    List<AssociatedAccount> associatedAccounts = automationMapper.getAssociatedAccountByUserAccount(username,id);
                                    if (associatedAccounts == null || associatedAccounts.size() < 1) {

                                        AssociatedAccount associatedAccount1 = new AssociatedAccount();
                                        associatedAccount1.setCreateTime(getCurrentTime());
                                        associatedAccount1.setAppUserAccount(username);
                                        associatedAccount1.setToken(id);
                                        associatedAccount1.setAppName("2");
                                        associatedAccount1.setuId(UuidUtil.getUUid());
                                        associatedAccount1.setUserId(associatedAccount.getUserId());
                                        automationMapper.saveAssociatedAccount(associatedAccount1);

                                        UpdateAssociatedAccount updateAssociatedAccount = new UpdateAssociatedAccount();
                                        updateAssociatedAccount.setCreateTime(getCurrentTime());
                                        updateAssociatedAccount.setAppUserAccount(username);
                                        updateAssociatedAccount.setToken(id);
                                        updateAssociatedAccount.setAppName("2");
                                        updateAssociatedAccount.setuId(UuidUtil.getUUid());
                                        updateAssociatedAccount.setUserId(associatedAccount.getUserId());
                                        automationMapper.saveUpdateAssociatedAccount(updateAssociatedAccount);

                                    }

                                }

                            }else if("20002".equals(code)){
                                String msg = jsonObject1.getString("msg");
                                throw ServiceException.userException("A system exception, contact customer service", msg);

                            }



                        }

                    }else if("20002".equals(code)){
                        String msg = jsonObject1.getString("msg");
                        throw ServiceException.userException("A system exception, contact customer service", msg);

                    }

                }



            }

        }

    }

    //当前时间
    public String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = format.format(new Date());
        return format1;
    }
}
