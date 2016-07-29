<%@page import="pers.gulo.fm.domain.User"%>
<%@page import="pers.gulo.fm.service.impl.AdminServiceImpl"%>
<%@page import="pers.gulo.fm.service.AdminService"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	User user =(User)session.getAttribute("user");
	if(user==null){
		response.sendRedirect(request.getContextPath()+"/login.jsp");
	}else{
		if(user.getType()!=1){
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}
	}
	int userNo=Integer.parseInt(request.getParameter("userNo"));
	AdminService aService =new AdminServiceImpl();
	List<User> userList= aService.listAllUser();
	for(User u:userList){
		if(u.getNo()==userNo){
			request.setAttribute("user", u);
			break;
		}
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
		<title>修改用户信息</title>
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<script src="jsjquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</head>

	<body>
		<div class="container">
			<h2 class="sub-header">修改用户信息</h2>
			<form class="form-horizontal" role="form" method="post" action="servlet/UpdateUserServlet">
				<div class="form-group">
					<label for="inputPassword" class="col-sm-2 control-label">用户编号</label>
					<div class="col-sm-10">
						<input class="form-control" id="disabledInput" type="text" placeholder="${user.no}" disabled name="no">
						<input type="hidden" name="no" value="${user.no}">
					</div>
				</div>
				
				<div class="form-group">
					<label for="inputPassword" class="col-sm-2 control-label">用户名</label>
					<div class="col-sm-10">
						<input class="form-control" id="disabledInput" type="text" placeholder="${user.username}" disabled name="username">
					</div>
				</div>
								
				<div class="form-group">
					<label class="col-sm-2 control-label">昵称</label>
					<div class="col-sm-10">
						<input class="form-control" id="focusedInput" type="text" name="nickname" value="${user.nickname}">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">身份证号码</label>
					<div class="col-sm-10">
						<input class="form-control" id="focusedInput" type="text" name="id" value="${user.ID}">
					</div>
				</div>
				
			<button type="submit" class="btn btn-default">提交</button>
			</form>
		</div>
	</body>

</html>
