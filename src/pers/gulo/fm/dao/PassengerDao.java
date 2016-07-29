package pers.gulo.fm.dao;

import java.util.List;

import pers.gulo.fm.domain.Flight;
import pers.gulo.fm.domain.Order;
import pers.gulo.fm.domain.User;
import pers.gulo.fm.exception.FMException;

public interface PassengerDao {	
	/**
	 * 根据出发地和目的地查询航班。
	 * @param start
	 * @param dist
	 * @return 返回Flight表，若发生错误返回null
	 */
	public List<Flight> findFlightByStartAndDist(String start,String dist);
	
	/**
	 * 订机票
	 * @param user
	 * @param flight
	 * @throws Exception
	 */
	public void bookFlight(User user,Flight flight) throws FMException;
	/**
	 * 取消订单
	 * @param order
	 */
	public void bounce(Order order) throws FMException;
	/**
	 * 用户付款
	 * @param user
	 * @param flight
	 */
	public void payFlight(Order order) throws FMException;
	/**
	 * 获取用户的订单列表
	 * @param user
	 * @return
	 */
	public List<Order> getOrderListOfUser(User user);
	/**
	 * 获取用户已付款的订单列表
	 * @param user
	 * @return
	 */
	public List<Order> getPayedOrderListOfUser(User user);
	/**
	 * 获取用户未付款的订单列表
	 * @param user
	 * @return
	 */
	public List<Order> getUnPayedOrderListOfUser(User user);
	
	public List<Flight> getUnBookedFlight(User user);
	
	public Order getOrder(Order order);
}
