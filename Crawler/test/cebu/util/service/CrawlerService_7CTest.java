package cebu.util.service;


import java.util.List;

import org.junit.Test;

import cebu.model.Ticket;

public class CrawlerService_7CTest {

	private CrawlerService_7C service = CrawlerService_7C.getInstance();
	
	
	@Test
	public void test_getTickets() {
		List<Ticket> tickets = service.getTickets("TAO", "ICN", "2016-07-19", "", 2);
		System.out.println(tickets);
	}

}
