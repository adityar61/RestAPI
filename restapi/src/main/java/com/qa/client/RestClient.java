package com.qa.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	//1. Get Method
	
	public void get(String url) throws ClientProtocolException, IOException
	{
		//This will create client Session
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		
		//This will hit GET URL
		CloseableHttpResponse HttpResponse = httpClient.execute(httpget);
		
		//To get Status Code
		int statusCode = HttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code--- " +statusCode);
		
		//To get JSON response
		String responseString = EntityUtils.toString(HttpResponse.getEntity(), "UTF-8");

		//ResponseString will get converted to JSON
		JSONObject responsejson = new JSONObject(responseString);
		System.out.println("Response JSON from API---- " +responsejson);
		
		//Get all Headers
		Header[] headerArray = HttpResponse.getAllHeaders();
		
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		
		for(Header header : headerArray)
		{
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers Array----- "+allHeaders);

	}

}
