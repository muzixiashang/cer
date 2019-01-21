package com.liyunet.service;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author lkj 红包接口
 *
 */
public interface LuckBagService {
      //发红包
	Object handLuckBag(Integer userId, String bidtnum, String bagnum, Integer lbid, String money, Integer type);
    //查找祝福语
	Object getLuckbagBlessList();
	//根据订单号查用户信息
	Object getBlockIdByorderId(String orderId);
	//拆红包
	Object getLuckBag(Integer userId, Integer id);
	//查红包详情
	Object getLuckBagInfo(Integer userId, Integer id);
	//输入区块id获取token
	Object getTokenaByuseraccount(HttpServletRequest request,String userAccount);
	//红包退回
 	Object  returnLuckbag();
	//获取发红包记录
	Object getsendLuckBagHistory(Integer userId, Integer page, Integer rows);
	//获取收红包记录
	Object getaccepLuckBagHistory(Integer userId, Integer page, Integer rows);
	//查红包详情
	Object getLuckBagInfoNotoken(Integer id);

	
	

}
