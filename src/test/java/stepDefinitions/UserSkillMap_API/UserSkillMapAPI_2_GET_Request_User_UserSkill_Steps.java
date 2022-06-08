package stepDefinitions.UserSkillMap_API;

import static io.restassured.RestAssured.*;
import org.testng.Assert;
import BaseClass.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class UserSkillMapAPI_2_GET_Request_User_UserSkill_Steps extends BaseClass {

@When("user sends the get request to get all the user skill records")
public void user_sends_the_get_request_to_get_all_the_user_skill_records() {
	
	RestAssured.baseURI = "https://springboot-lms-userskill.herokuapp.com/";
	RestAssured.basePath ="/UserSkills/US97";
	System.out.println("Success");
	
	}
}
