package weixin.lottery.controller;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.liuliangbao.jsonbean.MoreFlow.attentionAndsignInFlowEntity;
import weixin.liuliangbao.jsonbean.MoreFlow.gameFlow;
import weixin.liuliangbao.jsonbean.MoreFlow.shareFlow;
import weixin.lottery.service.impl.MoreFlowService;
import weixin.tenant.entity.WeixinAcctEntity;
import weixin.tenant.service.WeixinAcctServiceI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by xiaona on 2016/2/17.
 */
@Scope("prototype")
@Controller
@RequestMapping("/moreFlowSelfController")
public class MoreFlowSelfController extends BaseController {


    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(MoreFlowSelfController.class);
    @Autowired
    private WeixinAccountServiceI weixinAccountService;
    @Autowired
    private WeixinAcctServiceI weixinAcctService;

    @RequestMapping(params = "moreFlowList")
    public ModelAndView moreFlowList(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String phoneNumber = request.getParameter("phoneNumber");
        String accountid = request.getParameter("accountid");
        String openId = request.getParameter("openId");

        request.setAttribute("phoneNumber", phoneNumber);    //用户的手机号
        request.setAttribute("openId", openId);  //用户的openId

//        根据accountid查询当前商户的商业类型
        WeixinAccountEntity weixinAccountEntity = weixinAccountService.getEntity(WeixinAccountEntity.class, accountid);

        WeixinAcctEntity weixinAcctEntity = weixinAcctService.getEntity(WeixinAcctEntity.class, weixinAccountEntity.getAcctId());

        String tyep = weixinAcctEntity.getBusinessType();
        MoreFlowService moreFlowService = new MoreFlowService();
        List<attentionAndsignInFlowEntity> moreGZEntities = moreFlowService.queryMoreGZ(phoneNumber, tyep);  //关注签到的集合获取
        List<attentionAndsignInFlowEntity> moreQDEntities = moreFlowService.queryMoreQD(phoneNumber, tyep);  //关注签到的集合获取
        List<gameFlow> moreGameEntities = moreFlowService.queryMoreGame(phoneNumber, tyep);
        List<shareFlow> moreShareEntities = moreFlowService.queryMoreShare(phoneNumber, tyep);
        request.setAttribute("mediaurl", ResourceUtil.getConfigByName("media.url.prefix") + "/");
        request.setAttribute("url", ResourceUtil.getConfigByName("domain") + "/");
        request.setAttribute("moreGZEntities", moreGZEntities);
        request.setAttribute("moreQDEntities", moreQDEntities);
        request.setAttribute("moreGameEntities", moreGameEntities);
        request.setAttribute("moreShareEntities", moreShareEntities);

        //todo:无数据进入悲剧页面
        if (moreGZEntities.size() == 0 && moreGameEntities.size() == 0 && moreShareEntities.size() == 0 && moreQDEntities.size() == 0) {
            return new ModelAndView("weixin/lottery/guessRiddlesPage/sad");
        }
        //如果存在数据，那么定义一个公共的集合，用于存储这些数据
        return new ModelAndView("weixin/lottery/guessRiddlesPage/moreFlowList");
    }
}
