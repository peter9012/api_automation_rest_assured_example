package api_testing_example;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReUsableMethods;
import files.payload;

public class basic {

	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		//Add Place
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payload.AddPlace()).when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();	
		//Print Response
		System.out.println(response);	
		//Get place_id In Response
		JsonPath js = new JsonPath(response);
		String placeId = js.getString("place_id");
		System.out.println(placeId);
		
		//Update Place
		String newAddress = "60 spear stresst, USA";			
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\n" + 
				"	\"place_id\":\""+placeId+"\",\n" + 
				"	\"address\":\""+newAddress+"\",\n" + 
				"	\"key\":\"qaclick123\"\n" + 
				"}")
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//Get Place	
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id", placeId)
		.when().get("maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js_place = ReUsableMethods.rawToJason(getPlaceResponse);
		String actualAddress = js_place.getString("address");
		System.out.println(actualAddress);
		Assert.assertEquals(actualAddress, newAddress);

	}
}
