package pers.gulo.fm.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.gulo.fm.domain.Flight;
import pers.gulo.fm.exception.FMException;
import pers.gulo.fm.service.AdminService;
import pers.gulo.fm.service.impl.AdminServiceImpl;

public class DeleteFlightServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int flightNo=Integer.parseInt(request.getParameter("flightNo"));
		AdminService aService=new AdminServiceImpl();
		Flight flight=new Flight();
		flight.setNo(flightNo);
		try {
			aService.deleteFlight(flight);
		} catch (FMException e) {
			// TODO Auto-generated catch block
			request.getSession().setAttribute("flightManageMsg", e.getMessage());
			e.printStackTrace();
		}finally{
			response.sendRedirect(request.getContextPath()+"/flightManage.jsp");
		}
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
