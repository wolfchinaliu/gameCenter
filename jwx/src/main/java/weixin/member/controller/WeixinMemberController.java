package weixin.member.controller;

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
import weixin.report.model.UserGiveFlowEntity;
import weixin.util.DateUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.MessageFormat;
import java.util.*;

/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: Controller
 * @Description: 关注用户管理
 * @date 2015-01-21 17:37:26
 */
@Scope("prototype")
@Controller
@RequestMapping("/weixinMemberController")
public class WeixinMemberController extends BaseController {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(WeixinMemberController.class);

    @Autowired
    private WeixinMemberServiceI weixinMemberService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private WeixinAccountServiceI weixinAccountService;

    @Autowired
    WeixinGroupServiceI weixinGroupService;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 关注用户管理列表 页面跳转
     *
     * @return
     */
    @RequestMapping(params = "weixinMember")
    public ModelAndView weixinMember(HttpServletRequest request) {

        // 查询分组
        List groupList = weixinGroupService.findByProperty(WeixinGroupEntity.class, "accountid",
                ResourceUtil.getWeiXinAccountId());
        if (null != groupList && groupList.size() > 0)

            request.setAttribute("groupList", RoletoJson.listToReplaceStr(groupList, "groupName", "id"));
        else
            request.setAttribute("groupList", "未知_-1");

        return new ModelAndView("weixin/member/weixinMemberList");
    }

    @RequestMapping(params = "weixinMemberChoose")
    public ModelAndView weixinMemberChoose(HttpServletRequest request) {
        // 查询分组
        List groupList = weixinGroupService.findByProperty(WeixinGroupEntity.class, "accountid",
                ResourceUtil.getWeiXinAccountId());
        if (null != groupList && groupList.size() > 0)

            request.setAttribute("groupList", RoletoJson.listToReplaceStr(groupList, "groupName", "id"));
        else
            request.setAttribute("groupList", "未知_-1");

        return new ModelAndView("weixin/member/weixinMemberChooseList");
    }

    /**
     * easyui AJAX请求数据
     *
     * @param request
     * @param response
     * @param dataGrid
     * @param user
     */

