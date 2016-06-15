package test;

import static org.junit.Assert.*;

import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.apache.commons.dbcp2.Constants;
import org.apache.http.Consts;
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

public class JejuTest {
	private static final String Host = "https://www.jejuair.net";
	private static final String PostUrl = "https://www.jejuair.net/jejuair/com/jeju/ibe/goAvail.do";
	private final static String Dir = "D:/Documents/Github/Crawler/Data/";
	private String SavePath = Dir + "saveResponseHtml_7C.html";
	private String ParamsLabel = "hidRequestData=";
	
	// 爬虫类和html解析类
	private static Crawler crawler = Crawler.getInstance();
	private static HtmlParser htmlParser = HtmlParser.getInstance();
		
	@Test
	public void test() throws Exception {
		String postUrl = PostUrl;
		Gson gson = new Gson();

		JSONObject json = new JSONObject();
		json.put("mainFlag", "main");
		json.put("routeType", "I");
		json.put("tripType", "O");
		json.put("payType", "");
		json.put("depStn1", "WEH");
		json.put("arrStn1", "");
		json.put("depStn2", "");
		json.put("arrStn2", "ICN");
		json.put("depStnName1", "Weihai");
		json.put("arrStnName1", "Destination");
		json.put("depStnName2", "Origin");
		json.put("arrStnName2", "Seoul(Incheon)");
		json.put("depDate1", "2016-12-20");
		json.put("depDate2", "2016-12-20");
		json.put("country1", "");
		json.put("country2", "CN");
		json.put("selectSectionSize",  1);
		json.put("adtPaxCnt", "2");
		json.put("chdPaxCnt", "0");
		json.put("infPaxCnt", "0");
		json.put("dualFlag", "");
		json.put("multiFlag", "");	
		
		
		
		FormParams_7C pojo = new FormParams_7C();
		// 将JSON进行UTF-8编码,以便传输中文  
        String encoderJson = URLEncoder.encode(gson.toJson(pojo), Consts.UTF_8.name()); 
		//StringEntity postingString = new StringEntity(ParamsLabel + encoderJson);//gson.tojson() converts your pojo to json
		
        StringEntity postingString = new StringEntity(json.toString());
			
		
		HttpPost httpPost = new HttpPost(postUrl);
		
		String paramStr = crawler.getStrByInputStream(postingString.getContent());
		System.out.println(paramStr);
		
		
		httpPost.setEntity(postingString);
		
		httpPost.setHeader("Host", "www.jejuair.net");
		httpPost.setHeader("User-agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36");
		httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		httpPost.setHeader("Accept-Encoding", "gzip, deflate, br");
		httpPost.setHeader("Connection", "keep-alive");
		//httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		httpPost.setHeader("Origin", "https://www.jejuair.net");
		httpPost.setHeader("Referer", "https://www.jejuair.net/jejuair/main.jsp");
		httpPost.setHeader("Upgrade-Insecure-Requests", "1");
		httpPost.setHeader("DNT", "1");
		httpPost.setHeader("Content-type", "application/json");
		
		try {
			System.out.println(crawler.getStrByInputStream(httpPost.getEntity().getContent()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
/*		CloseableHttpClient httpClient = HttpClients.createDefault(); 
		HttpClientContext context = HttpClientContext.create();
		CloseableHttpResponse response302 = httpClient.execute(httpPost, context);
System.out.println("first response code: " + response302.getStatusLine().getStatusCode()); 
		
		// 不存在内部重定向，302，则不存在该航班信息，直接返回空
		if (response302.getStatusLine().getStatusCode() != 302) 
			return;
		
		// 内部重定向，302 存在该航班信息，需要进一步处理
		String redirectURL = Host + response302.getFirstHeader("Location").getValue();	// 重定向之后的url，为post返回页面
System.out.println("redirectUrl: " + redirectURL);	
		
		HttpGet request = new HttpGet(redirectURL);		// get方式获取response
		CloseableHttpResponse response200 = httpClient.execute(request);
System.out.println("seconde response code: " + response200.getStatusLine().getStatusCode());*/
		
		// 设置cookie
		
		
		
		
		//CloseableHttpClient httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
		CloseableHttpClient httpClient = HttpClients.createDefault(); 
		HttpClientContext context = HttpClientContext.create();
		CloseableHttpResponse  response = httpClient.execute(httpPost, context);
		
		
		
		CookieStore[] cookieStores = new CookieStore[1];
		cookieStores[0] = context.getCookieStore();
		
System.out.println(cookieStores[0]);
System.out.println(response.getStatusLine().getStatusCode());

		String html = crawler.getHtmlByResponse(response);
		crawler.saveHtmlToFile(html, SavePath);
		//System.out.println(html);
		
		
/*		String getStr = PostUrl + "?" + paramStr;
System.out.println(getStr);
		HttpGet httpGet = new HttpGet(getStr);
		
		CloseableHttpResponse  responseGet = httpClient.execute(httpGet, context);
		System.out.println(cookieStores[0]);
		System.out.println(responseGet.getStatusLine().getStatusCode());

String htmlGet = crawler.getHtmlByResponse(responseGet);
	 	crawler.saveHtmlToFile(htmlGet, SavePath.replace(".html", "_get.html"));*/
				
	}

}
