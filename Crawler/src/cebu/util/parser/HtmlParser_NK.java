package cebu.util.parser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cebu.model.Ticket;
import cebu.util.common.CommonUtil;


/**
 * 解析类
 * 7C
 * @author Administrator
 *
 */


public class HtmlParser_NK {
	
	/** 单例模式 **/
	private HtmlParser_NK() {

	}

	private static class HtmlParser_7CInstanceHolder {
		private static HtmlParser_NK HtmlParser_NK = new HtmlParser_NK();
	}

	public static HtmlParser_NK getInstance() {
		return HtmlParser_7CInstanceHolder.HtmlParser_NK;
	}
	
	
	/**
	 * 从json解析Ticket信息，不包括价格信息
	 * @param html
	 * @return 返回Ticket列表
	 */
	public List<Ticket> parseTicket(String html) {
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		if (CommonUtil.checkStrNullOrEmpty(html))
			return tickets;
		
		Document doc = Jsoup.parse(html);
		
		// 始发站
		String depAirport = doc.select("tr.departureInfo1").first().text();
		Pattern pattern = Pattern.compile("[A-Z]{3}");
		Matcher matcher = pattern.matcher(depAirport);
		if (matcher.find())
			depAirport = matcher.group();
		
		// 终点站
		String arrAirport = doc.select("tr.arrivalInfo1").first().text();
		matcher = pattern.matcher(arrAirport);
		if (matcher.find())
			arrAirport = matcher.group();
		
		// 航班列表
		Elements trs = doc.select("tbody.sortThisTable").first().select(">tr.rowsMarket1");
		
		// 有效航班列表，只需要单程直飞
		List<Element> availTr = new ArrayList<Element>();
		for(Element tr : trs) {
			String stopCount = tr.select("a.stopsLink").first().text();
			if (stopCount.equals("Nonstop"))
				availTr.add(tr);
		}
		
		// 出发时间年月日
		String depTimeYear = doc.select("input#DPPCalendarMarketSelectControl_HiddenFieldYear1").first().attr("value");
		String depTimeMonth = doc.select("input#DPPCalendarMarketSelectControl_HiddenFieldMonth1").first().attr("value");
		String depTimeDay = doc.select("input#DPPCalendarMarketSelectControl_HiddenFieldDay1").first().attr("value");
		
		for(Element tr : availTr) {
			// flightNumber
			String flightNumber = tr.select("td.flightNum").first().ownText();
			
			// 出发时间
			String[] depTimeArr = tr.select("td.depart").first().ownText().split(":");
			String depTimeFlag = tr.select("td.depart>sup").first().ownText();
			if (depTimeFlag.equals("PM"))
				depTimeArr[0] = String.valueOf((12 + Integer.valueOf(depTimeArr[0])));
			StringBuilder sbDep = new StringBuilder();
			sbDep.append(depTimeYear)
				.append(this.setZeroAtBeginning(depTimeMonth))		// 如果只有一位数字，前面补0
				.append(this.setZeroAtBeginning(depTimeDay))		// 如果只有一位数字，前面补0
				.append(depTimeArr[0])
				.append(depTimeArr[1]);
			String depTime = sbDep.toString();
			
			// 到达时间
			String[] arrTimeArr = tr.select("td.arrive").first().ownText().split(":");
			String arrTimeFlag = tr.select("td.arrive>sup").first().ownText();
			if (arrTimeFlag.equals("PM"))
				arrTimeArr[0] = String.valueOf((12 + Integer.valueOf(arrTimeArr[0])));
			StringBuilder sbArr = new StringBuilder();
			sbArr.append(depTimeYear)
				.append(this.setZeroAtBeginning(depTimeMonth))	// 如果只有一位数字，前面补0
				.append(this.setZeroAtBeginning(depTimeDay))	// 如果只有一位数字，前面补0
				.append(arrTimeArr[0])
				.append(arrTimeArr[1]);
			String arrTime = sbArr.toString();
			
			// 价格信息
			String priceStr = tr.select("li.standardPrice").first().select(">span").first().attr("data-price");
			String fuelStr = tr.select("li.standardFuel").first().select(">span").first().attr("data-price");
			if (fuelStr.equals("FREE"))
				fuelStr = "0";
			String taxStr = tr.select("li.standardTaxes").first().select(">span").first().attr("data-price");

			// tax汇总
			double fuel = Double.valueOf(fuelStr.trim());
			double tax = Double.valueOf(taxStr.trim());
			// 最终价格信息
			String adultPrice = priceStr;
			String adultTax = String.valueOf(fuel + tax);
			
			// 完成航班信息
			Ticket ticket = new Ticket();
			ticket.setflightNumber(flightNumber);
			ticket.setdepAirport(depAirport);
			ticket.setarrAirport(arrAirport);
			ticket.setdepTime(depTime);
			ticket.setarrTime(arrTime);
			ticket.setadultPrice(adultPrice);
			ticket.setadultTax(adultTax);
			ticket.setCurrency("USD");
			// createTime
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			ticket.setcreateTime(sdf.format(new Date()));
			
			tickets.add(ticket);
		}
		return tickets;
	}
	
	/**
	 * 时间的day month 如果只有一位数字，前面补0
	 * @param str
	 * @return
	 */
	public String setZeroAtBeginning(String str) {
		if (CommonUtil.checkStrNullOrEmpty(str) || str.length() > 1)
			return str;
		return "0" + str;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
