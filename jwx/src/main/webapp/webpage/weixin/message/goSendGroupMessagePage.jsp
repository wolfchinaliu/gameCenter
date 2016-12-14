<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="/inc.jsp"></jsp:include>
<!DOCTYPE html>
<html>
 <head>
  <title>消息群发</title>
  <t:base type="jquery"></t:base>
	<link href="plug-in/weixin/message/css/default.css" type="text/css" rel="stylesheet"/>
	<link href="plug-in/weixin/message/css/index.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="plug-in/easyui/jquery.easyui.min.1.3.2.js"></script> 
	<script type="text/javascript" src="plug-in/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="plug-in/jquery/jquery.form.min.js"></script>
	<script type="text/javascript">
		$(function() {

			$(".c_tree i").each(function(i, element) {
				var _top = i * -60, _top2 = i * -60 - 30;
				if (i == 0) {
					$(this).css({
						"background-position" : "0px " + _top2 + "px"
					});
				} else {
					$(this).css({
						"background-position" : "0px " + _top + "px"
					});
					$(this).hover(function() {
						$(this).css({
							"background-position" : "0px " + _top2 + "px"
						});
					}, function() {
						$(this).css({
							"background-position" : "0px " + _top + "px"
						});
					});
				}
			});

			//弹出框
			var _move = false;//移动标记
			var _x, _y;//鼠标离控件左上角的相对位置
			//标记点击的那个按钮
			var checkedPic = "text";
			$(".c_tree i").click(
							function() {
								var id = $(this).attr("id");
								checkedPic = id;
								var message = "";
								var srcUrl = "groupMessageNewsTemplateController.do?getAllUploadNewsTemplate";
								if (id == "text") {
									var htmlContent = "<textarea class=\"wz\" placeholder=\"请输入内容\" name=\"param\"></textarea>";
									$(".c_bj").html(htmlContent);
									$(".wz").focus();
									$("#groupmessage").attr("src", "");
								} else if (id == "image") {
									message = "上传图片";
									srcUrl += "&symbol=simple&type=image";
									$("#groupmessage").attr("src", srcUrl);
								} else if (id == "video") {
									message = "上传视频";
									srcUrl += "&symbol=simple&type=video";
									$("#groupmessage").attr("src", srcUrl);
								} else if (id == "voice") {
									message = "上传语音";
									srcUrl += "&symbol=simple&type=voice";
									$("#groupmessage").attr("src", srcUrl);
								} else if (id == "mpnews") {
									message = "选择素材";
									srcUrl += "&symbol=page&type=mpnews";
									$("#groupmessage").attr("src", srcUrl);
								}
								//设置弹出框标题
								if (id != "text") {
									$(".c_head").find("i").html(message);
									$('.cc').fadeIn('slow');
									$('.zz').css("display", "block");
								}
							});

			/* //群发
			$("#sendMessage").click(function() {

				var msgtype = checkedPic;
				var groupId = $("#groupId option:selected").val();
				var isToAll = $("#isToAll option:selected").val();
				var param = $("#param").val();
				var imgPath = $("#imgPath").val();
				
				if (checkedPic == "text") {
					param = $(".wz").val();
				}
				//alert("param:"+param);
				//alert("消息类型"+msgtype+",组ID："+groupId + ",是否全部：" + isToAll);
					
				$.ajax({
					url : "sendGroupMessageController.do?sendGroupMessage",
					method : "POST",
					dataType : "JSON",
					data : {
						"msgtype" : msgtype,
						"groupId" : groupId,
						"isToAll" : isToAll,
						"param" : param
					},
					success : function(data) {
						
						alert(data.msg);
						tip(data.msg);
					}
				});

			}); */
			$("#sendMessage").click(function() {

				var msgtype = checkedPic;
				$("#msgtype").val(msgtype);
				var groupId = $("#groupId option:selected").val();
				var isToAll = $("#isToAll option:selected").val();
				var param = $("#param").val();
				var imgPath = $("#imgPath").val();
				if (checkedPic == "text") {
					param = $(".wz").val();
					$("#param").val(param);
				}
				var options = {  
						   success: function(data){
							   
							   $.messager.alert('提示',data.msg,'info');
						   },      //提交后的回调函数  
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
			$('.cc span,.zright').click(function() {
				$('.cc').hide('fast');
				$('.zz').css("display", "none");
			})
			//是否显示分组
			$("#isToAll").change(function() {
				var isToAll = $("#isToAll").find("option:selected").val();
				if (isToAll == "false") {
					$(".c_two").css("display", "block");
				} else {
					$(".c_two").css("display", "none");
				}
			});

			$(".zleft").click(
				function() {
					var iFrame = document
							.getElementById('groupmessage').contentWindow;
					var mediaId = iFrame.getCheckedNews();
					//关闭弹出框
					$('.cc').hide('fast');
					$('.zz').css("display", "none");
					if (checkedPic == 'mpnews') {
						
						$("#param").val(mediaId[1]);
						
						$.ajax({
									url : "weixinArticleController.do?showMessage",
									data : {"mediaId" : mediaId[0]
									},
									dataType : "JSON",
									method : "POST",
									success : function(data) {
										
										//var list=jQuery.parseJSON(data);

										var list=data.obj;
										
										//console.log(list);
										var htmlContent = "<div class=\"media_preview_area pp\" style=\"width:320px;\">"
												+ "<div class=\"appmsg multi editing\"><div id=\"js_appmsg_preview\" class=\"appmsg_content\">";
										for (var i = 0; i < list.length; i++) {
											
											var imagePath = list[i].imagePath;
											
											//console.info(list[i]);
											
											//console.log(list[i]);
											if (i == 0) {
												htmlContent += "<div id=\"appmsgItem1\" class=\"js_appmsg_item has_thumb\" data-id=\"1\" data-fileid=\"200159570\">"
														+ "<div class=\"appmsg_info\"> <h3>"
														+ list[i].title
														+ "</h3><em class=\"appmsg_date\"></em></div><div class=\"cover_appmsg_item\">"
														+ "<h4 class=\"appmsg_title\"> <a target=\"_blank\" onclick=\"return false;\" href=\"javascript:void(0);\">"
														+ list[i].description
														+ "</a> </h4>"
														+ "<div class=\"appmsg_thumb_wrp\"> <img class=\"js_appmsg_thumb appmsg_thumb\" src=\""+imagePath+"\"></div></div></div>";
											} else {
												htmlContent += "<div id=\"appmsgItem2\" class=\"appmsg_item js_appmsg_item has_thumb\" data-id=\"2\" data-fileid=\"200159577\"> <img class=\"js_appmsg_thumb appmsg_thumb\" src=\""+imagePath+"\">"
														+ "<h4 class=\"appmsg_title\"> <a target=\"_blank\" href=\"javascript:void(0);\">"
														+ list[i].title
														+ "</a></h4></div>";
											}
										}
										htmlContent += "</div></div></div>";
										$(".c_bj")
												.html(htmlContent);
									}
								});
					} else if (checkedPic == 'image') {
						var media_id = mediaId[0];
						$("#param").val(media_id);
						var imagePath = mediaId[1];

						$("#imgPath").val(mediaId[1]);
						$(".c_bj").css("text-align", "center");
						var htmlContent = "<img alt=\"\" src=\""+imagePath+"\" width=\"300\" heigth=\"200\"/>";
						$(".c_bj").html(htmlContent);
					}else if (checkedPic == 'voice') {
						var media_id = mediaId[0];
						$("#param").val(media_id);
						var imagePath = mediaId[1];
						$(".c_bj").css("text-align", "center");
						var htmlContent = "<a class=\"title_wrp\" href=\"javascript:;\" title=\"语音文件\"><span class='icon icon_lh' style=\"background-color:#c5c6c8!important;background:url(https://res.wx.qq.com/mpres/zh_CN/htmledition/comm_htmledition/style/widget/media_z23182d.png)0 -422px no-repeat;\" src=\""+imagePath+"\" ></span>";
						 htmlContent += "<span class=\"title\">[语音]1\"</span> </a>";
						$(".c_bj").html(htmlContent);
					}else if (checkedPic == 'video') {
						var media_id = mediaId[0];
						$("#param").val(media_id);
						var imagePath = mediaId[1];
						$(".c_bj").css("text-align", "center");
						var htmlContent = "<a class=\"title_wrp\" href=\"javascript:;\" title=\"视频文件\"><span class='icon icon_lh' style=\"background-color:#c5c6c8!important;background:url(https://res.wx.qq.com/mpres/zh_CN/htmledition/comm_htmledition/style/widget/media_z23182d.png)0 -422px no-repeat;\" src=\""+imagePath+"\" ></span>";
						 htmlContent += "<span class=\"title\">[视频]1\"</span> </a>";
						$(".c_bj").html(htmlContent);
					}

				});

		});
		
	</script>

</head>
<body>
<t:formvalid formid="formobjGroup" dialog="true" 
		layout="table" action="" tiptype="1">
		<input type="hidden" name="msgtype" id="msgtype">
	<input type="hidden" name="note" id="param">
	<input type="hidden" name="imgPath" id="imgPath">
	
	<!--弹出层-->
	<div class="zz" style="display: none;"></div>
	<div class="cc" style="display: none;">
		<div class="c_head">
			<i>选择素材</i><span></span>
		</div>
		<div class="c_main">
			<div class="cbot">
				<!-- 弹出页面 -->
				<iframe src="" id="groupmessage" width="100%" height="400"
					frameborder="0" scrolling="yes"></iframe>
			</div>
		</div>
		<div class="tj">
			<input class="zleft c_btn" type="button" value="确定"><input
				class="zright" type="button" value="取消">
		</div>
	</div>
	<!--弹出层 end-->
	<div class="jmain">
		<div class="jtit">
			<!--list1-->
			<div class="list1">
				<div class="jtxt">群发对象</div>
				<div class="jcont">
					<div class="c_one">
						<select name="isToAll" id="isToAll">
							<option value="true">全部用户</option>
							<option value="false">按分组选择</option>
						</select>
					</div>
					<div class="c_two" style="display: none">
						<select name="groupId" id="groupId">
								<c:forEach items="${weixinGroupList }" var="group">
						  	 		<option value="${group.groupId}">${group.groupName}</option>
						  	 	</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<!--list1 end-->
		</div>
		<!--c_cont-->
		<div class="c_cont">
			<div class="c_tree">
				<ul>

					<li><a href="javascript:void(0);"> <i id="text"
							style="background-position: 0px -30px;" title="文字"></i>
					</a></li>

					<li><a href="javascript:void(0);"> <i id="image"
							style="background-position: 0px -60px;" title="图片"></i>
					</a></li>

					<li><a href="javascript:void(0);"> <i id="video"
							style="background-position: 0px -120px;" title="视频"></i>
					</a></li>

					<li><a href="javascript:void(0);"> <i id="voice"
							style="background-position: 0px -180px;" title="语音"></i>
					</a></li>

					<li><a href="javascript:void(0);"> <i id="mpnews"
							style="background-position: 0px -240px;" title="图文"></i>
					</a></li>

				</ul>
			</div>

			<div class="c_bj">
				<textarea class="wz" placeholder="请输入内容" name="param"></textarea>
			</div>
			<div class="ts">
				还可以输入<i>600</i>字
			</div>
		</div>
		<!--c_cont end-->
		<div class="c_footer">
			<input type="button" id="sendMessage" value="群发" class="c_btn">
		</div>
	</div>


	<div id="__xfz_ext_flag" _bgt_title="群发消息"></div>
	</t:formvalid>
</body>
</html>