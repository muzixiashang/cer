package com.liyunet.domain.gameapi;

public class OrderVo {
private Integer id;
private String sysOrderNum;
private String num;
private Integer payType;
private String createtime;
private String gameName;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getSysOrderNum() {
	return sysOrderNum;
}
public void setSysOrderNum(String sysOrderNum) {
	this.sysOrderNum = sysOrderNum;
}
public String getNum() {
	return num;
}
public void setNum(String num) {
	this.num = num;
}
public Integer getPayType() {
	return payType;
}
public void setPayType(Integer payType) {
	this.payType = payType;
}
public String getCreatetime() {
	return createtime;
}
public void setCreatetime(String createtime) {
	this.createtime = createtime;
}
public String getGameName() {
	return gameName;
}
public void setGameName(String gameName) {
	this.gameName = gameName;
}

}
