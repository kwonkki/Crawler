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

public class CrawlerService_7C implements ICrawlerService {
	
	/** 单例模式 **/
	private CrawlerService_7C() {

	}

	private static class InstanceHolder {
		private static CrawlerService_7C CrawlerService_7C = new CrawlerService_7C();
	}

	public static CrawlerService_7C getInstance() {
		return InstanceHolder.CrawlerService_7C;
	}

	// 爬虫类和解析类
	private Crawler_7C crawler = Crawler_7C.getInstance();	
	private HtmlParser_7C parser = HtmlParser_7C.getInstance();
	
	// 第一次post得到航班列表，第二次post得到航班价格（主要是税）
	private final String postUrl = "https://www.jejuair.net/jejuair/com/jeju/ibe/searchAvail.do";
	private final String postUrl_Price = "https://www.jejuair.net/jejuair/com/jeju/ibe/searchFare.do";
	
	@Override
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
	@Override
	public List<Ticket> getInfoOw(String depAirport, String arrAirport, String depTime, int adultNum) {
		// 设置表单变量
		FormParams_7C formParams = new FormParams_7C();
		formParams.setDepAirport(depAirport)
			.setArrAirport(arrAirport)
			.setDepTime(depTime)
			.setAdultNum(adultNum)
			.build();

		// 获取response json，解析Ticket信息，不包括价格信息
		String jsonStr = crawler.getPostResponseHtmlByParams(this.postUrl, formParams);
		List<Ticket> tickets = parser.parseTicketPartly(jsonStr);
		List<String> fareBasisList = parser.parseFareBasis(jsonStr);	// 每个航班的fareBasis
		
		// 遍历获取价格信息
		List<Ticket> finalTickets = new ArrayList<Ticket>();
		for(int i = 0; i < tickets.size(); i++) {
			Ticket ticket = tickets.get(i);
			String fareBasis = fareBasisList.get(i);
			if (CommonUtil.checkStrNullOrEmpty(fareBasis)) 
				continue;
			
			// 根据ticket类和fareBasis构建params
			List<NameValuePair> params = crawler.buildParamsForPricePostOw(ticket, fareBasis);

			// 第二次post，获取价格和税收信息
			String priceStr = crawler.getPostResponseJsonByParams(this.postUrl_Price, params);
			
			TicketPrice ticketPrice = parser.parseTicketPrice(priceStr);	// 解析为TicketPrice类
			
			// 更新价格信息
			ticket.setadultPrice(ticketPrice.getAdultPrice());
			ticket.setadultTax(ticketPrice.getAdultTax());
			ticket.setCurrency(ticketPrice.getCurrency());
			// createTime
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			ticket.setcreateTime(sdf.format(new Date()));
			
			finalTickets.add(ticket);
		}
		return finalTickets;	// 返回完成Ticket列表
	}

	/**
	 * 往返
	 * 尚未完成
	 */
	@Override
	public List<Ticket> getInfoRt(String depAirport, String arrAirport, String depTime, String retTime,
			int adultNum) {
		return null;
	}
	
}
