package com.crawler.cebu.test;


import org.junit.Before;
import org.junit.Test;

import com.crawler.cebu.CrawlerUtil;

public class CrawlerUtilTest {
	private final static String URL = "https://book.cebupacificair.com/Search.aspx?culture=en-us";
	private final static String SAVE_PATH = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/test_savedHtmlByUrl.html";

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
}
