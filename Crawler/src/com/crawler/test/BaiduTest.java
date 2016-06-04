package com.crawler.test;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class BaiduTest {
	
	public static void main(String[] args) {  
        String html = getHtmlByUrl("http://www.baidu.com/");  
        if (html!=null&&!"".equals(html)) {  
            Document doc = Jsoup.parse(html); 
            // ��ȡ�ٶ���ҳ��idΪ"s_tab"��div�е�����a��ǩ
            Elements linksElements = doc.select("div#s_tab>a");  
            // Elements linksElements = doc.select("div#page>div#content>div#main>div.left>div#recommend>ul>li>a");  
            //���ϴ������˼�� ��idΪ��page����div����   idΪ��content����div����   idΪ��main����div����  
            //classΪ��left����div����   idΪ��recommend����div����ul����li����a��ǩ  
            for (Element ele:linksElements) {  
                String href = ele.attr("href");  
                String title = ele.text();  
                System.out.println(href+","+title);  
            }  
        }  
    }  
	
	/** 
     * ����URL������е�html��Ϣ 
     * @param url 
     * @return 
     */  
    public static String getHtmlByUrl(String url){  
        String html = null;  
        //HttpClient httpClient = new  DefaultHttpClient();//����httpClient����  
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);//��get��ʽ�����URL  
        CloseableHttpResponse responce = null;
        try {  
            responce = httpClient.execute(httpget);//�õ�responce����  
            int resStatu = responce.getStatusLine().getStatusCode();//������  
            if (resStatu==HttpStatus.SC_OK) {//200����  �����Ͳ���  
                //�����Ӧʵ��  
                HttpEntity entity = responce.getEntity();  
                if (entity!=null) {  
                    html = EntityUtils.toString(entity);//���htmlԴ����  
                }  
            }  
        } catch (Exception e) {  
            System.out.println("���ʡ�"+url+"�������쳣!");  
            e.printStackTrace();  
        } finally {  
        	if (responce != null) {
        		try {
					responce.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
            try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }  
        return html;  
    }  
}
