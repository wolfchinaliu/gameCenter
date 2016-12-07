package com.shiliu.game.domain;

import java.util.Date;

public class UserFlowLog extends UserFlowLogKey {
    private Integer flow;

    private Date addTime;

    private Integer code;

    public Integer getFlow() {
        return flow;
    }

    public void setFlow(Integer flow) {
        this.flow = flow;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}