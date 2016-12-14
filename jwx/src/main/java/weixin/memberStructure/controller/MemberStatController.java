package weixin.memberStructure.controller;

import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.codehaus.jackson.map.ObjectMapper;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.*;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExcelTitle;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jeewx.api.wxuser.user.JwUserAPI;
import org.jeewx.api.wxuser.user.model.WxUserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.base.entity.Subscribe;
import weixin.guanjia.core.util.RedisUtil;
import weixin.guanjia.core.util.WeixinUtil;
import weixin.member.entity.WeixinGroupEntity;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinGroupServiceI;
import weixin.member.service.WeixinMemberServiceI;
import weixin.member.view.WeixinMemberBean;
import weixin.memberStructure.entity.MemberStatEntity;
import weixin.memberStructure.service.MemberStatService;
import weixin.memberStructure.view.SubMemberStatBean;
import weixin.report.model.UserGiveFlowEntity;
import weixin.tenant.service.WeixinAcctFlowAccoutServiceI;
import weixin.util.CommonUtils;
import weixin.util.DateUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.text.MessageFormat;
import java.util.*;

/**
 * @author WangPeng
 * @Title: Controller
 * @Description: 粉丝增长
 * @date 2016-11-07 17:35:26
 */
@Scope("prototype")
@Controller
@RequestMapping("/memberStatController")
public class MemberStatController extends BaseController {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(MemberStatController.class);

	@Autowired
    private MemberStatService memberStatService;
	@Autowired
	private WeixinAcctFlowAccoutServiceI weixinAcctFlowAccoutServiceI;
	@Autowired
	private SystemService systemService;
	
    /**
     * 粉丝增长 页面跳转
     *
     * @return
     */
    @RequestMapping(params = "memberIncrease")
    public ModelAndView memberIncrease(HttpServletRequest request) {


        return new ModelAndView("weixin/memberStat/MemberIncrease");
    }

    /**
     * easyui AJAX请求数据
     * 粉丝增长
     * @param request
     * @param response
     * @param dataGrid
     * @param user
     */

    @RequestMapping(params = "datagrid")
    public void datagrid(MemberStatEntity memberStatEntity, HttpServletRequest request, HttpServletResponse response,
                         DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(MemberStatEntity.class, dataGrid);
        // 查询条件组装器
       
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, memberStatEntity, request.getParameterMap());
        cq.eq("accountId", ResourceUtil.getWeiXinAccountId());// 根据公众ID进行数据隔离
        cq.add();
        this.memberStatService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * 下级粉丝增长 页面跳转
     *
     * @return
     */
    @RequestMapping(params = "subMemberIncrease")
    public ModelAndView subMemberIncrease(HttpServletRequest request) {


        return new ModelAndView("weixin/memberStat/SubMemberIncrease");
    }
    
    /**
     * easyui AJAX请求数据
     * 下级粉丝增长
     * @param request
     * @param response
     * @param dataGrid
     * @param user
     */

