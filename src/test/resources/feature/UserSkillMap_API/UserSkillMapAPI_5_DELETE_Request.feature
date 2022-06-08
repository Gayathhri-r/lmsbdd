#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@feature
Feature: DELETE request for userskill Map API
  I want to use this template for my feature file

@Scenario01
  Scenario: User sends a DELETE request to delete  user skill map in user skill map API with Valid Authorization
    Given User enters the request body to delete user skill map 
    When User sends the request body to delete userskill map API 
    Then User check the response body validation in user skill map 
    Then delete user skill map Check response with status code 200 ok
 		And delete user skill map Check Response status line "HTTP/1.1 200 "
    And delete user skill map Validate Post Schema "Configuration/Userskill/LMS_Schema_UserSkillMap_API_DELETE_Request.json"
  
@Scenario02  
Scenario: Delete Request for already deleted records in UserSkill API with valid authorization
    Given User enters the request body to delete user skill map 
    When User sends the request body to delete userskill map API 
    Then User check the response body validation in user skill map 
    Then delete user skill map Check response with status code 404 ok
 		And delete user skill map Check Response status line "HTTP/1.1 404 "
    And delete user skill map Validate Post Schema "Configuration/Userskill/LMS_Schema_UserSkillMap_API_ALREADY_DELETED_Request.json"
    
@Scenario03  
Scenario: Delete Request for invalid userskill id in UserSkill API with valid authorization
    Given User enters the request body to delete user skill map 
    When User sends the request body to delete invalid userskill id 
    Then User check the response body validation in user skill map 
    Then delete user skill map Check response with status code 404 ok
 		And delete user skill map Check Response status line "HTTP/1.1 404 "
    And delete user skill map Validate Post Schema "Configuration/Userskill/LMS_Schema_UserSkillMap_API_INVALID_USERSKILLID_Request.json"
        