package cebu.dao.interfaces;

import java.util.ArrayList;

import cebu.model.Ticket;

public interface IDaoTicket {
	
	public int insert(Ticket ticket);
	
	public int insert(ArrayList<Ticket> tickets);
	
	public ArrayList<Ticket> query(Ticket ticket);
	
	public int update(Ticket ticket);
	
	
}
