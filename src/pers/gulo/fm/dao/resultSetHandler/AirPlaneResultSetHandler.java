package pers.gulo.fm.dao.resultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;

import pers.gulo.fm.domain.AirPlane;

public class AirPlaneResultSetHandler implements ResultSetHandler<List<AirPlane>>{
	@Override
	public List<AirPlane> handle(ResultSet rs) throws SQLException {
		List<AirPlane> airPlanes =new ArrayList<AirPlane>();
		while (rs.next()) {
			AirPlane airPlane=new AirPlane();
			airPlane.setNo(rs.getInt("A_NO"));
			airPlane.setModel(rs.getString("A_MODEL"));
			airPlane.setCapacity(rs.getInt("A_CAPACITY"));
			airPlanes.add(airPlane);
		}
		return airPlanes;
	}

}
