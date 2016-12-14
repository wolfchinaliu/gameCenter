package weixin.activity.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExcelTitle;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.sf.json.JSONObject;
import weixin.activity.entity.WeixinActivityEntity;
import weixin.activity.entity.WeixinActivityQuestionEntity;
import weixin.activity.entity.WeixinGameDetailEntity;
import weixin.activity.entity.WeixinPracticalityRecordEntity;
import weixin.activity.service.IWeixinActivityService;
import weixin.liuliangbao.jsonbean.Update;
import weixin.liuliangbao.util.HttpUtil;
import weixin.member.entity.WeixinMemberEntity;

/**
 * 
 * @author popl
 * @date 2016-7-29
 * @dec 微信活动后台
 */
@Controller
@RequestMapping("/weixinActivityController")
public class WeixinActivityController extends BaseController {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(WeixinActivityController.class);

    @Autowired
    private SystemService systemService;
    @Autowired
    private IWeixinActivityService weixinActivityService;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    /**
     * 微信活动列表 页面跳转
     *
     * @return
     */
    @RequestMapping(params = "weixinActivityAddress")
    public ModelAndView weixinLotteryAddress(HttpServletRequest request) {
        request.setAttribute("hdid", request.getParameter("hdid"));
        return new ModelAndView("weixin/activity/weixinActivityAddress");
    }


    /**
     * 微信活动列表 页面跳转
     *
     * @return
     */
    @RequestMapping(params = "weixinActivity")
    public ModelAndView weixinLottery(HttpServletRequest request) {
        request.setAttribute("type", request.getParameter("type"));
        int type = Integer.valueOf(request.getParameter("type"));
       /* if(type < 100)
        	return new ModelAndView("weixin/activity/weixinActivityList");
        else if(type < 200)
        	 return new ModelAndView("weixin/activity/weixinQuestionTypeList");
        else if(type < 300)
        	return new ModelAndView("weixin/activity/weixinGameTypeList");*/
        return new ModelAndView("weixin/activity/weixinActivityList");
    }
    
    /**
     * 微信活动列表 页面跳转
     *
     * @return
     */
    @RequestMapping(params = "weixinActivityQuestion")
    public ModelAndView weixinActivityQuestion(HttpServletRequest request) {
        request.setAttribute("activityId", request.getParameter("activityId"));
        return new ModelAndView("weixin/activity/weixinActivityQuestionList");
    }

