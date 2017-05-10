package com.b22tran.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.b22tran.orders.OrdersList;
import com.b22tran.utils.HttpsUtils;
import com.b22tran.utils.OrdersUtils;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/")
public class ShopifyController {
	
	@Autowired
	HttpsUtils httpsUtils;
	@Autowired
	OrdersUtils ordersUtils;
	
	@ApiOperation(value = "Fulfill Shopify orders that request for cookie products")
	@RequestMapping(value = "/fulfill", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> fulfill(){
		String jsonOrder = httpsUtils.retrieveOrders(null);
		if(jsonOrder.equals(null)){
			return new ResponseEntity<String>("Error: could not retrieve orders from url",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		JSONObject output = ordersUtils.fullfillCookieOrders();
		
		return new ResponseEntity<String>(output.toJSONString(),HttpStatus.OK);
	}
	
	
}
