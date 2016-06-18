package cebu.util.service;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import cebu.model.Ticket;

public class CrawlerService_7CTest {

	private CrawlerService_7C service = CrawlerService_7C.getInstance();
	
	
	@Test
	public void test_getTickets() {
		ArrayList<Ticket> tickets = service.getTickets("WEH", "ICN", "2016-06-30", "", 2);
		System.out.println(tickets);
	}

}
