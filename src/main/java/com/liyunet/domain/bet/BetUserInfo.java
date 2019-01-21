package com.liyunet.domain.bet;

public class BetUserInfo {
	private Integer id;

	private Integer userid;

	private String notes;

	private String bidt;

	private String createtime;

	private Integer boid;
	// 判断是否猜对（0为开奖 1猜对 2猜错）

	private Integer status;

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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes == null ? null : notes.trim();
	}

	public String getBidt() {
		return bidt;
	}

	public void setBidt(String bidt) {
		this.bidt = bidt == null ? null : bidt.trim();
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime == null ? null : createtime.trim();
	}

	public Integer getBoid() {
		return boid;
	}

	public void setBoid(Integer boid) {
		this.boid = boid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}