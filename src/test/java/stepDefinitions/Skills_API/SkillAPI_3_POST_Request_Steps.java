package stepDefinitions.Skills_API;

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

public class SkillAPI_3_POST_Request_Steps extends BaseClass {

	public String skillName;
	public String skillID;
	
	ArrayList <Response>responseArrList = new ArrayList<Response>();
	ArrayList <String>skillIdArrList = new ArrayList<String>();
	String data[][] = null;
	
	
	@Given("User enters the request body to insert new skill")
	public void User_enters_the_request_body_to_insert_new_skill() throws Exception {
	
		HttpRequest = given().auth().basic(username, password).contentType("application/json");// request
		
	}

	@When("User sends the request body in an skill API")
	public void user_sends_the_request_body_in_an_skill_api()throws Exception { 
	
		rownum = ExcelUtils.getRowCount(skillExcelpath, "POST_SKILL_API");
		colnum = ExcelUtils.getCellCount(skillExcelpath, "POST_SKILL_API", 1);
		
		System.out.println(rownum);
		System.out.println(colnum);
		String skillAPI[][] = new String[rownum][colnum];
		for (int i = 1; i <= rownum; i++) {
			
			for (int j = 0; j < colnum; j++) {

				skillAPI[i - 1][j] = ExcelUtils.getCellData(skillExcelpath, "POST_SKILL_API", i, j); // i =1, j=0 --> first
																								// cell valu
			}
		}
		
		data=skillAPI;
		
		
		for (String[] row : skillAPI) {
	
			JSONObject params = new JSONObject();
			skillName = row[0];
			params.put("skill_name", row[0]);

			HttpRequest = given().auth().basic(username, password).contentType("application/json").body(params.toJSONString());// request
			
			response = HttpRequest.request(Method.POST,  "https://springboot-lms-userskill.herokuapp.com/Skills");// response
			response.then().log().all();
	
			String skillId = response.jsonPath().getString("skill_id");
			System.out.println("skill id:"+skillId);
			
			responseArrList.add(response);
			skillIdArrList.add(skillId);
				
		}
	}
		@Then("check response body validation for Post Skill API")
		public void check_response_body_validation_for_post_skill_api() {
		  
			for(int i=0; i<responseArrList.size(); i++)
			{
				
			String responsebody = responseArrList.get(i).body().asString();
			System.out.println("Response body:"+responsebody);
			
			String skillId = skillIdArrList.get(i); // stores all skill id in arraylist
			System.out.println("skill id :"+skillId);
			//System.out.println("skill name"+data[i][0]);
			
			
			//Response body validation
			System.out.println("=============RESPONSE BODY VALIDATION ==========");
			Assert.assertEquals(responsebody.contains(skillId),true);//validate skill id
			Assert.assertEquals(responsebody.contains(data[i][0]),true); //validate skill name
			Assert.assertEquals(responsebody.contains("Successfully Created !!"),true);//validate message response
			System.out.println("=============RESPONSE BODY VALIDATION SUCCESSFULL==========");
			
			//DB Validation
			System.out.println("=============DB VALIDATION==========");
			String skillname = data[i][0];
			response = HttpRequest.request(Method.GET, "https://springboot-lms-userskill.herokuapp.com/Skills"+"/"+skillId);
			response.then().log().all();
			String Skill_name = response.jsonPath().getString("skill_name");
			assertEquals(Skill_name, skillname);
			System.out.println("=============DB VALIDATION SUCCESSFULL==========");
			
			}

		}
		@Then("Create Skill Check response with status code {int} ok")
		public void create_skill_check_response_with_status_code_ok(int statuscode) {
			
			for(int i=0; i<responseArrList.size(); i++)
			{
				
			Response statusCode = responseArrList.get(i);
			assertEquals(statusCode.getStatusCode(),statuscode);
			
			}	
		
		}	
		
		@Then("Create Skill Check Response status line {string}")
		public void create_skill_check_response_status_line(String statusline) {
		  
			for(int i=0; i<responseArrList.size(); i++)
			{
				
			Response statusLine = responseArrList.get(i);
			assertEquals(statusLine.getStatusLine(),statusline);
			
			}	
		}
		
		@Then("Create Skill Validate Post Schema {string}")
		public void create_skill_validate_post_schema(String schemapath) {
		   
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

		@Then("check response body validation for existing skill Post Skill API")
		public void check_response_body_validation_for_existing_skill_Post_skill_API() {
			
		
			for(int i=0; i<responseArrList.size(); i++)
			{
				
			String responsebody = responseArrList.get(i).body().asString();
			System.out.println("Response body:"+responsebody);
					
			//Response body validation
			System.out.println("=============RESPONSE BODY VALIDATION ==========");
			Assert.assertEquals(responsebody.contains("Failed to create new Skill details as Skill already exists !!"),true);//validate message response
			System.out.println("=============RESPONSE BODY VALIDATION SUCCESSFULL==========");
			
			}

		}	
		
		@When("User sends the request body for invalid skillname")
		public void User_sends_the_request_body_for_invalid_skillname()throws Exception { 
		
			rownum = ExcelUtils.getRowCount(skillExcelpath, "POST_SKILL_INVALID_SKILLNAME");
			colnum = ExcelUtils.getCellCount(skillExcelpath, "POST_SKILL_INVALID_SKILLNAME", 1);
			
			System.out.println(rownum);
			System.out.println(colnum);

			String skillAPI[][] = new String[rownum][colnum];
			for (int i = 1; i <= rownum; i++) {
				
				for (int j = 0; j < colnum; j++) {

					skillAPI[i - 1][j] = ExcelUtils.getCellData(skillExcelpath, "POST_SKILL_INVALID_SKILLNAME", i, j); // i =1, j=0 --> first
																									// cell value
				}
			}
			
			data=skillAPI;
			
			JSONObject params = new JSONObject();
			for (String[] row : skillAPI) {
		
				skillName = row[0];
				params.put("skill_name", row[0]);

				HttpRequest = given().auth().basic(username, password).contentType("application/json").body(params.toJSONString());// request
				
				response = HttpRequest.request(Method.POST,  "https://springboot-lms-userskill.herokuapp.com/Skills");// response
				response.then().log().all();
		
				String skillId = response.jsonPath().getString("skill_id");
				System.out.println("skill id:"+skillId);
				
				responseArrList.add(response);
				skillIdArrList.add(skillId);
					
			}
		}	

		@Then("check response body validation for invalid skill name")
		public void check_response_body_validation_for_invalid_skill_name() {
			
		
			for(int i=0; i<responseArrList.size(); i++)
			{
				
			String responsebody = responseArrList.get(i).body().asString();
			System.out.println("Response body:"+responsebody);
					
			//Response body validation
			System.out.println("=============RESPONSE BODY VALIDATION ==========");
			Assert.assertEquals(responsebody.contains("Failed to create new Skill details as Skill Name contains special characters !!"),true);//validate message response
			System.out.println("=============RESPONSE BODY VALIDATION SUCCESSFULL==========");
			
			}
		}
}



	


