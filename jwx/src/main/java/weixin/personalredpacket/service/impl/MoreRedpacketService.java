package weixin.personalredpacket.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONObject;
import org.jeecgframework.core.util.ResourceUtil;
import weixin.liuliangbao.jsonbean.Update;
import weixin.liuliangbao.jsonbean.ViewBean.PhoneLocationBean;
import weixin.liuliangbao.util.HttpUtil;
import weixin.member.controller.ConnectionsManager;
import weixin.personalredpacket.entity.MoreRedpacketEntity;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaochun on 2016/1/27.
 */
public class MoreRedpacketService {
    public List<MoreRedpacketEntity> queryMoreRedpacket(String phoneNumber) throws SQLException {

        //
        String phoneUrl = ResourceUtil.getConfigByName("jfinalUrl-config") + "phoneLocation/getPhonelocation";
        Gson gson=new Gson();
        JSONObject myJsonObject = new JSONObject();
        Type phoneType = new TypeToken<PhoneLocationBean>() {
        }.getType();
        myJsonObject.accumulate("phoneNumber", phoneNumber);
        JSONObject phoneContent = HttpUtil.httpPost(phoneUrl, myJsonObject, false);

        String strPhoneContent = gson.toJson(phoneContent);

        PhoneLocationBean phoneJson = gson.fromJson(strPhoneContent, phoneType);
        String provinceName = phoneJson.getData().getProvinceName();

        //
        List<MoreRedpacketEntity> moreRedpacketEntities=new ArrayList<MoreRedpacketEntity>();

        StringBuffer sql=new StringBuffer();
        sql.append("select p.id,a.logoAccount,a.accountname,c.flowtype,p.subsidyValue,p.flowSendValue,");
        sql.append("(p.subsidyValue-p.flowSendValue) as leftflow,m.provincename,");
        sql.append("a.id as accountId ");
        sql.append("from weixin_personalredpacketset p join weixin_commonforhd c join weixin_account a join merchantcoverarea m  ");
//        sql.append("where (p.id=c.id and c.accountid=a.id and a.acct_id=m.accountID and c.flowtype='全国流量' and p.state='1')  ");
//        sql.append("or (p.id=c.id and c.accountid=a.id and a.acct_id=m.accountID and c.flowtype='省内流量' and p.state='1' and m.provincename='");
        sql.append("where p.id=c.id and c.accountid=a.id and a.acct_id=m.accountID  and p.state='1'and m.provincename='");
        sql.append(provinceName);
        sql.append("' order by c.flowtype asc ,leftflow desc");

        Connection connection = null;
        Statement stmt = null;
        ResultSet es = null;
        try {
            //
            connection = ConnectionsManager.getMysqlConn();

            stmt = connection.createStatement();
            es = stmt.executeQuery(sql.toString());
            es.last();  //
            if (es.getRow() == 0) {
                return moreRedpacketEntities;
            }
            es.beforeFirst();  //

            while (es.next()) {
                MoreRedpacketEntity en = new MoreRedpacketEntity();
                en.setId(es.getString("id"));
                en.setLogoAccount(es.getString("logoAccount"));
                en.setAccountname(es.getString("accountname"));
                en.setFlowtype(es.getString("flowtype"));
                en.setSubsidyValue(es.getString("subsidyValue"));
                en.setFlowSendValue(es.getString("flowSendValue"));
                en.setLeftflow(es.getString("leftflow"));
                en.setProvincename(es.getString("provincename"));
                en.setAccountId(es.getString("accountId"));
                moreRedpacketEntities.add(en);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            es.close();
            stmt.close();
            connection.close();
        }
        return moreRedpacketEntities;
    }
}
