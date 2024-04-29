package api.endpoints;
import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//this class is created to perform crud(create,read,update,delete) requests to the user api
public class userendpoints {

	public static Response createUser(User payload){
		Response response=given()
		    .contentType(ContentType.JSON)
		    .accept(ContentType.JSON)
		    .body(payload)
		.when()
		     .post(routes.post_url);
		return response;
	}
		public static Response getUser(String username1){
			Response response=given()
			    .pathParam("username",username1) //username is a parameter mentioned in routes class in get_url variable
			.when()
			     .get(routes.get_url);
			return response;
		
	}
		public static Response updateUser(String username1,User payload){
			Response response=given()
			    .contentType(ContentType.JSON)
			    .accept(ContentType.JSON)
			    .pathParam("username",username1)
			    .body(payload)
			.when()
			     .put(routes.update_url);
			return response;
		
}
		public static Response deleteUser(String username1){
			Response response=given()
			    .pathParam("username",username1) //username is a parameter mentioned in routes class in get_url variable
			.when()
			     .delete(routes.delete_url);
			return response;
		
	}
}
