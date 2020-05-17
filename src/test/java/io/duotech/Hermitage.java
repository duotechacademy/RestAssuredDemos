package io.duotech;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Hermitage {
	
	
	@Test
	public void verifyJsonDetails() {
		RestAssured.baseURI = "https://maps.googleapis.com";

		JsonPath jsonPath = given().
				queryParam("input", "Hermitage Russia").
				queryParam("inputtype", "textquery").
				queryParam("fields", "photos,formatted_address,name,rating,opening_hours,geometry").
				queryParam("key", "AIzaSyCW1pMbqWsBrmZaZAgUQPwPuv7EOBcQ-Qc").
				queryParam("language", "en").
		when().	log().all().	
				get("/maps/api/place/findplacefromtext/json").
		then().	log().all().	
				assertThat().
							statusCode(200).extract().response().jsonPath();
		
		
		//Verify the number of fields
		
		int noOffields = jsonPath.getInt("candidates[0].size()");
		
		assertTrue(noOffields<=6);
		
		Double expectedLatitude = 59.93983;
		
		
		Double result = jsonPath.getDouble("candidates[0].geometry.location.lat");
		
		assertEquals(result, expectedLatitude);
		
		
		
		
		
		
	}

}
