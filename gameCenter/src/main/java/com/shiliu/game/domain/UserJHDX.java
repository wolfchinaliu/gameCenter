package com.shiliu.game.domain;

import com.shiliu.game.utils.LongIdWorker;

public class UserJHDX {
    private Long userid = LongIdWorker.getDataId();

    private String phone;

    private String uptodate;

    private String flux;

    private Integer total;

    private String status;

    private String gameid;
    
    public UserJHDX() {
		super();
	}
    
    public UserJHDX(String phone, String gameid) {
		super();
		this.phone = phone;
		this.gameid = gameid;
	}

	public UserJHDX(String phone, String uptodate, String flux, String gameid) {
		super();
		this.phone = phone;
		this.uptodate = uptodate;
		this.flux = flux;
		this.gameid = gameid;
	}

	public Long getUserid() {
        return userid;
    }

	public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getUptodate() {
        return uptodate;
    }

    public void setUptodate(String uptodate) {
        this.uptodate = uptodate;
    }

    public String getFlux() {
        return flux;
    }

    public void setFlux(String flux) {
        this.flux = flux == null ? null : flux.trim();
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGameid() {
        return gameid;
    }

    public void setGameid(String gameid) {
        this.gameid = gameid == null ? null : gameid.trim();
    }
}