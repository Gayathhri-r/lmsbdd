@feature
Feature: Get request for all skills
I want to use this template for my feature file

@Scenario01
Scenario: Get request for all Skills with valid authorization
Given user sends the get request to get all the skill records
When user enters valid login data username and password with "https://springboot-lms-userskill.herokuapp.com/Skills"
Then check response body should display all the skills
And check Response displays status code 200 OK 
And check Response displays Status Line "HTTP/1.1 200 "
And check validation schema "Configuration/Skills/LMS_Schema_Skills_API_GET_Request.json"
  	
  	
@Scenario02
Scenario: Get request for all Skills with invalid authorization
Given user sends the get request to get all the skill records 
When user enters invalid login data username and password with "https://springboot-lms-userskill.herokuapp.com/Skills" 
Then check response body should display all the skills
And check Response displays status code 401 OK 
And check Response displays Status Line "HTTP/1.1 401 " 	
  	
@Scenario03
Scenario: Get request for all Skills with invalid url
Given user sends the get request to get all the skill records 
When user enters valid login data username and password with "https://springboot-lms-userskill.herokuapp.com/Skill" 
Then check response body should display all the skills
And check Response displays status code 404 OK 
And check Response displays Status Line "HTTP/1.1 404 " 
And check validation schema "Configuration/Skills/LMS_Schema_API_INVALID_ENDPOINT_GET_Request.json"
 
