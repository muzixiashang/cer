package com.liyunet.domain.charge;

public class ChargeOrderVo {
	private Integer id;
	private String createtime;
	private String coinType;
	private String bidtnum;
	private Integer state;
	private String  ression;
	
	private String homeimage;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getHomeimage() {
		return homeimage;
	}
	public void setHomeimage(String homeimage) {
		this.homeimage = homeimage;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getCoinType() {
		return coinType;
	}
	public void setCoinType(String coinType) {
		this.coinType = coinType;
	}
	public String getBidtnum() {
		return bidtnum;
	}
	public void setBidtnum(String bidtnum) {
		this.bidtnum = bidtnum;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getRession() {
		return ression;
	}
	public void setRession(String ression) {
		this.ression = ression;
	}

}
