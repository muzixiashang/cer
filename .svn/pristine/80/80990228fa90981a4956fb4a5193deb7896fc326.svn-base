package com.liyunet.common.sms;

import org.apache.commons.lang3.RandomUtils;

/**
 *	生成6位短信验证码 
 */
public class SmsCodeGenerator {

	public static String genSmsCode(){
		StringBuilder sb = new StringBuilder();
		while(sb.length() < 6){
			int nextInt = RandomUtils.nextInt(0, 10);
			sb.append(nextInt);
		}
		return sb.toString();
	}
	
	
	public static void main(String[] args) {
		String genSmsCode = genSmsCode();
		System.out.println(genSmsCode);
	}
}
