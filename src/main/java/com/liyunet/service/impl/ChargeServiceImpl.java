package com.liyunet.service.impl;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liyunet.common.constant.DateHelper;
import com.liyunet.common.password.AES;
import com.liyunet.common.util.DateUtile;
import com.liyunet.common.util.IpResourceLocation;
import com.liyunet.common.util.UrlLoad;
import com.liyunet.domain.FBTPrice;
import com.liyunet.domain.charge.ChargeAddressInfo;
import com.liyunet.domain.charge.ChargeAddressInfoExample;
import com.liyunet.domain.charge.ChargeCoinInfo;
import com.liyunet.domain.charge.ChargeCoinInfoExample;
import com.liyunet.domain.charge.ChargeCoinInfoVo;
import com.liyunet.domain.charge.ChargeCoinInfoExample.Criteria;
import com.liyunet.exception.ServiceException;
import com.liyunet.domain.charge.ChargeOrderInfo;
import com.liyunet.domain.charge.ChargeOrderInfoExample;
import com.liyunet.domain.charge.ChargeOrderVo;
import com.liyunet.domain.charge.ChargeUserBalance;
import com.liyunet.domain.charge.ChargeUserBalanceExample;
import com.liyunet.mapper.chargeMapper.ChargeAddressInfoMapper;
import com.liyunet.mapper.chargeMapper.ChargeCoinInfoMapper;
import com.liyunet.mapper.chargeMapper.ChargeOrderInfoMapper;
import com.liyunet.mapper.chargeMapper.ChargeUserBalanceMapper;
import com.liyunet.mapper.community.PushCommonMapper;
import com.liyunet.service.ChargeService;
import com.liyunet.util.PushRefinedCalculation;
import com.liyunet.vo.hk.BillVo;

@Service("chargeService")
@Transactional
public class ChargeServiceImpl implements ChargeService {
	@Autowired
	private ChargeCoinInfoMapper ccm;

	@Autowired
	private ChargeAddressInfoMapper cam;

	@Autowired
	private ChargeOrderInfoMapper com;

	@Autowired
	private ChargeUserBalanceMapper cum;

	@Autowired
	private  PushCommonMapper pushCommonMapper;
	// 获取币种列表
	@Override
	public Object getCoinList(Integer type) {
		// TODO Auto-generated method stub
		ChargeCoinInfoExample example = new ChargeCoinInfoExample();
		example.setOrderByClause("sort desc");
		Criteria createCriteria = example.createCriteria();
		createCriteria.andStateEqualTo(1);
		List<ChargeCoinInfo> selectByExample = ccm.selectByExample(example);
		List<ChargeCoinInfoVo> list = new ArrayList<ChargeCoinInfoVo>();
		for (int i = 0; i < selectByExample.size(); i++) {
			ChargeCoinInfoVo cv = new ChargeCoinInfoVo();
			ChargeCoinInfo chargeCoinInfo = selectByExample.get(i);
			String homeImage = null;
			String coinImage = null;
			if (type.intValue() == 1) {
				coinImage = selectByExample.get(i).getCoinImage().split(",")[0];
			} else if (type.intValue() == 2) {
				coinImage = selectByExample.get(i).getCoinImage().split(",")[1];
			}
			cv.setId(chargeCoinInfo.getId());
			cv.setCoinImage(coinImage);
			cv.setCoinInfo(chargeCoinInfo.getCoinInfo());
			list.add(cv);
		}
		// 判断安卓还是苹果 1是安卓 2是苹果

		return list;
	}

	// 判断用户是否绑定地址
	@Override
	public Object checkChargeState(Integer userId, Integer cid) {
		// TODO Auto-generated method stub
		ChargeAddressInfoExample example = new ChargeAddressInfoExample();
		com.liyunet.domain.charge.ChargeAddressInfoExample.Criteria createCriteria = example.createCriteria();
		createCriteria.andCidEqualTo(cid);
		createCriteria.andUseridEqualTo(userId);
		List<ChargeAddressInfo> selectByExample = cam.selectByExample(example);
		// 0是未绑定 1是已绑定
		int state = 0;
		if (selectByExample.size() > 0 && selectByExample != null) {
			state = 1;
		}

		return state;
	}

