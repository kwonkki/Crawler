package cebu.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.junit.Test;

import cebu.model.FormParams;
import cebu.model.Ticket;
import cebu.util.Crawler;
import cebu.util.HtmlParser;
import cebu.util.FormUtil.DestStation;
import cebu.util.FormUtil.OrgStation;
import cebu.util.FormUtil.TravelOption;

public class Main {
	private final static String URL = "https://book.cebupacificair.com/Search.aspx?culture=en-us";
	private final static String PostUrl = "https://book.cebupacificair.com/Search.aspx";
	private final static String SAVE_PATH_Response_Params = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/post_response_params.html";
	private final static String SAVE_PATH_Response = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/post_response.html";
	private final static String SAVE_PATH_Response_Radio = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/post_response_radio.html";
	private final static String SAVE_PATH_Response_Radio_1 = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/post_response_radio_1.html";
	private final static String SAVE_PATH = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/test_savedHtmlByUrl.html";
	private final static String SAVE_PATH_IniFile = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/Book a Trip.html";
	
	private static Crawler crawler = Crawler.getInstance();
	private static HtmlParser htmlParser = HtmlParser.getInstance();
	
	public static void main(String[] args) {
		Main main = new Main();
		main.getTicketOneWay();
	}

	// 时间信息
	@Test
	public void getTimeInfo() {
		System.out.println("---------------------- url ---------------------");
		String html = crawler.getHtmlByUrl(URL);
		Map<String, ArrayList<String>> map = htmlParser.parseTime(html);
		System.out.println(map);
		
		System.out.println("---------------------- file ---------------------");
		map = htmlParser.parseTime(SAVE_PATH);
		System.out.println(map);
	}
	
	// 站点信息
	// 只能在原网页另存为本地文件后进行解析
	// 直接传递url的方式无法获取到站点信息
	@Test
	public void getStationInfo() {
		System.out.println("---------------------- url ---------------------");
		String html = crawler.getHtmlByUrl(URL);
		Map<String, String> map= htmlParser.parseStation(html);
		System.out.println(map);	// 输出为空
		
		System.out.println("---------------------- file ---------------------");
		map = htmlParser.parseStation(SAVE_PATH_IniFile);
		System.out.println(map);	// 输出站点信息
	}
	
	// 获取航班信息
	// 单程
	@Test
	public void getTicketOneWay() {
		// 构建表单变量
		FormParams formParams = new FormParams();
		formParams.setTravelOption(TravelOption.OneWay)
			.setOrgStation(OrgStation.HKG)
			.setDestStation(DestStation.MNL)
			.setDepartureTime("2016-06-25")
			.setAdultNum(2)
			.setChildNum(0)
			.build();
		
		// 获取提交查询表单之后的response html，记录cookieStore，以数组方式传址
		CookieStore[] cookieStores = new CookieStore[1];
		String html = crawler.getPostResponseHtmlByParams(PostUrl, formParams, cookieStores);
		
		// 获取提交表单之后的response html中的航班的radio value信息
		ArrayList<String> radioValues = htmlParser.parseRadioValue(html);
		
		// 依次提交么个radio value信息，get方式获取对应的html，包含航班的价格信息
		ArrayList<String> radioValueGeneratedHtmls = crawler.getHtmlByRadio(cookieStores[0], radioValues);
		
		// 根据html 和radio value html 解析完整的航班信息
		ArrayList<Ticket> tickets = htmlParser.parseTicket(html, radioValueGeneratedHtmls);
		
		//System.out.println(cookieStores[0]);
		//System.out.println(radioValues);
		//System.out.println(radioValueGeneratedHtmls);
		System.out.println(tickets);
	}
	

}
