package com.longfor.itserver.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BugInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BugInfoExample() {
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

        public Criteria andFeedBackIdIsNull() {
            addCriterion("feed_back_id is null");
            return (Criteria) this;
        }

        public Criteria andFeedBackIdIsNotNull() {
            addCriterion("feed_back_id is not null");
            return (Criteria) this;
        }

        public Criteria andFeedBackIdEqualTo(Long value) {
            addCriterion("feed_back_id =", value, "feedBackId");
            return (Criteria) this;
        }

        public Criteria andFeedBackIdNotEqualTo(Long value) {
            addCriterion("feed_back_id <>", value, "feedBackId");
            return (Criteria) this;
        }

        public Criteria andFeedBackIdGreaterThan(Long value) {
            addCriterion("feed_back_id >", value, "feedBackId");
            return (Criteria) this;
        }

        public Criteria andFeedBackIdGreaterThanOrEqualTo(Long value) {
            addCriterion("feed_back_id >=", value, "feedBackId");
            return (Criteria) this;
        }

        public Criteria andFeedBackIdLessThan(Long value) {
            addCriterion("feed_back_id <", value, "feedBackId");
            return (Criteria) this;
        }

        public Criteria andFeedBackIdLessThanOrEqualTo(Long value) {
            addCriterion("feed_back_id <=", value, "feedBackId");
            return (Criteria) this;
        }

        public Criteria andFeedBackIdIn(List<Long> values) {
            addCriterion("feed_back_id in", values, "feedBackId");
            return (Criteria) this;
        }

        public Criteria andFeedBackIdNotIn(List<Long> values) {
            addCriterion("feed_back_id not in", values, "feedBackId");
            return (Criteria) this;
        }

        public Criteria andFeedBackIdBetween(Long value1, Long value2) {
            addCriterion("feed_back_id between", value1, value2, "feedBackId");
            return (Criteria) this;
        }

        public Criteria andFeedBackIdNotBetween(Long value1, Long value2) {
            addCriterion("feed_back_id not between", value1, value2, "feedBackId");
            return (Criteria) this;
        }

        public Criteria andRelationIdIsNull() {
            addCriterion("relation_id is null");
            return (Criteria) this;
        }

        public Criteria andRelationIdIsNotNull() {
            addCriterion("relation_id is not null");
            return (Criteria) this;
        }

        public Criteria andRelationIdEqualTo(Long value) {
            addCriterion("relation_id =", value, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdNotEqualTo(Long value) {
            addCriterion("relation_id <>", value, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdGreaterThan(Long value) {
            addCriterion("relation_id >", value, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdGreaterThanOrEqualTo(Long value) {
            addCriterion("relation_id >=", value, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdLessThan(Long value) {
            addCriterion("relation_id <", value, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdLessThanOrEqualTo(Long value) {
            addCriterion("relation_id <=", value, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdIn(List<Long> values) {
            addCriterion("relation_id in", values, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdNotIn(List<Long> values) {
            addCriterion("relation_id not in", values, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdBetween(Long value1, Long value2) {
            addCriterion("relation_id between", value1, value2, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdNotBetween(Long value1, Long value2) {
            addCriterion("relation_id not between", value1, value2, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationTypeIsNull() {
            addCriterion("relation_type is null");
            return (Criteria) this;
        }

        public Criteria andRelationTypeIsNotNull() {
            addCriterion("relation_type is not null");
            return (Criteria) this;
        }

        public Criteria andRelationTypeEqualTo(Integer value) {
            addCriterion("relation_type =", value, "relationType");
            return (Criteria) this;
        }

        public Criteria andRelationTypeNotEqualTo(Integer value) {
            addCriterion("relation_type <>", value, "relationType");
            return (Criteria) this;
        }

        public Criteria andRelationTypeGreaterThan(Integer value) {
            addCriterion("relation_type >", value, "relationType");
            return (Criteria) this;
        }

        public Criteria andRelationTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("relation_type >=", value, "relationType");
            return (Criteria) this;
        }

        public Criteria andRelationTypeLessThan(Integer value) {
            addCriterion("relation_type <", value, "relationType");
            return (Criteria) this;
        }

        public Criteria andRelationTypeLessThanOrEqualTo(Integer value) {
            addCriterion("relation_type <=", value, "relationType");
            return (Criteria) this;
        }

        public Criteria andRelationTypeIn(List<Integer> values) {
            addCriterion("relation_type in", values, "relationType");
            return (Criteria) this;
        }

        public Criteria andRelationTypeNotIn(List<Integer> values) {
            addCriterion("relation_type not in", values, "relationType");
            return (Criteria) this;
        }

        public Criteria andRelationTypeBetween(Integer value1, Integer value2) {
            addCriterion("relation_type between", value1, value2, "relationType");
            return (Criteria) this;
        }

        public Criteria andRelationTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("relation_type not between", value1, value2, "relationType");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andDescpIsNull() {
            addCriterion("descp is null");
            return (Criteria) this;
        }

        public Criteria andDescpIsNotNull() {
            addCriterion("descp is not null");
            return (Criteria) this;
        }

        public Criteria andDescpEqualTo(String value) {
            addCriterion("descp =", value, "descp");
            return (Criteria) this;
        }

        public Criteria andDescpNotEqualTo(String value) {
            addCriterion("descp <>", value, "descp");
            return (Criteria) this;
        }

        public Criteria andDescpGreaterThan(String value) {
            addCriterion("descp >", value, "descp");
            return (Criteria) this;
        }

        public Criteria andDescpGreaterThanOrEqualTo(String value) {
            addCriterion("descp >=", value, "descp");
            return (Criteria) this;
        }

        public Criteria andDescpLessThan(String value) {
            addCriterion("descp <", value, "descp");
            return (Criteria) this;
        }

        public Criteria andDescpLessThanOrEqualTo(String value) {
            addCriterion("descp <=", value, "descp");
            return (Criteria) this;
        }

        public Criteria andDescpLike(String value) {
            addCriterion("descp like", value, "descp");
            return (Criteria) this;
        }

        public Criteria andDescpNotLike(String value) {
            addCriterion("descp not like", value, "descp");
            return (Criteria) this;
        }

        public Criteria andDescpIn(List<String> values) {
            addCriterion("descp in", values, "descp");
            return (Criteria) this;
        }

        public Criteria andDescpNotIn(List<String> values) {
            addCriterion("descp not in", values, "descp");
            return (Criteria) this;
        }

        public Criteria andDescpBetween(String value1, String value2) {
            addCriterion("descp between", value1, value2, "descp");
            return (Criteria) this;
        }

        public Criteria andDescpNotBetween(String value1, String value2) {
            addCriterion("descp not between", value1, value2, "descp");
            return (Criteria) this;
        }

        public Criteria andReproductionStepIsNull() {
            addCriterion("reproduction_step is null");
            return (Criteria) this;
        }

        public Criteria andReproductionStepIsNotNull() {
            addCriterion("reproduction_step is not null");
            return (Criteria) this;
        }

        public Criteria andReproductionStepEqualTo(String value) {
            addCriterion("reproduction_step =", value, "reproductionStep");
            return (Criteria) this;
        }

        public Criteria andReproductionStepNotEqualTo(String value) {
            addCriterion("reproduction_step <>", value, "reproductionStep");
            return (Criteria) this;
        }

        public Criteria andReproductionStepGreaterThan(String value) {
            addCriterion("reproduction_step >", value, "reproductionStep");
            return (Criteria) this;
        }

        public Criteria andReproductionStepGreaterThanOrEqualTo(String value) {
            addCriterion("reproduction_step >=", value, "reproductionStep");
            return (Criteria) this;
        }

        public Criteria andReproductionStepLessThan(String value) {
            addCriterion("reproduction_step <", value, "reproductionStep");
            return (Criteria) this;
        }

        public Criteria andReproductionStepLessThanOrEqualTo(String value) {
            addCriterion("reproduction_step <=", value, "reproductionStep");
            return (Criteria) this;
        }

        public Criteria andReproductionStepLike(String value) {
            addCriterion("reproduction_step like", value, "reproductionStep");
            return (Criteria) this;
        }

        public Criteria andReproductionStepNotLike(String value) {
            addCriterion("reproduction_step not like", value, "reproductionStep");
            return (Criteria) this;
        }

        public Criteria andReproductionStepIn(List<String> values) {
            addCriterion("reproduction_step in", values, "reproductionStep");
            return (Criteria) this;
        }

        public Criteria andReproductionStepNotIn(List<String> values) {
            addCriterion("reproduction_step not in", values, "reproductionStep");
            return (Criteria) this;
        }

        public Criteria andReproductionStepBetween(String value1, String value2) {
            addCriterion("reproduction_step between", value1, value2, "reproductionStep");
            return (Criteria) this;
        }

        public Criteria andReproductionStepNotBetween(String value1, String value2) {
            addCriterion("reproduction_step not between", value1, value2, "reproductionStep");
            return (Criteria) this;
        }

        public Criteria andBrowerIsNull() {
            addCriterion("brower is null");
            return (Criteria) this;
        }

        public Criteria andBrowerIsNotNull() {
            addCriterion("brower is not null");
            return (Criteria) this;
        }

        public Criteria andBrowerEqualTo(String value) {
            addCriterion("brower =", value, "brower");
            return (Criteria) this;
        }

        public Criteria andBrowerNotEqualTo(String value) {
            addCriterion("brower <>", value, "brower");
            return (Criteria) this;
        }

        public Criteria andBrowerGreaterThan(String value) {
            addCriterion("brower >", value, "brower");
            return (Criteria) this;
        }

        public Criteria andBrowerGreaterThanOrEqualTo(String value) {
            addCriterion("brower >=", value, "brower");
            return (Criteria) this;
        }

        public Criteria andBrowerLessThan(String value) {
            addCriterion("brower <", value, "brower");
            return (Criteria) this;
        }

        public Criteria andBrowerLessThanOrEqualTo(String value) {
            addCriterion("brower <=", value, "brower");
            return (Criteria) this;
        }

        public Criteria andBrowerLike(String value) {
            addCriterion("brower like", value, "brower");
            return (Criteria) this;
        }

        public Criteria andBrowerNotLike(String value) {
            addCriterion("brower not like", value, "brower");
            return (Criteria) this;
        }

        public Criteria andBrowerIn(List<String> values) {
            addCriterion("brower in", values, "brower");
            return (Criteria) this;
        }

        public Criteria andBrowerNotIn(List<String> values) {
            addCriterion("brower not in", values, "brower");
            return (Criteria) this;
        }

        public Criteria andBrowerBetween(String value1, String value2) {
            addCriterion("brower between", value1, value2, "brower");
            return (Criteria) this;
        }

        public Criteria andBrowerNotBetween(String value1, String value2) {
            addCriterion("brower not between", value1, value2, "brower");
            return (Criteria) this;
        }

        public Criteria andHopeDateIsNull() {
            addCriterion("hope_date is null");
            return (Criteria) this;
        }

        public Criteria andHopeDateIsNotNull() {
            addCriterion("hope_date is not null");
            return (Criteria) this;
        }

        public Criteria andHopeDateEqualTo(Date value) {
            addCriterion("hope_date =", value, "hopeDate");
            return (Criteria) this;
        }

        public Criteria andHopeDateNotEqualTo(Date value) {
            addCriterion("hope_date <>", value, "hopeDate");
            return (Criteria) this;
        }

        public Criteria andHopeDateGreaterThan(Date value) {
            addCriterion("hope_date >", value, "hopeDate");
            return (Criteria) this;
        }

        public Criteria andHopeDateGreaterThanOrEqualTo(Date value) {
            addCriterion("hope_date >=", value, "hopeDate");
            return (Criteria) this;
        }

        public Criteria andHopeDateLessThan(Date value) {
            addCriterion("hope_date <", value, "hopeDate");
            return (Criteria) this;
        }

        public Criteria andHopeDateLessThanOrEqualTo(Date value) {
            addCriterion("hope_date <=", value, "hopeDate");
            return (Criteria) this;
        }

        public Criteria andHopeDateIn(List<Date> values) {
            addCriterion("hope_date in", values, "hopeDate");
            return (Criteria) this;
        }

        public Criteria andHopeDateNotIn(List<Date> values) {
            addCriterion("hope_date not in", values, "hopeDate");
            return (Criteria) this;
        }

        public Criteria andHopeDateBetween(Date value1, Date value2) {
            addCriterion("hope_date between", value1, value2, "hopeDate");
            return (Criteria) this;
        }

        public Criteria andHopeDateNotBetween(Date value1, Date value2) {
            addCriterion("hope_date not between", value1, value2, "hopeDate");
            return (Criteria) this;
        }

        public Criteria andCallonAccountIdIsNull() {
            addCriterion("callon_account_id is null");
            return (Criteria) this;
        }

        public Criteria andCallonAccountIdIsNotNull() {
            addCriterion("callon_account_id is not null");
            return (Criteria) this;
        }

        public Criteria andCallonAccountIdEqualTo(String value) {
            addCriterion("callon_account_id =", value, "callonAccountId");
            return (Criteria) this;
        }

        public Criteria andCallonAccountIdNotEqualTo(String value) {
            addCriterion("callon_account_id <>", value, "callonAccountId");
            return (Criteria) this;
        }

        public Criteria andCallonAccountIdGreaterThan(String value) {
            addCriterion("callon_account_id >", value, "callonAccountId");
            return (Criteria) this;
        }

        public Criteria andCallonAccountIdGreaterThanOrEqualTo(String value) {
            addCriterion("callon_account_id >=", value, "callonAccountId");
            return (Criteria) this;
        }

        public Criteria andCallonAccountIdLessThan(String value) {
            addCriterion("callon_account_id <", value, "callonAccountId");
            return (Criteria) this;
        }

        public Criteria andCallonAccountIdLessThanOrEqualTo(String value) {
            addCriterion("callon_account_id <=", value, "callonAccountId");
            return (Criteria) this;
        }

        public Criteria andCallonAccountIdLike(String value) {
            addCriterion("callon_account_id like", value, "callonAccountId");
            return (Criteria) this;
        }

        public Criteria andCallonAccountIdNotLike(String value) {
            addCriterion("callon_account_id not like", value, "callonAccountId");
            return (Criteria) this;
        }

        public Criteria andCallonAccountIdIn(List<String> values) {
            addCriterion("callon_account_id in", values, "callonAccountId");
            return (Criteria) this;
        }

        public Criteria andCallonAccountIdNotIn(List<String> values) {
            addCriterion("callon_account_id not in", values, "callonAccountId");
            return (Criteria) this;
        }

        public Criteria andCallonAccountIdBetween(String value1, String value2) {
            addCriterion("callon_account_id between", value1, value2, "callonAccountId");
            return (Criteria) this;
        }

        public Criteria andCallonAccountIdNotBetween(String value1, String value2) {
            addCriterion("callon_account_id not between", value1, value2, "callonAccountId");
            return (Criteria) this;
        }

        public Criteria andCallonEmployeeCodeIsNull() {
            addCriterion("callon_employee_code is null");
            return (Criteria) this;
        }

        public Criteria andCallonEmployeeCodeIsNotNull() {
            addCriterion("callon_employee_code is not null");
            return (Criteria) this;
        }

        public Criteria andCallonEmployeeCodeEqualTo(Long value) {
            addCriterion("callon_employee_code =", value, "callonEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andCallonEmployeeCodeNotEqualTo(Long value) {
            addCriterion("callon_employee_code <>", value, "callonEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andCallonEmployeeCodeGreaterThan(Long value) {
            addCriterion("callon_employee_code >", value, "callonEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andCallonEmployeeCodeGreaterThanOrEqualTo(Long value) {
            addCriterion("callon_employee_code >=", value, "callonEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andCallonEmployeeCodeLessThan(Long value) {
            addCriterion("callon_employee_code <", value, "callonEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andCallonEmployeeCodeLessThanOrEqualTo(Long value) {
            addCriterion("callon_employee_code <=", value, "callonEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andCallonEmployeeCodeIn(List<Long> values) {
            addCriterion("callon_employee_code in", values, "callonEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andCallonEmployeeCodeNotIn(List<Long> values) {
            addCriterion("callon_employee_code not in", values, "callonEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andCallonEmployeeCodeBetween(Long value1, Long value2) {
            addCriterion("callon_employee_code between", value1, value2, "callonEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andCallonEmployeeCodeNotBetween(Long value1, Long value2) {
            addCriterion("callon_employee_code not between", value1, value2, "callonEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andCallonEmployeeNameIsNull() {
            addCriterion("callon_employee_name is null");
            return (Criteria) this;
        }

        public Criteria andCallonEmployeeNameIsNotNull() {
            addCriterion("callon_employee_name is not null");
            return (Criteria) this;
        }

        public Criteria andCallonEmployeeNameEqualTo(String value) {
            addCriterion("callon_employee_name =", value, "callonEmployeeName");
            return (Criteria) this;
        }

        public Criteria andCallonEmployeeNameNotEqualTo(String value) {
            addCriterion("callon_employee_name <>", value, "callonEmployeeName");
            return (Criteria) this;
        }

        public Criteria andCallonEmployeeNameGreaterThan(String value) {
            addCriterion("callon_employee_name >", value, "callonEmployeeName");
            return (Criteria) this;
        }

        public Criteria andCallonEmployeeNameGreaterThanOrEqualTo(String value) {
            addCriterion("callon_employee_name >=", value, "callonEmployeeName");
            return (Criteria) this;
        }

        public Criteria andCallonEmployeeNameLessThan(String value) {
            addCriterion("callon_employee_name <", value, "callonEmployeeName");
            return (Criteria) this;
        }

        public Criteria andCallonEmployeeNameLessThanOrEqualTo(String value) {
            addCriterion("callon_employee_name <=", value, "callonEmployeeName");
            return (Criteria) this;
        }

        public Criteria andCallonEmployeeNameLike(String value) {
            addCriterion("callon_employee_name like", value, "callonEmployeeName");
            return (Criteria) this;
        }

        public Criteria andCallonEmployeeNameNotLike(String value) {
            addCriterion("callon_employee_name not like", value, "callonEmployeeName");
            return (Criteria) this;
        }

        public Criteria andCallonEmployeeNameIn(List<String> values) {
            addCriterion("callon_employee_name in", values, "callonEmployeeName");
            return (Criteria) this;
        }

        public Criteria andCallonEmployeeNameNotIn(List<String> values) {
            addCriterion("callon_employee_name not in", values, "callonEmployeeName");
            return (Criteria) this;
        }

        public Criteria andCallonEmployeeNameBetween(String value1, String value2) {
            addCriterion("callon_employee_name between", value1, value2, "callonEmployeeName");
            return (Criteria) this;
        }

        public Criteria andCallonEmployeeNameNotBetween(String value1, String value2) {
            addCriterion("callon_employee_name not between", value1, value2, "callonEmployeeName");
            return (Criteria) this;
        }

        public Criteria andCallonFullDeptPathIsNull() {
            addCriterion("callon_full_dept_path is null");
            return (Criteria) this;
        }

        public Criteria andCallonFullDeptPathIsNotNull() {
            addCriterion("callon_full_dept_path is not null");
            return (Criteria) this;
        }

        public Criteria andCallonFullDeptPathEqualTo(String value) {
            addCriterion("callon_full_dept_path =", value, "callonFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andCallonFullDeptPathNotEqualTo(String value) {
            addCriterion("callon_full_dept_path <>", value, "callonFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andCallonFullDeptPathGreaterThan(String value) {
            addCriterion("callon_full_dept_path >", value, "callonFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andCallonFullDeptPathGreaterThanOrEqualTo(String value) {
            addCriterion("callon_full_dept_path >=", value, "callonFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andCallonFullDeptPathLessThan(String value) {
            addCriterion("callon_full_dept_path <", value, "callonFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andCallonFullDeptPathLessThanOrEqualTo(String value) {
            addCriterion("callon_full_dept_path <=", value, "callonFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andCallonFullDeptPathLike(String value) {
            addCriterion("callon_full_dept_path like", value, "callonFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andCallonFullDeptPathNotLike(String value) {
            addCriterion("callon_full_dept_path not like", value, "callonFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andCallonFullDeptPathIn(List<String> values) {
            addCriterion("callon_full_dept_path in", values, "callonFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andCallonFullDeptPathNotIn(List<String> values) {
            addCriterion("callon_full_dept_path not in", values, "callonFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andCallonFullDeptPathBetween(String value1, String value2) {
            addCriterion("callon_full_dept_path between", value1, value2, "callonFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andCallonFullDeptPathNotBetween(String value1, String value2) {
            addCriterion("callon_full_dept_path not between", value1, value2, "callonFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andDraftedAccountIdIsNull() {
            addCriterion("drafted_account_id is null");
            return (Criteria) this;
        }

        public Criteria andDraftedAccountIdIsNotNull() {
            addCriterion("drafted_account_id is not null");
            return (Criteria) this;
        }

        public Criteria andDraftedAccountIdEqualTo(String value) {
            addCriterion("drafted_account_id =", value, "draftedAccountId");
            return (Criteria) this;
        }

        public Criteria andDraftedAccountIdNotEqualTo(String value) {
            addCriterion("drafted_account_id <>", value, "draftedAccountId");
            return (Criteria) this;
        }

        public Criteria andDraftedAccountIdGreaterThan(String value) {
            addCriterion("drafted_account_id >", value, "draftedAccountId");
            return (Criteria) this;
        }

        public Criteria andDraftedAccountIdGreaterThanOrEqualTo(String value) {
            addCriterion("drafted_account_id >=", value, "draftedAccountId");
            return (Criteria) this;
        }

        public Criteria andDraftedAccountIdLessThan(String value) {
            addCriterion("drafted_account_id <", value, "draftedAccountId");
            return (Criteria) this;
        }

        public Criteria andDraftedAccountIdLessThanOrEqualTo(String value) {
            addCriterion("drafted_account_id <=", value, "draftedAccountId");
            return (Criteria) this;
        }

        public Criteria andDraftedAccountIdLike(String value) {
            addCriterion("drafted_account_id like", value, "draftedAccountId");
            return (Criteria) this;
        }

        public Criteria andDraftedAccountIdNotLike(String value) {
            addCriterion("drafted_account_id not like", value, "draftedAccountId");
            return (Criteria) this;
        }

        public Criteria andDraftedAccountIdIn(List<String> values) {
            addCriterion("drafted_account_id in", values, "draftedAccountId");
            return (Criteria) this;
        }

        public Criteria andDraftedAccountIdNotIn(List<String> values) {
            addCriterion("drafted_account_id not in", values, "draftedAccountId");
            return (Criteria) this;
        }

        public Criteria andDraftedAccountIdBetween(String value1, String value2) {
            addCriterion("drafted_account_id between", value1, value2, "draftedAccountId");
            return (Criteria) this;
        }

        public Criteria andDraftedAccountIdNotBetween(String value1, String value2) {
            addCriterion("drafted_account_id not between", value1, value2, "draftedAccountId");
            return (Criteria) this;
        }

        public Criteria andDraftedEmployeeCodeIsNull() {
            addCriterion("drafted_employee_code is null");
            return (Criteria) this;
        }

        public Criteria andDraftedEmployeeCodeIsNotNull() {
            addCriterion("drafted_employee_code is not null");
            return (Criteria) this;
        }

        public Criteria andDraftedEmployeeCodeEqualTo(Long value) {
            addCriterion("drafted_employee_code =", value, "draftedEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andDraftedEmployeeCodeNotEqualTo(Long value) {
            addCriterion("drafted_employee_code <>", value, "draftedEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andDraftedEmployeeCodeGreaterThan(Long value) {
            addCriterion("drafted_employee_code >", value, "draftedEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andDraftedEmployeeCodeGreaterThanOrEqualTo(Long value) {
            addCriterion("drafted_employee_code >=", value, "draftedEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andDraftedEmployeeCodeLessThan(Long value) {
            addCriterion("drafted_employee_code <", value, "draftedEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andDraftedEmployeeCodeLessThanOrEqualTo(Long value) {
            addCriterion("drafted_employee_code <=", value, "draftedEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andDraftedEmployeeCodeIn(List<Long> values) {
            addCriterion("drafted_employee_code in", values, "draftedEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andDraftedEmployeeCodeNotIn(List<Long> values) {
            addCriterion("drafted_employee_code not in", values, "draftedEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andDraftedEmployeeCodeBetween(Long value1, Long value2) {
            addCriterion("drafted_employee_code between", value1, value2, "draftedEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andDraftedEmployeeCodeNotBetween(Long value1, Long value2) {
            addCriterion("drafted_employee_code not between", value1, value2, "draftedEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andDraftedEmployeeNameIsNull() {
            addCriterion("drafted_employee_name is null");
            return (Criteria) this;
        }

        public Criteria andDraftedEmployeeNameIsNotNull() {
            addCriterion("drafted_employee_name is not null");
            return (Criteria) this;
        }

        public Criteria andDraftedEmployeeNameEqualTo(String value) {
            addCriterion("drafted_employee_name =", value, "draftedEmployeeName");
            return (Criteria) this;
        }

        public Criteria andDraftedEmployeeNameNotEqualTo(String value) {
            addCriterion("drafted_employee_name <>", value, "draftedEmployeeName");
            return (Criteria) this;
        }

        public Criteria andDraftedEmployeeNameGreaterThan(String value) {
            addCriterion("drafted_employee_name >", value, "draftedEmployeeName");
            return (Criteria) this;
        }

        public Criteria andDraftedEmployeeNameGreaterThanOrEqualTo(String value) {
            addCriterion("drafted_employee_name >=", value, "draftedEmployeeName");
            return (Criteria) this;
        }

        public Criteria andDraftedEmployeeNameLessThan(String value) {
            addCriterion("drafted_employee_name <", value, "draftedEmployeeName");
            return (Criteria) this;
        }

        public Criteria andDraftedEmployeeNameLessThanOrEqualTo(String value) {
            addCriterion("drafted_employee_name <=", value, "draftedEmployeeName");
            return (Criteria) this;
        }

        public Criteria andDraftedEmployeeNameLike(String value) {
            addCriterion("drafted_employee_name like", value, "draftedEmployeeName");
            return (Criteria) this;
        }

        public Criteria andDraftedEmployeeNameNotLike(String value) {
            addCriterion("drafted_employee_name not like", value, "draftedEmployeeName");
            return (Criteria) this;
        }

        public Criteria andDraftedEmployeeNameIn(List<String> values) {
            addCriterion("drafted_employee_name in", values, "draftedEmployeeName");
            return (Criteria) this;
        }

        public Criteria andDraftedEmployeeNameNotIn(List<String> values) {
            addCriterion("drafted_employee_name not in", values, "draftedEmployeeName");
            return (Criteria) this;
        }

        public Criteria andDraftedEmployeeNameBetween(String value1, String value2) {
            addCriterion("drafted_employee_name between", value1, value2, "draftedEmployeeName");
            return (Criteria) this;
        }

        public Criteria andDraftedEmployeeNameNotBetween(String value1, String value2) {
            addCriterion("drafted_employee_name not between", value1, value2, "draftedEmployeeName");
            return (Criteria) this;
        }

        public Criteria andDraftedFullDeptPathIsNull() {
            addCriterion("drafted_full_dept_path is null");
            return (Criteria) this;
        }

        public Criteria andDraftedFullDeptPathIsNotNull() {
            addCriterion("drafted_full_dept_path is not null");
            return (Criteria) this;
        }

        public Criteria andDraftedFullDeptPathEqualTo(String value) {
            addCriterion("drafted_full_dept_path =", value, "draftedFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andDraftedFullDeptPathNotEqualTo(String value) {
            addCriterion("drafted_full_dept_path <>", value, "draftedFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andDraftedFullDeptPathGreaterThan(String value) {
            addCriterion("drafted_full_dept_path >", value, "draftedFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andDraftedFullDeptPathGreaterThanOrEqualTo(String value) {
            addCriterion("drafted_full_dept_path >=", value, "draftedFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andDraftedFullDeptPathLessThan(String value) {
            addCriterion("drafted_full_dept_path <", value, "draftedFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andDraftedFullDeptPathLessThanOrEqualTo(String value) {
            addCriterion("drafted_full_dept_path <=", value, "draftedFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andDraftedFullDeptPathLike(String value) {
            addCriterion("drafted_full_dept_path like", value, "draftedFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andDraftedFullDeptPathNotLike(String value) {
            addCriterion("drafted_full_dept_path not like", value, "draftedFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andDraftedFullDeptPathIn(List<String> values) {
            addCriterion("drafted_full_dept_path in", values, "draftedFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andDraftedFullDeptPathNotIn(List<String> values) {
            addCriterion("drafted_full_dept_path not in", values, "draftedFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andDraftedFullDeptPathBetween(String value1, String value2) {
            addCriterion("drafted_full_dept_path between", value1, value2, "draftedFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andDraftedFullDeptPathNotBetween(String value1, String value2) {
            addCriterion("drafted_full_dept_path not between", value1, value2, "draftedFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andCcAccountIsNull() {
            addCriterion("cc_account is null");
            return (Criteria) this;
        }

        public Criteria andCcAccountIsNotNull() {
            addCriterion("cc_account is not null");
            return (Criteria) this;
        }

        public Criteria andCcAccountEqualTo(String value) {
            addCriterion("cc_account =", value, "ccAccount");
            return (Criteria) this;
        }

        public Criteria andCcAccountNotEqualTo(String value) {
            addCriterion("cc_account <>", value, "ccAccount");
            return (Criteria) this;
        }

        public Criteria andCcAccountGreaterThan(String value) {
            addCriterion("cc_account >", value, "ccAccount");
            return (Criteria) this;
        }

        public Criteria andCcAccountGreaterThanOrEqualTo(String value) {
            addCriterion("cc_account >=", value, "ccAccount");
            return (Criteria) this;
        }

        public Criteria andCcAccountLessThan(String value) {
            addCriterion("cc_account <", value, "ccAccount");
            return (Criteria) this;
        }

        public Criteria andCcAccountLessThanOrEqualTo(String value) {
            addCriterion("cc_account <=", value, "ccAccount");
            return (Criteria) this;
        }

        public Criteria andCcAccountLike(String value) {
            addCriterion("cc_account like", value, "ccAccount");
            return (Criteria) this;
        }

        public Criteria andCcAccountNotLike(String value) {
            addCriterion("cc_account not like", value, "ccAccount");
            return (Criteria) this;
        }

        public Criteria andCcAccountIn(List<String> values) {
            addCriterion("cc_account in", values, "ccAccount");
            return (Criteria) this;
        }

        public Criteria andCcAccountNotIn(List<String> values) {
            addCriterion("cc_account not in", values, "ccAccount");
            return (Criteria) this;
        }

        public Criteria andCcAccountBetween(String value1, String value2) {
            addCriterion("cc_account between", value1, value2, "ccAccount");
            return (Criteria) this;
        }

        public Criteria andCcAccountNotBetween(String value1, String value2) {
            addCriterion("cc_account not between", value1, value2, "ccAccount");
            return (Criteria) this;
        }

        public Criteria andLikeProductIsNull() {
            addCriterion("like_product is null");
            return (Criteria) this;
        }

        public Criteria andLikeProductIsNotNull() {
            addCriterion("like_product is not null");
            return (Criteria) this;
        }

        public Criteria andLikeProductEqualTo(String value) {
            addCriterion("like_product =", value, "likeProduct");
            return (Criteria) this;
        }

        public Criteria andLikeProductNotEqualTo(String value) {
            addCriterion("like_product <>", value, "likeProduct");
            return (Criteria) this;
        }

        public Criteria andLikeProductGreaterThan(String value) {
            addCriterion("like_product >", value, "likeProduct");
            return (Criteria) this;
        }

        public Criteria andLikeProductGreaterThanOrEqualTo(String value) {
            addCriterion("like_product >=", value, "likeProduct");
            return (Criteria) this;
        }

        public Criteria andLikeProductLessThan(String value) {
            addCriterion("like_product <", value, "likeProduct");
            return (Criteria) this;
        }

        public Criteria andLikeProductLessThanOrEqualTo(String value) {
            addCriterion("like_product <=", value, "likeProduct");
            return (Criteria) this;
        }

        public Criteria andLikeProductLike(String value) {
            addCriterion("like_product like", value, "likeProduct");
            return (Criteria) this;
        }

        public Criteria andLikeProductNotLike(String value) {
            addCriterion("like_product not like", value, "likeProduct");
            return (Criteria) this;
        }

        public Criteria andLikeProductIn(List<String> values) {
            addCriterion("like_product in", values, "likeProduct");
            return (Criteria) this;
        }

        public Criteria andLikeProductNotIn(List<String> values) {
            addCriterion("like_product not in", values, "likeProduct");
            return (Criteria) this;
        }

        public Criteria andLikeProductBetween(String value1, String value2) {
            addCriterion("like_product between", value1, value2, "likeProduct");
            return (Criteria) this;
        }

        public Criteria andLikeProductNotBetween(String value1, String value2) {
            addCriterion("like_product not between", value1, value2, "likeProduct");
            return (Criteria) this;
        }

        public Criteria andLikeProgramIsNull() {
            addCriterion("like_program is null");
            return (Criteria) this;
        }

        public Criteria andLikeProgramIsNotNull() {
            addCriterion("like_program is not null");
            return (Criteria) this;
        }

        public Criteria andLikeProgramEqualTo(String value) {
            addCriterion("like_program =", value, "likeProgram");
            return (Criteria) this;
        }

        public Criteria andLikeProgramNotEqualTo(String value) {
            addCriterion("like_program <>", value, "likeProgram");
            return (Criteria) this;
        }

        public Criteria andLikeProgramGreaterThan(String value) {
            addCriterion("like_program >", value, "likeProgram");
            return (Criteria) this;
        }

        public Criteria andLikeProgramGreaterThanOrEqualTo(String value) {
            addCriterion("like_program >=", value, "likeProgram");
            return (Criteria) this;
        }

        public Criteria andLikeProgramLessThan(String value) {
            addCriterion("like_program <", value, "likeProgram");
            return (Criteria) this;
        }

        public Criteria andLikeProgramLessThanOrEqualTo(String value) {
            addCriterion("like_program <=", value, "likeProgram");
            return (Criteria) this;
        }

        public Criteria andLikeProgramLike(String value) {
            addCriterion("like_program like", value, "likeProgram");
            return (Criteria) this;
        }

        public Criteria andLikeProgramNotLike(String value) {
            addCriterion("like_program not like", value, "likeProgram");
            return (Criteria) this;
        }

        public Criteria andLikeProgramIn(List<String> values) {
            addCriterion("like_program in", values, "likeProgram");
            return (Criteria) this;
        }

        public Criteria andLikeProgramNotIn(List<String> values) {
            addCriterion("like_program not in", values, "likeProgram");
            return (Criteria) this;
        }

        public Criteria andLikeProgramBetween(String value1, String value2) {
            addCriterion("like_program between", value1, value2, "likeProgram");
            return (Criteria) this;
        }

        public Criteria andLikeProgramNotBetween(String value1, String value2) {
            addCriterion("like_program not between", value1, value2, "likeProgram");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(Integer value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(Integer value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(Integer value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(Integer value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(Integer value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<Integer> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<Integer> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(Integer value1, Integer value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("level not between", value1, value2, "level");
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

        public Criteria andChannelIsNull() {
            addCriterion("channel is null");
            return (Criteria) this;
        }

        public Criteria andChannelIsNotNull() {
            addCriterion("channel is not null");
            return (Criteria) this;
        }

        public Criteria andChannelEqualTo(Integer value) {
            addCriterion("channel =", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotEqualTo(Integer value) {
            addCriterion("channel <>", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelGreaterThan(Integer value) {
            addCriterion("channel >", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelGreaterThanOrEqualTo(Integer value) {
            addCriterion("channel >=", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLessThan(Integer value) {
            addCriterion("channel <", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLessThanOrEqualTo(Integer value) {
            addCriterion("channel <=", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelIn(List<Integer> values) {
            addCriterion("channel in", values, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotIn(List<Integer> values) {
            addCriterion("channel not in", values, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelBetween(Integer value1, Integer value2) {
            addCriterion("channel between", value1, value2, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotBetween(Integer value1, Integer value2) {
            addCriterion("channel not between", value1, value2, "channel");
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