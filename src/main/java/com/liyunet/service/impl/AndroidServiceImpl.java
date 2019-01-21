package com.liyunet.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.liyunet.common.http.PostAndGet;
import com.liyunet.common.password.AES;
import com.liyunet.common.pushPassword.PushPasswordHash;
import com.liyunet.common.queue.SmsListenerBusPwd;
import com.liyunet.common.sms.SmsCodeGenerator;
import com.liyunet.common.sms.SmsHolder;
import com.liyunet.common.util.IpResourceLocation;
import com.liyunet.common.util.UrlLoad;
import com.liyunet.domain.*;
import com.liyunet.domain.dto.AppParameter;
import com.liyunet.exception.ServiceException;
import com.liyunet.mapper.android.AndroidMapper;
import com.liyunet.mapper.community.PushCommonMapper;
import com.liyunet.service.AndroidService;
import com.liyunet.util.BidtWalletVo;
import com.liyunet.util.PushRefinedCalculation;
import com.liyunet.util.UuidUtil;
import com.liyunet.vo.androidVo.AppParameterVo;
import com.liyunet.vo.user.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by wuyunan on 2018/9/4.
 */

@Transactional
@Service("androidService")
public class AndroidServiceImpl implements AndroidService {

	private final PushCommonMapper pushCommonMapper;

	private final AndroidMapper androidMapper;

	@Autowired
	public AndroidServiceImpl(AndroidMapper androidMapper, PushCommonMapper pushCommonMapper) {
		this.androidMapper = androidMapper;
		this.pushCommonMapper = pushCommonMapper;
	}

	@Override
	public Object getProperty(Integer id) {
		PropertyVos vo = new PropertyVos();
		List<UserInfo> userInfoList = androidMapper.getuserInfoById(id);
		if (userInfoList != null && userInfoList.size() > 0) {
			List<FBTPrice> fbtPriceList = pushCommonMapper.getFBTPrice();
			String pNes = fbtPriceList.get(0).getPrice();
			double v = Double.parseDouble(pNes);
			UserInfo userInfo = userInfoList.get(0);
			String userId = userInfo.getuId();
			List<AssociatedAccount> associatedAccountList = androidMapper.getAssociatedAccount(userId);
			List<MoneyDetailsVo> moneyDetailsList = new ArrayList<>();
			double totalMoney = 0;
			DecimalFormat df = new DecimalFormat("#.##");
			df.setRoundingMode(RoundingMode.DOWN);
			if (associatedAccountList != null && associatedAccountList.size() > 0) {
				Boolean b = false;
				for (int i = 0; i < associatedAccountList.size(); i++) {
					if (associatedAccountList.get(i).getAppName().equals("1")) {
						b = true;
					} 
				}

				if(b){
				for (AssociatedAccount associatedAccount : associatedAccountList) {

					String appName = associatedAccount.getAppName();
					MoneyDetailsVo moneyDetails = new MoneyDetailsVo();

					moneyDetails.setAppName(appName);
					if ("0".equals(appName) || "4".equals(appName)) {
						continue;
					}
					double money = getMoney(appName, associatedAccount.getToken(), userInfo.getId());// 获取价格
					double mul = PushRefinedCalculation.mul(v, money);
					if (mul == 0) {
						moneyDetails.setConversion(mul + "");
					} else {
						moneyDetails.setConversion(df.format(mul));
					}
					if (money == 0) {
						moneyDetails.setMoney(money + "");

					} else {
						moneyDetails.setMoney(df.format(money));
					}

					totalMoney += money;
					moneyDetailsList.add(moneyDetails);

					if ("1".equals(appName)) {

						MoneyDetailsVo moneyDetails1 = new MoneyDetailsVo();
						moneyDetails1.setAppName("0");

						double money1 = getMoney("0", associatedAccount.getToken(), userInfo.getId());// 获取价格
						double mul1 = PushRefinedCalculation.mul(v, money1);

						if (mul1 == 0) {
							moneyDetails1.setConversion("" + mul1);

						} else {
							moneyDetails1.setConversion(df.format(mul1));

						}
						if (money1 == 0) {
							moneyDetails1.setMoney(money1 + "");

						} else {
							moneyDetails1.setMoney(df.format(money1));

						}

						totalMoney += money1;
						moneyDetailsList.add(moneyDetails1);
					}

				}
				
			}else{
			//不包括1的情况
				double money1 = getMoney("0","", userInfo.getId());// 获取价格
				//币达
				MoneyDetailsVo moneyDetails1 = new MoneyDetailsVo();
				moneyDetails1.setAppName("0");
				double mul = PushRefinedCalculation.mul(v, money1);
				if (mul == 0) {
					moneyDetails1.setConversion(mul + "");
				} else {
					moneyDetails1.setConversion(df.format(mul));
				}
				if (money1 == 0) {
					moneyDetails1.setMoney(money1 + "");

				} else {
					moneyDetails1.setMoney(df.format(money1));
				}
				//蛋生
				MoneyDetailsVo moneyDetails2 = new MoneyDetailsVo();
				moneyDetails2.setAppName("1");
				moneyDetails2.setConversion("0.00");
				moneyDetails2.setMoney("0.00");
				//otc
				MoneyDetailsVo moneyDetails3 = new MoneyDetailsVo();
				moneyDetails3.setAppName("2");
				moneyDetails3.setConversion("0.00");
				moneyDetails3.setMoney("0.00");
				
				moneyDetailsList.add(moneyDetails1);
				moneyDetailsList.add(moneyDetails2);
				moneyDetailsList.add(moneyDetails3);
				totalMoney += money1;	
				
			}

			}
			double mul = PushRefinedCalculation.mul(v, totalMoney);
			if (totalMoney == 0) {
				vo.setTotal(totalMoney + "");
			} else {
				vo.setTotal(df.format(totalMoney));
			}
			if (mul == 0) {
				vo.setTotalCny(mul + "");

			} else {
				vo.setTotalCny(df.format(mul));

			}
			vo.setList(moneyDetailsList);
			return vo;
		} else {
			throw ServiceException.userException("Please log in again!", "请重新登录!");

		}
	}

