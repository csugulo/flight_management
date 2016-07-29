package pers.gulo.fm.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.gulo.fm.domain.User;
import pers.gulo.fm.exception.FMException;
import pers.gulo.fm.service.UserService;
import pers.gulo.fm.service.impl.UserServiceImpl;

public class AddUserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService userService=new UserServiceImpl();
		User user =new User();
		user.setUsername(new String(request.getParameter("username").getBytes("ISO-8859-1"),"UTF-8"));
		user.setPassword(request.getParameter("password"));
		user.setNickname(new String(request.getParameter("nickname").getBytes("ISO-8859-1"),"UTF-8"));
		user.setID(request.getParameter("id"));
		try {
			userService.addUser(user);
			request.getSession().setAttribute("loginMsg", "注册成功！");
		} catch (FMException e) {
			// TODO Auto-generated catch block
			request.getSession().setAttribute("loginMsg", e.getMessage());
			e.printStackTrace();
		}finally{
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
