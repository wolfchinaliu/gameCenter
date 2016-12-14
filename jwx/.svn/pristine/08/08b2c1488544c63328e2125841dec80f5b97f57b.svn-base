package weixin.acctlist.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.annotation.DataSourceSwitch;
import org.jeecgframework.core.aop.DataSourceSwitcher;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.datasource.DataSourceType;
import org.jeecgframework.core.util.FileUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import weixin.acctlist.entity.AccountListEntity;
import weixin.acctlist.entity.AcctListEntity;
import weixin.acctlist.entity.WeixinacctListEntity;
import weixin.acctlist.service.WeixinacctListServiceI;
import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.PageAuthCallback;
import weixin.guanjia.account.service.PageAuthHandler;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.account.util.CheckPic;
import weixin.liuliangbao.jsonbean.ShareItem;
import weixin.member.entity.WeixinMemberEntity;
import weixin.member.service.WeixinMemberServiceI;
import weixin.source.controller.WeixinSourceController;
import weixin.tenant.entity.WeixinAcctEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by aa on 2016/6/12.
 */
@Scope("prototype")
@Controller
@RequestMapping("/acctListController")
public class AcctListController extends BaseController implements PageAuthHandler {
    private static final Logger LOGGER = Logger.getLogger(AcctListController.class);
    @Autowired
    private WeixinacctListServiceI weixinacctListServiceI;
    @Autowired
    private SystemService systemService;
    @Autowired
    private WeixinAccountServiceI weixinAccountService;
    @Autowired
    private WeixinMemberServiceI weixinMemberService;
    
    String message="";

    /*添加页面跳转*/
    @RequestMapping(params = "addAcctList")
    public ModelAndView addAcctList(HttpServletRequest request) {

        return new ModelAndView("weixin/acctlist/addAcctList");
    }

    //文件保存路径的前缀 add by mike
//    private static String filePathPrefix = null;

    //文件保存url的前缀 add by mike
//    private static String fileUrlPrefix = null;

    //工具方法:从配置文件mediaFile.properties中,读取文件保存路径的前缀,比如:/Users/zhixu/Desktop/TEST_MEDIA
//    public static String getFilePathPrefix() {
//        if (filePathPrefix == null) {
//            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("mediaFile.properties");
//            Properties p = new Properties();
//            try {
//                p.load(is);
//                filePathPrefix = p.getProperty("media.path.prefix");
//                is.close();//关闭
//                is = null;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return filePathPrefix;
//    }

    //工具方法:从配置文件mediaFile.properties中,读取文件url的前缀,比如:http://localhost/xampp/images
//    public static String getFileUrlPrefix() {
//        if (fileUrlPrefix == null) {
//            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("mediaFile.properties");
//            Properties p = new Properties();
//            try {
//                p.load(is);
//                fileUrlPrefix = p.getProperty("media.url.prefix");
//                is.close();//关闭
//                is = null;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return fileUrlPrefix;
//    }

