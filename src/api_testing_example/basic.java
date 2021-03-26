package api_testing_example;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;

import files.payload;

public class basic {

	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		//add place
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payload.AddPlace()).when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		//print response
		System.out.println(response);
		
		//get place_id in response
		JsonPath js = new JsonPath(response);
		String placeId = js.getString("place_id");
		System.out.println(placeId);
		
		//update place
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\n" + 
				"	\"place_id\":\""+placeId+"\",\n" + 
				"	\"address\":\"70 winter walk, USA\",\n" + 
				"	\"key\":\"qaclick123\"\n" + 
				"}")
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));

	}
}
