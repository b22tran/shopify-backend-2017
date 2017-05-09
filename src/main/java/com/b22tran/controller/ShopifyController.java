package com.b22tran.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="")
public class ShopifyController {
	
	@ApiOperation(value = "Fulfill Shopify orders that request for cookie products")
	@RequestMapping(value = "/fulfill", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> fulfill(){
		
		
		return new ResponseEntity<String>("",HttpStatus.OK);
	}
	
	
}
