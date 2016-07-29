package pers.gulo.fm.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import pers.gulo.fm.dao.AdminDao;
import pers.gulo.fm.dao.resultSetHandler.AirPlaneResultSetHandler;
import pers.gulo.fm.dao.resultSetHandler.CountResultSetHandler;
import pers.gulo.fm.dao.resultSetHandler.FlightResultSetHandler;
import pers.gulo.fm.dao.resultSetHandler.FloatResultSetHandler;
import pers.gulo.fm.dao.resultSetHandler.OrderResultSetHandler;
import pers.gulo.fm.dao.resultSetHandler.UserResultSetHandler;
import pers.gulo.fm.domain.AirPlane;
import pers.gulo.fm.domain.Flight;
import pers.gulo.fm.domain.Order;
import pers.gulo.fm.domain.Statistic;
import pers.gulo.fm.domain.User;
import pers.gulo.fm.exception.FMException;
import pers.gulo.fm.utils.DaoUtils;

public class AdminDaoImpl implements AdminDao{
	
	private static final String INSERT_AIRPLANE_SQL="insert into T_AIRPLANE values (null,?,?)";
	
	private static final String DELETE_AIRPLANE_SQL="delete from T_AIRPLANE where A_NO=?";
	
	private static final String INSERT_FLIGHT_SQL="insert into T_FLIGHT values(null,?,?,?,?,?,0)";
	
	private static final String UPDATE_FLIGHT_SQL="update T_FLIGHT set F_A_NO=?,F_START=?,F_DIST=?,F_PRICE=?,F_TIME=? where F_NO = ?";
	
	private static final String QUERY_USERS="select * from T_USER";
	
	private static final String DELETE_FLIGHT="delete from T_FLIGHT where F_NO = ?";
	
	private static final String QUERY_ALL_FLIGHT="select * from T_FLIGHT f,T_AIRPLANE a where a.A_NO=f.F_A_NO";
	
	private static final String QUERY_ALL_AIRPLANE="select * from T_AIRPLANE";
	
	private static final String DELETE_USER_SQL="delete from T_USER where U_NO=?";
	
	private static final String UPDATE_USER_SQL="update T_USER set U_NICKNAME=? ,U_ID=? where U_NO =?";
	
	private static final String QUERY_ALL_ORDER="select * from T_ORDER o,T_USER u,T_FLIGHT f,T_AIRPLANE a where o.O_U_NO=u.U_NO and o.O_F_NO=f.F_NO and f.F_A_NO=a.A_NO";
	
	private static final String QUERY_FLIGHT_NUM_WEEK_SQL="select count(*) from T_FLIGHT where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(F_TIME)";
	
	private static final String QUERY_FLIGHT_NUM_MONTH_SQL="select count(*) from T_FLIGHT where DATE_SUB(CURDATE(), INTERVAL 1 MONTH) <= date(F_TIME)";
	
	private static final String QUERY_FLIGHT_NUM_SQL="select count(*) from T_FLIGHT";
	
	private static final String QUERY_ORDER_NUM_WEEK_SQL="select count(*) from T_ORDER where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(O_TIME)";
	
	private static final String QUERY_ORDER_NUM_MONTH_SQL="select count(*) from T_ORDER where DATE_SUB(CURDATE(), INTERVAL 1 MONTH) <= date(O_TIME)";
	
	private static final String QUERY_ORDER_NUM_SQL="select count(*) from T_ORDER";
	
	private static final String QUERY_INCOME_WEEK_SQL="select sum(F_PRICE) from T_ORDER o,T_FLIGHT f where o.O_F_NO=f.F_NO and O_IS_PAYED=1 and O_IS_CANCELED=0 and DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(O_TIME)";

	private static final String QUERY_INCOME_MONTH_SQL="select sum(F_PRICE) from T_ORDER o,T_FLIGHT f where o.O_F_NO=f.F_NO and O_IS_PAYED=1 and O_IS_CANCELED=0 and DATE_SUB(CURDATE(), INTERVAL 1 MONTH) <= date(O_TIME)";
	
	private static final String QUERY_INCOME_SQL="select sum(F_PRICE) from T_ORDER o,T_FLIGHT f where o.O_F_NO=f.F_NO and O_IS_PAYED=1 and O_IS_CANCELED=0";
	@Override
	public void insertAirPlane(AirPlane airPlane) throws FMException {
		QueryRunner runner= new QueryRunner(DaoUtils.getSource());
		try {
			runner.update(INSERT_AIRPLANE_SQL, airPlane.getModel(),airPlane.getCapacity());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FMException("插入信息失败！");
		}
	}