    @RequestMapping(params = "datagrid")
    public void datagrid(WeixinMemberEntity weixinMember, HttpServletRequest request, HttpServletResponse response,
                         DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WeixinMemberEntity.class, dataGrid);
        // 查询条件组装器
        //昵称模糊查询
        if (StringUtils.isNotBlank(weixinMember.getNickName())) {
        	weixinMember.setNickName("*" + weixinMember.getNickName() + "*");
        }
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinMember, request.getParameterMap());
        cq.eq("accountId", ResourceUtil.getWeiXinAccountId());// 根据公众ID进行数据隔离
        cq.eq("subscribe", "1");
        cq.add();
        this.weixinMemberService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * 删除关注用户管理
     *
     * @return
     */
    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(WeixinMemberEntity weixinMember, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        weixinMember = systemService.getEntity(WeixinMemberEntity.class, weixinMember.getId());
        message = "关注用户管理删除成功";
        try {
            weixinMemberService.delete(weixinMember);
            // systemService.addLog(message, Globals.Log_Type_DEL,
            // Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "关注用户管理删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 批量删除关注用户管理
     *
     * @return
     */
    @RequestMapping(params = "doBatchDel")
    @ResponseBody
    public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        message = "关注用户管理删除成功";
        try {
            for (String id : ids.split(",")) {
                WeixinMemberEntity weixinMember = systemService.getEntity(WeixinMemberEntity.class, id);
                weixinMemberService.delete(weixinMember);
                // systemService.addLog(message, Globals.Log_Type_DEL,
                // Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "关注用户管理删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 添加关注用户管理
     *
     * @param ids
     * @return
     */
    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(WeixinMemberEntity weixinMember, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        message = "关注用户管理添加成功";
        try {
            weixinMemberService.save(weixinMember);
            // systemService.addLog(message, Globals.Log_Type_INSERT,
            // Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "关注用户管理添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 更新关注用户管理
     *
     * @param ids
     * @return
     */
    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(WeixinMemberEntity weixinMember, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        message = "关注用户管理更新成功";
        WeixinMemberEntity t = weixinMemberService.get(WeixinMemberEntity.class, weixinMember.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(weixinMember, t);
            weixinMemberService.saveOrUpdate(t);
            // systemService.addLog(message, Globals.Log_Type_UPDATE,
            // Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "关注用户管理更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 关注用户管理新增页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(WeixinMemberEntity weixinMember, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(weixinMember.getId())) {
            weixinMember = weixinMemberService.getEntity(WeixinMemberEntity.class, weixinMember.getId());
            req.setAttribute("weixinMemberPage", weixinMember);
        }
        return new ModelAndView("weixin/member/weixinMember-add");
    }

    /**
     * 关注用户管理查看页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goView")
    public ModelAndView goView(WeixinMemberEntity weixinMember, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(weixinMember.getId())) {
            weixinMember = weixinMemberService.getEntity(WeixinMemberEntity.class, weixinMember.getId());
            req.setAttribute("weixinMemberPage", weixinMember);
        }
        return new ModelAndView("weixin/member/weixinMember-view");
    }

    /**
     * 关注用户管理编辑页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(WeixinMemberEntity weixinMember, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(weixinMember.getId())) {
            weixinMember = weixinMemberService.getEntity(WeixinMemberEntity.class, weixinMember.getId());
            req.setAttribute("weixinMemberPage", weixinMember);
        }
        return new ModelAndView("weixin/member/weixinMember-update");
    }

    /**
     * 修改分组
     *
     * @param id
     * @param groupId
     * @param request
     * @return
     */
    @RequestMapping(params = "doUpdateGroup")
    @ResponseBody
    public AjaxJson doUpdateGroup(String openIds, String groupId, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        String id = null;

        try {


            WeixinGroupEntity weixinGroupEntity = weixinGroupService.get(WeixinGroupEntity.class, groupId);

            // 到微信服务器修改分组
            JSONObject jsonO = new JSONObject();
            String[] openIdList = openIds.split(",");
            jsonO.put("openid_list", openIdList);// 普通用户的标识，对当前公众号唯一
            jsonO.put("to_groupid", weixinGroupEntity.getGroupId());

            String accessToken = weixinAccountService.getAccessToken();
            String url = WeixinUtil.update_members_groupid_url.replace("ACCESS_TOKEN", accessToken);

            // 获取接口返回结果
            JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", jsonO.toString());


            if (jsonObject != null) {
                if (("0").equals(jsonObject.get("errcode").toString())) {
                    List<WeixinMemberEntity> weixinMemberList = new ArrayList<WeixinMemberEntity>();
                    for (int i = 0; i < openIdList.length; i++) {
                        List<WeixinMemberEntity> temp = weixinMemberService.findByProperty(WeixinMemberEntity.class, "openId", openIdList[i]);
                        temp.get(0).setWeixinGroupEntity(weixinGroupEntity);
                        weixinMemberList.add(temp.get(0));
                    }
                    this.systemService.batchSave(weixinMemberList);//批量更新
//					weixinMemberService.saveOrUpdate(weixinMember);
                    message = "用户分组修改成功";
                } else {

                    message = "用户分组修改失败";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "用户分组修改失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 跳转到修改分组页面
     *
     * @param id
     * @param req
     * @return
     */
    @RequestMapping(params = "goUpdateGroup")
    public ModelAndView goUpdateGroup(HttpServletRequest request) throws UnsupportedEncodingException {

        String openIds = request.getParameter("openIds");
        String nickNames = request.getParameter("nickNames");
        request.setAttribute("openIds", openIds);
        request.setAttribute("nickNames", URLDecoder.decode(nickNames, "UTF-8"));
        // 查询分组
        WeixinGroupEntity weixinGroupEntity = new WeixinGroupEntity();
        weixinGroupEntity.setAccountid(ResourceUtil.getWeiXinAccountId());
        CriteriaQuery cq = new CriteriaQuery(WeixinGroupEntity.class);
        // 查询条件组装器
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinGroupEntity,
                request.getParameterMap());
        cq.eq("accountid", ResourceUtil.getWeiXinAccountId());// 根据公众ID进行数据隔离
        List<WeixinGroupEntity> weixinGroupList = weixinGroupService.getListByCriteriaQuery(cq, false);
        request.setAttribute("weixinGroupList", weixinGroupList);

        return new ModelAndView("weixin/member/weixinMember-updateGroup");
    }

    /**
     * 导入功能跳转
     *
     * @return
     */
    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        return new ModelAndView("weixin/member/weixinMemberUpload");
    }

    /**
     * 导出excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(params = "exportXls")
    public void exportXls(WeixinMemberBean weixinMemberBean, HttpServletRequest request, HttpServletResponse response,
                          DataGrid dataGrid) {
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try {
            codedFileName = "微信粉丝管理";
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
//            CriteriaQuery cq = new CriteriaQuery(WeixinMemberEntity.class, dataGrid);
//            // 查询条件组装器
//            //昵称模糊查询
//            if (StringUtils.isNotBlank(weixinMember.getNickName())) {
//            	weixinMember.setNickName("*" + weixinMember.getNickName() + "*");
//            }
//            org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinMember, request.getParameterMap());
//            cq.eq("accountId", ResourceUtil.getWeiXinAccountId());// 根据公众ID进行数据隔离
//            cq.notEq("subscribe", "2");
//            cq.add();
//
//            List<WeixinMemberEntity> weixinMembers = this.weixinMemberService.getListByCriteriaQuery(cq, false);
//            for(WeixinMemberEntity member: weixinMembers){           	
//            	Date date = member.getSubscribeTime();
//            	if(date != null){
//            	java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
//            	String format = formatter.format(date);
//            	member.setShortSubscribeTime(format);
//            	}
//            }
            String nickname = request.getParameter("nickName"); 		   //粉丝昵称
            String phoneNumber = request.getParameter("phoneNumber");      //手机号
            String sex = request.getParameter("sex");  				       //性别
    		String beginDate = request.getParameter("subscribeTime_begin");      //开始时间           
            String endDate = request.getParameter("subscribeTime_end");          //结束时间
            String accountid = ResourceUtil.getWeiXinAccountId();
            StringBuffer sql = new StringBuffer();
            sql.append(" select m.nick_name,m.head_img_url,m.open_id,m.id,m.phoneNumber,m.sex,m.created,m.subscribe_time,m.unsubscribe_time,m.city,m.province,p.cityName,(select provinceName from province pv where pv.provinceCode = p.provinceCode limit 1) provinceName ,m.subscribe from weixin_member m LEFT JOIN phonelocation p on p.beginNumber = LEFT(m.phoneNumber,7) ");
            sql.append(" where 1 = 1 ");
            sql.append(" and m.account_id=").append("'").append(accountid).append("'");
            //模糊搜索
           //      sql粉丝昵称
           if (nickname != null && !"".equals(nickname)) {
               sql.append(" and m.nick_name like").append("'%").append(nickname).append("%'");
           }
           //     sql手机号
           if (phoneNumber != null && !"".equals(phoneNumber)) {
               sql.append(" and m.phoneNumber like").append("'%").append(phoneNumber).append("%'");
           }
           //     sql性别
           if (sex != null && !"".equals(sex)) {
               sql.append(" and m.sex=").append("'").append(sex).append("'");
           }
           // 过滤掉关注状态为2          
               sql.append(" and m.subscribe != ").append("'").append(2).append("'");
           //	  sql开始时间
           if (StringUtils.isNotBlank(beginDate)) {
               sql.append(" and DATE_FORMAT(m.subscribe_time,'%Y-%m-%d') >= '" + beginDate + "' ");
           }
           //	  sql结束时间
           if (StringUtils.isNotBlank(endDate)) {
               sql.append(" and DATE_FORMAT(m.subscribe_time,'%Y-%m-%d') <= '" + endDate + "' ");
           } 
           // 添加查询的用户记录的时间排序
           sql.append(" ORDER BY m.subscribe_time DESC"); 
           dataGrid.setPage(1);
           dataGrid.setRows(65535);
           HqlQuery hqlQuery = new HqlQuery(WeixinMemberBean.class, sql.toString(), dataGrid,65535);
           // 分页
           PageList pageList = this.weixinMemberService.getPageListBySql(hqlQuery, false);
           List<Object[]> weixinMemberslist = pageList.getResultList();
           List<WeixinMemberBean> newweixinMemberslist = new ArrayList<WeixinMemberBean>();
           for (int i = 0; i < weixinMemberslist.size(); i++) {
           	Object[] result = weixinMemberslist.get(i);
           	WeixinMemberBean entity = new WeixinMemberBean();
           		Object name = result[0];
            	if(name != null){
            		entity.setNickName(name.toString());
            	}
//            	Object headimgurl = result[1];
//            	if(headimgurl != null){
//            		entity.setHeadImgUrl(headimgurl.toString());
//            	}
//            	Object openId = result[2];
//            	if(openId != null){
//            		entity.setOpenId(openId.toString());
//            	}
//            	Object Id = result[3];
//            	if(Id != null){
//            		entity.setId(Id.toString());
//            	}
            	Object phoneNumber1 = result[4];
            		if(phoneNumber1 != null){
            			entity.setPhoneNumber(phoneNumber1.toString());
            	}
               Object sex1 = result[5];
            		if(sex1 != null){
            			entity.setSex(sex1.toString());
            	}
               Object created = result[6];
                   if(created != null){
                   	String createdtime = DateUtils.date_sdf.format(created);
                       entity.setCreated(createdtime);
                   }   
               Object subscribeTime = result[7];
                   if(subscribeTime != null){
                   	String subscribetime = DateUtils.date_sdf.format(subscribeTime);
                       entity.setSubscribeTime(subscribetime);
                   }
               Object unsubscribeTime = result[8];
                   if(unsubscribeTime != null){
                   	String unsubscribetime = DateUtils.date_sdf.format(unsubscribeTime);
                       entity.setUnsubscribeTime(unsubscribetime);
                   }
              Object city = result[9];
           		if(city != null){
           			entity.setCity(city.toString());
           	}
           	  Object province = result[10];
        		if(province != null){
        			entity.setProvince(province.toString());
        	}
        	  Object cityname = result[11];
           		if(cityname != null){
           			entity.setCityName(cityname.toString());
           	}
           	  Object provincename = result[12];
        		if(provincename != null){
        			entity.setProvinceName(provincename.toString());
        	}
        	  Object subscribe = result[13];
            		if(subscribe != null){
            			entity.setSubscribe(subscribe.toString());
            	}
                   newweixinMemberslist.add(entity);
               }
            workbook = ExcelExportUtil.exportExcel(
                    new ExcelTitle("微信粉丝管理列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"),
                    WeixinMemberBean.class, newweixinMemberslist);
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
    public void exportXlsByT(WeixinMemberEntity weixinMember, HttpServletRequest request, HttpServletResponse response,
                             DataGrid dataGrid) {
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try {
            codedFileName = "微信粉丝管理";
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
                    new ExcelTitle("微信粉丝列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"),
                    WeixinMemberEntity.class, null);
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
                List<WeixinMemberEntity> listWeixinMemberEntitys = (List<WeixinMemberEntity>) ExcelImportUtil
                        .importExcelByIs(file.getInputStream(), WeixinMemberEntity.class, params);
                for (WeixinMemberEntity weixinMember : listWeixinMemberEntitys) {
                    weixinMemberService.save(weixinMember);
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

    /**
     * 下载微信关注用户
     *
     * @param ids
     * @return
     */
    @RequestMapping(params = "loadMembers")
    @ResponseBody
    public AjaxJson loadMembers(WeixinMemberEntity weixinMember, HttpServletRequest request) {

        AjaxJson j = new AjaxJson();

        String accountId = ResourceUtil.getWeiXinAccountId();
        if (!StringUtil.isNotEmpty(accountId)) {
            message = "微信公众号不存在";
            j.setMsg(message);
            return j;
        }
        String accessToken = weixinAccountService.getAccessToken(accountId);
         Thread t = new UpdateThread(accessToken,accountId);
         t.start();

        message = "同步关注用户信息数据成功！请耐心等待";
        j.setMsg(message);
        return j;
    }

    /**
     * 下载微信关注用户
     *
     * @param ids
     * @return
     */
    @RequestMapping(params = "loadMembersDetailInfo", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson loadMembersDetailInfo(@RequestBody JSONObject params) {
        if (!StringUtils.equals(params.optString("pass"), "evbao123")) {
            AjaxJson result = new AjaxJson();
            result.setMsg("success");
            result.setSuccess(true);
            return result;
        }
        String accountId = ResourceUtil.getWeiXinAccountId();
        params.element("accountId", accountId);
        String pageNoInRedis = RedisUtil.getRedis(accountId + ".member.loading.pageNo");
        // Integer pageNo = Integer.parseInt(StringUtils.defaultString(pageNoInRedis, "1"));
        Integer pageNo = params.optInt("pageNo", Integer.parseInt(StringUtils.defaultString(pageNoInRedis, "1")));
        params.element("pageSize", 100);

        PageList pageList = this.weixinMemberService.getInCompleteMembers(params);
        while (CollectionUtils.isNotEmpty(pageList.getResultList())) {
            long start = System.currentTimeMillis();
            long tokenTime = System.currentTimeMillis();
            String accessToken = weixinAccountService.getAccessToken();
            tokenTime = System.currentTimeMillis() - tokenTime;

// TODO 这里,在经过很长时间以后会变得很耗时,在700页面时,会达到20多秒才能执行完
            long syncDetailTime = System.currentTimeMillis();
            List<String> openIdList = new ArrayList<>();
            Map<String, WeixinMemberEntity> openIdMemberMap = new HashMap<>();
            WxUserList weixinUserInfo = this.weixinMemberService.getWeixinUserInfo(accessToken, openIdList, pageList, openIdMemberMap);
            if (null == weixinUserInfo) {
                continue;
            }
            this.weixinMemberService.syncMemberDetailInfo(accessToken, params, pageList, weixinUserInfo, openIdMemberMap);
            syncDetailTime = System.currentTimeMillis() - syncDetailTime;
// TODO 这里,在经过很长时间以后会变得很耗时,在700页面时,会达到20多秒才能执行完
            params.element("pageNo", ++pageNo);
            RedisUtil.setRedis(accountId + ".member.loading.pageNo", pageNo + "");

            long nextPageDataTime = System.currentTimeMillis();
            pageList = this.weixinMemberService.getInCompleteMembers(params);
            nextPageDataTime = System.currentTimeMillis() - nextPageDataTime;
            start = System.currentTimeMillis() - start;
            logger.info(MessageFormat.format("更新粉丝:商家的[{0}]第[{1}]页,耗时:{2}ms:token:{3}ms,详细信息:{4}ms,下一页数据:{5}ms",
                    accountId, pageNo, start, tokenTime, syncDetailTime, nextPageDataTime));

        }
        RedisUtil.del(accountId + ".member.loading.status");
        logger.info(MessageFormat.format("商家[{0}]的粉丝列表同步完毕, 共计[{1}]个粉丝", accountId, pageList.getCount()));
        AjaxJson result = new AjaxJson();
        result.setMsg("success fully");
        result.setSuccess(true);
        return result;
    }

    /**
     * 微信客服查看
     *
     * @param request
     * @param openId
     * @return
     */
    @RequestMapping(params = "memberInfoList")
    public ModelAndView memberInfoList(HttpServletRequest request, String openId) {
        if (StringUtil.isNotEmpty(openId)) {
            WeixinMemberEntity weixinMember = weixinMemberService.getWeixinMemberEntityByOpenIdAndAccountId(openId,
                    ResourceUtil.getWeiXinAccountId());
            request.setAttribute("weixinMemberPage", weixinMember);
        }
        return new ModelAndView("weixin/member/memberDetailList");
    }


    public class UpdateThread extends Thread {
        private String accessToken;
        private String accountId;

        public UpdateThread(String _accessToken,String _accountId){
            this.accessToken = _accessToken;
            this.accountId = _accountId;
        }

        public void run() {
            try {
                String loadingStatus = RedisUtil.getRedis(accountId + ".member.loading.status");
                if (StringUtils.equals("true", loadingStatus)) {
                    logger.info(MessageFormat.format("商家[{0}]今天正在抓取", accountId));
                    return;
                }
                try {
                    String loadingTimeStr = RedisUtil.getRedis(accountId + ".member.loading.time");
                    if (StringUtils.isNotBlank(loadingTimeStr) && StringUtils.isNumeric(loadingTimeStr)) {
                        long loadingTime = Long.parseLong(loadingTimeStr);
                        Date loadingDate = new Date(loadingTime);
                        if (DateUtils.isSameDay(loadingDate, new Date())) {
                            String lastLoadingDate = DateUtils.formatDate(loadingDate, "yyyy-MM-dd HH:mm:ss.SSS");
                            logger.info(MessageFormat.format("商家[{0}]今天已同步过粉丝列表,上次同步时间抓取时间:{1}", accountId, lastLoadingDate));
                            return;
                        } else {
                            RedisUtil.del(accountId + ".member.loading.pageNo");
                            RedisUtil.del(accountId + ".member.next.openId");
                        }
                    }
                } catch (Exception e) {
                    logger.error("获取redis中的上次抓取时间出错", e);
                }
                RedisUtil.setRedis(accountId + ".member.loading.status", "true");
                RedisUtil.setRedis(accountId + ".member.loading.time", DateUtils.KEY_EXPIRE_TIME_ONE_DAY, System.currentTimeMillis() + "");
                logger.info(MessageFormat.format("启动获取商家[{0}]的粉丝列表线程", accountId));
                JwUserAPI.getAllWxuser1(logger, weixinAccountService, accountId, weixinMemberService, RedisUtil.getRedis(accountId + ".member.next.openId"));
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            } finally {
                RedisUtil.del(accountId + ".member.loading.status");
                RedisUtil.del(accountId + ".member.next.openId");
            }
        }
    }

    /**
     * 根据openId获取石榴openId以及手机号码
     *
     * @param request
     * @return
     */
    @RequestMapping(params = "getPhoneNumberByOpenId")
    @ResponseBody
    public AjaxJson getPhoneNumberByOpenId(HttpServletRequest request, HttpServletResponse response) {
        String openId = request.getParameter("openId");
        AjaxJson resultJSON = new AjaxJson();
        if (StringUtils.isBlank(openId)) {
            resultJSON.setSuccess(false);
            resultJSON.setMsg("参数不能为空!");
            return resultJSON;
        }
        WeixinMemberEntity weixinMember = weixinMemberService.getWeixinMember(openId);
        if (null == weixinMember) {
            resultJSON.setSuccess(false);
            resultJSON.setMsg("粉丝信息不存在!");
            return resultJSON;
        }

        resultJSON.setSuccess(true);
        HashMap<String, Object> map = new HashMap<>();
        map.put("openId", openId);
        map.put("phoneNumber", weixinMember.getPhoneNumber());
        map.put("shiliuOpenId", weixinMember.getShiliuOpenId());
        map.put("subscribe", weixinMember.getSubscribe());
        map.put("accountId", weixinMember.getAccountId());
        resultJSON.setAttributes(map);
        return resultJSON;
    }

}


