<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker,autocomplete"></t:base>
<jsp:include page="/inc.jsp"></jsp:include>
<link rel="stylesheet" href="plug-in/accordion/css/icons.css" type="text/css"></link>
<link rel="stylesheet" href="plug-in/accordion/css/menu-icons.css" type="text/css"></link>
<link rel="stylesheet" href="plug-in/accordion/css/accordion.css" type="text/css"></link>


<div id="cc" class="easyui-layout" style="width:100%;height:100%;background: white;">

<div data-options="region:'west',title:'',split:false" style="width:200px;height:100%;padding: 0px;overflow-x: hidden;overflow-y: auto;">
<div id="aa" class="easyui-accordion" region="west" split="true" style="width: 200px; padding: 0px;border: 0;">
	<ul id="tt" class="easyui-tree">
	    <li>
			<span>1.微营销介绍</span>
			<ul>
				<li><span><a href="#001">什么是微营销</a></span></li>
				<li><span><a href="#002">微版本介绍</a></span></li>
			</ul>
		</li>
	    <li>
	    	<span>2.基础配置</span>
	    	<ul>
				<li><span><a href="#003">如何注册微信公众号</a></span></li>
				<li><span><a href="#004">如何添加公众号</a></span></li>
			</ul>
	    </li>
	    <li>
	    	<span>3.基础服务</span>
	    	<ul>
				<li><span><a href="#005">关注时回复</a></span></li>
				<li><span><a href="#006">关键字回复</a></span></li>
				<li><span><a href="#007">默认回复</a></span></li>
				<li><span><a href="#008">自定义菜单</a></span></li>
			</ul>
	    </li>
	    <li>
	    	<span>5.素材管理</span>
	    	<ul>
				<li><span><a href="#009">文本素材</a></span></li>
				<li><span><a href="#0010">图文素材</a></span></li>
			</ul>	
	    </li>
	    <li>
	    	<span>6.消息发送</span>
	    	<ul>
				<li><span><a href="#0012">群发消息</a></span></li>
			</ul>	
	    </li>
	    <li>
	    	<span>7.客户管理</span>
	    	<ul>
				<li><span><a href="#0021">粉丝管理</a></span></li>
				<li><span><a href="#0022">会员卡管理</a></span></li>
				<li><span><a href="#0023">会员管理</a></span></li>
			</ul>	
	    </li>
	    <li>
	    	<span>8.微活动</span>
	    	<ul>
				<li><span><a href="#0031">大转盘</a></span></li>
				<li><span><a href="#0032">刮刮乐</a></span></li>
				<li><span><a href="#0033">优惠券</a></span></li>
			</ul>	
	    </li>
	    <li>
	    	<span>9.门店营销</span>
	    	<ul>
				<li><span><a href="#0041">门店管理</a></span></li>
				<li><span><a href="#0042">优惠券管理</a></span></li>
				<li><span><a href="#0043">优惠券明细</a></span></li>
				<li><span><a href="#0044">WIFI设备管理</a></span></li>
			</ul>	
	    </li>
	    <li>
	    	<span>10.微官网</span>
	    	<ul>
				<li><span><a href="#0051">站点信息</a></span></li>
				<li><span><a href="#0052">模板设置</a></span></li>
				<li><span><a href="#0053">广告管理</a></span></li>
				<li><span><a href="#0054">栏目管理</a></span></li>
				<li><span><a href="#0055">文章管理</a></span></li>
			</ul>
	    </li>
	    <li>
	    	<span>11.微商城</span>
	    	<ul>
				<li><span><a href="#0061">商城设置</a></span></li>
				<li><span><a href="#0062">分类管理</a></span></li>
				<li><span><a href="#0063">商品管理</a></span></li>
				<li><span><a href="#0064">订单管理</a></span></li>
				<li><span><a href="#0065">发货管理</a></span></li>
				<li><span><a href="#0066">仓储管理</a></span></li>
			</ul>	
	    </li>
		<li>
	    	<span>12.微餐饮</span>
	    	<ul>
				<li><span><a href="#0071">门店管理</a></span></li>
				<li><span><a href="#0072">分类管理</a></span></li>
				<li><span><a href="#0073">菜品管理</a></span></li>
				<li><span><a href="#0074">订单管理</a></span></li>
			</ul>	
	    </li>
	    <li>
	    	<span>13.微汽车</span>
	    	<ul>
				<li><span><a href="#0081">品牌设置</a></span></li>
				<li><span><a href="#0082">车系管理</a></span></li>
				<li><span><a href="#0083">车型管理</a></span></li>
				<li><span><a href="#0084">销售管理</a></span></li>
				<li><span><a href="#0085">预约管理</a></span></li>
			</ul>	
	    </li>
	</ul>
