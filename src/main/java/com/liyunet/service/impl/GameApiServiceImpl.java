package com.liyunet.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liyunet.common.constant.Constant;
import com.liyunet.common.constant.DateHelper;
import com.liyunet.common.password.AES;
import com.liyunet.common.password.MD5;
import com.liyunet.common.password.MD5Util;
import com.liyunet.common.pushToken.PushAuthHelper;
import com.liyunet.common.util.DemandNumFactory;
import com.liyunet.common.util.IpResourceLocation;
import com.liyunet.common.util.TokenUtil;
import com.liyunet.common.util.UrlLoad;
import com.liyunet.domain.*;
import com.liyunet.domain.gameapi.GameApiDemandOrder;
import com.liyunet.domain.gameapi.GameApiExchangeRate;
import com.liyunet.domain.gameapi.GameApiInfo;
import com.liyunet.domain.gameapi.GameApiInfoExample;
import com.liyunet.domain.gameapi.GameApiInfoExample.Criteria;
import com.liyunet.domain.gameapi.GameApiRoleInfo;
import com.liyunet.domain.gameapi.GameApiRoleInfoExample;
import com.liyunet.domain.gameapi.OrderVo;
import com.liyunet.exception.ServiceException;
import com.liyunet.mapper.game_api.GameApiMapper;
import com.liyunet.mapper.gameapiMapper.GameApiDemandOrderMapper;
import com.liyunet.mapper.gameapiMapper.GameApiExchangeRateMapper;
import com.liyunet.mapper.gameapiMapper.GameApiInfoMapper;
import com.liyunet.mapper.gameapiMapper.GameApiRoleInfoMapper;
import com.liyunet.service.GameApiService;
import com.liyunet.util.PushRefinedCalculation;
import com.liyunet.vo.user.LuckyUserInfoVo;
import com.liyunet.vo.user.PushUserInfoVo;
import com.liyunet.vo.user.UserInfoVo;
import com.sun.mail.util.UUDecoderStream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 */

@Service("gameApiService")
@Transactional
public class GameApiServiceImpl implements GameApiService {

	private final GameApiMapper gameApiMapper;

	@Autowired
	public GameApiServiceImpl(GameApiMapper gameApiMapper) {
		this.gameApiMapper = gameApiMapper;
	}

	@Autowired
	private GameApiInfoMapper gim;

	@Autowired
	private GameApiRoleInfoMapper grm;
	@Autowired
	private GameApiDemandOrderMapper gom;

	@Autowired
	private GameApiExchangeRateMapper gem;

	@Override
	public Object getKycGrade(String token, String id) {

		String userId = TokenUtil.getUserId(token);

		List<UserInfo> userInfoList = gameApiMapper.getUserInfo(userId);
		if (userInfoList != null && userInfoList.size() > 0) {
			UserInfo userInfo = userInfoList.get(0);
			String userAccount = userInfo.getUserAccount();
			if (!userAccount.equals(id)) {
				throw ServiceException.userException("Id with Token does not match！", "id与Token不匹配!");
			}
			String identityCard = userInfo.getIdentityCard();
			List<AntiAddiction> associatedAccountList = gameApiMapper.getAntiAddictionLevel(identityCard);
			if (associatedAccountList != null && associatedAccountList.size() > 0) {
				AntiAddiction antiAddiction = associatedAccountList.get(0);
				String level = antiAddiction.getLevel();
				if (level != null && !"".equals(level)) {
					return level;
				} else {
					return 0;
				}

			} else {
				return 0;
			}

		} else {
			throw ServiceException.userException("Token expired or mistake！", "Token有误或过期!");
		}

	}

