
@feature
Feature: Post request for new User skill feature
  I want to use this template for my feature file

@Scenario01
Scenario: User sends a POST request to create new user skill map in user skill API with Valid Authorization
Given User enters the request body to insert user skill map 
When User sends the request body in an user skill map API 
Then Create user skill map Check response with status code 201 ok
And check response body and db validation for Post userSkill API
And Create User Skill Check Response status line "HTTP/1.1 201 "
And Create user skill map Validate Post Schema "Configuration/Userskill/LMS_Schema_UserSkillMap_API_POST_Request.json"
    
@Scenario02
Scenario: User sends a POST request for invalid User in User skill API with Valid Authorization
Given User enters the request body to insert user skill map  
When User sends the request body for an invalid user id inUser skill API 
Then Create user skill map Check response with status code 404 ok
And check responsebody validation for post userskill map
And Create User Skill Check Response status line "HTTP/1.1 404 "
And Create user skill map Validate Post Schema "Configuration/Userskill/LMS_Schema_UserSkillMap_API_INVALID_USERID_POST_Request.json"
