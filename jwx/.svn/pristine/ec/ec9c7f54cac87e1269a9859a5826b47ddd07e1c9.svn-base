package weixin.lottery.service.impl;

import weixin.member.controller.ConnectionsManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by 晓春 on 2016/2/17.
 */
public class RiddlesNum {
    public static int getRiddlesNum(String hdid, String accountId) throws Exception {
        String sql = "select count(*) as total from lanternRiddlesBank where hdid='" + hdid + "' and accountid='" + accountId + "'";
        int totalRiddles = 0;  //总的题数目
        Connection connection = null;
        Statement stmt = null;
        ResultSet es = null;
        try {
//            创建的jdbc连接语句
            connection = ConnectionsManager.getMysqlConn();
            stmt = connection.createStatement();
            es = stmt.executeQuery(sql);
            while (es.next()) {
                totalRiddles = es.getInt("total");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            es.close();
            stmt.close();
            connection.close();
        }
        return totalRiddles;
    }
}
