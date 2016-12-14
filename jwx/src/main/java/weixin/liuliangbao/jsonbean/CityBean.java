package weixin.liuliangbao.jsonbean;

/**
 * Created by aa on 2015/12/1.
 */

import java.util.List;

/**
 * Created by aa on 2015/12/1.
 */
public class CityBean  implements java.io.Serializable {

    /**
     * code : 200
     * message : 登陆成功
     * data : [{"cityID":10010,"cityName":"2012-12-1012: 13","cityAresCode":"您参加大转盘，赠送20M","provinceID":"您参加大转盘，赠送20M","provinceCode":"您参加大转盘，赠送20M","cityCode":"您参加大转盘，赠送20M"}]
     */

    private String code;
    private String message;
    /**
     * cityID : 10010
     * cityName : 2012-12-1012: 13
     * cityAresCode : 您参加大转盘，赠送20M
     * provinceID : 您参加大转盘，赠送20M
     * provinceCode : 您参加大转盘，赠送20M
     * cityCode : 您参加大转盘，赠送20M
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
        private String cityID;
        private String cityName;
        private String cityAresCode;
        private String provinceID;
        private String provinceCode;
        private String cityCode;

        public void setCityID(String cityID) {
            this.cityID = cityID;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public void setCityAresCode(String cityAresCode) {
            this.cityAresCode = cityAresCode;
        }

        public void setProvinceID(String provinceID) {
            this.provinceID = provinceID;
        }

        public void setProvinceCode(String provinceCode) {
            this.provinceCode = provinceCode;
        }

        public void setCityCode(String cityCode) {
            this.cityCode = cityCode;
        }

        public String getCityID() {
            return cityID;
        }

        public String getCityName() {
            return cityName;
        }

        public String getCityAresCode() {
            return cityAresCode;
        }

        public String getProvinceID() {
            return provinceID;
        }

        public String getProvinceCode() {
            return provinceCode;
        }

        public String getCityCode() {
            return cityCode;
        }
    }
}

