package weixin.tenant.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.jeecgframework.core.util.*;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.service.TSbaseUserService;
import org.jeecgframework.web.system.service.UserService;

import weixin.activity.entity.WeixinAcountOuterEntity;
import weixin.advertisement.service.AdvertisementServiceI;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.message.entity.TextTemplate;
import weixin.liuliangbao.jsonbean.CityBean;
import weixin.liuliangbao.jsonbean.ProvinceBean;
import weixin.liuliangbao.jsonbean.FlowAccount.WeixinAcctBean;
import weixin.liuliangbao.jsonbean.FlowAccount.flowAccountBean;
import weixin.liuliangbao.util.HttpUtil;
import weixin.member.controller.ConnectionsManager;
import weixin.register.RegisterController;
import weixin.tenant.entity.*;
import weixin.tenant.service.*;

import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.DataGridReturn;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;

import java.io.OutputStream;

import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExcelTitle;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: Controller
 * @Description: 商户管理表
 * @date 2015-04-25 21:14:50
 */
//@Scope("prototype")
@Controller
@RequestMapping("/weixinAcctController")
public class WeixinAcctController extends BaseController {
    /**
     * Logger for this class
     */
    private static final Logger LOGGER = Logger.getLogger(WeixinAcctController.class);

    @Autowired
    private WeixinAcctServiceI weixinAcctService;
    @Autowired
    private SystemService systemService;
    private String message;
    @Autowired
    private WeixinAccountServiceI weixinAccountService;
    @Autowired
    private TSbaseUserService tSbaseUserService;
    @Autowired
    private AdvertisementServiceI adService;
    /**
     * 将流量账户的信息注册进来
     */
    @Autowired
    private WeixinAcctFlowAccoutServiceI weixinAcctFlowAccoutServiceI;
    @Autowired
    private ThirdOpenServiceI thirdOpenServiceI;
    /**
     * 将商户的覆盖区域进行注册
     */
    @Autowired
    private WeixinMerchantCoverAreaServiceI weixinMerchantCoverAreaServiceI;
    @Autowired
    private WeixinAdminServiceI weixinAdminService;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 商户信息(客户访问)
     *
     * @return
     */
    @RequestMapping(params = "myWeixinAcct")
    public ModelAndView myWeixinAcct(HttpServletRequest request) {

        WeixinAcctEntity acctEntity = new WeixinAcctEntity();
        acctEntity = weixinAcctService.getEntity(WeixinAcctEntity.class, ResourceUtil.getSessionUserName().getTenantId());
        request.setAttribute("currAcct", acctEntity);
        //普通员工账号没有权限
        TSUser users = systemService.get(TSUser.class, ResourceUtil.getSessionUserName().getId());
//        if ("0".equals(users.getType())) {
//            return new ModelAndView("common/403");
//        }

        return new ModelAndView("weixin/tenant/myAcctList");
    }

    /**
     * 商户信息(客户访问)
     *
     * @return
     */
    @RequestMapping(params = "areaAdd")
    public ModelAndView areaAdd(HttpServletRequest request) {


        return new ModelAndView("weixin/tenant/weixinAcct_areaAdd");
    }

