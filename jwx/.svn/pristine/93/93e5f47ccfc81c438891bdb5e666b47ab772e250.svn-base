package weixin.liuliangbao.weigatedoor.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.timer.DynamicTask;
import org.jeecgframework.core.util.FileUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.liuliangbao.weigatedoor.entity.WeidoorpptEntity;
import weixin.liuliangbao.weigatedoor.service.WeidoorpptServiceI;
import weixin.source.controller.WeixinSourceController;
import weixin.source.service.WeixinSourceServiceI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * Created by xudan on 2015/12/8.
 */
@Controller
@RequestMapping("/pptManagerController")
public class PPTManagerController extends BaseController {
    private static final Logger LOGGER = Logger.getLogger(WeiDoorController.class);

    @Autowired
    private SystemService systemService;

    @Autowired
    private DynamicTask dynamicTask;

    @Autowired
    private WeidoorpptServiceI weidoorpptService;

    @Autowired
    private WeixinSourceServiceI weixinSourceService;

    @Autowired
    private WeixinAccountServiceI weixinAccountService;

    //文件保存路径的前缀 add by mike
    private static String filePathPrefix = null;

    //文件保存url的前缀 add by mike
    private static String fileUrlPrefix = null;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * pptlist page jump-xudan-2015-12-11
     * @param request
     * @return
     */
    @RequestMapping(params = "pptManagerList")
    public ModelAndView pptManagerList(HttpServletRequest request) {
        return new ModelAndView("liuliangbao/weigatedoor/pptmanager");
    }


