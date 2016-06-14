package cebu.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cebu.dao.interfaces.DaoTemplate;
import cebu.dao.interfaces.IDaoTicket;
import cebu.dao.interfaces.IRowMapper;
import cebu.model.Ticket;

public class DaoImplTicket extends DaoTemplate implements IDaoTicket{
	
	private final String tableName = "5j";
	
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
	

	@Override
	public int insert(Ticket ticket) {
		if (this.query(ticket) != null)
			this.update(ticket);
		
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
		return super.update(sql, args);
	}

	@Override
	public int insert(ArrayList<Ticket> tickets) {
		int count = 0;
		for(Ticket ticket : tickets) {
			count += this.insert(ticket);
		}
		return count;
	}

	@Override
	public ArrayList<Ticket> query(Ticket ticket) {
		String sql = "select * from " + this.tableName + " where flightNumber = ? and depTime = ? and arrTime = ?";
		Object[] args = new Object[] {
				ticket.getflightNumber(), 
				ticket.getdepTime(), 
				ticket.getarrTime()
		};
		
		ArrayList<Object> objects = super.query(sql, args, new TicketRowMapper());
		ArrayList<Ticket> tickets = new ArrayList<Ticket>(objects.size());
		for(Object obj : objects) {
			tickets.add((Ticket) obj);
		}
		return tickets;
	}

	@Override
	public int update(Ticket ticket) {
		String sql = " update " + this.tableName + " set cabin = ?, carrier = ?, adultPrice = ?, adultTax = ?, createTime = ?, " + 
				" depAirport = ?, arrAirport = ? " +
				" where flightNumber = ? and depTime = ? and arrTime = ?";
		Object[] args = new Object[] {
				ticket.getCabin(), 
				ticket.getCarrier(),
				ticket.getadultPrice(), 
				ticket.getchildTax(), 
				ticket.getcreateTime(),
				ticket.getdepAirport(),
				ticket.getarrAirport(),
				
				ticket.getflightNumber(),
				ticket.getdepTime(),
				ticket.getarrTime()
		};
		return super.update(sql, args);
	}
	
}
