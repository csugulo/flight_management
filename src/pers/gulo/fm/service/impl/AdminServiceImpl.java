package pers.gulo.fm.service.impl;

import java.util.List;

import pers.gulo.fm.dao.AdminDao;
import pers.gulo.fm.dao.impl.AdminDaoImpl;
import pers.gulo.fm.domain.AirPlane;
import pers.gulo.fm.domain.Flight;
import pers.gulo.fm.domain.Order;
import pers.gulo.fm.domain.Statistic;
import pers.gulo.fm.domain.User;
import pers.gulo.fm.exception.FMException;
import pers.gulo.fm.service.AdminService;

public class AdminServiceImpl implements AdminService{

	@Override
	public List<User> listAllUser() {
		AdminDao aDao=new AdminDaoImpl();
		List<User> queryUsers = aDao.queryUsers();
		return queryUsers;
	}
	
	public void deleteUser(User user) throws FMException{
		AdminDao aDao=new AdminDaoImpl();
		aDao.deleteUser(user);
	}
	
	@Override
	public void updateUser(User user) throws FMException {
		AdminDao aDao=new AdminDaoImpl();
		aDao.updateUser(user);
	}
	
	@Override
	public List<Flight> listAllFlight() {
		AdminDao aDao=new AdminDaoImpl();
		return aDao.queryAllFlight();

	}
	
	@Override
	public void updateFlight(Flight flight) throws FMException {
		AdminDao aDao=new AdminDaoImpl();
		aDao.updateFlight(flight);
		
	}
	
	@Override
	public void addFlight(Flight flight) throws FMException {
		AdminDao aDao=new AdminDaoImpl();
		aDao.insertFlight(flight);
	}
	
	@Override
	public void deleteFlight(Flight flight) throws FMException {
		AdminDao aDao=new AdminDaoImpl();
		aDao.deleteFlight(flight);
	}
	
	@Override
	public List<AirPlane> listAllAirPlane() {
		AdminDao aDao=new AdminDaoImpl();
		
		List<AirPlane> queryAllAirPlane = aDao.queryAllAirPlane();
		return queryAllAirPlane;
	}
	
	@Override
	public void addAirPlane(AirPlane airPlane) throws FMException {
		AdminDao aDao=new AdminDaoImpl();
		aDao.insertAirPlane(airPlane);
	}
	
	@Override
	public void deleteAirPlane(AirPlane airPlane) throws FMException {
		AdminDao aDao=new AdminDaoImpl();
		aDao.deleteAirPlane(airPlane);
	}

	@Override
	public List<Order> listAllOrder() {
		AdminDao aDao=new AdminDaoImpl();
		return aDao.queryAllOrder();
	}
	
	@Override
	public Statistic makeStatistic() {
		AdminDao aDao=new AdminDaoImpl();
		return aDao.makeStatistic();
	}
}
