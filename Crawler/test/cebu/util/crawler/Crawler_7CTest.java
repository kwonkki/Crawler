package cebu.util.crawler;

import org.junit.Test;

import cebu.model.FormParams_7C;

public class Crawler_7CTest {
	
	private final static String Dir = "D:/Documents/Github/Crawler/Data/";
	private String SavePath = Dir + "post_response_7C.html";
	// 爬虫类和html解析类
	private static Crawler_7C crawler = Crawler_7C.getInstance();

	
	@Test
	public void test_() {
		FormParams_7C formParams = new FormParams_7C();
		formParams.setDepAirport("WEH")
			.setArrAirport("ICN")
			.setDepTime("2016-06-30")
			.setAdultNum(2)
			.setChildNum(0)
			.build();
		
		String postUrl = "https://www.jejuair.net/jejuair/com/jeju/ibe/searchAvail.do";
		String html = crawler.getPostResponseHtmlByParams(postUrl, formParams);
		System.out.println(html);
	}
	
	
}
