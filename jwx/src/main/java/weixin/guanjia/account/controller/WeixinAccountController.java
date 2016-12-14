package weixin.guanjia.account.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import weixin.cms.entity.WeixinCmsSiteEntity;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.account.util.CheckPic;
import weixin.guanjia.menu.entity.MenuEntity;
import weixin.payment.entity.WeixinPaymentConEntity;
import weixin.shop.entity.WeixinShopEntity;
import weixin.tenant.entity.weixinAcctFlowAccountEntity;
import weixin.tenant.service.WeixinAcctFlowAccoutServiceI;
import weixin.util.WeiXinConstants;

/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: Controller
 * @Description: 微信公众帐号信息
 * @date 2014-05-21 00:53:47
 */
@Scope("prototype")
@Controller
@RequestMapping("/weixinAccountController")
public class WeixinAccountController extends BaseController {
    /**
     * Logger for this class
     */
    private static final Logger LOGGER = Logger.getLogger(WeixinAccountController.class);

    @Autowired
    private WeixinAccountServiceI weixinAccountService;
    @Autowired
    private SystemService systemService;
    private String message;
    @Autowired
    private WeixinAcctFlowAccoutServiceI weixinAcctFlowAccoutService;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 微信公众帐号信息列表 页面跳转
     *
     * @return
     */
    @RequestMapping(params = "weixinAccount")
    public ModelAndView weixinAccount(HttpServletRequest request) {
        //普通员工账号没有权限
        TSUser users = systemService.get(TSUser.class, ResourceUtil.getSessionUserName().getId());
//        if ("0".equals(users.getType())) {
//            return new ModelAndView("common/403");
//        }

        TSUser user = ResourceUtil.getSessionUserName();
        TSRole urole = user.getTSRole();

        request.setAttribute("urole", urole);
        request.setAttribute("url",ResourceUtil.getConfigByName("domain1"));
        return new ModelAndView("weixin/guanjia/account/weixinAccountList");

        //直接展示公众号配置-刘晓春-2015年11月30日
//		WeixinAccountEntity weixinAccountEntity=ResourceUtil.getWeiXinAccount();
//
//		request.setAttribute("weixinAccountPage",weixinAccountEntity);
//
//		return new ModelAndView("weixin/guanjia/account/weixinAccountllb");
    }

