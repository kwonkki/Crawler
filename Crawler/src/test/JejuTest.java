package test;

import static org.junit.Assert.*;

import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp2.Constants;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.simple.parser.JSONParser;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import cebu.model.FormParams_7C;
import cebu.util.Crawler;
import cebu.util.HtmlParser;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JejuTest {
	private static final String Host = "https://www.jejuair.net";
	private static final String PostUrl = "https://www.jejuair.net/jejuair/com/jeju/ibe/goAvail.do";
	private final static String Dir = "D:/Documents/Github/Crawler/Data/";
	private String SavePath = Dir + "post_response_7C.html";
	private String ParamsLabel = "hidRequestData=";

	// 爬虫类和html解析类
	private static Crawler crawler = Crawler.getInstance();
	private static HtmlParser htmlParser = HtmlParser.getInstance();

	public static void main(String[] args) {
		JejuTest jejuTest = new JejuTest();
		jejuTest.test_post_form();
	}

	@Test
	public void test_get() throws Exception {
		String gsonStr = "{\"mainFlag\":\"main\",\"routeType\":\"I\",\"tripType\":\"O\",\"payType\":\"\",\"depStn1\":\"WEH\",\"arrStn1\":\"\",\"depStn2\":\"\",\"arrStn2\":\"ICN\",\"depStnName1\":\"Weihai\",\"arrStnName1\":\"Destination\",\"depStnName2\":\"Origin\",\"arrStnName2\":\"Seoul(Incheon)\",\"depDate1\":\"2016-12-20\",\"depDate2\":\"2016-12-20\",\"country1\":\"\",\"country2\":\"CN\",\"selectSectionSize\":1,\"adtPaxCnt\":2,\"chdPaxCnt\":0,\"infPaxCnt\":0,\"dualFlag\":\"\",\"multiFlag\":\"\"}";
		String safeUrl = URLEncoder.encode(gsonStr, "UTF-8");
		String getUrl = "https://www.jejuair.net/jejuair/com/jeju/ibe/goAvail.do?" + ParamsLabel + safeUrl;
		System.out.println("getUrl String: " + getUrl);

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(getUrl);

		httpGet.setHeader("Host", "www.jejuair.net");
		httpGet.setHeader("User-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0");
		httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		httpGet.setHeader("Accept-Encoding", "gzip, deflate, br");
		httpGet.setHeader("Connection", "keep-alive");
		httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
		httpGet.setHeader("Referer", "https://www.jejuair.net//jejuair/com/jeju/ibe/goAvail.do");
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
		// System.out.println(html);
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
		// String gsonStr = gson.toJson(pojo);

		String gsonStr = "hidRequestData={\"mainFlag\":\"main\",\"routeType\":\"I\",\"tripType\":\"O\",\"payType\":\"\",\"depStn1\":\"WEH\",\"arrStn1\":\"\",\"depStn2\":\"\",\"arrStn2\":\"ICN\",\"depStnName1\":\"Weihai\",\"arrStnName1\":\"Destination\",\"depStnName2\":\"Origin\",\"arrStnName2\":\"Seoul(Incheon)\",\"depDate1\":\"2016-12-20\",\"depDate2\":\"2016-12-20\",\"country1\":\"\",\"country2\":\"CN\",\"selectSectionSize\":1,\"adtPaxCnt\":2,\"chdPaxCnt\":0,\"infPaxCnt\":0,\"dualFlag\":\"\",\"multiFlag\":\"\"}";

		System.out.println(gson.toJson(pojo));
		// 将JSON进行UTF-8编码,以便传输中文
		// String encoderJson = URLEncoder.encode(gsonStr, Consts.UTF_8.name());

		StringEntity postingString = new StringEntity(gsonStr);
		// StringEntity postingString = new StringEntity("hidRequestData:" +
		// gson.toJson(pojo));
		HttpPost httpPost = new HttpPost(postUrl);

		String paramStr = crawler.getStrByInputStream(postingString.getContent());
		System.out.println(paramStr);

		httpPost.setEntity(postingString);
		httpPost.setHeader("Content-type", "application/json");

		// httpPost.setHeader("Host", "www.jejuair.net");
		// httpPost.setHeader("User-agent", "Mozilla/5.0 (Windows NT 10.0;
		// WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112
		// Safari/537.36");

		// httpPost.setHeader("Accept", "application/json");
		// httpPost.setHeader("Accept-Language",
		// "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		// httpPost.setHeader("Accept-Encoding", "gzip, deflate, br");

		// httpPost.setHeader("Content-type", "application/json");

		// httpPost.setHeader("Accept",
		// "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");

		// httpPost.setHeader("Connection", "keep-alive");
		// httpPost.setHeader("Content-Type",
		// "application/x-www-form-urlencoded")
		// httpPost.setHeader("Content-type", "application/json");
		// httpPost.setHeader("Origin", "https://www.jejuair.net");
		// httpPost.setHeader("Referer",
		// "https://www.jejuair.net/jejuair/main.jsp");
		// httpPost.setHeader("Upgrade-Insecure-Requests", "1");
		// httpPost.setHeader("DNT", "1");

		try {
			System.out.println(crawler.getStrByInputStream(httpPost.getEntity().getContent()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// 设置cookie

		// CloseableHttpClient httpClient =
		// HttpClientBuilder.create().setRedirectStrategy(new
		// LaxRedirectStrategy()).build();
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

	@Test
	public void test_post_json() throws Exception {
		Gson gson = new Gson();
		FormParams_7C pojo = new FormParams_7C();
		String gsonStr = gson.toJson(pojo);
		StringEntity postingString = new StringEntity(gsonStr);
		String postUrl = PostUrl;
		HttpPost httpPost = new HttpPost(postUrl);
		httpPost.setEntity(postingString);
		httpPost.setHeader("Host", "www.jejuair.net");
		httpPost.setHeader("User-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0");
		httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		httpPost.setHeader("Accept-Language", "en-US,zh;q=0.8,zh-CN;q=0.5,en;q=0.3");
		httpPost.setHeader("Accept-Encoding", "gzip, deflate");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		httpPost.setHeader("Origin", "https://www.jejuair.net");
		httpPost.setHeader("Referer", "https://www.jejuair.net/jejuair/main.jsp");


		try {
			System.out.println(crawler.getStrByInputStream(httpPost.getEntity().getContent()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpClientContext context = HttpClientContext.create();
		CloseableHttpResponse response = httpClient.execute(httpPost, context);

		CookieStore cookieStore = new BasicCookieStore();
		cookieStore = context.getCookieStore();
		
		System.out.println(response.getStatusLine().getStatusCode());

		String html = crawler.getHtmlByResponse(response);
		crawler.saveHtmlToFile(html, SavePath);
		
		JSONObject jsonSecond = new JSONObject();
		jsonSecond.put("AdultPaxCnt", "2");
		jsonSecond.put("ChildPaxCnt", "0");
		jsonSecond.put("InfantPaxCnt", "0");
		jsonSecond.put("RouteType", "I");
		jsonSecond.put("SystemType", "IBE");
		jsonSecond.put("Language", "EN");
		jsonSecond.put("DepStn", "WEH");
		jsonSecond.put("ArrStn", "ICN");
		jsonSecond.put("SegType", "DEP");
		jsonSecond.put("TripType", "OW");
		jsonSecond.put("DepDate", "2016-06-30");
		jsonSecond.put("Index", "3");

		String postUrlSecond = "https://www.jejuair.net/jejuair/com/jeju/ibe/searchAvail.do";
		StringEntity entitySecond = null;
		try {
			entitySecond = new StringEntity(jsonSecond.toString());
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		HttpPost httpPostSecond = new HttpPost(postUrlSecond);
		httpPostSecond.setEntity(entitySecond);

		httpPostSecond.setHeader("Host", "www.jejuair.net");
		httpPostSecond.setHeader("User-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0");
		httpPostSecond.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
		httpPostSecond.setHeader("Accept-Language", "en-US,zh;q=0.8,zh-CN;q=0.5,en;q=0.3");
		httpPostSecond.setHeader("Accept-Encoding", "gzip, deflate, br");
		httpPostSecond.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		httpPostSecond.setHeader("X-Requested-With", "XMLHttpRequest");
		httpPostSecond.setHeader("Referer", "https://www.jejuair.net/jejuair/com/jeju/ibe/goAvail.do");

		String paramStrSecond = crawler.getStrByInputStream(entitySecond.getContent());
		System.out.println("entity str: " + paramStrSecond);
		System.out.println(httpPostSecond.getRequestLine());

		HttpClientContext contextSecond = HttpClientContext.create();
		CloseableHttpClient httpClientSecond = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		CloseableHttpResponse responseSecond = httpClientSecond.execute(httpPostSecond, contextSecond);

		String reStrSecond = crawler.getStrByInputStream(responseSecond.getEntity().getContent());

System.out.println(reStrSecond);
		
		try {

			JSONParser parser = new JSONParser();
			Object resultObject = parser.parse(reStrSecond);
System.out.println(resultObject);
			if (resultObject instanceof JSONArray) {
				JSONArray array = (JSONArray) resultObject;
				for (Object object : array) {
					JSONObject obj = (JSONObject) object;
					System.out.println(obj.toString());
					System.out.println(obj.get("example"));
					System.out.println(obj.get("fr"));
				}

			} else if (resultObject instanceof JSONObject) {
				JSONObject obj = (JSONObject) resultObject;
				System.out.println(obj.toString());
				System.out.println(obj.get("example"));
				System.out.println(obj.get("fr"));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		System.out.println(reStrSecond);
	}

	@Test
	public void test_post_form() {
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		String gsonStr = "{\"mainFlag\":\"main\",\"routeType\":\"I\",\"tripType\":\"O\",\"payType\":\"\",\"depStn1\":\"WEH\",\"arrStn1\":\"\",\"depStn2\":\"\",\"arrStn2\":\"ICN\",\"depStnName1\":\"Weihai\",\"arrStnName1\":\"Destination\",\"depStnName2\":\"Origin\",\"arrStnName2\":\"Seoul(Incheon)\",\"depDate1\":\"2016-12-20\",\"depDate2\":\"2016-12-20\",\"country1\":\"\",\"country2\":\"CN\",\"selectSectionSize\":1,\"adtPaxCnt\":2,\"chdPaxCnt\":0,\"infPaxCnt\":0,\"dualFlag\":\"\",\"multiFlag\":\"\"}";
		formParams.add(new BasicNameValuePair("hidRequestData", gsonStr));

		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
		String postUrl = PostUrl;
		HttpPost httpPost = new HttpPost(postUrl);
		httpPost.setEntity(formEntity);

		httpPost.setHeader("Host", "www.jejuair.net");
		httpPost.setHeader("User-agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36");

		// httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		httpPost.setHeader("Accept-Encoding", "gzip, deflate, br");

		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		httpPost.setHeader("Origin", "https://www.jejuair.net");
		httpPost.setHeader("Referer", "https://www.jejuair.net/jejuair/main.jsp");
		httpPost.setHeader("Upgrade-Insecure-Requests", "1");

		try {
			System.out.println(crawler.getStrByInputStream(formEntity.getContent()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;

		try {
			httpClient = HttpClients.createDefault();
			HttpClientContext context = HttpClientContext.create();
			response = httpClient.execute(httpPost, context);
			String html = crawler.getHtmlByResponse(response);
			crawler.saveHtmlToFile(html, SavePath);
		} catch (Exception e) {
			System.out.println("访问[ " + postUrl + " ]出现异常!");
			e.printStackTrace();
		} finally {

		}

	}

	@Test
	public void test_post_form_twice() throws Exception {
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		formParams.add(new BasicNameValuePair("AdultPaxCnt", "2"));
		formParams.add(new BasicNameValuePair("ChildPaxCnt", "0"));
		formParams.add(new BasicNameValuePair("InfantPaxCnt", "0"));
		formParams.add(new BasicNameValuePair("RouteType", "I"));
		formParams.add(new BasicNameValuePair("SystemType", "IBE"));
		formParams.add(new BasicNameValuePair("Language", "EN"));
		formParams.add(new BasicNameValuePair("DepStn", "WEH"));
		formParams.add(new BasicNameValuePair("ArrStn", "ICN"));
		formParams.add(new BasicNameValuePair("SegType", "DEP"));
		formParams.add(new BasicNameValuePair("TripType", "OW"));
		formParams.add(new BasicNameValuePair("DepDate", "2016-06-30"));
		formParams.add(new BasicNameValuePair("Index", "3"));

		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);

		String postUrl = PostUrl;
		HttpPost httpPost = new HttpPost(postUrl);
		httpPost.setEntity(formEntity);

		httpPost.setHeader("Host", "www.jejuair.net");
		httpPost.setHeader("User-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0");
		httpPost.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
		httpPost.setHeader("Accept-Language", "en-US,zh;q=0.8,zh-CN;q=0.5,en;q=0.3");
		httpPost.setHeader("Accept-Encoding", "gzip, deflate, br");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
		httpPost.setHeader("Origin", "https://www.jejuair.net");
		httpPost.setHeader("Referer", "https://www.jejuair.net/jejuair/com/jeju/ibe/goAvail.do");

		String paramStr = crawler.getStrByInputStream(formEntity.getContent());
		System.out.println("entity str: " + paramStr);
		System.out.println(httpPost.getRequestLine());

		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;

		httpClient = HttpClients.createDefault();
		HttpClientContext context = HttpClientContext.create();
		response = httpClient.execute(httpPost, context);

		String reStr = crawler.getStrByInputStream(response.getEntity().getContent());

		try {

			JSONParser parser = new JSONParser();
			Object resultObject = parser.parse(reStr);
			System.out.println(resultObject);
			if (resultObject instanceof JSONArray) {
				JSONArray array = (JSONArray) resultObject;
				for (Object object : array) {
					JSONObject obj = (JSONObject) object;
					System.out.println(obj.toString());
					System.out.println(obj.get("example"));
					System.out.println(obj.get("fr"));
				}

			} else if (resultObject instanceof JSONObject) {
				JSONObject obj = (JSONObject) resultObject;
				System.out.println(obj.toString());
				System.out.println(obj.get("example"));
				System.out.println(obj.get("fr"));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		System.out.println(reStr);

	}

	@Test
	public void test_post_merge() throws Exception {
		JSONObject jsonFirst = new JSONObject();
		jsonFirst.put("RouteType", "I");

		String postUrlFirst = "https://www.jejuair.net/jejuair/booking/jjimClassInfo.do";
		StringEntity entityFirst = null;
		try {
			entityFirst = new StringEntity(jsonFirst.toString());
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		HttpPost httpPostFirst = new HttpPost(postUrlFirst);
		httpPostFirst.setEntity(entityFirst);

		httpPostFirst.setHeader("Host", "www.jejuair.net");
		httpPostFirst.setHeader("User-agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0");
		httpPostFirst.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
		httpPostFirst.setHeader("Accept-Language", "en-US,zh;q=0.8,zh-CN;q=0.5,en;q=0.3");
		httpPostFirst.setHeader("Accept-Encoding", "gzip, deflate, br");
		httpPostFirst.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		httpPostFirst.setHeader("X-Requested-With", "XMLHttpRequest");
		httpPostFirst.setHeader("Origin", "https://www.jejuair.net");
		httpPostFirst.setHeader("Referer", "https://www.jejuair.net/jejuair/com/jeju/ibe/goAvail.do");

		HttpClientContext contextFirst = HttpClientContext.create();
		CloseableHttpClient httpClientFirst = HttpClients.createDefault();
		CloseableHttpResponse responseFirst = httpClientFirst.execute(httpPostFirst, contextFirst);

		CookieStore cookieStore = new BasicCookieStore();
		cookieStore = contextFirst.getCookieStore();
System.out.println("cookieStore: " + cookieStore);
		String reStrFirst = crawler.getStrByInputStream(responseFirst.getEntity().getContent());
		System.out.println(reStrFirst);

		
		JSONObject json = new JSONObject();
		json.put("AdultPaxCnt", "2");
		json.put("ChildPaxCnt", "0");
		json.put("InfantPaxCnt", "0");
		json.put("RouteType", "I");
		json.put("SystemType", "IBE");
		json.put("Language", "EN");
		json.put("DepStn", "WEH");
		json.put("ArrStn", "ICN");
		json.put("SegType", "DEP");
		json.put("TripType", "OW");
		json.put("DepDate", "2016-06-30");
		json.put("Index", "3");

		String postUrl = "https://www.jejuair.net/jejuair/com/jeju/ibe/searchAvail.do";
		StringEntity entity = new StringEntity(json.toString());
		HttpPost httpPost = new HttpPost(postUrl);
		httpPost.setEntity(entity);

		List<Cookie> cookies = cookieStore.getCookies();
		StringBuffer sb = new StringBuffer();
		for(Cookie cookie : cookies) {
			sb.append(cookie.getName() + "=" + cookie.getValue() + "; ");
		}
		String cookieStr = sb.toString().substring(0, sb.toString().length() - 3);
System.out.println("cookieStr: " + cookieStr);
		
		
		httpPost.setHeader("User-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36");
		httpPost.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
		httpPost.setHeader("Accept-Language", "en-US,zh;q=0.8,zh-CN;q=0.5,en;q=0.3");
		httpPost.setHeader("Accept-Encoding", "gzip, deflate");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		httpPost.setHeader("Host", "www.jejuair.net");
		httpPost.setHeader("DNT", "1");
		httpPost.setHeader("Origin", "https://www.jejuair.net");
		httpPost.setHeader("Referer", "https://www.jejuair.net/jejuair/com/jeju/ibe/goAvail.do");
		httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
		httpPost.setHeader("Cookie", cookieStr);
		
		HttpClientContext context = HttpClientContext.create();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = httpClient.execute(httpPost, context);

		String reStr = crawler.getStrByInputStream(response.getEntity().getContent());
		System.out.println(reStr);
		
		try {

			JSONParser parser = new JSONParser();
			Object resultObject = parser.parse(reStr);
			System.out.println(resultObject);
			if (resultObject instanceof JSONArray) {
				JSONArray array = (JSONArray) resultObject;
				for (Object object : array) {
					JSONObject obj = (JSONObject) object;
					System.out.println(obj.toString());
					System.out.println(obj.get("example"));
					System.out.println(obj.get("fr"));
				}

			} else if (resultObject instanceof JSONObject) {
				JSONObject obj = (JSONObject) resultObject;
				System.out.println(obj.toString());
				System.out.println(obj.get("example"));
				System.out.println(obj.get("fr"));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		

	}

	@Test
	public void test_post_merge2() throws Exception {
		JSONObject jsonFirst = new JSONObject();
		jsonFirst.put("RouteType", "I");

		String postUrlFirst = "https://www.jejuair.net/jejuair/booking/jjimClassInfo.do";
		StringEntity entityFirst = null;
		try {
			entityFirst = new StringEntity(jsonFirst.toString());
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		HttpPost httpPostFirst = new HttpPost(postUrlFirst);
		httpPostFirst.setEntity(entityFirst);
		
		httpPostFirst.setHeader("Host", "www.jejuair.net");
		httpPostFirst.setHeader("User-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0");
		httpPostFirst.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
		httpPostFirst.setHeader("Accept-Language", "en-US,zh;q=0.8,zh-CN;q=0.5,en;q=0.3");
		httpPostFirst.setHeader("Accept-Encoding", "gzip, deflate");
		httpPostFirst.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		httpPostFirst.setHeader("X-Requested-With", "XMLHttpRequest");
		httpPostFirst.setHeader("Origin", "https://www.jejuair.net");
		httpPostFirst.setHeader("Referer", "https://www.jejuair.net/jejuair/com/jeju/ibe/goAvail.do");

		CookieStore cookieStore = new BasicCookieStore();
		HttpClientContext httpClientContext = HttpClientContext.create();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse responseFirst = httpClient.execute(httpPostFirst, httpClientContext);
		
		cookieStore = httpClientContext.getCookieStore();
System.out.println("------------cookieStores: " + cookieStore);
System.out.println("------------httpContext: " + httpClientContext.toString());
		List<Cookie> cookies = cookieStore.getCookies();
System.out.println("---------------- cookie ------------------");
		for(Cookie cookie : cookies) {
			System.out.println(cookie.getName() + " = " + cookie.getValue());
		}

		String reStrFirst = crawler.getStrByInputStream(responseFirst.getEntity().getContent());
		System.out.println(reStrFirst);

		try {
			JSONParser parser = new JSONParser();
			Object resultObject = parser.parse(reStrFirst);
			System.out.println(resultObject);
			if (resultObject instanceof JSONArray) {
				JSONArray array = (JSONArray) resultObject;
				for (Object object : array) {
					JSONObject obj = (JSONObject) object;
					System.out.println(obj.toString());
					System.out.println(obj.get("example"));
					System.out.println(obj.get("fr"));
				}
			} else if (resultObject instanceof JSONObject) {
				JSONObject obj = (JSONObject) resultObject;
				System.out.println(obj.toString());
				System.out.println(obj.get("example"));
				System.out.println(obj.get("fr"));
			}
		} catch (Exception e) {
		}

		
		JSONObject json = new JSONObject();
		json.put("AdultPaxCnt", "2");
		json.put("ChildPaxCnt", "0");
		json.put("InfantPaxCnt", "0");
		json.put("RouteType", "I");
		json.put("SystemType", "IBE");
		json.put("Language", "EN");
		json.put("DepStn", "WEH");
		json.put("ArrStn", "ICN");
		json.put("SegType", "DEP");
		json.put("TripType", "OW");
		json.put("DepDate", "2016-06-27");
		json.put("Index", "0");

		String postUrl = "https://www.jejuair.net/jejuair/com/jeju/ibe/searchAvail.do";
		StringEntity entity = null;
		try {
			entity = new StringEntity(json.toString());
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		HttpPost httpPost = new HttpPost(postUrl);
		httpPost.setEntity(entity);

		httpPost.setHeader("Host", "www.jejuair.net");
		httpPost.setHeader("User-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36");
		httpPost.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
		httpPost.setHeader("Accept-Language", "en-US,zh;q=0.8,zh-CN;q=0.5,en;q=0.3");
		httpPost.setHeader("Accept-Encoding", "gzip, deflate, br");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
		httpPost.setHeader("Origin", "https://www.jejuair.net");
		httpPost.setHeader("Referer", "https://www.jejuair.net/jejuair/com/jeju/ibe/goAvail.do");

		
		List<Cookie> cookies2 = cookieStore.getCookies();
		System.out.println("---------------- cookie2 ------------------");
		for(Cookie cookie : cookies2) {
			System.out.println(cookie.getName() + " = " + cookie.getValue());
		}
		
		
		CloseableHttpClient httpClientWithCookie = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		CloseableHttpResponse response = httpClientWithCookie.execute(httpPost, httpClientContext);

		String reStr = crawler.getStrByInputStream(response.getEntity().getContent());

		try {

System.out.println("reStr: " + reStr);
			JsonElement jelement = new JsonParser().parse(reStr);

		    JsonObject  jobject = jelement.getAsJsonObject();
		    jobject = jobject.getAsJsonObject("data");
		    JsonArray jarray = jobject.getAsJsonArray("availData");
		    jobject = jarray.get(0).getAsJsonObject();
		    String result = jobject.get("seatCount").toString();
System.out.println("result: " + result);
		    
			JSONParser parser = new JSONParser();
			Object resultObject = parser.parse(reStr);
			System.out.println(resultObject);
			if (resultObject instanceof JSONArray) {
				JSONArray array = (JSONArray) resultObject;
				for (Object object : array) {
					JSONObject obj = (JSONObject) object;
					System.out.println(obj.toString());
					System.out.println(obj.get("example"));
					System.out.println(obj.get("fr"));
				}

			} else if (resultObject instanceof JSONObject) {
				JSONObject obj = (JSONObject) resultObject;
				System.out.println(obj.toString());
				System.out.println(obj.get("example"));
				System.out.println(obj.get("fr"));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		System.out.println(reStr);

	}

	
	@Test
	public void test_post_form_First() throws Exception {
		JSONObject jsonFirst = new JSONObject();
		jsonFirst.put("RouteType", "I");

		String postUrlFirst = "https://www.jejuair.net/jejuair/booking/jjimClassInfo.do";
		StringEntity entityFirst = null;
		try {
			entityFirst = new StringEntity(jsonFirst.toString());
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		HttpPost httpPostFirst = new HttpPost(postUrlFirst);
		httpPostFirst.setEntity(entityFirst);

		httpPostFirst.setHeader("Host", "www.jejuair.net");
		httpPostFirst.setHeader("User-agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0");
		httpPostFirst.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
		httpPostFirst.setHeader("Accept-Language", "en-US,zh;q=0.8,zh-CN;q=0.5,en;q=0.3");
		httpPostFirst.setHeader("Accept-Encoding", "gzip, deflate, br");
		httpPostFirst.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		httpPostFirst.setHeader("X-Requested-With", "XMLHttpRequest");
		httpPostFirst.setHeader("Origin", "https://www.jejuair.net");
		httpPostFirst.setHeader("Referer", "https://www.jejuair.net/jejuair/com/jeju/ibe/goAvail.do");

		CloseableHttpClient httpClientFirst = null;
		CloseableHttpResponse responseFirst = null;

		httpClientFirst = HttpClients.createDefault();
		HttpClientContext contextFirst = HttpClientContext.create();
		responseFirst = httpClientFirst.execute(httpPostFirst, contextFirst);

		String reStrFirst = crawler.getStrByInputStream(responseFirst.getEntity().getContent());
		// System.out.println(reStrFirst);

		try {

			JSONParser parser = new JSONParser();
			Object resultObject = parser.parse(reStrFirst);
			System.out.println(resultObject);
			if (resultObject instanceof JSONArray) {
				JSONArray array = (JSONArray) resultObject;
				System.out.println("JSONArray size: " + array.size());
				for (Object object : array) {
					JSONObject obj = (JSONObject) object;
					System.out.println(obj.toString());
					System.out.println(obj.get("example"));
					System.out.println(obj.get("fr"));
				}

			} else if (resultObject instanceof JSONObject) {
				JSONObject obj = (JSONObject) resultObject;
				System.out.println(obj.toString());
				System.out.println(obj.get("example"));
				System.out.println(obj.get("fr"));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	
	@Test
	public void test_post_form_Second() throws Exception {
		JSONObject jsonSecond = new JSONObject();
		jsonSecond.put("AdultPaxCnt", "2");
		jsonSecond.put("ChildPaxCnt", "0");
		jsonSecond.put("InfantPaxCnt", "0");
		jsonSecond.put("RouteType", "I");
		jsonSecond.put("SystemType", "IBE");
		jsonSecond.put("Language", "EN");
		jsonSecond.put("DepStn", "WEH");
		jsonSecond.put("ArrStn", "ICN");
		jsonSecond.put("SegType", "DEP");
		jsonSecond.put("TripType", "OW");
		jsonSecond.put("DepDate", "2016-06-27");
		jsonSecond.put("Index", "0");

		String postUrlSecond = "https://www.jejuair.net/jejuair/com/jeju/ibe/searchAvail.do";
		StringEntity entitySecond = new StringEntity(jsonSecond.toString(), Consts.UTF_8);
		HttpPost httpPostSecond = new HttpPost(postUrlSecond);
		
		httpPostSecond.setEntity(entitySecond);

		
		httpPostSecond.setHeader("User-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36");
		httpPostSecond.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
		httpPostSecond.setHeader("Accept-Language", "en-US,zh;q=0.8,zh-CN;q=0.5,en;q=0.3");
		httpPostSecond.setHeader("Accept-Encoding", "gzip, deflate");
		httpPostSecond.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		httpPostSecond.setHeader("Host", "www.jejuair.net");
		httpPostSecond.setHeader("DNT", "1");
		httpPostSecond.setHeader("Origin", "https://www.jejuair.net");
		httpPostSecond.setHeader("Referer", "https://www.jejuair.net/jejuair/com/jeju/ibe/goAvail.do");
		httpPostSecond.setHeader("X-Requested-With", "XMLHttpRequest");
		
		String paramStrSecond = crawler.getStrByInputStream(entitySecond.getContent());
		System.out.println("entity str: " + paramStrSecond);
		System.out.println("----------request line: " + httpPostSecond.getRequestLine());

		CookieStore cookieStore = new BasicCookieStore();
		HttpClientContext httpClientContext = HttpClientContext.create();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse responseSecond = httpClient.execute(httpPostSecond, httpClientContext);

		cookieStore = httpClientContext.getCookieStore();
System.out.println("------------cookieStores: " + cookieStore);
System.out.println("------------httpContext: " + httpClientContext.toString());
		List<Cookie> cookies = cookieStore.getCookies();
System.out.println("---------------- cookie ------------------");
		for(Cookie cookie : cookies) {
			System.out.println(cookie.getName() + " = " + cookie.getValue());
		}
		
System.out.println("------------ response headers:");
		Header[] headers = responseSecond.getAllHeaders();
		for(Header header : headers) {
			System.out.println(header.getName() + "=" + header.getValue());
		}
		
/*		String info = this.getResponseBody(responseSecond.getEntity());
System.out.println("info: " + info);*/
		
		String reStrSecond = crawler.getStrByInputStream(responseSecond.getEntity().getContent());
System.out.println(reStrSecond);
		
		try {
			JSONParser parser = new JSONParser();
			Object resultObject = parser.parse(reStrSecond);
System.out.println(resultObject);
			if (resultObject instanceof JSONArray) {
				JSONArray array = (JSONArray) resultObject;
				for (Object object : array) {
					JSONObject obj = (JSONObject) object;
					System.out.println(obj.toString());
					System.out.println(obj.get("example"));
					System.out.println(obj.get("fr"));
				}
			} else if (resultObject instanceof JSONObject) {
				JSONObject obj = (JSONObject) resultObject;
				System.out.println(obj.toString());
				System.out.println(obj.get("example"));
				System.out.println(obj.get("fr"));
			}
		} catch (Exception e) {
		}

		System.out.println(reStrSecond);

	}
	
	
	
	public String getResponseBody(final HttpEntity entity) throws IOException, ParseException {
        if (entity == null) {
            throw new IllegalArgumentException("HTTP entity may not be null");
        }
        InputStream instream = entity.getContent();

        if (instream == null) {
            return "";
        }
        if (entity.getContentLength() > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(

            "HTTP entity too large to be buffered in memory");
        }
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(instream, HTTP.UTF_8));

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        } finally {
            instream.close();
            reader.close();
        }
        return buffer.toString();
    }

}