	@Override
	public Object getOTC(Integer userId, Integer page, Integer row) {
		page = (page - 1) * row;
		List<UserInfo> userInfoList = androidMapper.getuserInfoById(userId);
		if (userInfoList != null && userInfoList.size() > 0) {
			UserInfo userInfo = userInfoList.get(0);
			String uId = userInfo.getuId();
			List<AssociatedAccount> associatedAccountList = androidMapper.getAssociatedAccount(uId);
			AssociatedAccount associatedAccount = null;
			CommonVo vo = new CommonVo();
			if (associatedAccountList != null && associatedAccountList.size() > 0) {
				for (AssociatedAccount associatedAccount1 : associatedAccountList) {
					String appName = associatedAccount1.getAppName();
					if ("2".equals(appName)) {
						associatedAccount = associatedAccount1;
					}
				}
			} else {
				return vo;
			}
			if (associatedAccount == null) {
				return vo;
			}
			String token = associatedAccount.getToken();
			String s = null;
			try {
				s = UrlLoad.load(IpResourceLocation.OTC_IP + "/timetreaty_push/api/push/common/getRunningOrder",
						"id=" + token + "&page=" + page + "&row=" + row);

			} catch (Exception e) {
				e.printStackTrace();
			}
			JSONObject jsonObject = JSON.parseObject(s);
			String string = jsonObject.getString("state");
			JSONObject jsonObject1 = JSON.parseObject(string);
			String code = jsonObject1.getString("code");
			if ("20000".equals(code)) {
				String data = jsonObject.getString("data");
				if (data != null) {
					List<OrderVo> vos = new ArrayList<>();

					JSONObject jsonObject2 = JSON.parseObject(data);
					String parameter = jsonObject2.getString("parameter");
					JSONArray list = jsonObject2.getJSONArray("list");
					if (list != null) {
						for (int i = 0; i < list.size(); i++) {
							JSONObject jsonObject3 = list.getJSONObject(i);
							OrderVo orderVo = new OrderVo();
							String uId1 = jsonObject3.getString("uId");
							String createTime = jsonObject3.getString("createTime");
							String currencyType = jsonObject3.getString("currencyType");
							String dealNum = jsonObject3.getString("dealNum");
							orderVo.setId(uId1);
							orderVo.setCreateTime(createTime);
							orderVo.setCurrencyType(currencyType);
							orderVo.setDealNum(dealNum);
							orderVo.setContent("蛋生的世界提币至OTC交易");
							vos.add(orderVo);
						}
					}
					vo.setList(vos);
					vo.setParameter(parameter);
					return vo;
				} else {
					throw ServiceException.userException("Please log in again!", "信息查询失败!");
				}
			} else if ("20002".equals(code)) {
				String msg = jsonObject1.getString("msg");
				throw ServiceException.userException("A system exception, contact customer service", msg);
			} else {
				throw ServiceException.userException("Please log in again!", "信息查询失败!");
			}
		} else {
			throw ServiceException.userException("Please log in again!", "请重新登录!");
		}

	}

	@Override
	public Object getMessage(Integer userId, Integer page, Integer row) {

		page = (page - 1) * row;
		List<Announcement> announcementList = androidMapper.getAnnouncement(page, row);
		List<MessageVo> vo = new ArrayList<>();
		int i = 0;
		if (announcementList != null && announcementList.size() > 0) {
			for (Announcement announcement : announcementList) {
				Integer id = announcement.getId();
				// if (id.intValue() == 1 || id.intValue() == 2) {
				// continue;
				// }
				String uId = announcement.getuId();
				List<UserInfo> userInfoList = androidMapper.getuserInfoById(userId);
				MessageVo messageVo = new MessageVo();
				if (userInfoList != null && userInfoList.size() > 0) {
					UserInfo userInfo = userInfoList.get(0);
					List<AnnouncementRead> announcementReads = androidMapper.getAnnouncementRead(uId,
							userInfo.getuId());
					if (announcementReads != null && announcementReads.size() > 0) {
						messageVo.setIsRead(1);
					} else {
						messageVo.setIsRead(0);
						i++;
					}
				} else {
					messageVo.setIsRead(0);
				}
				messageVo.setType(1);
				messageVo.setId(announcement.getuId());
				messageVo.setAnnouncementTitle(announcement.getTitle());
				messageVo.setAnnouncementCreateTime(announcement.getCreateTime());
				vo.add(messageVo);
			}

		}
		CommonVo commonVo = new CommonVo();
		commonVo.setList(vo);
		commonVo.setParameter(i + "");
		return commonVo;

	}

	@Override
	public Object getMessageContent(Integer userId, String id) {

		List<Announcement> announcementList = androidMapper.getAnnouncementById(id);
		if (announcementList != null && announcementList.size() > 0) {
			Announcement announcement = announcementList.get(0);
			AnnouncementVo vo = new AnnouncementVo();
			vo.setContent(announcement.getContent());
			vo.setCreatrTime(announcement.getCreateTime());
			vo.setTitle(announcement.getTitle());
			if (userId != null) {
				List<UserInfo> userInfoList = androidMapper.getuserInfoById(userId);
				if (userInfoList != null && userInfoList.size() > 0) {
					UserInfo userInfo = userInfoList.get(0);
					List<AnnouncementRead> announcementReadList = androidMapper.getAnnouncementRead(id,
							userInfo.getuId());
					if (announcementReadList == null || announcementReadList.size() < 1) {

						AnnouncementRead announcementRead = new AnnouncementRead();
						announcementRead.setIsRead(1);
						announcementRead.setuId(id);
						announcementRead.setUserId(userInfo.getuId());
						androidMapper.saveAnnouncementRead(announcementRead);

					}
				} else {
					throw ServiceException.userException("Please log in again!", "信息查询失败!");
				}
			}

			return vo;

		} else {
			throw ServiceException.userException("Please log in again!", "信息查询失败!");

		}
	}

	@Override
	public Object getAddress(Integer userId, Integer page, Integer row, String type) {

		List<UserInfo> userInfoList = androidMapper.getuserInfoById(userId);
		if (userInfoList != null && userInfoList.size() > 0) {
			UserInfo userInfo = userInfoList.get(0);
			String uId = userInfo.getuId();
			page = (page - 1) * row;
			List<UserAddress> userAddressList = androidMapper.getUserAddress(uId, page, row, type);
			List<UserAddressVo> vos = new ArrayList<>();
			if (userAddressList != null && userAddressList.size() > 0) {
				for (UserAddress userAddress : userAddressList) {

					UserAddressVo vo = new UserAddressVo();
					vo.setAddress(userAddress.getAddress());
					vo.setAddressName(userAddress.getAddressName());
					vo.setCurrency(userAddress.getCurrency());
					vo.setId(userAddress.getuId());
					vo.setRemark(userAddress.getRemark());
					vo.setType(userAddress.getType());
					vos.add(vo);
				}
			}
			return vos;

		} else {
			throw ServiceException.userException("Please log in again!", "信息查询失败!");

		}
	}

