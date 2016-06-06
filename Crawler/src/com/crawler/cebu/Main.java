package com.crawler.cebu;


public class Main {
	private final static String URL = "https://book.cebupacificair.com/Search.aspx?culture=en-us";
	private final static String PostUrl = "https://book.cebupacificair.com/Search.aspx";
	private final static String SAVE_PATH_Response = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/post_response.html";
	
	private static CrawlerUtil crawlerUtil = CrawlerUtil.getInstance();
	private static ParseUtil parseUtil = ParseUtil.getInstance();
	
	public static void main(String[] args) {
		savePostResponseHtml();
	}

	public static void savePostResponseHtml() {
		parseUtil.savePostResponseHtml(PostUrl, SAVE_PATH_Response);
		System.out.println("done");
	}
}
