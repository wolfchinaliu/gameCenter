<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<jsp:include page="/inc.jsp"></jsp:include>
<t:base type="jquery,easyui,DatePicker"></t:base>
<!DOCTYPE html>
<html>
<head>
    <meta charset=utf-8>
    <!-- 优先使用最新版本 IE 和 Chrome -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no"/>
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style"/>
    <meta name="format-detection" content="telphone=no, email=no"/>
    <title>制作红包 - 去别的地方发</title>
    <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/0201/lib/normalize.css">
    <link rel="stylesheet" type="text/css" href="plug-in/liuliangbao/css/0201/index.css">
    <script src="plug-in/liuliangbao/js/0201/lib/common.js"></script>
</head>
<body>
<article class="container">
    <!--页头-->
    <header class="header">

    </header>

    <section class="main">

        <p class="main-text">
            ${message}您可前往以下公众号给您的好友好发流量红包，商家会根您发送红包里流量的多少给予一定的补助。关注这些商家有机会获得更多免费流量。
        </p>

<c:forEach var="item" items="${moreRedpacketEntities}">
        <section class="adbox">
            <article class="aditem">
                <a href="${url}makeRedpacketController.do?makeRedpacket&hdid=${item.id}" class="clx">
                    <figure class="aditem-img">
                        <img src="${mediaurl}${item.logoAccount}" alt="" />
                    </figure>
                    <section class="aditem-msg">
                        <h2>${item.accountname}</h2>
                        <p>
                            支持发送${item.flowtype}流量红包<br />
                            还有${item.leftflow}M流量用于补贴
                        </p>
                    </section>
                </a>
            </article>
        </section>
</c:forEach>
    </section>

</article>


</body>
</html>




<script type="text/javascript">
    var flag=true;
    $(document).ready(function(){
        $('#makeredpacket').on('click',function(){
            if(flag==false){
                return;
            }
            flag=false;
            $.ajax({
                type: 'POST',
                url: 'makeRedpacketController.do?doMake',
                dataType: 'JSON',
                data: {
                    "openId":$('#openId').val(),
                    "accountId":$('#accountId').val(),
                    "flowType":$('#flowType').val(),
                    "redpacketNum":$('#redpacketNum').val(),
                    "flowValue":$('#flowValue').val(),
                    "hdId":$('#hdId').val(),
                    "blessing":$('#blessing').val(),
                },
                cache: false,
                error: function () {
                    alert("请重试！");
                    return false;
                },
                success: function (data) {
                    if(data.attributes.result=='true'){
                        var id=data.attributes.id;
                        window.location.href = "makeRedpacketController.do?shareRedpacket&id="+id;
                    }else{
//                        alert(data.attributes.msg);

                        var r=confirm($('#accountname').val()+"的补贴流量不足，此活动后续会不定期开展，敬请期待您可以去别的公众号发。");
                        if(r==true){
                            flag=true;
                        }else{
                            window.location.href = "makeRedpacketController.do?shareRedpacket&id="+id;
                        }

                    }
                }
            });


        });

    });
</script>
</body>
</html>
