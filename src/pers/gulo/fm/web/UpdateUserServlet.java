package pers.gulo.fm.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.gulo.fm.domain.User;
import pers.gulo.fm.exception.FMException;
import pers.gulo.fm.service.AdminService;
import pers.gulo.fm.service.impl.AdminServiceImpl;

public class UpdateUserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("userManageMsg");
		String nickname=new String(request.getParameter("nickname").getBytes("ISO-8859-1"),"UTF-8");
		String id=request.getParameter("id");
		int no =Integer.parseInt(request.getParameter("no"));
		
		User user =new User();
		user.setNo(no);
		user.setNickname(nickname);
		user.setID(id);
		
		AdminService aService=new AdminServiceImpl();
		try {
			aService.updateUser(user);
		} catch (FMException e) {
			request.getSession().setAttribute("userManageMsg", e.getMessage());
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath()+"/userManage.jsp");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
