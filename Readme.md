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

2. For database storage, I have used in-memory storage for now and maintain two storages, one for products and other for discounts. I have purposefully not exposed a way to create product/discounts, as I feel this is not the resposibility of CheckoutService.
3. Discount is an abstract class and can support wide variety of discount types(Units based discount or percentage based discount or first time offer). I this app, I have used only UnitBasedDiscount Type, but modelled to support any other discount types.
4. Based on the user request, I am building ProductOrder object for each product. This contains details of product, number of units and any discounts if applicable.
5. To calculate the discount, I have implemented strategy pattern and each strategy contains the business logic/formula for the discount calcuation.
6. To pick the right discount strategy for a product, I have used factory pattern, which identifies the discount strategy based on discount type configured to the product.
7. Once all the product orders are built for the user request, I am calculating the final price for the entire cart(totalActualPrice - totalDiscountedPrice) and returning it back to user.



## Future Improvements

1. For fetching products and discounts, we can invoke relavant microservice(ProductService/DiscountService) or fetch them from a persistent storage.
2. Discounts can be changed frequently and can be of different types/models. Considering these, I feel MongoDB seems to be a better storage option.
3. We might have to apply more than one discount for a single product. In such cases above model doesn't work well. To support this, we can use Chain of Responsibility design pattern, where each Discount is an Chain, which applies a discount if required and then forwards to the next chain. 
4. In the all the data models, I have used long as the datatype for Price, but if more accuracy is needed we can use BigDecimal.
5. If products or discounts grow massively, then we need to look at optimising the data fetch. We can fetch all the products/discounts at once from the relavant microservice or DB and also cache them with some TTL.
6. This service should be highly available with low latency, so the application nodes need to be replicated and the load can be shared.



