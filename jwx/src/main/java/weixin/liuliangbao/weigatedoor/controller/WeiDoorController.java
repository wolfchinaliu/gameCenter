package weixin.liuliangbao.weigatedoor.controller;

import com.google.gson.Gson;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.liuliangbao.jsonbean.FlowMainEntity;
import weixin.liuliangbao.weigatedoor.entity.WeidoorpptEntity;
import weixin.oauth2.AdvancedUtil;
import weixin.oauth2.SNSUserInfo;
import weixin.oauth2.WeixinOauth2Token;
import weixin.source.controller.WeixinSourceController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aa on 2015/12/10.
 */
@Controller
@RequestMapping("/weiDoorController")
public class WeiDoorController extends BaseController {

    private static final Logger LOGGER = Logger.getLogger(WeiDoorController.class);

    @Autowired
    private SystemService systemService;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @RequestMapping(params = "testgoWeidoor")
    public ModelAndView testgoWeidoor(HttpServletRequest request){
        String accountid="402881e55185f10f015185f401680002";
        String nickname="许小乖ゾ";
        String openid="oUYCmwDu3Y48oPYeJsXvYXNrpbqs";
        String headimgUrl ="http://wx.qlogo.cn/mmopen/CttmTaYSYkTgX37xwUBp1olhzNIzOxoWX8khp930nw9R8ZlfEC1VYCFt4T38Tict0xwSRaE53Slmv61r3f9f7fw/0";
        return new ModelAndView("liuliangbao/weigatedoor/weidoor");
    }

    /**
     * 微门户 页面跳转 -许丹-2015年12月10日10:19:19
     *
     * @return
     */
    @RequestMapping(params = "goWeidoor")
    public ModelAndView goWeidoor(HttpServletRequest request) {
        ModelAndView mav=new ModelAndView();
        String linkType = "门户";
        //通过页面位置和公众号ID去查图片，返回一个list（图片、图片跳转url）
        String accountid = (String) request.getSession().getAttribute("accountid");
        String nickname = (String) request.getSession().getAttribute("nickname");
        String headimgUrl = (String) request.getSession().getAttribute("headimgUrl");

        //根据accountId和linkType去查图片名称
        String hql = "from WeidoorpptEntity where accountid='" + accountid + "' and pageLocation='" + linkType + "'";
        List<WeidoorpptEntity> weidoorpptList = this.systemService.findHql(hql, null);
//        LOGGER.info(weidoorpptList);


        //获得图片路径
        String prefixUrl = ResourceUtil.getMediaUrlPrefix();
//        LOGGER.info(prefixUrl);

        //TODO：根据weidoorpptList中的图片url放入一个数组中或者一个String  list中   每个url加上图片路径prefixUrl

        List<WeidoorpptEntity> weidoorpptListResult=new ArrayList<WeidoorpptEntity>();
        for (int i=0;i<weidoorpptList.size();i++){
            WeidoorpptEntity weidoor=new WeidoorpptEntity();
            weidoor.setId(weidoorpptList.get(i).getId());
            weidoor.setTitle(weidoorpptList.get(i).getTitle());
            weidoor.setPictureName(weidoorpptList.get(i).getPictureName());

            weidoor.setPictureUrl(prefixUrl + "/" +weidoorpptList.get(i).getPictureUrl());

            weidoor.setJumpType(weidoorpptList.get(i).getJumpType());
            weidoor.setJumpUrl(weidoorpptList.get(i).getJumpUrl());
            weidoor.setOperatetime(weidoorpptList.get(i).getOperatetime());
            weidoor.setAccountid(weidoorpptList.get(i).getAccountid());
            weidoor.setDescription(weidoorpptList.get(i).getDescription());
            weidoor.setPageLocation(weidoorpptList.get(i).getPageLocation());
            weidoorpptListResult.add(weidoor);
        }

        /*//图片名称和url拼接号url
        String imagePathName = prefixUrl + "/" + doorImgUrl;
        LOGGER.info(imagePathName);
*/
        mav.addObject("weidoorpptlist",weidoorpptListResult);
        mav.setViewName("liuliangbao/weigatedoor/weidoor");
        return mav;
        //return new ModelAndView("liuliangbao/weigatedoor/weidoor");
    }


