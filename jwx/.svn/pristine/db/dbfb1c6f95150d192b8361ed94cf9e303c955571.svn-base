package weixin.liuliangbao.jsonbean.FlowCard;

import weixin.member.controller.ConnectionsManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aa on 2015/12/21.
 */
public class findExtractionCode  implements java.io.Serializable {

    public static List<extractionCodeBean> find(int num) throws Exception {
        String sql = "select * from ExtractionCode LIMIT " + num + "";

        Connection connection = null;
        Statement stmt = null;
        ResultSet es = null;
        List<extractionCodeBean> lis = new ArrayList<extractionCodeBean>();
        try {
//            创建的jdbc连接语句
            connection = ConnectionsManager.getMysqlConn();

            stmt = connection.createStatement();
            es = stmt.executeQuery(sql);
            while (es.next()) {
                extractionCodeBean en = new extractionCodeBean();
                en.setId(es.getString("id"));
                en.setCode(es.getString("code"));
                lis.add(en);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            es.close();
            stmt.close();
            connection.close();
        }

        return lis;

    }
}
