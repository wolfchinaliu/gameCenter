package weixin.source.controller;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.util.*;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import weixin.activity.entity.WeixinActivityEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.liuliangbao.jsonbean.ShareItem;
import weixin.lottery.entity.WeixinGuessRiddleEntity;
import weixin.lottery.entity.WeixinLotteryEntity;
import weixin.personalredpacket.entity.PersonalRedpacketSetEntity;
import weixin.source.entity.WeixinUrlEntity;
import weixin.source.service.WeixinSourceServiceI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;


/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: Controller
 * @Description: 素材管理
 * @date 2015-01-29 10:45:04
 */
@Controller
@RequestMapping("/weixinUrlController")
public class WeixinUrlController extends BaseController {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(WeixinUrlController.class);

    @Autowired
    private SystemService systemService;

    /**
     * 跳转类型查询
     * @return
     */
    @RequestMapping(params = "weixinUrlFirst")
    public void weixinUrlFirst(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //调用后台的查询所有商户的信息的接口方法
        //输出到页面
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        Gson gson = new Gson();

        List<WeixinUrlEntity> weixinUrlEntities = this.systemService.findHql("from WeixinUrlEntity t where t.parentid=null or t.parentid=''", null);

        String json = gson.toJson(weixinUrlEntities);

        out.write(json);

    }

    /**
     * 拼接流量页面
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "weixinUrlFlow")
    public void weixinUrlFlow(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //调用后台的查询所有商户的信息的接口方法
        //输出到页面
        String flag=request.getParameter("flag");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        List<WeixinUrlEntity> weixinUrlEntitiesflow=new ArrayList<WeixinUrlEntity>();
        String accountid=ResourceUtil.getWeiXinAccountId();
        List<ShareItem> shareItemList=this.systemService.findHql("from ShareItem t where t.accountid=?",accountid);
        if(flag!=null){
            if(shareItemList.size()>0){
                weixinUrlEntitiesflow= this.systemService.findHql("from WeixinUrlEntity t where t.parentid=?", "412883874c557c9a014c557d38af0001");

            }else{
                weixinUrlEntitiesflow= this.systemService.findHql("from WeixinUrlEntity t where t.parentid='412883874c557c9a014c557d38af0001' and t.id!='412883874c557c9a014c557d38af0008'");
            }
        }else{
            weixinUrlEntitiesflow= this.systemService.findHql("from WeixinUrlEntity t where t.parentid='412883874c557c9a014c557d38af0001' and t.id!='412883874c557c9a014c557d38af0008'");
        }


        //查询网站域名和项目名
        String webroot = ResourceUtil.getConfigByName("domain");

        //微信固定页面的连接 TODO：替换url即可
        for (int i = 0; i < weixinUrlEntitiesflow.size(); i++) {
            weixinUrlEntitiesflow.get(i).setUrl(webroot + "/" + weixinUrlEntitiesflow.get(i).getUrl() + "&accountid=" + ResourceUtil.getWeiXinAccountId());
        }

        String json = gson.toJson(weixinUrlEntitiesflow);
        out.write(json);
    }

    /**
     * 拼接活动列表
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "weixinUrlLottery")
    public void weixinUrlLottery(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //输出到页面
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();

        //微信活动列表（页面微信活动的下拉框）
        List<WeixinUrlEntity> weixinUrlLottery = new ArrayList<WeixinUrlEntity>();
        //查询网站域名和项目名
        String webroot = ResourceUtil.getConfigByName("domain");
        //查询该商户活动列表，取出hdid和title
        String accountid = ResourceUtil.getWeiXinAccountId();
        String hql = "from WeixinLotteryEntity where accountid='" + accountid + "' and (state='1' or state='2')";
        List<WeixinLotteryEntity> weixinLotteryEntities = this.systemService.findHql(hql, null);
//        List<WeixinLotteryEntity> weixinLotteryEntities = this.systemService.findByProperty(WeixinLotteryEntity.class,"accountid", accountid);
        if (weixinLotteryEntities.size() > 0) {
            for (int i = 0; i < weixinLotteryEntities.size(); i++) {
                WeixinUrlEntity weixinUrl = new WeixinUrlEntity();
                weixinUrl.setName(weixinLotteryEntities.get(i).getTitle());   //title 即 name
                weixinUrl.setUrl(webroot + "/" + "lotteryController.do?startLottery&hdid=" + weixinLotteryEntities.get(i).getId());   //url 拼接id
                weixinUrlLottery.add(weixinUrl);
            }
        }
            List<WeixinGuessRiddleEntity> guessRiddleEntityList=this.systemService.findHql(" from WeixinGuessRiddleEntity  where  accountid='" + ResourceUtil.getWeiXinAccountId() + "' and (state='1' or state='2')");
            if(guessRiddleEntityList.size()>0){
                WeixinUrlEntity weixinUrl = new WeixinUrlEntity();
                 weixinUrl.setName(guessRiddleEntityList.get(0).getTitle());
                weixinUrl.setUrl(webroot + "/" + "guessRiddlesController.do?startLottery&hdid=" + guessRiddleEntityList.get(0).getId());
                weixinUrlLottery.add(weixinUrl);
            }
            List<PersonalRedpacketSetEntity> personalRedpacketSetEntityList=this.systemService.findByQueryString("from PersonalRedpacketSetEntity  where accountid='" + ResourceUtil.getWeiXinAccountId() + "' and (state='1' or state='2')");
            if(personalRedpacketSetEntityList.size()>0){
                WeixinUrlEntity weixinUrl = new WeixinUrlEntity();
                weixinUrl.setName(personalRedpacketSetEntityList.get(0).getTitle());
                weixinUrl.setUrl(webroot+"/"+"makeRedpacketController.do?makeRedpacket&hdid="+personalRedpacketSetEntityList.get(0).getId());
                weixinUrlLottery.add(weixinUrl);
            }
            
        Date now = new Date();
        String activityHql = "from WeixinActivityEntity where accountid=? and startTime < ? and endTime > ?";
        List<WeixinActivityEntity> activityEntities = this.systemService.findHql(activityHql, accountid,now,now);
        for(WeixinActivityEntity activityEntity : activityEntities){
        	 WeixinUrlEntity weixinUrl = new WeixinUrlEntity();
             weixinUrl.setName(activityEntity.getTitle());   //title 即 name
             weixinUrl.setUrl(webroot + "/" + "activityController.do?startActivity&hdid=" + activityEntity.getId());   //url 拼接id
             weixinUrlLottery.add(weixinUrl);
        }
        String json = gson.toJson(weixinUrlLottery);
        out.write(json);
    }

    /**
     * 测试页面
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(params = "weixinUrl")
    public ModelAndView weixinUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return new ModelAndView("weixin/source/url");
    }


}
