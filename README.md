# REST Assured API JAVA Automation Framework
## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [About Test Framework](#about-test-framework)
* [Running Tests](#running-tests)
* [Reporting](#reporting)

## General info
 Outline a brief description of this project.
- Rest Assured Framework with TestNG, Maven, Extent Reporting, Allure Reporting.
-  API Used -http://restful-booker.herokuapp.com/apidoc/index.html#api-Booking-GetBookings 
-  Clone the project - git clone https://github.com/String-Gaurav/apiAutomation.git


	
## Technologies
Project is created with:
* JAVA Version - jdk1.8.0_291
* Rest Assured
* Maven
* TestNG 
	
## About Test Framework

**Test Package**

`workingDir/src/test/java`

**Main Package**

`workingDir/src/main/java`

**TestNG XML Path:-**

`workingDir/testng.xml`

**TestData Sheet:-**
Test Data sheet used to pass data in the API's `workingDir/Test_Data/TestData.xlsx`


![image](https://user-images.githubusercontent.com/88622330/131568724-cfad1c03-0cba-41e5-b9ff-8c68cc52e0b6.png)


## Running Tests

**Steps to Configure the project/framework**.

- Clone the project to any location:- `git clone https://github.com/String-Gaurav/apiAutomation.git`
- Import the project in Intellij/Eclipse.
- Navigate to `workingDir/testng.xml`
- Run the xml as Testng confiuration.

* **You can  below commands from CMD**
* **Same Commands can be configured in CICD tools like Jenkins, TeamCity**

**MAVEN COMMANDS:-**

* mvn clean
* mvn compile
* mvn test


## Reporting

With the Extent and Allure library, we can create interactive and detailed reports for our API test results. We can add events, tags, devices, authors or any other relevant information we decide is essential to create an informative and productive report. Test Reports can be found under below paths

* Extent HTML Reporting Path- `workingDir/test-output/Report/test/ExtentReport.html`
* Allure Reporting Detail Request/Response Path- `workingDir/allure-results`


	
 * 1-Reporting of all the test cases.
 * 2-Can check the error logs.
 * 3-Check the screenshot where the test case is failing.
  
** Extent Reporter Spark for HTML**
 * 1-See the percentage of pass/fail test cases
 * 2-Date/OS/etc. details

Sample HTML and Allure reports 


Extent HTML Reporting Path- `workingDir/test-output/Report/test/ExtentReport.html`

![image](https://user-images.githubusercontent.com/88622330/131381582-d4a02ba9-d8cc-4b12-a1d1-55dd71482823.png)

![image](https://user-images.githubusercontent.com/88622330/131381630-7361a787-d9ba-4231-bd49-8f4c09469f1f.png)

Allure Reporting Detail Request/Response Path- `workingDir/allure-results`

![image](https://user-images.githubusercontent.com/88622330/131381700-dc76ef59-381f-439e-b2dc-bcd5460468a3.png)



  
```
