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

public class UserAPI_4_PUT_Request_Steps extends BaseClass {

	public String userName;
	public String userID;
	
	ArrayList <Response>responseArrList = new ArrayList<Response>();
	ArrayList <String>userIdArrList = new ArrayList<String>();
	String data[][] = null;
	
@Given("User enters put request to update UserAPI with valid authorization")
public void User_enters_put_request_to_update_UserAPI_with_valid_authorization() throws Exception {
   
	HttpRequest = given().auth().basic(username, password).contentType("application/json");// request
}

@When("User sends put request to update UserAPI with valid endpoints and authorization")
public void User_sends_put_request_to_update_UserAPI_with_valid_endpoints_and_authorization() throws Exception{
	 
	rownum = ExcelUtils.getRowCount(userExcelpath, "PUT_USER_API"); //get row count from excel util file
	colnum = ExcelUtils.getCellCount(userExcelpath, "PUT_USER_API", 1); //get col count from excel util file
	
	System.out.println(rownum); 
	System.out.println(colnum);
	String userAPI[][]  = new String[rownum][colnum];

	for (int i = 1; i <= rownum; i++) 
	{
		for (int j = 0; j < colnum; j++) 
		{
			userAPI[i-1][j] = ExcelUtils.getCellData(userExcelpath, "PUT_USER_API", i, j); //i =1, j=0 --> first cell value
				
		}
		
	}	
	
				data=userAPI;
				
				
				for (String[] row : userAPI) 
				{
					JSONObject params = new JSONObject();
					params.put("user_id", row[0]);
					params.put("name", row[1]);
					params.put("phone_number", row[2]);
					params.put("location", row[3]);
					params.put("time_zone", row[4]);
					params.put("linkedin_url", row[5]);
					params.put("education_ug", row[6]);
					params.put("education_pg", row[7]);
					params.put("visa_status", row[8]);
					params.put("comments", row[9]);
				
				
				HttpRequest = given().auth().basic(username,password).contentType("application/json").body(params.toString());;
				response = HttpRequest.request(Method.PUT, "https://springboot-lms-userskill.herokuapp.com/Users"+"/"+row[0]);
				response.then().log().all(); 
				String userId = response.jsonPath().getString("user_id");
				System.out.println("User id:"+userId);
				
				responseArrList.add(response);
				userIdArrList.add(userId);
				}
}

@Then("check response body and DB validation in Put UserAPI")
public void check_response_body_validation_and_DB_Validation_in_Put_user_api() {
  
	for(int i=0; i<responseArrList.size(); i++)
	{
		
	String responsebody = responseArrList.get(i).body().asString();
	System.out.println("Response body:"+responsebody);
	
	String userId = userIdArrList.get(i); // stores all user id in arraylist
	System.out.println("User id :"+userId);
	
	//Response body validation
	System.out.println("=============RESPONSE BODY VALIDATION ==========");
	Assert.assertEquals(responsebody.contains(userId),true);//validate user id
	Assert.assertEquals(responsebody.contains(data[i][1]),true); //validate user name
	Assert.assertEquals(responsebody.contains(data[i][2]),true); //validate phone number
	Assert.assertEquals(responsebody.contains(data[i][3]),true); //validate location
	Assert.assertEquals(responsebody.contains(data[i][4]),true); //validate time zone
	Assert.assertEquals(responsebody.contains(data[i][5]),true); //validate linked in url
	Assert.assertEquals(responsebody.contains(data[i][6]),true); //validate education ug
	Assert.assertEquals(responsebody.contains(data[i][7]),true);//validate education pg
	Assert.assertEquals(responsebody.contains(data[i][8]),true); //validate visa status
	Assert.assertEquals(responsebody.contains(data[i][9]),true); //validate comments
	
	Assert.assertEquals(responsebody.contains("Successfully Updated !!"),true);//validate message response
	
	System.out.println("=============RESPONSE BODY VALIDATION SUCCESSFULL==========");
	
	//DB Validation
	System.out.println("=============DB VALIDATION==========");
	String Username = data[i][1];
	String Phoneno = data[i][2];
	String Location = data[i][3];
	String timezone = data[i][4];
	String linkedinurl = data[i][5];
	/*String educationug = data[i][6];
	String educationpg = data[i][7];
	String visastatus = data[i][8];
	String Comments = data[i][9];*/
	
	
	response = HttpRequest.request(Method.GET, "https://springboot-lms-userskill.herokuapp.com/Users/"+userId);
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
	/*String education_ug = response.jsonPath().getString("education_ug");
	assertEquals(education_ug, educationug);
	String education_pg = response.jsonPath().getString("education_pg");
	assertEquals(education_pg, educationpg);
	String comments = response.jsonPath().getString("comments");
	assertEquals(Comments, comments);*/


	System.out.println("=============DB VALIDATION SUCCESSFULL==========");
	
	}

}
@Then("Update User check response with status code {int} ok in UserAPI")
public void update_user_check_response_with_status_code_ok_in_user_api(int statuscode) {
   
	for(int i=0; i<responseArrList.size(); i++)
	{
		
	Response statusCode = responseArrList.get(i);
	assertEquals(statusCode.getStatusCode(),statuscode);
	
	}
}

@Then("Update User check Response status line {string} in UserAPI")
public void update_user_check_response_status_line_in_user_api(String statusline) {
	
	for(int i=0; i<responseArrList.size(); i++)
	{
		
	Response statusLine = responseArrList.get(i);
	assertEquals(statusLine.getStatusLine(),statusline);
	
	}	
}

@Then("Update User validate Post Schema {string} in UserAPI")
public void update_user_validate_post_schema_in_user_api(String schemapath) {
	
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

@When("User sends the request body for invalid username in an User API")
public void user_sends_the_request_body_for_invalid_username_in_an_User_API() throws Exception{
    
	rownum = ExcelUtils.getRowCount(userExcelpath, "PUT_INVALID_USERNAME"); //get row count from excel util file
	colnum = ExcelUtils.getCellCount(userExcelpath, "PUT_INVALID_USERNAME", 1); //get col count from excel util file
	
	System.out.println(rownum); //display row values 
	System.out.println(colnum);//display col values 
	String userAPI[][]  = new String[rownum][colnum];

	for (int i = 1; i <= rownum; i++) 
	{
		for (int j = 0; j < colnum; j++) 
		{
			userAPI[i-1][j] = ExcelUtils.getCellData(userExcelpath, "PUT_INVALID_USERNAME", i, j); //i =1, j=0 --> first cell value
				
		}
		
	}	
	
	JSONObject params = new JSONObject();

	for (String[] row : userAPI) {
	
		params.put("user_id", row[0]);
		params.put("name", row[1]);
		params.put("phone_number", row[2]);
		params.put("location", row[3]);
		params.put("time_zone", row[4]);
		params.put("linkedin_url", row[5]);
		params.put("education_ug", row[6]);
		params.put("education_pg", row[7]);
		params.put("visa_status", row[8]);
		params.put("comments", row[9]);
	
	
		HttpRequest = given().auth().basic(username,password).contentType("application/json").body(params.toString());;
		response = HttpRequest.request(Method.PUT, "https://springboot-lms-userskill.herokuapp.com/Users/"+row[0]);
		response.then().log().all(); 
		responseArrList.add(response);
	
		}
	}

@Then("check response body validation for invalid username in Put User API")
public void check_response_body_validation_for_invalid_username_in_Put_UserAPI() {
	

	for(int i=0; i<responseArrList.size(); i++)
	{
		
	String responsebody = responseArrList.get(i).body().asString();
	System.out.println("=============RESPONSE BODY VALIDATION ==========");
	Assert.assertEquals(responsebody.contains("Failed to update existing user, as 'Name' value should be ',' separated !! "),true);//validate message response
	System.out.println("=============RESPONSE BODY VALIDATION SUCCESSFULL==========");
	
	}

}	
@When("User sends put request to update user name for non existing user id")
public void User_sends_put_request_to_update_user_name_for_non_existing_user_id () throws Exception{
	 
	rownum = ExcelUtils.getRowCount(userExcelpath, "PUT_NONEXISTING_USERID"); //get row count from excel util file
	colnum = ExcelUtils.getCellCount(userExcelpath, "PUT_NONEXISTING_USERID", 1); //get col count from excel util file
	
	System.out.println(rownum); //display row values -14
	System.out.println(colnum);//display col values -9
	String userAPI[][]  = new String[rownum][colnum];

	for (int i = 1; i <= rownum; i++) 
	{
		for (int j = 0; j < colnum; j++) 
		{
			userAPI[i-1][j] = ExcelUtils.getCellData(userExcelpath, "PUT_NONEXISTING_USERID", i, j); //i =1, j=0 --> first cell value
				
		}
		
	}	
	
				data=userAPI;
				for (String[] row : userAPI) 
				{
					JSONObject params = new JSONObject();
					params.put("user_id", row[0]);
					params.put("name", row[1]);
					params.put("phone_number", row[2]);
					params.put("location", row[3]);
					params.put("time_zone", row[4]);
					params.put("linkedin_url", row[5]);
					params.put("education_ug", row[6]);
					params.put("education_pg", row[7]);
					params.put("visa_status", row[8]);
					params.put("comments", row[9]);
				
				
				HttpRequest = given().auth().basic(username,password).contentType("application/json").body(params.toString());;
				response = HttpRequest.request(Method.PUT, "https://springboot-lms-userskill.herokuapp.com/Users/"+row[0]);
				response.then().log().all(); 
				String userId = response.jsonPath().getString("user_id");
				System.out.println("User id:"+userId);
				
				responseArrList.add(response);
				userIdArrList.add(userId);
				}
}
@Then("check response body validation for non existing user id in user api")
public void check_response_body_validation_for_non_existing_user_id_in_user_api() {
	

	for(int i=0; i<responseArrList.size(); i++)
	{
		
	String responsebody = responseArrList.get(i).body().asString();
	String userId = response.jsonPath().getString("user_id");
	System.out.println("=============RESPONSE BODY VALIDATION ==========");
	Assert.assertEquals(responsebody.contains("User ID-> "+userId+" Not Found !!"),false);//validate message response
	System.out.println("=============RESPONSE BODY VALIDATION SUCCESSFULL==========");
	
	}
}

}
