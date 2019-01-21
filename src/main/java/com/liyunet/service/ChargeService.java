package com.liyunet.service;

/**
 * 
 * @author lkj 充币
 *
 */
public interface ChargeService {
	// 获取币种列表
	Object getCoinList(Integer type);

	// 判断用户是否绑定地址
	Object checkChargeState(Integer userId, Integer cid);

	// 绑定地址
	Object bindingAddress(Integer userId, Integer cid, String address);

	// 修改绑定地址
	Object updateAddress(Integer id, String address);

	// 获取币种详情
	Object getCoinInfo(Integer userId, Integer cid, Integer type);

	// 充值
	Object charge(Integer userId, Integer addressid, String bidtnum);

	// 充值详情
	Object chargeInfo(Integer id);

	// 查充值记录
	Object getChargeHistory(Integer userId);

	// 充币接口
	Object addCharge(Integer id, Integer updateState, String ression);

	// 账单
	Object getBill(Integer userId);

}
