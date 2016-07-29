<%@page import="pers.gulo.fm.domain.Flight"%>
<%@page import="pers.gulo.fm.service.impl.AdminServiceImpl"%>
<%@page import="pers.gulo.fm.service.AdminService"%>
<%@page import="pers.gulo.fm.domain.User"%>
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
	int flightNo=Integer.parseInt(request.getParameter("flightNo"));
	AdminService aService =new AdminServiceImpl();
	List<Flight> flightList= aService.listAllFlight();
	for(Flight flight:flightList){
		if(flight.getNo()==flightNo){
			request.setAttribute("flight", flight);
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
		<title>修改航班信息</title>
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<script src="jsjquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</head>

	<body>
		<div class="container">
			<h2 class="sub-header">修改航班信息</h2>
			<form class="form-horizontal" role="form" method="post" action="servlet/UpdateFlightServlet">
				<div class="form-group">
					<label for="inputPassword" class="col-sm-2 control-label">航班编号</label>
					<div class="col-sm-10">
						<input class="form-control" id="disabledInput" type="text" placeholder="${flight.no}" disabled name="no">
						<input type="hidden" name="no" value="${flight.no}">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">班机编号</label>
					<div class="col-sm-10">
						<input class="form-control" id="focusedInput" type="text" name="aNo" value="${flight.airPlane.no}">
					</div>
				</div>
				
				<div class="form-group">
					<label for="inputPassword" class="col-sm-2 control-label">出发地</label>
					<div class="col-sm-10">
						<input class="form-control" id="disabledInput" type="text" placeholder="${flight.start}" disabled name="start">
						<input type="hidden" name="start" value="${flight.start}">
					</div>
				</div>
				
				<div class="form-group">
					<label for="inputPassword" class="col-sm-2 control-label">目的地</label>
					<div class="col-sm-10">
						<input class="form-control" id="disabledInput" type="text" placeholder="${flight.dist}" disabled name="start">
						<input type="hidden" name="dist" value="${flight.dist}">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">价格</label>
					<div class="col-sm-10">
						<input class="form-control" id="focusedInput" type="text" name="price" value="${flight.price}">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">起飞时间</label>
					<div class="col-sm-10">
						<input class="form-control" id="focusedInput" type="text" name="time" value="${flight.time}">
					</div>
				</div>
				
			<button type="submit" class="btn btn-default">提交</button>
			</form>
		</div>
	</body>

</html>
