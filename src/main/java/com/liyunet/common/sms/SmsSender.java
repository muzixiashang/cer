package com.liyunet.common.sms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;


public class SmsSender {
	//鲤鱼游戏app短信验证码参数
//	private static final int APP_ID = 1400025773;
//	private static final String APP_KEY = "7effa288b68a25f61fbc8f9f8083ff8e";
//	private static final int TEMP_ID = 11548;
	private static final int APP_ID = 1400037019;
	private static final String APP_KEY = "8caa2f610ccd2f46a2711c91de1a30f1";
	private static final int TEMP_ID = 31629;
	private static Logger logger = LogManager.getLogger("mail_sms");
	private static Logger opLogger = LogManager.getLogger("exception");
	
	/**
	 * 发送时间	区号	发送电话号码	状态码	
	 * 
	 * 
	 * @param smsCode
	 * @param region
	 * @param phoneNum
	 * @throws Exception
	 */
	public static void send(String smsCode , String region , String phoneNum) throws Exception{
		String sb= ChangTianYouSmsUtil.sendSmsCode(smsCode, phoneNum);
//		SmsSingleSender singleSender = new SmsSingleSender(APP_ID, APP_KEY);
//    	SmsSingleSenderResult singleSenderResult;
//    	ArrayList<String> params = new ArrayList<>();
//    	params.add(smsCode);
//    	singleSenderResult = singleSender.sendWithParam(region, phoneNum, TEMP_ID, params, "", "", "");
//    	StringBuilder sb = new StringBuilder(); 
//    	sb.append(LocalDateTime.now().toString() + "\t");
//    	sb.append(phoneNum + "\t");
//    	sb.append(EmailSMSTypeEnum.SMS + "\t");
//    	System.out.println(singleSenderResult);
    	logger.error(sb.toString());
	}
	public static void sendPwd(String smsCode , String region , String phoneNum) throws Exception{
		String sb= ChangTianYouSmsUtil.sendSmsCodePwd(smsCode, phoneNum);
		logger.error(sb.toString());
	}
	public static void main(String[] args) {
		try {
    		// 请根据实际 appid 和 appkey 进行开发，以下只作为演示 sdk 使用
    		int appid = 1400025773;
    		String appkey = "7effa288b68a25f61fbc8f9f8083ff8e";
    		
    		String phoneNumber1 = "13120036179"; 
    		String phoneNumber2 = "13066936683";
    		String phoneNumber3 = "18516855892"; 
    		int tmplId = 11548;

    		// 初始化单发
	    	SmsSingleSender singleSender = new SmsSingleSender(appid, appkey);
	    	SmsSingleSenderResult singleSenderResult;
	
	    	// 普通单发
	    	//singleSenderResult = singleSender.send(0, "86", phoneNumber2, "测试短信，普通单发，深圳，小明，上学。", "", "");
	    	//System.out.println(singleSenderResult);
	
	    	// 指定模板单发
	    	// 假设短信模板内容为：测试短信，{1}，{2}，{3}，上学。
	    	ArrayList<String> params = new ArrayList<>();
	    	params.add("574854");
	    	singleSenderResult = singleSender.sendWithParam("86", phoneNumber2, tmplId, params, "", "", "");
	    	System.out.println(singleSenderResult);
    	} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
