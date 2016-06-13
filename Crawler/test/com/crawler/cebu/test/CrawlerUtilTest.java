package com.crawler.cebu.test;


import java.util.List;

import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.junit.Before;
import org.junit.Test;

import com.crawler.cebu.CrawlerUtil;
import com.crawler.cebu.FormParams;
import com.crawler.cebu.FormUtil.DestStation;
import com.crawler.cebu.FormUtil.OrgStation;
import com.crawler.cebu.FormUtil.TravelOption;

public class CrawlerUtilTest {
	private final static String URL = "https://book.cebupacificair.com/Search.aspx?culture=en-us";
	private final static String SAVE_PATH = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/test_savedHtmlByUrl.html";
	private final static String PostUrl = "https://book.cebupacificair.com/Search.aspx";
	private final static String SAVE_PATH_Response = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/post_response.html";
	private final static String SAVE_PATH_Response_Params = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/post_response_params.html";
	
	private CrawlerUtil crawlerUtil = null;
	
	@Before
	public void before() {
		crawlerUtil = CrawlerUtil.getInstance();
	}
	
	@Test
	public void test_saveHtmlByUrl() {
		crawlerUtil.saveHtmlByUrl(URL, SAVE_PATH);
		System.out.println("done");
	}
	
	@Test
	public void test_getHtmlByUrl() {
		String str = crawlerUtil.getHtmlByUrl(URL);
		System.out.println(str);
	}
	
	@Test
	public void test_printFile() {
		 crawlerUtil.printFile(SAVE_PATH);
	}
	
	@Test
	public void test_savePostResponseHtml() {
		crawlerUtil.savePostResponseHtmlConst(PostUrl, SAVE_PATH_Response);
		System.out.println("done");
	}
	
	
	
	@Test
	public void test_savePostResponseHtmlByParams() {
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
		List<Cookie> cookies = cookieStores[0].getCookies();
		for(Cookie cookie : cookies) {
			System.out.println(cookie.getName() + "=" + cookie.getValue());
		}
		System.out.println(html);
		System.out.println("done");
	}
	
	@Test
	public void test_getPostResponseHtmlByParams() {
		// 构建表单变量
		FormParams formParams = new FormParams();
		formParams.setTravelOption(TravelOption.OneWay)
			.setOrgStation(OrgStation.HKG)
			.setDestStation(DestStation.MNL)
			.setDepartureTime("2016-06-25")
			.setAdultNum(2)
			.setChildNum(0)
			.build();
		String html = crawlerUtil.getPostResponseHtmlByParams(PostUrl, formParams);
		System.out.println(html);
		System.out.println("done");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
