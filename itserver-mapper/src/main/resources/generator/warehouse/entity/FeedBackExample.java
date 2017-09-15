package generator.warehouse.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FeedBackExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FeedBackExample() {
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

        public Criteria andProblemTitleIsNull() {
            addCriterion("problem_title is null");
            return (Criteria) this;
        }

        public Criteria andProblemTitleIsNotNull() {
            addCriterion("problem_title is not null");
            return (Criteria) this;
        }

        public Criteria andProblemTitleEqualTo(String value) {
            addCriterion("problem_title =", value, "problemTitle");
            return (Criteria) this;
        }

        public Criteria andProblemTitleNotEqualTo(String value) {
            addCriterion("problem_title <>", value, "problemTitle");
            return (Criteria) this;
        }

        public Criteria andProblemTitleGreaterThan(String value) {
            addCriterion("problem_title >", value, "problemTitle");
            return (Criteria) this;
        }

        public Criteria andProblemTitleGreaterThanOrEqualTo(String value) {
            addCriterion("problem_title >=", value, "problemTitle");
            return (Criteria) this;
        }

        public Criteria andProblemTitleLessThan(String value) {
            addCriterion("problem_title <", value, "problemTitle");
            return (Criteria) this;
        }

        public Criteria andProblemTitleLessThanOrEqualTo(String value) {
            addCriterion("problem_title <=", value, "problemTitle");
            return (Criteria) this;
        }

        public Criteria andProblemTitleLike(String value) {
            addCriterion("problem_title like", value, "problemTitle");
            return (Criteria) this;
        }

        public Criteria andProblemTitleNotLike(String value) {
            addCriterion("problem_title not like", value, "problemTitle");
            return (Criteria) this;
        }

        public Criteria andProblemTitleIn(List<String> values) {
            addCriterion("problem_title in", values, "problemTitle");
            return (Criteria) this;
        }

        public Criteria andProblemTitleNotIn(List<String> values) {
            addCriterion("problem_title not in", values, "problemTitle");
            return (Criteria) this;
        }

        public Criteria andProblemTitleBetween(String value1, String value2) {
            addCriterion("problem_title between", value1, value2, "problemTitle");
            return (Criteria) this;
        }

        public Criteria andProblemTitleNotBetween(String value1, String value2) {
            addCriterion("problem_title not between", value1, value2, "problemTitle");
            return (Criteria) this;
        }

        public Criteria andProblemDescpIsNull() {
            addCriterion("problem_descp is null");
            return (Criteria) this;
        }

        public Criteria andProblemDescpIsNotNull() {
            addCriterion("problem_descp is not null");
            return (Criteria) this;
        }

        public Criteria andProblemDescpEqualTo(String value) {
            addCriterion("problem_descp =", value, "problemDescp");
            return (Criteria) this;
        }

        public Criteria andProblemDescpNotEqualTo(String value) {
            addCriterion("problem_descp <>", value, "problemDescp");
            return (Criteria) this;
        }

        public Criteria andProblemDescpGreaterThan(String value) {
            addCriterion("problem_descp >", value, "problemDescp");
            return (Criteria) this;
        }

        public Criteria andProblemDescpGreaterThanOrEqualTo(String value) {
            addCriterion("problem_descp >=", value, "problemDescp");
            return (Criteria) this;
        }

        public Criteria andProblemDescpLessThan(String value) {
            addCriterion("problem_descp <", value, "problemDescp");
            return (Criteria) this;
        }

        public Criteria andProblemDescpLessThanOrEqualTo(String value) {
            addCriterion("problem_descp <=", value, "problemDescp");
            return (Criteria) this;
        }

        public Criteria andProblemDescpLike(String value) {
            addCriterion("problem_descp like", value, "problemDescp");
            return (Criteria) this;
        }

        public Criteria andProblemDescpNotLike(String value) {
            addCriterion("problem_descp not like", value, "problemDescp");
            return (Criteria) this;
        }

        public Criteria andProblemDescpIn(List<String> values) {
            addCriterion("problem_descp in", values, "problemDescp");
            return (Criteria) this;
        }

        public Criteria andProblemDescpNotIn(List<String> values) {
            addCriterion("problem_descp not in", values, "problemDescp");
            return (Criteria) this;
        }

        public Criteria andProblemDescpBetween(String value1, String value2) {
            addCriterion("problem_descp between", value1, value2, "problemDescp");
            return (Criteria) this;
        }

        public Criteria andProblemDescpNotBetween(String value1, String value2) {
            addCriterion("problem_descp not between", value1, value2, "problemDescp");
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

        public Criteria andSysEnvironmentIsNull() {
            addCriterion("sys_environment is null");
            return (Criteria) this;
        }

        public Criteria andSysEnvironmentIsNotNull() {
            addCriterion("sys_environment is not null");
            return (Criteria) this;
        }

        public Criteria andSysEnvironmentEqualTo(String value) {
            addCriterion("sys_environment =", value, "sysEnvironment");
            return (Criteria) this;
        }

        public Criteria andSysEnvironmentNotEqualTo(String value) {
            addCriterion("sys_environment <>", value, "sysEnvironment");
            return (Criteria) this;
        }

        public Criteria andSysEnvironmentGreaterThan(String value) {
            addCriterion("sys_environment >", value, "sysEnvironment");
            return (Criteria) this;
        }

        public Criteria andSysEnvironmentGreaterThanOrEqualTo(String value) {
            addCriterion("sys_environment >=", value, "sysEnvironment");
            return (Criteria) this;
        }

        public Criteria andSysEnvironmentLessThan(String value) {
            addCriterion("sys_environment <", value, "sysEnvironment");
            return (Criteria) this;
        }

        public Criteria andSysEnvironmentLessThanOrEqualTo(String value) {
            addCriterion("sys_environment <=", value, "sysEnvironment");
            return (Criteria) this;
        }

        public Criteria andSysEnvironmentLike(String value) {
            addCriterion("sys_environment like", value, "sysEnvironment");
            return (Criteria) this;
        }

        public Criteria andSysEnvironmentNotLike(String value) {
            addCriterion("sys_environment not like", value, "sysEnvironment");
            return (Criteria) this;
        }

        public Criteria andSysEnvironmentIn(List<String> values) {
            addCriterion("sys_environment in", values, "sysEnvironment");
            return (Criteria) this;
        }

        public Criteria andSysEnvironmentNotIn(List<String> values) {
            addCriterion("sys_environment not in", values, "sysEnvironment");
            return (Criteria) this;
        }

        public Criteria andSysEnvironmentBetween(String value1, String value2) {
            addCriterion("sys_environment between", value1, value2, "sysEnvironment");
            return (Criteria) this;
        }

        public Criteria andSysEnvironmentNotBetween(String value1, String value2) {
            addCriterion("sys_environment not between", value1, value2, "sysEnvironment");
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

        public Criteria andModifiedEmployeeCodeIsNull() {
            addCriterion("modified_employee_code is null");
            return (Criteria) this;
        }

        public Criteria andModifiedEmployeeCodeIsNotNull() {
            addCriterion("modified_employee_code is not null");
            return (Criteria) this;
        }

        public Criteria andModifiedEmployeeCodeEqualTo(Long value) {
            addCriterion("modified_employee_code =", value, "modifiedEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andModifiedEmployeeCodeNotEqualTo(Long value) {
            addCriterion("modified_employee_code <>", value, "modifiedEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andModifiedEmployeeCodeGreaterThan(Long value) {
            addCriterion("modified_employee_code >", value, "modifiedEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andModifiedEmployeeCodeGreaterThanOrEqualTo(Long value) {
            addCriterion("modified_employee_code >=", value, "modifiedEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andModifiedEmployeeCodeLessThan(Long value) {
            addCriterion("modified_employee_code <", value, "modifiedEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andModifiedEmployeeCodeLessThanOrEqualTo(Long value) {
            addCriterion("modified_employee_code <=", value, "modifiedEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andModifiedEmployeeCodeIn(List<Long> values) {
            addCriterion("modified_employee_code in", values, "modifiedEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andModifiedEmployeeCodeNotIn(List<Long> values) {
            addCriterion("modified_employee_code not in", values, "modifiedEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andModifiedEmployeeCodeBetween(Long value1, Long value2) {
            addCriterion("modified_employee_code between", value1, value2, "modifiedEmployeeCode");
            return (Criteria) this;
        }

        public Criteria andModifiedEmployeeCodeNotBetween(Long value1, Long value2) {
            addCriterion("modified_employee_code not between", value1, value2, "modifiedEmployeeCode");
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

        public Criteria andModifiedFullDeptPathIsNull() {
            addCriterion("modified_full_dept_path is null");
            return (Criteria) this;
        }

        public Criteria andModifiedFullDeptPathIsNotNull() {
            addCriterion("modified_full_dept_path is not null");
            return (Criteria) this;
        }

        public Criteria andModifiedFullDeptPathEqualTo(String value) {
            addCriterion("modified_full_dept_path =", value, "modifiedFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andModifiedFullDeptPathNotEqualTo(String value) {
            addCriterion("modified_full_dept_path <>", value, "modifiedFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andModifiedFullDeptPathGreaterThan(String value) {
            addCriterion("modified_full_dept_path >", value, "modifiedFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andModifiedFullDeptPathGreaterThanOrEqualTo(String value) {
            addCriterion("modified_full_dept_path >=", value, "modifiedFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andModifiedFullDeptPathLessThan(String value) {
            addCriterion("modified_full_dept_path <", value, "modifiedFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andModifiedFullDeptPathLessThanOrEqualTo(String value) {
            addCriterion("modified_full_dept_path <=", value, "modifiedFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andModifiedFullDeptPathLike(String value) {
            addCriterion("modified_full_dept_path like", value, "modifiedFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andModifiedFullDeptPathNotLike(String value) {
            addCriterion("modified_full_dept_path not like", value, "modifiedFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andModifiedFullDeptPathIn(List<String> values) {
            addCriterion("modified_full_dept_path in", values, "modifiedFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andModifiedFullDeptPathNotIn(List<String> values) {
            addCriterion("modified_full_dept_path not in", values, "modifiedFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andModifiedFullDeptPathBetween(String value1, String value2) {
            addCriterion("modified_full_dept_path between", value1, value2, "modifiedFullDeptPath");
            return (Criteria) this;
        }

        public Criteria andModifiedFullDeptPathNotBetween(String value1, String value2) {
            addCriterion("modified_full_dept_path not between", value1, value2, "modifiedFullDeptPath");
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

        public Criteria andFeedbackPhoneIsNull() {
            addCriterion("feedback_phone is null");
            return (Criteria) this;
        }

        public Criteria andFeedbackPhoneIsNotNull() {
            addCriterion("feedback_phone is not null");
            return (Criteria) this;
        }

        public Criteria andFeedbackPhoneEqualTo(String value) {
            addCriterion("feedback_phone =", value, "feedbackPhone");
            return (Criteria) this;
        }

        public Criteria andFeedbackPhoneNotEqualTo(String value) {
            addCriterion("feedback_phone <>", value, "feedbackPhone");
            return (Criteria) this;
        }

        public Criteria andFeedbackPhoneGreaterThan(String value) {
            addCriterion("feedback_phone >", value, "feedbackPhone");
            return (Criteria) this;
        }

        public Criteria andFeedbackPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("feedback_phone >=", value, "feedbackPhone");
            return (Criteria) this;
        }

        public Criteria andFeedbackPhoneLessThan(String value) {
            addCriterion("feedback_phone <", value, "feedbackPhone");
            return (Criteria) this;
        }

        public Criteria andFeedbackPhoneLessThanOrEqualTo(String value) {
            addCriterion("feedback_phone <=", value, "feedbackPhone");
            return (Criteria) this;
        }

        public Criteria andFeedbackPhoneLike(String value) {
            addCriterion("feedback_phone like", value, "feedbackPhone");
            return (Criteria) this;
        }

        public Criteria andFeedbackPhoneNotLike(String value) {
            addCriterion("feedback_phone not like", value, "feedbackPhone");
            return (Criteria) this;
        }

        public Criteria andFeedbackPhoneIn(List<String> values) {
            addCriterion("feedback_phone in", values, "feedbackPhone");
            return (Criteria) this;
        }

        public Criteria andFeedbackPhoneNotIn(List<String> values) {
            addCriterion("feedback_phone not in", values, "feedbackPhone");
            return (Criteria) this;
        }

        public Criteria andFeedbackPhoneBetween(String value1, String value2) {
            addCriterion("feedback_phone between", value1, value2, "feedbackPhone");
            return (Criteria) this;
        }

        public Criteria andFeedbackPhoneNotBetween(String value1, String value2) {
            addCriterion("feedback_phone not between", value1, value2, "feedbackPhone");
            return (Criteria) this;
        }

        public Criteria andFeedbackNameIsNull() {
            addCriterion("feedback_name is null");
            return (Criteria) this;
        }

        public Criteria andFeedbackNameIsNotNull() {
            addCriterion("feedback_name is not null");
            return (Criteria) this;
        }

        public Criteria andFeedbackNameEqualTo(String value) {
            addCriterion("feedback_name =", value, "feedbackName");
            return (Criteria) this;
        }

        public Criteria andFeedbackNameNotEqualTo(String value) {
            addCriterion("feedback_name <>", value, "feedbackName");
            return (Criteria) this;
        }

        public Criteria andFeedbackNameGreaterThan(String value) {
            addCriterion("feedback_name >", value, "feedbackName");
            return (Criteria) this;
        }

        public Criteria andFeedbackNameGreaterThanOrEqualTo(String value) {
            addCriterion("feedback_name >=", value, "feedbackName");
            return (Criteria) this;
        }

        public Criteria andFeedbackNameLessThan(String value) {
            addCriterion("feedback_name <", value, "feedbackName");
            return (Criteria) this;
        }

        public Criteria andFeedbackNameLessThanOrEqualTo(String value) {
            addCriterion("feedback_name <=", value, "feedbackName");
            return (Criteria) this;
        }

        public Criteria andFeedbackNameLike(String value) {
            addCriterion("feedback_name like", value, "feedbackName");
            return (Criteria) this;
        }

        public Criteria andFeedbackNameNotLike(String value) {
            addCriterion("feedback_name not like", value, "feedbackName");
            return (Criteria) this;
        }

        public Criteria andFeedbackNameIn(List<String> values) {
            addCriterion("feedback_name in", values, "feedbackName");
            return (Criteria) this;
        }

        public Criteria andFeedbackNameNotIn(List<String> values) {
            addCriterion("feedback_name not in", values, "feedbackName");
            return (Criteria) this;
        }

        public Criteria andFeedbackNameBetween(String value1, String value2) {
            addCriterion("feedback_name between", value1, value2, "feedbackName");
            return (Criteria) this;
        }

        public Criteria andFeedbackNameNotBetween(String value1, String value2) {
            addCriterion("feedback_name not between", value1, value2, "feedbackName");
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