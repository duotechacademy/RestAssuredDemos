package io.duotech;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class APItests {

	static {
		
		RestAssured.baseURI = "http://3.6.24.244";
		//RestAssured.basePath = "/maps/api/place/add/json";
	}
	
	@Test
	public void verifyDeleteAPI() throws IOException {
		//Create a place
		//Retrieve the place id from the result
		//Delete the place
		//Delete the place one more time to verify the message is an error message
		
		
		
		
		String response = given().log().all().
				queryParam("key", "qaclick123").
				header("Content-Type", "application/json").
				body(Payloads.getJsonFileAsString("duotech.json")).
		when().	
				post("/maps/api/place/add/json").
				
		then().log().all().
				assertThat().
							statusCode(200).extract().response().asString();
		
		
		System.out.println(response);
		
		JsonPath jp = new JsonPath(response);
		
		//jp.prettyPeek();
		
		String placeId = jp.getString("place_id");
		
		given().log().all().
				queryParam("key", "qaclick123").
				header("Content-Type", "application/json").
				body("{\r\n" + 
					"    \"place_id\": \""+placeId+"\"\r\n" + 
					"}\r\n" + 
					"").
		when().		
				delete("/maps/api/place/delete/json").
		then().log().all().
				assertThat().	
							statusCode(200).
							body("status", equalTo("OK"));
		
		given().log().all().
					queryParam("key", "qaclick123").
					header("Content-Type", "application/json").
					body("{\r\n" + 
						"    \"place_id\": \""+placeId+"\"\r\n" + 
						"}\r\n" + 
						"").
		when().		
				delete("/maps/api/place/delete/json").
		then().log().all().
				assertThat().	
							statusCode(404).
							body("msg", equalTo("Delete operation failed, looks like the data doesn't exists"));
		
		
		
		
		
							
		
		
		
	}
	
	
	@Test
	public void verifyUpdateAPI() {
		
		//Create a Place -> POST
		
		JsonPath jp = given().log().all().
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
				"}\r\n" + 
				"").
		when().	
				post("/maps/api/place/add/json").
				
		then().log().all().
				assertThat().
							statusCode(200).extract().response().jsonPath();
				
		
		
		//Retrieve place ID using JsonPath
		String placeID = jp.getString("place_id");
		
		//Updating the address -> PUT
		String newAddress = "8609 Westwood Center Dr, Ste 110, Vienna, VA 22182";
		
		given().log().all().
				queryParam("key", "qaclick123").
				queryParam("place_id", placeID).
				header("Content-Type", "application/json").
				body("{\r\n" + 
						"\"place_id\":\""+placeID+"\",\r\n" + 
						"\"address\":\""+newAddress+"\",\r\n" + 
						"\"key\":\"qaclick123\"\r\n" + 
						"}").
		when().		
				put("/maps/api/place/update/json").
		then().
				assertThat().
							statusCode(200).
							body("msg", equalTo("Address successfully updated"));
				
		
		
		//Verifying the update by GET request and extracting the new address from the response -> GET
		
		given().
				queryParam("key", "qaclick123").
				queryParam("place_id", placeID).
		when().		
				get("/maps/api/place/get/json").
		then().		
				assertThat().
							statusCode(200).
							body("address", equalTo(newAddress));
		
				
		
		
		
	}
	
	

}
