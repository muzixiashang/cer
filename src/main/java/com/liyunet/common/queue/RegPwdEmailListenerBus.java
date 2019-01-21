package com.liyunet.common.queue;

import com.liyunet.common.mail.MailSender;
import com.liyunet.common.mail.RegOrPwdEmailHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;


/**
 *	发送注册 找回密码邮件 
 */

public class RegPwdEmailListenerBus {
	//发送 注册/找回密码 邮件
	public static final LinkedBlockingQueue<RegOrPwdEmailHolder> REG_PWD_BUS = new LinkedBlockingQueue<>(2048);
	
	private static Logger logger = LogManager.getRootLogger();
	
	public void start(){
		Runnable r1 = () -> {
			while(true){
				try {
					RegOrPwdEmailHolder holder = REG_PWD_BUS.take();
					if(holder.isShutdown())break;
					MailSender.sendRegOrPwdEmail(holder);
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		new Thread(r1).start();
		logger.info("注册、找回密码邮箱发送队列已启动");
	}
}
