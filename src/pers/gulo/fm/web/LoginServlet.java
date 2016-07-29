package pers.gulo.fm.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pers.gulo.fm.domain.User;
import pers.gulo.fm.service.UserService;
import pers.gulo.fm.service.impl.UserServiceImpl;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			HttpSession session = request.getSession();
			String username= request.getParameter("username");
			String password =request.getParameter("password");
			
			UserService uService=new UserServiceImpl();
			User user = uService.login(username, password);
			if(user!=null){
				session.setAttribute("user", user);
				session.removeAttribute("loginMsg");
				
				if(user.getType()==1){ //管理员
					response.sendRedirect(request.getContextPath()+"/manage.jsp");
				}else{
					response.sendRedirect(request.getContextPath()+"/user.jsp");
				}
			}else{
				request.getSession().setAttribute("loginMsg", "用户名或密码错误！");
				response.sendRedirect(request.getContextPath()+"/login.jsp");
			}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
