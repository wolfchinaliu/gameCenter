package weixin.guanjia.account.service;

import org.springframework.web.servlet.ModelAndView;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

public interface PageAuthHandler extends Serializable {

    ModelAndView followAndBind(PageAuthCallback msg, HttpServletRequest request) throws Exception;

    // 唯一不同的是, 这两个方法在结束之后跳转的页面地址不同
    ModelAndView follow(PageAuthCallback msg, HttpServletRequest request) throws Exception;

}


