Near Shops
--------------

this application allow you list shops nearby you sorted by the distance,
 you can like a shop to be added to your preferred list or dislike him 

What's inside
---------------
- Spring boot : web, security, thymeleaf, data-mongo
- jsonWebToken
- angular 7

Code Structure and quality
----------------
### Backend
- I adopt for MCS (Model, Controller, Security) structure with 
- self-explanatory naming for packages, classes, methods and variables to avoid comments as possible
- the two REST endpoints  are in the level 2 of the Richardson Maturity Model with the using of verbs with the possibility to upgrade them to the level 3 supporting HATEOAS


### Frontend

- the app stays loyal to angular paradigm with the same naming conventions



Main Features
---------------

- [x] As a User, I can sign up using my email & password
- [x] As a User, I can sign in using my email & password
- [x] As a User, I can display the list of shops sorted by distance
- [x] As a User, I can like a shop, so it can be added to my preferred shops
  - Acceptance criteria: liked shops shouldn’t be displayed on the main page

Optional features
---------------
- [x] As a User, I can dislike a shop, so it won’t be displayed within “Nearby Shops” list during the next 2 hours
- [x] As a User, I can display the list of preferred shops
- [x] As a User, I can remove a shop from my preferred shops list

What's the app doesn't do yet
-----------------------------
- in the client side there's no exceptions handling of the api calling we suppose that the api always returns 200 status
- the app doesn't notify the user if any exception occurred


Installation
-----------------
please run this step in the order that they appear on

#### database

cd src/main/resources and run 
```
mongorestore dump-shops
```
#### Frontend
cd src/main/resources/angular-client and run
```
npm install && ng build
```

#### Backend
cd the project root folder and run 
```
mvn spring-boot:run
```

visit the url  http://localhost:8090

if the port 8090 is already in use please go to src/main/resources/application.properties and change the server.port property to any available port 
