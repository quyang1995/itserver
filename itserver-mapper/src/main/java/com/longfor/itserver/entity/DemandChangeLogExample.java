package com.longfor.itserver.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DemandChangeLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DemandChangeLogExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDemandIdIsNull() {
            addCriterion("demand_id is null");
            return (Criteria) this;
        }

        public Criteria andDemandIdIsNotNull() {
            addCriterion("demand_id is not null");
            return (Criteria) this;
        }

        public Criteria andDemandIdEqualTo(Long value) {
            addCriterion("demand_id =", value, "demandId");
            return (Criteria) this;
        }

        public Criteria andDemandIdNotEqualTo(Long value) {
            addCriterion("demand_id <>", value, "demandId");
            return (Criteria) this;
        }

        public Criteria andDemandIdGreaterThan(Long value) {
            addCriterion("demand_id >", value, "demandId");
            return (Criteria) this;
        }

        public Criteria andDemandIdGreaterThanOrEqualTo(Long value) {
            addCriterion("demand_id >=", value, "demandId");
            return (Criteria) this;
        }

        public Criteria andDemandIdLessThan(Long value) {
            addCriterion("demand_id <", value, "demandId");
            return (Criteria) this;
        }

        public Criteria andDemandIdLessThanOrEqualTo(Long value) {
            addCriterion("demand_id <=", value, "demandId");
            return (Criteria) this;
        }

        public Criteria andDemandIdIn(List<Long> values) {
            addCriterion("demand_id in", values, "demandId");
            return (Criteria) this;
        }

        public Criteria andDemandIdNotIn(List<Long> values) {
            addCriterion("demand_id not in", values, "demandId");
            return (Criteria) this;
        }

        public Criteria andDemandIdBetween(Long value1, Long value2) {
            addCriterion("demand_id between", value1, value2, "demandId");
            return (Criteria) this;
        }

        public Criteria andDemandIdNotBetween(Long value1, Long value2) {
            addCriterion("demand_id not between", value1, value2, "demandId");
            return (Criteria) this;
        }

        public Criteria andBefDescpIsNull() {
            addCriterion("bef_descp is null");
            return (Criteria) this;
        }

        public Criteria andBefDescpIsNotNull() {
            addCriterion("bef_descp is not null");
            return (Criteria) this;
        }

        public Criteria andBefDescpEqualTo(String value) {
            addCriterion("bef_descp =", value, "befDescp");
            return (Criteria) this;
        }

        public Criteria andBefDescpNotEqualTo(String value) {
            addCriterion("bef_descp <>", value, "befDescp");
            return (Criteria) this;
        }

        public Criteria andBefDescpGreaterThan(String value) {
            addCriterion("bef_descp >", value, "befDescp");
            return (Criteria) this;
        }

        public Criteria andBefDescpGreaterThanOrEqualTo(String value) {
            addCriterion("bef_descp >=", value, "befDescp");
            return (Criteria) this;
        }

        public Criteria andBefDescpLessThan(String value) {
            addCriterion("bef_descp <", value, "befDescp");
            return (Criteria) this;
        }

        public Criteria andBefDescpLessThanOrEqualTo(String value) {
            addCriterion("bef_descp <=", value, "befDescp");
            return (Criteria) this;
        }

        public Criteria andBefDescpLike(String value) {
            addCriterion("bef_descp like", value, "befDescp");
            return (Criteria) this;
        }

        public Criteria andBefDescpNotLike(String value) {
            addCriterion("bef_descp not like", value, "befDescp");
            return (Criteria) this;
        }

        public Criteria andBefDescpIn(List<String> values) {
            addCriterion("bef_descp in", values, "befDescp");
            return (Criteria) this;
        }

        public Criteria andBefDescpNotIn(List<String> values) {
            addCriterion("bef_descp not in", values, "befDescp");
            return (Criteria) this;
        }

        public Criteria andBefDescpBetween(String value1, String value2) {
            addCriterion("bef_descp between", value1, value2, "befDescp");
            return (Criteria) this;
        }

        public Criteria andBefDescpNotBetween(String value1, String value2) {
            addCriterion("bef_descp not between", value1, value2, "befDescp");
            return (Criteria) this;
        }

        public Criteria andActionChangeInfoIsNull() {
            addCriterion("action_change_info is null");
            return (Criteria) this;
        }

        public Criteria andActionChangeInfoIsNotNull() {
            addCriterion("action_change_info is not null");
            return (Criteria) this;
        }

        public Criteria andActionChangeInfoEqualTo(String value) {
            addCriterion("action_change_info =", value, "actionChangeInfo");
            return (Criteria) this;
        }

        public Criteria andActionChangeInfoNotEqualTo(String value) {
            addCriterion("action_change_info <>", value, "actionChangeInfo");
            return (Criteria) this;
        }

        public Criteria andActionChangeInfoGreaterThan(String value) {
            addCriterion("action_change_info >", value, "actionChangeInfo");
            return (Criteria) this;
        }

        public Criteria andActionChangeInfoGreaterThanOrEqualTo(String value) {
            addCriterion("action_change_info >=", value, "actionChangeInfo");
            return (Criteria) this;
        }

        public Criteria andActionChangeInfoLessThan(String value) {
            addCriterion("action_change_info <", value, "actionChangeInfo");
            return (Criteria) this;
        }

        public Criteria andActionChangeInfoLessThanOrEqualTo(String value) {
            addCriterion("action_change_info <=", value, "actionChangeInfo");
            return (Criteria) this;
        }

        public Criteria andActionChangeInfoLike(String value) {
            addCriterion("action_change_info like", value, "actionChangeInfo");
            return (Criteria) this;
        }

        public Criteria andActionChangeInfoNotLike(String value) {
            addCriterion("action_change_info not like", value, "actionChangeInfo");
            return (Criteria) this;
        }

        public Criteria andActionChangeInfoIn(List<String> values) {
            addCriterion("action_change_info in", values, "actionChangeInfo");
            return (Criteria) this;
        }

        public Criteria andActionChangeInfoNotIn(List<String> values) {
            addCriterion("action_change_info not in", values, "actionChangeInfo");
            return (Criteria) this;
        }

        public Criteria andActionChangeInfoBetween(String value1, String value2) {
            addCriterion("action_change_info between", value1, value2, "actionChangeInfo");
            return (Criteria) this;
        }

        public Criteria andActionChangeInfoNotBetween(String value1, String value2) {
            addCriterion("action_change_info not between", value1, value2, "actionChangeInfo");
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

        public Criteria andModifiedAccountIdIsNull() {
            addCriterion("modified_account_id is null");
            return (Criteria) this;
        }

        public Criteria andModifiedAccountIdIsNotNull() {
            addCriterion("modified_account_id is not null");
            return (Criteria) this;
        }

        public Criteria andModifiedAccountIdEqualTo(String value) {
            addCriterion("modified_account_id =", value, "modifiedAccountId");
            return (Criteria) this;
        }

        public Criteria andModifiedAccountIdNotEqualTo(String value) {
            addCriterion("modified_account_id <>", value, "modifiedAccountId");
            return (Criteria) this;
        }

        public Criteria andModifiedAccountIdGreaterThan(String value) {
            addCriterion("modified_account_id >", value, "modifiedAccountId");
            return (Criteria) this;
        }

        public Criteria andModifiedAccountIdGreaterThanOrEqualTo(String value) {
            addCriterion("modified_account_id >=", value, "modifiedAccountId");
            return (Criteria) this;
        }

        public Criteria andModifiedAccountIdLessThan(String value) {
            addCriterion("modified_account_id <", value, "modifiedAccountId");
            return (Criteria) this;
        }

        public Criteria andModifiedAccountIdLessThanOrEqualTo(String value) {
            addCriterion("modified_account_id <=", value, "modifiedAccountId");
            return (Criteria) this;
        }

        public Criteria andModifiedAccountIdLike(String value) {
            addCriterion("modified_account_id like", value, "modifiedAccountId");
            return (Criteria) this;
        }

        public Criteria andModifiedAccountIdNotLike(String value) {
            addCriterion("modified_account_id not like", value, "modifiedAccountId");
            return (Criteria) this;
        }

        public Criteria andModifiedAccountIdIn(List<String> values) {
            addCriterion("modified_account_id in", values, "modifiedAccountId");
            return (Criteria) this;
        }

        public Criteria andModifiedAccountIdNotIn(List<String> values) {
            addCriterion("modified_account_id not in", values, "modifiedAccountId");
            return (Criteria) this;
        }

        public Criteria andModifiedAccountIdBetween(String value1, String value2) {
            addCriterion("modified_account_id between", value1, value2, "modifiedAccountId");
            return (Criteria) this;
        }

        public Criteria andModifiedAccountIdNotBetween(String value1, String value2) {
            addCriterion("modified_account_id not between", value1, value2, "modifiedAccountId");
            return (Criteria) this;
        }

        public Criteria andModifiedNameIsNull() {
            addCriterion("modified_name is null");
            return (Criteria) this;
        }

        public Criteria andModifiedNameIsNotNull() {
            addCriterion("modified_name is not null");
            return (Criteria) this;
        }

        public Criteria andModifiedNameEqualTo(String value) {
            addCriterion("modified_name =", value, "modifiedName");
            return (Criteria) this;
        }

        public Criteria andModifiedNameNotEqualTo(String value) {
            addCriterion("modified_name <>", value, "modifiedName");
            return (Criteria) this;
        }

        public Criteria andModifiedNameGreaterThan(String value) {
            addCriterion("modified_name >", value, "modifiedName");
            return (Criteria) this;
        }

        public Criteria andModifiedNameGreaterThanOrEqualTo(String value) {
            addCriterion("modified_name >=", value, "modifiedName");
            return (Criteria) this;
        }

        public Criteria andModifiedNameLessThan(String value) {
            addCriterion("modified_name <", value, "modifiedName");
            return (Criteria) this;
        }

        public Criteria andModifiedNameLessThanOrEqualTo(String value) {
            addCriterion("modified_name <=", value, "modifiedName");
            return (Criteria) this;
        }

        public Criteria andModifiedNameLike(String value) {
            addCriterion("modified_name like", value, "modifiedName");
            return (Criteria) this;
        }

        public Criteria andModifiedNameNotLike(String value) {
            addCriterion("modified_name not like", value, "modifiedName");
            return (Criteria) this;
        }

        public Criteria andModifiedNameIn(List<String> values) {
            addCriterion("modified_name in", values, "modifiedName");
            return (Criteria) this;
        }

        public Criteria andModifiedNameNotIn(List<String> values) {
            addCriterion("modified_name not in", values, "modifiedName");
            return (Criteria) this;
        }

        public Criteria andModifiedNameBetween(String value1, String value2) {
            addCriterion("modified_name between", value1, value2, "modifiedName");
            return (Criteria) this;
        }

        public Criteria andModifiedNameNotBetween(String value1, String value2) {
            addCriterion("modified_name not between", value1, value2, "modifiedName");
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

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeIsNull() {
            addCriterion("modified_time is null");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeIsNotNull() {
            addCriterion("modified_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeEqualTo(Date value) {
            addCriterion("modified_time =", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeNotEqualTo(Date value) {
            addCriterion("modified_time <>", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeGreaterThan(Date value) {
            addCriterion("modified_time >", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modified_time >=", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeLessThan(Date value) {
            addCriterion("modified_time <", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeLessThanOrEqualTo(Date value) {
            addCriterion("modified_time <=", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeIn(List<Date> values) {
            addCriterion("modified_time in", values, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeNotIn(List<Date> values) {
            addCriterion("modified_time not in", values, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeBetween(Date value1, Date value2) {
            addCriterion("modified_time between", value1, value2, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeNotBetween(Date value1, Date value2) {
            addCriterion("modified_time not between", value1, value2, "modifiedTime");
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