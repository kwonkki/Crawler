package cebu.main;

import java.util.List;

import cebu.model.Ticket;
import cebu.util.service.CrawlerService_Spirit;
import cebu.util.service.ICrawlerService;

public class Main_Spirit {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 获取Ticket信息
		System.out.println("-------------- one way ----------------");
		ICrawlerService service = CrawlerService_Spirit.getInstance();
		List<Ticket> tickets = service.getTickets("ATL", "BOS", "2016-06-30", "", 2);
		System.out.println(tickets);
		
	}

}
