package weixin.activity.entity;
/**
 * @Auth popl
 * @Date 2016年7月28日 上午9:43:10
 * @authEmail popl_lu@sian.cn
 * @CalssName weixin.activity.entity.LotterRule
 * @dec 活动规则1 中奖类游戏规则
 */
public class LotterRule {

	private String name;
	
	private String value;
	
	private Integer probability;
	
	private Integer number;
	
	private Integer type;
	
	private String tempId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getProbability() {
		return probability;
	}

	public void setProbability(Integer probability) {
		this.probability = probability;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTempId() {
		return tempId;
	}

	public void setTempId(String tempId) {
		this.tempId = tempId;
	}
	
	
}
