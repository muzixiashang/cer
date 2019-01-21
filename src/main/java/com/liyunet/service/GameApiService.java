package com.liyunet.service;

import java.util.List;
import java.util.Map;

import com.liyunet.domain.AppUserMessage;
import com.liyunet.domain.GameMessage;
import com.liyunet.domain.gameapi.GameApiDemandOrder;
import com.liyunet.domain.gameapi.GameApiInfo;
import com.liyunet.domain.gameapi.GameApiRoleInfo;
import com.liyunet.domain.gameapi.OrderVo;

/**
 */

public interface GameApiService {

	Object getKycGrade(String token, String id);

	Object getLuckyUserContent(String token);

	void tradingDeductions(Integer userId, GameMessage gameMessage, String blockId);

	// 查游戏id
	List<GameApiInfo> findGameByAppId(String appId);

	// 查角色是否存在
	List<GameApiRoleInfo> findGameRoleExit(String roleId, String roleName, String serverId,Integer gameId,Integer userId);

	int createGameRole(GameApiRoleInfo apiRoleInfo);

	// 获取汇率
	String getExchangeRate();

	// 下单
	GameApiDemandOrder playAnBidtOrder(String billNo, String goodsName, double bidtNum, Integer userId,
			String totalFee,Integer gameId);

	int updateorder(GameApiDemandOrder order);
   //获取订单列表
	List<OrderVo>  getOrderListBytoken(Integer userId, Integer page, Integer rows);

}
