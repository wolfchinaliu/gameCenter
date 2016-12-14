package weixin.personalredpacket.service.impl;

import weixin.member.controller.ConnectionsManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by 晓春 on 2016/1/30.
 */
public  class leftRecordSendFlow {
    public static Double sendFlow(String id, String accountId) throws Exception {
        //1.查询剩余流量leftflow
        Double sendflow = null;
        String sqlflow = "select sum(redFlowValue) as sendflow from weixin_personalredpacketrecords where redpacksetId='" + id + "'and accountId='" + accountId + "'";
        Connection connection = null;
        Statement stmt = null;
        ResultSet es = null;
        try {
            //创建的jdbc连接语句
            connection = ConnectionsManager.getMysqlConn();
            stmt = connection.createStatement();
            es = stmt.executeQuery(sqlflow);
            while (es.next()) {
                sendflow = es.getDouble("sendflow");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            es.close();
            stmt.close();
            connection.close();
        }
        return sendflow;
    }


    /**
     * 流量卡的到期回收
     * @param batchNo
     * @return
     * @throws Exception
     */
    public static Double sendFlowCard(String batchNo) throws Exception {
        //1.查询剩余流量leftflow
        Double sendflowCard = null;
        String sqlflow = "select sum(flowValue) as sendflowCard from flowcardinfo where batchNo='" + batchNo + "' and isUse='是'";
        Connection connection = null;
        Statement stmt = null;
        ResultSet es = null;
        try {
            //创建的jdbc连接语句
            connection = ConnectionsManager.getMysqlConn();
            stmt = connection.createStatement();
            es = stmt.executeQuery(sqlflow);
            while (es.next()) {
                sendflowCard = es.getDouble("sendflowCard");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            es.close();
            stmt.close();
            connection.close();
        }
        return sendflowCard;
    }

}
