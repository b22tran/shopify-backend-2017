package com.b22tran.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.b22tran.orders.Orders;
import com.b22tran.orders.OrdersList;
import com.b22tran.orders.Products;

@Component
public class OrdersUtils {

	private final Logger logger = Logger.getLogger(OrdersUtils.class);
	private final JSONParser parser = new JSONParser();
	
	@Autowired
	HttpsUtils httpsUtils;
	
	@Autowired
	PaginationUtils pageUtils;
	
	// method will return the number of cookies in stock
	public int parseCookies(String jsonOrder){
		int cookieAmount = -1;
		try {
			JSONObject jsonObject;
			jsonObject = (JSONObject) parser.parse(jsonOrder);

			long availCookies = Long.valueOf(String.valueOf(parser.parse(jsonObject.get("available_cookies").toString())));
			cookieAmount = Math.toIntExact(availCookies);
		}catch(ParseException e){
			logger.error(e);
		}
		return cookieAmount;
	}
	
	// method will return a jsonobject of pagination data 
	public JSONObject parsePagination(String jsonOrder) {
		JSONObject pagination = null;
		try {
			JSONObject jsonObject;
			jsonObject = (JSONObject) parser.parse(jsonOrder);

			pagination = (JSONObject) parser.parse(jsonObject.get("pagination").toString());
			
		}catch(ParseException e){
			logger.error(e);
		}
		return pagination;
	}
	
	// method will return an orderlist from all orders from a page
	public OrdersList parseOrder(String jsonOrder) {
		OrdersList ordersList = new OrdersList();
		try {
			JSONObject jsonObject;
			jsonObject = (JSONObject) parser.parse(jsonOrder);

			JSONArray orders = (JSONArray) parser.parse(jsonObject.get("orders").toString());

			for (Object o : orders) {
				JSONObject ord = (JSONObject) o;
				int id = Integer.valueOf(String.valueOf(ord.get("id")));
				boolean fulfilled = (Boolean) ord.get("fulfilled");
				String customer_email = (String) ord.get("customer_email");
				JSONArray productsAry = (JSONArray) ord.get("products");
				ArrayList<Products> products = parseProducts(productsAry);
				
				Orders order = new Orders(id, fulfilled, customer_email, products);
				ordersList.add(order);
			}
		} catch (ParseException e) {
			logger.error(e);
		}
		return ordersList;
	}
	
	// method will arraylist of products by parsing products from an order
	private ArrayList<Products> parseProducts(JSONArray jsonAryProd){
		ArrayList<Products> productsList = new ArrayList<Products>();
		for(Object prod : jsonAryProd){
			JSONObject jsonProd = (JSONObject) prod;
			
			String title = String.valueOf(jsonProd.get("title"));
			int amount = Integer.valueOf((String.valueOf(jsonProd.get("amount"))));
			double unitPrice =0;
			if(!(jsonProd.get("unit_price")==null)){
					unitPrice = Double.valueOf((String.valueOf(jsonProd.get("unit_price"))));
			}else if(!(jsonProd.get("unit_price\"") ==null)){
					unitPrice = Double.valueOf((String.valueOf(jsonProd.get("unit_price\""))));
			}
			Products product = new Products(title,amount,unitPrice);
			productsList.add(product);
		}
		return productsList;
	}
	
	// method that will return the entire order list from all pages
	public OrdersList getOrdersListToBeFulfilled(){
		OrdersList ordersListToBeFulfilled = new OrdersList();
		String jsonOrder = httpsUtils.retrieveOrders(null);
		JSONObject paginationJson = parsePagination(jsonOrder);
		
		//get number of pages
		int numOfPages = pageUtils.getNumberOfOrderPages(paginationJson);
		
		if(numOfPages == -1){
			logger.error("Incorrect number of pages were returned");
			return null;
		}
		
		for(int i=1; i<=numOfPages; ++i){
			String param = "?page=" + i ;
			jsonOrder = httpsUtils.retrieveOrders(param);
			
			OrdersList tempOrdList = parseOrder(jsonOrder);
			tempOrdList.removeFulfilledOrders();
			tempOrdList.removeOrdersWithoutCookies();
			
			ordersListToBeFulfilled.addAll(tempOrdList);
		}
		ordersListToBeFulfilled.sortOrdersListBasedOnCookies();
		return ordersListToBeFulfilled;
	}
	
	// method fulfills orderlists return only unfulfilled orders
	public JSONObject fullfillCookieOrders(){
		ArrayList<Integer> unfulfilledOrders = new ArrayList<Integer>();
		String jsonOrder = httpsUtils.retrieveOrders(null);

		int cookieAmount = parseCookies(jsonOrder);
		OrdersList initUnfulfilledOrders = getOrdersListToBeFulfilled();
		
		for(Orders ord : initUnfulfilledOrders){
			if(ord.getProductCookieAmount() > cookieAmount){
				unfulfilledOrders.add(ord.getId());
			} else{
				cookieAmount -= ord.getProductCookieAmount();
			}
		}
		Collections.sort(unfulfilledOrders);
		
		JSONObject output = new JSONObject();
		output.put("unfulfilled_orders", unfulfilledOrders);
		output.put("remaining_cookies", cookieAmount);
		
		return output;
	}
}
