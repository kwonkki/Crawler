package cebu.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cebu.model.Ticket;
import cebu.model.TicketPrice;

/**
 * 抽象类，利用jsoup解析html document
 * @author lihaijun
 *
 */

public abstract class Parser {

	protected final static String WHITESPACE_UTF8 = "/u00a0"; // 空白符的ASCII码

	/**
	 * 从doc中解析下拉列表中的时间信息
	 * 
	 * @param doc
	 * @return
	 */
	protected Map<String, ArrayList<String>> parseTimeByDoc(Document doc) {
		// 出发日、出发年月、回程日、回程年月4个list组成的map
		Map<String, ArrayList<String>> map = new LinkedHashMap<String, ArrayList<String>>();
		if (doc == null)
			return map;

		String prefix = "select#";
		// 出发日
		String selectDayDeparture = prefix + FormUtil.Name_CalendarDayDeparture.replace('$', '_');
		Element target = doc.select(selectDayDeparture).get(0);
		Elements options = target.select("option");
		ArrayList<String> listDayDeparture = new ArrayList<String>();
		for (Element option : options) {
			listDayDeparture.add(option.text());
		}
		// 出发年月
		String selectMonthDeparture = prefix + FormUtil.Name_CalendarMonthDeparture.replace('$', '_');
		target = doc.select(selectMonthDeparture).get(0);
		options = target.select("option");
		ArrayList<String> listMonthDeparture = new ArrayList<String>();
		for (Element option : options) {
			listMonthDeparture.add(option.text());
		}
		// 返程日
		String selectDayReturn = prefix + FormUtil.Name_CalendarDayReturn.replace('$', '_');
		target = doc.select(selectDayReturn).get(0);
		options = target.select("option");
		ArrayList<String> listDayReturn = new ArrayList<String>();
		for (Element option : options) {
			listDayReturn.add(option.text());
		}
		// 返程年月
		String selectMonthReturn = prefix + FormUtil.Name_CalendarMonthReturn.replace('$', '_');
		target = doc.select(selectMonthReturn).get(0);
		options = target.select("option");
		ArrayList<String> listMonthReturn = new ArrayList<String>();
		for (Element option : options) {
			listMonthReturn.add(option.text());
		}

		map.put(FormUtil.Name_CalendarDayDeparture, listDayDeparture);
		map.put(FormUtil.Name_CalendarMonthDeparture, listMonthDeparture);
		map.put(FormUtil.Name_CalendarDayReturn, listDayReturn);
		map.put(FormUtil.Name_CalendarMonthReturn, listMonthReturn);
		return map;
	}

	/**
	 * 由doc解析航班信息
	 * 
	 * @param doc
	 *            doc对象
	 * @return 航班列表，每个航班是一个map，包含键值对属性信息
	 */
	protected ArrayList<Ticket> parseTicketByDoc(Document doc) {
		ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
		if (doc == null)
			return ticketList;

		// 获取ticket的radio values
		ArrayList<String> ticketRadioValues = this.parseRadioValue(doc);

		// 解析radio values的信息，不包括价格和税，需要进一步接卸radio点击之后生成的html
		for (String radioValue : ticketRadioValues) {
			Ticket tmpTicket = this.parseTicketByRadioValue(radioValue);
			if (tmpTicket != null) {
				ticketList.add(tmpTicket);
			}
		}
		return ticketList;
	}

