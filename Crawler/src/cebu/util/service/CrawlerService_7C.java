package cebu.util.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;

import cebu.model.FormParams_7C;
import cebu.model.Ticket;
import cebu.model.TicketPrice;
import cebu.util.common.CommonUtil;
import cebu.util.crawler.Crawler_7C;
import cebu.util.parser.HtmlParser_7C;

/**
 * 7C的service类，相关操作的主调入口
 * @author Administrator
 *
 */


public class CrawlerService_7C implements CrawlerService {
	
	/** 单例模式 **/
	private CrawlerService_7C() {

	}

	private static class InstanceHolder {
		private static CrawlerService_7C CrawlerService_7C = new CrawlerService_7C();
	}

	public static CrawlerService_7C getInstance() {
		return InstanceHolder.CrawlerService_7C;
	}

	private Crawler_7C crawler = Crawler_7C.getInstance();
	private HtmlParser_7C parser = HtmlParser_7C.getInstance();
	
	// 第一次post得到航班列表，第二次post得到航班价格（主要是税）
	private final String postUrl = "https://www.jejuair.net/jejuair/com/jeju/ibe/searchAvail.do";
	private final String postUrl_Price = "https://www.jejuair.net/jejuair/com/jeju/ibe/searchFare.do";
	
	@Override
	public ArrayList<Ticket> getTickets(String depAirport, String arrAirport, String depTime, String retTime,
			int adultNum) {
		if (CommonUtil.checkStrNullOrEmpty(retTime)) {
			return this.getInfoOw(depAirport, arrAirport, depTime, adultNum);
		} else {
			return this.getInfoRt(depAirport, arrAirport, depTime, retTime, adultNum);
		}
	}

	/**
	 * 单程
	 */
	@Override
	public ArrayList<Ticket> getInfoOw(String depAirport, String arrAirport, String depTime, int adultNum) {
		FormParams_7C formParams = new FormParams_7C();
		formParams.setDepAirport(depAirport)
			.setArrAirport(arrAirport)
			.setDepTime(depTime)
			.setAdultNum(adultNum)
			.build();

		String jsonStr = crawler.getPostResponseHtmlByParams(this.postUrl, formParams);

		ArrayList<Ticket> tickets = parser.parseTicketPartly(jsonStr);
		for(Ticket ticket : tickets) {
			List<NameValuePair> params = crawler.buildParamsForPricePostOw(ticket);
			String priceStr = crawler.getPostResponseJsonByParams(this.postUrl_Price, params);
			TicketPrice ticketPrice = parser.parseTicketPrice(priceStr);
			
			ticket.setadultPrice(ticketPrice.getAdultPrice());
			ticket.setadultTax(ticketPrice.getAdultTax());
			ticket.setCurrency(ticketPrice.getCurrency());
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			ticket.setcreateTime(sdf.format(new Date()));
		}
		return tickets;
	}

	/**
	 * 往返
	 */
	@Override
	public ArrayList<Ticket> getInfoRt(String depAirport, String arrAirport, String depTime, String retTime,
			int adultNum) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
