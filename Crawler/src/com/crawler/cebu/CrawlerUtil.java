package com.crawler.cebu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class CrawlerUtil {

	/**
	 * 控制台打印文件内容
	 * @param filePath	文件路径
	 */
	public static void printFile(String filePath) {
		FileReader fr = null;
		BufferedReader br = null;
		String s = null;
		try {
			fr = new FileReader(new File(filePath));
			br = new BufferedReader(fr);
			s = br.readLine();
			while(s != null) {
				System.out.println(s);
				s = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			free(br);
		}
	}
	
	

	/**
	 * 由response获取html字符串
	 * @param response
	 * @return
	 */
	public static String getHtmlByResponse(CloseableHttpResponse response) {
		StringBuffer sb = null;
		BufferedReader br = null;
		try {
			// 获得相应实体
			HttpEntity entity = response.getEntity();
			br = new BufferedReader(new InputStreamReader(entity.getContent()));
			
			sb = new StringBuffer();
			String line = "";
			while((line = br.readLine()) != null) {
				sb.append(line + "\r\n");	// 逐行读取，回车换行
			}	
			EntityUtils.consume(entity);	// 清空资源
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			free(response);
			free(br);
		}
		return sb.toString();
	}
	
	

	/**
	 * 根据URL获得所有的html信息
	 * @param url
	 * @return
	 */
	public static String getHtmlByUrl(String url) {
		String html = null;
		// 创建httpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpGet httpget = new HttpGet(url);// 以get方式请求该URL
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpget);// 得到responce对象
			int resStatu = response.getStatusLine().getStatusCode();// 返回码
			if (resStatu == HttpStatus.SC_OK) {// 200正常 其他就不对
				html = getHtmlByResponse(response);
			}
		} catch (Exception e) {
			System.out.println("访问[ " + url + " ]出现异常!");
			e.printStackTrace();
		} finally {
			free(response, httpClient);
		}
		return html;
	}

	
	/**
	 * 访问网页，保存为本地文件
	 * @param url url地址
	 * @param savePath 保存路径
	 */
	public static void saveHtmlByUrl(String url, String savePath) {
		CloseableHttpClient httpClient = HttpClients.createDefault(); // 创建httpClient对象
		HttpGet httpget = new HttpGet(url); // 以get方式请求该URL
		CloseableHttpResponse response = null; // 响应

		try {
			response = httpClient.execute(httpget); // 得到responce对象
			saveHtmlByHttpResponse(response, savePath);
		} catch (Exception e) {
			System.out.println("访问【" + url + "】出现异常!");
			e.printStackTrace();
		} finally {
			free(response, httpClient);
		}
	}

	
	/**
	 * 由HttpResponse 保存html到本地
	 * @param response	HttpResponse
	 * @param savePath	保存路径
	 */
	public static void saveHtmlByHttpResponse(CloseableHttpResponse response, String savePath) {
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
					int inByte;
					while ((inByte = br.read()) != -1) {
						bw.write(inByte);
					}
					EntityUtils.consume(entity);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					free(response);
					free(br, bw);
				}
			}
		}
	}
	
	
	/**
	 * 释放资源
	 * @param response	HttpResponse
	 * @param httpClient	HttpClient
	 */
	public static void free(CloseableHttpResponse response, CloseableHttpClient httpClient) {
		free(response);
		free(httpClient);
	}

	/**
	 * 释放资源
	 * @param httpClient	HttpClient
	 */
	public static void free(CloseableHttpClient httpClient) {
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
	 * @param response	HttpResponse
	 */
	public static void free(CloseableHttpResponse response) {
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
	 * @param br
	 * @param bw
	 */
	public static void free(BufferedReader br, BufferedWriter bw) {
		free(br);
		free(bw);
	}

	
	/**
	 * 释放资源
	 * @param bw	BufferedWriter
	 */
	public static void free(BufferedWriter bw) {
		try {
			if (bw != null)
				bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 释放资源
	 * @param br	BufferedReader
	 */
	public static void free(BufferedReader br) {
		try {
			if (br != null)
				br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}












}
