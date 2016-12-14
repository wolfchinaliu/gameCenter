package weixin.tenant.service.impl;

/**
 * Created by 晓春 on 2016/3/3.
 */
public class GetId {
    //初始化一个秘钥24位
    public static String randomStr(int len) {
        if (len == 0) {
            return "";
        }
        int a = (int) (Math.random() * 3);
        if (a == 0) {
            return ((int) (Math.random() * 10)) + randomStr(len - 1);
        } else if (a == 1) {
            return ((char) ((int) (Math.random() * 26) + 65)) + randomStr(len - 1);
        } else {
            return ((char) ((int) (Math.random() * 26) + 97)) + randomStr(len - 1);
        }
    }
}
