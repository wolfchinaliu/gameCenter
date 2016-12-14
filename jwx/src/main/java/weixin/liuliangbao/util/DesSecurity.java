package weixin.liuliangbao.util;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

/**
 * Created by aa on 2016/3/1.
 */
public class DesSecurity {
    public static final transient Logger LOGGER = Logger.getLogger(DesSecurity.class);
    private final static String Algorithm = "DESede/ECB/PKCS5Padding";

    private static final String KEY_ALGORITHM = "DESede";

    public static void main(String[] args) throws Exception {
        /*String key = "weishequceshiaeszifuchua";
        String value = "USqaZoMKvfHuTea*Tfz8Ifyd2FyqS2Ey7QqxzPD4Ds:LiGh66gRrDzrShD73bcWx2ZvqUXvW7NpIx7lpVHD:nP*EuNYCf1xBeB8Z9sSwnqqr7zgi:65uYfw4HG2PGbfN";
        */
        String key = "78901cbc9803cfee18097040";
        String value = "zgKJX0Fgo4qZ/bdcPBdYuAxy3d9/j0Hji3rppl03sAhLfSBJqdve+JjbxDx7Lbp/n71h3Mnkmnu6QGhfQyx07aqSNYob0ZwUrC3zbaqkwvNeolKLnqFsi8rY6DNlWorJSQfC5x5cEVC5gePB5K0W3vWvnDVDvgbBg46rXNwaaJkw7i/bIQZm3uBllBs3OBvj";
        DesSecurity du = new DesSecurity();
        String values = du.decrypt(key, value);
        LOGGER.info("-----------" + values);

    }

    public String decrypt(String key, String strVal) {
        SecretKey securekey = null;

        Security.addProvider(new com.sun.crypto.provider.SunJCE());

        try {
            DESedeKeySpec dks = new DESedeKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);

            securekey = keyFactory.generateSecret(dks);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Cipher cipher;
        String bindParam = null;

        try {
            strVal = replaceStr(strVal);
            cipher = Cipher.getInstance(Algorithm);
            cipher.init(Cipher.DECRYPT_MODE, securekey);

            byte[] arry = cipher.doFinal(Base64.decodeBase64(strVal.getBytes("UTF-8")));/**/
            bindParam = new String(arry, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bindParam;
    }

    private String replaceStr(String Str) {
        String repStr = Str.replaceAll("\\*", "+").replaceAll("\\:", "/")
                .replaceAll("\\_", "=");
        return repStr;
    }



}
