package com.liyunet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liyunet.common.pushToken.PushTokenInfo;
import com.liyunet.interceptor.anno.HTTPPublicResource;
import com.liyunet.service.BetService;
import com.liyunet.service.ChargeService;
import com.liyunet.service.CoinHousekeeperService;

/**
 * 
 * @author Administrator 充币接口
 *
 */

@RestController
@RequestMapping("/api/charge")
public class ChargeController {
	@Autowired
	private ChargeService cs;

	// 获取币种列表
	@HTTPPublicResource
	@RequestMapping(value = "/getCoinList", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getBetBanner(HttpServletRequest request, HttpServletResponse response,@RequestParam(required = true) Integer type) {
		return cs.getCoinList(type);
	}

	// 判断用户是否绑定地址
	@RequestMapping(value = "/checkChargeState", method = { RequestMethod.GET, RequestMethod.POST })
	public Object checkChargeState(HttpServletRequest request, HttpServletResponse response,
			PushTokenInfo pushTokenInfo, @RequestParam(required = true) Integer cid) {
		Integer userId = pushTokenInfo.getUserId();
		return cs.checkChargeState(userId, cid);
	}

	// 绑定地址
	@RequestMapping(value = "/bindingAddress", method = { RequestMethod.GET, RequestMethod.POST })
	public Object bindingAddress(HttpServletRequest request, HttpServletResponse response, PushTokenInfo pushTokenInfo,
			@RequestParam(required = true) Integer cid, @RequestParam(required = true) String address) {
		Integer userId = pushTokenInfo.getUserId();
		return cs.bindingAddress(userId, cid, address);
	}

	// 修改绑定地址
	@HTTPPublicResource
	@RequestMapping(value = "/updateAddress", method = { RequestMethod.GET, RequestMethod.POST })
	public Object updateAddress(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) Integer id, @RequestParam(required = true) String address) {
		return cs.updateAddress(id, address);
	}

	// 获取币种详情
	@RequestMapping(value = "/getCoinInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getCoinInfo(HttpServletRequest request, HttpServletResponse response, PushTokenInfo pushTokenInfo,
			@RequestParam(required = true) Integer cid,@RequestParam(required = true) Integer type) {
		Integer userId = pushTokenInfo.getUserId();
		return cs.getCoinInfo(userId, cid,type);
	}

	// 充值
	@RequestMapping(value = "/charge", method = { RequestMethod.GET, RequestMethod.POST })
	public Object charge(HttpServletRequest request, HttpServletResponse response, PushTokenInfo pushTokenInfo,
			@RequestParam(required = true) Integer cid, @RequestParam(required = true) String bidtnum) {
		Integer userId = pushTokenInfo.getUserId();
		return cs.charge(userId, cid, bidtnum);
	}

	// 充值详情
	@HTTPPublicResource
	@RequestMapping(value = "/chargeInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public Object chargeInfo(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) Integer id) {
		return cs.chargeInfo(id);
	}

	// 充值记录
	@RequestMapping(value = "/getChargeHistory", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getChargeHistory(HttpServletRequest request, HttpServletResponse response,
			PushTokenInfo pushTokenInfo) {
		Integer userId = pushTokenInfo.getUserId();
		return cs.getChargeHistory(userId);
	}

	// 充币接口
	@HTTPPublicResource
	@RequestMapping(value = "/addCharge", method = { RequestMethod.GET, RequestMethod.POST })
	public Object addCharge(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) Integer id,@RequestParam(required = true) Integer updateState,@RequestParam(required = true) String ression) {
		return cs.addCharge(id,updateState,ression);
	}
	
	// 账单
		@RequestMapping(value = "/getBill", method = { RequestMethod.GET, RequestMethod.POST })
		public Object getBill(HttpServletRequest request, HttpServletResponse response,
				PushTokenInfo pushTokenInfo) {
			Integer userId = pushTokenInfo.getUserId();
			return cs.getBill(userId);
		}
	

}
