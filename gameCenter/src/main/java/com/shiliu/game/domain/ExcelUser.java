package com.shiliu.game.domain;

import java.util.UUID;

public class ExcelUser {
    private String euId = UUID.randomUUID().toString().replace("-", "");

    private String lab1;

    private String lab2;

    private String lab3;

    private String state="3";//值默认为3，表示未充值,2：表示：充值成功但是要等待48小时1：表示已经充值，

    private String rechargeTime;

    private String gameId;
    
    public ExcelUser() {

	}
    
	public ExcelUser(String euId, String lab1, String lab2, String lab3, String state, String rechargeTime,
			String gameId) {
		this.euId = euId;
		this.lab1 = lab1;
		this.lab2 = lab2;
		this.lab3 = lab3;
		this.state = state;
		this.rechargeTime = rechargeTime;
		this.gameId = gameId;
	}

	public String getEuId() {
        return euId;
    }

    public void setEuId(String euId) {
        this.euId = euId == null ? null : euId.trim();
    }

    public String getLab1() {
        return lab1;
    }

    public void setLab1(String lab1) {
        this.lab1 = lab1 == null ? null : lab1.trim();
    }

    public String getLab2() {
        return lab2;
    }

    public void setLab2(String lab2) {
        this.lab2 = lab2 == null ? null : lab2.trim();
    }

    public String getLab3() {
        return lab3;
    }

    public void setLab3(String lab3) {
        this.lab3 = lab3 == null ? null : lab3.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getRechargeTime() {
    	if(this.rechargeTime!=null && this.rechargeTime.indexOf(".")!=-1){
    		rechargeTime=this.rechargeTime.substring(0, rechargeTime.lastIndexOf("."));
    	}
        return rechargeTime;
    }

    public void setRechargeTime(String rechargeTime) {
        this.rechargeTime = rechargeTime;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId == null ? null : gameId.trim();
    }

	@Override
	public String toString() {
		return "ExcelUser [euId=" + euId + ", lab1=" + lab1 + ", lab2=" + lab2 + ", lab3=" + lab3 + ", state=" + state
				+ ", rechargeTime=" + rechargeTime + ", openId=" + gameId + "]";
	}
    
}