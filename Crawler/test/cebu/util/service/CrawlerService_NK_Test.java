package cebu.util.service;

import java.util.List;

import org.junit.Test;

import cebu.model.Ticket;




public class CrawlerService_NK_Test {

	private CrawlerService_NK service = CrawlerService_NK.getInstance();
	
	/**
	 * 只考虑不转机 "Non stop"的
	 */
	@Test
	public void test_getTickets() {
		List<Ticket> tickets = service.getTickets("ATL", "BOS", "2016-07-31", "", 2);
		System.out.println(tickets);
	}

}
