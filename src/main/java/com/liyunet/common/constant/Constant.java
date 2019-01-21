package com.liyunet.common.constant;

import java.util.HashMap;
import java.util.Map;

public class Constant {

	public static final String CURRENT_USER_ID = "userId";
	public static final String CURRENT_SYS_USER_ID = "sysUserId";
	public static final Integer DEFAULT_USER_ID = 1;
	// cookie过期小时 14天
	public static final Long COOKIE_EXPIRE_HOURS = 336L;
	public static final Long APP_TOKEN_HOURS = 336L;
	public static final String BENLEI_CUSTOMER_ID_STRING = "80000012";
	public static final Integer BENLEI_CUSTOMER_ID_INT = 80000012;
	
	//H5用户opendid加密密钥
	public static final String userIdSecret = "LYWH@#$!32";
	//public static final String IP_ADDRESS = "http://www.liyunet.com:8888";
	
	public static final String IP_ADDRESS = "http://www.liyugame.com:4080";
	public static final String IP_NATIVE = "http://123.207.171.234:7666";
	
	//public static final String IP_ADDRESS = "http://123.206.88.117:8888";
	//public static final String IP_NATIVE = "http://123.206.88.117:7666";
	
	//畅天游sms短信参数
	public static final String msgTemplate="【Time Treaty Team】您的短信验证码是:{0}，感谢注册时间条约币账号，请于30分钟内填写。本条短信免费。";//短信内容模板
	public static final String un="time-1";//短信平台用户名
	public static final String pwd="eb747a";//短信平台密码
	
	//投票分类  1话题   2产品   3决策   4新建  
	public static final Map<String,String> VOTE_TYPE_MAP=new HashMap<String,String>(){{
		put("1","话题");
		put("2","产品");
		put("3","决策");
		put("4","新建");
	}};
}
