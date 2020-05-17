package io.duotech;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class RestAssuredDemo {
	
	
	// Send a POST request with given body and parameters
	//Verify the response code and response body
	//given()
	//when()
	//then()

	public static void main(String[] args) {
		
		RestAssured.baseURI = "http://3.6.24.244";
		
		
		given(). log().all().
		
				queryParam("key", "qaclick123").
				header("Content-Type", "application/json").
				body("{\r\n" + 
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
						"}").
		when().	log().all().	
				post("/maps/api/place/add/json").
		then(). log().all().
				assertThat().
							statusCode(200).
							body("scope", equalTo("APP")).and().body("status", equalTo("OK")).
							header("Server", "Apache/2.4.18 (Ubuntu)").and().header("Content-Length", "194").
							time(lessThan(2000L));
		
		


		
		

	}

}
