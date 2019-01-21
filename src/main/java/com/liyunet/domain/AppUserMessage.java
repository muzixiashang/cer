package com.liyunet.domain;

/**
 * Created by wuyunan on 2018/10/31.
 */
public class AppUserMessage {

    private Integer id;

    //手机型号
    private String phoneModel;
    //手机系统版本号
    private String phoneVersionNumber;
    //地理位置
    private String address;
    //渠道ID
    private String ditchId;
    //区块身份id
    private String blockId;
    //app版本号
    private String appVersion;
    //经度
    private String longitude;
    //纬度
    private String latitude;

    private Integer userId;

    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    public String getPhoneVersionNumber() {
        return phoneVersionNumber;
    }

    public void setPhoneVersionNumber(String phoneVersionNumber) {
        this.phoneVersionNumber = phoneVersionNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDitchId() {
        return ditchId;
    }

    public void setDitchId(String ditchId) {
        this.ditchId = ditchId;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
