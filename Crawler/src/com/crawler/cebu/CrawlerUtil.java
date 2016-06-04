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
	 * ����̨��ӡ�ļ�����
	 * @param filePath	�ļ�·��
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
	 * ��response��ȡhtml�ַ���
	 * @param response
	 * @return
	 */
	public static String getHtmlByResponse(CloseableHttpResponse response) {
		StringBuffer sb = null;
		BufferedReader br = null;
		try {
			// �����Ӧʵ��
			HttpEntity entity = response.getEntity();
			br = new BufferedReader(new InputStreamReader(entity.getContent()));
			
			sb = new StringBuffer();
			String line = "";
			while((line = br.readLine()) != null) {
				sb.append(line + "\r\n");	// ���ж�ȡ���س�����
			}	
			EntityUtils.consume(entity);	// �����Դ
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
	 * ����URL������е�html��Ϣ
	 * @param url
	 * @return
	 */
	public static String getHtmlByUrl(String url) {
		String html = null;
		// ����httpClient����
		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpGet httpget = new HttpGet(url);// ��get��ʽ�����URL
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpget);// �õ�responce����
			int resStatu = response.getStatusLine().getStatusCode();// ������
			if (resStatu == HttpStatus.SC_OK) {// 200���� �����Ͳ���
				html = getHtmlByResponse(response);
			}
		} catch (Exception e) {
			System.out.println("����[ " + url + " ]�����쳣!");
			e.printStackTrace();
		} finally {
			free(response, httpClient);
		}
		return html;
	}

	
	/**
	 * ������ҳ������Ϊ�����ļ�
	 * @param url url��ַ
	 * @param savePath ����·��
	 */
	public static void saveHtmlByUrl(String url, String savePath) {
		CloseableHttpClient httpClient = HttpClients.createDefault(); // ����httpClient����
		HttpGet httpget = new HttpGet(url); // ��get��ʽ�����URL
		CloseableHttpResponse response = null; // ��Ӧ

		try {
			response = httpClient.execute(httpget); // �õ�responce����
			saveHtmlByHttpResponse(response, savePath);
		} catch (Exception e) {
			System.out.println("���ʡ�" + url + "�������쳣!");
			e.printStackTrace();
		} finally {
			free(response, httpClient);
		}
	}

	
	/**
	 * ��HttpResponse ����html������
	 * @param response	HttpResponse
	 * @param savePath	����·��
	 */
	public static void saveHtmlByHttpResponse(CloseableHttpResponse response, String savePath) {
		int resStatu = response.getStatusLine().getStatusCode(); // ������
		if (resStatu == HttpStatus.SC_OK) { // 200���� �����Ͳ���
			HttpEntity entity = response.getEntity(); // �����Ӧʵ��
			BufferedReader br = null; // ����������
			BufferedWriter bw = null; // ���������
			if (entity != null) {
				try {
					entity = new BufferedHttpEntity(entity); // ����ʵ�壬������
					// ��ȡʵ��������
					br = new BufferedReader(new InputStreamReader(entity.getContent()));
					// д�������������ݵ�����������浽����
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
	 * �ͷ���Դ
	 * @param response	HttpResponse
	 * @param httpClient	HttpClient
	 */
	public static void free(CloseableHttpResponse response, CloseableHttpClient httpClient) {
		free(response);
		free(httpClient);
	}

	/**
	 * �ͷ���Դ
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
	 * �ͷ���Դ
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
	 * �ͷ���Դ
	 * @param br
	 * @param bw
	 */
	public static void free(BufferedReader br, BufferedWriter bw) {
		free(br);
		free(bw);
	}

	
	/**
	 * �ͷ���Դ
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
	 * �ͷ���Դ
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
