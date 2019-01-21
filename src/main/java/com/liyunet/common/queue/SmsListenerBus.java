package com.liyunet.common.queue;

import com.liyunet.common.sms.SmsHolder;
import com.liyunet.common.sms.SmsSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.LinkedBlockingQueue;


/**
 *	短信验证码发送队列 
 */
public class SmsListenerBus {
	//发送 注册/找回密码 邮件
	public static final LinkedBlockingQueue<SmsHolder> REG_SMS_BUS = new LinkedBlockingQueue<>(2048);
	
	private static Logger logger = LogManager.getRootLogger();
	
	public void start(){
		Runnable r1 = () -> {
			while(true){
				try {
					SmsHolder holder = REG_SMS_BUS.take();
					if(holder.shutdown())break;
					SmsSender.send(holder.getSmsCode(), holder.getRegion(), holder.getPhoneNum());
				} catch (Exception e) {
				} 
			}
		};
		new Thread(r1).start();
		logger.info("验证码发送队列已启动");
	}
}
