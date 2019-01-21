package com.liyunet.domain.luckbag;

import java.util.ArrayList;
import java.util.List;

public class LuckbagInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LuckbagInfoExample() {
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

        public Criteria andBidtnumIsNull() {
            addCriterion("bidtnum is null");
            return (Criteria) this;
        }

        public Criteria andBidtnumIsNotNull() {
            addCriterion("bidtnum is not null");
            return (Criteria) this;
        }

        public Criteria andBidtnumEqualTo(String value) {
            addCriterion("bidtnum =", value, "bidtnum");
            return (Criteria) this;
        }

        public Criteria andBidtnumNotEqualTo(String value) {
            addCriterion("bidtnum <>", value, "bidtnum");
            return (Criteria) this;
        }

        public Criteria andBidtnumGreaterThan(String value) {
            addCriterion("bidtnum >", value, "bidtnum");
            return (Criteria) this;
        }

        public Criteria andBidtnumGreaterThanOrEqualTo(String value) {
            addCriterion("bidtnum >=", value, "bidtnum");
            return (Criteria) this;
        }

        public Criteria andBidtnumLessThan(String value) {
            addCriterion("bidtnum <", value, "bidtnum");
            return (Criteria) this;
        }

        public Criteria andBidtnumLessThanOrEqualTo(String value) {
            addCriterion("bidtnum <=", value, "bidtnum");
            return (Criteria) this;
        }

        public Criteria andBidtnumLike(String value) {
            addCriterion("bidtnum like", value, "bidtnum");
            return (Criteria) this;
        }

        public Criteria andBidtnumNotLike(String value) {
            addCriterion("bidtnum not like", value, "bidtnum");
            return (Criteria) this;
        }

        public Criteria andBidtnumIn(List<String> values) {
            addCriterion("bidtnum in", values, "bidtnum");
            return (Criteria) this;
        }

        public Criteria andBidtnumNotIn(List<String> values) {
            addCriterion("bidtnum not in", values, "bidtnum");
            return (Criteria) this;
        }

        public Criteria andBidtnumBetween(String value1, String value2) {
            addCriterion("bidtnum between", value1, value2, "bidtnum");
            return (Criteria) this;
        }

        public Criteria andBidtnumNotBetween(String value1, String value2) {
            addCriterion("bidtnum not between", value1, value2, "bidtnum");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andBagnumIsNull() {
            addCriterion("bagnum is null");
            return (Criteria) this;
        }

        public Criteria andBagnumIsNotNull() {
            addCriterion("bagnum is not null");
            return (Criteria) this;
        }

        public Criteria andBagnumEqualTo(Integer value) {
            addCriterion("bagnum =", value, "bagnum");
            return (Criteria) this;
        }

        public Criteria andBagnumNotEqualTo(Integer value) {
            addCriterion("bagnum <>", value, "bagnum");
            return (Criteria) this;
        }

        public Criteria andBagnumGreaterThan(Integer value) {
            addCriterion("bagnum >", value, "bagnum");
            return (Criteria) this;
        }

        public Criteria andBagnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("bagnum >=", value, "bagnum");
            return (Criteria) this;
        }

        public Criteria andBagnumLessThan(Integer value) {
            addCriterion("bagnum <", value, "bagnum");
            return (Criteria) this;
        }

        public Criteria andBagnumLessThanOrEqualTo(Integer value) {
            addCriterion("bagnum <=", value, "bagnum");
            return (Criteria) this;
        }

        public Criteria andBagnumIn(List<Integer> values) {
            addCriterion("bagnum in", values, "bagnum");
            return (Criteria) this;
        }

        public Criteria andBagnumNotIn(List<Integer> values) {
            addCriterion("bagnum not in", values, "bagnum");
            return (Criteria) this;
        }

        public Criteria andBagnumBetween(Integer value1, Integer value2) {
            addCriterion("bagnum between", value1, value2, "bagnum");
            return (Criteria) this;
        }

        public Criteria andBagnumNotBetween(Integer value1, Integer value2) {
            addCriterion("bagnum not between", value1, value2, "bagnum");
            return (Criteria) this;
        }

        public Criteria andLbidIsNull() {
            addCriterion("lbid is null");
            return (Criteria) this;
        }

        public Criteria andLbidIsNotNull() {
            addCriterion("lbid is not null");
            return (Criteria) this;
        }

        public Criteria andLbidEqualTo(Integer value) {
            addCriterion("lbid =", value, "lbid");
            return (Criteria) this;
        }

        public Criteria andLbidNotEqualTo(Integer value) {
            addCriterion("lbid <>", value, "lbid");
            return (Criteria) this;
        }

        public Criteria andLbidGreaterThan(Integer value) {
            addCriterion("lbid >", value, "lbid");
            return (Criteria) this;
        }

        public Criteria andLbidGreaterThanOrEqualTo(Integer value) {
            addCriterion("lbid >=", value, "lbid");
            return (Criteria) this;
        }

        public Criteria andLbidLessThan(Integer value) {
            addCriterion("lbid <", value, "lbid");
            return (Criteria) this;
        }

