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
import com.liyunet.service.CoinHousekeeperService;

/**
 * Created by lkj on 2018/11/7. 竞猜接口
 */

@RestController
@RequestMapping("/api/bet")
public class BetController {
	@Autowired
	private BetService bs;

	// 查询所有的banner
	@HTTPPublicResource
	@RequestMapping(value = "/getBetBanner", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getBetBanner(HttpServletRequest request, HttpServletResponse response) {

		return bs.getBetBanner();
	}

	// 查询所有竞猜项目（首页）
	@HTTPPublicResource
	@RequestMapping(value = "/getBetList", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getBetList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) Integer type, @RequestParam(required = true) Integer page,
			@RequestParam(required = true) Integer rows) {

		return bs.getBetList(type, page, rows);
	}

	// 查询所有竞猜详情
	@HTTPPublicResource
	@RequestMapping(value = "/getBetDetails", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getBetDetails(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) Integer id) {

		return bs.getBetDetails(id);
	}

	// 查询所有竞猜详情登录
	@RequestMapping(value = "/getBetDetailsBytoken", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getBetDetailsBytoken(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) Integer id, PushTokenInfo pushTokenInfo) {
		Integer userId = pushTokenInfo.getUserId();
		return bs.getBetDetailsBytoken(id, userId);
	}

	// 查询所有竞猜记录登录
	@RequestMapping(value = "/getBetHistoryBytoken", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getBetHistoryBytoken(HttpServletRequest request, HttpServletResponse response,
			PushTokenInfo pushTokenInfo) {
		Integer userId = pushTokenInfo.getUserId();
		return bs.getBetHistoryBytoken(userId);
	}

	// 下注

	@RequestMapping(value = "/bet", method = { RequestMethod.GET, RequestMethod.POST })
	public Object bet(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) Integer oid, @RequestParam(required = true) Integer betnum,
			PushTokenInfo pushTokenInfo) {
		Integer userId = pushTokenInfo.getUserId();
		return bs.bet(oid, userId, betnum);
	}

	// 控制
	@HTTPPublicResource
	@RequestMapping(value = "/eggcontrol", method = { RequestMethod.GET, RequestMethod.POST })
	public Object eggcontrol(HttpServletRequest request, HttpServletResponse response) {
		return bs.eggcontrol();
	}

	// 发奖励
	@HTTPPublicResource
	@RequestMapping(value = "/setOption", method = { RequestMethod.GET, RequestMethod.POST })
	public Object setOption(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) Integer oid) {
		return bs.setOption(oid);
	}

}
