package weixin.util;

import java.security.NoSuchAlgorithmException;

import com.alibaba.fastjson.JSONObject;
import com.huawei.eidc.slee.security.Base64;
import com.huawei.eidc.slee.security.DESTools;
import com.huawei.eidc.slee.security.MD5;

import org.apache.log4j.Logger;
import weixin.liuliangbao.util.DESUtil;

/**
 * Created by aa on 2016/2/1.
 */
public class SecurityUtil {
    public static final transient Logger LOGGER = Logger.getLogger(SecurityUtil.class);
    /**
     * 对字符串进行加密 1. 数字签名保证信息完整性 2. 3DES加密保证不可阅读性 3. BASE64编码 Base64( 3DES( MD5(
     * 消息体 ) + 消息体)
     * 
     * @param str
     *            String
     * @return String
     * @throws NoSuchAlgorithmException
     * @throws Exception
     */
    public static String encrypt(String str, String desSecret) throws Exception {
        String body = null;

        DESTools des = DESTools.getInstance(desSecret);
        String md5str = MD5.md5(str);
        String enStr = md5str + str;
        byte[] b = des.encrypt(enStr.getBytes("UTF8"));
        body = Base64.encode(b);
        return body;
    }

    /**
     * 对字符串进行解密 Base64( 3DES( MD5( 消息体 ) + 消息体) 逆操作
     * 
     * @param str
     *            String
     * @return String 解密时判断数据签名，如果不匹配则返回null
     */
    public static String decrypt(String str, String desSecret) {
        String body = null;
        try {
            DESTools des = DESTools.getInstance(desSecret);
            byte[] b = Base64.decode(str);
            String md5body = new String(des.decrypt(b), "UTF8");
            if (md5body.length() < 32) {
                LOGGER.info("错误！消息体长度小于数字签名长度!");
                return null;
            }
            String md5Client = md5body.substring(0, 32);
            body = md5body.substring(32);
            String md5Server = MD5.md5(body);
            if (!md5Client.equals(md5Server)) {
                LOGGER.info("错误！数字签名不匹配:");
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return body;
    }

    public static void main(String[] args) throws Exception {
        String key = "932CF296";//9EE6FC1B
        JSONObject obj = new JSONObject();
        obj.put("acctId", "4028b88152627109015262a165640001");
        String body = obj.toString();
        String s = DESUtil.toHexString(DESUtil.encrypt(body, key)).toUpperCase();
        String deStr = DESUtil.decrypt(s, key);
//        String enStr = encrypt(body, key);
//
//        String deStr = decrypt(enStr, key);
        LOGGER.info(deStr);
    }

}
