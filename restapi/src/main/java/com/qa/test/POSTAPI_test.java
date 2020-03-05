package com.qa.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.com.Base.TestBase;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class POSTAPI_test extends TestBase{

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

	@Test
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException
	{
		 restClient = new RestClient();
		 
		 HashMap<String, String> headerMap = new HashMap<String, String>();
		 headerMap.put("Content-Type", "application/json");
		
		 //Jackson API
		 ObjectMapper mapper = new ObjectMapper();
		 Users users = new Users("Aditya","leader");
		 
		 //Java object to JSON File conversion
		 mapper.writeValue(new File("C:\\Users\\Aditya\\git\\RestAPI\\restapi\\src\\main\\java\\com\\qa\\data\\users.json"), users);
		 
		 //Object to JSON in string
		 String usersJSONString = mapper.writeValueAsString(users);
		 System.out.println(usersJSONString);
		 
		 HttpResponse = restClient.post(url, usersJSONString, headerMap);
		 
		 
		 //1. Status Code
		 
		 int statusCode = HttpResponse.getStatusLine().getStatusCode();
		 Assert.assertEquals(statusCode, testbase.RESPONSE_STATUS_CODE_201);
		 System.out.println("Status Code is - "+statusCode);
		 
		 //2. JSON String 
		 
		 String responseString = EntityUtils.toString(HttpResponse.getEntity(),"UTF-8"); 
		 JSONObject responseJSON = new JSONObject(responseString);
		 
		 System.out.println("The response of API is - "+responseJSON);
		 
		 //JSON to JAVA
		 Users usersResObj = mapper.readValue(responseString, Users.class);
		 System.out.println(usersResObj);
		 
		 Assert.assertTrue(users.getName().equals(usersResObj.getName()));
		 
		 Assert.assertTrue(users.getJob().equals(usersResObj.getJob()));

	}
	
	
}
