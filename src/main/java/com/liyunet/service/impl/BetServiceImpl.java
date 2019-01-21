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
import com.liyunet.domain.bet.AppControl;
import com.liyunet.domain.bet.BetBannerInfo;
import com.liyunet.domain.bet.BetBannerInfoExample;
import com.liyunet.domain.bet.BetInfo;
import com.liyunet.domain.bet.BetOptionInfo;
import com.liyunet.domain.bet.BetOptionInfoExample;
import com.liyunet.domain.bet.BetUserInfo;
import com.liyunet.domain.bet.BetUserInfoExample;
import com.liyunet.domain.bet.vo.BetBannerInfoVo;
import com.liyunet.domain.bet.vo.BetInfoVo;
import com.liyunet.domain.bet.vo.BetMyVo;
import com.liyunet.domain.bet.vo.BetOptionVo;
import com.liyunet.domain.bet.vo.BetTailVo;
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
import com.liyunet.mapper.betMapper.AppControlMapper;
import com.liyunet.mapper.betMapper.BetBannerInfoMapper;
import com.liyunet.mapper.betMapper.BetInfoMapper;
import com.liyunet.mapper.betMapper.BetOptionInfoMapper;
import com.liyunet.mapper.betMapper.BetUserInfoMapper;
import com.liyunet.mapper.luckbagMapper.LuckbagBlessingInfoMapper;
import com.liyunet.mapper.luckbagMapper.LuckbagInfoMapper;
import com.liyunet.mapper.luckbagMapper.LuckbagUserinfoMapper;
import com.liyunet.mapper.userMapper.CertificationMapper;
import com.liyunet.service.BetService;
import com.liyunet.service.LuckBagService;
import com.liyunet.util.PushRefinedCalculation;

@Service("BetService")
@Transactional
public class BetServiceImpl implements BetService {
	@Autowired
	private BetBannerInfoMapper bbm;

	@Autowired
	private BetInfoMapper bim;

	@Autowired
	private BetOptionInfoMapper bom;

	@Autowired
	private BetUserInfoMapper bum;

	@Autowired
	private CertificationMapper cm;

	@Autowired
	private AppControlMapper am;

	// 查所有banner
	@Override
	public Object getBetBanner() {
		// String formatedDateStr = DateHelper.getFormatedDateStr(new Date(),
		// "yyyy-MM-dd HH:mm:ss"); String formatedDateStr =
		// DateHelper.getFormatedDateStr(new Date(), "yyyy-MM-dd HH:mm:ss");
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// TODO Auto-generated method stub
		BetBannerInfoExample example = new BetBannerInfoExample();
		com.liyunet.domain.bet.BetBannerInfoExample.Criteria createCriteria = example.createCriteria();
		createCriteria.andStateEqualTo(1);
		example.setOrderByClause("sort desc");
		List<BetBannerInfo> selectByExample = bbm.selectByExample(example);
		List<BetBannerInfoVo> list = new ArrayList<BetBannerInfoVo>();
		for (int i = 0; i < selectByExample.size(); i++) {
			BetBannerInfo betBannerInfo = selectByExample.get(i);
			BetBannerInfoVo bv = new BetBannerInfoVo();
			bv.setBannerUrl(betBannerInfo.getBannerUrl());
			bv.setBid(betBannerInfo.getBid());
			bv.setId(betBannerInfo.getId());
			BetInfo betInfo = bim.selectByPrimaryKey(betBannerInfo.getBid());
			bv.setTitle(betInfo.getTitle());
			list.add(bv);
		}

		return list;
	}

