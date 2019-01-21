package com.liyunet.domain.hk;

import java.util.ArrayList;
import java.util.List;

public class CoinHkControlExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CoinHkControlExample() {
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

        public Criteria andTimeControlIsNull() {
            addCriterion("time_control is null");
            return (Criteria) this;
        }

        public Criteria andTimeControlIsNotNull() {
            addCriterion("time_control is not null");
            return (Criteria) this;
        }

        public Criteria andTimeControlEqualTo(String value) {
            addCriterion("time_control =", value, "timeControl");
            return (Criteria) this;
        }

        public Criteria andTimeControlNotEqualTo(String value) {
            addCriterion("time_control <>", value, "timeControl");
            return (Criteria) this;
        }

        public Criteria andTimeControlGreaterThan(String value) {
            addCriterion("time_control >", value, "timeControl");
            return (Criteria) this;
        }

        public Criteria andTimeControlGreaterThanOrEqualTo(String value) {
            addCriterion("time_control >=", value, "timeControl");
            return (Criteria) this;
        }

        public Criteria andTimeControlLessThan(String value) {
            addCriterion("time_control <", value, "timeControl");
            return (Criteria) this;
        }

        public Criteria andTimeControlLessThanOrEqualTo(String value) {
            addCriterion("time_control <=", value, "timeControl");
            return (Criteria) this;
        }

        public Criteria andTimeControlLike(String value) {
            addCriterion("time_control like", value, "timeControl");
            return (Criteria) this;
        }

        public Criteria andTimeControlNotLike(String value) {
            addCriterion("time_control not like", value, "timeControl");
            return (Criteria) this;
        }

        public Criteria andTimeControlIn(List<String> values) {
            addCriterion("time_control in", values, "timeControl");
            return (Criteria) this;
        }

        public Criteria andTimeControlNotIn(List<String> values) {
            addCriterion("time_control not in", values, "timeControl");
            return (Criteria) this;
        }

        public Criteria andTimeControlBetween(String value1, String value2) {
            addCriterion("time_control between", value1, value2, "timeControl");
            return (Criteria) this;
        }

        public Criteria andTimeControlNotBetween(String value1, String value2) {
            addCriterion("time_control not between", value1, value2, "timeControl");
            return (Criteria) this;
        }

        public Criteria andDumControlIsNull() {
            addCriterion("dum_control is null");
            return (Criteria) this;
        }

        public Criteria andDumControlIsNotNull() {
            addCriterion("dum_control is not null");
            return (Criteria) this;
        }

        public Criteria andDumControlEqualTo(String value) {
            addCriterion("dum_control =", value, "dumControl");
            return (Criteria) this;
        }

        public Criteria andDumControlNotEqualTo(String value) {
            addCriterion("dum_control <>", value, "dumControl");
            return (Criteria) this;
        }

        public Criteria andDumControlGreaterThan(String value) {
            addCriterion("dum_control >", value, "dumControl");
            return (Criteria) this;
        }

        public Criteria andDumControlGreaterThanOrEqualTo(String value) {
            addCriterion("dum_control >=", value, "dumControl");
            return (Criteria) this;
        }

        public Criteria andDumControlLessThan(String value) {
            addCriterion("dum_control <", value, "dumControl");
            return (Criteria) this;
        }

        public Criteria andDumControlLessThanOrEqualTo(String value) {
            addCriterion("dum_control <=", value, "dumControl");
            return (Criteria) this;
        }

        public Criteria andDumControlLike(String value) {
            addCriterion("dum_control like", value, "dumControl");
            return (Criteria) this;
        }

        public Criteria andDumControlNotLike(String value) {
            addCriterion("dum_control not like", value, "dumControl");
            return (Criteria) this;
        }

        public Criteria andDumControlIn(List<String> values) {
            addCriterion("dum_control in", values, "dumControl");
            return (Criteria) this;
        }

        public Criteria andDumControlNotIn(List<String> values) {
            addCriterion("dum_control not in", values, "dumControl");
            return (Criteria) this;
        }

        public Criteria andDumControlBetween(String value1, String value2) {
            addCriterion("dum_control between", value1, value2, "dumControl");
            return (Criteria) this;
        }

        public Criteria andDumControlNotBetween(String value1, String value2) {
            addCriterion("dum_control not between", value1, value2, "dumControl");
            return (Criteria) this;
        }

        public Criteria andElseoneControlIsNull() {
            addCriterion("elseone_control is null");
            return (Criteria) this;
        }

