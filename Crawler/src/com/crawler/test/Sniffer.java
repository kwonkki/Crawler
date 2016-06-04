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

public class Sniffer {
	private final static String WHITESPACE_UTF8 = "\u00a0";
	
	public static void main(String[] args) {  
		String url = "http://www.hbfgw.gov.cn/ywcs/dlddc/gzdt_1755/wj_1757/201605/t20160520_105192.shtml";
        String html = getHtmlByUrl(url);  
        if (html != null &&! "".equals(html)) {  
//System.out.println(html);
            Document doc = Jsoup.parse(html); 
            // ��ȡclassΪCustom_UnionStyle������div�ĵ�2��div���Ǵ�����ݵ�div
            Element targetDiv = doc.select("div.Custom_UnionStyle>div").get(1)
            		.select("div").get(0);
            // ����table�ĵ�1��
            Element table = targetDiv.select("table").get(0);  
            Elements trs = table.select("tr");		// ��ȡ table�е�����trԪ��
            for(int i = 0; i < trs.size(); i++) {	// ��������trԪ���е�����td
            	Element tr = trs.get(i);	
            	Elements tds = tr.select("td");
            	for(int j = 0; j < tds.size(); j++) {
            		Element td = tds.get(j);
            		String info = td.text().replace(WHITESPACE_UTF8, "");
            		System.out.print(info + "  ");
            	}
            	System.out.println();
            }
            
            // Ŀ����Ϣ1
            Elements ps = doc.select("div.Custom_UnionStyle>div").get(1).select("div>p");
//System.out.println("ps size: " + ps.size());
            Element p2 = ps.get(2);
            Elements spans2 = p2.select("span");
            for(int i = 0; i < spans2.size(); i++) {
            	Element span = spans2.get(i);
            	String info = span.text().replace(WHITESPACE_UTF8, "");
            	System.out.print(info);
            }
            System.out.println();
            System.out.println("target info 1: " + spans2.get(4).text());
            
            
            // Ŀ����Ϣ2
            Element p10 = ps.get(10);
            Elements spans10 = p10.select("span");
            for(int i = 0; i < spans10.size(); i++) {
            	Element span = spans10.get(i);
            	String info = span.text().replace(WHITESPACE_UTF8, "");
            	System.out.print(info);
            }
            System.out.println();
            System.out.print("target info 2: ");
            for(int i = 3; i <= 6; i++) {
            	Element span = spans10.get(i);
            	String info = span.text().replace(WHITESPACE_UTF8, "");
            	System.out.print(info);
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
        //����httpClient����  
        CloseableHttpClient httpClient = HttpClients.createDefault();
        
        HttpGet httpget = new HttpGet(url);//��get��ʽ�����URL  
        CloseableHttpResponse responce = null;
        try {  
            responce = httpClient.execute(httpget);//�õ�responce����  
            int resStatu = responce.getStatusLine().getStatusCode();//������  
            if (resStatu == HttpStatus.SC_OK) {//200����  �����Ͳ���  
                //�����Ӧʵ��  
                HttpEntity entity = responce.getEntity();  
                if (entity != null) {  
                    //html = EntityUtils.toString(entity);//���htmlԴ����  
                    html = EntityUtils.toString(entity, "utf-8");
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
