package com.liyunet.common.pushToken;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 订单号工厂类(单例模式)
 * @author wcl
 * @date 2017年9月29日 下午1:58:12
 */
public class PushCodeFactory {

	private  static Log log=LogFactory.getLog(PushCodeFactory.class);
	private static PushCodeFactory codeFactory;

	private static Map map=new HashMap(0);//map容器
	private final int MAP_NUM=50;
	private static int usercode=73416561;//初始化用户代码userCode

	private static Map tranmap=new HashMap(0);//map容器
	private final int TRAN_MAP_NUM=50;
	private static int trancode=10000;//初始化ttc币交易流水号



	public synchronized static PushCodeFactory getInstance(){
		if(null==codeFactory){
			codeFactory=new PushCodeFactory();
		}
		return codeFactory;
	}

//	private PushCodeFactory(){
//		init();
//		initTranCode();
//	}
	//=================================================用户代码相关start==============================================
	//返回编号
	public synchronized Integer getCodeNum(){
		if(map.size()<1){
			initMap();
		}
		int codeNum=(Integer)map.get(map.size()-1);
		map.remove(map.size()-1);
		return codeNum;
	}
	//编号初始化
//	private void init(){
//		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
//		BaseDao dao=(BaseDao)wac.getBean("pushUserInfoDao");
//		//BaseDao dao = (BaseDao)SpringContext.getContext().getBean("hibernateFacadeDAO");
//		String s_hql = "select goo.userCode from UserInfo  goo order  by goo.id desc ";//code
//		List suitList = dao.query(s_hql, 0, -1, new ArrayList());
//		usercode=getMaxCodeNum(suitList);
//		initMap();
//	}
	private int getMaxCodeNum(List suitList){
		int mon=0;
		int mt=0;
		if(suitList!=null && suitList.size()>0){
			mt=(Integer)suitList.get(0);
		}	
		mt=mt==0?usercode:mt;
		log.info("初始化用户编码："+mt);
		return mt;
	}
	
	//编号容器
	private void initMap(){
		for(int i=MAP_NUM-1;i>=0;i--){
			usercode+=1;
			map.put(i, usercode);
		 }
	}
	
	//=================================================用户代码相关end==============================================
	
	
	
	//=================================================ttc币交易流水号相关start=======================================
	//返回流水号
	public synchronized Integer getTranCodeNum(){
		if(tranmap.size()<1){
			initTranCodeMap();
		}
		int trancodeNum=(Integer)tranmap.get(tranmap.size()-1);
		tranmap.remove(tranmap.size()-1);
		return trancodeNum;
	}
//	//初始化流水号
//	private void initTranCode(){
//		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
//		BaseDao dao=(BaseDao)wac.getBean("userInfoDao");
//		String s_hql = "select goo.tranCode from TtcTranRecord  goo order  by goo.id desc ";//code
//		List suitList = dao.query(s_hql, 0, -1, new ArrayList());
//		trancode=getMaxTranCode(suitList);
//		initTranCodeMap();
//	}
	//获取库中最大流水号
	private int getMaxTranCode(List suitList){
		int mon=0;
		Integer mt=0;
		if(suitList!=null && suitList.size()>0){
			mt=Integer.parseInt(String.valueOf(suitList.get(0)));
		}	
		mt=mt==0?trancode:mt;
		log.info("初始化流水号："+mt);
		return mt;
	}
	//编号容器
	private void initTranCodeMap(){
		for(int i=TRAN_MAP_NUM-1;i>=0;i--){
			trancode+=1;
			tranmap.put(i, trancode);
		 }
	}
	//=================================================ttc币交易流水号相关end=======================================
}
