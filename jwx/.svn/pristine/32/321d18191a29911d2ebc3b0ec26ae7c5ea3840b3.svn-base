package weixin.acctlist.controller;





import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import weixin.acctlist.entity.AddressEntity;
import weixin.acctlist.entity.WeixinacctListEntity;


@Controller
@RequestMapping("/addressController")
public class AddressController {
    public static final transient Logger LOGGER = Logger.getLogger(AddressController.class);

    @Autowired
    private SystemService systemService;

	@RequestMapping(params="save")
	public ModelAndView save(AddressEntity addressEntity,HttpServletResponse response,ModelMap modelMap){
		
		ModelAndView modelAndView = new ModelAndView();
		String address = addressEntity.getAddress();
		 String[] subAddress = address.split(":");	
		 String point = subAddress[0];
		 String addr = subAddress[1];
		 modelMap.put("point", point);
		 modelMap.put("addr", addr);
		modelAndView.addObject("address", modelMap);
		modelAndView.setViewName("weixin/acctlist/addAcctList");
		LOGGER.info(address);
		
		return modelAndView;
	}
	
	
	
	@RequestMapping(params="viewMap")
	public ModelAndView viewMap(HttpServletRequest request,ModelMap modelMap){
		String acctId= request.getParameter("acctId");
		StringBuffer hql= new StringBuffer();
		hql.append("from WeixinacctListEntity where acctId =?");
		List<WeixinacctListEntity> weixinacctListEntity = this.systemService.findHql(hql.toString(),acctId);
		
		WeixinacctListEntity acctListEntity = weixinacctListEntity.get(0);
		String coordinates = acctListEntity.getCoordinates();
		String address =  acctListEntity.getPoint();
		/*String coordinate = coordinates.substring(0, 19);*/
		String[] coor = coordinates.split(",");
		String lng = coor[0];
		String lat = coor[1];
		modelMap.put("address", address);
		modelMap.put("lng", lng);
		modelMap.put("lat", lat);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("coordinate", modelMap);
		request.getSession().setAttribute("acctListEntity", acctListEntity);
		modelAndView.setViewName("weixin/acctlist/AddressMap");
		return modelAndView;
		
	}
}
