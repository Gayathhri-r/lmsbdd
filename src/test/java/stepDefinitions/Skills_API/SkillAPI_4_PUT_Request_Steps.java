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

public class SkillAPI_4_PUT_Request_Steps extends BaseClass {
	
	public String skillName;
	public String skillID;
	
	ArrayList <Response>responseArrList = new ArrayList<Response>();
	ArrayList <String>skillIdArrList = new ArrayList<String>();
	String data[][] = null;
	
	@Given("User enters put request to update skillAPI with valid authorization")
	public void User_enters_put_request_to_update_skillAPI_with_valid_authorization() throws Exception {
	  
		HttpRequest = given().auth().basic(username, password).contentType("application/json");// request
		
	}

	@When("User sends put request to update SkillAPI with valid endpoints and authorization")
	public void user_sends_put_request_to_update_skill_api_with_valid_endpoints_and_authorization() throws Exception {
	    
		rownum = ExcelUtils.getRowCount(skillExcelpath,"PUT_SKILL_API");
		colnum = ExcelUtils.getCellCount(skillExcelpath, "PUT_SKILL_API", 1);
		System.out.println(rownum);
		System.out.println(colnum);
		String skillAPI[][]  = new String[rownum][colnum];
		for (int i = 1; i <= rownum; i++) 
		{
			for(int j=0; j<colnum; j++) {
				
				skillAPI[i-1][j] = ExcelUtils.getCellData(skillExcelpath, "PUT_SKILL_API", i, j); //i =1, j=0 --> first cell value
				
			}
		}
		
			data=skillAPI;
		
			JSONObject params = new JSONObject();
			for (String[] row : skillAPI) {
			
				params.put("skill_name", row[1]);
				
				//System.out.println("skill id:"+row[0]);
				//System.out.println("skill name:"+row[1]);
				HttpRequest = given().auth().basic(username, password).contentType("application/json").body(params.toJSONString());
				response = HttpRequest.request(Method.PUT, "https://springboot-lms-userskill.herokuapp.com/Skills/"+row[0]);
				response.then().log().all();
				System.out.println("response"+response.asString());
				
				String skillId = response.jsonPath().getString("skill_id");
				System.out.println("skill id:"+skillId);
				
				responseArrList.add(response);
				skillIdArrList.add(skillId);
	
			}
			
	}

