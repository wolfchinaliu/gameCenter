package weixin.advertisement.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.core.annotation.DataSourceSwitch;
import org.jeecgframework.core.aop.DataSourceSwitcher;
import org.jeecgframework.core.extend.datasource.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import weixin.advertisement.entity.WeixinAdMaterialEntity;
import weixin.advertisement.service.MaterialServiceI;

@Controller
@RequestMapping("/adAction")
@SuppressWarnings("all")
public class AdAction {
    @Autowired
    private MaterialServiceI materialService;

    @RequestMapping(params = "adDetail")
    @DataSourceSwitch(dataSource=DataSourceType.dataSource_slave)
    public ModelAndView adDetail(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        WeixinAdMaterialEntity entity = this.materialService.getEntity(WeixinAdMaterialEntity.class, id);
        ModelAndView mv = new ModelAndView("weixin/advertisement/ad-view");
        mv.addObject("entity", entity);
        return mv;
    }
}
