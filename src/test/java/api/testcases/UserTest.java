package api.testcases;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {

	Faker faker;

	User userpayload;
	
	public static Logger logger;

	@BeforeClass
	public void generateTestData() {

		faker = new Faker();
		userpayload = new User();

		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().emailAddress());
		userpayload.setPassword(faker.internet().password());
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		
		
		//obtain logger

				logger = LogManager.getLogger("RestAssuredFramework");

	}

	
	@Test(priority=1)
	public void testCreateUser() {

		Response response = UserEndPoints.createUser(userpayload);

		// log response
		response.then().log().all();

		// validation

		Assert.assertEquals(response.getStatusCode(), 200);
		
		//log
		logger.info("Create User executed.");
	}

	@Test(priority=2)
	public void testGetUser() {

		Response response = UserEndPoints.GetUser(this.userpayload.getUsername());

		
		System.out.println("Get user data");
		// log response
		response.then().log().all();

		// validation

		Assert.assertEquals(response.getStatusCode(), 200);
		
		//log
		logger.info("Get User Data executed.");
	}

	@Test(priority=3)
	public void testUpdateUser() {
		userpayload.setFirstName(faker.name().firstName());
		Response response = UserEndPoints.UpdateUser(this.userpayload.getUsername(), userpayload);

		
		// log response
		response.then().log().all();

		// validation

		Assert.assertEquals(response.getStatusCode(), 200);
		

		System.out.println("After update  user data");
		
		Response responseafterupdate  = UserEndPoints.GetUser(this.userpayload.getUsername());
		
		responseafterupdate.then().log().all();
		
		//log
		logger.info("Update User executed.");
		
	}

	@Test (priority=4)
	public void testDeleteUser() {

		Response response = UserEndPoints.DeleteUser(this.userpayload.getUsername());

		System.out.println("Delete user data");
		
		// log response
		response.then().log().all();

		// validation

		Assert.assertEquals(response.getStatusCode(), 200);
		
		//log
		logger.info("Delete User executed.");
	}

}
