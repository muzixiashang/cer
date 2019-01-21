package com.liyunet.domain;

import com.liyunet.common.auth.PlatformAuth;
import org.apache.commons.lang3.StringUtils;


//用户信息表
public class BlockUserInfo {
	private static final long serialVersionUID = -4097584987436354096L;

	private Integer id;
	private String uId;
	private String user_name;
	private String pwd_;
	/**
	 * 昵称
	 */
	private String nick_name;
	private String phone_num;
	private String email_;

	/**
	 * 头像
	 */
	private String head_pic_path;
	private String create_time;
	/**
	 * 用户注册类型,是自己注册还是邀请注册
	 */

	//用户性别 0男 1女
	private Integer user_sex;
	//注册类型:0手机号注册 1邮箱
	private Integer regester_type;
	//用户个性签名
	private String user_desc;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getUsername() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPwd() {
		return pwd_;
	}

	public void setPwd_(String pwd_) {
		this.pwd_ = pwd_;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getPhoneNum() {
		return phone_num;
	}

	public void setPhoneNum(String phone_num) {
		this.phone_num = phone_num;
	}

	public String getEmail_() {
		return email_;
	}

	public void setEmail_(String email_) {
		this.email_ = email_;
	}

	public String getHeadPicPath() {
		return head_pic_path;
	}

	public void setHead_pic_path(String head_pic_path) {
		this.head_pic_path = head_pic_path;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public Integer getUserSex() {
		return user_sex;
	}

	public void setUser_sex(Integer user_sex) {
		this.user_sex = user_sex;
	}

	public Integer getRegester_type() {
		return regester_type;
	}

	public void setRegester_type(Integer regester_type) {
		this.regester_type = regester_type;
	}

	public String getUserDesc() {
		return user_desc;
	}

	public void setUser_desc(String user_desc) {
		this.user_desc = user_desc;
	}

	public BlockUserInfo() {
		super();
	}
	public BlockUserInfo(Integer id) {
		super();
		this.id=id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email_ == null) ? 0 : email_.hashCode());
		result = prime * result + ((nick_name == null) ? 0 : nick_name.hashCode());
		result = prime * result + ((phone_num == null) ? 0 : phone_num.hashCode());
		result = prime * result + ((pwd_ == null) ? 0 : pwd_.hashCode());
		result = prime * result + ((head_pic_path == null) ? 0 : head_pic_path.hashCode());
		result = prime * result + ((user_name == null) ? 0 : user_name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserInfo other = (UserInfo) obj;
		return this.getId().equals(other.getId());
	}

}
