package com.liyunet.job;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.liyunet.common.util.IpResourceLocation;
import com.liyunet.common.util.UrlLoad;
import com.liyunet.domain.luckbag.LuckbagInfo;
import com.liyunet.mapper.luckbagMapper.LuckbagInfoMapper;
import com.liyunet.service.LuckBagService;
import com.liyunet.service.impl.LuckBagServiceImpl;
import com.liyunet.thread.EndTimeThread;
import com.liyunet.util.SpringTool;

@Component("luckBagJob")
public class LuckBagJob {
	private static Logger opLogger = LogManager.getLogger("exception");
	@Autowired
	private LuckbagInfoMapper im;
	public static LuckBagJob LuckBagJob;

	@PostConstruct
	public void init() {
		LuckBagJob = this;
		LuckBagJob.im = this.im;
	}

	public void start() {
		try {
			// 查找状态是1的红包信息

			String load = UrlLoad.load(IpResourceLocation.LOCAL_IP+"/timetreaty_certification/api/luckbag/returnLuckbag","");
		} catch (Exception e) {
			e.printStackTrace();
			opLogger.error("紅包退回失败:", e);
		}
	}

	public static void main(String[] args) {
		try {
			String load = UrlLoad.load(IpResourceLocation.LOCAL_IP+"/timetreaty_certification/api/luckbag/returnLuckbag","");
		System.out.println(load);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}