	@Override
	public void saveAddress(Integer userId, String currency, String address, String name, String remark, String type) {

		if (!"1".equals(type) && !"0".equals(type)) {
			throw ServiceException.userException("Please log in again!", "信息查询失败!");
		}
		List<UserInfo> userInfoList = androidMapper.getuserInfoById(userId);
		if (userInfoList != null && userInfoList.size() > 0) {
			UserInfo userInfo = userInfoList.get(0);
			UserAddress userAddress = new UserAddress();
			userAddress.setAddress(address);
			userAddress.setAddressName(name);
			userAddress.setBlockId(userInfo.getUserAccount());
			userAddress.setUserId(userInfo.getuId());
			userAddress.setuId(UuidUtil.getUUid());
			userAddress.setType(Integer.parseInt(type));
			userAddress.setCurrency(currency);
			userAddress.setRemark(remark);
			userAddress.setCreateTime(getCurrentTime());
			int i = androidMapper.saveUserAddress(userAddress);
			if (i < 0) {
				throw ServiceException.userException("Save failed, please try in later!", "保存失败，请稍后在试!");
			}
		} else {
			throw ServiceException.userException("Please log in again!", "信息查询失败!");
		}
	}

	@Override
	public void deleteAddress(Integer userId, String id) {

		List<UserInfo> userInfoList = androidMapper.getuserInfoById(userId);
		if (userInfoList != null && userInfoList.size() > 0) {
			UserInfo userInfo = userInfoList.get(0);
			String uId = userInfo.getuId();
			List<UserAddress> userAddressList = androidMapper.getUserAddressById(uId, id);
			if (userAddressList != null && userAddressList.size() > 0) {
				int i = androidMapper.deleteUserAddress(id);
				if (i < 0) {
					throw ServiceException.userException("Delete failed!", "删除失败!");
				}
			}

		} else {
			throw ServiceException.userException("Please log in again!", "信息查询失败!");
		}
	}

	@Override
	public void updateAddress(Integer userId, String id, String currency, String address, String name, String remark) {

		List<UserInfo> userInfoList = androidMapper.getuserInfoById(userId);
		if (userInfoList != null && userInfoList.size() > 0) {
			UserInfo userInfo = userInfoList.get(0);
			String uId = userInfo.getuId();
			List<UserAddress> userAddressList = androidMapper.getUserAddressById(uId, id);
			if (userAddressList != null && userAddressList.size() > 0) {
				UserAddress userAddress = userAddressList.get(0);
				userAddress.setAddress(address);
				userAddress.setAddressName(name);
				userAddress.setCurrency(currency);
				userAddress.setRemark(remark);
				userAddress.setUpdateTime(getCurrentTime());
				int i = androidMapper.updateAddress(userAddress);
				if (i < 0) {
					throw ServiceException.userException("Modify the failure!", "修改失败!");

				}
			}

		} else {
			throw ServiceException.userException("Please log in again!", "信息查询失败!");
		}

	}

	@Override
	public void fundPwd(Integer userId, HttpServletRequest request) {

		List<UserInfo> userInfoList = androidMapper.getuserInfoById(userId);
		if (userInfoList != null && userInfoList.size() > 0) {
			UserInfo userInfo = userInfoList.get(0);
			String phoneNum = userInfo.getPhoneNum();
			if (phoneNum == null || "".equals(phoneNum)) {
				throw ServiceException.userException("Please bind mobile phones!", "请先绑定手机!");
			}

			if (!isphonenum(phoneNum)) {
				throw ServiceException.userException("The mobile phone number is not correct!", "手机号格式不正确!");
			}

			try {
				handleCellphones(phoneNum, "5");
			} catch (InterruptedException e) {
				throw ServiceException.userException("Verification code sent failure!", "验证码发送失败!");
			}

		} else {
			throw ServiceException.userException("Please log in again!", "信息查询失败!");
		}

	}

	@Override
	public void updatePwd(Integer userId, String newPwd, String code, String type, String oldPwd) {

		List<UserInfo> userInfoList = androidMapper.getuserInfoById(userId);
		if (userInfoList != null && userInfoList.size() > 0) {
			UserInfo userInfo = userInfoList.get(0);
			String phoneNum = userInfo.getPhoneNum();
			List<InvitationCode> PushInvitationCodes = androidMapper.getByCodeByaccount(code, phoneNum, "5");
			if (PushInvitationCodes == null || PushInvitationCodes.size() == 0) {

				throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");
			}

			InvitationCode ic = PushInvitationCodes.get(0);
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime expireTime = LocalDateTime.parse(ic.getExpireTime());
			if (ic.isUsed()) {
				throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");
			}
			if (!"1".equals(type) && !"0".equals(type)) {
				throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");
			}
			if ("1".equals(type)) {
				boolean b = false;
				try {
					b = PushPasswordHash.validatePassword(oldPwd, userInfo.getFundPwd());
				} catch (Exception e) {
					throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");
				}
				if (!b) {
					throw ServiceException.userException("Password mistake!", "密码错误!");
				}
			}

			if (ic.isUsed() || now.isAfter(expireTime)) {

				throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");

			} else {
				ic.setUsed(true);
				ic.setUseTime(now.toString());
				int i = androidMapper.updateInvitationCode(ic);
				if (i <= 0) {
					throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");
				}
			}
			try {
				userInfo.setFundPwd(PushPasswordHash.createHash(newPwd.trim()));
				int i = androidMapper.updateUserInfoFundPwd(userInfo);
				if (i < 0) {
					throw ServiceException.userException("The verification code is wrong or expired!", "验证码有误或过期!");
				}
			} catch (Exception e) {
				throw ServiceException.userException("Please log in again!", "信息查询失败!");
			}
		} else {
			throw ServiceException.userException("Please log in again!", "信息查询失败!");
		}

	}

	@Override
	public Object whetherfundPwd(Integer userId) {
		List<UserInfo> userInfoList = androidMapper.getuserInfoById(userId);
		if (userInfoList != null && userInfoList.size() > 0) {
			UserInfo userInfo = userInfoList.get(0);
			String fundPwd = userInfo.getFundPwd();
			if (fundPwd == null || "".equals(fundPwd)) {
				return 0;
			} else {
				return 1;
			}

		} else {
			throw ServiceException.userException("Please log in again!", "信息查询失败!");
		}
	}

