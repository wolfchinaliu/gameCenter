<%--
  Created by IntelliJ IDEA.
  User: aa
  Date: 2015/11/25
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商户的流量</title>
</head>
<body>

<div>
<table>
  <tr>
    <th>全国流量币剩余：</th>
    <td>${merchantInfoBean.data.get(0).countryFlowValue}</td>
  </tr>
  <tr>
    <th>本省流量币剩余：</th>
    <td>${merchantInfoBean.data.get(0).provinceFlowValue}</td>
  </tr>
  <tr>
    <th>全国流量卡剩余：</th>
    <td>${merchantInfoBean.data.get(0).countryFlowCardValue}</td>
  </tr>
  <tr>
    <th>本省流量卡剩余：</th>
    <td>${merchantInfoBean.data.get(0).provinceFlowCardValue}</td>
  </tr>
</table>
</div>

</body>
</html>
