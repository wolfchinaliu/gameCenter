package weixin.liuliangbao.jsonbean;

import java.util.List;

/**
 * Created by aa on 2015/12/1.
 */
public class ProvinceBean  implements java.io.Serializable {

    /**
     * code : 200
     * message : 登陆成功
     * data : [{"provinceID":"10010","provinceName":"2012-12-1012: 13","provinceCode":"您参加大转盘，赠送20M","ProvinceAresCode":"您参加大转盘，赠送20M"}]
     */

    private String code;
    private String message;
    /**
     * provinceID : 10010
     * provinceName : 2012-12-1012: 13
     * provinceCode : 您参加大转盘，赠送20M
     * ProvinceAresCode : 您参加大转盘，赠送20M
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
        private String provinceID;
        private String provinceName;
        private String provinceCode;
        private String ProvinceAresCode;

        public void setProvinceID(String provinceID) {
            this.provinceID = provinceID;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public void setProvinceCode(String provinceCode) {
            this.provinceCode = provinceCode;
        }

        public void setProvinceAresCode(String ProvinceAresCode) {
            this.ProvinceAresCode = ProvinceAresCode;
        }

        public String getProvinceID() {
            return provinceID;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public String getProvinceCode() {
            return provinceCode;
        }

        public String getProvinceAresCode() {
            return ProvinceAresCode;
        }
    }
}
