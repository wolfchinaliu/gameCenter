<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>${WeixinShopEntity.shopName }</title>
	<link rel="stylesheet" href="shop/css/wap/index.css" type="text/css" />
	<script type="text/javascript" src="shop/js/jquery-1.7.1.js"></script>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=0,minimum-scale=1.0, maximum-scale=1.0">
	<meta content="telephone=no" name="format-detection" />
	<style type="text/css">
		.w-viewmore{padding:10px}.w-viewmore .hide{display:none}.w-viewmore .op-btn{border:1px solid #e0e0e0;border-radius:3px;text-align:center;height:29px;line-height:29px;font-size:13px;background-color:#fff}.w-viewmore .more{float:left;width:213px;cursor:pointer;color:#303030}.w-viewmore .disabled{cursor:not-allowed;background-color:#ccc;color:#fff}.w-viewmore .gotop{float:right;width:78px;cursor:pointer;color:#303030}
	</style>
	<script type="text/javascript">
		function showfilter(){
			
			var divD = document.getElementById("filter");
			if(divD.style.display=="none"){
				
				divD.style.display = "block";     
			}else{
				
				divD.style.display = "none";
			}

			 window.scrollTo(0, 0);
		}
		
		function clearkeyword(){
			
			var key_word = document.getElementById("key_word").value;
			
			if(key_word == "输入关键词搜索"){
				document.getElementById("key_word").value = "";
			}
		}
		function subSearchForm(){
			
			var key_word = document.getElementById("key_word").value;
			
			if(key_word == "输入关键词搜索"){

				document.getElementById("key_word").value="";
			}
			document.getElementById('searchForm').action="shopGoodsController.do?golist";
			document.getElementById('searchForm').submit();
		}
	</script>
</head>
<body id="body_id">


<div class="filter" id="filter" style="display: none">
			<div class="filterEvn"></div>
			<div class="filterConten" id="filterConten">
				<div class="filterNav">

					<ul>
						<li class="ok"><a href="shopController.do?category">所有分类</a></li>
					</ul>

				</div>

				<div class="filterCon">
					<ul class="attr" style="display: block;">
							<li>
								<a href="shopGoodsController.do?golist">
								<span <c:if test='${empty categoryId}'>class="dot"</c:if>> </span>全部</a>
							</li>
							<c:forEach items="${weixinShopCategoryList}" var="weixinShopCategory">
							<li>
								<a href="shopGoodsController.do?golist&categoryId=${weixinShopCategory.id}">
								<span <c:if test='${not empty categoryId && categoryId eq weixinShopCategory.id}'>class="dot"</c:if>> </span>${weixinShopCategory.name}</a>
							</li>
							</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	<nav class="topBar">

		<div class="l">

			<a class="arrow" href="shopController.do?shopindex"></a>

		</div>

		<h1><form id="searchForm" action="shopGoodsController.do?golist" method="post">
			<c:if test="${empty key_word}">
				<input class="search sIcon" type="text" name="key_word" value="输入关键词搜索" id="key_word" onClick="clearkeyword();"/>
			</c:if>
			<c:if test="${not empty key_word}">
				<input class="search sIcon" type="text" name="key_word" value="${key_word}" id="key_word" onClick="clearkeyword();"/>
			</c:if>
			</form>
		</h1>

		<div class="r">
			<a href="javascript:showfilter();">筛选<span class="rightArr"></span></a>

		</div>

	</nav>
	<ul class="tabV2 clearfix">
		<li><a href="shopGoodsController.do?golist&categoryId=${categoryId}&key_word=${key_word}">综合</a></li>
		<li><a href="shopGoodsController.do?golist&categoryId=${categoryId}&key_word=${key_word}&sort=sellCount&order=desc">销量</a></li>
		<li><a href="shopGoodsController.do?golist&categoryId=${categoryId}&key_word=${key_word}&sort=realPrice,price&order=asc">价格</a></li>
	</ul>
	
	<div class="b5mConLis" id="mainDiv">
		<ul class="clear-fix">
			<%-- <c:forEach items="${goodsList}" var="weixinShopGoods">
			<li>

					<a href="shopGoodsController.do?goodsinfo&id=${weixinShopGoods.id }">

						<img src="${weixinShopGoods.titleImg }" alt="" style="height:150px"/>

						<h3>${weixinShopGoods.title }</h3>

						<section>

							<div class="new">
								<span>￥<em>
									<c:if test="${not empty weixinShopGoods.realPrice && weixinShopGoods.realPrice != 0}">${weixinShopGoods.realPrice}</c:if>
									<c:if test="${empty weixinShopGoods.realPrice || weixinShopGoods.realPrice == 0}">${weixinShopGoods.price}</c:if>
								</em><c:if test="${not empty weixinShopGoods.realPrice && weixinShopGoods.realPrice != 0}">促销</c:if></span>
							</div>

						</section>

				</a>
				</li>
				</c:forEach>--%>
				
				<div id="j-goods-container"></div>
		</ul>
	</div>
	<br>
	<div class="w-viewmore clearfix" mon="action=click">
			<a id="j-viewmore" href="javascript:;" class="op-btn more"
				mon="type=more">查看更多</a>
			<a href="javascript:;" id="j-gotop" class="op-btn gotop"
				mon="type=gotop">回到顶部</a>
	</div>
		
		<%-- 
	<div class="infoPages">
		<c:if test="${curpage == 1}">
			<a href="javascript:void(0);" class="grey">上一页</a>
		</c:if>
		<c:if test="${curpage != 1}">
			<a href="shopGoodsController.do?golist&categoryId=${categoryId}&nextpage=${curpage-1}">上一页</a>
		</c:if>
		
		<span>共找到${total }个商品，${curpage}/${totalpage}/</span>
		
		<c:if test="${curpage >= totalpage}">
			<a href="javascript:void(0);" class="grey">下一页</a>
		</c:if>
		<c:if test="${curpage < totalpage}">
			<a href="shopGoodsController.do?golist&categoryId=${categoryId}&nextpage=${curpage+1}">下一页</a>
		</c:if>
		
	</div>
--%>
	<br>

</body>
</html>
<script type='text/javascript' src='plug-in/jquery/jquery-1.8.3.js'></script>
	    <script type="text/javascript">
	    	var rows = 10;//每页显示记录数
	    	var page = 1;//当前页
	    	var total = 0;//总记录数
	    	var field = "id,title,categoryId,titleImg,price,realPrice,sellerId,";//字段
	    	var sort = "${sort}";//排序字段
	    	var order = "${order}";//排序方式
	    	var categoryId = '${categoryId}';//当前分类id
	    	var key_word = '${key_word}';//关键字
	    	var $moreBtn = $("#j-viewmore");//显示更多按钮
	    	$(function(){
	    		
	    	    //返回顶部按钮逻辑
	    	    $('#j-gotop').on("click", function () {
	    	        window.scrollTo(0, 0); 
	    	    });
	    	    $("#j-viewmore").on("click",function(){
	    	    	loadData();
		    	});
	    	    $("#j-viewmore").click();
		    });
		    //数据加载
		    function loadData(){
				if ($("#j-viewmore").hasClass("disabled")) {
					return;
				}
				$("#j-viewmore").html("努力加载中…").addClass("disabled");
	    		$.ajax({
	                type: "POST",
	                url: "shopGoodsController.do?getGoodsList",
	                data: {categoryId:categoryId, key_word:key_word, field:field, rows:rows,page:page,sort:sort,order:order},
	                dataType: "json",
	                success: function(data){
	                            $.each(data, function(key, value){
		                            if(key=="rows"){
			                            for(var i = 0; i < value.length; i++){
				                        	//console.log("article",value[i]);
			                            	setData(value[i]);
			                            }
			                        }
			                        if(key=="total"){
			                        	total = value;
				                    }
	                            });
	            		        if ((page * rows) >= total){
	            		        	$("#j-viewmore").html("已显示全部").addClass("disabled");
	            					$moreBtn.off('click');
	            				} else {
	            					page++;
	            					$("#j-viewmore").html("查看更多").removeClass("disabled");
	            				}
	                         }
	            });
			}
			//数据填充展示
			function setData(article){
				var price = article.price;
				var realPrice = article.realPrice;
				if(realPrice != null && realPrice !='' && realPrice!=0){
					price = realPrice + ' 促销价';
				}
				
				var html = "";
				html= '<li><a href="shopGoodsController.do?goodsinfo&id='+article.id+'">'
					+ '    <img src="${mediaUrlPrefix }/'+article.titleImg+'" alt="" style="height:150px"/>'
					+ '	   <h3>'+article.title+'</h3>'		
					+ '    <section><div class="new">'
					+ '        <span>￥<em>'
					+ price
					+ '			</em></span>'
					+ '    </section></div></li>';
							
					
				$("#j-goods-container").append(html);
			}
					//格式化CST日期的字串
		      function formatCSTDate(strDate,format){
		        return formatDate(new Date(strDate),format);
		      }
			//格式化日期,
   		   function formatDate(date,format){
		        var paddNum = function(num){
		          num += "";
		          return num.replace(/^(\d)$/,"0$1");
		        }
		        //指定格式字符
		        var cfg = {
		           yyyy : date.getFullYear() //年 : 4位
		          ,yy : date.getFullYear().toString().substring(2)//年 : 2位
		          ,M  : date.getMonth() + 1  //月 : 如果1位的时候不补0
		          ,MM : paddNum(date.getMonth() + 1) //月 : 如果1位的时候补0
		          ,d  : date.getDate()   //日 : 如果1位的时候不补0
		          ,dd : paddNum(date.getDate())//日 : 如果1位的时候补0
		          ,hh : date.getHours()  //时
		          ,mm : date.getMinutes() //分
		          ,ss : date.getSeconds() //秒
		        }
		        format || (format = "yyyy-MM-dd hh:mm:ss");
		        return format.replace(/([a-z])(\1)*/ig,function(m){return cfg[m];});
     	 } 
	    </script>