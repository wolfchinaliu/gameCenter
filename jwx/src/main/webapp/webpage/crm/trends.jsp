<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<jsp:include page="inc.jsp" />
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<title>灵动移动CRM</title>
	<style scoped>
		#list .image{
			width: 32px;
			height: 32px;
			border: 0;
			margin-right: 5px;
			float: left;
		}
		#list .header{
			font-size: 14px;
			font-weight: bold;
		}
		#list .subtitle{
			text-overflow: ellipsis;
			overflow: hidden;
		}
		#list .content{
			font-size: 14px;
		}
		#list .reply{
			height:auto;padding-top: 0;
		}
		#list .reply p{
			height:10px;vertical-align: top;
		}
	</style>
</head>
<body>
	<div class="easyui-navpanel">
		<header>
			<div class="m-toolbar">
				<div class="m-title">
					动态
				</div>
                <div class="m-left">
                    <a href="javascript:history.go(-1);" class="easyui-linkbutton m-back" plain="true" outline="false"></a>
                </div>
                <div class="m-right">
                    
                   <a href="crmController.do?message" >
						<a href="trendsController.do?writeRrends"><img alt="" src="crm/themes/icons/icon_plus.png"></a>
					</a>
                </div>
				
			</div>
		</header>

		<ul id="list" class="m-list">
			
			<li>
				<img class="image" src="crm/images/10.jpg" width="32px" height="32px"/>
				<div class="header">侯哥</div>
				<div class="subtitle">今天14:01</div>
				<div class="content" onclick="window.location.href='setController.do?invite'">
					<p>早安分享小时候总觉得做个女人，漂亮很重要，后来长大点觉得，有品位和气质很重要，再长大一些觉得一生有个男人疼你很重要 ，直到现在才明白，作为一个女人，健康独立精彩的活着最重要</p>
				</div>
				<div class="reply">
					<p>石少:哈哈</p>
					<p>侯哥回复石少:有点意思吧</p>
				</div>
				<div class="text">
					<input class="easyui-textbox" style="width:100%;height:32px;" data-options="prompt:'我也说一句...',buttonText:'<span style=\'padding:0 15px\'>发送</span>'">
				</div>
			</li>
			<li>				
				<img class="image" src="crm/images/11.jpg" width="32px" height="32px"/>
				<div class="header">小萌</div>
				<div class="subtitle">今天11:30</div>
				<div class="content">
					<p>北京阅兵 </p>
					<p><img src="crm/images/01.jpg" width="250px" height="200px"></p>
				</div>
				<div class="reply">
					<p>石少:壮观啊</p>
				</div>
				<div class="text">
					<input class="easyui-textbox" style="width:100%;height:32px;" data-options="prompt:'我也说一句...',buttonText:'<span style=\'padding:0 15px\'>发送</span>'">
				</div>
			</li>
			<li>				
				<img class="image" src="crm/images/12.jpg" width="32px" height="32px"/>
				<div class="header">夏天</div>
				<div class="subtitle">昨天10:59</div>
				<div class="content">
					<p>同样是女人，差别咋这样大呢❓会保养的女人，男人带出去🈶面子，自己也🈶价值！🇨🇳国母，今天亮瞎全世界女人男人的眼</p>
					<p><img src="crm/images/02.jpg" width="250px" height="200px"></p>
				</div>
				<div class="reply">
					<p>石少:呵呵</p>
				</div>
				<div class="text">
					<input class="easyui-textbox" style="width:100%;height:32px;" data-options="prompt:'我也说一句...',buttonText:'<span style=\'padding:0 15px\'>发送</span>'">
				</div>
			</li>
        </ul>
       	
	</div>
	
</body>
</html>