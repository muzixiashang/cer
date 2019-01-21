package com.liyunet.domain.bet;

import java.util.ArrayList;
import java.util.List;

public class AppControlExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AppControlExample() {
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

        public Criteria andEggStatusIsNull() {
            addCriterion("egg_status is null");
            return (Criteria) this;
        }

        public Criteria andEggStatusIsNotNull() {
            addCriterion("egg_status is not null");
            return (Criteria) this;
        }

        public Criteria andEggStatusEqualTo(Integer value) {
            addCriterion("egg_status =", value, "eggStatus");
            return (Criteria) this;
        }

        public Criteria andEggStatusNotEqualTo(Integer value) {
            addCriterion("egg_status <>", value, "eggStatus");
            return (Criteria) this;
        }

        public Criteria andEggStatusGreaterThan(Integer value) {
            addCriterion("egg_status >", value, "eggStatus");
            return (Criteria) this;
        }

        public Criteria andEggStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("egg_status >=", value, "eggStatus");
            return (Criteria) this;
        }

        public Criteria andEggStatusLessThan(Integer value) {
            addCriterion("egg_status <", value, "eggStatus");
            return (Criteria) this;
        }

        public Criteria andEggStatusLessThanOrEqualTo(Integer value) {
            addCriterion("egg_status <=", value, "eggStatus");
            return (Criteria) this;
        }

        public Criteria andEggStatusIn(List<Integer> values) {
            addCriterion("egg_status in", values, "eggStatus");
            return (Criteria) this;
        }

        public Criteria andEggStatusNotIn(List<Integer> values) {
            addCriterion("egg_status not in", values, "eggStatus");
            return (Criteria) this;
        }

        public Criteria andEggStatusBetween(Integer value1, Integer value2) {
            addCriterion("egg_status between", value1, value2, "eggStatus");
            return (Criteria) this;
        }

        public Criteria andEggStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("egg_status not between", value1, value2, "eggStatus");
            return (Criteria) this;
        }

        public Criteria andServerStatusIsNull() {
            addCriterion("server_status is null");
            return (Criteria) this;
        }

        public Criteria andServerStatusIsNotNull() {
            addCriterion("server_status is not null");
            return (Criteria) this;
        }

        public Criteria andServerStatusEqualTo(Integer value) {
            addCriterion("server_status =", value, "serverStatus");
            return (Criteria) this;
        }

        public Criteria andServerStatusNotEqualTo(Integer value) {
            addCriterion("server_status <>", value, "serverStatus");
            return (Criteria) this;
        }

        public Criteria andServerStatusGreaterThan(Integer value) {
            addCriterion("server_status >", value, "serverStatus");
            return (Criteria) this;
        }

        public Criteria andServerStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("server_status >=", value, "serverStatus");
            return (Criteria) this;
        }

        public Criteria andServerStatusLessThan(Integer value) {
            addCriterion("server_status <", value, "serverStatus");
            return (Criteria) this;
        }

        public Criteria andServerStatusLessThanOrEqualTo(Integer value) {
            addCriterion("server_status <=", value, "serverStatus");
            return (Criteria) this;
        }

        public Criteria andServerStatusIn(List<Integer> values) {
            addCriterion("server_status in", values, "serverStatus");
            return (Criteria) this;
        }

        public Criteria andServerStatusNotIn(List<Integer> values) {
            addCriterion("server_status not in", values, "serverStatus");
            return (Criteria) this;
        }

        public Criteria andServerStatusBetween(Integer value1, Integer value2) {
            addCriterion("server_status between", value1, value2, "serverStatus");
            return (Criteria) this;
        }

        public Criteria andServerStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("server_status not between", value1, value2, "serverStatus");
            return (Criteria) this;
        }

        public Criteria andServerContextIsNull() {
            addCriterion("server_context is null");
            return (Criteria) this;
        }

        public Criteria andServerContextIsNotNull() {
            addCriterion("server_context is not null");
            return (Criteria) this;
        }

        public Criteria andServerContextEqualTo(String value) {
            addCriterion("server_context =", value, "serverContext");
            return (Criteria) this;
        }

        public Criteria andServerContextNotEqualTo(String value) {
            addCriterion("server_context <>", value, "serverContext");
            return (Criteria) this;
        }

        public Criteria andServerContextGreaterThan(String value) {
            addCriterion("server_context >", value, "serverContext");
            return (Criteria) this;
        }

        public Criteria andServerContextGreaterThanOrEqualTo(String value) {
            addCriterion("server_context >=", value, "serverContext");
            return (Criteria) this;
        }

        public Criteria andServerContextLessThan(String value) {
            addCriterion("server_context <", value, "serverContext");
            return (Criteria) this;
        }

        public Criteria andServerContextLessThanOrEqualTo(String value) {
            addCriterion("server_context <=", value, "serverContext");
            return (Criteria) this;
        }

        public Criteria andServerContextLike(String value) {
            addCriterion("server_context like", value, "serverContext");
            return (Criteria) this;
        }

        public Criteria andServerContextNotLike(String value) {
            addCriterion("server_context not like", value, "serverContext");
            return (Criteria) this;
        }

        public Criteria andServerContextIn(List<String> values) {
            addCriterion("server_context in", values, "serverContext");
            return (Criteria) this;
        }

        public Criteria andServerContextNotIn(List<String> values) {
            addCriterion("server_context not in", values, "serverContext");
            return (Criteria) this;
        }

        public Criteria andServerContextBetween(String value1, String value2) {
            addCriterion("server_context between", value1, value2, "serverContext");
            return (Criteria) this;
        }

        public Criteria andServerContextNotBetween(String value1, String value2) {
            addCriterion("server_context not between", value1, value2, "serverContext");
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