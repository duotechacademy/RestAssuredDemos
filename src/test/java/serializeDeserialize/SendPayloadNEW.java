package serializeDeserialize;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.JsonPath;

public class SendPayloadNEW {
	
	
	@Test
	public void verifyJsonDetails() {
		RestAssured.baseURI = "http://localhost:8080";
		
		VideoGame pojo = new VideoGame(new Random().nextInt(1000), "Half-life", "2020-05-18", 100, "Sci-fi", "5.0");
		
		
		
		System.out.println(pojo);
		
		
		given().
				contentType("application/json").
				accept("application/json").
				body(pojo).
//				body(pojo, ObjectMapperType.JACKSON_2). //explicitly decalres which serializer/converter you want to use
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
