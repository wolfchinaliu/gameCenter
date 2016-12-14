package test;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;
import weixin.activity.job.WeixinActivityJob;

/**
 * @Auth popl
 * @Date 2016年8月23日 下午1:46:13
 * @authEmail popl_lu@sian.cn
 * @CalssName test.JobTest
 * @dec 
 */
@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration({"/dev/spring-mvc-context.xml","/dev/spring-mvc-hibernate.xml","/dev/spring-mvc-aop.xml","/dev/spring-mvc-cgform.xml","/dev/spring-minidao.xml"})
public class JobTest {
	@Resource
	private WeixinActivityJob weixinActivityJob;
	
	@Test
	public void startJob(){
		weixinActivityJob.beginDayRules();
	}
}