	@Override
	public Object getLuckyUserContent(String token) {
		String userId = TokenUtil.getUserId(token);
		List<UserInfo> userInfoList = gameApiMapper.getUserInfo(userId);
		if (userInfoList != null && userInfoList.size() > 0) {
			UserInfo dbEnpUser = userInfoList.get(0);
			String jwt = PushAuthHelper.createJsonWebToken(dbEnpUser.getId(), "pc", TimeUnit.HOURS,
					Constant.COOKIE_EXPIRE_HOURS);
			LuckyUserInfoVo vo = new LuckyUserInfoVo();
			vo.setToken(jwt);
			vo.setId(dbEnpUser.getUserAccount());
			vo.setuId(dbEnpUser.getuId());
			return vo;
		}
		return null;
	}

	@Override
	public void tradingDeductions(Integer userId, GameMessage gameMessage, String blockId) {

		List<UserInfo> userInfos = gameApiMapper.getUserInfo(userId + "");
		if (userInfos == null || userInfos.size() < 1) {
			throw ServiceException.userException("", "参数异常,扣费失败!");
		}
		UserInfo userInfo1 = userInfos.get(0);
		if (!userInfo1.getUserAccount().equals(blockId)) {
			throw ServiceException.userException("", "参数异常,扣费失败!");
		}
		String identityCard = userInfo1.getIdentityCard();
		List<AntiAddiction> antiAddictionList = gameApiMapper.getAntiAddictionLevel(identityCard);
		if (antiAddictionList == null || antiAddictionList.size() < 1) {
			throw ServiceException.userException("", "未通过KYC2级认证,扣费失败!");
		}
		AntiAddiction antiAddiction = antiAddictionList.get(0);
		String level = antiAddiction.getLevel();
		if (!"2".equals(level)) {
			throw ServiceException.userException("", "未通过KYC2级认证,扣费失败!");
		}
		List<UserInfo> userInfoList = gameApiMapper.getUserInfoByUserAccount("100000");
		if (userInfoList == null || userInfoList.size() < 1) {
			throw ServiceException.userException("", "参数异常,扣费失败!");
		}
		UserInfo userInfo = userInfoList.get(0);
		// String blockAddress = userInfo.getBlockAddress();
		gameMessage.setToUserAddress(userInfo.getId() + "");
		gameMessage.setCreateTime(getCurrentTime());
		String sign = gameMessage.getSign();
		String timestampStr = gameMessage.getTimestampStr();
		String mch_id = gameMessage.getMch_id();
		String app_id = gameMessage.getApp_id();
		String total_fee = gameMessage.getTotal_fee();

		List<GameMerchantMessage> gameMerchantMessageList = gameApiMapper.getGameMerchantMessage(mch_id, app_id);
		if (gameMerchantMessageList == null || gameMerchantMessageList.size() < 1) {
			throw ServiceException.userException("", "参数异常,扣费失败!");
		}
		GameMerchantMessage gameMerchantMessage = gameMerchantMessageList.get(0);
		// String secret_key = gameMerchantMessage.getSecret_key();
		String encode = "";
		encode = MD5.md5Java(mch_id + timestampStr);

		if (!encode.equals(sign) || encode.equals("")) {
			throw ServiceException.userException("", "交易异常,扣费失败!");
		}
		gameMessage.setUserId(userId);

		String s = null;
		double v = Double.parseDouble(total_fee);
		if (v <= 0) {
			throw ServiceException.userException("", "参数异常,扣费失败!");
		}
		double mul = PushRefinedCalculation.mul(10, v);
		try {
			String encode1 = URLEncoder.encode(AES.AESEncode("LYWH@#$!32", userId + ""), "UTF-8");
			String encode2 = URLEncoder.encode(AES.AESEncode("LYWH@#$!32", userInfo.getId() + ""), "UTF-8");
			String encode3 = URLEncoder.encode(AES.AESEncode("9$dz4Y33oy", mul + ""), "UTF-8");
			String encode4 = URLEncoder.encode(AES.AESEncode("9$dz4Y33oy", "yobXT2ENRb" + timestampStr), "UTF-8");
			s = UrlLoad.load(IpResourceLocation.TT_EGGWORLD_IP + "/ttc-eggworld/ttc/record",
					"openId=" + encode1 + "&muchMoney=" + encode3 + "&transactionType=32"
							+ "&app_id=3360a88e4318896c9fe3e031e64541f9c176af67fa87634f&mch_id=yobXT2ENRb&timestampStr="
							+ timestampStr + "&sign=" + encode4 + "&toopenid=" + encode2 + "&poundageType=0"
							+ "&poundage=0");

		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = JSON.parseObject(s);
		String string = jsonObject.getString("state");
		JSONObject jsonObject1 = JSON.parseObject(string);
		String code = jsonObject1.getString("code");
		if ("20000".equals(code)) {

			gameMessage.setId(null);
			gameMessage.setStatus(1);
			int i = gameApiMapper.saveGameMessage(gameMessage);
			if (i < 1) {
				throw ServiceException.userException("", "交易异常,扣费失败!");
			}

		} else if ("20002".equals(code)) {

			String msg = jsonObject1.getString("msg");
			gameMessage.setStatus(0);
			gameMessage.setReason(msg);
			gameApiMapper.saveGameMessage(gameMessage);
			throw ServiceException.userException("", msg);
		}

	}

	// 当前时间
	public String getCurrentTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format1 = format.format(new Date());
		return format1;
	}

