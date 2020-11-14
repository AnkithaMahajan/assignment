# assignment
1. Build all projects :

mvn clean install 

2. Start all the jars :

Task 1 -

java -jar greetword-0.0.1.jar
java -jar personname-0.0.1.jar
java -jar greetperson-0.0.1.jar

Task 2-

java -jar pplcategory-0.0.1.jar

3. Api urls and request:

Task 1 -

GET - http://localhost:8081/word/greet-word
POST - http://localhost:8082/person/person-name
GET - http://localhost:8083/greet/server-status
POST - http://localhost:8083/greet/greet-person

Sample Request for above POSTs :
{
    "firstname" : "Sidney",
    "lastname" : "Sheldon"
}

Task 2 - 

GET - http://localhost:8084/people/people-categories
GET - http://localhost:8084/people/person-info?id=2





