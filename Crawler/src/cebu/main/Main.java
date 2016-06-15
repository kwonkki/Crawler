package cebu.main;

import java.util.ArrayList;
import java.util.Map;
import org.apache.http.client.CookieStore;
import org.junit.Test;

import cebu.dao.service.TicketService;
import cebu.model.FormParams;
import cebu.model.Ticket;
import cebu.util.Crawler;
import cebu.util.HtmlParser;
import cebu.util.FormUtil.DestStation;
import cebu.util.FormUtil.OrgStation;
import cebu.util.FormUtil.TravelOption;

public class Main {
	
	// 文件保存目录
	private final static String Dir = "D:/Documents/Github/Crawler/Data/";
	//private final static String Dir = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/";
	
	// 文件路径前缀和后缀
	private final static String Prefix_Save_File = Dir + "post_response_";
	private final static String Suffix_Save_File = ".html";
	
	// 原始url
	private final static String URL = "https://book.cebupacificair.com/Search.aspx?culture=en-us";
	// 提交表单url
	private final static String PostUrl = "https://book.cebupacificair.com/Search.aspx";
	
	private final static String SAVE_PATH_FORMPARAMS = Dir + "avaliable_formParams.txt";
	private final static String SAVE_PATH = Dir + "test_savedHtmlByUrl.html";
	private final static String SAVE_PATH_IniFile = Dir + "Book a Trip.html";
	
	// 爬虫类和html解析类
	private static Crawler crawler = Crawler.getInstance();
	private static HtmlParser htmlParser = HtmlParser.getInstance();
	
	public static void main(String[] args) {
		Main main = new Main();
		main.get_save_ByAllFormParams();
		
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
	
	
	@Test
	public void get_save_ByAllFormParams() {
		ArrayList<FormParams> avaliableFormParams = new ArrayList<FormParams>(); 
		
		ArrayList<FormParams> allFormParams = buildAllFormParams();
		System.out.println("allFormParams count: " + allFormParams.size());
		int allCount = 0;
		int ticketCount = 0;
		for(FormParams formParams : allFormParams) {
			//System.out.println(formParams);
			int avaliableCount = this.get_save_TicketOneWayByParams(formParams);
			// 有效的表单变量
			if(avaliableCount > 0)
				avaliableFormParams.add(formParams);
			
			ticketCount += avaliableCount;
			System.out.println("current count: " + (++allCount) + ", ticket count: " + ticketCount);
		}
		
		// 保存有效表单便当到文件
		this.saveAvaliableFormParamsToFile(avaliableFormParams, SAVE_PATH_FORMPARAMS);
	}
	
	/**
	 * 保存有效表单便当到文件
	 * @param list
	 * @param savePath
	 */
	public void saveAvaliableFormParamsToFile(ArrayList<FormParams> list, String savePath) {
		StringBuilder sb = new StringBuilder();
		for(FormParams params : list) {
			String str = params.toString() + "\r\n";
			sb.append(str);
		}
		crawler.saveHtmlToFile(sb.toString(), savePath);
	}
	
	
	
	/**
	 * 构建所有可能的变量
	 * 单程，固定时间，共88*88=7744 - 88(重复的）个，共7656个
	 * @return
	 */
	public ArrayList<FormParams> buildAllFormParams() {
		ArrayList<FormParams> allFormParams = new ArrayList<FormParams>();
		
		// 构建表单变量
		TravelOption travelOption = TravelOption.OneWay;
		String depTime = "2016-06-20";
		int adultNum = 2;
		int childNum = 0;
		
		for(OrgStation orgStation : OrgStation.values()) {
			for(DestStation destStation : DestStation.values()) {
				// 始发和终点相同的跳过
				if (orgStation.name().equals(destStation.name())) {
					// System.out.println("equals");
					continue;
				}
					
				FormParams formParams = new FormParams();
				formParams.setTravelOption(travelOption)
					.setOrgStation(orgStation)
					.setDestStation(destStation)
					.setDepartureTime(depTime)
					.setAdultNum(adultNum)
					.setChildNum(childNum)
					.build();
				
				allFormParams.add(formParams);
			}
		}
		
		return allFormParams;
	}
	
	
	
	
	/**
	 * 根据表单参数，获取信息同时保存相关html文件
	 * 单程
	 */
	public int get_save_TicketOneWayByParams(FormParams formParams) {
		/** 文件保存路径  **/
		// post response
		String savePathPost = Prefix_Save_File 
				+ formParams.getTravelOption() + "_" 
				+ formParams.getOrgStation() + "_"
				+ formParams.getDestStation() + "_"
				+ formParams.getDepartureTime() + Suffix_Save_File;
		// radio value html
		String savePathRadioBase = savePathPost.replace(Suffix_Save_File, "_radio" + Suffix_Save_File);
		
		// 获取提交查询表单之后的response html，记录cookieStore，以数组方式传址
		CookieStore[] cookieStores = new CookieStore[1];
		String html = crawler.getPostResponseHtmlByParams(PostUrl, formParams, cookieStores);
		// 不存在该航班
		if (html == null || html.equals(""))
			return 0;
		
		// 获取提交表单之后的response html中的航班的radio value信息
		ArrayList<String> radioValues = htmlParser.parseRadioValue(html);
		// 为空，该html中没有radio value信息，即该航线没有航班
		if(radioValues == null || radioValues.size() <= 0)
			return 0;
		
		// 否则，存在该航班，保存文件
		crawler.saveHtmlToFile(html, savePathPost);	
		
		// 依次提交每个radio value信息，get方式获取对应的html，包含航班的价格信息
		ArrayList<String> radioValueGeneratedHtmls = crawler.getHtmlByRadio(cookieStores[0], radioValues);
		// 保存文件
		for(int i = 0; i < radioValueGeneratedHtmls.size(); i++) {
			String priceHtml = radioValueGeneratedHtmls.get(i);
			String savePathRadio = savePathRadioBase.replace(Suffix_Save_File, "_" + (i + 1) + Suffix_Save_File);
			crawler.saveHtmlToFile(priceHtml, savePathRadio);	// 保存文件
		}
			
		// 根据html 和radio value html 解析完整的航班信息
		ArrayList<Ticket> tickets = htmlParser.parseTicket(html, radioValueGeneratedHtmls);
		
		// System.out.println(tickets);
		
		// 插入数据库
		return TicketService.insert(tickets);
	}
	
	
	
	
	
	/**
	 * 获取信息同时保存相关html文件
	 * 单程
	 */
	@Test
	public void get_save_TicketOneWay() {
		// 构建表单变量
		TravelOption travelOption = TravelOption.OneWay;
		OrgStation orgStation = OrgStation.PER;
		DestStation destStation = DestStation.SYD;
		String depTime = "2016-06-20";
		int adultNum = 2;
		int childNum = 0;
		
		FormParams formParams = new FormParams();
		formParams.setTravelOption(travelOption)
			.setOrgStation(orgStation)
			.setDestStation(destStation)
			.setDepartureTime(depTime)
			.setAdultNum(adultNum)
			.setChildNum(childNum)
			.build();
		
		System.out.println(formParams);
		
		// 获取和保存信息
		get_save_TicketOneWayByParams(formParams);
	}
	

}
