package com.crawler.cebu;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.crawler.cebu.FormUtil.*;

public class ParseUtil {

	private CrawlerUtil crawlerUtil;

	/** 单例模式 **/
	private ParseUtil() {
		this.crawlerUtil = CrawlerUtil.getInstance();
	}

	private static class ParseUtilInstanceHolder {
		private static ParseUtil Parse_Util = new ParseUtil();
	}

	public static ParseUtil getInstance() {
		return ParseUtilInstanceHolder.Parse_Util;
	}

	private final static String WHITESPACE_UTF8 = "/u00a0"; // 空白符的ASCII码

	public void getInfo() {
		String url = "https://book.cebupacificair.com/Search.aspx?culture=en-us";
		String html = crawlerUtil.getHtmlByUrl(url);
		if (html != null && !"".equals(html)) {
			System.out.println(html);
			Document doc = Jsoup.parse(html);
			String selectStr = "select#ControlGroupSearchView_AvailabilitySearchInputSearchVieworiginStation1";
			Element target = doc.select(selectStr).get(0);
			System.out.println(target.text());
			Elements options = target.select("option");
			System.out.println("options count: " + options.size());
			for (Element option : options) {
				System.out.println(option.text().replace(WHITESPACE_UTF8, " "));
			}

			selectStr = "select#ControlGroupSearchView_AvailabilitySearchInputSearchViewdestinationStation1";
			target = doc.select(selectStr).get(0);
			System.out.println(target.text());
			options = target.select("option");
			System.out.println("options count: " + options.size());
			for (Element option : options) {
				System.out.println(option.text().replace(WHITESPACE_UTF8, " "));
			}
		}
	}

	/**
	 * 从文件解析html，提供相对应的url
	 * 
	 * @param filePath
	 *            本地html文件路径
	 * @param baseUrl
	 *            文件的原url地址，解析相关的链接
	 */
	public void parseByFileWithUrl(String filePath, String baseUrl) {
		if (filePath == null || filePath.equals("")) {
			System.out.println("no such file ...");
			return;
		}

		File file = new File(filePath);
		Document doc = null;
		try {
			doc = Jsoup.parse(file, "utf-8", baseUrl);
			String prefix = "select#";

			System.out.println("--------------------- day departure ---------------------");
			String selectDayDeparture = prefix + FormUtil.Name_CalendarDayDeparture.replace('$', '_');
			Element target = doc.select(selectDayDeparture).get(0);
			System.out.println("target text: " + target.text());
			Elements options = target.select("option");
			System.out.println("options count: " + options.size());
			for (Element option : options) {
				System.out.print("\"" + option.text() + "\", ");
			}
			System.out.println();

			System.out.println("--------------------- month departure ---------------------");
			String selectMonthDeparture = prefix + FormUtil.Name_CalendarMonthDeparture.replace('$', '_');
			target = doc.select(selectMonthDeparture).get(0);
			System.out.println("target text: " + target.text());
			options = target.select("option");
			System.out.println("options count: " + options.size());
			for (Element option : options) {
				System.out.print("\"" + option.text() + "\", ");
			}
			System.out.println();

			System.out.println("--------------------- day return ---------------------");
			String selectDayReturn = prefix + FormUtil.Name_CalendarDayReturn.replace('$', '_');
			target = doc.select(selectDayReturn).get(0);
			System.out.println("target text: " + target.text());
			options = target.select("option");
			System.out.println("options count: " + options.size());
			for (Element option : options) {
				System.out.print("\"" + option.text() + "\", ");
			}
			System.out.println();

			System.out.println("--------------------- month departure ---------------------");
			String selectMonthReturn = prefix + FormUtil.Name_CalendarMonthReturn.replace('$', '_');
			target = doc.select(selectMonthReturn).get(0);
			System.out.println("target text: " + target.text());
			options = target.select("option");
			System.out.println("options count: " + options.size());
			for (Element option : options) {
				System.out.print("\"" + option.text() + "\", ");
			}
			System.out.println();
		} catch (IOException e) {
			System.out.println("parse exeception ...");
			e.printStackTrace();
		}
	}

