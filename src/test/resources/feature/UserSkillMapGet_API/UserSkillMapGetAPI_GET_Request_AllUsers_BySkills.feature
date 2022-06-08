
@feature
Feature: Get request for all User by skills 
  I want to use this template for my feature file
  
@Scenario01
Scenario: Get request for all Users by skills with valid authorization
Given user sends the get request to get all the users by skills records
When user sends the get request to get particular user record with endpoint url "https://springboot-lms-userskill.herokuapp.com/UserSkillsMap/1905"
Then check response body should display all the users
And check Response displays status code 200 OK 
And check Response displays Status Line "HTTP/1.1 200 " 
And check validation schema "Configuration/UserskillMapGet/LMS_Schema_UserSkillMapGetAPI_GET_All_Users_All_Skills_Request.json"  	
  	
@Scenario02
Scenario: Get request for all Users by skills with 
Given user sends the get request to get all the users by skills records
When user sends the get request to get particular user record with endpoint url "https://springboot-lms-userskill.herokuapp.com/UserSkillsMap/190534"
Then check Response displays status code 404 OK 
And check response body validation for non existing skill id in skill api
And check Response displays Status Line "HTTP/1.1 404 "
And check validation schema "Configuration/Users/LMS_Schema_Users_API_GET_NON_EXISTING_USER_ID_Request.json"

 
  	
@Scenario03
Scenario: Get request for all Users by skills with invalid skill id
Given user sends the get request to get all the users by skills records
When user enters valid login data username and password with "https://springboot-lms-userskill.herokuapp.com/UserSkillsMap/212@"
Then check response body should display all the users
Then check response body validation for invalid skill id
And check Response displays status code 404 OK 
And check Response displays Status Line "HTTP/1.1 404 " 
And check validation schema "Configuration/UserskillMapGet/LMS_Schema_UserSkillMapGetAPI_GET_All_Users_All_Skills_INVALIDUSERID_Request2.json"
  	
  