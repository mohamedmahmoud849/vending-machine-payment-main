### Payment service for Vending Machine

Payment services for the vending machine project

### Endpoints
there is only one endpoint 
```http
url: paymentservice/payment/pay
method: post
content-type: application/json
body:
{
	"cardNumber": "4556544716835720", 
	"amountToBePaid": 8
}
```
### Payment Service
After sending a request with card details and amount to be paid, the service checks if the card exists in the database and if it has sufficient balance to complete the payment process

### Endpoint response
* 404: card not found
* 406: card has no sufficient balance
* 200 card is accepted and payment process succeeded
