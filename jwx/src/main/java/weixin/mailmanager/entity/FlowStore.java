package weixin.mailmanager.entity;

/**
 * Created by 晓春 on 2016/4/28.
 */
public class FlowStore {
    private java.lang.String flowType;
    private java.lang.String businessName;
    private java.lang.Double flow;
    private java.lang.Double flowCountry;
    private java.lang.Double flowProvince;
    private java.lang.Double flowTotal;
    private java.lang.String accountName;
    private java.lang.String accountId;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Double getFlow() {
        return flow;
    }

    public void setFlow(Double flow) {
        this.flow = flow;
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Double getFlowCountry() {
        return flowCountry;
    }

    public void setFlowCountry(Double flowCountry) {
        this.flowCountry = flowCountry;
    }

    public Double getFlowProvince() {
        return flowProvince;
    }

    public void setFlowProvince(Double flowProvince) {
        this.flowProvince = flowProvince;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Double getFlowTotal() {
        return flowTotal;
    }

    public void setFlowTotal(Double flowTotal) {
        this.flowTotal = flowTotal;
    }
}
