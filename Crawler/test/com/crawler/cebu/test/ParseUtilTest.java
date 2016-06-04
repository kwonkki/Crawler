package com.crawler.cebu.test;


import org.junit.Test;

import com.crawler.cebu.ParseUtil;

public class ParseUtilTest {

	private final static String URL = "https://book.cebupacificair.com/Search.aspx?culture=en-us";
	private final static String SAVE_PATH = "X:/Codes/JavaCrawler/Data/savedHtmlByUrl.html";
	
	@Test
	public void test_parseByFileWithUrl() {
		ParseUtil.parseByFileWithUrl(SAVE_PATH, URL);
		System.out.println("done");
	}
	
	@Test
	public void test_parseByFile() {
		ParseUtil.parseByFile(SAVE_PATH);
		System.out.println("done");
	}

}
