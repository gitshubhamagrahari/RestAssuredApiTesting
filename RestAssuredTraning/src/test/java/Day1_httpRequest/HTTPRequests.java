package Day1_httpRequest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

  

/*
  given(): content type ,set cookies, add auth , add param, set header info etc.

  when() : get, post,put ,delete

  then() : Validation status code, extract response , extract  header cookies and response body.....

*/

public class HTTPRequests {
	  int id ;
	 
	@Test 
	void getUsers() {
		
		given()
		
		.when()
		    .get("https://reqres.in/api/users?page=2")
		
		.then()
		    .statusCode(200)
		    .body("page",equalTo(2))
		    .log().all();
		    
		
	}
	
	@Test
	void createUser() {
		
		HashMap data =new HashMap();
		data.put("name","shubham");
		data.put("job","trainer");
		
		given()
		  .contentType("application/json")
		  .body(data)
		
		.when()
		   .post("https://reqres.in/api/users")
		
		.then()
		   .statusCode(201)
		   .log().all();
		
	}

	
	//updating the records
	@Test(priority=2)
void getUserId() {
		
		HashMap<String, String> data =new HashMap<>();
		data.put("name","shubham" );
		data.put("job","trainer");
		
		id=given()
		  .contentType("application/json")
		  .body(data)
		
		.when()
		   .post("https://reqres.in/api/users")
		   .jsonPath().getInt("id");
		
		System.out.println("Id is :"+ id);
		
	}
	
	
      @Test(priority=3, dependsOnMethods= {"getUserId"})
      void updateUser() {
	     HashMap data =new HashMap();
	     data.put("name","satyam");
	     data.put("job","trainer");
	
	    given()
			  .contentType("application/json")
			  .body(data)
			
			.when()
			   .put("https://reqres.in/api/users/"+id)
			
			   
			.then()
			   .statusCode(200)
			   .log().all();
	    
}
      
      @Test(priority =4)
      void deleteUser() {
    	  
    	  given()
    	  
    	  .when()
    	      .delete("https://reqres.in/api/users/"+id)
    	      
    	  .then()
    	      .statusCode(204)
    	      .log().all();
    	  
    	  
      }
}
