package com.liyunet.vo.user;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class BlockManageVo {


    private String picUrl;

    private String blockId;

    private String blickAddress;

    private String lastLogin;

    private String totalMoney;
    //审核状态
    private Integer status;

    private String rejectReason;

    private List<MoneyDetails> moneyDetails = new ArrayList<>();

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public String getBlickAddress() {
        return blickAddress;
    }

    public void setBlickAddress(String blickAddress) {
        this.blickAddress = blickAddress;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public List<MoneyDetails> getMoneyDetails() {
        return moneyDetails;
    }

    public void setMoneyDetails(List<MoneyDetails> moneyDetails) {
        this.moneyDetails = moneyDetails;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }
}
