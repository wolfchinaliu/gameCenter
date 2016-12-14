package weixin.personalredpacket.entity;


/**
 * Created by xiaochun on 2016/1/27.
 */

public class MoreRedpacketEntity  implements java.io.Serializable {
    /**红包设置主键*/
    private String id;
    /**商户头像*/
    private String logoAccount;
    /**商户名称*/
    private String accountname;
    /**流量类型*/
    private String flowtype;
    /**补贴流量*/
    private String subsidyValue;
    /**累计发放流量*/
    private String flowSendValue;
    /**剩余流量*/
    private String leftflow;
    /**商户覆盖省*/
    private String provincename;
    /**商户ID*/
    private String accountId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogoAccount() {
        return logoAccount;
    }

    public void setLogoAccount(String logAccount) {
        this.logoAccount = logAccount;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public String getFlowtype() {
        return flowtype;
    }

    public void setFlowtype(String flowtype) {
        this.flowtype = flowtype;
    }

    public String getSubsidyValue() {
        return subsidyValue;
    }

    public void setSubsidyValue(String subsidyValue) {
        this.subsidyValue = subsidyValue;
    }

    public String getFlowSendValue() {
        return flowSendValue;
    }

    public void setFlowSendValue(String flowSendValue) {
        this.flowSendValue = flowSendValue;
    }

    public String getLeftflow() {
        return leftflow;
    }

    public void setLeftflow(String leftflow) {
        this.leftflow = leftflow;
    }

    public String getProvincename() {
        return provincename;
    }

    public void setProvincename(String provincename) {
        this.provincename = provincename;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
