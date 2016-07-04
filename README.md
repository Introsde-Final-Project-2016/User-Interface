# Introduction and App Features

 This is a health care application which works with just one user. Application holds the general information of the user such as name, age. Also the life status information such as weight and height. System also holds the data of the goals of the person. User can see any kind of information by requesting through menu options. User can change the current goals. Program allows user to change the name and amount of the goal. User can also change the his/her weight or height. When there is a change in life status, system automatically updates the BMI value of the person and sets a new goal for the person. This new goal suggest to lose or gain weight to the user. If users condition is okay, system suggest to lose "zero" kg to the user. Any time of the day, user can enter the amount of goal that he/she did. If goal is accomplished the user will get Congrats message. If goal does not accomplished the user gets Random Quote as a motivational message. Also system shows how much left to finish the task.  

## System Architecture Schema

![](https://github.com/Introsde-Final-Project-2016/User-Interface/blob/master/Architecture.png)

## External Adapter Service

This service has to external API connection. One is BMI calculator the other one is Random Famous Quotes. This service sends the necessary JSON packages to the external APIs and then turns back the result to the Storage-Service.

###BMI Calculator
This adapter takes some information such as "weight", "height" and "age" and returns BMI value of the person and the weight suggestion that person should have. The communication between this adapter and Storage service is in REST with JSON objects.

###Random Famous Quotes
This external adapter service obtains random famous quotes. The communication between these services are in REST and uses JSON objects.

##Data Service
Data Service deals with all the data related requests in the application. This service is based on SQLite database and provide data related operations to Storage Service in this application. This service uses SOAP Protocol to communicate with other services.

##Storage Service
This Service collects all kind of necessary data from data-service and external services. While it uses SOAP technologies to connect with data-service, it is using REST technologies with external services. Communication between them is made by using JSON messages. This service sends the requested data to Business-Logic and Process-Centric services by using JSON type message and REST technologies.

##Business Logic Service
Business Logic Service implements all the logics  and get requests that are used in this application. This service has connection with Storage-Service and gets all the information asked by the user and send it to user-service. It uses Rest technologies and JSON type messages. It also has connection with Process-Centric layer. It takes external-data from storage and passes them to the Process-Centric service after obtaining meaningful information. It also make comparison of the current weight and expected weight(coming from external service) and decides the new goal and send this information to Process-Centric layer to set a new goal. 

##Process Centric Service
This service only works when there is update operation. When request comes from the user service it takes the information by using REST technologies and translate it to JSON type messages and sends it to the Storage layer which will send it to the database. When user trigger life status update operation, it calls the compare operation inside the Business-Logic layer. According to new weight or height,  after the return of Business layer, the BMI value of the person is automatically  updated. After the message from Business layer, this service sets a new goal with 3 possible options. Losing weight, gaining weight or keeping that weight. 

##User Interface Service
User Interface Service provides interface where the user can communicate with the application. This service passes JSON object through RESTful APIs to Business Logic Layer and Process Centric Layer. These layers turns back the response to the User interface layer. 

##Further Improvements
This application just provides service with basic console but it can work better with phone-applications. These smart phones automatically calculate how many hours did you run or walk in that day, even they can calculate the sleeping time. In this system everything is manually entered but Smart-Phones can solve this problem. 
There is just weight height and BMI values in the system but there are lots of other measures related with body and health. Such as blood pressure. These features can be added to the application and they can be monitored every day. This system can have connection with the family doctor of the user which can suggest goals, foods, or some restrictions in the users daily life. 
Also this application works for just one user, the login and sign-in interfaces could be done as future improvement to support a lot of user at the same time. 


