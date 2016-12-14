package weixin.tenant.controller;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import weixin.liuliangbao.jsonbean.AccountGroup.acctAndGroup;
import weixin.tenant.entity.MerchantAndGroupEntity;
import weixin.tenant.entity.MerchantGroupEntity;
import weixin.tenant.entity.WeixinAcctEntity;
import weixin.tenant.service.MerchantAndGroupEntityServiceI;
import weixin.tenant.service.MerchantGroupEntityServiceI;
import weixin.tenant.service.WeixinAcctServiceI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by aa on 2015/12/4.
 */
@Controller
@RequestMapping("/merchantAndGroupController")
public class MerchantAndGroupController extends BaseController {

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(MerchantAndGroupController.class);
    //    商户和商户组添加
    @Autowired
    private MerchantAndGroupEntityServiceI merchantAndGroupEntityServiceI;
    //    商户组添加
    @Autowired
    private MerchantGroupEntityServiceI merchantGroupEntityServiceI;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Autowired
    private SystemService systemService;
    @Autowired
    private WeixinAcctServiceI weixinAcctService;

    /**
     * 显示商户和组列表
     *
     * @return
     */
    @RequestMapping(params = "merchantAndGroup")
    public ModelAndView merchantAndGroup(HttpServletRequest request) {
        return new ModelAndView("weixin/tenant/merchantAndGroupList");
    }

