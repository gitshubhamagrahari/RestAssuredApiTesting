package Day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
 
/*
 Different ways we create request body
—----------------------
1> Hashmap
2>Using org.json
3> using POJO(plain old java object)
4> Using external json file 
 */
public class DiffWaysToCreatePostRequestBody4 {

	//4> Post request body Using external json file
	
	@Test
	
	void testPostusingPOJO() throws FileNotFoundException {
		
		//open the file
		File f =new File(".\\body.json");
		//read the file
		FileReader fr =new FileReader(f);
		//get the json data=>extract the data into json format
		JSONTokener jt =new JSONTokener(fr);
		//extract the data into json format/object
		
		JSONObject data = new JSONObject(jt);
		
		
		given()
		    .contentType("application/json")
		    .body(data.toString()) 
		
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
