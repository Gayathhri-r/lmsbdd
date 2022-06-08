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



public class UserSkillMapGetAPI_GET_Request_Skills_AllUsers_Steps extends BaseClass{

	@Given("user sends the get request to get all the users by skills records")
	public void user_sends_the_get_request_to_get_all_the_users_by_skills_records() {
	   
		RestAssured.baseURI = "https://springboot-lms-userskill.herokuapp.com/";
		RestAssured.basePath ="/UserSkillsMap/{skillId}";
		System.out.println("Success");
		
	}
	
}

