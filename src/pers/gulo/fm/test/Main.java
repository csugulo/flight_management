package pers.gulo.fm.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import com.sun.org.apache.xpath.internal.operations.Or;

import pers.gulo.fm.dao.AdminDao;
import pers.gulo.fm.dao.PassengerDao;
import pers.gulo.fm.dao.impl.AdminDaoImpl;
import pers.gulo.fm.dao.impl.PassengerDaoImpl;
import pers.gulo.fm.domain.AirPlane;
import pers.gulo.fm.domain.Flight;
import pers.gulo.fm.domain.Order;
import pers.gulo.fm.domain.Statistic;
import pers.gulo.fm.domain.User;
import pers.gulo.fm.exception.FMException;
import pers.gulo.fm.service.AdminService;
import pers.gulo.fm.service.UserService;
import pers.gulo.fm.service.impl.AdminServiceImpl;
import pers.gulo.fm.service.impl.UserServiceImpl;
import pers.gulo.fm.utils.DaoUtils;

public class Main {
	public static void main(String[] args) throws SQLException {
		AdminDao aDao=new AdminDaoImpl();
		Statistic makeStatistic = aDao.makeStatistic();
		System.out.println(makeStatistic);
	}
}
