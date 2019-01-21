package com.liyunet.common.mail;

public final class RegOrPwdEmailHolder {

	private String email;
	
	private String code;
	
	private RegOrPwdEmailType type;
	
	private String languageType="en";//zh:中文  en:英文
	
	public RegOrPwdEmailHolder(String email , String code,RegOrPwdEmailType type){
		this.code = code;
		this.email = email;
		this.type = type;
	}
	
	public boolean isShutdown(){
		return "shutdown".equals(getCode());
	}
	
	public static RegOrPwdEmailHolder getShutdownInstance(){
		return new RegOrPwdEmailHolder("","shutdown",null);
	}
	
	public String getEmail() {
		return email;
	}
	public String getCode() {
		return code;
	}
	
	public String getLanguageType() {
		return languageType;
	}

	public void setLanguageType(String languageType) {
		this.languageType = languageType;
	}

	public RegOrPwdEmailType getType() {
		return type;
	}

	/**
	 * 	标记发送的是注册邮件还是找回密码邮件
	 */
	public static enum RegOrPwdEmailType{
		REG,PWD;
	}
}
