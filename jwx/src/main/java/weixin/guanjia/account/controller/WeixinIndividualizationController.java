package weixin.guanjia.account.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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

import freemarker.template.utility.DateUtil;
import weixin.acctlist.entity.WeixinacctListEntity;
import weixin.acctlist.service.WeixinacctListServiceI;
import weixin.cms.entity.WeixinCmsSiteEntity;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.entity.WeixinIndividualizationEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.menu.entity.MenuEntity;
import weixin.payment.entity.WeixinPaymentConEntity;
import weixin.shop.entity.WeixinShopEntity;
import weixin.tenant.entity.weixinAcctFlowAccountEntity;
import weixin.tenant.service.WeixinAcctFlowAccoutServiceI;
import weixin.util.DateUtils;
import weixin.util.WeiXinConstants;

/**
 * @author wangpeng
 * @version V1.0
 * @Title: Controller
 * @Description: 公共号个性化管理
 * @date 2016-09-29 10:56:00
 */
@Scope("prototype")
@Controller
@RequestMapping("/weixinIndividualizationController")
public class WeixinIndividualizationController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger LOGGER = Logger.getLogger(WeixinIndividualizationController.class);

	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	@Autowired
	private SystemService systemService;
	private String message;
	@Autowired
	private WeixinAcctFlowAccoutServiceI weixinAcctFlowAccoutService;
	@Autowired
	private WeixinacctListServiceI weixinacctListServiceI;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 微信个性化 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "weixinIndividualization")
	public ModelAndView weixinIndividualization(HttpServletRequest request) {

		return new ModelAndView("weixin/guanjia/account/weixinIndividualizationList");
	}

	/**
	 * 添加页面跳转
	 */

	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(HttpServletRequest request) {
		return new ModelAndView("weixin/guanjia/account/weixinIndividualizationAdd");
	}

	// 上传图片到本地,并保存到数据库之中
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(WeixinIndividualizationEntity weixinIndividualizationEntity, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		long starttime = System.currentTimeMillis();
		StringBuilder builder = new StringBuilder();
		builder.append("weixinTranslateController_doAdd");
		try {
			String names = request.getParameter("name");
			if (StringUtils.isBlank(names)) {
				j.setMsg("名称不能为空");
				j.setSuccess(false);
				return j;
			}
			TSUser user = ResourceUtil.getSessionUserName();
			String acctId = user.getTenantId();
			  String hql =
			  "from WeixinIndividualizationEntity where acctId=?";
			  List<WeixinIndividualizationEntity> WeixinIndividualization =
			  this.systemService.findHql(hql,acctId);
			  if(WeixinIndividualization.size() !=0){ j.setMsg("每个商户只能添加一条记录");
			  j.setSuccess(false); return j; 
			  }
			String name = request.getParameter("name");
			String pathurl=ResourceUtil.getConfigByName("media.url.prefix");
            String imagepath=weixinIndividualizationEntity.getLogo();
            if(imagepath.contains(pathurl)){

            }else{
            	weixinIndividualizationEntity.setLogo(pathurl+"/"+weixinIndividualizationEntity.getLogo());
            }
//			String logo = request.getParameter("logo");
//			logo = logo.substring(logo.lastIndexOf("\\") + 1);
			String loginUserName = user.getUserName();
			LOGGER.info(loginUserName);

			// builder.append("WeixinIndividualizationController_"+accountname+"imageFilePath"+logo);
			Date dateTime = new Date();
			weixinIndividualizationEntity.setAcctId(acctId);
			weixinIndividualizationEntity.setName(name);
			weixinIndividualizationEntity.setCreateTime(dateTime);;
			this.systemService.save(weixinIndividualizationEntity);// (weixinIndividualizationEntity);
			message = "个性化添加成功";
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "个性化添加失败";
			throw new BusinessException(e.getMessage());
		}
		long runTime = System.currentTimeMillis() - starttime;
		LOGGER.info("runTime_" + runTime + "operator_" + builder.toString() + message);
		j.setMsg(message);
		return j;
	}

	/**
	 * easyui AJAX请求数据
	 *
	 * @param weixinAccount
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagrid")
	public void datagrid(WeixinIndividualizationEntity weixinIndividualizationController, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WeixinIndividualizationEntity.class, dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinIndividualizationController,
				request.getParameterMap());
		cq.eq("acctId", ResourceUtil.getSessionUserName().getTenantId());
		try {
			// 自定义追加查询条件
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.weixinAccountService.getDataGridReturn(cq, true);
		// this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/* 编辑页面跳转 */
	@RequestMapping(params = "goEdit")
	public ModelAndView goEdit(WeixinIndividualizationEntity weixinIndividualizationController,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(weixinIndividualizationController.getId())) {
			weixinIndividualizationController = weixinacctListServiceI.getEntity(WeixinIndividualizationEntity.class,
					weixinIndividualizationController.getId());
			weixinIndividualizationController.setName(weixinIndividualizationController.getName());
			req.setAttribute("weixinIndividualizationController", weixinIndividualizationController);
		}
		return new ModelAndView("weixin/guanjia/account/editIndividualizationList");
	}

	/**
	 * 保存编辑内容
	 * @param weixinIndividualization
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WeixinIndividualizationEntity weixinIndividualization, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		long starttime = System.currentTimeMillis();
		StringBuilder builder = new StringBuilder();
		builder.append("WeixinIndividualizationController_doUpdate");
		try {
			String names = request.getParameter("name");
			if (StringUtils.isBlank(names)) {
				j.setMsg("名称不能为空");
				j.setSuccess(false);
				return j;
			}
			TSUser user = ResourceUtil.getSessionUserName();
			
			String name = request.getParameter("name");
//			String logo = request.getParameter("logo");
//			logo = logo.substring(logo.lastIndexOf("\\") + 1);
			String acctId = user.getTenantId();
			Date dateTime = new Date();

			WeixinIndividualizationEntity weixinIndividualizationEntity = this.systemService
					.findUniqueByProperty(WeixinIndividualizationEntity.class, "acctId", acctId);
			weixinIndividualizationEntity.setCreateTime(dateTime);;
			weixinIndividualizationEntity.setName(name);
			String pathurl=ResourceUtil.getConfigByName("media.url.prefix");
            String imagepath=request.getParameter("logo");
            if(imagepath.contains(pathurl)){

            }else{
            	weixinIndividualizationEntity.setLogo(pathurl+"/"+request.getParameter("logo"));
            }
			this.systemService.saveOrUpdate(weixinIndividualizationEntity);
			LOGGER.info(weixinIndividualizationEntity.getName());
		} catch (Exception e) {
			e.printStackTrace();
			message = "个性化编辑失败";
			throw new BusinessException(e.getMessage());
		}
		message = "个性化编辑成功";
		j.setMsg(message);
		return j;

	}
	/**
     * 删除微信公众帐号信息
     *
     * @return
     */
    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(WeixinIndividualizationEntity weixinIndividualizationEntity,
                          HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        TSUser user = ResourceUtil.getSessionUserName();
        String acctId = user.getTenantId();
         weixinIndividualizationEntity = this.systemService
				.findUniqueByProperty(WeixinIndividualizationEntity.class, "acctId", acctId);
        message = "个性化删除成功";
        try {
            this.systemService.delete(weixinIndividualizationEntity);
            
        } catch (Exception e) {
            e.printStackTrace();
            message = "个性化删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }
	
}
