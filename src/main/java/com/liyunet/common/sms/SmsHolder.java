package com.liyunet.common.sms;

public final class SmsHolder {
	private String phoneNum;
	private String smsCode;
	private String region = "86";
	
	public boolean shutdown(){
		return "shutdown".equals(smsCode);
	}
	
	public static SmsHolder getShutdownInstance(){
		return new SmsHolder("","shutdown" ,"");
	}
	
	public SmsHolder(String phoneNum, String smsCode) {
		super();
		this.phoneNum = phoneNum;
		this.smsCode = smsCode;
	}
	
	public SmsHolder(String phoneNum, String smsCode, String region) {
		super();
		this.phoneNum = phoneNum;
		this.smsCode = smsCode;
		this.region = region;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getSmsCode() {
		return smsCode;
	}
	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
}
