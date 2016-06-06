package com.crawler.cebu;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.crawler.cebu.test.CrawlerUtilTest;

public class ParseUtil {

	private CrawlerUtil crawlerUtil;

	private ParseUtil() {
		this.crawlerUtil = CrawlerUtil.getInstance();
	}

	private static class ParseUtilInstanceHolder {
		private static ParseUtil Parse_Util = new ParseUtil();
	}

	public static ParseUtil getInstance() {
		return ParseUtilInstanceHolder.Parse_Util;
	}

	private final static String WHITESPACE_UTF8 = "/u00a0";

	public void getInfo() {
		String url = "https://book.cebupacificair.com/Search.aspx?culture=en-us";
		String html = crawlerUtil.getHtmlByUrl(url);
		if (html != null && !"".equals(html)) {
			System.out.println(html);
			Document doc = Jsoup.parse(html);
			String selectStr = "select#ControlGroupSearchView_AvailabilitySearchInputSearchVieworiginStation1";
			Element target = doc.select(selectStr).get(0);
			System.out.println(target.text());
			Elements options = target.select("option");
			System.out.println("options count: " + options.size());
			for (Element option : options) {
				System.out.println(option.text().replace(WHITESPACE_UTF8, ""));
			}

			selectStr = "select#ControlGroupSearchView_AvailabilitySearchInputSearchViewdestinationStation1";
			target = doc.select(selectStr).get(0);
			System.out.println(target.text());
			options = target.select("option");
			System.out.println("options count: " + options.size());
			for (Element option : options) {
				System.out.println(option.text().replace(WHITESPACE_UTF8, ""));
			}
		}
	}

	/**
	 * 从文件解析html，提供相对应的url
	 * 
	 * @param filePath
	 *            本地html文件路径
	 * @param baseUrl
	 *            文件的原url地址，解析相关的链接
	 */
	public void parseByFileWithUrl(String filePath, String baseUrl) {
		if (filePath == null || filePath.equals("")) {
			System.out.println("no such file ...");
			return;
		}

		File file = new File(filePath);
		Document doc = null;
		try {
			doc = Jsoup.parse(file, "utf-8", baseUrl);
			String prefix = "select#";

			System.out.println("--------------------- day departure ---------------------");
			String selectDayDeparture = prefix + FormUtil.Name_CalendarDayDeparture.replace('$', '_');
			Element target = doc.select(selectDayDeparture).get(0);
			System.out.println("target text: " + target.text());
			Elements options = target.select("option");
			System.out.println("options count: " + options.size());
			for (Element option : options) {
				System.out.print("\"" + option.text() + "\", ");
			}
			System.out.println();

			System.out.println("--------------------- month departure ---------------------");
			String selectMonthDeparture = prefix + FormUtil.Name_CalendarMonthDeparture.replace('$', '_');
			target = doc.select(selectMonthDeparture).get(0);
			System.out.println("target text: " + target.text());
			options = target.select("option");
			System.out.println("options count: " + options.size());
			for (Element option : options) {
				System.out.print("\"" + option.text() + "\", ");
			}
			System.out.println();

			System.out.println("--------------------- day return ---------------------");
			String selectDayReturn = prefix + FormUtil.Name_CalendarDayReturn.replace('$', '_');
			target = doc.select(selectDayReturn).get(0);
			System.out.println("target text: " + target.text());
			options = target.select("option");
			System.out.println("options count: " + options.size());
			for (Element option : options) {
				System.out.print("\"" + option.text() + "\", ");
			}
			System.out.println();

			System.out.println("--------------------- month departure ---------------------");
			String selectMonthReturn = prefix + FormUtil.Name_CalendarMonthReturn.replace('$', '_');
			target = doc.select(selectMonthReturn).get(0);
			System.out.println("target text: " + target.text());
			options = target.select("option");
			System.out.println("options count: " + options.size());
			for (Element option : options) {
				System.out.print("\"" + option.text() + "\", ");
			}
			System.out.println();
		} catch (IOException e) {
			System.out.println("parse exeception ...");
			e.printStackTrace();
		}
	}