	/**
	 * 由Doc解析Station信息
	 * 
	 * @param doc
	 *            Document对象
	 * @return map对象，Station的缩写和全称
	 */
	protected Map<String, String> parseStationByDoc(Document doc) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		// 定位到站点div
		Element cityPairDiv = doc.select("div#marketCityPair_1").get(0);
		Elements options = cityPairDiv.select("option");
		for (Element option : options) {
			String key = option.val();
			String value = option.text();
			if (!key.equals("") && !value.equals("")) // 去除空串
				map.put(key, value);
		}
		return map;
	}

	/**
	 * 从radio的value值中解析航班信息 carrier, number, flightNumber, depAirport,
	 * arrAirport, depTime, arrTime;
	 * 
	 * @param radioValue
	 */
	private Ticket parseTicketByRadioValue(String radioValue) {
		String carrier, number, flightNumber, depAirport, arrAirport, depTime, arrTime;
		String year, month, day, hour, minute;
		carrier = number = flightNumber = depAirport = arrAirport = depTime = arrTime = "";
		year = month = day = hour = minute = "";
		// 0~Z~~ZRP~6020~~1~X|5J~ 109~ ~~HKG~06/20/2016 08:25~MNL~06/20/2016
		// 10:35~

		// carrier
		String rgxCarrier = "[|](?<carrier>..)[~]";
		Pattern pattern = Pattern.compile(rgxCarrier);
		Matcher matcher = pattern.matcher(radioValue);
		while (matcher.find()) {
			carrier = matcher.group("carrier");
			break;
		}

		// number
		String rgxNumber = "[~]\\s(?<number>\\d+)[~]";
		pattern = Pattern.compile(rgxNumber);
		matcher = pattern.matcher(radioValue);
		while (matcher.find()) {
			number = matcher.group("number");
			break;
		}
		flightNumber = carrier + " " + number; // whole flightNumber

		// airport
		String rgxAirport = "[~](?<airport>[A-Z]{3})[~]";
		pattern = Pattern.compile(rgxAirport);
		matcher = pattern.matcher(radioValue);
		int i = 0;
		while (matcher.find()) {
			i++;
			if (i == 2)
				depAirport = matcher.group("airport"); // depAirport
			else if (i == 3)
				arrAirport = matcher.group("airport"); // arrAirport
		}

		// time
		String rgxTime = "(?<month>\\d{2})/(?<day>\\d{2})/(?<year>\\d{4})\\s(?<hour>\\d{2}):(?<minute>\\d{2})";
		pattern = Pattern.compile(rgxTime);
		matcher = pattern.matcher(radioValue);
		i = 0;
		while (matcher.find()) {
			i++;
			year = matcher.group("year");
			month = matcher.group("month");
			day = matcher.group("day");
			hour = matcher.group("hour");
			minute = matcher.group("minute");
			if (i == 1)
				depTime = year + month + day + hour + minute; // depTime
			else if (i == 2)
				arrTime = year + month + day + hour + minute; // arrTime
		}

		// 构建Ticket对象，adultPrice,adultTax默认为空
		// 需要进一步解析radio点击之后生成的html summary html得到价格信息
		Ticket ticket = new Ticket();
		ticket.setCarrier(carrier);
		ticket.setflightNumber(flightNumber);
		ticket.setdepAirport(depAirport);
		ticket.setarrAirport(arrAirport);
		ticket.setdepTime(depTime);
		ticket.setarrTime(arrTime);

		return ticket;
	}

	/**
	 * 解析航班查询页面中的航班radio的value值，忽略需要转机的航班 里面包含航班信息
	 * "0~Z~~ZRP~6020~~1~X|5J~ 109~ ~~HKG~06/20/2016 08:25~MNL~06/20/2016 10:35~"
	 * 
	 * @param html
	 *            查询结果页面html
	 * @return radio value list
	 */
	protected ArrayList<String> parseRadioValue(Document doc) {
		ArrayList<String> radioValueList = new ArrayList<String>(5);
		if (doc == null)
			return radioValueList;

		// 定位到table
		String selectTable = "table#availabilityTable";
		Element table = doc.select(selectTable).get(0);

		// 航班，每一个航班信息存放在tr中
		Elements trs = table.select("tbody>tr");

		for (Element tr : trs) {
			String radioValue = this.parseRadioValueInTr(tr);
			if (radioValue != null && !radioValue.equals(""))
				radioValueList.add(radioValue);
		}
		return radioValueList;
	}

	/**
	 * 解析tr元素中的radio的value值
	 * 
	 * @param tr
	 * @return
	 */
	private String parseRadioValueInTr(Element tr) {
		if (tr == null)
			return null;
		Elements tds = tr.select("td");

		// 存在两个td，则出现换乘转机
		boolean isTransferHappen = (tds.get(0).select(">b").size() > 1) ? true : false;

		// 忽略换乘的情况
		if (isTransferHappen)
			return "";

		// radio元素的value值
		Element tdFareFly = tr.select(">td.fareBundle").first().select(">div.radioButtonFareContainer").last()
				.select(">input").first();
		return tdFareFly.attr("value");
	}

	/**
	 * 根据radio点击之后的html，解析获取价格信息 包括currency adultPrice adultTax
	 * 
	 * @param doc
	 * @return
	 */
	protected TicketPrice parsePriceByDoc(Document doc) {
		if (doc == null)
			return null;

		String currency = "";
		int adultPrice = -1;
		int adultTax = -1;

		// adult人数
		Element adultCountSpan = doc.select("span.paxCount").first();
		int adultCount = Integer.valueOf(adultCountSpan.text());
		//System.out.println(adultCount);

		// 货币单位
		Element currencySpan = doc.select("span#overallTotalCurrencyCode").first();
		currency = currencySpan.text();
		//System.out.println(currency);

		// adult price
		Element adultPriceSpan = doc.select("span.baseFarePrice").first();
		adultPrice = Double.valueOf(adultPriceSpan.text().replace(",", "").trim()).intValue();
		//System.out.println(adultPrice);

		// adult tax , 注意除以人数，得到单价
		Element adultTaxSpan = doc.select("span#taxesFaresTotal").first();
		int adultTaxTotal = Double.valueOf(adultTaxSpan.text().replace(",", "").trim()).intValue();
		adultTax = Integer.valueOf(adultTaxTotal / adultCount);
		//System.out.println(adultTax);

		TicketPrice ticketPrice = new TicketPrice();
		ticketPrice.setCurrency(currency);
		ticketPrice.setAdultPrice(adultPrice);
		ticketPrice.setAdultTax(adultTax);
		return ticketPrice;
	}
}
