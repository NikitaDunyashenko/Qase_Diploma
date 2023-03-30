General information about the platform:

Qase is a test management platform that provides organization, tracking, and reporting for manual and automated software
testing. It allows organizing test cases into logical groups called Test Suites, defining the test case properties, such
as severity and priority. Describe the test case’s pre-conditions, post-conditions, and steps to reproduce.

Checklist:

1. Authentification
   a) signing in (entering correct email and password)
   b) signing in (entering incorrect email and password)
   b) signing out

2. Operations with projects
   a) creating a new project
   b) deleting a created project
   d) updating project attributes

3. Operations with suites
   a) creating a new suite
   b) deleting a created suite

4. Operations with test cases
   a) creating a new test case

5. Operations with defects
   a) creating a new defect
   b) updating properties of defect
   c) deleting a created defect

6. Operations with milestones:
   a) creating a new milestone

7. Operations with searcher:
   a) search for a specific project
   b) search for the project by a keyword

Mandatory to be installed:

1. Java JDK: 18
2. Maven version: 3.0.0-M5
3. Allure version: 2.10.0

Libraries used in the project:

1. selenium-java (version: 3.141.59)
2. testNG (version: 7.4.0)
3. WebDriverManager (version: 5.3.0)
4. allure-testng (version: 2.18.1)
5. lombok (version: 1.18.24)
6. log4j-core (version: 2.19.0)
7. jackson-dataformat-yaml (version: 2.12.3)
8. javafaker (version: 1.0.2)
9. rest-assured (version: 5.3.0)
10. gson (version: 2.10)

Instructions before running tests:

1. Clone the repository;
2. Change username and password in config.properties file to your owns.

Instructions for running tests:

1. Smoke Tests: mvn clean test -DsuiteXmlFile = "smokeTests.xml"
2. Regression Tests: mvn clean test -DsuiteXmlFile = "regressionTests.xml"
3. Smoke API Tests: mvn clean test -DsuiteXmlFile = "smokeApiTests.xml"
4. Regression API Tests: mvn clean test -DsuiteXmlFile = "regressionApiTests.xml"

Instructions for generating a report:
allure serve target/allure-results




