package com.liyunet.thread;

import com.liyunet.service.EndTimeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class EndTimeThread implements Runnable
{
    private static Logger opLogger = LogManager.getLogger("exception");

    public void run()
    {
        syncChainbSubNews();
    }

    public void syncChainbSubNews() {
        try {
            WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
            EndTimeService endTimeService = (EndTimeService) wac.getBean("endTimeService");
            endTimeService.syncEndTme();
        } catch (Exception e) {
            e.printStackTrace();
            opLogger.error("价格抓取FBT失败:", e);
        }
    }
}