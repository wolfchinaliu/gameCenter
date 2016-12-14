package weixin.tenant.controller;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import weixin.tenant.entity.WeixinMerchantCoverAreaEntity;
import weixin.tenant.service.WeixinMerchantCoverAreaServiceI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by aa on 2015/12/1.
 */
@Controller
@RequestMapping("/weixinMerchantCoverAreaController")
public class WeixinMerchantCoverAreaController extends BaseController {

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(WeixinProductController.class);

    @Autowired
    private WeixinMerchantCoverAreaServiceI weixinMerchantCoverAreaServiceI;

    //    添加相应的提示信息
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 覆盖区域类型列表 页面跳转
     *
     * @return
     */
    @RequestMapping(params = "weixinMerchantCoverArea")
    public ModelAndView weixinMerchantCoverArea(HttpServletRequest request) {
        return new ModelAndView("weixin/tenant/weixinCoverAreaList");
    }

    /**
     * easyui AJAX请求数据
     *
     * @param request
     * @param response
     * @param dataGrid
     */

    @RequestMapping(params = "datagrid")
    public void datagrid(WeixinMerchantCoverAreaEntity weixinMerchantCoverAreaEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WeixinMerchantCoverAreaEntity.class, dataGrid);
        //查询条件组装器
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinMerchantCoverAreaEntity, request.getParameterMap());

        try {
            //自定义追加查询条件
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        cq.add();
        this.weixinMerchantCoverAreaServiceI.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }



}
