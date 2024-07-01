Cache Management Example Springboot REST API

This application utilizes the springboot-cache dependency and implements a basic feature of the same. 

#Pre Requisites:
  - Java SDK 1.8
  - Any IDE supporting Springboot

#How to run:
  - clone or download the application to your local
  - Load it in STS or any IDE.
  - Build and resolve all the dependencies.
  - Run as Springboot Application
  - go to http://localhost:8081/cache-manager/swagger-ui.html and Try the apis.

#Info :
  - Each cache will be available for 15 minutes by default after which it will be removed automatically.
  - /books & /books/{id} APIs are implemented with caching mechanism.
  - A delay of 5 seconds is added into these apis to visualize the performance of cache.

#happy_coding
