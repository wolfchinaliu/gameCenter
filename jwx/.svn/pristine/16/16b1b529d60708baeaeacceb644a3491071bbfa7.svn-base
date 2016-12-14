package weixin.memberStructure.view;

import java.math.BigInteger;

import org.apache.commons.lang.StringUtils;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * Created by Wangpeng on 2016/10/18.
 * 
 */
public class SubMemberStatBean implements java.io.Serializable {
	@Excel(exportName="日期")
    private java.lang.String created;
	@Excel(exportName="商户名称",exportFieldWidth=15)
    private java.lang.String subAcctForName;
	@Excel(exportName="商户等级",exportConvertSign=1)
    private java.lang.String acctLevel;
	@Excel(exportName="所属商户",exportFieldWidth=15)
    private java.lang.String acctForName;
	@Excel(exportName="新增粉丝人数",exportFieldWidth=18)
	private BigInteger addCount;
	@Excel(exportName="取消关注粉丝人数",exportFieldWidth=20)
	private BigInteger cancelCount;
	@Excel(exportName="净增粉丝人数",exportFieldWidth=18)
	private BigInteger netCount;
	@Excel(exportName="当日绑定粉丝",exportFieldWidth=18)
	private BigInteger bindedCount;
	@Excel(exportName="累计关注人数",exportFieldWidth=18)
	private BigInteger subscribeCount;
	
	public String convertGetAcctLevel(){
		if(StringUtils.equals("0", acctLevel)){
			return "S级";
		}
		if(StringUtils.equals("1", acctLevel)){
			return "A级";
		}
		if(StringUtils.equals("2", acctLevel)){
			return "B级";
		}
		if(StringUtils.equals("3", acctLevel)){
			return "c级";
		}
		return acctLevel;
	}
	
	public BigInteger getCancelCount() {
		return cancelCount;
	}
	
	public void setCancelCount(BigInteger cancelCount) {
		this.cancelCount = cancelCount;
	}
	public java.lang.String getCreated() {
		return created;
	}
	public void setCreated(java.lang.String created) {
		this.created = created;
	}
	public java.lang.String getSubAcctForName() {
		return subAcctForName;
	}
	public void setSubAcctForName(java.lang.String subAcctForName) {
		this.subAcctForName = subAcctForName;
	}
	public java.lang.String getAcctLevel() {
		return acctLevel;
	}
	public void setAcctLevel(java.lang.String acctLevel) {
		this.acctLevel = acctLevel;
	}
	public java.lang.String getAcctForName() {
		return acctForName;
	}
	public void setAcctForName(java.lang.String acctForName) {
		this.acctForName = acctForName;
	}
	public BigInteger getAddCount() {
		return addCount;
	}
	public void setAddCount(BigInteger addCount) {
		this.addCount = addCount;
	}
	public BigInteger getNetCount() {
		return netCount;
	}
	public void setNetCount(BigInteger netCount) {
		this.netCount = netCount;
	}
	public BigInteger getBindedCount() {
		return bindedCount;
	}
	public void setBindedCount(BigInteger bindedCount) {
		this.bindedCount = bindedCount;
	}
	public BigInteger getSubscribeCount() {
		return subscribeCount;
	}
	public void setSubscribeCount(BigInteger subscribeCount) {
		this.subscribeCount = subscribeCount;
	}
    
	
}
