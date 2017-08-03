package com.longfor.itserver.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductCommentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProductCommentExample() {
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

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(Long value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(Long value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(Long value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(Long value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(Long value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(Long value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<Long> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<Long> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(Long value1, Long value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(Long value1, Long value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(Long value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(Long value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(Long value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(Long value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(Long value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<Long> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<Long> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(Long value1, Long value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(Long value1, Long value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andAccountIdIsNull() {
            addCriterion("account_id is null");
            return (Criteria) this;
        }

        public Criteria andAccountIdIsNotNull() {
            addCriterion("account_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccountIdEqualTo(String value) {
            addCriterion("account_id =", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotEqualTo(String value) {
            addCriterion("account_id <>", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThan(String value) {
            addCriterion("account_id >", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThanOrEqualTo(String value) {
            addCriterion("account_id >=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThan(String value) {
            addCriterion("account_id <", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThanOrEqualTo(String value) {
            addCriterion("account_id <=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLike(String value) {
            addCriterion("account_id like", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotLike(String value) {
            addCriterion("account_id not like", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdIn(List<String> values) {
            addCriterion("account_id in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotIn(List<String> values) {
            addCriterion("account_id not in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdBetween(String value1, String value2) {
            addCriterion("account_id between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotBetween(String value1, String value2) {
            addCriterion("account_id not between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andEmployeeCodeIsNull() {
            addCriterion("employee_code is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeCodeIsNotNull() {
            addCriterion("employee_code is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeCodeEqualTo(Long value) {
            addCriterion("employee_code =", value, "employeeCode");
            return (Criteria) this;
        }

        public Criteria andEmployeeCodeNotEqualTo(Long value) {
            addCriterion("employee_code <>", value, "employeeCode");
            return (Criteria) this;
        }

        public Criteria andEmployeeCodeGreaterThan(Long value) {
            addCriterion("employee_code >", value, "employeeCode");
            return (Criteria) this;
        }

        public Criteria andEmployeeCodeGreaterThanOrEqualTo(Long value) {
            addCriterion("employee_code >=", value, "employeeCode");
            return (Criteria) this;
        }

        public Criteria andEmployeeCodeLessThan(Long value) {
            addCriterion("employee_code <", value, "employeeCode");
            return (Criteria) this;
        }

        public Criteria andEmployeeCodeLessThanOrEqualTo(Long value) {
            addCriterion("employee_code <=", value, "employeeCode");
            return (Criteria) this;
        }

        public Criteria andEmployeeCodeIn(List<Long> values) {
            addCriterion("employee_code in", values, "employeeCode");
            return (Criteria) this;
        }

        public Criteria andEmployeeCodeNotIn(List<Long> values) {
            addCriterion("employee_code not in", values, "employeeCode");
            return (Criteria) this;
        }

        public Criteria andEmployeeCodeBetween(Long value1, Long value2) {
            addCriterion("employee_code between", value1, value2, "employeeCode");
            return (Criteria) this;
        }

        public Criteria andEmployeeCodeNotBetween(Long value1, Long value2) {
            addCriterion("employee_code not between", value1, value2, "employeeCode");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameIsNull() {
            addCriterion("employee_name is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameIsNotNull() {
            addCriterion("employee_name is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameEqualTo(String value) {
            addCriterion("employee_name =", value, "employeeName");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameNotEqualTo(String value) {
            addCriterion("employee_name <>", value, "employeeName");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameGreaterThan(String value) {
            addCriterion("employee_name >", value, "employeeName");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameGreaterThanOrEqualTo(String value) {
            addCriterion("employee_name >=", value, "employeeName");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameLessThan(String value) {
            addCriterion("employee_name <", value, "employeeName");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameLessThanOrEqualTo(String value) {
            addCriterion("employee_name <=", value, "employeeName");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameLike(String value) {
            addCriterion("employee_name like", value, "employeeName");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameNotLike(String value) {
            addCriterion("employee_name not like", value, "employeeName");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameIn(List<String> values) {
            addCriterion("employee_name in", values, "employeeName");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameNotIn(List<String> values) {
            addCriterion("employee_name not in", values, "employeeName");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameBetween(String value1, String value2) {
            addCriterion("employee_name between", value1, value2, "employeeName");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameNotBetween(String value1, String value2) {
            addCriterion("employee_name not between", value1, value2, "employeeName");
            return (Criteria) this;
        }

        public Criteria andFullDeptPathIsNull() {
            addCriterion("full_dept_path is null");
            return (Criteria) this;
        }

        public Criteria andFullDeptPathIsNotNull() {
            addCriterion("full_dept_path is not null");
            return (Criteria) this;
        }

        public Criteria andFullDeptPathEqualTo(String value) {
            addCriterion("full_dept_path =", value, "fullDeptPath");
            return (Criteria) this;
        }

        public Criteria andFullDeptPathNotEqualTo(String value) {
            addCriterion("full_dept_path <>", value, "fullDeptPath");
            return (Criteria) this;
        }

        public Criteria andFullDeptPathGreaterThan(String value) {
            addCriterion("full_dept_path >", value, "fullDeptPath");
            return (Criteria) this;
        }

        public Criteria andFullDeptPathGreaterThanOrEqualTo(String value) {
            addCriterion("full_dept_path >=", value, "fullDeptPath");
            return (Criteria) this;
        }

        public Criteria andFullDeptPathLessThan(String value) {
            addCriterion("full_dept_path <", value, "fullDeptPath");
            return (Criteria) this;
        }

        public Criteria andFullDeptPathLessThanOrEqualTo(String value) {
            addCriterion("full_dept_path <=", value, "fullDeptPath");
            return (Criteria) this;
        }

        public Criteria andFullDeptPathLike(String value) {
            addCriterion("full_dept_path like", value, "fullDeptPath");
            return (Criteria) this;
        }

        public Criteria andFullDeptPathNotLike(String value) {
            addCriterion("full_dept_path not like", value, "fullDeptPath");
            return (Criteria) this;
        }

        public Criteria andFullDeptPathIn(List<String> values) {
            addCriterion("full_dept_path in", values, "fullDeptPath");
            return (Criteria) this;
        }

        public Criteria andFullDeptPathNotIn(List<String> values) {
            addCriterion("full_dept_path not in", values, "fullDeptPath");
            return (Criteria) this;
        }

        public Criteria andFullDeptPathBetween(String value1, String value2) {
            addCriterion("full_dept_path between", value1, value2, "fullDeptPath");
            return (Criteria) this;
        }

        public Criteria andFullDeptPathNotBetween(String value1, String value2) {
            addCriterion("full_dept_path not between", value1, value2, "fullDeptPath");
            return (Criteria) this;
        }

        public Criteria andEmployeeTypeIdIsNull() {
            addCriterion("employee_type_id is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeTypeIdIsNotNull() {
            addCriterion("employee_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeTypeIdEqualTo(Long value) {
            addCriterion("employee_type_id =", value, "employeeTypeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeTypeIdNotEqualTo(Long value) {
            addCriterion("employee_type_id <>", value, "employeeTypeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeTypeIdGreaterThan(Long value) {
            addCriterion("employee_type_id >", value, "employeeTypeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeTypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("employee_type_id >=", value, "employeeTypeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeTypeIdLessThan(Long value) {
            addCriterion("employee_type_id <", value, "employeeTypeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeTypeIdLessThanOrEqualTo(Long value) {
            addCriterion("employee_type_id <=", value, "employeeTypeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeTypeIdIn(List<Long> values) {
            addCriterion("employee_type_id in", values, "employeeTypeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeTypeIdNotIn(List<Long> values) {
            addCriterion("employee_type_id not in", values, "employeeTypeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeTypeIdBetween(Long value1, Long value2) {
            addCriterion("employee_type_id between", value1, value2, "employeeTypeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeTypeIdNotBetween(Long value1, Long value2) {
            addCriterion("employee_type_id not between", value1, value2, "employeeTypeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeTypeTextIsNull() {
            addCriterion("employee_type_text is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeTypeTextIsNotNull() {
            addCriterion("employee_type_text is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeTypeTextEqualTo(String value) {
            addCriterion("employee_type_text =", value, "employeeTypeText");
            return (Criteria) this;
        }

        public Criteria andEmployeeTypeTextNotEqualTo(String value) {
            addCriterion("employee_type_text <>", value, "employeeTypeText");
            return (Criteria) this;
        }

        public Criteria andEmployeeTypeTextGreaterThan(String value) {
            addCriterion("employee_type_text >", value, "employeeTypeText");
            return (Criteria) this;
        }

        public Criteria andEmployeeTypeTextGreaterThanOrEqualTo(String value) {
            addCriterion("employee_type_text >=", value, "employeeTypeText");
            return (Criteria) this;
        }

        public Criteria andEmployeeTypeTextLessThan(String value) {
            addCriterion("employee_type_text <", value, "employeeTypeText");
            return (Criteria) this;
        }

        public Criteria andEmployeeTypeTextLessThanOrEqualTo(String value) {
            addCriterion("employee_type_text <=", value, "employeeTypeText");
            return (Criteria) this;
        }

        public Criteria andEmployeeTypeTextLike(String value) {
            addCriterion("employee_type_text like", value, "employeeTypeText");
            return (Criteria) this;
        }

        public Criteria andEmployeeTypeTextNotLike(String value) {
            addCriterion("employee_type_text not like", value, "employeeTypeText");
            return (Criteria) this;
        }

        public Criteria andEmployeeTypeTextIn(List<String> values) {
            addCriterion("employee_type_text in", values, "employeeTypeText");
            return (Criteria) this;
        }

        public Criteria andEmployeeTypeTextNotIn(List<String> values) {
            addCriterion("employee_type_text not in", values, "employeeTypeText");
            return (Criteria) this;
        }

        public Criteria andEmployeeTypeTextBetween(String value1, String value2) {
            addCriterion("employee_type_text between", value1, value2, "employeeTypeText");
            return (Criteria) this;
        }

        public Criteria andEmployeeTypeTextNotBetween(String value1, String value2) {
            addCriterion("employee_type_text not between", value1, value2, "employeeTypeText");
            return (Criteria) this;
        }

        public Criteria andLevelNumIsNull() {
            addCriterion("level_num is null");
            return (Criteria) this;
        }

        public Criteria andLevelNumIsNotNull() {
            addCriterion("level_num is not null");
            return (Criteria) this;
        }

        public Criteria andLevelNumEqualTo(Integer value) {
            addCriterion("level_num =", value, "levelNum");
            return (Criteria) this;
        }

        public Criteria andLevelNumNotEqualTo(Integer value) {
            addCriterion("level_num <>", value, "levelNum");
            return (Criteria) this;
        }

        public Criteria andLevelNumGreaterThan(Integer value) {
            addCriterion("level_num >", value, "levelNum");
            return (Criteria) this;
        }

        public Criteria andLevelNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("level_num >=", value, "levelNum");
            return (Criteria) this;
        }

        public Criteria andLevelNumLessThan(Integer value) {
            addCriterion("level_num <", value, "levelNum");
            return (Criteria) this;
        }

        public Criteria andLevelNumLessThanOrEqualTo(Integer value) {
            addCriterion("level_num <=", value, "levelNum");
            return (Criteria) this;
        }

        public Criteria andLevelNumIn(List<Integer> values) {
            addCriterion("level_num in", values, "levelNum");
            return (Criteria) this;
        }

        public Criteria andLevelNumNotIn(List<Integer> values) {
            addCriterion("level_num not in", values, "levelNum");
            return (Criteria) this;
        }

        public Criteria andLevelNumBetween(Integer value1, Integer value2) {
            addCriterion("level_num between", value1, value2, "levelNum");
            return (Criteria) this;
        }

        public Criteria andLevelNumNotBetween(Integer value1, Integer value2) {
            addCriterion("level_num not between", value1, value2, "levelNum");
            return (Criteria) this;
        }

        public Criteria andIpIsNull() {
            addCriterion("ip is null");
            return (Criteria) this;
        }

        public Criteria andIpIsNotNull() {
            addCriterion("ip is not null");
            return (Criteria) this;
        }

        public Criteria andIpEqualTo(String value) {
            addCriterion("ip =", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotEqualTo(String value) {
            addCriterion("ip <>", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThan(String value) {
            addCriterion("ip >", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThanOrEqualTo(String value) {
            addCriterion("ip >=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThan(String value) {
            addCriterion("ip <", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThanOrEqualTo(String value) {
            addCriterion("ip <=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLike(String value) {
            addCriterion("ip like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotLike(String value) {
            addCriterion("ip not like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpIn(List<String> values) {
            addCriterion("ip in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotIn(List<String> values) {
            addCriterion("ip not in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpBetween(String value1, String value2) {
            addCriterion("ip between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotBetween(String value1, String value2) {
            addCriterion("ip not between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andReplyTypeIsNull() {
            addCriterion("reply_type is null");
            return (Criteria) this;
        }

        public Criteria andReplyTypeIsNotNull() {
            addCriterion("reply_type is not null");
            return (Criteria) this;
        }

        public Criteria andReplyTypeEqualTo(Integer value) {
            addCriterion("reply_type =", value, "replyType");
            return (Criteria) this;
        }

        public Criteria andReplyTypeNotEqualTo(Integer value) {
            addCriterion("reply_type <>", value, "replyType");
            return (Criteria) this;
        }

        public Criteria andReplyTypeGreaterThan(Integer value) {
            addCriterion("reply_type >", value, "replyType");
            return (Criteria) this;
        }

        public Criteria andReplyTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("reply_type >=", value, "replyType");
            return (Criteria) this;
        }

        public Criteria andReplyTypeLessThan(Integer value) {
            addCriterion("reply_type <", value, "replyType");
            return (Criteria) this;
        }

        public Criteria andReplyTypeLessThanOrEqualTo(Integer value) {
            addCriterion("reply_type <=", value, "replyType");
            return (Criteria) this;
        }

        public Criteria andReplyTypeIn(List<Integer> values) {
            addCriterion("reply_type in", values, "replyType");
            return (Criteria) this;
        }

        public Criteria andReplyTypeNotIn(List<Integer> values) {
            addCriterion("reply_type not in", values, "replyType");
            return (Criteria) this;
        }

        public Criteria andReplyTypeBetween(Integer value1, Integer value2) {
            addCriterion("reply_type between", value1, value2, "replyType");
            return (Criteria) this;
        }

        public Criteria andReplyTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("reply_type not between", value1, value2, "replyType");
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