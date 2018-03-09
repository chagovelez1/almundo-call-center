#CALL-CENTER

This ms is intended to allow creation and management of call centeer agents and assign them the incoming calls based on the disponibility and type of agent

1. [Built With](#built-with)
3. [Deployment](#deployment)
4. [Authors](#authors)
5. [License](#license)

##Built With

- Java  & Spring Framework & Spring Boot - Web Framework
- Maven

##Deployment
For deployment you have to set the next environments (examples)

---
- MONGO_NAME: mongo database name
- MONGO_HOST: mongo database host
- MONGO_PORT: mongo database port
- VALUES_CRON_JOB_CHECK_UNASIGNED_CALLS_MILLIS : miliseconds to execute the check of unasigned calls and try to assign them

(THE PORT BY DEFAULT THAT WORK THE SERVICE IS 8080)


##Authors
- Santiago Velez - santiago.velez.913@gmail.com

##License
This project is property of Almundo.
