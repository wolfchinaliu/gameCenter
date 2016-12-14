package weixin.integrate.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "userflowaccount", schema = "")
@SuppressWarnings("serial")
public class UserFlowAccountEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String userID;
    private String userName;
    private String phoneNumber;
    private double countryFlowValue;
    private double provinceFlowValue;
    private String paymentpwd;//交易密码
    private Date createDate; //创建时间
    
    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name = "id", nullable = false, length = 36)
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public double getCountryFlowValue() {
        return countryFlowValue;
    }
    public void setCountryFlowValue(double countryFlowValue) {
        this.countryFlowValue = countryFlowValue;
    }
    public double getProvinceFlowValue() {
        return provinceFlowValue;
    }
    public void setProvinceFlowValue(double provinceFlowValue) {
        this.provinceFlowValue = provinceFlowValue;
    }
    public String getPaymentpwd() {
        return paymentpwd;
    }
    public void setPaymentpwd(String paymentpwd) {
        this.paymentpwd = paymentpwd;
    }
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
    
}
