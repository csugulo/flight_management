package pers.gulo.fm.dao.resultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.ResultSetHandler;

public class FloatResultSetHandler implements ResultSetHandler<Float>{

	@Override
	public Float handle(ResultSet rs) throws SQLException {
		Float result=0f;
		while(rs.next()){
			result=rs.getFloat(1);
		}
		return result;
	}

}
