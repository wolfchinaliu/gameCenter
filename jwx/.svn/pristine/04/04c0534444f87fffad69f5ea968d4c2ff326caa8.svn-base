<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>商品评价</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinShopAppraiseController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinShopAppraisePage.id }">
					<input id="createDate" name="createDate" type="hidden" value="${weixinShopAppraisePage.createDate }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							内容:
						</label>
					</td>
					<td class="value">
					     	 <input id="notes" name="notes" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">内容</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							商品ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="goodsId" name="goodsId" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">商品ID</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							粉丝ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="openId" name="openId" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">粉丝ID</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							粉丝昵称:
						</label>
					</td>
					<td class="value">
					     	 <input id="openName" name="openName" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">粉丝昵称</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							审核状态:
						</label>
					</td>
					<td class="value">
					     	 <input id="status" name="status" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审核状态</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							星级:
						</label>
					</td>
					<td class="value">
					     	 <input id="star" name="star" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">星级</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/shop/weixinShopAppraise.js"></script>		