package cebu.util;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBUtil {
/*	private static final String URL = "jdbc:mysql://localhost:3306/cebu";
	private static final String USER = "root";
	private static final String PASSWORD = "root@123";*/
	
	// 使用Apache的框架，提供数据源，实现了DataSource接口
	private static DataSource myDataSource = null;
	
	private static final Logger logger = LoggerFactory.getLogger("dbLog");
	
	private static class DBUtilInstanceHolder {
		private static DBUtil DB_Util = new DBUtil();
	}

	public static DBUtil getInstance() {
		return DBUtilInstanceHolder.DB_Util;
	}
	
	/**
	 * 私有构造方法
	 */
	private DBUtil() {
		
	}
	
	/**
	 * 静态代码注册驱动
	 */
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			// 使用Apache的框架，提供数据源，实现了DataSource接口
			Properties prop = new Properties();
			InputStream is = DBUtil.class.getClassLoader().
					getResourceAsStream("dbcpconfig.properties");
			prop.load(is);
			myDataSource = BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 获取datasource
	 * @return
	 */
	public static final DataSource getDataSource() {
		return myDataSource;
	}
	
	
	/**
	 * 获取数据库连接
	 * @return
	 */
	public final Connection getConnection() {
		Connection conn = null;
		try {
			// conn = DriverManager.getConnection(URL, USER, PASSWORD);
			conn = myDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
	/*
	 * 执行数据库更新操作
	 */
	public int exeSql(String sql) throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn=null;
		int resultNum = -1;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			resultNum = stmt.executeUpdate(sql);
		} catch(SQLException ex) {
			logger.error("执行数据库更新出错： " + ex.toString() + " | " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			this.free(rs, stmt, conn);
		}
		return resultNum;
	}
	
	/*
	 * 类似于SqlReader
	 * @创建resultset游标，调用此方法应该习惯调用rs.close()方法释放rs
	 * @param sql
	 * @return 每次调用此方法可以返回不同的rs记录集，避免不能嵌套查询的问题
	 */
	public List<Object> query(String sql) throws Exception {
		List list = new ArrayList();
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn=null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			 ResultSetMetaData md = rs.getMetaData();
		     int columnCount = md.getColumnCount();
		     while (rs.next()) {
	            Map rowData = new HashMap();
	            for (int i = 1; i <= columnCount; i++) {
	                rowData.put(md.getColumnName(i), rs.getObject(i));
	            }
	            list.add(rowData);
	        }
		} catch(SQLException e) {
			logger.error("使用sql语句查询数据库出错： " + e.toString() + " | " + e.getMessage());
			e.printStackTrace();
		}
		finally {
			this.free(rs, stmt, conn);
		}
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 关闭相关资源
	 * @param rs 
	 * @param st
	 * @param conn
	 */
	private final void free(ResultSet rs, Statement stmt, Connection conn) {
		this.free(rs);
		this.free(stmt);
		this.free(conn);
	}
	
	/**
	 * 关闭数据库连接
	 * @param conn
	 */
	private final void free(Connection conn) {
		if(conn == null) {
			return;
		}
		try {
			if(!conn.isClosed()) 
				conn.close();
		} catch (SQLException e) {
			logger.error("关闭数据库连接出错： " + e.getMessage());
		}
	}
	
	/**
	 * add by liupeng 20151006
	 * 关闭Statement
	 * @param stmt
	 */
	private final void free(Statement stmt) {
		if(stmt == null) {
			return;
		}
		try {
			if(!stmt.isClosed()) 
				stmt.close();
		} catch (SQLException e) {
			logger.error("关闭Statement出错： " + e.getMessage());
		}
	}
	
	/**
	 * add by liupeng 20151006
	 * 关闭Resultset
	 * @param rs
	 */
	private final void free(ResultSet rs) {
		if(rs == null) {
			return;
		}
		try {
			if(!rs.isClosed()) 
				rs.close();
		} catch (SQLException e) {
			logger.error("关闭Resultset出错： " + e.getMessage());
		}
	}
	
}