	// 查游戏是否存在
	@Override
	public List<GameApiInfo> findGameByAppId(String appId) {
		// TODO Auto-generated method stub
		GameApiInfoExample example = new GameApiInfoExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andAppIdEqualTo(appId);
		List<GameApiInfo> selectByExample = gim.selectByExample(example);
		return selectByExample;
	}

	// 查角色是否存在
	@Override
	public List<GameApiRoleInfo> findGameRoleExit(String roleId, String roleName, String serverId, Integer gameId,
			Integer userId) {
		// TODO Auto-generated method stub
		GameApiRoleInfoExample example = new GameApiRoleInfoExample();
		com.liyunet.domain.gameapi.GameApiRoleInfoExample.Criteria createCriteria = example.createCriteria();
		if (roleId != null || !roleId.equals("")) {
			createCriteria.andRoleIdEqualTo(roleId);
		}
		if (roleName != null || !roleName.equals("")) {
			createCriteria.andRoleNameEqualTo(roleName);
		}
		if (serverId != null || !serverId.equals("")) {
			createCriteria.andServerIdEqualTo(serverId);
		}

		createCriteria.andGameIdEqualTo(gameId);
		createCriteria.andUserIdEqualTo(userId);
		List<GameApiRoleInfo> selectByExample = grm.selectByExample(example);
		return selectByExample;
	}

	@Override
	public int createGameRole(GameApiRoleInfo apiRoleInfo) {
		// TODO Auto-generated method stub
		int insert = grm.insert(apiRoleInfo);
		return insert;
	}

	// 获取汇率
	@Override
	public String getExchangeRate() {
		// TODO Auto-generated method stub
		GameApiExchangeRate selectByPrimaryKey = gem.selectByPrimaryKey(1);
		return selectByPrimaryKey.getBidtExchangeRate();
	}

