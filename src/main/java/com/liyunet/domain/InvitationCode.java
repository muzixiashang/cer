package com.liyunet.domain;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * 验证码实体类
 */

public class InvitationCode {

	private Integer id;
	private String userId;
	//验证码1绑定手机验证，0邮箱注册验证,3修改邮箱，4注册靓号，5设置资金密码
	private String code_;
	
	//创建日期
	private String create_time = LocalDateTime.now().toString();
	
	//过期时间
	private String expire_time;
	
	private boolean used_;
	//发送账号	
	private String account_;
	
	private Integer type_;
	
	private String use_time;
	
	public void setExpire(TimeUnit unit,long duration){
		LocalDateTime create = LocalDateTime.parse(create_time);
		long nanos = unit.toNanos(duration);
		LocalDateTime expireTime = create.plusNanos(nanos);
		this.expire_time = expireTime.toString();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUseTime() {
		return use_time;
	}


	public void setUseTime(String useTime) {
		this.use_time = useTime;
	}


	public Integer getType() {
		return type_;
	}
	public void setType(Integer type) {
		this.type_ = type;
	}
	public String getAccount() {
		return account_;
	}
	public void setAccount(String account) {
		this.account_ = account;
	}
	public String getCreateTime() {
		return create_time;
	}
	public void setCreateTime(String createTime) {
		this.create_time = createTime;
	}
	public String getCode() {
		return code_;
	}
	public void setCode(String code) {
		this.code_ = code;
	}
	public String getExpireTime() {
		return expire_time;
	}
	public void setExpireTime(String expireTime) {
		this.expire_time = expireTime;
	}
	public boolean isUsed() {
		return used_;
	}
	public void setUsed(boolean used) {
		this.used_ = used;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