    /**
     * 更新微信公众帐号信息
     *
     * @param weixinAccount
     * @param request
     * @return
     */
    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(WeixinAccountEntity weixinAccount,
                             HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        message = "微信公众帐号信息更新成功";

        String mediaFileUrl = request.getParameter("imagePath");   //企业的二维码
        String mediaFileUrl1 = request.getParameter("imagePath1");  //企业logo
        String imageRelativeUrl = request.getParameter("imageRelativeUrl");//企业的二维码图片的相对路径
        String imageRelativeUrl1 = request.getParameter("imageRelativeUrl1");//企业的企业logo的相对路径
        String accountId = weixinAccount.getId();
//        System.out.println("------------>accountId:"+accountId
//        		+ "企业二维码："+mediaFileUrl+"企业logo:"+mediaFileUrl1
//        		+"企业的二维码图片的相对路径:"+imageRelativeUrl+"企业的企业logo的相对路径:"+imageRelativeUrl1+"<-----------------");
        if(imageRelativeUrl.contains(ResourceUtil.getMediaUrlPrefix())){ //判断二维码的相对地址是否包含域名
        	imageRelativeUrl = imageRelativeUrl.substring(imageRelativeUrl.lastIndexOf("/")+1);
        }
        if(imageRelativeUrl1.contains(ResourceUtil.getMediaUrlPrefix())){ //判断企业logo的相对地址是否包含域名
        	imageRelativeUrl1 = imageRelativeUrl1.substring(imageRelativeUrl1.lastIndexOf("/")+1);
        }
        weixinAccount.setQRcode(imageRelativeUrl);
        weixinAccount.setLogoAccount(imageRelativeUrl1);
        
        WeixinAccountEntity t = weixinAccountService.get(
                WeixinAccountEntity.class, weixinAccount.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(weixinAccount, t);
            weixinAccountService.saveOrUpdate(t);

            weixinAcctFlowAccountEntity weixinAcctFlowAccount = weixinAcctFlowAccoutService.findUniqueByProperty(weixinAcctFlowAccountEntity.class, "accountId", accountId);
            LOGGER.info(t.getAccountname());
            if (t.getAccountname() != null && !t.getAccountname().equals("")) {
                weixinAcctFlowAccount.setAccountName(t.getAccountname().toString());
                weixinAcctFlowAccoutService.saveOrUpdate(weixinAcctFlowAccount);
            } else {
                weixinAcctFlowAccoutService.saveOrUpdate(weixinAcctFlowAccount);
            }


            //systemService.addLog(message, Globals.Log_Type_UPDATE,
            //		Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "微信公众帐号信息更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        WeixinAccountEntity weixinAccountEntity = ResourceUtil.getWeiXinAccount();
        request.getSession().setAttribute(WeiXinConstants.WEIXIN_ACCOUNT, weixinAccountEntity);
        return j;

    }

    //文件保存路径的前缀 add by mike
    private static String filePathPrefix = null;

    //文件保存url的前缀 add by mike
    private static String fileUrlPrefix = null;

    //工具方法:从配置文件mediaFile.properties中,读取文件保存路径的前缀,比如:/Users/zhixu/Desktop/TEST_MEDIA
//    public static String getFilePathPrefix() {
//        if (filePathPrefix == null) {
//            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("mediaFile.properties");
//            Properties p = new Properties();
//            try {
//                p.load(is);
//                filePathPrefix = p.getProperty("media.path.prefix");
//                is.close();//关闭
//                is = null;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return filePathPrefix;
//    }

    //工具方法:从配置文件mediaFile.properties中,读取文件url的前缀,比如:http://localhost/xampp/images
//    public static String getFileUrlPrefix() {
//        if (fileUrlPrefix == null) {
//            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("mediaFile.properties");
//            Properties p = new Properties();
//            try {
//                p.load(is);
//                fileUrlPrefix = p.getProperty("media.url.prefix");
//                is.close();//关闭
//                is = null;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return fileUrlPrefix;
//    }


    /**
     * easyui AJAX请求数据
     *
     * @param weixinAccount
     * @param request
     * @param response
     * @param dataGrid
     */
    @RequestMapping(params = "datagrid")
    public void datagrid(WeixinAccountEntity weixinAccount,
                         HttpServletRequest request, HttpServletResponse response,
                         DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WeixinAccountEntity.class,
                dataGrid);
        // 查询条件组装器
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
                weixinAccount, request.getParameterMap());
        cq.eq("userName", ResourceUtil.getSessionUserName().getUserName());
        try {
            // 自定义追加查询条件
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        cq.add();
        this.weixinAccountService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * 删除微信公众帐号信息
     *
     * @return
     */
    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(WeixinAccountEntity weixinAccount,
                          HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        weixinAccount = systemService.getEntity(WeixinAccountEntity.class,
                weixinAccount.getId());
        message = "微信公众帐号信息删除成功";
        try {
            weixinAccountService.delete(weixinAccount);
            //systemService.addLog(message, Globals.Log_Type_DEL,
            //		Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "微信公众帐号信息删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 批量删除微信公众帐号信息
     *
     * @return
     */
    @RequestMapping(params = "doBatchDel")
    @ResponseBody
    public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        message = "微信公众帐号信息删除成功";
        try {
            for (String id : ids.split(",")) {
                WeixinAccountEntity weixinAccount = systemService.getEntity(
                        WeixinAccountEntity.class, id);
                weixinAccountService.delete(weixinAccount);
                //systemService.addLog(message, Globals.Log_Type_DEL,
                //		Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "微信公众帐号信息删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 添加微信公众帐号信息
     *
     * @param ids
     * @return
     */
    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(WeixinAccountEntity weixinAccount,
                          HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        message = "微信公众帐号信息添加成功";
        try {

            TSUser user = systemService.getEntity(TSUser.class, ResourceUtil.getSessionUserName().getId());
//			if(("01").equals(user.getTSRole().getRoleCode())){
//				
//				// 判断当前帐号是否已经添加微信公众账户
//				int f = weixinAccountService.findByUsername(ResourceUtil.getSessionUserName().getUserName()).size();
//				if(f>0){
//					
//					message = "微信公众帐号信息添加失败,您目前的权限只能添加一个微信公众帐号";
//				}
//			}else{

            weixinAccount.setPid(user.getAccountid());//
            weixinAccount.setLevel(1);
            weixinAccount.setUserName(ResourceUtil.getSessionUserName().getUserName());
            weixinAccountService.save(weixinAccount);
            //systemService.addLog(message, Globals.Log_Type_INSERT,Globals.Log_Leavel_INFO);

//				//重置session中的微信公众帐号ID
//				List<WeixinAccountEntity> acclst = weixinAccountService.findByProperty(WeixinAccountEntity.class, "accountnumber", weixinAccount.getAccountnumber());
//				request.getSession().setAttribute(WeiXinConstants.WEIXIN_ACCOUNT, acclst.get(0));
//				
            //}
        } catch (Exception e) {
            e.printStackTrace();
            message = "微信公众帐号信息添加失败";
            throw new BusinessException(e.getMessage());
        }

        j.setMsg(message);
        return j;
    }


    /**
     * 微信公众帐号信息新增页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(HttpServletRequest req) {


        WeixinAccountEntity weixinAccount = new WeixinAccountEntity();
        weixinAccount.setAccounttoken("w_" + getRandomString(8));
        req.setAttribute("weixinAccountPage", weixinAccount);

        return new ModelAndView("weixin/guanjia/account/weixinAccount-add");
    }

    /**
     * 微信公众帐号信息编辑页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(WeixinAccountEntity weixinAccount,
                                 HttpServletRequest req) {
        if (StringUtil.isNotEmpty(weixinAccount.getId())) {
            weixinAccount = weixinAccountService.getEntity(
                    WeixinAccountEntity.class, weixinAccount.getId());
            String qrcode = weixinAccount.getQRcode();
            String logo = weixinAccount.getLogoAccount();
            if(CheckPic.checkImg(qrcode)){
            	weixinAccount.setQRcode(ResourceUtil.getMediaUrlPrefix() + "/" + qrcode);
            }else{
            	weixinAccount.setQRcode( qrcode);
            }
            if(CheckPic.checkImg(logo)){
            	weixinAccount.setLogoAccount(ResourceUtil.getMediaUrlPrefix() + "/" + logo);
            }else{
            	weixinAccount.setLogoAccount(logo);
            }       
            req.setAttribute("weixinAccountPage", weixinAccount);
        }
        return new ModelAndView("weixin/guanjia/account/weixinAccountLXC");
    }


    /**
     * 微信公众号编辑页面
     *
     * @param req
     * @return
     */
    @RequestMapping(params = "goUpdateLXC")
    public ModelAndView goUpdateLXC(HttpServletRequest req) {
        WeixinAccountEntity weixinAccount = weixinAccountService.getEntity(
                WeixinAccountEntity.class, ResourceUtil.getWeiXinAccountId());
        req.setAttribute("weixinAccountPage", weixinAccount);
        return new ModelAndView("weixin/guanjia/account/weixinAccount-updateLXC");
    }

    /**
     * 初始化数据
     *
     * @param weixinAccount
     * @param req
     * @return
     */
    @RequestMapping(params = "doInit")
    @ResponseBody
    public AjaxJson doInit(String id, HttpServletRequest req) {


        List tempWeixinCmsSiteList = systemService.findByProperty(WeixinCmsSiteEntity.class, "accountid", id);
        if (null == tempWeixinCmsSiteList || tempWeixinCmsSiteList.size() == 0) {
            //微网站数据
            WeixinCmsSiteEntity weixinCmsSiteEntity = new WeixinCmsSiteEntity();
            weixinCmsSiteEntity.setAccountid(id);
            weixinCmsSiteEntity.setSiteName("微网站");
            weixinCmsSiteEntity.setLinkUrl(ResourceUtil.getDomain() + "/cmsController.do?goPage&page=index&accountid=" + id);
            systemService.save(weixinCmsSiteEntity);
        }

        List tempWeixinShopList = systemService.findByProperty(WeixinShopEntity.class, "accountid", id);
        if (null == tempWeixinShopList || tempWeixinShopList.size() == 0) {
            //微商城数据
            WeixinShopEntity weixinShopEntity = new WeixinShopEntity();
            weixinShopEntity.setAccountid(id);
            weixinShopEntity.setShopName("微商城");
            weixinShopEntity.setLinkUrl(ResourceUtil.getDomain() + "/shopController.do?shopindex&accountid=" + id);
            systemService.save(weixinShopEntity);
        }

        //支付
        List tempPaymentList = systemService.findByProperty(WeixinPaymentConEntity.class, "accountid", id);
        if (null == tempPaymentList || tempPaymentList.size() == 0) {

            WeixinPaymentConEntity weixinPaymentConEntity = new WeixinPaymentConEntity();
            weixinPaymentConEntity.setPayType("1");
            weixinPaymentConEntity.setAccountid(id);
            weixinPaymentConEntity.setPayName("微信支付");

            systemService.save(weixinPaymentConEntity);
        }


        AjaxJson j = new AjaxJson();
        message = "微信公众帐号初始化数据成功";
        j.setMsg(message);
        return j;
    }

    /**
     * 生成随机数
     *
     * @param length 表示生成字符串的长度
     * @return
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
