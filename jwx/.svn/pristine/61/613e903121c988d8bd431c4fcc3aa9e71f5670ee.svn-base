<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html>
<html>
 <head>
  <title>微信组</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<link href="plug-in/weixin/message/css/default.css" type="text/css" rel="stylesheet"/>
	<link href="plug-in/weixin/message/css/index.css" type="text/css" rel="stylesheet"/>
	
	<script type="text/javascript" src="plug-in/easyui/jquery.easyui.min.1.3.2.js"></script> 
	<script type="text/javascript" src="plug-in/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="plug-in/jquery/jquery.form.min.js"></script>
  <script type="text/javascript">
  $(function() {
		//是否显示分组
		$("#isToAll").change(function() {
			var isToAll = $("#isToAll").find("option:selected").val();
			if (isToAll == "false") {
				$(".c_two").css("display", "block");
				$(".c_three").css("display", "none");
			} 
			if (isToAll == "true") {
				$(".c_two").css("display", "none");
				$(".c_three").css("display", "none");
			}
			if (isToAll == "byuser") {
				$(".c_three").css("display", "block");
				$(".c_two").css("display", "none");
			}
		});
		
		//var htmlContent = "<div><img src='${weixinCardPage.logoUrl}'></div>";
		var title = '${weixinCardPage.title}';
		var brandName = '${weixinCardPage.brandName}';
		var logoUrl = '${weixinCardPage.logoUrl}';
		var color = '${weixinCardPage.color}';
		if(color == 'Color100'){
			color = '#CC463D';
		}else if(color == 'Color010'){
			color = '#63B359';
		}else if(color == 'Color080'){
			color = '#EE903C';
		}else{
			color = '#CC463D';
		}
		
		
		var htmlContent = "<div class=\"media_preview_area pp\" style=\"width:320px;\">"
			+ "<div class=\"appmsg multi editing\"><div id=\"js_appmsg_preview\" class=\"appmsg_content\">";
			
			htmlContent += "<div id=\"appmsgItem1\" class=\"js_appmsg_item has_thumb\" data-id=\"1\" data-fileid=\"200159570\">"
				+ "<div class=\"appmsg_info\"  style=\"color:white;background-color:"+color+"\"   > <h3>"
				+ title
				+ "</h3><em class=\"appmsg_date\"></em></div><div class=\"cover_appmsg_item\">"
				+ "<h4 class=\"appmsg_title\"> <a target=\"_blank\" onclick=\"return false;\" href=\"javascript:void(0);\">"
				+ brandName
				+ "</a> </h4>"
				+ "<div class=\"appmsg_thumb_wrp\"> <img class=\"js_appmsg_thumb appmsg_thumb\" src=\""+logoUrl+"\"></div></div></div>";
				
				htmlContent += "</div></div></div>";
		$(".c_bj").html(htmlContent);
		
		//群发提交
		$("#sendMessage").click(function() {

			var msgtype = "wxcard";
			$("#msgtype").val(msgtype);

			var options = {  
					   success: function(data){
						   
						   $.messager.alert('提示',data.msg,'info');
					   },//提交后的回调函数  
					   url : "sendGroupMessageController.do?sendGroupMessage",//默认是form的action， 如果申明，则会覆盖  
					   type: "POST",               //默认是form的method（get or post），如果申明，则会覆盖  
					   dataType: "JSON",           //html(默认), xml, script, json...接受服务端返回的类型  
					   //clearForm: true,          //成功提交后，清除所有表单元素的值  
					   //resetForm: true,          //成功提交后，重置所有表单元素的值  
					   timeout: 10000               //限制请求的时间，当请求大于3秒后，跳出请求  
			}  
			$("#formobjGroup").ajaxSubmit(options);  
			return false;   //阻止表单默认提交 
		});
  });
  </script>
 </head>
 <body>
 <t:formvalid formid="formobjGroup" dialog="true" layout="table" action="" tiptype="1">
	<input type="hidden" name="msgtype" id="msgtype">
	<input type="hidden" name="note" id="note" value="${weixinCardPage.cardId}">
 	<div class="jmain">
		<div class="jtit">
			
			<div class="list1">
				<div class="jtxt">群发对象</div>
				<div class="jcont">
					<div class="c_one">
						<select name="isToAll" id="isToAll">
							<option value="true">全部用户</option>
							<option value="false">按分组选择</option>
							
							<c:if test="${WEIXIN_ACCOUNT.accounttype == '1'}">
								<option value="byuser">按用户选择</option>
							</c:if>
							
						</select>
					</div>
					<div class="c_two" style="display: none">
						<select name="groupId" id="groupId">
								<c:forEach items="${weixinGroupList }" var="group">
						  	 		<option value="${group.groupId}">${group.groupName}</option>
						  	 	</c:forEach>
						</select>
					</div>
					
					<div class="c_three" style="display: none">
						<input id="receiveUserId" name="receiveUserId" type="hidden" style="width: 150px" class="inputxt" >
					    <input name="nickName" class="inputxt" value="${nickName}" id="nickName" readonly="readonly" datatype="*" /> 
					     	
					    <t:choose hiddenName="receiveUserId" hiddenid="openId" url="weixinMessageController.do?members" name="memberList" icon="icon-search" title="用户列表" textname="nickName" isclear="true"></t:choose>
						<span class="Validform_checktip">用户可多选</span>
					</div>
				</div>
			</div>
			<!--list1 end-->
		</div>
		
		<div class="c_cont">
			<div class="c_tree">
				<ul>
					<li><a href="javascript:void(0);"> <i id="card"
							style="background-position: 0px -240px;" title="卡券"></i>
					</a></li>

				</ul>
		</div>
		<div class="c_bj">
				<textarea class="wz" placeholder="请输入内容" name="param"></textarea>
			</div>
		<div class="c_footer">
			<input type="button" id="sendMessage" value="群发" class="c_btn">
		</div>
		
	</div>
</div>
</t:formvalid>
</body>
</html>
<%-- 
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="weixinCardController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${weixinCardPage.id }">
					
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								优惠券:
							</label>
						</td>
						<td class="value">
						    ${weixinCardPage.brandName}
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								发放对象:
							</label>
						</td>
						<td class="value">
						     <select name="isToAll" id="isToAll">
						     	<option value="true">全部用户</option>
								<option value="false">指定用户</option>
						     </select>
						    
						    <div class="c_two" style="display: none"> 
						    	<input id="receiveUserId" name="receiveUserId" type="hidden" style="width: 150px" class="inputxt" >
					     		<input name="nickName" class="inputxt" value="${nickName}" id="nickName" readonly="readonly" datatype="*" /> 
					     	
					     		<t:choose hiddenName="receiveUserId" hiddenid="openId" url="weixinMessageController.do?members" name="memberList" icon="icon-search" title="用户列表" textname="nickName" isclear="true"></t:choose>
								<span class="Validform_checktip">请选择发送对象,用户可多选</span>
							</div>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								发放数量:
							</label>
						</td>
						<td class="value">
						     	 <input id="quantity" name="quantity" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${weixinCardPage.quantity}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">卡券数量</label>
						</td>
					</tr>
			</table>
			
			<div class="ui_buttons" style="padding-left:5px;">
				<input type="submit" id="copyBtn" value="确定" class="ui_state_highlight">
        		<input type="button" id="closeBtn" value="取消" onclick='close();'>
        	</div>
		</t:formvalid>
 </body>
--%>