	// 绑定地址
	@Override
	public Object bindingAddress(Integer userId, Integer cid, String address) {
		// TODO Auto-generated method stub
		// 查绑定
		ChargeAddressInfoExample example = new ChargeAddressInfoExample();
		com.liyunet.domain.charge.ChargeAddressInfoExample.Criteria createCriteria = example.createCriteria();
		createCriteria.andCidEqualTo(cid);
		createCriteria.andUseridEqualTo(userId);
		List<ChargeAddressInfo> selectByExample = cam.selectByExample(example);
		if (selectByExample != null && selectByExample.size() > 0) {
			throw ServiceException.userException("已绑定", "已绑定");
		}
		String formatedDateStr = DateHelper.getFormatedDateStr(new Date(), "yyyy-MM-dd HH:mm:ss");
		ChargeAddressInfo addressInfo = new ChargeAddressInfo();
		addressInfo.setAddress(address);
		addressInfo.setCid(cid);
		addressInfo.setUserid(userId);
		addressInfo.setCreatetime(formatedDateStr);
		int insertSelective = cam.insertSelective(addressInfo);
		return insertSelective;
	}

	// 修改绑定地址
	@Override
	public Object updateAddress(Integer id, String address) {
		// TODO Auto-generated method stub
		String formatedDateStr = DateHelper.getFormatedDateStr(new Date(), "yyyy-MM-dd HH:mm:ss");
		ChargeAddressInfo selectByPrimaryKey = cam.selectByPrimaryKey(id);
		selectByPrimaryKey.setAddress(address);
		selectByPrimaryKey.setUpdatetime(formatedDateStr);
		int updateByPrimaryKey = cam.updateByPrimaryKey(selectByPrimaryKey);
		return updateByPrimaryKey;
	}

	// 币种详情
	@Override
	public Object getCoinInfo(Integer userId, Integer cid, Integer type) {
		// TODO Auto-generated method stub
		// 查充值地址
		ChargeCoinInfo selectByPrimaryKey = ccm.selectByPrimaryKey(cid);
		String chargeAddress = selectByPrimaryKey.getChargeAddress();
		// 查二维码
		String qrCode = selectByPrimaryKey.getQrCode();
		// 查币种信息
		String coinInfo = selectByPrimaryKey.getCoinInfo();
		// 查图标
		String homeImage = null;
		if (type.intValue() == 1) {
			homeImage = selectByPrimaryKey.getHomeImage().split(",")[0];
		} else if (type.intValue() == 2) {
			homeImage = selectByPrimaryKey.getHomeImage().split(",")[1];
		}

		// 查绑定的地址
		ChargeAddressInfoExample example = new ChargeAddressInfoExample();
		com.liyunet.domain.charge.ChargeAddressInfoExample.Criteria createCriteria = example.createCriteria();
		createCriteria.andCidEqualTo(cid);
		createCriteria.andUseridEqualTo(userId);
		List<ChargeAddressInfo> selectByExample = cam.selectByExample(example);
		String address = selectByExample.get(0).getAddress();
		// 查修改时间
		String updatetime = selectByExample.get(0).getUpdatetime();
		Integer addressId = selectByExample.get(0).getId();
		Map map = new HashMap<String, Object>();
		map.put("chargeAddress", chargeAddress);
		map.put("qrCode", qrCode);
		map.put("address", address);
		map.put("updatetime", updatetime);
		map.put("addressId", addressId);
		map.put("homeImage", homeImage);
		map.put("coinInfo", coinInfo);

		return map;
	}

