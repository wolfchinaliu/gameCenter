package weixin.manual.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Columns;

/**
 * @author Sukin
 * 2016年9月5日
 */
@Entity
@Table(name = "manualgiven", schema = "")
@SuppressWarnings("serial")
public class ManualGiven implements Serializable {
	    @Id
		private String id;    //id
		private String accountid; //商户id
		private String patchNo; //批次号
		private Double flowValue; //流量值
		private String flowType; //流量类型
		private Integer isNow; //是否立即赠送 立即赠送1 预定时间赠送2
		private Date givenTime; //赠送时间
		private Date createDate; //创建时间
		private String result; //结果 成功1 失败2 预定3 设置时间过长0
		private String reason; //原因 商户余额不足
		private String fileName; //上传的文件名
		private Double totalFlow; // 总流量
		private String des; // 描述
		private String getWays;//获取方式
		private String state; //状态:正在处理0，处理完毕1,还未开始2
		private Integer totalnum;//总条数
		private Integer sucnum;//成功的条数
		
		public Integer getTotalnum() {
			return totalnum;
		}
		public void setTotalnum(Integer totalnum) {
			this.totalnum = totalnum;
		}
		public Integer getSucnum() {
			return sucnum;
		}
		public void setSucnum(Integer sucnum) {
			this.sucnum = sucnum;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getGetWays() {
			return getWays;
		}
		public void setGetWays(String getWays) {
			this.getWays = getWays;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getAccountid() {
			return accountid;
		}
		public void setAccountid(String accountid) {
			this.accountid = accountid;
		}
		public String getPatchNo() {
			return patchNo;
		}
		public void setPatchNo(String patchNo) {
			this.patchNo = patchNo;
		}
		public Double getFlowValue() {
			return flowValue;
		}
		public void setFlowValue(Double flowValue) {
			this.flowValue = flowValue;
		}
		public String getFlowType() {
			return flowType;
		}
		public void setFlowType(String flowType) {
			this.flowType = flowType;
		}
		public Integer getIsNow() {
			return isNow;
		}
		public void setIsNow(Integer isNow) {
			this.isNow = isNow;
		}
		public Date getGivenTime() {
			return givenTime;
		}
		public void setGivenTime(Date givenTime) {
			this.givenTime = givenTime;
		}
		public Date getCreateDate() {
			return createDate;
		}
		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}
		public String getResult() {
			return result;
		}
		public void setResult(String result) {
			this.result = result;
		}
		public String getReason() {
			return reason;
		}
		public void setReason(String reason) {
			this.reason = reason;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public Double getTotalFlow() {
			return totalFlow;
		}
		public void setTotalFlow(Double totalFlow) {
			this.totalFlow = totalFlow;
		}
		public String getDes() {
			return des;
		}
		public void setDes(String des) {
			this.des = des;
		}
		
}
