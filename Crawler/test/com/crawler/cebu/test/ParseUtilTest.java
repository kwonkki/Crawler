package com.crawler.cebu.test;

import org.junit.Test;
import com.crawler.cebu.*;

public class ParseUtilTest {

	/**中文测试**/
	private final static String URL = "https://book.cebupacificair.com/Search.aspx?culture=en-us";
	private final static String PostUrl = "https://book.cebupacificair.com/Search.aspx";
	private final static String SAVE_PATH_Response = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/post_response.html";
	private final static String SAVE_PATH_Response_Params = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/post_response_params.html";
	private final static String SAVE_PATH = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/test_savedHtmlByUrl.html";
	private final static String SAVE_PATH_IniFile = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/Book a Trip.html";
	
	private static ParseUtil parseUtil = ParseUtil.getInstance();
	
	
	@Test
	public void test_parseByFileWithUrl() {
		parseUtil.parseTimeByFileWithUrl(SAVE_PATH_Response, URL);
		System.out.println("done");
	}
	
	@Test
	public void test_parseByFile() {
		parseUtil.parseTimeByFile(SAVE_PATH);
		System.out.println("done");
	}


	
	@Test
	public void test_parseFlightInfoByFile() {
		parseUtil.parseFlightByFile(SAVE_PATH_Response);
		System.out.println("done");
	}
	
	@Test 
	public void test_parseOriginStationsByFile() {
		parseUtil.printMap(parseUtil.parseStationsByFile(SAVE_PATH_IniFile));
		System.out.println("done");
	}
	

	
}
