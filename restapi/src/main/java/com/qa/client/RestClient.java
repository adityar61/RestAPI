package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	//1. Get Method without Headers
	public CloseableHttpResponse get_without_Headers(String url) throws ClientProtocolException, IOException
	{
		//This will create client Session
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		
		//This will hit GET URL
		CloseableHttpResponse HttpResponse = httpClient.execute(httpget);
		
		return HttpResponse;
	}

	//2. Get Method with Headers

	public CloseableHttpResponse get_with_Headers(String url, HashMap<String, String> header_map) throws ClientProtocolException, IOException
	{
		//This will create client Session
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		
		for(Map.Entry<String,String> mEntry : header_map.entrySet())
		{
			httpget.addHeader(mEntry.getKey(), mEntry.getValue());
		}
		
		//This will hit GET URL
		CloseableHttpResponse HttpResponse = httpClient.execute(httpget);	
		return HttpResponse;
	}
	
	//Post Method - 
	public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
				HttpPost httppost = new HttpPost(url); //http Post request
				httppost.setEntity(new StringEntity(entityString)); //For defining Payload
				
				//For Headers
				for(Map.Entry<String,String> mEntry : headerMap.entrySet())
				{
					httppost.addHeader(mEntry.getKey(), mEntry.getValue());
				}
				
			CloseableHttpResponse HttpResponse = httpClient.execute(httppost);
			
			return HttpResponse;
	}
}
