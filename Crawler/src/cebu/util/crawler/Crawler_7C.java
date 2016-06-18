package cebu.util.crawler;

import java.util.*;
import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicNameValuePair;

import cebu.model.FormParams;
import cebu.model.Ticket;
import cebu.util.common.CommonUtil;

/**
 * 爬虫类，继承自Crawler
 * 7C
 * @author Administrator
 *
 */


public class Crawler_7C extends Crawler{
	
	/** 单例模式 **/
	private Crawler_7C() {

	}

	private static class CrawlerInstanceHolder {
		private static Crawler_7C Crawler_7C = new Crawler_7C();
	}

	public static Crawler_7C getInstance() {
		return CrawlerInstanceHolder.Crawler_7C;
	}

	
	/**
	 * 根据ticket构造第二次post得到价格信息的表单
	 * 单程
	 * @param ticket
	 * @return
	 */
	public List<NameValuePair> buildParamsForPricePostOw(Ticket ticket) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("ReqType", "Price"));
		params.add(new BasicNameValuePair("TripType", "OW"));
		params.add(new BasicNameValuePair("AdultPaxCnt", "2"));
		params.add(new BasicNameValuePair("ChildPaxCnt", "0"));
		params.add(new BasicNameValuePair("InfantPaxCnt", "0"));
		params.add(new BasicNameValuePair("DepStn", ticket.getdepAirport()));
		params.add(new BasicNameValuePair("ArrStn", ticket.getarrAirport()));
		params.add(new BasicNameValuePair("DepDate", ticket.getdepTime()));
		params.add(new BasicNameValuePair("ArrDate", ticket.getarrTime()));
		params.add(new BasicNameValuePair("FltNo", ticket.getflightNumber().split(" ")[1]));
		params.add(new BasicNameValuePair("RBD", "O"));
		params.add(new BasicNameValuePair("FareBasis", "OOW1CN"));
		params.add(new BasicNameValuePair("DepDate2", ""));
		params.add(new BasicNameValuePair("ArrDate2", ""));
		params.add(new BasicNameValuePair("DepStn2", ""));
		params.add(new BasicNameValuePair("ArrStn2", ""));
		params.add(new BasicNameValuePair("Language", "EN"));
		params.add(new BasicNameValuePair("RouteType", "I"));
		params.add(new BasicNameValuePair("SystemType", "IBE"));
		return params;
	}
	
	public List<NameValuePair> buildParamsForPricePostRt(Ticket ticketDep, Ticket ticketRet) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("ReqType", "Price"));
		params.add(new BasicNameValuePair("TripType", "RT"));
		params.add(new BasicNameValuePair("AdultPaxCnt", "2"));
		params.add(new BasicNameValuePair("ChildPaxCnt", "0"));
		params.add(new BasicNameValuePair("InfantPaxCnt", "0"));
		params.add(new BasicNameValuePair("DepStn", ticketDep.getdepAirport()));
		params.add(new BasicNameValuePair("ArrStn", ticketDep.getarrAirport()));
		params.add(new BasicNameValuePair("DepDate", ticketDep.getdepTime()));
		params.add(new BasicNameValuePair("ArrDate", ticketDep.getarrTime()));
		params.add(new BasicNameValuePair("FltNo", ticketDep.getflightNumber().split(" ")[1]));
		params.add(new BasicNameValuePair("RBD", "A"));
		params.add(new BasicNameValuePair("FareBasis", "AEE1CN"));
		params.add(new BasicNameValuePair("DepDate2", ticketRet.getdepTime()));
		params.add(new BasicNameValuePair("ArrDate2", ticketRet.getarrTime()));
		params.add(new BasicNameValuePair("DepStn2", ticketRet.getdepAirport()));
		params.add(new BasicNameValuePair("ArrStn2", ticketRet.getarrAirport()));
		params.add(new BasicNameValuePair("FltNo2", ticketRet.getflightNumber().split(" ")[1]));
		params.add(new BasicNameValuePair("RBD2", "D"));
		params.add(new BasicNameValuePair("FareBasis2", "DEE1CN"));
		params.add(new BasicNameValuePair("Language", "EN"));
		params.add(new BasicNameValuePair("RouteType", "I"));
		params.add(new BasicNameValuePair("SystemType", "IBE"));
		return params;
	}
	
	
	/**
	 * 根据表单类FormParams获取post返回的json
	 * @param postUrl
	 * @param formParams
	 * @return
	 */
	public String getPostResponseJsonByParams(String postUrl, FormParams formParams) {
		return this.getPostResponseJsonByParams(postUrl, formParams.getFormParams());
	}
	
	/**
	 * 根据NameValuePair list获取post返回后的json
	 * @param postUrl
	 * @param params
	 * @return
	 */
	public String getPostResponseJsonByParams(String postUrl, List<NameValuePair> params) {
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, Consts.UTF_8);
		HttpPost httpPost = new HttpPost(postUrl);
		httpPost.setEntity(formEntity);
		this.setHttpPostHeader(httpPost);
		
		CloseableHttpClient httpClient = HttpClients.createDefault(); 
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpPost);
		} catch (Exception e) {
			CommonUtil.log(Crawler_7C.class, "访问url: " + postUrl + "错误");
		}
		String jsonStr = this.getHtmlByResponse(response);
		return jsonStr;
	}
	
	
	
	/**
	 * 设置HttpPost的请求头信息
	 */
	public void setHttpPostHeader(HttpPost httpPost) {
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
