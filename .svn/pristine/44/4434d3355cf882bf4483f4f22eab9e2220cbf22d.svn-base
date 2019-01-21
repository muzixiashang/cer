package com.liyunet.job;

import com.liyunet.thread.EndTimeThread;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component("syncEndTimeJob")
public class EndTimeJob
{
	private static Logger opLogger = LogManager.getLogger("exception");

	public void start()
	{
		try
		{
			EndTimeThread endThread = new EndTimeThread();
			new Thread(endThread).start();
		} catch (Exception e) {
			e.printStackTrace();
			opLogger.error("关闭活动失败:", e);
		}
	}




}