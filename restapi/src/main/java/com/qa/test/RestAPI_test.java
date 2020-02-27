package com.qa.test;


import java.io.IOException;
import java.util.Properties;

import org.apache.http.client.ClientProtocolException;
import org.com.Base.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.client.RestClient;

public class RestAPI_test extends TestBase {

	TestBase testbase;
	String service_url;
	String api_url;
	String url ;
	RestClient restClient;
	
	@BeforeMethod
	public void setUp() {
		testbase = new TestBase();
		service_url = prop.getProperty("URL");
		api_url = prop.getProperty("serviceURL");
		url = service_url + api_url;
	}
	
	@Test
	public void getAPITest() throws ClientProtocolException, IOException
	{
		restClient = new RestClient();
		restClient.get(url);
	}
	
}