    /**
     * 查询所有的省
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "addProvince")
    public void provinceFind(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //        调用后台的查询所有商户的信息的接口方法
//输出到页面
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String url = "http://localhost:18082/jwx/province/Allprovince";

        //        使用工具类获取到查询的内容
        String content = HttpUtil.httpGet(url).toString();
////        使用Gson将传递的数据变为bean格式
        Gson gson = new Gson();
//        此处定义一个bean进行接收
        Type type = new TypeToken<ProvinceBean>() {
        }.getType();

        ProvinceBean provinceJson = gson.fromJson(content, type);

        String json = gson.toJson(provinceJson.getData());

        out.write(json);

    }

    /**
     * 根据省查询市
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "addCity")
    public void cityByProvinceId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Gson gson = new Gson();
        //        调用后台的查询所有商户的信息的接口方法
//输出到页面
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        String url = "http://localhost:18082/jwx/province/FindAllCityByPcode";

        JSONObject myJsonObject = new JSONObject();
        myJsonObject.accumulate("id", id);
        JSONObject content = HttpUtil.httpPost(url, myJsonObject, false);

        String reStr = gson.toJson(content);

        //        此处定义一个bean进行接收
        Type type = new TypeToken<CityBean>() {
        }.getType();

        CityBean CityBean = gson.fromJson(reStr, type);

        String json = gson.toJson(CityBean.getData());

        out.write(json);
    }
   
    /**
     * 我的商户信息(客户访问)
     *
     * @param request
     * @param response
     * @param dataGrid
     */
    @RequestMapping(params = "maccdatagrid")
    public void maccdatagrid(WeixinAcctBean weixinAcct, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
    	 StringBuilder datagrid = new StringBuilder();
         long startTime = System.currentTimeMillis();//获取开始当前的时间   查询方法的执行时间
         datagrid.append("方法开始时间_" + startTime + "ms" + "_");  //添加的日志
         
         String acctForName = request.getParameter("acctForName");  //商户名称
         String username = request.getParameter("username");     //登入名称
         String Acclevel = request.getParameter("acctlevel");    //商户级别
         String belogAcct = request.getParameter("belogAcct");   //所属商户
         String businessType = request.getParameter("businessType");     //商业类型
         String province = request.getParameter("province");     //省份
         String city = request.getParameter("city");     //市区 
         String ids = ResourceUtil.getSessionUserName().getTenantId();  //登录的商户id，也就是查询其下面的所有商户
         StringBuffer sql = new StringBuffer();
         sql.append("select a.id,a.acctForName,t.username,m.accountId,m.tenantId,a.acct_level,a.belogAcct,a.business_Type,a.province,a.city,a.totalAccount,a.opendedAccount,a.status from MerchantFlowAccount m join weixin_acct a on m.tenantId=a.id join t_s_base_user t on t.accountId = m.accountId where 1=1 ");
         WeixinAcctEntity weixinAcctEntity = weixinAcctService.getEntity(WeixinAcctEntity.class, ids);
         if (!"0".equals(weixinAcctEntity.getAcctLevel())) {
         sql.append("and m.acct_id=").append("'").append(ids).append("'"); //根据公众ID进行数据隔离
//       sql.append("and a.pid != '"+ResourceUtil.getSessionUserName().getTenantId()+"' ");
         } 
//         else {
//         sql.append("and m.acct_id !=").append("'").append(ids).append("'");
//         
//         }
         sql.append(" and a.acct_level !=0 ");
        //       模糊查询，商户姓名
        if (acctForName != null && !"".equals(acctForName)) {
            sql.append(" and a.acctForName like").append("'%").append(acctForName).append("%'");
        }
        //       模糊查询，登入名称
        if (username != null && !"".equals(username)) {
            sql.append(" and t.username like").append("'%").append(username).append("%'");
        }
        //       sql查询，商户级别
        if (Acclevel != null && !"".equals(Acclevel)) {
            sql.append(" and a.acct_level=").append("'").append(Acclevel).append("'");
        }
        //       模糊查询，所属商户
        if (belogAcct != null && !"".equals(belogAcct)) {
            sql.append(" and a.belogAcct like").append("'%").append(belogAcct).append("%'");
        }
        //      sql查询，商业类型
        if (businessType != null && !"".equals(businessType)) {
            sql.append(" and a.business_Type=").append("'%").append(businessType).append("%'");
        } 
        //      sql查询，省份
        if (province != null && !"".equals(province)) {
            sql.append(" and a.province=").append("'%").append(province).append("%'");
        }
        //      sql查询，市名
        if (city != null && !"".equals(city)) {
            sql.append(" and a.city=").append("'%").append(city).append("%'");
        }
        datagrid.append("当前执行的sql语句" + sql + "_");  //添加的日志
        HqlQuery hqlQuery = new HqlQuery(WeixinAcctBean.class, sql.toString(), dataGrid);
        // 分页
        PageList pageList = this.weixinAccountService.getPageListBySql(hqlQuery, false);
        datagrid.append("当前pageList查询的条数" + pageList + "_");  //添加的日志
        List<Object[]> list = pageList.getResultList();
        List<WeixinAcctBean> param = new ArrayList<WeixinAcctBean>();
        for(Object[] objects : list) {
        	WeixinAcctBean wd = new WeixinAcctBean();
        	Object gid = objects[0];
            if (gid != null) {
                wd.setId(gid.toString());
            }
            Object gname = objects[1];
            if (gname != null) {
                wd.setAcctForName(gname.toString());
            }
            Object gnames = objects[2];
            if (gnames != null) {
                wd.setUsername(gnames.toString());
            }
            Object tcid = objects[3];
            if (tcid != null) {
                wd.setAccountId(tcid.toString());
            }
            Object tid = objects[4];
            if (tid != null) {
                wd.setTenantId(tid.toString());
            }
            Object level = objects[5];
            if (level != null) {
                wd.setAcctlevel(level.toString());
            }
            Object beact = objects[6];
            if (beact != null) {
                wd.setBelogAcct(beact.toString());
            }
            Object busiType1 = objects[7];
            if (busiType1 != null) {
                wd.setBusinessType(busiType1.toString());
            }
            Object provinc = objects[8];
            if (provinc != null) {
                wd.setProvince(provinc.toString());
            } else {
            	wd.setProvince("");
            }
            Object cit = objects[9];
            if (cit != null) {
                wd.setCity(cit.toString());
            } else {
            	wd.setCity("");
            }
            Object totalAccount = objects[10];
            if (totalAccount != null) {
                wd.setTotalAccount(Integer.parseInt(totalAccount.toString()));
            } else {
            	wd.setTotalAccount(null);
            }
            Object opendedAccount = objects[11];
            if (opendedAccount != null) {
                wd.setOpendedAccount(Integer.parseInt(opendedAccount.toString()));
            } else {
            	wd.setOpendedAccount(null);
            }
            Object status = objects[12];
            if (status != null) {
                wd.setStatus(status.toString());
            } else {
            	wd.setStatus("");
            }
            param.add(wd);
        }
        dataGrid.setResults(param);  
        dataGrid.setTotal(pageList.getCount());
        dataGrid.setPage(pageList.getCurPageNO());
        dataGrid.setRows(pageList.getOffset());
        TagUtil.datagrid(response, dataGrid); 
        
        long endTime = System.currentTimeMillis();//获取结束的当前时间   检测程序的运行时间
        datagrid.append("方法结束时间_" + endTime + "ms" + "_");  //添加的日志

        long totaltime = endTime - startTime;  //总耗时
        datagrid.append("方法执行总的时间" + totaltime + "ms");  //添加的日志

        LOGGER.info("weixinAcctController的__maccdatagrid__方法执行过程中的各个操作的输入输出参数以及结果_" + datagrid.toString());

//        CriteriaQuery cq = new CriteriaQuery(WeixinAcctEntity.class, dataGrid);
//        //查询条件组装器
//        // 模糊查询
//       
//        //登入名称
//        if (StringUtils.isNotBlank(weixinAcct.getAcctName())) { 
//        	weixinAcct.setAcctName("*" + weixinAcct.getAcctName() + "*");
//        	
//        }
//        //商户名称
//        if (StringUtils.isNotBlank(weixinAcct.getAcctForName())) {
//        	weixinAcct.setAcctForName("*" + weixinAcct.getAcctForName() + "*");
//        }
//        //所属商户
//        if (StringUtils.isNotBlank(weixinAcct.getBelogAcct())) {
//        	weixinAcct.setBelogAcct("*" + weixinAcct.getBelogAcct() + "*");
//        }
//        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinAcct, request.getParameterMap());
////		根据条件进行查询
////		cq.eq("id", ResourceUtil.getSessionUserName().getTenantId());//根据公众ID进行数据隔离
////        获取当前登录的商户的id
//        /**
//         * s级商户可以看到所有的商户，但是不包含自己本身的信息
//         */
//        String accT_id = ResourceUtil.getSessionUserName().getTenantId();
//        WeixinAcctEntity weixinAcctEntity = weixinAcctService.getEntity(WeixinAcctEntity.class, accT_id);
//        if (!"0".equals(weixinAcctEntity.getAcctLevel())) {
//            cq.eq("pid", ResourceUtil.getSessionUserName().getTenantId());//根据公众ID进行数据隔离
//
//        }
////        cq.notEq("acctLevel", "0");  //不能查询同样的平台以及本身
//        try {
//            //自定义追加查询条件
//        } catch (Exception e) {
//            throw new BusinessException(e.getMessage());
//        }
//        cq.add();
//
//        DataGridReturn dataGridReturn = this.weixinAcctService.getDataGridReturn(cq, true);
//        List rows = dataGridReturn.getRows();
//        for(int i = 0;i<rows.size();i++ ){
//        	WeixinAcctEntity object = (WeixinAcctEntity) rows.get(i);
//        	String id = object.getId();
//            WeixinAccountEntity weixinAccountEntity = weixinAccountService.findUniqueByProperty(WeixinAccountEntity.class, "acctId", id);
//            if(weixinAccountEntity != null){
//            	TSBaseUser baseUser = tSbaseUserService.findUniqueByProperty(TSBaseUser.class, "accountid", weixinAccountEntity.getId());
//                object.setAcctName(baseUser.getUserName());
//            } else {
//            	 object.setAcctName("");
//            }
//            
//        }
//        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * 商户管理表编辑页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goUpdateMyacct")
    public ModelAndView goUpdateMyacct(WeixinAcctEntity weixinAcct, HttpServletRequest req) throws Exception {
//        需要被编辑的商户
        if (StringUtil.isNotEmpty(weixinAcct.getId())) {
            weixinAcct = weixinAcctService.getEntity(WeixinAcctEntity.class, weixinAcct.getId());
            req.setAttribute("weixinAcct", weixinAcct);
            WeixinAccountEntity weixinAccountEntity = weixinAccountService.findUniqueByProperty(WeixinAccountEntity.class, "acctId", weixinAcct.getId());
            LogUtil.info("--------------------------之前的数据初始化有错-----------最早没有在weixin_acccount中添加acctId" + weixinAccountEntity.getId());

            String sql = "select * from WxAdmin where accountId='" + weixinAccountEntity.getId() + "' ";
            Connection connection = null;
            Statement stmt = null;
            ResultSet es = null;
            Gson gson = new Gson();

            try {
//            创建的jdbc连接语句
                connection = ConnectionsManager.getMysqlConn();

                stmt = connection.createStatement();
                List<String> lis = new ArrayList<String>();

                es = stmt.executeQuery(sql);
                while (es.next()) {

                    String wex = es.getString("accountCurId");
                    lis.add(wex);

//
                }
//                将ListString类型的转为一个String类型
                String weixinList = String.join(",", lis.toString());
                weixinList = weixinList.substring(1, weixinList.length() - 1);
                req.setAttribute("weixinList", weixinList);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                es.close();
                stmt.close();
                connection.close();
            }
            this.weixinAccountService.getSession().evict(weixinAcct);
//            if (weixinAcct.getAcctLevel().equals("1")) {
//                weixinAcct.setAcctLevel("A级");
//            }
//            if (weixinAcct.getAcctLevel().equals("2")) {
//                weixinAcct.setAcctLevel("B级");
//            }
//            if (weixinAcct.getAcctLevel().equals("3")) {
//                weixinAcct.setAcctLevel("C级");
//            }
            String hql0 = "from ProvinceEntity where provinceName='" + weixinAcct.getProvince() + "'";
            List<ProvinceEntity> ls = this.systemService.findHql(hql0, null);
            String code = null;
            if (ls.size() > 0) {
                code = ls.get(0).getProvinceCode();
            }

            String hql1 = "from CityEntity where provinceCode='" + code + "'";
            List<CityEntity> lc = this.systemService.findHql(hql1, null);
            req.setAttribute("cityList", lc);
            //关于市区的显示（后期优化）--会被删除
            req.setAttribute("weixinAcctPage", weixinAcct);
        }
//        被编辑商户的覆盖区域
        WeixinMerchantCoverAreaEntity weixinMerchantCoverAreaEntity = new WeixinMerchantCoverAreaEntity();
        if (StringUtil.isNotEmpty(weixinAcct.getId())) {
            weixinMerchantCoverAreaEntity = weixinAcctService.findUniqueByProperty(WeixinMerchantCoverAreaEntity.class, "accountID", weixinAcct.getId());
            String hql0 = "from ProvinceEntity where provincename='" + weixinMerchantCoverAreaEntity.getProvincename() + "'";
            List<ProvinceEntity> ls = this.systemService.findHql(hql0, null);
            String code1 = null;
            if (ls.size() > 0) {
                code1 = ls.get(0).getProvinceCode();
            }
            String hql1 = "from CityEntity where provinceCode='" + code1 + "'";
            List<CityEntity> lc1 = this.systemService.findHql(hql1, null);

            req.setAttribute("cityList1", lc1);
            /**
             * 添加运营商流量类型------xiaona--2016年4月30日
             */
            if(StringUtils.isBlank(weixinMerchantCoverAreaEntity.getBusinessArea())){
                String businessArea="三网通";
                req.setAttribute("businessArea",businessArea);
            }else {
                req.setAttribute("businessArea",weixinMerchantCoverAreaEntity.getBusinessArea());
            }
                //在页面根据取到的值从而进行相关的判断，从而看当前被选中的选项
            req.setAttribute("weixinMerchantCoverAreaPage", weixinMerchantCoverAreaEntity);
        }
//        被编辑商户的上级商户
//        (因为商户等级暂时是不可以进行升级的，故而以固定文本框进行显示)
        WeixinAcctEntity weixinOnAcct = new WeixinAcctEntity();
        if (StringUtil.isNotEmpty(ResourceUtil.getSessionUserName().getTenantId())) {
            weixinOnAcct = weixinAcctService.getEntity(WeixinAcctEntity.class, ResourceUtil.getSessionUserName().getTenantId());

//            （如果直接可以进行升级操作，此段代码可以省略）
            req.setAttribute("weixinOnAcctPage", weixinOnAcct);
        }
        String hql = "from ProvinceEntity";
        List<ProvinceEntity> lis = new ArrayList<ProvinceEntity>();
        lis = this.systemService.findHql(hql, null);
        req.setAttribute("lis", lis);   //加载所有的省
        return new ModelAndView("weixin/tenant/myAcct-updateInfo");
    }

    /**
     * 更新商户信息
     *
     * @return
     */
    @RequestMapping(params = "doUpdateMyacctInfo")
    @ResponseBody
    public AjaxJson doUpdateMyacctInfo(WeixinAcctEntity weixinAcct, HttpServletRequest request) {
        StringBuilder doUpdateMyacctInfo = new StringBuilder();
        long startTime = System.currentTimeMillis();//获取开始当前的时间   查询方法的执行时间
        doUpdateMyacctInfo.append("方法开始时间_" + startTime + "ms" + "_");  //添加的日志

        String status = request.getParameter("status");
        AjaxJson j = new AjaxJson();
        String ids = request.getParameter("accountCurId");

        String pro = request.getParameter("province1");    //获取页面选中的省和市，这是覆盖区域
//        String cityn = request.getParameter("city1");

        String citiesflag = request.getParameter("leftcities");

        String businessArea=request.getParameter("businessArea");
        /**
         * 进行判断：如果覆盖区域左侧的全部被选中到右边，那么就是涵盖的就是整个省，此时不需要进行每个市的存储，只是需要存储null值表示的是存储的整个省
         */
        String selectedCitys="";
        if(citiesflag==null || citiesflag.equals("")){
            selectedCitys =null;
        }else {
            selectedCitys = request.getParameter("selectedCities");
        }

        message = "商户信息更新成功";
        WeixinAcctEntity t = weixinAcctService.get(WeixinAcctEntity.class, weixinAcct.getId());
        String oldAgency = t.getAdAgency();
        String oldSellAdPos = t.getSellAdpos();
        WeixinAcctEntity currAcct = this.systemService.get(WeixinAcctEntity.class, ResourceUtil.getSessionUserName().getTenantId());
        try {
//            if (weixinAcct.getAcctLevel().equals("A级")) {
//                t.setAcctLevel("1");
//            }
//            if (weixinAcct.getAcctLevel().equals("B级")) {
//                t.setAcctLevel("2");
//            }
//            if (weixinAcct.getAcctLevel().equals("C级")) {
//                t.setAcctLevel("3");
//            }
            t.setBusinessType(weixinAcct.getBusinessType());
            t.setProvince(weixinAcct.getProvince());
            t.setBusinessName(weixinAcct.getBusinessName());
            t.setCity(weixinAcct.getCity());
            t.setAcctForName(weixinAcct.getAcctForName());
            t.setEmail(weixinAcct.getEmail());
            t.setDomainurl(weixinAcct.getDomainurl());
            t.setQqNumber(weixinAcct.getQqNumber());
            t.setStatus(status);  //状态，默认情况下是激活
            t.setMobilePhone(weixinAcct.getMobilePhone());
            t.setSellAdpos(weixinAcct.getSellAdpos());
            t.setAdAgency(weixinAcct.getAdAgency());
            if(t.getAcctLevel().equals("3")){
                t.setAdAgency("0");
            }else{
                t.setSellAdpos("0");
            }
            if(!StringUtils.equals(currAcct.getAcctLevel(), "0")){
                t.setAdAgency("0");
            }
            if(!(StringUtils.equals(currAcct.getAdAgency(), "1") || StringUtils.equals(currAcct.getAcctLevel(), "0"))){
                t.setSellAdpos("0");
            }
            String newAgency = t.getAdAgency();
            String newSellAdPos = t.getSellAdpos();
            if(!StringUtils.equals(oldAgency, newAgency) && StringUtils.equals(newAgency, "0")){
                //取消所有下级代理的广告
                this.adService.terminalAd(t.getId(), MessageFormat.format("广告代理商状态由 {0} 变为 {1}", oldAgency,newAgency));
            }
            if(!StringUtils.equals(oldSellAdPos, newSellAdPos)){
                // 撤下广告
                this.adService.terminalAd(t.getId(), MessageFormat.format("出让广告状态由 {0} 变为 {1}", oldSellAdPos,newSellAdPos));
            }
            weixinAcctService.saveOrUpdate(t);

            doUpdateMyacctInfo.append("商户信息更新完成" + t + "_");  //添加的日志
//            获取更新的商户区域表
            WeixinMerchantCoverAreaEntity weixinMerchantCoverAreaEntity = new WeixinMerchantCoverAreaEntity();

            weixinMerchantCoverAreaEntity = weixinMerchantCoverAreaServiceI.findUniqueByProperty(WeixinMerchantCoverAreaEntity.class, "accountID", weixinAcct.getId());
            weixinMerchantCoverAreaEntity.setProvincename(pro);
            weixinMerchantCoverAreaEntity.setCityname(selectedCitys);
            weixinMerchantCoverAreaEntity.setBusinessArea(businessArea);   //编辑商户的流量运营商类型
            weixinMerchantCoverAreaServiceI.saveOrUpdate(weixinMerchantCoverAreaEntity);

            doUpdateMyacctInfo.append("商户覆盖区域表更新完成" + weixinMerchantCoverAreaEntity + "_");  //添加的日志

////            根据编辑的商户查询其相应的accountId
//            String hql = "from WeixinAccountEntity where 1=1 and acctId='" + weixinAcct.getId() + "'";
//            List<WeixinAccountEntity> lisa = weixinAccountService.findHql(hql, null);
//            WeixinAccountEntity entitys = lisa.get(0);
//            String accountId = entitys.getId();
//            for (String id : ids.split(",")) {
//                String hql0 = "from WeixinAdminEntity where accountId='" + accountId + "'";
//                List<WeixinAdminEntity> lisadmin = weixinAdminService.findHql(hql0, null);
//
//                if (lisadmin.size() > 0) {
//                    for (WeixinAdminEntity en : lisadmin) {
//                        if (!en.getAccountCurId().equals(id)) {
//                            WeixinAdminEntity newRn = new WeixinAdminEntity();
//                            newRn.setAccountCurId(id);
//                            newRn.setAccountId(accountId);
//                            newRn.setStatus("激活");
//                            weixinAdminService.save()
//                        }
//                    }
//
//                } else {
//                    WeixinAdminEntity newRn = new WeixinAdminEntity();
//                    newRn.setAccountCurId(id);
//                    newRn.setAccountId(accountId);
//                    newRn.setStatus("激活");
//                }
//
//            }

        } catch (Exception e){
            e.printStackTrace();
            message = "商户信息更新失败";
            throw new BusinessException(e.getMessage());
        }

        long endTime = System.currentTimeMillis();//获取结束的当前时间   检测程序的运行时间
        doUpdateMyacctInfo.append("方法结束时间_" + endTime + "ms" + "_");  //添加的日志

        long totaltime = endTime - startTime;  //总耗时
        doUpdateMyacctInfo.append("方法执行总的时间" + totaltime + "ms");  //添加的日志

        LOGGER.info("weixinAcctController的__doUpdateMyacctInfo__方法执行过程中的各个操作的输入输出参数以及结果_" + doUpdateMyacctInfo.toString());
        j.setMsg(message);
        return j;
    }

    /**
     * 商户下属密码重置
     *
     * @return
     */
    @RequestMapping(params = "Acctchangepassword")
    public ModelAndView Acctchangepassword(WeixinAcctEntity weixinAcct, HttpServletRequest request) {
        if (StringUtil.isNotEmpty(weixinAcct.getId())) {
            try {
                String id = weixinAcct.getId();
                WeixinAcctEntity weixinAcctEntity = weixinAcctService.getEntity(WeixinAcctEntity.class, id);
                String name = weixinAcctEntity.getAcctName();
                WeixinAccountEntity weixinAccountEntity = weixinAccountService.findUniqueByProperty(WeixinAccountEntity.class, "acctId", id);
                String id2 = weixinAccountEntity.getId();
                String hql0 = "from TSBaseUser where 1 = 1 AND accountid = ? ";
                List<TSBaseUser> lisUser = systemService.findHql(hql0, id2);
                TSBaseUser tsBaseUser = lisUser.get(0);
                request.setAttribute("user", tsBaseUser);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return new ModelAndView("weixin/tenant/myAcct_ChangePWD");
    }

    /**
     * 判断修改商户密码之前原密码是否正确
     *
     * @param request
     * @return
     */
    @RequestMapping(params = "checkPWD")
    @ResponseBody
    public AjaxJson checkPWD(HttpServletRequest request) {

        AjaxJson j = new AjaxJson();


        //获取页面输入项
//        String password = request.getParameter("password");

        String accountId = request.getParameter("id");
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");

        String password = oConvertUtils.getString(request.getParameter("password"));   //取到页面的密码进行加密
        String pString = PasswordUtil.encrypt(username, password, PasswordUtil.getStaticSalt());
        if (!pString.equals(pwd)) {
            j.setMsg("原密码输入错误，请重新输入");
            j.setSuccess(false);
        } else {
            j.setMsg("密码正确");
        }
        return j;
    }

    /**
     * 修改商户管理下的所有商户的登录密码密码
     *
     * @return
     */
    @RequestMapping(params = "savenewpwd")
    @ResponseBody
    public AjaxJson savenewpwd(HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        String tsId = request.getParameter("id");
        String hql0 = "from TSBaseUser where 1 = 1 AND accountid = ? ";
        List<TSBaseUser> lisUser = systemService.findHql(hql0, tsId);
        TSBaseUser tsBaseUser = lisUser.get(0);
        String newpassword = oConvertUtils.getString(request.getParameter("newpassword"));
        try {
            tsBaseUser.setPassword(PasswordUtil.encrypt(tsBaseUser.getUserName(), newpassword, PasswordUtil.getStaticSalt()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        systemService.updateEntitie(tsBaseUser);
        j.setMsg("修改成功");

        return j;
    }

    /**
     * 商户信息
     *
     * @return
     */
    @RequestMapping(params = "myacctView")
    public ModelAndView myacctView(WeixinAcctEntity weixinAcct, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(weixinAcct.getId())) {
            weixinAcct = weixinAcctService.getEntity(WeixinAcctEntity.class, weixinAcct.getId());
            req.setAttribute("weixinAcctPage", weixinAcct);
        }
        return new ModelAndView("weixin/tenant/myAcct-view");
    }

    /**
     * 更新商户信息
     *
     * @return
     */
    @RequestMapping(params = "doUpdateMyacct")
    @ResponseBody
    public AjaxJson doUpdateMyacct(WeixinAcctEntity weixinAcct, HttpServletRequest request) {

        AjaxJson j = new AjaxJson();
        message = "商户信息更新成功";

        WeixinAcctEntity t = weixinAcctService.get(WeixinAcctEntity.class, weixinAcct.getId());
        try {

            t.setAcctName(weixinAcct.getAcctName());
            t.setEmail(weixinAcct.getEmail());
            t.setDomainurl(weixinAcct.getDomainurl());
            t.setQqNumber(weixinAcct.getQqNumber());
            t.setMobilePhone(weixinAcct.getMobilePhone());

            weixinAcctService.saveOrUpdate(t);
        } catch (Exception e) {
            e.printStackTrace();
            message = "商户信息更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 商户管理表列表 页面跳转
     *
     * @return
     */
    @RequestMapping(params = "weixinAcct")
    public ModelAndView weixinAcct(HttpServletRequest request) {
        return new ModelAndView("weixin/tenant/weixinAcctList");
    }

    /**
     * easyui AJAX请求数据
     *
     * @param request
     * @param response
     * @param dataGrid
     */
    @RequestMapping(params = "datagrid")
    public void datagrid(WeixinAcctEntity weixinAcct, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WeixinAcctEntity.class, dataGrid);
        //查询条件组装器
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinAcct, request.getParameterMap());
        try {
            //自定义追加查询条件
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        cq.add();
        this.weixinAcctService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * 删除商户管理表
     *
     * @return
     */
    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(WeixinAcctEntity weixinAcct, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        weixinAcct = systemService.getEntity(WeixinAcctEntity.class, weixinAcct.getId());
        message = "商户管理表删除成功";
        try {
            weixinAcctService.delete(weixinAcct);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "商户管理表删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 批量删除商户管理表
     *
     * @return
     */
    @RequestMapping(params = "doBatchDel")
    @ResponseBody
    public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        message = "商户管理表删除成功";
        try {
            for (String id : ids.split(",")) {
                WeixinAcctEntity weixinAcct = systemService.getEntity(WeixinAcctEntity.class,
                        id
                );
                weixinAcctService.delete(weixinAcct);
                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "商户管理表删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 检测新增的用户名是否已经存在
     *
     * @param request
     * @return
     */
    @RequestMapping(params = "checkRegisterName")
    @ResponseBody
    public AjaxJson checkRegisterName(HttpServletRequest request) {

        AjaxJson j = new AjaxJson();

        //获取页面输入项
        String loginName = request.getParameter("loginName");

        if (loginName == null && "".equals(loginName)) {
            j.setMsg("用户名为空，请输入用户名！");
            j.setSuccess(false);
        }

        TSUser users = systemService.findUniqueByProperty(TSUser.class, "userName", loginName);
        if (users != null) {

            j.setMsg("此用户已经存在，请重新输入！");
            j.setSuccess(false);
        }
        return j;
    }


    /**
     * 添加商户管理表和base表以及user表还有就是微信公众账号表以及商户覆盖区域表还有就是商户创建流量账户表等
     *
     * @return
     */
    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(WeixinAcctEntity weixinAcct, HttpServletRequest request, HttpServletResponse response) throws Exception {
        StringBuilder doAdd = new StringBuilder();
        long startTime = System.currentTimeMillis();//获取开始当前的时间   查询方法的执行时间
        doAdd.append("方法开始时间_" + startTime + "ms" + "_");  //添加的日志

        AjaxJson j = new AjaxJson();

        //获取表单上的不在WeixinAcctEntity表中的值,去除空格
        String loginame1 = request.getParameter("loginName");
//        去除空格
        String loginame = loginame1.replaceAll(" ", "");
//        LOGGER.info(loginame);
        String accName1 = request.getParameter("acctForName");
        String accName = accName1.replaceAll(" ", "");
        String password = request.getParameter("passwordName");
        String accountNumber = request.getParameter("accountNumber");
//        String accname=request.getParameter("");
        String ids = request.getParameter("accountCurId");
        if(StringUtils.isBlank(ids)){
            ids="";
        }
        /**
         * 商户流量运营商的覆盖区域------2016年4月30日
         */
        String businessArea =request.getParameter("businessArea");

        String status = request.getParameter("status");

        String pro = request.getParameter("province1");    //获取页面选中的省和市，这是覆盖区域
        String citiesflag = request.getParameter("leftcities");
        /**
         * 进行判断：如果覆盖区域左侧的全部被选中到右边，那么就是涵盖的就是整个省，此时不需要进行每个市的存储，只是需要存储null值表示的是存储的整个省
         */
        String dd=request.getParameter("selectedCities");
        String selectedCitys="";
        if(citiesflag==null || citiesflag.equals("")){
            selectedCitys =null;
        }else {
            selectedCitys = request.getParameter("selectedCities");
        }


        String level = weixinAcct.getAcctLevel();
//        PrintWriter out = response.getWriter();

        if (level == null || level.equals("") || "4".equals(level)) {
            weixinAcct.setAcctLevel("3");     //如果商户等级不进行选择，那么此时的商户等级为C级
        }

        TSUser userCheck = systemService.findUniqueByProperty(TSUser.class, "userName", loginame);
//        获取数据库的最新记录
        String count = "0";

        String sql = "select * from weixin_acct where create_date in(select max(create_date) from weixin_acct)";

        doAdd.append("查询数据库中至今为止最大的商户编码sql语句" + sql + "_");  //添加的日志
        Connection connection = null;
        Statement stmt = null;
        ResultSet es = null;
        try {
//            创建的jdbc连接语句
            connection = ConnectionsManager.getMysqlConn();

            stmt = connection.createStatement();
            es = stmt.executeQuery(sql);
            while (es.next()) {
                if (es.getString("acct_code") != null && !"".equals(es.getString("acct_code"))) {
                    count = es.getString("acct_code");
                } else {
                    count = "1000";
                }

//                request.setAttribute("flowValue", valueSub);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            es.close();
            stmt.close();
            connection.close();
        }
        doAdd.append("最大的商户编码" + count + "_");  //添加的日志
        Integer count1 = Integer.parseInt(count);

        count1 += 1;

        String count2 = count1.toString();
        //从缓存中获取当前的用户的id
        weixinAcct.setPid(ResourceUtil.getSessionUserName().getTenantId());
        WeixinAcctEntity currAcct = this.systemService.get(WeixinAcctEntity.class, ResourceUtil.getSessionUserName().getTenantId());
        message = "商户管理表添加成功";
        try {
            if (userCheck == null) {

                weixinAcct.setAcctName(loginame); //当前登录的商户的登陆名也就是操作者
                weixinAcct.setAcctForName(accName);
                weixinAcct.setAcctCode(count2);
                weixinAcct.setRequestnum(1000);
                weixinAcct.setSmsnum(5);
                weixinAcct.setUsernum(5);
                weixinAcct.setCreateDate(new Date());
                weixinAcct.setAccountnum(3);
                weixinAcct.setNewsnum(5);
                weixinAcct.setStatus(status);  //状态，默认情况下是激活

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.add(Calendar.MONTH, 3);
                weixinAcct.setEndDate(calendar.getTime());
                if(weixinAcct.getAcctLevel().equals("3")){
                    weixinAcct.setAdAgency("0");
                }else{
                    weixinAcct.setSellAdpos("0");
                }
                if(!StringUtils.equals(currAcct.getAcctLevel(), "0")){
                    weixinAcct.setAdAgency("0");
                }
                if(!(StringUtils.equals(currAcct.getAdAgency(), "1") || StringUtils.equals(currAcct.getAcctLevel(), "0"))){
                    weixinAcct.setSellAdpos("0");
                }

                weixinAcctService.save(weixinAcct);

                doAdd.append("商户管理表添加成，添加的商户的名称是" + weixinAcct.getAcctForName() + "_");  //添加的日志
                LogUtil.info("---------weixinAcctService----商户管理表记录创建成功---" + weixinAcct.getId());
                //获取租户信息
                WeixinAcctEntity tenantEntity = weixinAcctService.findUniqueByProperty(WeixinAcctEntity.class, "acctName", loginame);
                TSUser tsUser = new TSUser();
                tsUser.setUserName(loginame);
                tsUser.setMobilePhone(tenantEntity.getMobilePhone());
                tsUser.setEmail(tenantEntity.getEmail());
                tsUser.setPassword(PasswordUtil.encrypt(tsUser.getUserName(), password, PasswordUtil.getStaticSalt()));
                tsUser.setStatus(Globals.User_Normal);
                tsUser.setTSDepart(null);
//                设置租户id
                tsUser.setTenantId(tenantEntity.getId());

//                tsUser.setTenantId(ResourceUtil.getSessionUserName().getTenantId());
                tsUser.setType("1");//用户类型 1：商家管理员账号
//                tsUser.setAccountid(ResourceUtil.getSessionUserName().getTenantId());
                //        systemService.save(tsUser);

                //这是查询已经有的角色，从而进行授权和赋值
                String acctLevel = weixinAcct.getAcctLevel();
                if ("1".equals(acctLevel)) {
                    TSRole tSRole = systemService.findUniqueByProperty(TSRole.class, "roleCode", "001");
                    tsUser.setTSRole(tSRole);
                    doAdd.append("当前商户的角色以及授权" + tSRole.getRoleName() + "_");  //添加的日志
                } else if ("2".equals(acctLevel)) {
                    TSRole tSRole = systemService.findUniqueByProperty(TSRole.class, "roleCode", "002");
                    tsUser.setTSRole(tSRole);
                    doAdd.append("当前商户的角色以及授权" + tSRole.getRoleName() + "_");  //添加的日志
                } else if ("0".equals(acctLevel)) {
                    TSRole tSRole = systemService.findUniqueByProperty(TSRole.class, "roleCode", "000");
                    tsUser.setTSRole(tSRole);
                    doAdd.append("当前商户的角色以及授权" + tSRole.getRoleName() + "_");  //添加的日志
                } else if("4".equals(level)){
                    TSRole tSRole = systemService.findUniqueByProperty(TSRole.class, "roleCode", "wshsh");
                    tsUser.setTSRole(tSRole);
                    doAdd.append("当前商户的角色以及授权" + tSRole.getRoleName() + "_");  //添加的日志
                }else{
                	 TSRole tSRole = systemService.findUniqueByProperty(TSRole.class, "roleCode", "003");
                     tsUser.setTSRole(tSRole);
                     doAdd.append("当前商户的角色以及授权" + tSRole.getRoleName() + "_");  //添加的日志
                }


                LogUtil.info("---------systemService----给角色授权---" + tsUser.getTSRole().getId());
                //将商户的流量账户信息进行初始化
//                weixinAcctFlowAccountEntity weixinAcctFlowAccountEntity1 = new weixinAcctFlowAccountEntity();
//
//                weixinAcctFlowAccountEntity1.setAcct_id(ResourceUtil.getSessionUserName().getTenantId());
//                weixinAcctFlowAccountEntity1.setAccountName(tenantEntity.getAcctForName());
//                weixinAcctFlowAccountEntity1.setTenantId(tenantEntity.getId());

//                weixinAcctFlowAccountEntity1.setAccountId("4028b881516b211101516b22db350004");
//                weixinAcctFlowAccoutServiceI.save(weixinAcctFlowAccountEntity1);
                //将商户的覆盖区域添加到覆盖区域表中
                WeixinMerchantCoverAreaEntity weixinMerchantCoverAreaEntity = new WeixinMerchantCoverAreaEntity();

                weixinMerchantCoverAreaEntity.setAccountID(tenantEntity.getId());
                weixinMerchantCoverAreaEntity.setProvincename(pro);
                weixinMerchantCoverAreaEntity.setCityname(selectedCitys);
                weixinMerchantCoverAreaEntity.setBusinessArea(businessArea);   //商户运营商的流量的覆盖区域
                weixinMerchantCoverAreaServiceI.save(weixinMerchantCoverAreaEntity);
                doAdd.append("当前商户的覆盖区域" + weixinMerchantCoverAreaEntity + "_");  //添加的日志
                LogUtil.info("---------weixinMerchantCoverAreaServiceI----商户覆盖区域表创建成功---" + weixinMerchantCoverAreaEntity.getAccountID() + "商户管理表中的id");

                //校验用户名
                //再添加之前先判断是不是已经存在此用户
                TSUser users = systemService.findUniqueByProperty(TSUser.class, "userName", tsUser.getUserName());
                if (users == null) {
                    WeixinAccountEntity weixinAccountEntity = new WeixinAccountEntity();
                    weixinAccountEntity.setAccountname(accountNumber);
                    weixinAccountEntity.setAccountnumber(accountNumber);
                    weixinAccountEntity.setAccounttoken("w_" + RegisterController.getRandomString(8));
                    weixinAccountEntity.setAuthorizationType("0");
                    weixinAccountEntity.setUserName(loginame);
                    weixinAccountEntity.setAcctId(tenantEntity.getId());
                    weixinAccountService.save(weixinAccountEntity);

                    //增加
                    if("4".equals(level)){
                    WeixinAcountOuterEntity acountOuterEntity = new WeixinAcountOuterEntity();
                    acountOuterEntity.setAccountId(weixinAccountEntity.getId());
                    acountOuterEntity.setWshAppid(request.getParameter("outAppId"));
                    acountOuterEntity.setWshAppKey(request.getParameter("outAppKey"));
                    systemService.save(acountOuterEntity);
                    }
                    doAdd.append("当前商户微信公众号信息初始化" + weixinAccountEntity.getId() + "_");  //添加的日志
                    LogUtil.info("---------weixinAccountService----微信公众账号表---" + weixinAccountEntity.getId());
                    WeixinAccountEntity weixinAccount = weixinAccountService.findUniqueByProperty(WeixinAccountEntity.class, "userName", loginame);
//                    商户账户的添加,，在新建账户的时候将商户的流量值初始化为0
                    weixinAcctFlowAccountEntity weixinAcctFlowAccountEntity1 = new weixinAcctFlowAccountEntity();
                    weixinAcctFlowAccountEntity1.setAcct_id(ResourceUtil.getSessionUserName().getTenantId());
                    weixinAcctFlowAccountEntity1.setCountryFlowValue(0.0);
                    weixinAcctFlowAccountEntity1.setProvinceFlowValue(0.0);
                    weixinAcctFlowAccountEntity1.setCountryFlowCardValue(0.0);
                    weixinAcctFlowAccountEntity1.setProvinceFlowCardValue(0.0);
                    weixinAcctFlowAccountEntity1.setAccountName(tenantEntity.getAcctForName());
                    weixinAcctFlowAccountEntity1.setTenantId(tenantEntity.getId());
                    weixinAcctFlowAccountEntity1.setAccountId(weixinAccount.getId());
                    weixinAcctFlowAccoutServiceI.save(weixinAcctFlowAccountEntity1);
                    doAdd.append("当前商户流量账户的初始化" + weixinAcctFlowAccountEntity1.getAccountId() + "_");  //添加的日志
//                    weixinAcctFlowAccountEntity weixinAcctFlowAccountEntity1 = new weixinAcctFlowAccountEntity();
                    LogUtil.info("---------weixinAcctFlowAccoutServiceI----微信公众账号表---" + weixinAccountEntity.getId());
//添加多个管理员账号的时候以逗号进行间隔
                    for (String id : ids.split(",")) {
                        WeixinAdminEntity weixinAdminentity = new WeixinAdminEntity();

                        weixinAdminentity.setAccountCurId(id);
                        weixinAdminentity.setAccountId(weixinAccount.getId());
                        weixinAdminentity.setStatus("激活");
                        weixinAdminService.save(weixinAdminentity);
                        doAdd.append("当前商户微信管理员账号的添加" + weixinAdminentity.getAccountId() + "_");  //添加的日志
                    }
                    LogUtil.info("---------weixinAdminService----微信管理员账号设置---");
                    tsUser.setAccountid(weixinAccount.getId());
                    String vrif = weixinAccount.getId();
                    /**
                     * 去除欢迎关注语的提示------2016年5月6日--xiaona   现在欢迎关注语的创建不需要默认设置，可以自己手动添加
                     */
                    this.createBaseSourceForAccount(weixinAccount.getId());   //创建关注的时的欢迎关注语,只有文本不会默认设置
                    systemService.save(tsUser);
                    doAdd.append("当前用户注册成功，注册的名称是" + tsUser.getUserName() + "_");  //添加的日志
                    LogUtil.info("---------商户注册完成-------" + tsUser.getId());

                }
            } else {
                String msg = "用户名已经存在！";
            }
//            weixinAcctService.save(weixinAcct);
//            systemService.save(tsUser);
            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "商户管理表添加失败";
            throw new BusinessException(e.getMessage());
        }

        long endTime = System.currentTimeMillis();//获取结束的当前时间   检测程序的运行时间
        doAdd.append("方法结束时间_" + endTime + "ms" + "_");  //添加的日志

        long totaltime = endTime - startTime;  //总耗时
        doAdd.append("方法执行总的时间" + totaltime + "ms");  //添加的日志

        LOGGER.info("weixinAcctController的__doAdd__方法执行过程中的各个操作的输入输出参数以及结果_" + doAdd.toString());
        j.setMsg(message);
        return j;
    }


    /**
     * 创建基础数据
     *
     * @param accountId
     */
    public void createBaseSourceForAccount(String accountId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //文本消息
        TextTemplate textTemplate = new TextTemplate();
        textTemplate.setAccountId(accountId);
        textTemplate.setTemplateName("欢迎关注语");
        textTemplate.setContent("欢迎您的关注！");
        textTemplate.setAddTime(sdf.format(new Date()));
        systemService.save(textTemplate);
//        textTemplate = systemService.findUniqueByProperty(TextTemplate.class, "accountId", accountId);
//
//        //欢迎关注语
//        Subscribe subscribe = new Subscribe();
//        subscribe.setAccountid(accountId);
//        subscribe.setTemplateId(textTemplate.getId());
//        subscribe.setTemplateName("欢迎关注语");
//        subscribe.setMsgType("text");
//        subscribe.setAddTime(sdf.format(new Date()));
//        systemService.save(subscribe);

//        //微网站数据
//        WeixinCmsSiteEntity weixinCmsSiteEntity = new WeixinCmsSiteEntity();
//        weixinCmsSiteEntity.setAccountid(accountId);
//        weixinCmsSiteEntity.setSiteName("微网站");
//        weixinCmsSiteEntity.setLinkUrl(bundler.getString("domain") + "/cmsController.do?goPage&page=index&accountid=" + accountId);
//        systemService.save(weixinCmsSiteEntity);
//
//        //微商城数据
//        WeixinShopEntity weixinShopEntity = new WeixinShopEntity();
//        weixinShopEntity.setAccountid(accountId);
//        weixinShopEntity.setShopName("微商城");
//        weixinShopEntity.setLinkUrl(bundler.getString("domain") + "/shopController.do?shopindex&accountid=" + accountId);
//        systemService.save(weixinShopEntity);
//
//        //自定义菜单
//        List<MenuEntity> menuList = new ArrayList<MenuEntity>();
//
//        //自定义菜单:微网站
//        MenuEntity menuCms = new MenuEntity();
//        menuCms.setName("官网");
//        menuCms.setAccountId(accountId);
//        menuCms.setMenuKey("1");
//        menuCms.setType("view");
//        menuCms.setOrders("1");
//        menuCms.setMsgType("");
//        menuCms.setUrl(bundler.getString("domain") + "/cmsController.do?goPage&page=index&accountid=" + accountId);
//        menuList.add(menuCms);
//
//        //自定义菜单:微商城
//        MenuEntity menuShop = new MenuEntity();
//        menuShop.setName("商城");
//        menuShop.setAccountId(accountId);
//        menuShop.setMenuKey("2");
//        menuShop.setType("view");
//        menuShop.setOrders("2");
//        menuShop.setMsgType("");
//        menuShop.setUrl(bundler.getString("domain") + "/shopController.do?shopindex&accountid=" + accountId);
//        menuList.add(menuCms);
//
//        systemService.batchSave(menuList);
//
//        //支付
//        WeixinPaymentConEntity weixinPaymentConEntity = new WeixinPaymentConEntity();
//        weixinPaymentConEntity.setPayType("1");
//        weixinPaymentConEntity.setAccountid(accountId);
//        weixinPaymentConEntity.setPayName("微信支付");
//
//        systemService.save(weixinPaymentConEntity);
    }

    /**
     * 更新商户管理表
     *
     * @return
     */
    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(WeixinAcctEntity weixinAcct, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        message = "商户管理表更新成功";
        WeixinAcctEntity t = weixinAcctService.get(WeixinAcctEntity.class, weixinAcct.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(weixinAcct, t);
            weixinAcctService.saveOrUpdate(t);
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "商户管理表更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }


    /**
     * 商户管理表新增页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(WeixinAcctEntity weixinAcct, HttpServletRequest req) {
        StringBuilder goAdd = new StringBuilder();
        long startTime = System.currentTimeMillis();//获取开始当前的时间   查询方法的执行时间
        goAdd.append("方法开始时间_" + startTime + "ms" + "_");  //添加的日志

        //修改此处的显示-----晓娜，从缓存中获取当前登录的用户的租户id，从而根据租户id查询当前租户的信息，从而可以在页面使用el表达式进行显示
        if (StringUtil.isNotEmpty(ResourceUtil.getSessionUserName().getTenantId())) {
            weixinAcct = weixinAcctService.getEntity(WeixinAcctEntity.class, ResourceUtil.getSessionUserName().getTenantId());
            req.setAttribute("weixinAcctPage", weixinAcct);
        } 	
        String hql = "from ProvinceEntity";
        List<ProvinceEntity> lis = new ArrayList<ProvinceEntity>();
        lis = this.systemService.findHql(hql, null);
        goAdd.append("加载的省的个数" + lis.size() + "_");  //添加的日志
        long endTime = System.currentTimeMillis();//获取结束的当前时间   检测程序的运行时间
        goAdd.append("方法结束时间_" + endTime + "ms" + "_");  //添加的日志

        long totaltime = endTime - startTime;  //总耗时
        goAdd.append("方法执行总的时间" + totaltime + "ms");  //添加的日志

        LOGGER.info("weixinAcctController的__    __方法执行过程中的各个操作的输入输出参数以及结果_" + goAdd.toString());
        req.setAttribute("lis", lis);   //加载所有的省

        return new ModelAndView("weixin/tenant/weixinAcct-add");
    }


    @RequestMapping(params = "addCity1")
    public void cityByProvinceId1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        StringBuilder cityByProvinceId1 = new StringBuilder();
        long startTime = System.currentTimeMillis();//获取开始当前的时间   查询方法的执行时间
        cityByProvinceId1.append("方法开始时间_" + startTime + "ms" + "_");  //添加的日志


        Gson gson = new Gson();
        //        调用后台的查询所有商户的信息的接口方法
//输出到页面
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String provinceName = request.getParameter("provinceName");  //获取的是省的名字

        String hql0 = "from ProvinceEntity where provinceName='" + provinceName + "'";
        cityByProvinceId1.append("当前获取的省_" + provinceName + "_");  //添加的日志
        cityByProvinceId1.append("执行的hql语句" + hql0 + "_");  //添加的日志
        List<ProvinceEntity> lis = new ArrayList<ProvinceEntity>();
        lis = this.systemService.findHql(hql0, null);
        String code = null;
        if (lis.size() > 0) {
            code = lis.get(0).getProvinceCode();
        }
        cityByProvinceId1.append("获取的省code" + code + "_");  //添加的日志
//        根据省名字查询省code

        String hql = "from CityEntity where provinceCode='" + code + "'";
        cityByProvinceId1.append("根据省code查询市执行的hql语句" + hql + "_");  //添加的日志
        List<CityEntity> lisCity = new ArrayList<CityEntity>();
        lisCity = this.systemService.findHql(hql, null);
        cityByProvinceId1.append("查询的市的个数" + lisCity.size() + "所属省" + provinceName + "_");  //添加的日志
        request.setAttribute("lis", lisCity);   //根据省加载市
        String json = gson.toJson(lisCity);
        out.write(json);
        long endTime = System.currentTimeMillis();//获取结束的当前时间   检测程序的运行时间
        cityByProvinceId1.append("方法结束时间_" + endTime + "ms" + "_");  //添加的日志

        long totaltime = endTime - startTime;  //总耗时
        cityByProvinceId1.append("方法执行总的时间" + totaltime + "ms");  //添加的日志

        LOGGER.info("weixinAcctController的__    __方法执行过程中的各个操作的输入输出参数以及结果_" + cityByProvinceId1.toString());
    }

    @RequestMapping(params = "queryAreaCity")
    public void queryAreaCity(HttpServletRequest request, HttpServletResponse response) throws IOException {

        StringBuilder queryAreaCity = new StringBuilder();
        long startTime = System.currentTimeMillis();//获取开始当前的时间   查询方法的执行时间
        queryAreaCity.append("方法开始时间_" + startTime + "ms" + "_");  //添加的日志


        Gson gson = new Gson();
        //        调用后台的查询所有商户的信息的接口方法
//输出到页面
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String provinceName = request.getParameter("provinceName");  //获取的是省的名字
        String accid = request.getParameter("accountId");  //获取的是省的名字

        String hql0 = "from WeixinMerchantCoverAreaEntity where provincename='" + provinceName + "' and accountID='" + accid + "'";
        queryAreaCity.append("当前获取的省_" + provinceName + "_");  //添加的日志
        queryAreaCity.append("执行的hql语句" + hql0 + "_");  //添加的日志
        List<WeixinMerchantCoverAreaEntity> lis = new ArrayList<WeixinMerchantCoverAreaEntity>();
        lis = this.systemService.findHql(hql0, null);
        String city = "";
        if (lis.size() > 0) {
            city = lis.get(0).getCityname();
            if(city==null){
                city="";
            }
        }
        List<CityEntity> lisCity = new ArrayList<CityEntity>();
        if (!city.equals("")) {
            for (String citys : city.split(",")) {
                CityEntity singlecity = new CityEntity();
                singlecity.setCityName(citys);
                UUID uuid = UUID.randomUUID();
                singlecity.setId(uuid.toString());
                lisCity.add(singlecity);
            }
        }else {
            lisCity=null;
        }

        request.setAttribute("lis", lisCity);   //根据省加载市
        String json = gson.toJson(lisCity);
        out.write(json);
        long endTime = System.currentTimeMillis();//获取结束的当前时间   检测程序的运行时间
        queryAreaCity.append("方法结束时间_" + endTime + "ms" + "_");  //添加的日志

        long totaltime = endTime - startTime;  //总耗时
        queryAreaCity.append("方法执行总的时间" + totaltime + "ms");  //添加的日志

        LOGGER.info("weixinAcctController的__    __方法执行过程中的各个操作的输入输出参数以及结果_" + queryAreaCity.toString());
    }

    /**
     * 商户管理表编辑页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(WeixinAcctEntity weixinAcct, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(weixinAcct.getId())) {
            weixinAcct = weixinAcctService.getEntity(WeixinAcctEntity.class, weixinAcct.getId());
            req.setAttribute("weixinAcctPage", weixinAcct);
        }
        return new ModelAndView("weixin/tenant/weixinAcct-update");
    }

    /**
     * 导入功能跳转
     *
     * @return
     */
    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        return new ModelAndView("weixin/tenant/weixinAcctUpload");
    }

    /**
     * 导出excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(params = "exportXls")
    public void exportXls(WeixinAcctEntity weixinAcct, HttpServletRequest request, HttpServletResponse response
            , DataGrid dataGrid) {
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try {
            codedFileName = "商户管理表";
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
            CriteriaQuery cq = new CriteriaQuery(WeixinAcctEntity.class, dataGrid);
            org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinAcct, request.getParameterMap());

            List<WeixinAcctEntity> weixinAccts = this.weixinAcctService.getListByCriteriaQuery(cq, false);
            workbook = ExcelExportUtil.exportExcel(new ExcelTitle("商户管理表列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(),
                    "导出信息"), WeixinAcctEntity.class, weixinAccts);
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
    public void exportXlsByT(WeixinAcctEntity weixinAcct, HttpServletRequest request, HttpServletResponse response
            , DataGrid dataGrid) {
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try {
            codedFileName = "商户管理表";
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
            workbook = ExcelExportUtil.exportExcel(new ExcelTitle("商户管理表列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(),
                    "导出信息"), WeixinAcctEntity.class, null);
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
                List<WeixinAcctEntity> listWeixinAcctEntitys =
                        (List<WeixinAcctEntity>) ExcelImportUtil.importExcelByIs(file.getInputStream(), WeixinAcctEntity.class, params);
                for (WeixinAcctEntity weixinAcct : listWeixinAcctEntitys) {
                    weixinAcctService.save(weixinAcct);
                }
                j.setMsg("文件导入成功！");
            } catch (Exception e) {
                j.setMsg("文件导入失败！");
                LOGGER.error(ExceptionUtil.getExceptionMessage(e));
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
     * 生成第三方接口的apiId和apiKey页面跳转--xudan-2015-12-23 15:03:17
     *
     * @return
     */
    @RequestMapping(params = "goProduceIdKey")
    public ModelAndView goProduceIdKey(WeixinAcctEntity weixinAcct, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(weixinAcct.getId())) {
            ThirdOpenServiceEntity openServiceEntity = new ThirdOpenServiceEntity();
            String hql = "from ThirdOpenService where accountid='" + weixinAcct.getId() + "'";
            List<ThirdOpenServiceEntity> openServiceList = this.systemService.findHql(hql, null);
            if (openServiceList.size() > 0) {
                openServiceEntity = openServiceList.get(0);
            }
            req.setAttribute("openService", openServiceEntity);
        }
        /*String accountid="297eae2351a5649e0151a56a7ec80004";
        ThirdOpenServiceEntity openServiceEntity = new ThirdOpenServiceEntity();
        String hql = "from ThirdOpenServiceEntity where accountId='" + accountid + "'";
        List<ThirdOpenServiceEntity> openServiceList = this.systemService.findHql(hql, null);
        if (openServiceList.size() > 0) {
            openServiceEntity = openServiceList.get(0);
        }
        req.setAttribute("openService", openServiceEntity);
        */
        return new ModelAndView("weixin/tenant/produceApiIdKey");
    }

    /**
     * 保存第三方接口的apiId和apiKey到数据库--xudan-2015-12-23 15:03:17
     *
     * @return
     */
    @RequestMapping(params = "doProduceIdKey")
    @ResponseBody
    public AjaxJson doProduceIdKey(WeixinAcctEntity weixinAcct, HttpServletRequest request) throws Exception {
        AjaxJson j = new AjaxJson();
        String accountId = request.getParameter("accountid");
        String apiId = request.getParameter("apiIdhidden");
        String apiKey = request.getParameter("apiKeyhidden");

        try {
            String hql = "from ThirdOpenServiceEntity where apiId='" + apiId + "'";
            List<ThirdOpenServiceEntity> openServiceList = this.systemService.findHql(hql, null);

            if (openServiceList.size() <= 0) {
                //添加数据
                ThirdOpenServiceEntity openServiceEntity = new ThirdOpenServiceEntity();
                openServiceEntity.setAccountId(UUID.randomUUID().toString());
                openServiceEntity.setAccountId(accountId);
                openServiceEntity.setApiId(apiId);
                openServiceEntity.setApiKey(apiKey);
                thirdOpenServiceI.save(openServiceEntity);

                j.setMsg("apiId和apiKey生成成功！");
            } else {
                j.setMsg("apiId和apiKey不可修改！");
            }

        } catch (Exception e) {
            j.setMsg("apiId和apiKey生成失败！");
            LOGGER.error(ExceptionUtil.getExceptionMessage(e));
        }

        return j;
    }


    /**
     * 点击添加亲情号，返回到该Controller，判断该用户的亲情号设置是否已经超过了4个，
     * 如果超过了4个，则不允许再添加亲情号
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(params = "addAreaCity")
    public void addAreaCity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long startTime = System.currentTimeMillis();

        response.setContentType("application/json;charset=UTF-8");

        StringBuilder builder = new StringBuilder();
        String provincename = request.getParameter("provincename");

        Map<String, Object> map = new HashMap<String, Object>();
        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter out = response.getWriter();
//        根据省名称获取到省code
        ProvinceEntity provinceEntity = this.systemService.findUniqueByProperty(ProvinceEntity.class, "provinceName", provincename);
        if (provincename == null || provincename.equals("")) {
            map.put("status", 2);
            map.put("msg", "您还没有选择省份，请先选择省份后在进行市区的添加");
            String json = objectMapper.writeValueAsString(map);
            out.write(json);
            return;
        }
        List<CityEntity> lisn = new ArrayList<CityEntity>();
        lisn = systemService.findByProperty(CityEntity.class, "provinceCode", provinceEntity.getProvinceCode());

        if (lisn.size() <= 0) {
            map.put("status", 1);
            map.put("msg", "你选择的省份是不存在对应的市区的");
            String json = objectMapper.writeValueAsString(map);
            out.write(json);
            return;
        }
        map.put("status", 0);
        map.put("msg", "您可以添加覆盖区域");
        request.setAttribute("cityList", lisn);
        HttpSession session = request.getSession();
        session.setAttribute("lis", lisn);
        map.put("cityList", lisn);
        map.put("provincename", provincename);
        String json = objectMapper.writeValueAsString(map);
        double runtime = (System.currentTimeMillis() - startTime) / 1000.0;
        out.write(json);
        LOGGER.info("绑定亲情号,耗时" + runtime + "秒");
        LOGGER.info("debug_2016_0206runTime" + runtime + builder.toString());
    }
    
    @RequestMapping(params = "subAcct")
    public void subAcct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 调用后台的查询所有商户的信息的接口方法
        // 输出到页面
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        WeixinAcctEntity currAcct = this.weixinAcctService.get(WeixinAcctEntity.class, ResourceUtil.getSessionUserName().getTenantId());
        if(currAcct==null || StringUtils.equals(currAcct.getAdAgency(), "0")){
            return ;
        }
        List<WeixinAcctEntity> acctList = this.weixinAcctService.getSubAcctForAd(currAcct.getId());
        List typeList = new ArrayList<>();

        Map<String, String> map = new HashMap<String, String>();
        map.put("code", currAcct.getId());
        map.put("name", currAcct.getAcctName());
        typeList.add(map);
        if(acctList!=null && !acctList.isEmpty()){
            for (WeixinAcctEntity acct : acctList) {
                Map<String, String> m = new HashMap<String, String>();
                m.put("code", acct.getId());
                m.put("name", acct.getAcctName());
                typeList.add(m);
            }
        }
        String json = gson.toJson(typeList);
        out.write(json);
    }
    
    @RequestMapping(params = "NotCAllSubAcct")
    public void NotCAllSubAcct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 调用后台的查询所有商户的信息的接口方法
        // 输出到页面
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        WeixinAcctEntity currAcct = this.weixinAcctService.get(WeixinAcctEntity.class, ResourceUtil.getSessionUserName().getTenantId());
        if(currAcct==null){
            return ;
        }
        List<WeixinAcctEntity> acctList = this.weixinAcctService.getNotCAllSubAcct(currAcct.getId());
        List typeList = new ArrayList<>();

        Map<String, String> map = new HashMap<String, String>();
        map.put("code", "");
        map.put("name", "--请选择--");
        typeList.add(map);
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("code", currAcct.getId());
        map1.put("name", currAcct.getAcctForName());
        typeList.add(map1);
        if(acctList!=null && !acctList.isEmpty()){
            for (WeixinAcctEntity acct : acctList) {
                Map<String, String> m = new HashMap<String, String>();
                m.put("code", acct.getId());
                m.put("name", acct.getAcctForName());
                typeList.add(m);
            }
        }
        String json = gson.toJson(typeList);
        out.write(json);
    }
}
