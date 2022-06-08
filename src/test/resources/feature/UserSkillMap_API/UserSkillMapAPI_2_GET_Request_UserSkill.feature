
@feature
Feature: Get request for a particular user skills
  I want to use this template for my feature file

  
@Scenario01
Scenario: Get request for a particular user with skill with valid authorization
Given user sends the get request to get all the user skill records 
When user sends the get request to get particular user record with endpoint url "https://springboot-lms-userskill.herokuapp.com/UserSkills/US2855"
And Update user skill map check response body and db validations
And check Response displays status code 200 OK 
And check Response displays Status Line "HTTP/1.1 200 " 
And check validation schema "Configuration/Userskill/LMS_Schema_UserSkillMap_API_GET_Userskill_Request.json"


@Scenario02
Scenario: Get request for non existing User with valid authorization

Given user sends the get request to get all the user skill records  
When user sends the get request to get particular user record with endpoint url "https://springboot-lms-userskill.herokuapp.com/UserSkills/U21234234"
Then check Response displays status code 404 OK 
Then check response body validation for non existing user id in user api
 And check Response displays Status Line "HTTP/1.1 404 "
And check validation schema "Configuration/Users/LMS_Schema_Users_API_GET_NON_EXISTING_USER_ID_Request.json"
 	
@Scenario03
Scenario: Get request for invalid user Id with valid authorization
Given user sends the get request to get a particular user record  
When user sends the get request to get particular user record with endpoint url "https://springboot-lms-userskill.herokuapp.com/UserSkills/212@"
Then check Response displays status code 404 OK 
Then check response body validation for invalid skill id
And check Response displays Status Line "HTTP/1.1 404 " 
And check validation schema "Configuration/Users/LMS_Schema_Users_API_GET_INVALID_User_ID_Request.json" 

  	

