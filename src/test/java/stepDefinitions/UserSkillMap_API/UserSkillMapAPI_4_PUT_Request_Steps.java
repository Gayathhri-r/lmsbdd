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

public class UserSkillMapAPI_4_PUT_Request_Steps extends BaseClass{

	
	public String userskillID;
	
	ArrayList <Response>responseArrList = new ArrayList<Response>();
	ArrayList <String>userskillIdArrList = new ArrayList<String>();
	String data[][] = null;
	
	@Given("User enters the request body to update user skill map")
	public void user_enters_the_request_body_to_update_user_skill_map() throws Exception {
	   
		HttpRequest = given().auth().basic(username, password).contentType("application/json").log().all();// request
	}

	@When("User sends the request body to update skill map API")
	public void user_sends_the_request_body_to_update_skill_map_api() throws Exception {
	    
		rownum = ExcelUtils.getRowCount(userskillExcelpath,"PUT_USERSKILLMAP_API");
		colnum = ExcelUtils.getCellCount(userskillExcelpath, "PUT_USERSKILLMAP_API", 1);
		System.out.println(rownum);
		System.out.println(colnum);
		String userSkillAPI[][]  = new String[rownum][colnum];
		for (int i = 1; i <= rownum; i++) 
		{
			for(int j=0; j<colnum; j++) {
				
				userSkillAPI[i-1][j] = ExcelUtils.getCellData(userskillExcelpath, "PUT_USERSKILLMAP_API", i, j); //i =1, j=0 --> first cell value
				
			}
		}
			data=userSkillAPI;
			
			for (String[] row : userSkillAPI) {
				
				JSONObject params = new JSONObject();
				//params.put("user_skill_id", row[0]);
				params.put("user_id", row[1]);
				params.put("skill_id", row[2]);
				params.put("months_of_exp", row[3]);
				
				/*params.put("user_id", "U1711");
				params.put("skill_id", "1242");
				params.put("months_of_exp", "20");*/
				
				HttpRequest = given().auth().basic(username, password).contentType("application/json");
				HttpRequest.body(params.toJSONString());
				
				response = HttpRequest.request(Method.PUT, "https://springboot-lms-userskill.herokuapp.com/UserSkills/"+row[0]);
				response.then().log().all();
				
				String userSkillId = response.jsonPath().getString("user_skill_id");
				System.out.println("User skill id"+userSkillId);
				
				responseArrList.add(response);
				userskillIdArrList.add(userSkillId);
			}
	}
	
	@Then("Update user skill map check response body and db validations")
	public void Update_user_skill_map_check_response_body_and_db_validations() {
	  
		for(int i=0; i<responseArrList.size(); i++)
		{
			
		String responsebody = responseArrList.get(i).body().asString();
		System.out.println("Response body:"+responsebody);
		
		String userskillId = userskillIdArrList.get(i); // stores all skill id in arraylist
		System.out.println("user skill id :"+userskillId);
		//System.out.println("skill name"+data[i][0]);
		
		
		//Response body validation
		System.out.println("=============RESPONSE BODY VALIDATION ==========");
		Assert.assertEquals(responsebody.contains(userskillId),true);//validate userskill id
		Assert.assertEquals(responsebody.contains(data[i][1]),true); //validate user  id
		Assert.assertEquals(responsebody.contains(data[i][2]),true); //validate skill  id
		Assert.assertEquals(responsebody.contains(data[i][3]),true); //validate months of exp
		Assert.assertEquals(responsebody.contains("Successfully Updated !!"),true);//validate message response
		System.out.println("=============RESPONSE BODY VALIDATION SUCCESSFULL==========");
		
		//DB Validation
		System.out.println("=============DB VALIDATION==========");
		
		String userid = data[i][1];
		String skillid = data[i][2];
		String monthsofexp = data[i][3];
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
	@Then("Update user skill map Check response with status code {int} ok")
	public void update_user_skill_map_check_response_with_status_code_ok(int statuscode) {
	    
		for(int i=0; i<responseArrList.size(); i++)
		{
			
		Response statusCode = responseArrList.get(i);
		assertEquals(statusCode.getStatusCode(),statuscode);
		
		}	
	
	}

	@Then("Update user skill map Check Response status line {string}")
	public void update_user_skill_map_check_response_status_line(String statusline) {
	   
		for(int i=0; i<responseArrList.size(); i++)
		{
			
		Response statusLine = responseArrList.get(i);
		assertEquals(statusLine.getStatusLine(),statusline);
		
		}
	}

	@Then("Update user skill map Validate Post Schema {string}")
	public void update_user_skill_map_validate_post_schema(String schemapath) {
	  
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

	
	
}

