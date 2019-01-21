package com.liyunet.domain.charge;

import java.util.ArrayList;
import java.util.List;

public class ChargeCoinInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ChargeCoinInfoExample() {
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

        public Criteria andCoinInfoIsNull() {
            addCriterion("coin_info is null");
            return (Criteria) this;
        }

        public Criteria andCoinInfoIsNotNull() {
            addCriterion("coin_info is not null");
            return (Criteria) this;
        }

        public Criteria andCoinInfoEqualTo(String value) {
            addCriterion("coin_info =", value, "coinInfo");
            return (Criteria) this;
        }

        public Criteria andCoinInfoNotEqualTo(String value) {
            addCriterion("coin_info <>", value, "coinInfo");
            return (Criteria) this;
        }

        public Criteria andCoinInfoGreaterThan(String value) {
            addCriterion("coin_info >", value, "coinInfo");
            return (Criteria) this;
        }

        public Criteria andCoinInfoGreaterThanOrEqualTo(String value) {
            addCriterion("coin_info >=", value, "coinInfo");
            return (Criteria) this;
        }

        public Criteria andCoinInfoLessThan(String value) {
            addCriterion("coin_info <", value, "coinInfo");
            return (Criteria) this;
        }

        public Criteria andCoinInfoLessThanOrEqualTo(String value) {
            addCriterion("coin_info <=", value, "coinInfo");
            return (Criteria) this;
        }

        public Criteria andCoinInfoLike(String value) {
            addCriterion("coin_info like", value, "coinInfo");
            return (Criteria) this;
        }

        public Criteria andCoinInfoNotLike(String value) {
            addCriterion("coin_info not like", value, "coinInfo");
            return (Criteria) this;
        }

        public Criteria andCoinInfoIn(List<String> values) {
            addCriterion("coin_info in", values, "coinInfo");
            return (Criteria) this;
        }

        public Criteria andCoinInfoNotIn(List<String> values) {
            addCriterion("coin_info not in", values, "coinInfo");
            return (Criteria) this;
        }

        public Criteria andCoinInfoBetween(String value1, String value2) {
            addCriterion("coin_info between", value1, value2, "coinInfo");
            return (Criteria) this;
        }

        public Criteria andCoinInfoNotBetween(String value1, String value2) {
            addCriterion("coin_info not between", value1, value2, "coinInfo");
            return (Criteria) this;
        }

        public Criteria andCoinImageIsNull() {
            addCriterion("coin_image is null");
            return (Criteria) this;
        }

        public Criteria andCoinImageIsNotNull() {
            addCriterion("coin_image is not null");
            return (Criteria) this;
        }

        public Criteria andCoinImageEqualTo(String value) {
            addCriterion("coin_image =", value, "coinImage");
            return (Criteria) this;
        }

        public Criteria andCoinImageNotEqualTo(String value) {
            addCriterion("coin_image <>", value, "coinImage");
            return (Criteria) this;
        }

        public Criteria andCoinImageGreaterThan(String value) {
            addCriterion("coin_image >", value, "coinImage");
            return (Criteria) this;
        }

        public Criteria andCoinImageGreaterThanOrEqualTo(String value) {
            addCriterion("coin_image >=", value, "coinImage");
            return (Criteria) this;
        }

        public Criteria andCoinImageLessThan(String value) {
            addCriterion("coin_image <", value, "coinImage");
            return (Criteria) this;
        }

        public Criteria andCoinImageLessThanOrEqualTo(String value) {
            addCriterion("coin_image <=", value, "coinImage");
            return (Criteria) this;
        }

        public Criteria andCoinImageLike(String value) {
            addCriterion("coin_image like", value, "coinImage");
            return (Criteria) this;
        }

        public Criteria andCoinImageNotLike(String value) {
            addCriterion("coin_image not like", value, "coinImage");
            return (Criteria) this;
        }

        public Criteria andCoinImageIn(List<String> values) {
            addCriterion("coin_image in", values, "coinImage");
            return (Criteria) this;
        }

        public Criteria andCoinImageNotIn(List<String> values) {
            addCriterion("coin_image not in", values, "coinImage");
            return (Criteria) this;
        }

        public Criteria andCoinImageBetween(String value1, String value2) {
            addCriterion("coin_image between", value1, value2, "coinImage");
            return (Criteria) this;
        }

        public Criteria andCoinImageNotBetween(String value1, String value2) {
            addCriterion("coin_image not between", value1, value2, "coinImage");
            return (Criteria) this;
        }

        public Criteria andChargeAddressIsNull() {
            addCriterion("charge_address is null");
            return (Criteria) this;
        }

        public Criteria andChargeAddressIsNotNull() {
            addCriterion("charge_address is not null");
            return (Criteria) this;
        }

        public Criteria andChargeAddressEqualTo(String value) {
            addCriterion("charge_address =", value, "chargeAddress");
            return (Criteria) this;
        }

