@feature
Feature: Delete Request for Skill API
  I want to use this template for my feature file

@Scenario01
  Scenario: Delete Request for Skill API with valid authorization
  Given User enters delete request to delete Skill with valid authorization
  When User sends delete request to delete Skill with valid endpoints and authorization 
  Then Delete skill check response with status code 200 ok in SkillAPI
  Then check response body validation in delete SkillAPI 
  Then Delete skill check Response status line "HTTP/1.1 200 " in SkillAPI
  Then Delete skill validate Schema "Configuration/Skills/LMS_Schema_Skills_API_DELETE_Request.json" in SkillAPI
  
@Scenario02
  Scenario: Delete Request for already deleted records in Skill API with valid authorization
  Given User enters delete request to delete Skill with valid authorization
  When User sends delete request to delete Skill with valid endpoints and authorization
  Then check response body validation for already deleted records SkillAPI
  And Delete skill check response with status code 404 ok in SkillAPI
  And Delete skill check Response status line "HTTP/1.1 404 " in SkillAPI
  And Delete skill validate Schema "Configuration/Skills/LMS_Schema_Skills_API_ALREADY_DELETED_RECORDS_DELETE_Request.json" in SkillAPI
  
  @Scenario03
  Scenario: Delete Request for invalid skill id in Skill API with valid authorization
  Given User enters delete request to delete Skill with valid authorization
  When User sends delete request to delete Skill for invalid skill id with valid endpoints and authorization
  Then check response body validation for invalid skill id 
  And Delete skill check response with status code 404 ok in SkillAPI
  And Delete skill check Response status line "HTTP/1.1 404 " in SkillAPI
  And Delete skill validate Schema "Configuration/Skills/LMS_Schema_Skills_API_GET_INVALID_SKILL_ID_Request.json" in SkillAPI
  
 @Scenario04
  Scenario: Delete Request for non-existing skill id in Skill API with valid authorization
  Given User enters delete request to delete Skill with valid authorization
  When User sends delete request to delete Skill for non existing skill id with valid endpoints and authorization
  Then check response body validation for non existing skill id
  And Delete skill check response with status code 404 ok in SkillAPI
  And Delete skill check Response status line "HTTP/1.1 404 " in SkillAPI
  And Delete skill validate Schema "Configuration/Skills/LMS_Schema_Skills_API_GET_NON_EXISTING_SKILL_ID_Request.json" in SkillAPI 

    
 		