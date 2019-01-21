package com.liyunet.domain.bet.vo;

import java.util.List;

public class BetInfoVo {
	private int id;
	private String title;
	private String context;
	private String bannerUrl;
	private String createtime;
	private String endtime;
	private String perpelenum;
	private String bidtnum;
	private BetOptionVo betOptionVo;
	// 最终结果
	private String result;
	//最终倍数
	private String multiple;
	// 选项
	private List<BetOptionVo> list;
	// 选项类型 1 是两个选项 2是四个选项
	private int optionType;
	// 结束状态 0:未结束 1是等待结果中 2是已结束
	
	private int status;

	
	public String getMultiple() {
		return multiple;
	}

	public void setMultiple(String multiple) {
		this.multiple = multiple;
	}

	public BetOptionVo getBetOptionVo() {
		return betOptionVo;
	}

	public void setBetOptionVo(BetOptionVo betOptionVo) {
		this.betOptionVo = betOptionVo;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getBannerUrl() {
		return bannerUrl;
	}

	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getPerpelenum() {
		return perpelenum;
	}

	public void setPerpelenum(String perpelenum) {
		this.perpelenum = perpelenum;
	}

	public String getBidtnum() {
		return bidtnum;
	}

	public void setBidtnum(String bidtnum) {
		this.bidtnum = bidtnum;
	}

	public List<BetOptionVo> getList() {
		return list;
	}

	public void setList(List<BetOptionVo> list) {
		this.list = list;
	}

	public int getOptionType() {
		return optionType;
	}

	public void setOptionType(int optionType) {
		this.optionType = optionType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