        public Criteria andLbidLessThanOrEqualTo(Integer value) {
            addCriterion("lbid <=", value, "lbid");
            return (Criteria) this;
        }

        public Criteria andLbidIn(List<Integer> values) {
            addCriterion("lbid in", values, "lbid");
            return (Criteria) this;
        }

        public Criteria andLbidNotIn(List<Integer> values) {
            addCriterion("lbid not in", values, "lbid");
            return (Criteria) this;
        }

        public Criteria andLbidBetween(Integer value1, Integer value2) {
            addCriterion("lbid between", value1, value2, "lbid");
            return (Criteria) this;
        }

        public Criteria andLbidNotBetween(Integer value1, Integer value2) {
            addCriterion("lbid not between", value1, value2, "lbid");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("userId is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userId is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(Integer value) {
            addCriterion("userId =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(Integer value) {
            addCriterion("userId <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(Integer value) {
            addCriterion("userId >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("userId >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(Integer value) {
            addCriterion("userId <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(Integer value) {
            addCriterion("userId <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<Integer> values) {
            addCriterion("userId in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<Integer> values) {
            addCriterion("userId not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(Integer value1, Integer value2) {
            addCriterion("userId between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(Integer value1, Integer value2) {
            addCriterion("userId not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(String value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(String value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(String value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(String value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(String value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(String value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLike(String value) {
            addCriterion("createtime like", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotLike(String value) {
            addCriterion("createtime not like", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<String> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<String> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(String value1, String value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(String value1, String value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andMomeyIsNull() {
            addCriterion("momey is null");
            return (Criteria) this;
        }

        public Criteria andMomeyIsNotNull() {
            addCriterion("momey is not null");
            return (Criteria) this;
        }

        public Criteria andMomeyEqualTo(String value) {
            addCriterion("momey =", value, "momey");
            return (Criteria) this;
        }

        public Criteria andMomeyNotEqualTo(String value) {
            addCriterion("momey <>", value, "momey");
            return (Criteria) this;
        }

        public Criteria andMomeyGreaterThan(String value) {
            addCriterion("momey >", value, "momey");
            return (Criteria) this;
        }

        public Criteria andMomeyGreaterThanOrEqualTo(String value) {
            addCriterion("momey >=", value, "momey");
            return (Criteria) this;
        }

        public Criteria andMomeyLessThan(String value) {
            addCriterion("momey <", value, "momey");
            return (Criteria) this;
        }

        public Criteria andMomeyLessThanOrEqualTo(String value) {
            addCriterion("momey <=", value, "momey");
            return (Criteria) this;
        }

        public Criteria andMomeyLike(String value) {
            addCriterion("momey like", value, "momey");
            return (Criteria) this;
        }

        public Criteria andMomeyNotLike(String value) {
            addCriterion("momey not like", value, "momey");
            return (Criteria) this;
        }

        public Criteria andMomeyIn(List<String> values) {
            addCriterion("momey in", values, "momey");
            return (Criteria) this;
        }

        public Criteria andMomeyNotIn(List<String> values) {
            addCriterion("momey not in", values, "momey");
            return (Criteria) this;
        }

        public Criteria andMomeyBetween(String value1, String value2) {
            addCriterion("momey between", value1, value2, "momey");
            return (Criteria) this;
        }

        public Criteria andMomeyNotBetween(String value1, String value2) {
            addCriterion("momey not between", value1, value2, "momey");
            return (Criteria) this;
        }

        public Criteria andSubstitutesoneIsNull() {
            addCriterion("substitutesone is null");
            return (Criteria) this;
        }

        public Criteria andSubstitutesoneIsNotNull() {
            addCriterion("substitutesone is not null");
            return (Criteria) this;
        }

        public Criteria andSubstitutesoneEqualTo(String value) {
            addCriterion("substitutesone =", value, "substitutesone");
            return (Criteria) this;
        }

        public Criteria andSubstitutesoneNotEqualTo(String value) {
            addCriterion("substitutesone <>", value, "substitutesone");
            return (Criteria) this;
        }

        public Criteria andSubstitutesoneGreaterThan(String value) {
            addCriterion("substitutesone >", value, "substitutesone");
            return (Criteria) this;
        }

        public Criteria andSubstitutesoneGreaterThanOrEqualTo(String value) {
            addCriterion("substitutesone >=", value, "substitutesone");
            return (Criteria) this;
        }

        public Criteria andSubstitutesoneLessThan(String value) {
            addCriterion("substitutesone <", value, "substitutesone");
            return (Criteria) this;
        }

        public Criteria andSubstitutesoneLessThanOrEqualTo(String value) {
            addCriterion("substitutesone <=", value, "substitutesone");
            return (Criteria) this;
        }

        public Criteria andSubstitutesoneLike(String value) {
            addCriterion("substitutesone like", value, "substitutesone");
            return (Criteria) this;
        }

        public Criteria andSubstitutesoneNotLike(String value) {
            addCriterion("substitutesone not like", value, "substitutesone");
            return (Criteria) this;
        }

        public Criteria andSubstitutesoneIn(List<String> values) {
            addCriterion("substitutesone in", values, "substitutesone");
            return (Criteria) this;
        }

        public Criteria andSubstitutesoneNotIn(List<String> values) {
            addCriterion("substitutesone not in", values, "substitutesone");
            return (Criteria) this;
        }

        public Criteria andSubstitutesoneBetween(String value1, String value2) {
            addCriterion("substitutesone between", value1, value2, "substitutesone");
            return (Criteria) this;
        }

        public Criteria andSubstitutesoneNotBetween(String value1, String value2) {
            addCriterion("substitutesone not between", value1, value2, "substitutesone");
            return (Criteria) this;
        }

        public Criteria andSubstitutestwoIsNull() {
            addCriterion("substitutestwo is null");
            return (Criteria) this;
        }

        public Criteria andSubstitutestwoIsNotNull() {
            addCriterion("substitutestwo is not null");
            return (Criteria) this;
        }

        public Criteria andSubstitutestwoEqualTo(String value) {
            addCriterion("substitutestwo =", value, "substitutestwo");
            return (Criteria) this;
        }

        public Criteria andSubstitutestwoNotEqualTo(String value) {
            addCriterion("substitutestwo <>", value, "substitutestwo");
            return (Criteria) this;
        }

        public Criteria andSubstitutestwoGreaterThan(String value) {
            addCriterion("substitutestwo >", value, "substitutestwo");
            return (Criteria) this;
        }

        public Criteria andSubstitutestwoGreaterThanOrEqualTo(String value) {
            addCriterion("substitutestwo >=", value, "substitutestwo");
            return (Criteria) this;
        }

        public Criteria andSubstitutestwoLessThan(String value) {
            addCriterion("substitutestwo <", value, "substitutestwo");
            return (Criteria) this;
        }

        public Criteria andSubstitutestwoLessThanOrEqualTo(String value) {
            addCriterion("substitutestwo <=", value, "substitutestwo");
            return (Criteria) this;
        }

        public Criteria andSubstitutestwoLike(String value) {
            addCriterion("substitutestwo like", value, "substitutestwo");
            return (Criteria) this;
        }

        public Criteria andSubstitutestwoNotLike(String value) {
            addCriterion("substitutestwo not like", value, "substitutestwo");
            return (Criteria) this;
        }

        public Criteria andSubstitutestwoIn(List<String> values) {
            addCriterion("substitutestwo in", values, "substitutestwo");
            return (Criteria) this;
        }

        public Criteria andSubstitutestwoNotIn(List<String> values) {
            addCriterion("substitutestwo not in", values, "substitutestwo");
            return (Criteria) this;
        }

        public Criteria andSubstitutestwoBetween(String value1, String value2) {
            addCriterion("substitutestwo between", value1, value2, "substitutestwo");
            return (Criteria) this;
        }

        public Criteria andSubstitutestwoNotBetween(String value1, String value2) {
            addCriterion("substitutestwo not between", value1, value2, "substitutestwo");
            return (Criteria) this;
        }

        public Criteria andOrderidIsNull() {
            addCriterion("orderID is null");
            return (Criteria) this;
        }

        public Criteria andOrderidIsNotNull() {
            addCriterion("orderID is not null");
            return (Criteria) this;
        }

        public Criteria andOrderidEqualTo(String value) {
            addCriterion("orderID =", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotEqualTo(String value) {
            addCriterion("orderID <>", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThan(String value) {
            addCriterion("orderID >", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThanOrEqualTo(String value) {
            addCriterion("orderID >=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThan(String value) {
            addCriterion("orderID <", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThanOrEqualTo(String value) {
            addCriterion("orderID <=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLike(String value) {
            addCriterion("orderID like", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotLike(String value) {
            addCriterion("orderID not like", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidIn(List<String> values) {
            addCriterion("orderID in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotIn(List<String> values) {
            addCriterion("orderID not in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidBetween(String value1, String value2) {
            addCriterion("orderID between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotBetween(String value1, String value2) {
            addCriterion("orderID not between", value1, value2, "orderid");
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

        public Criteria andBalanceIsNull() {
            addCriterion("balance is null");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNotNull() {
            addCriterion("balance is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceEqualTo(String value) {
            addCriterion("balance =", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotEqualTo(String value) {
            addCriterion("balance <>", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThan(String value) {
            addCriterion("balance >", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThanOrEqualTo(String value) {
            addCriterion("balance >=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThan(String value) {
            addCriterion("balance <", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThanOrEqualTo(String value) {
            addCriterion("balance <=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLike(String value) {
            addCriterion("balance like", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotLike(String value) {
            addCriterion("balance not like", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceIn(List<String> values) {
            addCriterion("balance in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotIn(List<String> values) {
            addCriterion("balance not in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceBetween(String value1, String value2) {
            addCriterion("balance between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotBetween(String value1, String value2) {
            addCriterion("balance not between", value1, value2, "balance");
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