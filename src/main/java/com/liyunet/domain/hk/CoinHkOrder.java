package com.liyunet.domain.hk;

/**
 * 币管家订单表
 * 
 * @author Administrator
 *
 */
public class CoinHkOrder {
	private Integer id;
	// 用户id
	private Integer userid;
	// 付款数量
	private String payNum;
	// 创建时间
	private String createtime;
	// 订单号
	private String billNo;
	// 付款类型(1，币达钱包支付 2，token钱包支付)
	private Integer type;
	// token钱包地址
	private String tokenAddress;
	// 系统
	private Integer toblockid;
	// 审核状态 1通过 2审核中 4驳回
	private Integer state;
	// 驳回理由
	private String reason;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getPayNum() {
		return payNum;
	}

	public void setPayNum(String payNum) {
		this.payNum = payNum == null ? null : payNum.trim();
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime == null ? null : createtime.trim();
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo == null ? null : billNo.trim();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTokenAddress() {
		return tokenAddress;
	}

	public void setTokenAddress(String tokenAddress) {
		this.tokenAddress = tokenAddress == null ? null : tokenAddress.trim();
	}

	public Integer getToblockid() {
		return toblockid;
	}

	public void setToblockid(Integer toblockid) {
		this.toblockid = toblockid;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason == null ? null : reason.trim();
	}
}