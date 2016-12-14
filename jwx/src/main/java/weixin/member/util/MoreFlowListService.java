package weixin.member.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONObject;
import org.jeecgframework.core.util.ResourceUtil;
import weixin.liuliangbao.jsonbean.MoreFlow.attentionAndsignInFlowEntity;
import weixin.liuliangbao.jsonbean.MoreFlow.gameFlow;
import weixin.liuliangbao.jsonbean.MoreFlow.shareFlow;
import weixin.liuliangbao.jsonbean.ViewBean.PhoneLocationBean;
import weixin.liuliangbao.util.HttpUtil;
import weixin.member.controller.ConnectionsManager;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaona on 2016/2/17.
 */
public class MoreFlowListService {

    /**
     * 关注和签到查询
     *
     * @param accId
     * @param tyep
     * @return
     * @throws SQLException
     */
    public List<attentionAndsignInFlowEntity> queryMoreGZ(String accId, String tyep) throws SQLException {
        String dd=ResourceUtil.getWeiXinAccountId();
        String sql = "";
            sql = " select w.*,r.flowValue from weixin_account w join MerchantFlowAccount m join weixin_acct a join merchantFlowGiveRules r where w.id=m.accountId and w.id=r.merchantID and(m.countryFlowValue>0 OR m.provinceFlowValue>0) and w.acct_id=a.id and (a.business_type<>'" + tyep + "' or a.id='" + accId + "') and r.operateType='关注' and r.flowValue>0";
//            sql = " select w.*,r.flowValue from weixin_account w join MerchantFlowAccount m join weixin_acct a join merchantFlowGiveRules r where w.id=m.accountId and w.id=r.merchantID and(m.countryFlowValue>0 OR m.provinceFlowValue>0) and w.acct_id=a.id and (a.business_type<>'" + tyep + "') and r.operateType='关注' and r.flowValue>0";
        List<attentionAndsignInFlowEntity> lisFlow = new ArrayList<attentionAndsignInFlowEntity>();
        Connection connection = null;
        Statement stmt = null;
        ResultSet es = null;
        try {
//            创建的jdbc连接语句
            connection = ConnectionsManager.getMysqlConn();

            stmt = connection.createStatement();
            es = stmt.executeQuery(sql);
//获取读取配置文件的地址，从而加上图片的名字就是我们需要查找的地址

            String prefixurl = ResourceUtil.getConfigByName("media.url.prefix");
            while (es.next()) {
                attentionAndsignInFlowEntity en = new attentionAndsignInFlowEntity();
                en.setId(es.getString("id"));
                en.setAccountName(es.getString("accountname"));
                en.setLogoAccount(prefixurl + "/" + es.getString("logoAccount"));   //企业logo的目录文件名
                en.setFlowValue(es.getString("flowValue"));
                lisFlow.add(en);
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            es.close();
            stmt.close();
            connection.close();
        }
        return lisFlow;
    }

    /**
     * 签到
     *
     * @param phoneNumber
     * @param tyep
     * @return
     * @throws SQLException
     */
    public List<attentionAndsignInFlowEntity> queryMoreQD(String accId, String tyep) throws SQLException {
        /**
         *如果没有手机号的，那么可以不用根据手机号进行查询
         */
        String sql = "";

            sql = " select w.*,r.flowValue from weixin_account w join MerchantFlowAccount m join weixin_acct a join merchantFlowGiveRules r where w.id=m.accountId and w.id=r.merchantID and(m.countryFlowValue>0 OR m.provinceFlowValue>0) and w.acct_id=a.id and (a.business_type<>'" + tyep + "' or a.id='" + accId + "') and r.operateType='签到' and r.flowValue>0";
//            sql = " select w.*,r.flowValue from weixin_account w join MerchantFlowAccount m join weixin_acct a join merchantFlowGiveRules r where w.id=m.accountId and w.id=r.merchantID and(m.countryFlowValue>0 OR m.provinceFlowValue>0) and w.acct_id=a.id and (a.business_type<>'" + tyep + "') and r.operateType='签到' and r.flowValue>0";

        List<attentionAndsignInFlowEntity> lisFlow = new ArrayList<attentionAndsignInFlowEntity>();
        Connection connection = null;
        Statement stmt = null;
        ResultSet es = null;
        try {
//            创建的jdbc连接语句
            connection = ConnectionsManager.getMysqlConn();

            stmt = connection.createStatement();
            es = stmt.executeQuery(sql);
//获取读取配置文件的地址，从而加上图片的名字就是我们需要查找的地址

            String prefixurl = ResourceUtil.getConfigByName("media.url.prefix");
            while (es.next()) {
                attentionAndsignInFlowEntity en = new attentionAndsignInFlowEntity();
                en.setId(es.getString("id"));
                en.setAccountName(es.getString("accountname"));
                en.setLogoAccount(prefixurl + "/" + es.getString("logoAccount"));   //企业logo的目录文件名
                en.setFlowValue(es.getString("flowValue"));
                lisFlow.add(en);
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            es.close();
            stmt.close();
            connection.close();
        }
        return lisFlow;
    }

    /**
     * 游戏列表查询
     *
     * @param accId
     * @param tyep
     * @return
     * @throws SQLException
     */
    public List<gameFlow> queryMoreGame(String accId, String tyep) throws SQLException {
        /**
         *如果没有手机号的，那么可以不用根据手机号进行查询
         */
        String sql = "";

          sql = "select m.*,w.logoAccount,w.accountname from weixin_lotterylxc m JOIN weixin_account w JOIN weixin_acct a  where m.accountid=w.id and w.acct_id=a.id and (a.business_type<>'" + tyep + "' or a.id='" + accId + "')";
//            sql = "select m.*,w.logoAccount,w.accountname from weixin_lotterylxc m JOIN weixin_account w JOIN weixin_acct a  where m.accountid=w.id and w.acct_id=a.id and (a.business_type<>'" + tyep + "')";
        List<gameFlow> lisLotty = new ArrayList<gameFlow>();
        Connection connection = null;
        Statement stmt = null;
        ResultSet es = null;
        try {
            connection = ConnectionsManager.getMysqlConn();

            stmt = connection.createStatement();
            es = stmt.executeQuery(sql);
            //获取读取配置文件的地址，从而加上图片的名字就是我们需要查找的地址

            String prefixurl = ResourceUtil.getConfigByName("media.url.prefix");
            while (es.next()) {
                gameFlow en = new gameFlow();
                en.setAccountid(es.getString("accountid"));
                en.setId(es.getString("id"));
                en.setTitle(es.getString("title"));
                en.setCreateName(es.getString("create_name"));
                en.setLotteryType(es.getString("lottery_type"));
                en.setLogoAccount(prefixurl + "/" + es.getString("logoAccount"));
                en.setAccountname(es.getString("accountname"));
                if (es.getString("lottery_type").equals("1") || es.getString("lottery_type").equals("2")) {
//红包和刮刮卡显示是最大值，也就是一等奖的值
                    en.setFlowValue(es.getDouble("firstprize"));
                } else {
//红包的值是最大值与份数的商以及与2的乘积
                    Double hongbaoValue = 0.0;
                    //分母不能为0
                    if (es.getInt("lotterynumber") > 0) {
                        hongbaoValue = es.getDouble("abledotherprize") / es.getInt("lotterynumber") * 2;
                    }
                    en.setFlowValue(hongbaoValue);
                }
                lisLotty.add(en);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            es.close();
            stmt.close();
            connection.close();
        }
        return lisLotty;
    }

    /**
     * 分享列表查询
     *
     * @param phoneNumber
     * @param tyep
     * @return
     * @throws SQLException
     */
    public List<shareFlow> queryMoreShare(String accId, String tyep) throws SQLException {
        /**
         *如果没有手机号的，那么可以不用根据手机号进行查询
         */
        String sql = "";
           sql = "select m.*,r.flowValue from weixin_main m join weixin_account w join weixin_acct a join merchantFlowGiveRules r where r.merchantID=m.accountid and  m.accountid=w.id and w.acct_id=a.id and (a.business_type<>'" + tyep + "' or a.id='" + accId + "')and r.operateType='分享' and m.isShare='1' and r.flowValue>0";
//            sql = "select m.*,r.flowValue from weixin_main m join weixin_account w join weixin_acct a join merchantFlowGiveRules r where r.merchantID=m.accountid and  m.accountid=w.id and w.acct_id=a.id and (a.business_type<>'" + tyep + "')and r.operateType='分享' and m.isShare='1' and r.flowValue>0";
        List<shareFlow> lisShare = new ArrayList<shareFlow>();
        Connection connection = null;
        Statement stmt = null;
        ResultSet es = null;
        try {
            connection = ConnectionsManager.getMysqlConn();

            stmt = connection.createStatement();
            es = stmt.executeQuery(sql);
//            读取图片的地址,domain就是网站的根目录，也就是部署在tomcat下面的项目
            String prefix = ResourceUtil.getConfigByName("media.url.prefix");
            while (es.next()) {
//                NewsItem en = new NewsItem();
                shareFlow en = new shareFlow();
                en.setId(es.getString("id"));
                en.setTitle(es.getString("title"));
                en.setImagepath(es.getString("imagepath"));   //读取分享网络的根目录地址
                en.setShareTitle(es.getString("shareTitle"));
                en.setAccountid(es.getString("accountid"));
                en.setFlowValue(es.getString("flowValue"));
                lisShare.add(en);
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            es.close();
            stmt.close();
            connection.close();
        }
        return lisShare;
    }

}
