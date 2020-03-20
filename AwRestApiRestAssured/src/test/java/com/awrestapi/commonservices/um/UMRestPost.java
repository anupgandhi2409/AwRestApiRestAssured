package com.awrestapi.commonservices.um;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.awrestapi.initialize.Initialize;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class UMRestPost extends Initialize{
	
	String URI = "https://cmsvc01.ssdevrd.com/api/system/admins/addadminuser";
	
	@Test
	public void post() throws JsonParseException, JsonMappingException, IOException {
		
		String baseURI = RestAssured.baseURI = URI;
		Response httpResponse = null;
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("aw-tenant-code", "ldSJvUmNW6DMYNU5ZYmmjhUmxtCW2kHeltYg3BVcbZI=");
		headerMap.put("Accept", "application/SCIM+json;version=1");
		headerMap.put("Content-Type", "application/json");
		
		JSONObject requestParam = new JSONObject();
		
		//json to Map
		InputStream inputStream = null;
		inputStream = this.getClass().getResourceAsStream("/users.json");
		ObjectMapper mapper = new ObjectMapper();
		LinkedHashMap<String, Object> users = mapper.readValue(inputStream, new TypeReference<LinkedHashMap<String,Object>>() {
		});
		System.out.println(users);
		
		RequestSpecification httpRequest = RestAssured.given()
				.auth().basic("gandhi", "gandhi2")
				.headers(headerMap).params(users);
		
		httpResponse = httpRequest.post(baseURI);
		
		//System.out.println(httpResponse);
		
		System.out.println(httpResponse.getStatusLine());
		int statusCode = httpResponse.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		
		
		
		
		ResponseBody responseBody = httpResponse.body();
				
	}

}
