package BaseClass;

import static org.testng.Assert.assertEquals;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hamcrest.Matcher;
import org.testng.annotations.BeforeClass;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static io.restassured.RestAssured.*;

public class BaseClass{
	
	
	public String baseURL = "https://springboot-lms-userskill.herokuapp.com";
	public String userbasePath =  "/Users";
	public String skillbasePath = "/Skills";
	public String userskillbasePath = "/UserSkills";
	public String userskillmapgetbasePath = "/UserSkillsMap";
	public String username = "APIPROCESSING";
	public String password = "2xx@Success";
	public String skillExcelpath = "./src/test/resources/Configuration/RestAssured_LMS_BDD_Cucumber_Skill.xlsx";
	public String userExcelpath = "./src/test/resources/Configuration/RestAssured_LMS_BDD_Cucumber_User.xlsx";
	public String userskillExcelpath = "./src/test/resources/Configuration/RestAssured_LMS_BDD_Cucumber_Userskill.xlsx";
	public static RequestSpecification HttpRequest;
	public static Response response;
	public static  ValidatableResponse json;
	public static int rownum;
	public static int colnum;
	
	public void createRequest(String userName, String password) {
		HttpRequest = given()
				.baseUri(baseURL)
				.auth()
				.basic(userName, password)
				.header("content-Type","application/json");
	}
	
	public void call(String path, Method method) {
		response = HttpRequest.request(method, path);
	}
	
	public void validateStatus(int status) {
		
		assertEquals(response.getStatusCode(), status);
	}
	
	public void validateStatusLine(String statusLine) {
		
		assertEquals(response.getStatusLine(), statusLine);
		
	}
	public void validateContentType(String contentType) {
		
		assertEquals(response.getContentType(), contentType);
	}
		

	public void validateSchema(String schemaPath) {
		response
		.then()
		.assertThat().
		body(matchesJsonSchemaInClasspath(schemaPath));
	}

		
}

