package com.shiliu.game.domain;

import java.util.Date;

public class PlayRecord {

	private Long id;

    private String openid;

    private String nickName;

    private String gameId;

    private Short level;

    private String awardName;

    private Date palyTime;

    private String phoneNumber;
    
    public PlayRecord() {
		super();
	}
    
    public PlayRecord(String openid, String nickName, String gameId, String phoneNumber) {
		super();
		this.openid = openid;
		this.nickName = nickName;
		this.gameId = gameId;
		this.phoneNumber = phoneNumber;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId == null ? null : gameId.trim();
    }

    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName == null ? null : awardName.trim();
    }

    public Date getPalyTime() {
        return palyTime;
    }

    public void setPalyTime(Date palyTime) {
        this.palyTime = palyTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }
}