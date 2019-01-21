package com.liyunet;


/*
public final class ResponseCode {
	public static String SUCCESS = "10000";
	public static String ERROR = "10001";
}*/


public enum ResponseCode {
	SUCCESS("10000")
	,ERROR("10001")
	//ajax成功
	,AJAXSUCCESS("20000")
	//系统或传参异常
	,AJAXSYSTEMERROR("20001")
	//业务异常
	,AJAXSERVICEERROR("20002")
	//未登录异常
	,AJAXNOTLOGINERROR("20003");
	
	private final String value;
	
	ResponseCode(String value){
		this.value = value;
	}
	
	public String getValue() {
        return value;
    }
}