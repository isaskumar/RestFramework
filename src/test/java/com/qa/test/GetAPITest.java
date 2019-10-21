package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;


public class GetAPITest extends TestBase {
	TestBase testBase;
	String baseUrl;
	String resources;
	String url;
	RestClient restClient; 
	CloseableHttpResponse closeableHttpResponse;
	
	@BeforeTest
	public void setUp() throws ClientProtocolException, IOException {
		testBase = new TestBase();
		 baseUrl = prop.getProperty("URL");
		 resources = prop.getProperty("serviceURL");
		
		 url = baseUrl + resources;	
	}
	
	
	@Test
	public void getTest() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		closeableHttpResponse = restClient.get(url);
		// 2. Status Code
				int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
				System.out.println("statusCode---"+statusCode);
				
				Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "status code is not 200");
				
				//3.Json String 
				String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
				JSONObject responseJson = new JSONObject(responseString);
				System.out.println("response from the API---"+responseJson);
				
				
				//4.All Headers
				Header[] headerArray = closeableHttpResponse.getAllHeaders();
				
				HashMap<String, String> allHeaders = new HashMap<String, String>();
				for(Header header : headerArray) {
					allHeaders.put(header.getName(), header.getValue());
				}
				System.out.println("Header Array ---"+allHeaders);
	}

}