        public Criteria andElseoneControlIsNotNull() {
            addCriterion("elseone_control is not null");
            return (Criteria) this;
        }

        public Criteria andElseoneControlEqualTo(String value) {
            addCriterion("elseone_control =", value, "elseoneControl");
            return (Criteria) this;
        }

        public Criteria andElseoneControlNotEqualTo(String value) {
            addCriterion("elseone_control <>", value, "elseoneControl");
            return (Criteria) this;
        }

        public Criteria andElseoneControlGreaterThan(String value) {
            addCriterion("elseone_control >", value, "elseoneControl");
            return (Criteria) this;
        }

        public Criteria andElseoneControlGreaterThanOrEqualTo(String value) {
            addCriterion("elseone_control >=", value, "elseoneControl");
            return (Criteria) this;
        }

        public Criteria andElseoneControlLessThan(String value) {
            addCriterion("elseone_control <", value, "elseoneControl");
            return (Criteria) this;
        }

        public Criteria andElseoneControlLessThanOrEqualTo(String value) {
            addCriterion("elseone_control <=", value, "elseoneControl");
            return (Criteria) this;
        }

        public Criteria andElseoneControlLike(String value) {
            addCriterion("elseone_control like", value, "elseoneControl");
            return (Criteria) this;
        }

        public Criteria andElseoneControlNotLike(String value) {
            addCriterion("elseone_control not like", value, "elseoneControl");
            return (Criteria) this;
        }

        public Criteria andElseoneControlIn(List<String> values) {
            addCriterion("elseone_control in", values, "elseoneControl");
            return (Criteria) this;
        }

        public Criteria andElseoneControlNotIn(List<String> values) {
            addCriterion("elseone_control not in", values, "elseoneControl");
            return (Criteria) this;
        }

        public Criteria andElseoneControlBetween(String value1, String value2) {
            addCriterion("elseone_control between", value1, value2, "elseoneControl");
            return (Criteria) this;
        }

        public Criteria andElseoneControlNotBetween(String value1, String value2) {
            addCriterion("elseone_control not between", value1, value2, "elseoneControl");
            return (Criteria) this;
        }

        public Criteria andElsetwoControlIsNull() {
            addCriterion("elsetwo_control is null");
            return (Criteria) this;
        }

        public Criteria andElsetwoControlIsNotNull() {
            addCriterion("elsetwo_control is not null");
            return (Criteria) this;
        }

        public Criteria andElsetwoControlEqualTo(String value) {
            addCriterion("elsetwo_control =", value, "elsetwoControl");
            return (Criteria) this;
        }

        public Criteria andElsetwoControlNotEqualTo(String value) {
            addCriterion("elsetwo_control <>", value, "elsetwoControl");
            return (Criteria) this;
        }

        public Criteria andElsetwoControlGreaterThan(String value) {
            addCriterion("elsetwo_control >", value, "elsetwoControl");
            return (Criteria) this;
        }

        public Criteria andElsetwoControlGreaterThanOrEqualTo(String value) {
            addCriterion("elsetwo_control >=", value, "elsetwoControl");
            return (Criteria) this;
        }

        public Criteria andElsetwoControlLessThan(String value) {
            addCriterion("elsetwo_control <", value, "elsetwoControl");
            return (Criteria) this;
        }

        public Criteria andElsetwoControlLessThanOrEqualTo(String value) {
            addCriterion("elsetwo_control <=", value, "elsetwoControl");
            return (Criteria) this;
        }

        public Criteria andElsetwoControlLike(String value) {
            addCriterion("elsetwo_control like", value, "elsetwoControl");
            return (Criteria) this;
        }

        public Criteria andElsetwoControlNotLike(String value) {
            addCriterion("elsetwo_control not like", value, "elsetwoControl");
            return (Criteria) this;
        }

        public Criteria andElsetwoControlIn(List<String> values) {
            addCriterion("elsetwo_control in", values, "elsetwoControl");
            return (Criteria) this;
        }

        public Criteria andElsetwoControlNotIn(List<String> values) {
            addCriterion("elsetwo_control not in", values, "elsetwoControl");
            return (Criteria) this;
        }

        public Criteria andElsetwoControlBetween(String value1, String value2) {
            addCriterion("elsetwo_control between", value1, value2, "elsetwoControl");
            return (Criteria) this;
        }

        public Criteria andElsetwoControlNotBetween(String value1, String value2) {
            addCriterion("elsetwo_control not between", value1, value2, "elsetwoControl");
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