package com.liyunet.domain.hk;

/**
 * 币管家投资项目表
 * 
 * @author Administrator
 *
 */
public class CoinHkProjet {
	private Integer id;
	// 项目名
	private String projectName;
	// 项目周期
	private String projectCycle;
	// 开始时间
	private String startTime;
	// 结束时间
	private String endTime;
	// 收益率
	private String profit;
	// 最小限额
	private String minQuota;
	// 最大限额
	private String maxQuota;
	// 收益数量
	private String profitNum;
	// 上下架状态 0下架 1上架
	private Integer state;
	// 开启状态 1已开启，2未开启，3已结束，0看时间
	private Integer status;
	// 投资数量 
	private String substitutesone;
	// 文案
	private String substitutestwo;
	// 人数
	private Integer dummyNum;
	// 顺序
	private Integer sort;
	// 创建时间
	private String createtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName == null ? null : projectName.trim();
	}

	public String getProjectCycle() {
		return projectCycle;
	}

	public void setProjectCycle(String projectCycle) {
		this.projectCycle = projectCycle == null ? null : projectCycle.trim();
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime == null ? null : startTime.trim();
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime == null ? null : endTime.trim();
	}

	public String getProfit() {
		return profit;
	}

	public void setProfit(String profit) {
		this.profit = profit == null ? null : profit.trim();
	}

	public String getMinQuota() {
		return minQuota;
	}

	public void setMinQuota(String minQuota) {
		this.minQuota = minQuota == null ? null : minQuota.trim();
	}

	public String getMaxQuota() {
		return maxQuota;
	}

	public void setMaxQuota(String maxQuota) {
		this.maxQuota = maxQuota == null ? null : maxQuota.trim();
	}

	public String getProfitNum() {
		return profitNum;
	}

	public void setProfitNum(String profitNum) {
		this.profitNum = profitNum == null ? null : profitNum.trim();
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSubstitutesone() {
		return substitutesone;
	}

	public void setSubstitutesone(String substitutesone) {
		this.substitutesone = substitutesone == null ? null : substitutesone.trim();
	}

	public String getSubstitutestwo() {
		return substitutestwo;
	}

	public void setSubstitutestwo(String substitutestwo) {
		this.substitutestwo = substitutestwo == null ? null : substitutestwo.trim();
	}

	public Integer getDummyNum() {
		return dummyNum;
	}

	public void setDummyNum(Integer dummyNum) {
		this.dummyNum = dummyNum;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime == null ? null : createtime.trim();
	}
}