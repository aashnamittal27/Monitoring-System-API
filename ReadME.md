# Monitoring-System-API


The Monitoring System takes patient's vital signs as input from patient-monitoring device. When a parameter goes out-of-range, an alert is generated.

## Index
1. [Prerequisites](#Prerequisites)
2. [Compiling](#Compiling)
3. [Executing](#Executing)
4. [APIs Created](#ApiCreated)
5. [Goal Set and Achieved](#Goalset)

## Prerequisites
Firstly, the project makes use of a batch file in order to build the source files and hence, the target system must have a Windows environment.
Then, the target system must have Java (>=12) installed and available in the system path.
The project makes use of Maven as the build tool and hence, Maven must also be in the system path.

## Compiling
In the root directory of the project, a batch file by the name "AutoBuildTest.bat" is present. 
Run the batch file in the command line and it will compile the sources and execute the test cases.  
```
./../Monitoring-System-API> AutoBuildTest.bat
```  
Once the batch file has sucessfully executed, you should be able to see a `"build successful"` message.

NOTE 1: This AutoBuildTest.bat also runs JaCoCo code coverage library and you can check the code coverage report by opening `"index.html"` in browser. 

```
./target/site/jacoco> index.html
```

NOTE 2: This AutoBuildTest.bat also runs javancss cyclomatic cmplexity measuring library and you can check the cyclomatic complexity report by opening `"javancss.html"` in browser. 

```
./target/site> javancss.html
```


## Execution
Once the batch file has been run, a binary will be put in the target folder and it will have the name "casestudy-0.0.1-SNAPSHOT.jar".
In order to run the application, move to target folder and  open the commandline and copy paste the below command.  
```
java -jar casestudy-0.0.1-SNAPSHOT.jar
```  


## APIs Created





### Bed Service API
| HTTP Mehtod | API URL        | Description                                                                | Success Response                                 | Failure Response  |
|-------------|----------------|----------------------------------------------------------------------------|--------------------------------------------------|-------------------|
| POST        | "/bed"      | This adds a new bed to the system with is available status as "yes" | 201 status code with "CREATED"    | -- |
| GET         | "/bed"      | Fetches list of all beds in the system along with their status          | 200 status code   'OK'                 | --                |
| GET         | "/bed/{id}" | Fetches the details of bed with the BedID = "id"                                   | 200 Status code 'OK' | 404 status code 'Not Found'     |
| DELETE      | "/bed/{id}  | Deletes specified record of bed                                                  | 204 status code 'NO CONTENT'                                   | 400 status code 'BAD REQUEST'     |



### Patient Service API

| HTTP Mehtod | API URL        | Description                                                                | Success Response                                 | Failure Response  |
|-------------|----------------|----------------------------------------------------------------------------|--------------------------------------------------|-------------------|
| POST        | "/patient"      | This adds a new patient to the system | 201 status code 'CREATED'  | 400 status code 'BAD REQUEST' |
| GET         | "/patient"      | Fetches list of all patient along with their details in the system              | 200 status code 'OK'                   | --                |
| GET         | "/patient/{pid}" | Fetches the details of the particular patient with the pid = "id"                                   | status code 200 with 'NO CONTENT'  | status code 404 'NOT FOUND'    |
| DELETE      | "/patient/{pid}"  | Deletes specific  record of the patient                                                   | status code 204 with 'NO CONTENT'                                   | status code 404 'NOT FOUND'     |





### Vital Moniter Service API

| HTTP Mehtod | API URL        | Description                                                                | Success Response                                 | Failure Response  |
|-------------|----------------|----------------------------------------------------------------------------|--------------------------------------------------|-------------------|
| POST        | "/monitor"      | This API gives the vital status of all the patients who are on bed | 200 status code 'OK'  | -- |

For more info on the API's, start the springboot application and goto 
http://localhost:8080/swagger-ui.html on the browser.
