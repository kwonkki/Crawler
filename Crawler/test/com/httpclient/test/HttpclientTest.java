package com.httpclient.test;


import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

public class HttpclientTest {

	@Test
	public void givenPostRequest_whenConsumingUrlWhichRedirects_thenNotRedirected() 
	  throws ClientProtocolException, IOException {
	    HttpClient instance = HttpClientBuilder.create().build();
	    HttpResponse response = instance.execute(new HttpPost("http://t.co/I5YYd9tddw"));
	    System.out.println(response.getStatusLine().getStatusCode()); // equals(301));
	}

}
