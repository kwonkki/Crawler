package cebu.main;

import java.util.ArrayList;
import org.apache.http.client.CookieStore;

import cebu.dao.service.TicketService;
import cebu.model.FormParams;
import cebu.model.FormParams_5J;
import cebu.model.Ticket;
import cebu.util.crawler.Crawler_5J;
import cebu.util.parser.FormUtil.TravelOption;
import cebu.util.parser.HtmlParser;

public class Main_5J {
	
	// 文件保存目录
	private final static String Dir = "D:/Documents/Github/Crawler/Data/";
	//private final static String Dir = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/";
	
	// 文件路径前缀和后缀
	private final static String Prefix_Save_File = Dir + "post_response_";
	private final static String Suffix_Save_File = ".html";
	
	// 提交表单url
	private final static String PostUrl = "https://book.cebupacificair.com/Search.aspx";
	
	// 爬虫类和html解析类
	private static Crawler_5J crawler = Crawler_5J.getInstance();
	private static HtmlParser htmlParser = HtmlParser.getInstance();
	
	public static void main(String[] args) {
		Main_5J main = new Main_5J();
		
		// 构建表单变量
		FormParams_5J formParams = new FormParams_5J();
		formParams.setTravelOption(TravelOption.OneWay)
			.setDepAirport("HKG")
			.setArrAirport("MNL")
			.setDepTime("2016-06-20")
			.setAdultNum(2)
			.setChildNum(0)
			.build();
		
		// 获取信息，保存html文件，存入数据库
		int airlineCount = main.get_save_TicketOneWayByParams(formParams);
		System.out.println("airline count: " + airlineCount);
	}

	/**
	 * 根据表单参数，获取信息同时保存相关html文件，插入数据库
	 * 单程
	 * @param formParams
	 * @return 航班数目
	 */
	public int get_save_TicketOneWayByParams(FormParams formParams) {
		/** 文件保存路径  **/
		// post response
		String savePathPost = Prefix_Save_File 
				+ formParams.getDepAirport()+ "_"
				+ formParams.getArrAirport()+ "_"
				+ formParams.getDepTime() + Suffix_Save_File;
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
		
		// 否则，存在该航班，保存post response html文件
		crawler.saveHtmlToFile(html, savePathPost);	
		
		// 依次提交每个radio value信息，get方式获取对应的html，包含航班的价格信息
		ArrayList<String> radioValueGeneratedHtmls = crawler.getHtmlByRadio(cookieStores[0], radioValues);
		
		// 保存radio value产生的包含价格的文件
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
	

}
