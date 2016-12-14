package weixin.member.controller;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.jeecgframework.core.util.ResourceUtil;

/**
 * Created by aa on 2015/12/12.
 */
public class ConnectionsManager {

    // 数据库连接url
    private static String url;
    // 用户名
    private static String user;
    // 数据库密码
    private static String password;
    // properties对象
    private static Properties ps;
    // 输入流
    private static InputStream fis;

    /**
     * 获取mysql连接
     *
     * @return
     * @throws Exception
     */
    public static Connection getMysqlConn() {
        Connection conn = null;
        try {

            // 获取输入流
            //这种获取输入流的方式是最好的，无论你把配置文件放在什么地方，它都会去自动的查找它的
            //因此无论是开发j2se项目还是j2ee的项目都应该使用这种加载的方式。
//            fis = ConnectionsManager.class.getClassLoader()
//                    .getResourceAsStream("dbconfig.properties");
//
//            ps = new Properties();
//
//            // 把流加载到内存中
//            ps.load(fis);
//
//            // 关闭流
//            fis.close();

            // 获取数据库url
            url = ResourceUtil.getJdbcUrl();//ps.getProperty("jdbc.url.jeecg");
            // 获取用户名
            user = ResourceUtil.getJdbcUsername();//ps.getProperty("jdbc.username.jeecg");
            // 获取密码
            password = ResourceUtil.getJdbcPassword();//ps.getProperty("jdbc.password.jeecg");
            // 获取连接
            conn = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }
}
