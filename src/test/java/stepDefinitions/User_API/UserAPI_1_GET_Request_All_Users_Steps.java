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

public class UserAPI_1_GET_Request_All_Users_Steps extends BaseClass {
	
	@Given("user sends the get request to get all the users records")
	public void user_sends_the_get_request_to_get_all_the_users_records() {
		
		RestAssured.baseURI = "https://springboot-lms-userskill.herokuapp.com/";
		RestAssured.basePath ="/Users";
		System.out.println("Success");
		
	}

	@Then("check response body should display all the users")
	public void check_response_body_should_display_all_the_users() {
		
		String responseBody=response.getBody().asString();
		System.out.println("Response body is" +responseBody);
	    
	}

	@Then("Header should have content-type")
	public void header_should_have_content_type() {
		
		String ContentType = response.getContentType();
		System.out.println("Content Type:" +ContentType);
		Assert.assertEquals(ContentType, "application/json");
	    
	}

	}
	