    @RequestMapping(params = "subDatagrid")
    @ResponseBody
    public void subDatagrid(SubMemberStatBean subMemberStatBean, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
       
       String name = request.getParameter("subAcctForName");
       String acctlevel = request.getParameter("acctLevel");
       String acctForName = request.getParameter("acctForName");
       String beginDate = request.getParameter("created_begin");
       String endDate = request.getParameter("created_end");
       String accountId = ResourceUtil.getWeiXinAccountId();
       StringBuffer sql = new StringBuffer();
       sql.append("select wa.acctForName subAcctForName,wa.acct_level,acct.acctForName, ms.add_count, ms.cancel_count,ms.net_count,ms.binded_count,ms.subscribe_count,DATE_FORMAT(ms.created, '%Y-%m-%d')  FROM member_stat ms LEFT JOIN weixin_acct wa ON ms.acct_id = wa.id LEFT JOIN weixin_acct acct ON wa.pid = acct.id where 1 = 1 ");
       List<String> subAccountIdList = weixinAcctFlowAccoutServiceI.findSubAccountIdList(accountId);
       if(subAccountIdList.size() == 0){
    	   subAccountIdList.add("");
       }
       if (accountId != null && !"".equals(accountId)) {
			sql.append(" and ms.account_id in (").append(CommonUtils.listToSqlString(subAccountIdList)).append(")");
		}
       //商户名称模糊查询
       if (name != null && !"".equals(name)) {
           sql.append(" and wa.acctForName like ").append("'%").append(name).append("%'");
       }
       //商户等级
       if (acctlevel != null && !"".equals(acctlevel)) {
           sql.append(" and wa.acct_level=").append("'").append(acctlevel).append("'");
       }
       //所属商户
       if(StringUtils.isNotBlank(acctForName)){
           sql.append(" and wa.pid = '"+StringUtils.trim(acctForName)+"' ");
       }
       //时间查询
       if (StringUtils.isNotBlank(beginDate)) {
           sql.append(" and DATE_FORMAT(ms.created,'%Y-%m-%d') >= '" + beginDate + "' ");
       }
       if (StringUtils.isNotBlank(endDate)) {
           sql.append(" and DATE_FORMAT(ms.created,'%Y-%m-%d') <= '" + endDate + "' ");
       }
       // 添加查询的用户记录的时间排序
       sql.append(" ORDER BY DATE_FORMAT(ms.created,'%Y%m%d') DESC");
	   
       HqlQuery hqlQuery = new HqlQuery(SubMemberStatBean.class, sql.toString(), dataGrid);
       PageList pageList = this.systemService.getPageListBySql(hqlQuery, false);
       List<Object[]> list = pageList.getResultList();
       List<SubMemberStatBean> param = new ArrayList<SubMemberStatBean>();
       for (Object[] objects : list) {
    	   SubMemberStatBean subMemberStat = new SubMemberStatBean();
    	   
    	   Object subAcctForName = objects[0];
           if (subAcctForName != null) {
        	   subMemberStat.setSubAcctForName(subAcctForName.toString());
           }
           Object acctLevel = objects[1];
           if (acctLevel != null) {
        	   subMemberStat.setAcctLevel(acctLevel.toString());
           }
           Object acctforName = objects[2];
           if (acctforName != null) {
        	   subMemberStat.setAcctForName(acctforName.toString());
           }
           Object addCount = objects[3];
           if (addCount != null) {
        	   subMemberStat.setAddCount(new BigInteger(ObjectUtils.toString(addCount, "0")));
           }
           Object cancelCount = objects[4];
           if (cancelCount != null) {
        	   subMemberStat.setCancelCount(new BigInteger(ObjectUtils.toString(cancelCount, "0")));
           }
           Object netCount = objects[5];
           if (netCount != null) {
        	   subMemberStat.setNetCount(new BigInteger(ObjectUtils.toString(netCount, "0")));
           }
           Object bindedCount = objects[6];
           if (bindedCount != null) {
        	   subMemberStat.setBindedCount(new BigInteger(ObjectUtils.toString(bindedCount, "0")));
           }
           Object subscribeCount = objects[7];
           if (subscribeCount != null) {
        	   subMemberStat.setSubscribeCount(new BigInteger(ObjectUtils.toString(subscribeCount, "0")));
           }
           Object created = objects[8];
           if (created != null) {
               subMemberStat.setCreated(ObjectUtils.toString(created));
           }
           param.add(subMemberStat);
       }
       dataGrid.setResults(param);
       dataGrid.setTotal(pageList.getCount());
       dataGrid.setPage(pageList.getCurPageNO());
       dataGrid.setRows(pageList.getOffset());
       TagUtil.datagrid(response, dataGrid);
       
    }
    
