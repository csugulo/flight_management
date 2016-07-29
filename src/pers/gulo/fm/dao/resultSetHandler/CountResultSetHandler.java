package pers.gulo.fm.dao.resultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.ResultSetHandler;

public class CountResultSetHandler implements ResultSetHandler<Integer>{

	@Override
	public Integer handle(ResultSet rs) throws SQLException {
		rs.next();
		return rs.getInt(1);
	}

}
