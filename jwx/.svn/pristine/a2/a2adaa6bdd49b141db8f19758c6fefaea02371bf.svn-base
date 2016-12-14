package weixin.report.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.annotation.DataSourceSwitch;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.extend.datasource.DataSourceType;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExcelTitle;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import weixin.liuliangbao.jsonbean.AccountGroup.acctAndGroup;
import weixin.report.model.NewMemberEntity;
import weixin.report.model.UserGetFlow;
import weixin.report.model.UserGiveFlow;
import weixin.report.model.UserGiveFlowEntity;
import weixin.report.service.UserGiveFlowServiceI;
import weixin.util.DateUtils;

/**
 * Created by xiaona on 2016/3/9.
 */
@Scope("prototype")
@Controller
@RequestMapping("/reportCountController")
public class ReportCountController extends BaseController {
    /**
     * Logger for this class
     */
    private static final Logger LOGGER = Logger.getLogger(ReportCountController.class);

    @Autowired
    private SystemService systemService;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Autowired
    private UserGiveFlowServiceI userGiveFlowService;

    /**
     * 用户流量币充值记录表
     *
     * @return
     */
    @RequestMapping(params = "userGiveFlowList")
    public ModelAndView userGiveFlowList(HttpServletRequest request) {
        return new ModelAndView("weixin/report/userGiveFlowlist");
    }