    /**
     * 导出excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(params = "exportXls")
    public void exportXls(MemberStatEntity memberStatEntity, HttpServletRequest request, HttpServletResponse response,
                          DataGrid dataGrid) {
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try {
            codedFileName = "粉丝增加";
            // 根据浏览器进行转码，使其支持中文文件名
            if (BrowserUtils.isIE(request)) {
                response.setHeader("content-disposition",
                        "attachment;filename=" + java.net.URLEncoder.encode(codedFileName, "UTF-8") + ".xls");
            } else {
                String newtitle = new String(codedFileName.getBytes("UTF-8"), "ISO8859-1");
                response.setHeader("content-disposition", "attachment;filename=" + newtitle + ".xls");
            }
            // 产生工作簿对象
            HSSFWorkbook workbook = null;
            CriteriaQuery cq = new CriteriaQuery(MemberStatEntity.class, dataGrid);
            // 查询条件组装器
        
            org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, memberStatEntity, request.getParameterMap());
            cq.eq("accountId", ResourceUtil.getWeiXinAccountId());// 根据公众ID进行数据隔离
            cq.add();
            List<MemberStatEntity> memberStat = this.memberStatService.getListByCriteriaQuery(cq, false);
            workbook = ExcelExportUtil.exportExcel(
                    new ExcelTitle("粉丝增加", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"),
                    MemberStatEntity.class, memberStat);
            fOut = response.getOutputStream();
            workbook.write(fOut);
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            try {
                fOut.flush();
                fOut.close();
            } catch (IOException e) {

            }
        }
    }
    
    /**
     * 导出excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(params = "subExportXls")
    public void subExportXls(SubMemberStatBean subMemberStatBean, HttpServletRequest request, HttpServletResponse response,
                          DataGrid dataGrid) {
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try {
            codedFileName = "下级粉丝增加";
            // 根据浏览器进行转码，使其支持中文文件名
            if (BrowserUtils.isIE(request)) {
                response.setHeader("content-disposition",
                        "attachment;filename=" + java.net.URLEncoder.encode(codedFileName, "UTF-8") + ".xls");
            } else {
                String newtitle = new String(codedFileName.getBytes("UTF-8"), "ISO8859-1");
                response.setHeader("content-disposition", "attachment;filename=" + newtitle + ".xls");
            }
            // 产生工作簿对象
            HSSFWorkbook workbook = null;
            String name = request.getParameter("subAcctForName");
            String acctlevel = request.getParameter("acctLevel");
            String acctForName = request.getParameter("acctForName");
            String beginDate = request.getParameter("created_begin");
            String endDate = request.getParameter("created_end");
            String accountId = ResourceUtil.getWeiXinAccountId();
            StringBuffer sql = new StringBuffer();
            sql.append("select wa.acctForName subAcctForName,wa.acct_level,acct.acctForName, ms.add_count, ms.cancel_count,ms.net_count,ms.binded_count,ms.subscribe_count,DATE_FORMAT(ms.created, '%Y-%m-%d')  FROM member_stat ms LEFT JOIN weixin_acct wa ON ms.acct_id = wa.id LEFT JOIN weixin_acct acct ON wa.pid = acct.id where 1 = 1 ");
            List<String> subAccountIdList = weixinAcctFlowAccoutServiceI.findSubAccountIdList(accountId);
            if(subAccountIdList.size() == 0){
         	   subAccountIdList.add("");
            }
            if (accountId != null && !"".equals(accountId)) {
     			sql.append(" and ms.account_id in (").append(CommonUtils.listToSqlString(subAccountIdList)).append(")");
     		}
            //商户名称模糊查询
            if (name != null && !"".equals(name)) {
                sql.append(" and wa.acctForName like ").append("'%").append(name).append("%'");
            }
            //商户等级
            if (acctlevel != null && !"".equals(acctlevel)) {
                sql.append(" and wa.acct_level=").append("'").append(acctlevel).append("'");
            }
            //所属商户
            if(StringUtils.isNotBlank(acctForName)){
                sql.append(" and wa.pid = '"+StringUtils.trim(acctForName)+"' ");
            }
            //时间查询
            if (StringUtils.isNotBlank(beginDate)) {
                sql.append(" and DATE_FORMAT(ms.created,'%Y-%m-%d') >= '" + beginDate + "' ");
            }
            if (StringUtils.isNotBlank(endDate)) {
                sql.append(" and DATE_FORMAT(ms.created,'%Y-%m-%d') <= '" + endDate + "' ");
            }
            // 添加查询的用户记录的时间排序
            sql.append(" ORDER BY DATE_FORMAT(ms.created,'%Y%m%d') DESC");
            dataGrid.setPage(1);
            dataGrid.setRows(65535);
            HqlQuery hqlQuery = new HqlQuery(SubMemberStatBean.class, sql.toString(), dataGrid,65535);
            PageList pageList = this.systemService.getPageListBySql(hqlQuery, false);
            List<Object[]> list = pageList.getResultList();
            List<SubMemberStatBean> param = new ArrayList<SubMemberStatBean>();
            for (Object[] objects : list) {
         	   SubMemberStatBean subMemberStat = new SubMemberStatBean();
         	   
         	   Object subAcctForName = objects[0];
                if (subAcctForName != null) {
             	   subMemberStat.setSubAcctForName(subAcctForName.toString());
                }
                Object acctLevel = objects[1];
                if (acctLevel != null) {
             	   subMemberStat.setAcctLevel(acctLevel.toString());
                }
                Object acctforName = objects[2];
                if (acctforName != null) {
             	   subMemberStat.setAcctForName(acctforName.toString());
                }
                Object addCount = objects[3];
                if (addCount != null) {
             	   subMemberStat.setAddCount(new BigInteger(ObjectUtils.toString(addCount, "0")));
                }
                Object cancelCount = objects[4];
                if (cancelCount != null) {
             	   subMemberStat.setCancelCount(new BigInteger(ObjectUtils.toString(cancelCount, "0")));
                }
                Object netCount = objects[5];
                if (netCount != null) {
             	   subMemberStat.setNetCount(new BigInteger(ObjectUtils.toString(netCount, "0")));
                }
                Object bindedCount = objects[6];
                if (bindedCount != null) {
             	   subMemberStat.setBindedCount(new BigInteger(ObjectUtils.toString(bindedCount, "0")));
                }
                Object subscribeCount = objects[7];
                if (subscribeCount != null) {
             	   subMemberStat.setSubscribeCount(new BigInteger(ObjectUtils.toString(subscribeCount, "0")));
                }
                Object created = objects[8];
                if (created != null) {
                    subMemberStat.setCreated(ObjectUtils.toString(created));
                }
                param.add(subMemberStat);
            }
            
            workbook = ExcelExportUtil.exportExcel(
                    new ExcelTitle("下级粉丝增加", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"),
                    SubMemberStatBean.class, param);
            fOut = response.getOutputStream();
            workbook.write(fOut);
        } catch (Exception e) {
        	e.printStackTrace();
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
    public void exportXlsByT(MemberStatEntity memberStatEntity, HttpServletRequest request, HttpServletResponse response,
                             DataGrid dataGrid) {
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try {
            codedFileName = "粉丝增加";
            // 根据浏览器进行转码，使其支持中文文件名
            if (BrowserUtils.isIE(request)) {
                response.setHeader("content-disposition",
                        "attachment;filename=" + java.net.URLEncoder.encode(codedFileName, "UTF-8") + ".xls");
            } else {
                String newtitle = new String(codedFileName.getBytes("UTF-8"), "ISO8859-1");
                response.setHeader("content-disposition", "attachment;filename=" + newtitle + ".xls");
            }
            // 产生工作簿对象
            HSSFWorkbook workbook = null;
            workbook = ExcelExportUtil.exportExcel(
                    new ExcelTitle("粉丝增加", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"),
                    MemberStatEntity.class, null);
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

    	

}


