package com.shiliu.game.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.alibaba.fastjson.JSON;
import com.shiliu.game.domain.ExcelUser;
import com.shiliu.game.service.IExcelUserService;
/**
 * @Auth wkr
 * @Date 2016年11月21日 下午1:31:16
 * @authEmail super_otowa@163.com
 * @CalssName com.shiliu.game.controller.ShowClientController
 * @dec 显示客户信息
 */
@Controller
@RequestMapping(value="/showClient")
@SessionAttributes(value={"getGameId"})
public class ShowClientController {

	@Resource
	private IExcelUserService excelUserservice;
	private final int PAGESIZE=100;
	
	/**
	 * 初始化页面信息
	 */
	@RequestMapping(value="/init")
	public String init(@ModelAttribute(value="getGameId") String getGameId ,Model model){
		int count = excelUserservice.count(getGameId);
		int totalPage = count%PAGESIZE==0?count/PAGESIZE:count/PAGESIZE+1;
		
		List<ExcelUser> selectId = excelUserservice.selectId(0, PAGESIZE,getGameId);
		
		model.addAttribute("usersList",selectId);
		model.addAttribute("totalPage",totalPage);
		
		return "client/showClient";
	}
	
	/**
	 * 分页显示客户信息
	 * @param pageIndex
	 */
	@RequestMapping(value="/paging")
	public void paging(@ModelAttribute(value="getGameId") String getGameId ,int pageIndex,PrintWriter pw){
		List<ExcelUser> selectId = excelUserservice.selectId((pageIndex-1)*PAGESIZE, PAGESIZE,getGameId);
		
		pw.println(JSON.toJSONString(selectId));
	}
}
