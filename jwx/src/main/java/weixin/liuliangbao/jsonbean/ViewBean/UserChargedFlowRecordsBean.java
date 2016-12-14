package weixin.liuliangbao.jsonbean.ViewBean;

import java.util.List;

/**
 * Created by aa on 2015/12/16.
 */
public class UserChargedFlowRecordsBean  implements java.io.Serializable {
    /**
     * code : 200
     * message : 登陆成功
     * data : [{"InterfaceID":"XXXXXXXXXXXXXX","flowMealID":"XXXXXXXXXX","flowID":"XXXXXXXXXXX","ID":"XXXXXXXXXXXXXXXXXXX","userID":"XXXXXXXXXXXXXXXXXXXX","flowKeyNo":"201512141902123456","isCharged":"1","requestDate":"2015-12-14","disposeDate":"2015-12-14","phoneNumber":"15201015003","flowValue":"20M"},{"id":10012,"chargeTime":"2012-12-1112: 13","chargeDesc":"您每日签到，赠送10M"}]
     */

    private String code;
    private String message;
    /**
     * InterfaceID : XXXXXXXXXXXXXX
     * flowMealID : XXXXXXXXXX
     * flowID : XXXXXXXXXXX
     * ID : XXXXXXXXXXXXXXXXXXX
     * userID : XXXXXXXXXXXXXXXXXXXX
     * flowKeyNo : 201512141902123456
     * isCharged : 1
     * requestDate : 2015-12-14
     * disposeDate : 2015-12-14
     * phoneNumber : 15201015003
     * flowValue : 20M
     */

    private List<DataEntity> data;

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String flowMealID;
        private String flowID;
        private String ID;
        private String userID;
        private String isCharged;
        private String requestDate;
        private String disposeDate;
        private String phoneNumber;
        private String flowValue;
        private String chargeState;
        private String resultReason;
        private String productNo;
        private String getStatus;

        public String getGetStatus() {
            return getStatus;
        }

        public void setGetStatus(String getStatus) {
            this.getStatus = getStatus;
        }

        public void setFlowMealID(String flowMealID) {
            this.flowMealID = flowMealID;
        }

        public void setFlowID(String flowID) {
            this.flowID = flowID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public void setIsCharged(String isCharged) {
            this.isCharged = isCharged;
        }

        public void setRequestDate(String requestDate) {
            this.requestDate = requestDate;
        }

        public void setDisposeDate(String disposeDate) {
            this.disposeDate = disposeDate;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public void setFlowValue(String flowValue) {
            this.flowValue = flowValue;
        }

        public String getFlowMealID() {
            return flowMealID;
        }

        public String getFlowID() {
            return flowID;
        }

        public String getID() {
            return ID;
        }

        public String getUserID() {
            return userID;
        }

        public String getIsCharged() {
            return isCharged;
        }

        public String getRequestDate() {
            return requestDate;
        }

        public String getDisposeDate() {
            return disposeDate;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getFlowValue() {
            return flowValue;
        }

        public String getChargeState() {
            return chargeState;
        }

        public void setChargeState(String chargeState) {
            this.chargeState = chargeState;
        }

        public String getResultReason() {
            return resultReason;
        }

        public void setResultReason(String resultReason) {
            this.resultReason = resultReason;
        }

        public String getProductNo() {
            return productNo;
        }

        public void setProductNo(String productNo) {
            this.productNo = productNo;
        }
    }
}
