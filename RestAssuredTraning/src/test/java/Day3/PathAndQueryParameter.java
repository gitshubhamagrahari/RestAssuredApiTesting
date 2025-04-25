package Day3;

import org.json.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*; 



public class PathAndQueryParameter {
//url : https://reques.in/api/users?page=2&id=5
	@Test
	 void testQueryAndPathParameter() {
		
		given()
		   .pathParam("mypath","users")  //path parameter
		   .queryParam("page","2")  //query parameter1
		   .queryParam("id","5")  //query parameter2
		.when()
		    .get("https://reques.in/api/{mypath}?")
		.then()
		     .statusCode(200)
		     .log().all();
	}
	
	
}
