package com.crawler.cebu.test;


import org.junit.Test;

import com.crawler.cebu.CrawlerUtil;

public class CrawlerUtilTest {
	private final static String URL = "https://book.cebupacificair.com/Search.aspx?culture=en-us";
	private final static String SAVE_PATH = "X:/Codes/JavaCrawler/Data/savedHtmlByUrl.html";

	@Test
	public void test_saveHtmlByUrl() {
		CrawlerUtil.saveHtmlByUrl(URL, SAVE_PATH);
		System.out.println("done");
	}

	
	@Test
	public void test_getHtmlByUrl() {
		String str = CrawlerUtil.getHtmlByUrl(URL);
		System.out.println(str);
	}
}
