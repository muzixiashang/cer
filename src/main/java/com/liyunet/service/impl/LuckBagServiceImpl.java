package com.liyunet.service.impl;

import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liyunet.common.constant.Constant;
import com.liyunet.common.constant.DateHelper;
import com.liyunet.common.luckbag.LuckBagUtile;
import com.liyunet.common.luckbag.LuckBagVo;
import com.liyunet.common.luckbag.SendVo;
import com.liyunet.common.password.AES;
import com.liyunet.common.password.MD5Util;
import com.liyunet.common.pushToken.PushAuthHelper;
import com.liyunet.common.util.DateUtile;
import com.liyunet.common.util.IpResourceLocation;
import com.liyunet.common.util.UrlLoad;
import com.liyunet.domain.UserInfo;
import com.liyunet.domain.hk.CoinHkOrder;
import com.liyunet.domain.hk.CoinHkUserProject;
import com.liyunet.domain.luckbag.LuckbagBlessingInfo;
import com.liyunet.domain.luckbag.LuckbagBlessingInfoExample;
import com.liyunet.domain.luckbag.LuckbagBlessingInfoExample.Criteria;
import com.liyunet.exception.ServiceException;
import com.liyunet.domain.luckbag.LuckbagInfo;
import com.liyunet.domain.luckbag.LuckbagInfoExample;
import com.liyunet.domain.luckbag.LuckbagUserinfo;
import com.liyunet.domain.luckbag.LuckbagUserinfoExample;
import com.liyunet.mapper.luckbagMapper.LuckbagBlessingInfoMapper;
import com.liyunet.mapper.luckbagMapper.LuckbagInfoMapper;
import com.liyunet.mapper.luckbagMapper.LuckbagUserinfoMapper;
import com.liyunet.mapper.userMapper.CertificationMapper;
import com.liyunet.service.LuckBagService;
import com.liyunet.util.PushRefinedCalculation;

@Service("luckbagService")
@Transactional
public class LuckBagServiceImpl implements LuckBagService {
	@Autowired
	private LuckbagInfoMapper im;

	@Autowired
	private LuckbagBlessingInfoMapper bm;

	@Autowired
	private CertificationMapper cm;

	@Autowired
	private LuckbagUserinfoMapper um;