    /**
     *
     * @param activityEntity
     * @param request
     * @param response
     * @param dataGrid
     */
    @RequestMapping(params = "datagrid")
    public void datagrid(WeixinActivityEntity activityEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WeixinActivityEntity.class, dataGrid);
        //商户ID
        activityEntity.setAccountid(ResourceUtil.getWeiXinAccountId());
        //查询条件组装器
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, activityEntity, request.getParameterMap());
        cq.add();
        this.systemService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * 删除微信活动
     *
     * @return
     */
    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(WeixinActivityEntity activityEntity, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        activityEntity = systemService.getEntity(WeixinActivityEntity.class, activityEntity.getId());
        message = "微信活动删除成功";
        try {
        	weixinActivityService.deleteActivity(activityEntity);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "微信活动删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 批量删除微信活动
     *
     * @return
     */
    @RequestMapping(params = "doBatchDel")
    @ResponseBody
    public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        message = "微信活动删除成功";
        try {
            for (String id : ids.split(",")) {
                WeixinActivityEntity activityEntity = systemService.getEntity(WeixinActivityEntity.class,
                        id
                );
                systemService.delete(activityEntity);
                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "微信活动删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }


    /**
     * 添加微信活动
     *
     * @return
     */
    @RequestMapping(params = "doSave")
    @ResponseBody
    public AjaxJson doSave(WeixinActivityEntity activityEntity, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        String start = request.getParameter("startTime");
        String endtime = request.getParameter("endTime");
        if (start == null || start.equals("")) {
            j.setSuccess(false);
            j.setMsg("请填写开始时间");
            return j;
        }

        if (endtime == null || endtime.equals("")) {
            j.setSuccess(false);
            j.setMsg("请填写结束时间");
            return j;
        }
        Date date = new Date();    //定义一个当前的日期
        if (activityEntity.getTotalNumber() < activityEntity.getEvenNumber() && activityEntity.getTotalNumber() > 0) {
            j.setSuccess(false);
            j.setMsg("总的抽奖次数不能小于每人抽奖次数");
            return j;
        }
        if (activityEntity.getStartTime().getTime() >= activityEntity.getEndTime().getTime()) {
            j.setSuccess(false);
            j.setMsg("结束时间必须大于开始时间");
            return j;
        }
        if (date.getTime() > activityEntity.getEndTime().getTime()) {
            j.setMsg("结束时间不能小于当前的时间");
            j.setSuccess(false);
            return j;
        }
        message = "微信活动添加成功";
        try {
        	 if(activityEntity.getId() != null && !"".equals(activityEntity.getId().trim())  ){
             	message = "微信活动更新成功";
             	WeixinActivityEntity entity = weixinActivityService.getEntity(WeixinActivityEntity.class, activityEntity.getId());
             	 MyBeanUtils.copyBeanNotNull2Bean(activityEntity,entity);
             	systemService.updateEntitie(entity);
             	 systemService.addLog(message, Globals.Log_Type_UPLOAD, Globals.Log_Leavel_INFO);
             }else{
            activityEntity.setCreateTime(date);
        	systemService.save(activityEntity);
            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
             }
        } catch (Exception e) {
            e.printStackTrace();
            message = "微信活动添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 更新微信活动
     *
     * @return
     */


    /**
     * 微信活动新增页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goEdit")
    public ModelAndView goAdd(WeixinActivityEntity activityEntity, HttpServletRequest req) {
    	int code =1 ;
        if (StringUtil.isNotEmpty(activityEntity.getId())) {
            activityEntity = systemService.getEntity(WeixinActivityEntity.class, activityEntity.getId());
            //定义开始前10分钟以外可以编辑
            Date now = new Date();
            if(activityEntity.getStartTime().getTime() - now.getTime() < 600000)code = 0;
        }
        req.setAttribute("activity", activityEntity);
        req.setAttribute("code", code);
        if(activityEntity.getType() < 100)
        	return new ModelAndView("weixin/activity/weixinActivity-edit");
        else if(activityEntity.getType() < 200)
        	return new ModelAndView("weixin/activity/weixinQuestionType-edit");
        else if(activityEntity.getType() < 300)
        	return new ModelAndView("weixin/activity/weixinGameType-edit");
        return new ModelAndView("weixin/activity/weixinActivity-edit");

    }


    /**
     * 导入功能跳转
     *
     * @return
     */
    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        return new ModelAndView("weixin/lottery/weixinLotteryUpload");
    }

    /**
     * 导出excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(params = "exportXls")
    public void exportXls(WeixinActivityEntity activityEntity, HttpServletRequest request, HttpServletResponse response
            , DataGrid dataGrid) {
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try {
            codedFileName = "微信活动";
            // 根据浏览器进行转码，使其支持中文文件名
            if (BrowserUtils.isIE(request)) {
                response.setHeader(
                        "content-disposition",
                        "attachment;filename="
                                + java.net.URLEncoder.encode(codedFileName,
                                "UTF-8") + ".xls");
            } else {
                String newtitle = new String(codedFileName.getBytes("UTF-8"),
                        "ISO8859-1");
                response.setHeader("content-disposition",
                        "attachment;filename=" + newtitle + ".xls");
            }
            // 产生工作簿对象
            HSSFWorkbook workbook = null;
            CriteriaQuery cq = new CriteriaQuery(WeixinActivityEntity.class, dataGrid);
            org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, activityEntity, request.getParameterMap());

            List<WeixinActivityEntity> weixinLotterys = this.systemService.getListByCriteriaQuery(cq, false);
            workbook = ExcelExportUtil.exportExcel(new ExcelTitle("微信活动列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(),
                    "导出信息"), WeixinActivityEntity.class, weixinLotterys);
            fOut = response.getOutputStream();
            workbook.write(fOut);
        } catch (Exception e) {
        } finally {
            try {
                fOut.flush();
                fOut.close();
            } catch (IOException e) {

            }
        }
    }

    /**
     * 导出excel 使模板
     *
     * @param request
     * @param response
     */
    @RequestMapping(params = "exportXlsByT")
    public void exportXlsByT(WeixinActivityEntity activityEntity, HttpServletRequest request, HttpServletResponse response
            , DataGrid dataGrid) {
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try {
            codedFileName = "微信活动";
            // 根据浏览器进行转码，使其支持中文文件名
            if (BrowserUtils.isIE(request)) {
                response.setHeader(
                        "content-disposition",
                        "attachment;filename="
                                + java.net.URLEncoder.encode(codedFileName,
                                "UTF-8") + ".xls");
            } else {
                String newtitle = new String(codedFileName.getBytes("UTF-8"),
                        "ISO8859-1");
                response.setHeader("content-disposition",
                        "attachment;filename=" + newtitle + ".xls");
            }
            // 产生工作簿对象
            HSSFWorkbook workbook = null;
            workbook = ExcelExportUtil.exportExcel(new ExcelTitle("微信活动列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(),
                    "导出信息"), WeixinActivityEntity.class, null);
            fOut = response.getOutputStream();
            workbook.write(fOut);
        } catch (Exception e) {
        } finally {
            try {
                fOut.flush();
                fOut.close();
            } catch (IOException e) {

            }
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(params = "importExcel", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setSecondTitleRows(1);
            params.setNeedSave(true);
            try {
                List<WeixinActivityEntity> listEntitys =
                        (List<WeixinActivityEntity>) ExcelImportUtil.importExcelByIs(file.getInputStream(), WeixinActivityEntity.class, params);
                for (WeixinActivityEntity activityEntity : listEntitys) {
                    systemService.save(activityEntity);
                }
                j.setMsg("文件导入成功！");
            } catch (Exception e) {
                j.setMsg("文件导入失败！");
                logger.error(ExceptionUtil.getExceptionMessage(e));
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return j;
    }
    /*************************--------------------------中奖纪录--------------------------------**************************/
    @RequestMapping(params = "goPrizeRecord")
	public ModelAndView goPrizeRecordd(HttpServletRequest request) {
		String hdId = request.getParameter("hdId");
		request.setAttribute("hdId", hdId);
		return new ModelAndView("weixin/activity/weixinPracticalityRecordList");
	}
    
	@RequestMapping(params = "recordDatagrid")
	public void datagrid(WeixinPracticalityRecordEntity recordEntity,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WeixinPracticalityRecordEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, recordEntity, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	/***
	 * 标识发货  实物 标识位，流量进行再次赠送操作
	 * @param recordEntity
	 * @param request
	 * @return
	 */
	 @RequestMapping(params = "sendPro")
	    @ResponseBody
	    public AjaxJson sendPro(WeixinPracticalityRecordEntity recordEntity, HttpServletRequest request) {
	        AjaxJson j = new AjaxJson();
	        recordEntity = systemService.getEntity(WeixinPracticalityRecordEntity.class, recordEntity.getId());
	        message = "已成功标识发货";
	        if(recordEntity.getIsSend() == 2){
	        	j.setMsg("此记录已发货");
	        	return j;
	        }
	        try {
	        	if(recordEntity.getType() == 1){ // 进行流量赠送
	        		WeixinActivityEntity activityEntity = systemService.get(WeixinActivityEntity.class, recordEntity.getHdid());
	        		if(StringUtil.isEmpty(recordEntity.getMobile())){
	        			WeixinMemberEntity memberEntity = new WeixinMemberEntity();
	    	        	String hql = "from WeixinMemberEntity t where t.accountId='" + recordEntity.getAccountid() + "' and t.openId='" + recordEntity.getOpenid()
	    						+ "'";
	    				List<WeixinMemberEntity> weixinMemberList = systemService.findByQueryString(hql);
	    				if (weixinMemberList != null && weixinMemberList.size() > 0) {
	    					memberEntity = weixinMemberList.get(0);
	    				}
	    	        	if(StringUtil.isNotEmpty(memberEntity.getPhoneNumber())){
	    	        		recordEntity.setMobile(memberEntity.getPhoneNumber());}
	        		}
	        		String url =  ResourceUtil.getConfigByName("jfinalUrl-config")
							+ "userGetFlow/UpdateFlowAndAddFlowRecord";
					Gson gson = new Gson();
					JSONObject myJson = new JSONObject();
					myJson.accumulate("phoneNumber", recordEntity.getMobile());
					myJson.accumulate("flowValue", recordEntity.getPrizevalue().replaceAll("\\D+", ""));
					myJson.accumulate("id", activityEntity.getAccountid());
					myJson.accumulate("opreateType", weixinActivityService.getOpreateType(activityEntity.getType()));
					myJson.accumulate("openid", recordEntity.getOpenid());
					myJson.accumulate("flowType", activityEntity.getFlowType()); // 省内：省内流量；全国：全国流量
					myJson.element("nickName", recordEntity.getNickname());
					JSONObject contentFlow = HttpUtil.httpPost(url, myJson, false);
					String strFlow = gson.toJson(contentFlow);
					Type type = new TypeToken<Update>() {
					}.getType();
					// 是否中奖参数
					Update update = gson.fromJson(strFlow, type);
					logger.info(
							"jfinal：" + myJson.toString() + "url:" + url + "---hdid：" + activityEntity.getId() + "---openId:" + recordEntity.getOpenid());
						if (!"200".equals(update.getCode()) && !"10026".equals(update.getCode())) {
							j.setMsg("流量赠送失败");
							return j;
						}
	        	}
	        	recordEntity.setIsSend(2);
	        	recordEntity.setSendTime(new Date());
	        	weixinActivityService.updateEntitie(recordEntity);
	            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
	        } catch (Exception e) {
	            e.printStackTrace();
	            message = "标识发货失败";
	            throw new BusinessException(e.getMessage());
	        }
	        j.setMsg(message);
	        return j;
	    }
	 
	 @RequestMapping(params = "getMemberNumber")
	    @ResponseBody
	    public AjaxJson getMemberNumber(WeixinPracticalityRecordEntity recordEntity, HttpServletRequest request) {
	        AjaxJson j = new AjaxJson();
	        recordEntity = systemService.getEntity(WeixinPracticalityRecordEntity.class, recordEntity.getId());
	        message = "获取用户手机号成功";
	        try {
	        	WeixinMemberEntity memberEntity = new WeixinMemberEntity();
	        	String hql = "from WeixinMemberEntity t where t.accountId='" + recordEntity.getAccountid() + "' and t.openId='" + recordEntity.getOpenid()
						+ "'";
				List<WeixinMemberEntity> weixinMemberList = systemService.findByQueryString(hql);
				if (weixinMemberList != null && weixinMemberList.size() > 0) {
					memberEntity = weixinMemberList.get(0);
				}
	        	if(StringUtil.isNotEmpty(memberEntity.getPhoneNumber())){
	        		recordEntity.setMobile(memberEntity.getPhoneNumber());
	        		weixinActivityService.updateEntitie(recordEntity);
		            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
	        	}else{
	        		 message = "获取失败 用户没有绑定手机";
	        	}
	        	
	        } catch (Exception e) {
	            e.printStackTrace();
	            message = "获取用户手机失败";
	            throw new BusinessException(e.getMessage());
	        }
	        j.setMsg(message);
	        return j;
	    }
	 
	 @RequestMapping(params = "goQuestionList")
		public ModelAndView goQuestionList(HttpServletRequest request) {
		 System.out.println("OK");
			String hdId = request.getParameter("hdId");
			request.setAttribute("hdId", hdId);
			return new ModelAndView("weixin/activity/questionListForActivity");
		}
	 
	 @RequestMapping(params = "questionDatagrid")
		public void Questiontagrid(WeixinActivityQuestionEntity questionEntity,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
			CriteriaQuery cq = new CriteriaQuery(WeixinActivityQuestionEntity.class, dataGrid);
			//查询条件组装器
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, questionEntity, request.getParameterMap());
			try{
			//自定义追加查询条件
			}catch (Exception e) {
				throw new BusinessException(e.getMessage());
			}
			cq.add();
			this.systemService.getDataGridReturn(cq, true);
			TagUtil.datagrid(response, dataGrid);
		}

	 /**
	     * 微信活动新增页面跳转
	     *
	     * @return
	     */
	    @RequestMapping(params = "goQuestionEdit")
	    public ModelAndView goQuestionEdit(WeixinActivityQuestionEntity questionEntity, HttpServletRequest req) {
	        if (StringUtil.isNotEmpty(questionEntity.getId())) {
	        	questionEntity = systemService.getEntity(WeixinActivityQuestionEntity.class, questionEntity.getId());
	        }
	        req.setAttribute("question", questionEntity);
	        return new ModelAndView("weixin/activity/questionEditForActivity");

	    }
	    @RequestMapping(params = "doQuestionDel")
	    @ResponseBody
	    public AjaxJson doQuestionDel(WeixinActivityQuestionEntity questionEntity, HttpServletRequest request) {
	        AjaxJson j = new AjaxJson();
	        questionEntity = systemService.getEntity(WeixinActivityQuestionEntity.class, questionEntity.getId());
	        message = "微信答题活动试题删除成功";
	        try {
	        	systemService.delete(questionEntity);
	            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
	        } catch (Exception e) {
	            e.printStackTrace();
	            message = "微信答题活动试题删除失败";
	            throw new BusinessException(e.getMessage());
	        }
	        j.setMsg(message);
	        return j;
	    }
	    @RequestMapping(params = "doQuestionSave")
	    @ResponseBody
	    public AjaxJson doQuestionSave(WeixinActivityQuestionEntity questionEntity, HttpServletRequest request) {
	        AjaxJson j = new AjaxJson();
	        message = "微信活动添加成功";
	        try {
	        	 if(questionEntity.getId() != null && !"".equals(questionEntity.getId().trim())  ){
	             	message = "微信活动更新成功";
	             	WeixinActivityEntity entity = weixinActivityService.getEntity(WeixinActivityQuestionEntity.class, questionEntity.getId());
	             	 MyBeanUtils.copyBeanNotNull2Bean(questionEntity,entity);
	             	systemService.updateEntitie(entity);
	             	 systemService.addLog(message, Globals.Log_Type_UPLOAD, Globals.Log_Leavel_INFO);
	             }else{
	            	 questionEntity.setCreateTime(new Date());
	        	systemService.save(questionEntity);
	            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
	             }
	        } catch (Exception e) {
	            e.printStackTrace();
	            message = "微信活动添加失败";
	            throw new BusinessException(e.getMessage());
	        }
	        j.setMsg(message);
	        return j;
	    }
/****------------------------------参与记录----------------------------------***/
	    @RequestMapping(params = "hdDetail")
		public ModelAndView hdDetail(HttpServletRequest request) {
			String hdId = request.getParameter("hdId");
			request.setAttribute("hdId", hdId);
			return new ModelAndView("weixin/activity/weixinGameDetailList");
		}
	    @RequestMapping(params = "gameDetailDatagrid")
		public void gameDetaildDatagrid(WeixinGameDetailEntity detailEntity,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
			CriteriaQuery cq = new CriteriaQuery(WeixinGameDetailEntity.class, dataGrid);
			//查询条件组装器
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, detailEntity, request.getParameterMap());
			try{
			//自定义追加查询条件
			}catch (Exception e) {
				throw new BusinessException(e.getMessage());
			}
			cq.add();
			this.systemService.getDataGridReturn(cq, true);
			TagUtil.datagrid(response, dataGrid);
		}
}
