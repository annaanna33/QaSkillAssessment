#QA Skill Assessment
##Framework: 
* Java 
* Selenium
     - for UI testing 
* TestNG
    - assertion/controlling the flow of test
* RestAssured 
    - api testing
* JavaFaker 
    - generate  random data
* Following `POM` and `Singleton` design patterns
* Generates `HTML report`. Attaches screenshot to report at failed tests

###Utilities
**BrowserUtils:**
- Commonly used method `explicit waits`, `implicit waits` etc

**ConfigurationReader**
- Read the data from `configuration.properties` file

**ContactUtils**
- Commonly used method to create `contact`, `search/edit/delete contact by name`, etc

**Driver**
- Used for instantiating driver.
- Singleton design
    - Made constructor private to avoid creating instance, uses public getter/setter 
- Reads driver value from `configuration.properties` file

**Listener**
- TestNG listener class
    - used for running logic `before/after` `test/suite` (ex. taking screenshot on test failure)
    