	// 充值
	@Override
	public Object charge(Integer userId, Integer cid, String bidtnum) {
		// TODO Auto-generated method stub
		double parseDouble = Double.parseDouble(bidtnum);
		if (parseDouble <= 0) {
			throw ServiceException.userException("", "充值数额不能小于等于0");
		}
		// 判断地址修改时间是否大于24小时
		String formatedDateStr = DateHelper.getFormatedDateStr(new Date(), "yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ChargeAddressInfoExample example = new ChargeAddressInfoExample();
		com.liyunet.domain.charge.ChargeAddressInfoExample.Criteria createCriteria = example.createCriteria();
		createCriteria.andCidEqualTo(cid);
		createCriteria.andUseridEqualTo(userId);
		List<ChargeAddressInfo> selectByExample = cam.selectByExample(example);
		if (selectByExample == null || selectByExample.size() <= 0) {
			throw ServiceException.userException("", "请绑定充币地址");
		}
		String updatetime = selectByExample.get(0).getUpdatetime();
		if (updatetime != null && updatetime != "" && !updatetime.equals("null")) {

			String newTime = DateHelper.getFormatedDateStr(new Date(), "yyyy-MM-dd HH:mm:ss");

			Date updT = null;
			Date newT = null;

			try {
				newT = sdf.parse(newTime);
				updT = DateUtile.addDate(sdf.parse(updatetime), 1);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			long ntime = newT.getTime();
			long uime = updT.getTime();

			if (ntime < uime) {
				throw ServiceException.userException("", "修改地址24小时后方可充币");
			}
		}
		ChargeOrderInfo chargeOrderInfo = new ChargeOrderInfo();
		chargeOrderInfo.setCid(cid);
		chargeOrderInfo.setBidtNum(bidtnum);
		chargeOrderInfo.setCreatetime(formatedDateStr);
		chargeOrderInfo.setOrderId(UUID.randomUUID().toString());
		chargeOrderInfo.setStatus(0);
		// 1充值
		chargeOrderInfo.setOrderType(1);
		chargeOrderInfo.setPlusMinus(1);
		if (cid.intValue() == 1) {
			chargeOrderInfo.setIsBidt(1);
		} else {
			chargeOrderInfo.setIsBidt(0);
		}
		chargeOrderInfo.setUserid(userId);
		int insert = com.insertSelectKey(chargeOrderInfo);
		return chargeOrderInfo.getId();
	}

	// 充值详情
	@Override
	public Object chargeInfo(Integer id) {
		// TODO Auto-generated method stub
		// 查充值地址数量和时间
		ChargeOrderInfo selectByPrimaryKey = com.selectByPrimaryKey(id);
		Integer cid = selectByPrimaryKey.getCid();
		Integer userid = selectByPrimaryKey.getUserid();
		String createtime = selectByPrimaryKey.getCreatetime();
		String bidtNum = selectByPrimaryKey.getBidtNum();

		ChargeAddressInfoExample example = new ChargeAddressInfoExample();
		com.liyunet.domain.charge.ChargeAddressInfoExample.Criteria createCriteria = example.createCriteria();
		createCriteria.andCidEqualTo(cid);
		createCriteria.andUseridEqualTo(userid);
		List<ChargeAddressInfo> selectByPrimaryKeylist = cam.selectByExample(example);

		String address = selectByPrimaryKeylist.get(0).getAddress();
		ChargeCoinInfo selectByPrimaryKey2 = ccm.selectByPrimaryKey(cid);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("createtime", createtime);
		map.put("bidtnum", bidtNum);
		map.put("address", address);
		map.put("homeimape", selectByPrimaryKey2.getHomeImage().split(",")[0]);
		map.put("coininfo", selectByPrimaryKey2.getCoinInfo());
		map.put("status", selectByPrimaryKey.getStatus());
		map.put("resson", selectByPrimaryKey.getReason());
		return map;
	}

	// 历史记录
	@Override
	public Object getChargeHistory(Integer userId) {
		// TODO Auto-generated method stub
		// 查订单
		ChargeOrderInfoExample example = new ChargeOrderInfoExample();
		example.setOrderByClause("createtime desc");
		com.liyunet.domain.charge.ChargeOrderInfoExample.Criteria createCriteria = example.createCriteria();
		createCriteria.andUseridEqualTo(userId);
		List<ChargeOrderInfo> selectByExample = com.selectByExample(example);
		List<ChargeOrderVo> list = new ArrayList<ChargeOrderVo>();
		for (int i = 0; i < selectByExample.size(); i++) {
			ChargeOrderInfo chargeOrderInfo = selectByExample.get(i);
			ChargeOrderVo chargeOrderVo = new ChargeOrderVo();
			chargeOrderVo.setCreatetime(chargeOrderInfo.getCreatetime());
			chargeOrderVo.setBidtnum(chargeOrderInfo.getBidtNum());
			chargeOrderVo.setRession(chargeOrderInfo.getReason());
			chargeOrderVo.setState(chargeOrderInfo.getStatus());
			chargeOrderVo.setId(chargeOrderInfo.getId());
			// ChargeAddressInfo selectByPrimaryKey =
			// cam.selectByPrimaryKey(chargeOrderInfo.getAddressid());
			ChargeCoinInfo selectByPrimaryKey2 = ccm.selectByPrimaryKey(chargeOrderInfo.getCid());
			chargeOrderVo.setCoinType(selectByPrimaryKey2.getCoinInfo());
			chargeOrderVo.setHomeimage(selectByPrimaryKey2.getHomeImage().split(",")[0]);
			list.add(chargeOrderVo);

		}

		return list;
	}

	@Override
	public Object addCharge(Integer id, Integer updateState, String ression) {
		// TODO Auto-generated method stub
		String timestampStr = System.currentTimeMillis() + "";
		ChargeOrderInfo selectByPrimaryKey = com.selectByPrimaryKey(id);
		// 判断是否是币达
		if (selectByPrimaryKey.getCid().intValue() != 1) {
			// 不是币达
			if (updateState.intValue() == 1) {
				// 加余额
				ChargeUserBalanceExample chargeUserBalanceExample = new ChargeUserBalanceExample();
				com.liyunet.domain.charge.ChargeUserBalanceExample.Criteria createCriteria = chargeUserBalanceExample
						.createCriteria();
				createCriteria.andUseridEqualTo(selectByPrimaryKey.getUserid());
				createCriteria.andCidEqualTo(selectByPrimaryKey.getCid());
				List<ChargeUserBalance> selectByExample = cum.selectByExample(chargeUserBalanceExample);
				if (selectByExample != null && selectByExample.size() > 0) {
					Double balance = Double.parseDouble(selectByExample.get(0).getBalance());
					Double bidtNum = Double.parseDouble(selectByPrimaryKey.getBidtNum());
					double add = PushRefinedCalculation.add(balance, bidtNum);
					selectByExample.get(0).setBalance(add + "");
					cum.updateByPrimaryKey(selectByExample.get(0));
				} else {
					Double bidtNum = Double.parseDouble(selectByPrimaryKey.getBidtNum());
					Integer userid = selectByPrimaryKey.getUserid();
					Integer cid = selectByPrimaryKey.getCid();
					ChargeUserBalance cub = new ChargeUserBalance();
					cub.setBalance(bidtNum + "");
					cub.setUserid(userid);
					cub.setCid(cid);
					cum.insert(cub);

				}

				selectByPrimaryKey.setStatus(updateState);
				com.updateByPrimaryKey(selectByPrimaryKey);
			} else {
				selectByPrimaryKey.setStatus(updateState);
				selectByPrimaryKey.setReason(ression);
				com.updateByPrimaryKey(selectByPrimaryKey);
			}
		} else {

			// 判断状态
			// 如果是1；通过

			if (updateState.intValue() == 1) {

				String bidt = selectByPrimaryKey.getBidtNum();
				String userId = selectByPrimaryKey.getUserid() + "";
				double b = Double.parseDouble(bidt);
				// 调用绑码系统
				String s = null;

				double mul = PushRefinedCalculation.mul(10, b);
				try {
					String encode1 = URLEncoder.encode(AES.AESEncode("LYWH@#$!32", userId), "UTF-8");
					String encode3 = URLEncoder.encode(AES.AESEncode("9$dz4Y33oy", mul + ""), "UTF-8");
					String encode4 = URLEncoder.encode(AES.AESEncode("9$dz4Y33oy", "yobXT2ENRb" + timestampStr),
							"UTF-8");
					s = UrlLoad.load(IpResourceLocation.TT_EGGWORLD_IP + "/ttc-eggworld/ttc/record",
							"openId=" + encode1 + "&muchMoney=" + encode3 + "&transactionType=39"
									+ "&app_id=3360a88e4318896c9fe3e031e64541f9c176af67fa87634f&mch_id=yobXT2ENRb&timestampStr="
									+ timestampStr + "&sign=" + encode4);

					JSONObject jsonObject = JSON.parseObject(s);
					String string = jsonObject.getString("state");
					JSONObject jsonObject1 = JSON.parseObject(string);
					String code = jsonObject1.getString("code");
					if ("20000".equals(code)) {

						selectByPrimaryKey.setStatus(updateState);
						com.updateByPrimaryKey(selectByPrimaryKey);

					} else if ("20002".equals(code)) {

						String msg = jsonObject1.getString("msg");
						if (msg.equals("success")) {
							throw ServiceException.userException("", "未完成kyc二级认证");
						} else {
							throw ServiceException.userException("", msg);
						}
					}

				} catch (Exception e) {
					throw ServiceException.userException("", e.toString());
				}

				// 修改状态

			} else {

				selectByPrimaryKey.setStatus(updateState);
				selectByPrimaryKey.setReason(ression);
				com.updateByPrimaryKey(selectByPrimaryKey);

			}
		}
		return null;
	}

	// 获取账单
	@Override
	public Object getBill(Integer userId) {
		// TODO Auto-generated method stub
		BillVo bill = null;
		try {
			bill = getBillbyUserid(userId);
			if (!bill.getEggLoginTime().equals("0")) {
				bill.setEggLoginTime(getDayBytime(bill.getEggLoginTime()));
			}
			if (!bill.getCkTime().equals("0")) {
				String nd = pareTime(bill.getCkTime());
				bill.setCkTime(nd);
			}
			//富比特价格
			List<FBTPrice> fbtPriceList = pushCommonMapper.getFBTPrice();
			String pNes = fbtPriceList.get(0).getPrice();
			bill.setFubtPrice(pNes);
			String createtime = pareTime(bill.getCreatetime());
			bill.setCreatetime(createtime);
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bill;
	}

	// 获取账单
	public static BillVo getBillbyUserid(Integer uId) throws Exception {
		final String url = "jdbc:mysql://123.207.158.196:3306/timetreaty_certification";
		final String name = "com.mysql.jdbc.Driver";
		final String user = "gonggonguser";
		final String password = "gonggong_!@#123_";
		Long begin = new Date().getTime();
		Connection conn = null;
		PreparedStatement statement = null;
		Class.forName(name);// 指定连接类型
		// try {
		conn = DriverManager.getConnection(url, user, password);
		conn.setAutoCommit(false);
		statement = (PreparedStatement) conn.prepareStatement("");
		int key = 0;
		String sql = "select * from data_total where userId=" + uId;
		ResultSet rsuser = statement.executeQuery(sql);
		BillVo bv = new BillVo();
		while (rsuser.next()) {
			// 可以根据列名称也可以根据列索引
			// int id = rsuser.getInt(1);

			bv.setUserId(Integer.parseInt(rsuser.getString("userId")));
			bv.setBidtBalance(rsuser.getString("bidt_balance"));
			bv.setBidtRank(rsuser.getString("bidt_rank"));
			bv.setCkBalance(rsuser.getString("ck_balance"));
			bv.setCkTime(rsuser.getString("ck_time"));
			bv.setCreatetime(rsuser.getString("createtime"));
			bv.setEggBalance(rsuser.getString("egg_balance"));
			bv.setEggLevel(rsuser.getString("egg_level"));
			bv.setEggLoginTime(rsuser.getString("egg_login_time"));
			bv.setOtcBalance(rsuser.getString("otc_balance"));
			bv.setProduct(rsuser.getString("product"));
			bv.setTimeRank(rsuser.getString("time_rank"));
			bv.setEggRank(rsuser.getString("egg_rank"));
			bv.setCkProfit(rsuser.getString("ck_profit"));
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

		return bv;
	}

	// 获取天数

	public static String getDayBytime(String time) {
		String formatedDateStr = DateHelper.getFormatedDateStr(new Date(), "yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String days = "";
		try {
			Date newDate = sdf.parse(formatedDateStr);
			Date oldDate = sdf.parse(time);
			long newtime = newDate.getTime();
			long oldtime = oldDate.getTime();
			int i = (int) ((newtime - oldtime) / (24 * 3600 * 1000));
			days = i + "";

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return days;

	}

	public static String pareTime(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date parse = null;
		try {
			parse = sdf.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String formatedDateStr = DateHelper.getFormatedDateStr(parse, "yyyy-MM-dd");
		String[] split = formatedDateStr.split("-");
		String nd = split[0] + "年" + split[1] + "月" + split[2] + "日";
		return nd;
	}

}
