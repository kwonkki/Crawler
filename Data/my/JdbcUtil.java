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
	
	// ʹ���Զ�������Դ
	// private static MyDataSource myDataSource = null;
	
	// ʹ��Apache�Ŀ�ܣ��ṩ����Դ��ʵ����DataSource�ӿ�
	private static DataSource myDataSource = null;
	
	/**
	 * ˽�й��췽��
	 */
	private JdbcUtil() {
		
	}
	
	/**
	 * ��̬����ע������
	 */
	static {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			
			// ʹ���Զ�������Դ
			// myDataSource = new MyDataSource();
			
			// ʹ��Apache�Ŀ�ܣ��ṩ����Դ��ʵ����DataSource�ӿ�
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
	 * ��ȡdatasource
	 * @return
	 */
	public static final DataSource GetDataSource() {
		return myDataSource;
	}
	
	
	/**
	 * ��ȡ���ݿ�����
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
	 * �ر������Դ
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
