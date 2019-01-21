package com.liyunet.domain.charge;

public class ChargeCoinInfo {
    private Integer id;

    private String coinInfo;

    private String coinImage;

    private String chargeAddress;

    private String qrCode;

    private Integer state;

    private Integer sort;

    private String homeImage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCoinInfo() {
        return coinInfo;
    }

    public void setCoinInfo(String coinInfo) {
        this.coinInfo = coinInfo == null ? null : coinInfo.trim();
    }

    public String getCoinImage() {
        return coinImage;
    }

    public void setCoinImage(String coinImage) {
        this.coinImage = coinImage == null ? null : coinImage.trim();
    }

    public String getChargeAddress() {
        return chargeAddress;
    }

    public void setChargeAddress(String chargeAddress) {
        this.chargeAddress = chargeAddress == null ? null : chargeAddress.trim();
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode == null ? null : qrCode.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getHomeImage() {
        return homeImage;
    }

    public void setHomeImage(String homeImage) {
        this.homeImage = homeImage == null ? null : homeImage.trim();
    }
}