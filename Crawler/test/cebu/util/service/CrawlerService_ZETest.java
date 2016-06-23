package cebu.util.service;

import java.util.List;

import org.junit.Test;

import cebu.model.Ticket;

public class CrawlerService_ZETest {

	private CrawlerService_ZE service = CrawlerService_ZE.getInstance();
	
	
	@Test
	public void test_getTickets() {
		List<Ticket> tickets = service.getTickets("GMP", "CJU", "2016-07-31", "", 2);
		System.out.println(tickets);
	}

}
