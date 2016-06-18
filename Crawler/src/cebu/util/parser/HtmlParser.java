package cebu.util.parser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import cebu.model.Ticket;
import cebu.model.TicketPrice;

/**
 * 利用jsoup，html解析工具类
 * 解析html字符串
 * @author lihaijun
 *
 */

public class HtmlParser extends Parser{

	/** 单例模式 **/
	private HtmlParser() {

	}

	private static class ParseUtilInstanceHolder {
		private static HtmlParser Parse_Util = new HtmlParser();
	}

	public static HtmlParser getInstance() {
		return ParseUtilInstanceHolder.Parse_Util;
	}

	private boolean check(String html) {
		return (html != null && !html.equals("")) ? true : false;
	}
	
	/**
	 * 从html字符串解析下拉列表中的时间信息
	 * @param html html字符串
	 * @return	
	 */
	public Map<String, ArrayList<String>> parseTime(String html) {
		// 出发日、出发年月、回程日、回程年月4个list组成的map
		if (!check(html))
			return null;

		Document doc = Jsoup.parse(html);
		return super.parseTimeByDoc(doc);
	}

	/**
	 * 从html字符串解析时间信息
	 * @param html html字符串
	 * @param baseUrl html来源的url地址，用于解析html字符串中的其他路径相关信息
	 * @return	
	 */
	public Map<String, ArrayList<String>> parseTimeWithUrl(String html, String baseUrl) {
		// 出发日、出发年月、回程日、回程年月4个list组成的map
		if (!check(html))
			return null;

		Document doc = Jsoup.parse(html, baseUrl);
		return super.parseTimeByDoc(doc);
	}

	/**
	 * 由html string解析航班信息，不完整
	 * @param html html字符串
	 * @return	
	 */
	private ArrayList<Ticket> parseTicket(String html) {
		ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
		if (!check(html))
			return ticketList;

		Document doc = Jsoup.parse(html); // 文件解析
		return super.parseTicketByDoc(doc);
	}
	
	
	/**
	 * 解析html字符串中的航班的radio value信息（不包括转机）
	 * 包含始发站、终点站、出发时间、到达时间等
	 * @param html
	 * @return
	 */
	public ArrayList<String> parseRadioValue(String html) {
		if (!check(html))
			return null;

		Document doc = Jsoup.parse(html); // 文件解析
		return super.parseRadioValue(doc);
	}
	
	/**
	 * 解析html字符串中的航班的radio value信息（不包括转机）
	 * 包含始发站、终点站、出发时间、到达时间等
	 * @param html
	 * @param baseUrl html来源的url
	 * @return
	 */
	public ArrayList<String> parseRadioValueWithUrl(String html, String baseUrl) {
		if (!check(html))
			return null;

		Document doc = Jsoup.parse(html, baseUrl); // 文件解析
		return super.parseRadioValue(doc);
	}
	
	/**
	 * 根据post提交之后的response html 和 radio value产生的html解析完整的ticket信息
	 * @param html post提交之后的response html
	 * @param radioValueGeneratedHtmls 所有radio value产生的html列表
	 * @return
	 */
	public ArrayList<Ticket> parseTicket(String html, ArrayList<String> radioValueGeneratedHtmls) {
		if (!check(html) || radioValueGeneratedHtmls == null || radioValueGeneratedHtmls.size() <= 0)
			return null;
		
		int count = radioValueGeneratedHtmls.size();
		// 解析response html中radio value包含的部分信息
		ArrayList<Ticket> tmpTickets = new ArrayList<Ticket>();
		tmpTickets = this.parseTicket(html);
		// 解析radio value产生的价格信息
		ArrayList<TicketPrice> prices = new ArrayList<TicketPrice>();
		for(String tmpHtml : radioValueGeneratedHtmls)
			prices.add(this.parsePriceByRadioHtml(tmpHtml));
		// 数目不一致
		if(tmpTickets.size() != prices.size()) {
			System.out.println("ticket size not equals price size");
			return null;
		}
		
		// 合并成完整信息
		ArrayList<Ticket> tickets = new ArrayList<Ticket>(count);
		for(int i = 0; i < count; i++) {
			Ticket ticket = tmpTickets.get(i);
			TicketPrice price = prices.get(i);
			ticket.setadultPrice(price.getAdultPrice());
			ticket.setadultTax(price.getAdultTax());
			ticket.setCurrency(price.getCurrency());
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			ticket.setcreateTime(sdf.format(new Date()));
			
			tickets.add(ticket);
		}
		return tickets;
	}
	