    @RequestMapping(params = "userFlowdatagrid")
    @DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    public void userFlowdatagrid(acctAndGroup accten, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws ParseException {
        String nickname = request.getParameter("nickname");
        //手机号查询
        String phoneNumber = request.getParameter("phoneNumber");
        
        String flowType1 = request.getParameter("flowType");
        
        String hdType1 = request.getParameter("hdType");

        String beginDate = request.getParameter("gettime_begin");
        
        String endDate = request.getParameter("gettime_end");
        
        String accountid = ResourceUtil.getWeiXinAccountId();
        StringBuffer sql = new StringBuffer();
        sql.append(
                "select u.id,m.nick_name,u.flowType,u.reason,u.operateDate,(case when status=1 then u.flowValue else 0 end) flowValue,u.phoneNumber,u.merchantID from userflowgiverecords u LEFT JOIN weixin_member m on u.merchantID = m.account_id and u.phoneNumber = m.phoneNumber where 1=1 and u.flowValue>0.0 and u.`status` = 1");

        if (accountid != null && !"".equals(accountid)) {
            sql.append(" and u.merchantID=").append("'").append(accountid).append("'");
        }
        if (nickname != null && !"".equals(nickname)) {
            sql.append(" and m.nick_name like").append("'%").append(nickname).append("%'");
        }
        if (phoneNumber != null && !"".equals(phoneNumber)) {
            sql.append(" and u.phoneNumber=").append("'").append(phoneNumber).append("'");
        }
        if (flowType1 != null && !"".equals(flowType1)) {
            sql.append(" and u.flowType=").append("'").append(flowType1).append("'");
        }
        if (StringUtils.isNotBlank(beginDate)) {
            sql.append(" and DATE_FORMAT(u.operateDate,'%Y-%m-%d') >= '" + beginDate + "' ");
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and DATE_FORMAT(u.operateDate,'%Y-%m-%d') <= '" + endDate + "' ");
        }
        if (hdType1 != null && !"".equals(hdType1)) {
            sql.append(" and u.reason=").append("'").append(hdType1).append("'");
        }
        sql.append(" ORDER BY operateDate DESC"); // 添加查询的用户记录的时间排序
        HqlQuery hqlQuery = new HqlQuery(UserGiveFlow.class, sql.toString(), dataGrid);
        PageList pageList = this.systemService.getPageListBySql(hqlQuery, false);
        List<Object[]> list = pageList.getResultList();
        List<UserGiveFlow> param = new ArrayList<UserGiveFlow>();

        for (Object[] objects : list) {
            UserGiveFlow wd = new UserGiveFlow();
            Object id = objects[0];
            if (id != null) {
                wd.setId(id.toString());
            }
            Object name = objects[1];
            if (name != null) {
                wd.setNickname(name.toString());
            }
            Object flowType = objects[2];
            if (flowType != null) {
                wd.setFlowType(flowType.toString());
            }
            Object hdType = objects[3];
            if (hdType != null) {
                wd.setHdType(hdType.toString());
            }
            Object getDate = objects[4];
            if (getDate != null) {
                SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date d = sim.parse(getDate.toString());
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String starttime = format.format(d);
                wd.setGettime(starttime);
            }
            Object getFlow = objects[5];
            if (getFlow != null) {
                wd.setFlowValue((Double.parseDouble(getFlow.toString())));
            } else {
                wd.setFlowValue(0.0);
            }

            Object phone = objects[6];
            if (phone != null) {
                wd.setPhoneNumber(phone.toString());
            }
            Object accountId = objects[6];
            if (accountId != null) {
                wd.setAccountId(accountId.toString());
            }
            param.add(wd);
        }
        dataGrid.setResults(param);
        dataGrid.setTotal(pageList.getCount());
        dataGrid.setPage(pageList.getCurPageNO());
        dataGrid.setRows(pageList.getOffset());
        TagUtil.datagrid(response, dataGrid);

    }

    /**
     * 用户充值到账记录查询
     *
     * @return
     */
    @RequestMapping(params = "userGetFlowList")
    public ModelAndView userGetFlowList(HttpServletRequest request) {
        return new ModelAndView("weixin/report/userGetFlowlist");
    }

    @RequestMapping(params = "userGetFlowdatagrid")
    @DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    public void userGetFlowdatagrid(acctAndGroup accten, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws ParseException {

        String flowType1 = request.getParameter("flowType");

        String state1 = request.getParameter("state");

        String accountid = ResourceUtil.getWeiXinAccountId();
        StringBuffer sql = new StringBuffer();
        sql.append(
                "select u.id,u.flowType,u.requestDate,u.flowValue,u.chargePhone,m.provinceName,u.chargeState,u.accountId,u.chargeType from userchargedflowrecords u join weixin_member m "
                        + " where u.phoneNumber=m.phoneNumber and u.chargeType is NULL");

        // if (accountid != null && !"".equals(accountid)) {
        // sql.append(" and
        // u.accountId=").append("'").append(accountid).append("'");
        // }
        if (flowType1 != null && !"".equals(flowType1)) {
            sql.append(" and u.flowType=").append("'").append(flowType1).append("'");
        }
        if (state1 != null && !"".equals(state1)) {
            sql.append(" and u.chargeState=").append("'").append(state1).append("'");
        }
        sql.append(" ORDER BY u.requestDate DESC"); // 添加查询的用户记录的时间排序
        HqlQuery hqlQuery = new HqlQuery(UserGetFlow.class, sql.toString(), dataGrid);
        PageList pageList = this.systemService.getPageListBySql(hqlQuery, false);
        List<Object[]> list = pageList.getResultList();
        List<UserGetFlow> param = new ArrayList<UserGetFlow>();

        for (Object[] objects : list) {
            UserGetFlow wd = new UserGetFlow();
            Object id = objects[0];
            if (id != null) {
                wd.setId(id.toString());
            }
            Object flowType = objects[1];
            if (flowType != null) {
                wd.setFlowType(flowType.toString());
            }
            Object getDate = objects[2];
            if (getDate != null) {
                SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
                Date d = sim.parse(getDate.toString());
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String starttime = format.format(d);
                wd.setGettime(starttime);
            }
            Object getFlow = objects[3];
            if (getFlow != null) {
                wd.setFlowValue((Double.parseDouble(getFlow.toString())));
            } else {
                wd.setFlowValue(0.0);
            }

            Object phone = objects[4];
            if (phone != null) {
                wd.setPhoneNumber(phone.toString());
            }
            Object phoneLocation = objects[5];
            if (phoneLocation != null) {
                wd.setPhoneNumberLocation(phoneLocation.toString());
            }
            Object stateCharge = objects[6];
            if (stateCharge != null) {
                wd.setState(stateCharge.toString());
            }
            Object accountId = objects[7];
            if (accountId != null) {
                wd.setAccountId(accountId.toString());
            }
            param.add(wd);
        }
        dataGrid.setResults(param);
        dataGrid.setTotal(pageList.getCount());
        dataGrid.setPage(pageList.getCurPageNO());
        dataGrid.setRows(pageList.getOffset());
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * 新增粉丝记录
     *
     * @return
     */
    @RequestMapping(params = "newMemberList")
    public ModelAndView newMemberList(HttpServletRequest request) {
        return new ModelAndView("weixin/report/newMemberList");
    }

    /**
     * 新增粉丝记录
     *
     * @return
     */
    @RequestMapping(params = "newMemberDatagrid")
    @DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    public void newMemberDatagrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws ParseException {
        Map paraMap = request.getParameterMap();
        String beginDate = request.getParameter("querytime_begin");
        String endDate = request.getParameter("querytime_end");
        String unit = request.getParameter("unit");

        String accountid = ResourceUtil.getWeiXinAccountId();
        StringBuffer sql = new StringBuffer();
        sql.append(
                "select count(1) num,DATE_FORMAT(min(m.subscribe_time),'%Y-%m-%d') begin,DATE_FORMAT(max(m.subscribe_time),'%Y-%m-%d') end from weixin_member m where m.subscribe='1' and m.account_id='"
                        + accountid + "' ");
        if (StringUtils.isNotBlank(beginDate)) {
            sql.append(" and DATE_FORMAT(m.subscribe_time,'%Y-%m-%d') >= '" + beginDate + "' ");
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql.append(" and DATE_FORMAT(m.subscribe_time,'%Y-%m-%d') <= '" + endDate + "' ");
        }
        if (StringUtils.equals(unit, "day")) {
            sql.append("group by date_format(m.subscribe_time,'%Y-%m-%d')");
        } else if (StringUtils.equals(unit, "week")) {
            sql.append("group by date_format(m.subscribe_time,'%X-%V')");
        } else if (StringUtils.equals(unit, "month")) {
            sql.append("group by date_format(m.subscribe_time,'%Y-%m')");
        } else if (StringUtils.equals(unit, "year")) {
            sql.append("group by date_format(m.subscribe_time,'%Y')");
        }
        LOGGER.info(sql);
        HqlQuery hqlQuery = new HqlQuery(NewMemberEntity.class, sql.toString(), dataGrid);
        PageList pageList = this.systemService.getPageListBySql(hqlQuery, false);
        List<Object[]> list = pageList.getResultList();
        List<NewMemberEntity> param = new ArrayList<NewMemberEntity>();

        for (Object[] objects : list) {
            NewMemberEntity e = new NewMemberEntity();
            e.setNum(String.valueOf(objects[0]));
            e.setTime(String.valueOf(objects[1])+" ---- "+String.valueOf(objects[2]));
            param.add(e);
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
    @DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    public void exportXls(UserGiveFlowEntity userFlowList, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try {
            codedFileName = "用户流量币领取记录";
            // 根据浏览器进行转码，使其支持中文文件名
            if (BrowserUtils.isIE(request)) {
                response.setHeader("content-disposition", "attachment;filename=" + java.net.URLEncoder.encode(codedFileName, "UTF-8") + ".xls");
            } else {
                String newtitle = new String(codedFileName.getBytes("UTF-8"), "ISO8859-1");
                response.setHeader("content-disposition", "attachment;filename=" + newtitle + ".xls");
            }
            // 产生工作簿对象
            HSSFWorkbook workbook = null;
            String nickname = request.getParameter("nickname");
            //手机号查询
            String phoneNumber = request.getParameter("phoneNumber");
            
            String flowType1 = request.getParameter("flowType");
            
            String hdType1 = request.getParameter("hdType");

            String beginDate = request.getParameter("gettime_begin");
            
            String endDate = request.getParameter("gettime_end");
            
            String accountid = ResourceUtil.getWeiXinAccountId();
            StringBuffer sql = new StringBuffer();
            sql.append(
                    "select u.id,m.nick_name,u.flowType,u.reason,u.operateDate,(case when status=1 then u.flowValue else 0 end) flowValue,u.phoneNumber,u.merchantID from userflowgiverecords u LEFT JOIN weixin_member m on u.merchantID = m.account_id and u.phoneNumber = m.phoneNumber where 1=1 and u.flowValue>0.0 and u.`status` = 1");

            if (accountid != null && !"".equals(accountid)) {
                sql.append(" and u.merchantID=").append("'").append(accountid).append("'");
            }
            if (nickname != null && !"".equals(nickname)) {
                sql.append(" and m.nick_name like").append("'%").append(nickname).append("%'");
            }
            if (phoneNumber != null && !"".equals(phoneNumber)) {
                sql.append(" and u.phoneNumber=").append("'").append(phoneNumber).append("'");
            }
            if (flowType1 != null && !"".equals(flowType1)) {
                sql.append(" and u.flowType=").append("'").append(flowType1).append("'");
            }
            if (StringUtils.isNotBlank(beginDate)) {
                sql.append(" and DATE_FORMAT(u.operateDate,'%Y-%m-%d') >= '" + beginDate + "' ");
            }
            if (StringUtils.isNotBlank(endDate)) {
                sql.append(" and DATE_FORMAT(u.operateDate,'%Y-%m-%d') <= '" + endDate + "' ");
            }
            if (hdType1 != null && !"".equals(hdType1)) {
                sql.append(" and u.reason=").append("'").append(hdType1).append("'");
            }
            sql.append(" ORDER BY operateDate DESC"); // 添加查询的用户记录的时间排序
            dataGrid.setPage(1);
            dataGrid.setRows(65535);
            HqlQuery hqlQuery = new HqlQuery(UserGiveFlow.class, sql.toString(), dataGrid);
            PageList pageList = this.systemService.getPageListBySql(hqlQuery, false);
            /* 过滤集合中不等于0.0的流量值 */
            List<Object[]> userflowlist = pageList.getResultList();
            List<UserGiveFlowEntity> newUserFlowList = new ArrayList<UserGiveFlowEntity>();
            for (int i = 0; i < userflowlist.size(); i++) {
            	Object[] result = userflowlist.get(i);
            	UserGiveFlowEntity entity = new UserGiveFlowEntity();
                     entity.setOperator(ObjectUtils.toString(result[1], ""));
                     entity.setFlowType(ObjectUtils.toString(result[2], ""));
                     entity.setReason(ObjectUtils.toString(result[3], ""));
                Object operatedate = result[4];
                    if(operatedate != null){
                    	String shortOperateDate = DateUtils.date_sdf.format(operatedate);
                    	
                        entity.setShortOperateDate(shortOperateDate);
                        entity.setOperateDate((Date)operatedate);
                    }   
                Object flowvalue = result[5];
                    if (flowvalue != null) {
                    	entity.setFlowValue((Double.parseDouble(flowvalue.toString())));
                    }
                    entity.setPhoneNumber(ObjectUtils.toString(result[6],""));  
                newUserFlowList.add(entity);
                }
            workbook = ExcelExportUtil.exportExcel(new ExcelTitle("用户流量币领取记录", "导出人:" + ResourceUtil.getSessionUserName().getUserName(), "导出信息"),
                    UserGiveFlowEntity.class, newUserFlowList);
            fOut = response.getOutputStream();
            workbook.write(fOut);
        } catch (Exception e) {
        	LOGGER.error(weixin.util.LogUtil.printStackTrace(e));
        } finally {
            try {
                fOut.flush();
                fOut.close();
            } catch (IOException e) {
                LOGGER.error(weixin.util.LogUtil.printStackTrace(e));
            }
        }
    }

}
