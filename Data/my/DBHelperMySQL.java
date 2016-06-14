package com.multithread.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.multithread.test.Ticket;
import com.multithread.test.TicketSL;

public class DBHelperMySQL {
	
	/*
	private static final String driverName = "com.mysql.jdbc.Driver";
	//private static final String url = "jdbc:mysql://localhost:3306/gyy";
	private static final String url = "jdbc:mysql://localhost:3306/flightdb";
	private static final String userName = "root";
	//private static final String passWord = "ubee";
	//private static final String passWord = "gyymysql";
	private static final String passWord = "67890def";
	*/
	private static final Logger logger = LoggerFactory.getLogger("dbLog");
	
	public DBHelperMySQL()
	{
		
	}
	
	public  void exeInsertByOne(Ticket ticket,String sql,Long iCatch,String tableName) throws Exception {
		PreparedStatement st = null;
		Statement querySt = null;
		ResultSet rs = null;
		ResultSet queryRs = null;
		Connection conn=null;
		try {
			conn = getConnection();
			// 在insert之前首先判断
			querySt = conn.createStatement();
			StringBuilder judgeSql = new StringBuilder();
			judgeSql.append("select 1 from ");
			judgeSql.append(tableName);
			judgeSql.append(" where flightNumber='");
			judgeSql.append(ticket.getflightNumber());
			judgeSql.append("' and depTime='");
			judgeSql.append(ticket.getdepTime());
			judgeSql.append("' and arrTime='");
			judgeSql.append(ticket.getarrTime());
			judgeSql.append("'");
			queryRs = querySt.executeQuery(judgeSql.toString());
			if(queryRs.next()) {
				// 数据库中已存在，执行更新
				StringBuilder updateSql = new StringBuilder();
				updateSql.append("update ");
				updateSql.append(tableName);
				updateSql.append(" set carrier=?,depAirport=?,arrAirport=?,adultPrice=?,adultTax=?,childPrice=?,childTax=?,flyTime=?,createTime=?,cabin=?,count=?,seats=? ");
				updateSql.append("where flightNumber='");
				updateSql.append(ticket.getflightNumber());
				updateSql.append("' and depTime='");
				updateSql.append(ticket.getdepTime());
				updateSql.append("' and arrTime='");
				updateSql.append(ticket.getarrTime());
				updateSql.append("'");
				sql = updateSql.toString();
				
				st = conn.prepareStatement(sql);
		        st.setString(1, ticket.getCarrier());
		        st.setString(2, ticket.getdepAirport());  
		        st.setString(3, ticket.getarrAirport()); 
		        st.setInt(4, ticket.getadultPrice());
		        st.setInt(5, ticket.getadultTax()); 
		        st.setInt(6, ticket.getchildPrice()); 
		        st.setInt(7, ticket.getchildTax());
		        st.setString(8, ticket.getflyTime()); 
		        st.setString(9, ticket.getcreateTime());
		        st.setString(10, ticket.getCabin()); 
		        st.setLong(11, iCatch);
		        st.setInt(12, ticket.getSeats()); 
		        if (st.executeUpdate() <= 0) {  
		        	logger.warn("更新数据失败： " + "	"+"turn	"+iCatch.toString() + "  表格：  " + tableName);
		        }  
		        st.clearParameters();    
//		        logger.warn("更新数据成功： " + "	 turn	"+iCatch.toString() + "  表格：  " + tableName);
			} else {
				// 数据库中不存在，执行插入
				st = conn.prepareStatement(sql);
		        st.setString(1, ticket.getCarrier());
		        st.setString(2, ticket.getflightNumber()); 
		        st.setString(3, ticket.getdepAirport());  
		        st.setString(4, ticket.getdepTime());
		        st.setString(5, ticket.getarrAirport()); 
		        st.setString(6, ticket.getarrTime()); 
		        st.setInt(7, ticket.getadultPrice());
		        st.setInt(8, ticket.getadultTax()); 
		        st.setInt(9, ticket.getchildPrice()); 
		        st.setInt(10, ticket.getchildTax());
		        st.setString(11, ticket.getflyTime()); 
		        st.setString(12, ticket.getcreateTime());
		        st.setString(13, ticket.getCabin()); 
		        //st.setInt(14, 1); 
		        st.setLong(14, iCatch);
		       // st.setInt(15, 1); 
		        st.setInt(15, ticket.getSeats()); 
		        if (st.executeUpdate() <= 0) {  
		        	logger.warn("新增数据失败： " + "	 turn	"+iCatch.toString() + "  表格：  " + tableName);
		        }  
		        st.clearParameters();    
//		        logger.warn("新增数据成功： " + "	 turn	"+iCatch.toString() + "  表格：  " + tableName);
			}
			
		} catch(SQLException ex) {
			logger.warn("数据库操作异常： " + ex.toString() + " | " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			closeResultSet(rs);
			closeStatement(st);
			closeConn(conn);
		}
	}
	
	public static void exeInsertByOneSL(TicketSL ticket,String sql,Long iCatch,String tableName) throws Exception {
		PreparedStatement st = null;
		Statement querySt = null;
		ResultSet rs = null;
		ResultSet queryRs = null;
		Connection conn=null;
		try {
			conn = getConnection();
			// 在insert之前首先判断
			querySt = conn.createStatement();
			StringBuilder judgeSql = new StringBuilder();
			judgeSql.append("select 1 from ");
			judgeSql.append(tableName);
			judgeSql.append(" where flightNumber='");
			judgeSql.append(ticket.getflightNumber());
			judgeSql.append("' and depTime='");
			judgeSql.append(ticket.getdepTime());
			judgeSql.append("' and arrTime='");
			judgeSql.append(ticket.getarrTime());
			judgeSql.append("'");
			queryRs = querySt.executeQuery(judgeSql.toString());
			if(queryRs.next()) {
				// 数据库中已存在，执行更新
				StringBuilder updateSql = new StringBuilder();
				updateSql.append("update ");
				updateSql.append(tableName);
				updateSql.append(" set carrier=?,depAirport=?,arrAirport=?,adultPrice=?,adultTax=?,childPrice=?,childTax=?,flyTime=?,createTime=?,cabin=?,count=?,seats=?,currency=? ");
				updateSql.append("where flightNumber='");
				updateSql.append(ticket.getflightNumber());
				updateSql.append("' and depTime='");
				updateSql.append(ticket.getdepTime());
				updateSql.append("' and arrTime='");
				updateSql.append(ticket.getarrTime());
				updateSql.append("'");
				sql = updateSql.toString();
				
				st = conn.prepareStatement(sql);
		        st.setString(1, ticket.getCarrier());
		        st.setString(2, ticket.getdepAirport());  
		        st.setString(3, ticket.getarrAirport()); 
		        st.setInt(4, ticket.getadultPrice());
		        st.setInt(5, ticket.getadultTax()); 
		        st.setInt(6, ticket.getchildPrice()); 
		        st.setInt(7, ticket.getchildTax());
		        st.setString(8, ticket.getflyTime()); 
		        st.setString(9, ticket.getcreateTime());
		        st.setString(10, ticket.getCabin()); 
		        st.setLong(11, iCatch);
		        st.setInt(12, ticket.getSeats()); 
		        st.setString(13, ticket.getCurrency());
		        if (st.executeUpdate() <= 0) {  
		        	logger.warn("更新数据失败： " + "	"+"turn	"+iCatch.toString() + "  表格：  " + tableName);
		        }  
		        st.clearParameters();    
		        logger.warn("更新数据成功： " + "	 turn	"+iCatch.toString() + "  表格：  " + tableName);
			} else {
				st = conn.prepareStatement(sql);
				st.setString(1, ticket.getCarrier());
		        st.setString(2, ticket.getflightNumber()); 
		        st.setString(3, ticket.getdepAirport());  
		        st.setString(4, ticket.getdepTime());
		        st.setString(5, ticket.getarrAirport()); 
		        st.setString(6, ticket.getarrTime()); 
		        st.setInt(7, ticket.getadultPrice());
		        st.setInt(8, ticket.getadultTax()); 
		        st.setInt(9, ticket.getchildPrice()); 
		        st.setInt(10, ticket.getchildTax());
		        st.setString(11, ticket.getflyTime()); 
		        st.setString(12, ticket.getcreateTime());
		        st.setString(13, ticket.getCabin()); 
		        //st.setInt(14, 1); 
		        st.setLong(14, iCatch);
		       // st.setInt(15, 1); 
		        st.setInt(15, ticket.getSeats()); 
		        st.setString(16, ticket.getCurrency());
		        if (st.executeUpdate() <= 0) {  
		        	logger.warn("新增数据失败： " + "	 turn	"+iCatch.toString() + "  表格：  " + tableName);
		        }  
		        st.clearParameters();    
		        logger.warn("新增数据成功： " + "	 turn	"+iCatch.toString() + "  表格：  " + tableName);
			}
		} catch(SQLException ex) {
			logger.error("数据库操作异常： " + ex.toString() + " | " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			closeResultSet(rs);
			closeStatement(st);
			closeConn(conn);
		}
	}
	
	public static void exeInsertDate(List<Ticket> list,String sql,Long iCatch) throws Exception {
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection conn=null;
		try {
			conn = getConnection();
			st = conn.prepareStatement(sql);
			
			 for (Ticket ticket : list) {  
			        st.setString(1, ticket.getCarrier());
			        st.setString(2, ticket.getflightNumber()); 
			        st.setString(3, ticket.getdepAirport());  
			        st.setString(4, ticket.getdepTime());
			        st.setString(5, ticket.getarrAirport()); 
			        st.setString(6, ticket.getarrTime()); 
			        st.setInt(7, ticket.getadultPrice());
			        st.setInt(8, ticket.getadultTax()); 
			        st.setInt(9, ticket.getchildPrice()); 
			        st.setInt(10, ticket.getchildTax());
			        st.setString(11, ticket.getflyTime()); 
			        st.setString(12, ticket.getcreateTime());
			        st.setString(13, ticket.getCabin()); 
			        //st.setInt(14, 1); 
			        st.setLong(14, iCatch);
			       // st.setInt(15, 1); 
			        st.setInt(15, ticket.getSeats()); 
			        
			        if (st.executeUpdate() <= 0) {  
			          throw new Exception("保存数据错误!");  
			        }  
			        st.clearParameters();  
			      }  
		} catch(SQLException ex) {
			logger.error("执行数据插入出错： " + ex.toString() + " | " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			closeResultSet(rs);
			closeStatement(st);
			closeConn(conn);
		}
	}
	
	public static void exeInsertDateSL(List<TicketSL> list,String sql,Long iCatch) throws Exception {
		PreparedStatement st = null;
		ResultSet rs = null;
		Connection conn=null;
		try {
			conn = getConnection();
			st = conn.prepareStatement(sql);
			
			for (TicketSL ticket : list) {  
		        st.setString(1, ticket.getCarrier());
		        st.setString(2, ticket.getflightNumber()); 
		        st.setString(3, ticket.getdepAirport());  
		        st.setString(4, ticket.getdepTime());
		        st.setString(5, ticket.getarrAirport()); 
		        st.setString(6, ticket.getarrTime()); 
		        st.setInt(7, ticket.getadultPrice());
		        st.setInt(8, ticket.getadultTax()); 
		        st.setInt(9, ticket.getchildPrice()); 
		        st.setInt(10, ticket.getchildTax());
		        st.setString(11, ticket.getflyTime()); 
		        st.setString(12, ticket.getcreateTime());
		        st.setString(13, ticket.getCabin()); 
		        //st.setInt(14, 1); 
		        st.setLong(14, iCatch);
		       // st.setInt(15, 1); 
		        st.setInt(15, ticket.getSeats()); 
		        st.setString(16, ticket.getCurrency());
		        if (st.executeUpdate() <= 0) {  
		          throw new Exception("保存数据错误!");  
		        }  
			        st.clearParameters();  
			 }  
		} catch(SQLException ex) {
			logger.error("执行数据插入出错： " + ex.toString() + " | " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			closeResultSet(rs);
			closeStatement(st);
			closeConn(conn);
		}
	}
	 
	/*
	 * 执行数据库更新操作
	 */
	public static int exeSql(String sql) throws Exception {
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
			closeResultSet(rs);
			closeStatement(stmt);
			closeConn(conn);
		}
		
		return resultNum;
	}
	
	/*
	 * 类似于SqlReader
	 * @创建resultset游标，调用此方法应该习惯调用rs.close()方法释放rs
	 * @param sql
	 * @return 每次调用此方法可以返回不同的rs记录集，避免不能嵌套查询的问题
	 */
	public static List<Object> query(String sql) throws Exception {
			List list = new ArrayList();
			Statement stmt = null;
			ResultSet rs = null;
			Connection conn=null;
			try
			{
			
			    //conn = DriverManager.getConnection(url,userName,passWord);
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
			}
			catch(SQLException e)
			{
				logger.error("使用sql语句查询数据库出错： " + e.toString() + " | " + e.getMessage());
				e.printStackTrace();
			}
			finally
			{
				closeResultSet(rs);
				closeStatement(stmt);
				closeConn(conn);
			}
			
			return list;
		}

	/**
	 * add by liupeng 20151006
	 * JDNI数据源
	 */
	private static String jndiName = "java:/comp/env/jdbc/flightdb";
	
	/**
	 * add by liupeng 20151006
	 * 获得数据库连接
	 * @return
	 * @throws Exception
	 */
	private static Connection getConnection() throws Exception {
		InitialContext cxt = new InitialContext();
		DataSource ds = (DataSource) cxt.lookup(jndiName);
		if ( ds == null ) {
		    logger.error("JDNI Data source not found");
		   throw new Exception("Data source not found!");
		}
		
		return ds.getConnection();
	}
	
	/**
	 * add by liupeng 20151006
	 * 关闭数据库连接
	 * @param conn
	 */
	private static void closeConn(Connection conn) {
		if(conn == null) {
			return;
		}
		try {
			if(!conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			logger.error("关闭数据库连接出错： " + e.getMessage());
		}
	}
	
	/**
	 * add by liupeng 20151006
	 * 关闭Statement
	 * @param stmt
	 */
	private static void closeStatement(Statement stmt) {
		if(stmt == null) {
			return;
		}
		try {
			if(!stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			logger.error("关闭Statement出错： " + e.getMessage());
		}
	}
	
	/**
	 * add by liupeng 20151006
	 * 关闭Resultset
	 * @param rs
	 */
	private static void closeResultSet(ResultSet rs) {
		if(rs == null) {
			return;
		}
		try {
			if(!rs.isClosed()) {
				rs.close();
			}
		} catch (SQLException e) {
			logger.error("关闭Resultset出错： " + e.getMessage());
		}
	}
}
