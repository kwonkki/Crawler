package com.crawler.cebu.test;


import org.junit.Test;

import com.crawler.cebu.CrawlerUtil;
import com.crawler.cebu.ParseUtil;

public class ParseUtilTest {

	/**中文测试**/
	private final static String URL = "https://book.cebupacificair.com/Search.aspx?culture=en-us";
	private final static String PostUrl = "https://book.cebupacificair.com/Search.aspx";
	private final static String SAVE_PATH_Response = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/post_response.html";
	private static ParseUtil parseUtil = ParseUtil.getInstance();
	private static CrawlerUtil crawlerUtil = CrawlerUtil.getInstance();
	
	
	@Test
	public void test_parseByFileWithUrl() {
		parseUtil.parseByFileWithUrl(SAVE_PATH_Response, URL);
		System.out.println("done");
	}
	
	@Test
	public void test_parseByFile() {
		parseUtil.parseByFile(SAVE_PATH_Response);
		System.out.println("done");
	}

	@Test
	public void test_savePostResponseHtml() {
		parseUtil.savePostResponseHtml(PostUrl, SAVE_PATH_Response);
		System.out.println("done");
	}
	
	@Test
	public void test_parseFlightInfoByFile() {
		parseUtil.parseFlightInfoByFile(SAVE_PATH_Response);
		System.out.println("done");
	}
	
	
}
