package weixin.source.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.util.Date;

/**
 * 微信链接-刘晓春-2015年12月16日
 */
@Entity
@Table(name = "weixin_url", schema = "")
@SuppressWarnings("serial")
public class WeixinUrlEntity implements java.io.Serializable {
	/**主键*/
	private String id;
	/**父Id*/
	private String parentid;
	/**链接地址*/
	private String url;
	/**链接值*/
	private String key;
	/**链接名称*/
	private String name;
	/**创建日期*/
	private Date addtime;


	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=36)
	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}


	@Column(name ="PARENTID",nullable=true,length=1000)
	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	@Column(name ="URL",nullable=true,length=1000)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name ="KEY",nullable=true,length=255)
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Column(name ="NAME",nullable=true,length=255)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name ="ADDTIME",nullable=true,length=36)
	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
}
