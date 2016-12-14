package weixin.mailmanager.mailHelper;


import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import org.apache.fop.fo.flow.Flow;
import org.apache.log4j.Logger;
import org.jeecgframework.core.util.ResourceUtil;

import org.jeecgframework.minidao.annotation.Sql;
import weixin.mailmanager.Service.MailManagerServiceI;
import weixin.mailmanager.entity.*;
import weixin.mailmanager.util.SqlHelper;
import weixin.util.DataDictionaryUtil.FlowType;

import javax.mail.internet.InternetAddress;


/**
 * 邮件发送工具类：这里主要根据邮件的回执内容进行相应信息的展示
 *
 * @author lxc
 */
public class MailUitl {
    private static final Logger logger = Logger.getLogger(MailUitl.class);
    public static String EMAILREG = "emailreg";    //注册
    public static String EMAILFINDPASS = "emailfindpass";    //找回密码
    public static String EMAILBIND = "emailbind";    //绑定

    /**
     * type包含四种类型
     * 1、版本邮件（versionEmail）
     * 2、每天邮件(dayEmail)
     * 3、每周邮件(weekEmail)
     * 4、每月邮件(monthEmail)
     */
    public static String VERSIONEMAIL = "versionEmail";    //版本发布
    public static String DAYEMAIL = "dayEmail";    //日吸粉
    public static String WEEKEMAIL = "weekEmail";    //周吸粉
    public static String MONTHEMAIL = "monthEmail";    //月吸粉
    public static String OPEREMAIL = "operEmail";    //运维邮件
    private MailManagerServiceI mailManagerServiceI;


    MemberEntity lisNum = new MemberEntity(); //各类商户数目集合
    MemberEntity enMember2 = new MemberEntity();    //总的粉丝数目
    MemberEntity enMember3 = new MemberEntity();    //新增粉丝数据
    MemberEntity enMember4 = new MemberEntity();    //访问人数
    MemberEntity enMember5 = new MemberEntity();    //访问人次
    FlowStore enFlowStore1 = new FlowStore();    //获取记录
    List<FlowStore> listFlowStore = new ArrayList<FlowStore>(); //提现记录
    List<FlowStore> listsflow = new ArrayList<FlowStore>();//s级销售量
    List<FlowStore> listAcct = new ArrayList<FlowStore>();//商户流量消费
    int merchantNum = 0;    //新增商户总数
    int memberTotal = 0;    //总共的粉丝数
    int memberNum = 0;       //访问人数
    int visitorsNum = 0;  //访问人次
    double getFlow = 0.0;    //提现记录
    double giveFlow = 0.0;    //获取记录
    double active = 0.0;      //活跃度
    double highActive = 0.0;  //最高活跃度
    double flowCharges = 0.0; //用户提现总流量
    String flowChargeGroup = "无";//提现流量分运营商显示
    String content = "无数据";    //邮件正文


