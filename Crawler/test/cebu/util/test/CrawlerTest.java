package cebu.util.test;

import java.util.List;
import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.junit.Before;
import org.junit.Test;

import cebu.model.FormParams;
import cebu.util.Crawler;
import cebu.util.FormUtil.DestStation;
import cebu.util.FormUtil.OrgStation;
import cebu.util.FormUtil.TravelOption;

public class CrawlerTest {
	private final static String URL = "https://book.cebupacificair.com/Search.aspx?culture=en-us";
	private final static String SAVE_PATH = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/test_savedHtmlByUrl.html";
	private final static String PostUrl = "https://book.cebupacificair.com/Search.aspx";
	private final static String SAVE_PATH_Response = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/post_response.html";
	private final static String SAVE_PATH_Response_Params = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/post_response_params.html";
	
	private Crawler crawler = null;
	
	@Before
	public void before() {
		crawler = Crawler.getInstance();
	}
	
	@Test
	public void test_saveHtmlByUrl() {
		crawler.saveHtmlByUrl(URL, SAVE_PATH);
		System.out.println("done");
	}
	
	@Test
	public void test_getHtmlByUrl() {
		String str = crawler.getHtmlByUrl(URL);
		System.out.println(str);
	}
	
	@Test
	public void test_savePostResponseHtmlConst() {
		crawler.savePostResponseHtmlConst(PostUrl, SAVE_PATH_Response);
		System.out.println("done");
	}
	
	
	@Test
	public void test_savePostResponseHtmlByParams() {
		// 构建表单变量
		FormParams formParams = new FormParams();
		formParams.setTravelOption(TravelOption.OneWay)
			.setOrgStation(OrgStation.HKG)
			.setDestStation(DestStation.MNL)
			.setDepartureTime("2016-06-20")
			.setAdultNum(2)
			.setChildNum(0)
			.build();
		crawler.savePostResponseHtmlByParams(PostUrl, formParams, SAVE_PATH_Response_Params);
		
		HttpClientContext context = HttpClientContext.create();
		CookieStore[] cookieStores = {context.getCookieStore()};
		String html = crawler.getPostResponseHtmlByParams(PostUrl, formParams, cookieStores);
		List<Cookie> cookies = cookieStores[0].getCookies();
		for(Cookie cookie : cookies) {
			System.out.println(cookie.getName() + "=" + cookie.getValue());
		}
		System.out.println(html);
		System.out.println("done");
	}
	
	@Test
	public void test_getPostResponseHtmlByParams() {
		// 构建表单变量
		FormParams formParams = new FormParams();
		formParams.setTravelOption(TravelOption.OneWay)
			.setOrgStation(OrgStation.HKG)
			.setDestStation(DestStation.MNL)
			.setDepartureTime("2016-06-20")
			.setAdultNum(2)
			.setChildNum(0)
			.build();
		
		CookieStore[] cookieStores = new CookieStore[1];
		String html = crawler.getPostResponseHtmlByParams(PostUrl, formParams, cookieStores);
		//String html = crawler.getPostResponseHtmlByParams(PostUrl, formParams);
		System.out.println(html);
		System.out.println(cookieStores[0]);
		System.out.println("done");
	}
	
	@Test
	public void test_getHtmlByRadio() {
		// 构建表单变量
		FormParams formParams = new FormParams();
		formParams.setTravelOption(TravelOption.OneWay)
			.setOrgStation(OrgStation.HKG)
			.setDestStation(DestStation.MNL)
			.setDepartureTime("2016-06-25")
			.setAdultNum(2)
			.setChildNum(0)
			.build();
		
		CookieStore[] cookieStores = new CookieStore[1];
		
		String html = crawler.getPostResponseHtmlByParams(PostUrl, formParams, cookieStores);
		String radioValue = "0~Z~~ZRP~6020~~1~X|5J~ 109~ ~~HKG~06/20/2016 08:25~MNL~06/20/2016 10:35~";
		
		String resHtml = crawler.getHtmlByRadio(cookieStores[0], radioValue);
		System.out.println(resHtml);
		System.out.println("done");
	
	}

}
