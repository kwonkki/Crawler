package cebu.dao.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cebu.util.db.JdbcUtil;

public class DaoTemplate {
	public int update(String sql, Object[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0, stop = args.length; i < stop; i++) {
				Object object = args[i];
					ps.setObject(i+1, object);
			}
			count = ps.executeUpdate();
		} catch (SQLException e) {
			JdbcUtil.logError("更新数据库出错： ", e);
		} finally {
			JdbcUtil.free(rs, ps, conn);
		}
		return count;
	}
	
	public ArrayList<Object> query(String sql, Object[] args, IRowMapper rowMapper) {
		ArrayList<Object> objects = new ArrayList<Object>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Object obj = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0, stop = args.length; i < stop; i++) {
				ps.setObject(i+1, args[i]);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				obj = rowMapper.mapRow(rs);
				objects.add(obj);
			}
		} catch (SQLException e) {
			JdbcUtil.logError("查询出错： ", e);
		} finally {
			JdbcUtil.free(rs, ps, conn);
		}
		return objects;
	}
}