	/**
	 * 从文件解析html
	 * 
	 * @param filePath
	 *            本地html文件路径
	 */
	public void parseTimeByFile(String filePath) {
		if (filePath == null || filePath.equals("")) {
			System.out.println("no such file ...");
			return;
		}

		File file = new File(filePath);
		Document doc = null;
		try {
			doc = Jsoup.parse(file, "utf-8");
			String prefix = "select#";

			System.out.println("--------------------- day departure ---------------------");
			String selectDayDeparture = prefix + FormUtil.Name_CalendarDayDeparture.replace('$', '_');
			Element target = doc.select(selectDayDeparture).get(0);
			System.out.println("target text: " + target.text());
			Elements options = target.select("option");
			System.out.println("options count: " + options.size());
			for (Element option : options) {
				System.out.print("\"" + option.text() + "\", ");
			}
			System.out.println();

			System.out.println("--------------------- month departure ---------------------");
			String selectMonthDeparture = prefix + FormUtil.Name_CalendarMonthDeparture.replace('$', '_');
			target = doc.select(selectMonthDeparture).get(0);
			System.out.println("target text: " + target.text());
			options = target.select("option");
			System.out.println("options count: " + options.size());
			for (Element option : options) {
				System.out.print("\"" + option.text() + "\", ");
			}
			System.out.println();

			System.out.println("--------------------- day return ---------------------");
			String selectDayReturn = prefix + FormUtil.Name_CalendarDayReturn.replace('$', '_');
			target = doc.select(selectDayReturn).get(0);
			System.out.println("target text: " + target.text());
			options = target.select("option");
			System.out.println("options count: " + options.size());
			for (Element option : options) {
				System.out.print("\"" + option.text() + "\", ");
			}
			System.out.println();

			System.out.println("--------------------- month departure ---------------------");
			String selectMonthReturn = prefix + FormUtil.Name_CalendarMonthReturn.replace('$', '_');
			target = doc.select(selectMonthReturn).get(0);
			System.out.println("target text: " + target.text());
			options = target.select("option");
			System.out.println("options count: " + options.size());
			for (Element option : options) {
				System.out.print("\"" + option.text() + "\", ");
			}
			System.out.println();
		} catch (IOException e) {
			System.out.println("parse exeception ...");
			e.printStackTrace();
		}
	}

