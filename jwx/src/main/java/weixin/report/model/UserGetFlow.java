package weixin.report.model;

/**
 * Created by xiaona on 2016/3/10.
 * 用户充值到账记录查询
 */
public class UserGetFlow implements java.io.Serializable {
    private java.lang.String id;
//    private java.lang.String nickname;
    private java.lang.String flowType;
    //    private java.util.Date gettime;
    private java.lang.String gettime;
    private java.lang.Double flowValue;
    private java.lang.String phoneNumber;
    private java.lang.String phoneNumberLocation;
    private java.lang.String state;
    private java.lang.String accountId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public String getGettime() {
        return gettime;
    }

    public void setGettime(String gettime) {
        this.gettime = gettime;
    }

    public Double getFlowValue() {
        return flowValue;
    }

    public void setFlowValue(Double flowValue) {
        this.flowValue = flowValue;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumberLocation() {
        return phoneNumberLocation;
    }

    public void setPhoneNumberLocation(String phoneNumberLocation) {
        this.phoneNumberLocation = phoneNumberLocation;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
