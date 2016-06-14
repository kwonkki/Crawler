package cebu.dao.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IRowMapper {

	public Object mapRow(ResultSet rs) throws SQLException;
}