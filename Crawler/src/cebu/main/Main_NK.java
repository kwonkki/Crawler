package cebu.main;

import java.util.List;

import cebu.model.Ticket;
import cebu.util.service.CrawlerService_NK;
import cebu.util.service.ICrawlerService;

public class Main_NK {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 获取Ticket信息
		System.out.println("-------------- one way ----------------");
		ICrawlerService service = CrawlerService_NK.getInstance();
		List<Ticket> tickets = service.getTickets("ATL", "BOS", "2016-07-31", "", 2);
		System.out.println(tickets);
		
	}

}
