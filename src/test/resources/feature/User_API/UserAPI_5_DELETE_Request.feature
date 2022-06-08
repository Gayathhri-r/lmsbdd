
@feature
Feature: PUT Request for User API
  I want to use this template for my feature file

@Scenario01
Scenario: User sends a DELETE request to delete user in UserAPI with Valid Authorization
Given User enters delete request to delete userAPI with valid authorization 
When User sends delete request to delete a user in UserAPI with valid endpoints and authorization 
Then Delete User check response with status code 200 ok in UserAPI
Then check response body validation in delete UserAPI
And Delete User check Response status line "HTTP/1.1 200 " in UserAPI
And Delete User validate Post Schema "Configuration/Userskill/LMS_Schema_User_API_DELETE_Request.json" in UserAPI

    
@Scenario02
Scenario: User sends a DELETE request for already deleted user in UserAPI with Valid Authorization
Given User enters delete request to delete userAPI with valid authorization 
When User sends delete request to delete a user in UserAPI with valid endpoints and authorization 
Then check response body validation for already deleted records User API
And Delete User check response with status code 404 ok in UserAPI
And Delete User check Response status line "HTTP/1.1 404 " in UserAPI
And Delete User validate Post Schema "Configuration/Users/LMS_Schema_User_API_DELETE_EXISTING_RECORD.json" in UserAPI





  