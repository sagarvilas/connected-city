# connected-city
A simple spring-boot app to check if two cities are connected.
Takes source and destination as query parameters and returns "yes" or "no" 
depending on connetivity between the cities.

## Getting Started
You run it using the ```java -jar``` command.

* Clone this repository 
* Make sure you are using JDK 1.8 and Maven 3.x
* You can build the project and run the tests by running ```mvn clean install```
* Once successfully built, you can run the service by one of these two methods:
```
        java -jar target/connected-city.jar
or
        mvn spring-boot:run
```
 
 By default cities are read from resources/cities.txt, this location can be changed by 
 updating value of "file.path" in application.properties or by provinding commandline arguments
 ```
        java -jar target/connected-city.jar --file.path=file:<your file>
or
        mvn spring-boot:run -Dspring-boot.run.arguments=--file.path=file:<your file>
```
 
 ## API Documentation
 Documentation is provided using swagger, to get visit http://localhost:8080/swagger-ui.html
 
 ## Code Coverage
 Code coverage report is provided by JaCoCo, to get coverage report go to target/jacoco-result/index.html

 ## Behind the Scenes
 Behind the scenes a graph is created, with cities as nodes and list of neighbours.
 While looking for path BFS is used, this logic can be changed by implementing PathFinder
 interface and providing implementation for isNodeConnected method.
