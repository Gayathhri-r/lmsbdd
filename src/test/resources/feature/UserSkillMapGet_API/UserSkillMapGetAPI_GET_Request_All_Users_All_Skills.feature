
@feature
Feature: Get request for all user all skills
  I want to use this template for my feature file

  
@Scenario01
Scenario: Get request for all Users with the skills with  valid authorization
Given user sends the get request to get all the users all skills records
When user enters valid login data username and password with "https://springboot-lms-userskill.herokuapp.com/UserSkillsMap"
Then check response body should display all the users
And check Response displays status code 200 OK 
And check Response displays Status Line "HTTP/1.1 200 " 
And check validation schema "Configuration/UserskillMapGet/LMS_Schema_UserSkillMapGetAPI_GET_All_Users_All_Skills_Request.json"  	
  	
@Scenario02
Scenario: Get request for all Users with the skills with invalid authorization
Given user sends the get request to get all the users all skills records
When user enters invalid login data username and password with "https://springboot-lms-userskill.herokuapp.com/UserSkillsMap"
Then check response body for all the usersskill
And check Response displays status code 401 OK 
And check Response displays Status Line "HTTP/1.1 401 " 
 
  	
@Scenario03
Scenario: Get request for all  Users with the skills with invalid url
Given user sends the get request to get all the users all skills records
When user enters valid login data username and password with "https://springboot-lms-userskill.herokuapp.com/UserSkillsMa"
Then check response body should display all the users
And check Response displays status code 404 OK 
And check Response displays Status Line "HTTP/1.1 404 "
And check validation schema "Configuration/Skills/LMS_Schema_API_INVALID_ENDPOINT_GET_Request.json"