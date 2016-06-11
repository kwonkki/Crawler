package com.crawler.cebu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 利用jsoup，html解析工具类
 * 1、解析html字符串中的始发到站时间列表、始发站和终点站列表、post表单提交之后的航班查询信息
 * 2、解析本地html文件中的始发到站时间列表、始发站和终点站列表、post表单提交之后的航班查询信息
 * @author lihaijun
 *
 */

public class ParseUtil {

	/** 单例模式 **/
	private ParseUtil() {

	}

	private static class ParseUtilInstanceHolder {
		private static ParseUtil Parse_Util = new ParseUtil();
	}

	public static ParseUtil getInstance() {
		return ParseUtilInstanceHolder.Parse_Util;
	}

	private final static String WHITESPACE_UTF8 = "/u00a0"; // 空白符的ASCII码

	/**
	 * 从本地html文件中读取信息
	 * @param filePath	本地文件路径
	 * @return
	 */
	private String readHtmlFromFile(String filePath) {
		if (filePath == null || filePath.equals("")) {
			System.out.println("no such file ...");
			return "";
		}

		File file = new File(filePath);
		StringBuffer sb = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			sb = new StringBuffer();
			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line + "\r\n"); // 逐行读取，回车换行
			}
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	
	/**
	 * 从html字符串解析时间信息
	 * @param html html字符串
	 * @return	
	 */
	public Map<String, ArrayList<String>> parseTime(String html) {
		// 出发日、出发年月、回程日、回程年月4个list组成的map
		Map<String, ArrayList<String>> map = new LinkedHashMap<String, ArrayList<String>>();
		if (html == null || html.equals(""))
			return map;

		Document doc = null;
		doc = Jsoup.parse(html);
		
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
	 * 从doc中解析时间信息
	 * @param doc
	 * @return
	 */
	private Map<String, ArrayList<String>> parseTimeByDoc(Document doc) {
		// 出发日、出发年月、回程日、回程年月4个list组成的map
		Map<String, ArrayList<String>> map = new LinkedHashMap<String, ArrayList<String>>();
		if(doc == null)
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
	 * 从html字符串解析时间信息
	 * @param html html字符串
	 * @param baseUrl html来源的url地址，用于解析html字符串中的其他路径相关信息
	 * @return	
	 */
	public Map<String, ArrayList<String>> parseTimeWithUrl(String html, String baseUrl) {
		// 出发日、出发年月、回程日、回程年月4个list组成的map
		Map<String, ArrayList<String>> map = new LinkedHashMap<String, ArrayList<String>>();
		if (html == null || html.equals(""))
			return map;

		Document doc = null;
		doc = Jsoup.parse(html, baseUrl);
		map = this.parseTimeByDoc(doc);
		return map;
	}


	/**
	 * 从文件解析html，提供相对应的url
	 * 
	 * @param filePath
	 *            本地html文件路径
	 * @param baseUrl
	 *            文件的原url地址，解析相关的链接
	 */
	public Map<String, ArrayList<String>> parseTimeByFileWithUrl(String filePath, String baseUrl) {
		String html = this.readHtmlFromFile(filePath);
		return this.parseTimeWithUrl(html, baseUrl);
	}

	/**
	 * 从文件解析html
	 * 
	 * @param filePath
	 *            本地html文件路径
	 */
	public Map<String, ArrayList<String>> parseTimeByFile(String filePath) {
		String html = this.readHtmlFromFile(filePath);
		return this.parseTime(html);
	}

	/**
	 * 由html string解析航班信息
	 * @param html html字符串
	 * @return	
	 */
	public ArrayList<Map<String, String>> parseFlight(String html) {
		ArrayList<Map<String, String>> flightList = new ArrayList<Map<String, String>>();
		if (html == null || html.equals(""))
			return flightList;

		Document doc = null;
		doc = Jsoup.parse(html); // 文件解析
		return this.parseFlightByDoc(doc);
	}

	/**
	 * 从html string 获取航班信息
	 * 提供String来源的url，解析路径相关信息
	 * @param html
	 * @param baseUrl
	 * @return
	 */
	public ArrayList<Map<String, String>> parseFlightWithUrl(String html, String baseUrl) {
		ArrayList<Map<String, String>> flightList = new ArrayList<Map<String, String>>();
		if (html == null || html.equals(""))
			return flightList;

		Document doc = null;
		doc = Jsoup.parse(html, baseUrl); // 文件解析
		return this.parseFlightByDoc(doc);
	}

	/**
	 * 由doc解析航班信息
	 * @param doc doc对象
	 * @return 航班列表，每个航班是一个map，包含键值对属性信息
	 */
	private ArrayList<Map<String, String>> parseFlightByDoc(Document doc) {
		ArrayList<Map<String, String>> flightList = new ArrayList<Map<String, String>>();
		if (doc == null)
			return flightList;
		
		// 定位到table
		String selectTable = "table#availabilityTable";
		Element table = doc.select(selectTable).get(0);

		// 表头
		StringBuffer sb = new StringBuffer(80);
		Elements tHead = table.select("thead>tr");
		for (Element headName : tHead)
			sb.append(headName.text());
		Map<String, String> mapTableHead = new LinkedHashMap<String, String>();
		mapTableHead.put(FormUtil.TableHead, sb.toString());
		flightList.add(mapTableHead);
		
		// 航班，每一个航班信息存放在tr中
		Elements trs = table.select("tbody>tr");

		for (Element tr : trs) {
			//System.out.println("---------------------------------------------" + (++i));
			Map<String, String> mapFlightInfo = this.parseFlightInfoInTr(tr); // 解析单个tr中的航班
			flightList.add(mapFlightInfo); // 打印航班map信息
		}
		return flightList;
	}

	/**
	 * 从html文件中解析航班信息
	 * 
	 * @param filePath
	 *            文件本地路径
	 */
	public ArrayList<Map<String, String>> parseFlightByFile(String filePath) {
		String html = this.readHtmlFromFile(filePath);
		return this.parseFlight(html);
	}
	
	/**
	 * 从本地文件解析航班信息
	 * 提供文件来源的url，解析路径相关信息
	 * @param filePath	本地文件路径
	 * @param baseUrl	来源url
	 * @return
	 */
	public ArrayList<Map<String, String>> parseFlightByFileWithUrl(String filePath, String baseUrl) {
		String html = this.readHtmlFromFile(filePath);
		return this.parseFlightWithUrl(html, baseUrl);
	}
		
		
	/**
	 * 打印map容器信息
	 * 
	 * @param map
	 */
	public void printMap(Map<String, String> map) {
		for (Map.Entry<String, String> entry : map.entrySet())
			System.out.println(entry.getKey() + " : " + entry.getValue());

		System.out.println("--------------------");
		int i = 0;
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.print(entry.getKey() + ", ");
			if (++i == 10) {
				i = 0;
				System.out.println();
			}

		}

		System.out.println("--------------------");
		i = 0;
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.print(entry.getValue() + ", ");
			if (++i == 10) {
				i = 0;
				System.out.println();
			}

		}

	}

	/**
	 * 解析每一个tr中的航班信息
	 * 
	 * @param tr
	 * @return 每一个航班是一个map，包含对应的键值对属性信息
	 */
	private Map<String, String> parseFlightInfoInTr(Element tr) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (tr == null)
			return map;
		Elements tds = tr.select("td");

		// 起始站点，可能存在转机情况下的换乘站点
		String from = "";
		String to = "";
		// 存在两个td，则出现换乘转机
		boolean isTransferHappen = (tds.get(0).select(">b").size() > 1) ? true : false;
		
		if (isTransferHappen) {
			Elements spansInTd1 = tds.get(0).select(">span");
			String from1NextDayArrival = "";
			String to1NextDayArrival = "";
			int spanCount = spansInTd1.size();
			if (spanCount == 1) 
				from1NextDayArrival = spansInTd1.first().text();
			else if (spanCount == 2) {
				from1NextDayArrival = spansInTd1.first().text();
				to1NextDayArrival = spansInTd1.last().text();
			}
			
			String textTd1 = tds.get(0).text()
					.replace(from1NextDayArrival, "").replace(to1NextDayArrival, "");
			int lastIndexOfNum = -1;
			for(int i = textTd1.length() - 1; i >=0; i--) {
				char ch = textTd1.charAt(i);
				if (ch >= '0' && ch <= '9') {
					lastIndexOfNum = i;
					break;
				}
			}
			String from1 = textTd1.substring(0, lastIndexOfNum - 3);
			String to1 = textTd1.substring(lastIndexOfNum - 3, textTd1.length()); 
			
			Elements spansInTd2 = tds.get(1).select(">span");
			String from2NextDayArrival = "";
			String to2NextDayArrival = "";
			if (spansInTd2.size() == 1) 
				from2NextDayArrival = spansInTd2.first().text();
			else if (spansInTd2.size() == 2) {
				from2NextDayArrival = spansInTd2.first().text();
				to2NextDayArrival = spansInTd2.last().text();
			}
				
			String textTd2 = tds.get(1).text()
					.replace(from2NextDayArrival, "").replace(to2NextDayArrival, "");
			lastIndexOfNum = -1;
			for(int i = textTd2.length() - 1; i >=0; i--) {
				char ch = textTd2.charAt(i);
				if (ch >= '0' && ch <= '9') {
					lastIndexOfNum = i;
					break;
				}
			}
			String from2 = textTd2.substring(0, lastIndexOfNum - 3);
			String to2 = textTd2.substring(lastIndexOfNum - 3, textTd2.length()); 
			
			from = from1 + " " + from1NextDayArrival + " " + from2 + " " + from2NextDayArrival;
			to = to1 + " " + to1NextDayArrival + " " + to2 + " " + to2NextDayArrival;
		} else {
			from = tds.get(0).text();
			to = tds.get(1).text();
		}

		// 航班号和详细信息
		Element tdDetails = tr.select("td.footnote").get(0);
		String flightCompany = tdDetails.select("Strong").get(0).text();
		String flightNumber = tdDetails.select("span.flightInfoLink").get(0).text();
		String flight = flightCompany + " " + flightNumber;
		String flightDetails = tdDetails.select("p.pAirportName").get(0).text();

		// fly 价格、详细信息
		Element tdFareFly = tr.select("td.fareBundle").get(0);
		String[] fareFlyInfo = this.parseFareTd(tdFareFly);

		// fly+bag 价格、详细信息
		Element tdFareFlyBag = tr.select("td.fareBundle_FBAG").get(0);
		String[] fareFlyBagInfo = this.parseFareTd(tdFareFlyBag);

		// fly+bag+meal 价格、详细信息
		Element tdFareFlyBagMeal = tr.select("td.fareBundle_FBAGMEAL").get(0);
		String[] fareFlyBagMealInfo = this.parseFareTd(tdFareFlyBagMeal);

		/** 变量信息存入map **/
		map.put(FormUtil.From, from);
		map.put(FormUtil.To, to);

		map.put(FormUtil.Flight, flight);
		map.put(FormUtil.Flight_Details, flightDetails);

		map.put(FormUtil.Price_Fly_Hidden, fareFlyInfo[0]);
		map.put(FormUtil.Details_Fly_Hidden, fareFlyInfo[1]);

		map.put(FormUtil.Price_Fly, fareFlyInfo[2]);
		map.put(FormUtil.Details_Fly, fareFlyInfo[3]);

		map.put(FormUtil.Price_Fly_Bag_Hidden, fareFlyBagInfo[0]);
		map.put(FormUtil.Details_Fly_Bag_Hidden, fareFlyBagInfo[1]);

		map.put(FormUtil.Price_Fly_Bag, fareFlyBagInfo[2]);
		map.put(FormUtil.Details_Fly_Bag, fareFlyBagInfo[3]);

		map.put(FormUtil.Price_Fly_Bag_Meal_Hidden, fareFlyBagMealInfo[0]);
		map.put(FormUtil.Details_Fly_Bag_Meal_Hidden, fareFlyBagMealInfo[1]);

		map.put(FormUtil.Price_Fly_Bag_Meal, fareFlyBagMealInfo[2]);
		map.put(FormUtil.Details_Fly_Bag_Meal, fareFlyBagMealInfo[3]);

		return map;
	}

	/**
	 * 解析航班价格及其具体信息所在的td
	 * 
	 * @param td
	 * @return
	 */
	private String[] parseFareTd(Element td) {
		/**
		 * 数组中分别存放
		 * String flyPriceHidden = ""; // 隐藏价格 String
		 * flyPriceHiddenDetails = ""; // 隐藏细节 
		 * String flyPrice = ""; // 显示价格
		 * String flyPriceDetails = ""; // 显示细节
		 */
		String[] strings = { "", "", "", "" };
		if (td == null)
			return strings;

		Elements tdFareFlyDivs = td.select(">div.farePrices");
		String tmpFlyPrice = "";
		String tmpFlyPriceDetails = "";
		for (int i = 0; i < tdFareFlyDivs.size(); i++) {
			// 价格
			tmpFlyPrice = tdFareFlyDivs.get(i).select("span.ADTprice").get(0).text();
			strings[0 + i * 2] = tmpFlyPrice;

			// 细节信息
			Elements lis = tdFareFlyDivs.get(i).select("li");
			StringBuffer sb = new StringBuffer(100);
			for (int j = 0; j < 7; j++) // 只需要前7个li标签的信息
				sb.append(lis.get(j).text() + " ");
			tmpFlyPriceDetails = sb.toString();
			strings[1 + i * 2] = tmpFlyPriceDetails;
		}
		return strings;
	}

	/**
	 * 从html string获取站点信息，起始和终点站点列表完全一样
	 * @param html
	 *            html字符串
	 * @return
	 */
	public Map<String, String> parseStation(String html) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (html == null || html.equals(""))
			return map;

		Document doc = Jsoup.parse(html);;
		return this.parseStationByDoc(doc);
	}


	/**
	 * 由Doc解析Station信息
	 * @param doc	Document对象
	 * @return map对象，Station的缩写和全称
	 */
	private Map<String, String> parseStationByDoc(Document doc) {
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
	 * 从html string获取站点信息，起始和终点站点列表完全一样
	 * 提供String来源的url，解析路径相关信息
	 * @param html
	 * @param baseUrl
	 * @return
	 */
	public Map<String, String> parseStationWithUrl(String html, String baseUrl) { 
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (html == null || html.equals(""))
			return map;

		Document doc = null;
		doc = Jsoup.parse(html, baseUrl);
		return this.parseStationByDoc(doc);
		
	}

	/**
	 * 获取站点信息，起始和终点站点列表完全一样
	 * @param filePath
	 * @return
	 */
	public Map<String, String> parseStationsByFile(String filePath) {
		String html = this.readHtmlFromFile(filePath);
		return this.parseStation(html);
	}
	
	/**
	 * 获取站点信息，起始和终点站点列表完全一样
	 * 提供String来源的url，解析路径相关信息
	 * @param filePath
	 * @param baseUrl
	 * @return
	 */
	public Map<String, String> parseStationsByFileWithUrl(String filePath, String baseUrl) {
		String html = this.readHtmlFromFile(filePath);
		return this.parseStationWithUrl(html, baseUrl);
	}

}
