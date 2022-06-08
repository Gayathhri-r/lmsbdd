
@feature
Feature: POST Request for Skill API
  I want to use this template for my feature file

@Scenario01
Scenario: User sends a POST request to create new skill in skill API with Valid Authorization
Given User enters the request body to insert new skill 
When User sends the request body in an skill API  
Then Create Skill Check response with status code 201 ok 
And check response body validation for Post Skill API
And Create Skill Check Response status line "HTTP/1.1 201 "
And Create Skill Validate Post Schema "Configuration/Skills/LMS_Schema_Skills_API_1_POST_Request.json"

@Scenario02
Scenario: User sends a POST request for already existing skill in skill API with Valid Authorization
Given User enters the request body to insert new skill 
When User sends the request body in an skill API
Then Create Skill Check response with status code 400 ok
And check response body validation for existing skill Post Skill API 
And Create Skill Check Response status line "HTTP/1.1 400 "
And Create Skill Validate Post Schema "Configuration/Skills/LMS_Schema_Skills_API_1_EXISTING_RECORD_POST_Request.json"

@Scenario02
Scenario: User sends a POST request for invalid skillname in skill API with Valid Authorization
Given User enters the request body to insert new skill 
When User sends the request body for invalid skillname 
Then Create Skill Check response with status code 400 ok
And check response body validation for invalid skill name
And Create Skill Check Response status line "HTTP/1.1 400 "
And Create Skill Validate Post Schema "Configuration/Skills/LMS_Schema_Skills_API_1_POST_INVALID_SKILLNAME_Request.json"  


 		