	public void savePostResponseHtml(String postUrl, String savePath) {
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		formParams.add(new BasicNameValuePair("__EVENTTARGET", ""));
		formParams.add(new BasicNameValuePair("__EVENTARGUMENT", ""));
		formParams.add(new BasicNameValuePair("__VIEWSTATE", "/wEPDwUBMGRkZ0I3jKvfnhKfx57f3Wjr7p2WhvU="));
		formParams.add(new BasicNameValuePair("pageToken", ""));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$RadioButtonMarketStructure", "OneWay"));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView_AvailabilitySearchInputSearchVieworiginStation1",
				"HKG"));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$TextBoxMarketOrigin1", "HKG"));
		formParams.add(new BasicNameValuePair("name_originStationContainercategory", "HongKong (HKG)"));
		formParams.add(new BasicNameValuePair("originStationContainercategory", "HKG"));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView_AvailabilitySearchInputSearchViewdestinationStation1", "MNL"));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$TextBoxMarketDestination1", "MNL"));
		formParams.add(new BasicNameValuePair("name_destinationStationContainercategory", "Manila (MNL)"));
		formParams.add(new BasicNameValuePair("destinationStationContainercategory", "MNL"));
		formParams.add(new BasicNameValuePair("date_picker_1", "2016-06-20"));
		formParams.add(new BasicNameValuePair("date_picker_1_AccCalendar", "2016-06-20"));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListMarketDay1", "20"));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListMarketMonth1", "2016-06"));
		formParams.add(
				new BasicNameValuePair("ControlGroupSearchView_AvailabilitySearchInputSearchVieworiginStation2", ""));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$TextBoxMarketOrigin2", ""));
		formParams.add(new BasicNameValuePair("name_originStationContainercategory", "From"));
		formParams.add(new BasicNameValuePair("originStationContainercategory", ""));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView_AvailabilitySearchInputSearchViewdestinationStation2", ""));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$TextBoxMarketDestination2", ""));
		formParams.add(new BasicNameValuePair("name_destinationStationContainercategory", "To"));
		formParams.add(new BasicNameValuePair("destinationStationContainercategory", ""));
		formParams.add(new BasicNameValuePair("date_picker_2", "2016-06-20"));
		formParams.add(new BasicNameValuePair("date_picker_2_AccCalendar", "2016-06-20"));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListMarketDay2", "20"));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListMarketMonth2", "2016-06"));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListPassengerType_ADT", "1"));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListPassengerType_CHD", "0"));
		formParams.add(
				new BasicNameValuePair("ControlGroupSearchView$AvailabilitySearchInputSearchView$promoCodeID", ""));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView$AvailabilitySearchInputSearchView$ButtonSubmit",
				"FIND IT!"));
		formParams.add(new BasicNameValuePair("MemberLoginView2LoginView$TextBoxUserID", "EMAIL"));
		formParams.add(new BasicNameValuePair("MemberLoginView2LoginView$PasswordFieldPassword", ""));
		formParams.add(new BasicNameValuePair("MemberLoginView2LoginView$tboxConfirmation", ""));
		formParams.add(new BasicNameValuePair("MemberLoginView2LoginView$tboxHiddenUsername", ""));

		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);

		HttpPost httpPost = new HttpPost(postUrl);
		httpPost.setHeader("Host", "book.cebupacificair.com");
		httpPost.setHeader("User-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.7 Safari/537.36");
		httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		httpPost.setHeader("Accept-Encoding", "gzip, deflate, br");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		httpPost.setHeader("Referer", "https://book.cebupacificair.com/Search.aspx?culture=en-us");

		httpPost.setEntity(formEntity);

		/*
		 * try { System.out.println(
		 * "--------------------------------- executing request entity ------------------------------------"
		 * );
		 * System.out.println(crawlerUtil.getStrByInputStream(httpPost.getEntity
		 * ().getContent())); } catch (UnsupportedOperationException |
		 * IOException e1) { e1.printStackTrace(); }
		 * 
		 * System.out.println(
		 * "--------------------------------- request headers ------------------------------------"
		 * ); Header[] headers1 = httpPost.getAllHeaders(); for(Header header1 :
		 * headers1) System.out.println(header1.getName() + " : " +
		 * header1.getValue());
		 */

		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;

		try {
			// 处理post之后的重定向
			httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
			response = httpClient.execute(httpPost);

			/*
			 * System.out.println(crawlerUtil.getStrByInputStream(response.
			 * getEntity().getContent()));
			 * 
			 * System.out.println(
			 * "--------------------------------- response headers ------------------------------------"
			 * ); Header[] headers = response.getAllHeaders(); for(Header header
			 * : headers) System.out.println(header.getName() + " : " +
			 * header.getValue());
			 * 
			 * System.out.println(
			 * "--------------------------------- response header location ------------------------------------"
			 * ); Header[] locationHeaders = response.getHeaders("Location");
			 * System.out.println("location header length : " +
			 * locationHeaders.length); if(locationHeaders.length == 0)
			 * System.out.println("header location length is 0 ..."); else
			 * System.out.println(locationHeaders[0].getName() + " : " +
			 * locationHeaders[0].getValue());
			 * 
			 * 
			 * System.out.println(
			 * "--------------------------------- response string ------------------------------------"
			 * ); System.out.println(response.toString());
			 */

			System.out.println("response code : " + response.getStatusLine().getStatusCode());
			crawlerUtil.saveHtmlByHttpResponse(response, savePath);

		} catch (Exception e) {
			System.out.println("访问[ " + postUrl + " ]出现异常!");
			e.printStackTrace();
		} finally {
			crawlerUtil.free(response, httpClient);
		}
	}

	
	
	public void savePostResponseHtmlByParams(String postUrl, String savePath) {
		FormParams formParams = new FormParams();
		formParams.setTravelOption(TravelOption.OneWay)
			.setOrgStation(OrgStation.HKG)
			.setDestStation(DestStation.MNL)
			.setDepartureTime("2016-06-20")
			.setAdultNum(1)
			.setChildNum(0)
			.build();
		

		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(formParams.getFormParams(), Consts.UTF_8);

		HttpPost httpPost = new HttpPost(postUrl);
		httpPost.setHeader("Host", "book.cebupacificair.com");
		httpPost.setHeader("User-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.7 Safari/537.36");
		httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		httpPost.setHeader("Accept-Encoding", "gzip, deflate, br");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		httpPost.setHeader("Referer", "https://book.cebupacificair.com/Search.aspx?culture=en-us");

		httpPost.setEntity(formEntity);

		/*
		 * try { System.out.println(
		 * "--------------------------------- executing request entity ------------------------------------"
		 * );
		 * System.out.println(crawlerUtil.getStrByInputStream(httpPost.getEntity
		 * ().getContent())); } catch (UnsupportedOperationException |
		 * IOException e1) { e1.printStackTrace(); }
		 * 
		 * System.out.println(
		 * "--------------------------------- request headers ------------------------------------"
		 * ); Header[] headers1 = httpPost.getAllHeaders(); for(Header header1 :
		 * headers1) System.out.println(header1.getName() + " : " +
		 * header1.getValue());
		 */

		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;

		try {
			// 处理post之后的重定向
			httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
			response = httpClient.execute(httpPost);

			/*
			 * System.out.println(crawlerUtil.getStrByInputStream(response.
			 * getEntity().getContent()));
			 * 
			 * System.out.println(
			 * "--------------------------------- response headers ------------------------------------"
			 * ); Header[] headers = response.getAllHeaders(); for(Header header
			 * : headers) System.out.println(header.getName() + " : " +
			 * header.getValue());
			 * 
			 * System.out.println(
			 * "--------------------------------- response header location ------------------------------------"
			 * ); Header[] locationHeaders = response.getHeaders("Location");
			 * System.out.println("location header length : " +
			 * locationHeaders.length); if(locationHeaders.length == 0)
			 * System.out.println("header location length is 0 ..."); else
			 * System.out.println(locationHeaders[0].getName() + " : " +
			 * locationHeaders[0].getValue());
			 * 
			 * 
			 * System.out.println(
			 * "--------------------------------- response string ------------------------------------"
			 * ); System.out.println(response.toString());
			 */

			System.out.println("response code : " + response.getStatusLine().getStatusCode());
			crawlerUtil.saveHtmlByHttpResponse(response, savePath);

		} catch (Exception e) {
			System.out.println("访问[ " + postUrl + " ]出现异常!");
			e.printStackTrace();
		} finally {
			crawlerUtil.free(response, httpClient);
		}
	}

	
	/**
	 * 从html文件中解析航班信息
	 * 
	 * @param filePath
	 *            文件本地路径
	 */
	public void parseFlightByFile(String filePath) {
		if (filePath == null || filePath.equals("")) {
			System.out.println("no such file ...");
			return;
		}

		File file = new File(filePath);
		Document doc = null;
		try {
			doc = Jsoup.parse(file, "utf-8"); // 文件解析

			// 定位到table
			String selectTable = "table#availabilityTable";
			Element table = doc.select(selectTable).get(0);

			// 表头
			Elements tHead = table.select("thead>tr");
			for (Element headName : tHead)
				System.out.print(headName.text());
			System.out.println();

			// 航班，每一个航班信息存放在tr中
			Elements trs = table.select("tbody>tr");
			// System.out.println("tr count: " + trs.size());

			int i = 0;
			for (Element tr : trs) {
				System.out.println("---------------------------------------------" + (++i));
				Map<String, String> mapFlightInfo = this.parseFlightInfoInTr(tr); // 解析单个tr中的航班
				this.printMap(mapFlightInfo); // 打印航班map信息
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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
			if(++i == 10) {
				i = 0;
				System.out.println();
			}
				
		}
		
		System.out.println("--------------------");
		i = 0;
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.print(entry.getValue() + ", ");
			if(++i == 10) {
				i = 0;
				System.out.println();
			}
				
		}
			
	}

	/**
	 * 解析每一个tr中的航班信息
	 * 
	 * @param tr
	 * @return
	 */
	private Map<String, String> parseFlightInfoInTr(Element tr) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (tr == null)
			return map;
		Elements tds = tr.select("td");

		// 起始站点
		String from = tds.get(0).text();
		String to = tds.get(1).text();

		// 航班号和详细信息
		Element tdDetails = tr.select("td.footnote").get(0);
		String flightCompany = tdDetails.select("Strong").get(0).text();
		String flightNumber = tdDetails.select("span.flightInfoLink").get(0).text();
		// System.out.println("flight company: " + flightCompany);
		// System.out.println("flight number: " + flightNumber);
		String flight = flightCompany + " " + flightNumber;
		String flightDetails = tdDetails.select("p.pAirportName").get(0).text();
		// System.out.println("flight details: " + flightDetails);

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
		 * 数组中分别存放 String flyPriceHidden = ""; // 隐藏价格 String
		 * flyPriceHiddenDetails = ""; // 隐藏细节 String flyPrice = ""; // 显示价格
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
			strings[0 + i] = tmpFlyPrice;

			// 细节信息
			Elements lis = tdFareFlyDivs.get(i).select("li");
			StringBuffer sb = new StringBuffer(100);
			for (int j = 0; j < 7; j++) // 只需要前7个li标签的信息
				sb.append(lis.get(j).text() + " ");
			tmpFlyPriceDetails = sb.toString();
			strings[1 + i] = tmpFlyPriceDetails;
		}
		return strings;
	}
	
	/**
	 * 获取站点信息，起始和终点站点列表完全一样
	 * @param filePath
	 * @return
	 */
	public Map<String, String> parseStationsByFile(String filePath) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (filePath == null || filePath.equals("")) {
			System.out.println("no such file ...");
			return map;
		}
		
		System.out.println(filePath);
		File file = new File(filePath);
		Document doc = null;
		try {
			doc = Jsoup.parse(file, "utf-8");
			// 定位到站点div
			Element cityPairDiv = doc.select("div#marketCityPair_1").get(0);
			Elements options = cityPairDiv.select("option");
			for(Element option : options) {
				String key = option.val();
				String value = option.text();
				if (!key.equals("") && !value.equals("")) 	// 去除空串
					map.put(key, value);
			}
		} catch (IOException e) {
			System.out.println("parse exeception ...");
			e.printStackTrace();
		}
		return map;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
