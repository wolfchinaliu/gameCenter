package com.shiliu.game.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserGameExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserGameExample() {
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

        public Criteria andUserGameIdIsNull() {
            addCriterion("user_game_id is null");
            return (Criteria) this;
        }

        public Criteria andUserGameIdIsNotNull() {
            addCriterion("user_game_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserGameIdEqualTo(Long value) {
            addCriterion("user_game_id =", value, "userGameId");
            return (Criteria) this;
        }

        public Criteria andUserGameIdNotEqualTo(Long value) {
            addCriterion("user_game_id <>", value, "userGameId");
            return (Criteria) this;
        }

        public Criteria andUserGameIdGreaterThan(Long value) {
            addCriterion("user_game_id >", value, "userGameId");
            return (Criteria) this;
        }

        public Criteria andUserGameIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_game_id >=", value, "userGameId");
            return (Criteria) this;
        }

        public Criteria andUserGameIdLessThan(Long value) {
            addCriterion("user_game_id <", value, "userGameId");
            return (Criteria) this;
        }

        public Criteria andUserGameIdLessThanOrEqualTo(Long value) {
            addCriterion("user_game_id <=", value, "userGameId");
            return (Criteria) this;
        }

        public Criteria andUserGameIdIn(List<Long> values) {
            addCriterion("user_game_id in", values, "userGameId");
            return (Criteria) this;
        }

        public Criteria andUserGameIdNotIn(List<Long> values) {
            addCriterion("user_game_id not in", values, "userGameId");
            return (Criteria) this;
        }

        public Criteria andUserGameIdBetween(Long value1, Long value2) {
            addCriterion("user_game_id between", value1, value2, "userGameId");
            return (Criteria) this;
        }

        public Criteria andUserGameIdNotBetween(Long value1, Long value2) {
            addCriterion("user_game_id not between", value1, value2, "userGameId");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNull() {
            addCriterion("openid is null");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNotNull() {
            addCriterion("openid is not null");
            return (Criteria) this;
        }

        public Criteria andOpenidEqualTo(String value) {
            addCriterion("openid =", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotEqualTo(String value) {
            addCriterion("openid <>", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThan(String value) {
            addCriterion("openid >", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("openid >=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThan(String value) {
            addCriterion("openid <", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThanOrEqualTo(String value) {
            addCriterion("openid <=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLike(String value) {
            addCriterion("openid like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotLike(String value) {
            addCriterion("openid not like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidIn(List<String> values) {
            addCriterion("openid in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotIn(List<String> values) {
            addCriterion("openid not in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidBetween(String value1, String value2) {
            addCriterion("openid between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotBetween(String value1, String value2) {
            addCriterion("openid not between", value1, value2, "openid");
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

        public Criteria andFlowNumIsNull() {
            addCriterion("flow_num is null");
            return (Criteria) this;
        }

        public Criteria andFlowNumIsNotNull() {
            addCriterion("flow_num is not null");
            return (Criteria) this;
        }

        public Criteria andFlowNumEqualTo(Integer value) {
            addCriterion("flow_num =", value, "flowNum");
            return (Criteria) this;
        }

        public Criteria andFlowNumNotEqualTo(Integer value) {
            addCriterion("flow_num <>", value, "flowNum");
            return (Criteria) this;
        }

        public Criteria andFlowNumGreaterThan(Integer value) {
            addCriterion("flow_num >", value, "flowNum");
            return (Criteria) this;
        }

        public Criteria andFlowNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("flow_num >=", value, "flowNum");
            return (Criteria) this;
        }

        public Criteria andFlowNumLessThan(Integer value) {
            addCriterion("flow_num <", value, "flowNum");
            return (Criteria) this;
        }

        public Criteria andFlowNumLessThanOrEqualTo(Integer value) {
            addCriterion("flow_num <=", value, "flowNum");
            return (Criteria) this;
        }

        public Criteria andFlowNumIn(List<Integer> values) {
            addCriterion("flow_num in", values, "flowNum");
            return (Criteria) this;
        }

        public Criteria andFlowNumNotIn(List<Integer> values) {
            addCriterion("flow_num not in", values, "flowNum");
            return (Criteria) this;
        }

        public Criteria andFlowNumBetween(Integer value1, Integer value2) {
            addCriterion("flow_num between", value1, value2, "flowNum");
            return (Criteria) this;
        }

        public Criteria andFlowNumNotBetween(Integer value1, Integer value2) {
            addCriterion("flow_num not between", value1, value2, "flowNum");
            return (Criteria) this;
        }

        public Criteria andPlayTimeIsNull() {
            addCriterion("play_time is null");
            return (Criteria) this;
        }

        public Criteria andPlayTimeIsNotNull() {
            addCriterion("play_time is not null");
            return (Criteria) this;
        }

        public Criteria andPlayTimeEqualTo(Date value) {
            addCriterion("play_time =", value, "playTime");
            return (Criteria) this;
        }

        public Criteria andPlayTimeNotEqualTo(Date value) {
            addCriterion("play_time <>", value, "playTime");
            return (Criteria) this;
        }

        public Criteria andPlayTimeGreaterThan(Date value) {
            addCriterion("play_time >", value, "playTime");
            return (Criteria) this;
        }

        public Criteria andPlayTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("play_time >=", value, "playTime");
            return (Criteria) this;
        }

        public Criteria andPlayTimeLessThan(Date value) {
            addCriterion("play_time <", value, "playTime");
            return (Criteria) this;
        }

        public Criteria andPlayTimeLessThanOrEqualTo(Date value) {
            addCriterion("play_time <=", value, "playTime");
            return (Criteria) this;
        }

        public Criteria andPlayTimeIn(List<Date> values) {
            addCriterion("play_time in", values, "playTime");
            return (Criteria) this;
        }

        public Criteria andPlayTimeNotIn(List<Date> values) {
            addCriterion("play_time not in", values, "playTime");
            return (Criteria) this;
        }

        public Criteria andPlayTimeBetween(Date value1, Date value2) {
            addCriterion("play_time between", value1, value2, "playTime");
            return (Criteria) this;
        }

        public Criteria andPlayTimeNotBetween(Date value1, Date value2) {
            addCriterion("play_time not between", value1, value2, "playTime");
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

        public Criteria andPlayTimesIsNull() {
            addCriterion("play_times is null");
            return (Criteria) this;
        }

        public Criteria andPlayTimesIsNotNull() {
            addCriterion("play_times is not null");
            return (Criteria) this;
        }

        public Criteria andPlayTimesEqualTo(Integer value) {
            addCriterion("play_times =", value, "playTimes");
            return (Criteria) this;
        }

        public Criteria andPlayTimesNotEqualTo(Integer value) {
            addCriterion("play_times <>", value, "playTimes");
            return (Criteria) this;
        }

        public Criteria andPlayTimesGreaterThan(Integer value) {
            addCriterion("play_times >", value, "playTimes");
            return (Criteria) this;
        }

        public Criteria andPlayTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("play_times >=", value, "playTimes");
            return (Criteria) this;
        }

        public Criteria andPlayTimesLessThan(Integer value) {
            addCriterion("play_times <", value, "playTimes");
            return (Criteria) this;
        }

        public Criteria andPlayTimesLessThanOrEqualTo(Integer value) {
            addCriterion("play_times <=", value, "playTimes");
            return (Criteria) this;
        }

        public Criteria andPlayTimesIn(List<Integer> values) {
            addCriterion("play_times in", values, "playTimes");
            return (Criteria) this;
        }

        public Criteria andPlayTimesNotIn(List<Integer> values) {
            addCriterion("play_times not in", values, "playTimes");
            return (Criteria) this;
        }

        public Criteria andPlayTimesBetween(Integer value1, Integer value2) {
            addCriterion("play_times between", value1, value2, "playTimes");
            return (Criteria) this;
        }

        public Criteria andPlayTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("play_times not between", value1, value2, "playTimes");
            return (Criteria) this;
        }

        public Criteria andTotalTimesIsNull() {
            addCriterion("total_times is null");
            return (Criteria) this;
        }

        public Criteria andTotalTimesIsNotNull() {
            addCriterion("total_times is not null");
            return (Criteria) this;
        }

        public Criteria andTotalTimesEqualTo(Integer value) {
            addCriterion("total_times =", value, "totalTimes");
            return (Criteria) this;
        }

        public Criteria andTotalTimesNotEqualTo(Integer value) {
            addCriterion("total_times <>", value, "totalTimes");
            return (Criteria) this;
        }

        public Criteria andTotalTimesGreaterThan(Integer value) {
            addCriterion("total_times >", value, "totalTimes");
            return (Criteria) this;
        }

        public Criteria andTotalTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_times >=", value, "totalTimes");
            return (Criteria) this;
        }

        public Criteria andTotalTimesLessThan(Integer value) {
            addCriterion("total_times <", value, "totalTimes");
            return (Criteria) this;
        }

        public Criteria andTotalTimesLessThanOrEqualTo(Integer value) {
            addCriterion("total_times <=", value, "totalTimes");
            return (Criteria) this;
        }

        public Criteria andTotalTimesIn(List<Integer> values) {
            addCriterion("total_times in", values, "totalTimes");
            return (Criteria) this;
        }

        public Criteria andTotalTimesNotIn(List<Integer> values) {
            addCriterion("total_times not in", values, "totalTimes");
            return (Criteria) this;
        }

        public Criteria andTotalTimesBetween(Integer value1, Integer value2) {
            addCriterion("total_times between", value1, value2, "totalTimes");
            return (Criteria) this;
        }

        public Criteria andTotalTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("total_times not between", value1, value2, "totalTimes");
            return (Criteria) this;
        }

        public Criteria andHolenFlowIsNull() {
            addCriterion("holen_flow is null");
            return (Criteria) this;
        }

        public Criteria andHolenFlowIsNotNull() {
            addCriterion("holen_flow is not null");
            return (Criteria) this;
        }

        public Criteria andHolenFlowEqualTo(Integer value) {
            addCriterion("holen_flow =", value, "holenFlow");
            return (Criteria) this;
        }

        public Criteria andHolenFlowNotEqualTo(Integer value) {
            addCriterion("holen_flow <>", value, "holenFlow");
            return (Criteria) this;
        }

        public Criteria andHolenFlowGreaterThan(Integer value) {
            addCriterion("holen_flow >", value, "holenFlow");
            return (Criteria) this;
        }

        public Criteria andHolenFlowGreaterThanOrEqualTo(Integer value) {
            addCriterion("holen_flow >=", value, "holenFlow");
            return (Criteria) this;
        }

        public Criteria andHolenFlowLessThan(Integer value) {
            addCriterion("holen_flow <", value, "holenFlow");
            return (Criteria) this;
        }

        public Criteria andHolenFlowLessThanOrEqualTo(Integer value) {
            addCriterion("holen_flow <=", value, "holenFlow");
            return (Criteria) this;
        }

        public Criteria andHolenFlowIn(List<Integer> values) {
            addCriterion("holen_flow in", values, "holenFlow");
            return (Criteria) this;
        }

        public Criteria andHolenFlowNotIn(List<Integer> values) {
            addCriterion("holen_flow not in", values, "holenFlow");
            return (Criteria) this;
        }

        public Criteria andHolenFlowBetween(Integer value1, Integer value2) {
            addCriterion("holen_flow between", value1, value2, "holenFlow");
            return (Criteria) this;
        }

        public Criteria andHolenFlowNotBetween(Integer value1, Integer value2) {
            addCriterion("holen_flow not between", value1, value2, "holenFlow");
            return (Criteria) this;
        }

        public Criteria andMaxScoreIsNull() {
            addCriterion("max_score is null");
            return (Criteria) this;
        }

        public Criteria andMaxScoreIsNotNull() {
            addCriterion("max_score is not null");
            return (Criteria) this;
        }

        public Criteria andMaxScoreEqualTo(Integer value) {
            addCriterion("max_score =", value, "maxScore");
            return (Criteria) this;
        }

        public Criteria andMaxScoreNotEqualTo(Integer value) {
            addCriterion("max_score <>", value, "maxScore");
            return (Criteria) this;
        }

        public Criteria andMaxScoreGreaterThan(Integer value) {
            addCriterion("max_score >", value, "maxScore");
            return (Criteria) this;
        }

        public Criteria andMaxScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_score >=", value, "maxScore");
            return (Criteria) this;
        }

        public Criteria andMaxScoreLessThan(Integer value) {
            addCriterion("max_score <", value, "maxScore");
            return (Criteria) this;
        }

        public Criteria andMaxScoreLessThanOrEqualTo(Integer value) {
            addCriterion("max_score <=", value, "maxScore");
            return (Criteria) this;
        }

        public Criteria andMaxScoreIn(List<Integer> values) {
            addCriterion("max_score in", values, "maxScore");
            return (Criteria) this;
        }

        public Criteria andMaxScoreNotIn(List<Integer> values) {
            addCriterion("max_score not in", values, "maxScore");
            return (Criteria) this;
        }

        public Criteria andMaxScoreBetween(Integer value1, Integer value2) {
            addCriterion("max_score between", value1, value2, "maxScore");
            return (Criteria) this;
        }

        public Criteria andMaxScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("max_score not between", value1, value2, "maxScore");
            return (Criteria) this;
        }

        public Criteria andLastCycleScoreIsNull() {
            addCriterion("last_cycle_score is null");
            return (Criteria) this;
        }

        public Criteria andLastCycleScoreIsNotNull() {
            addCriterion("last_cycle_score is not null");
            return (Criteria) this;
        }

        public Criteria andLastCycleScoreEqualTo(Integer value) {
            addCriterion("last_cycle_score =", value, "lastCycleScore");
            return (Criteria) this;
        }

        public Criteria andLastCycleScoreNotEqualTo(Integer value) {
            addCriterion("last_cycle_score <>", value, "lastCycleScore");
            return (Criteria) this;
        }

        public Criteria andLastCycleScoreGreaterThan(Integer value) {
            addCriterion("last_cycle_score >", value, "lastCycleScore");
            return (Criteria) this;
        }

        public Criteria andLastCycleScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("last_cycle_score >=", value, "lastCycleScore");
            return (Criteria) this;
        }

        public Criteria andLastCycleScoreLessThan(Integer value) {
            addCriterion("last_cycle_score <", value, "lastCycleScore");
            return (Criteria) this;
        }

        public Criteria andLastCycleScoreLessThanOrEqualTo(Integer value) {
            addCriterion("last_cycle_score <=", value, "lastCycleScore");
            return (Criteria) this;
        }

        public Criteria andLastCycleScoreIn(List<Integer> values) {
            addCriterion("last_cycle_score in", values, "lastCycleScore");
            return (Criteria) this;
        }

        public Criteria andLastCycleScoreNotIn(List<Integer> values) {
            addCriterion("last_cycle_score not in", values, "lastCycleScore");
            return (Criteria) this;
        }

        public Criteria andLastCycleScoreBetween(Integer value1, Integer value2) {
            addCriterion("last_cycle_score between", value1, value2, "lastCycleScore");
            return (Criteria) this;
        }

        public Criteria andLastCycleScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("last_cycle_score not between", value1, value2, "lastCycleScore");
            return (Criteria) this;
        }

        public Criteria andCurrentScoreIsNull() {
            addCriterion("current_score is null");
            return (Criteria) this;
        }

        public Criteria andCurrentScoreIsNotNull() {
            addCriterion("current_score is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentScoreEqualTo(Integer value) {
            addCriterion("current_score =", value, "currentScore");
            return (Criteria) this;
        }

        public Criteria andCurrentScoreNotEqualTo(Integer value) {
            addCriterion("current_score <>", value, "currentScore");
            return (Criteria) this;
        }

        public Criteria andCurrentScoreGreaterThan(Integer value) {
            addCriterion("current_score >", value, "currentScore");
            return (Criteria) this;
        }

        public Criteria andCurrentScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("current_score >=", value, "currentScore");
            return (Criteria) this;
        }

        public Criteria andCurrentScoreLessThan(Integer value) {
            addCriterion("current_score <", value, "currentScore");
            return (Criteria) this;
        }

        public Criteria andCurrentScoreLessThanOrEqualTo(Integer value) {
            addCriterion("current_score <=", value, "currentScore");
            return (Criteria) this;
        }

        public Criteria andCurrentScoreIn(List<Integer> values) {
            addCriterion("current_score in", values, "currentScore");
            return (Criteria) this;
        }

        public Criteria andCurrentScoreNotIn(List<Integer> values) {
            addCriterion("current_score not in", values, "currentScore");
            return (Criteria) this;
        }

        public Criteria andCurrentScoreBetween(Integer value1, Integer value2) {
            addCriterion("current_score between", value1, value2, "currentScore");
            return (Criteria) this;
        }

        public Criteria andCurrentScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("current_score not between", value1, value2, "currentScore");
            return (Criteria) this;
        }

        public Criteria andIsUpdateIsNull() {
            addCriterion("is_update is null");
            return (Criteria) this;
        }

        public Criteria andIsUpdateIsNotNull() {
            addCriterion("is_update is not null");
            return (Criteria) this;
        }

        public Criteria andIsUpdateEqualTo(Boolean value) {
            addCriterion("is_update =", value, "isUpdate");
            return (Criteria) this;
        }

        public Criteria andIsUpdateNotEqualTo(Boolean value) {
            addCriterion("is_update <>", value, "isUpdate");
            return (Criteria) this;
        }

        public Criteria andIsUpdateGreaterThan(Boolean value) {
            addCriterion("is_update >", value, "isUpdate");
            return (Criteria) this;
        }

        public Criteria andIsUpdateGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_update >=", value, "isUpdate");
            return (Criteria) this;
        }

        public Criteria andIsUpdateLessThan(Boolean value) {
            addCriterion("is_update <", value, "isUpdate");
            return (Criteria) this;
        }

        public Criteria andIsUpdateLessThanOrEqualTo(Boolean value) {
            addCriterion("is_update <=", value, "isUpdate");
            return (Criteria) this;
        }

        public Criteria andIsUpdateIn(List<Boolean> values) {
            addCriterion("is_update in", values, "isUpdate");
            return (Criteria) this;
        }

        public Criteria andIsUpdateNotIn(List<Boolean> values) {
            addCriterion("is_update not in", values, "isUpdate");
            return (Criteria) this;
        }

        public Criteria andIsUpdateBetween(Boolean value1, Boolean value2) {
            addCriterion("is_update between", value1, value2, "isUpdate");
            return (Criteria) this;
        }

        public Criteria andIsUpdateNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_update not between", value1, value2, "isUpdate");
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