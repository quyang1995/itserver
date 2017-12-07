package com.longfor.itserver.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProgramApprovalSnapshotExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProgramApprovalSnapshotExample() {
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

        public Criteria andProductCodeIsNull() {
            addCriterion("product_code is null");
            return (Criteria) this;
        }

        public Criteria andProductCodeIsNotNull() {
            addCriterion("product_code is not null");
            return (Criteria) this;
        }

        public Criteria andProductCodeEqualTo(String value) {
            addCriterion("product_code =", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotEqualTo(String value) {
            addCriterion("product_code <>", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeGreaterThan(String value) {
            addCriterion("product_code >", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeGreaterThanOrEqualTo(String value) {
            addCriterion("product_code >=", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeLessThan(String value) {
            addCriterion("product_code <", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeLessThanOrEqualTo(String value) {
            addCriterion("product_code <=", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeLike(String value) {
            addCriterion("product_code like", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotLike(String value) {
            addCriterion("product_code not like", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeIn(List<String> values) {
            addCriterion("product_code in", values, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotIn(List<String> values) {
            addCriterion("product_code not in", values, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeBetween(String value1, String value2) {
            addCriterion("product_code between", value1, value2, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotBetween(String value1, String value2) {
            addCriterion("product_code not between", value1, value2, "productCode");
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

        public Criteria andCommitDateIsNull() {
            addCriterion("commit_date is null");
            return (Criteria) this;
        }

        public Criteria andCommitDateIsNotNull() {
            addCriterion("commit_date is not null");
            return (Criteria) this;
        }

        public Criteria andCommitDateEqualTo(Date value) {
            addCriterion("commit_date =", value, "commitDate");
            return (Criteria) this;
        }

        public Criteria andCommitDateNotEqualTo(Date value) {
            addCriterion("commit_date <>", value, "commitDate");
            return (Criteria) this;
        }

        public Criteria andCommitDateGreaterThan(Date value) {
            addCriterion("commit_date >", value, "commitDate");
            return (Criteria) this;
        }

        public Criteria andCommitDateGreaterThanOrEqualTo(Date value) {
            addCriterion("commit_date >=", value, "commitDate");
            return (Criteria) this;
        }

        public Criteria andCommitDateLessThan(Date value) {
            addCriterion("commit_date <", value, "commitDate");
            return (Criteria) this;
        }

        public Criteria andCommitDateLessThanOrEqualTo(Date value) {
            addCriterion("commit_date <=", value, "commitDate");
            return (Criteria) this;
        }

        public Criteria andCommitDateIn(List<Date> values) {
            addCriterion("commit_date in", values, "commitDate");
            return (Criteria) this;
        }

        public Criteria andCommitDateNotIn(List<Date> values) {
            addCriterion("commit_date not in", values, "commitDate");
            return (Criteria) this;
        }

        public Criteria andCommitDateBetween(Date value1, Date value2) {
            addCriterion("commit_date between", value1, value2, "commitDate");
            return (Criteria) this;
        }

        public Criteria andCommitDateNotBetween(Date value1, Date value2) {
            addCriterion("commit_date not between", value1, value2, "commitDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNull() {
            addCriterion("start_date is null");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNotNull() {
            addCriterion("start_date is not null");
            return (Criteria) this;
        }

        public Criteria andStartDateEqualTo(Date value) {
            addCriterion("start_date =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotEqualTo(Date value) {
            addCriterion("start_date <>", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThan(Date value) {
            addCriterion("start_date >", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(Date value) {
            addCriterion("start_date >=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThan(Date value) {
            addCriterion("start_date <", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThanOrEqualTo(Date value) {
            addCriterion("start_date <=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIn(List<Date> values) {
            addCriterion("start_date in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotIn(List<Date> values) {
            addCriterion("start_date not in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateBetween(Date value1, Date value2) {
            addCriterion("start_date between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotBetween(Date value1, Date value2) {
            addCriterion("start_date not between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andGrayReleaseDateIsNull() {
            addCriterion("gray_release_date is null");
            return (Criteria) this;
        }

        public Criteria andGrayReleaseDateIsNotNull() {
            addCriterion("gray_release_date is not null");
            return (Criteria) this;
        }

        public Criteria andGrayReleaseDateEqualTo(Date value) {
            addCriterion("gray_release_date =", value, "grayReleaseDate");
            return (Criteria) this;
        }

        public Criteria andGrayReleaseDateNotEqualTo(Date value) {
            addCriterion("gray_release_date <>", value, "grayReleaseDate");
            return (Criteria) this;
        }

        public Criteria andGrayReleaseDateGreaterThan(Date value) {
            addCriterion("gray_release_date >", value, "grayReleaseDate");
            return (Criteria) this;
        }

        public Criteria andGrayReleaseDateGreaterThanOrEqualTo(Date value) {
            addCriterion("gray_release_date >=", value, "grayReleaseDate");
            return (Criteria) this;
        }

        public Criteria andGrayReleaseDateLessThan(Date value) {
            addCriterion("gray_release_date <", value, "grayReleaseDate");
            return (Criteria) this;
        }

        public Criteria andGrayReleaseDateLessThanOrEqualTo(Date value) {
            addCriterion("gray_release_date <=", value, "grayReleaseDate");
            return (Criteria) this;
        }

        public Criteria andGrayReleaseDateIn(List<Date> values) {
            addCriterion("gray_release_date in", values, "grayReleaseDate");
            return (Criteria) this;
        }

        public Criteria andGrayReleaseDateNotIn(List<Date> values) {
            addCriterion("gray_release_date not in", values, "grayReleaseDate");
            return (Criteria) this;
        }

        public Criteria andGrayReleaseDateBetween(Date value1, Date value2) {
            addCriterion("gray_release_date between", value1, value2, "grayReleaseDate");
            return (Criteria) this;
        }

        public Criteria andGrayReleaseDateNotBetween(Date value1, Date value2) {
            addCriterion("gray_release_date not between", value1, value2, "grayReleaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateIsNull() {
            addCriterion("release_date is null");
            return (Criteria) this;
        }

        public Criteria andReleaseDateIsNotNull() {
            addCriterion("release_date is not null");
            return (Criteria) this;
        }

        public Criteria andReleaseDateEqualTo(Date value) {
            addCriterion("release_date =", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateNotEqualTo(Date value) {
            addCriterion("release_date <>", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateGreaterThan(Date value) {
            addCriterion("release_date >", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateGreaterThanOrEqualTo(Date value) {
            addCriterion("release_date >=", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateLessThan(Date value) {
            addCriterion("release_date <", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateLessThanOrEqualTo(Date value) {
            addCriterion("release_date <=", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateIn(List<Date> values) {
            addCriterion("release_date in", values, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateNotIn(List<Date> values) {
            addCriterion("release_date not in", values, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateBetween(Date value1, Date value2) {
            addCriterion("release_date between", value1, value2, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateNotBetween(Date value1, Date value2) {
            addCriterion("release_date not between", value1, value2, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andUedDateIsNull() {
            addCriterion("ued_date is null");
            return (Criteria) this;
        }

        public Criteria andUedDateIsNotNull() {
            addCriterion("ued_date is not null");
            return (Criteria) this;
        }

        public Criteria andUedDateEqualTo(Date value) {
            addCriterion("ued_date =", value, "uedDate");
            return (Criteria) this;
        }

        public Criteria andUedDateNotEqualTo(Date value) {
            addCriterion("ued_date <>", value, "uedDate");
            return (Criteria) this;
        }

        public Criteria andUedDateGreaterThan(Date value) {
            addCriterion("ued_date >", value, "uedDate");
            return (Criteria) this;
        }

        public Criteria andUedDateGreaterThanOrEqualTo(Date value) {
            addCriterion("ued_date >=", value, "uedDate");
            return (Criteria) this;
        }

        public Criteria andUedDateLessThan(Date value) {
            addCriterion("ued_date <", value, "uedDate");
            return (Criteria) this;
        }

        public Criteria andUedDateLessThanOrEqualTo(Date value) {
            addCriterion("ued_date <=", value, "uedDate");
            return (Criteria) this;
        }

        public Criteria andUedDateIn(List<Date> values) {
            addCriterion("ued_date in", values, "uedDate");
            return (Criteria) this;
        }

        public Criteria andUedDateNotIn(List<Date> values) {
            addCriterion("ued_date not in", values, "uedDate");
            return (Criteria) this;
        }

        public Criteria andUedDateBetween(Date value1, Date value2) {
            addCriterion("ued_date between", value1, value2, "uedDate");
            return (Criteria) this;
        }

        public Criteria andUedDateNotBetween(Date value1, Date value2) {
            addCriterion("ued_date not between", value1, value2, "uedDate");
            return (Criteria) this;
        }

        public Criteria andArchitectureDateIsNull() {
            addCriterion("architecture_date is null");
            return (Criteria) this;
        }

        public Criteria andArchitectureDateIsNotNull() {
            addCriterion("architecture_date is not null");
            return (Criteria) this;
        }

        public Criteria andArchitectureDateEqualTo(Date value) {
            addCriterion("architecture_date =", value, "architectureDate");
            return (Criteria) this;
        }

        public Criteria andArchitectureDateNotEqualTo(Date value) {
            addCriterion("architecture_date <>", value, "architectureDate");
            return (Criteria) this;
        }

        public Criteria andArchitectureDateGreaterThan(Date value) {
            addCriterion("architecture_date >", value, "architectureDate");
            return (Criteria) this;
        }

        public Criteria andArchitectureDateGreaterThanOrEqualTo(Date value) {
            addCriterion("architecture_date >=", value, "architectureDate");
            return (Criteria) this;
        }

        public Criteria andArchitectureDateLessThan(Date value) {
            addCriterion("architecture_date <", value, "architectureDate");
            return (Criteria) this;
        }

        public Criteria andArchitectureDateLessThanOrEqualTo(Date value) {
            addCriterion("architecture_date <=", value, "architectureDate");
            return (Criteria) this;
        }

        public Criteria andArchitectureDateIn(List<Date> values) {
            addCriterion("architecture_date in", values, "architectureDate");
            return (Criteria) this;
        }

        public Criteria andArchitectureDateNotIn(List<Date> values) {
            addCriterion("architecture_date not in", values, "architectureDate");
            return (Criteria) this;
        }

        public Criteria andArchitectureDateBetween(Date value1, Date value2) {
            addCriterion("architecture_date between", value1, value2, "architectureDate");
            return (Criteria) this;
        }

        public Criteria andArchitectureDateNotBetween(Date value1, Date value2) {
            addCriterion("architecture_date not between", value1, value2, "architectureDate");
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

        public Criteria andProgramStatusIsNull() {
            addCriterion("program_status is null");
            return (Criteria) this;
        }

        public Criteria andProgramStatusIsNotNull() {
            addCriterion("program_status is not null");
            return (Criteria) this;
        }

        public Criteria andProgramStatusEqualTo(Integer value) {
            addCriterion("program_status =", value, "programStatus");
            return (Criteria) this;
        }

        public Criteria andProgramStatusNotEqualTo(Integer value) {
            addCriterion("program_status <>", value, "programStatus");
            return (Criteria) this;
        }

        public Criteria andProgramStatusGreaterThan(Integer value) {
            addCriterion("program_status >", value, "programStatus");
            return (Criteria) this;
        }

        public Criteria andProgramStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("program_status >=", value, "programStatus");
            return (Criteria) this;
        }

        public Criteria andProgramStatusLessThan(Integer value) {
            addCriterion("program_status <", value, "programStatus");
            return (Criteria) this;
        }

        public Criteria andProgramStatusLessThanOrEqualTo(Integer value) {
            addCriterion("program_status <=", value, "programStatus");
            return (Criteria) this;
        }

        public Criteria andProgramStatusIn(List<Integer> values) {
            addCriterion("program_status in", values, "programStatus");
            return (Criteria) this;
        }

        public Criteria andProgramStatusNotIn(List<Integer> values) {
            addCriterion("program_status not in", values, "programStatus");
            return (Criteria) this;
        }

        public Criteria andProgramStatusBetween(Integer value1, Integer value2) {
            addCriterion("program_status between", value1, value2, "programStatus");
            return (Criteria) this;
        }

        public Criteria andProgramStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("program_status not between", value1, value2, "programStatus");
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

        public Criteria andAccountTypeIsNull() {
            addCriterion("account_type is null");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIsNotNull() {
            addCriterion("account_type is not null");
            return (Criteria) this;
        }

        public Criteria andAccountTypeEqualTo(Integer value) {
            addCriterion("account_type =", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotEqualTo(Integer value) {
            addCriterion("account_type <>", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeGreaterThan(Integer value) {
            addCriterion("account_type >", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("account_type >=", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeLessThan(Integer value) {
            addCriterion("account_type <", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeLessThanOrEqualTo(Integer value) {
            addCriterion("account_type <=", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIn(List<Integer> values) {
            addCriterion("account_type in", values, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotIn(List<Integer> values) {
            addCriterion("account_type not in", values, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeBetween(Integer value1, Integer value2) {
            addCriterion("account_type between", value1, value2, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("account_type not between", value1, value2, "accountType");
            return (Criteria) this;
        }

        public Criteria andDemoApprovalDateIsNull() {
            addCriterion("demo_approval_date is null");
            return (Criteria) this;
        }

        public Criteria andDemoApprovalDateIsNotNull() {
            addCriterion("demo_approval_date is not null");
            return (Criteria) this;
        }

        public Criteria andDemoApprovalDateEqualTo(Date value) {
            addCriterion("demo_approval_date =", value, "demoApprovalDate");
            return (Criteria) this;
        }

        public Criteria andDemoApprovalDateNotEqualTo(Date value) {
            addCriterion("demo_approval_date <>", value, "demoApprovalDate");
            return (Criteria) this;
        }

        public Criteria andDemoApprovalDateGreaterThan(Date value) {
            addCriterion("demo_approval_date >", value, "demoApprovalDate");
            return (Criteria) this;
        }

        public Criteria andDemoApprovalDateGreaterThanOrEqualTo(Date value) {
            addCriterion("demo_approval_date >=", value, "demoApprovalDate");
            return (Criteria) this;
        }

        public Criteria andDemoApprovalDateLessThan(Date value) {
            addCriterion("demo_approval_date <", value, "demoApprovalDate");
            return (Criteria) this;
        }

        public Criteria andDemoApprovalDateLessThanOrEqualTo(Date value) {
            addCriterion("demo_approval_date <=", value, "demoApprovalDate");
            return (Criteria) this;
        }

        public Criteria andDemoApprovalDateIn(List<Date> values) {
            addCriterion("demo_approval_date in", values, "demoApprovalDate");
            return (Criteria) this;
        }

        public Criteria andDemoApprovalDateNotIn(List<Date> values) {
            addCriterion("demo_approval_date not in", values, "demoApprovalDate");
            return (Criteria) this;
        }

        public Criteria andDemoApprovalDateBetween(Date value1, Date value2) {
            addCriterion("demo_approval_date between", value1, value2, "demoApprovalDate");
            return (Criteria) this;
        }

        public Criteria andDemoApprovalDateNotBetween(Date value1, Date value2) {
            addCriterion("demo_approval_date not between", value1, value2, "demoApprovalDate");
            return (Criteria) this;
        }

        public Criteria andBiddingDateIsNull() {
            addCriterion("bidding_date is null");
            return (Criteria) this;
        }

        public Criteria andBiddingDateIsNotNull() {
            addCriterion("bidding_date is not null");
            return (Criteria) this;
        }

        public Criteria andBiddingDateEqualTo(Date value) {
            addCriterion("bidding_date =", value, "biddingDate");
            return (Criteria) this;
        }

        public Criteria andBiddingDateNotEqualTo(Date value) {
            addCriterion("bidding_date <>", value, "biddingDate");
            return (Criteria) this;
        }

        public Criteria andBiddingDateGreaterThan(Date value) {
            addCriterion("bidding_date >", value, "biddingDate");
            return (Criteria) this;
        }

        public Criteria andBiddingDateGreaterThanOrEqualTo(Date value) {
            addCriterion("bidding_date >=", value, "biddingDate");
            return (Criteria) this;
        }

        public Criteria andBiddingDateLessThan(Date value) {
            addCriterion("bidding_date <", value, "biddingDate");
            return (Criteria) this;
        }

        public Criteria andBiddingDateLessThanOrEqualTo(Date value) {
            addCriterion("bidding_date <=", value, "biddingDate");
            return (Criteria) this;
        }

        public Criteria andBiddingDateIn(List<Date> values) {
            addCriterion("bidding_date in", values, "biddingDate");
            return (Criteria) this;
        }

        public Criteria andBiddingDateNotIn(List<Date> values) {
            addCriterion("bidding_date not in", values, "biddingDate");
            return (Criteria) this;
        }

        public Criteria andBiddingDateBetween(Date value1, Date value2) {
            addCriterion("bidding_date between", value1, value2, "biddingDate");
            return (Criteria) this;
        }

        public Criteria andBiddingDateNotBetween(Date value1, Date value2) {
            addCriterion("bidding_date not between", value1, value2, "biddingDate");
            return (Criteria) this;
        }

        public Criteria andWinningBidDateIsNull() {
            addCriterion("winning_bid_date is null");
            return (Criteria) this;
        }

        public Criteria andWinningBidDateIsNotNull() {
            addCriterion("winning_bid_date is not null");
            return (Criteria) this;
        }

        public Criteria andWinningBidDateEqualTo(Date value) {
            addCriterion("winning_bid_date =", value, "winningBidDate");
            return (Criteria) this;
        }

        public Criteria andWinningBidDateNotEqualTo(Date value) {
            addCriterion("winning_bid_date <>", value, "winningBidDate");
            return (Criteria) this;
        }

        public Criteria andWinningBidDateGreaterThan(Date value) {
            addCriterion("winning_bid_date >", value, "winningBidDate");
            return (Criteria) this;
        }

        public Criteria andWinningBidDateGreaterThanOrEqualTo(Date value) {
            addCriterion("winning_bid_date >=", value, "winningBidDate");
            return (Criteria) this;
        }

        public Criteria andWinningBidDateLessThan(Date value) {
            addCriterion("winning_bid_date <", value, "winningBidDate");
            return (Criteria) this;
        }

        public Criteria andWinningBidDateLessThanOrEqualTo(Date value) {
            addCriterion("winning_bid_date <=", value, "winningBidDate");
            return (Criteria) this;
        }

        public Criteria andWinningBidDateIn(List<Date> values) {
            addCriterion("winning_bid_date in", values, "winningBidDate");
            return (Criteria) this;
        }

        public Criteria andWinningBidDateNotIn(List<Date> values) {
            addCriterion("winning_bid_date not in", values, "winningBidDate");
            return (Criteria) this;
        }

        public Criteria andWinningBidDateBetween(Date value1, Date value2) {
            addCriterion("winning_bid_date between", value1, value2, "winningBidDate");
            return (Criteria) this;
        }

        public Criteria andWinningBidDateNotBetween(Date value1, Date value2) {
            addCriterion("winning_bid_date not between", value1, value2, "winningBidDate");
            return (Criteria) this;
        }

        public Criteria andProdApprovalDateIsNull() {
            addCriterion("prod_approval_date is null");
            return (Criteria) this;
        }

        public Criteria andProdApprovalDateIsNotNull() {
            addCriterion("prod_approval_date is not null");
            return (Criteria) this;
        }

        public Criteria andProdApprovalDateEqualTo(Date value) {
            addCriterion("prod_approval_date =", value, "prodApprovalDate");
            return (Criteria) this;
        }

        public Criteria andProdApprovalDateNotEqualTo(Date value) {
            addCriterion("prod_approval_date <>", value, "prodApprovalDate");
            return (Criteria) this;
        }

        public Criteria andProdApprovalDateGreaterThan(Date value) {
            addCriterion("prod_approval_date >", value, "prodApprovalDate");
            return (Criteria) this;
        }

        public Criteria andProdApprovalDateGreaterThanOrEqualTo(Date value) {
            addCriterion("prod_approval_date >=", value, "prodApprovalDate");
            return (Criteria) this;
        }

        public Criteria andProdApprovalDateLessThan(Date value) {
            addCriterion("prod_approval_date <", value, "prodApprovalDate");
            return (Criteria) this;
        }

        public Criteria andProdApprovalDateLessThanOrEqualTo(Date value) {
            addCriterion("prod_approval_date <=", value, "prodApprovalDate");
            return (Criteria) this;
        }

        public Criteria andProdApprovalDateIn(List<Date> values) {
            addCriterion("prod_approval_date in", values, "prodApprovalDate");
            return (Criteria) this;
        }

        public Criteria andProdApprovalDateNotIn(List<Date> values) {
            addCriterion("prod_approval_date not in", values, "prodApprovalDate");
            return (Criteria) this;
        }

        public Criteria andProdApprovalDateBetween(Date value1, Date value2) {
            addCriterion("prod_approval_date between", value1, value2, "prodApprovalDate");
            return (Criteria) this;
        }

        public Criteria andProdApprovalDateNotBetween(Date value1, Date value2) {
            addCriterion("prod_approval_date not between", value1, value2, "prodApprovalDate");
            return (Criteria) this;
        }

        public Criteria andDevApprovalDateIsNull() {
            addCriterion("dev_approval_date is null");
            return (Criteria) this;
        }

        public Criteria andDevApprovalDateIsNotNull() {
            addCriterion("dev_approval_date is not null");
            return (Criteria) this;
        }

        public Criteria andDevApprovalDateEqualTo(Date value) {
            addCriterion("dev_approval_date =", value, "devApprovalDate");
            return (Criteria) this;
        }

        public Criteria andDevApprovalDateNotEqualTo(Date value) {
            addCriterion("dev_approval_date <>", value, "devApprovalDate");
            return (Criteria) this;
        }

        public Criteria andDevApprovalDateGreaterThan(Date value) {
            addCriterion("dev_approval_date >", value, "devApprovalDate");
            return (Criteria) this;
        }

        public Criteria andDevApprovalDateGreaterThanOrEqualTo(Date value) {
            addCriterion("dev_approval_date >=", value, "devApprovalDate");
            return (Criteria) this;
        }

        public Criteria andDevApprovalDateLessThan(Date value) {
            addCriterion("dev_approval_date <", value, "devApprovalDate");
            return (Criteria) this;
        }

        public Criteria andDevApprovalDateLessThanOrEqualTo(Date value) {
            addCriterion("dev_approval_date <=", value, "devApprovalDate");
            return (Criteria) this;
        }

        public Criteria andDevApprovalDateIn(List<Date> values) {
            addCriterion("dev_approval_date in", values, "devApprovalDate");
            return (Criteria) this;
        }

        public Criteria andDevApprovalDateNotIn(List<Date> values) {
            addCriterion("dev_approval_date not in", values, "devApprovalDate");
            return (Criteria) this;
        }

        public Criteria andDevApprovalDateBetween(Date value1, Date value2) {
            addCriterion("dev_approval_date between", value1, value2, "devApprovalDate");
            return (Criteria) this;
        }

        public Criteria andDevApprovalDateNotBetween(Date value1, Date value2) {
            addCriterion("dev_approval_date not between", value1, value2, "devApprovalDate");
            return (Criteria) this;
        }

        public Criteria andTestApprovalDateIsNull() {
            addCriterion("test_approval_date is null");
            return (Criteria) this;
        }

        public Criteria andTestApprovalDateIsNotNull() {
            addCriterion("test_approval_date is not null");
            return (Criteria) this;
        }

        public Criteria andTestApprovalDateEqualTo(Date value) {
            addCriterion("test_approval_date =", value, "testApprovalDate");
            return (Criteria) this;
        }

        public Criteria andTestApprovalDateNotEqualTo(Date value) {
            addCriterion("test_approval_date <>", value, "testApprovalDate");
            return (Criteria) this;
        }

        public Criteria andTestApprovalDateGreaterThan(Date value) {
            addCriterion("test_approval_date >", value, "testApprovalDate");
            return (Criteria) this;
        }

        public Criteria andTestApprovalDateGreaterThanOrEqualTo(Date value) {
            addCriterion("test_approval_date >=", value, "testApprovalDate");
            return (Criteria) this;
        }

        public Criteria andTestApprovalDateLessThan(Date value) {
            addCriterion("test_approval_date <", value, "testApprovalDate");
            return (Criteria) this;
        }

        public Criteria andTestApprovalDateLessThanOrEqualTo(Date value) {
            addCriterion("test_approval_date <=", value, "testApprovalDate");
            return (Criteria) this;
        }

        public Criteria andTestApprovalDateIn(List<Date> values) {
            addCriterion("test_approval_date in", values, "testApprovalDate");
            return (Criteria) this;
        }

        public Criteria andTestApprovalDateNotIn(List<Date> values) {
            addCriterion("test_approval_date not in", values, "testApprovalDate");
            return (Criteria) this;
        }

        public Criteria andTestApprovalDateBetween(Date value1, Date value2) {
            addCriterion("test_approval_date between", value1, value2, "testApprovalDate");
            return (Criteria) this;
        }

        public Criteria andTestApprovalDateNotBetween(Date value1, Date value2) {
            addCriterion("test_approval_date not between", value1, value2, "testApprovalDate");
            return (Criteria) this;
        }

        public Criteria andOnlinePlanDateIsNull() {
            addCriterion("online_plan_date is null");
            return (Criteria) this;
        }

        public Criteria andOnlinePlanDateIsNotNull() {
            addCriterion("online_plan_date is not null");
            return (Criteria) this;
        }

        public Criteria andOnlinePlanDateEqualTo(Date value) {
            addCriterion("online_plan_date =", value, "onlinePlanDate");
            return (Criteria) this;
        }

        public Criteria andOnlinePlanDateNotEqualTo(Date value) {
            addCriterion("online_plan_date <>", value, "onlinePlanDate");
            return (Criteria) this;
        }

        public Criteria andOnlinePlanDateGreaterThan(Date value) {
            addCriterion("online_plan_date >", value, "onlinePlanDate");
            return (Criteria) this;
        }

        public Criteria andOnlinePlanDateGreaterThanOrEqualTo(Date value) {
            addCriterion("online_plan_date >=", value, "onlinePlanDate");
            return (Criteria) this;
        }

        public Criteria andOnlinePlanDateLessThan(Date value) {
            addCriterion("online_plan_date <", value, "onlinePlanDate");
            return (Criteria) this;
        }

        public Criteria andOnlinePlanDateLessThanOrEqualTo(Date value) {
            addCriterion("online_plan_date <=", value, "onlinePlanDate");
            return (Criteria) this;
        }

        public Criteria andOnlinePlanDateIn(List<Date> values) {
            addCriterion("online_plan_date in", values, "onlinePlanDate");
            return (Criteria) this;
        }

        public Criteria andOnlinePlanDateNotIn(List<Date> values) {
            addCriterion("online_plan_date not in", values, "onlinePlanDate");
            return (Criteria) this;
        }

        public Criteria andOnlinePlanDateBetween(Date value1, Date value2) {
            addCriterion("online_plan_date between", value1, value2, "onlinePlanDate");
            return (Criteria) this;
        }

        public Criteria andOnlinePlanDateNotBetween(Date value1, Date value2) {
            addCriterion("online_plan_date not between", value1, value2, "onlinePlanDate");
            return (Criteria) this;
        }

        public Criteria andDevTypeIsNull() {
            addCriterion("dev_type is null");
            return (Criteria) this;
        }

        public Criteria andDevTypeIsNotNull() {
            addCriterion("dev_type is not null");
            return (Criteria) this;
        }

        public Criteria andDevTypeEqualTo(Integer value) {
            addCriterion("dev_type =", value, "devType");
            return (Criteria) this;
        }

        public Criteria andDevTypeNotEqualTo(Integer value) {
            addCriterion("dev_type <>", value, "devType");
            return (Criteria) this;
        }

        public Criteria andDevTypeGreaterThan(Integer value) {
            addCriterion("dev_type >", value, "devType");
            return (Criteria) this;
        }

        public Criteria andDevTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("dev_type >=", value, "devType");
            return (Criteria) this;
        }

        public Criteria andDevTypeLessThan(Integer value) {
            addCriterion("dev_type <", value, "devType");
            return (Criteria) this;
        }

        public Criteria andDevTypeLessThanOrEqualTo(Integer value) {
            addCriterion("dev_type <=", value, "devType");
            return (Criteria) this;
        }

        public Criteria andDevTypeIn(List<Integer> values) {
            addCriterion("dev_type in", values, "devType");
            return (Criteria) this;
        }

        public Criteria andDevTypeNotIn(List<Integer> values) {
            addCriterion("dev_type not in", values, "devType");
            return (Criteria) this;
        }

        public Criteria andDevTypeBetween(Integer value1, Integer value2) {
            addCriterion("dev_type between", value1, value2, "devType");
            return (Criteria) this;
        }

        public Criteria andDevTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("dev_type not between", value1, value2, "devType");
            return (Criteria) this;
        }

        public Criteria andAnalyzingConditionsIsNull() {
            addCriterion("analyzing_conditions is null");
            return (Criteria) this;
        }

        public Criteria andAnalyzingConditionsIsNotNull() {
            addCriterion("analyzing_conditions is not null");
            return (Criteria) this;
        }

        public Criteria andAnalyzingConditionsEqualTo(Integer value) {
            addCriterion("analyzing_conditions =", value, "analyzingConditions");
            return (Criteria) this;
        }

        public Criteria andAnalyzingConditionsNotEqualTo(Integer value) {
            addCriterion("analyzing_conditions <>", value, "analyzingConditions");
            return (Criteria) this;
        }

        public Criteria andAnalyzingConditionsGreaterThan(Integer value) {
            addCriterion("analyzing_conditions >", value, "analyzingConditions");
            return (Criteria) this;
        }

        public Criteria andAnalyzingConditionsGreaterThanOrEqualTo(Integer value) {
            addCriterion("analyzing_conditions >=", value, "analyzingConditions");
            return (Criteria) this;
        }

        public Criteria andAnalyzingConditionsLessThan(Integer value) {
            addCriterion("analyzing_conditions <", value, "analyzingConditions");
            return (Criteria) this;
        }

        public Criteria andAnalyzingConditionsLessThanOrEqualTo(Integer value) {
            addCriterion("analyzing_conditions <=", value, "analyzingConditions");
            return (Criteria) this;
        }

        public Criteria andAnalyzingConditionsIn(List<Integer> values) {
            addCriterion("analyzing_conditions in", values, "analyzingConditions");
            return (Criteria) this;
        }

        public Criteria andAnalyzingConditionsNotIn(List<Integer> values) {
            addCriterion("analyzing_conditions not in", values, "analyzingConditions");
            return (Criteria) this;
        }

        public Criteria andAnalyzingConditionsBetween(Integer value1, Integer value2) {
            addCriterion("analyzing_conditions between", value1, value2, "analyzingConditions");
            return (Criteria) this;
        }

        public Criteria andAnalyzingConditionsNotBetween(Integer value1, Integer value2) {
            addCriterion("analyzing_conditions not between", value1, value2, "analyzingConditions");
            return (Criteria) this;
        }

        public Criteria andDevWorkloadIsNull() {
            addCriterion("dev_workload is null");
            return (Criteria) this;
        }

        public Criteria andDevWorkloadIsNotNull() {
            addCriterion("dev_workload is not null");
            return (Criteria) this;
        }

        public Criteria andDevWorkloadEqualTo(Integer value) {
            addCriterion("dev_workload =", value, "devWorkload");
            return (Criteria) this;
        }

        public Criteria andDevWorkloadNotEqualTo(Integer value) {
            addCriterion("dev_workload <>", value, "devWorkload");
            return (Criteria) this;
        }

        public Criteria andDevWorkloadGreaterThan(Integer value) {
            addCriterion("dev_workload >", value, "devWorkload");
            return (Criteria) this;
        }

        public Criteria andDevWorkloadGreaterThanOrEqualTo(Integer value) {
            addCriterion("dev_workload >=", value, "devWorkload");
            return (Criteria) this;
        }

        public Criteria andDevWorkloadLessThan(Integer value) {
            addCriterion("dev_workload <", value, "devWorkload");
            return (Criteria) this;
        }

        public Criteria andDevWorkloadLessThanOrEqualTo(Integer value) {
            addCriterion("dev_workload <=", value, "devWorkload");
            return (Criteria) this;
        }

        public Criteria andDevWorkloadIn(List<Integer> values) {
            addCriterion("dev_workload in", values, "devWorkload");
            return (Criteria) this;
        }

        public Criteria andDevWorkloadNotIn(List<Integer> values) {
            addCriterion("dev_workload not in", values, "devWorkload");
            return (Criteria) this;
        }

        public Criteria andDevWorkloadBetween(Integer value1, Integer value2) {
            addCriterion("dev_workload between", value1, value2, "devWorkload");
            return (Criteria) this;
        }

        public Criteria andDevWorkloadNotBetween(Integer value1, Integer value2) {
            addCriterion("dev_workload not between", value1, value2, "devWorkload");
            return (Criteria) this;
        }

        public Criteria andOverallCostIsNull() {
            addCriterion("overall_cost is null");
            return (Criteria) this;
        }

        public Criteria andOverallCostIsNotNull() {
            addCriterion("overall_cost is not null");
            return (Criteria) this;
        }

        public Criteria andOverallCostEqualTo(BigDecimal value) {
            addCriterion("overall_cost =", value, "overallCost");
            return (Criteria) this;
        }

        public Criteria andOverallCostNotEqualTo(BigDecimal value) {
            addCriterion("overall_cost <>", value, "overallCost");
            return (Criteria) this;
        }

        public Criteria andOverallCostGreaterThan(BigDecimal value) {
            addCriterion("overall_cost >", value, "overallCost");
            return (Criteria) this;
        }

        public Criteria andOverallCostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("overall_cost >=", value, "overallCost");
            return (Criteria) this;
        }

        public Criteria andOverallCostLessThan(BigDecimal value) {
            addCriterion("overall_cost <", value, "overallCost");
            return (Criteria) this;
        }

        public Criteria andOverallCostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("overall_cost <=", value, "overallCost");
            return (Criteria) this;
        }

        public Criteria andOverallCostIn(List<BigDecimal> values) {
            addCriterion("overall_cost in", values, "overallCost");
            return (Criteria) this;
        }

        public Criteria andOverallCostNotIn(List<BigDecimal> values) {
            addCriterion("overall_cost not in", values, "overallCost");
            return (Criteria) this;
        }

        public Criteria andOverallCostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("overall_cost between", value1, value2, "overallCost");
            return (Criteria) this;
        }

        public Criteria andOverallCostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("overall_cost not between", value1, value2, "overallCost");
            return (Criteria) this;
        }

        public Criteria andBidDevWorkloadIsNull() {
            addCriterion("bid_dev_workload is null");
            return (Criteria) this;
        }

        public Criteria andBidDevWorkloadIsNotNull() {
            addCriterion("bid_dev_workload is not null");
            return (Criteria) this;
        }

        public Criteria andBidDevWorkloadEqualTo(Integer value) {
            addCriterion("bid_dev_workload =", value, "bidDevWorkload");
            return (Criteria) this;
        }

        public Criteria andBidDevWorkloadNotEqualTo(Integer value) {
            addCriterion("bid_dev_workload <>", value, "bidDevWorkload");
            return (Criteria) this;
        }

        public Criteria andBidDevWorkloadGreaterThan(Integer value) {
            addCriterion("bid_dev_workload >", value, "bidDevWorkload");
            return (Criteria) this;
        }

        public Criteria andBidDevWorkloadGreaterThanOrEqualTo(Integer value) {
            addCriterion("bid_dev_workload >=", value, "bidDevWorkload");
            return (Criteria) this;
        }

        public Criteria andBidDevWorkloadLessThan(Integer value) {
            addCriterion("bid_dev_workload <", value, "bidDevWorkload");
            return (Criteria) this;
        }

        public Criteria andBidDevWorkloadLessThanOrEqualTo(Integer value) {
            addCriterion("bid_dev_workload <=", value, "bidDevWorkload");
            return (Criteria) this;
        }

        public Criteria andBidDevWorkloadIn(List<Integer> values) {
            addCriterion("bid_dev_workload in", values, "bidDevWorkload");
            return (Criteria) this;
        }

        public Criteria andBidDevWorkloadNotIn(List<Integer> values) {
            addCriterion("bid_dev_workload not in", values, "bidDevWorkload");
            return (Criteria) this;
        }

        public Criteria andBidDevWorkloadBetween(Integer value1, Integer value2) {
            addCriterion("bid_dev_workload between", value1, value2, "bidDevWorkload");
            return (Criteria) this;
        }

        public Criteria andBidDevWorkloadNotBetween(Integer value1, Integer value2) {
            addCriterion("bid_dev_workload not between", value1, value2, "bidDevWorkload");
            return (Criteria) this;
        }

        public Criteria andBidOverallCostIsNull() {
            addCriterion("bid_overall_cost is null");
            return (Criteria) this;
        }

        public Criteria andBidOverallCostIsNotNull() {
            addCriterion("bid_overall_cost is not null");
            return (Criteria) this;
        }

        public Criteria andBidOverallCostEqualTo(BigDecimal value) {
            addCriterion("bid_overall_cost =", value, "bidOverallCost");
            return (Criteria) this;
        }

        public Criteria andBidOverallCostNotEqualTo(BigDecimal value) {
            addCriterion("bid_overall_cost <>", value, "bidOverallCost");
            return (Criteria) this;
        }

        public Criteria andBidOverallCostGreaterThan(BigDecimal value) {
            addCriterion("bid_overall_cost >", value, "bidOverallCost");
            return (Criteria) this;
        }

        public Criteria andBidOverallCostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("bid_overall_cost >=", value, "bidOverallCost");
            return (Criteria) this;
        }

        public Criteria andBidOverallCostLessThan(BigDecimal value) {
            addCriterion("bid_overall_cost <", value, "bidOverallCost");
            return (Criteria) this;
        }

        public Criteria andBidOverallCostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("bid_overall_cost <=", value, "bidOverallCost");
            return (Criteria) this;
        }

        public Criteria andBidOverallCostIn(List<BigDecimal> values) {
            addCriterion("bid_overall_cost in", values, "bidOverallCost");
            return (Criteria) this;
        }

        public Criteria andBidOverallCostNotIn(List<BigDecimal> values) {
            addCriterion("bid_overall_cost not in", values, "bidOverallCost");
            return (Criteria) this;
        }

        public Criteria andBidOverallCostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bid_overall_cost between", value1, value2, "bidOverallCost");
            return (Criteria) this;
        }

        public Criteria andBidOverallCostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bid_overall_cost not between", value1, value2, "bidOverallCost");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusIsNull() {
            addCriterion("approval_status is null");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusIsNotNull() {
            addCriterion("approval_status is not null");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusEqualTo(Integer value) {
            addCriterion("approval_status =", value, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusNotEqualTo(Integer value) {
            addCriterion("approval_status <>", value, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusGreaterThan(Integer value) {
            addCriterion("approval_status >", value, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("approval_status >=", value, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusLessThan(Integer value) {
            addCriterion("approval_status <", value, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusLessThanOrEqualTo(Integer value) {
            addCriterion("approval_status <=", value, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusIn(List<Integer> values) {
            addCriterion("approval_status in", values, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusNotIn(List<Integer> values) {
            addCriterion("approval_status not in", values, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusBetween(Integer value1, Integer value2) {
            addCriterion("approval_status between", value1, value2, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andApprovalStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("approval_status not between", value1, value2, "approvalStatus");
            return (Criteria) this;
        }

        public Criteria andBpmCodeIsNull() {
            addCriterion("bpm_code is null");
            return (Criteria) this;
        }

        public Criteria andBpmCodeIsNotNull() {
            addCriterion("bpm_code is not null");
            return (Criteria) this;
        }

        public Criteria andBpmCodeEqualTo(String value) {
            addCriterion("bpm_code =", value, "bpmCode");
            return (Criteria) this;
        }

        public Criteria andBpmCodeNotEqualTo(String value) {
            addCriterion("bpm_code <>", value, "bpmCode");
            return (Criteria) this;
        }

        public Criteria andBpmCodeGreaterThan(String value) {
            addCriterion("bpm_code >", value, "bpmCode");
            return (Criteria) this;
        }

        public Criteria andBpmCodeGreaterThanOrEqualTo(String value) {
            addCriterion("bpm_code >=", value, "bpmCode");
            return (Criteria) this;
        }

        public Criteria andBpmCodeLessThan(String value) {
            addCriterion("bpm_code <", value, "bpmCode");
            return (Criteria) this;
        }

        public Criteria andBpmCodeLessThanOrEqualTo(String value) {
            addCriterion("bpm_code <=", value, "bpmCode");
            return (Criteria) this;
        }

        public Criteria andBpmCodeLike(String value) {
            addCriterion("bpm_code like", value, "bpmCode");
            return (Criteria) this;
        }

        public Criteria andBpmCodeNotLike(String value) {
            addCriterion("bpm_code not like", value, "bpmCode");
            return (Criteria) this;
        }

        public Criteria andBpmCodeIn(List<String> values) {
            addCriterion("bpm_code in", values, "bpmCode");
            return (Criteria) this;
        }

        public Criteria andBpmCodeNotIn(List<String> values) {
            addCriterion("bpm_code not in", values, "bpmCode");
            return (Criteria) this;
        }

        public Criteria andBpmCodeBetween(String value1, String value2) {
            addCriterion("bpm_code between", value1, value2, "bpmCode");
            return (Criteria) this;
        }

        public Criteria andBpmCodeNotBetween(String value1, String value2) {
            addCriterion("bpm_code not between", value1, value2, "bpmCode");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andApprovalViewIsNull() {
            addCriterion("approval_view is null");
            return (Criteria) this;
        }

        public Criteria andApprovalViewIsNotNull() {
            addCriterion("approval_view is not null");
            return (Criteria) this;
        }

        public Criteria andApprovalViewEqualTo(String value) {
            addCriterion("approval_view =", value, "approvalView");
            return (Criteria) this;
        }

        public Criteria andApprovalViewNotEqualTo(String value) {
            addCriterion("approval_view <>", value, "approvalView");
            return (Criteria) this;
        }

        public Criteria andApprovalViewGreaterThan(String value) {
            addCriterion("approval_view >", value, "approvalView");
            return (Criteria) this;
        }

        public Criteria andApprovalViewGreaterThanOrEqualTo(String value) {
            addCriterion("approval_view >=", value, "approvalView");
            return (Criteria) this;
        }

        public Criteria andApprovalViewLessThan(String value) {
            addCriterion("approval_view <", value, "approvalView");
            return (Criteria) this;
        }

        public Criteria andApprovalViewLessThanOrEqualTo(String value) {
            addCriterion("approval_view <=", value, "approvalView");
            return (Criteria) this;
        }

        public Criteria andApprovalViewLike(String value) {
            addCriterion("approval_view like", value, "approvalView");
            return (Criteria) this;
        }

        public Criteria andApprovalViewNotLike(String value) {
            addCriterion("approval_view not like", value, "approvalView");
            return (Criteria) this;
        }

        public Criteria andApprovalViewIn(List<String> values) {
            addCriterion("approval_view in", values, "approvalView");
            return (Criteria) this;
        }

        public Criteria andApprovalViewNotIn(List<String> values) {
            addCriterion("approval_view not in", values, "approvalView");
            return (Criteria) this;
        }

        public Criteria andApprovalViewBetween(String value1, String value2) {
            addCriterion("approval_view between", value1, value2, "approvalView");
            return (Criteria) this;
        }

        public Criteria andApprovalViewNotBetween(String value1, String value2) {
            addCriterion("approval_view not between", value1, value2, "approvalView");
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