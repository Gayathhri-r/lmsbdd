package stepDefinitions.Skills_API;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

import BaseClass.BaseClass;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.Method;


public class SkillAPI_2_Get_Request_Skills_Steps extends BaseClass {
	
	
	@Given("user enters the get request to get particular skill record with URL and endpoint URL")
	public void user_enters_the_get_request_to_get_particular_skill_record_with_url_and_endpoint_url() {
		RestAssured.baseURI = "https://springboot-lms-userskill.herokuapp.com/";
		RestAssured.basePath ="/Skills";
		System.out.println("Success");
	}

	@When("user sends the get request to get particular skill record with endpoint url {string}")
	public void user_sends_the_get_request_to_get_particular_skill_record_with_endpoint_url(String baseurl) {
		
		HttpRequest = given()
				.auth()
				.basic(username, password)
				.contentType("application/json");

		response = HttpRequest.request(Method.GET,baseurl );
	}
	
}
