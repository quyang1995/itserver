package com.longfor.itserver.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProductExample() {
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

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
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

        public Criteria andContactAccountIdIsNull() {
            addCriterion("contact_account_id is null");
            return (Criteria) this;
        }

        public Criteria andContactAccountIdIsNotNull() {
            addCriterion("contact_account_id is not null");
            return (Criteria) this;
        }

        public Criteria andContactAccountIdEqualTo(String value) {
            addCriterion("contact_account_id =", value, "contactAccountId");
            return (Criteria) this;
        }

        public Criteria andContactAccountIdNotEqualTo(String value) {
            addCriterion("contact_account_id <>", value, "contactAccountId");
            return (Criteria) this;
        }

        public Criteria andContactAccountIdGreaterThan(String value) {
            addCriterion("contact_account_id >", value, "contactAccountId");
            return (Criteria) this;
        }

        public Criteria andContactAccountIdGreaterThanOrEqualTo(String value) {
            addCriterion("contact_account_id >=", value, "contactAccountId");
            return (Criteria) this;
        }

        public Criteria andContactAccountIdLessThan(String value) {
            addCriterion("contact_account_id <", value, "contactAccountId");
            return (Criteria) this;
        }

        public Criteria andContactAccountIdLessThanOrEqualTo(String value) {
            addCriterion("contact_account_id <=", value, "contactAccountId");
            return (Criteria) this;
        }

        public Criteria andContactAccountIdLike(String value) {
            addCriterion("contact_account_id like", value, "contactAccountId");
            return (Criteria) this;
        }

        public Criteria andContactAccountIdNotLike(String value) {
            addCriterion("contact_account_id not like", value, "contactAccountId");
            return (Criteria) this;
        }

        public Criteria andContactAccountIdIn(List<String> values) {
            addCriterion("contact_account_id in", values, "contactAccountId");
            return (Criteria) this;
        }

        public Criteria andContactAccountIdNotIn(List<String> values) {
            addCriterion("contact_account_id not in", values, "contactAccountId");
            return (Criteria) this;
        }

        public Criteria andContactAccountIdBetween(String value1, String value2) {
            addCriterion("contact_account_id between", value1, value2, "contactAccountId");
            return (Criteria) this;
        }

        public Criteria andContactAccountIdNotBetween(String value1, String value2) {
            addCriterion("contact_account_id not between", value1, value2, "contactAccountId");
            return (Criteria) this;
        }

        public Criteria andContactEmployeeCodeIsNull() {
            addCriterion("contact_employee_code is null");
            return (Criteria) this;
        }

        public Criteria andContactEmployeeCodeIsNotNull() {
            addCriterion("contact_employee_code is not null");
            return (Criteria) this;
        }

        public Criteria andContactEmployeeCodeEqualTo(Long value) {
            addCriterion("contact_employee_code =", value, "contactEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andContactEmployeeCodeNotEqualTo(Long value) {
            addCriterion("contact_employee_code <>", value, "contactEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andContactEmployeeCodeGreaterThan(Long value) {
            addCriterion("contact_employee_code >", value, "contactEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andContactEmployeeCodeGreaterThanOrEqualTo(Long value) {
            addCriterion("contact_employee_code >=", value, "contactEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andContactEmployeeCodeLessThan(Long value) {
            addCriterion("contact_employee_code <", value, "contactEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andContactEmployeeCodeLessThanOrEqualTo(Long value) {
            addCriterion("contact_employee_code <=", value, "contactEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andContactEmployeeCodeIn(List<Long> values) {
            addCriterion("contact_employee_code in", values, "contactEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andContactEmployeeCodeNotIn(List<Long> values) {
            addCriterion("contact_employee_code not in", values, "contactEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andContactEmployeeCodeBetween(Long value1, Long value2) {
            addCriterion("contact_employee_code between", value1, value2, "contactEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andContactEmployeeCodeNotBetween(Long value1, Long value2) {
            addCriterion("contact_employee_code not between", value1, value2, "contactEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andContactEmployeeNameIsNull() {
            addCriterion("contact_employee_name is null");
            return (Criteria) this;
        }

        public Criteria andContactEmployeeNameIsNotNull() {
            addCriterion("contact_employee_name is not null");
            return (Criteria) this;
        }

        public Criteria andContactEmployeeNameEqualTo(String value) {
            addCriterion("contact_employee_name =", value, "contactEmployeeName");
            return (Criteria) this;
        }

        public Criteria andContactEmployeeNameNotEqualTo(String value) {
            addCriterion("contact_employee_name <>", value, "contactEmployeeName");
            return (Criteria) this;
        }

        public Criteria andContactEmployeeNameGreaterThan(String value) {
            addCriterion("contact_employee_name >", value, "contactEmployeeName");
            return (Criteria) this;
        }

        public Criteria andContactEmployeeNameGreaterThanOrEqualTo(String value) {
            addCriterion("contact_employee_name >=", value, "contactEmployeeName");
            return (Criteria) this;
        }

        public Criteria andContactEmployeeNameLessThan(String value) {
            addCriterion("contact_employee_name <", value, "contactEmployeeName");
            return (Criteria) this;
        }

        public Criteria andContactEmployeeNameLessThanOrEqualTo(String value) {
            addCriterion("contact_employee_name <=", value, "contactEmployeeName");
            return (Criteria) this;
        }

        public Criteria andContactEmployeeNameLike(String value) {
            addCriterion("contact_employee_name like", value, "contactEmployeeName");
            return (Criteria) this;
        }

        public Criteria andContactEmployeeNameNotLike(String value) {
            addCriterion("contact_employee_name not like", value, "contactEmployeeName");
            return (Criteria) this;
        }

        public Criteria andContactEmployeeNameIn(List<String> values) {
            addCriterion("contact_employee_name in", values, "contactEmployeeName");
            return (Criteria) this;
        }

        public Criteria andContactEmployeeNameNotIn(List<String> values) {
            addCriterion("contact_employee_name not in", values, "contactEmployeeName");
            return (Criteria) this;
        }

        public Criteria andContactEmployeeNameBetween(String value1, String value2) {
            addCriterion("contact_employee_name between", value1, value2, "contactEmployeeName");
            return (Criteria) this;
        }

        public Criteria andContactEmployeeNameNotBetween(String value1, String value2) {
            addCriterion("contact_employee_name not between", value1, value2, "contactEmployeeName");
            return (Criteria) this;
        }

        public Criteria andContactFullDeptPathIsNull() {
            addCriterion("contact_full_dept_path is null");
            return (Criteria) this;
        }

        public Criteria andContactFullDeptPathIsNotNull() {
            addCriterion("contact_full_dept_path is not null");
            return (Criteria) this;
        }

        public Criteria andContactFullDeptPathEqualTo(String value) {
            addCriterion("contact_full_dept_path =", value, "contactFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andContactFullDeptPathNotEqualTo(String value) {
            addCriterion("contact_full_dept_path <>", value, "contactFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andContactFullDeptPathGreaterThan(String value) {
            addCriterion("contact_full_dept_path >", value, "contactFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andContactFullDeptPathGreaterThanOrEqualTo(String value) {
            addCriterion("contact_full_dept_path >=", value, "contactFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andContactFullDeptPathLessThan(String value) {
            addCriterion("contact_full_dept_path <", value, "contactFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andContactFullDeptPathLessThanOrEqualTo(String value) {
            addCriterion("contact_full_dept_path <=", value, "contactFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andContactFullDeptPathLike(String value) {
            addCriterion("contact_full_dept_path like", value, "contactFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andContactFullDeptPathNotLike(String value) {
            addCriterion("contact_full_dept_path not like", value, "contactFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andContactFullDeptPathIn(List<String> values) {
            addCriterion("contact_full_dept_path in", values, "contactFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andContactFullDeptPathNotIn(List<String> values) {
            addCriterion("contact_full_dept_path not in", values, "contactFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andContactFullDeptPathBetween(String value1, String value2) {
            addCriterion("contact_full_dept_path between", value1, value2, "contactFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andContactFullDeptPathNotBetween(String value1, String value2) {
            addCriterion("contact_full_dept_path not between", value1, value2, "contactFullDeptPath");
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