    @RequestMapping(params = "goFlowCenter")
    public void goFlowCenter(HttpServletRequest request,HttpServletResponse response)throws IOException {
        LOGGER.info("--------------------goFlowCenter---begin-----------------");
        String openid = request.getParameter("openId");
        String accountid = request.getParameter("accountid");
        String nickname = request.getParameter("nickname");
        String headimgUrl=request.getParameter("headimgUrl");
        LOGGER.info(openid);
        LOGGER.info(accountid+nickname+headimgUrl);



        ObjectMapper objectMapper = new ObjectMapper();
        Gson gson = new Gson();
        JSONObject myJsonObject = new JSONObject();
        String json = objectMapper.writeValueAsString(headimgUrl);
        PrintWriter out = response.getWriter();
        out.write(json);
    }







    /**
     * 引导授权界面
     *
     * @param request
     */
    @RequestMapping(params = "startWeidoor")
    public ModelAndView startWeidoor(HttpServletRequest request, HttpServletResponse response) {
        String hdid = request.getParameter("hdid");
        FlowMainEntity hdEntity = this.systemService.get(FlowMainEntity.class, hdid);
        if (hdEntity == null) {
            return new ModelAndView("liuliangbao/weigatedoor/weidoorNotExists");
        }
        String rdUrl = "weiDoorController.do?goRedirectWeidoor";
        String accountid = hdEntity.getAccountid();
        request.setAttribute("accountid", accountid);
        WeixinAccountEntity account = this.systemService.get(WeixinAccountEntity.class, accountid);
        String requestUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
        requestUrl = requestUrl.replace("APPID", account.getAccountappid());
        requestUrl = requestUrl.replace("SCOPE", "snsapi_userinfo");
        requestUrl = requestUrl.replace("STATE", hdid);
        String path = request.getContextPath();
        String localhosturl = request.getScheme() + "://" + request.getServerName() + path + "/";
        String url = "";
        try {
            url = URLEncoder.encode(localhosturl + rdUrl, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        requestUrl = requestUrl.replace("REDIRECT_URI", url);
        LOGGER.info(requestUrl);
        return new ModelAndView("redirect:" + requestUrl);
    }


    @RequestMapping(params = "goRedirectWeidoor")
    public ModelAndView goRedirectWeidoor(HttpServletRequest request) {
        String hdid = request.getParameter("state");
        if (hdid == null || "".equals(hdid)) {
            return new ModelAndView("liuliangbao/weigatedoor/weidoorNotExists");
        }
        String hdNotUrl = "liuliangbao/weigatedoor/weidoorNotExists";
        String code = request.getParameter("code");
        if (!"authdeny".equals(code)) {
            FlowMainEntity hdEntity = this.systemService.get(FlowMainEntity.class, hdid);
            String accountid = hdEntity.getAccountid();
            WeixinAccountEntity account = this.systemService.get(WeixinAccountEntity.class, accountid);
            // 获取网页授权access_token
            WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(account.getAccountappid(),
                    account.getAccountappsecret(), code);
            // 网页授权接口访问凭证
            String accessToken = weixinOauth2Token.getAccessToken();
            // 用户标识
            String openId = weixinOauth2Token.getOpenId();
            // 获取用户信息
            SNSUserInfo snsUserInfo = AdvancedUtil.getSnsUserInfo(accessToken, openId); // 设置要传递的参数
            request.getSession().setAttribute("hdId", hdid);
            request.getSession().setAttribute("openId", openId);
            request.getSession().setAttribute("accountid", accountid);
            request.getSession().setAttribute("nickname", snsUserInfo.getNickName());
            LOGGER.info(snsUserInfo.getNickName());
            LOGGER.info(snsUserInfo.getHeadImgUrl());
            request.getSession().setAttribute("headimgUrl", snsUserInfo.getHeadImgUrl());
            return new ModelAndView("redirect:" + "weiDoorController.do?goWeidoor");
        } else {
            return new ModelAndView(hdNotUrl);
        }
    }


}
