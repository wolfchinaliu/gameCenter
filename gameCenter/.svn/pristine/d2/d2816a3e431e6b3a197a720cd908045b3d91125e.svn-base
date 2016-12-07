package com.shiliu.game.utils;

/** 
* @author popl 
* @version 1.0
* @createDate 2016年5月24日 上午10:15:56 
* @description
*/

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Key;
import java.security.MessageDigest;

/**
 * Created by xiaochun on 2016/1/24.
 */
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.alibaba.fastjson.JSONObject;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


@SuppressWarnings("restriction")
public class EncryptUtil {


    //解密数据
    public static String DESdecrypt(String message,String key) throws Exception {

        byte[] bytesrc =convertHexString(message);
        Cipher   cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));

        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);

        byte[] retByte = cipher.doFinal(bytesrc);
        return new String(retByte);
    }

    public static byte[] DESencrypt(String message, String key)
            throws Exception {
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

        return cipher.doFinal(message.getBytes("UTF-8"));
    }

    public static byte[] convertHexString(String ss)
    {
        byte digest[] = new byte[ss.length() / 2];
        for(int i = 0; i < digest.length; i++)
        {
            String byteString = ss.substring(2 * i, 2 * i + 2);
            int byteValue = Integer.parseInt(byteString, 16);
            digest[i] = (byte)byteValue;
        }

        return digest;
    }

    public static String DESencryptToString(String message, String key) throws Exception{
    	 Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

         DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));

         SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
         SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
         IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
         cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
         String messageURLEncoder = URLEncoder.encode(message, "UTF-8");
         
         
         return toHexString(cipher.doFinal(messageURLEncoder.getBytes("UTF-8")));
    }

    public static String toHexString(byte b[]) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String plainText = Integer.toHexString(0xff & b[i]);
            if (plainText.length() < 2)
                plainText = "0" + plainText;
            hexString.append(plainText);
        }

        return hexString.toString();
    }

    public static String getBase64(String str) {  
        byte[] b = null;  
        String s = null;  
        try {  
            b = str.getBytes("utf-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        if (b != null) {  
            s = new BASE64Encoder().encode(b);  
        }  
        return s;  
    }  
  
    // 解密  
    public static String getFromBase64(String s) {  
        byte[] b = null;  
        String result = null;  
        if (s != null) {  
            BASE64Decoder decoder = new BASE64Decoder();  
            try {  
                b = decoder.decodeBuffer(s);  
                result = new String(b, "utf-8");  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return result;  
    }  
    public static String SHA1Encrypt(String strSrc) throws Exception{
		MessageDigest md = null;
		String strDes = null;

		byte[] bt = strSrc.getBytes();
		md = MessageDigest.getInstance("SHA-1");
		md.update(bt);
		strDes = toHexString(md.digest()); // to HexString
		return strDes;
	}

    public static void main(String[] args) throws Exception {
        String key = ";'/<>,.`";
        JSONObject obj = new JSONObject();
        obj.put("acctId", "abc");
        obj.put("openId", "测试！@#￥%……&*（）!@#$%^&*()\\.");
        String jiami=java.net.URLEncoder.encode(obj.toString(), "UTF-8").toLowerCase();
        System.out.println("加密数据:"+jiami);
        String a=DESencryptToString(obj.toJSONString(), key).toUpperCase();
//        String b1 = DESdecrypt("jiami", key);
       // System.out.println(b1);

        System.out.println("加密后的数据为:"+a);
        System.out.println(SHA1Encrypt("jsapi_ticket=sM4AOVdWfPE4DxkXGEs8VMCPGGVi4C3VM0P37wVUCFvkVAy_90u5h9nbSlYy3-Sl-HhTdfl2fzFy1AOcHKP7qg&noncestr=Wm3WZYTPz0wzccnW&timestamp=1414587457&url=http://mp.weixin.qq.com?params=value"));
//        String a="81AE6CF3D78ADF25B1F7A7D1A3698ACB96B46A51DF5FBE171D220F9BF2AA2237FF78A6702AEBEFDE9B00F5A7A5B87DD805075F628113DECD8E8551E89BA02638CC00C16B0206E7FDBCDA43E4DE4C201AB73090829C229C8A44BF706FF6A18335E45A2F7801E946BE82F7FBBF9CC6789F87E264FD385902430723EB33C33977F5453ED306FEB4965C4EEB2F1E0670EC3195609BF16A4D20380B465420BEBBA729A08F73F5291CB9E04923BA11092DA73DC49958EB5A002066";
//        String key="12345678";
        String b=java.net.URLDecoder.decode(DESdecrypt(a,key), "UTF-8") ;
        System.out.println("解密后的数据:"+b);

    }
}
