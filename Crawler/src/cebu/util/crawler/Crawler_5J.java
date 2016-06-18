package cebu.util.crawler;

import java.io.*;
import java.net.*;
import java.util.*;
import org.apache.http.*;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicNameValuePair;

public class Crawler_5J extends Crawler{
	
	/** 单例模式 **/
	private Crawler_5J() {

	}

	private static class CrawlerInstanceHolder {
		private static Crawler_5J Crawler_5J = new Crawler_5J();
	}

	public static Crawler_5J getInstance() {
		return CrawlerInstanceHolder.Crawler_5J;
	}

	

	// 测试函数，固定参数
	/**
	 * @param postUrl
	 * @param savePath
	 */
	/**
	 * @param postUrl
	 * @param savePath
	 */
	public void savePostResponseHtmlConst(String postUrl, String savePath) {
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		formParams.add(new BasicNameValuePair("__EVENTTARGET", ""));
		formParams.add(new BasicNameValuePair("__EVENTARGUMENT", ""));
		formParams.add(new BasicNameValuePair("__VIEWSTATE", "/wEPDwUBMGRkZ0I3jKvfnhKfx57f3Wjr7p2WhvU="));
		formParams.add(new BasicNameValuePair("pageToken", ""));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$RadioButtonMarketStructure", "OneWay"));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView_AvailabilitySearchInputSearchVieworiginStation1",
				"HKG"));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$TextBoxMarketOrigin1", "HKG"));
		formParams.add(new BasicNameValuePair("name_originStationContainercategory", "HongKong (HKG)"));
		formParams.add(new BasicNameValuePair("originStationContainercategory", "HKG"));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView_AvailabilitySearchInputSearchViewdestinationStation1", "MNL"));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$TextBoxMarketDestination1", "MNL"));
		formParams.add(new BasicNameValuePair("name_destinationStationContainercategory", "Manila (MNL)"));
		formParams.add(new BasicNameValuePair("destinationStationContainercategory", "MNL"));
		formParams.add(new BasicNameValuePair("date_picker_1", "2016-06-20"));
		formParams.add(new BasicNameValuePair("date_picker_1_AccCalendar", "2016-06-20"));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListMarketDay1", "20"));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListMarketMonth1", "2016-06"));
		formParams.add(
				new BasicNameValuePair("ControlGroupSearchView_AvailabilitySearchInputSearchVieworiginStation2", ""));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$TextBoxMarketOrigin2", ""));
		formParams.add(new BasicNameValuePair("name_originStationContainercategory", "From"));
		formParams.add(new BasicNameValuePair("originStationContainercategory", ""));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView_AvailabilitySearchInputSearchViewdestinationStation2", ""));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$TextBoxMarketDestination2", ""));
		formParams.add(new BasicNameValuePair("name_destinationStationContainercategory", "To"));
		formParams.add(new BasicNameValuePair("destinationStationContainercategory", ""));
		formParams.add(new BasicNameValuePair("date_picker_2", "2016-06-20"));
		formParams.add(new BasicNameValuePair("date_picker_2_AccCalendar", "2016-06-20"));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListMarketDay2", "20"));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListMarketMonth2", "2016-06"));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListPassengerType_ADT", "2"));
		formParams.add(new BasicNameValuePair(
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListPassengerType_CHD", "0"));
		formParams.add(
				new BasicNameValuePair("ControlGroupSearchView$AvailabilitySearchInputSearchView$promoCodeID", ""));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView$AvailabilitySearchInputSearchView$ButtonSubmit",
				"FIND IT!"));
		formParams.add(new BasicNameValuePair("MemberLoginView2LoginView$TextBoxUserID", "EMAIL"));
		formParams.add(new BasicNameValuePair("MemberLoginView2LoginView$PasswordFieldPassword", ""));
		formParams.add(new BasicNameValuePair("MemberLoginView2LoginView$tboxConfirmation", ""));
		formParams.add(new BasicNameValuePair("MemberLoginView2LoginView$tboxHiddenUsername", ""));

		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);

		HttpPost httpPost = new HttpPost(postUrl);
		this.setHttpPostHeader(httpPost);
		httpPost.setEntity(formEntity);

		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;

		try {
			// 处理post之后的重定向
			httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
			
			HttpClientContext context = HttpClientContext.create();
			response = httpClient.execute(httpPost, context);
			CookieStore cookieStore = context.getCookieStore();
			this.saveHtmlByResponse(response, savePath);
			
			String radioValue = "0~Z~~ZRP~6020~~1~X|5J~ 109~ ~~HKG~06/20/2016 08:25~MNL~06/20/2016 10:35~";
			this.saveHtmlByRadio(cookieStore, radioValue, savePath.replace(".html", "_getUri.html"));
		} catch (Exception e) {
			System.out.println("访问[ " + postUrl + " ]出现异常!");
			e.printStackTrace();
		} finally {
			this.free(response, httpClient);
		}
	}

	
	/**
	 * 根据radio选项获取对应的动态生成的html，不许传递cookie记录会话状态
	 * @param cookieStore	传递CookieStore
	 * @param radioValue radio的value值
	 * @return 返回get方法调用后的response的html字符串
	 */
	public String getHtmlByRadio(CookieStore cookieStore, String radioValue) {
		// 设置参数
		List<NameValuePair> getParams = new ArrayList<NameValuePair>();
		getParams.add(new BasicNameValuePair("flightKeys", radioValue));
		getParams.add(new BasicNameValuePair("fMarkets", "1"));
		getParams.add(new BasicNameValuePair("keyDelimeter", ","));
		// 构建uri
		URIBuilder uriBuilder = new URIBuilder();
		uriBuilder.setScheme("https")
			.setHost("book.cebupacificair.com")
			.setPath("/TaxAndFeeInclusiveDisplayAjax-resource.aspx")
			.setParameters(getParams)
			.setCharset(Consts.UTF_8);
		
		URI uri = null;;
		String html = null;
		try {
			uri = uriBuilder.build();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		HttpGet httpGet = new HttpGet(uri);
		this.setHttpGetHeader(httpGet);		// 设置请求头
		
		// 自定义httpclient，设置cookieStore
		CloseableHttpClient httpClientWithCookie = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		CloseableHttpResponse response = null;
		try {
			response = httpClientWithCookie.execute(httpGet);
			html = this.getHtmlByResponse(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return html;
	}
	
	public ArrayList<String> getHtmlByRadio(CookieStore cookieStore, ArrayList<String> radioValues) {
		ArrayList<String> radioValueGeneratedHtmls = new ArrayList<String>();
		if(radioValues == null ||  radioValues.size() <= 0)
			return null;
		
		for(String radioValue : radioValues) {
			String html = this.getHtmlByRadio(cookieStore, radioValue);
			radioValueGeneratedHtmls.add(html);
		}
		return radioValueGeneratedHtmls;
	}
	
	/**
	 * 保存根据radio选项获取对应的动态生成的html到本地文件，需要传递cookie记录会话状态
	 * @param cookieStore	传递CookieStore
	 * @param radioValue radio的value值
	 */
	public void saveHtmlByRadio(CookieStore cookieStore, String radioValue, String savePath) {
		// 设置参数
		List<NameValuePair> getParams = new ArrayList<NameValuePair>();
		getParams.add(new BasicNameValuePair("flightKeys", radioValue));
		getParams.add(new BasicNameValuePair("fMarkets", "1"));
		getParams.add(new BasicNameValuePair("keyDelimeter", ","));
		// 构建uri
		URIBuilder uriBuilder = new URIBuilder();
		uriBuilder.setScheme("https")
			.setHost("book.cebupacificair.com")
			.setPath("/TaxAndFeeInclusiveDisplayAjax-resource.aspx")
			.setParameters(getParams)
			.setCharset(Consts.UTF_8);
		
		URI uri = null;;
		try {
			uri = uriBuilder.build();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		HttpGet httpGet = new HttpGet(uri);
		this.setHttpGetHeader(httpGet);		// 设置请求头
		
		// 自定义httpclient，设置cookieStore
		CloseableHttpClient httpClientWithCookie = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		CloseableHttpResponse response = null;
		try {
			response = httpClientWithCookie.execute(httpGet);
			this.saveHtmlByResponse(response, savePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 设置HttpPost的请求头信息
	 */
	public void setHttpPostHeader(HttpPost httpPost) {
		httpPost.setHeader("Host", "book.cebupacificair.com");
		httpPost.setHeader("User-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.7 Safari/537.36");
		httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		httpPost.setHeader("Accept-Encoding", "gzip, deflate, br");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		httpPost.setHeader("Referer", "https://book.cebupacificair.com/Search.aspx?culture=en-us");
	}
	
	/**
	 * 设置HttpGet的请求头信息
	 */
	public void setHttpGetHeader(HttpGet httpGet) {
		httpGet.setHeader("Host", "book.cebupacificair.com");
		httpGet.setHeader("User-agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0");
		httpGet.setHeader("Accept", "*/*");
		httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		httpGet.setHeader("Accept-Encoding", "gzip, deflate, br");
		httpGet.setHeader("X-Requested-With", "XMLHttpRequest");
		httpGet.setHeader("Referer", "https://book.cebupacificair.com/Select.aspx");
	}

}
