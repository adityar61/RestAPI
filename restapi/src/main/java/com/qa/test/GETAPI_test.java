package com.qa.test;


import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.com.Base.TestBase;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GETAPI_test extends TestBase {

	TestBase testbase;
	String service_url;
	String api_url;
	String url ;
	RestClient restClient;
	CloseableHttpResponse HttpResponse;
	
	@BeforeMethod
	public void setUp() {
		testbase = new TestBase();
		service_url = prop.getProperty("URL");
		api_url = prop.getProperty("serviceURL");
		url = service_url + api_url;
	}
	
	@Test(priority =1)
	public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException
	{
		restClient = new RestClient();
		HttpResponse = restClient.get_without_Headers(url);
		
		//To get Status Code
		int statusCode = HttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code--- " +statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status Code is not 200");
		
		//To get JSON response
		String responseString = EntityUtils.toString(HttpResponse.getEntity(), "UTF-8");

		//ResponseString will get converted to JSON
		JSONObject responsejson = new JSONObject(responseString);
		System.out.println("Response JSON from API---- " +responsejson);
		
		//Single Value Assersion 
		//per_page:
		String perPageValue = TestUtil.getValueByJPath(responsejson, "/per_page");
		System.out.println("Value of per Page is- "+perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);
		
		//total
		String totalvalue = TestUtil.getValueByJPath(responsejson, "/total");
		System.out.println("Value of per Page is- "+totalvalue);
		Assert.assertEquals(Integer.parseInt(totalvalue), 12);
		
		//get value from JSON Array-
		
		String lname = TestUtil.getValueByJPath(responsejson, "/data[1]/last_name");
		String id = TestUtil.getValueByJPath(responsejson, "/data[1]/id");
		String avatar = TestUtil.getValueByJPath(responsejson, "/data[1]/avatar");
		String first_name = TestUtil.getValueByJPath(responsejson, "/data[1]/first_name");
		String email = TestUtil.getValueByJPath(responsejson, "/data[1]/email");

		System.out.println("Last Name - "+lname);
		System.out.println("ID - "+id);
		System.out.println("Avatar - "+avatar);
		System.out.println("First Name - "+first_name);		
		System.out.println("Email - "+email);
		//Get all Headers
		Header[] headerArray = HttpResponse.getAllHeaders();
		
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		
		for(Header header : headerArray)
		{
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers Array----- "+allHeaders);
	}
	
	@Test(priority = 2)
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException
	{
		restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		//headerMap.put("username", "uname");
		//headerMap.put("password", "pwd");
		
		HttpResponse = restClient.get_with_Headers(url, headerMap);
		
		//To get Status Code
		int statusCode = HttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code--- " +statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status Code is not 200");
		
		//To get JSON response
		String responseString = EntityUtils.toString(HttpResponse.getEntity(), "UTF-8");

		//ResponseString will get converted to JSON
		JSONObject responsejson = new JSONObject(responseString);
		System.out.println("Response JSON from API---- " +responsejson);
		
		//Single Value Assersion 
		//per_page:
		String perPageValue = TestUtil.getValueByJPath(responsejson, "/per_page");
		System.out.println("Value of per Page is- "+perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);
		
		//total
		String totalvalue = TestUtil.getValueByJPath(responsejson, "/total");
		System.out.println("Value of per Page is- "+totalvalue);
		Assert.assertEquals(Integer.parseInt(totalvalue), 12);
		
		//get value from JSON Array-
		
		String lname = TestUtil.getValueByJPath(responsejson, "/data[1]/last_name");
		String id = TestUtil.getValueByJPath(responsejson, "/data[1]/id");
		String avatar = TestUtil.getValueByJPath(responsejson, "/data[1]/avatar");
		String first_name = TestUtil.getValueByJPath(responsejson, "/data[1]/first_name");
		String email = TestUtil.getValueByJPath(responsejson, "/data[1]/email");

		System.out.println("Last Name - "+lname);
		System.out.println("ID - "+id);
		System.out.println("Avatar - "+avatar);
		System.out.println("First Name - "+first_name);		
		System.out.println("Email - "+email);
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
