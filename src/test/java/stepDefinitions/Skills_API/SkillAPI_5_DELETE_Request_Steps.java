package stepDefinitions.Skills_API;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;

import BaseClass.BaseClass;
import Utilities.ExcelUtils;
import io.cucumber.java.en.*;
import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;

public class SkillAPI_5_DELETE_Request_Steps extends BaseClass {

	
	public String skillID;
	ArrayList <Response>responseArrList = new ArrayList<Response>();
	ArrayList <String>skillIdArrList = new ArrayList<String>();
	String data[][] = null;
	
	@Given("User enters delete request to delete Skill with valid authorization")
	public void User_enters_delete_request_to_delete_Skill_with_valid_authorization() throws Exception {

		HttpRequest = given().auth().basic(username, password).contentType("application/json");// request
			
	}

	@When("User sends delete request to delete Skill with valid endpoints and authorization")
	public void user_sends_put_request_to_delete_Skill_with_valid_endpoints_and_authorization() throws Exception {

		rownum = ExcelUtils.getRowCount(skillExcelpath, "DELETE_SKILL_API");
		colnum = ExcelUtils.getCellCount(skillExcelpath, "DELETE_SKILL_API", 1);
		System.out.println(rownum);
		System.out.println(colnum);
	
		String skillAPI[][]  = new String[rownum][colnum];
		for (int i = 1; i <= rownum; i++) 
		{
			for(int j=0; j<colnum; j++) {
				
				skillAPI[i-1][j] = ExcelUtils.getCellData(skillExcelpath, "DELETE_SKILL_API", i, j); // i =1, j=0 --> first cell value 
																								
			}
		}
		
		data=skillAPI;
		
		
		for (String[] row : skillAPI) {
			
			JSONObject params = new JSONObject();
			params.put("skill_id", row[0]);
			System.out.println("skill id:" + row[0]);

			HttpRequest = given().auth().basic(username, password).contentType("application/json");
			response = HttpRequest.request(Method.DELETE, "https://springboot-lms-userskill.herokuapp.com/Skills/"+ row[0]);
			// response.then().log().all();

			System.out.println("response:" + response.asString());
			String skillId = response.jsonPath().getString("skill_id");//null 
			System.out.println("skill id:"+skillId);
			
			responseArrList.add(response);
			skillIdArrList.add(skillId);
			
		}
	}

	@Then("check response body validation in delete SkillAPI")
	public void check_response_body_validation_in_delete_SkillAPI() {
		
		//Response body validation
		System.out.println("=============RESPONSE BODY VALIDATION ==========");
		for(int i=0; i<responseArrList.size(); i++)
		{
			
		String responsebody = responseArrList.get(i).body().asString();
		System.out.println("Response body:"+responsebody);
		
		Assert.assertEquals(responsebody.contains(data[i][0]),true); 
		Assert.assertEquals(responsebody.contains("The record has been deleted !!"),true);//validate message response
		
		}
		System.out.println("=============RESPONSE BODY VALIDATION SUCCESSFULL==========");
						
	}
	@Then("Delete skill check response with status code {int} ok in SkillAPI")
	public void update_user_check_response_with_status_code_ok_in_skill_api(int statuscode) {

		for(int i=0; i<responseArrList.size(); i++)
		{
			
		Response statusCode = responseArrList.get(i);
		assertEquals(statusCode.getStatusCode(),statuscode);
		
		}
	}

	@Then("Delete skill check Response status line {string} in SkillAPI")
	public void update_user_check_response_status_line_in_skill_api(String statusline) {

		for(int i=0; i<responseArrList.size(); i++)
		{
			
		Response statusLine = responseArrList.get(i);
		assertEquals(statusLine.getStatusLine(),statusline);
		
		}

	}

