package weixin.manual.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.core.util.FileUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExcelTitle;
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

import sdk.jfinal.JFinalUtils;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.integrate.entity.UserFlowAccountEntity;
import weixin.integrate.service.UserFlowAccountService;
import weixin.liuliangbao.controller.flowcontroller.FlowController;
import weixin.manual.entity.ManualGiven;
import weixin.manual.entity.ManualGivenRecords;
import weixin.manual.service.ManualGivenRecordsService;
import weixin.manual.service.ManualGivenService;
import weixin.manual.util.ManualGivenUtil;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinMemberServiceI;
import weixin.report.model.UserGiveFlowEntity;
import weixin.report.service.UserGiveFlowServiceI;
import weixin.tenant.entity.weixinAcctFlowAccountEntity;
import weixin.tenant.service.WeixinAcctFlowAccoutServiceI;
import weixin.util.DataDictionaryUtil.FlowType;

/**
 * @author Sukin 2016年9月5日
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller
@RequestMapping("/manualGivenController")
//implements Runnable
public class ManualGivenController extends BaseController {
    public static final transient Logger LOGGER = Logger.getLogger(ManualGivenController.class);

    @Autowired
    private ManualGivenService manualGivenService;
    @Autowired
    private SystemService systemService;

    @Autowired
    private WeixinAcctFlowAccoutServiceI accountService;

    @Autowired
    private UserFlowAccountService userFlowService;

    @Autowired
    private UserGiveFlowServiceI ugfService;

    @Autowired
    private ManualGivenRecordsService manualGivenRecordsService;

    @Autowired
    private WeixinMemberServiceI weixinMember;

    private CommonService commonService;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /* 商户手工赠送流量的列表 */
    @RequestMapping(params = "manualRecords")
    public ModelAndView getManualGivenRecord(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("/manual/manualGivenRecords");
        return mav;
    }

    /**
     * @author wangpeng 2016年9月6日
     * @param request
     * @param response
     * @param dataGrid
     */
    @RequestMapping(params = "mgrdatagrid")
    @ResponseBody
    public void mgrdatagrid(ManualGiven manualGiven, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(ManualGiven.class, dataGrid);
        // 查询条件组装器
        // 赠送描述模糊查询
        if (StringUtils.isNotBlank(manualGiven.getDes())) {
            manualGiven.setDes("*" + manualGiven.getDes() + "*");
        }
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, manualGiven, request.getParameterMap());
        LOGGER.info("商户id:" + ResourceUtil.getSessionUserName().getTenantId());
        cq.eq("accountid", ResourceUtil.getSessionUserName().getTenantId());// 根据公众ID进行数据隔离
        cq.add();
        this.manualGivenService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    /* 商户批次赠送流量详情 */
    @RequestMapping(params = "patchManualRecords")
    public ModelAndView patchManualRecords(HttpServletRequest request) {
        String patchNo = request.getParameter("patchNo");
        List<ManualGiven> mglist = this.systemService.findHql("from ManualGiven  where patchNo =?", patchNo);
        request.setAttribute("patchNo", patchNo);
        request.setAttribute("failnum", mglist.get(0).getTotalnum() - mglist.get(0).getSucnum());
        request.setAttribute("sucnum", mglist.get(0).getSucnum());
        return new ModelAndView("/manual/patchManualGivenRecords");
    }

    /**
     * @author wangpeng 2016年9月7日
     * @param request
     * @param response
     * @param dataGrid
     */
    @RequestMapping(params = "datagrid")
    @ResponseBody
    public void datagrid(ManualGivenRecords manualgivenrecords, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(ManualGivenRecords.class, dataGrid);
        // 查询条件组装器

        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, manualgivenrecords, request.getParameterMap());
        cq.add();
        this.manualGivenRecordsService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * 取消赠送
     *
     * @return
     */
    @RequestMapping(params = "doupdate")
    @ResponseBody
    public AjaxJson doupdate(ManualGiven manualGiven, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();

        manualGiven = systemService.getEntity(ManualGiven.class, manualGiven.getId());
        Integer isnow = manualGiven.getIsNow();
        String flowtype = manualGiven.getFlowType();
        Double totalvalue = manualGiven.getTotalFlow();
        String accountid = manualGiven.getAccountid();
        String patchno = manualGiven.getPatchNo();
        if (isnow == 2 && manualGiven.getGivenTime().getTime() > System.currentTimeMillis()) { // 预定状态并且赠送时间还没到
            // manualGiven.setTotalFlow(null);
            // manualGiven.setFlowValue(null);
            // 根据账户id查询流量值,返还商户流量
            double acctFlow;
            List<weixinAcctFlowAccountEntity> account = this.systemService.findHql("from weixinAcctFlowAccountEntity t where t.tenantId=?", accountid);
            if (FlowType.country.getCode().equals(flowtype)) { // 全国流量
                acctFlow = Double.valueOf(account.get(0).getCountryFlowValue());
                account.get(0).setCountryFlowValue(acctFlow + totalvalue);
            } else { // 省内流量
                acctFlow = Double.valueOf(account.get(0).getProvinceFlowValue());
                account.get(0).setProvinceFlowValue(acctFlow + totalvalue);
            }
            manualGiven.setResult("2");// 赠送失败
            manualGiven.setReason("3");// 商家取消赠送
            accountService.saveOrUpdate(account.get(0)); // 更新商户流量账户
            systemService.updateBySqlString("update ManualGivenRecords set result ='2',reason = '3' where reason='4' and patchNo='" + patchno + "'");
            manualGivenService.saveOrUpdate(manualGiven);
            message = "取消成功";
        } else {
            message = "流量已经赠送,请刷新网页查看,时间误差大约2分钟";
        }
        j.setMsg(message);
        return j;
    }

    /** 添加手工赠送流量 */
    @RequestMapping(params = "goAddManual")
    public ModelAndView goAddManualGiven(ManualGiven mg, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("/manual/addManualGiven1");
        return mav;
    }

    @RequestMapping(params = "doManualGiven")
    @ResponseBody
    public AjaxJson doManualGiven(HttpServletRequest request, HttpServletResponse response) throws ParseException, FileNotFoundException, IOException {
        long start = System.currentTimeMillis();// 开始时间
        StringBuilder ll = new StringBuilder();
        ll.append("进入手工赠送流量方法doManualGiven");
        AjaxJson aj = new AjaxJson();
        ManualGiven mg = new ManualGiven();
        Date now = new Date(); // 创建时间
        Date givenTime = null; // 赠送时间
        String id = UUID.randomUUID().toString(); // 生成id
        if (null == ResourceUtil.getSessionUserName()) {
            aj.setMsg("系统长时间没有操作,请退出系统重新登录之后再试!");
            return aj;
        }
        String merchantid = ResourceUtil.getSessionUserName().getTenantId(); // 商户id
        String filename = null; // 文件名
        String giveType = request.getParameter("giveType"); // 赠送类型
        String[] phone = null; // 手机号
        String getWays;
        if ("handgift".equals(giveType)) { // 手动输入
            String phoneno = request.getParameter("phoneNum");
            if (StringUtils.isBlank(phoneno)) {
                aj.setMsg("请输入手机号!");
                return aj;
            }
            phone = ManualGivenUtil.getphone(phoneno);
            filename = ManualGivenUtil.writeToFile(ManualGivenUtil.ruleProcess(phoneno));
            getWays = "1";
            ll.append("手机号的获取方式是手动输入！");
        } else { // 文本导入
            filename = request.getParameter("txtPath");
            if (StringUtils.isBlank(filename)) {
                aj.setMsg("请上传有效的文件!");
                return aj;
            }
            phone = ManualGivenUtil.readFromFile(filename);
            getWays = "2";
            ll.append("手机号的获取方式是文本导入！");
        }
        
        if (filename.contains("\\")) {
            filename = filename.substring(filename.lastIndexOf("\\") + 1);
        } else {
            filename = filename.substring(filename.lastIndexOf("/") + 1);
        }
        String patchno = ManualGivenUtil.makePatchNo();// 批次号
        String describe = request.getParameter("describe"); // 赠送描述
        double flowValue = Double.parseDouble(request.getParameter("flowValue")); // 每一个用户所得流量值
        String flowType = request.getParameter("flowType"); // 流量类型 1全国流量 2省内流量
        // 根据账户id查询流量值,判断商户流量是否充足
        mg.setId(id);
        mg.setAccountid(merchantid);
        mg.setPatchNo(patchno);
        String isNow = request.getParameter("giveTime"); // 赠送时间
        int iiinow; //
        if ("immediate".equals(isNow)) {// 立即赠送
            givenTime = now;
            ll.append("-----商户赠送时间:" + givenTime);
            iiinow = 1;

        } else { // 预定赠送
            String gt = request.getParameter("scheduledTime");
            ll.append("-----商户赠送时间:" + gt);
            givenTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(gt);
            if (givenTime.getTime() < System.currentTimeMillis()) { // 赠送时间不能小于当前时间
                aj.setMsg("赠送时间不能小于当前时间!");
                return aj;
            }
            iiinow = 2;
        }
        // 更新流量赠送规则
        mg.setFlowType(flowType);
        mg.setFlowValue(flowValue);
        mg.setIsNow(iiinow);
        mg.setDes(describe);
        mg.setCreateDate(now);
        mg.setGivenTime(givenTime);
        mg.setFileName(filename);
        mg.setGetWays(getWays);
        mg.setTotalnum(phone.length);
        mg.setSucnum(0);
        mg.setReason("4");
        mg.setResult("3");
        mg.setState("0");
        manualGivenService.save(mg);
        new Thread(){
           public void run(){
        		System.out.println("-----------------------------------------------------------线程开始--------------------------------------------------------------");
        			try {
        				check(mg);
        			} catch (IOException e) {
        				mg.setReason("5");
                        mg.setResult("2");
                   	    mg.setState("1");
                   	    manualGivenService.save(mg);                   	    
                   	    aj.setMsg("文件内容格式异常");
        				e.printStackTrace();
        			}
         }	
        }.start();
        aj.setMsg("提交成功");

        long end = System.currentTimeMillis();
        LOGGER.info(ll.toString() + "商户赠送流量花费时间doManualGiven:" + (end - start) + "ms");
        return aj;
    }
    
    /**
     * 手机号的校验，记录的初次保存
     * @param mg
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void check(ManualGiven mg) throws FileNotFoundException,IOException{
        String filename = mg.getFileName();
        try {
	        String[] phone = ManualGivenUtil.readFromFile(ResourceUtil.getMediaTxtPrefix()+"/"+filename);
	        // 查询商户id
	        List<WeixinAccountEntity> weixinAccount = this.systemService.findHql("from WeixinAccountEntity t where t.acctId=?", mg.getAccountid()); //
	        if (null == weixinAccount || weixinAccount.isEmpty()) {
	            return;
	        }
	        List<String> wfphone = new ArrayList<String>(); // 非法手机号
	        List<String> llphone = new ArrayList<String>(); // 黑名单手机号
	        List<String> naphone = new ArrayList<String>(); // 不在覆盖区域手机号
	        List<String> efphone = new ArrayList<String>(); // 合格手机号
	        for (String p : phone) { // 手机号验证
	            if (!ManualGivenUtil.regixPhone(p)) { // 不合法手机号的合法性
	            	if(p.length()>15){
	            		p = p.substring(0, 15); 
	            	}
	                wfphone.add(p);
	            } else if (weixinMember.getPhoneNumberInBlackList(p)) {// 黑名单
	                llphone.add(p);
	            } else if (!JFinalUtils.CKCoverandLocation(p, weixinAccount.get(0).getId())) { // 不在覆盖区域
	                naphone.add(p);
	            } else {// 合法手机号
	                efphone.add(p);
	            }
            }
	        double flowValue = mg.getFlowValue(); // 每一个用户所得流量值
	        double totalFlow;
	        String flowType = mg.getFlowType(); // 流量类型 1全国流量 2省内流量
	        // 根据账户id查询流量值,判断商户流量是否充足
	        double acctFlow;
	        totalFlow = flowValue * efphone.size();
	        // 更新流量赠送规则
	        mg.setTotalFlow(totalFlow);
	        String merchantid = mg.getAccountid();
	        int state = 0; // 状态 1表示商户流量充值 0表示流量不充足
            List<weixinAcctFlowAccountEntity> account = this.systemService.findHql("from weixinAcctFlowAccountEntity t where t.tenantId=?", merchantid);
	        if (FlowType.country.getCode().equals(flowType)) { // 全国流量
	                acctFlow = Double.valueOf(account.get(0).getCountryFlowValue());
	                if (acctFlow < totalFlow) {
	                    mg.setReason("2");
	                    mg.setResult("2");
	                    mg.setState("1");
	                    manualGivenService.saveOrUpdate(mg);
	                } else {
	                    account.get(0).setCountryFlowValue(acctFlow - totalFlow);
	                    state = 1;
	                }
	            } else { // 省内流量
	                acctFlow = Double.valueOf(account.get(0).getProvinceFlowValue());
	                if (acctFlow < totalFlow) {
	                    mg.setReason("2");
	                    mg.setResult("2");
	                    mg.setState("1");
	                    manualGivenService.saveOrUpdate(mg);
	                } else {
	                    account.get(0).setProvinceFlowValue(acctFlow - totalFlow);
	                    state = 1;
	                }
	            }
	            accountService.saveOrUpdate(account.get(0)); // 更新商户流量账户
	
	            // 合格手机号赠送详情
	            List<ManualGivenRecords> efmgrlist = new ArrayList<ManualGivenRecords>(); // 合格手机号详情记录
	            List<ManualGivenRecords> wfmgrlist = new ArrayList<ManualGivenRecords>(); // 不合法手机号详情记录
	            List<ManualGivenRecords> ncmgrlist = new ArrayList<ManualGivenRecords>(); // 不在覆盖区域手机号详情记录
	            List<ManualGivenRecords> llmgrlist = new ArrayList<ManualGivenRecords>(); // 黑名单手机号详情记录
	            if (state == 1) { // 商户流量充足
	                for (String efp : efphone) {
	                    ManualGivenRecords mgr = new ManualGivenRecords();
	                    mgr.setCreateDate(new Date());
	                    mgr.setId(UUID.randomUUID().toString());
	                    mgr.setFlowType(mg.getFlowType());
	                    mgr.setFlowValue(mg.getFlowValue());
	                    mgr.setGivenTime(mg.getGivenTime());
	                    mgr.setPhoneNum(efp);
	                    mgr.setPatchNo(mg.getPatchNo());
	                    mgr.setResult("4");
	                    mgr.setReason("4");
	                    efmgrlist.add(mgr);
	                    // manualGivenRecordsService.save(mgr);
	                }
	            } else {
	                for (String efp : efphone) {
	                    ManualGivenRecords mgr = new ManualGivenRecords();
	                    mgr.setCreateDate(new Date());
	                    mgr.setId(UUID.randomUUID().toString());
	                    mgr.setFlowType(mg.getFlowType());
	                    mgr.setFlowValue(0.0);
	                    mgr.setGivenTime(mg.getGivenTime());
	                    mgr.setPhoneNum(efp);
	                    mgr.setPatchNo(mg.getPatchNo());
	                    mgr.setResult("2");
	                    mgr.setReason("2");
	                    efmgrlist.add(mgr);
	                    // manualGivenRecordsService.save(mgr);
	                }
	            }
		        manualGivenRecordsService.batchSave(efmgrlist);
		        for (String phoneno : wfphone) { // 不合法手机号
		            ManualGivenRecords mgr = new ManualGivenRecords();
		            mgr.setCreateDate(new Date());
		            mgr.setId(UUID.randomUUID().toString());
		            mgr.setFlowType(mg.getFlowType());
		            mgr.setFlowValue(0.0);
		            mgr.setGivenTime(mg.getGivenTime());
		            mgr.setPhoneNum(phoneno);
		            mgr.setPatchNo(mg.getPatchNo());
		            mgr.setResult("2");
		            mgr.setReason("5");
		            wfmgrlist.add(mgr);
		        }
		        manualGivenRecordsService.batchSave(wfmgrlist);
		
		        for (String phoneno : llphone) { // 黑名单手机号
		            ManualGivenRecords mgr = new ManualGivenRecords();
		            mgr.setCreateDate(new Date());
		            mgr.setId(UUID.randomUUID().toString());
		            mgr.setFlowType(mg.getFlowType());
		            mgr.setFlowValue(0.0);
		            mgr.setGivenTime(mg.getGivenTime());
		            mgr.setPhoneNum(phoneno);
		            mgr.setPatchNo(mg.getPatchNo());
		            mgr.setResult("2");
		            mgr.setReason("6");
		            llmgrlist.add(mgr);// 手工赠送记录
		        }
		        manualGivenRecordsService.batchSave(llmgrlist);
		
		        for (String phoneno : naphone) { // 不在覆盖区域
		            ManualGivenRecords mgr = new ManualGivenRecords();
		            mgr.setCreateDate(new Date());
		            mgr.setId(UUID.randomUUID().toString());
		            mgr.setFlowType(mg.getFlowType());
		            mgr.setFlowValue(0.0);
		            mgr.setGivenTime(mg.getGivenTime());
		            mgr.setPhoneNum(phoneno);
		            mgr.setPatchNo(mg.getPatchNo());
		            mgr.setResult("2");
		            mgr.setReason("7");
		            ncmgrlist.add(mgr); // 手工赠送记录
		        }
		        manualGivenRecordsService.batchSave(ncmgrlist);           
	            mg.setState("2");
	            manualGivenService.saveOrUpdate(mg);

        }catch(Exception e){
            	 mg.setReason("5");
                 mg.setResult("2");
            	 mg.setState("1");
                manualGivenService.saveOrUpdate(mg);
                systemService.updateBySqlString("update ManualGivenRecords set result = '2',reason = '8' where patchNo='"+mg.getPatchNo()+"'");
                e.printStackTrace();
            }
    }

    /**
     * 导出excel 手工赠送流量的记录
     * 
     * @param request
     * @param response
     */
    @RequestMapping(params = "exportXls")
    public void exportXls(ManualGivenRecords manualgivenrecords, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try {
            codedFileName = "手工赠送流量记录";
            // 根据浏览器进行转码，使其支持中文文件名
            if (BrowserUtils.isIE(request)) {
                response.setHeader("content-disposition", "attachment;filename=" + java.net.URLEncoder.encode(codedFileName, "UTF-8") + ".xls");
            } else {
                String newtitle = new String(codedFileName.getBytes("UTF-8"), "ISO8859-1");
                response.setHeader("content-disposition", "attachment;filename=" + newtitle + ".xls");
            }
            // 产生工作簿对象
            HSSFWorkbook workbook = null;
            CriteriaQuery cq = new CriteriaQuery(ManualGivenRecords.class, dataGrid);
            // 查询条件组装器
            String patchNo = request.getParameter("patchNo");
            org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, manualgivenrecords, request.getParameterMap());
            cq.eq("patchNo", patchNo);// 根据公众ID进行数据隔离

            cq.add();
            List<ManualGivenRecords> manualgivenrecord = this.manualGivenRecordsService.getListByCriteriaQuery(cq, false);
            for (ManualGivenRecords manual : manualgivenrecord) {
                String flowType = manual.getFlowType();
                switch (flowType) {
                case "1":
                    manual.setFlowType("全国流量");
                    break;
                case "2":
                    manual.setFlowType("省内流量");
                    break;
                default:
                    manual.setFlowType(null);
                    break;
                }
                String result = manual.getResult();
                switch (result) {
                case "1":
                    manual.setResult("成功");
                    break;
                case "2":
                    manual.setResult("失败");
                    break;
                case "3":
                    manual.setResult("预定");
                    break;
                default:
                    manual.setResult(null);
                    break;
                }
                String reason = manual.getReason();
                switch (reason) {
                case "1":
                    manual.setReason("赠送成功");
                    break;
                case "2":
                    manual.setReason("余额不足");
                    break;
                case "3":
                    manual.setReason("取消赠送");
                    break;
                case "4":
                    manual.setReason("未到预定时间");
                    break;
                case "5":
                    manual.setReason("手机不合法");
                    break;
                case "6":
                    manual.setReason("手机黑名单");
                    break;
                case "7":
                    manual.setReason("不在覆盖区域");
                    break;
                case "8":
                    manual.setReason("异常");
                    break;
                default:
                    manual.setReason(null);
                    break;
                }
            }
            workbook = ExcelExportUtil.exportExcel(new ExcelTitle("手工赠送流量记录", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"),
                    ManualGivenRecords.class, manualgivenrecord);
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

    @RequestMapping(params = "uploadtxt", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson uploadTxt(MultipartHttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        Map<String, MultipartFile> fileMap = request.getFileMap();
        String filePathName = null;
        String fileRelativeUrl = null;// 图片相对url. 相对url加上前缀,就构成了完整的图片url
        AjaxJson j = new AjaxJson();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile mf = entity.getValue();// 获取上传文件对象
            String fileName = mf.getOriginalFilename();// 获取文件名
            String extend = FileUtils.getExtend(fileName);// 获取文件扩展名
            if (!"txt".equalsIgnoreCase(extend)) {
                j.setMsg("请上传txt文件!");
                return j;
            }
            filePathName = ResourceUtil.getMediaTxtPrefix() + File.separator + System.currentTimeMillis() + "." + extend;
            fileRelativeUrl = System.currentTimeMillis() + "." + extend;
            try {
                byte[] imageBytes = mf.getBytes();

                // 将文件内容写到指定的文件之中
                ManualGivenUtil.writeData2File(filePathName, imageBytes);
                LOGGER.info("文件已经保存到" + filePathName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("txtPath", filePathName);
        attributes.put("txtRelativeUrl", fileRelativeUrl);
        attributes.put("fileKey", "");
        attributes.put("name", "");
        attributes.put("viewhref", "");
        attributes.put("delurl", "");
        j.setMsg("文件添加成功");
        j.setAttributes(attributes);

        return j;
    }

}
