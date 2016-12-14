package weixin.guanjia.account.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import sun.font.TrueTypeFont;

/**
 * @author wangpeng
 * @version V1.0
 * @Title: Entity
 * @Description: 公共号个性化管理
 * @date 2016-09-29 10:56:00
 */
@Entity
@Table(name = "weixinIndividualization", schema = "")
@SuppressWarnings("serial")
public class WeixinIndividualizationEntity implements java.io.Serializable {

	/**
	 * 主键
	 */
	private java.lang.String id;
	
	/**
	 * 商户id
	 */
	private java.lang.String acctId;
	
	/**
	 * 名称
	 */
	private java.lang.String name;
	

	/**
	 * 公众logo
	 */
	private java.lang.String logo;

	/**
	 * 修改时间
	 * @return
	 */
	private Date createTime;
	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "ID", nullable = false, length = 36)
	public java.lang.String getId() {
		return id;
	}

	
	public void setId(java.lang.String id) {
		this.id = id;
	}

	


	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getLogo() {
		return logo;
	}

	public void setLogo(java.lang.String logo) {
		this.logo = logo;
	}
	

	public java.lang.String getAcctId() {
		return acctId;
	}

	public void setAcctId(java.lang.String acctId) {
		this.acctId = acctId;
	}

	

	
	
	
}
