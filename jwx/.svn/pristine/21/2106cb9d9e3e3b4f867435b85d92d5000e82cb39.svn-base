package weixin.util;

/**
 * Created by GuoLiang on 2016/6/15 19:44.
 */
public class AvoidRepeatInfo {
    private String accountId;
    private String openId;
    private String shiliuOpenId;
    private String operateType;
    private String phoneNumber;

    public AvoidRepeatInfo(String openId, String accountId, String operateType) {
        this.accountId = accountId;
        this.openId = openId;
        this.operateType = operateType;
    }
    public AvoidRepeatInfo(String openId, String accountId, String operateType, String phoneNumber) {
        this.accountId = accountId;
        this.openId = openId;
        this.operateType = operateType;
        this.phoneNumber = phoneNumber;
    }

    public AvoidRepeatInfo(String accountId, String openId, String shiliuOpenId, String operateType, String phoneNumber) {
        this.accountId = accountId;
        this.openId = openId;
        this.shiliuOpenId = shiliuOpenId;
        this.operateType = operateType;
        this.phoneNumber = phoneNumber;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AvoidRepeatInfo that = (AvoidRepeatInfo) o;

        if (accountId != null ? !accountId.equals(that.accountId) : that.accountId != null) return false;
        if (openId != null ? !openId.equals(that.openId) : that.openId != null) return false;
        if (shiliuOpenId != null ? !shiliuOpenId.equals(that.shiliuOpenId) : that.shiliuOpenId != null) return false;
        if (operateType != null ? !operateType.equals(that.operateType) : that.operateType != null) return false;
        return phoneNumber != null ? phoneNumber.equals(that.phoneNumber) : that.phoneNumber == null;

    }

    @Override
    public int hashCode() {
        int result = accountId != null ? accountId.hashCode() : 0;
        result = 31 * result + (openId != null ? openId.hashCode() : 0);
        result = 31 * result + (shiliuOpenId != null ? shiliuOpenId.hashCode() : 0);
        result = 31 * result + (operateType != null ? operateType.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AvoidRepeatInfo{" +
                "accountId='" + accountId + '\'' +
                ", openId='" + openId + '\'' +
                ", shiliuOpenId='" + shiliuOpenId + '\'' +
                ", operateType='" + operateType + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
