package com.liyunet.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.liyunet.service.impl.GameApiServiceImpl;



/**
 * 订单号工厂类(单例模式)
 * @author wcl
 * @date 2017年9月29日 下午1:58:12
 */
public class DemandNumFactory {
	
	
	private  static Log log=LogFactory.getLog(DemandNumFactory.class);
	private static Map map=new HashMap(0);//map容器
	private final int MAP_NUM=50;
	private static int DemandNum=1000001;//初始化订单号
	
	private static DemandNumFactory orderNumFactory;
	
	
	public synchronized static DemandNumFactory getInstance(){
		if(null==orderNumFactory){
			orderNumFactory=new DemandNumFactory();
		}
		return orderNumFactory;
	}
	
	private DemandNumFactory(){
		init();
	}
	
	//返回订单号
	public synchronized Integer getDemandNum(){
		if(map.size()<1){
			initMap();
		}
		int orderId=(Integer)map.get(map.size()-1);
		map.remove(map.size()-1);
		return orderId;
	}
	//订单号初始化
	private void init(){
		List<Integer> suitList=null;
		try {
			suitList = GameApiServiceImpl.getOrderId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DemandNum=getMaxOrderNum(suitList);
		initMap();
	}
	private int getMaxOrderNum(List<Integer> suitList){
		int mon=0;
		int mt=0;
		if(suitList!=null && suitList.size()>0){
			mt= suitList.get(0);
		}	
		mt=mt==0?DemandNum:mt;
		log.info("初始化预订单："+mt);
		return mt;
	}
	
	//订单号容器
	private void initMap(){
		for(int i=MAP_NUM-1;i>=0;i--){
			DemandNum+=1;
			map.put(i, DemandNum);
		 }
	}
}
