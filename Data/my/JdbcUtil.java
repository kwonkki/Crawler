package com.utils;

import java.io.*;
import java.sql.*;
import java.util.Properties;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;
//import com.jdbc.datasource.MyDataSource;

public class JdbcUtil {
/*	private static final String URL = "jdbc:postgresql://localhost:5432/erhaijava";
	private static final String USER = "postgres";
	private static final String PASSWORD = "root@123";*/
	
	// 使用自定义数据源
	// private static MyDataSource myDataSource = null;
	
	// 使用Apache的框架，提供数据源，实现了DataSource接口
	private static DataSource myDataSource = null;
	
	/**
	 * 私有构造方法
	 */
	private JdbcUtil() {
		
	}
	
	/**
	 * 静态代码注册驱动
	 */
	static {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			
			// 使用自定义数据源
			// myDataSource = new MyDataSource();
			
			// 使用Apache的框架，提供数据源，实现了DataSource接口
			Properties prop = new Properties();
			InputStream is = JdbcUtil.class.getClassLoader().
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
	public static final DataSource GetDataSource() {
		return myDataSource;
	}
	
	
	/**
	 * 获取数据库连接
	 * @return
	 */
	public static final Connection GetConnection() {
		Connection conn = null;
		try {
			// conn = DriverManager.getConnection(URL, USER, PASSWORD);
			conn = myDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 关闭相关资源
	 * @param rs 
	 * @param st
	 * @param conn
	 */
	public static final void Free(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null)
					try {
						conn.close();
						// myDataSource.free(conn);
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		}
	}
	
}
