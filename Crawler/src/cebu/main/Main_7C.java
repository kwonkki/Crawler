package cebu.main;

import java.util.ArrayList;

import cebu.model.Ticket;
import cebu.util.service.CrawlerService_7C;

public class Main_7C {
	
	public static void main(String[] args) {
		Main_7C main = new Main_7C();
		main.getTickets();
		System.out.println("done");
	}
	
	public void getTickets() {
		
		// 获取Ticket信息
		CrawlerService_7C service = CrawlerService_7C.getInstance();
		ArrayList<Ticket> tickets = service.getTickets("WEH", "ICN", "2016-06-30", "", 2);
		System.out.println(tickets);
		
		// 插入数据库
		// TicketService.insert(tickets);
	}
}
