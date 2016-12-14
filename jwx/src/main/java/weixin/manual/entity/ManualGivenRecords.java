package weixin.manual.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @author Sukin
 * 2016年9月5日
 */
@Entity
@Table(name = "manualgivenrecords", schema = "")
@SuppressWarnings("serial")
public class ManualGivenRecords implements Serializable {
		 
		 @Id
		 private String id; //id
		 @Excel(exportName="批次")
		 private String patchNo; // 批次号
		 @Excel(exportName="流量值")
		 private Double flowValue; // 流量值
		 @Excel(exportName="流量类型")
		 private String flowType; // 流量类型
		 @Excel(exportName="赠送时间")
		 private Date givenTime; // 赠送时间
		 @Excel(exportName=" 手机号")
		 private String phoneNum; // 手机号
		 
		 private Date createDate; // 创建时间 
		 @Excel(exportName=" 结果")
		 private String result;// 结果 成功1 失败2 预定3 
		 @Excel(exportName=" 原因")
		 private String reason;// 原因 赠送成功1;赠送失败,商户余额不足2;赠送失败，商家取消赠送3;未到赠送预定时间4;手机不合法5;不在覆盖区域6;黑名单7;
		 
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
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
		public Date getGivenTime() {
			return givenTime;
		}
		public void setGivenTime(Date givenTime) {
			this.givenTime = givenTime;
		}
		public String getPhoneNum() {
			return phoneNum;
		}
		public void setPhoneNum(String phoneNum) {
			this.phoneNum = phoneNum;
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
	

}
