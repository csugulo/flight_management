package pers.gulo.fm.web;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.gulo.fm.domain.AirPlane;
import pers.gulo.fm.domain.Flight;
import pers.gulo.fm.exception.FMException;
import pers.gulo.fm.service.AdminService;
import pers.gulo.fm.service.impl.AdminServiceImpl;

public class AddFlightServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("flightManageMsg");
		AdminService aService=new AdminServiceImpl();
		Flight flight=new Flight();
		AirPlane airPlane=new AirPlane();
		airPlane.setNo(Integer.parseInt(request.getParameter("aNo")));
		flight.setAirPlane(airPlane);
		flight.setStart(new String(request.getParameter("start").getBytes("ISO-8859-1"),"UTF-8"));
		flight.setDist(new String(request.getParameter("dist").getBytes("ISO-8859-1"),"UTF-8"));
		flight.setPrice(Float.parseFloat(request.getParameter("price")));
		flight.setTime(Timestamp.valueOf(request.getParameter("time")));
		
		try {
			aService.addFlight(flight);
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
