package weixin.liuliangbao.weigatedoor.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import weixin.liuliangbao.weigatedoor.entity.WeidoorpptEntity;
import weixin.liuliangbao.weigatedoor.service.WeidoorpptServiceI;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xudan on 2015/12/29.
 */
@Controller
@RequestMapping("/pptjumpPageController")
public class PPTjumpPageController {
    private static final Logger logger = Logger.getLogger(WeiDoorController.class);
    @Autowired
    private WeidoorpptServiceI weidoorpptService;

    @RequestMapping(params = "goPPTJumpPage")
    public ModelAndView goPPTJumpPage(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        builder.append("PPTjumpPageController_goPPTJumpPage");

        String id = request.getParameter("pptid");
        WeidoorpptEntity doorPPT=this.weidoorpptService.getEntity(WeidoorpptEntity.class,id);
        request.setAttribute("doorPPT", doorPPT);

        builder.append("id_"+id);

        long runTime=System.currentTimeMillis()-startTime;
        logger.info("runTime"+runTime+builder.toString());

        return new ModelAndView("liuliangbao/weigatedoor/pptJumpPage");

    }


}
