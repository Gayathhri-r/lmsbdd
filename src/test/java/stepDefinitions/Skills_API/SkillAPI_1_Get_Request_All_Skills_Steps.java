package stepDefinitions.Skills_API;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.Assert;
import BaseClass.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;

public class SkillAPI_1_Get_Request_All_Skills_Steps extends BaseClass{

@Given("user sends the get request to get all the skill records")
public void user_sends_the_get_request_to_get_all_the_skill_records() {
    
	RestAssured.baseURI = "https://springboot-lms-userskill.herokuapp.com/";
	RestAssured.basePath ="/Skills";
	System.out.println("Success");
}

@When("user enters valid login data username and password with {string}")
public void user_enters_valid_login_data_username_and_password_with(String baseUrl) {
     
	HttpRequest = given().auth().basic(username, password).contentType("application/json");
	response = HttpRequest.request(Method.GET,baseUrl);
}

@Then("check response body should display all the skills")
public void check_response_body_should_display_all_the_skills() {
    
	String responseBody=response.getBody().asString();
	System.out.println("Response body is" +responseBody);
	
}

@Then("check Response displays status code {int} OK")
public void check_response_displays_status_code_ok(int statuscode) {
    
	int StatusCode = response.getStatusCode();
	System.out.println("Status Code is:"+StatusCode);
	Assert.assertEquals(StatusCode,statuscode);
	
}

@Then("check Response displays Status Line {string}")
public void check_response_displays_status_line(String statusline) {
   
	String StatusLine = response.getStatusLine();
	System.out.println("Status Line:"+StatusLine);
	Assert.assertEquals(StatusLine, statusline);
}

@When("user enters invalid login data username and password with {string}")
public void user_enters_invalid_login_data_username_and_password_with(String baseUrl) {
   
	HttpRequest = given().auth().basic("API", "GET").contentType("application/json");
	response = HttpRequest.request(Method.GET,baseUrl);				
				
} 
	
@Then("check validation schema {string}")
public void check_validation_schema(String schemapath) { 
	   
	System.out.println("==========SCHEMA VALIDATION===========");
					response
						.then()
						.assertThat()
						.body(matchesJsonSchemaInClasspath(schemapath));
	System.out.println("==========SCHEMA VALIDATION SUCCESSFULL===========");
		}
				
}


