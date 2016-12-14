package weixin.weicar.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import weixin.weicar.entity.CarIndexManagerEntity;
import weixin.weicar.entity.CarOrderEntity;
import weixin.weicar.entity.CarOrderSettingEntity;
import weixin.weicar.entity.CarSeriesEntity;
import weixin.weicar.entity.CarVehicleTypeEntity;
import weixin.weicar.service.CarIndexManagerServiceI;
import weixin.weicar.service.CarOrderServiceI;
import weixin.weicar.service.CarOrderSettingServiceI;
import weixin.weicar.service.CarSeriesServiceI;
import weixin.weicar.service.CarVehicleTypeServiceI;

import com.alibaba.fastjson.JSON;

/**
 * @Title: Controller
 * @Description: 汽车首页
 * @author onlineGenerator
 * @date 2015-05-26 20:40:01
 * @version V1.0
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/car")
public class CarController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CarController.class);

	@Autowired
	private CarIndexManagerServiceI carIndexManagerService;
	@Autowired
	private CarOrderSettingServiceI carOrderSettingService;

	@Autowired
	private CarOrderServiceI carOrderService;
	@Autowired
	private CarSeriesServiceI carSeriesService;
	@Autowired
	private CarVehicleTypeServiceI carVehicleTypeService;

	@Autowired
	private SystemService systemService;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@RequestMapping(params = "index")
	public ModelAndView index(CarIndexManagerEntity carIndexManager,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(carIndexManager.getId())) {
			carIndexManager = carIndexManagerService.getEntity(
					CarIndexManagerEntity.class, carIndexManager.getId());
			req.setAttribute("carIndexManagerPage", carIndexManager);
		}
		String wxPrimaryKey = req.getParameter("accountid");
		HttpSession session = req.getSession();
		session.setAttribute("WX", wxPrimaryKey);
		String wechatid = req.getParameter("wechatid");
		if (StringUtils.isBlank(wechatid)) {
			wechatid = System.currentTimeMillis() + "";
		}
		session.setAttribute("wechatid", wechatid);
		return new ModelAndView("weixin/weicar/page/index");
	}

	/**
	 * 试驾
	 * 
	 * @param carIndexManager
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "carTry")
	public ModelAndView carTry(CarIndexManagerEntity carIndexManager,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(carIndexManager.getId())) {
			carIndexManager = carIndexManagerService.getEntity(
					CarIndexManagerEntity.class, carIndexManager.getId());
			req.setAttribute("carIndexManagerPage", carIndexManager);
		}
		HttpSession session = req.getSession();
		if (null == session.getAttribute("WX")) {
			return new ModelAndView("weixin/weicar/page/index");
		}
		String acountKey = (String) session.getAttribute("WX");
		CarOrderSettingEntity entity = new CarOrderSettingEntity();
		entity.setAccountid(acountKey);
		entity.setOrderType("10");
		CarOrderSettingEntity returnEntity = carOrderSettingService
				.getCarOrderSettingByParam(entity);
		if (returnEntity == null) {

		}
		CarSeriesEntity seriesEntity = new CarSeriesEntity();
		seriesEntity.setAccountid((String) session.getAttribute("WX"));
		List<CarSeriesEntity> list = carSeriesService
				.getCarSeriesByAccId(seriesEntity);

		if (session.getAttribute("wechatid") != null)
		{
			CarOrderEntity orderEntity = new CarOrderEntity();
			orderEntity.setCarOrderSettingEntity(entity);
			orderEntity.setOpenId((String) session.getAttribute("wechatid"));
			Long count = carOrderService.getCarOrderCount(orderEntity);
			req.setAttribute("count", count);
		}
		req.setAttribute("carOrderSetting", returnEntity);
		req.setAttribute("carSeriesList", list);
		return new ModelAndView("weixin/weicar/page/car_try");
	}

	@RequestMapping(params = "carSeriesList")
	public ModelAndView carSeriesList(String CarOrderSettingId,
			HttpServletRequest req) {
		HttpSession session=req.getSession();
		if (null == session.getAttribute("WX")) {
			return new ModelAndView("weixin/weicar/page/index");
		}
		CarSeriesEntity seriesEntity = new CarSeriesEntity();
		seriesEntity.setAccountid((String) session.getAttribute("WX"));
		List<CarSeriesEntity> list = carSeriesService
				.getCarSeriesByAccId(seriesEntity);
		req.setAttribute("carSeriesList", list);
		return new ModelAndView("weixin/weicar/page/car_list");
	}
	
	/**
	 * 
	 * 根据车系ID列出车型
	 * @param carSeriesId
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "carVehicleTypeList")
	public ModelAndView carVehicleTypeList(String carSeriesId,
			HttpServletRequest req) {
		List<CarVehicleTypeEntity> list = carVehicleTypeService.findByProperty(CarVehicleTypeEntity.class, "carSeriesEntity.id", carSeriesId);
		req.setAttribute("carVehicleTypeList", list);
		return new ModelAndView("weixin/weicar/page/car_type_list");
	}
	
	@RequestMapping(params = "carTryList")
	public ModelAndView carTryList(String CarOrderSettingId,
			HttpServletRequest req) {
		if (StringUtil.isEmpty(CarOrderSettingId)) {
			return new ModelAndView("weixin/weicar/page/index");
		}
		CarOrderSettingEntity entity = carOrderSettingService.get(
				CarOrderSettingEntity.class, CarOrderSettingId);
		if (entity == null) {
			return new ModelAndView("weixin/weicar/page/index");
		}
		req.setAttribute("carOrderSetting", entity);
		HttpSession session = req.getSession();
		if (session.getAttribute("wechatid") != null) {
			CarOrderEntity carOrder = new CarOrderEntity();
			carOrder.setCarOrderSettingEntity(entity);
			carOrder.setOpenId((String) session.getAttribute("wechatid"));
			List<CarOrderEntity> list = carOrderService
					.getCarOrderByParam(carOrder);
			req.setAttribute("carOrderList", list);
		}
		return new ModelAndView("weixin/weicar/page/car_try_list");
	}

	/**
	 * 获取车型
	 * 
	 * @param seriesId
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "getCarVehicleTypeJson")
	@ResponseBody
	public AjaxJson getCarSeriesJson(String seriesId, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		Map<String, Object> params = new HashMap<String, Object>();
		if (StringUtils.isBlank(seriesId)) {
			j.setSuccess(false);
			j.setMsg("缺少关键字");
			return j;
		}
		List<CarVehicleTypeEntity> carVehicleTypeEntityList = carVehicleTypeService
				.findByProperty(CarVehicleTypeEntity.class,
						"carSeriesEntity.id", seriesId);
		params.put("data", JSON.toJSONString(carVehicleTypeEntityList));
		j.setAttributes(params);
		j.setMsg(message);
		return j;
	}

	@RequestMapping(params = "saveCarTry")
	@ResponseBody
	public AjaxJson saveCarTry(CarOrderEntity carOrderEntity,
			HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		HttpSession session=req.getSession();
		carOrderEntity.setOpenId((String)session.getAttribute("wechatid"));
		carOrderService.save(carOrderEntity);
		return j;
	}

	@RequestMapping(params = "carTools")
	public ModelAndView carTools(CarIndexManagerEntity carIndexManager,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(carIndexManager.getId())) {
			carIndexManager = carIndexManagerService.getEntity(
					CarIndexManagerEntity.class, carIndexManager.getId());
			req.setAttribute("carIndexManagerPage", carIndexManager);
		}
		String wxPrimaryKey = req.getParameter("accountid");
		HttpSession session = req.getSession();
		if (null == session.getAttribute("WX")) {
			session.setAttribute("WX", wxPrimaryKey);
		}

		return new ModelAndView("weixin/weicar/page/car_tools");
	}

}
