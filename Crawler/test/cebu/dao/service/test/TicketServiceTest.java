package cebu.dao.service.test;

import org.junit.Test;

import cebu.dao.service.TicketService;
import cebu.model.Ticket;

public class TicketServiceTest {
	
	public static void main(String[] args) {
		TicketServiceTest test = new TicketServiceTest();
		test.test_insert();
	}
	

	@Test
	public void test_insert() {
		Ticket ticket = new Ticket();
		ticket.setCabin("U");
		ticket.setCarrier("5j");
		ticket.setflightNumber("109");
		ticket.setdepAirport("HKG");
		ticket.setarrAirport("MNL");
		ticket.setdepTime("201601201330");
		ticket.setarrTime("201601201540");
		ticket.setadultPrice(480);
		ticket.setadultTax(330);
		ticket.setSeats(2);
		ticket.setcreateTime("20160101122045");
		
		TicketService.insert(ticket);
		
		System.out.println("done");
	}
	
	@Test
	public void test_query() {
		Ticket ticket = new Ticket();
		ticket.setCabin("U");
		ticket.setCarrier("5j");
		ticket.setflightNumber("109");
		ticket.setdepAirport("HKG");
		ticket.setarrAirport("MNL");
		ticket.setdepTime("201601201330");
		ticket.setarrTime("201601201540");
		ticket.setadultPrice(480);
		ticket.setadultTax(330);
		ticket.setSeats(2);
		ticket.setcreateTime("20160101122045");
		
		System.out.println(TicketService.query(ticket));
		
		System.out.println("done");
	}
	
	@Test
	public void test_update() {
		Ticket ticket = new Ticket();
		ticket.setCabin("U");
		ticket.setCarrier("5j");
		ticket.setflightNumber("109");
		ticket.setdepAirport("HKGGGGG");
		ticket.setarrAirport("MNLLLLL");
		ticket.setdepTime("201601201330");
		ticket.setarrTime("201601201540");
		ticket.setadultPrice(480);
		ticket.setadultTax(330);
		ticket.setSeats(2);
		ticket.setcreateTime("20160101124000");
		
		System.out.println(TicketService.update(ticket));
		System.out.println("done");
	}
}