	@Then("check response body validation in Put SkillAPI")
	public void check_response_body_validation_in_put_skill_api() {
		
		//Response body validation
		
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
		Assert.assertEquals(responsebody.contains(data[i][1]),true); //validate skill name
		Assert.assertEquals(responsebody.contains("Successfully Updated !!"),true);//validate message response
		System.out.println("=============RESPONSE BODY VALIDATION SUCCESSFULL==========");
		
		//DB Validation
		System.out.println("=============DB VALIDATION==========");
		String skillname = data[i][1];
		response = HttpRequest.request(Method.GET, "https://springboot-lms-userskill.herokuapp.com/Skills"+"/"+skillId);
		response.then().log().all();
		String Skill_name = response.jsonPath().getString("skill_name");
		assertEquals(Skill_name, skillname);
		}
		System.out.println("=============DB VALIDATION SUCCESSFULL==========");					
	}
	
	@Then("check response with status code {int} in SkillAPI")
	public void update_user_check_response_with_status_code_in_skill_api(int statuscode) {
	   
		for(int i=0; i<responseArrList.size(); i++)
		{
			
		Response statusCode = responseArrList.get(i);
		assertEquals(statusCode.getStatusCode(),statuscode);
		
		}	
	}
	
	@Then("check Response status line {string} in SkillAPI")
	public void check_response_status_line_in_skill_api(String statusline) {
		
		for(int i=0; i<responseArrList.size(); i++)
		{
			
		Response statusLine = responseArrList.get(i);
		assertEquals(statusLine.getStatusLine(),statusline);
		
		}
	}
	
	@Then("validate Post Schema {string} in API")
	public void validate_post_schema_in_api(String schemapath) {
	    
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
		
	@When("User sends put request to update skill name with already available skillname")
	public void user_sends_put_request_to_update_skill_api_with_already_available_skillname() throws Exception {
	    
		rownum = ExcelUtils.getRowCount(skillExcelpath,"PUT_EXISTING_SKILLNAME");
		colnum = ExcelUtils.getCellCount(skillExcelpath, "PUT_EXISTING_SKILLNAME", 1);
		System.out.println(rownum);
		System.out.println(colnum);
		String skillAPI[][]  = new String[rownum][colnum];
		for (int i = 1; i <= rownum; i++) 
		{
			for(int j=0; j<colnum; j++) {
				
				skillAPI[i-1][j] = ExcelUtils.getCellData(skillExcelpath, "PUT_EXISTING_SKILLNAME", i, j); //i =1, j=0 --> first cell value
				
			}
		}
		
			data=skillAPI;
		
			JSONObject params = new JSONObject();
			for (String[] row : skillAPI) {
			
				params.put("skill_name", row[1]);
				
				//System.out.println("skill id:"+row[0]);
				//System.out.println("skill name:"+row[1]);
				HttpRequest = given().auth().basic(username, password).contentType("application/json").body(params.toJSONString());
				response = HttpRequest.request(Method.PUT, "https://springboot-lms-userskill.herokuapp.com/Skills/"+row[0]);
				response.then().log().all();
				System.out.println("response"+response.asString());
				
				String skillId = response.jsonPath().getString("skill_id");
				System.out.println("skill id:"+skillId);
				
				responseArrList.add(response);
			
			}
			
	}

	@Then("check response body validation for existing skill name to an  existing skill id")
	public void check_response_body_validation_for_existing_skill_name_to_an_existing_skill_id() {
		
		//Response body validation
		
		for(int i=0; i<responseArrList.size(); i++)
		{
			
		String responsebody = responseArrList.get(i).body().asString();
		System.out.println("Response body:"+responsebody);
				
		//Response body validation
		System.out.println("=============RESPONSE BODY VALIDATION ==========");
		Assert.assertEquals(responsebody.contains("Failed to update existing Skill details as Skill already exists !!"),true);//validate message response
		System.out.println("=============RESPONSE BODY VALIDATION SUCCESSFULL==========");
		
		}
							
	}

	
	@When("User sends put request to update skill name for non existing skill id")
	public void user_sends_put_request_to_update_skill_name_for_non_existing_skill_id() throws Exception {
		
		rownum = ExcelUtils.getRowCount(skillExcelpath,"PUT_SKILL_NONEXISTING_SKILLID");
		colnum = ExcelUtils.getCellCount(skillExcelpath, "PUT_SKILL_NONEXISTING_SKILLID", 1);
		System.out.println(rownum);
		System.out.println(colnum);
		
		HttpRequest = given().auth().basic(username, password).contentType("application/json");// request
		String skillAPI[][] = new String[rownum][colnum];
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colnum; j++) {

				skillAPI[i - 1][j] = ExcelUtils.getCellData(skillExcelpath, "PUT_SKILL_NONEXISTING_SKILLID", i, j); // i =1, j=0 --> first cell value
																						
			}
		}
		
		data=skillAPI;
		
		JSONObject params = new JSONObject();
		for (String[] row : skillAPI) {
		
			params.put("skill_name", row[1]);
			
			//System.out.println("skill id:"+row[0]);
			//System.out.println("skill name:"+row[1]);
			HttpRequest = given().auth().basic(username, password).contentType("application/json").body(params.toJSONString());
			response = HttpRequest.request(Method.PUT, "https://springboot-lms-userskill.herokuapp.com/Skills/"+row[0]);
			response.then().log().all();
			System.out.println("response"+response.asString());
			responseArrList.add(response);
			
		}
					
	}
	@When("User sends put request to update invalid skill name")
	public void User_sends_put_request_to_update_invalid_skill_name() throws Exception {
	    
		rownum = ExcelUtils.getRowCount(skillExcelpath,"PUT_SKILL_INVALID_SKILLNAME");
		colnum = ExcelUtils.getCellCount(skillExcelpath, "PUT_SKILL_INVALID_SKILLNAME", 1);
		System.out.println(rownum);
		System.out.println(colnum);
		String skillAPI[][]  = new String[rownum][colnum];
		for (int i = 1; i <= rownum; i++) 
		{
			for(int j=0; j<colnum; j++) {
				
				skillAPI[i-1][j] = ExcelUtils.getCellData(skillExcelpath, "PUT_SKILL_INVALID_SKILLNAME", i, j); //i =1, j=0 --> first cell value
				
			}
		}
		
			data=skillAPI;
		
			JSONObject params = new JSONObject();
			for (String[] row : skillAPI) {
			
				params.put("skill_name", row[1]);
				
				//System.out.println("skill id:"+row[0]);
				//System.out.println("skill name:"+row[1]);
				HttpRequest = given().auth().basic(username, password).contentType("application/json").body(params.toJSONString());
				response = HttpRequest.request(Method.PUT, "https://springboot-lms-userskill.herokuapp.com/Skills/"+row[0]);
				response.then().log().all();
				System.out.println("response"+response.asString());
				
				String skillId = response.jsonPath().getString("skill_id");
				System.out.println("skill id:"+skillId);
				
				responseArrList.add(response);
				skillIdArrList.add(skillId);
	
			}	
	}
	
	@Then("check response body validation for non existing skill id in skill api")
	public void check_response_body_validation_for_non_existing_skill_id_in_skill_api() {
		
	
		for(int i=0; i<responseArrList.size(); i++)
		{
			
		String responsebody = responseArrList.get(i).body().asString();
		System.out.println("Response body:"+responsebody);
		String skillId = response.jsonPath().getString("skill_id");		
		//Response body validation
		System.out.println("=============RESPONSE BODY VALIDATION ==========");
		Assert.assertEquals(responsebody.contains("Skill ID - "+skillId+" is invalid !!"),false);//validate message response
		System.out.println("=============RESPONSE BODY VALIDATION SUCCESSFULL==========");
		
		}
	}

}

	


	
		


	
	


