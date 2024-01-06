**Project Title**: Social Network API Testing

**Description**

This project is designed to test the Social Network API, focusing on functionalities such as creating posts, commenting on posts, and retrieving a list of users. The tests are written using Java with Rest-Assured and Cucumber, and they are run using TestNG.

**Dependencies**

1. Java JDK 8 or higher
2. Maven
3. Rest-Assured
4. Cucumber
5. TestNG

**Installing**

Clone the repository to your local machine.

Ensure Java and Maven are installed on your system.

Import the project into your favorite IDE as a Maven project.


**Configuration**

Set up the config.properties file in src/test/resources with the required base URI and other configurations.

Update the authentication details in the step definition files as per your API requirements.

**Executing Tests**
To run the tests, use one of the following methods:

**Through IDE**:

Right-click on the TestRunner.java file.

Run it as a TestNG test.

**Using Maven:**

Open a terminal in the project root directory.

Run the command: mvn test.

**Features**

User Posts: Test scenarios for creating and viewing posts.

User Comments: Test scenarios for commenting on posts and viewing comments.

User List: Test scenarios for retrieving a list of users.

**Structure**

src/main/java: Main Java code for the application (if any).

src/test/java: Contains test code.

stepdefinitions: Cucumber step definition classes.

utils: Helper classes and utilities for the tests.

models: Java classes representing JSON structures (if needed).

src/test/resources: Stores feature files and other non-Java resources.

features: Cucumber feature files with BDD scenarios.

config.properties: Configuration properties for the tests.

**Reporting**

Reports are generated in the target/cucumber-reports directory as HTML files.
