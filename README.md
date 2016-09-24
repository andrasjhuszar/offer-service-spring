# offer-service-spring

Start the application using com.ahuszar.offerservice.rest.Application class.

Example calls:
- GET http://localhost:8080/products to get all products available for creating an Offer
- GET http://localhost:8080/offers to get all saved offers 

- POST http://localhost:8080/offers to create an offer with a payload: 

{
  "product" : PRODUCT_ID,
  "description" : DESCRIPTION,
  "price" : PRICE
}


for example:

{
  "product" : 1,
  "description" : "Autumn offer",
  "price" : 3.45
}

Content Type is always application/json

-----------------------------------------------------------------------------------------------

To build the application please use Maven

Technologies used:
- Spring boot web
- Spring boot data
- Spring boot test
- Mockito  