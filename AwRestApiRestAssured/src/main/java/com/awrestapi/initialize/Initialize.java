package com.awrestapi.initialize;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Initialize {
	
	public void init() throws IOException {
		
		Properties properties  = new Properties();
		InputStream is = null;
		is = this.getClass().getResourceAsStream("/config.properties");
		properties.load(is);
		
	}

}
