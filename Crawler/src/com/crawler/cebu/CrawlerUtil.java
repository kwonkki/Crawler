package com.crawler.cebu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class CrawlerUtil {
	
	private final String WHITESPACE_UTF8 = "/u00a0";
	
	/** 单例模式 **/
	private CrawlerUtil() {

	}

	private static class CrawlerUtilInstanceHolder {
		private static CrawlerUtil Crawler_Util = new CrawlerUtil();
	}

	public static CrawlerUtil getInstance() {
		return CrawlerUtilInstanceHolder.Crawler_Util;
	}

	/**
	 * 控制台打印文件内容
	 * 
	 * @param filePath
	 *            文件路径
	 */
	public void printFile(String filePath) {
		FileReader fr = null;
		BufferedReader br = null;
		String s = null;
		try {
			fr = new FileReader(new File(filePath));
			br = new BufferedReader(fr);
			s = br.readLine();
			while (s != null) {
				System.out.println(s);
				s = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			this.free(br);
		}
	}

	/**
	 * 由response获取html字符串
	 * 
	 * @param response
	 * @return
	 */
	public String getHtmlByResponse(CloseableHttpResponse response) {
		String html = null;
		try {
			// 获得相应实体
			HttpEntity entity = response.getEntity();
			InputStream inputStream = entity.getContent();
			html = this.getStrByInputStream(inputStream);
			EntityUtils.consume(entity); // 清空资源
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			this.free(response);
		}
		return html;
	}

	/**
	 * 获取InputStream中的信息
	 * 
	 * @param inputStream
	 * @return
	 */
	public String getStrByInputStream(InputStream inputStream) {
		StringBuffer sb = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(inputStream));
			sb = new StringBuffer();
			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line.replace(WHITESPACE_UTF8, " ") + "\r\n"); // 逐行读取，回车换行
			}
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			this.free(br);
		}
		return sb.toString();
	}

	/**
	 * 根据URL获得所有的html信息
	 * 
	 * @param url
	 * @return
	 */
	public String getHtmlByUrl(String url) {
		String html = null;
		// 创建httpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpGet httpget = new HttpGet(url);// 以get方式请求该URL
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpget);// 得到responce对象
			int resStatu = response.getStatusLine().getStatusCode();// 返回码
			if (resStatu == HttpStatus.SC_OK) {// 200正常 其他就不对
				html = this.getHtmlByResponse(response);
			}
		} catch (Exception e) {
			System.out.println("访问[ " + url + " ]出现异常!");
			e.printStackTrace();
		} finally {
			this.free(response, httpClient);
		}
		return html;
	}

	/**
	 * 访问网页，保存为本地文件
	 * 
	 * @param url
	 *            url地址
	 * @param savePath
	 *            保存路径
	 */
	public void saveHtmlByUrl(String url, String savePath) {
		CloseableHttpClient httpClient = HttpClients.createDefault(); // 创建httpClient对象
		HttpGet httpget = new HttpGet(url); // 以get方式请求该URL
		CloseableHttpResponse response = null; // 响应

		try {
			response = httpClient.execute(httpget); // 得到responce对象
			this.saveHtmlByResponse(response, savePath);
		} catch (Exception e) {
			System.out.println("访问[ " + url + " ]出现异常!");
			e.printStackTrace();
		} finally {
			this.free(response, httpClient);
		}
	}



	/**
	 * 由HttpResponse 保存html到本地
	 * 
	 * @param response
	 *            HttpResponse
	 * @param savePath
	 *            保存路径
	 */
	public void saveHtmlByResponse(CloseableHttpResponse response, String savePath) {
		int resStatu = response.getStatusLine().getStatusCode(); // 返回码
		if (resStatu == HttpStatus.SC_OK) { // 200正常 其他就不对
			HttpEntity entity = response.getEntity(); // 获得相应实体
			BufferedReader br = null; // 缓冲输入流
			BufferedWriter bw = null; // 缓冲输出流
			if (entity != null) {
				try {
					entity = new BufferedHttpEntity(entity); // 缓存实体，可重用
					// 获取实体输入流
					br = new BufferedReader(new InputStreamReader(entity.getContent()));
					// 写出输入流的内容到输出流，保存到本地
					bw = new BufferedWriter(new FileWriter(new File(savePath)));

					String line = null;
					while ((line = br.readLine()) != null) {
						bw.write(line.replace(WHITESPACE_UTF8, " ") + "\r\n");	// 回车换行
					}
					EntityUtils.consume(entity);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					this.free(response);
					this.free(br, bw);
				}
			}
		}
	}
	
	// 测试函数，固定参数
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
		this.setHttpPostHeader(httpPost);
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
			this.saveHtmlByResponse(response, savePath);

		} catch (Exception e) {
			System.out.println("访问[ " + postUrl + " ]出现异常!");
			e.printStackTrace();
		} finally {
			this.free(response, httpClient);
		}
	}

	/**
	 * 设置HttpPost的请求头信息
	 */
	private void setHttpPostHeader(HttpPost httpPost) {
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
	 * 保存post提交之后重定向的网页到本地文件
	 * @param postUrl	post提交url
	 * @param formParams	post表单参数
	 * @param savePath	保存路径
	 */
	public void savePostResponseHtmlByParams(String postUrl, FormParams formParams, String savePath) {
		// 获取FormParams中的参数
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(formParams.getFormParams(), Consts.UTF_8);
		// 设置HttpPost文件头
		HttpPost httpPost = new HttpPost(postUrl);
		this.setHttpPostHeader(httpPost);
		// 设置实体
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

			System.out.println("response code : " + response.getStatusLine().getStatusCode());	// 状态码
			this.saveHtmlByResponse(response, savePath);	// 保存到本地文件
		} catch (Exception e) {
			System.out.println("访问[ " + postUrl + " ]出现异常!");
			e.printStackTrace();
		} finally {
			this.free(response, httpClient);
		}
	}

	/**
	 * 保存post提交之后重定向的网页到本地文件
	 * @param postUrl	post提交url
	 * @param formParams	post表单参数
	 */
	public String getPostResponseHtmlByParams(String postUrl, FormParams formParams) {
		// 获取FormParams中的参数
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(formParams.getFormParams(), Consts.UTF_8);
		// 设置HttpPost文件头
		HttpPost httpPost = new HttpPost(postUrl);
		this.setHttpPostHeader(httpPost);
		// 设置实体
		httpPost.setEntity(formEntity);

		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		String html = null;

		try {
			// 处理post之后的重定向
			httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
			response = httpClient.execute(httpPost);
			// 获取response中的信息
			html = this.getHtmlByResponse(response);
		} catch (Exception e) {
			System.out.println("访问[ " + postUrl + " ]出现异常!");
			e.printStackTrace();
		} finally {
			this.free(response, httpClient);
		}
		return html;
	}

	
	
	
	
	
	

	/**
	 * 释放资源
	 * 
	 * @param response
	 *            HttpResponse
	 * @param httpClient
	 *            HttpClient
	 */
	public void free(CloseableHttpResponse response, CloseableHttpClient httpClient) {
		free(response);
		free(httpClient);
	}

	/**
	 * 释放资源
	 * 
	 * @param httpClient
	 *            HttpClient
	 */
	public void free(CloseableHttpClient httpClient) {
		if (httpClient != null) {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 释放资源
	 * 
	 * @param response
	 *            HttpResponse
	 */
	public void free(CloseableHttpResponse response) {
		if (response != null) {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 释放资源
	 * 
	 * @param br
	 * @param bw
	 */
	public void free(BufferedReader br, BufferedWriter bw) {
		free(br);
		free(bw);
	}

	/**
	 * 释放资源
	 * 
	 * @param bw
	 *            BufferedWriter
	 */
	public void free(BufferedWriter bw) {
		try {
			if (bw != null)
				bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 释放资源
	 * 
	 * @param br
	 *            BufferedReader
	 */
	public void free(BufferedReader br) {
		try {
			if (br != null)
				br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
