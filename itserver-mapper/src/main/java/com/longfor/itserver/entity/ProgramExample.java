package com.longfor.itserver.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProgramExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProgramExample() {
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