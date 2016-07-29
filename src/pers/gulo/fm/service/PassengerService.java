package pers.gulo.fm.service;

import java.util.List;

import pers.gulo.fm.domain.Flight;
import pers.gulo.fm.domain.User;

public interface PassengerService {
	/**
	 * 用户预订机票
	 * @param user
	 * @param flight
	 */
	public void bookFlight(User user,Flight flight);
	
	/**
	 * 取消预订
	 * @param user
	 * @param flight
	 */
	public void cancelBook(User user,Flight flight);
	/**
	 * 付款
	 * @param user
	 * @param flight
	 */
	public void pay(User user,Flight flight);
	
	public List<Flight> listUnBookedFlight(User user);
}
