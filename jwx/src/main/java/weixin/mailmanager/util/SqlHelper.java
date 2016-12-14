package weixin.mailmanager.util;


import org.apache.log4j.Logger;
import weixin.mailmanager.entity.FlowStore;
import weixin.mailmanager.entity.MemberEntity;
import weixin.member.controller.ConnectionsManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.lang3.StringUtils;



/**
 * Created by 晓春 on 2016/4/28.
 */
public class SqlHelper {
    public static final transient Logger LOGGER = Logger.getLogger(SqlHelper.class);
    public static Connection connection = null;
    public static Statement stmt = null;
    public static ResultSet es = null;

    /**
     * 根据传入的类型来决定查询语句------每日，每周，每月递增的商户数
     *    /**
     * type包含四种类型
     * 1、每天邮件(dayEmail)
     * 2、每周邮件(weekEmail)
     * 3、每月邮件(monthEmail)
     * @param type
     * @return
     */
    public static MemberEntity MerchantAdd(String type)throws Exception{
        String sql="";
        Calendar cal   =   Calendar.getInstance();
        MemberEntity memberEntity=new MemberEntity();
        if(type.equals("dayEmail")){ //查询的是当天的记录数

            cal.add(Calendar.DATE,   -1);
            Date curdate= cal.getTime();
            SimpleDateFormat dt= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dt.format(curdate);
//            sql="Select countnum,acct_level from "+
//                    "(select count(id) as countnum, acct_level from weixin_acct where  create_date >= '"+ dt.format(curdate) +"'Group  BY acct_level ) aa";
            sql="SELECT sum(countnum)as countTotal,sum(case when acct_level='A级' then countnum ELSE 0 END) as countA,sum(case when acct_level='B级' then countnum ELSE 0 END) as countB,sum(case when acct_level='C级' then countnum Else 0 END) as countC " +
                    "FROM (SELECT (case when acct_level='1' then 'A级' WHEN acct_level='2' then 'B级' WHEN acct_level='3' then 'C级' ELSE acct_level END)acct_level, id,count(id) AS countnum FROM weixin_acct WHERE create_date >= '"+dt.format(curdate)+"' GROUP BY acct_level ORDER BY acct_level) aa";
        }else if(Objects.equals(type, "weekEmail")){ //查询的是本周的记录数
            cal.add(Calendar.DATE,   -7);
            Date curdate= cal.getTime();
            SimpleDateFormat dt= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dt.format(curdate);
//            sql="select countnum,id,sum(countnum) sumNum from" +
//                    "(select count(id) as countnum ,acct_level from weixin_acct where create_date >= '"+ dt.format(curdate) +"'Group  BY acct_level ) aa";
            sql="SELECT sum(countnum)as countTotal,sum(case when acct_level='A级' then countnum ELSE 0 END) as countA,sum(case when acct_level='B级' then countnum ELSE 0 END) as countB,sum(case when acct_level='C级' then countnum Else 0 END) as countC " +
                    "FROM (SELECT (case when acct_level='1' then 'A级' WHEN acct_level='2' then 'B级' WHEN acct_level='3' then 'C级' ELSE acct_level END)acct_level, id,count(id) AS countnum FROM weixin_acct WHERE create_date >= '"+dt.format(curdate)+"' GROUP BY acct_level ORDER BY acct_level) aa";

//            sql="SELECT count(id) as total from weixin_acct WHERE YEARWEEK(date_format(create_date,'%Y-%m-%d')) = YEARWEEK(now())";
        }else { //查询的是当月的记录数//
            cal.add(Calendar.MONTH,-1);
            Date curdate= cal.getTime();
            SimpleDateFormat dt= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dt.format(curdate);
//            sql="select countnum,id,sum(countnum) sumNum from" +
//                    "(select count(id) as countnum ,id from weixin_acct where date_format(create_date,'%Y-%m')=date_format(now(),'%Y-%m') GROUP BY id ORDER BY count(id) desc ) aa limit 1";
//            sql="select countnum,id,sum(countnum) sumNum from" +
//                    "(select count(id) as countnum ,acct_level from weixin_acct where create_date >= '"+ dt.format(curdate) +"'Group  BY acct_level ) aa";
            sql="SELECT sum(countnum)as countTotal,sum(case when acct_level='A级' then countnum ELSE 0 END) as countA,sum(case when acct_level='B级' then countnum ELSE 0 END) as countB,sum(case when acct_level='C级' then countnum Else 0 END) as countC " +
                    "FROM (SELECT (case when acct_level='1' then 'A级' WHEN acct_level='2' then 'B级' WHEN acct_level='3' then 'C级' ELSE acct_level END)acct_level, id,count(id) AS countnum FROM weixin_acct WHERE create_date >= '"+dt.format(curdate)+"' GROUP BY acct_level ORDER BY acct_level) aa";

        }
        try {
            //创建的jdbc连接语句
            connection = ConnectionsManager.getMysqlConn();
            stmt = connection.createStatement();
            es = stmt.executeQuery(sql);

            memberEntity.setSumNum(0);
            memberEntity.setCountA(0);
            memberEntity.setCountB(0);
            memberEntity.setCountC(0);

            while (es.next()) {
                memberEntity.setSumNum(es.getInt("countTotal"));
                memberEntity.setCountA(es.getInt("countA"));
                memberEntity.setCountB(es.getInt("countB"));
                memberEntity.setCountC(es.getInt("countC"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            es.close();
            stmt.close();
            connection.close();
        }

        return memberEntity;
    }

    /**
     * 获得粉丝总数
     * @return
     */
    public static MemberEntity membersTotal()throws Exception{
        //List<memberEntity> lisNum=new ArrayList<memberEntity>();
        MemberEntity memberEntity= new MemberEntity();
        String sql="";
//        sql = "select count(DISTINCT(open_id)) as total from weixin_member";
        sql = "SELECT (SELECT count(DISTINCT open_id) cc from weixin_member where subscribe='1' GROUP BY NULL) sumNum, "
        		+ "count(DISTINCT open_id) countnum,account_id, (SELECT accountname from weixin_account a where a.id = m.account_id limit 1) accountname "
        		+ "from weixin_member m where subscribe='1' GROUP BY account_id ORDER BY countnum DESC limit 1";
        try {
            //创建的jdbc连接语句
            connection = ConnectionsManager.getMysqlConn();
            stmt = connection.createStatement();
            es = stmt.executeQuery(sql);
            if(es.next()) {
                memberEntity.setAccountName(StringUtils.defaultString(es.getString("accountname"), ""));
                memberEntity.setMaxNum(es.getInt("countnum"));
                memberEntity.setSumNum(es.getInt("sumNum"));
                memberEntity.setAccountid(es.getString("account_id"));
            }else{
                memberEntity.setSumNum(0);
                memberEntity.setMaxNum(0);
                memberEntity.setAccountName("无");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            es.close();
            stmt.close();
            connection.close();
        }
        return memberEntity;
    }
    /**
     * 根据传入的类型来决定查询语句------每日，每周，每月递增的粉丝数
     * type包含四种类型
     * 1、每天邮件(dayEmail)
     * 2、每周邮件(weekEmail)
     * 3、每月邮件(monthEmail)
     * @param type
     * @return
     */
    public static MemberEntity membersAdd(String type)throws Exception{
        MemberEntity lisNum=new MemberEntity();
        String sql="";
        Calendar cal   =   Calendar.getInstance();
        Date curdatedaynow= cal.getTime();
        SimpleDateFormat dt= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(Objects.equals(type, "dayEmail")){ //查询的是当天的记录数
            cal.add(Calendar.DATE,   -1);
            Date curdateday= cal.getTime();

            dt.format(curdateday);
//            sql="select countnum,account_id,sum(countnum) sumNum from" +
//                    "(select count(DISTINCT(open_id)) as countnum ,account_id from weixin_member where subscribe_time >='"+dt.format(curdateday)+"' GROUP BY account_id ORDER BY COUNT(DISTINCT open_id) desc ) aa limit 1";
            sql="SELECT countnum,account_id,sumNum,accountname FROM (SELECT countnum,account_id,sum(countnum) sumNum FROM (SELECT count(DISTINCT(open_id)) AS countnum,account_id FROM weixin_member WHERE subscribe_time BETWEEN '"+dt.format(curdateday)+"' " +
                    "AND '"+dt.format(curdatedaynow)+"' AND subscribe = 1 GROUP BY account_id ORDER BY COUNT(DISTINCT open_id) DESC) aa GROUP BY null LIMIT 1) b LEFT JOIN weixin_account c ON c.id = b.account_id";
        }else if(Objects.equals(type, "weekEmail")){ //查询的是本周的记录数
            cal.add(Calendar.DATE,   -7);
            Date curdateweek= cal.getTime();
            dt.format(curdateweek);
//            sql="select countnum,account_id,sum(countnum) sumNum from" +
//                    "(select count(DISTINCT(open_id)) as countnum ,account_id from weixin_member where subscribe_time >='"+dt.format(curdateweek)+"' GROUP BY account_id ORDER BY COUNT(DISTINCT open_id) desc ) aa limit 1";
            sql="SELECT countnum,account_id,sumNum,accountname FROM(SELECT countnum,account_id,sum(countnum) sumNum FROM (SELECT count(DISTINCT(open_id)) AS countnum,account_id FROM weixin_member WHERE subscribe_time BETWEEN '"+dt.format(curdateweek)+"' " +
                    "AND '"+dt.format(curdatedaynow)+"' AND subscribe = 1 GROUP BY account_id ORDER BY COUNT(DISTINCT open_id) DESC) aa GROUP BY null LIMIT 1) b LEFT JOIN weixin_account c ON c.id = b.account_id";
        }else { //查询的是当月的记录数
            cal.add(Calendar.MONTH,-1);
            Date curdatemonth= cal.getTime();
            dt.format(curdatemonth);
//            sql="select countnum,account_id,sum(countnum) sumNum from" +
//                    "(select count(DISTINCT(open_id)) as countnum ,account_id from weixin_member where subscribe_time >='"+dt.format(curdatemonth)+"' GROUP BY account_id ORDER BY COUNT(DISTINCT open_id) desc ) aa limit 1";
            sql="SELECT countnum,account_id,sumNum,accountname FROM(SELECT countnum,account_id,sum(countnum) sumNum FROM (SELECT count(DISTINCT(open_id)) AS countnum,account_id FROM weixin_member WHERE subscribe_time BETWEEN '"+dt.format(curdatemonth)+"' " +
                    "AND '"+dt.format(curdatedaynow)+"' AND subscribe = 1 GROUP BY account_id ORDER BY COUNT(DISTINCT open_id) DESC) aa GROUP BY null LIMIT 1) b LEFT JOIN weixin_account c ON c.id = b.account_id";
        }
        try {
            //创建的jdbc连接语句
            connection = ConnectionsManager.getMysqlConn();
            stmt = connection.createStatement();
            es = stmt.executeQuery(sql);
            if(es.next()){
                lisNum.setAccountName(es.getString("accountname"));
                lisNum.setAccountid(es.getString("account_id"));
                lisNum.setMaxNum(es.getInt("countnum"));
                lisNum.setSumNum(es.getInt("sumNum"));

            }else{
                lisNum.setAccountName("无");
                lisNum.setMaxNum(0);
                lisNum.setSumNum(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            es.close();
            stmt.close();
            connection.close();
        }
        return lisNum;
    }


    /**
     * 粉丝的访问------获取到粉丝访问信息数目，访问人数以及最高的人数
     */
    public static MemberEntity viewMemberNum(String type) throws SQLException {
        MemberEntity lisNum=new MemberEntity();
        String sql="";
//        sql = "select countnum,accountid,sum(countnum) sumNum from" +
//                "(select COUNT(DISTINCT openId) as countnum ,accountid from weixin_viewaddress  GROUP BY accountid ORDER BY COUNT(DISTINCT openId) desc ) aa limit 1;";
        sql="SELECT countnum,sumNum,accountname,accountid FROM (SELECT countnum,accountid,sum(countnum) sumNum FROM (SELECT COUNT(DISTINCT openId) AS countnum,accountid FROM weixin_viewaddress WHERE accountid is not NULL GROUP BY accountid ORDER BY COUNT(DISTINCT openId) DESC) aa GROUP BY null LIMIT 1)b LEFT JOIN weixin_account c  on c.id=b.accountid;";

        //创建的jdbc连接语句
        try {
            connection = ConnectionsManager.getMysqlConn();
            stmt = connection.createStatement();
            es = stmt.executeQuery(sql);
            if(es.next()){
                lisNum.setAccountName(es.getString("accountname"));
                lisNum.setMaxNum(es.getInt("countnum"));
                lisNum.setSumNum(es.getInt("sumNum"));
                lisNum.setAccountid(es.getString("accountid"));
            }else{
                lisNum.setAccountName("无");
                lisNum.setSumNum(0);
                lisNum.setMaxNum(0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            es.close();
            stmt.close();
            connection.close();
        }
        return  lisNum;
    }

    /**
     * 获得访问人次以及最高人次
     */
    public static MemberEntity viewVisitNum(String type) throws SQLException {
        MemberEntity lisNum=new MemberEntity();
        String sql="";

//        sql = "select countnum,accountid,sum(countnum) sumNum from" +
//                "(select COUNT(openId) as countnum ,accountid from weixin_viewaddress GROUP BY accountid ORDER BY COUNT(openId) desc ) aa limit 1;";
        sql="SELECT countnum,sumNum,accountname,accountid FROM (SELECT countnum,accountid,sum(countnum) sumNum FROM (SELECT COUNT(DISTINCT openId) AS countnum,accountid FROM weixin_viewaddress WHERE accountid is not NULL GROUP BY accountid ORDER BY COUNT(DISTINCT openId) DESC) aa GROUP BY null LIMIT 1)b LEFT JOIN weixin_account c  on c.id=b.accountid;";

        //创建的jdbc连接语句
        try {
            connection = ConnectionsManager.getMysqlConn();
            stmt = connection.createStatement();
            es = stmt.executeQuery(sql);
            if(es.next()){
                lisNum.setAccountName(es.getString("accountname"));
                lisNum.setMaxNum(es.getInt("countnum"));
                lisNum.setSumNum(es.getInt("sumNum"));
                lisNum.setAccountid(es.getString("accountid"));
            }else{
                lisNum.setAccountName("无");
                lisNum.setSumNum(0);
                lisNum.setMaxNum(0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            es.close();
            stmt.close();
            connection.close();
        };
        return  lisNum;
    }

    /**
     * 粉丝活跃度------粉丝人数/总的粉丝数
     */
    public  static double viewRate(){
        String type="perNum";
        double viewRate=0.0;
        int memNum=0;
        int viewNum=0;
        try {
            memNum = SqlHelper.membersTotal().getSumNum(); //总粉丝数
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            viewNum = SqlHelper.viewMemberNum(type).getSumNum();   //访问的人数
            if(memNum!=0){
                // 访问的人数/总的粉丝数
                viewRate=viewNum/memNum;
            }else{
                viewRate=0;

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        //获取格式化对象
        NumberFormat nt = NumberFormat.getPercentInstance();
        //设置百分数精确度2即保留两位小数
        nt.setMinimumFractionDigits(2);
        nt.format(viewRate);  //将小数转为百分数
        return viewRate;
    }

    /**
     * 根据传入的类型来决定查询语句------每日，每周，每月获取的流量值
     *type包含四种类型
     *1、每天邮件(dayEmail)
     *2、每周邮件(weekEmail)
     *3、每月邮件(monthEmail)
     * @param type
     * @return
     * @throws Exception
     */
    public static FlowStore getFlow(String type)throws Exception{
        FlowStore enFlowStore =new FlowStore();
        String sql="";
        Calendar cal =  Calendar.getInstance();
        Date curdatenow =cal.getTime();
        if(type=="dayEmail"){ //查询的是当天的记录数
            cal.add(Calendar.DATE,   -1);
            Date curdateday= cal.getTime();
            SimpleDateFormat dt= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dt.format(curdateday);
//            sql="select sum(flowValue) as getflow from userflowgiverecords where operateDate >='"+dt.format(curdateday)+"'";
            sql="SELECT getflow,merchantID,flowTotal,accountname FROM(SELECT merchantID,getflow,SUM(getflow) as flowTotal FROM" +
                    "(SELECT merchantID,sum(flowValue) AS getflow FROM userflowgiverecords WHERE operateDate BETWEEN '"+dt.format(curdateday)+"' AND '"+dt.format(curdatenow)+"' GROUP BY merchantID ORDER BY SUM(flowValue) DESC)aa GROUP BY null )b LEFT JOIN weixin_account w ON w.id=b.merchantID";
        }else if(type=="weekEmail"){ //查询的是本周的记录数
            cal.add(Calendar.DATE,   -7);
            Date curdateweek= cal.getTime();
            SimpleDateFormat dt= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dt.format(curdateweek);
//            sql="SELECT merchantID,getflow,SUM(getflow) FROM (SELECT merchantID, sum(flowValue) AS getflow FROM userflowgiverecords WHERE operateDate >= '"+dt.format(curdateweek)+"'GROUP BY merchantID ORDER BY SUM(flowValue) DESC)aa";
            sql="SELECT getflow,merchantID,flowTotal,accountname FROM(SELECT merchantID,getflow,SUM(getflow) as flowTotal FROM" +
                    "(SELECT merchantID,sum(flowValue) AS getflow FROM userflowgiverecords WHERE operateDate BETWEEN '"+dt.format(curdateweek)+"' AND '"+dt.format(curdatenow)+"' GROUP BY merchantID ORDER BY SUM(flowValue) DESC)aa GROUP BY null )b LEFT JOIN weixin_account w ON w.id=b.merchantID";
        }else { //查询的是当月的记录数
            cal.add(Calendar.MONTH,-1);
            Date curdatemonth= cal.getTime();
            SimpleDateFormat dt= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dt.format(curdatemonth);
//            sql="SELECT merchantID,getflow,SUM(getflow) FROM (SELECT merchantID, sum(flowValue) AS getflow FROM userflowgiverecords WHERE operateDate >= '"+dt.format(curdatemonth)+"'GROUP BY merchantID ORDER BY SUM(flowValue) DESC)aa";
            sql="SELECT getflow,merchantID,flowTotal,accountname FROM(SELECT merchantID,getflow,SUM(getflow) as flowTotal FROM" +
                    "(SELECT merchantID,sum(flowValue) AS getflow FROM userflowgiverecords WHERE operateDate BETWEEN '"+dt.format(curdatemonth)+"' AND '"+dt.format(curdatenow)+"' GROUP BY merchantID ORDER BY SUM(flowValue) DESC)aa GROUP BY null )b LEFT JOIN weixin_account w ON w.id=b.merchantID";

        }
        try {
            //创建的jdbc连接语句
            connection = ConnectionsManager.getMysqlConn();
            stmt = connection.createStatement();
            es = stmt.executeQuery(sql);
            if(es.next()){
                enFlowStore.setFlowTotal(es.getDouble("flowTotal"));
                enFlowStore.setFlow(es.getDouble("getflow"));
                enFlowStore.setAccountName(es.getString("accountname"));
                enFlowStore.setAccountId(es.getString("merchantID"));
            }else{
                enFlowStore.setFlowTotal(0.0);
                enFlowStore.setFlow(0.0);
                enFlowStore.setAccountName("无");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            es.close();
            stmt.close();
            connection.close();
        }
        return enFlowStore;
    }

    /**
     * 根据传入的类型来决定查询语句------每日，每周，每月各运营商（移动/联通/电信）提现的流量值
     *type包含四种类型
     *1、每天邮件(dayEmail)
     *2、每周邮件(weekEmail)
     *3、每月邮件(monthEmail)
     * @param type
     * @return
     * @throws Exception
     */
    public  static List<FlowStore> flowCharged(String type)throws Exception{

        List<FlowStore> fs=new ArrayList<FlowStore>();

        String sql="";
        Calendar cal = Calendar.getInstance();
        Date curdatenow =cal.getTime();
        if(type=="dayEmail"){ //查询的是当天的记录数
            cal.add(Calendar.DATE,   -1);
            Date curdateday= cal.getTime();
            SimpleDateFormat dt= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dt.format(curdatenow);
            dt.format(curdateday);
//            sql="select sum(flowValue) as flowTotal,flowType from userchargedflowrecords where requestDate >= '"+dt.format(curdateday)+"'GROUP BY flowType";
            sql="SELECT flowType,businessCode,businessName,sum(flowValue) FROM(SELECT record.*,l.businessCode,b.businessName FROM (SELECT IFNULL(chargePhone, phoneNumber) chargePhone, SUBSTR(IFNULL(chargePhone, phoneNumber), 1, 7) beginNumber,"
            +"(case when flowType = '省内流量' then '省内' when flowType ='国内流量' then '国内' else flowType END) flowType,flowValue FROM `userchargedflowrecords` tr WHERE tr.resultReason = '充值成功'"
            +"AND requestDate BETWEEN '"+dt.format(curdateday)+"' AND '"+dt.format(curdatenow)+"') record LEFT JOIN phonelocation l ON record.beginNumber = l.beginNumber LEFT JOIN businessinfo b ON b.businessCode= l.businessCode) s GROUP BY flowType,businessCode;";
        }else if(type=="weekEmail"){ //查询的是本周的记录数
            cal.add(Calendar.DATE,   -7);
            Date curdateweek= cal.getTime();
            SimpleDateFormat dt= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dt.format(curdateweek);
//            sql="select sum(flowValue) as flowTotal,flowType from userchargedflowrecords where requestDate >= '"+dt.format(curdateweek)+"' GROUP BY flowType";
            sql="SELECT flowType,businessCode,businessName,sum(flowValue) FROM(SELECT record.*,l.businessCode,b.businessName FROM (SELECT IFNULL(chargePhone, phoneNumber) chargePhone, SUBSTR(IFNULL(chargePhone, phoneNumber), 1, 7) beginNumber,"
                    +"(case when flowType = '省内流量' then '省内' when flowType ='国内流量' then '国内' else flowType END) flowType,flowValue FROM `userchargedflowrecords` tr WHERE tr.resultReason = '充值成功'"
                    +"AND requestDate BETWEEN '"+dt.format(curdateweek)+"' AND '"+dt.format(curdatenow)+"') record LEFT JOIN phonelocation l ON record.beginNumber = l.beginNumber LEFT JOIN businessinfo b ON b.businessCode= l.businessCode) s GROUP BY flowType,businessCode;";
        }else { //查询的是当月的记录数
            cal.add(Calendar.MONTH,-1);
            Date curdatemonth= cal.getTime();
            SimpleDateFormat dt= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dt.format(curdatemonth);
//            sql="select sum(flowValue) as flowTotal,flowType from userchargedflowrecords where requestDate >= '"+dt.format(curdatemonth)+"' GROUP BY flowType";
            sql="SELECT flowType,businessCode,businessName,sum(flowValue) as flowMerchant FROM(SELECT record.*,l.businessCode,b.businessName FROM (SELECT IFNULL(chargePhone, phoneNumber) chargePhone, SUBSTR(IFNULL(chargePhone, phoneNumber), 1, 7) beginNumber,"
                    +"(case when flowType = '省内流量' then '省内' when flowType ='国内流量' then '国内' else flowType END) flowType,flowValue FROM `userchargedflowrecords` tr WHERE tr.resultReason = '充值成功'"
                    +"AND requestDate BETWEEN '"+dt.format(curdatemonth)+"' AND '"+dt.format(curdatenow)+"') record LEFT JOIN phonelocation l ON record.beginNumber = l.beginNumber LEFT JOIN businessinfo b ON b.businessCode= l.businessCode) s GROUP BY flowType,businessCode;";
        }
        try {
            //创建的jdbc连接语句
            connection = ConnectionsManager.getMysqlConn();
            stmt = connection.createStatement();
            es = stmt.executeQuery(sql);
            while (es.next()) {
                FlowStore f=new FlowStore();
                f.setFlowType(es.getString("flowType"));
                f.setBusinessName(es.getString("businessName"));
                f.setFlow(es.getDouble("flow"));
                fs.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            es.close();
            stmt.close();
            connection.close();
        }
        return fs;
    }

    /**
     * S级商户充值流量
     * @param type
     * @return
     * @throws SQLException
     */
    public static List<FlowStore> SFlowSale(String type) throws SQLException {
        List<FlowStore> sfs=new ArrayList<FlowStore>();
        String sql="";
        Calendar cal = Calendar.getInstance();
        Date curdatenow =cal.getTime();
        if(type=="dayEmail"){ //查询的是当天的记录数
            cal.add(Calendar.DATE,   -1);
            Date curdateday= cal.getTime();
            SimpleDateFormat dt= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dt.format(curdatenow);
            dt.format(curdateday);

            sql="SELECT sum(f.flowValue) as flowTotal,f.flowType from flowcardtraderecords f where f.fromAcc_id IN(SELECT m.id from  merchantflowaccount m WHERE m.accountId IN (SELECT ac.id FROM weixin_account ac WHERE acct_id IN (SELECT a.id FROM weixin_acct a WHERE " +
                    "a.acct_level = '0')) AND m.accountName='石榴科技') group by null and f.tradingDate BETWEEN '"+dt.format(curdateday) +"' AND '"+dt.format(curdatenow)+"';";
        }else if(type=="weekEmail"){ //查询的是本周的记录数
            cal.add(Calendar.DATE,   -7);
            Date curdateweek= cal.getTime();
            SimpleDateFormat dt= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dt.format(curdateweek);

            sql="SELECT sum(f.flowValue) as flowTotal,f.flowType from flowcardtraderecords f where f.fromAcc_id IN(SELECT m.id from  merchantflowaccount m WHERE m.accountId IN (SELECT ac.id FROM weixin_account ac WHERE acct_id IN (SELECT a.id FROM weixin_acct a WHERE " +
                    "a.acct_level = '0')) AND m.accountName='石榴科技') group by null and f.tradingDate BETWEEN '"+dt.format(curdateweek) +"' AND '"+dt.format(curdatenow)+"';";
        }else { //查询的是当月的记录数
            cal.add(Calendar.MONTH,-1);
            Date curdatemonth= cal.getTime();
            SimpleDateFormat dt= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dt.format(curdatemonth);
//            sql="select sum(flowValue) as flowTotal,flowType from userchargedflowrecords where requestDate >= '"+dt.format(curdatemonth)+"' GROUP BY flowType";
            sql="SELECT sum(f.flowValue) as flowTotal,f.flowType from flowcardtraderecords f where f.fromAcc_id IN(SELECT m.id from  merchantflowaccount m WHERE m.accountId IN (SELECT ac.id FROM weixin_account ac WHERE acct_id IN (SELECT a.id FROM weixin_acct a WHERE " +
                    "a.acct_level = '0')) AND m.accountName='石榴科技') group by null and f.tradingDate BETWEEN '"+dt.format(curdatemonth) +"' AND '"+dt.format(curdatenow)+"';'";
        }
        try {
            //创建的jdbc连接语句
            connection = ConnectionsManager.getMysqlConn();
            stmt = connection.createStatement();
            es = stmt.executeQuery(sql);
            while (es.next()) {
                FlowStore f=new FlowStore();
                f.setFlowType(es.getString("flowType"));
                f.setFlow(es.getDouble("flowTotal"));
                sfs.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            es.close();
            stmt.close();
            connection.close();
        }
        return sfs;
    }

    public static boolean save(String strSql) throws SQLException {
        boolean flag=false;
        try{
            connection = ConnectionsManager.getMysqlConn();
            stmt = connection.createStatement();
            flag = stmt.execute(strSql);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            stmt.close();
            connection.close();
        }
        return flag;
    }

    /**
     * 查询商户某时间段消费流量
     * @param type
     * @return
     * @throws SQLException
     */
    public static List<FlowStore> AcctCharged(String type) throws SQLException {
        List<FlowStore> listAcct=new ArrayList<FlowStore>();
        String sql="";
        Calendar cal = Calendar.getInstance();
        Date curdatenow =cal.getTime();
        if(type=="dayEmail"){ //查询的是当天的记录数
            cal.add(Calendar.DATE,   -1);
            Date curdateday= cal.getTime();
            SimpleDateFormat dt= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dt.format(curdatenow);
            dt.format(curdateday);

            sql="SELECT flowtotal,accountname from(SELECT sum(u.flowValue)as flowtotal,accountId FROM userchargedflowrecords u where u.getStatus='自取'  AND u.resultReason='充值成功' and u.requestDate BETWEEN '"+dt.format(curdateday)+"' AND '"+dt.format(curdatenow)+"' GROUP BY u.accountId ORDER BY SUM(u.flowValue) DESC ) a JOIN weixin_account w ON w.id=a.accountId;";
        }else if(type=="weekEmail"){ //查询的是本周的记录数
            cal.add(Calendar.DATE,   -7);
            Date curdateweek= cal.getTime();
            SimpleDateFormat dt= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dt.format(curdateweek);

            sql="SELECT flowtotal,accountname from(SELECT sum(u.flowValue)as flowtotal,accountId FROM userchargedflowrecords u where u.getStatus='自取'  AND u.resultReason='充值成功' and u.requestDate BETWEEN '"+dt.format(curdateweek)+"' AND '"+dt.format(curdatenow)+"' GROUP BY u.accountId ORDER BY SUM(u.flowValue) DESC ) a JOIN weixin_account w ON w.id=a.accountId;";
        }else { //查询的是当月的记录数
            cal.add(Calendar.MONTH,-1);
            Date curdatemonth= cal.getTime();
            SimpleDateFormat dt= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dt.format(curdatemonth);

            sql="SELECT flowtotal,accountname from(SELECT sum(u.flowValue)as flowtotal,accountId FROM userchargedflowrecords u where u.getStatus='自取'  AND u.resultReason='充值成功' and u.requestDate BETWEEN '"+dt.format(curdatemonth)+"' AND '"+dt.format(curdatenow)+"' GROUP BY u.accountId ORDER BY SUM(u.flowValue) DESC ) a JOIN weixin_account w ON w.id=a.accountId;";
        }
        try {
            //创建的jdbc连接语句
            connection = ConnectionsManager.getMysqlConn();
            stmt = connection.createStatement();
            es = stmt.executeQuery(sql);
            while (es.next()) {
                FlowStore f=new FlowStore();
                f.setFlow(es.getDouble("flowtotal"));
                f.setAccountName(es.getString("accountname"));
                listAcct.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            es.close();
            stmt.close();
            connection.close();
        }
        return listAcct;
    }



    public static void main(String[] args) {
        double num=0.01;
        //获取格式化对象
        NumberFormat nt = NumberFormat.getPercentInstance();
        //设置百分数精确度2即保留两位小数
        nt.setMinimumFractionDigits(2);
        nt.format(num);  //将小数转为百分数
        LOGGER.info("nt.format(num) = " + nt.format(num));
    }

}
