package com.shiliu.game.domain;

import java.util.Date;
import com.shiliu.game.utils.LongIdWorker;

public class UserGame {
    private Long userGameId = LongIdWorker.getDataId();

    private String phone;

    private String openid;

    private String gameId;

    private Integer flowNum;

    private Date playTime;

    private Date createTime;

    private Integer playTimes;

    private Integer totalTimes;

    private Integer holenFlow;

    private Integer maxScore;

    private Integer lastCycleScore;

    private Integer currentScore;

    private Boolean isUpdate;

    public Long getUserGameId() {
        return userGameId;
    }

    public void setUserGameId(Long userGameId) {
        this.userGameId = userGameId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId == null ? null : gameId.trim();
    }

    public Integer getFlowNum() {
        return flowNum;
    }

    public void setFlowNum(Integer flowNum) {
        this.flowNum = flowNum;
    }

    public Date getPlayTime() {
        return playTime;
    }

    public void setPlayTime(Date playTime) {
        this.playTime = playTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPlayTimes() {
        return playTimes;
    }

    public void setPlayTimes(Integer playTimes) {
        this.playTimes = playTimes;
    }

    public Integer getTotalTimes() {
        return totalTimes;
    }

    public void setTotalTimes(Integer totalTimes) {
        this.totalTimes = totalTimes;
    }

    public Integer getHolenFlow() {
        return holenFlow;
    }

    public void setHolenFlow(Integer holenFlow) {
        this.holenFlow = holenFlow;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }

    public Integer getLastCycleScore() {
        return lastCycleScore;
    }

    public void setLastCycleScore(Integer lastCycleScore) {
        this.lastCycleScore = lastCycleScore;
    }

    public Integer getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(Integer currentScore) {
        this.currentScore = currentScore;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }
}