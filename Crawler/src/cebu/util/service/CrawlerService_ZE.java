package cebu.util.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.client.CookieStore;

import cebu.model.FormParams_ZE;
import cebu.model.Ticket;
import cebu.util.common.CommonUtil;
import cebu.util.crawler.Crawler_ZE;
import cebu.util.parser.HtmlXmlParser_ZE;
/**
 * 7C的service类，相关操作的主调入口
 * @author Administrator
 *
 */

public class CrawlerService_ZE implements ICrawlerService {
	
	/** 单例模式 **/
	private CrawlerService_ZE() {

	}

	private static class InstanceHolder {
		private static CrawlerService_ZE CrawlerService_ZE = new CrawlerService_ZE();
	}

	public static CrawlerService_ZE getInstance() {
		return InstanceHolder.CrawlerService_ZE;
	}

	// 爬虫类和解析类
	private Crawler_ZE crawler = Crawler_ZE.getInstance();	
	private HtmlXmlParser_ZE parser = HtmlXmlParser_ZE.getInstance();
	
	// 第一次post得到航班列表，第二次post得到航班价格（主要是税）
	private final String postUrl1 = "http://www.eastarjet.com/book/book.htm";
	private final String postUrl2 = "http://www.eastarjet.com/book/bookAjax.ajax";
	
	String savePath1 = "D:/Documents/Github/Crawler/Data/post_response_ZE.html";
	String savePath2 = "D:/Documents/Github/Crawler/Data/post_response_ZE.xml";
	
	public List<Ticket> getTickets(String depAirport, String arrAirport, String depTime, String retTime,
			int adultNum) {
		if (CommonUtil.checkStrNullOrEmpty(retTime)) {
			// 返程时间为空或者null，使用单程
			return this.getInfoOw(depAirport, arrAirport, depTime, adultNum);
		} else {
			// 否则使用往返
			return this.getInfoRt(depAirport, arrAirport, depTime, retTime, adultNum);
		}
	}

	/**
	 * 单程
	 */
	public List<Ticket> getInfoOw(String depAirport, String arrAirport, String depTime, int adultNum) {
		// 设置表单变量
		FormParams_ZE formParams = new FormParams_ZE();
		formParams.setDepAirport(depAirport)
			.setArrAirport(arrAirport)
			.setDepTime(depTime)
			.setAdultNum(adultNum)
			.build();
		
		CookieStore[] cookieStores = new CookieStore[1];
		String html = crawler.getPostResponseHtmlByParams(this.postUrl1, formParams, cookieStores);
		String tax = parser.parseTax(html);
		
		List<Ticket> tickets = new ArrayList<Ticket>();
		
		formParams.buildPost2();
		String xml = crawler.getPostResponseHtmlByParamsWithCookie(this.postUrl2, formParams, cookieStores[0]);
		tickets = parser.parseTicketPartly(xml);
		
		for(Ticket ticket : tickets) {
			ticket.setadultTax(tax);
			// createTime
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			ticket.setcreateTime(sdf.format(new Date()));
		}
		return tickets;
	}

	/**
	 * 往返
	 * 尚未完成
	 */
	public List<Ticket> getInfoRt(String depAirport, String arrAirport, String depTime, String retTime,
			int adultNum) {
		return null;
	}
	
}