    /**
     * 发送邮件的方法
     *
     * @param to :收件人
     */
//    调用的时候直接使用    MailUitl.sendMail(),单个发邮件，遍历邮箱中的地址，进行单个人的发送
//    to,发送给某人
    public Integer sendMail(String type) {

        //配置邮件
        try {
            MailSenderInfo mailInfo = new MailSenderInfo();
            mailInfo.setMailServerHost(ResourceUtil.getMailServerhost());    //邮件服务器
            mailInfo.setMailServerPort(ResourceUtil.getMailServerport());    //邮件端口
            mailInfo.setValidate(true);
            mailInfo.setUserName(ResourceUtil.getMailFromaddress());    //邮箱
            mailInfo.setPassword(ResourceUtil.getMailFromaddressPassword());//您的邮箱密码
            mailInfo.setFromAddress(ResourceUtil.getMailFromaddress());
            if (Objects.equals(type, "dayMail")) {
                mailInfo.setSubject("每日运行数据");
                content = dayMail();
            }

            if (Objects.equals(type, "weekMail")) {
                mailInfo.setSubject("每周运营数据");
                content = weekMail();
            }

            if (Objects.equals(type, "monthMail")) {
                mailInfo.setSubject("每月运营数据");
                content = monthMail();
            }
            mailInfo.setContent(content);
            mailInfo.setNickname(ResourceUtil.getMailFromaddressNickname());    //发件人名称
            SimpleMailSender sms = new SimpleMailSender();
            String accept = ResourceUtil.getMailToaddress();
            String[] acceptAddress = accept.split(",");
            boolean result = false;
            for (int i = 0; i < acceptAddress.length; i++) {
                mailInfo.setToAddress(acceptAddress[i]);
                result = sms.sendHtmlMail(mailInfo);//发送html格式
            }

            if (result == true) {
                return CodeEnum.success.getValue();
            } else {
                return CodeEnum.error.getValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CodeEnum.error.getValue();
        }
    }

    //发送版本管理内容
    public static Boolean sendVersionMail(VersionEntity versionEntity) {
        /**
         * 版本管理内容----2016年5月9日
         */
        String mailSubject = "";
        String versionnum = "";
        String publishtime = "";
        String addcontent = "";
        String imporvecontent = "";
        String deletecontent = "";
        String content = "";    //邮件正文
        versionnum = ResourceUtil.getMailContentVersion();

        String receiver = "";
        String[] receivers = null;
        boolean result = false;
        if (!(versionEntity == null)) {
            versionnum = versionEntity.getVersionNO();
            publishtime = versionEntity.getPublishDate();
            addcontent = versionEntity.getAddContent();
            imporvecontent = versionEntity.getImproveContent();
            deletecontent = versionEntity.getDeleteContent();
            mailSubject = versionEntity.getMainsubject();
        }
        try {
            MailSenderInfo mailInfo = new MailSenderInfo();
            mailInfo.setMailServerHost(ResourceUtil.getMailServerhost());    //邮件服务器
            mailInfo.setMailServerPort(ResourceUtil.getMailServerport());    //邮件端口
            mailInfo.setValidate(true);
            mailInfo.setUserName(ResourceUtil.getMailFromaddress());    //邮箱
            mailInfo.setPassword(ResourceUtil.getMailFromaddressPassword());//您的邮箱密码
            mailInfo.setFromAddress(ResourceUtil.getMailFromaddress());
            mailInfo.setSubject(mailSubject + versionnum);    //主题
            content = "新版本发布" + "版本号" + versionnum + "发布时间" + publishtime + "新增" + addcontent + "优化" + imporvecontent + "删除" + deletecontent;
            mailInfo.setContent(content);
            mailInfo.setNickname(ResourceUtil.getMailFromaddressNickname());
            receiver = ResourceUtil.getMailToaddress();
            receivers = receiver.split(",");
            SimpleMailSender sms = new SimpleMailSender();
            if (receivers.length > 0) {

                for (int i = 0; i < receivers.length; i++) {
                    mailInfo.setToAddress(receivers[i]);
                    result = sms.sendHtmlMail(mailInfo);//发送html格式
                }
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }

    public String dayMail() {
        try {
            StringBuilder sb = new StringBuilder();
            long startTime = System.currentTimeMillis();//获取开始当前的时间   查询方法的执行时间
            sb.append("方法开始时间_" + startTime + "ms" + "_");  //添加的日志

            lisNum = SqlHelper.MerchantAdd(DAYEMAIL); //各类商户的每日递增数
            merchantNum = lisNum.getSumNum(); //商户总数
            enMember2 = SqlHelper.membersTotal();         //总粉丝数
            enMember3 = SqlHelper.membersAdd(DAYEMAIL); //新增粉丝的数目
            enMember4 = SqlHelper.viewMemberNum(DAYEMAIL);//访问人数
            enMember5 = SqlHelper.viewVisitNum(DAYEMAIL);//访问人次
            listsflow = SqlHelper.SFlowSale(DAYEMAIL); //S级销售量
            listAcct = SqlHelper.AcctCharged(DAYEMAIL);//商户流量消费
            double sflowAll = 0.0;
            String sflowtype = "无";
            String flowAcct = "无";
            double sflowCountry = 0.0;
            double sflowProvince = 0.0;
//                String highTotalMember="";
//                String highAddAcctid="";
//                String highVisitAcctid="";
//                String highVisitorsAcctid="";
//                String highGetFlowAcctid="";
//                if(enMember2.getAccountid()!=null){
//                    highTotalMember=enMember2.getAccountid();
//                }
//                if(enMember3.getAccountid()!=null){
//                    highAddAcctid=enMember3.getAccountid();
//                }
//                if(enMember4.getAccountid()!=null){
//                    highVisitAcctid=enMember4.getAccountid();
//                }
//                if(enMember5.getAccountid()!=null){
//                    highVisitorsAcctid=enMember5.getAccountid();
//                }
//                if(enFlowStore1.getAccountId()!=null){
//                    highGetFlowAcctid=enFlowStore1.getAccountId();
//                }
            if (listFlowStore.size() != 0) {
                for (int i = 0; i < listsflow.size(); i++) {
                    sflowAll = listFlowStore.get(i).getFlow();
                    sflowtype = listFlowStore.get(i).getFlowType() + listFlowStore.get(i).getFlow();
                    if (listsflow.get(i).getFlowType() == FlowType.country.getCode()) {
                        sflowCountry = listsflow.get(i).getFlow();
                    } else {
                        sflowProvince = listsflow.get(i).getFlow();
                    }
                }
            }
            if (enMember2.getSumNum() != 0) {
                active = enMember4.getSumNum() / enMember2.getSumNum();       //活跃度
            }
            if (enMember4.getSumNum() != 0) {
                highActive = enMember4.getMaxNum() / enMember2.getSumNum(); //最高活跃度
            }
            enFlowStore1 = SqlHelper.getFlow(DAYEMAIL);  //获取记录
            listFlowStore = SqlHelper.flowCharged(DAYEMAIL);//提现记录
            if (listFlowStore.size() != 0) {
                for (int i = 0; i < listFlowStore.size(); i++) {
                    flowCharges = listFlowStore.get(i).getFlow();
                    flowChargeGroup = listFlowStore.get(i).getBusinessName()+" " + flowCharges +"M";
                }
            }
            String addFuns = "";
            if (enMember3.getMaxNum() != 0) {
                addFuns = enMember3.getAccountName() +" "+ enMember3.getMaxNum();
            } else {
                addFuns = "0";
            }
            String addPersonCount = "";
            if (enMember4.getMaxNum() != 0) {
                addPersonCount = enMember4.getAccountName() +" "+ enMember4.getMaxNum();
            } else {
                addPersonCount = "0";
            }
            String addCount = "";
            if (enMember5.getMaxNum() != 0) {
                addCount = enMember5.getAccountName() +" " +enMember5.getMaxNum();
            } else {
                addCount = "0";
            }
            String getFlowStore = "";
            if (enFlowStore1.getFlow() != 0) {
                getFlowStore = enFlowStore1.getAccountName() +" "+ enFlowStore1.getFlow();
            } else {
                getFlowStore = "无";
            }
            if (listAcct.size() != 0) {
                for (int i = 0; i < listAcct.size(); i++) {
                    flowAcct = listAcct.get(i).getAccountName() + listAcct.get(i).getFlow();
                }
            }

            UUID uuid = UUID.randomUUID();
            String id = uuid.toString().replace("-", "");
//                OperationData operationData = new OperationData();
//                operationData.setId(id);
//                operationData.setAddAcctCount(merchantNum);
//                operationData.setAddAcctA(lisNum.getCountA());
//                operationData.setAddAcctB(lisNum.getCountB());
//                operationData.setAddAcctC(lisNum.getCountC());
//                operationData.setTotalMember(enMember2.getSumNum().doubleValue());
//                operationData.setHighTotalMember(enMember2.getMaxNum().doubleValue());
//                operationData.setHighTotalAcctid(highTotalMember);
//                operationData.setAddMember(enMember3.getSumNum().doubleValue());
//                operationData.setHighAddMember(enMember3.getMaxNum().doubleValue());
//                operationData.setHighAddAcctid(highAddAcctid);
//                operationData.setAddVisitMember(enMember4.getSumNum().doubleValue());
//                operationData.setHighVisitMember(enMember4.getMaxNum().doubleValue());
//                operationData.setHighVisitAcctid(highVisitAcctid);
//                operationData.setAddVisitors(enMember5.getSumNum().doubleValue());
//                operationData.setHighVisitors(enMember5.getMaxNum().doubleValue());
//                operationData.setHighVisitorsAcctid(highVisitorsAcctid);
//                operationData.setActivity(active);
//                operationData.setHighActivity(highActive);
//                operationData.setHighActivityAcctid(highVisitAcctid);
//                operationData.setChargeFlow(sflowAll);
//                operationData.setChargeCountry(sflowCountry);
//                operationData.setChargeProvince(sflowProvince);
//                operationData.setGetFlow(enFlowStore1.getFlowTotal().doubleValue());
//                operationData.setHighGetFlow(enFlowStore1.getFlow().doubleValue());
//                operationData.setHighGetFlowAcctid(highGetFlowAcctid);
//                operationData.setFlowCharged(flowCharges);
//                operationData.setAddDate(new Date());
//
//                mailManagerServiceI.save(operationData);

//                content = "尊敬的各位管理人：<br>运营数据<br>新增商户：" + merchantNum + "A级" + lisNum.getCountA() + "B级" + lisNum.getCountB() + "C级" + lisNum.getCountC()
//                        + "<br>总粉丝数：" + enMember2.getSumNum() + "最多：" + enMember2.getAccountName() + enMember2.getMaxNum()
//                        + "人<br>新增粉丝数：" + enMember3.getSumNum() + "最多：" + addFuns
//                        + "人<br>访问人数：" + enMember4.getSumNum() + "最多：" + addPersonCount
//                        + "人<br>访问人次:" + enMember5.getSumNum() + "最多：" + addCount
//                        + "人<br>活跃度：" + active + "% 最多：" + highActive
//                        + "<br>流量充值(S级):" + sflowAll + "M" + sflowtype
//                        + "<br>流量领取:" + enFlowStore1.getFlowTotal() + "最多：" + getFlowStore
//                        + "<br>流量提现:" + flowCharges + "M" + flowChargeGroup;

            //绑定邮箱模板
            ResourceBundle messages = ResourceUtil.getSystemResource();
            Object[] args = {merchantNum, lisNum.getCountA(), lisNum.getCountB(), lisNum.getCountC(), enMember2.getSumNum(), enMember2.getAccountName(), enMember2.getMaxNum(), enMember3.getSumNum(), addFuns, enMember4.getSumNum(), addPersonCount, enMember5.getSumNum(), addCount, active, highActive, sflowAll, sflowtype, enFlowStore1.getFlowTotal(), getFlowStore, flowCharges, flowChargeGroup, flowAcct};
            //新建标准的格式
            MessageFormat formatter = new MessageFormat("");
            String mess = new String((messages.getString("mail_content_day")).getBytes("ISO-8859-1"), "utf-8");
            //从资源文件中获取相应的模板信息
            formatter.applyPattern(mess);
            //填充模板
            content = formatter.format(args);

            String strSql = "";

            Date dt = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                strSql = "insert into operationdata (id,addAcctCount,addAcctA,addAcctB,addAcctC,totalMember,highTotalMember,highTotalAcctid,addMember,highAddMember,highAddAcctid,addVisitMember,highVisitMember,highVisitAcctid,addVisitors,highVisitors,highVisitorsAcctid,activity,highActivity,highActivityAcctid,chargeFlow,chargeCountry,chargeProvince,getFlow,highGetFlow,highGetFlowAcctid,flowCharged,addDate) "
//                        + "VALUES ('" + id + "'," + merchantNum + "," + lisNum.getCountA() + "," + lisNum.getCountB() + "," + lisNum.getCountC() + "," + enMember2.getSumNum() + "," + enMember2.getMaxNum() + ",'" + enMember2.getAccountid() + "'," + enMember3.getSumNum() + "," + enMember3.getMaxNum() + ",'" + enMember3.getAccountid() + "'," + enMember4.getSumNum() + "," + enMember4.getMaxNum() + ",'" + enMember4.getAccountid() + "'," + enMember5.getSumNum() + "," + enMember5.getMaxNum() + ",'" + enMember5.getAccountid() + "'," + active + "," + highActive + ",'" + enMember4.getAccountid() + "'," + sflowAll +","+sflowCountry+","+sflowProvince+ "," + enFlowStore1.getFlowTotal() + "," + enFlowStore1.getFlow()+",'"+enFlowStore1.getAccountId() + "'," + flowCharges +",'"+ sdf.format(dt)+"')";
//                SqlHelper.save(strSql);

            long endTime = System.currentTimeMillis();//获取结束的当前时间   检测程序的运行时间
            sb.append("方法结束时间_" + endTime + "ms" + "_");  //添加的日志

            long totaltime = endTime - startTime;  //总耗时
            sb.append("方法执行总的时间" + totaltime + "ms");  //添加的日志

            logger.info("MailUitl的__subscribeAcctListPage__方法执行过程中的各个操作的输入输出参数以及结果_" + sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    public String weekMail() {
        try {
            lisNum = SqlHelper.MerchantAdd(WEEKEMAIL); //各类商户的每日递增数
            merchantNum = lisNum.getSumNum(); //商户总数
            enMember2 = SqlHelper.membersTotal();         //总粉丝数
            enMember3 = SqlHelper.membersAdd(WEEKEMAIL); //新增粉丝的数目
            enMember4 = SqlHelper.viewMemberNum(WEEKEMAIL);//访问人数
            enMember5 = SqlHelper.viewVisitNum(WEEKEMAIL);//访问人次
            listsflow = SqlHelper.SFlowSale(WEEKEMAIL); //S级销售量
            enFlowStore1 = SqlHelper.getFlow(WEEKEMAIL);  //获取记录
            listFlowStore = SqlHelper.flowCharged(WEEKEMAIL);//提现记录
            double sflowAll = 0.0;
            String sflowtype = "";
            if (listFlowStore.size() != 0) {
                for (int i = 0; i < listsflow.size(); i++) {
                    sflowAll = listFlowStore.get(i).getFlow();
                    sflowtype = listFlowStore.get(i).getFlowType() + listFlowStore.get(i).getFlow();
                }
            }
            if (enMember2.getSumNum() != 0) {
                active = enMember4.getSumNum() / enMember2.getSumNum();       //活跃度
            }
            if (enMember4.getSumNum() != 0) {
                highActive = enMember4.getMaxNum() / enMember2.getSumNum();
            }

            if (listFlowStore.size() != 0) {
                for (int i = 0; i < listFlowStore.size(); i++) {
                    flowCharges = listFlowStore.get(i).getFlow();
                    flowChargeGroup = listFlowStore.get(i).getBusinessName() + listFlowStore.get(i).getBusinessName();
                }
            }
            String addFuns = "";
            if (enMember3.getMaxNum() != 0) {
                addFuns = enMember3.getAccountName() + enMember3.getMaxNum();
            } else {
                addFuns = "0";
            }
            String addPersonCount = "";
            if (enMember4.getMaxNum() != 0) {
                addPersonCount = enMember4.getAccountName() + enMember4.getMaxNum();
            } else {
                addPersonCount = "0";
            }
            String addCount = "";
            if (enMember5.getMaxNum() != 0) {
                addCount = enMember5.getAccountName() + enMember5.getMaxNum();
            } else {
                addCount = "0";
            }
            String getFlowStore = "";
            if (enFlowStore1.getFlow() != 0) {
                getFlowStore = enFlowStore1.getAccountName() + enFlowStore1.getFlow();
            } else {
                getFlowStore = "无";
            }
//            content="尊敬的各位管理人：<br>运营数据<br>新增商户："+merchantNum +"A级"+lisNum.getCountA()+"B级"+lisNum.getCountB()+"C级"+lisNum.getCountC()
//                    +"<br>总粉丝数："+enMember2.getSumNum()+"最多："+enMember2.getAccountName()+enMember2.getMaxNum()
//                    +"人<br>新增粉丝数："+enMember3.getSumNum()+"最多："+addFuns
//                    +"人<br>访问人数："+enMember4.getSumNum()+"最多："+addPersonCount
//                    +"人<br>访问人次:"+enMember5.getSumNum()+"最多："+addCount
//                    +"人<br>活跃度："+active +"% 最多："+highActive
//                    +"<br>流量充值(S级):" +sflowAll+"M"+sflowtype
//                    +"<br>流量领取:"+enFlowStore1.getFlowTotal()+"最多："+getFlowStore
//                    +"<br>流量提现:"+flowCharges+"M"+flowChargeGroup;
            ResourceBundle messages = ResourceUtil.getSystemResource();
            Object[] args = {merchantNum, lisNum.getCountA(), lisNum.getCountB(), lisNum.getCountC(), enMember2.getSumNum(), enMember2.getAccountName(), enMember2.getMaxNum(), enMember3.getSumNum(), addFuns, enMember4.getSumNum(), addPersonCount, enMember5.getSumNum(), addCount, active, highActive, sflowAll, sflowtype, enFlowStore1.getFlowTotal(), getFlowStore, flowCharges, flowChargeGroup};
            //新建标准的格式
            MessageFormat formatter = new MessageFormat("");
            String mess = new String((messages.getString("mail_content")).getBytes("ISO-8859-1"), "utf-8");
            //从资源文件中获取相应的模板信息
            formatter.applyPattern(mess);
            //填充模板
            content = formatter.format(args);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    public String monthMail() {
        try {

            lisNum = SqlHelper.MerchantAdd(MONTHEMAIL); //各类商户的每日递增数
            merchantNum = lisNum.getSumNum(); //商户总数
            enMember2 = SqlHelper.membersTotal();         //总粉丝数
            enMember3 = SqlHelper.membersAdd(MONTHEMAIL); //新增粉丝的数目
            enMember4 = SqlHelper.viewMemberNum(MONTHEMAIL);//访问人数
            enMember5 = SqlHelper.viewVisitNum(MONTHEMAIL);//访问人次
            listsflow = SqlHelper.SFlowSale(MONTHEMAIL); //S级销售量
            double sflowAll = 0.0;
            String sflowtype = "";
            if (listsflow.size() != 0) {
                for (int i = 0; i < listsflow.size(); i++) {
                    sflowAll = listFlowStore.get(i).getFlow();
                    sflowtype = listFlowStore.get(i).getFlowType() + listFlowStore.get(i).getFlow();
                }
            }
            if (enMember2.getSumNum() != 0) {
                active = enMember4.getSumNum() / enMember2.getSumNum();       //活跃度
            }
            if (enMember4.getSumNum() != 0) {
                highActive = enMember4.getMaxNum() / enMember2.getSumNum();
            }
            enFlowStore1 = SqlHelper.getFlow(MONTHEMAIL);  //获取记录
            listFlowStore = SqlHelper.flowCharged(MONTHEMAIL);//提现记录
            if (listFlowStore.size() != 0) {
                for (int i = 0; i < listFlowStore.size(); i++) {
                    flowCharges = listFlowStore.get(i).getFlow();
                    flowChargeGroup = listFlowStore.get(i).getBusinessName() + listFlowStore.get(i).getBusinessName();
                }
            }
            String addFuns = "";
            if (enMember3.getMaxNum() != 0) {
                addFuns = enMember3.getAccountName() + enMember3.getMaxNum();
            } else {
                addFuns = "0";
            }
            String addPersonCount = "";
            if (enMember4.getMaxNum() != 0 && enMember4.getAccountName()!=null) {
                addPersonCount = enMember4.getAccountName() + enMember4.getMaxNum();
            } else {
                addPersonCount = "0";
            }
            String addCount = "";
            if (enMember5.getMaxNum() != 0) {
                addCount = enMember5.getAccountName() + enMember5.getMaxNum();
            } else {
                addCount = "0";
            }
            String getFlowStore = "";
            if (enFlowStore1.getFlow() != 0) {
                getFlowStore = enFlowStore1.getAccountName() + enFlowStore1.getFlow();
            } else {
                getFlowStore = "无";
            }
//            content="尊敬的各位管理人：<br>运营数据<br>新增商户："+merchantNum +"A级"+lisNum.getCountA()+"B级"+lisNum.getCountB()+"C级"+lisNum.getCountC()
//                    +"<br>总粉丝数："+enMember2.getSumNum()+"最多："+enMember2.getAccountName()+enMember2.getMaxNum()
//                    +"人<br>新增粉丝数："+enMember3.getSumNum()+"最多："+addFuns
//                    +"人<br>访问人数："+enMember4.getSumNum()+"最多："+addPersonCount
//                    +"人<br>访问人次:"+enMember5.getSumNum()+"最多："+addCount
//                    +"人<br>活跃度："+active +"% 最多："+highActive
//                    +"<br>流量充值(S级):" +sflowAll+"M"+sflowtype
//                    +"<br>流量领取:"+enFlowStore1.getFlowTotal()+"最多："+getFlowStore
//                    +"<br>流量提现:"+flowCharges+"M"+flowChargeGroup;
            ResourceBundle messages = ResourceUtil.getSystemResource();
            Object[] args = {merchantNum, lisNum.getCountA(), lisNum.getCountB(), lisNum.getCountC(), enMember2.getSumNum(), enMember2.getAccountName(), enMember2.getMaxNum(), enMember3.getSumNum(), addFuns, enMember4.getSumNum(), addPersonCount, enMember5.getSumNum(), addCount, active, highActive, sflowAll, sflowtype, enFlowStore1.getFlowTotal(), getFlowStore, flowCharges, flowChargeGroup};
            //新建标准的格式
            MessageFormat formatter = new MessageFormat("");
            String mess = new String((messages.getString("mail_content")).getBytes("ISO-8859-1"), "utf-8");
            //从资源文件中获取相应的模板信息
            formatter.applyPattern(mess);
            //填充模板
            content = formatter.format(args);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("生成每月发送的运营数据失败， 具体请查看日志信息。", e);
        }
        return content;
    }


    public static void main(String[] args) {
        MailUitl mailUitl = new MailUitl();

        mailUitl.sendMail("weekMail");
    }

}
