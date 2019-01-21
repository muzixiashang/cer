package com.liyunet.vo.user;

//用户信息表vo
public class PushUserInfoVo {

	private Integer id;
	private String user_name;
	private String nickName;
	private String phoneNum;
	private String email;
	private String userHeadPicPath;
	private String createTime;
	private String token;
	private String userSexStr;
	private String userCode;
	private String promotionCode;
	private Integer promotionPeopleNum;
  	private int isAuthentication;//是否手机认证 0未认证 1已认证
  	private Integer accountBalance=0;//账户余额
  	private String accountBalanceStr="0";//账户余额
	//用户类型 1普通用户，2商家
	private String userType;
	//是否设置资金密码
	private boolean isPwdFund;
	//用户状态 1正常 2暂停 3删除
	private int userStatus;
	//用户性别 0男 1女
	private Integer userSex;
	//所在地区
	private String area;
	//用户真实姓名
	private String realName;
	//用户个性签名
	private String userDesc;
	//用户钱包地址
	private String eth;
	private String TTCsite;
	//比特币地址
	private String bitcoinPath;

	public String getUsername() {
		return user_name;
	}
	public String getNickName() {
		return nickName;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public String getEmail() {
		return email;
	}
	public String getUserHeadPicPath() {
		return userHeadPicPath;
	}
	public String getCreateTime() {
		return createTime;
	}
	public int getUserStatus() {
		return userStatus;
	}
	public Integer getUserSex() {
		return userSex;
	}
	public String getArea() {
		return area;
	}
	public String getRealName() {
		return realName;
	}
	public String getUserDesc() {
		return userDesc;
	}
	public String getEth() {
		return eth;
	}
	public void setUsername(String username) {
		this.user_name = username;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setUserHeadPicPath(String userHeadPicPath) {
		this.userHeadPicPath = userHeadPicPath;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}
	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}
	public void setEth(String eth) {
		this.eth = eth;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUserSexStr() {
		return userSexStr;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserSexStr(String userSexStr) {
		this.userSexStr = userSexStr;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getBitcoinPath() {
		return bitcoinPath;
	}
	public void setBitcoinPath(String bitcoinPath) {
		this.bitcoinPath = bitcoinPath;
	}
	public String getPromotionCode() {
		return promotionCode;
	}
	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}
	public Integer getPromotionPeopleNum() {
		return promotionPeopleNum;
	}
	public void setPromotionPeopleNum(Integer promotionPeopleNum) {
		this.promotionPeopleNum = promotionPeopleNum;
	}
	public int getIsAuthentication() {
		return isAuthentication;
	}
	public void setIsAuthentication(int isAuthentication) {
		this.isAuthentication = isAuthentication;
	}
	public Integer getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(Integer accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String getAccountBalanceStr() {
		return accountBalanceStr;
	}
	public void setAccountBalanceStr(String accountBalanceStr) {
		this.accountBalanceStr = accountBalanceStr;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public boolean isPwdFund() {
		return isPwdFund;
	}

	public void setPwdFund(boolean pwdFund) {
		isPwdFund = pwdFund;
	}

	public String getTTCsite() {
		return TTCsite;
	}

	public void setTTCsite(String TTCsite) {
		this.TTCsite = TTCsite;
	}
}
