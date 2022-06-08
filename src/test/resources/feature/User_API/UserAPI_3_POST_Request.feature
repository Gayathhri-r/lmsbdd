
@feature
Feature: POST Request for User API
  I want to use this template for my feature file

@Scenario01
Scenario: User sends a POST request to create new user in User API with Valid Authorization
Given User enters the request body to insert users 
When User sends the request body in an User API 
Then Create User Check response with status code 201 ok
And check response body validation and DB Validation for Post User API
And Create User Check Response status line "HTTP/1.1 201 "
And Create User Validate Post Schema "Configuration/Users/LMS_Schema_User_API_POST_Request.json"
    

@Scenario02
Scenario: User sends a POST request for an existing user with valid Authorization
Given User enters the request body to insert users
When User sends the request body in an User API 
Then Create User Check response with status code 400 ok
And check response body validation for existing User Post UserAPI
And Create User Check Response status line "HTTP/1.1 400 "
And Create User Validate Post Schema "Configuration/Users/LMS_Schema_User_API_POST_EXISTING_RECORD.json"
 
@Scenario03   
Scenario: User sends a POST request for an invalid username with valid Authorization
Given User enters the request body to insert users
When User sends the request body for invalid user in an User API
Then Create User Check response with status code 400 ok
And check response body validation for invalid username in Post User API
And Create User Check Response status line "HTTP/1.1 400 "
And Create User Validate Post Schema "Configuration/Users/LMS_Schema_User_API_POST_INVALID_USERNAME.json"


  