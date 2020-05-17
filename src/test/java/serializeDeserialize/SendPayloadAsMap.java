package serializeDeserialize;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class SendPayloadAsMap {
	
	
	@Test
	public void verifyJsonDetails() {
		RestAssured.baseURI = "http://localhost:8080";
		
		Map <String,Object> mapObject = new LinkedHashMap<>();
		mapObject.put("id", new Random().nextInt(1000));
		mapObject.put("name", "Zelda");
		mapObject.put("releaseDate", "2020-05-16");
		mapObject.put("reviewScore", 80);
		mapObject.put("category", "Adventure");
		mapObject.put("rating", "4.5");
		
		
		
		
		
		given().
				contentType("application/json").
				accept("application/json").
				body(mapObject).
		when().	log().all().	
				post("/app/videogames").
		then().	log().all().	
				assertThat().
					statusCode(200);

		
		
		
		
		
		
		
//		given().
//				pathParam("videoGameId", 12).
//				accept("application/json").
//		when().	log().all().	
//				get("/app/videogames/{videoGameId}").
//		then().	log().all().	
//				assertThat().
//							statusCode(200);

}
	
}
