
@feature
Feature: PUT Request for User API
  I want to use this template for my feature file

@Scenario01
Scenario: User sends a PUT request to Update user detail in UserAPI with Valid Authorization
Given User enters put request to update UserAPI with valid authorization 
When User sends put request to update UserAPI with valid endpoints and authorization 
Then Update User check response with status code 201 ok in UserAPI
And check response body and DB validation in Put UserAPI
And Update User check Response status line "HTTP/1.1 201 " in UserAPI
And Update User validate Post Schema "Configuration/Users/LMS_Schema_User_API_PUT_Request.json" in UserAPI

@Scenario02
Scenario: User sends a PUT request to Update invalid username in SkillAPI with Valid Authorization
Given User enters put request to update UserAPI with valid authorization 
When User sends the request body for invalid username in an User API
Then check response body validation for invalid username in Put User API
And Update User check response with status code 400 ok in UserAPI
And Update User check Response status line "HTTP/1.1 400 " in UserAPI
And Update User validate Post Schema "Configuration/Users/LMS_Schema_User_API_PUT_INVALID_USERNAME.json" in UserAPI

@Scenario03
Scenario: User sends a PUT request to Update user name to non existing user ID in SkillAPI with Valid Authorization
Given User enters put request to update UserAPI with valid authorization
When User sends put request to update user name for non existing user id 
Then check response body validation for non existing user id in user api
And Update User check response with status code 404 ok in UserAPI
And Update User check Response status line "HTTP/1.1 404 " in UserAPI
And validate Post Schema "Configuration/Skills/LMS_Schema_User_API_PUT_INVALID_USERID.json" in API