    /**
     * pptmanager datagrid -xudan-2015-12-10 19:40:41
     * @param weidoorpptEntity
     * @param request
     * @param response
     * @param dataGrid
     */
    @RequestMapping(params = "mydatagrid")
    @ResponseBody
    public void datagrid(WeidoorpptEntity weidoorpptEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WeidoorpptEntity.class, dataGrid);
        //查询条件组装器
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weidoorpptEntity, request.getParameterMap());

        try {
            //自定义追加查询条件
            //只查询该公众号的幻灯片
            cq.eq("accountid", ResourceUtil.getWeiXinAccountId());
            LOGGER.info(ResourceUtil.getWeiXinAccountId());

        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        cq.add();
        this.weidoorpptService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }


    /**
     * ppt delete-xudan-2015-12-11 19:41:01
     * @param weidoorppt
     * @param req
     * @return
     */
    @RequestMapping(params = "goDelete")
    @ResponseBody
    public AjaxJson deletePPt(WeidoorpptEntity weidoorppt, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        weidoorppt = this.systemService.getEntity(WeidoorpptEntity.class, weidoorppt.getId());

        this.systemService.delete(weidoorppt);

        message = "删除信息数据成功！";
        systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        j.setMsg(this.message);
        return j;
    }


    /**
     * 预览页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(WeidoorpptEntity weidoorppt, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(weidoorppt.getId())) {
            weidoorppt = weidoorpptService.getEntity(WeidoorpptEntity.class, weidoorppt.getId());
            weidoorppt.setPictureUrl(ResourceUtil.getMediaUrlPrefix() + "/" +weidoorppt.getPictureUrl());
            String pptImageUrl=weidoorppt.getPictureUrl();
            //weidoorppt.setPictureUrl(getFileUrlPrefix() + "/" +weidoorppt.getPictureUrl());
            req.setAttribute("pptImageUrl", pptImageUrl);
        }
        /*String urltest="http://xiaoguaii.ngrok.natapp.cn/xxg/1449716014664.jpg";
        req.setAttribute("testurl",urltest);*/
        return new ModelAndView("liuliangbao/weigatedoor/pptInfo-update");
    }


    /**
     * delete ppt - xudan -2015-12-11 20:24:34
     * @param weidoorppt
     * @param request
     * @return
     */
    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(WeidoorpptEntity weidoorppt, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        weidoorppt = systemService.getEntity(WeidoorpptEntity.class, weidoorppt.getId());
        message = "幻灯片删除成功";
        try{
            weidoorpptService.delete(weidoorppt);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        }catch(Exception e){
            e.printStackTrace();
            message = "幻灯片删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }




    //////////////////////////////////////////   xiaoguai add start   //////////////////////////////////////////

    /**
     * 添加页面跳转
     *
     * @param request
     * @return
     */
    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(HttpServletRequest request) {
        return new ModelAndView("liuliangbao/weigatedoor/addpptInfo");
    }


    //素材管理上传图片
    @RequestMapping(params = "uploadthumbMike", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson uploadthumbMike(MultipartHttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {

        Map<String, MultipartFile> fileMap = request.getFileMap();
        String filePathName = null;
        String fileRelativeUrl = null;//图片相对url.  相对url加上前缀,就构成了完整的图片url

        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile mf = entity.getValue();// 获取上传文件对象
            String fileName = mf.getOriginalFilename();// 获取文件名
            String extend = FileUtils.getExtend(fileName);// 获取文件扩展名
            filePathName = ResourceUtil.getMediaPathPrefix() + File.separator + System.currentTimeMillis() + "." + extend;
            fileRelativeUrl = System.currentTimeMillis() + "." + extend;
            try {
                byte[] imageBytes = mf.getBytes();
                //将文件内容写到指定的文件之中
                WeixinSourceController.writeData2File(filePathName, imageBytes);
                LOGGER.info("文件已经保存到" + filePathName);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        AjaxJson j = new AjaxJson();
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("imagePath", filePathName);
        attributes.put("imageRelativeUrl", fileRelativeUrl);
        attributes.put("fileKey", "");
        attributes.put("name", "");
        attributes.put("viewhref", "");
        attributes.put("delurl", "");
        j.setMsg("文件添加成功");
        j.setAttributes(attributes);

        return j;

    }


    //上传图片到本地,并保存到数据库之中
    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(WeidoorpptEntity weidoorpptEntity, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        long startTime = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        builder.append("PPTManagerController_doAdd");

        try {
            String imageFileUrl = request.getParameter("imagePath");
            String imageRelativeUrl = request.getParameter("imageRelativeUrl");
            File file = new File(imageFileUrl);
            String pptTile=request.getParameter("pptTile");
            String description=request.getParameter("description");

            String pageLocation=request.getParameter("inputsupplyPage");
            String jumpType=request.getParameter("finalJumpType");//跳转类型
            String jumpUrlfirst=request.getParameter("pageurl");//跳转url
            LOGGER.info(jumpUrlfirst);

            //如果是默认页面
            String pageContent=request.getParameter("content");

            //如果是内部页面
            String jumpUrlSecond=request.getParameter("finalJumpUrl");
            LOGGER.info(jumpUrlSecond);

            /*String jumpUrl;
            if (jumpType.equals("默认页面")){
                jumpUrl=" ";
            }else{
                jumpUrl=jumpUrlfirst==""||jumpUrlfirst==null?jumpUrlSecond:jumpUrlfirst;
            }*/

            String jumpUrl= Objects.equals(jumpUrlfirst, "") ||jumpUrlfirst==null?jumpUrlSecond:jumpUrlfirst;

            //String jumpUrl=jumpUrlfirst==""||jumpUrlfirst==null?jumpUrlSecond:jumpUrlfirst;
            LOGGER.info(jumpUrl);

            /*String jumpUrll=jumpUrlfirst.equals("")?jumpUrlSecond:jumpUrlfirst;
            LOGGER.info(jumpUrll);*/

            String finalJumpUrlName=request.getParameter("finalJumpUrlName");
            /*String finalJumpUrlName;
            if(jumpType.equals("默认页面")||jumpType.equals("外部页面")){
                finalJumpUrlName=" ";
            }else{
                finalJumpUrlName=request.getParameter("finalJumpUrlName");
            }*/

            TSUser user=ResourceUtil.getSessionUserName();
            String loginUserName= user.getUserName();
            LOGGER.info(loginUserName);

            builder.append("pptTile_"+pptTile+"jumpType_"+jumpType);

            weidoorpptEntity.setId(UUID.randomUUID().toString());
            weidoorpptEntity.setTitle(pptTile);
            weidoorpptEntity.setPictureName(pptTile);
            weidoorpptEntity.setPictureUrl(imageRelativeUrl);
            weidoorpptEntity.setJumpType(jumpType);
            weidoorpptEntity.setJumpUrl(jumpUrl);
            weidoorpptEntity.setPageUrl(finalJumpUrlName);
            weidoorpptEntity.setPageLocation(pageLocation);
            weidoorpptEntity.setDescription(description);
            weidoorpptEntity.setOperator(loginUserName);
            weidoorpptEntity.setOperatetime(new Date());
            weidoorpptEntity.setPageContent(pageContent);




            weidoorpptService.save(weidoorpptEntity);
            message = "幻灯片上传成功";
            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

        } catch (Exception e) {
            e.printStackTrace();
            message = "幻灯片上传失败";
            throw new BusinessException(e.getMessage());
        }
        long runTime=System.currentTimeMillis()-startTime;
        LOGGER.info("runTime_"+runTime+"operator_"+builder.toString()+message);
        j.setMsg(message);
        return j;
    }


    /*编辑页面跳转*/
    @RequestMapping(params = "goEditppt")
    public ModelAndView goEditppt(WeidoorpptEntity weidoorppt, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(weidoorppt.getId())) {
            weidoorppt = weidoorpptService.getEntity(WeidoorpptEntity.class, weidoorppt.getId());
            weidoorppt.setPictureUrl(ResourceUtil.getMediaUrlPrefix() + "/" +weidoorppt.getPictureUrl());

            if("流量页面".equals(weidoorppt.getJumpType())|| "flowpage".equals(weidoorppt.getJumpType())){
                String jumpUrl = weidoorppt.getJumpUrl();

            }
            req.setAttribute("weidoorppt", weidoorppt);
        }
        return new ModelAndView("liuliangbao/weigatedoor/updatecontent");

    }



    //
    @RequestMapping(params = "doAdddd")
    @ResponseBody
    public AjaxJson doAdddd(WeidoorpptEntity weidoorpptEntity, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        long startTime = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        builder.append("PPTManagerController_doAdddd");

        try {

            String jumptype=request.getParameter("jumptype");
            if(StringUtils.isBlank(jumptype)){
                j.setMsg("跳转类型不能为空");
                j.setSuccess(false);
                return j;
            }
            String pptID=request.getParameter("id");
            LOGGER.info(pptID);
            //删除数据库中的数据
//            String imageRelativeUrl = request.getParameter("imageRelativeUrl");
            String imageRelativeUrl = request.getParameter("pictureUrl");
            String reduceUrl=ResourceUtil.getMediaUrlPrefix()+"/";
            String finalImageUrl=imageRelativeUrl.replace(reduceUrl,"");
            LOGGER.info(finalImageUrl);

///////////////////////////

           // String pageLocation=request.getParameter("inputsupplyPage");
            String jumpType=request.getParameter("finalJumpType");//跳转类型
            String jumpUrlfirst=request.getParameter("pageurl");//跳转url
            LOGGER.info(jumpUrlfirst);

            //如果是默认页面
            String pageContent;
            String jumpUrlSecond;
            if(jumpType.equals("外部页面")){
                pageContent=" ";
                jumpUrlSecond=request.getParameter("pageurl");
            }else{
                pageContent=request.getParameter("content");
                jumpUrlSecond=request.getParameter("finalJumpUrl");
            }

            //如果是内部页面

            LOGGER.info(jumpUrlSecond);


            /*String jumpUrl;
            if (jumpType.equals("默认页面")){
                jumpUrl=" ";
            }else{
                jumpUrl=jumpUrlfirst==""||jumpUrlfirst==null?jumpUrlSecond:jumpUrlfirst;
            }*/
           String jumpUrl=jumpUrlfirst==""||jumpUrlfirst==null?jumpUrlSecond:jumpUrlfirst;
            LOGGER.info(jumpUrl);

            String jumpUrll= StringUtils.defaultString(jumpUrlfirst, jumpUrlSecond);
            LOGGER.info(jumpUrll);
            String finalJumpUrlName;
            if(jumpType.equals("默认页面")||jumpType.equals("外部页面")){
                finalJumpUrlName=" ";
            }else{
                finalJumpUrlName=request.getParameter("finalJumpUrlName");
            }


            TSUser user=ResourceUtil.getSessionUserName();
            String loginUserName= user.getUserName();
            LOGGER.info(loginUserName);
            ///////////////
            String imageRelativeUrll = request.getParameter("imageRelativeUrl");
            LOGGER.info(imageRelativeUrll);

            String finalPictureUrl= StringUtils.defaultString(imageRelativeUrll, finalImageUrl);

            String pptTile=request.getParameter("title");
            String description=request.getParameter("description");

            String pageUrl=request.getParameter("pageUrl");
            /*String jumpType=request.getParameter("finalJumpUrl");
            String jumpUrl=request.getParameter("jumpUrl");

            String pageLocation11=request.getParameter("pageLocation");

            TSUser user=ResourceUtil.getSessionUserName();
            String loginUserName= user.getUserName();*/
            String pageLocation=request.getParameter("pageLocation");
            weidoorpptEntity.setId(pptID);
            weidoorpptEntity.setTitle(pptTile);
            weidoorpptEntity.setPictureName(pptTile);
            weidoorpptEntity.setPictureUrl(finalImageUrl);
            weidoorpptEntity.setJumpType(jumpType);

            weidoorpptEntity.setJumpUrl(jumpUrlSecond);
            weidoorpptEntity.setPageUrl(finalJumpUrlName);

            weidoorpptEntity.setPageContent(pageContent);
            weidoorpptEntity.setPageLocation(pageLocation);
            weidoorpptEntity.setDescription(description);
            weidoorpptEntity.setOperator(loginUserName);
            weidoorpptEntity.setOperatetime(new Date());

            weidoorpptService.saveOrUpdate(weidoorpptEntity);
            message = "幻灯片编辑成功";
            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

        } catch (Exception e) {
            //e.printStackTrace();
            message = "幻灯片编辑失败";
            e.printStackTrace();
            //throw new BusinessException(e.getMessage());
        }
        long runTime=System.currentTimeMillis()-startTime;
        LOGGER.info("runTime_"+runTime+"operator_"+builder.toString()+message);

        j.setMsg(message);
        return j;
    }
}
