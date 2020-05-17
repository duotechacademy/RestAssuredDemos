package serializeDeserialize;

import static io.restassured.RestAssured.given;

import java.util.Random;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeserializeJSONtoPOJOVideoGame {
	
	
	@Test
	public void derserializeJson() {
	
	RestAssured.baseURI = "http://localhost:8080";
	
	
	
	
	
	
	
	
	Response response = given().
			pathParam("videoGameId", 363).
			accept("application/json").
	when().	log().all().	
			get("/app/videogames/{videoGameId}").
	then().	log().all().	
			assertThat().
				statusCode(200).extract().response();
	
	
	   VideoGame game = response.as(VideoGame.class);
	   
	   
	   System.out.println(game.toString());
	   
	   System.out.println(game.getId());
	   
	   System.out.println(game.getName());
	   
	   
	
	
	

}
	
}
