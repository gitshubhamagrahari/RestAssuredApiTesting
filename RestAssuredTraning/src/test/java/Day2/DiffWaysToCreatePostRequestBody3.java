package Day2;

import org.json.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
 
/*
 Different ways we create request body
—----------------------
1> Hashmap
2>Using org.json
3> using POJO(plain old java object)
4> Using external json file 
 */
public class DiffWaysToCreatePostRequestBody3 {

	//3> Post request body Using POJO(Plain old java object 
	
	@Test
	
	void testPostusingPOJO() {
		
		Pojo_PostRequest data =new Pojo_PostRequest();
		data.setName("Scott");
		data.setLocation("France");
		data.setPhone("1234567890");
		
		String coursesArr[] = {"C","C++"};
		
		data.setCourses(coursesArr);
		
		
		given()
		    .contentType("application/json")
		    .body(data) 
		
		.when()
		    .post("https://localhost:3000/students")
		 
		.then()
		   .statusCode(201)
		   .body("name",equalTo("Scott"))
		   .body("location",equalTo("France"))
		   .body("phone",equalTo("1234567890"))
		   .body("courses[0]",equalTo("C"))
		   .body("courses[1]",equalTo("C++"))
		   .header("Content-Type", "application/json; charset=utf-8")
		   .log().all();
		
		
		
		
	}
	
	//deleting student records
	@Test
	
	void testdelete()
	{
		given()
		
		.when()
		  .delete("http://localhost:3000/students/4")
		
		.then()
		   .statusCode(200);
		   
	}
	
	
	
	
	
	
	
	
	
	
}
