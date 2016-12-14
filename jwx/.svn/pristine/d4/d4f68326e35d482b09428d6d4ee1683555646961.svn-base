package weixin.guanjia.message.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.jeecgframework.core.common.entity.IdEntity;

@Entity
@Table(name = "weixin_autoresponse_default")
public class AutoResponseDefault extends IdEntity {

	private String templateName;
	private String templateId;
	private String msgType;
	private String addTime;
	private String accountid;

	@Column(name = "accountid",length=36)
	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	@Column(name = "templatename")
	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	@Column(name = "templateid")
	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	@Column(name = "msgtype")
	public String getMsgType() {
		return msgType;
	}

	@Column(name = "addtime")
	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

}
