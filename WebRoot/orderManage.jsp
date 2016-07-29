<%@page import="pers.gulo.fm.domain.Order"%>
<%@page import="pers.gulo.fm.service.impl.AdminServiceImpl"%>
<%@page import="pers.gulo.fm.service.AdminService"%>
<%@page import="pers.gulo.fm.domain.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	User user =(User)session.getAttribute("user");
	if(user==null){
		response.sendRedirect(request.getContextPath()+"/login.jsp");
	}else{
		if(user.getType()!=1){
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}
	}
	AdminService aService=new AdminServiceImpl();
	List<Order> orderList=aService.listAllOrder();
	request.setAttribute("orderList", orderList);
	request.setAttribute("orderListLength", orderList.size());
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
	<title>机票预订系统控制台</title>

    <!-- Bootstrap core CSS -->
    <link href="./Dashboard Template for Bootstrap_files/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="./Dashboard Template for Bootstrap_files/dashboard.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="./Dashboard Template for Bootstrap_files/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="http://v3.bootcss.com/examples/dashboard/#">机票预订系统控制台</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="servlet/LogoutServlet">退出</a></li>
          </ul>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li><a href="manage.jsp">概况</a></li>
            <li><a href="userManage.jsp">用户管理</a></li>
            <li><a href="flightManage.jsp">航班管理 </a></li>
            <li><a href="airPlaneManage.jsp">班机管理</a></li>
          </ul>
          <ul class="nav nav-sidebar">
          	<li class="active"><a href="#">订单管理<span class="sr-only">(current)</span></a></li>
            <li><a href="statisticManage.jsp">数据统计</a></li>
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <c:if test="${sessionScope.orderManageMsg!=null}">
			<div class="alert alert-info" role="alert">
				${orderManageMsg}
				${sessionScope.orderManageMsg=null}
			</div>
			</c:if>
          <h2 class="sub-header">当前共有${orderListLength}条订单信息</h2>
          <div class="table-responsive">
        	<table class="table table-striped">
              <thead>
                <tr>
                  <th>订单编号</th>
                  <th>班机型号</th>
                  <th>用户名</th>
                  <th>出发地</th>
                  <th>目的地</th>
                  <th>价格</th>
                  <th>是否付款</th>
                  <th>是否取消</th>
                  <th>起飞时间</th>
                  <th>订单生成时间</th>
                </tr>
              </thead>
              <tbody>
			<c:forEach var="o" items="${orderList}">
				 <tr>
                  <td><c:out value="${o.no}"/></td>
                  <td><c:out value="${o.flight.airPlane.model}"/></td>
                  <td><c:out value="${o.user.username}"/></td>
                  <td><c:out value="${o.flight.start}"/></td>
                  <td><c:out value="${o.flight.dist}"/></td>
                  <td><c:out value="${o.flight.price}"/></td>
                  <td>
                  	<c:if test="${o.isPayed}">
                  		<c:out value="是"/>
                  	</c:if>
                  	<c:if test="${!o.isPayed}">
                  		<c:out value="否"/>
                  	</c:if>
                  </td>
                  <td>
                  	<c:if test="${o.isCanceled}">
                  		<c:out value="是"/>
                  	</c:if>
                  	<c:if test="${!o.isCanceled}">
                  		<c:out value="否"/>
                  	</c:if>
                  </td>
                  <td><c:out value="${o.flight.time}"/></td>
                  <td><c:out value="${o.time}"/></td>

                </tr>
			</c:forEach>
                
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="./Dashboard Template for Bootstrap_files/jquery.min.js"></script>
    <script src="./Dashboard Template for Bootstrap_files/bootstrap.min.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <script src="./Dashboard Template for Bootstrap_files/holder.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="./Dashboard Template for Bootstrap_files/ie10-viewport-bug-workaround.js"></script>
  

</body></html>

