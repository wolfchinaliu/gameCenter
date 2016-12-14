package weixin.liuliangbao.business.controller;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.timer.DynamicTask;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.RoletoJson;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExcelTitle;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import weixin.liuliangbao.business.entity.BusinessInterfaceEntity;
import weixin.liuliangbao.business.service.BusinessInterfaceServiceI;
import weixin.liuliangbao.weigatedoor.entity.WeidoorpptEntity;
import weixin.source.entity.WeixinSourceEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


/**
 * Created by aa on 2015/11/30.
 */
@Controller
@RequestMapping("/businessInterfaceController")
public class BusinessInterfaceController extends BaseController {

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(BusinessInterfaceController.class);

    @Autowired
    private BusinessInterfaceServiceI businessInterfaceService;

    @Autowired
    private BusinessInterfaceServiceI businessService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private DynamicTask dynamicTask;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @RequestMapping(params = "datagrid")
    public void datagrid(BusinessInterfaceEntity businessInterface,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(BusinessInterfaceEntity.class, dataGrid);
        //查询条件组装器
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, businessInterface, request.getParameterMap());
        try{
            //自定义追加查询条件
        }catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        cq.add();
        this.businessInterfaceService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);


    }


    /**
     * 运营商接口 页面跳转 -许丹-2015年12月1日10:19:19
     *
     * @return
     */
    @RequestMapping(params = "businessInterfaceList")
    public ModelAndView BusinessInterfaceList(HttpServletRequest request) {

        //查询地域下拉框
        //查询运营商
        List<BusinessInterfaceEntity> businessList=systemService.getList(BusinessInterfaceEntity.class);
        if(null!=businessList && businessList.size()>0){
            request.setAttribute("businessReplace",RoletoJson.listToReplaceStr(businessList,"businessName","id"));

            request.setAttribute("provinceReplace",RoletoJson.listToReplaceStr(businessList,"provinceName","id"));}
        else {
            request.setAttribute("businessReplace", "未知_-1");
            request.setAttribute("provinceReplace", "未知_-1");
        }
        return new ModelAndView("liuliangbao/business/businessInterface");
    }


    /**
     * 启用或停用运营商接口
     * @param businessInterface
     * @param request
     * @return
     */
    @RequestMapping(params = "startOrStopInterface")
    @ResponseBody
    public AjaxJson startOrStopInterface(BusinessInterfaceEntity businessInterface,HttpServletRequest request){
        AjaxJson j=new AjaxJson();
        boolean isStart=businessInterface.getState().equals("1");
        businessInterface=systemService.get(BusinessInterfaceEntity.class,businessInterface.getId());
        boolean isSuccess=dynamicTask.startOrStop(businessInterface.getState(),isStart);
        if(isSuccess){
            businessInterface.setState(isStart?"1":"0");
            businessService.updateEntitie(businessInterface);
            systemService.addLog((isStart?"启用":"关闭")+businessInterface.getId(), Globals.Log_Type_UPDATE,Globals.Log_Leavel_INFO);
        }
        j.setMsg(isSuccess?"运营商接口启用成功":"运营商接口关闭成功");
        return j;
    }


    /**
     * 运行运营商接口-许丹-2015年12月7日10:19:00
     * @param businessInterface
     * @param request
     * @return
     */
    @RequestMapping(params = "goRun")
    @ResponseBody
    public AjaxJson goRun(BusinessInterfaceEntity businessInterface, HttpServletRequest request){
        AjaxJson j=new AjaxJson();
        businessInterface=systemService.getEntity(BusinessInterfaceEntity.class,businessInterface.getId());
        message="接口运行成功";
        try{
            businessInterface.setState("1");
            businessService.updateEntitie(businessInterface);
        }catch (Exception e){
            e.printStackTrace();
            message="接口运行失败";
            throw  new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }


    /**
     * 停止运营商接口-许丹-2015年12月7日10:19:06
     * @param businessInterface
     * @param request
     * @return
     */
    @RequestMapping(params = "goQuit")
    @ResponseBody
    public AjaxJson goQuit(BusinessInterfaceEntity businessInterface, HttpServletRequest request){
        AjaxJson j=new AjaxJson();
        businessInterface=systemService.getEntity(BusinessInterfaceEntity.class,businessInterface.getId());
        message="接口停止成功";
        try{
            businessInterface.setState("0");
            businessService.updateEntitie(businessInterface);
        }catch (Exception e){
            e.printStackTrace();
            message="接口停止失败";
            throw  new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }


    /**
     * 设置运营商接口为默认-许丹-2015年12月7日10:22:55
     * @param businessInterface
     * @param request
     * @return
     */
    @RequestMapping(params = "goSetDefault")
    @ResponseBody
    public AjaxJson goSetDefault(BusinessInterfaceEntity businessInterface,HttpServletRequest request){
        AjaxJson j=new AjaxJson();
        businessInterface=systemService.getEntity(BusinessInterfaceEntity.class,businessInterface.getId());
        //首先判断这个接口是否为全国接口，如果不是全国接口提示不能设置为默认
        String provinceCode=businessInterface.getProvinceCode();
        if (provinceCode.equals("0")){
            message="接口设置默认成功";
            try{
                businessInterface.setIsDefault(1);
                businessService.updateEntitie(businessInterface);
            }catch (Exception e){
                e.printStackTrace();
                message="接口设置默认失败";
                throw  new BusinessException(e.getMessage());
            }
        }else{
            message="此接口不是全国接口，不能设置为默认通道！";
        }
        j.setMsg(message);
        return j;
    }


    /**
     * 设置运营商接口为非默认-许丹-2015年12月7日10:22:55
     * @param businessInterface
     * @param request
     * @return
     */
    @RequestMapping(params = "goSetNotDefault")
    @ResponseBody
    public AjaxJson goSetNotDefault(BusinessInterfaceEntity businessInterface,HttpServletRequest request){
        AjaxJson j=new AjaxJson();
        businessInterface=systemService.getEntity(BusinessInterfaceEntity.class,businessInterface.getId());
        message="接口设置非默认成功";
        try{
            businessInterface.setIsDefault(0);
            businessService.updateEntitie(businessInterface);
        }catch (Exception e){
            e.printStackTrace();
            message="接口设置非默认失败";
            throw  new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }




    /**
     * 导出excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(params = "exportXls")
    public void exportXls(BusinessInterfaceEntity businessInterface, HttpServletRequest request, HttpServletResponse response
            , DataGrid dataGrid) {
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try {
            codedFileName = "运营商接口管理";
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
            CriteriaQuery cq = new CriteriaQuery(BusinessInterfaceEntity.class, dataGrid);
            org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, businessInterface, request.getParameterMap());

            List<BusinessInterfaceEntity> businessInterfaces = this.businessService.getListByCriteriaQuery(cq, false);
            workbook = ExcelExportUtil.exportExcel(new ExcelTitle("运营商接口管理列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(),
                    "导出信息"), BusinessInterfaceEntity.class, businessInterfaces);
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
    public void exportXlsByT(BusinessInterfaceEntity businessInterface, HttpServletRequest request, HttpServletResponse response
            , DataGrid dataGrid) {
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try {
            codedFileName = "运营商接口管理";
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
            workbook = ExcelExportUtil.exportExcel(new ExcelTitle("运营商接口管理列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(),
                    "导出信息"), BusinessInterfaceEntity.class, null);
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
