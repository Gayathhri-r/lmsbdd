
@feature
Feature: Get Request for all users
 I want to use this template for my feature file

@Scenario01
Scenario: Get request for all Users with valid authorization
Given user sends the get request to get all the users records
When user enters valid login data username and password with "https://springboot-lms-userskill.herokuapp.com/Users"
Then check response body should display all the users
And check Response displays status code 200 OK 
And check Response displays Status Line "HTTP/1.1 200 " 
And check validation schema "Configuration/Users/LMS_Schema_Users_API_GET_Request.json"
  	
  	
@Scenario02
Scenario: Get request for all Users with invalid authorization
Given user sends the get request to get all the users records 
When user enters invalid login data username and password with "https://springboot-lms-userskill.herokuapp.com/Users"
Then check response body should display all the users
And check Response displays status code 401 OK 
And check Response displays Status Line "HTTP/1.1 401 " 	
  	
@Scenario03
Scenario: Get request for all Users with invalid url
Given user sends the get request to get all the users records 
When user enters valid login data username and password with "https://springboot-lms-userskill.herokuapp.com/User"
Then check response body should display all the users
And check Response displays status code 404 OK 
And check Response displays Status Line "HTTP/1.1 404 "
And check validation schema "Configuration/Skills/LMS_Schema_API_INVALID_ENDPOINT_GET_Request.json"  
 
  
  