package api.test;
import com.github.javafaker.Faker;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import api.endpoints.userendpoints2;
import api.payload.User;
import io.restassured.response.Response;
public class usertests2 {
	
	Faker faker;
	User userpayload;
	public Logger logger;//created the variable for log

	@BeforeClass
   public void setup(){
	   faker=new Faker();
	   userpayload =new User();
	   userpayload.setId(faker.idNumber().hashCode());
	   userpayload.setUsername(faker.name().username());
       userpayload.setFirstName(faker.name().firstName());
	   userpayload.setLastName(faker.name().lastName());
	   userpayload.setEmail(faker.internet().safeEmailAddress());
       userpayload.setPassword(faker.internet().password(5,10));
       userpayload.setPhone(faker.phoneNumber().cellPhone());
       
     //logs
	    logger= LogManager.getLogger(this.getClass());
	    logger.debug("debugging..........");
	}
       @Test(priority=1)
       public void testPostUser(){
   		logger.info("************ Creating user***********");//logging some info to the log file
           Response response=userendpoints2.createUser(userpayload);     	 
//    	   System.out.println("after post");

    	   response.then().log().all();
    	   Assert.assertEquals(response.getStatusCode(),200);
   		  logger.info("**************user is created**********");

    	   }
       @Test(priority=2)
       public void getUserByName() {
   		logger.info("************ Reading user Info ***********");                                                                                                                                    

    	  Response response2= userendpoints2.getUser(this.userpayload.getUsername());
    	  response2.then().log().all();
    	 // response.statusCode();
   	   Assert.assertEquals(response2.getStatusCode(),200);//in assertion use getstatuscode();
		logger.info("************ user info is displayed ***********");

       }
       @Test(priority=3)
       public void updateUserByName() {
   		logger.info("************ Updating the user***********");

    	   userpayload.setFirstName(faker.name().firstName());
    	   userpayload.setLastName(faker.name().lastName());
    	   userpayload.setEmail(faker.internet().safeEmailAddress());
    	   Response response= userendpoints2.updateUser(this.userpayload.getUsername(),userpayload);
//     	  System.out.println("unupdated body");
           response.then().log().body();
     	  //checking data after update
    	  Response response1= userendpoints2.getUser(this.userpayload.getUsername());
//    	  System.out.println("updated body");
    	  response1.then().log().body();
   	   Assert.assertEquals(response1.getStatusCode(),200);
		logger.info("************ user  updated ***********");

    	  
}
       @Test(priority=4)
       public void testdeleteUserByName() {
   		logger.info("************Deleting user***********");

    	   Response response3=userendpoints2.deleteUser(this.userpayload.getUsername());
       	   Assert.assertEquals(response3.getStatusCode(),200);
   		logger.info("************ user deleted***********");


}}
