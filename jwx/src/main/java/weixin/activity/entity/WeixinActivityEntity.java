package weixin.activity.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @Auth popl
 * @Date 2016年7月28日 上午9:06:36
 * @authEmail popl_lu@sian.cn
 * @CalssName weixin.activity.entity.WeixinActivityEntity
 * @dec 活动实体
 */
@Entity
@Table(name = "weixin_activity", schema = "")
@SuppressWarnings("serial")
public class WeixinActivityEntity {
	
	private String id;
	
	private String title;
	
	private String createName;
	
	private String description;
	
	private Date createTime;
	
	private Date startTime;
	
	private Date endTime;
	
	private Integer frequency;
	
	private Integer evenNumber;
	
	private String flowType;
	
	private Integer type;
	
	private String activityRule;
	
	private String preDraw;
	
	private String flowDraw;
	
	private String noDraw;
	
	private String accountid;
	
	private Integer totalNumber;
	
	private String timePart;
	
	private String imagePath;
	
	private String activityUrl;

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
	@Column(name ="TITLE",nullable=true,length=50)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}
	@Column(name ="DESCRIPTION",nullable=true,length=1000)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name ="CREATE_TIME",nullable=true,length=20)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name ="START_TIME",nullable=true,length=20)
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	@Column(name ="END_TIME",nullable=true,length=20)
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@Column(name ="FREQUENCY",nullable=true,length=4)
	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}
	@Column(name ="EVEN_NUMBER",nullable=true,length=4)
	public Integer getEvenNumber() {
		return evenNumber;
	}

	public void setEvenNumber(Integer evenNumber) {
		this.evenNumber = evenNumber;
	}
	@Column(name ="ACTIVITY_TYPE",nullable=true,length=4)
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	@Column(name ="ACTIVITY_RULE",nullable=true,length=500)
	public String getActivityRule() {
		return activityRule;
	}

	public void setActivityRule(String activityRule) {
		this.activityRule = activityRule;
	}
	@Column(name ="PRE_DRAW",nullable=true,length=300)
	public String getPreDraw() {
		return preDraw;
	}

	public void setPreDraw(String preDraw) {
		this.preDraw = preDraw;
	}
	@Column(name ="FLOW_DRAW",nullable=true,length=300)
	public String getFlowDraw() {
		return flowDraw;
	}

	public void setFlowDraw(String flowDraw) {
		this.flowDraw = flowDraw;
	}
	@Column(name ="NO_DRAW",nullable=true,length=200)
	public String getNoDraw() {
		return noDraw;
	}

	public void setNoDraw(String noDraw) {
		this.noDraw = noDraw;
	}
	@Column(name ="ACCOUNTID",nullable=true,length=32)
	public String getAccountid(){
		return this.accountid;
	}

	public void setAccountid(String accountid){
		this.accountid = accountid;
	}
	@Column(name ="FLOW_TYPE",nullable=true,length=32)
	public String getFlowType() {
		return flowType;
	}

	public void setFlowType(String flowType) {
		this.flowType = flowType;
	}
	@Column(name ="TOTAL_NUMBER",nullable=true,length=11)
	public Integer getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}
	@Column(name ="TIME_PART",nullable=true,length=255)
	public String getTimePart() {
		return timePart;
	}

	public void setTimePart(String timePart) {
		this.timePart = timePart;
	}
	@Column(name ="image_path",nullable=true,length=255)
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	@Column(name ="activity_url",nullable=true,length=255)
	public String getActivityUrl() {
		return activityUrl;
	}

	public void setActivityUrl(String activityUrl) {
		this.activityUrl = activityUrl;
	}
	
	
}
