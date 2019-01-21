package com.liyunet.domain.gameapi;
/**
 * 游戏订单表
 * @author Administrator
 *
 */
public class GameApiDemandOrder {
    private Integer id;
    /**
	 * 创建时间
	 */
    private String createTime;
    /**
	 * 预定单号.（7位.1000001）
	 */
    private Integer demandNum;
    /**
	 * 游戏方订单号.
	 */
    private String gameOrderNum;

    private Integer integral;

    private Integer objId;
    /**
	 * 订单来源；0:鲤鱼游戏app;  1:sdk客户端;  2:H5 SDK  3:h5wap站     10:其他
	 */
    private Integer orderSource;
    /**
	 * 支付方订单号.
	 */
    private String payOrderNum;
    /**
	 * 购买产品币达
	 */
    private String payTotalMoney;
	/**
	 * 订单支付方式:0：支付宝;1：微信;2:银联 3:手机话费 4:其他 5,币达
	 */
    private Integer payType;
    /**
	 * 购买产品名称.
	 */
    private String productName;
    /**
	 * 购买产品人民币
	 */
    private String productPrice;

    private Integer resultType;

    private String returnUrl;
    /**
	 * 是否已经推送给游戏方
	 * 1已推送  0未推送
	 */
    private Integer sendMsgToGamecompany;
    /**
	 * 购买产品数量
	 */
    private Integer shoppingNum;
    /**
	 * 订单状态: 0未支付  1支付成功 2支付未成功 3已退款 4交易意外
	 */
    private Integer status;

    private String syncTime;

    private String tradCode;

    private String tradMsg;

    private String tradNo;
    /**
	 * 所属游戏
	 */
    private Integer gameId;
    /**
	 * 下单人
	 */
    private Integer userId;
    /**
	 * 购买产品描述.
	 */
    private String productDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public Integer getDemandNum() {
        return demandNum;
    }

    public void setDemandNum(Integer demandNum) {
        this.demandNum = demandNum;
    }

    public String getGameOrderNum() {
        return gameOrderNum;
    }

    public void setGameOrderNum(String gameOrderNum) {
        this.gameOrderNum = gameOrderNum == null ? null : gameOrderNum.trim();
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getObjId() {
        return objId;
    }

    public void setObjId(Integer objId) {
        this.objId = objId;
    }

    public Integer getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(Integer orderSource) {
        this.orderSource = orderSource;
    }

    public String getPayOrderNum() {
        return payOrderNum;
    }

    public void setPayOrderNum(String payOrderNum) {
        this.payOrderNum = payOrderNum == null ? null : payOrderNum.trim();
    }

    public String getPayTotalMoney() {
        return payTotalMoney;
    }

    public void setPayTotalMoney(String payTotalMoney) {
        this.payTotalMoney = payTotalMoney == null ? null : payTotalMoney.trim();
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice == null ? null : productPrice.trim();
    }

    public Integer getResultType() {
        return resultType;
    }

    public void setResultType(Integer resultType) {
        this.resultType = resultType;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl == null ? null : returnUrl.trim();
    }

    public Integer getSendMsgToGamecompany() {
        return sendMsgToGamecompany;
    }

    public void setSendMsgToGamecompany(Integer sendMsgToGamecompany) {
        this.sendMsgToGamecompany = sendMsgToGamecompany;
    }

    public Integer getShoppingNum() {
        return shoppingNum;
    }

    public void setShoppingNum(Integer shoppingNum) {
        this.shoppingNum = shoppingNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSyncTime() {
        return syncTime;
    }

    public void setSyncTime(String syncTime) {
        this.syncTime = syncTime == null ? null : syncTime.trim();
    }

    public String getTradCode() {
        return tradCode;
    }

    public void setTradCode(String tradCode) {
        this.tradCode = tradCode == null ? null : tradCode.trim();
    }

    public String getTradMsg() {
        return tradMsg;
    }

    public void setTradMsg(String tradMsg) {
        this.tradMsg = tradMsg == null ? null : tradMsg.trim();
    }

    public String getTradNo() {
        return tradNo;
    }

    public void setTradNo(String tradNo) {
        this.tradNo = tradNo == null ? null : tradNo.trim();
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc == null ? null : productDesc.trim();
    }
}