package test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import cebu.util.Crawler;

public class JejuTest {
	private final static String Dir = "D:/Documents/Github/Crawler/Data/";
	private String SavePath = Dir + "post_response_7C.html";
	// 爬虫类和html解析类
	private static Crawler crawler = Crawler.getInstance();
	public static void main(String[] args) {
	}

	
	
	@Test
	public void test_final() throws Exception {
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
		
		String postUrl = "https://www.jejuair.net/jejuair/com/jeju/ibe/searchAvail.do";
		UrlEncodedFormEntity formEntity2 = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
		HttpPost httpPost = new HttpPost(postUrl);
		httpPost.setEntity(formEntity2);

		httpPost.setHeader("User-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36");
		httpPost.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
		httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
		httpPost.setHeader("Accept-Encoding", "gzip, deflate");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		httpPost.setHeader("Host", "www.jejuair.net");
		httpPost.setHeader("DNT", "1");
		httpPost.setHeader("Origin", "https://www.jejuair.net");
		httpPost.setHeader("Referer", "https://www.jejuair.net/jejuair/com/jeju/ibe/goAvail.do");
		httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
		
		HttpClientContext context = HttpClientContext.create();
		CloseableHttpClient httpClient = HttpClients.createDefault(); 
		CloseableHttpResponse response = httpClient.execute(httpPost, context);
		String html = crawler.getHtmlByResponse(response);
		crawler.saveHtmlToFile(html, SavePath);
System.out.println(html);
	}
	
	
	
	
	
	@Test
	public void test_match() {
		String str = "NetFunnel.gRtype=5101;NetFunnel.gControl.resul" + 
		"t='5002:200:key=45DAA5D8E96606173883AE38FF1B2A887F93A4DB73288CA37BEA56941D957922" + 
		"733F0686F16DAEED0A19C3A53D2A2A668CEF525E248A2F0CBA3300189B88CB4790380560FF1645B3" + 
		"2BFE90D1CA9D4F452F019F599E4CC67FBE08CE04706479216428C47DB38E08B635A222911665CE54" + 
		"2C30&nwait=0&nnext=0&tps=0&ttl=0&ip=apc.jejuair.net&port=443'; NetFunnel.gContro" + 
		"l._showResult();";
		
		
		String regex = "5002:200:.+443";
		//String regex = "NetFunnel";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		while(matcher.find()) {
			System.out.println(matcher.group());
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
