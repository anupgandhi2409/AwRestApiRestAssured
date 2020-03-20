package com.awrestapi.commonservices.um;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTest {
	
	@Test
	public void jsonToMap() throws JsonParseException, JsonMappingException, IOException {
		
		InputStream inputStream = null;
		inputStream = this.getClass().getResourceAsStream("/users.json");
		
		ObjectMapper mapper = new ObjectMapper();
		
		LinkedHashMap<String, Object> users = mapper.readValue(inputStream, new TypeReference<LinkedHashMap<String,Object>>() {
		});
		
		System.out.println(users);
		
		
	}

}