	/**
	 * 从文件解析html
	 * 
	 * @param filePath
	 *            本地html文件路径
	 */
	public void parseByFile(String filePath) {
		if (filePath == null || filePath.equals("")) {
			System.out.println("no such file ...");
			return;
		}

		File file = new File(filePath);
		Document doc = null;
		try {
			doc = Jsoup.parse(file, "utf-8");
			String prefix = "select#";

			System.out.println("--------------------- day departure ---------------------");
			String selectDayDeparture = prefix + FormUtil.Name_CalendarDayDeparture.replace('$', '_');
			Element target = doc.select(selectDayDeparture).get(0);
			System.out.println("target text: " + target.text());
			Elements options = target.select("option");
			System.out.println("options count: " + options.size());
			for (Element option : options) {
				System.out.print("\"" + option.text() + "\", ");
			}
			System.out.println();

			System.out.println("--------------------- month departure ---------------------");
			String selectMonthDeparture = prefix + FormUtil.Name_CalendarMonthDeparture.replace('$', '_');
			target = doc.select(selectMonthDeparture).get(0);
			System.out.println("target text: " + target.text());
			options = target.select("option");
			System.out.println("options count: " + options.size());
			for (Element option : options) {
				System.out.print("\"" + option.text() + "\", ");
			}
			System.out.println();

			System.out.println("--------------------- day return ---------------------");
			String selectDayReturn = prefix + FormUtil.Name_CalendarDayReturn.replace('$', '_');
			target = doc.select(selectDayReturn).get(0);
			System.out.println("target text: " + target.text());
			options = target.select("option");
			System.out.println("options count: " + options.size());
			for (Element option : options) {
				System.out.print("\"" + option.text() + "\", ");
			}
			System.out.println();

			System.out.println("--------------------- month departure ---------------------");
			String selectMonthReturn = prefix + FormUtil.Name_CalendarMonthReturn.replace('$', '_');
			target = doc.select(selectMonthReturn).get(0);
			System.out.println("target text: " + target.text());
			options = target.select("option");
			System.out.println("options count: " + options.size());
			for (Element option : options) {
				System.out.print("\"" + option.text() + "\", ");
			}
			System.out.println();
		} catch (IOException e) {
			System.out.println("parse exeception ...");
			e.printStackTrace();
		}
	}

	public void savePostResponseHtml(String postUrl, String savePath) {
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
				"ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListPassengerType_ADT", "1"));
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
		httpPost.setHeader("Host", "book.cebupacificair.com");
		httpPost.setHeader("User-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.7 Safari/537.36");
		httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		httpPost.setHeader("Accept-Encoding", "gzip, deflate, br");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		httpPost.setHeader("Referer", "https://book.cebupacificair.com/Search.aspx?culture=en-us");

		httpPost.setEntity(formEntity);

		/*
		 * try { System.out.println(
		 * "--------------------------------- executing request entity ------------------------------------"
		 * );
		 * System.out.println(crawlerUtil.getStrByInputStream(httpPost.getEntity
		 * ().getContent())); } catch (UnsupportedOperationException |
		 * IOException e1) { e1.printStackTrace(); }
		 * 
		 * System.out.println(
		 * "--------------------------------- request headers ------------------------------------"
		 * ); Header[] headers1 = httpPost.getAllHeaders(); for(Header header1 :
		 * headers1) System.out.println(header1.getName() + " : " +
		 * header1.getValue());
		 */

		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;

		try {
			// 处理post之后的重定向
			httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
			response = httpClient.execute(httpPost);

			/*
			 * System.out.println(crawlerUtil.getStrByInputStream(response.
			 * getEntity().getContent()));
			 * 
			 * System.out.println(
			 * "--------------------------------- response headers ------------------------------------"
			 * ); Header[] headers = response.getAllHeaders(); for(Header header
			 * : headers) System.out.println(header.getName() + " : " +
			 * header.getValue());
			 * 
			 * System.out.println(
			 * "--------------------------------- response header location ------------------------------------"
			 * ); Header[] locationHeaders = response.getHeaders("Location");
			 * System.out.println("location header length : " +
			 * locationHeaders.length); if(locationHeaders.length == 0)
			 * System.out.println("header location length is 0 ..."); else
			 * System.out.println(locationHeaders[0].getName() + " : " +
			 * locationHeaders[0].getValue());
			 * 
			 * 
			 * System.out.println(
			 * "--------------------------------- response string ------------------------------------"
			 * ); System.out.println(response.toString());
			 */

			System.out.println("response code : " + response.getStatusLine().getStatusCode());
			crawlerUtil.saveHtmlByHttpResponse(response, savePath);

		} catch (Exception e) {
			System.out.println("访问[ " + postUrl + " ]出现异常!");
			e.printStackTrace();
		} finally {
			crawlerUtil.free(response, httpClient);
		}
	}

	public void parseFlightInfoByFile(String filePath) {
		if (filePath == null || filePath.equals("")) {
			System.out.println("no such file ...");
			return;
		}

		File file = new File(filePath);
		Document doc = null;
		try {
			doc = Jsoup.parse(file, "utf-8");

			String selectTable = "table#availabilityTable";
			Element table = doc.select(selectTable).get(0);
			
			// 表头
			Elements tHead = table.select("thead>tr");
			for (Element headName : tHead)
				System.out.print(headName.text());
			System.out.println();
			
			// 航班
			Elements trs= table.select("tbody>tr");
			for (Element tr : trs)
				System.out.println(tr.text());
			System.out.println();
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
		}

	}

}
