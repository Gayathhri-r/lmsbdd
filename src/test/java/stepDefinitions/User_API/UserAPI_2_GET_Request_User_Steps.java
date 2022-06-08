package stepDefinitions.User_API;

import static io.restassured.RestAssured.given;

import java.io.File;
import org.testng.Assert;

import BaseClass.BaseClass;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class UserAPI_2_GET_Request_User_Steps extends BaseClass {

	@Given("user sends the get request to get a particular user record")
	public void user_sends_the_get_request_to_get_a_particular_user_record() {
		
		RestAssured.baseURI = "https://springboot-lms-userskill.herokuapp.com/";
		RestAssured.basePath ="/Users";
		System.out.println("Success");
		
	}
	
	@When("user sends the get request to get particular user record with endpoint url {string}")
	public void user_sends_the_get_request_to_get_particular_user_record_with_endpoint_url(String baseurl) {
		
		HttpRequest = given()
				.auth()
				.basic(username, password)
				.contentType("application/json");

		response = HttpRequest.request(Method.GET,baseurl );
	}

	@Then("check Content type")
	public void check_content_type() {
	  
		String ContentType = response.getContentType();
		System.out.println("Content Type:" +ContentType);
		Assert.assertEquals(ContentType, "application/json");
	}
}