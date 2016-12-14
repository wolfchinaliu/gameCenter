package weixin.activity.entity;
/**
 * @Auth popl
 * @Date 2016年8月6日 上午10:36:50
 * @authEmail popl_lu@sian.cn
 * @CalssName weixin.activity.entity.QuestionRule
 * @dec 
 */
public class QuestionRule {

	//每题流量值
	private Integer oneFlow;
	//全对流量值
	private Integer allFlow;
	//
	private Integer partFlow;
	//每天得奖人数
	private Integer evenNumber;
	public Integer getOneFlow() {
		return oneFlow;
	}
	public void setOneFlow(Integer oneFlow) {
		this.oneFlow = oneFlow;
	}
	public Integer getAllFlow() {
		return allFlow;
	}
	public void setAllFlow(Integer allFlow) {
		this.allFlow = allFlow;
	}
	public Integer getPartFlow() {
		return partFlow;
	}
	public void setPartFlow(Integer partFlow) {
		this.partFlow = partFlow;
	}
	public Integer getEvenNumber() {
		return evenNumber;
	}
	public void setEvenNumber(Integer evenNumber) {
		this.evenNumber = evenNumber;
	}
	
	
}
