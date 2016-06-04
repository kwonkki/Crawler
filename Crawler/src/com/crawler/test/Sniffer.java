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
            // 获取class为Custom_UnionStyle的所有div的第2个div，是存放数据的div
            Element targetDiv = doc.select("div.Custom_UnionStyle>div").get(1)
            		.select("div").get(0);
            // 所有table的第1个
            Element table = targetDiv.select("table").get(0);  
            Elements trs = table.select("tr");		// 获取 table中的所有tr元素
            for(int i = 0; i < trs.size(); i++) {	// 遍历所有tr元素中的所有td
            	Element tr = trs.get(i);	
            	Elements tds = tr.select("td");
            	for(int j = 0; j < tds.size(); j++) {
            		Element td = tds.get(j);
            		String info = td.text().replace(WHITESPACE_UTF8, "");
            		System.out.print(info + "  ");
            	}
            	System.out.println();
            }
            
            // 目标信息1
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
            
            
            // 目标信息2
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
     * 根据URL获得所有的html信息 
     * @param url 
     * @return 
     */  
    public static String getHtmlByUrl(String url){  
        String html = null;  
        //创建httpClient对象  
        CloseableHttpClient httpClient = HttpClients.createDefault();
        
        HttpGet httpget = new HttpGet(url);//以get方式请求该URL  
        CloseableHttpResponse responce = null;
        try {  
            responce = httpClient.execute(httpget);//得到responce对象  
            int resStatu = responce.getStatusLine().getStatusCode();//返回码  
            if (resStatu == HttpStatus.SC_OK) {//200正常  其他就不对  
                //获得相应实体  
                HttpEntity entity = responce.getEntity();  
                if (entity != null) {  
                    //html = EntityUtils.toString(entity);//获得html源代码  
                    html = EntityUtils.toString(entity, "utf-8");
                }  
            }  
        } catch (Exception e) {  
            System.out.println("访问【"+url+"】出现异常!");  
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
