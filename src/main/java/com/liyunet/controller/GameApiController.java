package com.liyunet.controller;

import com.liyunet.common.constant.Constant;
import com.liyunet.common.http.PostAndGet;
import com.liyunet.common.password.AES;
import com.liyunet.common.password.MD5;
import com.liyunet.common.pushToken.PushTokenInfo;
import com.liyunet.domain.AppUserMessage;
import com.liyunet.domain.GameMessage;
import com.liyunet.domain.gameapi.GameApiDemandOrder;
import com.liyunet.domain.gameapi.GameApiInfo;
import com.liyunet.domain.gameapi.GameApiRoleInfo;
import com.liyunet.domain.gameapi.OrderVo;
import com.liyunet.exception.ServiceException;
import com.liyunet.interceptor.anno.HTTPPublicResource;
import com.liyunet.interceptor.anno.HTTPPublicToken;
import com.liyunet.service.GameApiService;
import com.liyunet.util.PushRefinedCalculation;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.STRING;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wuyunan on 2018/9/17.
 */

@RestController
@RequestMapping("/api/game")
public class GameApiController {

	private final GameApiService gameApiService;

	@Autowired
	public GameApiController(GameApiService gameApiService) {
		this.gameApiService = gameApiService;
	}
	//
	// /**
	// * 蛋生绑定身份证转到区块身份
	// */
	// @HTTPPublicResource
	// @RequestMapping(value = "/getKycGrade", method = RequestMethod.POST)
	// public Object getKycGrade(
	// HttpServletRequest request, HttpServletResponse response,
	// @RequestParam(required = true) String token,
	// @RequestParam(required = true) String id,
	// @RequestParam(required = true, defaultValue = "zh") String languageType)
	// {
	//
	// return gameApiService.getKycGrade(token,id);
	//
	// }

