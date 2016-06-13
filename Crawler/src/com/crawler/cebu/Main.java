package com.crawler.cebu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.junit.Test;

import com.crawler.cebu.FormUtil.DestStation;
import com.crawler.cebu.FormUtil.OrgStation;
import com.crawler.cebu.FormUtil.TravelOption;

import cebu.model.FormParams;
import cebu.util.Crawler;
import cebu.util.HtmlParser;

public class Main {
	private final static String URL = "https://book.cebupacificair.com/Search.aspx?culture=en-us";
	private final static String PostUrl = "https://book.cebupacificair.com/Search.aspx";
	private final static String SAVE_PATH_Response_Params = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/post_response_params.html";
	private final static String SAVE_PATH_Response = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/post_response.html";
	private final static String SAVE_PATH_Response_Radio = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/post_response_radio.html";
	private final static String SAVE_PATH_Response_Radio_1 = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/post_response_radio_1.html";
	private final static String SAVE_PATH = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/test_savedHtmlByUrl.html";
	private final static String SAVE_PATH_IniFile = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/Book a Trip.html";
	
	private static Crawler crawlerUtil = Crawler.getInstance();
	private static HtmlParser parseUtil = HtmlParser.getInstance();
	
	public static void main(String[] args) {
		//Main main = new Main();
	}

	// 时间信息
	@Test
	public void getTimeInfo() {
		System.out.println("---------------------- url ---------------------");
		String html = crawlerUtil.getHtmlByUrl(URL);
		Map<String, ArrayList<String>> map = parseUtil.parseTime(html);
		System.out.println(map);
		
		System.out.println("---------------------- file ---------------------");
		map = parseUtil.parseTime(SAVE_PATH);
		System.out.println(map);
	}
	
	// 站点信息
	// 只能在原网页另存为本地文件后进行解析
	// 直接传递url的方式无法获取到站点信息
	@Test
	public void getStationInfo() {
		System.out.println("---------------------- url ---------------------");
		String html = crawlerUtil.getHtmlByUrl(URL);
		Map<String, String> map= parseUtil.parseStation(html);
		System.out.println(map);	// 输出为空
		
		System.out.println("---------------------- file ---------------------");
		map = parseUtil.parseStation(SAVE_PATH_IniFile);
		System.out.println(map);	// 输出站点信息
	}
	
	// 获取航班信息
	// 单程
	@Test
	public void getTicketInfoOneWay() {
		// 构建表单变量
		FormParams formParams = new FormParams();
		formParams.setTravelOption(TravelOption.OneWay)
			.setOrgStation(OrgStation.HKG)
			.setDestStation(DestStation.MNL)
			.setDepartureTime("2016-06-20")
			.setAdultNum(1)
			.setChildNum(0)
			.build();
		
		System.out.println("---------------------- url ---------------------");
		String html = crawlerUtil.getPostResponseHtmlByParams(PostUrl, formParams);
		ArrayList<Map<String, String>> TicketList = parseUtil.parseTicket(html);
		ToolUtil.printTicketInfo(TicketList);
		
		System.out.println("---------------------- file ---------------------");
		TicketList = parseUtil.parseTicket(SAVE_PATH_Response);
		ToolUtil.printTicketInfo(TicketList);
	}
	
	// 获取航班信息
	// 往返
	@Test
	public void getTicketInfoRound() {
/*		// 构建表单变量
		FormParams formParams = new FormParams();
		formParams.setTravelOption(TravelOption.RoundTrip)
			.setOrgStation(OrgStation.HKG)
			.setDestStation(DestStation.MNL)
			.setDepartureTime("2016-06-20")
			.setReturnTime("2016-06-25")
			.setAdultNum(1)
			.setChildNum(0)
			.build();
		
		System.out.println("---------------------- url ---------------------");
		String html = crawlerUtil.getPostResponseHtmlByParams(PostUrl, formParams);
		ArrayList<Map<String, String>> TicketList = parseUtil.parseTicket(html);
		ToolUtil.printTicketInfo(TicketList);*/
		
		System.out.println("---------------------- file ---------------------");
		ArrayList<Map<String, String>> TicketList = parseUtil.parseTicket(SAVE_PATH_Response);
		ToolUtil.printTicketInfo(TicketList);
	}
	
	
	@Test 
	public void getTicketRadioValues() {
		// 构建表单变量
		FormParams formParams = new FormParams();
		formParams.setTravelOption(TravelOption.OneWay)
			.setOrgStation(OrgStation.HKG)
			.setDestStation(DestStation.MNL)
			.setDepartureTime("2016-06-20")
			.setAdultNum(1)
			.setChildNum(0)
			.build();
		
		String html = crawlerUtil.getPostResponseHtmlByParams(PostUrl, formParams);
		
		ArrayList<String> list = parseUtil.parseTicketRadioValues(html);
		System.out.println(list);
	}
	
	
	@Test
	public void getTicketRadioValuesInfo() {
		// 构建表单变量
		FormParams formParams = new FormParams();
		formParams.setTravelOption(TravelOption.OneWay)
			.setOrgStation(OrgStation.HKG)
			.setDestStation(DestStation.MNL)
			.setDepartureTime("2016-06-20")
			.setAdultNum(2)
			.setChildNum(0)
			.build();
		
		crawlerUtil.savePostResponseHtmlByParams(PostUrl, formParams, SAVE_PATH_Response_Params);
		
		HttpClientContext context = HttpClientContext.create();
		CookieStore[] cookieStores = {context.getCookieStore()};
		String html = crawlerUtil.getPostResponseHtmlByParams(PostUrl, formParams, cookieStores);
		CookieStore cookieStore = cookieStores[0];
		
		List<Cookie> cookies = cookieStore.getCookies();
		for(Cookie cookie : cookies) {
			System.out.println(cookie.getName() + "=" + cookie.getValue());
		}
		
		ArrayList<String> radioValues = parseUtil.parseTicketRadioValues(html);
		
		int size = radioValues.size();
		for(int i = 0; i < size; i++) {
			crawlerUtil.saveHtmlByRadio(cookieStore, radioValues.get(i), 
					SAVE_PATH_Response_Radio.replace(".html", "_" + (i + 1) + ".html"));
		}
		System.out.println("done");
	}
	

}
