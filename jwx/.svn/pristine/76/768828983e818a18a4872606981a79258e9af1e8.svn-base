package weixin.liuliangbao.controller.flowcontroller;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.cgform.exception.BusinessException;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import weixin.liuliangbao.jsonbean.WeixinMainEntity;
import weixin.liuliangbao.service.WeixinMainServiceI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by aa on 2016/1/20.
 */
@Controller
@RequestMapping("/weixinMainController")
public class WeixinMainController {
    private static final Logger LOGGER = Logger.getLogger(WeixinMainController.class);
    @Autowired
    private WeixinMainServiceI weixinMainServiceI;
    @Autowired
    private SystemService systemService;

    private String message;
    @RequestMapping(params = "weixinMainList")
    public ModelAndView WeixinMainList(HttpServletRequest request) {
        return new ModelAndView("liuliangbao/flowmanager/weixinMainList");
    }

    @RequestMapping(params = "mydatagrid")
    @ResponseBody
    public void datagrid(WeixinMainEntity weixinMainEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WeixinMainEntity.class, dataGrid);
        //查询条件组装器
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinMainEntity, request.getParameterMap());

        try {
            //自定义追加查询条件
            //只查询该公众号的幻灯片
            cq.eq("accountid", ResourceUtil.getWeiXinAccountId());
            LOGGER.info(ResourceUtil.getWeiXinAccountId());

        } catch (Exception e) {
            throw new org.jeecgframework.core.common.exception.BusinessException(e.getMessage());
        }
        cq.add();
        this.weixinMainServiceI.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);

    }

    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(HttpServletRequest request) {
        return new ModelAndView("liuliangbao/flowmanager/addWeixinMain");
    }
    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(WeixinMainEntity weixinMainEntity, HttpServletRequest request) {
        if (StringUtil.isNotEmpty(weixinMainEntity.getId())) {
            weixinMainEntity = weixinMainServiceI.getEntity(WeixinMainEntity.class, weixinMainEntity.getId());
            request.setAttribute("weixin",weixinMainEntity);
            String pagetype=weixinMainEntity.getPagetype();
            String jumptype=weixinMainEntity.getJumptype();
//            if(jumptype.equals("default")||jumptype.equals("默认页面")){
//
//            }else {
//                pagetype = pagetype.substring(0, pagetype.indexOf("shareId") - 1);
//            }
            switch (jumptype){
                case "default":
                    weixinMainEntity.setShareTitle("");
                    break;
                case "默认页面":
                    weixinMainEntity.setShareTitle("");
                    break;
                case "outurl":
                    weixinMainEntity.setShareTitle("");
                    break;
                case "外部页面":
                    weixinMainEntity.setShareTitle("");
                    break;
                case "flowpage":
                    if(weixinMainEntity.getIsShare().equals("")||weixinMainEntity.getIsShare()==null){
                        weixinMainEntity.setShareTitle("");
                        if(pagetype.contains("shareId")){
                            pagetype= pagetype.substring(0, pagetype.indexOf("shareId") - 1);
                        }else{

                        }
                    }else{
                        pagetype = pagetype.substring(0, pagetype.indexOf("shareId") - 1);
                        if(pagetype.contains("shareController.do")){

                        }else{
                            weixinMainEntity.setShareTitle("");
                        }
                    }
                    break;
                case "流量页面":
                    if(weixinMainEntity.getIsShare().equals("")||weixinMainEntity.getIsShare()==null){
                        weixinMainEntity.setShareTitle("");
                        if(pagetype.contains("shareId")){
                            pagetype= pagetype.substring(0, pagetype.indexOf("shareId") - 1);
                        }else{

                        }
                    }else{
                        pagetype = pagetype.substring(0, pagetype.indexOf("shareId") - 1);
                        if(pagetype.contains("shareController.do")){

                        }else{
                            weixinMainEntity.setShareTitle("");
                        }
                    }
                    break;
                default:
                    weixinMainEntity.setShareTitle("");
                    //pagetype = pagetype.substring(0, pagetype.indexOf("shareId") - 1);
                    break;
            }
            request.setAttribute("pagetype",pagetype);

        }
        return new ModelAndView("/liuliangbao/flowmanager/updateWeixinMain");
    }


    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(WeixinMainEntity weixinMainEntity, HttpServletRequest request) throws BusinessException {

        long startTime = System.currentTimeMillis();
        StringBuffer buffer = new StringBuffer();
        buffer.append("weixinMainController_doUpdate_begin:_");
        try {
            AjaxJson j = new AjaxJson();
            message = "微门户信息更新成功";
            if (weixinMainEntity.getId()==null) {
                String id= UUID.randomUUID().toString();
                id=id.replace("-","");
                String jumptype=weixinMainEntity.getJumptype();
//                if(jumptype.equals("default")){
//                    String pathmain=ResourceUtil.getConfigByName("domain");
//                    weixinMainEntity.setPagetype(pathmain+"/weixinMainController.do?goDefault&id="+id);
////                    weixinMainEntity.setPagetype(id);//todo:这里是默认页面的的跳转地址，还没有做
//                    weixinMainEntity.setShareId(id);
//                }else{
//                    weixinMainEntity.setPagetype(weixinMainEntity.getPagetype()+"&shareId="+weixinMainEntity.getShareTitle());
//                }
                switch (jumptype){
                    case "default":
                        String pathmain=ResourceUtil.getConfigByName("domain");
                        weixinMainEntity.setPagetype(pathmain+"/weixinMainController.do?goDefault&id="+id);
//                    weixinMainEntity.setPagetype(id);//todo:这里是默认页面的的跳转地址，还没有做
                        weixinMainEntity.setShareId(id);
                        weixinMainEntity.setShareTitle(ResourceUtil.getWeiXinAccount().getAccountname());
                        break;
                    case "outurl":
                        weixinMainEntity.setPagetype(weixinMainEntity.getPageurl());
                        break;
                    default:
                        if ("".equals(weixinMainEntity.getShareTitle()) || weixinMainEntity.getShareTitle() == null) {
                            weixinMainEntity.setIsShare("");
                            weixinMainEntity.setPagetype(weixinMainEntity.getPagetype());
                        } else {
                            weixinMainEntity.setIsShare("1");
                            weixinMainEntity.setPagetype(weixinMainEntity.getPagetype() + "&shareId=" + weixinMainEntity.getShareTitle());
                        }
                        break;
                }

                String pathurl=ResourceUtil.getConfigByName("media.url.prefix");
                String imagepath=weixinMainEntity.getImagepath();
                if(imagepath.contains(pathurl)){

                }else{
                    weixinMainEntity.setImagepath(pathurl+"/"+weixinMainEntity.getImagepath());
                }

                weixinMainEntity.setCreateDate(new Date());
                weixinMainServiceI.save(weixinMainEntity);
                buffer.append("_add_");
            } else {
                try {
                    weixinMainEntity.setOperateDate(new Date());
                    String jumptype=weixinMainEntity.getJumptype();
//                    if(jumptype.equals("default")||jumptype.equals("默认页面")){
//
//                    }else {
//                        weixinMainEntity.setPagetype(weixinMainEntity.getPagetype()+"&shareId="+weixinMainEntity.getShareTitle());
//                    }
                    String pathmain=ResourceUtil.getConfigByName("domain");
                    String pathurl=ResourceUtil.getConfigByName("media.url.prefix");
                    String imagepath=weixinMainEntity.getImagepath();
                    if(imagepath.contains(pathurl)){

                    }else{
                        weixinMainEntity.setImagepath(pathurl+"/"+weixinMainEntity.getImagepath());
                    }
                    switch (jumptype){
                        case "default":
                            weixinMainEntity.setPagetype(pathmain+"/weixinMainController.do?goDefault&id="+weixinMainEntity.getId());
//                    weixinMainEntity.setPagetype(id);//todo:这里是默认页面的的跳转地址，还没有做
                            weixinMainEntity.setShareId(weixinMainEntity.getId());
                            weixinMainEntity.setShareTitle(ResourceUtil.getWeiXinAccount().getAccountname());
                            weixinMainEntity.setIsShare("");
                            break;
                        case "默认页面":
                            weixinMainEntity.setPagetype(pathmain+"/weixinMainController.do?goDefault&id="+weixinMainEntity.getId());
//                    weixinMainEntity.setPagetype(id);//todo:这里是默认页面的的跳转地址，还没有做
                            weixinMainEntity.setShareId(weixinMainEntity.getId());
                            weixinMainEntity.setShareTitle(ResourceUtil.getWeiXinAccount().getAccountname());
                            weixinMainEntity.setIsShare("");
                            break;
                        case "outurl":
                            weixinMainEntity.setPagetype(weixinMainEntity.getPageurl());
                            weixinMainEntity.setIsShare("");
                            break;
                        case "外部页面":
                            weixinMainEntity.setPagetype(weixinMainEntity.getPageurl());
                            weixinMainEntity.setIsShare("");
                            break;
                        case "lottery":
                            weixinMainEntity.setIsShare("");
                            weixinMainEntity.setPagetype(weixinMainEntity.getPagetype());
                            break;
                        case "活动页面":
                            weixinMainEntity.setIsShare("");
                            weixinMainEntity.setPagetype(weixinMainEntity.getPagetype());
                            break;
                        default:
                            if(StringUtils.isBlank(weixinMainEntity.getShareTitle())){
                                weixinMainEntity.setIsShare("");
                            }else{
                                weixinMainEntity.setIsShare("1");
                            }
                            weixinMainEntity.setPagetype(weixinMainEntity.getPagetype()+"&shareId="+weixinMainEntity.getShareTitle());
                            break;
                    }
//                    String pathurl=ResourceUtil.getConfigByName("media.url.prefix");
//                    weixinMainEntity.setImagepath(pathurl+"/"+weixinMainEntity.getImagepath());
                    WeixinMainEntity t = weixinMainServiceI.get(WeixinMainEntity.class, weixinMainEntity.getId());
                    MyBeanUtils.copyBeanNotNull2Bean(weixinMainEntity, t);
                    weixinMainServiceI.saveOrUpdate(t);
                    buffer.append("_update_");

                } catch (Exception e) {
                    e.printStackTrace();
                    message = "微信单图消息更新失败";
                    throw new BusinessException(e.getMessage());
                }
            }

            long endTime = System.currentTimeMillis();
            buffer.append("_time:_" + (endTime - startTime) + "ms");
            LOGGER.info(buffer.toString());
            j.setMsg(message);
            return j;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(WeixinMainEntity weixinMainEntity, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        weixinMainEntity = systemService.getEntity(WeixinMainEntity.class, weixinMainEntity.getId());
        message = "门户删除成功";
        try{
            weixinMainServiceI.delete(weixinMainEntity);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        }catch(Exception e){
            e.printStackTrace();
            message = "门户删除失败";
            throw new org.jeecgframework.core.common.exception.BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "goDefault")
    public ModelAndView goDefault(HttpServletRequest request) throws ParseException {
        String id=request.getParameter("id");
        List<WeixinMainEntity> weixinMainEntityList=this.systemService.findHql("from WeixinMainEntity t where t.shareId=?",id);
        WeixinMainEntity weixinMainEntity;
        if(weixinMainEntityList.size()>0){
            weixinMainEntity=weixinMainEntityList.get(0);

            String datedd = weixinMainEntity.getCreateDate().toString();

            Date date1 = (new SimpleDateFormat("yyyy-MM-dd")).parse(datedd);
            String date = (new SimpleDateFormat("yyyy-MM-dd")).format(date1);
            request.getSession().setAttribute("date", date);
            request.setAttribute("weixin",weixinMainEntity);
        }
        return new ModelAndView("liuliangbao/flowmanager/goDefault");
    }


    @RequestMapping(params = "weixinMainAddress")
    public ModelAndView weixinMainAddress(HttpServletRequest request) {
//        request.setAttribute("hdid", request.getParameter("hdid"));
//        request.setAttribute("rooturl",ResourceUtil.getConfigByName("domain"));
        String rooturl=ResourceUtil.getConfigByName("domain");
        String accountid=ResourceUtil.getWeiXinAccountId();
        String id=request.getParameter("id");
        if(id.equals("")||id==null) {
            request.setAttribute("url", rooturl + "/mainController.do?load&accountid=" + accountid);
        }else{
            WeixinMainEntity weixinMainEntity=weixinMainServiceI.getEntity(WeixinMainEntity.class,id);
            request.setAttribute("url",weixinMainEntity.getPagetype());
        }
        return new ModelAndView("liuliangbao/flowmanager/weixinMainAddress");
    }

}
