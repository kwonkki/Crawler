package com.crawler.cebu;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Test;

import com.crawler.cebu.FormUtil.DestStation;
import com.crawler.cebu.FormUtil.OrgStation;
import com.crawler.cebu.FormUtil.TravelOption;

public class Main {
	private final static String URL = "https://book.cebupacificair.com/Search.aspx?culture=en-us";
	private final static String PostUrl = "https://book.cebupacificair.com/Search.aspx";
	private final static String SAVE_PATH_Response_Params = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/post_response_params.html";
	private final static String SAVE_PATH_Response = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/post_response.html";
	private final static String SAVE_PATH = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/test_savedHtmlByUrl.html";
	private final static String SAVE_PATH_IniFile = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/Book a Trip.html";
	
	private static CrawlerUtil crawlerUtil = CrawlerUtil.getInstance();
	private static ParseUtil parseUtil = ParseUtil.getInstance();
	
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
		map = parseUtil.parseTimeByFile(SAVE_PATH);
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
		map = parseUtil.parseStationsByFile(SAVE_PATH_IniFile);
		System.out.println(map);	// 输出站点信息
	}
	
	// 获取航班信息
	// 单程
	@Test
	public void getFlightInfoOneWay() {
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
		ArrayList<Map<String, String>> flightList = parseUtil.parseFlight(html);
		System.out.println(flightList);
		
		System.out.println("---------------------- file ---------------------");
		flightList = parseUtil.parseFlightByFile(SAVE_PATH_Response);
		System.out.println(flightList);
	}
	
	// 获取航班信息
	// 往返
	@Test
	public void getFlightInfoRound() {
		// 构建表单变量
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
		ArrayList<Map<String, String>> flightList = parseUtil.parseFlight(html);
		System.out.println(flightList);
		
		System.out.println("---------------------- file ---------------------");
		flightList = parseUtil.parseFlightByFile(SAVE_PATH_Response);
		System.out.println(flightList);
	}
}