	// 发红包
	@Override
	public Object handLuckBag(Integer userId, String bidtnum, String bagnum, Integer lbid, String money, Integer type) {
		// TODO Auto-generated method stub
		
		// 余额
		if (type == 2) {
			bidtnum = PushRefinedCalculation.mul(Double.parseDouble(money), Double.parseDouble(bagnum)) + "";
		}
		if (Double.parseDouble(bidtnum) <= 0) {
			throw ServiceException.userException("发红包数量不能小于0", "发红包数量不能小于0");
			
		}
		LuckbagInfo info = new LuckbagInfo();
		// 绑码系统
		// 返回20000
		// 调用币达钱包
		String timestampStr = System.currentTimeMillis() + "";
		String billno = UUID.randomUUID().toString();
		String formatedDateStr = DateHelper.getFormatedDateStr(new Date(), "yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = null;

		double mul = PushRefinedCalculation.mul(10, Double.parseDouble(bidtnum));
		try {
			String encode1 = URLEncoder.encode(AES.AESEncode("LYWH@#$!32", userId + ""), "UTF-8");
			String encode3 = URLEncoder.encode(AES.AESEncode("9$dz4Y33oy", mul + ""), "UTF-8");
			String encode4 = URLEncoder.encode(AES.AESEncode("9$dz4Y33oy", "yobXT2ENRb" + timestampStr), "UTF-8");
			s = UrlLoad.load(IpResourceLocation.TT_EGGWORLD_IP + "/ttc-eggworld/ttc/record",
					"openId=" + encode1 + "&muchMoney=" + encode3 + "&transactionType=34"
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

			info.setOrderid(MD5Util.getMD5(UUID.randomUUID().toString()));
			// 红包数量
			info.setBagnum(Integer.parseInt(bagnum));

			// 创建时间
			info.setCreatetime(formatedDateStr);

			// 总金额
			info.setBidtnum(bidtnum);
			info.setBalance(bidtnum);
			// 1：随机 2固定
			info.setType(type);
			// 祝福
			info.setLbid(lbid);
			// 每个红包多少
			info.setMomey(money);
			// 可领取
			info.setState(1);
			info.setUserid(userId);
			im.insert(info);

		} else if ("20002".equals(code)) {

			String msg = jsonObject1.getString("msg");
			if (msg.equals("success")) {
				throw ServiceException.userException("", "未完成kyc二级认证");
			} else {
				throw ServiceException.userException("", msg);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", info.getOrderid());
		map.put("id", info.getId());
		return map;
	}

	// 查找祝福语

	@Override
	public Object getLuckbagBlessList() {
		// TODO Auto-generated method stub
		LuckbagBlessingInfoExample example = new LuckbagBlessingInfoExample();
		example.setOrderByClause("sort DESC");
		Criteria createCriteria = example.createCriteria();
		createCriteria.andStateEqualTo(1);
		List<LuckbagBlessingInfo> selectByExample = bm.selectByExample(example);
		return selectByExample;
	}

	// 根据订单号查找用户信息
	@Override
	public Object getBlockIdByorderId(String orderId) {
		// TODO Auto-generated method stub
		String formatedDateStr = DateHelper.getFormatedDateStr(new Date(), "yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LuckbagInfoExample example = new LuckbagInfoExample();
		com.liyunet.domain.luckbag.LuckbagInfoExample.Criteria createCriteria = example.createCriteria();
		createCriteria.andOrderidEqualTo(orderId);
		List<LuckbagInfo> selectByExample = im.selectByExample(example);
		// 判断是否过期
		String creTime = selectByExample.get(0).getCreatetime();
		String newTime = DateHelper.getFormatedDateStr(new Date(), "yyyy-MM-dd HH:mm:ss");
		Date creT = null;
		Date newT = null;
		try {
			newT = sdf.parse(newTime);
			creT = DateUtile.addDate(sdf.parse(creTime), 1);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		long lnewT = newT.getTime();
		long lcreT = creT.getTime();

		// 根据用户id查区块id
		List<UserInfo> userInfoByid = cm.getUserInfoByid(selectByExample.get(0).getUserid() + "");

		// 查询祝福
		LuckbagBlessingInfo selectByPrimaryKey = bm.selectByPrimaryKey(selectByExample.get(0).getLbid());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("blockId", userInfoByid.get(0).getUserAccount());
		map.put("blesss", selectByPrimaryKey.getBlessing());
		map.put("id", selectByExample.get(0).getId());
		// 过期

		// 可领
		if (selectByExample.get(0).getState() == 1) {
			if (lnewT >= lcreT) {
				map.put("state", 1);
			} else {
				map.put("state", 0);
			}
		} else if (selectByExample.get(0).getState() == 0) {
			map.put("state", 2);
		} else {
			map.put("invalidStatus", 1);
		}

		return map;
	}

	// 抢红包
	@Override
	public Object getLuckBag(Integer userId, Integer id) {
		// TODO Auto-generated method stub
		String formatedDateStr = DateHelper.getFormatedDateStr(new Date(), "yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 根据id查出红包信息
		LuckbagInfo selectByPrimaryKey = im.selectByPrimaryKey(id);
		// 判断是否过期
		String creTime = selectByPrimaryKey.getCreatetime();
		String newTime = DateHelper.getFormatedDateStr(new Date(), "yyyy-MM-dd HH:mm:ss");
		Date creT = null;
		Date newT = null;
		try {
			newT = sdf.parse(newTime);
			creT = DateUtile.addDate(sdf.parse(creTime), 1);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		long lnewT = newT.getTime();
		long lcreT = creT.getTime();
		if (lnewT >= lcreT) {
			throw ServiceException.userException("", "红包已过期");
		}

		Integer type = selectByPrimaryKey.getType();
		// 查自己有没有抢过
		LuckbagUserinfoExample example1 = new LuckbagUserinfoExample();
		com.liyunet.domain.luckbag.LuckbagUserinfoExample.Criteria createCriteria1 = example1.createCriteria();
		createCriteria1.andToUseridEqualTo(userId);
		createCriteria1.andLidEqualTo(id);
		List<LuckbagUserinfo> selectByExample = um.selectByExample(example1);
		if (selectByExample != null && selectByExample.size() > 0) {
			throw ServiceException.userException("", "您已抢过该红包");

		}
		// 查出已经抢过的人数
		LuckbagUserinfoExample example = new LuckbagUserinfoExample();
		com.liyunet.domain.luckbag.LuckbagUserinfoExample.Criteria createCriteria = example.createCriteria();
		createCriteria.andLidEqualTo(id);
		int countByExample = um.countByExample(example);
		if (countByExample >= selectByPrimaryKey.getBagnum()) {

			throw ServiceException.userException("", "红包已被抢完");
		}

		LuckbagUserinfo lu = new LuckbagUserinfo();
		// 领取数量
		double randomMoney = 0.0;
		// 随机红包
		if (type == 1) {
			// 红包剩余金额
			double remainMoney = Double.parseDouble(selectByPrimaryKey.getBalance());
			// 红包剩余数量
			int remainSize = selectByPrimaryKey.getBagnum() - countByExample;
			randomMoney = LuckBagUtile.getRandomMoney(remainMoney, remainSize);
			lu.setNum(randomMoney + "");

		} else {
			randomMoney = Double.parseDouble(selectByPrimaryKey.getMomey());
			lu.setNum(randomMoney + "");

		}
		// 每次余额-randomMoney
		selectByPrimaryKey.setBalance(
				PushRefinedCalculation.sub(Double.parseDouble(selectByPrimaryKey.getBalance()), randomMoney) + "");
		if (selectByPrimaryKey.getBagnum() - countByExample == 1) {
			selectByPrimaryKey.setState(0);
		}
		lu.setFromUserid(selectByPrimaryKey.getUserid());
		lu.setLid(id);
		lu.setCreatetime(formatedDateStr);
		lu.setToUserid(userId);

		// 绑码返回20000
		String timestampStr = System.currentTimeMillis() + "";
		String billno = UUID.randomUUID().toString();
		String s = null;

		double mul = PushRefinedCalculation.mul(10, randomMoney);
		try {
			String encode1 = URLEncoder.encode(AES.AESEncode("LYWH@#$!32", userId + ""), "UTF-8");
			String encode3 = URLEncoder.encode(AES.AESEncode("9$dz4Y33oy", mul + ""), "UTF-8");
			String encode4 = URLEncoder.encode(AES.AESEncode("9$dz4Y33oy", "yobXT2ENRb" + timestampStr), "UTF-8");
			s = UrlLoad.load(IpResourceLocation.TT_EGGWORLD_IP + "/ttc-eggworld/ttc/record",
					"openId=" + encode1 + "&muchMoney=" + encode3 + "&transactionType=35"
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

			im.updateByPrimaryKey(selectByPrimaryKey);
			um.insert(lu);
		} else if ("20002".equals(code)) {

			String msg = jsonObject1.getString("msg");
			if (msg.equals("success")) {
				throw ServiceException.userException("", "未完成kyc二级认证");
			} else {
				throw ServiceException.userException("", msg);
			}
		}

		return null;
	}

	// 查红包详情
	@Override
	public Object getLuckBagInfo(Integer userId, Integer id) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formatedDateStr = DateHelper.getFormatedDateStr(new Date(), "yyyy-MM-dd HH:mm:ss");
		// 根据id查出红包信息
		LuckbagInfo selectByPrimaryKey = im.selectByPrimaryKey(id);
		// 查区块
		List<UserInfo> userInfoByid = cm.getUserInfoByid(selectByPrimaryKey.getUserid() + "");
		// 判断是否过期
		String creTime = selectByPrimaryKey.getCreatetime();
		String newTime = DateHelper.getFormatedDateStr(new Date(), "yyyy-MM-dd HH:mm:ss");
		Date creT = null;
		Date newT = null;
		try {
			newT = sdf.parse(newTime);
			creT = DateUtile.addDate(sdf.parse(creTime), 1);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		long lnewT = newT.getTime();
		long lcreT = creT.getTime();
		// 查询祝福
		LuckbagBlessingInfo luckbagBlessingInfo = bm.selectByPrimaryKey(selectByPrimaryKey.getLbid());
		List<LuckBagVo> lvs = new ArrayList<LuckBagVo>();

		// 查抢红包的人
		LuckbagUserinfoExample example1 = new LuckbagUserinfoExample();
		example1.setOrderByClause("createtime DESC");
		com.liyunet.domain.luckbag.LuckbagUserinfoExample.Criteria createCriteria1 = example1.createCriteria();
		createCriteria1.andLidEqualTo(id);
		List<LuckbagUserinfo> selectByExample = um.selectByExample(example1);
		for (int i = 0; i < selectByExample.size(); i++) {
			LuckBagVo lv = new LuckBagVo();
			List<UserInfo> blockinfo = cm.getUserInfoByid(selectByExample.get(i).getToUserid() + "");
			lv.setBlockid(blockinfo.get(0).getUserAccount());
			if (blockinfo.get(0).getId().intValue() == userId.intValue()) {
				lv.setState(1);
			} else {
				lv.setState(0);
			}
			lv.setNum(selectByExample.get(i).getNum());
			lv.setCreatetime(selectByExample.get(i).getCreatetime());
			lvs.add(lv);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("blockId", userInfoByid.get(0).getUserAccount());
		map.put("blesss", luckbagBlessingInfo.getBlessing());
		map.put("bagInfo", selectByPrimaryKey);
		// 过期

		// 可领
		if (selectByPrimaryKey.getState() == 1) {
			if (lnewT >= lcreT) {
				map.put("invalidStatus", 1);
			} else {
				map.put("invalidStatus", 0);
			}
		} else if (selectByPrimaryKey.getState() == 0) {
			// 领完了
			map.put("invalidStatus", 2);
		} else {
			map.put("invalidStatus", 1);
		}

		map.put("userList", lvs);

		if (userId == selectByPrimaryKey.getUserid()) {
			map.put("isuser", 1);
		} else {
			map.put("isuser", 0);
		}

		return map;
	}

	// 获取token
	@Override
	public Object getTokenaByuseraccount(HttpServletRequest request, String userAccount) {
		// TODO Auto-generated method stub
		List<UserInfo> userInfoByUserAccount = cm.getUserInfoByUserAccount(Integer.parseInt(userAccount));
		if (userInfoByUserAccount == null || userInfoByUserAccount.size() <= 0) {
			throw ServiceException.userException("", "账号不存在");
		}
		// 创建一个365天过期的JSON WEB TOKEN
		String reqFrom = request.getHeader("From");
		String jwt = PushAuthHelper.createJsonWebToken(userInfoByUserAccount.get(0).getId(), reqFrom, TimeUnit.HOURS,
				Constant.COOKIE_EXPIRE_HOURS);

		return jwt;
	}

	// 退还红包
	@Override
	public Object returnLuckbag() {
		// TODO Auto-generated method stub

		// 查找状态是1的红包信息

		List<LuckbagInfo> selectByExample = im.selectluckbagBycreatetime();
		for (int i = 0; i < selectByExample.size(); i++) {
			// 调接口
			// 返回20000
			// 修改状态
			Integer userId = selectByExample.get(i).getUserid();
			String balance = selectByExample.get(i).getBalance();
			String timestampStr = System.currentTimeMillis() + "";
			String billno = UUID.randomUUID().toString();
			String s = null;

			double mul = PushRefinedCalculation.mul(10, Double.parseDouble(balance));
			try {
				String encode1 = URLEncoder.encode(AES.AESEncode("LYWH@#$!32", userId + ""), "UTF-8");
				String encode3 = URLEncoder.encode(AES.AESEncode("9$dz4Y33oy", mul + ""), "UTF-8");
				String encode4 = URLEncoder.encode(AES.AESEncode("9$dz4Y33oy", "yobXT2ENRb" + timestampStr), "UTF-8");
				s = UrlLoad.load(IpResourceLocation.TT_EGGWORLD_IP + "/ttc-eggworld/ttc/record",
						"openId=" + encode1 + "&muchMoney=" + encode3 + "&transactionType=36"
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
				selectByExample.get(i).setState(2);
				im.updateByPrimaryKey(selectByExample.get(i));

			} else if ("20002".equals(code)) {

				String msg = jsonObject1.getString("msg");
				if (msg.equals("success")) {
					throw ServiceException.userException("", "未完成kyc二级认证");
				} else {
					throw ServiceException.userException("", msg);
				}
			}
		}

		return null;
	}

	// 查找发红包记录
	@Override
	public Object getsendLuckBagHistory(Integer userId, Integer page, Integer rows) {
		// TODO Auto-generated method stub
		page = (page - 1) * rows;
		List<SendVo> lvs = new ArrayList<SendVo>();

		// 查红包记录
		List<LuckbagInfo> selectByExample = im.getsendLuckBagHistory(userId, page, rows);
		for (int i = 0; i < selectByExample.size(); i++) {
			// 祝福语信息
			LuckbagBlessingInfo selectByPrimaryKey = bm.selectByPrimaryKey(selectByExample.get(i).getLbid());
			// 红包领取信息
			LuckbagUserinfoExample example = new LuckbagUserinfoExample();
			com.liyunet.domain.luckbag.LuckbagUserinfoExample.Criteria createCriteria = example.createCriteria();
			createCriteria.andLidEqualTo(selectByExample.get(i).getId());
			// 领取数量
			int countByExample = um.countByExample(example);

			SendVo lv = new SendVo();
			lv.setId(selectByExample.get(i).getId());
			lv.setNum(selectByExample.get(i).getBidtnum());
			lv.setCreatetime(selectByExample.get(i).getCreatetime());
			lv.setBlessinfo(selectByPrimaryKey.getBlessing());
			if (selectByExample.get(i).getState() == 0) {
				lv.setMsg("已领完");
			} else if (selectByExample.get(i).getState() == 1) {
				lv.setMsg("已领" + countByExample + "/" + selectByExample.get(i).getBagnum());
			} else {
				lv.setMsg("退款 " + selectByExample.get(i).getBalance() + " 已领" + countByExample + "/"
						+ selectByExample.get(i).getBagnum());

			}

			lvs.add(lv);
		}

		Map<Object, Object> countlist = im.selectCountByuserId(userId);
		String string = countlist.toString();
		System.out.println(string);
		Map<String, Object> map = new HashMap();

		map.put("list", lvs);
		map.put("bagnum", countlist == null ? 0 : (countlist.get("count(*)") == null ? 0 : countlist.get("count(*)")));
		map.put("bidtnum",
				countlist == null ? 0
						: (countlist.get("SUM(CAST(bidtnum AS DECIMAL(10,1)))") == null ? 0
								: countlist.get("SUM(CAST(bidtnum AS DECIMAL(10,1)))")));
		return map;
	}

	// 获取收红包记录
	@Override
	public Object getaccepLuckBagHistory(Integer userId, Integer page, Integer rows) {
		// TODO Auto-generated method stub
		page = (page - 1) * rows;
		List<LuckBagVo> bagVos = new ArrayList<LuckBagVo>();
		// 获取收红包记录
		List<LuckbagUserinfo> list = um.getaccepLuckBagHistory(userId, page, rows);
		for (int i = 0; i < list.size(); i++) {
			// 红包信息
			LuckbagInfo selectByPrimaryKey = im.selectByPrimaryKey(list.get(i).getLid());

			// 查出发红包人信息
			List<UserInfo> userInfoByid = cm.getUserInfoByid(list.get(i).getFromUserid() + "");

			// 祝福语信息
			LuckbagBlessingInfo selectByPrimaryKey2 = bm.selectByPrimaryKey(selectByPrimaryKey.getLbid());
			LuckBagVo lv = new LuckBagVo();
			lv.setId(selectByPrimaryKey.getId());
			lv.setBlockid(userInfoByid.get(0).getUserAccount());
			lv.setBlress(selectByPrimaryKey2.getBlessing());
			lv.setNum(list.get(i).getNum());
			lv.setCreatetime(list.get(i).getCreatetime());
			bagVos.add(lv);
		}
		Map<Object, Object> countlist = um.selectCountByuserId(userId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bagnum", countlist == null ? 0 : (countlist.get("count(*)") == null ? 0 : countlist.get("count(*)")));
		map.put("bidtnum",
				countlist == null ? 0
						: (countlist.get("SUM(CAST(num AS DECIMAL(10,1)))") == null ? 0
								: countlist.get("SUM(CAST(num AS DECIMAL(10,1)))")));
		map.put("list", bagVos);
		return map;
	}

	// 查详情
	@Override
	public Object getLuckBagInfoNotoken(Integer id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formatedDateStr = DateHelper.getFormatedDateStr(new Date(), "yyyy-MM-dd HH:mm:ss");
		// 根据id查出红包信息
		LuckbagInfo selectByPrimaryKey = im.selectByPrimaryKey(id);
		// 查区块
		List<UserInfo> userInfoByid = cm.getUserInfoByid(selectByPrimaryKey.getUserid() + "");
		// 判断是否过期
		String creTime = selectByPrimaryKey.getCreatetime();
		String newTime = DateHelper.getFormatedDateStr(new Date(), "yyyy-MM-dd HH:mm:ss");
		Date creT = null;
		Date newT = null;
		try {
			newT = sdf.parse(newTime);
			creT = DateUtile.addDate(sdf.parse(creTime), 1);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		long lnewT = newT.getTime();
		long lcreT = creT.getTime();
		// 查询祝福
		LuckbagBlessingInfo luckbagBlessingInfo = bm.selectByPrimaryKey(selectByPrimaryKey.getLbid());
		List<LuckBagVo> lvs = new ArrayList<LuckBagVo>();

		// 查抢红包的人
		LuckbagUserinfoExample example1 = new LuckbagUserinfoExample();
		example1.setOrderByClause("createtime DESC");
		com.liyunet.domain.luckbag.LuckbagUserinfoExample.Criteria createCriteria1 = example1.createCriteria();
		createCriteria1.andLidEqualTo(id);
		List<LuckbagUserinfo> selectByExample = um.selectByExample(example1);
		for (int i = 0; i < selectByExample.size(); i++) {
			LuckBagVo lv = new LuckBagVo();
			List<UserInfo> blockinfo = cm.getUserInfoByid(selectByExample.get(i).getToUserid() + "");
			lv.setBlockid(blockinfo.get(0).getUserAccount());
			lv.setState(0);
			lv.setNum(selectByExample.get(i).getNum());
			lv.setCreatetime(selectByExample.get(i).getCreatetime());
			lvs.add(lv);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("blockId", userInfoByid.get(0).getUserAccount());
		map.put("blesss", luckbagBlessingInfo.getBlessing());
		map.put("bagInfo", selectByPrimaryKey);
		// 过期

		// 可领
		if (selectByPrimaryKey.getState() == 1) {
			if (lnewT >= lcreT) {
				map.put("invalidStatus", 1);
			} else {
				map.put("invalidStatus", 0);
			}
		} else if (selectByPrimaryKey.getState() == 0) {
			// 领完了
			map.put("invalidStatus", 2);
		} else {
			map.put("invalidStatus", 1);
		}

		map.put("userList", lvs);
		return map;
	}

}
