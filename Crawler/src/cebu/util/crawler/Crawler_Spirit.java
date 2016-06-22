package cebu.util.crawler;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;


/**
 * 爬虫类，继承自Crawler
 * 7C
 * @author Administrator
 *
 */


public class Crawler_Spirit extends Crawler{
	
	/** 单例模式 **/
	private Crawler_Spirit() {

	}

	private static class CrawlerInstanceHolder {
		private static Crawler_Spirit Crawler_Spirit = new Crawler_Spirit();
	}

	public static Crawler_Spirit getInstance() {
		return CrawlerInstanceHolder.Crawler_Spirit;
	}

	
	/**
	 * 设置HttpPost的请求头信息
	 */
	@Override
	public void setHttpPostHeader(HttpPost httpPost) {
		httpPost.setHeader("User-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36");
		httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
		httpPost.setHeader("Accept-Encoding", "gzip, deflate");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		httpPost.setHeader("Host", "www.spirit.com");
		httpPost.setHeader("DNT", "1");
		httpPost.setHeader("Origin", "https://www.spirit.com");
		httpPost.setHeader("Referer", "https://www.spirit.com/");
		httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
	}

	@Override
	public void setHttpGetHeader(HttpGet httpGet) {
		// TODO Auto-generated method stub
		
	}

}