	@Then("Delete skill validate Schema {string} in SkillAPI")
	public void update_user_validate_schema_in_skill_api(String schemapath) {

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
	
	@Then("check response body validation for already deleted records SkillAPI")
	public void check_response_body_validation_for_already_deleted_records_skill_api() {
	   
		for(int i=0; i<responseArrList.size(); i++)
		{
			
		String responsebody = responseArrList.get(i).body().asString();
		System.out.println("Response body:"+responsebody);
		String skillId = response.jsonPath().getString("skill_id");
		//Response body validation
		System.out.println("=============RESPONSE BODY VALIDATION ==========");
		Assert.assertEquals(responsebody.contains("Skill(id- "+skillId+") Not Found !!"),false);//validate message response
		System.out.println("=============RESPONSE BODY VALIDATION SUCCESSFULL==========");
		
		}
	}
	
	@When("User sends delete request to delete Skill for invalid skill id with valid endpoints and authorization")
	public void User_sends_delete_request_to_delete_Skill_for_invalid_skill_id_with_valid_endpoints_and_authorization() throws Exception {

		rownum = ExcelUtils.getRowCount(skillExcelpath, "DELETE_SKILL_INVALID_SKILLID");
		colnum = ExcelUtils.getCellCount(skillExcelpath, "DELETE_SKILL_INVALID_SKILLID", 1);
		System.out.println(rownum);
		System.out.println(colnum);
	
		String skillAPI[][]  = new String[rownum][colnum];
		for (int i = 1; i <= rownum; i++) 
		{
			for(int j=0; j<colnum; j++) {
				
				skillAPI[i-1][j] = ExcelUtils.getCellData(skillExcelpath, "DELETE_SKILL_INVALID_SKILLID", i, j); // i =1, j=0 --> first cell value 
																								
			}
		}
		
		data=skillAPI;
		
		
		for (String[] row : skillAPI) {
			
			JSONObject params = new JSONObject();
			params.put("skill_id", row[0]);
			System.out.println("skill id:" + row[0]);

			HttpRequest = given().auth().basic(username, password).contentType("application/json");
			response = HttpRequest.request(Method.DELETE, "https://springboot-lms-userskill.herokuapp.com/Skills/"+ row[0]);
			// response.then().log().all();

			System.out.println("response:" + response.asString());
			String skillId = response.jsonPath().getString("skill_id");
			System.out.println("skill id:"+skillId);
			
			responseArrList.add(response);
			skillIdArrList.add(skillId);
			
		}
	}
	@When("User sends delete request to delete Skill for non existing skill id with valid endpoints and authorization")
	public void User_sends_delete_request_to_delete_Skill_for_non_existing_skill_id_with_valid_endpoints_and_authorization() throws Exception {

		rownum = ExcelUtils.getRowCount(skillExcelpath, "PUT_SKILL_NONEXISTING_SKILLID");  //can reuse the excel from put skill
		colnum = ExcelUtils.getCellCount(skillExcelpath, "PUT_SKILL_NONEXISTING_SKILLID", 1);//can reuse the excel from put skill
		System.out.println(rownum);
		System.out.println(colnum);
	
		String skillAPI[][]  = new String[rownum][colnum];
		for (int i = 1; i <= rownum; i++) 
		{
			for(int j=0; j<colnum; j++) {
				
				skillAPI[i-1][j] = ExcelUtils.getCellData(skillExcelpath, "DELETE_SKILL_INVALID_SKILLID", i, j); // i =1, j=0 --> first cell value 
																								
			}
		}
		
		data=skillAPI;
		
		
		for (String[] row : skillAPI) {
			
			JSONObject params = new JSONObject();
			params.put("skill_id", row[0]);
			System.out.println("skill id:" + row[0]);

			HttpRequest = given().auth().basic(username, password).contentType("application/json");
			response = HttpRequest.request(Method.DELETE, "https://springboot-lms-userskill.herokuapp.com/Skills/"+ row[0]);
			// response.then().log().all();

			System.out.println("response:" + response.asString());
			String skillId = response.jsonPath().getString("skill_id");
			System.out.println("skill id:"+skillId);
			
			responseArrList.add(response);
			skillIdArrList.add(skillId);
			
		}
	}
	@Then("check response body validation for non existing skill id")
	public void check_response_body_validation_for_non_existing_skill_id() {
	   
		for(int i=0; i<responseArrList.size(); i++)
		{
			
		String responsebody = responseArrList.get(i).body().asString();
		System.out.println("Response body:"+responsebody);
		String skillId = response.jsonPath().getString("skill_id");
		//Response body validation
		System.out.println("=============RESPONSE BODY VALIDATION ==========");
		Assert.assertEquals(responsebody.contains("Skill(id- "+skillId+") Not Found !!"),false);//validate message response
		System.out.println("=============RESPONSE BODY VALIDATION SUCCESSFULL==========");
		
		}
	}
	@Then("check response body validation for invalid skill id")
	public void check_response_body_validation_for_invalid_skill_id() {
	   
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
