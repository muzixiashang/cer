package com.liyunet.domain.hk;

import java.util.ArrayList;
import java.util.List;

public class CoinHkProjetExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CoinHkProjetExample() {
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

        public Criteria andProjectNameIsNull() {
            addCriterion("project_name is null");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNotNull() {
            addCriterion("project_name is not null");
            return (Criteria) this;
        }

        public Criteria andProjectNameEqualTo(String value) {
            addCriterion("project_name =", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotEqualTo(String value) {
            addCriterion("project_name <>", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThan(String value) {
            addCriterion("project_name >", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("project_name >=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThan(String value) {
            addCriterion("project_name <", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThanOrEqualTo(String value) {
            addCriterion("project_name <=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLike(String value) {
            addCriterion("project_name like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotLike(String value) {
            addCriterion("project_name not like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameIn(List<String> values) {
            addCriterion("project_name in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotIn(List<String> values) {
            addCriterion("project_name not in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameBetween(String value1, String value2) {
            addCriterion("project_name between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotBetween(String value1, String value2) {
            addCriterion("project_name not between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectCycleIsNull() {
            addCriterion("project_cycle is null");
            return (Criteria) this;
        }

        public Criteria andProjectCycleIsNotNull() {
            addCriterion("project_cycle is not null");
            return (Criteria) this;
        }

        public Criteria andProjectCycleEqualTo(String value) {
            addCriterion("project_cycle =", value, "projectCycle");
            return (Criteria) this;
        }

        public Criteria andProjectCycleNotEqualTo(String value) {
            addCriterion("project_cycle <>", value, "projectCycle");
            return (Criteria) this;
        }

        public Criteria andProjectCycleGreaterThan(String value) {
            addCriterion("project_cycle >", value, "projectCycle");
            return (Criteria) this;
        }

        public Criteria andProjectCycleGreaterThanOrEqualTo(String value) {
            addCriterion("project_cycle >=", value, "projectCycle");
            return (Criteria) this;
        }

        public Criteria andProjectCycleLessThan(String value) {
            addCriterion("project_cycle <", value, "projectCycle");
            return (Criteria) this;
        }

        public Criteria andProjectCycleLessThanOrEqualTo(String value) {
            addCriterion("project_cycle <=", value, "projectCycle");
            return (Criteria) this;
        }

        public Criteria andProjectCycleLike(String value) {
            addCriterion("project_cycle like", value, "projectCycle");
            return (Criteria) this;
        }

        public Criteria andProjectCycleNotLike(String value) {
            addCriterion("project_cycle not like", value, "projectCycle");
            return (Criteria) this;
        }

        public Criteria andProjectCycleIn(List<String> values) {
            addCriterion("project_cycle in", values, "projectCycle");
            return (Criteria) this;
        }

        public Criteria andProjectCycleNotIn(List<String> values) {
            addCriterion("project_cycle not in", values, "projectCycle");
            return (Criteria) this;
        }

        public Criteria andProjectCycleBetween(String value1, String value2) {
            addCriterion("project_cycle between", value1, value2, "projectCycle");
            return (Criteria) this;
        }

        public Criteria andProjectCycleNotBetween(String value1, String value2) {
            addCriterion("project_cycle not between", value1, value2, "projectCycle");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(String value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(String value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(String value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(String value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(String value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLike(String value) {
            addCriterion("start_time like", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotLike(String value) {
            addCriterion("start_time not like", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<String> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<String> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(String value1, String value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(String value1, String value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(String value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(String value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(String value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(String value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(String value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLike(String value) {
            addCriterion("end_time like", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotLike(String value) {
            addCriterion("end_time not like", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<String> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<String> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(String value1, String value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(String value1, String value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andProfitIsNull() {
            addCriterion("profit is null");
            return (Criteria) this;
        }

        public Criteria andProfitIsNotNull() {
            addCriterion("profit is not null");
            return (Criteria) this;
        }

        public Criteria andProfitEqualTo(String value) {
            addCriterion("profit =", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitNotEqualTo(String value) {
            addCriterion("profit <>", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitGreaterThan(String value) {
            addCriterion("profit >", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitGreaterThanOrEqualTo(String value) {
            addCriterion("profit >=", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitLessThan(String value) {
            addCriterion("profit <", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitLessThanOrEqualTo(String value) {
            addCriterion("profit <=", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitLike(String value) {
            addCriterion("profit like", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitNotLike(String value) {
            addCriterion("profit not like", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitIn(List<String> values) {
            addCriterion("profit in", values, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitNotIn(List<String> values) {
            addCriterion("profit not in", values, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitBetween(String value1, String value2) {
            addCriterion("profit between", value1, value2, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitNotBetween(String value1, String value2) {
            addCriterion("profit not between", value1, value2, "profit");
            return (Criteria) this;
        }

        public Criteria andMinQuotaIsNull() {
            addCriterion("min_quota is null");
            return (Criteria) this;
        }

        public Criteria andMinQuotaIsNotNull() {
            addCriterion("min_quota is not null");
            return (Criteria) this;
        }

        public Criteria andMinQuotaEqualTo(String value) {
            addCriterion("min_quota =", value, "minQuota");
            return (Criteria) this;
        }

        public Criteria andMinQuotaNotEqualTo(String value) {
            addCriterion("min_quota <>", value, "minQuota");
            return (Criteria) this;
        }

        public Criteria andMinQuotaGreaterThan(String value) {
            addCriterion("min_quota >", value, "minQuota");
            return (Criteria) this;
        }

        public Criteria andMinQuotaGreaterThanOrEqualTo(String value) {
            addCriterion("min_quota >=", value, "minQuota");
            return (Criteria) this;
        }

        public Criteria andMinQuotaLessThan(String value) {
            addCriterion("min_quota <", value, "minQuota");
            return (Criteria) this;
        }

        public Criteria andMinQuotaLessThanOrEqualTo(String value) {
            addCriterion("min_quota <=", value, "minQuota");
            return (Criteria) this;
        }

        public Criteria andMinQuotaLike(String value) {
            addCriterion("min_quota like", value, "minQuota");
            return (Criteria) this;
        }

        public Criteria andMinQuotaNotLike(String value) {
            addCriterion("min_quota not like", value, "minQuota");
            return (Criteria) this;
        }

        public Criteria andMinQuotaIn(List<String> values) {
            addCriterion("min_quota in", values, "minQuota");
            return (Criteria) this;
        }

        public Criteria andMinQuotaNotIn(List<String> values) {
            addCriterion("min_quota not in", values, "minQuota");
            return (Criteria) this;
        }

        public Criteria andMinQuotaBetween(String value1, String value2) {
            addCriterion("min_quota between", value1, value2, "minQuota");
            return (Criteria) this;
        }

        public Criteria andMinQuotaNotBetween(String value1, String value2) {
            addCriterion("min_quota not between", value1, value2, "minQuota");
            return (Criteria) this;
        }

        public Criteria andMaxQuotaIsNull() {
            addCriterion("max_quota is null");
            return (Criteria) this;
        }

        public Criteria andMaxQuotaIsNotNull() {
            addCriterion("max_quota is not null");
            return (Criteria) this;
        }

        public Criteria andMaxQuotaEqualTo(String value) {
            addCriterion("max_quota =", value, "maxQuota");
            return (Criteria) this;
        }

        public Criteria andMaxQuotaNotEqualTo(String value) {
            addCriterion("max_quota <>", value, "maxQuota");
            return (Criteria) this;
        }

        public Criteria andMaxQuotaGreaterThan(String value) {
            addCriterion("max_quota >", value, "maxQuota");
            return (Criteria) this;
        }

        public Criteria andMaxQuotaGreaterThanOrEqualTo(String value) {
            addCriterion("max_quota >=", value, "maxQuota");
            return (Criteria) this;
        }

        public Criteria andMaxQuotaLessThan(String value) {
            addCriterion("max_quota <", value, "maxQuota");
            return (Criteria) this;
        }

        public Criteria andMaxQuotaLessThanOrEqualTo(String value) {
            addCriterion("max_quota <=", value, "maxQuota");
            return (Criteria) this;
        }

        public Criteria andMaxQuotaLike(String value) {
            addCriterion("max_quota like", value, "maxQuota");
            return (Criteria) this;
        }

        public Criteria andMaxQuotaNotLike(String value) {
            addCriterion("max_quota not like", value, "maxQuota");
            return (Criteria) this;
        }

        public Criteria andMaxQuotaIn(List<String> values) {
            addCriterion("max_quota in", values, "maxQuota");
            return (Criteria) this;
        }

        public Criteria andMaxQuotaNotIn(List<String> values) {
            addCriterion("max_quota not in", values, "maxQuota");
            return (Criteria) this;
        }

        public Criteria andMaxQuotaBetween(String value1, String value2) {
            addCriterion("max_quota between", value1, value2, "maxQuota");
            return (Criteria) this;
        }

        public Criteria andMaxQuotaNotBetween(String value1, String value2) {
            addCriterion("max_quota not between", value1, value2, "maxQuota");
            return (Criteria) this;
        }

        public Criteria andProfitNumIsNull() {
            addCriterion("profit_num is null");
            return (Criteria) this;
        }

        public Criteria andProfitNumIsNotNull() {
            addCriterion("profit_num is not null");
            return (Criteria) this;
        }

        public Criteria andProfitNumEqualTo(String value) {
            addCriterion("profit_num =", value, "profitNum");
            return (Criteria) this;
        }

        public Criteria andProfitNumNotEqualTo(String value) {
            addCriterion("profit_num <>", value, "profitNum");
            return (Criteria) this;
        }

        public Criteria andProfitNumGreaterThan(String value) {
            addCriterion("profit_num >", value, "profitNum");
            return (Criteria) this;
        }

        public Criteria andProfitNumGreaterThanOrEqualTo(String value) {
            addCriterion("profit_num >=", value, "profitNum");
            return (Criteria) this;
        }

        public Criteria andProfitNumLessThan(String value) {
            addCriterion("profit_num <", value, "profitNum");
            return (Criteria) this;
        }

        public Criteria andProfitNumLessThanOrEqualTo(String value) {
            addCriterion("profit_num <=", value, "profitNum");
            return (Criteria) this;
        }

        public Criteria andProfitNumLike(String value) {
            addCriterion("profit_num like", value, "profitNum");
            return (Criteria) this;
        }

        public Criteria andProfitNumNotLike(String value) {
            addCriterion("profit_num not like", value, "profitNum");
            return (Criteria) this;
        }

        public Criteria andProfitNumIn(List<String> values) {
            addCriterion("profit_num in", values, "profitNum");
            return (Criteria) this;
        }

        public Criteria andProfitNumNotIn(List<String> values) {
            addCriterion("profit_num not in", values, "profitNum");
            return (Criteria) this;
        }

        public Criteria andProfitNumBetween(String value1, String value2) {
            addCriterion("profit_num between", value1, value2, "profitNum");
            return (Criteria) this;
        }

        public Criteria andProfitNumNotBetween(String value1, String value2) {
            addCriterion("profit_num not between", value1, value2, "profitNum");
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

        public Criteria andDummyNumIsNull() {
            addCriterion("dummy_num is null");
            return (Criteria) this;
        }

        public Criteria andDummyNumIsNotNull() {
            addCriterion("dummy_num is not null");
            return (Criteria) this;
        }

        public Criteria andDummyNumEqualTo(Integer value) {
            addCriterion("dummy_num =", value, "dummyNum");
            return (Criteria) this;
        }

        public Criteria andDummyNumNotEqualTo(Integer value) {
            addCriterion("dummy_num <>", value, "dummyNum");
            return (Criteria) this;
        }

        public Criteria andDummyNumGreaterThan(Integer value) {
            addCriterion("dummy_num >", value, "dummyNum");
            return (Criteria) this;
        }

        public Criteria andDummyNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("dummy_num >=", value, "dummyNum");
            return (Criteria) this;
        }

        public Criteria andDummyNumLessThan(Integer value) {
            addCriterion("dummy_num <", value, "dummyNum");
            return (Criteria) this;
        }

        public Criteria andDummyNumLessThanOrEqualTo(Integer value) {
            addCriterion("dummy_num <=", value, "dummyNum");
            return (Criteria) this;
        }

        public Criteria andDummyNumIn(List<Integer> values) {
            addCriterion("dummy_num in", values, "dummyNum");
            return (Criteria) this;
        }

        public Criteria andDummyNumNotIn(List<Integer> values) {
            addCriterion("dummy_num not in", values, "dummyNum");
            return (Criteria) this;
        }

        public Criteria andDummyNumBetween(Integer value1, Integer value2) {
            addCriterion("dummy_num between", value1, value2, "dummyNum");
            return (Criteria) this;
        }

        public Criteria andDummyNumNotBetween(Integer value1, Integer value2) {
            addCriterion("dummy_num not between", value1, value2, "dummyNum");
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