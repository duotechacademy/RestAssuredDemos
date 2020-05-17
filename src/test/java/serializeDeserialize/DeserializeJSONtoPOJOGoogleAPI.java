package serializeDeserialize;

import static io.restassured.RestAssured.given;

import java.util.Random;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import placePojos.Place;

public class DeserializeJSONtoPOJOGoogleAPI {
	
	
	@Test
	public void derserializeJson() {
	
	RestAssured.baseURI = "https://maps.googleapis.com";
	
	
	
	
	
	
	
	
	Response response = given().
			queryParam("input", "Hermitage Russia").
			queryParam("inputtype", "textquery").
			queryParam("fields", "photos,formatted_address,name,rating,opening_hours,geometry").
			queryParam("key", "AIzaSyCW1pMbqWsBrmZaZAgUQPwPuv7EOBcQ-Qc").
			
	when().	log().all().	
			get("/maps/api/place/findplacefromtext/json").
	then().	log().all().	
			assertThat().
				statusCode(200).extract().response();
	
	
	Place hermitage = response.as(Place.class);
	
	System.out.println(hermitage.getCandidates().get(0).getFormattedAddress());
	
	
	  
	   
	   
	
	
	

}
	
}
