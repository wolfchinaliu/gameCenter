package weixin.lottery.controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import weixin.lottery.entity.WeixinLotteryEntity;
import weixin.lottery.service.WeixinLotteryServiceI;
import weixin.util.DateUtils;


/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: Controller
 * @Description: 微信活动
 * @date 2015-02-05 14:26:01
 */
@Scope("prototype")
@Controller
@RequestMapping("/weixinLotteryController")
public class WeixinLotteryController extends BaseController {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(WeixinLotteryController.class);

    @Autowired
    private WeixinLotteryServiceI weixinLotteryService;
    @Autowired
    private SystemService systemService;
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
    @RequestMapping(params = "weixinLotteryAddress")
    public ModelAndView weixinLotteryAddress(HttpServletRequest request) {
        request.setAttribute("hdid", request.getParameter("hdid"));
        return new ModelAndView("weixin/lottery/weixinLotteryAddress");
    }


    /**
     * 微信活动列表 页面跳转
     *
     * @return
     */
    @RequestMapping(params = "weixinLottery")
    public ModelAndView weixinLottery(HttpServletRequest request) {
        request.setAttribute("lotteryType", request.getParameter("lotteryType"));
        return new ModelAndView("weixin/lottery/weixinLotteryList");
    }


    /**
     * 活动列表页面-大转盘、刮刮卡、红包分别调用-刘晓春-2015年12月17日
     *
     * @param weixinLottery
     * @param request
     * @param response
     * @param dataGrid
     */
    @RequestMapping(params = "datagrid")
    public void datagrid(WeixinLotteryEntity weixinLottery, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WeixinLotteryEntity.class, dataGrid);
        //商户ID
        weixinLottery.setAccountid(ResourceUtil.getWeiXinAccountId());
        //查询条件组装器
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinLottery, request.getParameterMap());
        cq.add();
        this.weixinLotteryService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * 删除微信活动
     *
     * @return
     */
    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(WeixinLotteryEntity weixinLottery, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        weixinLottery = systemService.getEntity(WeixinLotteryEntity.class, weixinLottery.getId());
        message = "微信活动删除成功";
        try {
            weixinLotteryService.delWeixinLottery(weixinLottery);
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
                WeixinLotteryEntity weixinLottery = systemService.getEntity(WeixinLotteryEntity.class,
                        id
                );
                weixinLotteryService.delete(weixinLottery);
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
    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(WeixinLotteryEntity weixinLottery, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        Integer one = weixinLottery.getFirstprizeProb()==null?0:weixinLottery.getFirstprizeProb();
        Integer two = weixinLottery.getSecondprizeProb()==null?0:weixinLottery.getSecondprizeProb();
        Integer three = weixinLottery.getThirdprizeProb()==null?0:weixinLottery.getThirdprizeProb();
        Integer four = weixinLottery.getForthprizeProb()==null?0:weixinLottery.getForthprizeProb();
        Integer five = weixinLottery.getFifthprizeProb()==null?0:weixinLottery.getFifthprizeProb();
        Integer six = weixinLottery.getSixthprizeProb()==null?0:weixinLottery.getSixthprizeProb();
        Integer total = one + two + three+four+five+six;
        String start = request.getParameter("starttime");
        String endtime = request.getParameter("endtime");
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

//        if (date.getTime() > weixinLottery.getStarttime().getTime()) {
//            j.setSuccess(false);
//            j.setMsg("开始时间不能小于当前的时间");
//            return j;
//        }

        if (total > 10000) {
            j.setSuccess(false);
            j.setMsg("概率之和不能大于10000");
            return j;
        }
        /*
        * 添加的判断
        * */
        if (weixinLottery.getLotterynumber() < weixinLottery.getLotterynumberday()) {
            j.setSuccess(false);
            j.setMsg("总的抽奖次数不能小于每人抽奖次数");
            return j;
        }
        if (weixinLottery.getStarttime().getTime() >= weixinLottery.getEndtime().getTime()) {
            j.setSuccess(false);
            j.setMsg("结束时间必须大于开始时间");
            return j;
        }
        if (date.getTime() > weixinLottery.getEndtime().getTime()) {
            j.setMsg("结束时间不能小于当前的时间");
            j.setSuccess(false);
            return j;
        }
//        if()
        message = "微信活动添加成功";
        try {
            if(date.getTime()>weixinLottery.getStarttime().getTime()){
                weixinLottery.setState("1");//设置为活动进行中
            }else {
                weixinLottery.setState("2");//设置活动为尚未开始。
            }
            weixinLotteryService.save(weixinLottery);
            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
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
    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(WeixinLotteryEntity weixinLottery, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        Integer one = weixinLottery.getFirstprizeProb()==null?0:weixinLottery.getFirstprizeProb();
        Integer two = weixinLottery.getSecondprizeProb()==null?0:weixinLottery.getSecondprizeProb();
        Integer three = weixinLottery.getThirdprizeProb()==null?0:weixinLottery.getThirdprizeProb();
        Integer four = weixinLottery.getForthprizeProb()==null?0:weixinLottery.getForthprizeProb();
        Integer five = weixinLottery.getFifthprizeProb()==null?0:weixinLottery.getFifthprizeProb();
        Integer six = weixinLottery.getSixthprizeProb()==null?0:weixinLottery.getSixthprizeProb();
        Integer total = one + two + three+four+five+six;
        if (total > 10000) {
            j.setSuccess(false);
            j.setMsg("概率之和不能大于10000");
            return j;
        }
       /* if (StringUtils.isBlank(weixinLottery.getSixthprize())) {
            j.setSuccess(false);
            j.setMsg("六等奖流量值不能为空");
            return j;
        }*/
        WeixinLotteryEntity t = weixinLotteryService.get(WeixinLotteryEntity.class, weixinLottery.getId());
        Date starttime = weixinLottery.getStarttime()==null?t.getStarttime():weixinLottery.getStarttime();
        Date enttime = weixinLottery.getEndtime()==null?t.getEndtime():weixinLottery.getEndtime();
        if (starttime.getTime() >= enttime.getTime()) {
            j.setSuccess(false);
            j.setMsg("结束时间必须大于开始时间");
            return j;
        }
        message = "微信活动更新成功";
        try {
            MyBeanUtils.copyBeanNotNull2Bean(weixinLottery, t);
            weixinLotteryService.saveOrUpdate(t);
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "微信活动更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }


    /**
     * 微信活动新增页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(WeixinLotteryEntity weixinLottery, HttpServletRequest req) {
        req.setAttribute("lotteryType", req.getParameter("lotteryType"));
        String lotteryType= req.getParameter("lotteryType");
        if (StringUtil.isNotEmpty(weixinLottery.getId())) {
            weixinLottery = weixinLotteryService.getEntity(WeixinLotteryEntity.class, weixinLottery.getId());
            req.setAttribute("weixinLotteryPage", weixinLottery);
        }
        if("1".equals(lotteryType)){
            return new ModelAndView("weixin/lottery/weixinzhuanpan-add");
        }else {
            return new ModelAndView("weixin/lottery/weixinLottery-add");
        }

    }

    /**
     * 微信活动编辑页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(WeixinLotteryEntity weixinLottery, HttpServletRequest req,HttpServletResponse resp) throws Exception {
        req.setAttribute("lotteryType", req.getParameter("lotteryType"));

        String lotteryType=req.getParameter("lotteryType");
        if (StringUtil.isNotEmpty(weixinLottery.getId())) {
            weixinLottery = weixinLotteryService.getEntity(WeixinLotteryEntity.class, weixinLottery.getId());

            Date createTime = weixinLottery.getStarttime();
            req.setAttribute("createTime", createTime);   //活动开始时间
            Date endTime = weixinLottery.getEndtime();
            req.setAttribute("endTime", DateUtils.datetimeFormat.format(endTime));   //活动结束时间

            Date date = new Date();    //定义一个当前的日期
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = format.format(date);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   ////定义一个当前的日期，转换为相应的日期
            Date date1 = sdf.parse(time);
            //            分三种情况进行判读
//            活动未开始的时候，如果当前日期小于开始日期，表示尚未开始
            if (date1.getTime() < createTime.getTime()) {
                req.setAttribute("hd", "-1");
            }
//            活动进行中，当前日期介于开始和结束日期之间
            if (date1.getTime() > createTime.getTime() && date1.getTime() < endTime.getTime()) {
                req.setAttribute("hd", "0");
            }
//            活动结束后，当前日期大于结束日期
            if (date1.getTime() > endTime.getTime()) {
                req.setAttribute("hd", "1");
            }
            req.setAttribute("weixinLotteryPage", weixinLottery);
        }
//        OutputStream os = resp.getOutputStream();
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
//        bw.write(weixinLottery.getEndtime().toString());
//        return null;
//        根据传入的类型进行判断，如果是1代表是大转盘
        if("1".equals(lotteryType)){
            return new ModelAndView("weixin/lottery/weixinzhuanpan-update");
        }else {
            return new ModelAndView("weixin/lottery/weixinLottery-update");
        }
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
    public void exportXls(WeixinLotteryEntity weixinLottery, HttpServletRequest request, HttpServletResponse response
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
            CriteriaQuery cq = new CriteriaQuery(WeixinLotteryEntity.class, dataGrid);
            org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinLottery, request.getParameterMap());

            List<WeixinLotteryEntity> weixinLotterys = this.weixinLotteryService.getListByCriteriaQuery(cq, false);
            workbook = ExcelExportUtil.exportExcel(new ExcelTitle("微信活动列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(),
                    "导出信息"), WeixinLotteryEntity.class, weixinLotterys);
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
    public void exportXlsByT(WeixinLotteryEntity weixinLottery, HttpServletRequest request, HttpServletResponse response
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
                    "导出信息"), WeixinLotteryEntity.class, null);
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
                List<WeixinLotteryEntity> listWeixinLotteryEntitys =
                        (List<WeixinLotteryEntity>) ExcelImportUtil.importExcelByIs(file.getInputStream(), WeixinLotteryEntity.class, params);
                for (WeixinLotteryEntity weixinLottery : listWeixinLotteryEntitys) {
                    weixinLotteryService.save(weixinLottery);
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
}
