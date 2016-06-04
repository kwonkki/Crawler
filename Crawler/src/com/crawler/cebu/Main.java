package com.crawler.cebu;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class Main {
	private final static String URL = "https://book.cebupacificair.com/Search.aspx?culture=en-us";
	private final static String PostUrl = "https://book.cebupacificair.com/Search.aspx";
	private final static String SAVE_PATH_Response = "X:/Codes/JavaCrawler/Data/savedHtmlByUrl_Response.html";
	
	public static void main(String[] args) {
		getResponseHtml();
	}

	public static void getResponseHtml() {
		/**
		NameValuePair[] data = { new BasicNameValuePair(FormUtil.Name_TravelOptions, FormUtil.Val_TravelOptions[1]),
				new BasicNameValuePair(FormUtil.Name_Origin, "HKG"),
				new BasicNameValuePair(FormUtil.Name_Destination, "MNL"),
				new BasicNameValuePair(FormUtil.Name_CalendarDayDeparture, "01"),
				new BasicNameValuePair(FormUtil.Name_CalendarMonthDeparture, "2016-07"),
				new BasicNameValuePair(FormUtil.Name_Adults, "1"),
				new BasicNameValuePair(FormUtil.Name_Children, "0") };
		**/
		
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		formParams.add(new BasicNameValuePair(FormUtil.Name_TravelOptions, FormUtil.Val_TravelOptions[1]));
		formParams.add(new BasicNameValuePair(FormUtil.Name_Origin, "HKG"));
		formParams.add(new BasicNameValuePair(FormUtil.Name_Destination, "MNL"));
		formParams.add(new BasicNameValuePair(FormUtil.Name_CalendarDayDeparture, "01"));
		formParams.add(new BasicNameValuePair(FormUtil.Name_CalendarMonthDeparture, "2016-07"));
		formParams.add(new BasicNameValuePair(FormUtil.Name_Adults, "1"));
		formParams.add(new BasicNameValuePair(FormUtil.Name_Children, "0"));
		formParams.add(new BasicNameValuePair(FormUtil.Name_Submit, FormUtil.Val_Submit));

		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
		HttpPost httpPost = new HttpPost(PostUrl);
		httpPost.setEntity(formEntity);
		
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		
		try {
			httpClient =  HttpClients.createDefault();
			response = httpClient.execute(httpPost);
System.out.println("response code : " + response.getStatusLine().getStatusCode());
			CrawlerUtil.saveHtmlByHttpResponse(response, SAVE_PATH_Response);
		} catch (Exception e) {
			System.out.println("访问[ " + URL + " ]出现异常!");
			e.printStackTrace();
		} finally {
			CrawlerUtil.free(response, httpClient);
		}
	}
}
