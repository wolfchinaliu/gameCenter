package weixin.member.controller;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.liuliangbao.jsonbean.MoreFlow.gameFlow;
import weixin.lottery.entity.WeixinLotteryEntity;
import weixin.lottery.service.WeixinLotteryServiceI;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinMemberServiceI;
import weixin.tenant.entity.WeixinAcctEntity;
import weixin.tenant.entity.weixinAcctFlowAccountEntity;
import weixin.tenant.service.WeixinAcctServiceI;

import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Created by aa on 2015/12/11.(点击游戏送流量)
 */
@Controller
@RequestMapping("/gameFlowController")
public class GameFlowController extends BaseController {
    /**
     * Logger for this class write for LOGGER
     */
    private static final Logger logger = Logger.getLogger(WeixinCoinJournalController.class);
    @Autowired
    private WeixinAccountServiceI weixinAccountService;   //引入微信公众账号的信息
    @Autowired
    private WeixinMemberServiceI weixinMemberService; //引入的是粉丝管理实体
    @Autowired
    private WeixinAcctServiceI weixinAcctService;

    @Autowired
    private WeixinLotteryServiceI weixinLotteryService;

    @Autowired
    private SystemService systemService;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

//   关注和游戏以及分享和签到的按钮都是在一个页面的

    /**
     * 用户点击关注时的跳转页面显示,跳转页面的时候是不需要添加注解	@ResponseBody
     *
     * @return
     */
    @RequestMapping(params = "myLotty")
    public ModelAndView subscribeAcctListPage(HttpServletRequest request) throws Exception {
        StringBuilder subscribeAcctListPage = new StringBuilder();
        long startTime = System.currentTimeMillis();//获取开始当前的时间   查询方法的执行时间
        subscribeAcctListPage.append("方法开始时间_" + startTime + "ms" + "_");  //添加的日志

        String messageInfo = null;

        String openId = request.getParameter("openId");
        subscribeAcctListPage.append("获取的openId" + openId + "_");  //添加的日志
        request.setAttribute("openId", openId);
//从页面获取OpenId
//        String mOpenId1 = request.getParameter("MopenId");

//        模拟测试使用的效果图
//        String mOpenId1 = "ov5lNs4x6VIE2ZasPfsCyezGZZnc";
//        根据用户关注某个商户获取到的隶属于这个商户的openId,来查询微信公众账号的信息
        WeixinAccountEntity weixinAccountEntity = new WeixinAccountEntity();
        WeixinMemberEntity weixinMemberEntity = new WeixinMemberEntity();
//        WeixinMemberEntity weixinMemberEntity = new WeixinMemberEntity();

//        weixinMemberEntity = weixinMemberService.findUniqueByProperty(WeixinMemberEntity.class, "openId", mOpenId1);
//        直接就可以获取商户id
        String account_id = request.getParameter("accountid");
        if (account_id == null || account_id.equals("")) {
            weixinMemberEntity = weixinMemberService.findUniqueByProperty(WeixinMemberEntity.class, "openId", openId);
            account_id = weixinMemberEntity.getAccountId();
        }
        weixinAccountEntity = weixinAccountService.getEntity(WeixinAccountEntity.class, account_id);
        subscribeAcctListPage.append("获取的account_id" + account_id + "_");  //添加的日志

//根据公众账号的实体可以获取到租户或者说是商户管理表

        String id = weixinAccountEntity.getAcctId();
        WeixinAcctEntity weixinAcctEntity = new WeixinAcctEntity();
        try {
            weixinAcctEntity = weixinAcctService.getEntity(WeixinAcctEntity.class, id);
//            weixinAcctEntity = weixinAcctService.findUniqueByProperty(WeixinAcctEntity.class, "id", tenantId);
            if (weixinAcctEntity.getId() != null || !weixinAcctEntity.getId().equals("")) {
                message = "查询结果不为空";
            } else {
                message = "查询结果为空值";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //        获取当前的商户所在的省份
        String provinceName = weixinAcctEntity.getProvince();
        subscribeAcctListPage.append("获取的当前商户的省provinceName" + provinceName + "_");  //添加的日志
        //当前的商业类型
        String accType = weixinAcctEntity.getBusinessType();
        subscribeAcctListPage.append("获取的当前商户商业类型" + accType + "_");  //添加的日志

//        获取当前的商户id
        String accId = weixinAcctEntity.getId();
        subscribeAcctListPage.append("获取的当前商户id" + accId + "_");  //添加的日志

//        定义接收的实体集合
//        List<WeixinLotteryEntity> lisLotty = new ArrayList<WeixinLotteryEntity>();
        List<gameFlow> lisLotty = new ArrayList<gameFlow>();

        List<gameFlow> tempList = new ArrayList<gameFlow>();  //过滤空的集合的
//        读取配置文件使用原始的jdbc进行实现
//        String sql = "select m.*,w.logoAccount,w.accountname from weixin_lotterylxc m JOIN weixin_account w JOIN weixin_acct a  where m.accountid=w.id and w.acct_id=a.id and a.province='" + provinceName + "' and (a.business_type<>'" + accType + "' or a.id='" + accId + "') and (m.state='1' or m.state='2')";
        String sql = "select m.*,w.logoAccount,w.accountname from weixin_lotterylxc m JOIN weixin_account w JOIN weixin_acct a  where m.accountid=w.id and w.acct_id=a.id and (a.business_type<>'" + accType + "' or a.id='" + accId + "') and (m.state='1' or m.state='2')"; //只是保留行业互斥
        LogUtil.info("查询的游戏的sql语句----------------------" + sql);
        subscribeAcctListPage.append("查询是本省的并且是不同商业类型的包含自己的游戏列表的sql语句" + sql + "_");  //添加的日志
        logger.info(sql);
//        select w.* from weixin_lottery w join weixin_account a join weixin_acct t where w.accountid=a.id and a.acct_id=t.id and t.province='江西省' and (t.business_type<>2 or t.id='4028b881516b211101516b22dad60001')
//        String sql = "SELECT id,title from weixin_lottery";
//        获取jdbc连接
        Connection connection = null;
        Statement stmt = null;
        ResultSet es = null;
        try {
            connection = ConnectionsManager.getMysqlConn();

            stmt = connection.createStatement();
            es = stmt.executeQuery(sql);
            es.last();  //指针移到最后一行
            if (es.getRow() == 0) {
                messageInfo = "暂时没有商家相关活动，不好意思";
            }
            es.beforeFirst();  //复位结果集
            //获取读取配置文件的地址，从而加上图片的名字就是我们需要查找的地址

            String prefixurl = ResourceUtil.getConfigByName("media.url.prefix");
            while (es.next()) {
//                WeixinLotteryEntity en = new WeixinLotteryEntity();
                gameFlow en = new gameFlow();
                en.setAccountid(es.getString("accountid"));
                en.setId(es.getString("id"));
                en.setTitle(es.getString("title"));
                en.setCreateName(es.getString("create_name"));
//                en.setAccountid(es.getString("accountid"));
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
                    en.setFlowValue(new BigDecimal(hongbaoValue).setScale(2, BigDecimal.ROUND_CEILING).doubleValue());
                }

                lisLotty.add(en);
                subscribeAcctListPage.append("查询是本省的并且是不同商业类型的包含自己的游戏列表的结果集个数" + lisLotty.size() + "_");  //添加的日志

            }
            //循环遍历通过查找是否存在相同的id进行过滤  ,不需要进行过滤了
//            for (gameFlow mg : lisLotty) {
//                if (hasTarget(tempList, mg.getAccountid())) {
//                    continue;
//                }
//                tempList.add(mg);
//            }


        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            es.close();
            stmt.close();
            connection.close();
        }
        request.setAttribute("message", messageInfo);    //相关的提示信息，来处理结果集为空的现象
//        request.setAttribute("listFor", tempList);
        request.setAttribute("listFor", lisLotty);
        long endTime = System.currentTimeMillis();//获取结束的当前时间   检测程序的运行时间
        subscribeAcctListPage.append("方法结束时间_" + endTime + "ms" + "_");  //添加的日志

        long totaltime = endTime - startTime;  //总耗时
        subscribeAcctListPage.append("方法执行总的时间" + totaltime + "ms");  //添加的日志

        logger.info("gameFlowController的__subscribeAcctListPage__方法执行过程中的各个操作的输入输出参数以及结果_" + subscribeAcctListPage.toString());
        return new ModelAndView("weixin/member/LottyFlow/gameFlow");
    }

    //循环过滤掉accountId相同的列表
    public static boolean hasTarget(List<gameFlow> tempList, String accountid) {
        for (gameFlow gm : tempList) {
            if (gm.getAccountid().equals(accountid)) {
                return true;
            }
        }
        return false;
    }


}
