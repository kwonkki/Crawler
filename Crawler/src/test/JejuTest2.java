package test;

import static org.junit.Assert.*;

import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.apache.commons.dbcp2.Constants;
import org.apache.http.Consts;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.protocol.HTTP;
import org.junit.Test;

import com.google.gson.Gson;

import cebu.model.FormParams_7C;
import cebu.util.Crawler;
import cebu.util.HtmlParser;
import net.sf.json.JSONObject;

public class JejuTest2 {
	private static final String Host = "https://www.jejuair.net";
	private static final String PostUrl = "https://www.jejuair.net/jejuair/booking/jjimClassInfo.do";
	private final static String Dir = "D:/Documents/Github/Crawler/Data/";
	private String SavePath = Dir + "post_response_7C.html";
	private String ParamsLabel = "hidRequestData=";

	// 爬虫类和html解析类
	private static Crawler crawler = Crawler.getInstance();
	private static HtmlParser htmlParser = HtmlParser.getInstance();

	@Test
	public void test_get() {
		Gson gson = new Gson();
		FormParams_7C pojo = new FormParams_7C();
System.out.println("pojo: " + pojo);
		String jsonStr = gson.toJson(pojo);
System.out.println("jsonStr: " + jsonStr);
		// String getUrl =
		// "https://www.jejuair.net/jejuair/com/jeju/ibe/goAvail.do?hidRequestData=%7B%22mainFlag%22%3A%22main%22%2C%22routeType%22%3A%22I%22%2C%22tripType%22%3A%22O%22%2C%22payType%22%3A%22%22%2C%22depStn1%22%3A%22WEH%22%2C%22arrStn1%22%3A%22%22%2C%22depStn2%22%3A%22%22%2C%22arrStn2%22%3A%22ICN%22%2C%22depStnName1%22%3A%22Weihai%22%2C%22arrStnName1%22%3A%22Destination%22%2C%22depStnName2%22%3A%22Origin%22%2C%22arrStnName2%22%3A%22Seoul%28Incheon%29%22%2C%22depDate1%22%3A%222016-12-20%22%2C%22depDate2%22%3A%222016-12-20%22%2C%22country1%22%3A%22%22%2C%22country2%22%3A%22CN%22%2C%22selectSectionSize%22%3A1%2C%22adtPaxCnt%22%3A2%2C%22chdPaxCnt%22%3A0%2C%22infPaxCnt%22%3A0%2C%22dualFlag%22%3A%22%22%2C%22multiFlag%22%3A%22%22%7D";
		String getUrl = "https://www.jejuair.net/jejuair/com/jeju/ibe/goAvail.do?" + ParamsLabel + jsonStr;
System.out.println("getUrl String: " + getUrl);

		//CloseableHttpClient httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		URI uri = URI.create(getUrl);
System.out.println("URI: " + uri);
		HttpGet httpGet = new HttpGet(uri);
		//HttpGet httpGet = new HttpGet(getUrl);
		
		
		httpGet.setHeader("Host", "www.jejuair.net");
		httpGet.setHeader("User-agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0");

		httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		httpGet.setHeader("Accept-Encoding", "gzip, deflate, br");
		httpGet.setHeader("Connection", "keep-alive");
		// httpPost.setHeader("Content-Type",
		// "application/x-www-form-urlencoded")
System.out.println(httpGet.getRequestLine().toString());

		HttpClientContext context = HttpClientContext.create();
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpGet, context);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		CookieStore[] cookieStores = new CookieStore[1];
		cookieStores[0] = context.getCookieStore();

		System.out.println(cookieStores[0]);
		System.out.println(response.getStatusLine().getStatusCode());

		String html = crawler.getHtmlByResponse(response);
		crawler.saveHtmlToFile(html, SavePath);
	}

	@Test
	public void test_post() throws Exception {
		

		/*
		 * JSONObject json = new JSONObject(); json.put("mainFlag", "main");
		 * json.put("routeType", "I"); json.put("tripType", "O");
		 * json.put("payType", ""); json.put("depStn1", "WEH");
		 * json.put("arrStn1", ""); json.put("depStn2", ""); json.put("arrStn2",
		 * "ICN"); json.put("depStnName1", "Weihai"); json.put("arrStnName1",
		 * "Destination"); json.put("depStnName2", "Origin");
		 * json.put("arrStnName2", "Seoul(Incheon)"); json.put("depDate1",
		 * "2016-12-20"); json.put("depDate2", "2016-12-20");
		 * json.put("country1", ""); json.put("country2", "CN");
		 * json.put("selectSectionSize", 1); json.put("adtPaxCnt", "2");
		 * json.put("chdPaxCnt", "0"); json.put("infPaxCnt", "0");
		 * json.put("dualFlag", ""); json.put("multiFlag", "");
		 */
		
		String postUrl = PostUrl;
		Gson gson = new Gson();
		FormParams_7C pojo = new FormParams_7C();
		String gsonStr = gson.toJson(pojo);
System.out.println(gson.toJson(pojo));
		// 将JSON进行UTF-8编码,以便传输中文
		//String encoderJson = URLEncoder.encode(gsonStr, Consts.UTF_8.name());
		
		//StringEntity postingString = new StringEntity(ParamsLabel + encoderJson);
		StringEntity postingString = new StringEntity("hidRequestData:" + gson.toJson(pojo));
		HttpPost httpPost = new HttpPost(postUrl);

		String paramStr = crawler.getStrByInputStream(postingString.getContent());
System.out.println(paramStr);

		httpPost.setEntity(postingString);
		httpPost.setHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
		httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
		httpPost.setHeader("Host", "www.jejuair.net");
		httpPost.setHeader("User-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0");

		httpPost.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
		httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		httpPost.setHeader("Accept-Encoding", "gzip, deflate, br");
		
		//httpPost.setHeader("Content-type", "application/json");

		// httpPost.setHeader("Accept",
		// "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		
		//httpPost.setHeader("Connection", "keep-alive");
		// httpPost.setHeader("Content-Type",
		// "application/x-www-form-urlencoded")
		//httpPost.setHeader("Content-type", "application/json");
		//httpPost.setHeader("Origin", "https://www.jejuair.net");
		httpPost.setHeader("Referer", "https://www.jejuair.net/jejuair/com/jeju/ibe/goAvail.do");
		//httpPost.setHeader("Upgrade-Insecure-Requests", "1");
		//httpPost.setHeader("DNT", "1");

		try {
			System.out.println(crawler.getStrByInputStream(httpPost.getEntity().getContent()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// 设置cookie

		//CloseableHttpClient httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpClientContext context = HttpClientContext.create();
		CloseableHttpResponse response = httpClient.execute(httpPost, context);

		CookieStore[] cookieStores = new CookieStore[1];
		cookieStores[0] = context.getCookieStore();

		System.out.println(cookieStores[0]);
		System.out.println(response.getStatusLine().getStatusCode());

		String html = crawler.getHtmlByResponse(response);
		crawler.saveHtmlToFile(html, SavePath);
		// System.out.println(html);

		/*
		 * String getStr = PostUrl + "?" + paramStr; System.out.println(getStr);
		 * HttpGet httpGet = new HttpGet(getStr);
		 * 
		 * CloseableHttpResponse responseGet = httpClient.execute(httpGet,
		 * context); System.out.println(cookieStores[0]);
		 * System.out.println(responseGet.getStatusLine().getStatusCode());
		 * 
		 * String htmlGet = crawler.getHtmlByResponse(responseGet);
		 * crawler.saveHtmlToFile(htmlGet, SavePath.replace(".html",
		 * "_get.html"));
		 */

	}

}
