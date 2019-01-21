package com.liyunet.service;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author lkj 竞猜
 *
 */
public interface BetService {
  
	//查所有的banner
	Object getBetBanner();
    
	//查询所有竞猜项目
	Object getBetList(Integer type,Integer page, Integer rows);
 
	//获取竞猜详情
	Object getBetDetails(Integer id);

	//查竞猜详情
	Object getBetDetailsBytoken(Integer id, Integer userId);
     
	//下注
	Object bet(Integer oid, Integer userId,Integer betnum);
     //查竞猜记录
	Object getBetHistoryBytoken(Integer userId);

	Object eggcontrol();
    //发奖励
	Object setOption(Integer oid);
  

	
	

}
