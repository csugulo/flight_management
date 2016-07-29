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

public class RechargeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService uService=new UserServiceImpl();
		User user=new User();
		user.setNo(Integer.parseInt(request.getParameter("userNo")));
		float number=Float.parseFloat(request.getParameter("number"));
		uService.recharge(user,number);
		User sessionUser=(User) request.getSession().getAttribute("user");
		sessionUser.setBalance(sessionUser.getBalance()+number);
		response.sendRedirect(request.getContextPath()+"/myWallet.jsp");
		request.getSession().setAttribute("myWalletMsg", "充值成功！");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
