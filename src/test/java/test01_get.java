import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class test01_get {
	@Test
	void test() {
		Response response=get("https://reqres.in/api/users?page=2");
		System.out.println(response.asString());
		System.out.println(response.getBody().asString());
        System.out.println(response.getStatusCode());
        int statuscode=response.getStatusCode();
        Assert.assertEquals(statuscode, 201);

	}
	@Test
	void test2() {
      given().get("https://reqres.in/api/users?page=2").then().statusCode(200)
      .body("data.id[0]",equalTo(7));
}
	private ResponseAwareMatcher<Response> equalTo(int i) {
		// TODO Auto-generated method stub
		return null;
	}}
