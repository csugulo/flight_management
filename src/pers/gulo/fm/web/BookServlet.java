package pers.gulo.fm.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.gulo.fm.dao.PassengerDao;
import pers.gulo.fm.dao.impl.PassengerDaoImpl;
import pers.gulo.fm.domain.Flight;
import pers.gulo.fm.domain.User;
import pers.gulo.fm.exception.FMException;

public class BookServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user =(User) request.getSession().getAttribute("user");
		PassengerDao pDao=new PassengerDaoImpl();
		int flightNo=Integer.parseInt(request.getParameter("flightNo"));
		Flight flight=new Flight();
		flight.setNo(flightNo);
		try {
			pDao.bookFlight(user, flight);
			request.getSession().setAttribute("bookFlightMsg", "预订成功！");
		} catch (FMException e) {
			request.getSession().setAttribute("bookFlightMsg", "预订失败！余票不足！");
			e.printStackTrace();
		}finally{
			response.sendRedirect(request.getContextPath()+"/bookFlight.jsp");
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
