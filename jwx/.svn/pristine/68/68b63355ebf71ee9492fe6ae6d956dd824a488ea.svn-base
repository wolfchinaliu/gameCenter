package weixin.liuliangbao.jsonbean;

import java.util.List;

/**
 * Created by xiaochun on 2015/11/24.
 */
public class MerchantBean  implements java.io.Serializable {
    /**
     * business_type : 服装
     * name : 化妆品
     * id : 16
     */

    /**
     * code : 200
     * message : 请求成功
     * data : [{"id":10010,"name":"2012-12-1012: 13","business_type":"您参加大转盘，赠送20M"}]
     */

    private String code;
    private String message;
    /**
     * id : 10010
     * name : 2012-12-1012: 13
     * business_type : 您参加大转盘，赠送20M
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
        private String id;
        private String name;
        private String business_type;

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setBusiness_type(String business_type) {
            this.business_type = business_type;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getBusiness_type() {
            return business_type;
        }
    }
}