    /**
     * �̻���
     *
     * @param merchantGroup
     * @param request
     * @param response
     * @param dataGrid
     */
    @RequestMapping(params = "datagrid")
    @ResponseBody
    public void datagrid(MerchantGroupEntity merchantGroup, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {


//        此处存在一个问题就是查询的是全部的，可以改为根据条件查询，但是此时就是假分页了
        CriteriaQuery cq = new CriteriaQuery(MerchantGroupEntity.class, dataGrid);
        //查询条件组装器
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, merchantGroup, request.getParameterMap());
        String id = ResourceUtil.getSessionUserName().getTenantId();
        try {
            //自定义追加查询条件
            cq.eq("tenantId", id);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
//        int pageindex = cq.getCurPage();
////        int pageindex = dataGrid.getPage();
//        int pageSize = cq.getPageSize();
////        int pageSize = dataGrid.getRows();
//        int begin = (pageindex - 1) * pageSize;
//        int end = pageindex * pageSize;
//
//        String sql = "SELECT m.groupId as id,g.groupName as name ,COUNT(m.acctId)as numAccount FROM MerchantAndGroup m JOIN MerchantGroup g WHERE g.id=m.groupId and g.tenantId='" + id + "' GROUP BY m.groupId LIMIT " + begin + "," + end + "";
//        List<MerchantGroupEntity> lisGandAcct = new ArrayList<MerchantGroupEntity>();
////        List<UnionEntity> lisGandAcct = new ArrayList<UnionEntity>();
//        lisGandAcct = systemService.findListbySql(sql);
//
//        dataGrid.setResults(lisGandAcct);
//        cq.setDataGrid(dataGrid);
        this.merchantGroupEntityServiceI.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "datagridBySql")
    public void datagridBySql(acctAndGroup acctEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
//        String id = ResourceUtil.getSessionUserName().getTenantId();
//        添加日志追踪记录
        StringBuilder queryAccGroup = new StringBuilder();
        long startTime = System.currentTimeMillis();//获取开始当前的时间   查询方法的执行时间
        queryAccGroup.append("方法开始时间_" + startTime + "ms" + "_");  //添加的日志


        StringBuffer sql = new StringBuffer();

        //        此处可以写模糊查询
        String groupName = request.getParameter("groupName");
//        String ids = "ff80808150fbeb480150fc0af00e0001";
        String ids = ResourceUtil.getSessionUserName().getTenantId();
        sql.append("SELECT groupId id ,groupName , tenantId,COUNT(m.acctId) counts  FROM MerchantAndGroup m JOIN MerchantGroup g WHERE g.id=m.groupId ");
//        sql.append(" and g.tenantId=").append("'").append(ResourceUtil.getSessionUserName().getTenantId()).append("'");
        sql.append(" and g.tenantId=").append("'").append(ids).append("'");
        if (groupName != null && !"".equals(groupName)) {
            sql.append(" and groupName=").append("'").append(groupName).append("'");
        }
        sql.append(" GROUP BY m.groupId");
        sql.append(" ORDER BY g.foundedTime DESC");
        queryAccGroup.append("当前执行的sql语句" + sql + "_");

        HqlQuery hqlQuery = new HqlQuery(acctAndGroup.class, sql.toString(), dataGrid);
        PageList pageList = this.merchantAndGroupEntityServiceI.getPageListBySql(hqlQuery, false);
        queryAccGroup.append("当前查询的结果总数" + pageList.getCount() + "_");
        List<Object[]> list = pageList.getResultList();
        List<acctAndGroup> param = new ArrayList<acctAndGroup>();
//        int i = 0;
        for (Object[] objects : list) {
            acctAndGroup wd = new acctAndGroup();
            Object gid = objects[0];
            if (gid != null) {
                wd.setId(gid.toString());
            }
            Object gname = objects[1];
            if (gname != null) {
                wd.setGroupName(gname.toString());
            }
            Object tid = objects[2];
            if (tid != null) {
                wd.setTenantId(tid.toString());
            }
            Object counts = objects[3];
            if (counts != null) {
                wd.setCounts(Integer.valueOf(counts.toString()));
            }

//            wd.setId(i + "");
//            i++;
            param.add(wd);
        }
        dataGrid.setResults(param);
        dataGrid.setTotal(pageList.getCount());
        dataGrid.setPage(pageList.getCurPageNO());
        dataGrid.setRows(pageList.getOffset());
        TagUtil.datagrid(response, dataGrid);

        long endTime = System.currentTimeMillis();//获取结束的当前时间   检测程序的运行时间
        queryAccGroup.append("方法结束时间_" + endTime + "ms" + "_");  //添加的日志

        long totaltime = endTime - startTime;  //总耗时
        queryAccGroup.append("方法执行总的时间" + totaltime + "ms");  //添加的日志

        logger.info("merchantAndGroupController的datagridBySql方法执行过程中的各个操作的输入输出参数以及结果_" + queryAccGroup.toString());
    }

    /**
     * 商户组和商户的添加
     *
     * @return
     */
    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(HttpServletRequest req) {
        WeixinAcctEntity weixinAcct = new WeixinAcctEntity();
        String id = ResourceUtil.getSessionUserName().getTenantId();
        req.setAttribute("id", id);
        if (StringUtil.isNotEmpty(id)) {
            weixinAcct = weixinAcctService.getEntity(WeixinAcctEntity.class, id);
            req.setAttribute("weixinAcctPage", weixinAcct);
        }
        return new ModelAndView("weixin/tenant/weixinAcctAndGroup-add");
    }

    /**
     * 商户组和商户的添加
     *
     * @return
     */
    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(MerchantGroupEntity merchantGroup, HttpServletRequest request) {

        StringBuilder addGroup = new StringBuilder();
        long startTime = System.currentTimeMillis();//获取开始当前的时间   查询方法的执行时间
        addGroup.append("方法开始时间_" + startTime + "ms" + "_");  //添加的日志

        AjaxJson j = new AjaxJson();
        message = "商户组创建成功";

        String tennanid = request.getParameter("id");
        addGroup.append("租户Id_" + tennanid + "_");  //添加的日志
        String gname = request.getParameter("groupName");
        addGroup.append("组名_" + gname + "_");  //添加的日志
        WeixinAcctEntity weixinAcctEntity = weixinAcctService.getEntity(WeixinAcctEntity.class, tennanid);
        try {
            merchantGroup.setGroupName(gname);
            merchantGroup.setTenantId(tennanid);
            merchantGroup.setFoundedTime(new Date());
            merchantGroup.setFounder(weixinAcctEntity.getAcctName());
            merchantGroupEntityServiceI.save(merchantGroup);

            addGroup.append("一条商户组记录添加成功_" + merchantGroup + "_");  //添加的日志

//            此时还得更新商户和商户组的表
            MerchantAndGroupEntity m = new MerchantAndGroupEntity();
            m.setGroupId(merchantGroup.getId());
            //并且把自己加入到这个商户组里面，初始化的时候就加入
            m.setAcctId(weixinAcctEntity.getId());
            merchantAndGroupEntityServiceI.save(m);

            addGroup.append("一条商户和组记录添加成功_" + m + "_");  //添加的日志
        } catch (Exception e) {
            e.printStackTrace();
            message = "商户组或者商户和组创建失败";
            throw new BusinessException(e.getMessage());
        }
        long endTime = System.currentTimeMillis();//获取结束的当前时间   检测程序的运行时间
        addGroup.append("方法结束时间_" + endTime + "ms" + "_");  //添加的日志

        long totaltime = endTime - startTime;  //总耗时
        addGroup.append("方法执行总的时间" + totaltime + "ms");  //添加的日志

        logger.info("merchantAndGroupController的doAdd方法执行过程中的各个操作的输入输出参数以及结果_" + addGroup.toString());
        j.setMsg(message);
        return j;


    }

    /**
     * 检测新增的用户名是否已经存在
     *
     * @param request
     * @return
     */
    @RequestMapping(params = "checkgroupName")
    @ResponseBody
    public AjaxJson checkgroupName(HttpServletRequest request) {

        StringBuilder checkgroupName = new StringBuilder();
        long startTime = System.currentTimeMillis();//获取开始当前的时间   查询方法的执行时间
        checkgroupName.append("方法开始时间_" + startTime + "ms" + "_");  //添加的日志
        AjaxJson j = new AjaxJson();

        //获取页面输入项
        String groupName = request.getParameter("groupName");
        checkgroupName.append("页面获取的组名_" + groupName + "_");  //添加的日志
        MerchantGroupEntity groups = systemService.findUniqueByProperty(MerchantGroupEntity.class, "groupName", groupName);
        if (groups != null) {

            j.setMsg("此组名已经存在，请重新输入！");
            j.setSuccess(false);
        }

        long endTime = System.currentTimeMillis();//获取结束的当前时间   检测程序的运行时间
        checkgroupName.append("方法结束时间_" + endTime + "ms" + "_");  //添加的日志

        long totaltime = endTime - startTime;  //总耗时
        checkgroupName.append("方法执行总的时间" + totaltime + "ms");  //添加的日志

        logger.info("merchantAndGroupController的checkgroupName方法执行过程中的各个操作的输入输出参数以及结果_" + checkgroupName.toString());
        return j;
    }


    /**
     * 商户组和商户的添加
     *
     * @return
     */
    @RequestMapping(params = "maccdatagridAllList")
    public ModelAndView maccdatagridAllList(acctAndGroup merchantGroup, HttpServletRequest request) {
//        将组id设置到request里面
        String idg = merchantGroup.getId();
        request.setAttribute("id", idg);
        String tennantId = ResourceUtil.getSessionUserName().getTenantId();
        try {
            MerchantGroupEntity merchantGroup1 = new MerchantGroupEntity();
            merchantGroup1 = merchantGroupEntityServiceI.getEntity(MerchantGroupEntity.class, idg);
            request.setAttribute("merchantGroup", merchantGroup1);
            request.setAttribute("tennantId", tennantId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("weixin/tenant/maccdatagridAll");
    }

    /**
     * 将选中的商户添加到组
     *
     * @return
     */
    @RequestMapping(params = "doBatchAdd")
    @ResponseBody
    public AjaxJson doBatchAdd(String ids, HttpServletRequest request) {
        StringBuilder doBatchAdd = new StringBuilder();
        long startTime = System.currentTimeMillis();//获取开始当前的时间   查询方法的执行时间
        doBatchAdd.append("方法开始时间_" + startTime + "ms" + "_");  //添加的日志

        String groupId = request.getParameter("id");

        doBatchAdd.append("页面获取组ID" + groupId + "_");  //添加的日志

        doBatchAdd.append("添加的组id" + ids + "_");  //添加的日志
        AjaxJson j = new AjaxJson();
        message = "向组中添加信息成功信息数据成功";
        int succeed = 0;
        int error = 0;
        try {
            for (String id : ids.split(",")) {
                List<MerchantAndGroupEntity> lisMG = this.merchantAndGroupEntityServiceI.findByProperty(MerchantAndGroupEntity.class, "acctId", id);
                if (lisMG.size() > 0) {

                }
                WeixinAcctEntity textTemplate = this.weixinAcctService.getEntity(WeixinAcctEntity.class, id);
                doBatchAdd.append("根据id查询到的商户实体" + textTemplate + "_");  //添加的日志
//                this.weixinAcctService.delete(textTemplate);
                MerchantAndGroupEntity merchantAndGroupEntity = new MerchantAndGroupEntity();
                merchantAndGroupEntity.setAcctId(textTemplate.getId());
                merchantAndGroupEntity.setGroupId(groupId);
                merchantAndGroupEntity.setJoinDate(new Date());
                merchantAndGroupEntity.setStatus("加入");
                merchantAndGroupEntityServiceI.save(merchantAndGroupEntity);
                doBatchAdd.append("添加组成员成功" + merchantAndGroupEntity + "_");  //添加的日志
                succeed += 1;
                systemService.addLog(message, Globals.Log_Type_DEL,
                        Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            error += 1;
            message = "添加信息数据失败";
            throw new BusinessException(e.getMessage());
        }
        message = "添加信息数据成功" + succeed + "条，失败" + error + "条";
        j.setMsg(message);
        long endTime = System.currentTimeMillis();//获取结束的当前时间   检测程序的运行时间
        doBatchAdd.append("方法结束时间_" + endTime + "ms" + "_");  //添加的日志

        long totaltime = endTime - startTime;  //总耗时
        doBatchAdd.append("方法执行总的时间" + totaltime + "ms");  //添加的日志

        logger.info("merchantAndGroupController的__doBatchAdd__方法执行过程中的各个操作的输入输出参数以及结果_" + doBatchAdd.toString());
        return j;
    }

    /**
     * 删除信息
     *
     * @param merchantGroup
     * @param req
     * @return
     */
    @RequestMapping(params = "del")
    @ResponseBody
    public AjaxJson del(MerchantGroupEntity merchantGroup,
                        HttpServletRequest req) {
        StringBuilder del = new StringBuilder();
        long startTime = System.currentTimeMillis();//获取开始当前的时间   查询方法的执行时间
        del.append("方法开始时间_" + startTime + "ms" + "_");  //添加的日志

        AjaxJson j = new AjaxJson();
        merchantGroup = this.merchantGroupEntityServiceI.getEntity(MerchantGroupEntity.class,
                merchantGroup.getId());
        del.append("要删除的组记录" + merchantGroup + "_");  //添加的日志
//        根据获取的组id查询下面的商户并且进行删除
        List<MerchantAndGroupEntity> lisAcct = merchantAndGroupEntityServiceI.findByProperty(MerchantAndGroupEntity.class, "groupId", merchantGroup.getId());
        del.append("根据组id查询到其下的成员商户的个数" + lisAcct.size() + "_");  //添加的日志
        try {
            for (MerchantAndGroupEntity merchantEntity : lisAcct) {
                this.merchantAndGroupEntityServiceI.delete(merchantEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.merchantGroupEntityServiceI.delete(merchantGroup);
        del.append("成功删除" + merchantGroup + "_");  //添加的日志
        message = "删除信息数据成功！";
        systemService.addLog(message, Globals.Log_Type_DEL,
                Globals.Log_Leavel_INFO);
        j.setMsg(this.message);

        long endTime = System.currentTimeMillis();//获取结束的当前时间   检测程序的运行时间
        del.append("方法结束时间_" + endTime + "ms" + "_");  //添加的日志

        long totaltime = endTime - startTime;  //总耗时
        del.append("方法执行总的时间" + totaltime + "ms");  //添加的日志

        logger.info("merchantAndGroupController的__del__方法执行过程中的各个操作的输入输出参数以及结果_" + del.toString());
        return j;
    }

    /**
     * 商户信息查询（加载的商户查询所有的商户）
     *
     * @param weixinAcctEntity
     * @param request
     * @param response
     * @param dataGrid
     */
    @RequestMapping(params = "maccdatagridAll")
    public void maccdatagridAll(WeixinAcctEntity weixinAcctEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws Exception {

        StringBuilder maccdatagridAll = new StringBuilder();
        long startTime = System.currentTimeMillis();//获取开始当前的时间   查询方法的执行时间
        maccdatagridAll.append("方法开始时间_" + startTime + "ms" + "_");  //添加的日志
        //        获取组id
        String groupId = request.getParameter("id");
        StringBuffer sql = new StringBuffer();


//        此处可以写模糊查询
        String acctfor = request.getParameter("acctForName");

        String belogAcct = request.getParameter("belogAcct");

//        String accType = weixinAcctEntity.getAcctLevel();
        String accType = request.getParameter("businessType");


        String provinceName = request.getParameter("province");

        String city = request.getParameter("city");

//        判断一下查到根据组id查询的用户数据是否为空，如果不为空，则进行下面的操作
//        String sql0 = "select p.acctId from MerchantAndGroup p where 1=1 and p.groupId='" + groupId + "' ";
        String hql = "from MerchantAndGroupEntity p where p.groupId='" + groupId + "'";
        List<MerchantAndGroupEntity> lis = new ArrayList<MerchantAndGroupEntity>();
        lis = merchantAndGroupEntityServiceI.findHql(hql, null);
//        判断得到的集合是否为空，为空的时候是无法进行sql语句的in操作的，此时我们需要对得到的结果集进行判断，从而将不需要的结果进行排除即可
        if (lis.size() > 0 && lis.get(0).getAcctId() != null) {
            sql.append("select a.id,a.acctForName,a.belogAcct,a.business_type,a.province,a.city from weixin_acct a where a.id not in(select p.acctId from MerchantAndGroup p where 1=1 and p.groupId='" + groupId + "' )");
        } else {
            sql.append("select a.id,a.acctForName,a.belogAcct,a.business_type,a.province,a.city from weixin_acct a");
        }
//        sql.append("select a.id,a.acctForName,a.belogAcct,a.business_type,a.province,a.city from weixin_acct a where a.id not in(select p.acctId from MerchantAndGroup p where 1=1 and p.groupId='" + groupId + "' )");
//       动态sql查询，商户名
        maccdatagridAll.append("当前执行sql语句" + sql + "_");  //添加的日志
        if (acctfor != null && !"".equals(acctfor)) {
            sql.append(" and a.acctForName=").append("'").append(acctfor).append("'");
            maccdatagridAll.append("商户名acctfor" + acctfor + "_");  //添加的日志
        }
//        所属商户
        if (belogAcct != null && !"".equals(belogAcct)) {
            sql.append(" and a.belogAcct=").append("'").append(belogAcct).append("'");
            maccdatagridAll.append("belogAcct" + belogAcct + "_");  //添加的日志
        }
//        商业类型
        if (accType != null && !"".equals(accType)) {
            sql.append(" and a.business_type=").append("'").append(accType).append("'");
            maccdatagridAll.append("accType" + accType + "_");  //添加的日志
        }
//        省
        if (provinceName != null && !"".equals(provinceName)) {
            sql.append(" and a.province=").append("'").append(provinceName).append("'");
            maccdatagridAll.append("provinceName" + provinceName + "_");  //添加的日志
        }
        //       市
        if (city != null && !"".equals(city)) {
            sql.append(" and a.city=").append("'").append(city).append("'");
            maccdatagridAll.append("city" + city + "_");  //添加的日志
        }
        HqlQuery hqlQuery = new HqlQuery(WeixinAcctEntity.class, sql.toString(), dataGrid);
        PageList pageList = this.weixinAcctService.getPageListBySql(hqlQuery, false);

        maccdatagridAll.append("查询结果pageList的条数" + pageList.getCount() + "_");  //添加的日志
        List<Object[]> list = pageList.getResultList();
        List<WeixinAcctEntity> param = new ArrayList<WeixinAcctEntity>();
        int i = 0;
        for (Object[] objects : list) {
            WeixinAcctEntity wd = new WeixinAcctEntity();
            Object id = objects[0];
            if (id != null) {
                wd.setId(id.toString());
            }
            Object name = objects[1];
            if (name != null) {
                wd.setAcctForName(name.toString());
            }
            Object belong = objects[2];
            if (belong != null) {
                wd.setBelogAcct(belong.toString());
            }
            Object bty = objects[3];
            if (bty != null) {
                wd.setBusinessType(bty.toString());
            }
            Object pvin = objects[4];
            if (bty != null) {
                wd.setProvince(pvin.toString());
            }
            Object cty = objects[5];
            if (cty != null) {
                wd.setCity(cty.toString());
            }
            param.add(wd);
        }
        dataGrid.setResults(param);
        dataGrid.setTotal(pageList.getCount());
        dataGrid.setPage(pageList.getCurPageNO());
        dataGrid.setRows(pageList.getOffset());
        TagUtil.datagrid(response, dataGrid);
        long endTime = System.currentTimeMillis();//获取结束的当前时间   检测程序的运行时间
        maccdatagridAll.append("方法结束时间_" + endTime + "ms" + "_");  //添加的日志

        long totaltime = endTime - startTime;  //总耗时
        maccdatagridAll.append("方法执行总的时间" + totaltime + "ms");  //添加的日志

        logger.info("merchantAndGroupController的__maccdatagridAll__方法执行过程中的各个操作的输入输出参数以及结果_" + maccdatagridAll.toString());
//        CriteriaQuery cq = new CriteriaQuery(WeixinAcctEntity.class, dataGrid);
//        //查询条件组装器
//        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinAcct, request.getParameterMap());
////		根据条件进行查询
////		cq.eq("id", ResourceUtil.getSessionUserName().getTenantId());//根据公众ID进行数据隔离
////        cq.eq("pid", ResourceUtil.getSessionUserName().getTenantId());//根据公众ID进行数据隔离
//
//        try {
//            //自定义追加查询条件
//        } catch (Exception e) {
//            throw new BusinessException(e.getMessage());
//        }
//        cq.add();
//
//        this.weixinAcctService.getDataGridReturn(cq, true);
//        TagUtil.datagrid(response, dataGrid);

    }


    /**
     * 商户组和商户的添加
     *
     * @return
     */
    @RequestMapping(params = "maccdatagridJoinedList")
    public ModelAndView maccdatagridJoinedList(acctAndGroup merchantGroup, HttpServletRequest request) {
//        获取组id
        String idg = merchantGroup.getId();
        request.setAttribute("id", idg);
//        String tennantId = ResourceUtil.getSessionUserName().getTenantId();
//        try {
//            merchantGroup = merchantGroupEntityServiceI.getEntity(MerchantGroupEntity.class, idg);
//            request.setAttribute("merchantGroup", merchantGroup);
//            request.setAttribute("tennantId", tennantId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return new ModelAndView("weixin/tenant/maccdatagridJoinedList");
    }

    /**
     * 商户的查询（已经添加的）
     *
     * @param accten
     * @param request
     * @param response
     * @param dataGrid
     */
    @RequestMapping(params = "maccdatagridJoined")
    public void maccdatagridJoined(acctAndGroup accten, HttpServletRequest request, HttpServletResponse
            response, DataGrid dataGrid) {
        String groupId = request.getParameter("id");

        StringBuilder maccdatagridJoined = new StringBuilder();
        long startTime = System.currentTimeMillis();//获取开始当前的时间   查询方法的执行时间
        maccdatagridJoined.append("方法开始时间_" + startTime + "ms" + "_");  //添加的日志

        //        此处可以写模糊查询
        String acctfor = request.getParameter("acctForName");

        String belogAcct = request.getParameter("belogAcct");

        String accType = request.getParameter("businessType");

        String provinceName = request.getParameter("province");

        String city = request.getParameter("city");

        StringBuffer sql = new StringBuffer();
        sql.append("select a.id,a.acctForName,a.belogAcct,a.business_type,a.province,a.city from weixin_acct a join MerchantAndGroup m where a.id=m.acctId ");
        if (groupId != null && !"".equals(groupId)) {
            sql.append(" and groupId=").append("'").append(groupId).append("'");
        }

        //       动态sql查询，商户名
        if (acctfor != null && !"".equals(acctfor)) {
            sql.append(" and a.acctForName=").append("'").append(acctfor).append("'");
        }
//        所属商户
        if (belogAcct != null && !"".equals(belogAcct)) {
            sql.append(" and a.belogAcct=").append("'").append(belogAcct).append("'");
        }
//        商业类型
        if (accType != null && !"".equals(accType)) {
            sql.append(" and a.business_type=").append("'").append(accType).append("'");
        }
//        省
        if (provinceName != null && !"".equals(provinceName)) {
            sql.append(" and a.province=").append("'").append(provinceName).append("'");
        }
        //       市
        if (city != null && !"".equals(city)) {
            sql.append(" and a.city=").append("'").append(city).append("'");
        }
        HqlQuery hqlQuery = new HqlQuery(WeixinAcctEntity.class, sql.toString(), dataGrid);
        PageList pageList = this.weixinAcctService.getPageListBySql(hqlQuery, false);
        maccdatagridJoined.append("查询结果pageList总条数" + pageList + "_");  //添加的日志
        List<Object[]> list = pageList.getResultList();
        List<WeixinAcctEntity> param = new ArrayList<WeixinAcctEntity>();
        int i = 0;
        for (Object[] objects : list) {
            WeixinAcctEntity wd = new WeixinAcctEntity();
            Object id = objects[0];
            if (id != null) {
                wd.setId(id.toString());
            }
            Object name = objects[1];
            if (name != null) {
                wd.setAcctForName(name.toString());
            }
            Object belong = objects[2];
            if (belong != null) {
                wd.setBelogAcct(belong.toString());
            }
            Object bty = objects[3];
            if (bty != null) {
                wd.setBusinessType(bty.toString());
            }
            Object pvin = objects[4];
            if (bty != null) {
                wd.setProvince(pvin.toString());
            }
            Object cty = objects[5];
            if (cty != null) {
                wd.setCity(cty.toString());
            }
            param.add(wd);
        }
        dataGrid.setResults(param);
        dataGrid.setTotal(pageList.getCount());
        dataGrid.setPage(pageList.getCurPageNO());
        dataGrid.setRows(pageList.getOffset());
        TagUtil.datagrid(response, dataGrid);


        long endTime = System.currentTimeMillis();//获取结束的当前时间   检测程序的运行时间
        maccdatagridJoined.append("方法结束时间_" + endTime + "ms" + "_");  //添加的日志

        long totaltime = endTime - startTime;  //总耗时
        maccdatagridJoined.append("方法执行总的时间" + totaltime + "ms");  //添加的日志
        logger.info("merchantAndGroupController的__maccdatagridJoined__方法执行过程中的各个操作的输入输出参数以及结果_" + maccdatagridJoined.toString());
//        List<MerchantAndGroupEntity> lisAccGroup = merchantAndGroupEntityServiceI.findByProperty(MerchantAndGroupEntity.class, "groupId", groupId);
//        List<WeixinAcctEntity> lisAdded = new ArrayList<WeixinAcctEntity>();
//        try {
//            for (MerchantAndGroupEntity merEntity : lisAccGroup) {
//                WeixinAcctEntity weixinAcctEntity = new WeixinAcctEntity();
//                String acctId = merEntity.getAcctId();
//                weixinAcctEntity = weixinAcctService.getEntity(WeixinAcctEntity.class, acctId);
//                lisAdded.add(weixinAcctEntity);
//            }
//
//            dataGrid.setResults(lisAdded);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new BusinessException(e.getMessage());
//        }
//        TagUtil.datagrid(response, dataGrid);
    }


    /**
     * 测试使用的
     */
    @RequestMapping(params = "merchantAndGroupJdbc")
    public ModelAndView merchantAndGroupJdbc(HttpServletRequest request) {
//       租户id
        String id = ResourceUtil.getSessionUserName().getTenantId();
//        String hql0 = "from CgreportConfigItemEntity where 1 = 1 AND cGRHEAD_ID = ? ";

        String sql = "SELECT m.groupId,g.groupName,COUNT(m.acctId) FROM MerchantAndGroup m JOIN MerchantGroup g WHERE g.id=m.groupId and g.tenantId='" + id + "' GROUP BY m.groupId";
//        String sql = "SELECT m.groupId,g.groupName,COUNT(m.acctId) FROM MerchantAndGroup m JOIN MerchantGroup g WHERE g.id=m.groupId and g.tenantId='ff80808150fbeb480150fc0af00e0009' GROUP BY m.groupId ";
        try {
            List<MerchantGroupEntity> lisGandAcct = new ArrayList<MerchantGroupEntity>();

            lisGandAcct = systemService.findListbySql(sql);
            request.setAttribute("lisGAcct", lisGandAcct);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return new ModelAndView("weixin/tenant/merchantAndGroupList");
    }

    /**
     * 批量删除以及删除已经存在于商户和组中的信息消息
     *
     * @return
     */
    @RequestMapping(params = "doBatchDelete")
    @ResponseBody
    public AjaxJson doBatchDelete(String ids, HttpServletRequest request) {
//        获取组id,删除就是删除在商户和组那个表中的对应的数据
        StringBuilder doBatchDelete = new StringBuilder();
        long startTime = System.currentTimeMillis();//获取开始当前的时间   查询方法的执行时间
        doBatchDelete.append("方法开始时间_" + startTime + "ms" + "_");  //添加的日志

        String groupId = request.getParameter("id");
        doBatchDelete.append("页面获取的要删除的组id" + groupId + "_");  //添加的日志

        doBatchDelete.append("要删除的成员ids" + ids + "_");  //添加的日志
        AjaxJson j = new AjaxJson();
        message = "删除信息数据成功";
        int succeed = 0;
        int error = 0;
        try {
            for (String id : ids.split(",")) {
                String hql = "from MerchantAndGroupEntity where acctId='" + id + "' and groupId='" + groupId + "'";
                doBatchDelete.append("查询成员的hql语句" + hql + "_");  //添加的日志
                List<MerchantAndGroupEntity> lis = merchantAndGroupEntityServiceI.findHql(hql, null);
                MerchantAndGroupEntity merchantAndGroup = new MerchantAndGroupEntity();
                if (lis.size() > 0) {
                    merchantAndGroup = lis.get(0);
                } else {
                    j.setSuccess(false);
                    j.setMsg("数据有错");
                    return j;
                }
//                MerchantAndGroupEntity merchantAndGroup = this.merchantAndGroupEntityServiceI.findUniqueByProperty(MerchantAndGroupEntity.class, "acctId", id);
                this.merchantAndGroupEntityServiceI.delete(merchantAndGroup);
                doBatchDelete.append("成功删除的记录" + merchantAndGroup + "_");  //添加的日志
                succeed += 1;
                systemService.addLog(message, Globals.Log_Type_DEL,
                        Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            error += 1;
            message = "删除信息数据失败";
            throw new BusinessException(e.getMessage());
        }
        message = "删除信息数据成功" + succeed + "条，失败" + error + "条";
        j.setMsg(message);

        long endTime = System.currentTimeMillis();//获取结束的当前时间   检测程序的运行时间
        doBatchDelete.append("方法结束时间_" + endTime + "ms" + "_");  //添加的日志

        long totaltime = endTime - startTime;  //总耗时
        doBatchDelete.append("方法执行总的时间" + totaltime + "ms");  //添加的日志

        logger.info("merchantAndGroupController的__doBatchDelete__方法执行过程中的各个操作的输入输出参数以及结果_" + doBatchDelete.toString());
        return j;
    }
}
