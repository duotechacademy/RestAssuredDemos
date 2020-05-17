package io.duotech;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Payloads {
	
	static String addDuotechBody = "{\r\n" + 
			"  \"location\": {\r\n" + 
			"    \"lat\": -77.2273128,\r\n" + 
			"    \"lng\": 38.9151944\r\n" + 
			"  },\r\n" + 
			"  \"accuracy\": 50,\r\n" + 
			"  \"name\": \"Duotech Academy\",\r\n" + 
			"  \"phone_number\":  \"(571) 295-4555\",\r\n" + 
			"  \"address\": \"8133 Leesburg Pike STE 300, Vienna, VA 22182\",\r\n" + 
			"  \"types\": [\r\n" + 
			"    \"academy\",\r\n" + 
			"    \"bootcamp\"\r\n" + 
			"  ],\r\n" + 
			"  \"website\": \"duotech.io\",\r\n" + 
			"  \"language\": \"English\"\r\n" + 
			"}\r\n" + 
			"";
	
	public static String addPlace(String name, String phone, String address) {
		return "{\r\n" + 
				"  \"location\": {\r\n" + 
				"    \"lat\": -77.2273128,\r\n" + 
				"    \"lng\": 38.9151944\r\n" + 
				"  },\r\n" + 
				"  \"accuracy\": 50,\r\n" + 
				"  \"name\": \""+name+"\",\r\n" + 
				"  \"phone_number\":  \""+phone+",\r\n" + 
				"  \"address\": \""+address+"\",\r\n" + 
				"  \"types\": [\r\n" + 
				"    \"academy\",\r\n" + 
				"    \"bootcamp\"\r\n" + 
				"  ],\r\n" + 
				"  \"website\": \"duotech.io\",\r\n" + 
				"  \"language\": \"English\"\r\n" + 
				"}\r\n" + 
				"";
	}
	
	public static String getJsonFileAsString(String path) throws IOException {
		
		return new String (Files.readAllBytes(Paths.get(path)));
	}

}