	/**
	 * 根据post提交之后的response html 和 radio value产生的html解析完整的ticket信息
	 * @param html post提交之后的response html
	 * @param radioValueGeneratedHtmls 所有radio value产生的html列表
	 * @param baseUrl html来源的url
	 * @return
	 */
	public ArrayList<Ticket> parseTicketWithUrl(String html, ArrayList<String> radioValueGeneratedHtml, String baseUrl) {
		if (!check(html) || radioValueGeneratedHtml == null || radioValueGeneratedHtml.size() <= 0)
			return null;
		
		int count = radioValueGeneratedHtml.size();
		ArrayList<Ticket> tmpTickets = new ArrayList<Ticket>(count);
		tmpTickets = this.parseTicketWithUrl(html, baseUrl);
		
		ArrayList<TicketPrice> prices = new ArrayList<TicketPrice>(count);
		for(String tmpHtml : radioValueGeneratedHtml)
			prices.add(this.parsePriceByRadioHtmlWithUrl(tmpHtml, baseUrl));
		
		ArrayList<Ticket> tickets = new ArrayList<Ticket>(count);
		for(int i = 0; i < count; i++) {
			Ticket ticket = tmpTickets.get(i);
			TicketPrice price = prices.get(i);
			ticket.setadultPrice(price.getAdultPrice());
			ticket.setadultTax(price.getAdultTax());
			ticket.setCurrency(price.getCurrency());
			tickets.add(ticket);
		}
		return tickets;
	}
	

	/**
	 * 从html string 获取航班信息
	 * 提供String来源的url，解析路径相关信息
	 * @param html
	 * @param baseUrl
	 * @return
	 */
	private ArrayList<Ticket> parseTicketWithUrl(String html, String baseUrl) {
		if (!check(html))
			return null;

		Document doc = Jsoup.parse(html, baseUrl); // 文件解析
		return super.parseTicketByDoc(doc);
	}

	/**
	 * 从html string获取站点信息，起始和终点站点列表完全一样
	 * @param html
	 *            html字符串
	 * @return
	 */
	public Map<String, String> parseStation(String html) {
		if (!check(html))
			return null;

		Document doc = Jsoup.parse(html);;
		return super.parseStationByDoc(doc);
	}


	/**
	 * 从html string获取站点信息，起始和终点站点列表完全一样
	 * 提供String来源的url，解析路径相关信息
	 * @param html
	 * @param baseUrl
	 * @return
	 */
	public Map<String, String> parseStationWithUrl(String html, String baseUrl) { 
		if (!check(html))
			return null;

		Document doc = Jsoup.parse(html, baseUrl);
		return super.parseStationByDoc(doc);
	}

	/**
	 * 从html字符串中解析航班价格信息
	 * @param html
	 */
	private TicketPrice parsePriceByRadioHtml(String html) {
		if (!check(html))
			return null;

		Document doc = Jsoup.parse(html); // 文件解析
		return super.parsePriceByDoc(doc);
	}
	
	/**
	 * 从html字符串中解析航班价格信息
	 * @param html
	 */
	private TicketPrice parsePriceByRadioHtmlWithUrl(String html, String baseUrl) {
		if (!check(html))
			return null;

		Document doc = Jsoup.parse(html, baseUrl); // 文件解析
		return super.parsePriceByDoc(doc);
	}
	
	
	

}
