package weixin.liuliangbao.controller.flowcontroller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.util.ResourceUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import weixin.liuliangbao.jsonbean.MerchantBean;
import weixin.liuliangbao.util.HttpUtil;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;

/**
 * Created by xiaochun on 2015/11/24.
 */
@Scope("prototype")
@Controller
@RequestMapping("/flowController")
public class FlowController  extends BaseController {
    public static final transient Logger LOGGER = Logger.getLogger(FlowController.class);
    private String path= ResourceUtil.getConfigByName("jfinalUrl-config");
    @RequestMapping(params = "myflow")
    public ModelAndView myflow(HttpServletRequest request) {



//        String url = "http://localhost:18082/jwx/merchant/AllMerchant";
        String url=path+"merchant/AllMerchant";
        String content= HttpUtil.httpGet(url).toString();
//        String content = httpUtil.h(url, HttpUtil.HTTP_TYPE.GET,null);
        LOGGER.info(content);

        Gson gson = new Gson();
        Type type = new TypeToken<MerchantBean>(){}.getType();
        MerchantBean testJson = gson.fromJson(content,type);
        LOGGER.info(gson.toJson(testJson));

        LOGGER.info("------------------------"+testJson.getData().get(0).getId()+"----------------------------");
        ModelAndView mav = new ModelAndView();
        mav.addObject("id",testJson.getData().get(0).getId());

        mav.setViewName("liuliangbao/flowmanager/myflow");
        return mav;
    }
}
