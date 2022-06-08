package stepDefinitions.UserSkillMapGet_API;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.Assert;
import BaseClass.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class UserSkillMapGetAPI_GET_Request_User_All_Skills_Steps extends BaseClass {

	@Given("user sends the get request to get an user with all skills records")
	public void user_sends_the_get_request_to_get_an_user_with_all_skills_records() {
	    
		RestAssured.baseURI = "https://springboot-lms-userskill.herokuapp.com/";
		RestAssured.basePath ="/UserSkillsMap/{userId}";
		System.out.println("Success");
	}
	
}


