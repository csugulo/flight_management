<%@page import="pers.gulo.fm.domain.User"%>
<%@page import="pers.gulo.fm.service.impl.AdminServiceImpl"%>
<%@page import="pers.gulo.fm.service.AdminService"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	User user =(User)session.getAttribute("user");
	if(user==null){
		response.sendRedirect(request.getContextPath()+"/login.jsp");
	}
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<meta name="description" content="">
		<meta name="author" content="">
		<title>充值</title>
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<script src="jsjquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</head>

	<body>
		<div class="container">
			<h2 class="sub-header">充值</h2>
			<form class="form-horizontal" role="form" method="post" action="servlet/RechargeServlet">
			
								
				<div class="form-group">
					<label class="col-sm-2 control-label">充值金额</label>
					<div class="col-sm-10">
						<input class="form-control" id="focusedInput" type="text" name="number">
						<input type="hidden" name="userNo" value="${sessionScope.user.no}">
					</div>
				</div>
				
			<button type="submit" class="btn btn-default">提交</button>
			</form>
		</div>
	</body>

</html>
