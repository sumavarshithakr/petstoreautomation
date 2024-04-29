package api.endpoints;
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//this class is created to perform crud(create,read,update,delete) requests to the user api
public class userendpoints2 {
//method
	static ResourceBundle getURL(){
		ResourceBundle routes=ResourceBundle.getBundle("routes");//loads properties file
		return routes;
	}
	public static Response createUser(User payload){
		String posturl=getURL().getString("post_url");
		Response response=given()
		    .contentType(ContentType.JSON)
		    .accept(ContentType.JSON)
		    .body(payload)
		.when()
		     .post(posturl);
		return response;
	}
		public static Response getUser(String username1){
			String geturl=getURL().getString("get_url");

			Response response=given()
			    .pathParam("username",username1) //username is a parameter mentioned in routes class in get_url variable
			.when()
			     .get(geturl);
			return response;
		
	}
		public static Response updateUser(String username1,User payload){
			String updateurl=getURL().getString("update_url");

			Response response=given()
			    .contentType(ContentType.JSON)
			    .accept(ContentType.JSON)
			    .pathParam("username",username1)
			    .body(payload)
			.when()
			     .put(updateurl);
			return response;
		
}
		public static Response deleteUser(String username1){
			String deleteurl=getURL().getString("delete_url");

			Response response=given()
			    .pathParam("username",username1) //username is a parameter mentioned in routes class in get_url variable
			.when()
			     .delete(deleteurl);
			return response;
		
	}
}
