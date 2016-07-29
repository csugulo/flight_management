package pers.gulo.fm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import pers.gulo.fm.dao.PassengerDao;
import pers.gulo.fm.dao.resultSetHandler.CountResultSetHandler;
import pers.gulo.fm.dao.resultSetHandler.FlightResultSetHandler;
import pers.gulo.fm.dao.resultSetHandler.FloatResultSetHandler;
import pers.gulo.fm.dao.resultSetHandler.OrderResultSetHandler;
import pers.gulo.fm.domain.Flight;
import pers.gulo.fm.domain.Order;
import pers.gulo.fm.domain.User;
import pers.gulo.fm.exception.FMException;
import pers.gulo.fm.utils.DaoUtils;

public class PassengerDaoImpl implements PassengerDao {
	
	private static final String FIND_FLIGHT_SQL="select * from T_FLIGHT f,T_AIRPLANE a where f.F_A_NO=a.A_NO";
	
	private static final String BOOK_FLIGHT_SQL="insert into T_ORDER values(null,?,?,0,0,?)";
	
	private static final String INSERT_ORDER="insert into T_ORDER values (null,?,?,0,0,?)";
	
	private static final String UPDATE_PSG_NUM_SQL="update T_FLIGHT set F_PSG_NUM = F_PSG_NUM + 1 where F_NO = ?";
	
	private static final String BOUNCE_SQL="update T_ORDER set O_IS_CANCELED = 1 where O_NO = ?";
	
	private static final String BOUNCE_PSG_NUM_SQL="update T_FLIGHT set F_PSG_NUM = F_PSG_NUM -1 where F_NO = ?";
	
	private static final String REFUND_SQL="update T_USER set U_BALANCE = U_BALANCE + ? * (select O_IS_PAYED from T_ORDER where O_NO= ?) where U_NO = ?";
	
	private static final String UPDATE_IS_PAYED_SQL="update T_ORDER set O_IS_PAYED = 1 where O_NO = ?";
	
	private static final String PAY_SQL="update T_USER set U_BALANCE = U_BALANCE - ? where U_NO = ?";
	
	private static final String GET_ORDER_LIST_SQL="select * from T_ORDER o,T_USER u,T_FLIGHT f,T_AIRPLANE a where o.O_U_NO=u.U_NO and o.O_F_NO=f.F_NO and f.F_A_NO=a.A_NO and O_U_NO = ?";
	
	private static final String GET_PAYED_ORDER_LIST_SQL="select * from T_ORDER o,T_USER u,T_FLIGHT f,T_AIRPLANE a where o.O_U_NO=u.U_NO and o.O_F_NO=f.F_NO and f.F_A_NO=a.A_NO and O_IS_PAYED=1 and O_U_NO = ?";

	private static final String GET_UNPAYED_ORDER_LIST_SQL="select * from T_ORDER o,T_USER u,T_FLIGHT f,T_AIRPLANE a where o.O_U_NO=u.U_NO and o.O_F_NO=f.F_NO and f.F_A_NO=a.A_NO and O_IS_PAYED=0 and O_U_NO = ?";
	
	private static final String GET_UNBOOKED_FLIGHT="select * from T_FLIGHT f,T_AIRPLANE a where f.F_A_NO=a.A_NO and f.F_NO not in (select O_F_NO from T_ORDER o where o.O_U_NO = ?)";
	
	private static final String GET_ORDER_SQL="select * from T_ORDER o,T_USER u,T_FLIGHT f,T_AIRPLANE a where o.O_U_NO=u.U_NO and o.O_F_NO=f.F_NO and f.F_A_NO=a.A_NO and O_NO = ?";
	
	private static final String QUERY_BALANCE="select U_BALANCE from T_USER where U_NO = ?";
	
	
	@Override
	public List<Flight> findFlightByStartAndDist(String start, String dist) {
		List<Flight> flightList=null;
		QueryRunner runner=new QueryRunner(DaoUtils.getSource());
		String SQL=new String(FIND_FLIGHT_SQL);
		if(start!=null&&!start.trim().equals("")){
			SQL+=" and F_START='"+start.trim()+"'";
		}
		if(dist!=null&&!dist.trim().equals("")){
			SQL+=" and F_DIST='"+dist+"'";
		}
		System.out.println(SQL);
		try {
			flightList=runner.query(SQL,new FlightResultSetHandler());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flightList;
	}

	@Override
	public void bookFlight(User user, Flight flight) throws FMException {
			QueryRunner runner =new QueryRunner(DaoUtils.getSource());
			try {
				runner.update(BOOK_FLIGHT_SQL,user.getNo(),flight.getNo(),new Date());
				runner.update(UPDATE_PSG_NUM_SQL,flight.getNo());
			} catch (SQLException e) {
				e.printStackTrace();
				throw new FMException("预订失败！");

			}
	}

	@Override
	public void bounce(Order order) throws FMException {
		QueryRunner runner=new QueryRunner(DaoUtils.getSource());
		try {
			runner.update(BOUNCE_SQL, order.getNo());
			runner.update(BOUNCE_PSG_NUM_SQL,order.getFlight().getNo());
			if (order.getIsPayed()) {
				runner.update(REFUND_SQL,order.getFlight().getPrice(),order.getNo(),order.getUser().getNo());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FMException("退票失败！");
		}
		
	}

	@Override
	public void payFlight(Order order)  throws FMException{
		QueryRunner runner=new QueryRunner(DaoUtils.getSource());
		
		try {
			Float balance = runner.query(QUERY_BALANCE, new FloatResultSetHandler(),order.getUser().getNo());
			if(balance<order.getFlight().getPrice()) throw new FMException("付款失败！余额不足！");
			runner.update(UPDATE_IS_PAYED_SQL, order.getNo());
			runner.update(PAY_SQL,order.getFlight().getPrice(),order.getUser().getNo());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FMException("付款失败！余额不足！");
		}
		
	}

	@Override
	public List<Order> getOrderListOfUser(User user) {
		List<Order> orderList=null;
		QueryRunner runner=new QueryRunner(DaoUtils.getSource());
		try {
			orderList = runner.query(GET_ORDER_LIST_SQL, new OrderResultSetHandler() , user.getNo());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderList;
	}
	
	@Override
	public List<Order> getPayedOrderListOfUser(User user) {
		List<Order> orderList=null;
		QueryRunner runner=new QueryRunner(DaoUtils.getSource());
		try {
			orderList = runner.query(GET_PAYED_ORDER_LIST_SQL, new OrderResultSetHandler() , user.getNo());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderList;
	}
	@Override
	public List<Order> getUnPayedOrderListOfUser(User user) {
		List<Order> orderList=null;
		QueryRunner runner=new QueryRunner(DaoUtils.getSource());
		try {
			orderList = runner.query(GET_UNPAYED_ORDER_LIST_SQL, new OrderResultSetHandler() , user.getNo());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderList;
	}
	
	@Override
	public List<Flight> getUnBookedFlight(User user) {
		List<Flight> unBookedFlightList=new ArrayList<Flight>();
		QueryRunner runner=new QueryRunner(DaoUtils.getSource());
		try {
			System.err.println(user.getNo()+"---------------------------");
			unBookedFlightList = runner.query(GET_UNBOOKED_FLIGHT, new FlightResultSetHandler(), user.getNo());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return unBookedFlightList;
	}
	@Override
	public Order getOrder(Order order) {
		QueryRunner runner=new QueryRunner(DaoUtils.getSource());
		Order o=new Order();
		try {
			List<Order> list = runner.query(GET_ORDER_SQL, new OrderResultSetHandler(),order.getNo());
			o=list.get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}
}
