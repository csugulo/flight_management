package pers.gulo.fm.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.gulo.fm.dao.AdminDao;
import pers.gulo.fm.dao.impl.AdminDaoImpl;
import pers.gulo.fm.domain.AirPlane;
import pers.gulo.fm.exception.FMException;
import pers.gulo.fm.service.AdminService;
import pers.gulo.fm.service.impl.AdminServiceImpl;

public class DeleteAirPlaneServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int no=Integer.parseInt(request.getParameter("airPlaneNo"));
		AirPlane airPlane=new AirPlane();
		airPlane.setNo(no);
		
		AdminService aService=new AdminServiceImpl();
		try {
			aService.deleteAirPlane(airPlane);
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
