
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

public class UserAPI_3_POST_Request_Steps extends BaseClass {

	public String userName;
	public String userID;
	
	ArrayList <Response>responseArrList = new ArrayList<Response>();
	ArrayList <String>userIdArrList = new ArrayList<String>();
	String data[][] = null;
	
	@Given("User enters the request body to insert users")
	public  void user_enters_the_request_body_to_insert_users() throws Exception {
	    
		HttpRequest = given().auth().basic(username, password).contentType("application/json");// request
				
	}
			
	@When("User sends the request body in an User API")
	public void user_sends_the_request_body_in_an_user_api() throws Exception{
	    
		rownum = ExcelUtils.getRowCount(userExcelpath, "POST_USER_API"); //get row count from excel util file
		colnum = ExcelUtils.getCellCount(userExcelpath, "POST_USER_API", 1); //get col count from excel util file
		
		System.out.println(rownum); 
		System.out.println(colnum);
		String userAPI[][]  = new String[rownum][colnum];
	
		for (int i = 1; i <= rownum; i++) 
		{
			for (int j = 0; j < colnum; j++) 
			{
				userAPI[i-1][j] = ExcelUtils.getCellData(userExcelpath, "POST_USER_API", i, j); //i =1, j=0 --> first cell value
					
			}
			
		}	
		
		data=userAPI;
		JSONObject params = new JSONObject();
	
		for (String[] row : userAPI) {
		
			params.put("name", row[0]); //key are compared with the original swagger url, values are mapped to the headers
			params.put("phone_number", row[1]);
			params.put("location", row[2]);
			params.put("time_zone", row[3]);
			params.put("linkedin_url", row[4]);
			params.put("education_ug", row[5]);
			params.put("education_pg", row[6]);
			params.put("visa_status", row[7]);
			params.put("comments", row[8]);
			
		
			HttpRequest = given().auth().basic(username, password).contentType("application/json").body(params.toJSONString());
			
			response = HttpRequest.request(Method.POST, "https://springboot-lms-userskill.herokuapp.com/Users");
			response.then().log().all(); //log all data
			
			//capture User ID from response for further validation
			String userId = response.jsonPath().getString("user_id");
			System.out.println("User id:"+userId);
			
			responseArrList.add(response);
			userIdArrList.add(userId);
			
			}
		}

	@Then("check response body validation and DB Validation for Post User API")
	public void check_response_body_validation_and_DB_Validation_for_post_user_api() {
	  
		for(int i=0; i<responseArrList.size(); i++)
		{
			
		String responsebody = responseArrList.get(i).body().asString();
		System.out.println("Response body:"+responsebody);
		
		String userId = userIdArrList.get(i); // stores all skill id in arraylist
		System.out.println("User id :"+userId);
		
		
		
		//Response body validation
		System.out.println("=============RESPONSE BODY VALIDATION ==========");
		Assert.assertEquals(responsebody.contains(userId),true);//validate user id
		Assert.assertEquals(responsebody.contains(data[i][0]),true); //validate user name
		Assert.assertEquals(responsebody.contains(data[i][1]),true); //validate phone number
		Assert.assertEquals(responsebody.contains(data[i][2]),true); //validate location
		Assert.assertEquals(responsebody.contains(data[i][3]),true); //validate time zone
		Assert.assertEquals(responsebody.contains(data[i][4]),true); //validate linked in url
		Assert.assertEquals(responsebody.contains(data[i][5]),true); //validate education ug
		Assert.assertEquals(responsebody.contains(data[i][6]),true); //validate education pg
		Assert.assertEquals(responsebody.contains(data[i][7]),true); //validate visa status
		Assert.assertEquals(responsebody.contains(data[i][8]),true); //validate comments
		Assert.assertEquals(responsebody.contains("Successfully Created !!"),true);//validate message response
		
		System.out.println("=============RESPONSE BODY VALIDATION SUCCESSFULL==========");
		
		//DB Validation
		System.out.println("=============DB VALIDATION==========");
		String Username = data[i][0];
		String Phoneno = data[i][1];
		String Location = data[i][2];
		String timezone = data[i][3];
		String linkedinurl = data[i][4];
		String educationug = data[i][5];
		String educationpg = data[i][6];
		String visastatus = data[i][7];
		String Comments = data[i][8];
		
	
		response = HttpRequest.request(Method.GET, "https://springboot-lms-userskill.herokuapp.com/Users"+"/"+userId);
		response.then().log().all();
		String user_name = response.jsonPath().getString("name");
		assertEquals(user_name, Username);
		String phoneno = response.jsonPath().getString("phone_number");
		assertEquals(Phoneno, phoneno);
		String location = response.jsonPath().getString("location");
		assertEquals(Location, location);
		String time_zone = response.jsonPath().getString("time_zone");
		assertEquals(time_zone, timezone);
		String linked_in_url = response.jsonPath().getString("linkedin_url");
		assertEquals(linked_in_url, linkedinurl);
	
		System.out.println("=============DB VALIDATION SUCCESSFULL==========");
		
		}

	}
	@Then("Create User Check response with status code {int} ok")
	public void check_response_with_status_code_ok(int statuscode) {
		
		for(int i=0; i<responseArrList.size(); i++)
		{
			
		Response statusCode = responseArrList.get(i);
		assertEquals(statusCode.getStatusCode(),statuscode);
		
		}	
		
	}
	
