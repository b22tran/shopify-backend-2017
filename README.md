#shopifyBackendProblem

Back End Development Problem
Student Id:991173683

Unfortunately I havn't pushed it to a cloud host so the program will have to be tested locally.
Call upon your localhost:port/fulfill ; /fulfill being the api endpoint call.
Example:
	localhost:8080/fulfill

When ran locally based on the current JSON provied by the link below, I recieved the output of:
{
	"unfulfilled_orders": [6, 8, 10],
	"remaining_cookies": 0
}


Backend Problem:
One Shopify merchant bakes tasty treats and sells them locally, but they haven’t been able to bake cookies fast enough to keep up with the demand of all the orders.  Because of this, they have asked for your help to organize all cookie orders, so they can see which orders they will need to bake more cookies for.

You have a limited amount of cookies remaining and a list of orders. The merchant needs to know which orders will not be *fulfilled after following the requirements specified below.

 *order fulfillment: The process between when a merchant receives an order from a customer and delivers the product into the customer's hands. 

You have access to a paginated API in: https://backend-challenge-fall-2017.herokuapp.com/orders.json 
where you can obtain all the orders. The API accepts a `page` parameter, example: https://backend-challenge-fall-2017.herokuapp.com/orders.json?page=2

Example JSON data:
https://backend-challenge-fall-2017.herokuapp.com/orders.json?page=3
Outputed:

{"available_cookies":6,"orders":[{"id":11,"fulfilled":false,"customer_email":"julien@interview.com","products":[{"title":"Carrot Cake","amount":1,"unit_price\"":20.45},{"title":"Cookie","amount":1,"unit_price":14.5}]}],"pagination":{"current_page":3,"per_page":5,"total":11}}

Requirements


Read all orders from the paginated API.
Any order without cookies can be fulfilled.
Prioritize fulfilling orders with the highest amount of cookies.
If orders have the same amount of cookies, prioritize the order with the lowest ID.
If an order has an amount of cookies bigger than the remaining cookies, skip the order.



What to submit: 

Output (in JSON)

{
  "remaining_cookies": "Amount of cookies remaining after trying to fulfill orders",
  "unfulfilled_orders": [ "IDs of the order that couldn't be fulfilled in ascending order" ]
}



*Example Answer Output

{
  "remaining_cookies": 0,
  "unfulfilled_orders": [ 3, 5 ]
}

