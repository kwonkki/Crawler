package cebu.dao.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cebu.dao.util.JdbcUtil;

/**
 * Dao的jdbc实现模板
 * 定义了基本的update（包括insert、update）和query操作
 * 其他具体子类如DaoImplTicket继承自该类
 * @author Administrator
 *
 */

public class DaoTemplate {
	
	/**
	 * 更新
	 * @param sql sql语句，参数用?问号占位符
	 * @param args	参数数组
	 * @return
	 */
	public int update(String sql, Object[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			// 有工具类JdbcUtil获取连接，该类使用了Apache common dbcp数据源
			conn = JdbcUtil.getConnection();	
			ps = conn.prepareStatement(sql);
			// 设置参数
			for (int i = 0, stop = args.length; i < stop; i++) {
				Object object = args[i];
					ps.setObject(i+1, object);
			}
System.out.println("update sql: " + ps.toString());
			// 执行update
			count = ps.executeUpdate();
		} catch (SQLException e) {
			JdbcUtil.logError("更新数据库出错： ", e);	// 记录日志
		} finally {
			JdbcUtil.free(rs, ps, conn);	// 释放资源
		}
		return count;
	}
	
	/**
	 * 查询
	 * @param sql sql语句，参数用?问号占位符
	 * @param args 参数数组
	 * @param rowMapper 映射器，将结果集映射为具体类的对象
	 * @return
	 */
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
//System.out.println("select sql: " + ps.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				obj = rowMapper.mapRow(rs);		// 将结果集映射为具体对象
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
