# Delivery Service 

***User can see all parcel delivery orders that he/she created;***

***Courier can view all parcel delivery orders that assigned to him;***

***Admin can view all parcel delivery orders;***

 > GET /delivery-ms/orders

***User,Courier can see the details of a delivery order;***

 > GET /delivery-ms/orders/{orderId}

***User can cancel parcel delivery order to courier;***

 > PUT /delivery-ms/orders/{orderId}/cancel 

***Admin can assign parcel delivery order to courier;***

 > PUT /orders/{orderId}/courier/{courierUserId}
 
 *For further improvement, it can be accomplished by automatically assigning newly created orders to couirers that are available using Message Brokers.*

***Admin,Courier can change the status of a parcel delivery order;***
 > PUT /orders/{orderId}

***Can track the delivery order by coordinates;***

*Further development by using WebSocket*

***Used tecnhologies***
- Build tool - Gradle
- Testing - Spock
- Database migration - Liquibase
 

   
