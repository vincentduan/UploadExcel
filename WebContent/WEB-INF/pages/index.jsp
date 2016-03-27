<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript"></script>
<script src="<%=basePath %>resources/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="<%=basePath %>resources/js/tools.js" type="text/javascript"></script>
<script type="text/javascript">
	function AllUsers(){
		var url="<%=basePath %>index/allUsers";
		var flag = "1";
		var data = {"name":name,"flag":flag};
		sendRequestPost(url,data,function(data){
			$("#result").empty();
			$.each(data,function(n,value) {
				$("#result").append(value.name+",");
			 });
			});
	}
</script>
</head>

<body>
<a href="javascript:void(0)" onclick="AllUsers();">所有用户</a>
	<c:forEach items="${users }" var="user">
   	 列表：
   	${user.name }=======${user.address }========${user.phone }<br>
	</c:forEach>
	${user.name }=======${user.address }========${user.phone }
	<br>
	<div id="result"></div>
</body>
</html>
