package com.newdemo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ParseConfig {
	
	public static Properties readConfigInfo() {
		
		Properties properties = new Properties();
		try {
			InputStream in = ParseConfig.class.getResourceAsStream("/properties.properties");
			properties.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return properties;
	}

}
