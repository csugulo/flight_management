package pers.gulo.fm.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import pers.gulo.fm.dao.UserDao;
import pers.gulo.fm.dao.resultSetHandler.UserResultSetHandler;
import pers.gulo.fm.domain.Flight;
import pers.gulo.fm.domain.User;
import pers.gulo.fm.exception.FMException;
import pers.gulo.fm.utils.DaoUtils;

public class UserDaoImpl implements UserDao {
	
	private static final String CHECK_LOGIN_SQL="select * from T_USER where U_USERNAME=? and U_PASSWORD=?";
	
	private static final String INSERT_USER_SQL="insert into T_USER values (null,?,?,?,?,0,0)";
	
	private static final String RECHARGE_SQL="update T_USER set U_BALANCE =U_BALANCE + ? where U_NO=?";
	
	@Override
	public User login(String username, String password) {
		User user=null;
		QueryRunner runner =new QueryRunner(DaoUtils.getSource());
		try {
			List<User> list=runner.query(CHECK_LOGIN_SQL,new UserResultSetHandler(), username,password);
			if(list!=null&&list.size()!=0) user=list.get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	@Override
	public void insertUser(User user) throws FMException {
		QueryRunner runner =new QueryRunner(DaoUtils.getSource());
		try {
			runner.update(INSERT_USER_SQL,user.getUsername(),user.getPassword(),user.getNickname(),user.getID());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FMException("发生错误或用户名被占用！请重新注册！");
		}
		
	}
	
	@Override
	public void reCharge(User user, float number) {
		QueryRunner runner =new QueryRunner(DaoUtils.getSource());
		try {
			runner.update(RECHARGE_SQL,number,user.getNo());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
