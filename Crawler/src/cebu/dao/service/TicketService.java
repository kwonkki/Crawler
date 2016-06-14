package cebu.dao.service;

import java.util.ArrayList;

import cebu.dao.factory.DaoFactoryTicket;
import cebu.dao.interfaces.IDaoTicket;
import cebu.model.Ticket;

public class TicketService {
	private IDaoTicket daoTicket = DaoFactoryTicket.getDao(); 
	
	public int insert(Ticket ticket) {
		return daoTicket.insert(ticket);
	}
	
	public int insert(ArrayList<Ticket> tickets) {
		return daoTicket.insert(tickets);
	}
	
	public ArrayList<Ticket> query(Ticket ticket) {
		return daoTicket.query(ticket);
	}
	
	public int update(Ticket ticket) {
		return daoTicket.update(ticket);
	}
	
}
