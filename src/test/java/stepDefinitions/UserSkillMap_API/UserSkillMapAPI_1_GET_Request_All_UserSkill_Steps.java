package stepDefinitions.UserSkillMap_API;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;
import BaseClass.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;
public class UserSkillMapAPI_1_GET_Request_All_UserSkill_Steps extends BaseClass {

@Given("user sends the get request to get all the user skill records with URL and endpoint URL")
public void user_sends_the_get_request_to_get_all_the_user_skill_records_with_url_and_endpoint_url() {
	
	RestAssured.baseURI = "https://springboot-lms-userskill.herokuapp.com/";
	RestAssured.basePath ="/UserSkills";
	System.out.println("Success");
}

}
