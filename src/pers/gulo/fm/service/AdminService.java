package pers.gulo.fm.service;

import java.util.List;

import pers.gulo.fm.domain.AirPlane;
import pers.gulo.fm.domain.Flight;
import pers.gulo.fm.domain.Order;
import pers.gulo.fm.domain.Statistic;
import pers.gulo.fm.domain.User;
import pers.gulo.fm.exception.FMException;

public interface AdminService{
	
	public List<User> listAllUser();
	
	public void deleteUser(User user) throws FMException;
	
	public void updateUser(User user) throws FMException;
	
	public List<Flight> listAllFlight();
	
	public void updateFlight(Flight flight) throws FMException;
	
	public void addFlight(Flight flight)  throws FMException;
	
	public void deleteFlight(Flight flight) throws FMException;
	
	public List<AirPlane> listAllAirPlane();

	public void addAirPlane(AirPlane airPlane) throws FMException ;

	public void deleteAirPlane(AirPlane airPlane) throws FMException;
	
	public List<Order> listAllOrder();
	
	public Statistic makeStatistic();

}
