package weixin.util;

public class DataDictionaryUtil {

    public enum MaterialStatus {
        nocommit("1", "未提交"), commit("2", "待审核"), audit_fail("3", "审核不通过"), audit_pass("4", "审核通过");

        MaterialStatus(String code, String name) {
            this.code = code;
            this.name = name;
        }

        private String name;
        private String code;

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }
    }

    public enum UrlType {
        inner("inner", "内部页面"), outer("outer", "外部链接");

        UrlType(String code, String name) {
            this.code = code;
            this.name = name;
        }

        private String name;
        private String code;

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }
    }

    public enum MaterialType {
        jd("1", "酒店"), cy("2", "餐饮"), yx("3", "游戏"), zq("4", "证券"), dc("5", "地产"), gw("6", "购物"), ly("7", "旅游"), yh("8", "银行"), jr("9", "金融"), qt("10", "其他");

        MaterialType(String code, String name) {
            this.code = code;
            this.name = name;
        }

        private String name;
        private String code;

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }
    }

    public enum AdPublishStatus {
        nopublish("1", "未发布"),publish("2", "发布中"),finish("3","发布结束"),active_terminate("4","主动终止"),passive_terminate("5","被动终止");

        AdPublishStatus(String code, String name) {
            this.code = code;
            this.name = name;
        }

        private String name;
        private String code;

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }
    }

    public enum AdPublishPosition{
        sign("1", "签到"),ggk("2", "刮刮卡"),zhuanpan("3","大转盘"),handshake("4","摇一摇"),weixinRedpacket("5","红包"),
        sixPin("9","六角拼拼");

        AdPublishPosition(String code, String name) {
            this.code = code;
            this.name = name;
        }

        private String name;
        private String code;

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }
    }
    
    public enum FlowType{
    	country("1","全国流量"),province("2","省内流量");
        FlowType(String code, String name) {
            this.code = code;
            this.name = name;
        }

        private String name;
        private String code;

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }
    }
    
    public enum MerchantFlowTradeType {
        charge("1", "商户充值"),fallback_flowcard("2","流量卡到期回收"),fallback_redpacket("3","红包到期回收"),fallback_riddle("4","字谜到期回收");
        MerchantFlowTradeType(String code, String name) {
            this.code = code;
            this.name = name;
        }

        private String name;
        private String code;

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }
    }
    
    public enum Level{
    	levela("1","A级"),levelb("2","B级"),levelc("3","C级");
    	Level(String code,String name){
    		 this.code = code;
             this.name = name;
    	}
    	private String name;
        private String code;

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }
        
    }

}