	@HTTPPublicResource
	@RequestMapping(value = "/getLuckyUserContent", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getLuckyUserContent(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String token,
			@RequestParam(required = true, defaultValue = "zh") String languageType) {

		return gameApiService.getLuckyUserContent(token);

	}

	// 别的游戏交易扣除
	@RequestMapping(value = "/tradingDeductions", method = { RequestMethod.GET, RequestMethod.POST })
	public Object tradingDeductions(HttpServletRequest request, HttpServletResponse response,
			PushTokenInfo pushTokenInfo, @Param("id") String id, @ModelAttribute GameMessage gameMessage) {
		Integer userId = pushTokenInfo.getUserId();
		gameApiService.tradingDeductions(userId, gameMessage, id);
		return null;
	}

	// --------------------------------lkj------------------------------------------
	// 获取openid
	@RequestMapping(value = "/getOpenIdBytoken", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getOpenIdBytoken(HttpServletRequest request, HttpServletResponse response,
			PushTokenInfo pushTokenInfo) {
		Integer userId = pushTokenInfo.getUserId();
		String openid = AES.AESEncode(Constant.userIdSecret, userId + "");
		return openid;
	}

	// 获取游戏登录地址
	@HTTPPublicResource
	@RequestMapping(value = "/getLoginUrl", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getLoginUrl(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String appId) {
		List<GameApiInfo> apiInfos = gameApiService.findGameByAppId(appId);
		if (apiInfos == null || apiInfos.size() <= 0) {
			throw ServiceException.userException("", "403 error:Invalid openid");
		}
		return apiInfos.get(0).getLoginUrl();
	}

	// 获取汇率
	@HTTPPublicResource
	@RequestMapping(value = "/getExchangeRate", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getExchangeRate(HttpServletRequest request, HttpServletResponse response) {
		String exchangeRate = gameApiService.getExchangeRate();
		return exchangeRate;
	}

	// 创建角色
	@RequestMapping(value = "/createGameRole", method = { RequestMethod.GET, RequestMethod.POST })
	public Object createGameRole(HttpServletRequest request, HttpServletResponse response, PushTokenInfo pushTokenInfo,
			@RequestParam(required = true) String appId, @RequestParam(required = true) String openId,
			@RequestParam(required = true) String mchId, @RequestParam(required = true) String serverId,
			@RequestParam(required = true) String serverName, @RequestParam(required = true) String roleId,
			@RequestParam(required = true) String roleName, @RequestParam(required = true) String timestampStr,
			@RequestParam(required = true) String sign) {
		Integer userId = pushTokenInfo.getUserId();
		// 验证openid
		String openid1 = URLEncoder.encode(AES.AESEncode(Constant.userIdSecret, userId + ""));
		String openid2 = AES.AESEncode(Constant.userIdSecret, userId + "");
		if (!openId.equals(openid1) && !openId.equals(openid2)) {
			throw ServiceException.userException("", "无效的openid");
		}
		// 查游戏id是否存在
		List<GameApiInfo> apiInfos = gameApiService.findGameByAppId(appId);

		if (apiInfos == null || apiInfos.size() <= 0) {
			throw ServiceException.userException("", "appid不存在");
		}
		String mchid = apiInfos.get(0).getMchId();
		String apiSecret = apiInfos.get(0).getApiSecret();
		if (!mchId.equals(mchid)) {
			throw ServiceException.userException("", "无效的muchid");
		}
		if (!sign.equals(MD5.md5Java(apiSecret + mchid + timestampStr))) {
			throw ServiceException.userException("", "签名错误");
		}
		// 根据角色名服务器id角色id查角色
		List<GameApiRoleInfo> apiRoleInfos = gameApiService.findGameRoleExit(roleId, roleName, serverId,
				apiInfos.get(0).getId(), userId);
		if (apiRoleInfos != null && apiRoleInfos.size() > 0) {
			throw ServiceException.userException("", "角色已存在");
		}

		GameApiRoleInfo apiRoleInfo = new GameApiRoleInfo();
		apiRoleInfo.setCreatetime("");
		apiRoleInfo.setGameId(apiInfos.get(0).getId());
		apiRoleInfo.setOpenId(openid2);
		apiRoleInfo.setRoleId(roleId);
		apiRoleInfo.setRoleName(roleName);
		apiRoleInfo.setServerId(serverId);
		apiRoleInfo.setServerName(serverName);
		apiRoleInfo.setUserId(userId);
		try {
			Integer code = gameApiService.createGameRole(apiRoleInfo);
			if (code.intValue() == 1) {
				return "角色创建 成功";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	// 币达购买道具
	@RequestMapping(value = "/playAnBidtOrder", method = { RequestMethod.GET, RequestMethod.POST })
	public Object playAnBidtOrder(HttpServletRequest request, HttpServletResponse response, PushTokenInfo pushTokenInfo,
			@RequestParam(required = true) String appId, @RequestParam(required = true) String mchId,
			@RequestParam(required = true) String timestampStr, @RequestParam(required = true) String sign,
			@RequestParam(required = true) String billNo, // 订单号
			@RequestParam(required = true) String goodsName, // 商品名
			@RequestParam(required = true) String totalFee// 人民币的价格
	) {
		Integer userId = pushTokenInfo.getUserId();
		// 查游戏id是否存在
		List<GameApiInfo> apiInfos = gameApiService.findGameByAppId(appId);

		if (apiInfos == null || apiInfos.size() <= 0) {
			throw ServiceException.userException("", "appid不存在");
		}
		String mchid = apiInfos.get(0).getMchId();
		String apiSecret = apiInfos.get(0).getApiSecret();
		if (!mchId.equals(mchid)) {
			throw ServiceException.userException("", "无效的muchid");
		}
		if (!sign.equals(MD5.md5Java(apiSecret + mchid + timestampStr))) {
			throw ServiceException.userException("", "签名错误");
		}

		if (Double.parseDouble(totalFee) <= 0) {
			throw ServiceException.userException("", "充值金额必须大于0");
		}
		// 转换成bidt的数量(获取汇率)
		String exchangeRate = gameApiService.getExchangeRate();
		double totalF = Double.parseDouble(totalFee);
		double exchangeR = Double.parseDouble(exchangeRate);
		// 币达数量
		double bidtNum = PushRefinedCalculation.mul(totalF, exchangeR);
		// 下单

		GameApiDemandOrder order = null;
		try {
			order = gameApiService.playAnBidtOrder(billNo, goodsName, bidtNum, userId, totalFee,
					apiInfos.get(0).getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 下单成功回调
		if (order != null) {
			// gameOrderNum="123456789"&productName="首充礼包"&totalAmount="88"&demandOrder="7654321"
			String param = "gameOrderNum=\"" + order.getGameOrderNum() + "\"&productName=\"" + order.getProductName()
					+ "\"&totalAmount=\"" + order.getProductPrice() + "\"&demandOrder=\"" + order.getDemandNum() + "\"";
			try {
				String sendGet = PostAndGet.sendGet(apiInfos.get(0).getCallbackUrl(), param);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			throw ServiceException.userException("", "订单失败");
		}
		return null;
	}

	// 根据token查订单记录
	@RequestMapping(value = "/getOrderListBytoken", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getOrderListBytoken(HttpServletRequest request, HttpServletResponse response,
			PushTokenInfo pushTokenInfo, @RequestParam(required = true) Integer page,
			@RequestParam(required = true) Integer rows) {
		Integer userId = pushTokenInfo.getUserId();
		List<OrderVo> order = gameApiService.getOrderListBytoken(userId, page, rows);
		return order;
	}

}
