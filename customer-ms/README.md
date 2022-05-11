# Customer Service 

***Customer can create an user account***
 >   POST /customer-ms/customers  ->  POST /auth-ms/users (access level -> internal)
 
*Future development may inclue*
- Modify customer details - PUT  /customer-ms/customers/{customerId}
- Add payment options for customer - POST /customer-ms/payment-methods
- Implemet authorization filter while developing above endpoints

*Used Technologies*
- Build tool - Maven
- Testing - Junit5
- Migration tool - Liquibase
