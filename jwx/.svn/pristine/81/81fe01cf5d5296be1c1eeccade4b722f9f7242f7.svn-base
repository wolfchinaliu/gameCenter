<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head id="Head1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>微客服</title>
<meta charset="utf-8" />
<meta name="description" />
<meta name="keywords" />
<meta content="eric.wu" name="author" />
<meta content="application/xhtml+xml;charset=UTF-8"
	http-equiv="Content-Type" />
<meta content="no-cache,must-revalidate" http-equiv="Cache-Control" />
<meta content="no-cache" http-equiv="pragma" />
<meta content="0" http-equiv="expires" />
<meta content="telephone=no, address=no" name="format-detection" />
<meta
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"
	name="viewport" />
<link rel="stylesheet" type="text/css"
	href="${webRoot }/webpage/weixin/customer/talk/css/mobilemain.css" />
<script src="${webRoot }/webpage/weixin/customer/talk/js/jquery_min.js"></script>
<script>
	document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
		WeixinJSBridge.call('hideToolbar');
		WeixinJSBridge.call('hideOptionMenu');
		$(window).scrollTop(document.body.scrollHeight);
	});
</script>
</head>
<body onselectstart="return true;" ondragstart="return false;">
	<div id="container" class="container animate">
		<div class="containertop" id="containertop">
			<c:forEach var="msg" items="${msgList }">
				<p class="time" style="display: block;"><fmt:formatDate value="${msg.createDate}" type="both"/> </p>
				<c:choose>
					<c:when test="${msg.createName  eq AId }">
						<ul class="ul_talk reply">
							<li class="tbox">
								<div>
									<span class="head"> <img src="${currentHeadImg }" />
									</span>
								</div>
								<div>
									<span class="arrow"> <svg>
	                                <path d="M18,40 A9,5,0,0,0,2,37 L0,23"
												stroke-width="1" stroke="#2792ff" fill="#2792ff" />
	                            </svg>
									</span>
								</div>
								<div>
									<article class="content">${msg.content }</article>
								</div>
							</li>
						</ul>
					</c:when>
					<c:otherwise>
						<ul class="ul_talk ">
							<li class="tbox">
								<div>
									<span class="head"> <c:if test="${headImg==null }">
											<img
												src="${webRoot }/webpage/weixin/customer/talk/images/mengmei.jpg" />
										</c:if> <c:if test="${headImg!=null }">
											<img src="${headImg }" />
										</c:if>

									</span>
								</div>
								<div>
									<span class="arrow"><svg>
							<path d="M2,20 A9,5,0,0,1,18,24 L20,0" stroke-width="1"
												stroke="#e2e2e2" fill="#e2e2e2" /></svg></span>
								</div>
								<div>
									<article class="content">${msg.content }</article>
								</div>
							</li>
						</ul>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
		<footer>
			<section class="nav_footer" id="nav_footer">
				<ul>
					<ol class="tbox">
						<li><a class="pointer toolsface"
							style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"
							id="facePoint"></a></li>
						<li><iframe style="display: none;" name="Upload_iframe2"
								id="Upload_iframe2"></iframe> <!-- <form method="post" enctype="multipart/form-data"
								target="Upload_iframe"
								action="MobileUpload.aspx?aid=373051&weimobId=613938331811"
								id="Upload_form">
								<input type="file" accept="image/jpg, image/jpeg, image/png"
									class="picfile" 
									name="Upload_file"> <a class="pointer toolscamera"
									id="picPoint1"></a>
							</form> --></li>

						<li style="width: 100%;"><input type="text" id="sendtext"
							class="toolstext" style="width: 100%;" /></li>

						<li><a id="btnsend" class="toolssend on"
							style="height: 24px; padding-top: 6px; margin: 0 7px;">发送</a></li>
					</ol>
					<ol id="face_area" style="display: none;">

						<li class="page_emotion box_swipe" id="page_smiley">
							<dl id="list_smiley" class="list_emotion pt_10">

							</dl>
							<dt>
								<ol id="nav_smiley" class="nav_emotion">
									<span class="on"></span>
									<span></span>
								</ol>
							</dt>
						</li>

						<li class="page_emotion box_swipe" id="page_xiong"
							style="display: none;">
							<dl id="list_xiong" class="list_emotion pt_10">

							</dl>
							<dt>
								<ol id="nav_xiong" class="nav_emotion">
									<span class="on"></span>
									<span></span>
								</ol>
							</dt>
						</li>

						<li class="page_emotion box_swipe" id="page_mayi"
							style="display: none;">
							<dl id="list_mayi" class="list_emotion pt_10">

							</dl>
							<dt>
								<ol id="nav_mayi" class="nav_emotion">
									<span class="on"></span>
									<span></span>
								</ol>
							</dt>
						</li>


					</ol>

					<ol>
						<li class="facetype" id="facetype" style="display: none;"><span
							data-key="jingdian" class="jingdian on">经典</span><span
							data-key="xiong" class="xiong">熊大&兔兔</span><span data-key="mayi"
							class="mayi">黑蚂蚁</span></li>
					</ol>

				</ul>


			</section>
		</footer>
		<input type="hidden" id="pageNumber" value="2" /> <input
			type="hidden" id="lastmsgtime" value="2015/9/16 11:56:49" />
	</div>
</body>
</html>

<script src="${webRoot }/webpage/weixin/customer/talk/js/emotion.js"></script>
<script src="${webRoot }/webpage/weixin/customer/talk/js/helper_min.js"></script>
<script src="${webRoot }/webpage/weixin/customer/talk/js/swipe.js"></script>

<script
	src="${webRoot }/webpage/weixin/customer/talk/js/MobileCustomer.js?v=8"></script>
<script>
	var userAgent = navigator.userAgent.toLowerCase();
	var IsIOS = false;
	if (userAgent.indexOf("iphone") >= 0) {
		IsIOS = true;
		$("#nav_footer ul").css("position", "absolute");
	}
	var sWeimobId = "${sWeimobId}";
	var AId = "${AId}";
	var SocketIp = "120.55.104.98";
	var receiveOpenId = "${receiveOpenId}";
	var CustomerHeadUrl = "${currentHeadImg}";
	var nickName="${nickName}";
	var sendFlag="${sendFlag}";
	$(function() {
		$('#sendtext').bind('input propertychange', function() {
			var text = $("#sendtext").val();
			var btn = $("#btnsend");
			if (text.trim().length > 0) {
				btn.removeClass("on");
			} else {
				btn.addClass("on");
			}
		});
	})
	$("#btnsend").click(function() {
		var text = $("#sendtext").val();
		if (text.trim().length == 0) {
			return;
		} else {
			sendMessage(AId, sWeimobId, 'text', text.trim());
		}
	});

	/* window.preViewImg = (function() {
		var imgsSrc = {};
		function reviewImage(dsrc, gid) {
			if (typeof window.WeixinJSBridge != 'undefined') {
				WeixinJSBridge.invoke('imagePreview', {
					'current' : dsrc,
					'urls' : imgsSrc[gid]
				});
			} else {
				alert("请在微信中查看", null, function() {
				});
			}
		}
		function init(thi, evt) {
			var dsrc = thi.getAttribute("data-src");
			var gid = thi.getAttribute("data-gid");
			if (dsrc && gid) {
				imgsSrc[gid] = imgsSrc[gid] || [];
				imgsSrc[gid].push(dsrc);
				thi.addEventListener("click", function() {
					reviewImage(dsrc, gid);
				}, false);
			}
		}
		return init;
	})(); */
</script>
