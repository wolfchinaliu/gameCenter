package com.shiliu.game.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VoteExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VoteExample() {
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

        public Criteria andVoteIdIsNull() {
            addCriterion("vote_id is null");
            return (Criteria) this;
        }

        public Criteria andVoteIdIsNotNull() {
            addCriterion("vote_id is not null");
            return (Criteria) this;
        }

        public Criteria andVoteIdEqualTo(String value) {
            addCriterion("vote_id =", value, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdNotEqualTo(String value) {
            addCriterion("vote_id <>", value, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdGreaterThan(String value) {
            addCriterion("vote_id >", value, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdGreaterThanOrEqualTo(String value) {
            addCriterion("vote_id >=", value, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdLessThan(String value) {
            addCriterion("vote_id <", value, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdLessThanOrEqualTo(String value) {
            addCriterion("vote_id <=", value, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdLike(String value) {
            addCriterion("vote_id like", value, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdNotLike(String value) {
            addCriterion("vote_id not like", value, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdIn(List<String> values) {
            addCriterion("vote_id in", values, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdNotIn(List<String> values) {
            addCriterion("vote_id not in", values, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdBetween(String value1, String value2) {
            addCriterion("vote_id between", value1, value2, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdNotBetween(String value1, String value2) {
            addCriterion("vote_id not between", value1, value2, "voteId");
            return (Criteria) this;
        }

        public Criteria andTextIsNull() {
            addCriterion("text is null");
            return (Criteria) this;
        }

        public Criteria andTextIsNotNull() {
            addCriterion("text is not null");
            return (Criteria) this;
        }

        public Criteria andTextEqualTo(String value) {
            addCriterion("text =", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextNotEqualTo(String value) {
            addCriterion("text <>", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextGreaterThan(String value) {
            addCriterion("text >", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextGreaterThanOrEqualTo(String value) {
            addCriterion("text >=", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextLessThan(String value) {
            addCriterion("text <", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextLessThanOrEqualTo(String value) {
            addCriterion("text <=", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextLike(String value) {
            addCriterion("text like", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextNotLike(String value) {
            addCriterion("text not like", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextIn(List<String> values) {
            addCriterion("text in", values, "text");
            return (Criteria) this;
        }

        public Criteria andTextNotIn(List<String> values) {
            addCriterion("text not in", values, "text");
            return (Criteria) this;
        }

        public Criteria andTextBetween(String value1, String value2) {
            addCriterion("text between", value1, value2, "text");
            return (Criteria) this;
        }

        public Criteria andTextNotBetween(String value1, String value2) {
            addCriterion("text not between", value1, value2, "text");
            return (Criteria) this;
        }

        public Criteria andVoteNameIsNull() {
            addCriterion("vote_name is null");
            return (Criteria) this;
        }

        public Criteria andVoteNameIsNotNull() {
            addCriterion("vote_name is not null");
            return (Criteria) this;
        }

        public Criteria andVoteNameEqualTo(String value) {
            addCriterion("vote_name =", value, "voteName");
            return (Criteria) this;
        }

        public Criteria andVoteNameNotEqualTo(String value) {
            addCriterion("vote_name <>", value, "voteName");
            return (Criteria) this;
        }

        public Criteria andVoteNameGreaterThan(String value) {
            addCriterion("vote_name >", value, "voteName");
            return (Criteria) this;
        }

        public Criteria andVoteNameGreaterThanOrEqualTo(String value) {
            addCriterion("vote_name >=", value, "voteName");
            return (Criteria) this;
        }

        public Criteria andVoteNameLessThan(String value) {
            addCriterion("vote_name <", value, "voteName");
            return (Criteria) this;
        }

        public Criteria andVoteNameLessThanOrEqualTo(String value) {
            addCriterion("vote_name <=", value, "voteName");
            return (Criteria) this;
        }

        public Criteria andVoteNameLike(String value) {
            addCriterion("vote_name like", value, "voteName");
            return (Criteria) this;
        }

        public Criteria andVoteNameNotLike(String value) {
            addCriterion("vote_name not like", value, "voteName");
            return (Criteria) this;
        }

        public Criteria andVoteNameIn(List<String> values) {
            addCriterion("vote_name in", values, "voteName");
            return (Criteria) this;
        }

        public Criteria andVoteNameNotIn(List<String> values) {
            addCriterion("vote_name not in", values, "voteName");
            return (Criteria) this;
        }

        public Criteria andVoteNameBetween(String value1, String value2) {
            addCriterion("vote_name between", value1, value2, "voteName");
            return (Criteria) this;
        }

        public Criteria andVoteNameNotBetween(String value1, String value2) {
            addCriterion("vote_name not between", value1, value2, "voteName");
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

        public Criteria andFlowTypeEqualTo(String value) {
            addCriterion("flow_type =", value, "flowType");
            return (Criteria) this;
        }

        public Criteria andFlowTypeNotEqualTo(String value) {
            addCriterion("flow_type <>", value, "flowType");
            return (Criteria) this;
        }

        public Criteria andFlowTypeGreaterThan(String value) {
            addCriterion("flow_type >", value, "flowType");
            return (Criteria) this;
        }

        public Criteria andFlowTypeGreaterThanOrEqualTo(String value) {
            addCriterion("flow_type >=", value, "flowType");
            return (Criteria) this;
        }

        public Criteria andFlowTypeLessThan(String value) {
            addCriterion("flow_type <", value, "flowType");
            return (Criteria) this;
        }

        public Criteria andFlowTypeLessThanOrEqualTo(String value) {
            addCriterion("flow_type <=", value, "flowType");
            return (Criteria) this;
        }

        public Criteria andFlowTypeLike(String value) {
            addCriterion("flow_type like", value, "flowType");
            return (Criteria) this;
        }

        public Criteria andFlowTypeNotLike(String value) {
            addCriterion("flow_type not like", value, "flowType");
            return (Criteria) this;
        }

        public Criteria andFlowTypeIn(List<String> values) {
            addCriterion("flow_type in", values, "flowType");
            return (Criteria) this;
        }

        public Criteria andFlowTypeNotIn(List<String> values) {
            addCriterion("flow_type not in", values, "flowType");
            return (Criteria) this;
        }

        public Criteria andFlowTypeBetween(String value1, String value2) {
            addCriterion("flow_type between", value1, value2, "flowType");
            return (Criteria) this;
        }

        public Criteria andFlowTypeNotBetween(String value1, String value2) {
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

        public Criteria andFrequencyEqualTo(Integer value) {
            addCriterion("frequency =", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyNotEqualTo(Integer value) {
            addCriterion("frequency <>", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyGreaterThan(Integer value) {
            addCriterion("frequency >", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyGreaterThanOrEqualTo(Integer value) {
            addCriterion("frequency >=", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyLessThan(Integer value) {
            addCriterion("frequency <", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyLessThanOrEqualTo(Integer value) {
            addCriterion("frequency <=", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyIn(List<Integer> values) {
            addCriterion("frequency in", values, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyNotIn(List<Integer> values) {
            addCriterion("frequency not in", values, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyBetween(Integer value1, Integer value2) {
            addCriterion("frequency between", value1, value2, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyNotBetween(Integer value1, Integer value2) {
            addCriterion("frequency not between", value1, value2, "frequency");
            return (Criteria) this;
        }

        public Criteria andTimesIsNull() {
            addCriterion("times is null");
            return (Criteria) this;
        }

        public Criteria andTimesIsNotNull() {
            addCriterion("times is not null");
            return (Criteria) this;
        }

        public Criteria andTimesEqualTo(Integer value) {
            addCriterion("times =", value, "times");
            return (Criteria) this;
        }

        public Criteria andTimesNotEqualTo(Integer value) {
            addCriterion("times <>", value, "times");
            return (Criteria) this;
        }

        public Criteria andTimesGreaterThan(Integer value) {
            addCriterion("times >", value, "times");
            return (Criteria) this;
        }

        public Criteria andTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("times >=", value, "times");
            return (Criteria) this;
        }

        public Criteria andTimesLessThan(Integer value) {
            addCriterion("times <", value, "times");
            return (Criteria) this;
        }

        public Criteria andTimesLessThanOrEqualTo(Integer value) {
            addCriterion("times <=", value, "times");
            return (Criteria) this;
        }

        public Criteria andTimesIn(List<Integer> values) {
            addCriterion("times in", values, "times");
            return (Criteria) this;
        }

        public Criteria andTimesNotIn(List<Integer> values) {
            addCriterion("times not in", values, "times");
            return (Criteria) this;
        }

        public Criteria andTimesBetween(Integer value1, Integer value2) {
            addCriterion("times between", value1, value2, "times");
            return (Criteria) this;
        }

        public Criteria andTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("times not between", value1, value2, "times");
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

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andVoteFlowIsNull() {
            addCriterion("vote_Flow is null");
            return (Criteria) this;
        }

        public Criteria andVoteFlowIsNotNull() {
            addCriterion("vote_Flow is not null");
            return (Criteria) this;
        }

        public Criteria andVoteFlowEqualTo(Integer value) {
            addCriterion("vote_Flow =", value, "voteFlow");
            return (Criteria) this;
        }

        public Criteria andVoteFlowNotEqualTo(Integer value) {
            addCriterion("vote_Flow <>", value, "voteFlow");
            return (Criteria) this;
        }

        public Criteria andVoteFlowGreaterThan(Integer value) {
            addCriterion("vote_Flow >", value, "voteFlow");
            return (Criteria) this;
        }

        public Criteria andVoteFlowGreaterThanOrEqualTo(Integer value) {
            addCriterion("vote_Flow >=", value, "voteFlow");
            return (Criteria) this;
        }

        public Criteria andVoteFlowLessThan(Integer value) {
            addCriterion("vote_Flow <", value, "voteFlow");
            return (Criteria) this;
        }

        public Criteria andVoteFlowLessThanOrEqualTo(Integer value) {
            addCriterion("vote_Flow <=", value, "voteFlow");
            return (Criteria) this;
        }

        public Criteria andVoteFlowIn(List<Integer> values) {
            addCriterion("vote_Flow in", values, "voteFlow");
            return (Criteria) this;
        }

        public Criteria andVoteFlowNotIn(List<Integer> values) {
            addCriterion("vote_Flow not in", values, "voteFlow");
            return (Criteria) this;
        }

        public Criteria andVoteFlowBetween(Integer value1, Integer value2) {
            addCriterion("vote_Flow between", value1, value2, "voteFlow");
            return (Criteria) this;
        }

        public Criteria andVoteFlowNotBetween(Integer value1, Integer value2) {
            addCriterion("vote_Flow not between", value1, value2, "voteFlow");
            return (Criteria) this;
        }

        public Criteria andWinRuleIsNull() {
            addCriterion("win_rule is null");
            return (Criteria) this;
        }

        public Criteria andWinRuleIsNotNull() {
            addCriterion("win_rule is not null");
            return (Criteria) this;
        }

        public Criteria andWinRuleEqualTo(String value) {
            addCriterion("win_rule =", value, "winRule");
            return (Criteria) this;
        }

        public Criteria andWinRuleNotEqualTo(String value) {
            addCriterion("win_rule <>", value, "winRule");
            return (Criteria) this;
        }

        public Criteria andWinRuleGreaterThan(String value) {
            addCriterion("win_rule >", value, "winRule");
            return (Criteria) this;
        }

        public Criteria andWinRuleGreaterThanOrEqualTo(String value) {
            addCriterion("win_rule >=", value, "winRule");
            return (Criteria) this;
        }

        public Criteria andWinRuleLessThan(String value) {
            addCriterion("win_rule <", value, "winRule");
            return (Criteria) this;
        }

        public Criteria andWinRuleLessThanOrEqualTo(String value) {
            addCriterion("win_rule <=", value, "winRule");
            return (Criteria) this;
        }

        public Criteria andWinRuleLike(String value) {
            addCriterion("win_rule like", value, "winRule");
            return (Criteria) this;
        }

        public Criteria andWinRuleNotLike(String value) {
            addCriterion("win_rule not like", value, "winRule");
            return (Criteria) this;
        }

        public Criteria andWinRuleIn(List<String> values) {
            addCriterion("win_rule in", values, "winRule");
            return (Criteria) this;
        }

        public Criteria andWinRuleNotIn(List<String> values) {
            addCriterion("win_rule not in", values, "winRule");
            return (Criteria) this;
        }

        public Criteria andWinRuleBetween(String value1, String value2) {
            addCriterion("win_rule between", value1, value2, "winRule");
            return (Criteria) this;
        }

        public Criteria andWinRuleNotBetween(String value1, String value2) {
            addCriterion("win_rule not between", value1, value2, "winRule");
            return (Criteria) this;
        }

        public Criteria andVoteEndTimeIsNull() {
            addCriterion("vote_end_time is null");
            return (Criteria) this;
        }

        public Criteria andVoteEndTimeIsNotNull() {
            addCriterion("vote_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andVoteEndTimeEqualTo(Date value) {
            addCriterion("vote_end_time =", value, "voteEndTime");
            return (Criteria) this;
        }

        public Criteria andVoteEndTimeNotEqualTo(Date value) {
            addCriterion("vote_end_time <>", value, "voteEndTime");
            return (Criteria) this;
        }

        public Criteria andVoteEndTimeGreaterThan(Date value) {
            addCriterion("vote_end_time >", value, "voteEndTime");
            return (Criteria) this;
        }

        public Criteria andVoteEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("vote_end_time >=", value, "voteEndTime");
            return (Criteria) this;
        }

        public Criteria andVoteEndTimeLessThan(Date value) {
            addCriterion("vote_end_time <", value, "voteEndTime");
            return (Criteria) this;
        }

        public Criteria andVoteEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("vote_end_time <=", value, "voteEndTime");
            return (Criteria) this;
        }

        public Criteria andVoteEndTimeIn(List<Date> values) {
            addCriterion("vote_end_time in", values, "voteEndTime");
            return (Criteria) this;
        }

        public Criteria andVoteEndTimeNotIn(List<Date> values) {
            addCriterion("vote_end_time not in", values, "voteEndTime");
            return (Criteria) this;
        }

        public Criteria andVoteEndTimeBetween(Date value1, Date value2) {
            addCriterion("vote_end_time between", value1, value2, "voteEndTime");
            return (Criteria) this;
        }

        public Criteria andVoteEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("vote_end_time not between", value1, value2, "voteEndTime");
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

        public Criteria andVoteNumIsNull() {
            addCriterion("vote_num is null");
            return (Criteria) this;
        }

        public Criteria andVoteNumIsNotNull() {
            addCriterion("vote_num is not null");
            return (Criteria) this;
        }

        public Criteria andVoteNumEqualTo(Integer value) {
            addCriterion("vote_num =", value, "voteNum");
            return (Criteria) this;
        }

        public Criteria andVoteNumNotEqualTo(Integer value) {
            addCriterion("vote_num <>", value, "voteNum");
            return (Criteria) this;
        }

        public Criteria andVoteNumGreaterThan(Integer value) {
            addCriterion("vote_num >", value, "voteNum");
            return (Criteria) this;
        }

        public Criteria andVoteNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("vote_num >=", value, "voteNum");
            return (Criteria) this;
        }

        public Criteria andVoteNumLessThan(Integer value) {
            addCriterion("vote_num <", value, "voteNum");
            return (Criteria) this;
        }

        public Criteria andVoteNumLessThanOrEqualTo(Integer value) {
            addCriterion("vote_num <=", value, "voteNum");
            return (Criteria) this;
        }

        public Criteria andVoteNumIn(List<Integer> values) {
            addCriterion("vote_num in", values, "voteNum");
            return (Criteria) this;
        }

        public Criteria andVoteNumNotIn(List<Integer> values) {
            addCriterion("vote_num not in", values, "voteNum");
            return (Criteria) this;
        }

        public Criteria andVoteNumBetween(Integer value1, Integer value2) {
            addCriterion("vote_num between", value1, value2, "voteNum");
            return (Criteria) this;
        }

        public Criteria andVoteNumNotBetween(Integer value1, Integer value2) {
            addCriterion("vote_num not between", value1, value2, "voteNum");
            return (Criteria) this;
        }

        public Criteria andImgUrlIsNull() {
            addCriterion("img_url is null");
            return (Criteria) this;
        }

        public Criteria andImgUrlIsNotNull() {
            addCriterion("img_url is not null");
            return (Criteria) this;
        }

        public Criteria andImgUrlEqualTo(String value) {
            addCriterion("img_url =", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotEqualTo(String value) {
            addCriterion("img_url <>", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlGreaterThan(String value) {
            addCriterion("img_url >", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("img_url >=", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLessThan(String value) {
            addCriterion("img_url <", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLessThanOrEqualTo(String value) {
            addCriterion("img_url <=", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLike(String value) {
            addCriterion("img_url like", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotLike(String value) {
            addCriterion("img_url not like", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlIn(List<String> values) {
            addCriterion("img_url in", values, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotIn(List<String> values) {
            addCriterion("img_url not in", values, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlBetween(String value1, String value2) {
            addCriterion("img_url between", value1, value2, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotBetween(String value1, String value2) {
            addCriterion("img_url not between", value1, value2, "imgUrl");
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