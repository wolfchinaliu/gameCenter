package weixin.liuliangbao.util;

/**
 * Created by xiaochun on 2016/1/24.
 */
import org.apache.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;


public class DESUtil {
    public static final transient Logger LOGGER = Logger.getLogger(DESUtil.class);
    private byte[] desKey;


    //解密数据
    public static String decrypt(String message,String key) throws Exception {

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

    public static byte[] encrypt(String message, String key)
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


    public static void main(String[] args) throws Exception {
        String key = "r5dh6jk4";
        String value="flowKeyNumber=shiliuflowkeybankhrcrept&phoneNumber=15607916599&timeStamp=20160315182836228355";
        String jiami=java.net.URLEncoder.encode(value, "utf-8").toLowerCase();

        LOGGER.info("加密数据:"+jiami);
        String a=toHexString(encrypt(jiami, key)).toUpperCase();


        LOGGER.info("加密后的数据为:"+a);

//        String a="81AE6CF3D78ADF25B1F7A7D1A3698ACB96B46A51DF5FBE171D220F9BF2AA2237FF78A6702AEBEFDE9B00F5A7A5B87DD805075F628113DECD8E8551E89BA02638CC00C16B0206E7FDBCDA43E4DE4C201AB73090829C229C8A44BF706FF6A18335E45A2F7801E946BE82F7FBBF9CC6789F87E264FD385902430723EB33C33977F5453ED306FEB4965C4EEB2F1E0670EC3195609BF16A4D20380B465420BEBBA729A08F73F5291CB9E04923BA11092DA73DC49958EB5A002066";
//        String key="12345678";
        String b=java.net.URLDecoder.decode(decrypt(a,key), "utf-8") ;
        LOGGER.info("解密后的数据:"+b);

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

}