	// 获取竞猜list
	@Override
	public Object getBetList(Integer type, Integer page, Integer rows) {
		// TODO Auto-generated method stub
		String formatedDateStr = DateHelper.getFormatedDateStr(new Date(), "yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		page = (page - 1) * rows;
		// type=0是未结束 2是已结束
		// 查竞猜list
		List<BetInfo> betInfos = new ArrayList<BetInfo>();
		if (type == 2) {
			betInfos = bim.selectBetListtwo(page, rows);
		} else {
			betInfos = bim.selectBetListone(page, rows);
		}
		List<BetInfoVo> betInfoVos = new ArrayList<BetInfoVo>();
		for (int i = 0; i < betInfos.size(); i++) {
			// 查总人数 和总数量
			String sbidtnum = bum.selectNum(betInfos.get(i).getId()) == null ? "0.0"
					: bum.selectNum(betInfos.get(i).getId());
			String scount = bum.selectCount(betInfos.get(i).getId());
			// String sbidtnum = countlist.get("SUM(CAST(bidt AS
			// DECIMAL(10,1)))")==null?"0": countlist.get("SUM(CAST(bidt AS
			// DECIMAL(10,1)))")+"";
			// String scount = countlist.get("count(*)")+"";
			// 查选项
			BetOptionInfoExample example = new BetOptionInfoExample();
			com.liyunet.domain.bet.BetOptionInfoExample.Criteria createCriteria = example.createCriteria();
			createCriteria.andBidEqualTo(betInfos.get(i).getId());
			createCriteria.andStateEqualTo(1);
			example.setOrderByClause("sort desc");
			List<BetOptionInfo> selectByExample = bom.selectByExample(example);
			int optionType = 0;
			if (selectByExample.size() > 2) {
				optionType = 2;
			} else if (selectByExample.size() > 0 && selectByExample.size() <= 2) {
				optionType = 1;
			} else {
				optionType = 0;
			}
			List<BetOptionVo> betOptionVos = new ArrayList<BetOptionVo>();
			// 倍数计算
			Map<Integer, String> betmap = new HashMap<Integer, String>();
			for (int j = 0; j < selectByExample.size(); j++) {
				// 查找每个选项的人数
				// Map<Object, Object> bums =
				// bum.selectsingleCountandNum(selectByExample.get(j).getId());
				// double bidtnum = bums.get("SUM(CAST(bidt AS
				// DECIMAL(10,1)))")==null?0.0:Double.parseDouble(
				// bums.get("SUM(CAST(bidt AS DECIMAL(10,1)))")+"");
				// double count = Double.parseDouble(bums.get("count(*)")+"") ;
				double bidtnum = bum.selectsingleNum(selectByExample.get(j).getId()) == null ? 0.0
						: bum.selectsingleNum(selectByExample.get(j).getId());
				double count = bum.selectsingleCount(selectByExample.get(j).getId());
				double tp = Double.parseDouble(betInfos.get(i).getTp());
				// 倍数

				String multiple = "";
				String pn = "";
				// 判断总币达是否为0，如果是0走a方案：竞猜选项数量 x（1-抽成百分比）
				if (sbidtnum.equals("0.0")) {
					multiple = Math.floor(
							PushRefinedCalculation.mul(selectByExample.size(), PushRefinedCalculation.sub(1, tp)) * 100)
							/ 100 + "";
					pn = "0";

				} else {
					// b方案
					// 判断子选项是不是0
					if (bidtnum == 0.0) {
						multiple = Math.floor(PushRefinedCalculation.div(20.0,
								PushRefinedCalculation.mul(
										PushRefinedCalculation.add(20.0, Double.parseDouble(sbidtnum)),
										PushRefinedCalculation.sub(1, tp)))
								* 100) / 100 + "";
						pn = "0";

					} else {
						multiple = getMultiple(Double.parseDouble(sbidtnum), tp, bidtnum);
						pn = Math.floor(PushRefinedCalculation.div(Double.parseDouble(sbidtnum), bidtnum) * 10000)
								/ 10000 + "";
					}

				}
				BetOptionVo betOptionVo = new BetOptionVo();
				betOptionVo.setOpetion(selectByExample.get(j).getOptionInfo());
				betOptionVo.setId(selectByExample.get(j).getId());
				betOptionVo.setMultiple(multiple);
				betOptionVo.setProportion(pn);
				betOptionVos.add(betOptionVo);
				betmap.put(selectByExample.get(j).getId(), multiple);
			}

			BetInfoVo betInfoVo = new BetInfoVo();
			betInfoVo.setId(betInfos.get(i).getId());
			betInfoVo.setBannerUrl(betInfos.get(i).getBanner());
			betInfoVo.setContext(betInfos.get(i).getContext());
			betInfoVo.setCreatetime(betInfos.get(i).getCreatetime());
			betInfoVo.setEndtime(betInfos.get(i).getEndtime());
			betInfoVo.setList(betOptionVos);
			betInfoVo.setOptionType(optionType);
			betInfoVo.setPerpelenum(scount);
			betInfoVo.setBidtnum(sbidtnum);
			betInfoVo.setMultiple(betmap.get(betInfos.get(i).getResultId()));
			betInfoVo.setResult(betInfos.get(i).getResult());
			Date etime = null;
			Date ntime = null;
			try {
				etime = sdf.parse(betInfos.get(i).getEndtime());
				ntime = sdf.parse(formatedDateStr);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long letime = etime.getTime();
			long lnend = ntime.getTime();
			if (betInfos.get(i).getLotteryStatus() == 2) {
				betInfoVo.setStatus(betInfos.get(i).getLotteryStatus());
			} else {
				if (lnend < letime) {
					betInfoVo.setStatus(0);
				} else {
					betInfoVo.setStatus(1);
				}
			}

			betInfoVo.setTitle(betInfos.get(i).getTitle());
			betInfoVos.add(betInfoVo);
		}

		return betInfoVos;
	}

	// 查详情
	@Override
	public Object getBetDetails(Integer id) {
		String formatedDateStr = DateHelper.getFormatedDateStr(new Date(), "yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// TODO Auto-generated method stub
		BetInfo selectByPrimaryKey = bim.selectByPrimaryKey(id);
		// 查总人数 和总数量
		String sbidtnum = bum.selectNum(id) == null ? "0.0" : bum.selectNum(id);
		String scount = bum.selectCount(id);
		// 查选项
		BetOptionInfoExample example = new BetOptionInfoExample();
		com.liyunet.domain.bet.BetOptionInfoExample.Criteria createCriteria = example.createCriteria();
		createCriteria.andBidEqualTo(selectByPrimaryKey.getId());
		createCriteria.andStateEqualTo(1);
		example.setOrderByClause("sort desc");
		List<BetOptionInfo> selectByExample = bom.selectByExample(example);
		int optionType = 0;
		if (selectByExample.size() > 2) {
			optionType = 2;
		} else if (selectByExample.size() > 0 && selectByExample.size() <= 2) {
			optionType = 1;
		} else {
			optionType = 0;
		}
		List<BetOptionVo> betOptionVos = new ArrayList<BetOptionVo>();
		// 倍数计算
		Map<Integer, String> notmap = new HashMap<Integer, String>();
		for (int j = 0; j < selectByExample.size(); j++) {
			// 查找每个选项的人数
			double bidtnum = bum.selectsingleNum(selectByExample.get(j).getId()) == null ? 0.0
					: bum.selectsingleNum(selectByExample.get(j).getId());
			double count = bum.selectsingleCount(selectByExample.get(j).getId());
			double tp = Double.parseDouble(selectByPrimaryKey.getTp());
			// 倍数
			String multiple = "";
			String pn = "";
			// 判断总币达是否为0，如果是0走a方案：竞猜选项数量 x（1-抽成百分比）
			if (sbidtnum.equals("0.0")) {
				multiple = Math.floor(
						PushRefinedCalculation.mul(selectByExample.size(), PushRefinedCalculation.sub(1, tp)) * 100)
						/ 100 + "";
				pn = "0";

			} else {
				// b方案
				// 判断子选项是不是0
				if (bidtnum == 0.0) {
					multiple = Math
							.floor(PushRefinedCalculation.div(20.0,
									PushRefinedCalculation.mul(
											PushRefinedCalculation.add(20.0, Double.parseDouble(sbidtnum)),
											PushRefinedCalculation.sub(1, tp)))
									* 100)
							/ 100 + "";
					pn = "0";

				} else {
					multiple = getMultiple(Double.parseDouble(sbidtnum), tp, bidtnum);
					pn = Math.floor(PushRefinedCalculation.div(Double.parseDouble(sbidtnum), bidtnum) * 10000) / 10000
							+ "";
				}

			}
			BetOptionVo betOptionVo = new BetOptionVo();
			betOptionVo.setOpetion(selectByExample.get(j).getOptionInfo());
			betOptionVo.setId(selectByExample.get(j).getId());
			betOptionVo.setMultiple(multiple);
			betOptionVo.setProportion(pn);
			betOptionVos.add(betOptionVo);
			notmap.put(selectByExample.get(j).getId(), multiple);
		}

		BetInfoVo betInfoVo = new BetInfoVo();
		betInfoVo.setId(selectByPrimaryKey.getId());
		betInfoVo.setBannerUrl(selectByPrimaryKey.getBanner());
		betInfoVo.setContext(selectByPrimaryKey.getContext());
		betInfoVo.setCreatetime(selectByPrimaryKey.getCreatetime());
		betInfoVo.setEndtime(selectByPrimaryKey.getEndtime());
		betInfoVo.setList(betOptionVos);
		betInfoVo.setOptionType(optionType);
		betInfoVo.setPerpelenum(scount);
		betInfoVo.setBidtnum(sbidtnum);
		betInfoVo.setResult(selectByPrimaryKey.getResult());

		Date etime = null;
		Date ntime = null;
		try {
			etime = sdf.parse(selectByPrimaryKey.getEndtime());
			ntime = sdf.parse(formatedDateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long letime = etime.getTime();
		long lnend = ntime.getTime();
		if (selectByPrimaryKey.getLotteryStatus() == 2) {
			betInfoVo.setStatus(selectByPrimaryKey.getLotteryStatus());
		} else {
			if (lnend < letime) {
				betInfoVo.setStatus(0);
			} else {
				betInfoVo.setStatus(1);
			}
		}

		betInfoVo.setTitle(selectByPrimaryKey.getTitle());
		List<BetTailVo> betTailVos = new ArrayList<BetTailVo>();
		List<Map<String, Object>> notnews = bum.selectNots(id);
		for (int i = 0; i < notnews.size(); i++) {
			// 不中奖不显示

			String notes = (int) Double.parseDouble(notnews.get(i).get("notes") + "") + "";
			String option_info = notnews.get(i).get("option_info") + "";
			String bidt = notnews.get(i).get("bidt") + "";
			String userId = notnews.get(i).get("userId") + "";
			String bo = notnews.get(i).get("bo") + "";
			BetTailVo betTailVo = new BetTailVo();
			if (selectByPrimaryKey.getLotteryStatus().intValue() == 2) {
				if (bo.equals(selectByPrimaryKey.getResultId() + "")) {
					// 收益
					double b = Math.floor(PushRefinedCalculation.mul(Double.parseDouble(bidt),
							Double.parseDouble(notmap.get(Integer.parseInt(bo)))) * 10) / 10;
					betTailVo.setBidt(b + "");
					List<UserInfo> userInfoByid = cm.getUserInfoByid(userId);
					betTailVo.setBlcokId(userInfoByid.get(0).getUserAccount());
					betTailVo.setNots(notes);
					betTailVo.setOption(option_info);
					betTailVos.add(betTailVo);
				}

			} else {
				// 收益
				double b = Math.floor(PushRefinedCalculation.mul(Double.parseDouble(bidt),
						Double.parseDouble(notmap.get(Integer.parseInt(bo)))) * 10) / 10;
				betTailVo.setBidt(b + "");
				List<UserInfo> userInfoByid = cm.getUserInfoByid(userId);
				betTailVo.setBlcokId(userInfoByid.get(0).getUserAccount());
				betTailVo.setNots(notes);
				betTailVo.setOption(option_info);
				betTailVos.add(betTailVo);

			}

		}
		System.out.println(notnews);
		List<BetMyVo> betMyVos = new ArrayList<BetMyVo>();
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("betInfoVo", betInfoVo);
		map.put("betMyVolist", betMyVos);
		map.put("betTailVolist", betTailVos);
		return map;
	}

	// 查详情带token
	@Override
	public Object getBetDetailsBytoken(Integer id, Integer uId) {
		String formatedDateStr = DateHelper.getFormatedDateStr(new Date(), "yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// TODO Auto-generated method stub
		BetInfo selectByPrimaryKey = bim.selectByPrimaryKey(id);
		// 查总人数 和总数量

		String sbidtnum = bum.selectNum(id) == null ? "0.0" : bum.selectNum(id);
		String scount = bum.selectCount(id);
		// 查选项
		BetOptionInfoExample example = new BetOptionInfoExample();
		com.liyunet.domain.bet.BetOptionInfoExample.Criteria createCriteria = example.createCriteria();
		createCriteria.andBidEqualTo(selectByPrimaryKey.getId());
		createCriteria.andStateEqualTo(1);
		example.setOrderByClause("sort desc");
		List<BetOptionInfo> selectByExample = bom.selectByExample(example);
		int optionType = 0;
		if (selectByExample.size() > 2) {
			optionType = 2;
		} else if (selectByExample.size() > 0 && selectByExample.size() <= 2) {
			optionType = 1;
		} else {
			optionType = 0;
		}
		List<BetOptionVo> betOptionVos = new ArrayList<BetOptionVo>();
		// 倍数计算
		Map<Integer, String> notmap = new HashMap<Integer, String>();
		for (int j = 0; j < selectByExample.size(); j++) {

			double bidtnum = bum.selectsingleNum(selectByExample.get(j).getId()) == null ? 0.0
					: bum.selectsingleNum(selectByExample.get(j).getId());
			double count = bum.selectsingleCount(selectByExample.get(j).getId());
			double tp = Double.parseDouble(selectByPrimaryKey.getTp());
			// 倍数
			String multiple = "";
			String pn = "";
			// 判断总币达是否为0，如果是0走a方案：竞猜选项数量 x（1-抽成百分比）
			if (sbidtnum.equals("0.0")) {
				multiple = Math.floor(
						PushRefinedCalculation.mul(selectByExample.size(), PushRefinedCalculation.sub(1, tp)) * 100)
						/ 100 + "";
				pn = "0";

			} else {
				// b方案
				// 判断子选项是不是0
				if (bidtnum == 0.0) {
					multiple = Math
							.floor(PushRefinedCalculation.div(20.0,
									PushRefinedCalculation.mul(
											PushRefinedCalculation.add(20.0, Double.parseDouble(sbidtnum)),
											PushRefinedCalculation.sub(1, tp)))
									* 100)
							/ 100 + "";
					pn = "0";

				} else {
					multiple = getMultiple(Double.parseDouble(sbidtnum), tp, bidtnum);
					pn = Math.floor(PushRefinedCalculation.div(Double.parseDouble(sbidtnum), bidtnum) * 10000) / 10000
							+ "";
				}

			}
			BetOptionVo betOptionVo = new BetOptionVo();
			betOptionVo.setOpetion(selectByExample.get(j).getOptionInfo());
			betOptionVo.setId(selectByExample.get(j).getId());
			betOptionVo.setMultiple(multiple);
			betOptionVo.setProportion(pn);
			betOptionVos.add(betOptionVo);
			notmap.put(selectByExample.get(j).getId(), multiple);
		}

		BetInfoVo betInfoVo = new BetInfoVo();
		betInfoVo.setId(selectByPrimaryKey.getId());
		betInfoVo.setBannerUrl(selectByPrimaryKey.getBanner());
		betInfoVo.setContext(selectByPrimaryKey.getContext());
		betInfoVo.setCreatetime(selectByPrimaryKey.getCreatetime());
		betInfoVo.setEndtime(selectByPrimaryKey.getEndtime());
		betInfoVo.setList(betOptionVos);
		betInfoVo.setOptionType(optionType);
		betInfoVo.setPerpelenum(scount);
		betInfoVo.setBidtnum(sbidtnum);
		betInfoVo.setResult(selectByPrimaryKey.getResult());
		Date etime = null;
		Date ntime = null;
		try {
			etime = sdf.parse(selectByPrimaryKey.getEndtime());
			ntime = sdf.parse(formatedDateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long letime = etime.getTime();
		long lnend = ntime.getTime();
		if (selectByPrimaryKey.getLotteryStatus() == 2) {
			betInfoVo.setStatus(selectByPrimaryKey.getLotteryStatus());
		} else {
			if (lnend < letime) {
				betInfoVo.setStatus(0);
			} else {
				betInfoVo.setStatus(1);
			}
		}

		betInfoVo.setTitle(selectByPrimaryKey.getTitle());
		List<BetTailVo> betTailVos = new ArrayList<BetTailVo>();
		List<Map<String, Object>> notnews = bum.selectNots(id);
		for (int i = 0; i < notnews.size(); i++) {
			String notes = (int) Double.parseDouble(notnews.get(i).get("notes") + "") + "";
			String option_info = notnews.get(i).get("option_info") + "";
			String bidt = notnews.get(i).get("bidt") + "";
			String userId = notnews.get(i).get("userId") + "";
			String bo = notnews.get(i).get("bo") + "";
			BetTailVo betTailVo = new BetTailVo();
			// 收益
			if (selectByPrimaryKey.getLotteryStatus().intValue() == 2) {
				if (bo.equals(selectByPrimaryKey.getResultId() + "")) {
					double b = Math.floor(PushRefinedCalculation.mul(Double.parseDouble(bidt),
							Double.parseDouble(notmap.get(Integer.parseInt(bo)))) * 10) / 10;
					betTailVo.setBidt(b + "");
					List<UserInfo> userInfoByid = cm.getUserInfoByid(userId);
					betTailVo.setBlcokId(userInfoByid.get(0).getUserAccount());
					betTailVo.setNots(notes);
					betTailVo.setOption(option_info);
					betTailVo.setId(Integer.parseInt(bo));
					betTailVos.add(betTailVo);
				}
			} else {
				// 收益
				double b = Math.floor(PushRefinedCalculation.mul(Double.parseDouble(bidt),
						Double.parseDouble(notmap.get(Integer.parseInt(bo)))) * 10) / 10;
				betTailVo.setBidt(b + "");
				List<UserInfo> userInfoByid = cm.getUserInfoByid(userId);
				betTailVo.setBlcokId(userInfoByid.get(0).getUserAccount());
				betTailVo.setNots(notes);
				betTailVo.setOption(option_info);
				betTailVos.add(betTailVo);

			}
		}
		List<UserInfo> userInfoByid = cm.getUserInfoByid(uId + "");
		String userAccount = userInfoByid.get(0).getUserAccount();
		List<BetMyVo> betMyVos = new ArrayList<BetMyVo>();
		for (int i = 0; i < notnews.size(); i++) {
			String notes = (int) Double.parseDouble(notnews.get(i).get("notes") + "") + "";
			String option_info = notnews.get(i).get("option_info") + "";
			String bidt = notnews.get(i).get("bidt") + "";
			String userId = notnews.get(i).get("userId") + "";
			String bo = notnews.get(i).get("bo") + "";
			List<UserInfo> userInfoByidb = cm.getUserInfoByid(userId);
			if (userInfoByidb.get(0).getUserAccount().equals(userAccount)) {

				double b = Math.floor(PushRefinedCalculation.mul(Double.parseDouble(bidt),
						Double.parseDouble(notmap.get(Integer.parseInt(bo)))) * 10) / 10;
				bidt = b + "";
				if (selectByPrimaryKey.getLotteryStatus() == 2) {
					if (!(selectByPrimaryKey.getResultId().intValue() == Integer.parseInt(bo))) {
						bidt = "0.0";
					}

				}

				BetMyVo betMyVo = new BetMyVo();
				betMyVo.setBidt(bidt);
				betMyVo.setNum(Math.floor(PushRefinedCalculation.mul(Double.parseDouble(notes), 20.0) * 10) / 10 + "");
				betMyVo.setOption(option_info);
				betMyVos.add(betMyVo);
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("betInfoVo", betInfoVo);
		map.put("betMyVolist", betMyVos);
		map.put("betTailVolist", betTailVos);
		return map;
	}

	// 下注
	@Override
	public Object bet(Integer oid, Integer userId, Integer betnum) {
		// TODO Auto-generated method stub
		String formatedDateStr = DateHelper.getFormatedDateStr(new Date(), "yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timestampStr = System.currentTimeMillis() + "";
		double bidtnum = PushRefinedCalculation.mul(20.0, betnum);
		// 通过选项查投注
		BetOptionInfo selectByPrimaryKey = bom.selectByPrimaryKey(oid);
		BetInfo betInfo = bim.selectByPrimaryKey(selectByPrimaryKey.getBid());
		int insert = 0;
		Date etime = null;
		Date ntime = null;
		try {
			etime = sdf.parse(betInfo.getEndtime());
			ntime = sdf.parse(formatedDateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long letime = etime.getTime();
		long lnend = ntime.getTime();

		if (lnend > letime || betInfo.getLotteryStatus() == 2) {
			throw ServiceException.userException("", "已结束");
		}
		if (betnum <= 0) {
			throw ServiceException.userException("", "注数必须大于0");
		}

		String s = null;

		double mul = PushRefinedCalculation.mul(10, bidtnum);
		try {
			String encode1 = URLEncoder.encode(AES.AESEncode("LYWH@#$!32", userId + ""), "UTF-8");
			String encode3 = URLEncoder.encode(AES.AESEncode("9$dz4Y33oy", mul + ""), "UTF-8");
			String encode4 = URLEncoder.encode(AES.AESEncode("9$dz4Y33oy", "yobXT2ENRb" + timestampStr), "UTF-8");
			s = UrlLoad.load(IpResourceLocation.TT_EGGWORLD_IP + "/ttc-eggworld/ttc/record",
					"openId=" + encode1 + "&muchMoney=" + encode3 + "&transactionType=37"
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

			// 如果20000

			// 总币达数

			BetUserInfo betUserInfo = new BetUserInfo();
			betUserInfo.setBidt(bidtnum + "");
			betUserInfo.setBoid(oid);
			betUserInfo.setCreatetime(formatedDateStr);
			betUserInfo.setNotes(betnum + "");
			betUserInfo.setStatus(0);
			betUserInfo.setUserid(userId);
			//

			insert = bum.insert(betUserInfo);
		} else if ("20002".equals(code)) {

			String msg = jsonObject1.getString("msg");
			if (msg.equals("success")) {
				throw ServiceException.userException("", "未完成kyc二级认证");
			} else {
				throw ServiceException.userException("", msg);
			}
		}
		return insert;
	}

	// 获取倍数
	static String getMultiple(double sum, double tp, double count) {
		double div = 0;
		if (count == 0) {
			div = 0;
		} else {
			div = PushRefinedCalculation.div(count, PushRefinedCalculation.mul(sum, PushRefinedCalculation.sub(1, tp)));
			div = Math.floor(div * 100) / 100;
		}
		if (div >= 5) {
			div = Math.floor(div * 10) / 10;

		} else if (div <= 1) {
			div = 1;
		}
		return div + "";
	}

	public static void main(String[] args) {
		String multiple = getMultiple(100, 0.2, 100);

		System.out.println(multiple);
	}

	// 查竞猜记录
	@Override
	public Object getBetHistoryBytoken(Integer userId) {
		// TODO Auto-generated method stub
		String formatedDateStr = DateHelper.getFormatedDateStr(new Date(), "yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 查竞猜list
		List<BetInfo> betInfos = bim.selectBetHistoryList(userId, 0);
		List<BetInfoVo> betInfoVos1 = new ArrayList<BetInfoVo>();
		for (int i = 0; i < betInfos.size(); i++) {
			// 查选项=======================================
			// 查总人数 和总数量
			String sbidtnum = bum.selectNum(betInfos.get(i).getId()) == null ? "0.0"
					: bum.selectNum(betInfos.get(i).getId());
			String scount = bum.selectCount(betInfos.get(i).getId());
			BetOptionInfoExample example = new BetOptionInfoExample();
			com.liyunet.domain.bet.BetOptionInfoExample.Criteria createCriteria = example.createCriteria();
			createCriteria.andBidEqualTo(betInfos.get(i).getId());
			createCriteria.andStateEqualTo(1);
			example.setOrderByClause("sort desc");
			List<BetOptionInfo> selectByExample = bom.selectByExample(example);
			List<BetOptionVo> betOptionVos = new ArrayList<BetOptionVo>();
			// 倍数计算
			for (int j = 0; j < selectByExample.size(); j++) {

				// 查找每个选项的人数
				double bidtnum = bum.selectsingleNum(selectByExample.get(j).getId()) == null ? 0.0
						: bum.selectsingleNum(selectByExample.get(j).getId());
				double count = bum.selectsingleCount(selectByExample.get(j).getId());
				double tp = Double.parseDouble(betInfos.get(i).getTp());
				// 倍数
				String multiple = "";
				String pn = "";
				// 判断总币达是否为0，如果是0走a方案：竞猜选项数量 x（1-抽成百分比）
				if (sbidtnum.equals("0.0")) {
					multiple = Math.floor(
							PushRefinedCalculation.mul(selectByExample.size(), PushRefinedCalculation.sub(1, tp)) * 100)
							/ 100 + "";
					pn = "0";

				} else {
					// b方案
					// 判断子选项是不是0
					if (bidtnum == 0.0) {
						multiple = Math.floor(PushRefinedCalculation.div(20.0,
								PushRefinedCalculation.mul(
										PushRefinedCalculation.add(20.0, Double.parseDouble(sbidtnum)),
										PushRefinedCalculation.sub(1, tp)))
								* 100) / 100 + "";
						pn = "0";

					} else {
						multiple = getMultiple(Double.parseDouble(sbidtnum), tp, bidtnum);
						pn = Math.floor(PushRefinedCalculation.div(Double.parseDouble(sbidtnum), bidtnum) * 10000)
								/ 10000 + "";
					}

				}
				BetOptionVo betOptionVo = new BetOptionVo();
				betOptionVo.setOpetion(selectByExample.get(j).getOptionInfo());
				betOptionVo.setId(selectByExample.get(j).getId());
				betOptionVo.setMultiple(multiple);
				// 用户投资
				// 根据选项查用户投资
				String sum = bum.selectsumByop(selectByExample.get(j).getId(), userId) == null ? "0"
						: bum.selectsumByop(selectByExample.get(j).getId(), userId) + "";
				betOptionVo.setProportion(sum);
				betOptionVos.add(betOptionVo);
			}

			// ==============================

			BetInfoVo betInfoVo = new BetInfoVo();
			betInfoVo.setEndtime(betInfos.get(i).getEndtime());
			betInfoVo.setId(betInfos.get(i).getId());
			betInfoVo.setList(betOptionVos);
			betInfoVo.setResult(betInfos.get(i).getResult());
			Date etime = null;
			Date ntime = null;
			try {
				etime = sdf.parse(betInfos.get(i).getEndtime());
				ntime = sdf.parse(formatedDateStr);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long letime = etime.getTime();
			long lnend = ntime.getTime();
			if (betInfos.get(i).getLotteryStatus() == 2) {
				betInfoVo.setStatus(betInfos.get(i).getLotteryStatus());
			} else {
				if (lnend < letime) {
					betInfoVo.setStatus(0);
				} else {
					betInfoVo.setStatus(1);
				}
			}

			betInfoVo.setTitle(betInfos.get(i).getTitle());
			betInfoVos1.add(betInfoVo);
		}

		// ---------------------------------------------
		// 胜利的记录
		List<Integer> isExit = new ArrayList<Integer>();

		List<BetInfoVo> betInfoVos2 = new ArrayList<BetInfoVo>();
		List<BetInfo> betInfosvt = bim.selectBetHistoryList(userId, 1);
		for (int i = 0; i < betInfosvt.size(); i++) {
			// 查选项=======================================
			// 查总人数 和总数量
			String sbidtnum = bum.selectNum(betInfosvt.get(i).getId()) == null ? "0.0"
					: bum.selectNum(betInfosvt.get(i).getId());
			String scount = bum.selectCount(betInfosvt.get(i).getId());

			BetOptionInfo selectByExample = bom.selectByPrimaryKey(betInfosvt.get(i).getResultId());
			// 倍数计算

			// BetUserInfoExample bumexample = new BetUserInfoExample();
			// com.liyunet.domain.bet.BetUserInfoExample.Criteria
			// createCriteria2 = bumexample.createCriteria();
			// createCriteria2.andBoidEqualTo(selectByExample.getId());
			// List<BetUserInfo> selectByExample2 =
			// bum.selectByExample(bumexample);
			BetOptionVo betOptionVo = new BetOptionVo();
			// if (selectByExample2 != null && selectByExample2.size() > 0) {

			// 查找每个选项的人数
			double bidtnum = bum.selectsingleNum(selectByExample.getId()) == null ? 0.0
					: bum.selectsingleNum(selectByExample.getId());
			double count = bum.selectsingleCount(selectByExample.getId());
			double tp = Double.parseDouble(betInfosvt.get(i).getTp());
			// 倍数
			String multiple = getMultiple(Double.parseDouble(sbidtnum), tp, bidtnum);

			betOptionVo.setOpetion(selectByExample.getOptionInfo());
			betOptionVo.setId(selectByExample.getId());
			betOptionVo.setMultiple(multiple);
			// 根据选项查用户投资
			String sum = bum.selectsumByop(selectByExample.getId(), userId) == null ? "0"
					: bum.selectsumByop(selectByExample.getId(), userId) + "";
			// 用户投资收益

			betOptionVo.setProportion(
					Math.floor(PushRefinedCalculation.mul(Double.parseDouble(sum), Double.parseDouble(multiple)) * 10)
							/ 10 + "");

			// }
			// ==============================

			BetInfoVo betInfoVo = new BetInfoVo();
			betInfoVo.setEndtime(betInfosvt.get(i).getEndtime());
			betInfoVo.setId(betInfosvt.get(i).getId());
			betInfoVo.setBetOptionVo(betOptionVo);
			betInfoVo.setResult(betInfosvt.get(i).getResult());
			Date etime = null;
			Date ntime = null;
			try {
				etime = sdf.parse(betInfosvt.get(i).getEndtime());
				ntime = sdf.parse(formatedDateStr);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long letime = etime.getTime();
			long lnend = ntime.getTime();
			if (betInfosvt.get(i).getLotteryStatus() == 2) {
				betInfoVo.setStatus(betInfosvt.get(i).getLotteryStatus());
			} else {
				if (lnend < letime) {
					betInfoVo.setStatus(0);
				} else {
					betInfoVo.setStatus(1);
				}
			}

			betInfoVo.setTitle(betInfosvt.get(i).getTitle());
			isExit.add(betInfosvt.get(i).getId());
			betInfoVos2.add(betInfoVo);
		}
		// 失败的记录

		List<BetInfoVo> betInfoVos3 = new ArrayList<BetInfoVo>();
		// if (betInfosvt.size() <= 0 || betInfosvt == null) {
		List<BetInfo> betInfoslw = bim.selectBetHistoryList(userId, 2);
		for (int i = 0; i < betInfoslw.size(); i++) {
			// 查选项=======================================
			// 查总人数 和总数量
			if (!isExit.contains(betInfoslw.get(i).getId())) {

				String sbidtnum = bum.selectNum(betInfoslw.get(i).getId()) == null ? "0.0"
						: bum.selectNum(betInfoslw.get(i).getId());
				String scount = bum.selectCount(betInfoslw.get(i).getId());

				BetOptionInfo selectByPrimaryKey = bom.selectByPrimaryKey(betInfoslw.get(i).getResultId());
				// 倍数计算
				// BetUserInfoExample bumexample = new BetUserInfoExample();
				// com.liyunet.domain.bet.BetUserInfoExample.Criteria
				// createCriteria2 = bumexample.createCriteria();
				// createCriteria2.andBoidEqualTo(selectByPrimaryKey.getId());
				// List<BetUserInfo> selectByExample2 =
				// bum.selectByExample(bumexample);
				BetOptionVo betOptionVo = new BetOptionVo();
				// if (selectByExample2 != null && selectByExample2.size() > 0)
				// {

				// 查找每个选项的人数
				double bidtnum = bum.selectsingleNum(betInfoslw.get(i).getResultId()) == null ? 0.0
						: bum.selectsingleNum(betInfoslw.get(i).getResultId());
				double count = bum.selectsingleCount(selectByPrimaryKey.getId());
				double tp = Double.parseDouble(betInfoslw.get(i).getTp());
				// 倍数
				BetOptionInfoExample example = new BetOptionInfoExample();
				com.liyunet.domain.bet.BetOptionInfoExample.Criteria createCriteria = example.createCriteria();
				createCriteria.andBidEqualTo(betInfoslw.get(i).getId());
				createCriteria.andStateEqualTo(1);
				List<BetOptionInfo> selectByExample = bom.selectByExample(example);

				// 倍数
				String multiple = "";
				// 判断总币达是否为0，如果是0走a方案：竞猜选项数量 x（1-抽成百分比）
				if (sbidtnum.equals("0.0")) {
					multiple = Math.floor(
							PushRefinedCalculation.mul(selectByExample.size(), PushRefinedCalculation.sub(1, tp)) * 100)
							/ 100 + "";

				} else {
					// b方案
					// 判断子选项是不是0
					if (bidtnum == 0.0) {
						multiple = Math.floor(PushRefinedCalculation.div(20.0,
								PushRefinedCalculation.mul(
										PushRefinedCalculation.add(20.0, Double.parseDouble(sbidtnum)),
										PushRefinedCalculation.sub(1, tp)))
								* 100) / 100 + "";

					} else {
						multiple = getMultiple(Double.parseDouble(sbidtnum), tp, bidtnum);
					}

				}

				betOptionVo.setOpetion(selectByPrimaryKey.getOptionInfo());
				betOptionVo.setId(selectByPrimaryKey.getId());
				betOptionVo.setMultiple(multiple);
				// 用户投资
				betOptionVo.setProportion("0");
				// }
				// ==============================

				BetInfoVo betInfoVo = new BetInfoVo();
				betInfoVo.setEndtime(betInfoslw.get(i).getEndtime());
				betInfoVo.setId(betInfoslw.get(i).getId());
				betInfoVo.setBetOptionVo(betOptionVo);
				betInfoVo.setResult(betInfoslw.get(i).getResult());
				Date etime = null;
				Date ntime = null;
				try {
					etime = sdf.parse(betInfoslw.get(i).getEndtime());
					ntime = sdf.parse(formatedDateStr);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				long letime = etime.getTime();
				long lnend = ntime.getTime();
				if (betInfoslw.get(i).getLotteryStatus() == 2) {
					betInfoVo.setStatus(betInfoslw.get(i).getLotteryStatus());
				} else {
					if (lnend < letime) {
						betInfoVo.setStatus(0);
					} else {
						betInfoVo.setStatus(1);
					}
				}

				betInfoVo.setTitle(betInfoslw.get(i).getTitle());
				betInfoVos3.add(betInfoVo);
			}
			// }

		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("betInfoVos1", betInfoVos1);
		map.put("betInfoVos2", betInfoVos2);
		map.put("betInfoVos3", betInfoVos3);

		return map;
	}

	@Override
	public Object eggcontrol() {
		// 0是关闭 1是开启
		// TODO Auto-generated method stub
		AppControl selectByPrimaryKey = am.selectByPrimaryKey(1);
		if (selectByPrimaryKey != null) {
			return selectByPrimaryKey.getEggStatus();

		} else {
			return 1;
		}

	}

	// 发奖励
	@Override
	public Object setOption(Integer oid) {
		// TODO Auto-generated method stub
		String formatedDateStr = DateHelper.getFormatedDateStr(new Date(), "yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timestampStr = System.currentTimeMillis() + "";
		BetOptionInfo betOptionInfo = bom.selectByPrimaryKey(oid);
		BetInfo betInfo = bim.selectByPrimaryKey(betOptionInfo.getBid());
		int insert = 0;
		Date etime = null;
		Date ntime = null;
		try {
			etime = sdf.parse(betInfo.getEndtime());
			ntime = sdf.parse(formatedDateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long letime = etime.getTime();
		long lnend = ntime.getTime();

		if (lnend < letime) {
			throw ServiceException.userException("", "未结束不能发放奖励");
		}

		// 根据oid查询选项

		if (betInfo.getLotteryStatus().intValue() == 2) {
			throw ServiceException.userException("", "该竞猜奖励已发放");
		}
		// 查总人数 和总数量
		String sbidtnum = bum.selectNum(betInfo.getId()) == null ? "0.0" : bum.selectNum(betInfo.getId());
		String scount = bum.selectCount(betInfo.getId());
		// 查选项
		BetOptionInfoExample example = new BetOptionInfoExample();
		com.liyunet.domain.bet.BetOptionInfoExample.Criteria createCriteria = example.createCriteria();
		createCriteria.andBidEqualTo(betInfo.getId());
		createCriteria.andStateEqualTo(1);
		example.setOrderByClause("sort desc");
		List<BetOptionInfo> selectByExample = bom.selectByExample(example);

		// 倍数计算
		Map<Integer, String> notmap = new HashMap<Integer, String>();
		// 收益计算
		Map<Integer, String> primap = new HashMap<Integer, String>();
		for (int j = 0; j < selectByExample.size(); j++) {
			// 查找每个选项的人数
			double bidtnum = bum.selectsingleNum(selectByExample.get(j).getId()) == null ? 0.0
					: bum.selectsingleNum(selectByExample.get(j).getId());
			double count = bum.selectsingleCount(selectByExample.get(j).getId());
			double tp = Double.parseDouble(betInfo.getTp());
			// 倍数
			String multiple = "";
			String pn = "";
			// 判断总币达是否为0，如果是0走a方案：竞猜选项数量 x（1-抽成百分比）
			if (sbidtnum.equals("0.0")) {
				multiple = Math.floor(
						PushRefinedCalculation.mul(selectByExample.size(), PushRefinedCalculation.sub(1, tp)) * 100)
						/ 100 + "";
				pn = "0";

			} else {
				// b方案
				// 判断子选项是不是0
				if (bidtnum == 0.0) {
					multiple = Math
							.floor(PushRefinedCalculation.div(20.0,
									PushRefinedCalculation.mul(
											PushRefinedCalculation.add(20.0, Double.parseDouble(sbidtnum)),
											PushRefinedCalculation.sub(1, tp)))
									* 100)
							/ 100 + "";
					pn = "0";

				} else {
					multiple = getMultiple(Double.parseDouble(sbidtnum), tp, bidtnum);
					pn = Math.floor(PushRefinedCalculation.div(Double.parseDouble(sbidtnum), bidtnum) * 10000) / 10000
							+ "";
				}

			}

			notmap.put(selectByExample.get(j).getId(), multiple);
			primap.put(selectByExample.get(j).getId(), pn);
		}

		// 查出竞猜下所有的人
		List<Map<String, Object>> notnews = bum.selectNots(betInfo.getId());
		for (int i = 0; i < notnews.size(); i++) {
			String notes = (int) Double.parseDouble(notnews.get(i).get("notes") + "") + "";
			String option_info = notnews.get(i).get("option_info") + "";
			String bidt = notnews.get(i).get("bidt") + "";
			String userId = notnews.get(i).get("userId") + "";
			String bo = notnews.get(i).get("bo") + "";
			BetTailVo betTailVo = new BetTailVo();
			// 收益
			if (bo.equals(oid + "")) {
				double b = Math.floor(PushRefinedCalculation.mul(Double.parseDouble(bidt),
						Double.parseDouble(notmap.get(Integer.parseInt(bo)))) * 10) / 10;
				// 调用绑码系统
				String s = null;

				double mul = PushRefinedCalculation.mul(10, b);
				try {
					String encode1 = URLEncoder.encode(AES.AESEncode("LYWH@#$!32", userId), "UTF-8");
					String encode3 = URLEncoder.encode(AES.AESEncode("9$dz4Y33oy", mul + ""), "UTF-8");
					String encode4 = URLEncoder.encode(AES.AESEncode("9$dz4Y33oy", "yobXT2ENRb" + timestampStr),
							"UTF-8");
					s = UrlLoad.load(IpResourceLocation.TT_EGGWORLD_IP + "/ttc-eggworld/ttc/record",
							"openId=" + encode1 + "&muchMoney=" + encode3 + "&transactionType=38"
									+ "&app_id=3360a88e4318896c9fe3e031e64541f9c176af67fa87634f&mch_id=yobXT2ENRb&timestampStr="
									+ timestampStr + "&sign=" + encode4);

					JSONObject jsonObject = JSON.parseObject(s);
					String string = jsonObject.getString("state");
					JSONObject jsonObject1 = JSON.parseObject(string);
					String code = jsonObject1.getString("code");
					if ("20000".equals(code)) {
						continue;
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

			}
		}

		// -----------------------------------------------------------------
		List<BetUserInfo> betUserInfos = bum.selectPeopleBid(betInfo.getId());
		for (int i = 0; i < betUserInfos.size(); i++) {
			if (betUserInfos.get(i).getStatus().intValue() == 0) {
				if (betUserInfos.get(i).getBoid().intValue() == oid.intValue()) {

					betUserInfos.get(i).setStatus(1);
					bum.updateByPrimaryKey(betUserInfos.get(i));

				} else {
					// 修改
					betUserInfos.get(i).setStatus(2);
					bum.updateByPrimaryKey(betUserInfos.get(i));
				}
			}

		}
		betInfo.setLotteryStatus(2);
		betInfo.setResultId(oid);
		betInfo.setResult(betOptionInfo.getOptionInfo());
		int updateByPrimaryKey = bim.updateByPrimaryKey(betInfo);
		return updateByPrimaryKey;
	}

}
