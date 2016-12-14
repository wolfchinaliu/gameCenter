package weixin.liuliangbao.jsonbean.ViewBean;

import java.util.List;

/**
 * Created by aa on 2015/11/27.
 */
public class BusinessInterfaceUserBean  implements java.io.Serializable {
    /**
     * code : 200
     * message : 请求成功
     * data : [{"businessCode":86,"businessName":"赵彤","phoneNumber":"","InterfaceID":"","InterfaceName":"","flowValue":"30M","provinceCode":"15011275277","IsProvince":"1"}]
     */

    private int code;
    private String message;
    /**
     * businessCode : 86
     * businessName : 赵彤
     * phoneNumber :
     * InterfaceID :
     * InterfaceName :
     * flowValue : 30M
     * provinceCode : 15011275277
     * IsProvince : 1
     */

    private List<DataEntity> data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private int businessCode;
        private String businessName;
        private String phoneNumber;
        private String InterfaceID;
        private String InterfaceName;
        private String flowValue;
        private String provinceCode;
        private String IsProvince;

        public void setBusinessCode(int businessCode) {
            this.businessCode = businessCode;
        }

        public void setBusinessName(String businessName) {
            this.businessName = businessName;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public void setInterfaceID(String InterfaceID) {
            this.InterfaceID = InterfaceID;
        }

        public void setInterfaceName(String InterfaceName) {
            this.InterfaceName = InterfaceName;
        }

        public void setFlowValue(String flowValue) {
            this.flowValue = flowValue;
        }

        public void setProvinceCode(String provinceCode) {
            this.provinceCode = provinceCode;
        }

        public void setIsProvince(String IsProvince) {
            this.IsProvince = IsProvince;
        }

        public int getBusinessCode() {
            return businessCode;
        }

        public String getBusinessName() {
            return businessName;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getInterfaceID() {
            return InterfaceID;
        }

        public String getInterfaceName() {
            return InterfaceName;
        }

        public String getFlowValue() {
            return flowValue;
        }

        public String getProvinceCode() {
            return provinceCode;
        }

        public String getIsProvince() {
            return IsProvince;
        }
    }
}