	@Override
	public Object getBidtWallet(Integer userId, String project, Integer page, Integer row) {

		List<UserInfo> userInfoList = androidMapper.getUserInfoByToken(userId, project);
		if (userInfoList != null && userInfoList.size() > 0) {
			CommonVo commonVo = new CommonVo();
			UserInfo userInfo = userInfoList.get(0);
			// 蛋生相关资产操作
//			String uId = userInfo.getuId();
//			List<AssociatedAccount> associatedAccountList = androidMapper.getAssociatedAccountByUserIdAndAppName(uId,
//					1);
//
//			if (associatedAccountList != null && associatedAccountList.size() > 0) {
				// AssociatedAccount associatedAccount =
				// associatedAccountList.get(0);
				// String token = associatedAccount.getToken();
				Integer userAccount = userInfo.getId();
				String s = null;
				try {
					s = UrlLoad.loadGet(IpResourceLocation.TT_EGGWORLD_IP + "/ttc-eggworld/ttc/getBills?id="
							+ userAccount + "&page=" + page + "&rows=" + 10000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				JSONObject jsonObject = JSON.parseObject(s);
				String string = jsonObject.getString("state");
				JSONObject jsonObject1 = JSON.parseObject(string);
				String code = jsonObject1.getString("code");
				if ("20000".equals(code)) {
					JSONArray data = jsonObject.getJSONArray("data");
					List<BidtWalletVo> bidtWalletVos = new ArrayList<>();
					if (data != null && !"".equals(data)) {
						for (int i = 0; i < data.size(); i++) {
							JSONObject jsonObject2 = data.getJSONObject(i);
							BidtWalletVo vo = new BidtWalletVo();
							String muchMoney = jsonObject2.getString("muchMoney");
							double div = PushRefinedCalculation.div(10, Double.parseDouble(muchMoney));
							vo.setCoinNum(div + "");// 数量
							String transactionType = jsonObject2.getString("transactionType");
							String toOpenId = jsonObject2.getString("toOpenId");
							String bidtContent = getBidtContentSimplification(transactionType);
							if ("17".equals(transactionType) || "21".equals(transactionType)
									|| "40".equals(transactionType)) {
								vo.setTransactionStatus(jsonObject2.getString("transactionState"));
								vo.setReasonText(jsonObject2.getString("reasonText"));
							} else {
								vo.setTransactionStatus(null);
								vo.setReasonText(null);
							}
							if (bidtContent == null) {
								continue;
							}
							if ("32".equals(transactionType) && toOpenId.equals("618")) {
								vo.setContent("币管家投资");
							} else {
								vo.setContent(bidtContent);
							}

							String regulation = getRegulationSimplification(transactionType);
							vo.setRegulation(regulation);
							vo.setId(jsonObject2.getString("id"));
							vo.setTime(jsonObject2.getString("createTime"));
							vo.setAddress(jsonObject2.getString("accountAddress"));
							bidtWalletVos.add(vo);
						}
						commonVo.setList(bidtWalletVos);
					}

				} else if ("20002".equals(code)) {
					String msg = jsonObject1.getString("msg");
					throw ServiceException.userException("", msg);
				}
				//
//			}
			return commonVo;
		} else {
			throw ServiceException.userException("Please log in again!", "信息查询失败!");
		}

	}

	private String getRegulationSimplification(String type) {

		switch (type) {
		// case "0":
		// return "1";
		// case "1":
		// return "1";
		// case "2":
		// return "1";
		// case "3":
		// return "1";
		// case "4":
		// return "1";
		// case "5":
		// return "1";
		// case "6":
		// return "1";
		case "7":
			return "1";
		// case "8":
		// return "0";
		// case "9":
		// return "0";
		// case "10":
		// return "0";
		// case "11":
		// return "0";
		// case "12":
		// return "0";
		// case "13":
		// return "1";
		// case "14":
		// return "0";
		// case "15":
		// return "0";
		case "16":
			return "1";
		// case "17":
		// return "0";
		case "20":
			return "1";
		// case "21":
		// return "0";
		case "22":
			return "1";
		case "25":
			return "0";
		case "30":
			return "0";
		case "31":
			return "1";
		case "32":
			return "0";
		case "34":
			return "0";
		case "35":
			return "1";
		case "36":
			return "1";
		case "37":
			return "0";
		case "38":
			return "1";
		case "39":
			return "1";
		case "40":
			return "0";
		case "41":
			return "0";
		}
		return null;
	}

	private String getRegulation(String type) {

		switch (type) {
		case "0":
			return "1";
		case "1":
			return "1";
		case "2":
			return "1";
		case "3":
			return "1";
		case "4":
			return "1";
		case "5":
			return "1";
		case "6":
			return "1";
		case "7":
			return "1";
		case "8":
			return "0";
		case "9":
			return "0";
		case "10":
			return "0";
		case "11":
			return "0";
		case "12":
			return "0";
		case "13":
			return "1";
		case "14":
			return "0";
		case "15":
			return "0";
		case "16":
			return "1";
		case "17":
			return "0";
		case "20":
			return "1";
		case "21":
			return "0";
		case "22":
			return "1";
		case "25":
			return "0";
		case "30":
			return "0";
		case "31":
			return "1";
		case "32":
			return "0";
		case "34":
			return "0";
		case "35":
			return "1";
		case "36":
			return "1";
		case "37":
			return "0";
		case "38":
			return "1";
		case "39":
			return "1";
		case "40":
			return "0";
		case "41":
			return "0";
		}
		return null;
	}

	private String getBidtContentSimplification(String type) {

		switch (type) {
		// case "0":
		// return "个人交易中心";
		// case "1":
		// return "家族交易中心";
		// case "2":
		// return "成就";
		// case "3":
		// return "每日任务";
		// case "4":
		// return "订单中心";
		// case "5":
		// return "TTC充值";
		// case "6":
		// return "人民币充值";
		case "7":
			return "蛋生的世界";
		// case "8":
		// return "提币到平台账户";
		// case "9":
		// return "个人商店";
		// case "10":
		// return "家族商店";
		// case "11":
		// return "解锁蛋坑";
		// case "12":
		// return "刷新家族商店";
		// case "13":
		// return "注册";
		// case "14":
		// return "创建家族";
		// case "15":
		// return "vi会员充值";
		case "16":
			return "活动奖励";
		// case "17":
		// return "活动提现";
		case "20":
			return "蛋生的世界分红奖励";
		// case "21":
		// return "蛋生提币到OTC";
		case "22":
			return "退款-MyOTC";
		case "25":
			return "抽奖";
		case "30":
			return "Luckycoin";
		case "31":
			return "Luckycoin奖金";
		case "32":
			return "转出";
		case "34":
			return "发出红包";
		case "35":
			return "收到红包";
		case "36":
			return "红包退回";
		case "37":
			return "竞猜扣除";
		case "38":
			return "竞猜成功";
		case "39":
			return "充币转入";
		case "40":
			return "划转";
		case "41":
			return "购买道具";
		}
		return null;
	}

	private String getBidtContent(String type) {

		switch (type) {
		case "0":
			return "个人交易中心";
		case "1":
			return "家族交易中心";
		case "2":
			return "成就";
		case "3":
			return "每日任务";
		case "4":
			return "订单中心";
		case "5":
			return "TTC充值";
		case "6":
			return "人民币充值";
		case "7":
			return "蛋生的世界";
		case "8":
			return "提币到平台账户";
		case "9":
			return "个人商店";
		case "10":
			return "家族商店";
		case "11":
			return "解锁蛋坑";
		case "12":
			return "刷新家族商店";
		case "13":
			return "注册";
		case "14":
			return "创建家族";
		case "15":
			return "vi会员充值";
		case "16":
			return "活动奖励";
		case "17":
			return "活动提现";
		case "20":
			return "蛋生的世界分红奖励";
		case "21":
			return "蛋生提币到OTC";
		case "22":
			return "退款-MyOTC";
		case "25":
			return "抽奖";
		case "30":
			return "Luckycoin";
		case "31":
			return "Luckycoin奖金";
		case "32":
			return "转出";
		case "34":
			return "发出红包";
		case "35":
			return "收到红包";
		case "36":
			return "红包退回";
		case "37":
			return "竞猜扣除";
		case "38":
			return "竞猜成功";
		case "39":
			return "充币转入";
		case "40":
			return "划转";
		case "41":
			return "购买道具";
		}
		return null;
	}

	@Override
	public void updatePwd(Integer userId, String oldPwd, String newPwd) {

		List<UserInfo> userInfoList = androidMapper.getuserInfoById(userId);
		if (userInfoList != null && userInfoList.size() > 0) {
			UserInfo userInfo = userInfoList.get(0);
			boolean b = false;
			try {
				b = PushPasswordHash.validatePassword(oldPwd, userInfo.getUserPwd());
			} catch (Exception e) {
				throw ServiceException.userException("The verification code is wrong or expired!", "原密码不正确!");
			}
			if (!b) {
				throw ServiceException.userException("Password mistake!", "密码错误!");
			}

			try {
				userInfo.setUserPwd(PushPasswordHash.createHash(newPwd.trim()));
				int i = androidMapper.updateUserInfoPwd(userInfo);
				if (i < 0) {
					throw ServiceException.userException("The verification code is wrong or expired!", "原密码不正确!");
				}
			} catch (Exception e) {
				throw ServiceException.userException("Please log in again!", "信息查询失败!");
			}
		} else {
			throw ServiceException.userException("Please log in again!", "信息查询失败!");
		}
	}

	@Override
	public Object selectAddress(String blockId, Integer page, Integer row) {

		// List<UserInfo> userInfoList =
		// androidMapper.getUserInfoByBlockId(blockId);
		page = (page - 1) * row;
		List<UserAddress> userAddressList = androidMapper.selectAddress(blockId, page, row);
		List<UserInfo> userInfoByBlockId = androidMapper.getUserInfoByBlockId(blockId);
		CommonVo commonVo = new CommonVo();
		if (userInfoByBlockId != null && userInfoByBlockId.size() > 0) {
			commonVo.setParameter("1");
		} else {
			commonVo.setParameter("0");
		}
		List<UserAddressVo> vos = new ArrayList<>();
		if (userAddressList != null && userAddressList.size() > 0) {

			for (UserAddress userAddress : userAddressList) {
				UserAddressVo vo = new UserAddressVo();
				// vo.setId(userAddress.getuId());
				vo.setAddress(userAddress.getAddress());
				vo.setCurrency(userAddress.getCurrency());
				vos.add(vo);
			}
		}
		commonVo.setList(vos);
		return commonVo;
	}

	@Override
	public Object mobileVersionNumber(Integer userId, AppParameter appParameter, String phone) {
		if (phone.equals("android")) {
			String blockId = appParameter.getBlockId();
			try {
				if (blockId != null && !"".equals(blockId)) {
					List<AppUserMessage> appUserMessageList = androidMapper.getAppUserMessage(blockId);
					AppUserMessage appUserMessage = null;
					if (appUserMessageList != null && appUserMessageList.size() > 0) {
						appUserMessage = appUserMessageList.get(0);
					} else {
						appUserMessage = new AppUserMessage();
					}
					appUserMessage.setAddress(appParameter.getAddress());
					appUserMessage.setAppVersion(appParameter.getAppVersion());
					appUserMessage.setBlockId(appParameter.getBlockId());
					appUserMessage.setDitchId(appParameter.getDitchId());
					appUserMessage.setLatitude(appParameter.getLatitude());
					appUserMessage.setLongitude(appParameter.getLongitude());
					appUserMessage.setPhoneModel(appParameter.getPhoneModel());
					appUserMessage.setPhoneVersionNumber(appParameter.getPhoneVersionNumber());
					appUserMessage.setUserId(userId);
					appUserMessage.setType(phone);
					if (appUserMessage.getId() != null) {
						androidMapper.updateAppUserMessage(appUserMessage);
					} else {
						androidMapper.insertAppUserMessage(appUserMessage);
					}
				}
			} catch (Exception e) {

			}

			String ditchId = appParameter.getDitchId();
			String appVersion = appParameter.getAppVersion();
			int dev = appVersion.indexOf("_");
			String type = null;
			if (dev > 0) {
				type = "0";
			} else {
				type = "1";
			}
			if (userId != null) {
				String latitude = appParameter.getLatitude();
				String longitude = appParameter.getLongitude();
				if (latitude != null && longitude != null) {
					APPUserPreciseAddress appUserPreciseAddress = new APPUserPreciseAddress();
					appUserPreciseAddress.setLongitude(longitude);
					appUserPreciseAddress.setLatitude(latitude);
					appUserPreciseAddress.setUserId(userId + "");
					androidMapper.saveAPPUserPreciseAddress(appUserPreciseAddress);
				}

			}
			List<APPditchId> apPditchIdList = androidMapper.getAPPditchId(ditchId, type);
			AppParameterVo vo = new AppParameterVo();
			if (apPditchIdList != null && apPditchIdList.size() > 0) {
				APPditchId apPditchId = apPditchIdList.get(0);
				int status = apPditchId.getStatus();
				if (0 == status) {
					vo.setStatus(0);
					return vo;
				}
				String version = apPditchId.getVersion();

				if (!version.equals(appVersion)) {
					int updateStatus = apPditchId.getUpdateStatus();
					if (0 == updateStatus) {
						List<APPUpdateCondition> appUpdateConditionList = androidMapper.getAPPUpdateCondition(version);
						boolean isUserId = whetherUserId(userId + "", appUpdateConditionList);
						boolean isphoneModel = whetherPhoneModel(appParameter, appUpdateConditionList);
						boolean isphoneVersionNumber = whetherPhoneVersionNumber(appParameter, appUpdateConditionList);
						boolean isaddress = whetherAddress(appParameter, appUpdateConditionList);
						boolean isditchId = whetherDitchId(appParameter, appUpdateConditionList);
						boolean isblockId = whetherBlockId(appParameter, appUpdateConditionList);
						boolean isappVersion = whetherAppVersion(appParameter, appUpdateConditionList);
						if (!isUserId || !isphoneModel || !isphoneVersionNumber || !isaddress || !isditchId
								|| !isblockId || !isappVersion) {
							vo.setStatus(0);
							return vo;
						}
					}
					vo.setUrl(apPditchId.getUrl());
					vo.setTime(apPditchId.getCreateTime());
					List<APPUpdateContent> appUpdateContentList = androidMapper.getAPPUpdateContent(version);
					if (appUpdateContentList == null && appUpdateContentList.size() < 1) {
						vo.setStatus(0);
						return vo;
					}
					String minVersionNumber = apPditchId.getMinVersionNumber();
					String versionNumber = apPditchId.getVersionNumber();
					String replace = "";
					if (dev > 0) {

						int v = appVersion.indexOf("v");
						replace = appVersion.substring(v + 1, appVersion.length());
						int j = Integer.parseInt(minVersionNumber);// 最小
						int i1 = 0;
						if (!"".equals(replace)) {
							i1 = Integer.parseInt(replace);
						}
						if (i1 < j) {
							vo.setStatus(2);
						} else {
							vo.setStatus(1);
						}
						int i2 = Integer.parseInt(versionNumber);
						if (i2 < i1) {
							vo.setStatus(0);
						}

					} else {
						replace = appVersion.replace(".", "");
						int i = Integer.parseInt(minVersionNumber);// 最小
						int i1 = Integer.parseInt(replace);
						if (i1 < i) {
							vo.setStatus(2);
						} else {
							vo.setStatus(1);
						}
						int i2 = Integer.parseInt(versionNumber);
						if (i2 < i1) {
							vo.setStatus(0);
						}

					}
					vo.setAppVersion(apPditchId.getVersion());
					vo.setList(appUpdateContentList);
					return vo;
				}

			}
			vo.setStatus(0);
			return vo;
		} else {

			return null;
		}

	}

	private boolean whetherUserId(String userId, List<APPUpdateCondition> appUpdateConditionList) {
		// id
		boolean flag = false;
		int i = 0;
		for (APPUpdateCondition appUpdateCondition : appUpdateConditionList) {
			if ("1".equals(appUpdateCondition.getType())) {
				i = 1;
				if (appUpdateCondition.getCondition().equals(userId)) {
					i = 2;
					break;
				}
			}
		}
		if (i == 0) {
			flag = true;
		}
		if (i == 1) {
			flag = false;
		}
		if (i == 2) {
			flag = true;
		}
		return flag;
	}

	private boolean whetherPhoneModel(AppParameter appParameter, List<APPUpdateCondition> appUpdateConditionList) {
		// 手机型号
		boolean flag = false;
		int i = 0;
		for (APPUpdateCondition appUpdateCondition : appUpdateConditionList) {
			if ("1".equals(appUpdateCondition.getType())) {
				i = 1;
				String phoneModel = appParameter.getPhoneModel();
				if (phoneModel.equals(appUpdateCondition.getCondition())) {
					i = 2;
					break;
				}
			}
		}
		if (i == 0) {
			flag = true;
		}
		if (i == 1) {
			flag = false;
		}
		if (i == 2) {
			flag = true;
		}
		return flag;
	}

	private boolean whetherPhoneVersionNumber(AppParameter appParameter,
			List<APPUpdateCondition> appUpdateConditionList) {
		// 手机系统版本号
		boolean flag = false;
		int i = 0;
		for (APPUpdateCondition appUpdateCondition : appUpdateConditionList) {
			if ("2".equals(appUpdateCondition.getType())) {
				i = 1;
				String phoneVersionNumber = appParameter.getPhoneVersionNumber();
				if (phoneVersionNumber.equals(appUpdateCondition.getCondition())) {
					i = 2;
					break;
				}
			}
		}
		if (i == 0) {
			flag = true;
		}
		if (i == 1) {
			flag = false;
		}
		if (i == 2) {
			flag = true;
		}
		return flag;
	}

	private boolean whetherAddress(AppParameter appParameter, List<APPUpdateCondition> appUpdateConditionList) {
		// 地理位置
		boolean flag = false;
		int i = 0;
		for (APPUpdateCondition appUpdateCondition : appUpdateConditionList) {
			if ("3".equals(appUpdateCondition.getType())) {
				i = 1;
				String address = appParameter.getAddress();
				if (address.equals(appUpdateCondition.getCondition())) {
					i = 2;
					break;
				}
			}
		}
		if (i == 0) {
			flag = true;
		}
		if (i == 1) {
			flag = false;
		}
		if (i == 2) {
			flag = true;
		}
		return flag;
	}

	private boolean whetherDitchId(AppParameter appParameter, List<APPUpdateCondition> appUpdateConditionList) {
		// 渠道ID
		boolean flag = false;
		int i = 0;
		for (APPUpdateCondition appUpdateCondition : appUpdateConditionList) {
			if ("4".equals(appUpdateCondition.getType())) {
				i = 1;
				String ditchId = appParameter.getDitchId();
				if (ditchId.equals(appUpdateCondition.getCondition())) {
					i = 2;
					break;
				}
			}
		}
		if (i == 0) {
			flag = true;
		}
		if (i == 1) {
			flag = false;
		}
		if (i == 2) {
			flag = true;
		}
		return flag;
	}

	private boolean whetherBlockId(AppParameter appParameter, List<APPUpdateCondition> appUpdateConditionList) {
		// 区块身份id
		boolean flag = false;
		int i = 0;
		for (APPUpdateCondition appUpdateCondition : appUpdateConditionList) {
			if ("5".equals(appUpdateCondition.getType())) {
				i = 1;
				String blockId = appParameter.getBlockId();
				if (blockId.equals(appUpdateCondition.getCondition())) {
					i = 2;
					break;
				}
			}
		}
		if (i == 0) {
			flag = true;
		}
		if (i == 1) {
			flag = false;
		}
		if (i == 2) {
			flag = true;
		}
		return flag;
	}

	private boolean whetherAppVersion(AppParameter appParameter, List<APPUpdateCondition> appUpdateConditionList) {
		// app版本号
		boolean flag = false;
		int i = 0;
		for (APPUpdateCondition appUpdateCondition : appUpdateConditionList) {
			if ("6".equals(appUpdateCondition.getType())) {
				i = 1;
				String appVersion = appParameter.getAppVersion();
				if (appVersion.equals(appUpdateCondition.getCondition())) {
					i = 2;
					break;
				}
			}
		}
		if (i == 0) {
			flag = true;
		}
		if (i == 1) {
			flag = false;
		}
		if (i == 2) {
			flag = true;
		}
		return flag;
	}

	@Override
	public Object whetherKycOrFundPwd(Integer userId) {

		List<UserInfo> userInfoList = androidMapper.getuserInfoById(userId);
		if (userInfoList != null && userInfoList.size() > 0) {
			UserInfo userInfo = userInfoList.get(0);
			String identityCard = userInfo.getIdentityCard();
			List<AntiAddiction> antiAddictionList = androidMapper.getAntiAddiction(identityCard);
			if (antiAddictionList != null && antiAddictionList.size() > 0) {
				AntiAddiction antiAddiction = antiAddictionList.get(0);
				String level = antiAddiction.getLevel();
				if (!"2".equals(level)) {
					return 1;
				}
				String fundPwd = userInfo.getFundPwd();
				if ("".equals(fundPwd) || fundPwd == null) {
					return 2;
				} else {
					return 0;
				}
			} else {
				throw ServiceException.userException("Please complete the kyc secondary authentication!",
						"请先完成kyc二级身份认证!");
			}
		} else {
			throw ServiceException.userException("Please log in again!", "信息查询失败!");
		}
	}

	@Override
	public void withdrawDeposit(Integer userId, String address, String num, String remarks, String blockId) {

		List<UserInfo> userInfoList = androidMapper.getuserInfoById(userId);
		if (userInfoList != null && userInfoList.size() > 0) {
			UserInfo userInfo = userInfoList.get(0);
			String userAccount = userInfo.getUserAccount();
			if (!userAccount.equals(blockId)) {
				throw ServiceException.userException("Mention money failed", "提币失败");
			}
			Integer id = userInfo.getId();
			String s = "0";
			double v = Double.parseDouble(num);
			String content = null;
			try {
				if (v < 100) {
					throw ServiceException.userException("The number of deals at least 100 BIDT", "最少交易数量100BIDT");
				} else {
					double mul = PushRefinedCalculation.mul(v, 10);
					s = URLEncoder.encode(AES.AESEncode("9$dz4Y33oy", mul + ""), "UTF-8");
				}
				String encode1 = URLEncoder.encode(AES.AESEncode("LYWH@#$!32", id + ""), "UTF-8");
				long timeStamp = System.currentTimeMillis();
				String encode = URLEncoder.encode(AES.AESEncode("9$dz4Y33oy", "yobXT2ENRb" + timeStamp), "UTF-8");
				content = UrlLoad.load(IpResourceLocation.TT_EGGWORLD_IP + "/ttc-eggworld/ttc/record",
						"openId=" + encode1 + "&muchMoney=" + s
								+ "&transactionType=40&&app_id=3360a88e4318896c9fe3e031e64541f9c176af67fa87634f&mch_id=yobXT2ENRb&timestampStr="
								+ timeStamp + "&sign=" + encode + "&address=" + address + "&remarks=" + remarks);
			} catch (Exception e) {
				e.printStackTrace();
			}
			JSONObject jsonObject = JSON.parseObject(content);
			String string = jsonObject.getString("state");
			JSONObject jsonObject1 = JSON.parseObject(string);
			String code = jsonObject1.getString("code");
			if ("20002".equals(code)) {
				String msg = jsonObject1.getString("msg");
				throw ServiceException.userException("A system exception, contact customer service", msg);

			}
		}

	}

	@Override
	public void verifyFundPwd(Integer userId, String pwd) {

		List<UserInfo> userInfoList = androidMapper.getuserInfoById(userId);
		if (userInfoList != null && userInfoList.size() > 0) {
			UserInfo userInfo = userInfoList.get(0);
			String fundPwd = userInfo.getFundPwd();
			boolean b = false;
			try {
				b = PushPasswordHash.validatePassword(pwd, fundPwd);
			} catch (Exception e) {
				e.printStackTrace();
				throw ServiceException.userException("Password mistake!", "资产密码错误!");
			}
			if (!b) {
				throw ServiceException.userException("Password mistake!", "资产密码错误!");
			}

		} else {
			throw ServiceException.userException("Please log in again!", "信息查询失败!");
		}

	}

	@Override
	public Object getBidtWalletContent(Integer uId, String id) {

		String s = null;
		try {
			s = UrlLoad.load(IpResourceLocation.TT_EGGWORLD_IP + "/ttc-eggworld/ttc/getBillsInfo", "id=" + id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = JSON.parseObject(s);
		String string = jsonObject.getString("state");
		JSONObject jsonObject1 = JSON.parseObject(string);
		String code = jsonObject1.getString("code");
		if ("20000".equals(code)) {
			String data = jsonObject.getString("data");
			if (data != null) {
				JSONObject jsonObject2 = JSON.parseObject(data);
				String openId = jsonObject2.getString("openId");
				if (!((uId + "").equals(openId))) {
					throw ServiceException.userException("Please log in again!", "信息查询失败!");
				}
				BidtWalletVo vo = new BidtWalletVo();
				String muchMoney = jsonObject2.getString("muchMoney");
				double div = PushRefinedCalculation.div(10, Double.parseDouble(muchMoney));
				vo.setCoinNum(div + "");// 数量
				String transactionType = jsonObject2.getString("transactionType");
				String bidtContent = getBidtContent(transactionType);
				if ("17".equals(transactionType) || "21".equals(transactionType) || "40".equals(transactionType)) {
					vo.setTransactionStatus(jsonObject2.getString("transactionState"));
					vo.setReasonText(jsonObject2.getString("reasonText"));
				} else {
					vo.setTransactionStatus(null);
					vo.setReasonText(null);
				}
				vo.setContent(bidtContent);
				String regulation = getRegulation(transactionType);
				vo.setRegulation(regulation);
				vo.setId(jsonObject2.getString("id"));
				vo.setTime(jsonObject2.getString("createTime"));
				vo.setAddress(jsonObject2.getString("accountAddress"));
				return vo;
			} else {
				throw ServiceException.userException("Please log in again!", "信息查询失败!");
			}
		} else if ("20002".equals(code)) {
			String msg = jsonObject1.getString("msg");
			throw ServiceException.userException("A system exception, contact customer service", msg);
		} else {
			throw ServiceException.userException("Please log in again!", "信息查询失败!");
		}
	}

	@Override
	public Object getDitchId(String ditchId) {

		List<APPditchId> apPditchIdList = androidMapper.getAPPditchIdByDitchId(ditchId);
		APPditchId apPditchId = apPditchIdList.get(0);
		String version = apPditchId.getVersion();
		return version;
	}

	@Override
	public Object iosBugGather(String reason, String detail, String systemVerson) {

		return null;
	}

	@Override
	public Object getBlockIdAndAddress(String address) {

		if (address != null && !"".equals(address)) {
			int length = address.length();
			List<UserInfo> userInfoByBlockId = null;
			if ((length < 10)) {
				userInfoByBlockId = androidMapper.getUserInfoByBlockId(address);

			} else {
				userInfoByBlockId = androidMapper.getUserInfoByblockAddress(address);
			}
			if (userInfoByBlockId != null && userInfoByBlockId.size() > 0) {
				UserInfo userInfo = userInfoByBlockId.get(0);
				String blockAddress = userInfo.getBlockAddress();
				String userAccount = userInfo.getUserAccount();
				UserInfoVo vo = new UserInfoVo();
				vo.setBlockAddress(blockAddress);
				vo.setBlockId(userAccount);
				return vo;
			} else {
				throw ServiceException.userException("Please log in again!", "信息查询失败!");
			}

		} else {
			throw ServiceException.userException("Please log in again!", "信息查询失败!");
		}

	}

	@Override
	public void androidLoginNum(String blockId) {
		try {
			if (blockId != null && !"".equals(blockId)) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String format1 = format.format(new Date());
				List<AndroidLogin> androidLoginList = androidMapper.getAndroidLogin(blockId, format1);
				if (androidLoginList == null || androidLoginList.size() < 1) {
					AndroidLogin androidLogin = new AndroidLogin();
					androidLogin.setBlockId(blockId);
					androidLogin.setCreateTime(format1);
					androidMapper.saveAndroidLogin(androidLogin);
				}

			}

		} catch (Exception e) {
		}
	}

	@Override
	public void androidDownloadNum() {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String format1 = format.format(new Date());

			List<AndroidDownload> androidDownloadList = androidMapper.getAndroidDownload(format1);
			if (androidDownloadList != null && androidDownloadList.size() > 0) {
				AndroidDownload androidDownload = androidDownloadList.get(0);
				int downloadNum = androidDownload.getDownloadNum();
				downloadNum += 1;
				androidDownload.setDownloadNum(downloadNum);
				androidMapper.updateAndroidDownload(androidDownload);
			} else {
				AndroidDownload androidDownload = new AndroidDownload();
				androidDownload.setCreateTime(format1);
				androidDownload.setDownloadNum(1);
				androidMapper.saveAndroidDownload(androidDownload);
			}
		} catch (Exception e) {
		}
	}

	private boolean isphonenum(String account) {
		return account.startsWith("1") && account.length() == 11;
	}

	private void handleCellphones(String account, String type) throws InterruptedException {
		// 生成6位短信验证码
		String smsCode = SmsCodeGenerator.genSmsCode();
		while (isCodeExists(smsCode)) {
			smsCode = SmsCodeGenerator.genSmsCode();
		}
		createInstance(smsCode, account, type);
		SmsHolder holder = new SmsHolder(account, smsCode);
		SmsListenerBusPwd.REG_SMS_BUS.put(holder);
	}

	private boolean isCodeExists(String code) {
		if (StringUtils.isEmpty(code))
			return true;
		int i = androidMapper.getByCode(code);
		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}

	// 手机注册验证码
	private InvitationCode createInstance(String code, String account, String type) {
		InvitationCode ic = new InvitationCode();
		ic.setAccount(account);
		ic.setCode(code);
		ic.setExpire(TimeUnit.MINUTES, 30);
		ic.setType(Integer.parseInt(type));
		int i = androidMapper.saveInvitation_code(ic);
		if (i > 0) {
			return ic;
		} else {
			return null;
		}
	}

	private double getMoney(String appName, String userId, Integer id) {

		if ("1".equals(appName) || "0".equals(appName)) {

			String s = null;
			try {
				s = UrlLoad.loadGet(IpResourceLocation.CODE_IP + "/ttc-eggworld/ttc/getAssets?id=" + id);

			} catch (Exception e) {
				e.printStackTrace();
			}
			String game = "0";
			String sdk = "0";
			JSONObject jsonObject = JSON.parseObject(s);
			String string = jsonObject.getString("state");
			JSONObject jsonObject1 = JSON.parseObject(string);
			String code = jsonObject1.getString("code");
			if ("20000".equals(code)) {
				String data = jsonObject.getString("data");
				if (data == null) {
					game = "0";
					sdk = "0";

				} else {
					JSONObject jsonObject2 = JSON.parseObject(data);
					game = jsonObject2.getString("game");
					sdk = jsonObject2.getString("sdk");
				}
			} else if ("20002".equals(code)) {
				String msg = jsonObject1.getString("msg");
				throw ServiceException.userException("A system exception, contact customer service", msg);

			}
			String price = "0";
			if ("1".equals(appName)) {
				price = game;
			}
			if ("0".equals(appName)) {
				price = sdk;
			}
			double v = Double.parseDouble(price);
			if (v == 0) {
				return 0;
			} else {
				double div = PushRefinedCalculation.div(10, v);
				return div;

			}
		}

		return 0;
	}

	public String getCurrentTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format1 = format.format(new Date());
		return format1;
	}
}
