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

public class AddAirPlaneServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("airPlaneManageMsg");
		AdminService aService=new AdminServiceImpl();
		AirPlane airPlane=new AirPlane();
		airPlane.setModel(new String(request.getParameter("model").getBytes("ISO-8859-1"),"UTF-8"));
		airPlane.setCapacity(Integer.parseInt(request.getParameter("capacity")));
		try {
			aService.addAirPlane(airPlane);
		} catch (FMException e) {
			// TODO Auto-generated catch block
			request.getSession().setAttribute("airPlaneManageMsg", e.getMessage());
			e.printStackTrace();
		}finally{
			response.sendRedirect(request.getContextPath()+"/airPlaneManage.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
