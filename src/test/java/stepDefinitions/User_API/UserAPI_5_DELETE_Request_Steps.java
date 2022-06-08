package stepDefinitions.User_API;

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

public class UserAPI_5_DELETE_Request_Steps extends BaseClass {
	public String userName;
	public String userID;
	
	ArrayList <Response>responseArrList = new ArrayList<Response>();
	ArrayList <String>userIdArrList = new ArrayList<String>();
	String data[][] = null;
	
@Given("User enters delete request to delete userAPI with valid authorization")
public void User_enters_delete_request_to_delete_userAPI_with_valid_authorization()  throws Exception {
   
	HttpRequest = given().auth().basic(username, password).contentType("application/json");// request
	
}

@When("User sends delete request to delete a user in UserAPI with valid endpoints and authorization")
public void user_sends_delete_request_to_delete_a_user_in_user_api_with_valid_endpoints_and_authorization()throws Exception{
	
	rownum = ExcelUtils.getRowCount(userExcelpath, "DELETE_USER_API"); //get row count from excel util file
	colnum = ExcelUtils.getCellCount(userExcelpath, "DELETE_USER_API", 1); //get col count from excel util file
	
	System.out.println(rownum); //display row values -14
	System.out.println(colnum);//display col values -9
	String userAPI[][]  = new String[rownum][colnum];
	
	for (int i = 1; i <= rownum; i++) 
	{
		for (int j = 0; j < colnum; j++) 
		{
			userAPI[i-1][j] = ExcelUtils.getCellData(userExcelpath, "DELETE_USER_API", i, j); //i =1, j=0 --> first cell value
			//System.out.println(UserAPI[i-1][j].length());
			
		}
		
	}	
				data=userAPI;
				
				JSONObject params = new JSONObject();
				for (String[] row : userAPI) {
					
				params.put("user_id", row[0]); 
				HttpRequest =given().auth().basic(username,password).contentType("application/json").log().all();
				//HttpRequest.body(params.toString());
				response = HttpRequest.request(Method.DELETE,"https://springboot-lms-userskill.herokuapp.com/Users/"+row[0]);
				response.then().log().all(); 
				responseArrList.add(response);
				
				}
			
		}

@Then("check response body validation in delete UserAPI")
public void check_response_body_validation_in_delete_UserAPI() {
  
	for(int i=0; i<responseArrList.size(); i++)
	{
		
	String responsebody = responseArrList.get(i).body().asString();
	System.out.println("Response body:"+responsebody);
	
	//Response body validation
	System.out.println("=============RESPONSE BODY VALIDATION ==========");
	Assert.assertEquals(responsebody.contains(data[i][0]),true);
	Assert.assertEquals(responsebody.contains("The record has been deleted !!"),true);//validate message response
	System.out.println("=============RESPONSE BODY VALIDATION SUCCESSFULL==========");
	
	}

}
@Then("Delete User check response with status code {int} ok in UserAPI")
public void delete_User_check_response_with_status_code_ok_in_user_api(int statuscode) {
   
	for(int i=0; i<responseArrList.size(); i++)
	{
		
	Response statusCode = responseArrList.get(i);
	assertEquals(statusCode.getStatusCode(),statuscode);
	
	}
}

@Then("Delete User check Response status line {string} in UserAPI")
public void delete_User_check_response_status_line_in_user_api(String statusline) {
	
	for(int i=0; i<responseArrList.size(); i++)
	{
		
	Response statusLine = responseArrList.get(i);
	assertEquals(statusLine.getStatusLine(),statusline);
	
	}
	
}

@Then("Delete User validate Post Schema {string} in UserAPI")
public void delete_User_validate_post_schema_in_user_api(String schemapath) {
    
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
@Then("check response body validation for already deleted records User API")
public void check_response_body_validation_for_already_deleted_records_User_api() {
   
	for(int i=0; i<responseArrList.size(); i++)
	{
		
	String responsebody = responseArrList.get(i).body().asString();
	
	System.out.println("Response body:"+responsebody);
	String userId = response.jsonPath().getString("user_id");
	//Response body validation
	System.out.println("=============RESPONSE BODY VALIDATION ==========");
	Assert.assertEquals(responsebody.contains("User id- "+userId+" Not Found !!"),false);//validate message response
	System.out.println("=============RESPONSE BODY VALIDATION SUCCESSFULL==========");
	
	}

	
}
}
