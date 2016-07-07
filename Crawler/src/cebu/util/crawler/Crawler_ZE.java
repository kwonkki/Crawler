package cebu.util.crawler;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;


/**
 * 爬虫类，继承自Crawler
 * 7C
 * @author Administrator
 *
 */


public class Crawler_ZE extends Crawler{
	
	/** 单例模式 **/
	private Crawler_ZE() {

	}

	private static class CrawlerInstanceHolder {
		private static Crawler_ZE Crawler_ZE = new Crawler_ZE();
	}

	public static Crawler_ZE getInstance() {
		return CrawlerInstanceHolder.Crawler_ZE;
	}

	
	
	/**
	 * 设置第二个HttpPost的请求头信息
	 * 可能不需要set headers也能够直接post
	 */
	public void setHttpPostHeader2(HttpPost httpPost) {
		httpPost.setHeader("User-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36");
		httpPost.setHeader("Accept", "*/*");
		httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
		httpPost.setHeader("Accept-Encoding", "gzip, deflate");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		httpPost.setHeader("DNT", "1");
		httpPost.setHeader("Host", "www.eastarjet.com");
		httpPost.setHeader("Origin", "http://www.eastarjet.com");
		httpPost.setHeader("Referer", "http://www.eastarjet.com/book/book.htm");
		httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
	}
	
	@Override
	public void setHttpPostHeader(HttpPost httpPost) {

	}

	@Override
	public void setHttpGetHeader(HttpGet httpGet) {
		// TODO Auto-generated method stub
		
	}

}