	// 下单
	@Override
	public GameApiDemandOrder playAnBidtOrder(String billNo, String goodsName, double bidtNum, Integer userId,
			String totalFee, Integer gameId) {
		// TODO Auto-generated method stub
		GameApiDemandOrder order = new GameApiDemandOrder();
		String timestampStr = System.currentTimeMillis() + "";
		String billno = UUID.randomUUID().toString();
		String formatedDateStr = DateHelper.getFormatedDateStr(new Date(), "yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = null;

		double mul = PushRefinedCalculation.mul(10, bidtNum);
		try {
			String encode1 = URLEncoder.encode(AES.AESEncode("LYWH@#$!32", userId + ""), "UTF-8");
			String encode3 = URLEncoder.encode(AES.AESEncode("9$dz4Y33oy", mul + ""), "UTF-8");
			String encode4 = URLEncoder.encode(AES.AESEncode("9$dz4Y33oy", "yobXT2ENRb" + timestampStr), "UTF-8");
			s = UrlLoad.load(IpResourceLocation.TT_EGGWORLD_IP + "/ttc-eggworld/ttc/record",
					"openId=" + encode1 + "&muchMoney=" + encode3 + "&transactionType=41"
							+ "&app_id=3360a88e4318896c9fe3e031e64541f9c176af67fa87634f&mch_id=yobXT2ENRb&timestampStr="
							+ timestampStr + "&sign=" + encode4);

		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = JSON.parseObject(s);
		String string = jsonObject.getString("state");
		JSONObject jsonObject1 = JSON.parseObject(string);
		String code = jsonObject1.getString("code");
		if ("20000".equals(code)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String format1 = format.format(new Date());
			// 调用蛋生

			order.setCreateTime(format1);
			order.setGameOrderNum(billNo);
			order.setGameId(gameId);
			order.setProductName(goodsName);
			// app
			order.setOrderSource(0);
			// 币达支付
			order.setPayType(5);
			order.setPayTotalMoney(bidtNum + "");
			order.setProductPrice(totalFee);
			order.setSendMsgToGamecompany(0);
			order.setPayOrderNum(UUID.randomUUID().toString());
			order.setStatus(1);
			order.setShoppingNum(1);
			order.setIntegral(0);
			order.setUserId(userId);
			int ordernum= DemandNumFactory.getInstance().getDemandNum();
			order.setDemandNum(ordernum);
			gom.insertOverride(order);

		} else if ("20002".equals(code)) {

			String msg = jsonObject1.getString("msg");
			if (msg.equals("success")) {
				throw ServiceException.userException("", "未完成kyc二级认证");
			} else {
				throw ServiceException.userException("", msg);
			}
		}

		return order;
	}

	@Override
	public int updateorder(GameApiDemandOrder order) {
		// TODO Auto-generated method stub
		int updateByPrimaryKey = gom.updateByPrimaryKey(order);
		return updateByPrimaryKey;
	}

	// 查历史记录
	@Override
	public List<OrderVo> getOrderListBytoken(Integer userId, Integer page, Integer rows) {
		// TODO Auto-generated method stub
		page = (page - 1) * rows;
		List<GameApiDemandOrder> list = gom.getOrderListBytoken(userId, page, rows);
		List<OrderVo> orders = new ArrayList<OrderVo>();
		for (int i = 0; i < list.size(); i++) {
			OrderVo ov = new OrderVo();
			ov.setCreatetime(list.get(i).getCreateTime());
			ov.setGameName(gim.selectByPrimaryKey(list.get(i).getGameId()).getGameName());
			ov.setId(list.get(i).getId());
			ov.setNum(list.get(i).getPayTotalMoney());
			ov.setPayType(list.get(i).getPayType());
			ov.setSysOrderNum(list.get(i).getPayOrderNum());
			orders.add(ov);
		}

		return orders;
	}
//获取订单号
	public static List<Integer> getOrderId() throws Exception {
		// jdbc:mysql://118.89.219.59:3306/timetreaty_certification
		final String url = IpResourceLocation.CER_DATA_SOURCE;
		final String name = "com.mysql.jdbc.Driver";
		final String user = IpResourceLocation.CER_DATA_USER;
		final String password = IpResourceLocation.CER_DATA_PWD;
		Long begin = new Date().getTime();
		Connection conn = null;
		PreparedStatement statement = null;
		Class.forName(name);// 指定连接类型
		// try {

		conn = DriverManager.getConnection(url, user, password);
		conn.setAutoCommit(false);
		statement = (PreparedStatement) conn.prepareStatement("");
		int key = 0;
		String sql = "select goo.demand_num from game_api_demand_order goo where goo.demand_num is not null order by goo.demand_num desc";

		String demand_num = null;

		List<Integer> list = new ArrayList<Integer>();
		ResultSet rsuser = statement.executeQuery(sql);
		while (rsuser.next()) {
			// 可以根据列名称也可以根据列索引
			// int id = rsuser.getInt(1);

			demand_num = rsuser.getString("demand_num");
			list.add(Integer.parseInt(demand_num));
		}

		conn.commit();
		try {
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}
