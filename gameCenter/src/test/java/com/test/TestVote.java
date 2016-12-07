package com.test;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shiliu.game.domain.ExcelUser;
import com.shiliu.game.domain.Vote;
import com.shiliu.game.domain.VoteOption;
import com.shiliu.game.service.IExcelUserService;
import com.shiliu.game.service.IVoteService;
import com.shiliu.game.utils.StringUtils;

/**
 * @Auth popl_lu
 * @Date 2016年7月14日 下午4:48:55
 * @authEmail popl_lu@sian.cn
 * @CalssName com.test.TestVote
 * @dec 
 */
@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration({"/applicationContext.xml"})
public class TestVote {

	@Resource
	private IExcelUserService excelUserService;
	@Test
	public void inser(){
		/*Vote vote = new Vote();
		vote.setVoteId("test--123");
		vote.setAcctId("acctid");
		vote.setCreateTime(new Date());
		vote.setFlowType("provincial");
		vote.setFrequency(0);
		vote.setText("这只是个投票测试");
		vote.setTimes(1);
		vote.setType("vote");
		vote.setVoteFlow(5);
		vote.setVoteName("测试");
		vote.setVoteNum(2);
		
		voteService.saveVote(vote);*/
		
		/*List<VoteOption> list = new ArrayList<VoteOption>();
		
		VoteOption option = new VoteOption();
		option.setOptionId(StringUtils.getUUID());
		option.setOrderId(1);
		option.setText("测试选项--option");
		option.setVoteId("test--123");
		option.setVoteNum(1);
		option.setImgUrl("image/aaa.jpg");
		list.add(option);
		
		VoteOption option1 = new VoteOption();
		option1.setOptionId(StringUtils.getUUID());
		option1.setOrderId(2);
		option1.setText("测试选项--option1");
		option1.setVoteId("test--123");
		option1.setVoteNum(1);
		option1.setImgUrl("image/aaa.jpg");
		list.add(option1);
		
		VoteOption option2 = new VoteOption();
		option2.setOptionId(StringUtils.getUUID());
		option2.setOrderId(3);
		option2.setText("测试选项--option2");
		option2.setVoteId("test--123");
		option2.setVoteNum(3);
		list.add(option2);
		
		VoteOption option3 = new VoteOption();
		option3.setOptionId(StringUtils.getUUID());
		option3.setOrderId(4);
		option3.setText("测试选项--option3");
		option3.setVoteId("test--123");
		option3.setVoteNum(1);
		list.add(option3);
		
		VoteOption option4 = new VoteOption();
		option4.setOptionId(StringUtils.getUUID());
		option4.setOrderId(5);
		option4.setText("测试选项--option4");
		option4.setVoteId("test--123");
		option4.setVoteNum(1);
		list.add(option4);
		
		VoteOption option5 = new VoteOption();
		option5.setOptionId(StringUtils.getUUID());
		option5.setOrderId(6);
		option5.setText("测试选项--option5");
		option5.setVoteId("test--123");
		option5.setVoteNum(1);
		list.add(option5);
		
		voteService.saveVoteOptionList(list);
		
		String ids = option.getOptionId()+","+option1.getOptionId()+","+option2.getOptionId();
		System.out.println(ids);
		*/
		//voteService.updateVoteNum("d51bab559f6d4fb0a9bc9d55e54b577d,3166e2881216405da375e470fc47c93a,c50e0ce11cad468ea9ce220209024df3");
		/*ExcelUser excelUser = new ExcelUser();
		excelUser.setLab1("18870147561");
		excelUser.setLab2("王五");
		excelUser.setLab3("移动");
		excelUser.setOpenId("fdaf122312");*/
		/*ExcelUser excelUser = new ExcelUser();
		excelUser.setLab1("18870447561");
		ExcelUser selectUser = excelUserService.selectUser(excelUser);
		if (selectUser==null) {
			System.out.println("测试成功");
		}*/
	/*	ExcelUser excelUser = new ExcelUser();
		excelUser.setLab1("18871147561");
		excelUser.setLab2("赵六");
		excelUser.setLab3("联通");
		excelUser.setState("3");
		excelUser.setOpenId("fsdaf122312");
		excelUserService.insertSelective(excelUser);*/
		/*List<ExcelUser> list = excelUserService.selectId(6,6);
		for(ExcelUser user:list){
			System.out.println(user);
		}*/
		//System.out.println(excelUserService.count());
	}
}
