package cebu.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cebu.dao.interfaces.DaoTemplate;
import cebu.dao.interfaces.IDaoTicket;
import cebu.dao.interfaces.IRowMapper;
import cebu.model.Ticket;

/**
 * dao实现方式，jdbc + apache common dbcp 数据源
 * 
 * 注意修改数据库表名称 "5j"
 * 
 * @author Administrator
 *
 */

public class DaoImplTicket extends DaoTemplate implements IDaoTicket{
	
	private final String tableName = "5j";	// ticket存放的数据库表名
	
	/**
	 * Ticket映射器，将结果集rs映射成Ticket对象
	 * @author Administrator
	 *
	 */
	class TicketRowMapper implements IRowMapper {
		@Override
		public Object mapRow(ResultSet rs) throws SQLException {
			Ticket ticket = new Ticket();
			ticket.setCabin(rs.getString("cabin"));
			ticket.setCarrier(rs.getString("carrier"));
			ticket.setflightNumber(rs.getString("flightNumber"));
			ticket.setdepAirport(rs.getString("depAirport"));
			ticket.setarrAirport(rs.getString("arrAirport"));
			ticket.setdepTime(rs.getString("depTime"));
			ticket.setarrTime(rs.getString("arrTime"));
			ticket.setadultPrice(rs.getInt("adultPrice"));
			ticket.setadultTax(rs.getInt("adultTax"));
			ticket.setSeats(rs.getInt("seats"));
			ticket.setcreateTime(rs.getString("createTime"));
			return ticket;
		}
	}
	

	/**
	 * 插入Ticket
	 */
	@Override
	public int insert(Ticket ticket) {
		// 已经存在，则执行更新
		if (this.query(ticket) != null && this.query(ticket).size() > 0) {	
//System.out.println("ticket exists, update data...");
			return this.update(ticket);
		}
		
		String sql = "insert into " + this.tableName + " (cabin, carrier, flightNumber, depAirport, arrAirport, " +
				"depTime, arrTime, adultPrice, adultTax, seats, createTime) values " +
				"(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] args = new Object[] {
				ticket.getCabin(),
				ticket.getCarrier(),
				ticket.getflightNumber(),
				ticket.getdepAirport(),
				ticket.getarrAirport(),
				ticket.getdepTime(),
				ticket.getarrTime(),
				ticket.getadultPrice(),
				ticket.getadultTax(),
				ticket.getSeats(),
				ticket.getcreateTime()
		};
		// 交给父类DaoTemplate操作
		return super.update(sql, args);
	}

	/**
	 * 插入多个Ticket
	 */
	@Override
	public int insert(ArrayList<Ticket> tickets) {
		int count = 0;
		for(Ticket ticket : tickets) {
			count += this.insert(ticket);
		}
		return count;
	}

	/**
	 * 查询Ticket是否已经存在
	 */
	@Override
	public ArrayList<Ticket> query(Ticket ticket) {
		String sql = "select * from " + this.tableName + " where flightNumber = ? and depTime = ? and arrTime = ?";
		Object[] args = new Object[] {
				ticket.getflightNumber(), 
				ticket.getdepTime(), 
				ticket.getarrTime()
		};	// 根据flightNumber, depTime, arrTime查询
		// 交给父类DaoTemplate操作
		ArrayList<Object> objects = super.query(sql, args, new TicketRowMapper());
//System.out.println("Object size: " + objects.size());
		// objects 转为 Tickets
		ArrayList<Ticket> tickets = new ArrayList<Ticket>(objects.size());
		for(Object obj : objects) {
			tickets.add((Ticket) obj);
		}
		return tickets;
	}

	/**
	 * 更新Ticket
	 */
	@Override
	public int update(Ticket ticket) {
		String sql = " update " + this.tableName + " set cabin = ?, carrier = ?, adultPrice = ?, adultTax = ?, createTime = ?, " + 
				" depAirport = ?, arrAirport = ? " +
				" where flightNumber = ? and depTime = ? and arrTime = ?";
		Object[] args = new Object[] {
				ticket.getCabin(), 
				ticket.getCarrier(),
				ticket.getadultPrice(), 
				ticket.getadultTax(), 
				ticket.getcreateTime(),
				ticket.getdepAirport(),
				ticket.getarrAirport(),
				// 根据flightNumber, depTime, arrTime更新
				ticket.getflightNumber(),
				ticket.getdepTime(),
				ticket.getarrTime()
		};
		// 交给父类DaoTemplate操作
		return super.update(sql, args);
	}
	
}
