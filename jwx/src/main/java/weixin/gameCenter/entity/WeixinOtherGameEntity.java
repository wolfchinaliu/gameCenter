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
 * @Date 2016年9月5日 下午3:50:48
 * @authEmail popl_lu@sian.cn
 * @CalssName weixin.gameCenter.entity.WeixinOtherGameEntity
 * @dec 
 */
@Entity
@Table(name = "weixin_other_game", schema = "")
@SuppressWarnings("serial")
public class WeixinOtherGameEntity {

	private String id;
	
	private Date addTime;
	
	private Date startTime;
	
	private Date endTime;
	
	private String ruleId;
	
	private String accountId;
	
	private String gameType;

	private Short flowType;
	
	private String title;
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@Column(name ="add_time")
	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	@Column(name ="start_time")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	@Column(name ="end_time")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@Column(name ="rule_id",length=32)
	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	@Column(name ="account_id",length=32)
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String acountId) {
		this.accountId = acountId;
	}
	@Column(name ="game_type",length=32)
	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	@Column(name ="flow_type",length=5)
	public Short getFlowType() {
		return flowType;
	}

	public void setFlowType(Short flowType) {
		this.flowType = flowType;
	}
	@Column(name ="title",length=25)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
