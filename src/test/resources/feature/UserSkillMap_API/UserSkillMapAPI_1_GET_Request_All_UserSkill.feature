
@feature
Feature: Get request for all user skills
  I want to use this template for my feature file

  
@Scenario01
Scenario: Get request for all User Skills with valid authorization
Given user sends the get request to get all the user skill records with URL and endpoint URL 
When user enters valid login data username and password with "https://springboot-lms-userskill.herokuapp.com/UserSkills"
Then check response body should display all the users
And check Response displays status code 200 OK 
And check Response displays Status Line "HTTP/1.1 200 " 
And check validation schema "Configuration/Userskill/LMS_Schema_UserSkillMap_API_GET_Request.json"
  	
@Scenario02
Scenario: Get request for all User Skills with invalid authorization
Given user sends the get request to get all the user skill records with URL and endpoint URL 
When user enters invalid login data username and password with "https://springboot-lms-userskill.herokuapp.com/UserSkills"
Then check response body should display all the users
And check Response displays status code 401 OK 
And check Response displays Status Line "HTTP/1.1 401 " 	
  	
 @Scenario03
Scenario: Get request for all User Skills with invalid endpoint url
Given user sends the get request to get all the user skill records with URL and endpoint URL 
When user enters valid login data username and password with "https://springboot-lms-userskill.herokuapp.com/UserSkill"
Then check response body should display all the users
And check Response displays status code 404 OK 
And check Response displays Status Line "HTTP/1.1 404 "
And check validation schema "Configuration/Skills/LMS_Schema_API_INVALID_ENDPOINT_GET_Request.json"  
   	

  	