	@Then("Create User Check Response status line {string}")
	public void check_response_status_line(String statusline) {
	    
		for(int i=0; i<responseArrList.size(); i++)
		{
			
		Response statusLine = responseArrList.get(i);
		assertEquals(statusLine.getStatusLine(),statusline);
		
		}	
	}
		
	@Then("Create User Validate Post Schema {string}")
	public void  validate_post_schema(String schemapath) {
	    
		System.out.println("=========SCHEMA VALIDATION ====================");
		for(int i=0; i<responseArrList.size(); i++)
		{
			
			Response responseSchema = responseArrList.get(i);
			responseSchema
						.then()
						.assertThat()
						.body(matchesJsonSchemaInClasspath(schemapath));
			
		}
		System.out.println("=======SCHEMA VALIDATION SUCCESSFULL================");
	}
	
	@Then("check response body validation for existing User Post UserAPI")
	public void check_response_body_validation_for_existing_User_Post_UserAPI() {
		
	
		for(int i=0; i<responseArrList.size(); i++)
		{
			
		String responsebody = responseArrList.get(i).body().asString();
		System.out.println("Response body:"+responsebody);
				
		//Response body validation
		System.out.println("=============RESPONSE BODY VALIDATION ==========");
		Assert.assertEquals(responsebody.contains("Failed to create new User details as phone number already exists !!"),true);//validate message response
		System.out.println("=============RESPONSE BODY VALIDATION SUCCESSFULL==========");
		
		}

	}	
	
	@When("User sends the request body for invalid user in an User API")
	public void user_sends_the_request_body_for_invalid_user_in_an_User_API() throws Exception{
	    
		rownum = ExcelUtils.getRowCount(userExcelpath, "POST_INVALID_USER"); //get row count from excel util file
		colnum = ExcelUtils.getCellCount(userExcelpath, "POST_INVALID_USER", 1); //get col count from excel util file
		
		System.out.println(rownum); //display row values 
		System.out.println(colnum);//display col values 
		String userAPI[][]  = new String[rownum][colnum];
	
		for (int i = 1; i <= rownum; i++) 
		{
			for (int j = 0; j < colnum; j++) 
			{
				userAPI[i-1][j] = ExcelUtils.getCellData(userExcelpath, "POST_INVALID_USER", i, j); //i =1, j=0 --> first cell value
					
			}
			
		}	
		
		JSONObject params = new JSONObject();
	
		for (String[] row : userAPI) {
		
			params.put("name", row[0]); //key are compared with the original swagger url, values are mapped to the headers
			params.put("phone_number", row[1]);
			params.put("location", row[2]);
			params.put("time_zone", row[3]);
			params.put("linkedin_url", row[4]);
			params.put("education_ug", row[5]);
			params.put("education_pg", row[6]);
			params.put("visa_status", row[7]);
			params.put("comments", row[8]);
			
		
			HttpRequest = given().auth().basic(username, password).contentType("application/json").body(params.toJSONString());
			
			response = HttpRequest.request(Method.POST, "https://springboot-lms-userskill.herokuapp.com/Users");
			response.then().log().all(); //log all data
			responseArrList.add(response);
			
			}
		}
	
	@Then("check response body validation for invalid username in Post User API")
	public void check_response_body_validation_for_invalid_username_in_Post_UserAPI() {
		
	
		for(int i=0; i<responseArrList.size(); i++)
		{
			
		String responsebody = responseArrList.get(i).body().asString();
		System.out.println("=============RESPONSE BODY VALIDATION ==========");
		Assert.assertEquals(responsebody.contains("Failed to create new user, as 'Name' value should be ',' separated !! "),true);//validate message response
		System.out.println("=============RESPONSE BODY VALIDATION SUCCESSFULL==========");
		
		}

	}	
}



