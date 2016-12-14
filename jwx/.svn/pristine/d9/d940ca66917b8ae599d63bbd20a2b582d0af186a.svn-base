package weixin.activity.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * @Auth popl
 * @Date 2016年8月6日 下午10:06:33
 * @authEmail popl_lu@sian.cn
 * @CalssName weixin.activity.entity.WeixinActivityQuestion
 * @dec 
 */
@Entity
@Table(name = "WEIXIN_ACTIVITY_QUESTION", schema = "")
@SuppressWarnings("serial")
public class WeixinActivityQuestionEntity {

	private String id;
	
	private String activityId;
	
	private String questionText;
	
	private String questionOption;
	
	private Integer serial;
	
	private String answer;
	
	private String image;
	
	private Date createTime;
	
	private List<Map<Object,Object>> options;

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "QUESTION_ID", nullable = false, length = 50)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "ACTIVITY_ID", nullable = false, length = 50)
	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	@Column(name = "QUESTION_TEXT",  length = 500)
	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	@Column(name = "QUESTION_OPTION", length = 1000)
	public String getQuestionOption() {
		return questionOption;
	}

	public void setQuestionOption(String questionOption) {
		this.questionOption = questionOption;
	}

	@Column(name = "QUESTION_SERIAL", length = 100)
	public Integer getSerial() {
		return serial;
	}

	public void setSerial(Integer serial) {
		this.serial = serial;
	}

	@Column(name = "QUESTION_ANSWER", length = 100)
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Column(name = "QUESTION_IMAGE", length = 100)
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Transient
	public List<Map<Object, Object>> getOptions() {
		return options;
	}

	public void setOptions(List<Map<Object, Object>> options) {
		this.options = options;
	}
	
}
