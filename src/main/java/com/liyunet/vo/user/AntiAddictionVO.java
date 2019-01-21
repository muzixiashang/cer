package com.liyunet.vo.user;

/**
 * Created by wuyunan on 2018/7/19.
 */
public class AntiAddictionVO {

    private Integer status;

    private String rejectReason;

    private String address;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
