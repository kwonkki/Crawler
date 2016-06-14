package cebu.dao.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 映射器接口
 * 将结果集ResultSet映射为具体类的对象
 * @author Administrator
 *
 */

public interface IRowMapper {

	public Object mapRow(ResultSet rs) throws SQLException;
}