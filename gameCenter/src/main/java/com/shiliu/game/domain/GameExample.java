package com.shiliu.game.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GameExample() {
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

        public Criteria andGameNameIsNull() {
            addCriterion("game_name is null");
            return (Criteria) this;
        }

        public Criteria andGameNameIsNotNull() {
            addCriterion("game_name is not null");
            return (Criteria) this;
        }

        public Criteria andGameNameEqualTo(String value) {
            addCriterion("game_name =", value, "gameName");
            return (Criteria) this;
        }

        public Criteria andGameNameNotEqualTo(String value) {
            addCriterion("game_name <>", value, "gameName");
            return (Criteria) this;
        }

        public Criteria andGameNameGreaterThan(String value) {
            addCriterion("game_name >", value, "gameName");
            return (Criteria) this;
        }

        public Criteria andGameNameGreaterThanOrEqualTo(String value) {
            addCriterion("game_name >=", value, "gameName");
            return (Criteria) this;
        }

        public Criteria andGameNameLessThan(String value) {
            addCriterion("game_name <", value, "gameName");
            return (Criteria) this;
        }

        public Criteria andGameNameLessThanOrEqualTo(String value) {
            addCriterion("game_name <=", value, "gameName");
            return (Criteria) this;
        }

        public Criteria andGameNameLike(String value) {
            addCriterion("game_name like", value, "gameName");
            return (Criteria) this;
        }

        public Criteria andGameNameNotLike(String value) {
            addCriterion("game_name not like", value, "gameName");
            return (Criteria) this;
        }

        public Criteria andGameNameIn(List<String> values) {
            addCriterion("game_name in", values, "gameName");
            return (Criteria) this;
        }

        public Criteria andGameNameNotIn(List<String> values) {
            addCriterion("game_name not in", values, "gameName");
            return (Criteria) this;
        }

        public Criteria andGameNameBetween(String value1, String value2) {
            addCriterion("game_name between", value1, value2, "gameName");
            return (Criteria) this;
        }

        public Criteria andGameNameNotBetween(String value1, String value2) {
            addCriterion("game_name not between", value1, value2, "gameName");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andGameRuleIsNull() {
            addCriterion("game_rule is null");
            return (Criteria) this;
        }

        public Criteria andGameRuleIsNotNull() {
            addCriterion("game_rule is not null");
            return (Criteria) this;
        }

        public Criteria andGameRuleEqualTo(String value) {
            addCriterion("game_rule =", value, "gameRule");
            return (Criteria) this;
        }

        public Criteria andGameRuleNotEqualTo(String value) {
            addCriterion("game_rule <>", value, "gameRule");
            return (Criteria) this;
        }

        public Criteria andGameRuleGreaterThan(String value) {
            addCriterion("game_rule >", value, "gameRule");
            return (Criteria) this;
        }

        public Criteria andGameRuleGreaterThanOrEqualTo(String value) {
            addCriterion("game_rule >=", value, "gameRule");
            return (Criteria) this;
        }

        public Criteria andGameRuleLessThan(String value) {
            addCriterion("game_rule <", value, "gameRule");
            return (Criteria) this;
        }

        public Criteria andGameRuleLessThanOrEqualTo(String value) {
            addCriterion("game_rule <=", value, "gameRule");
            return (Criteria) this;
        }

        public Criteria andGameRuleLike(String value) {
            addCriterion("game_rule like", value, "gameRule");
            return (Criteria) this;
        }

        public Criteria andGameRuleNotLike(String value) {
            addCriterion("game_rule not like", value, "gameRule");
            return (Criteria) this;
        }

        public Criteria andGameRuleIn(List<String> values) {
            addCriterion("game_rule in", values, "gameRule");
            return (Criteria) this;
        }

        public Criteria andGameRuleNotIn(List<String> values) {
            addCriterion("game_rule not in", values, "gameRule");
            return (Criteria) this;
        }

        public Criteria andGameRuleBetween(String value1, String value2) {
            addCriterion("game_rule between", value1, value2, "gameRule");
            return (Criteria) this;
        }

        public Criteria andGameRuleNotBetween(String value1, String value2) {
            addCriterion("game_rule not between", value1, value2, "gameRule");
            return (Criteria) this;
        }

        public Criteria andGameTypeIsNull() {
            addCriterion("game_type is null");
            return (Criteria) this;
        }

        public Criteria andGameTypeIsNotNull() {
            addCriterion("game_type is not null");
            return (Criteria) this;
        }

        public Criteria andGameTypeEqualTo(Integer value) {
            addCriterion("game_type =", value, "gameType");
            return (Criteria) this;
        }

        public Criteria andGameTypeNotEqualTo(Integer value) {
            addCriterion("game_type <>", value, "gameType");
            return (Criteria) this;
        }

        public Criteria andGameTypeGreaterThan(Integer value) {
            addCriterion("game_type >", value, "gameType");
            return (Criteria) this;
        }

        public Criteria andGameTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("game_type >=", value, "gameType");
            return (Criteria) this;
        }

        public Criteria andGameTypeLessThan(Integer value) {
            addCriterion("game_type <", value, "gameType");
            return (Criteria) this;
        }

        public Criteria andGameTypeLessThanOrEqualTo(Integer value) {
            addCriterion("game_type <=", value, "gameType");
            return (Criteria) this;
        }

        public Criteria andGameTypeIn(List<Integer> values) {
            addCriterion("game_type in", values, "gameType");
            return (Criteria) this;
        }

        public Criteria andGameTypeNotIn(List<Integer> values) {
            addCriterion("game_type not in", values, "gameType");
            return (Criteria) this;
        }

        public Criteria andGameTypeBetween(Integer value1, Integer value2) {
            addCriterion("game_type between", value1, value2, "gameType");
            return (Criteria) this;
        }

        public Criteria andGameTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("game_type not between", value1, value2, "gameType");
            return (Criteria) this;
        }

        public Criteria andFlowTypeIsNull() {
            addCriterion("flow_type is null");
            return (Criteria) this;
        }

        public Criteria andFlowTypeIsNotNull() {
            addCriterion("flow_type is not null");
            return (Criteria) this;
        }

        public Criteria andFlowTypeEqualTo(Short value) {
            addCriterion("flow_type =", value, "flowType");
            return (Criteria) this;
        }

        public Criteria andFlowTypeNotEqualTo(Short value) {
            addCriterion("flow_type <>", value, "flowType");
            return (Criteria) this;
        }

        public Criteria andFlowTypeGreaterThan(Short value) {
            addCriterion("flow_type >", value, "flowType");
            return (Criteria) this;
        }

        public Criteria andFlowTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("flow_type >=", value, "flowType");
            return (Criteria) this;
        }

        public Criteria andFlowTypeLessThan(Short value) {
            addCriterion("flow_type <", value, "flowType");
            return (Criteria) this;
        }

        public Criteria andFlowTypeLessThanOrEqualTo(Short value) {
            addCriterion("flow_type <=", value, "flowType");
            return (Criteria) this;
        }

        public Criteria andFlowTypeIn(List<Short> values) {
            addCriterion("flow_type in", values, "flowType");
            return (Criteria) this;
        }

        public Criteria andFlowTypeNotIn(List<Short> values) {
            addCriterion("flow_type not in", values, "flowType");
            return (Criteria) this;
        }

        public Criteria andFlowTypeBetween(Short value1, Short value2) {
            addCriterion("flow_type between", value1, value2, "flowType");
            return (Criteria) this;
        }

        public Criteria andFlowTypeNotBetween(Short value1, Short value2) {
            addCriterion("flow_type not between", value1, value2, "flowType");
            return (Criteria) this;
        }

        public Criteria andFrequencyIsNull() {
            addCriterion("frequency is null");
            return (Criteria) this;
        }

        public Criteria andFrequencyIsNotNull() {
            addCriterion("frequency is not null");
            return (Criteria) this;
        }

        public Criteria andFrequencyEqualTo(Short value) {
            addCriterion("frequency =", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyNotEqualTo(Short value) {
            addCriterion("frequency <>", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyGreaterThan(Short value) {
            addCriterion("frequency >", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyGreaterThanOrEqualTo(Short value) {
            addCriterion("frequency >=", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyLessThan(Short value) {
            addCriterion("frequency <", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyLessThanOrEqualTo(Short value) {
            addCriterion("frequency <=", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyIn(List<Short> values) {
            addCriterion("frequency in", values, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyNotIn(List<Short> values) {
            addCriterion("frequency not in", values, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyBetween(Short value1, Short value2) {
            addCriterion("frequency between", value1, value2, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyNotBetween(Short value1, Short value2) {
            addCriterion("frequency not between", value1, value2, "frequency");
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

        public Criteria andEvenTimesIsNull() {
            addCriterion("even_times is null");
            return (Criteria) this;
        }

        public Criteria andEvenTimesIsNotNull() {
            addCriterion("even_times is not null");
            return (Criteria) this;
        }

        public Criteria andEvenTimesEqualTo(Integer value) {
            addCriterion("even_times =", value, "evenTimes");
            return (Criteria) this;
        }

        public Criteria andEvenTimesNotEqualTo(Integer value) {
            addCriterion("even_times <>", value, "evenTimes");
            return (Criteria) this;
        }

        public Criteria andEvenTimesGreaterThan(Integer value) {
            addCriterion("even_times >", value, "evenTimes");
            return (Criteria) this;
        }

        public Criteria andEvenTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("even_times >=", value, "evenTimes");
            return (Criteria) this;
        }

        public Criteria andEvenTimesLessThan(Integer value) {
            addCriterion("even_times <", value, "evenTimes");
            return (Criteria) this;
        }

        public Criteria andEvenTimesLessThanOrEqualTo(Integer value) {
            addCriterion("even_times <=", value, "evenTimes");
            return (Criteria) this;
        }

        public Criteria andEvenTimesIn(List<Integer> values) {
            addCriterion("even_times in", values, "evenTimes");
            return (Criteria) this;
        }

        public Criteria andEvenTimesNotIn(List<Integer> values) {
            addCriterion("even_times not in", values, "evenTimes");
            return (Criteria) this;
        }

        public Criteria andEvenTimesBetween(Integer value1, Integer value2) {
            addCriterion("even_times between", value1, value2, "evenTimes");
            return (Criteria) this;
        }

        public Criteria andEvenTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("even_times not between", value1, value2, "evenTimes");
            return (Criteria) this;
        }

        public Criteria andCreatTimeIsNull() {
            addCriterion("creat_time is null");
            return (Criteria) this;
        }

        public Criteria andCreatTimeIsNotNull() {
            addCriterion("creat_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreatTimeEqualTo(Date value) {
            addCriterion("creat_time =", value, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeNotEqualTo(Date value) {
            addCriterion("creat_time <>", value, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeGreaterThan(Date value) {
            addCriterion("creat_time >", value, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("creat_time >=", value, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeLessThan(Date value) {
            addCriterion("creat_time <", value, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeLessThanOrEqualTo(Date value) {
            addCriterion("creat_time <=", value, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeIn(List<Date> values) {
            addCriterion("creat_time in", values, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeNotIn(List<Date> values) {
            addCriterion("creat_time not in", values, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeBetween(Date value1, Date value2) {
            addCriterion("creat_time between", value1, value2, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeNotBetween(Date value1, Date value2) {
            addCriterion("creat_time not between", value1, value2, "creatTime");
            return (Criteria) this;
        }

        public Criteria andImageUrl1IsNull() {
            addCriterion("image_url1 is null");
            return (Criteria) this;
        }

        public Criteria andImageUrl1IsNotNull() {
            addCriterion("image_url1 is not null");
            return (Criteria) this;
        }

        public Criteria andImageUrl1EqualTo(String value) {
            addCriterion("image_url1 =", value, "imageUrl1");
            return (Criteria) this;
        }

        public Criteria andImageUrl1NotEqualTo(String value) {
            addCriterion("image_url1 <>", value, "imageUrl1");
            return (Criteria) this;
        }

        public Criteria andImageUrl1GreaterThan(String value) {
            addCriterion("image_url1 >", value, "imageUrl1");
            return (Criteria) this;
        }

        public Criteria andImageUrl1GreaterThanOrEqualTo(String value) {
            addCriterion("image_url1 >=", value, "imageUrl1");
            return (Criteria) this;
        }

        public Criteria andImageUrl1LessThan(String value) {
            addCriterion("image_url1 <", value, "imageUrl1");
            return (Criteria) this;
        }

        public Criteria andImageUrl1LessThanOrEqualTo(String value) {
            addCriterion("image_url1 <=", value, "imageUrl1");
            return (Criteria) this;
        }

        public Criteria andImageUrl1Like(String value) {
            addCriterion("image_url1 like", value, "imageUrl1");
            return (Criteria) this;
        }

        public Criteria andImageUrl1NotLike(String value) {
            addCriterion("image_url1 not like", value, "imageUrl1");
            return (Criteria) this;
        }

        public Criteria andImageUrl1In(List<String> values) {
            addCriterion("image_url1 in", values, "imageUrl1");
            return (Criteria) this;
        }

        public Criteria andImageUrl1NotIn(List<String> values) {
            addCriterion("image_url1 not in", values, "imageUrl1");
            return (Criteria) this;
        }

        public Criteria andImageUrl1Between(String value1, String value2) {
            addCriterion("image_url1 between", value1, value2, "imageUrl1");
            return (Criteria) this;
        }

        public Criteria andImageUrl1NotBetween(String value1, String value2) {
            addCriterion("image_url1 not between", value1, value2, "imageUrl1");
            return (Criteria) this;
        }

        public Criteria andImageUrl2IsNull() {
            addCriterion("image_url2 is null");
            return (Criteria) this;
        }

        public Criteria andImageUrl2IsNotNull() {
            addCriterion("image_url2 is not null");
            return (Criteria) this;
        }

        public Criteria andImageUrl2EqualTo(String value) {
            addCriterion("image_url2 =", value, "imageUrl2");
            return (Criteria) this;
        }

        public Criteria andImageUrl2NotEqualTo(String value) {
            addCriterion("image_url2 <>", value, "imageUrl2");
            return (Criteria) this;
        }

        public Criteria andImageUrl2GreaterThan(String value) {
            addCriterion("image_url2 >", value, "imageUrl2");
            return (Criteria) this;
        }

        public Criteria andImageUrl2GreaterThanOrEqualTo(String value) {
            addCriterion("image_url2 >=", value, "imageUrl2");
            return (Criteria) this;
        }

        public Criteria andImageUrl2LessThan(String value) {
            addCriterion("image_url2 <", value, "imageUrl2");
            return (Criteria) this;
        }

        public Criteria andImageUrl2LessThanOrEqualTo(String value) {
            addCriterion("image_url2 <=", value, "imageUrl2");
            return (Criteria) this;
        }

        public Criteria andImageUrl2Like(String value) {
            addCriterion("image_url2 like", value, "imageUrl2");
            return (Criteria) this;
        }

        public Criteria andImageUrl2NotLike(String value) {
            addCriterion("image_url2 not like", value, "imageUrl2");
            return (Criteria) this;
        }

        public Criteria andImageUrl2In(List<String> values) {
            addCriterion("image_url2 in", values, "imageUrl2");
            return (Criteria) this;
        }

        public Criteria andImageUrl2NotIn(List<String> values) {
            addCriterion("image_url2 not in", values, "imageUrl2");
            return (Criteria) this;
        }

        public Criteria andImageUrl2Between(String value1, String value2) {
            addCriterion("image_url2 between", value1, value2, "imageUrl2");
            return (Criteria) this;
        }

        public Criteria andImageUrl2NotBetween(String value1, String value2) {
            addCriterion("image_url2 not between", value1, value2, "imageUrl2");
            return (Criteria) this;
        }

        public Criteria andImageUrl3IsNull() {
            addCriterion("image_url3 is null");
            return (Criteria) this;
        }

        public Criteria andImageUrl3IsNotNull() {
            addCriterion("image_url3 is not null");
            return (Criteria) this;
        }

        public Criteria andImageUrl3EqualTo(String value) {
            addCriterion("image_url3 =", value, "imageUrl3");
            return (Criteria) this;
        }

        public Criteria andImageUrl3NotEqualTo(String value) {
            addCriterion("image_url3 <>", value, "imageUrl3");
            return (Criteria) this;
        }

        public Criteria andImageUrl3GreaterThan(String value) {
            addCriterion("image_url3 >", value, "imageUrl3");
            return (Criteria) this;
        }

        public Criteria andImageUrl3GreaterThanOrEqualTo(String value) {
            addCriterion("image_url3 >=", value, "imageUrl3");
            return (Criteria) this;
        }

        public Criteria andImageUrl3LessThan(String value) {
            addCriterion("image_url3 <", value, "imageUrl3");
            return (Criteria) this;
        }

        public Criteria andImageUrl3LessThanOrEqualTo(String value) {
            addCriterion("image_url3 <=", value, "imageUrl3");
            return (Criteria) this;
        }

        public Criteria andImageUrl3Like(String value) {
            addCriterion("image_url3 like", value, "imageUrl3");
            return (Criteria) this;
        }

        public Criteria andImageUrl3NotLike(String value) {
            addCriterion("image_url3 not like", value, "imageUrl3");
            return (Criteria) this;
        }

        public Criteria andImageUrl3In(List<String> values) {
            addCriterion("image_url3 in", values, "imageUrl3");
            return (Criteria) this;
        }

        public Criteria andImageUrl3NotIn(List<String> values) {
            addCriterion("image_url3 not in", values, "imageUrl3");
            return (Criteria) this;
        }

        public Criteria andImageUrl3Between(String value1, String value2) {
            addCriterion("image_url3 between", value1, value2, "imageUrl3");
            return (Criteria) this;
        }

        public Criteria andImageUrl3NotBetween(String value1, String value2) {
            addCriterion("image_url3 not between", value1, value2, "imageUrl3");
            return (Criteria) this;
        }

        public Criteria andEndTimesIsNull() {
            addCriterion("end_times is null");
            return (Criteria) this;
        }

        public Criteria andEndTimesIsNotNull() {
            addCriterion("end_times is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimesEqualTo(Date value) {
            addCriterion("end_times =", value, "endTimes");
            return (Criteria) this;
        }

        public Criteria andEndTimesNotEqualTo(Date value) {
            addCriterion("end_times <>", value, "endTimes");
            return (Criteria) this;
        }

        public Criteria andEndTimesGreaterThan(Date value) {
            addCriterion("end_times >", value, "endTimes");
            return (Criteria) this;
        }

        public Criteria andEndTimesGreaterThanOrEqualTo(Date value) {
            addCriterion("end_times >=", value, "endTimes");
            return (Criteria) this;
        }

        public Criteria andEndTimesLessThan(Date value) {
            addCriterion("end_times <", value, "endTimes");
            return (Criteria) this;
        }

        public Criteria andEndTimesLessThanOrEqualTo(Date value) {
            addCriterion("end_times <=", value, "endTimes");
            return (Criteria) this;
        }

        public Criteria andEndTimesIn(List<Date> values) {
            addCriterion("end_times in", values, "endTimes");
            return (Criteria) this;
        }

        public Criteria andEndTimesNotIn(List<Date> values) {
            addCriterion("end_times not in", values, "endTimes");
            return (Criteria) this;
        }

        public Criteria andEndTimesBetween(Date value1, Date value2) {
            addCriterion("end_times between", value1, value2, "endTimes");
            return (Criteria) this;
        }

        public Criteria andEndTimesNotBetween(Date value1, Date value2) {
            addCriterion("end_times not between", value1, value2, "endTimes");
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