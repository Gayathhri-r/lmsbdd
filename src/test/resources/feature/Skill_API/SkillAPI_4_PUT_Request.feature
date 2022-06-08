@feature
Feature: PUT Request for Skill API
  I want to use this template for my feature file

@Scenario01
Scenario: User sends a PUT request to Update skill name in SkillAPI with Valid Authorization
Given User enters put request to update skillAPI with valid authorization
When User sends put request to update SkillAPI with valid endpoints and authorization 
Then check response with status code 201 in SkillAPI
And check response body validation in Put SkillAPI
And check Response status line "HTTP/1.1 201 " in SkillAPI
And validate Post Schema "Configuration/Skills/LMS_Schema_Skills_API_2_PUT_Request.json" in API
  
@Scenario02
Scenario: User sends a PUT request to Update existing skill name in SkillAPI with Valid Authorization
Given User enters put request to update skillAPI with valid authorization
When User sends put request to update skill name with already available skillname 
Then check response with status code 400 in SkillAPI
And check response body validation for existing skill name to an  existing skill id
And check Response status line "HTTP/1.1 400 " in SkillAPI
And validate Post Schema "Configuration/Skills/LMS_Schema_Skills_API_2_PUT_UPDATE_EXISTING_SKILLNAME.json" in API
    
@Scenario03
Scenario: User sends a PUT request to Update skill name to non existing Skill ID in SkillAPI with Valid Authorization
Given User enters put request to update skillAPI with valid authorization
When User sends put request to update skill name for non existing skill id 
Then check response body validation for non existing skill id in skill api
And check response with status code 404 in SkillAPI
And check Response status line "HTTP/1.1 404 " in SkillAPI
And validate Post Schema "Configuration/Skills/LMS_Schema_Skills_API_2_NON_EXISTING_SKILLID_PUT_Request.json" in API

@Scenario04
Scenario: User sends a PUT request to Update invalid skill name in SkillAPI with Valid Authorization
Given User enters put request to update skillAPI with valid authorization
When User sends put request to update invalid skill name 
Then check response body validation for invalid skill name
And check response with status code 400 in SkillAPI
And check Response status line "HTTP/1.1 400 " in SkillAPI
And validate Post Schema "Configuration/Skills/LMS_Schema_Skills_API_2_PUT_INVALID_SKILLNAME_Request.json" in API
 