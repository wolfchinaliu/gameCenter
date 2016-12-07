package com.shiliu.game.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelUserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExcelUserExample() {
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

        public Criteria andEuIdIsNull() {
            addCriterion("eu_id is null");
            return (Criteria) this;
        }

        public Criteria andEuIdIsNotNull() {
            addCriterion("eu_id is not null");
            return (Criteria) this;
        }

        public Criteria andEuIdEqualTo(String value) {
            addCriterion("eu_id =", value, "euId");
            return (Criteria) this;
        }

        public Criteria andEuIdNotEqualTo(String value) {
            addCriterion("eu_id <>", value, "euId");
            return (Criteria) this;
        }

        public Criteria andEuIdGreaterThan(String value) {
            addCriterion("eu_id >", value, "euId");
            return (Criteria) this;
        }

        public Criteria andEuIdGreaterThanOrEqualTo(String value) {
            addCriterion("eu_id >=", value, "euId");
            return (Criteria) this;
        }

        public Criteria andEuIdLessThan(String value) {
            addCriterion("eu_id <", value, "euId");
            return (Criteria) this;
        }

        public Criteria andEuIdLessThanOrEqualTo(String value) {
            addCriterion("eu_id <=", value, "euId");
            return (Criteria) this;
        }

        public Criteria andEuIdLike(String value) {
            addCriterion("eu_id like", value, "euId");
            return (Criteria) this;
        }

        public Criteria andEuIdNotLike(String value) {
            addCriterion("eu_id not like", value, "euId");
            return (Criteria) this;
        }

        public Criteria andEuIdIn(List<String> values) {
            addCriterion("eu_id in", values, "euId");
            return (Criteria) this;
        }

        public Criteria andEuIdNotIn(List<String> values) {
            addCriterion("eu_id not in", values, "euId");
            return (Criteria) this;
        }

        public Criteria andEuIdBetween(String value1, String value2) {
            addCriterion("eu_id between", value1, value2, "euId");
            return (Criteria) this;
        }

        public Criteria andEuIdNotBetween(String value1, String value2) {
            addCriterion("eu_id not between", value1, value2, "euId");
            return (Criteria) this;
        }

        public Criteria andLab1IsNull() {
            addCriterion("lab1 is null");
            return (Criteria) this;
        }

        public Criteria andLab1IsNotNull() {
            addCriterion("lab1 is not null");
            return (Criteria) this;
        }

        public Criteria andLab1EqualTo(String value) {
            addCriterion("lab1 =", value, "lab1");
            return (Criteria) this;
        }

        public Criteria andLab1NotEqualTo(String value) {
            addCriterion("lab1 <>", value, "lab1");
            return (Criteria) this;
        }

        public Criteria andLab1GreaterThan(String value) {
            addCriterion("lab1 >", value, "lab1");
            return (Criteria) this;
        }

        public Criteria andLab1GreaterThanOrEqualTo(String value) {
            addCriterion("lab1 >=", value, "lab1");
            return (Criteria) this;
        }

        public Criteria andLab1LessThan(String value) {
            addCriterion("lab1 <", value, "lab1");
            return (Criteria) this;
        }

        public Criteria andLab1LessThanOrEqualTo(String value) {
            addCriterion("lab1 <=", value, "lab1");
            return (Criteria) this;
        }

        public Criteria andLab1Like(String value) {
            addCriterion("lab1 like", value, "lab1");
            return (Criteria) this;
        }

        public Criteria andLab1NotLike(String value) {
            addCriterion("lab1 not like", value, "lab1");
            return (Criteria) this;
        }

        public Criteria andLab1In(List<String> values) {
            addCriterion("lab1 in", values, "lab1");
            return (Criteria) this;
        }

        public Criteria andLab1NotIn(List<String> values) {
            addCriterion("lab1 not in", values, "lab1");
            return (Criteria) this;
        }

        public Criteria andLab1Between(String value1, String value2) {
            addCriterion("lab1 between", value1, value2, "lab1");
            return (Criteria) this;
        }

        public Criteria andLab1NotBetween(String value1, String value2) {
            addCriterion("lab1 not between", value1, value2, "lab1");
            return (Criteria) this;
        }

        public Criteria andLab2IsNull() {
            addCriterion("lab2 is null");
            return (Criteria) this;
        }

        public Criteria andLab2IsNotNull() {
            addCriterion("lab2 is not null");
            return (Criteria) this;
        }

        public Criteria andLab2EqualTo(String value) {
            addCriterion("lab2 =", value, "lab2");
            return (Criteria) this;
        }

        public Criteria andLab2NotEqualTo(String value) {
            addCriterion("lab2 <>", value, "lab2");
            return (Criteria) this;
        }

        public Criteria andLab2GreaterThan(String value) {
            addCriterion("lab2 >", value, "lab2");
            return (Criteria) this;
        }

        public Criteria andLab2GreaterThanOrEqualTo(String value) {
            addCriterion("lab2 >=", value, "lab2");
            return (Criteria) this;
        }

        public Criteria andLab2LessThan(String value) {
            addCriterion("lab2 <", value, "lab2");
            return (Criteria) this;
        }

        public Criteria andLab2LessThanOrEqualTo(String value) {
            addCriterion("lab2 <=", value, "lab2");
            return (Criteria) this;
        }

        public Criteria andLab2Like(String value) {
            addCriterion("lab2 like", value, "lab2");
            return (Criteria) this;
        }

        public Criteria andLab2NotLike(String value) {
            addCriterion("lab2 not like", value, "lab2");
            return (Criteria) this;
        }

        public Criteria andLab2In(List<String> values) {
            addCriterion("lab2 in", values, "lab2");
            return (Criteria) this;
        }

        public Criteria andLab2NotIn(List<String> values) {
            addCriterion("lab2 not in", values, "lab2");
            return (Criteria) this;
        }

        public Criteria andLab2Between(String value1, String value2) {
            addCriterion("lab2 between", value1, value2, "lab2");
            return (Criteria) this;
        }

        public Criteria andLab2NotBetween(String value1, String value2) {
            addCriterion("lab2 not between", value1, value2, "lab2");
            return (Criteria) this;
        }

        public Criteria andLab3IsNull() {
            addCriterion("lab3 is null");
            return (Criteria) this;
        }

        public Criteria andLab3IsNotNull() {
            addCriterion("lab3 is not null");
            return (Criteria) this;
        }

        public Criteria andLab3EqualTo(String value) {
            addCriterion("lab3 =", value, "lab3");
            return (Criteria) this;
        }

        public Criteria andLab3NotEqualTo(String value) {
            addCriterion("lab3 <>", value, "lab3");
            return (Criteria) this;
        }

        public Criteria andLab3GreaterThan(String value) {
            addCriterion("lab3 >", value, "lab3");
            return (Criteria) this;
        }

        public Criteria andLab3GreaterThanOrEqualTo(String value) {
            addCriterion("lab3 >=", value, "lab3");
            return (Criteria) this;
        }

        public Criteria andLab3LessThan(String value) {
            addCriterion("lab3 <", value, "lab3");
            return (Criteria) this;
        }

        public Criteria andLab3LessThanOrEqualTo(String value) {
            addCriterion("lab3 <=", value, "lab3");
            return (Criteria) this;
        }

        public Criteria andLab3Like(String value) {
            addCriterion("lab3 like", value, "lab3");
            return (Criteria) this;
        }

        public Criteria andLab3NotLike(String value) {
            addCriterion("lab3 not like", value, "lab3");
            return (Criteria) this;
        }

        public Criteria andLab3In(List<String> values) {
            addCriterion("lab3 in", values, "lab3");
            return (Criteria) this;
        }

        public Criteria andLab3NotIn(List<String> values) {
            addCriterion("lab3 not in", values, "lab3");
            return (Criteria) this;
        }

        public Criteria andLab3Between(String value1, String value2) {
            addCriterion("lab3 between", value1, value2, "lab3");
            return (Criteria) this;
        }

        public Criteria andLab3NotBetween(String value1, String value2) {
            addCriterion("lab3 not between", value1, value2, "lab3");
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

        public Criteria andStateEqualTo(String value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("state like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("state not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeIsNull() {
            addCriterion("recharge_time is null");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeIsNotNull() {
            addCriterion("recharge_time is not null");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeEqualTo(Date value) {
            addCriterion("recharge_time =", value, "rechargeTime");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeNotEqualTo(Date value) {
            addCriterion("recharge_time <>", value, "rechargeTime");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeGreaterThan(Date value) {
            addCriterion("recharge_time >", value, "rechargeTime");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("recharge_time >=", value, "rechargeTime");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeLessThan(Date value) {
            addCriterion("recharge_time <", value, "rechargeTime");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeLessThanOrEqualTo(Date value) {
            addCriterion("recharge_time <=", value, "rechargeTime");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeIn(List<Date> values) {
            addCriterion("recharge_time in", values, "rechargeTime");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeNotIn(List<Date> values) {
            addCriterion("recharge_time not in", values, "rechargeTime");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeBetween(Date value1, Date value2) {
            addCriterion("recharge_time between", value1, value2, "rechargeTime");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeNotBetween(Date value1, Date value2) {
            addCriterion("recharge_time not between", value1, value2, "rechargeTime");
            return (Criteria) this;
        }

        public Criteria andOpenIdIsNull() {
            addCriterion("open_id is null");
            return (Criteria) this;
        }

        public Criteria andOpenIdIsNotNull() {
            addCriterion("open_id is not null");
            return (Criteria) this;
        }

        public Criteria andOpenIdEqualTo(String value) {
            addCriterion("open_id =", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotEqualTo(String value) {
            addCriterion("open_id <>", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdGreaterThan(String value) {
            addCriterion("open_id >", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdGreaterThanOrEqualTo(String value) {
            addCriterion("open_id >=", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLessThan(String value) {
            addCriterion("open_id <", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLessThanOrEqualTo(String value) {
            addCriterion("open_id <=", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLike(String value) {
            addCriterion("open_id like", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotLike(String value) {
            addCriterion("open_id not like", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdIn(List<String> values) {
            addCriterion("open_id in", values, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotIn(List<String> values) {
            addCriterion("open_id not in", values, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdBetween(String value1, String value2) {
            addCriterion("open_id between", value1, value2, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotBetween(String value1, String value2) {
            addCriterion("open_id not between", value1, value2, "openId");
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