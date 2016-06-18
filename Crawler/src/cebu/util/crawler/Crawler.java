package cebu.util.crawler;

import java.io.*;
import org.apache.http.*;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.*;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cebu.model.FormParams;

public abstract class Crawler {
	
	protected static final String WHITESPACE_UTF8 = "/u00a0";
	protected final Logger logger = LoggerFactory.getLogger(FormParams.class);
	/**
	 * 由response获取html字符串
	 * 
	 * @param response
	 * @return
	 */
	public  String getHtmlByResponse(CloseableHttpResponse response) {
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
	public  String getStrByInputStream(InputStream inputStream) {
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
	public  String getHtmlByUrl(String url) {
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
	public  void saveHtmlByUrl(String url, String savePath) {
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
	public  void saveHtmlByResponse(CloseableHttpResponse response, String savePath) {
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
	
	/**
	 * 设置HttpPost的请求头信息
	 */
	public  abstract void setHttpPostHeader(HttpPost httpPost);
	
	/**
	 * 设置HttpGet的请求头信息
	 */
	public  abstract void setHttpGetHeader(HttpGet httpGet);
	
	/**
	 * 保存post提交之后重定向的网页到本地文件
	 * @param postUrl	post提交url
	 * @param formParams	post表单参数
	 * @param savePath	保存路径
	 */
	public  void savePostResponseHtmlByParams(String postUrl, FormParams formParams, String savePath) {
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
	public  void savePostResponseHtmlByParams(String postUrl, FormParams formParams, String savePath, CookieStore[] cookieStores) {
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
	public  String getPostResponseHtmlByParams(String postUrl, FormParams formParams) {
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
	public  String getPostResponseHtmlByParams(String postUrl, FormParams formParams, CookieStore[] cookieStores) {
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
	public  void free(CloseableHttpResponse response, CloseableHttpClient httpClient) {
		free(response);
		free(httpClient);
	}

	/**
	 * 释放资源
	 * 
	 * @param httpClient
	 *            HttpClient
	 */
	public  void free(CloseableHttpClient httpClient) {
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
	public  void free(CloseableHttpResponse response) {
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
	public  void free(BufferedReader br, BufferedWriter bw) {
		free(br);
		free(bw);
	}

	/**
	 * 释放资源
	 * 
	 * @param bw
	 *            BufferedWriter
	 */
	public  void free(BufferedWriter bw) {
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
	public  void free(BufferedReader br) {
		try {
			if (br != null)
				br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public final void log(String info) {
		logger.error(info);
	}

}
