package com.awrestapi.commonservices.um;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class UMRestGet {
	
	String URI = "https://cmsvc01.ssdevrd.com/api/system/admins/128";
	
	@Test
	public void get() {
		
		String baseURI = RestAssured.baseURI = URI;
		Response httpResponse = null;
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("aw-tenant-code", "ldSJvUmNW6DMYNU5ZYmmjhUmxtCW2kHeltYg3BVcbZI=");
		headerMap.put("Accept", "application/SCIM+json;version=1");
		headerMap.put("Content-Type", "application/json");
			
		
		RequestSpecification httpRequest = RestAssured.given()
				.auth().basic("gandhi", "gandhi2")
				.headers(headerMap);
				
		httpResponse = httpRequest.get(baseURI);
			
		ResponseBody responseBody = httpResponse.body();
		
		int statusCode = httpResponse.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		String statusLine = httpResponse.getStatusLine();
		System.out.println(statusLine);
		Cookies c = httpResponse.detailedCookies();
		
		//response json parse
		JsonPath jsonPath = responseBody.jsonPath();
		String userName = jsonPath.get("FirstName");
		System.out.println(userName);
		
		int value = jsonPath.get("Id.Value");
		System.out.println(value);
		
		ArrayList<LinkedHashMap> roles = jsonPath.get("Roles");
		
		for (LinkedHashMap object : roles) {
			System.out.println(object.get("Id"));
			System.out.println(object.get("Uuid"));
		}
	}
}
