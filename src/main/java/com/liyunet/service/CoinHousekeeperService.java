package com.liyunet.service;

/**
 * 
 * @author lkj
 *
 */
// 币管家接口
public interface CoinHousekeeperService {
	// 查询所有投资记录
	Object getHKlist(Integer page, Integer rows);
   //查询个人收益
	Object getProfitBytoken(Integer userId);
	//根据id查项目
	Object getHKbyId(Integer id);
	//支付
	Object hkpay(Integer userId, Integer pid, String num, Integer type,String address);
	//获取总资产
	Object getHkcount(Integer userId);
	//获取总人数
	Object getHKpeoplenum();
	//查询投资记录
	Object getHKpayinfo(Integer userId,Integer page,Integer rows);
	//查询详情
	Object getHKpaydetailsById(Integer id);
	//判断用户是否已购买
	Object getUserPaystate(Integer userId, Integer pid);
	//判断用户是否投资
	Object getUserPayinfostate(Integer userId);
	//获取合约地址
	Object getContractAdress(Integer id);
	 
}
