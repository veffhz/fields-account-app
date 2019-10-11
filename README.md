# Spring demo project (mvc, data, thymeleaf)

#### requirements:
java 8, maven, postgres (schema grain).

#### build
`mvn clean package`

#### run
`mvn spring-boot:run` or `java -jar target/fields-account-app.jar`

### documentation

Domain model:
* account
* field

Pages:
* / -> home

Rest apis:
* /api/field GET PUT
* /api/field/{fieldId} GET POST DELETE
* /api/account GET
* /api/account/{account_id} GET
