package com.crawler.cebu;

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

public class Main {
	private final static String URL = "https://book.cebupacificair.com/Search.aspx?culture=en-us";
	private final static String PostUrl = "https://book.cebupacificair.com/Search.aspx";
	private final static String ResponseUrl = "https://book.cebupacificair.com/Select.aspx";
	private final static String SAVE_PATH_Response = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/post_response.html";
	private final static String SAVE_PATH_Response_Select = "C:/Users/lihaijun/Documents/GitHub/Crawler/Data/post_response_select.html";
	
	
	public static void main(String[] args) {
		getResponseHtml();
	}

	public static void getResponseHtml() {
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		/*formParams.add(new BasicNameValuePair("__EVENTTARGET",""));
		formParams.add(new BasicNameValuePair("__EVENTARGUMENT",""));
		formParams.add(new BasicNameValuePair("__VIEWSTATE","//wEPDwUBMGRkZ0I3jKvfnhKfx57f3Wjr7p2WhvU=")); 
		formParams.add(new BasicNameValuePair("pageToken","" ));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView$AvailabilitySearchInputSearchView$RadioButtonMarketStructure","OneWay"));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView_AvailabilitySearchInputSearchVieworiginStation1","HKG"));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView$AvailabilitySearchInputSearchView$TextBoxMarketOrigin1","HKG"));
		formParams.add(new BasicNameValuePair("name_originStationContainercategory","HongKong+(HKG)"));
		formParams.add(new BasicNameValuePair("originStationContainercategory","HKG"));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView_AvailabilitySearchInputSearchViewdestinationStation1","MNL"));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView$AvailabilitySearchInputSearchView$TextBoxMarketDestination1","MNL"));
		formParams.add(new BasicNameValuePair("name_destinationStationContainercategory","Manila+(MNL)"));
		formParams.add(new BasicNameValuePair("destinationStationContainercategory","MNL"));
		formParams.add(new BasicNameValuePair("date_picker_1","2016-06-20"));
		formParams.add(new BasicNameValuePair("date_picker_1_AccCalendar","2016-06-20"));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListMarketDay1","20"));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListMarketMonth1","2016-06"));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView_AvailabilitySearchInputSearchVieworiginStation2",""));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView$AvailabilitySearchInputSearchView$TextBoxMarketOrigin2",""));
		formParams.add(new BasicNameValuePair("name_originStationContainercategory","From"));
		formParams.add(new BasicNameValuePair("originStationContainercategory",""));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView_AvailabilitySearchInputSearchViewdestinationStation2",""));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView$AvailabilitySearchInputSearchView$TextBoxMarketDestination2",""));
		formParams.add(new BasicNameValuePair("name_destinationStationContainercategory","To"));
		formParams.add(new BasicNameValuePair("destinationStationContainercategory",""));
		formParams.add(new BasicNameValuePair("date_picker_2","2016-06-20"));
		formParams.add(new BasicNameValuePair("date_picker_2_AccCalendar","2016-06-20"));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListMarketDay2","20"));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListMarketMonth2","2016-06"));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListPassengerType_ADT","1"));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListPassengerType_CHD","0"));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView$AvailabilitySearchInputSearchView$promoCodeID",""));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView$AvailabilitySearchInputSearchView$ButtonSubmit","Find+It!"));
		formParams.add(new BasicNameValuePair("MemberLoginView2LoginView$TextBoxUserID","EMAIL"));
		formParams.add(new BasicNameValuePair("MemberLoginView2LoginView$PasswordFieldPassword","")); 
		formParams.add(new BasicNameValuePair("MemberLoginView2LoginView$tboxConfirmation","" ));
		formParams.add(new BasicNameValuePair("MemberLoginView2LoginView$tboxHiddenUsername",""));*/
		
		formParams.add(new BasicNameValuePair("__EVENTTARGET", ""));                                                                           
		formParams.add(new BasicNameValuePair("__EVENTARGUMENT", ""));                                                                          
		formParams.add(new BasicNameValuePair("__VIEWSTATE", "/wEPDwUBMGRkZ0I3jKvfnhKfx57f3Wjr7p2WhvU="));                                      
		formParams.add(new BasicNameValuePair("pageToken", ""));                                                                                
		formParams.add(new BasicNameValuePair("ControlGroupSearchView$AvailabilitySearchInputSearchView$RadioButtonMarketStructure", "OneWay"));
		formParams.add(new BasicNameValuePair("ControlGroupSearchView_AvailabilitySearchInputSearchVieworiginStation1", "HKG"));                
		formParams.add(new BasicNameValuePair("ControlGroupSearchView$AvailabilitySearchInputSearchView$TextBoxMarketOrigin1", "HKG"));         
		formParams.add(new BasicNameValuePair("name_originStationContainercategory", "HongKong (HKG)"));                                        
		formParams.add(new BasicNameValuePair("originStationContainercategory", "HKG"));                                                       
		formParams.add(new BasicNameValuePair("ControlGroupSearchView_AvailabilitySearchInputSearchViewdestinationStation1", "MNL"));           
		formParams.add(new BasicNameValuePair("ControlGroupSearchView$AvailabilitySearchInputSearchView$TextBoxMarketDestination1", "MNL"));    
		formParams.add(new BasicNameValuePair("name_destinationStationContainercategory", "Manila (MNL)"));                                     
		formParams.add(new BasicNameValuePair("destinationStationContainercategory", "MNL"));                                                   
		formParams.add(new BasicNameValuePair("date_picker_1", "2016-06-20"));                                                                  
		formParams.add(new BasicNameValuePair("date_picker_1_AccCalendar", "2016-06-20"));                                                      
		formParams.add(new BasicNameValuePair("ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListMarketDay1", "20"));        
		formParams.add(new BasicNameValuePair("ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListMarketMonth1", "2016-06")); 
		formParams.add(new BasicNameValuePair("ControlGroupSearchView_AvailabilitySearchInputSearchVieworiginStation2", ""));                   
		formParams.add(new BasicNameValuePair("ControlGroupSearchView$AvailabilitySearchInputSearchView$TextBoxMarketOrigin2", ""));            
		formParams.add(new BasicNameValuePair("name_originStationContainercategory", "From"));                                                  
		formParams.add(new BasicNameValuePair("originStationContainercategory", ""));                                                           
		formParams.add(new BasicNameValuePair("ControlGroupSearchView_AvailabilitySearchInputSearchViewdestinationStation2", ""));              
		formParams.add(new BasicNameValuePair("ControlGroupSearchView$AvailabilitySearchInputSearchView$TextBoxMarketDestination2", ""));       
		formParams.add(new BasicNameValuePair("name_destinationStationContainercategory", "To"));                                               
		formParams.add(new BasicNameValuePair("destinationStationContainercategory", ""));                                                      
		formParams.add(new BasicNameValuePair("date_picker_2", "2016-06-20"));                                                                  
		formParams.add(new BasicNameValuePair("date_picker_2_AccCalendar", "2016-06-20"));                                                      
		formParams.add(new BasicNameValuePair("ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListMarketDay2", "20"));        
		formParams.add(new BasicNameValuePair("ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListMarketMonth2", "2016-06")); 
		formParams.add(new BasicNameValuePair("ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListPassengerType_ADT", "1"));  
		formParams.add(new BasicNameValuePair("ControlGroupSearchView$AvailabilitySearchInputSearchView$DropDownListPassengerType_CHD", "0"));  
		formParams.add(new BasicNameValuePair("ControlGroupSearchView$AvailabilitySearchInputSearchView$promoCodeID", ""));                     
		formParams.add(new BasicNameValuePair("ControlGroupSearchView$AvailabilitySearchInputSearchView$ButtonSubmit", "FIND IT!"));            
		formParams.add(new BasicNameValuePair("MemberLoginView2LoginView$TextBoxUserID", "EMAIL"));                                             
		formParams.add(new BasicNameValuePair("MemberLoginView2LoginView$PasswordFieldPassword", ""));                                          
		formParams.add(new BasicNameValuePair("MemberLoginView2LoginView$tboxConfirmation", ""));                                               
		formParams.add(new BasicNameValuePair("MemberLoginView2LoginView$tboxHiddenUsername", ""));                                             
		
		System.out.println("--------------------------------- form params ------------------------------------");
		for(NameValuePair nameValuePair : formParams) {
			System.out.println(nameValuePair.getName() + "=" + nameValuePair.getValue());
		}
		
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
		
		// 测试输出消息中的变量信息
		System.out.println("--------------------------------- form entity ------------------------------------");
		InputStream inputStream = null;
		try {
			inputStream = formEntity.getContent();
			System.out.println(CrawlerUtil.getStrByInputStream(inputStream));
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
		
		HttpPost httpPost = new HttpPost(PostUrl);
		
		httpPost.setHeader("Host", "book.cebupacificair.com");
		httpPost.setHeader("User-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.7 Safari/537.36");
		httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		httpPost.setHeader("Accept-Encoding", "gzip, deflate, br");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		httpPost.setHeader("Referer", "https://book.cebupacificair.com/Search.aspx?culture=en-us");
		//httpPost.setHeader("Content-Length", "2249");
		//httpPost.setHeader("Cookie", " SP.NET_SessionId=2wq3nv20iz3aex451t2rihu2; skysales=142270986.20480.0000; bid_cap9kylkxexvqbwfljqctlwjpvukqvde=9fac177a-da3e-4c3b-9c91-55d2fd523fda");
		//httpPost.setHeader("Connection", "keep-alive");
		
		httpPost.setEntity(formEntity);
		
		System.out.println("--------------------------------- executing request ------------------------------------");
		System.out.println(httpPost.getRequestLine());
		
		
		try {
			System.out.println("--------------------------------- executing request entity ------------------------------------");
			System.out.println(CrawlerUtil.getStrByInputStream(httpPost.getEntity().getContent()));
		} catch (UnsupportedOperationException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("--------------------------------- request headers ------------------------------------");
		Header[] headers1 = httpPost.getAllHeaders();
		for(Header header1 : headers1)
			System.out.println(header1.getName() + " : " + header1.getValue());
		
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		
		try {
			//httpClient =  HttpClients.createDefault();
			//response = httpClient.execute(httpPost);
			
			httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
			response = httpClient.execute(httpPost);
			
			System.out.println(CrawlerUtil.getStrByInputStream(response.getEntity().getContent()));
			
			//CrawlerUtil.saveHtmlByUrl(ResponseUrl, SAVE_PATH_Response_Select);
			
			System.out.println("--------------------------------- response headers ------------------------------------");
			Header[] headers = response.getAllHeaders();
			for(Header header : headers)
				System.out.println(header.getName() + " : " + header.getValue());
			
			System.out.println("--------------------------------- response header location ------------------------------------");
			Header[] locationHeaders = response.getHeaders("Location");
			System.out.println("location header length : " + locationHeaders.length);
			if(locationHeaders.length == 0)
				System.out.println("header location length is 0 ...");
			else 
				System.out.println(locationHeaders[0].getName() + " : " + locationHeaders[0].getValue());
			
			
			System.out.println("--------------------------------- response string ------------------------------------");
			System.out.println(response.toString());
			
			System.out.println("response code : " + response.getStatusLine().getStatusCode());
			CrawlerUtil.saveHtmlByHttpResponse(response, SAVE_PATH_Response);
			
			//CrawlerUtil.saveHtmlByUrl(ResponseUrl, SAVE_PATH_Response_Select);
			
		} catch (Exception e) {
			System.out.println("访问[ " + URL + " ]出现异常!");
			e.printStackTrace();
		} finally {
			CrawlerUtil.free(response, httpClient);
		}
	}
}
