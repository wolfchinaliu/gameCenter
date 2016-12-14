package weixin.gameCenter.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @Auth popl
 * @Date 2016年9月1日 下午5:20:27
 * @authEmail popl_lu@sian.cn
 * @CalssName weixin.gameCenter.entity.WeixinGameType
 * @dec 游戏类型
 */
@Entity
@Table(name = "weixin_game_type", schema = "")
@SuppressWarnings("serial")
public class WeixinGameTypeEntity implements java.io.Serializable{

	private String id;
	
	private String gameName;
	
	private String playUrl;
	
	private String editUrl;
	
	private Short status;
	
	private Date addTime;
	
	private String logoPath;
	
	private String accountId;
	
	
	@Column(name ="account_id",length=32)
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	@Column(name ="logo_path",length=255)
	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	@Column(name ="add_time")
	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
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
	@Column(name ="game_name",length=255)
	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	@Column(name ="play_url",length=255)
	public String getPlayUrl() {
		return playUrl;
	}

	public void setPlayUrl(String playUrl) {
		this.playUrl = playUrl;
	}
	@Column(name ="edit_url",length=255)
	public String getEditUrl() {
		return editUrl;
	}

	public void setEditUrl(String editUrl) {
		this.editUrl = editUrl;
	}
	@Column(name ="status")
	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}
//	@Column(name ="des_key",length=8)
//	public String getDesKey() {
//		return desKey;
//	}
//
//	public void setDesKey(String desKey) {
//		this.desKey = desKey;
//	}
	
	
}
