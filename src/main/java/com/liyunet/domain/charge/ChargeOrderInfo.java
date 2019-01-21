package com.liyunet.domain.charge;

public class ChargeOrderInfo {
    private Integer id;

    private String orderId;

    private String bidtNum;

    private String createtime;

    private Integer cid;

    private Integer userid;

    private Integer status;

    private String reason;

    private Integer orderType;

    private Integer plusMinus;

    private Integer isBidt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getBidtNum() {
        return bidtNum;
    }

    public void setBidtNum(String bidtNum) {
        this.bidtNum = bidtNum == null ? null : bidtNum.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getPlusMinus() {
        return plusMinus;
    }

    public void setPlusMinus(Integer plusMinus) {
        this.plusMinus = plusMinus;
    }

    public Integer getIsBidt() {
        return isBidt;
    }

    public void setIsBidt(Integer isBidt) {
        this.isBidt = isBidt;
    }
}