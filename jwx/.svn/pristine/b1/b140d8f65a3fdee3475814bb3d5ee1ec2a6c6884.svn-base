package weixin.util;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by guoliang on 2016/6/18.
 */
public class PropertyUtils {

    public static String getLastestProperty(String filename, String key, String defaultValue) {
        if (StringUtils.isBlank(filename) || StringUtils.isBlank(key)) {
            return defaultValue;
        }
        Properties properties = new Properties();
        InputStream in = null;
        try {
            in = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
            return defaultValue;
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return properties.getProperty(key, defaultValue);
    }

}
