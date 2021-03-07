## Introduction

CheckoutService is a simple microservice, which performs checkout action in a e-commerce. This is built using the following technologies

 * Java 1.8 
 * Spring Boot
 * Maven


 
## How to start the app
1. Goto $PROJECT_DIRECTORY, and execute the following command
		` ./mvnw spring-boot:run`
2. This starts a webserver on port 8080(configurable). Application can be accessed through `http://localhost:8080/`
3. Alternatively, import the project in any IDE(Eclipse or IntelliJ) and run the class `CheckoutServiceApplication.java`


## API's

#### Checkout

- Method : POST 
- Endpoint : `/checkout` 
- Headers : 
 
 ```
    Content-Type : application/json
    Accept : application/json
 ```
- Request Body

 ```
	[
	"001",
	"002",
	"003",
	"001"
 	]
 ```
- Response Body

 ```

	{
	"price" : 330
	}
 ```
 
## Approach
1. The application can be divided into multiple layers
	* Controller : Implements the processing logic of the webservice, parsing the parameters and handling input-output
	* Service : Implements the business logic for processing the request. 
	* Model : Functional Objects which might be persisted in the database.
	* Repository : Interface for the database. Inserts, updates, deletes and reads objects from the database.

2. For database storage, I have used in-memory storage for now, but this can be easily extended in future to fit in any persistent data storage. I am maintaining two storages, one for products and other for discounts. 
3. Discount is an abstract class and can support wide variety of discount types(Units based discount or percentage based discount or first time offer).
4. Based on the input request, I am building ProductOrder object for each product which contains details of product, number of units and any discounts if applicable.
5. To calculate the discount, I have implemented strategy pattern and each strategy contains the business logic of discount calcuation.
6. To pick the right discount strategy for a product, I have used factory pattern, which identifies  discount strategy based on discount type configured to the product.
7. Once all the product orders are built for the input request, I am calculating the final price of product and returning it back to user.



## Future Improvements

1. Having a persistent storage for products and discounts. 
2. Discounts can be changed frequently and can be of different types/models. Considering these, I feel MongoDB seems to be a better storage option.
3. We might have to apply more than one discounts for a single product. In such cases above model doesn't work well. To support this, we can use Chain of Responsibility design pattern, where each Discount is an Chain, which applies a discoun if required conditions are met and then forwards to the next chain. 
4. Price of the product is currently defined using long datatype, but if accuracy is needed we can use BigDecimal.
5. If products or discounts grow massively, then in such cases we need to look at optimising the data fetch and data storage. We can use Database sharding and Caching the fetched records.
6. This service should be highly available, so the application nodes need to be replicated and so load is shared.



