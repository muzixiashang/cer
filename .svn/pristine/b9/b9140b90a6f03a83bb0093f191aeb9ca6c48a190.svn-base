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
import com.liyunet.service.CoinHousekeeperService;
import com.liyunet.service.LuckBagService;

/**
 * Created by lkj on 2018/11/7. 红包接口
 */

@RestController
@RequestMapping("/api/luckbag")
public class LuckBagController {
	@Autowired
	private LuckBagService luckBagService;

	// 发红包
	@RequestMapping(value = "/handLuckBag", method = { RequestMethod.GET, RequestMethod.POST })
	public Object handLuckBag(HttpServletRequest request, HttpServletResponse response, PushTokenInfo pushTokenInfo,
			@RequestParam(required = false) String bidtnum, @RequestParam(required = false) String bagnum,
			@RequestParam(required = true) Integer lbid, @RequestParam(required = false) String money,
			@RequestParam(required = true) Integer type) {
		Integer userId = pushTokenInfo.getUserId();
		return luckBagService.handLuckBag(userId, bidtnum, bagnum, lbid, money, type);
	}

	// 查找祝福语
	@HTTPPublicResource
	@RequestMapping(value = "/getLuckbagBlessList", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getLuckbagBlessList(HttpServletRequest request, HttpServletResponse response) {
		return luckBagService.getLuckbagBlessList();
	}

	// 根据订号查找区块id
	@HTTPPublicResource
	@RequestMapping(value = "/getBlockIdByorderId", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getBlockIdByorderId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String orderId) {
		return luckBagService.getBlockIdByorderId(orderId);
	}

	// 抢红包
	@RequestMapping(value = "/getLuckBag", method = { RequestMethod.GET, RequestMethod.POST })
	public synchronized Object getLuckBag(HttpServletRequest request, HttpServletResponse response,
			PushTokenInfo pushTokenInfo, @RequestParam(required = true) Integer id) {
		Integer userId = pushTokenInfo.getUserId();
		return luckBagService.getLuckBag(userId, id);
	}

	// 查红包详情
	@RequestMapping(value = "/getLuckBagInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public synchronized Object getLuckBagInfo(HttpServletRequest request, HttpServletResponse response,
			PushTokenInfo pushTokenInfo, @RequestParam(required = true) Integer id) {
		Integer userId = pushTokenInfo.getUserId();
		return luckBagService.getLuckBagInfo(userId, id);
	}

	// 查红包详情
	@HTTPPublicResource
	@RequestMapping(value = "/getLuckBagInfoNotoken", method = { RequestMethod.GET, RequestMethod.POST })
	public synchronized Object getLuckBagInfoNotoken(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) Integer id) {
		return luckBagService.getLuckBagInfoNotoken(id);
	}

	// 输入区块id获取token
	// getUserInfoByUserAccount
	@HTTPPublicResource
	@RequestMapping(value = "/getTokenaByuseraccount", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getTokenaByuseraccount(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String userAccount) {
		return luckBagService.getTokenaByuseraccount(request, userAccount);
	}

	// 查发红包记录
	@RequestMapping(value = "/getsendLuckBagHistory", method = { RequestMethod.GET, RequestMethod.POST })
	public synchronized Object getsendLuckBagHistory(HttpServletRequest request, HttpServletResponse response,
			PushTokenInfo pushTokenInfo, @RequestParam(required = true) Integer page,
			@RequestParam(required = true) Integer rows) {
		Integer userId = pushTokenInfo.getUserId();
		return luckBagService.getsendLuckBagHistory(userId, page, rows);
	}

	// 查收红包记录
	@RequestMapping(value = "/getaccepLuckBagHistory", method = { RequestMethod.GET, RequestMethod.POST })
	public synchronized Object getaccepLuckBagHistory(HttpServletRequest request, HttpServletResponse response,
			PushTokenInfo pushTokenInfo, @RequestParam(required = true) Integer page,
			@RequestParam(required = true) Integer rows) {
		Integer userId = pushTokenInfo.getUserId();
		return luckBagService.getaccepLuckBagHistory(userId, page, rows);
	}

	// 红包退回
	@HTTPPublicResource
	@RequestMapping(value = "/returnLuckbag", method = { RequestMethod.GET, RequestMethod.POST })
	public Object returnLuckbag(HttpServletRequest request, HttpServletResponse response) {
		return luckBagService.returnLuckbag();
	}

}
