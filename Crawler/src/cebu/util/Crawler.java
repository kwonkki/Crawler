package cebu.util;

import java.io.*;
import java.net.*;
import java.util.*;
import org.apache.http.*;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import cebu.model.FormParams;

public class Crawler {
	
	private final String WHITESPACE_UTF8 = "/u00a0";
	
	/** 单例模式 **/
	private Crawler() {

	}

	private static class CrawlerInstanceHolder {
		private static Crawler Crawler = new Crawler();
	}

	public static Crawler getInstance() {
		return CrawlerInstanceHolder.Crawler;
	}

	

	/**
	 * 由response获取html字符串
	 * 
	 * @param response
	 * @return
	 */
	private String getHtmlByResponse(CloseableHttpResponse response) {
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
	private String getStrByInputStream(InputStream inputStream) {
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
	private void saveHtmlByResponse(CloseableHttpResponse response, String savePath) {
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
	
	/**
	 * 将html字符串保存到本地文件
	 * @param html html字符串
	 * @param savePath	本地保存路径
	 */
	public void saveHtmlToFile(String html, String savePath) {
		BufferedWriter bw = null; // 缓冲输出流
		// 写出输入流的内容到输出流，保存到本地
		try {
			bw = new BufferedWriter(new FileWriter(new File(savePath)));
			bw.write(html.replace(WHITESPACE_UTF8, " ") + "\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			this.free(bw);
		}
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
			/*
			List<Cookie> cookies = cookieStore.getCookies();
			for(Cookie cookie : cookies) {
				System.out.println(cookie.getName() + "=" + cookie.getValue());
			}*/
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
		String html = "";
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
			return radioValueGeneratedHtmls;
		
		for(String radioValue : radioValues) {
			String html = this.getHtmlByRadio(cookieStore, radioValue);
//System.out.println("radio html:" + html);
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
	 * 设置HttpGet的请求头信息
	 */
	private void setHttpGetHeader(HttpGet httpGet) {
		httpGet.setHeader("Host", "book.cebupacificair.com");
		httpGet.setHeader("User-agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0");
		httpGet.setHeader("Accept", "*/*");
		httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		httpGet.setHeader("Accept-Encoding", "gzip, deflate, br");
		httpGet.setHeader("X-Requested-With", "XMLHttpRequest");
		httpGet.setHeader("Referer", "https://book.cebupacificair.com/Select.aspx");
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

		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;

		try {
			// 处理post之后的重定向
			httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
			response = httpClient.execute(httpPost);

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
	 * 保存post提交之后重定向的网页到本地文件，传出cookieStore参数
	 * @param postUrl	post提交url
	 * @param formParams	post表单参数
	 * @param savePath	保存路径
	 * @param cookieStores 数组传址，传出cookieStore参数
	 */
	public void savePostResponseHtmlByParams(String postUrl, FormParams formParams, String savePath, CookieStore[] cookieStores) {
		// 获取FormParams中的参数
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(formParams.getFormParams(), Consts.UTF_8);
		// 设置HttpPost文件头
		HttpPost httpPost = new HttpPost(postUrl);
		this.setHttpPostHeader(httpPost);
		// 设置实体
		httpPost.setEntity(formEntity);

		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;

		try {
			// 处理post之后的重定向
			httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
			HttpClientContext context = HttpClientContext.create();
			response = httpClient.execute(httpPost, context);
			
			// 设置cookie
			cookieStores[0] = context.getCookieStore();
			
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
	 * 获取post提交之后重定向的网页字符串
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
	 * 获取post提交之后重定向的网页字符串，传出CookieStore参数
	 * @param postUrl	提交post的url
	 * @param formParams	表单变量
	 * @param cookieStores	传出CokieStore数组，数组传址方式
	 * @return
	 */
	public String getPostResponseHtmlByParams(String postUrl, FormParams formParams, CookieStore[] cookieStores) {
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
			HttpClientContext context = HttpClientContext.create();
			response = httpClient.execute(httpPost, context);
			
			// 设置cookie
			cookieStores[0] = context.getCookieStore();
			
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
	private void free(CloseableHttpResponse response, CloseableHttpClient httpClient) {
		free(response);
		free(httpClient);
	}

	/**
	 * 释放资源
	 * 
	 * @param httpClient
	 *            HttpClient
	 */
	private void free(CloseableHttpClient httpClient) {
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
	private void free(CloseableHttpResponse response) {
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
	private void free(BufferedReader br, BufferedWriter bw) {
		free(br);
		free(bw);
	}

	/**
	 * 释放资源
	 * 
	 * @param bw
	 *            BufferedWriter
	 */
	private void free(BufferedWriter bw) {
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
	private void free(BufferedReader br) {
		try {
			if (br != null)
				br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