    //商家列表上传图片
    @RequestMapping(params = "uploadAcctPicture", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson uploadAcctPicture(MultipartHttpServletRequest request, HttpServletResponse response)
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
    public AjaxJson doAdd(WeixinacctListEntity weixinacctListEntity,HttpServletRequest request){
        AjaxJson j =new AjaxJson();
        long starttime =System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        builder.append("AcctListController_doAdd");
        try{
            String acctlistName=request.getParameter("acctListName");
            if(StringUtils.isBlank(acctlistName)){
                j.setMsg("名称不能为空");
                j.setSuccess(false);
                return j;
            }
            TSUser user= ResourceUtil.getSessionUserName();
            String acctid=user.getTenantId();
            //String acctId2 = weixinacctListEntity.getAcctId();
            String hql ="from WeixinacctListEntity where acctId=?";
            List<WeixinacctListEntity> weixinacctList = this.systemService.findHql(hql,acctid);
            if(weixinacctList.size() !=0){
            	 j.setMsg("每个商户只能添加一条记录");
                 j.setSuccess(false);
                 return j;
            }
            String point = request.getParameter("point");
            String coordinates = request.getParameter("coordinates");
            String shoppAddress = request.getParameter("shoppAddress");
            String imageFilePath= request.getParameter("imagePath");
            String imageRelativeUrl=request.getParameter("imageRelativeUrl");
            String address=request.getParameter("address");
            String phone=request.getParameter("phone");
            String description=request.getParameter("description");
            String addressPicUrl=request.getParameter("imageRelativeUrl1");

            
            String loginUserName=user.getUserName();
            LOGGER.info(loginUserName);
           
            builder.append("acctList_"+acctlistName+"imageFilePath"+imageFilePath);

            weixinacctListEntity.setId(UUID.randomUUID().toString());
            weixinacctListEntity.setPhone(phone);
            weixinacctListEntity.setAcctId(acctid);
            weixinacctListEntity.setAcctlistName(acctlistName);
            weixinacctListEntity.setPictureId(UUID.randomUUID().toString());
            weixinacctListEntity.setPictureUrl(imageRelativeUrl);
            weixinacctListEntity.setAddress(address);
            weixinacctListEntity.setAddressPicUrl(addressPicUrl);
            weixinacctListEntity.setDescription(description);
            weixinacctListEntity.setOperator(loginUserName);
            weixinacctListEntity.setOperateTime(new Date());
            weixinacctListEntity.setShoppAddress(shoppAddress);
            weixinacctListEntity.setCoordinates(coordinates);
            weixinacctListEntity.setPoint(point);
            weixinacctListServiceI.save(weixinacctListEntity);
            message="商家信息添加成功";
            systemService.addLog(message, Globals.Log_Type_INSERT,Globals.Log_Leavel_INFO);
        }catch (Exception e){
            e.printStackTrace();
            message="商家信息添加失败";
            throw new BusinessException(e.getMessage());
        }
        long runTime =System.currentTimeMillis()-starttime;
        LOGGER.info("runTime_"+runTime+"operator_"+builder.toString()+message);
        j.setMsg(message);
        return j;
    }

    /*编辑页面跳转*/
    @RequestMapping(params="goEdit")
    public ModelAndView goEdit(WeixinacctListEntity weixinacctList,HttpServletRequest req){
        if(StringUtil.isNotEmpty(weixinacctList.getId())){
            weixinacctList=weixinacctListServiceI.getEntity(WeixinacctListEntity.class,weixinacctList.getId());
            weixinacctList.setPictureUrl(ResourceUtil.getMediaUrlPrefix() + "/" + weixinacctList.getPictureUrl());
            weixinacctList.setAddressPicUrl(ResourceUtil.getMediaUrlPrefix()+"/" + weixinacctList.getAddressPicUrl());
            req.setAttribute("weixinAcctList",weixinacctList);
        }
        return new ModelAndView("weixin/acctlist/editAcctList");
    }

    /*编辑*/
    @RequestMapping(params="doEdit")
    @ResponseBody
    public AjaxJson doEdit(WeixinacctListEntity weixinacctListEntity,HttpServletRequest req){
        AjaxJson j = new AjaxJson();
        long startTime= System.currentTimeMillis();
        StringBuilder builder =new StringBuilder();
        builder.append("AcctListController_doEdit");
        try{
            String acctlistName =req.getParameter("acctlistName");
            if(StringUtils.isBlank(acctlistName)){
                j.setMsg("名称不能为空");
                j.setSuccess(false);
                return j;
            }
            String coordinates=req.getParameter("coordinates");
            String acctid=req.getParameter("acctid");
            LOGGER.info(acctid);
            String imageFilePath= req.getParameter("imagePath");

            String imageRelativeUrl=req.getParameter("imageRelativeUrl");
            /** ----------高庆佳 2016年8月24日09:22-----------------*/
            if(imageRelativeUrl.contains(ResourceUtil.getMediaUrlPrefix())){ //判断二维码的相对地址是否包含域名
            	imageRelativeUrl = imageRelativeUrl.substring(imageRelativeUrl.lastIndexOf("/")+1);
            }
            String reduceUrl=ResourceUtil.getMediaUrlPrefix()+"/";
            String finalImageUrl=imageRelativeUrl.replace(reduceUrl,"");
        
            LOGGER.info(finalImageUrl);

            String address=req.getParameter("address");
            String phone=req.getParameter("phone");
            String description=req.getParameter("description");

        
            String replaceUrl=ResourceUtil.getMediaUrlPrefix()+"/";
         

            String id=req.getParameter("id");
            TSUser user= ResourceUtil.getSessionUserName();
            String loginUserName=user.getUserName();
            LOGGER.info(loginUserName);

            weixinacctListEntity.setId(id);
            weixinacctListEntity.setAcctlistName(acctlistName);
            weixinacctListEntity.setPhone(phone);
            weixinacctListEntity.setAcctId(acctid);
            weixinacctListEntity.setAcctlistName(acctlistName);
            weixinacctListEntity.setPictureId(UUID.randomUUID().toString());
            weixinacctListEntity.setPictureUrl(imageRelativeUrl);
            weixinacctListEntity.setPoint(address);
            weixinacctListEntity.setAddressPicId(UUID.randomUUID().toString().replace("-",""));
         
            weixinacctListEntity.setDescription(description);
            weixinacctListEntity.setOperator(loginUserName);
            weixinacctListEntity.setOperateTime(new Date());
            weixinacctListEntity.setCoordinates(coordinates);
            weixinacctListServiceI.saveOrUpdate(weixinacctListEntity);
            message="商家信息编辑成功";
            systemService.addLog(message,Globals.Log_Type_INSERT,Globals.Log_Leavel_INFO);
        }catch (Exception e){
            message="商家信息编辑失败";
            e.printStackTrace();
        }
        long runTime =System.currentTimeMillis()-startTime;
        LOGGER.info("runTime_"+runTime+"operator_"+builder.toString()+message);
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params="acctList")
    public ModelAndView acctList(HttpServletRequest req){
        return new ModelAndView("weixin/acctlist/AcctList");
    }

    /*是否添加商家信息*/
    public AjaxJson isOneAcct(HttpServletRequest req){
        String acctId= ResourceUtil.getSessionUserName().getTenantId();
        AjaxJson j = new AjaxJson();
        WeixinacctListEntity weixinacctList= this.systemService.get(WeixinacctListEntity.class,acctId);

        if(weixinacctList.getId()!=null && weixinacctList.getId()!=""){
            j.setMsg("该商户已经添加，请去修改！");
        }
        return j;
    }

    @RequestMapping(params = "datagrid")
    public void datagrid(WeixinacctListEntity weixinacctList,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
        CriteriaQuery cq = new CriteriaQuery(WeixinacctListEntity.class, dataGrid);
        //查询条件组装器
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, weixinacctList, request.getParameterMap());
        try{
            cq.eq("acctId", ResourceUtil.getSessionUserName().getTenantId());
            //自定义追加查询条件
        }catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        cq.add();
        this.weixinacctListServiceI.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }
    //商家列表页
    @RequestMapping(params = "weixinacctList")
    public ModelAndView weixinacctList(HttpServletRequest request, HttpServletResponse response) {
        long startTime = System.currentTimeMillis();//获取开始当前的时间
        StringBuffer buffer = new StringBuffer();
        try {
            buffer.append("AcctListController_startLoad_begin:_");
            String accountid = request.getParameter("accountid");
            String openId = request.getParameter("openId");
            buffer.append("accountid_" + accountid + "_openId_" + openId);
            LOGGER.info("商户列表页startLoad方法开始" + accountid + "__openid__" + openId);
            Map<String, String> properties = new HashMap<String, String>();
            properties.put("accountid", accountid);  //活动ID，传给后面用
            String url = "";
            if (StringUtils.isBlank(openId)) {
                url = weixinAccountService.pageAuth2(accountid, properties, this);   //调用授权封装：商户ID，
            } else {
                url = weixinAccountService.pageAuth2(accountid, properties, this, openId);
            }
//        String url=weixinAccountService.pageAuth(accountid,properties,this);   //调用授权封装：商户ID，
            buffer.append("_url_" + url);
            long endTime = System.currentTimeMillis();//获取结束的当前时间
            buffer.append("AcctListController_startLoad_end_time:" + (endTime - startTime) + "ms");
            LOGGER.info(buffer.toString());
            LOGGER.info("商户列表页startLoad方法运行时间：----" + (endTime - startTime) + "ms");
            return new ModelAndView("redirect:" + url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();//获取结束的当前时间
        buffer.append("AcctListController_startLoad_end_time:" + (endTime - startTime) + "ms");
        LOGGER.info(buffer.toString());
        return new ModelAndView("/common/404");
    }
    

	@Override
	public ModelAndView followAndBind(PageAuthCallback msg, HttpServletRequest request) throws Exception {
		return this.follow(msg, request);
	}

	@Override
	public ModelAndView follow(PageAuthCallback msg, HttpServletRequest request) throws Exception {
		String accountId = msg.getAccountId();
		String openId = msg.getOpenId();
		WeixinMemberEntity weixinMember = this.weixinMemberService.getWeixinMember(openId, accountId);
		String pho = weixinMember.getPhoneNumber();
		String phoneNumber = org.apache.commons.lang.StringUtils.isBlank(pho)?null : pho.substring(0,7);
//		List<Map<String,Object>> cityNames = this.systemService.findForJdbc("select cityName from phonelocation where beginNumber=?", StringUtils.left(phoneNumber, 7));
//		String cityName = null;
//		if (!CollectionUtils.isEmpty(cityNames)) {
//			cityName = (String) cityNames.get(0).get("cityName");
//		}
        Long start = System.currentTimeMillis();  //方法开始时间
        StringBuffer sb = new StringBuffer();
        sb.append("AcctListController.weixinacctList():");
       
        try{
        	//String hql="select a.*, (case when a.provincename like (select CONCAT(cityName, '%') from phonelocation where beginNumber =? limit 1) then 1 else 0 end) coverArea from weixin_accountlist a WHERE (a.acctLevel = '3' and businessType!=(select businessType from weixin_accountlist where accountid=?)) or(accountid=?) or (a.acctLevel = 2) or(a.acctLevel = 1) ORDER By coverArea desc,  a.isPlay desc,a.focusflow desc";
        	String hql="from AccountListEntity where accountid=?";
        	
        	List<AccountListEntity> accountListEntity = this.systemService.findHql(hql, accountId);
        	
        	if(accountListEntity.get(0).getAcctLevel().equals("3")){
        		String hql1="select a.*,(case when a.provincename like (select CONCAT(cityName, '%') from phonelocation where beginNumber =? limit 1) then 1 else 0 end) coverArea from weixin_accountlist a where acctLevel =3 and businessType !=(select businessType from weixin_accountlist where accountid=?) or acctLevel = 2 AND businessType != (SELECT businessType FROM weixin_accountlist WHERE accountid = ?) or accountid=? or acctLevel =1 ORDER By coverArea desc,  a.isPlay desc,a.focusflow desc";
        		List<Map<String, Object>> findForJdbc = this.systemService.findForJdbc(hql1,phoneNumber,accountId,accountId,accountId);
        		request.setAttribute("listAcct", findForJdbc);
        	}else if(accountListEntity.get(0).getAcctLevel().equals("2")){
        		String hql1="select a.*,(case when a.provincename like (select CONCAT(cityName, '%') from phonelocation where beginNumber =? limit 1) then 1 else 0 end) coverArea from weixin_accountlist a where acctLevel =3 and businessType !=(select businessType from weixin_accountlist where accountid=?) or acctLevel = 2 AND businessType != (SELECT businessType FROM weixin_accountlist WHERE accountid = ?) or accountid=? or acctLevel =1 ORDER By coverArea desc,  a.isPlay desc,a.focusflow desc";
        		List<Map<String, Object>> findForJdbc = this.systemService.findForJdbc(hql1,phoneNumber,accountId,accountId,accountId);
        		request.setAttribute("listAcct", findForJdbc);
        		
        	}else{
        		String hql1="select a.*,(case when a.provincename like (select CONCAT(cityName, '%') from phonelocation where beginNumber =? limit 1) then 1 else 0 end) coverArea from weixin_accountlist a ORDER By coverArea desc,  a.isPlay desc,a.focusflow desc";
        		List<Map<String, Object>> findForJdbc = this.systemService.findForJdbc(hql1,phoneNumber);
        		request.setAttribute("listAcct", findForJdbc);
        	}
        	
        	request.setAttribute("imgpath",ResourceUtil.getMediaUrlPrefix()+"/");
            return new ModelAndView("weixin/acctlist/weixinAcctList");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Long end = System.currentTimeMillis();
            sb.append(", 方法耗时:" + (end - start) + "ms");
            LOGGER.info(sb.toString());
        }
        return new ModelAndView("/common/404");
    }


    

    /*商家详情界面*/
    @RequestMapping(params="showAcct")
    @DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    public ModelAndView showAcct(AcctListEntity acctListEntity,HttpServletRequest req){
        String acctId=acctListEntity.getAcctId();  // 商家ID
        String accountid=acctListEntity.getAccountid();  //  公共号ID
        StringBuffer hql= new StringBuffer();
        StringBuffer hql2= new StringBuffer();
        StringBuffer hql3= new StringBuffer();
        StringBuffer hql4=new StringBuffer();
        /** 高庆佳2016年8月26日 */
        StringBuffer hql5 = new StringBuffer(); //查询二维码用
        try{
        	hql2.append("from AccountListEntity where acctId =?");
        	List<AccountListEntity> AccountListEntity = this.systemService.findHql(hql2.toString(),acctId);
            hql3.append("from AccountListEntity where acctId=?");
            List<AccountListEntity> AccountList = this.systemService.findHql(hql3.toString(),acctId);
           
            hql4.append("from ShareItem where accountid=?");
            List<ShareItem> shareItem = this.systemService.findHql(hql4.toString(),accountid);
            /** 高庆佳2016年8月26日  去微信公众号获取二维码*/
            hql5.append("from WeixinAccountEntity where id=?");
            List<WeixinAccountEntity> weixinAccountList = this.systemService.findHql(hql5.toString(),accountid);
//            System.out.println("----------------->"+weixinAccountList.toString()+acctId+"----------------"+accountid+"<-----------------");
            
            hql.append("from WeixinacctListEntity where acctId ='");
            hql.append(acctId+"'");
            HqlQuery hqlQuery = new HqlQuery(hql.toString(),null);
            List<WeixinacctListEntity> weixinacctListEntityList = this.systemService.findHql(hql.toString(),null);
            if(weixinacctListEntityList==null || weixinacctListEntityList.isEmpty()){
            	WeixinAcctEntity acct = this.systemService.get(WeixinAcctEntity.class, acctId);
            	acctListEntity.setPictureUrl("");
            	if(shareItem !=null && shareItem.size()>0){
            	acctListEntity.setShareId(shareItem.get(0).getId());
            	}
            	acctListEntity.setIsPlay(AccountListEntity.get(0).getIsPlay());
            	acctListEntity.setAccountname(AccountList.get(0).getAcctlistName());
            	acctListEntity.setShareflow(AccountListEntity.get(0).getShareflow());
            	acctListEntity.setSignflow(AccountListEntity.get(0).getSignflow());
            	acctListEntity.setFocusflow(AccountListEntity.get(0).getFocusflow());
            	/** 高庆佳2016年8月26日 */
            	acctListEntity.setAccounttype(weixinAccountList.get(0).getAccounttype()); //公众号的类型 
//            	acctListEntity.setQRcode(ResourceUtil.getMediaUrlPrefix() + "/" +weixinAccountList.get(0).getQRcode()); //公众号的二维码
 
            }else {
            	if(shareItem !=null && shareItem.size()>0){
            	acctListEntity.setShareId(shareItem.get(0).getId());
            	}
            	acctListEntity.setIsPlay(AccountListEntity.get(0).getIsPlay());
            	acctListEntity.setPoint(weixinacctListEntityList.get(0).getPoint());
            	acctListEntity.setAcctlistName(weixinacctListEntityList.get(0).getAcctlistName());
            	acctListEntity.setAddress(weixinacctListEntityList.get(0).getAddress());
            	acctListEntity.setPhone(weixinacctListEntityList.get(0).getPhone());
            	acctListEntity.setDescription(weixinacctListEntityList.get(0).getDescription());
            	acctListEntity.setPictureUrl(ResourceUtil.getMediaUrlPrefix() + "/" + weixinacctListEntityList.get(0).getPictureUrl());
            	acctListEntity.setAddressPicUrl(ResourceUtil.getMediaUrlPrefix() + "/" + weixinacctListEntityList.get(0).getAddressPicUrl());
            	acctListEntity.setShoppAddress(weixinacctListEntityList.get(0).getShoppAddress());
            	acctListEntity.setShareflow(AccountListEntity.get(0).getShareflow());
            	acctListEntity.setSignflow(AccountListEntity.get(0).getSignflow());
            	acctListEntity.setFocusflow(AccountListEntity.get(0).getFocusflow());
            	/** 高庆佳2016年8月26日 */
            	acctListEntity.setAccounttype(weixinAccountList.get(0).getAccounttype()); //公众号的类型 
//            	acctListEntity.setQRcode(ResourceUtil.getMediaUrlPrefix() + "/" +weixinAccountList.get(0).getQRcode()); //公众号的二维码
//            	System.out.println("----------------->公众号的类型:"+ weixinAccountList.get(0).getAccounttype() +
//            			"公众号的二维码:"+ResourceUtil.getMediaUrlPrefix() + "/" +weixinAccountList.get(0).getQRcode()+"<-----------------");
            }
            //acctListEntity.setAccountid(accountid);
            req.setAttribute("acctListEntity", acctListEntity);
            
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ModelAndView("weixin/acctlist/details");
    }
    /**
     * 	 高庆佳2016年8月26日  跳转商家二维码页面
     * @param req
     * @param acctListEntity
     * @return
     */
    @RequestMapping(params="goAcctQRcode")
    public ModelAndView goAcctQRcode(AcctListEntity acctListEntity,HttpServletRequest req){
    	String accountid = req.getParameter("accountid");
        StringBuffer hql = new StringBuffer();
        hql.append("from WeixinAccountEntity where id=?");
        List<WeixinAccountEntity> weixinAccountList = this.systemService.findHql(hql.toString(),accountid);
        String qrcode = weixinAccountList.get(0).getQRcode();
        String accountLogo = weixinAccountList.get(0).getLogoAccount();
        //根据二维码的格式选择链接
        if(CheckPic.checkImg(qrcode)){
        	qrcode = ResourceUtil.getMediaUrlPrefix() + "/" +qrcode;
        }
        if(CheckPic.checkImg(accountLogo)){
        	accountLogo = ResourceUtil.getMediaUrlPrefix() + "/" +accountLogo;
        }
        acctListEntity.setQRcode(qrcode);
        acctListEntity.setAccountname(weixinAccountList.get(0).getAccountname());
        acctListEntity.setAccountLogo(accountLogo);
//       	System.out.println("公众号的二维码:"+ResourceUtil.getMediaUrlPrefix() + "/" +weixinAccountList.get(0).getQRcode()+"<-----------------");
        req.setAttribute("acctListEntity", acctListEntity);
    	return new ModelAndView("weixin/acctlist/QRcode");
    }
    /*地址栏*/
    public ModelAndView showAcctAddress(AcctListEntity acctListEntity,HttpServletRequest req){
        if(acctListEntity.getAcctId() !=null) {
            req.setAttribute("acctAddress", acctListEntity);
        }
        return new ModelAndView("weixin/acctlist/weixinAcctAddress");
    }

}
