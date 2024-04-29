package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.userendpoints;
import api.payload.User;
import api.utilities.dataproviders;
import io.restassured.response.Response;

public class DDtests {
	@Test(priority=1,dataProvider="Data1",dataProviderClass=dataproviders.class)
    public void testpostuser(String id,String username,String firstName,String lastName,String email,String password,String phone) {
		User userpayload=new User();
		   userpayload.setId(Integer.parseInt(id));
		   userpayload.setUsername(username);
	       userpayload.setFirstName(firstName);
		   userpayload.setLastName(lastName);
		   userpayload.setEmail(email);
	       userpayload.setPassword(password);
	       userpayload.setPhone(phone);
	       
	       Response response=userendpoints.createUser(userpayload);     	 
//    	   response.then().log().all();
//    	   Assert.assertEquals(response.getStatusCode(),200);
	}
	@Test(priority=2,dataProvider="UserNames",dataProviderClass=dataproviders.class)
    public void testdeleteuser(String username) {
		Response response=userendpoints.deleteUser(username);
//    	   Assert.assertEquals(response.getStatusCode(),200);

	}
}
