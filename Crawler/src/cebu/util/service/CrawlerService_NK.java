package cebu.util.service;

import java.util.List;

import cebu.model.FormParams_NK;
import cebu.model.Ticket;
import cebu.util.common.CommonUtil;
import cebu.util.crawler.Crawler_NK;
import cebu.util.parser.HtmlParser_NK;
/**
 * 7C的service类，相关操作的主调入口
 * @author Administrator
 *
 */

public class CrawlerService_NK implements ICrawlerService {
	
	/** 单例模式 **/
	private CrawlerService_NK() {

	}

	private static class InstanceHolder {
		private static CrawlerService_NK CrawlerService_NK = new CrawlerService_NK();
	}

	public static CrawlerService_NK getInstance() {
		return InstanceHolder.CrawlerService_NK;
	}

	// 爬虫类和解析类
	private Crawler_NK crawler = Crawler_NK.getInstance();	
	private HtmlParser_NK parser = HtmlParser_NK.getInstance();
	
	// 第一次post得到航班列表，第二次post得到航班价格（主要是税）
	private final String postUrl = "https://www.spirit.com/Default.aspx?action=search";
	
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
		FormParams_NK formParams = new FormParams_NK();
		formParams.setDepAirport(depAirport)
			.setArrAirport(arrAirport)
			.setDepTime(depTime)
			.setAdultNum(adultNum)
			.build();
		
		String html = crawler.getPostResponseHtmlByParams(this.postUrl, formParams);
		return parser.parseTicket(html);
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
