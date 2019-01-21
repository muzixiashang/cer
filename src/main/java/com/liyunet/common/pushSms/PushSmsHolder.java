package com.liyunet.common.pushSms;

public final class PushSmsHolder {
	private String phoneNum;
	private String region = "86";
	private String code;
	private String type;

	public static PushSmsHolder getShutdownInstance(){
		return new PushSmsHolder("","");
	}

	public PushSmsHolder(String phoneNum) {
		super();
		this.phoneNum = phoneNum;
	}

	public PushSmsHolder(String phoneNum, String code) {
		super();
		this.phoneNum = phoneNum;
		this.code = code;
	}


	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
