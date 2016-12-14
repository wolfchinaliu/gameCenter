package weixin.memberStructure.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Title: Entity
 * @Description: 粉丝统计表
 * @author WangPeng
 * @date 2016-11-03 17:37:26
 *
 */


@Entity
@Table(name = "member_stat", schema = "")
@SuppressWarnings("serial")

public class MemberStatEntity implements Serializable {
	private String id; 
	private String accountId;
	private String acctId;
	@Excel(exportName="取消关注粉丝人数",exportFieldWidth=18)
	private BigInteger cancelCount;
	@Excel(exportName="新增粉丝人数",exportFieldWidth=14)
	private BigInteger addCount;
	@Excel(exportName="净增粉丝人数",exportFieldWidth=14)
	private BigInteger netCount;
	@Excel(exportName="当日绑定粉丝",exportFieldWidth=14)
	private BigInteger bindedCount;
	@Excel(exportName="累计关注人数",exportFieldWidth=14)
	private BigInteger subscribeCount;
	@Excel(exportName="日期",exportFieldWidth=18,exportFormat="yyyy-MM-dd")
	private Date created;
	
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
	@Column(name = "account_id", nullable = true, length = 100)
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	@Column(name = "cancel_count", nullable = true, length = 20)
	public BigInteger getCancelCount() {
		return cancelCount;
	}
	public void setCancelCount(BigInteger cancelCount) {
		this.cancelCount = cancelCount;
	}
	@Column(name = "add_count", nullable = true, length = 20)
	public BigInteger getAddCount() {
		return addCount;
	}
	public void setAddCount(BigInteger addCount) {
		this.addCount = addCount;
	}
	@Column(name = "net_count", nullable = true, length = 20)
	public BigInteger getNetCount() {
		return netCount;
	}
	public void setNetCount(BigInteger netCount) {
		this.netCount = netCount;
	}
	@Column(name = "acct_id", nullable = true, length = 100)
	public String getAcctId() {
		return acctId;
	}
	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}

	@Column(name = "binded_count", nullable = true, length = 20)
	public BigInteger getBindedCount() {
		return bindedCount;
	}
	public void setBindedCount(BigInteger bindedCount) {
		this.bindedCount = bindedCount;
	}
	@Column(name = "subscribe_count", nullable = true, length = 20)
	public BigInteger getSubscribeCount() {
		return subscribeCount;
	}
	public void setSubscribeCount(BigInteger subscribeCount) {
		this.subscribeCount = subscribeCount;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	
}