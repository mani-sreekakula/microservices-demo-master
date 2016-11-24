# microservices-demo



## Run the Services from Command Line

The three services need to run in three different terminals.

To do this, we need to open three CMD windows (Windows) or three Terminal windows (MacOS, Linux) and arrange so we can view them conveniently.

 1. In each window, change to the directory where you cloned the demo
 1. In the first window, build the application using `mvn clean package`
 1. In the same window run: `java -jar target/microservice-demo-1.1.0.RELEASE.jar registration` and wait for it to start up
 1. Switch to the second window and run: `java -jar target/microservice-demo-1.1.0.RELEASE.jar products-catalog` and again wait for
 it to start up
 1. In the third window run: `java -jar target/microservice-demo-1.1.0.RELEASE.jar pricing`
 1. In any of the browsers open the link: [http://localhost:1111](http://localhost:1111)

We should see servers being registered in the log output of the (registration) window.
 1. Registration Service will be running in [http://localhost:1111](http://localhost:1111)
 1. Product Catalog Service will be running in [http://localhost:2025](http://localhost:2025)
 1. Pricing Service will be running in [http://localhost:2028](http://localhost:2028)
 
These are the following valid requests accepted by implemented microservices:
 1. To add a new product in to the system Ex: [http://localhost:2025/products/add?productName=PPP&productType=PPP&price=111](http://localhost:2025/products/add?productName=PPP&productType=PPP&price=111)
 1. To retrieve list of products based on product type Ex: [http://localhost:2025/products/type/PT1](http://localhost:2025/products/type/PT1)
 1. To remove a product from catalog Ex: [http://localhost:2025/products/delete/1](http://localhost:2025/products/delete/1)
 1. To get the price of a given product Ex: [http://localhost:2028/pricing/2](http://localhost:2028/pricing/2)

