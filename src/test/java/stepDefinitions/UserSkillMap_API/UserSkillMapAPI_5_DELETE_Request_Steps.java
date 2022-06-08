package stepDefinitions.UserSkillMap_API;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
import java.io.File;

import org.json.simple.JSONObject;
import org.testng.Assert;

import BaseClass.BaseClass;
import Utilities.ExcelUtils;
import io.cucumber.java.en.*;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class UserSkillMapAPI_5_DELETE_Request_Steps extends BaseClass {
public String userskillID;
	
	ArrayList <Response>responseArrList = new ArrayList<Response>();
	ArrayList <String>userskillIdArrList = new ArrayList<String>();
	String data[][] = null;
	
	@Given("User enters the request body to delete user skill map")
	public void user_enters_the_request_body_to_delete_user_skill_map() throws Exception {
		
		HttpRequest = given().auth().basic(username, password).contentType("application/json").log().all();// request
		
	}

	@When("User sends the request body to delete userskill map API")
	public void user_sends_the_request_body_to_delete_userskill_map_api() throws Exception {
	    
		rownum = ExcelUtils.getRowCount(userskillExcelpath,"DELETE_USERSKILLMAP_API");
		colnum = ExcelUtils.getCellCount(userskillExcelpath, "DELETE_USERSKILLMAP_API", 1);
		System.out.println(rownum);
		System.out.println(colnum);
		String userSkillAPI[][]  = new String[rownum][colnum];
		
		for (int i = 1; i <= rownum; i++) 
		{
			for(int j=0; j<colnum; j++) {
				
				userSkillAPI[i-1][j] = ExcelUtils.getCellData(userskillExcelpath, "DELETE_USERSKILLMAP_API", i, j); //i =1, j=0 --> first cell value
				
			}
		}
			data=userSkillAPI;
			JSONObject params = new JSONObject();
			for (String[] row : userSkillAPI) {
				
				params.put("user_skill_id", row[0]);
			
				/*params.put("user_id", "U1711");*/
	
				HttpRequest = given().auth().basic(username, password).contentType("application/json");
				HttpRequest.body(params.toJSONString());
				
				response = HttpRequest.request(Method.DELETE, "https://springboot-lms-userskill.herokuapp.com/UserSkills/"+row[0]);
				response.then().log().all();
				
				String userSkillId = response.jsonPath().getString("user_skill_id");
				System.out.println("User skill id"+userSkillId);
				
				responseArrList.add(response);
				//userskillIdArrList.add(userSkillId);
	
			}
	}

	@Then("User check the response body validation in user skill map")
	public void User_check_the_response_body_validation_in_user_skill_map () {
	  
		//Response body validation
				System.out.println("=============RESPONSE BODY VALIDATION ==========");
				for(int i=0; i<responseArrList.size(); i++)
				{
					
				String responsebody = responseArrList.get(i).body().asString();
				System.out.println("Response body:"+responsebody);
				
				//Response body validation
				
				
				Assert.assertEquals(responsebody.contains(data[i][0]),true); 
				Assert.assertEquals(responsebody.contains("The record has been deleted !!"),true);//validate message response
				
				}
				System.out.println("=============RESPONSE BODY VALIDATION SUCCESSFULL==========");
		}


	@Then("delete user skill map Check response with status code {int} ok")
	public void delete_user_skill_map_check_response_with_status_code_ok(int statuscode) {
	   
		for(int i=0; i<responseArrList.size(); i++)
		{
			
		Response statusCode = responseArrList.get(i);
		assertEquals(statusCode.getStatusCode(),statuscode);
		
		}
	}

	@Then("delete user skill map Check Response status line {string}")
	public void delete_user_skill_map_check_response_status_line(String statusline) {
	    
		for(int i=0; i<responseArrList.size(); i++)
		{
			
		Response statusLine = responseArrList.get(i);
		assertEquals(statusLine.getStatusLine(),statusline);
		
		}
	}

	@Then("delete user skill map Validate Post Schema {string}")
	public void delete_user_skill_map_validate_post_schema(String schemapath) {
	   
		System.out.println("=========SCHEMA VALIDATION ========");
		for(int i=0; i<responseArrList.size(); i++)
		{
			
			Response responseSchema = responseArrList.get(i);
			responseSchema
						.then()
						.assertThat()
						.body(matchesJsonSchemaInClasspath(schemapath));
			
		}
		System.out.println("=======SCHEMA VALIDATION SUCCESSFULL=========");

	}
	@Then("check response body validation for already deleted records userskillAPI")
	public void check_response_body_validation_for_already_deleted_records_userskill_api() {
	   
		for(int i=0; i<responseArrList.size(); i++)
		{
			
		String responsebody = responseArrList.get(i).body().asString();
		System.out.println("Response body:"+responsebody);
		String userSkillId = response.jsonPath().getString("user_skill_id");
		//Response body validation
		System.out.println("=============RESPONSE BODY VALIDATION ==========");
		Assert.assertEquals(responsebody.contains("User Skill Id- "+userSkillId+" Not Found !!"),true);//validate message response
		System.out.println("=============RESPONSE BODY VALIDATION SUCCESSFULL==========");
		
		}

	}
	@When("User sends the request body to delete invalid userskill id")
	public void User_sends_the_request_body_to_delete_invalid_userskill_id() throws Exception {
	    
		rownum = ExcelUtils.getRowCount(userskillExcelpath,"DELETE_NON_EXISTING_USERSKILL");
		colnum = ExcelUtils.getCellCount(userskillExcelpath, "DELETE_NON_EXISTING_USERSKILL", 1);
		System.out.println(rownum);
		System.out.println(colnum);
		String userSkillAPI[][]  = new String[rownum][colnum];
		
		for (int i = 1; i <= rownum; i++) 
		{
			for(int j=0; j<colnum; j++) {
				
				userSkillAPI[i-1][j] = ExcelUtils.getCellData(userskillExcelpath, "DELETE_NON_EXISTING_USERSKILL", i, j); //i =1, j=0 --> first cell value
				
			}
		}
			data=userSkillAPI;
			JSONObject params = new JSONObject();
			for (String[] row : userSkillAPI) {
				
				params.put("user_skill_id", row[0]);
			
				/*params.put("user_id", "U1711");*/
	
				HttpRequest = given().auth().basic(username, password).contentType("application/json");
				HttpRequest.body(params.toJSONString());
				
				response = HttpRequest.request(Method.DELETE, "https://springboot-lms-userskill.herokuapp.com/UserSkills/"+row[0]);
				response.then().log().all();
				
				String userSkillId = response.jsonPath().getString("user_skill_id");
				System.out.println("User skill id"+userSkillId);
				
				responseArrList.add(response);
				//userskillIdArrList.add(userSkillId);
	
			}
	}
	
}



