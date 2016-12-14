package weixin.customer.controller;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import weixin.customer.entity.WeixinCustomerLxcRespEntity;
import weixin.customer.service.WeixinCustomerLxcRespServiceI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by xiaochun on 2015/12/7.
 */
@Scope("prototype")
@Controller
@RequestMapping("/weixinCustomerLxcRespController")
public class WeixinCustomerLxcRespController {
    private static final Logger logger = Logger.getLogger(WeixinCustomerController.class);
    @Autowired
    private WeixinCustomerLxcRespServiceI weixinCustomerLxcRespService;
    private String message;
    /**
     * 客服表列表 页面跳转
     *
     * @return
     */
    @RequestMapping(params = "weixinCustomer")
    public ModelAndView weixinCustomer(HttpServletRequest request) {

        String hql="from WeixinCustomerLxcRespEntity t where t.accountId='"+ ResourceUtil.getWeiXinAccountId()+"'";
        List<WeixinCustomerLxcRespEntity> weixinCustomerKeys=weixinCustomerLxcRespService.findHql(hql,null);
        LogUtil.info("---------findCustomerKey----关键字查询结果条数：----" + weixinCustomerKeys != null ? weixinCustomerKeys.size() : 0);
        if (weixinCustomerKeys.size()>0) {
            request.setAttribute("weixinCustomer",weixinCustomerKeys.get(0));
        }
        return new ModelAndView("weixin/customerlxc/customerresp");
    }

    @RequestMapping(params="doAddOrUpdate")
    public void doAddOrUpdate(HttpServletRequest request,HttpServletResponse response){
        String id=request.getParameter("id");
        String keyWord=request.getParameter("keyWord");
        String content=request.getParameter("content");

        WeixinCustomerLxcRespEntity weixinCustomerLxcRespEntity=new WeixinCustomerLxcRespEntity();
        if (id != null && id!="") {
            weixinCustomerLxcRespEntity=weixinCustomerLxcRespService.get(WeixinCustomerLxcRespEntity.class,id);

        }
        weixinCustomerLxcRespEntity.setKeyWord(keyWord);
        weixinCustomerLxcRespEntity.setContent(content);
        weixinCustomerLxcRespEntity.setAccountId(ResourceUtil.getWeiXinAccountId());
        weixinCustomerLxcRespEntity.setAddTime(new Date());

        try{
            weixinCustomerLxcRespService.saveOrUpdate(weixinCustomerLxcRespEntity);
            message="ok";
        }catch (Exception e){
            e.printStackTrace();
            message = "客服关键字保存失败";
        }
    }

}
