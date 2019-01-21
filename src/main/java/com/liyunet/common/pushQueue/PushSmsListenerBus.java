package com.liyunet.common.pushQueue;

import com.liyunet.common.pushSms.PushSmsHolder;
import com.liyunet.common.pushSms.PushSmsSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.LinkedBlockingQueue;


/**
 *	短信验证码发送队列 
 */
public class PushSmsListenerBus {
	//发送 注册/找回密码 邮件
	public static final LinkedBlockingQueue<PushSmsHolder> REG_SMS_BUS = new LinkedBlockingQueue<>(2048);
	
	private static Logger logger = LogManager.getRootLogger();
	
	public void start(){
		Runnable r1 = () -> {
			while(true){
				try {
					PushSmsHolder holder = REG_SMS_BUS.take();
					PushSmsSender.send(holder.getPhoneNum(),holder.getCode());
				} catch (Exception e) {
				} 
			}
		};
		new Thread(r1).start();
		logger.info("验证码发送队列已启动");
	}
}
