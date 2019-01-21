package com.liyunet.domain;

/**
 * 限制
 */
public class APPUpdateCondition {

    private Integer id;
    //0.用户 1.手机型号 2.手机系统版本号 3.地理位置 4.渠道ID 5.区块身份id 6.app版本号
    private String type;
    //对应版本号
    private String  versionNumber;
    //对应的渠道id
    private String ditchId;
    //不限制条件
    private String condition;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getDitchId() {
        return ditchId;
    }

    public void setDitchId(String ditchId) {
        this.ditchId = ditchId;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
