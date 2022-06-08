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
Feature: PUT request for userskill Map API
  I want to use this template for my feature file

@Scenario01
  Scenario: User sends a PUT request to update  user skill map in user skill map API with Valid Authorization
    Given User enters the request body to update user skill map 
    When User sends the request body to update skill map API 
    Then Update user skill map Check response with status code 201 ok
    And Update user skill map check response body and db validations
 		And Update user skill map Check Response status line "HTTP/1.1 201 "
    And Update user skill map Validate Post Schema "Configuration/Userskill/LMS_Schema_UserSkillMap_API_PUT_Request.json"

    
