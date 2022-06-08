
@feature
Feature: Title of your feature
I want to use this template for my feature file

@Scenario01
Scenario: Get request for a single Skills with valid authorization
Given user enters the get request to get particular skill record with URL and endpoint URL 
When user sends the get request to get particular skill record with endpoint url "https://springboot-lms-userskill.herokuapp.com/Skills/1095"
And check response body validation for Post Skill API
And check Response displays status code 200 OK
And check Response displays Status Line "HTTP/1.1 200 "
And check validation schema "Configuration/Skills/LMS_Schema_Skills_API_GET_Skill_ID_Request.json" 
  	
@Scenario02
Scenario: Get request for non existing Skills with valid authorization
Given user enters the get request to get particular skill record with URL and endpoint URL   
When user sends the get request to get particular skill record with endpoint url "https://springboot-lms-userskill.herokuapp.com/Skills/2183453"
Then check response body validation for non existing skill id
Then check Response displays status code 404 OK 
 And check Response displays Status Line "HTTP/1.1 404 "
And check validation schema "Configuration/Skills/LMS_Schema_Skills_API_GET_NON_EXISTING_SKILL_ID_Request.json"

 @Scenario03
Scenario: Get request for invalid Skill Id with valid authorization
Given user enters the get request to get particular skill record with URL and endpoint URL  
When user sends the get request to get particular skill record with endpoint url "https://springboot-lms-userskill.herokuapp.com/Skills/2166#"
Then check Response displays status code 404 OK
And check response body validation for invalid skill id 
And check Response displays Status Line "HTTP/1.1 404 " 
And check validation schema "Configuration/Skills/LMS_Schema_Skills_API_GET_INVALID_SKILL_ID_Request.json" 

 
  	
 