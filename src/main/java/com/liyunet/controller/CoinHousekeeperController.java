package com.liyunet.controller;

import com.liyunet.common.pushToken.PushTokenInfo;
import com.liyunet.domain.AppUserMessage;
import com.liyunet.domain.GameMessage;
import com.liyunet.interceptor.anno.HTTPPublicResource;
import com.liyunet.interceptor.anno.HTTPPublicToken;
import com.liyunet.service.CoinHousekeeperService;
import com.liyunet.service.GameApiService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lkj on 2018/11/7. 币管家接口
 */

@RestController
@RequestMapping("/api/coinhousekeeper")
public class CoinHousekeeperController {
	@Autowired
	private CoinHousekeeperService coinHousekeeperService;

	// 获取资产

	// 查询所有投资项目
	@HTTPPublicResource
	@RequestMapping(value = "/getHKlist", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getLuckyUserContent(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) Integer page, @RequestParam(required = true) Integer rows,
			@RequestParam(required = true, defaultValue = "zh") String languageType) {

		return coinHousekeeperService.getHKlist(page, rows);
	}

	// 查个人收益
	@RequestMapping(value = "/getProfitBytoken", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getProfitBytoken(HttpServletRequest request, HttpServletResponse response,
			PushTokenInfo pushTokenInfo) {
		Integer userId = pushTokenInfo.getUserId();
		return coinHousekeeperService.getProfitBytoken(userId);
	}
	//

	// 根据id查项目
	@HTTPPublicResource
	@RequestMapping(value = "/getHKbyId", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getHKbyId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String id,
			@RequestParam(required = true, defaultValue = "zh") String languageType) {
		int parseInt = Integer.parseInt(id);
		return coinHousekeeperService.getHKbyId(parseInt);
	}

	// 支付
	@RequestMapping(value = "/hkpay", method = { RequestMethod.GET, RequestMethod.POST })
	public Object hkpay(HttpServletRequest request, HttpServletResponse response, PushTokenInfo pushTokenInfo,
			@RequestParam(required = true) Integer pid, @RequestParam(required = true) String num,
			@RequestParam(required = true) Integer type, @RequestParam(required = true) String address) {
		Integer userId = pushTokenInfo.getUserId();
		return coinHousekeeperService.hkpay(userId, pid, num, type, address);
	}

	// 获取总资产
	@RequestMapping(value = "/getHkcount", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getHkcount(HttpServletRequest request, HttpServletResponse response, PushTokenInfo pushTokenInfo) {
		Integer userId = pushTokenInfo.getUserId();
		return coinHousekeeperService.getHkcount(userId);
	}

	// 查询所有投资项目人数
	@HTTPPublicResource
	@RequestMapping(value = "/getHKpeoplenum", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getHKpeoplenum(HttpServletRequest request, HttpServletResponse response) {

		return coinHousekeeperService.getHKpeoplenum();
	}

	// 查询投资记录
	@RequestMapping(value = "/getHKpayinfo", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getHKpayinfo(HttpServletRequest request, HttpServletResponse response, PushTokenInfo pushTokenInfo
			,@RequestParam(required = true) Integer page, @RequestParam(required = true) Integer rows) {
		Integer userId = pushTokenInfo.getUserId();
		return coinHousekeeperService.getHKpayinfo(userId,page,rows);
	}

	// 查询投资记录详情
	@HTTPPublicResource
	@RequestMapping(value = "/getHKpaydetailsById", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getHKpaydetailsById(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) Integer id) {
		return coinHousekeeperService.getHKpaydetailsById(id);
	}
	//判断用是否已购买
	@RequestMapping(value = "/getUserPaystate", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getUserPaystate(HttpServletRequest request, HttpServletResponse response, PushTokenInfo pushTokenInfo
			,@RequestParam(required = true) Integer pid) {
		Integer userId = pushTokenInfo.getUserId();
		return coinHousekeeperService.getUserPaystate(userId,pid);
	}
	//判断用户是否投资
	@RequestMapping(value = "/getUserPayinfostate", method = { RequestMethod.GET, RequestMethod.POST })
	public Object getUserPayinfostate(HttpServletRequest request, HttpServletResponse response, PushTokenInfo pushTokenInfo
			) {
		Integer userId = pushTokenInfo.getUserId();
		return coinHousekeeperService.getUserPayinfostate(userId);
	}
	
	
	//根据项目id获取合约地址
	// 查询投资记录详情
		@HTTPPublicResource
		@RequestMapping(value = "/getContractAdress", method = { RequestMethod.GET, RequestMethod.POST })
		public Object getContractAdress(HttpServletRequest request, HttpServletResponse response,
				@RequestParam(required = true) Integer id) {
			return coinHousekeeperService.getContractAdress(id);
		}
	

}
