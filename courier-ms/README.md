# Courier Service 

***Admin can create a courier account;***
 >  POST /courier-ms/couriers -> POST /auth-ms/users (access level - internal)

***Admin can see list of couriers with their statuses;***
 > GET /courier-ms/status

***Update courier current status based on order status changes that assigned to him/her.***
 > PUT /courier-ms/couriers/status