        public Criteria andChargeAddressNotEqualTo(String value) {
            addCriterion("charge_address <>", value, "chargeAddress");
            return (Criteria) this;
        }

        public Criteria andChargeAddressGreaterThan(String value) {
            addCriterion("charge_address >", value, "chargeAddress");
            return (Criteria) this;
        }

        public Criteria andChargeAddressGreaterThanOrEqualTo(String value) {
            addCriterion("charge_address >=", value, "chargeAddress");
            return (Criteria) this;
        }

        public Criteria andChargeAddressLessThan(String value) {
            addCriterion("charge_address <", value, "chargeAddress");
            return (Criteria) this;
        }

        public Criteria andChargeAddressLessThanOrEqualTo(String value) {
            addCriterion("charge_address <=", value, "chargeAddress");
            return (Criteria) this;
        }

        public Criteria andChargeAddressLike(String value) {
            addCriterion("charge_address like", value, "chargeAddress");
            return (Criteria) this;
        }

        public Criteria andChargeAddressNotLike(String value) {
            addCriterion("charge_address not like", value, "chargeAddress");
            return (Criteria) this;
        }

        public Criteria andChargeAddressIn(List<String> values) {
            addCriterion("charge_address in", values, "chargeAddress");
            return (Criteria) this;
        }

        public Criteria andChargeAddressNotIn(List<String> values) {
            addCriterion("charge_address not in", values, "chargeAddress");
            return (Criteria) this;
        }

        public Criteria andChargeAddressBetween(String value1, String value2) {
            addCriterion("charge_address between", value1, value2, "chargeAddress");
            return (Criteria) this;
        }

        public Criteria andChargeAddressNotBetween(String value1, String value2) {
            addCriterion("charge_address not between", value1, value2, "chargeAddress");
            return (Criteria) this;
        }

        public Criteria andQrCodeIsNull() {
            addCriterion("QR_code is null");
            return (Criteria) this;
        }

        public Criteria andQrCodeIsNotNull() {
            addCriterion("QR_code is not null");
            return (Criteria) this;
        }

        public Criteria andQrCodeEqualTo(String value) {
            addCriterion("QR_code =", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeNotEqualTo(String value) {
            addCriterion("QR_code <>", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeGreaterThan(String value) {
            addCriterion("QR_code >", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeGreaterThanOrEqualTo(String value) {
            addCriterion("QR_code >=", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeLessThan(String value) {
            addCriterion("QR_code <", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeLessThanOrEqualTo(String value) {
            addCriterion("QR_code <=", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeLike(String value) {
            addCriterion("QR_code like", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeNotLike(String value) {
            addCriterion("QR_code not like", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeIn(List<String> values) {
            addCriterion("QR_code in", values, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeNotIn(List<String> values) {
            addCriterion("QR_code not in", values, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeBetween(String value1, String value2) {
            addCriterion("QR_code between", value1, value2, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeNotBetween(String value1, String value2) {
            addCriterion("QR_code not between", value1, value2, "qrCode");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andHomeImageIsNull() {
            addCriterion("home_image is null");
            return (Criteria) this;
        }

        public Criteria andHomeImageIsNotNull() {
            addCriterion("home_image is not null");
            return (Criteria) this;
        }

        public Criteria andHomeImageEqualTo(String value) {
            addCriterion("home_image =", value, "homeImage");
            return (Criteria) this;
        }

        public Criteria andHomeImageNotEqualTo(String value) {
            addCriterion("home_image <>", value, "homeImage");
            return (Criteria) this;
        }

        public Criteria andHomeImageGreaterThan(String value) {
            addCriterion("home_image >", value, "homeImage");
            return (Criteria) this;
        }

        public Criteria andHomeImageGreaterThanOrEqualTo(String value) {
            addCriterion("home_image >=", value, "homeImage");
            return (Criteria) this;
        }

        public Criteria andHomeImageLessThan(String value) {
            addCriterion("home_image <", value, "homeImage");
            return (Criteria) this;
        }

        public Criteria andHomeImageLessThanOrEqualTo(String value) {
            addCriterion("home_image <=", value, "homeImage");
            return (Criteria) this;
        }

        public Criteria andHomeImageLike(String value) {
            addCriterion("home_image like", value, "homeImage");
            return (Criteria) this;
        }

        public Criteria andHomeImageNotLike(String value) {
            addCriterion("home_image not like", value, "homeImage");
            return (Criteria) this;
        }

        public Criteria andHomeImageIn(List<String> values) {
            addCriterion("home_image in", values, "homeImage");
            return (Criteria) this;
        }

        public Criteria andHomeImageNotIn(List<String> values) {
            addCriterion("home_image not in", values, "homeImage");
            return (Criteria) this;
        }

        public Criteria andHomeImageBetween(String value1, String value2) {
            addCriterion("home_image between", value1, value2, "homeImage");
            return (Criteria) this;
        }

        public Criteria andHomeImageNotBetween(String value1, String value2) {
            addCriterion("home_image not between", value1, value2, "homeImage");
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