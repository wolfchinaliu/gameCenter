package weixin.idea.extend.function.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.ResourceUtil;

import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.entity.message.resp.Article;
import weixin.guanjia.core.entity.message.resp.NewsMessageResp;
import weixin.guanjia.core.entity.message.resp.TextMessageResp;
import weixin.guanjia.core.util.MessageUtil;
import weixin.idea.extend.function.KeyServiceI;

public class DaZhuanPanService implements KeyServiceI {

	/**
	 * 大转盘链接地址
	 */
	@Override
	public String excute(String content, TextMessageResp defaultMessage,
			HttpServletRequest request) {
		WeixinAccountServiceI weixinAccountService = (WeixinAccountServiceI) ApplicationContextUtil.getContext().getBean("weixinAccountService");
		String accountid = weixinAccountService.findByToUsername(defaultMessage.getFromUserName()).getId();
		String hdid=request.getParameter("hdid");
		List<Article> articleList = new ArrayList<Article>();
		Article article = new Article();
		article.setTitle("大转盘");
		article.setDescription("大转盘抽奖咯");
		article.setPicUrl(ResourceUtil.getDomain() + "/plug-in/weixin/images/activity-lottery-1.png");//获取配置文件配置的域名
		article.setUrl(ResourceUtil.getDomain()
				+ "/lotteryController.do?goZhuanpan&hdid="+hdid+"&openId="+defaultMessage.getToUserName());
		articleList.add(article);
		NewsMessageResp newsMessage = new NewsMessageResp();
		newsMessage.setToUserName(defaultMessage.getToUserName());
		newsMessage.setFromUserName(defaultMessage.getFromUserName());
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		newsMessage.setArticleCount(articleList.size());
		newsMessage.setArticles(articleList);
		return MessageUtil.newsMessageToXml(newsMessage);
	}

	@Override
	public String getKey() {
		return "大转盘";
	}

}
