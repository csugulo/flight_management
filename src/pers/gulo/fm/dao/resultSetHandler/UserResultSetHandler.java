package pers.gulo.fm.dao.resultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;

import pers.gulo.fm.domain.User;

public class UserResultSetHandler implements ResultSetHandler<List<User>> {
	@Override
	public List<User> handle(ResultSet rs) throws SQLException {
		List<User> userList=new ArrayList<User>();
		while(rs.next()){
			User user =new User();
			user.setUsername(rs.getString("U_USERNAME"));
			user.setPassword(rs.getString("U_PASSWORD"));
			user.setNo(rs.getInt("U_NO"));
			user.setID(rs.getString("U_ID"));
			user.setNickname(rs.getString("U_NICKNAME"));
			user.setType(rs.getInt("U_TYPE"));
			user.setBalance(rs.getFloat("U_BALANCE"));
			userList.add(user);
		}
		return userList;
	}
}
