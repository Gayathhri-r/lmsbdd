package stepDefinitions.UserSkillMap_API;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
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

public class UserSkillMapAPI_3_POST_Request_Steps extends BaseClass {
	
	public String userskillName;
	public String userskillID;
	
	ArrayList <Response>responseArrList = new ArrayList<Response>();
	ArrayList <String>userskillIdArrList = new ArrayList<String>();
	String data[][] = null;
	
	@Given("User enters the request body to insert user skill map")
	public void user_enters_the_request_body_to_insert_user_skill_map() throws Exception {
		
		HttpRequest = given().auth().basic(username, password).contentType("application/json");// request
		
	}

	@When("User sends the request body in an user skill map API")
	public void user_sends_the_request_body_in_an_user_skill_map_api() throws Exception {
		
		rownum = ExcelUtils.getRowCount(userskillExcelpath,"POST_USERSKILLMAP_API");
		colnum = ExcelUtils.getCellCount(userskillExcelpath, "POST_USERSKILLMAP_API", 1);
		System.out.println(rownum);
		System.out.println(colnum);
		String userSkillAPI[][]  = new String[rownum][colnum];
		
		for (int i = 1; i <= rownum; i++) 
		{
			for(int j=0; j<colnum; j++) {
				
				userSkillAPI[i-1][j] = ExcelUtils.getCellData(userskillExcelpath, "POST_USERSKILLMAP_API", i, j); //i =1, j=0 --> first cell value
				
			}
		}
			
			data=userSkillAPI;
			JSONObject params = new JSONObject();
			for (String[] row : userSkillAPI) {
				
				params.put("user_id", row[0]);
				params.put("skill_id", row[1]);
				params.put("months_of_exp", row[2]);
				
				/*params.put("user_id", "U1711");
				params.put("skill_id", "1242");
				params.put("months_of_exp", "20");*/
				
				HttpRequest = given().auth().basic(username, password).contentType("application/json");
				HttpRequest.body(params.toJSONString());

				response = HttpRequest.request(Method.POST, "https://springboot-lms-userskill.herokuapp.com/UserSkills");
				response.then().log().all();
				
				String uSkillId = response.jsonPath().getString("user_skill_id");
				System.out.println("Uskillid"+uSkillId);
				
				responseArrList.add(response);
				userskillIdArrList.add(uSkillId);
	
			}
	}

	@Then("check response body and db validation for Post userSkill API")
	public void check_response_body_and_db_validation_for_post_userskill_api() {
	  
		for(int i=0; i<responseArrList.size(); i++)
		{
			
		String responsebody = responseArrList.get(i).body().asString();
		System.out.println("Response body:"+responsebody);
		
		String userskillId = userskillIdArrList.get(i); // stores all skill id in arraylist
		System.out.println("skill id :"+userskillId);
		//System.out.println("skill name"+data[i][0]);
		
		
		//Response body validation
		System.out.println("=============RESPONSE BODY VALIDATION ==========");
		Assert.assertEquals(responsebody.contains(userskillId),true);//validate userskill id
		Assert.assertEquals(responsebody.contains(data[i][0]),true); //validate user  id
		Assert.assertEquals(responsebody.contains(data[i][1]),true); //validate skill  id
		Assert.assertEquals(responsebody.contains(data[i][2]),true); //validate months of exp
		Assert.assertEquals(responsebody.contains("Successfully Created !!"),true);//validate message response
		System.out.println("=============RESPONSE BODY VALIDATION SUCCESSFULL==========");
		
		//DB Validation
		System.out.println("=============DB VALIDATION==========");
		
		String userid = data[i][0];
		String skillid = data[i][1];
		String monthsofexp = data[i][2];
		response = HttpRequest.request(Method.GET, "https://springboot-lms-userskill.herokuapp.com/UserSkills"+"/"+userskillId);
		response.then().log().all();
		String user_id = response.jsonPath().getString("user_id");
		assertEquals(user_id, userid);
		String skill_id = response.jsonPath().getString("skill_id");
		assertEquals(skill_id, skillid);
		String months_of_exp = response.jsonPath().getString("months_of_exp");
		assertEquals(months_of_exp, monthsofexp);
		
		System.out.println("=============DB VALIDATION SUCCESSFULL==========");
		
		}

	}
	@Then("Create user skill map Check response with status code {int} ok")
	public void create_user_skill_map_check_response_with_status_code_ok(int statuscode) {
		
		for(int i=0; i<responseArrList.size(); i++)
		{
			
		Response statusCode = responseArrList.get(i);
		assertEquals(statusCode.getStatusCode(),statuscode);
		
		}	
	
	}	
	
	@Then("Create User Skill Check Response status line {string}")
	public void create_user_skill_check_response_status_line(String statusline) {
	    
		for(int i=0; i<responseArrList.size(); i++)
		{
			
		Response statusLine = responseArrList.get(i);
		assertEquals(statusLine.getStatusLine(),statusline);
		
		}
	}
	
	@Then("Create user skill map Validate Post Schema {string}")
	public void create_user_skill_map_validate_post_schema(String schemapath) {
	   
		System.out.println("=========SCHEMA VALIDATION ========");
		for(int i=0; i<responseArrList.size(); i++)
		{
			
			Response responseSchema = responseArrList.get(i);
			responseSchema
						.then()
						.assertThat()
						.body(matchesJsonSchemaInClasspath(schemapath));
			
		}
		System.out.println("=======SCHEMA VALIDATION SUCCESSFULL===========");
	}
	
	@When("User sends the request body for an invalid user id inUser skill API")
	public void user_sends_the_request_body_for_an_invalid_user_id_in_user_skill_api() throws Exception {
	   
		rownum = ExcelUtils.getRowCount(userskillExcelpath,"POST_INVALID_USERID");
		colnum = ExcelUtils.getCellCount(userskillExcelpath, "POST_INVALID_USERID", 1);
		System.out.println(rownum);
		System.out.println(colnum);
		String userSkillAPI[][]  = new String[rownum][colnum];
		
		for (int i = 1; i <= rownum; i++) 
		{
			for(int j=0; j<colnum; j++) {
				
				userSkillAPI[i-1][j] = ExcelUtils.getCellData(userskillExcelpath, "POST_INVALID_USERID", i, j); //i =1, j=0 --> first cell value
				
			}
		}
			
			data=userSkillAPI;
			JSONObject params = new JSONObject();
			for (String[] row : userSkillAPI) {
				
				params.put("user_id", row[0]);
				params.put("skill_id", row[1]);
				params.put("months_of_exp", row[2]);
				
				HttpRequest = given().auth().basic(username, password).contentType("application/json");
				HttpRequest.body(params.toJSONString());

				response = HttpRequest.request(Method.POST, "https://springboot-lms-userskill.herokuapp.com/UserSkills");
				response.then().log().all();
				responseArrList.add(response);
				
			}
	}

	@Then("check responsebody validation for post userskill map")
	public void check_responsebody_validation_for_post_userskill_map() {
	   
		for(int i=0; i<responseArrList.size(); i++)
		{
			
		String responsebody = responseArrList.get(i).body().asString();
		System.out.println("Response body:"+responsebody);
		//String userid = data[i][1];
		
		//Response body validation
		System.out.println("=============RESPONSE BODY VALIDATION ==========");
		Assert.assertEquals(responsebody.contains(" Not Found !!"),true);//validate message response
		System.out.println("=============RESPONSE BODY VALIDATION SUCCESSFULL==========");
	
		}
	}
	
}

	





