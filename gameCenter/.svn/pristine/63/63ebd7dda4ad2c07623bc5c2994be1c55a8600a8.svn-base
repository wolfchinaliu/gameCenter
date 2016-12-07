package com.shiliu.game.domain;

import com.shiliu.game.utils.LongIdWorker;

public class Poll {
    private String pollId;

    private String appName;

    private String phone;

    public String getPollId() {
        return Long.toString(LongIdWorker.getDataId());
    }

    public void setPollId(String pollId) {
        this.pollId = pollId == null ? null : pollId.trim();
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }
}