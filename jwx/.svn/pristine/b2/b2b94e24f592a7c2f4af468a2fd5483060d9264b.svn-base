<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>套餐类型</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinProductController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinProductPage.id }">
					<input id="productName" name="productName" type="hidden" value="${weixinProductPage.productName }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								价格:
							</label>
						</td>
						<td class="value">
						     	 <input id="price" name="price" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinProductPage.price}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">价格</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								描述:
							</label>
						</td>
						<td class="value">
						     	 <input id="remark" name="remark" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinProductPage.remark}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">描述</label>
						</td>
					</tr>
					<tr>
					<td align="right">
						<label class="Validform_label">
							请求上限:
						</label>
					</td>
					<td class="value">
					     	 <input id="requestNum" name="requestNum" type="text" style="width: 150px" class="inputxt"  
								               
								               value='${weixinProductPage.requestNum}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">请求上限</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							群发次数:
						</label>
					</td>
					<td class="value">
					     	 <input id="groupSMSNum" name="groupSMSNum" type="text" style="width: 150px" class="inputxt"  
								               
								               value='${weixinProductPage.groupSMSNum}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">群发次数</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							图文素材上限:
						</label>
					</td>
					<td class="value">
					     	 <input id="newsTemplateNum" name="newsTemplateNum" type="text" style="width: 150px" class="inputxt"  
								               
								               value='${weixinProductPage.newsTemplateNum}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">图文素材上限</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							文本素材上限:
						</label>
					</td>
					<td class="value">
					     	 <input id="textTemplateNum" name="textTemplateNum" type="text" style="width: 150px" class="inputxt"  
								               
								               value='${weixinProductPage.textTemplateNum}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">文本素材上限</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/weixin/tenant/weixinProduct.js"></script>		