</div>

</div>


<div data-options="region:'center',title:''" style="padding-left:10px;padding-top: 10px;">
	<div align="center" style="padding-top:20px;font-size:20;font-weight: 700;padding-bottom: 50px;">微营销产品使用指南</div>
	<div>
		<h3 style="font-size: 16;">微信营销介绍</h3>
		<h4 id="001">什么是微营销？</h4>
		<p>微营销是针对微信商家公众号提供与众不同的、有针对性的营销推广服务。通过微信营销平台，用户可以轻松管理自己的微信各类信息，对微信公众账号进行维护、开展智能机器人、在线发优惠劵、抽奖、刮奖、派发会员卡、打造微官网、开启微团购等多种活动，对微信营销实现有效监控，极大扩展潜在客户群和实现企业的运营目标。</p>
		<h4 id="002">微营销版本介绍</h4>
		<p>通用版 </p>
		<p>行业版 </p>
	</div>
	
	<div>
		<h3 style="font-size: 16;">基本配置</h3>
		<h4 id="003">如何注册微信公众号？</h4>
		<p>登录https://mp.weixin.qq.com/，点击注册填写相关信息即可申请微信公众号，微信公众号只能在PC端登录，不能用手机端，公众号分服务号和订阅号，企业可申请服务号，个人、自媒体申请订阅号，服务号每个月限制群发4条，而订阅号每天可发送一次，但消息会折叠起来放在一个订阅号的消息盒子里，服务号仍然是放在聊天列表。</p>
		<P><img src="index/help/zhuce.png"></P>
		<h4 id="004">如何添加公众号？</h4>
		<p>1.登录微营销平台，进入 "我的微管"-"公众账号管理"-"添加公众账号" 进入添加公众号界面。</p>
		<P><img src="index/help/gzzh.png"></P>
		<P>公众号名称与微信公众平台公众号名称保持一致。</P>
		<P>公众号原始id，这个很重要，公众平台进行通讯就靠这个ID，所以不能填错，如果在填的时候遇到问题可以点不懂就问我或者直接联系在线客服。</P>
		<P>2.登录微信公众平台mp.weixin.qq.com，点"公众号设置"——"注册信息"，可以获得原始ID信息，如下图：</P>
		<P><img src="index/help/zh.png"></P>
		<p>3.填写AppId 和AppSecret填写</p>
		<P><img src="index/help/appid.png"></P>
		<P>4.公众账号信息填写完整，保存即产生一条公众账号记录，如下图</P>
		<P><img src="index/help/gzh.png"></P>
		<p>添加好公众号后会产生一个token，需要将它配置到腾讯微信公众管理平台</p>
		
		<p>5.绑定URL和TOKEN 登录微信公众平台mp.weixin.qq.com，点击高级功能，进入开发模式，开启开发模式</p>
		<p>填写接口URL和对应TOKEN，点击提交。如果提示提交成功，说明公众号绑定成功。</p>
		<P><img src="index/help/token.png"></P>
		<p>url和token都可以在微信营销平台-"首页"中获得，完成这项配置并提交成功，至此，微信公众号基本配置完成。</p>
	</div>
	
	<div>
		<h3 style="font-size: 16;">基础服务</h3>
		<h4 id="005">关注时回复</h4>
		<p>
			关注时回复：指粉丝关注微信公众号后推送的第一条信息，可以是文本，也可以是图文.
		</p>
		<P></P>
		<p>回复内容在素材管理，文本消息、图文消息模块进行编辑。</P>
		<P></P>
		<h4 id="006">关键字回复</h4>
		<h4 id="007">默认回复</h4>
		<h4 id="008">自定义菜单</h4>
		<p>自定义菜单是企业微信公众号消息框底部的导航菜单，可以很方便用户进行交互，相当于一个轻量级的APP。</p>
		<P><img src="index/help/zdy.png"></P>
		<p>菜单设置:自定义菜单目前限制只能3个一级菜单，5个二级菜单，微营销后台也对添加菜单作了相应的限制，只允许同时启用三个一级菜单，五个二级菜单。 并且只有保存主菜单后才能添加子菜单。</p>
	</div>
	
	<div>
		<h3 style="font-size: 16;">素材管理</h3>
		<h4 id="009">文本素材</h4>
		<p>图文素材</p>
		<P></P>
	</div>
	<div>
		<h3 style="font-size: 16;">消息发送</h3>
		<h4 id="0012">消息群发</h4>
		<p>商家可以根据自己的需求，选择群发消息的人群，并利用性别、地区这些筛选条件，精确定位粉丝人群后进行消息推送，商家既可以向全部粉丝群发消息，也可针对特定粉丝人群单独发送消息。</p>
		<P><img src="index/help/qf.png"></P>
		<p>群发消息的内容格式包括：文本、图片、语音、视频、图文。选择图文消息群发的页面如下：</P>
		<P><img src="index/help/twqf.png"></P>
		<p>备注：<br>1、目前只有认证服务号商家才可使用高级群发功能。<br>2、每月每个粉丝最多接收4条群发消息，超出4条也可发送成功，但粉丝无法收到。</p>
	</div>
	<div>
		<h3 style="font-size: 16;">客户管理</h3>
		<h4 id="0021">粉丝管理</h4>
		<p>通过同步微信粉丝，把所有粉丝信息下载到微信营销平台进行集中管理。通过对粉丝的性别，地域、行为、活跃度等进行营销分析.</p>
		<P><img src="index/help/fs.png"></P>
		<h4 id="0022">会员卡管理</h4>
		<p>卡片设置 包含卡片名称、背景、logo、使用说明等，设置后可生成完整的微信电子会员卡</p>
		<P><img src="index/help/membercardadd.png"></P>
		<h4 id="0023">会员管理</h4>
		<p>微信会员列表：可直接为某个会员充值、消费、修改积分、编辑信息、冻结会员卡，并可查看各会员的消费统计表</p>
	</div>

	<div>
		<h3 style="font-size: 16;">微活动</h3>
		<h4 id="0031">微信大转盘</h4>
		<p>该模块可为商家提供转盘抽奖服务，商家通过设置活动时间，预计参加抽奖人数，相应奖项和触发关键词，由网友在线参与抽奖。</p>
		<P><img src="index/help/dzp.png"></P>
		<p>大转盘活动设置：</p>
		<P>1、设置抽奖前需要先编辑一条图文消息，提前一天或者当天推送给所有粉丝，告知在某个时间段发某个关键词可以参与抽奖；</p>
		<P>2、进入抽奖设置，设定活动的类别，设定活动的预热时间，已经活动的开始和结束时间，设置活动回复关键词“我要抽奖”，确定设置后系统会根据设定的中奖几率产生相应的SN码；</p>
		<P>3、活动设定后，在规定时间到时，网友发送关键词如“我要抽奖”，就可以参与三次转盘，中奖后会得到一个SN码，网友到店时向商家出示SN码，商家根据系统产生的SN码进行比对，确认无误后即可兑换奖品。</P>
		<P><img src="index/help/dzp.jpg"></P>
		<h4 id="0032">刮刮乐</h4>
		<p>该模块可为商家提供刮刮卡抽奖服务，全网第一个可以通过微信玩刮刮卡，用户通过手机屏幕进行刮奖的游戏！</p>
		<h4 id="0033">优惠券</h4>
		<p>优惠券的类型有：优惠券、代金券、礼品券、团购券、折扣券</p>
	</div>
	<div style="padding-top: 30px;">
		<h3 style="font-size: 16;">门店营销</h3>
		<h4 id="0041">门店管理</h4>
		<p>门店管理列表 进入微营销管理平台，点击“门店管理”即可查看门店列表。</p>
		<P><img src="index/help/mdyx.png"></P>
		<p>添加门店 在管理平台点击“创建门店”，进入编辑门店详情页面。</p>
		<p>进入编辑门店基本信息页，填写门店名称、logo、电话、营业时间、餐厅特色、登录密码、会员卡消费密码、门店二维码、门店缩略图、门店实景、门店所在的地区、并在地图上标识、公交信息、商家通知设置，点击保存，完成门店添加。</p>
		<h4 id="0042">优惠券管理</h4>
		<p>优惠券的类型有：优惠券、代金券、礼品券、团购券、折扣券</p>
		<P><img src="index/help/yhq.png"></P>
		<h4 id="0043">优惠券明细</h4>
		<p>优惠券明细 查看优惠券领取列表</p>
		<P><img src="index/help/yhqxf.png"></P>
		<p>点击"消费"，可以对会员所领取的优惠券进行核销。</p>
		<h4 id="0044">WIFI管理</h4>
		<p>1.基本设置 登录微信公众平台，"添加功能插件"——"微信连Wi-Fi"，点击"开通"按钮进入，填写开通申请资料。</p>
		<P><img src="index/help/wxwifi.png"></P>
		<p>2.路由设置 在店内安装好一台路由器并且连接上网，在浏览器中输入http://192.168.0.1，输入账号和密码，账号和密码一般都是admin，进入路由器设置页面，获取mac地址</p>
		<p>3.添加设备 填写适用门店,无线ssid、路由器wifi密码、路由器的mac地址，创建设备前先要保证已经创建好审核通过的门店信息。如下图：</p>
		<P><img src="index/help/wifiadd.png"></P>
		<p>4.使用方法 安装上述步骤创建好WIFI设备并且生效后，获取WIFI二维码图片打印并粘贴到店内供客户扫描上网。如下图示：</p>
		<P><img src="index/help/wifiq.png"></P>
	</div>
	<div>
		<h3 style="font-size: 16;">微官网</h3>
		<h4 id="0051">站点信息</h4>
		<P><img src="index/help/gw.png"></P>
		<h4 id="0052">模板设置</h4>
		<h4 id="0053">广告管理</h4>
		<h4 id="0054">栏目管理</h4>
		<h4 id="0055">文章管理</h4>
	</div>
	
	<div>
		<h3 style="font-size: 16;">微商城</h3>
		<h4 id="0061">商城设置</h4>
		<h4 id="0062">分类设置</h4>
		<p>通过分组对商品进行类别管理，选择某个分组后可以直接拖拽来调整位置</p>
		<h4 id="0063">商品管理</h4>
		<p>编辑商品的基本信息、物流信息和其他信息，点击“保存”按钮，就完成了商品的添加</p>
		<P><img src="index/help/spgl.png"></P>
		<h4 id="0064">发货管理</h4>
		<h4 id="0065">仓储管理</h4>
	</div>
	<div>
		<h3 style="font-size: 16;">微餐饮</h3>
		<h4 id="0071">门店管理</h4>
		<h4 id="0072">分类管理</h4>
		<h4 id="0073">菜品管理</h4>
		<h4 id="0074">订单管理</h4>
	</div>
	<div>
		<h3 style="font-size: 16;">微汽车</h3>
		<h4 id="0071">品牌设置</h4>
		<h4 id="0072">车系管理</h4>
		<h4 id="0073">车型管理</h4>
		<h4 id="0074">销售管理</h4>
		<h4 id="0074">预约管理</h4>
	</div>
</div>

</div>