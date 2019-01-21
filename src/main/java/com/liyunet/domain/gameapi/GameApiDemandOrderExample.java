package com.liyunet.domain.gameapi;

import java.util.ArrayList;
import java.util.List;

public class GameApiDemandOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GameApiDemandOrderExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(String value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(String value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(String value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(String value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(String value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLike(String value) {
            addCriterion("create_time like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotLike(String value) {
            addCriterion("create_time not like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<String> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<String> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(String value1, String value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(String value1, String value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andDemandNumIsNull() {
            addCriterion("demand_num is null");
            return (Criteria) this;
        }

        public Criteria andDemandNumIsNotNull() {
            addCriterion("demand_num is not null");
            return (Criteria) this;
        }

        public Criteria andDemandNumEqualTo(Integer value) {
            addCriterion("demand_num =", value, "demandNum");
            return (Criteria) this;
        }

        public Criteria andDemandNumNotEqualTo(Integer value) {
            addCriterion("demand_num <>", value, "demandNum");
            return (Criteria) this;
        }

        public Criteria andDemandNumGreaterThan(Integer value) {
            addCriterion("demand_num >", value, "demandNum");
            return (Criteria) this;
        }

        public Criteria andDemandNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("demand_num >=", value, "demandNum");
            return (Criteria) this;
        }

        public Criteria andDemandNumLessThan(Integer value) {
            addCriterion("demand_num <", value, "demandNum");
            return (Criteria) this;
        }

        public Criteria andDemandNumLessThanOrEqualTo(Integer value) {
            addCriterion("demand_num <=", value, "demandNum");
            return (Criteria) this;
        }

        public Criteria andDemandNumIn(List<Integer> values) {
            addCriterion("demand_num in", values, "demandNum");
            return (Criteria) this;
        }

        public Criteria andDemandNumNotIn(List<Integer> values) {
            addCriterion("demand_num not in", values, "demandNum");
            return (Criteria) this;
        }

        public Criteria andDemandNumBetween(Integer value1, Integer value2) {
            addCriterion("demand_num between", value1, value2, "demandNum");
            return (Criteria) this;
        }

        public Criteria andDemandNumNotBetween(Integer value1, Integer value2) {
            addCriterion("demand_num not between", value1, value2, "demandNum");
            return (Criteria) this;
        }

        public Criteria andGameOrderNumIsNull() {
            addCriterion("game_order_num is null");
            return (Criteria) this;
        }

        public Criteria andGameOrderNumIsNotNull() {
            addCriterion("game_order_num is not null");
            return (Criteria) this;
        }

        public Criteria andGameOrderNumEqualTo(String value) {
            addCriterion("game_order_num =", value, "gameOrderNum");
            return (Criteria) this;
        }

        public Criteria andGameOrderNumNotEqualTo(String value) {
            addCriterion("game_order_num <>", value, "gameOrderNum");
            return (Criteria) this;
        }

        public Criteria andGameOrderNumGreaterThan(String value) {
            addCriterion("game_order_num >", value, "gameOrderNum");
            return (Criteria) this;
        }

        public Criteria andGameOrderNumGreaterThanOrEqualTo(String value) {
            addCriterion("game_order_num >=", value, "gameOrderNum");
            return (Criteria) this;
        }

        public Criteria andGameOrderNumLessThan(String value) {
            addCriterion("game_order_num <", value, "gameOrderNum");
            return (Criteria) this;
        }

        public Criteria andGameOrderNumLessThanOrEqualTo(String value) {
            addCriterion("game_order_num <=", value, "gameOrderNum");
            return (Criteria) this;
        }

        public Criteria andGameOrderNumLike(String value) {
            addCriterion("game_order_num like", value, "gameOrderNum");
            return (Criteria) this;
        }

        public Criteria andGameOrderNumNotLike(String value) {
            addCriterion("game_order_num not like", value, "gameOrderNum");
            return (Criteria) this;
        }

        public Criteria andGameOrderNumIn(List<String> values) {
            addCriterion("game_order_num in", values, "gameOrderNum");
            return (Criteria) this;
        }

        public Criteria andGameOrderNumNotIn(List<String> values) {
            addCriterion("game_order_num not in", values, "gameOrderNum");
            return (Criteria) this;
        }

        public Criteria andGameOrderNumBetween(String value1, String value2) {
            addCriterion("game_order_num between", value1, value2, "gameOrderNum");
            return (Criteria) this;
        }

        public Criteria andGameOrderNumNotBetween(String value1, String value2) {
            addCriterion("game_order_num not between", value1, value2, "gameOrderNum");
            return (Criteria) this;
        }

        public Criteria andIntegralIsNull() {
            addCriterion("_integral is null");
            return (Criteria) this;
        }

        public Criteria andIntegralIsNotNull() {
            addCriterion("_integral is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralEqualTo(Integer value) {
            addCriterion("_integral =", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralNotEqualTo(Integer value) {
            addCriterion("_integral <>", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralGreaterThan(Integer value) {
            addCriterion("_integral >", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralGreaterThanOrEqualTo(Integer value) {
            addCriterion("_integral >=", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralLessThan(Integer value) {
            addCriterion("_integral <", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralLessThanOrEqualTo(Integer value) {
            addCriterion("_integral <=", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralIn(List<Integer> values) {
            addCriterion("_integral in", values, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralNotIn(List<Integer> values) {
            addCriterion("_integral not in", values, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralBetween(Integer value1, Integer value2) {
            addCriterion("_integral between", value1, value2, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralNotBetween(Integer value1, Integer value2) {
            addCriterion("_integral not between", value1, value2, "integral");
            return (Criteria) this;
        }

        public Criteria andObjIdIsNull() {
            addCriterion("obj_id is null");
            return (Criteria) this;
        }

        public Criteria andObjIdIsNotNull() {
            addCriterion("obj_id is not null");
            return (Criteria) this;
        }

        public Criteria andObjIdEqualTo(Integer value) {
            addCriterion("obj_id =", value, "objId");
            return (Criteria) this;
        }

        public Criteria andObjIdNotEqualTo(Integer value) {
            addCriterion("obj_id <>", value, "objId");
            return (Criteria) this;
        }

        public Criteria andObjIdGreaterThan(Integer value) {
            addCriterion("obj_id >", value, "objId");
            return (Criteria) this;
        }

        public Criteria andObjIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("obj_id >=", value, "objId");
            return (Criteria) this;
        }

        public Criteria andObjIdLessThan(Integer value) {
            addCriterion("obj_id <", value, "objId");
            return (Criteria) this;
        }

        public Criteria andObjIdLessThanOrEqualTo(Integer value) {
            addCriterion("obj_id <=", value, "objId");
            return (Criteria) this;
        }

        public Criteria andObjIdIn(List<Integer> values) {
            addCriterion("obj_id in", values, "objId");
            return (Criteria) this;
        }

        public Criteria andObjIdNotIn(List<Integer> values) {
            addCriterion("obj_id not in", values, "objId");
            return (Criteria) this;
        }

        public Criteria andObjIdBetween(Integer value1, Integer value2) {
            addCriterion("obj_id between", value1, value2, "objId");
            return (Criteria) this;
        }

        public Criteria andObjIdNotBetween(Integer value1, Integer value2) {
            addCriterion("obj_id not between", value1, value2, "objId");
            return (Criteria) this;
        }

        public Criteria andOrderSourceIsNull() {
            addCriterion("order_source is null");
            return (Criteria) this;
        }

        public Criteria andOrderSourceIsNotNull() {
            addCriterion("order_source is not null");
            return (Criteria) this;
        }

        public Criteria andOrderSourceEqualTo(Integer value) {
            addCriterion("order_source =", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceNotEqualTo(Integer value) {
            addCriterion("order_source <>", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceGreaterThan(Integer value) {
            addCriterion("order_source >", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_source >=", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceLessThan(Integer value) {
            addCriterion("order_source <", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceLessThanOrEqualTo(Integer value) {
            addCriterion("order_source <=", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceIn(List<Integer> values) {
            addCriterion("order_source in", values, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceNotIn(List<Integer> values) {
            addCriterion("order_source not in", values, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceBetween(Integer value1, Integer value2) {
            addCriterion("order_source between", value1, value2, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceNotBetween(Integer value1, Integer value2) {
            addCriterion("order_source not between", value1, value2, "orderSource");
            return (Criteria) this;
        }

        public Criteria andPayOrderNumIsNull() {
            addCriterion("pay_order_num is null");
            return (Criteria) this;
        }

        public Criteria andPayOrderNumIsNotNull() {
            addCriterion("pay_order_num is not null");
            return (Criteria) this;
        }

        public Criteria andPayOrderNumEqualTo(String value) {
            addCriterion("pay_order_num =", value, "payOrderNum");
            return (Criteria) this;
        }

        public Criteria andPayOrderNumNotEqualTo(String value) {
            addCriterion("pay_order_num <>", value, "payOrderNum");
            return (Criteria) this;
        }

        public Criteria andPayOrderNumGreaterThan(String value) {
            addCriterion("pay_order_num >", value, "payOrderNum");
            return (Criteria) this;
        }

        public Criteria andPayOrderNumGreaterThanOrEqualTo(String value) {
            addCriterion("pay_order_num >=", value, "payOrderNum");
            return (Criteria) this;
        }

        public Criteria andPayOrderNumLessThan(String value) {
            addCriterion("pay_order_num <", value, "payOrderNum");
            return (Criteria) this;
        }

        public Criteria andPayOrderNumLessThanOrEqualTo(String value) {
            addCriterion("pay_order_num <=", value, "payOrderNum");
            return (Criteria) this;
        }

        public Criteria andPayOrderNumLike(String value) {
            addCriterion("pay_order_num like", value, "payOrderNum");
            return (Criteria) this;
        }

        public Criteria andPayOrderNumNotLike(String value) {
            addCriterion("pay_order_num not like", value, "payOrderNum");
            return (Criteria) this;
        }

        public Criteria andPayOrderNumIn(List<String> values) {
            addCriterion("pay_order_num in", values, "payOrderNum");
            return (Criteria) this;
        }

        public Criteria andPayOrderNumNotIn(List<String> values) {
            addCriterion("pay_order_num not in", values, "payOrderNum");
            return (Criteria) this;
        }

        public Criteria andPayOrderNumBetween(String value1, String value2) {
            addCriterion("pay_order_num between", value1, value2, "payOrderNum");
            return (Criteria) this;
        }

        public Criteria andPayOrderNumNotBetween(String value1, String value2) {
            addCriterion("pay_order_num not between", value1, value2, "payOrderNum");
            return (Criteria) this;
        }

        public Criteria andPayTotalMoneyIsNull() {
            addCriterion("pay_total_money is null");
            return (Criteria) this;
        }

        public Criteria andPayTotalMoneyIsNotNull() {
            addCriterion("pay_total_money is not null");
            return (Criteria) this;
        }

        public Criteria andPayTotalMoneyEqualTo(String value) {
            addCriterion("pay_total_money =", value, "payTotalMoney");
            return (Criteria) this;
        }

        public Criteria andPayTotalMoneyNotEqualTo(String value) {
            addCriterion("pay_total_money <>", value, "payTotalMoney");
            return (Criteria) this;
        }

        public Criteria andPayTotalMoneyGreaterThan(String value) {
            addCriterion("pay_total_money >", value, "payTotalMoney");
            return (Criteria) this;
        }

        public Criteria andPayTotalMoneyGreaterThanOrEqualTo(String value) {
            addCriterion("pay_total_money >=", value, "payTotalMoney");
            return (Criteria) this;
        }

        public Criteria andPayTotalMoneyLessThan(String value) {
            addCriterion("pay_total_money <", value, "payTotalMoney");
            return (Criteria) this;
        }

        public Criteria andPayTotalMoneyLessThanOrEqualTo(String value) {
            addCriterion("pay_total_money <=", value, "payTotalMoney");
            return (Criteria) this;
        }

        public Criteria andPayTotalMoneyLike(String value) {
            addCriterion("pay_total_money like", value, "payTotalMoney");
            return (Criteria) this;
        }

        public Criteria andPayTotalMoneyNotLike(String value) {
            addCriterion("pay_total_money not like", value, "payTotalMoney");
            return (Criteria) this;
        }

        public Criteria andPayTotalMoneyIn(List<String> values) {
            addCriterion("pay_total_money in", values, "payTotalMoney");
            return (Criteria) this;
        }

        public Criteria andPayTotalMoneyNotIn(List<String> values) {
            addCriterion("pay_total_money not in", values, "payTotalMoney");
            return (Criteria) this;
        }

        public Criteria andPayTotalMoneyBetween(String value1, String value2) {
            addCriterion("pay_total_money between", value1, value2, "payTotalMoney");
            return (Criteria) this;
        }

        public Criteria andPayTotalMoneyNotBetween(String value1, String value2) {
            addCriterion("pay_total_money not between", value1, value2, "payTotalMoney");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNull() {
            addCriterion("pay_type is null");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNotNull() {
            addCriterion("pay_type is not null");
            return (Criteria) this;
        }

        public Criteria andPayTypeEqualTo(Integer value) {
            addCriterion("pay_type =", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotEqualTo(Integer value) {
            addCriterion("pay_type <>", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThan(Integer value) {
            addCriterion("pay_type >", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_type >=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThan(Integer value) {
            addCriterion("pay_type <", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThanOrEqualTo(Integer value) {
            addCriterion("pay_type <=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeIn(List<Integer> values) {
            addCriterion("pay_type in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotIn(List<Integer> values) {
            addCriterion("pay_type not in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeBetween(Integer value1, Integer value2) {
            addCriterion("pay_type between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_type not between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNull() {
            addCriterion("product_name is null");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNotNull() {
            addCriterion("product_name is not null");
            return (Criteria) this;
        }

        public Criteria andProductNameEqualTo(String value) {
            addCriterion("product_name =", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotEqualTo(String value) {
            addCriterion("product_name <>", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThan(String value) {
            addCriterion("product_name >", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("product_name >=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThan(String value) {
            addCriterion("product_name <", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThanOrEqualTo(String value) {
            addCriterion("product_name <=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLike(String value) {
            addCriterion("product_name like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotLike(String value) {
            addCriterion("product_name not like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameIn(List<String> values) {
            addCriterion("product_name in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotIn(List<String> values) {
            addCriterion("product_name not in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameBetween(String value1, String value2) {
            addCriterion("product_name between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotBetween(String value1, String value2) {
            addCriterion("product_name not between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductPriceIsNull() {
            addCriterion("product_price is null");
            return (Criteria) this;
        }

        public Criteria andProductPriceIsNotNull() {
            addCriterion("product_price is not null");
            return (Criteria) this;
        }

        public Criteria andProductPriceEqualTo(String value) {
            addCriterion("product_price =", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceNotEqualTo(String value) {
            addCriterion("product_price <>", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceGreaterThan(String value) {
            addCriterion("product_price >", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceGreaterThanOrEqualTo(String value) {
            addCriterion("product_price >=", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceLessThan(String value) {
            addCriterion("product_price <", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceLessThanOrEqualTo(String value) {
            addCriterion("product_price <=", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceLike(String value) {
            addCriterion("product_price like", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceNotLike(String value) {
            addCriterion("product_price not like", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceIn(List<String> values) {
            addCriterion("product_price in", values, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceNotIn(List<String> values) {
            addCriterion("product_price not in", values, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceBetween(String value1, String value2) {
            addCriterion("product_price between", value1, value2, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceNotBetween(String value1, String value2) {
            addCriterion("product_price not between", value1, value2, "productPrice");
            return (Criteria) this;
        }

        public Criteria andResultTypeIsNull() {
            addCriterion("result_type is null");
            return (Criteria) this;
        }

        public Criteria andResultTypeIsNotNull() {
            addCriterion("result_type is not null");
            return (Criteria) this;
        }

        public Criteria andResultTypeEqualTo(Integer value) {
            addCriterion("result_type =", value, "resultType");
            return (Criteria) this;
        }

        public Criteria andResultTypeNotEqualTo(Integer value) {
            addCriterion("result_type <>", value, "resultType");
            return (Criteria) this;
        }

        public Criteria andResultTypeGreaterThan(Integer value) {
            addCriterion("result_type >", value, "resultType");
            return (Criteria) this;
        }

        public Criteria andResultTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("result_type >=", value, "resultType");
            return (Criteria) this;
        }

        public Criteria andResultTypeLessThan(Integer value) {
            addCriterion("result_type <", value, "resultType");
            return (Criteria) this;
        }

        public Criteria andResultTypeLessThanOrEqualTo(Integer value) {
            addCriterion("result_type <=", value, "resultType");
            return (Criteria) this;
        }

        public Criteria andResultTypeIn(List<Integer> values) {
            addCriterion("result_type in", values, "resultType");
            return (Criteria) this;
        }

        public Criteria andResultTypeNotIn(List<Integer> values) {
            addCriterion("result_type not in", values, "resultType");
            return (Criteria) this;
        }

        public Criteria andResultTypeBetween(Integer value1, Integer value2) {
            addCriterion("result_type between", value1, value2, "resultType");
            return (Criteria) this;
        }

        public Criteria andResultTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("result_type not between", value1, value2, "resultType");
            return (Criteria) this;
        }

        public Criteria andReturnUrlIsNull() {
            addCriterion("return_url is null");
            return (Criteria) this;
        }

        public Criteria andReturnUrlIsNotNull() {
            addCriterion("return_url is not null");
            return (Criteria) this;
        }

        public Criteria andReturnUrlEqualTo(String value) {
            addCriterion("return_url =", value, "returnUrl");
            return (Criteria) this;
        }

        public Criteria andReturnUrlNotEqualTo(String value) {
            addCriterion("return_url <>", value, "returnUrl");
            return (Criteria) this;
        }

        public Criteria andReturnUrlGreaterThan(String value) {
            addCriterion("return_url >", value, "returnUrl");
            return (Criteria) this;
        }

        public Criteria andReturnUrlGreaterThanOrEqualTo(String value) {
            addCriterion("return_url >=", value, "returnUrl");
            return (Criteria) this;
        }

        public Criteria andReturnUrlLessThan(String value) {
            addCriterion("return_url <", value, "returnUrl");
            return (Criteria) this;
        }

        public Criteria andReturnUrlLessThanOrEqualTo(String value) {
            addCriterion("return_url <=", value, "returnUrl");
            return (Criteria) this;
        }

        public Criteria andReturnUrlLike(String value) {
            addCriterion("return_url like", value, "returnUrl");
            return (Criteria) this;
        }

        public Criteria andReturnUrlNotLike(String value) {
            addCriterion("return_url not like", value, "returnUrl");
            return (Criteria) this;
        }

        public Criteria andReturnUrlIn(List<String> values) {
            addCriterion("return_url in", values, "returnUrl");
            return (Criteria) this;
        }

        public Criteria andReturnUrlNotIn(List<String> values) {
            addCriterion("return_url not in", values, "returnUrl");
            return (Criteria) this;
        }

        public Criteria andReturnUrlBetween(String value1, String value2) {
            addCriterion("return_url between", value1, value2, "returnUrl");
            return (Criteria) this;
        }

        public Criteria andReturnUrlNotBetween(String value1, String value2) {
            addCriterion("return_url not between", value1, value2, "returnUrl");
            return (Criteria) this;
        }

        public Criteria andSendMsgToGamecompanyIsNull() {
            addCriterion("send_msg_to_gamecompany is null");
            return (Criteria) this;
        }

        public Criteria andSendMsgToGamecompanyIsNotNull() {
            addCriterion("send_msg_to_gamecompany is not null");
            return (Criteria) this;
        }

        public Criteria andSendMsgToGamecompanyEqualTo(Integer value) {
            addCriterion("send_msg_to_gamecompany =", value, "sendMsgToGamecompany");
            return (Criteria) this;
        }

        public Criteria andSendMsgToGamecompanyNotEqualTo(Integer value) {
            addCriterion("send_msg_to_gamecompany <>", value, "sendMsgToGamecompany");
            return (Criteria) this;
        }

        public Criteria andSendMsgToGamecompanyGreaterThan(Integer value) {
            addCriterion("send_msg_to_gamecompany >", value, "sendMsgToGamecompany");
            return (Criteria) this;
        }

        public Criteria andSendMsgToGamecompanyGreaterThanOrEqualTo(Integer value) {
            addCriterion("send_msg_to_gamecompany >=", value, "sendMsgToGamecompany");
            return (Criteria) this;
        }

        public Criteria andSendMsgToGamecompanyLessThan(Integer value) {
            addCriterion("send_msg_to_gamecompany <", value, "sendMsgToGamecompany");
            return (Criteria) this;
        }

        public Criteria andSendMsgToGamecompanyLessThanOrEqualTo(Integer value) {
            addCriterion("send_msg_to_gamecompany <=", value, "sendMsgToGamecompany");
            return (Criteria) this;
        }

        public Criteria andSendMsgToGamecompanyIn(List<Integer> values) {
            addCriterion("send_msg_to_gamecompany in", values, "sendMsgToGamecompany");
            return (Criteria) this;
        }

        public Criteria andSendMsgToGamecompanyNotIn(List<Integer> values) {
            addCriterion("send_msg_to_gamecompany not in", values, "sendMsgToGamecompany");
            return (Criteria) this;
        }

        public Criteria andSendMsgToGamecompanyBetween(Integer value1, Integer value2) {
            addCriterion("send_msg_to_gamecompany between", value1, value2, "sendMsgToGamecompany");
            return (Criteria) this;
        }

        public Criteria andSendMsgToGamecompanyNotBetween(Integer value1, Integer value2) {
            addCriterion("send_msg_to_gamecompany not between", value1, value2, "sendMsgToGamecompany");
            return (Criteria) this;
        }

        public Criteria andShoppingNumIsNull() {
            addCriterion("shopping_num is null");
            return (Criteria) this;
        }

        public Criteria andShoppingNumIsNotNull() {
            addCriterion("shopping_num is not null");
            return (Criteria) this;
        }

        public Criteria andShoppingNumEqualTo(Integer value) {
            addCriterion("shopping_num =", value, "shoppingNum");
            return (Criteria) this;
        }

        public Criteria andShoppingNumNotEqualTo(Integer value) {
            addCriterion("shopping_num <>", value, "shoppingNum");
            return (Criteria) this;
        }

        public Criteria andShoppingNumGreaterThan(Integer value) {
            addCriterion("shopping_num >", value, "shoppingNum");
            return (Criteria) this;
        }

        public Criteria andShoppingNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("shopping_num >=", value, "shoppingNum");
            return (Criteria) this;
        }

        public Criteria andShoppingNumLessThan(Integer value) {
            addCriterion("shopping_num <", value, "shoppingNum");
            return (Criteria) this;
        }

        public Criteria andShoppingNumLessThanOrEqualTo(Integer value) {
            addCriterion("shopping_num <=", value, "shoppingNum");
            return (Criteria) this;
        }

        public Criteria andShoppingNumIn(List<Integer> values) {
            addCriterion("shopping_num in", values, "shoppingNum");
            return (Criteria) this;
        }

        public Criteria andShoppingNumNotIn(List<Integer> values) {
            addCriterion("shopping_num not in", values, "shoppingNum");
            return (Criteria) this;
        }

        public Criteria andShoppingNumBetween(Integer value1, Integer value2) {
            addCriterion("shopping_num between", value1, value2, "shoppingNum");
            return (Criteria) this;
        }

        public Criteria andShoppingNumNotBetween(Integer value1, Integer value2) {
            addCriterion("shopping_num not between", value1, value2, "shoppingNum");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andSyncTimeIsNull() {
            addCriterion("sync_time is null");
            return (Criteria) this;
        }

        public Criteria andSyncTimeIsNotNull() {
            addCriterion("sync_time is not null");
            return (Criteria) this;
        }

        public Criteria andSyncTimeEqualTo(String value) {
            addCriterion("sync_time =", value, "syncTime");
            return (Criteria) this;
        }

        public Criteria andSyncTimeNotEqualTo(String value) {
            addCriterion("sync_time <>", value, "syncTime");
            return (Criteria) this;
        }

        public Criteria andSyncTimeGreaterThan(String value) {
            addCriterion("sync_time >", value, "syncTime");
            return (Criteria) this;
        }

        public Criteria andSyncTimeGreaterThanOrEqualTo(String value) {
            addCriterion("sync_time >=", value, "syncTime");
            return (Criteria) this;
        }

        public Criteria andSyncTimeLessThan(String value) {
            addCriterion("sync_time <", value, "syncTime");
            return (Criteria) this;
        }

        public Criteria andSyncTimeLessThanOrEqualTo(String value) {
            addCriterion("sync_time <=", value, "syncTime");
            return (Criteria) this;
        }

        public Criteria andSyncTimeLike(String value) {
            addCriterion("sync_time like", value, "syncTime");
            return (Criteria) this;
        }

        public Criteria andSyncTimeNotLike(String value) {
            addCriterion("sync_time not like", value, "syncTime");
            return (Criteria) this;
        }

        public Criteria andSyncTimeIn(List<String> values) {
            addCriterion("sync_time in", values, "syncTime");
            return (Criteria) this;
        }

        public Criteria andSyncTimeNotIn(List<String> values) {
            addCriterion("sync_time not in", values, "syncTime");
            return (Criteria) this;
        }

        public Criteria andSyncTimeBetween(String value1, String value2) {
            addCriterion("sync_time between", value1, value2, "syncTime");
            return (Criteria) this;
        }

        public Criteria andSyncTimeNotBetween(String value1, String value2) {
            addCriterion("sync_time not between", value1, value2, "syncTime");
            return (Criteria) this;
        }

        public Criteria andTradCodeIsNull() {
            addCriterion("trad_code is null");
            return (Criteria) this;
        }

        public Criteria andTradCodeIsNotNull() {
            addCriterion("trad_code is not null");
            return (Criteria) this;
        }

        public Criteria andTradCodeEqualTo(String value) {
            addCriterion("trad_code =", value, "tradCode");
            return (Criteria) this;
        }

        public Criteria andTradCodeNotEqualTo(String value) {
            addCriterion("trad_code <>", value, "tradCode");
            return (Criteria) this;
        }

        public Criteria andTradCodeGreaterThan(String value) {
            addCriterion("trad_code >", value, "tradCode");
            return (Criteria) this;
        }

        public Criteria andTradCodeGreaterThanOrEqualTo(String value) {
            addCriterion("trad_code >=", value, "tradCode");
            return (Criteria) this;
        }

        public Criteria andTradCodeLessThan(String value) {
            addCriterion("trad_code <", value, "tradCode");
            return (Criteria) this;
        }

        public Criteria andTradCodeLessThanOrEqualTo(String value) {
            addCriterion("trad_code <=", value, "tradCode");
            return (Criteria) this;
        }

        public Criteria andTradCodeLike(String value) {
            addCriterion("trad_code like", value, "tradCode");
            return (Criteria) this;
        }

        public Criteria andTradCodeNotLike(String value) {
            addCriterion("trad_code not like", value, "tradCode");
            return (Criteria) this;
        }

        public Criteria andTradCodeIn(List<String> values) {
            addCriterion("trad_code in", values, "tradCode");
            return (Criteria) this;
        }

        public Criteria andTradCodeNotIn(List<String> values) {
            addCriterion("trad_code not in", values, "tradCode");
            return (Criteria) this;
        }

        public Criteria andTradCodeBetween(String value1, String value2) {
            addCriterion("trad_code between", value1, value2, "tradCode");
            return (Criteria) this;
        }

        public Criteria andTradCodeNotBetween(String value1, String value2) {
            addCriterion("trad_code not between", value1, value2, "tradCode");
            return (Criteria) this;
        }

        public Criteria andTradMsgIsNull() {
            addCriterion("trad_msg is null");
            return (Criteria) this;
        }

        public Criteria andTradMsgIsNotNull() {
            addCriterion("trad_msg is not null");
            return (Criteria) this;
        }

        public Criteria andTradMsgEqualTo(String value) {
            addCriterion("trad_msg =", value, "tradMsg");
            return (Criteria) this;
        }

        public Criteria andTradMsgNotEqualTo(String value) {
            addCriterion("trad_msg <>", value, "tradMsg");
            return (Criteria) this;
        }

        public Criteria andTradMsgGreaterThan(String value) {
            addCriterion("trad_msg >", value, "tradMsg");
            return (Criteria) this;
        }

        public Criteria andTradMsgGreaterThanOrEqualTo(String value) {
            addCriterion("trad_msg >=", value, "tradMsg");
            return (Criteria) this;
        }

        public Criteria andTradMsgLessThan(String value) {
            addCriterion("trad_msg <", value, "tradMsg");
            return (Criteria) this;
        }

        public Criteria andTradMsgLessThanOrEqualTo(String value) {
            addCriterion("trad_msg <=", value, "tradMsg");
            return (Criteria) this;
        }

        public Criteria andTradMsgLike(String value) {
            addCriterion("trad_msg like", value, "tradMsg");
            return (Criteria) this;
        }

        public Criteria andTradMsgNotLike(String value) {
            addCriterion("trad_msg not like", value, "tradMsg");
            return (Criteria) this;
        }

        public Criteria andTradMsgIn(List<String> values) {
            addCriterion("trad_msg in", values, "tradMsg");
            return (Criteria) this;
        }

        public Criteria andTradMsgNotIn(List<String> values) {
            addCriterion("trad_msg not in", values, "tradMsg");
            return (Criteria) this;
        }

        public Criteria andTradMsgBetween(String value1, String value2) {
            addCriterion("trad_msg between", value1, value2, "tradMsg");
            return (Criteria) this;
        }

        public Criteria andTradMsgNotBetween(String value1, String value2) {
            addCriterion("trad_msg not between", value1, value2, "tradMsg");
            return (Criteria) this;
        }

        public Criteria andTradNoIsNull() {
            addCriterion("trad_no is null");
            return (Criteria) this;
        }

        public Criteria andTradNoIsNotNull() {
            addCriterion("trad_no is not null");
            return (Criteria) this;
        }

        public Criteria andTradNoEqualTo(String value) {
            addCriterion("trad_no =", value, "tradNo");
            return (Criteria) this;
        }

        public Criteria andTradNoNotEqualTo(String value) {
            addCriterion("trad_no <>", value, "tradNo");
            return (Criteria) this;
        }

        public Criteria andTradNoGreaterThan(String value) {
            addCriterion("trad_no >", value, "tradNo");
            return (Criteria) this;
        }

        public Criteria andTradNoGreaterThanOrEqualTo(String value) {
            addCriterion("trad_no >=", value, "tradNo");
            return (Criteria) this;
        }

        public Criteria andTradNoLessThan(String value) {
            addCriterion("trad_no <", value, "tradNo");
            return (Criteria) this;
        }

        public Criteria andTradNoLessThanOrEqualTo(String value) {
            addCriterion("trad_no <=", value, "tradNo");
            return (Criteria) this;
        }

        public Criteria andTradNoLike(String value) {
            addCriterion("trad_no like", value, "tradNo");
            return (Criteria) this;
        }

        public Criteria andTradNoNotLike(String value) {
            addCriterion("trad_no not like", value, "tradNo");
            return (Criteria) this;
        }

        public Criteria andTradNoIn(List<String> values) {
            addCriterion("trad_no in", values, "tradNo");
            return (Criteria) this;
        }

        public Criteria andTradNoNotIn(List<String> values) {
            addCriterion("trad_no not in", values, "tradNo");
            return (Criteria) this;
        }

        public Criteria andTradNoBetween(String value1, String value2) {
            addCriterion("trad_no between", value1, value2, "tradNo");
            return (Criteria) this;
        }

        public Criteria andTradNoNotBetween(String value1, String value2) {
            addCriterion("trad_no not between", value1, value2, "tradNo");
            return (Criteria) this;
        }

        public Criteria andGameIdIsNull() {
            addCriterion("game_id is null");
            return (Criteria) this;
        }

        public Criteria andGameIdIsNotNull() {
            addCriterion("game_id is not null");
            return (Criteria) this;
        }

        public Criteria andGameIdEqualTo(Integer value) {
            addCriterion("game_id =", value, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdNotEqualTo(Integer value) {
            addCriterion("game_id <>", value, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdGreaterThan(Integer value) {
            addCriterion("game_id >", value, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("game_id >=", value, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdLessThan(Integer value) {
            addCriterion("game_id <", value, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdLessThanOrEqualTo(Integer value) {
            addCriterion("game_id <=", value, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdIn(List<Integer> values) {
            addCriterion("game_id in", values, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdNotIn(List<Integer> values) {
            addCriterion("game_id not in", values, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdBetween(Integer value1, Integer value2) {
            addCriterion("game_id between", value1, value2, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdNotBetween(Integer value1, Integer value2) {
            addCriterion("game_id not between", value1, value2, "gameId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}