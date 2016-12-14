package weixin.gameCenter.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @Auth popl
 * @Date 2016年9月24日 下午1:40:14
 * @authEmail popl_lu@sian.cn
 * @CalssName weixin.gameCenter.entity.WeixinGameSafeRuleEntity
 * @dec 游戏领取流量的安全规则
 */
@Entity
@Table(name = "weixin_game_saferule", schema = "")
@SuppressWarnings("serial")
public class WeixinGameSafeRuleEntity implements Serializable {

	private String id;
	
	private String accountId;
	
	private Integer maxFlow;
	
	private Integer everyoneTimes;
	
	private Integer maxTimes;
	
	private Short frequency;

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
	@Column(name ="ACCOUNT_ID",length=36)
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	@Column(name ="MAX_FLOW",length=11)
	public Integer getMaxFlow() {
		return maxFlow;
	}

	public void setMaxFlow(Integer maxFlow) {
		this.maxFlow = maxFlow;
	}
	@Column(name ="EVERYONE_TIMES",length=11)
	public Integer getEveryoneTimes() {
		return everyoneTimes;
	}

	public void setEveryoneTimes(Integer everyoneTimes) {
		this.everyoneTimes = everyoneTimes;
	}
	@Column(name ="MAX_TIMES",length=11)
	public Integer getMaxTimes() {
		return maxTimes;
	}

	public void setMaxTimes(Integer maxTimes) {
		this.maxTimes = maxTimes;
	}
	@Column(name ="FREQUENCY",length=36)
	public Short getFrequency() {
		return frequency;
	}

	public void setFrequency(Short frequency) {
		this.frequency = frequency;
	}
	
}