	@Override
	public void deleteAirPlane(AirPlane airPlane) throws FMException{
		QueryRunner runner= new QueryRunner(DaoUtils.getSource());
		try {
			runner.update(DELETE_AIRPLANE_SQL,airPlane.getNo());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FMException("外键约束！删除班机失败！");
		}
	}

	@Override
	public void insertFlight(Flight flight) throws FMException{
		QueryRunner runner= new QueryRunner(DaoUtils.getSource());
		try {
			runner.update(INSERT_FLIGHT_SQL, flight.getAirPlane().getNo(),flight.getStart(),flight.getDist(),flight.getPrice(),flight.getTime());
			System.out.println(flight);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FMException("添加航班失败！ 没有该班机！");
		}
		
		
	}

	@Override
	public void updateFlight(Flight flight) throws FMException{
		QueryRunner runner =new QueryRunner(DaoUtils.getSource());
		try {
			runner.update(UPDATE_FLIGHT_SQL,flight.getAirPlane().getNo(),flight.getStart(),flight.getDist(),flight.getPrice(),flight.getTime(),flight.getNo());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FMException("修改航班信息失败！");
		}
	}

	@Override
	public List<User> queryUsers() {
		List<User> users =null;
		QueryRunner runner= new QueryRunner(DaoUtils.getSource());
		try {
			users= runner.query(QUERY_USERS, new UserResultSetHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public void deleteFlight(Flight flight) throws FMException {
		QueryRunner runner=new QueryRunner(DaoUtils.getSource());
		try {
			runner.update(DELETE_FLIGHT,flight.getNo());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FMException("外键约束，不能删除");
		}
	}

	@Override
	public List<Flight> queryAllFlight() {
		List<Flight> flights=null;
		QueryRunner runner=new QueryRunner(DaoUtils.getSource());
		try {
			flights=runner.query(QUERY_ALL_FLIGHT, new FlightResultSetHandler());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flights;
	}

	@Override
	public List<AirPlane> queryAllAirPlane() {
		List<AirPlane> airPlanes=null;
		QueryRunner runner =new QueryRunner(DaoUtils.getSource());
		try {
			airPlanes = runner.query(QUERY_ALL_AIRPLANE, new AirPlaneResultSetHandler());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return airPlanes;
	}

	@Override
	public void deleteUser(User user) throws FMException {
		QueryRunner runner= new QueryRunner(DaoUtils.getSource());
		try {
			runner.update(DELETE_USER_SQL,user.getNo());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FMException("由于外键约束，删除失败！");
		}
		
		
	}
	
	@Override
	public void updateUser(User user) throws FMException {
		QueryRunner runner= new QueryRunner(DaoUtils.getSource());
		try {
			runner.update(UPDATE_USER_SQL,user.getNickname(),user.getID(),user.getNo());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FMException("修改用户信息失败！");
		}
	}
	
	@Override
	public List<Order> queryAllOrder() {
		List<Order> orderList=null;
		QueryRunner runner=new QueryRunner(DaoUtils.getSource());
		try {
			orderList = runner.query(QUERY_ALL_ORDER, new OrderResultSetHandler());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderList;
	}
	
	@Override
	public Statistic makeStatistic() {
		Statistic statistic=new Statistic();
		QueryRunner runner= new QueryRunner(DaoUtils.getSource());
		try {
			statistic.setWeekFlight(runner.query(QUERY_FLIGHT_NUM_WEEK_SQL, new CountResultSetHandler()));
			statistic.setMonthFlight(runner.query(QUERY_FLIGHT_NUM_MONTH_SQL, new CountResultSetHandler()));
			statistic.setTotalFlight(runner.query(QUERY_FLIGHT_NUM_SQL, new CountResultSetHandler()));
			
			statistic.setWeekOrder(runner.query(QUERY_ORDER_NUM_WEEK_SQL, new CountResultSetHandler()));
			statistic.setMonthOrder(runner.query(QUERY_ORDER_NUM_MONTH_SQL, new CountResultSetHandler()));
			statistic.setTotalOrder(runner.query(QUERY_ORDER_NUM_SQL, new CountResultSetHandler()));
			
			statistic.setWeekIncome(runner.query(QUERY_INCOME_WEEK_SQL, new FloatResultSetHandler()));
			statistic.setMonthIncome(runner.query(QUERY_INCOME_MONTH_SQL, new FloatResultSetHandler()));
			statistic.setTotalIncome(runner.query(QUERY_INCOME_SQL, new FloatResultSetHandler()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statistic;
	}


}
