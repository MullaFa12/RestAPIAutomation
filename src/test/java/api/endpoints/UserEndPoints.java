package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.response.Response;
import io.restassured.http.ContentType;


public class UserEndPoints {
	
	public static Response createUser (User payload) {
		
		
		Response response= given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.body(payload)
		
		.when()
		.post(Routes.post_url);
		
		
		return response;
		
		
	}
	
      public static Response GetUser (String UserName) {
		
		
		Response response= given()
		.accept(ContentType.JSON)
		
		.pathParam("username", UserName )
		
		.when()
		.get(Routes.get_url);
		
		
		return response;
		
		
	}
     public static Response UpdateUser (String UserName,User payload) {
	
	
	Response response= given()
	.accept(ContentType.JSON)
	.contentType(ContentType.JSON)
	.pathParam("username", UserName )
	.body(payload)
	
	.when()
	.put(Routes.put_url);
	
	
	return response;
	
	
}
     
     public static Response DeleteUser (String UserName) {
    		
    		
    		Response response= given()
    		.accept(ContentType.JSON)
    
    		.pathParam("username", UserName )
    		
    		
    		.when()
    		.delete(Routes.delete_url);
    		
    		
    		return response;
    		
    		
    	}

}
