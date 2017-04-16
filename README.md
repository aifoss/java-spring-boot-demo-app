# fixer-demo

Java Spring Boot Demo Application Using Fixer.io API

**How To Run**

***Prerequisites***

Installation of Java (1.8).
Download of fixer-demo-1.0-SNAPSHOT.jar (from /target directory).

***Run Option 1: Command Line***

Start the application by issuing the following command (from the directory where the above jar has been downloaded):

java -jar fixer-demo-1.0-SNAPSHOT.jar FixerDemoApplication.class

On a separate terminal window, issue curl requests, e.g.:

curl "http://localhost:9000/rates?base=USD"
curl "http://localhost:9000/rates?base=USD&target=CAD,EUR,GBP"
curl "http://localhost:9000/rates?base=USD&target=CAD,EUR,GBP&timestamp=2017-03-10T01:10:15Z"

***Run Option 2: UI***

A simple UI is provided for the demo application.

Start the application as described in Run Option 1.

Open a browser and go to http://localhost:9000/.

***Run Option 3: Postman***

In case Postman is installed in the system, 
start the demo application as above,
then send GET requests on Postman using the request URLs as above.
 