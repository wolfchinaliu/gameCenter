package com.shiliu.game.domain;

import java.util.ArrayList;
import java.util.List;

public class BallotExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BallotExample() {
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

        public Criteria andBallotIdIsNull() {
            addCriterion("ballot_id is null");
            return (Criteria) this;
        }

        public Criteria andBallotIdIsNotNull() {
            addCriterion("ballot_id is not null");
            return (Criteria) this;
        }

        public Criteria andBallotIdEqualTo(String value) {
            addCriterion("ballot_id =", value, "ballotId");
            return (Criteria) this;
        }

        public Criteria andBallotIdNotEqualTo(String value) {
            addCriterion("ballot_id <>", value, "ballotId");
            return (Criteria) this;
        }

        public Criteria andBallotIdGreaterThan(String value) {
            addCriterion("ballot_id >", value, "ballotId");
            return (Criteria) this;
        }

        public Criteria andBallotIdGreaterThanOrEqualTo(String value) {
            addCriterion("ballot_id >=", value, "ballotId");
            return (Criteria) this;
        }

        public Criteria andBallotIdLessThan(String value) {
            addCriterion("ballot_id <", value, "ballotId");
            return (Criteria) this;
        }

        public Criteria andBallotIdLessThanOrEqualTo(String value) {
            addCriterion("ballot_id <=", value, "ballotId");
            return (Criteria) this;
        }

        public Criteria andBallotIdLike(String value) {
            addCriterion("ballot_id like", value, "ballotId");
            return (Criteria) this;
        }

        public Criteria andBallotIdNotLike(String value) {
            addCriterion("ballot_id not like", value, "ballotId");
            return (Criteria) this;
        }

        public Criteria andBallotIdIn(List<String> values) {
            addCriterion("ballot_id in", values, "ballotId");
            return (Criteria) this;
        }

        public Criteria andBallotIdNotIn(List<String> values) {
            addCriterion("ballot_id not in", values, "ballotId");
            return (Criteria) this;
        }

        public Criteria andBallotIdBetween(String value1, String value2) {
            addCriterion("ballot_id between", value1, value2, "ballotId");
            return (Criteria) this;
        }

        public Criteria andBallotIdNotBetween(String value1, String value2) {
            addCriterion("ballot_id not between", value1, value2, "ballotId");
            return (Criteria) this;
        }

        public Criteria andGameIdIsNull() {
            addCriterion("game_id is null");
            return (Criteria) this;
        }

        public Criteria andGameIdIsNotNull() {
            addCriterion("game_id is not null");
            return (Criteria) this;
        }

        public Criteria andGameIdEqualTo(String value) {
            addCriterion("game_id =", value, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdNotEqualTo(String value) {
            addCriterion("game_id <>", value, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdGreaterThan(String value) {
            addCriterion("game_id >", value, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdGreaterThanOrEqualTo(String value) {
            addCriterion("game_id >=", value, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdLessThan(String value) {
            addCriterion("game_id <", value, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdLessThanOrEqualTo(String value) {
            addCriterion("game_id <=", value, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdLike(String value) {
            addCriterion("game_id like", value, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdNotLike(String value) {
            addCriterion("game_id not like", value, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdIn(List<String> values) {
            addCriterion("game_id in", values, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdNotIn(List<String> values) {
            addCriterion("game_id not in", values, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdBetween(String value1, String value2) {
            addCriterion("game_id between", value1, value2, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdNotBetween(String value1, String value2) {
            addCriterion("game_id not between", value1, value2, "gameId");
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

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
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