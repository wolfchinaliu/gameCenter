package weixin.gameCenter.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @Auth popl
 * @Date 2016年9月21日 上午11:12:10
 * @authEmail popl_lu@sian.cn
 * @CalssName weixin.gameCenter.entity.WeixinGameFlowRecord
 * @dec 游戏流量领取/支出记录
 */
@Entity
@Table(name = "weixin_game_flow_rec", schema = "")
@SuppressWarnings("serial")
public class WeixinGameFlowRecordEntity {

	private String id;
	
	private String gameId;
	
	private String reqIP;
	
	private String flowValue;
	
	private Integer renCode;
	
	private Date reqTime;
	
	private String openId;

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=36)
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	@Column(name ="GAME_ID",length=36)
	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	@Column(name ="REQ_IP",length=15)
	public String getReqIP() {
		return reqIP;
	}

	public void setReqIP(String reqIP) {
		this.reqIP = reqIP;
	}
	@Column(name ="FLOW_VALUE",length=15)
	public String getFlowValue() {
		return flowValue;
	}

	public void setFlowValue(String flowValue) {
		this.flowValue = flowValue;
	}
	@Column(name ="REN_CODE",length=15)
	public Integer getRenCode() {
		return renCode;
	}
	
	public void setRenCode(Integer renCode) {
		this.renCode = renCode;
	}
	@Column(name ="REQ_TIME")
	public Date getReqTime() {
		return reqTime ;
	}

	public void setReqTime(Date reqTime) {
		this.reqTime = reqTime;
	}
	@Column(name ="open_id")
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	
}
