package com.b22tran.utils;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.b22tran.orders.Orders;
import com.b22tran.orders.OrdersList;
import com.b22tran.orders.Products;

public class OrdersUtils {

	private final JSONParser parser = new JSONParser();
	
	public int parseCookies(String jsonOrder){
		int cookieAmount = -1;
		try {
			JSONObject jsonObject;
			jsonObject = (JSONObject) parser.parse(jsonOrder);

			long availCookies = Long.valueOf(String.valueOf(parser.parse(jsonObject.get("available_cookies").toString())));
			cookieAmount = Math.toIntExact(availCookies);
		}catch(ParseException e){
			System.out.println(e);
		}
		return cookieAmount;
	}
	public JSONObject parsePagination(String jsonOrder) {
		JSONObject pagination = null;
		try {
			JSONObject jsonObject;
			jsonObject = (JSONObject) parser.parse(jsonOrder);

			pagination = (JSONObject) parser.parse(jsonObject.get("pagination").toString());
			
		}catch(ParseException e){
			System.out.println(e);
		}
		return pagination;
	}
	public OrdersList parseOrder(String jsonOrder) {
		OrdersList ordersList = new OrdersList();
		try {
			JSONObject jsonObject;
			jsonObject = (JSONObject) parser.parse(jsonOrder);

			JSONArray orders = (JSONArray) parser.parse(jsonObject.get("orders").toString());

			for (Object o : orders) {
				JSONObject ord = (JSONObject) o;
				int id = (Integer) ord.get("id");
				boolean fulfilled = (Boolean) ord.get("fulfilled");
				String customer_email = (String) ord.get("customer_email");
				JSONArray productsAry = (JSONArray) ord.get("products");
				ArrayList<Products> products = parseProducts(productsAry);
				
				Orders order = new Orders(id, fulfilled, customer_email, products);
				ordersList.add(order);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ordersList;
	}
	private ArrayList<Products> parseProducts(JSONArray jsonAryProd){
		ArrayList<Products> productsList = null;
		for(Object prod : jsonAryProd){
			JSONObject jsonProd = (JSONObject) prod;
			
			String title = String.valueOf(jsonProd.get("title"));
			int amount = Integer.valueOf((String.valueOf(jsonProd.get("amount"))));
			double unitPrice =0;
			if(!jsonProd.get("unit_price").equals(null)){
					unitPrice = Double.valueOf((String.valueOf(jsonProd.get("unit_price"))));
			}else if(!jsonProd.get("unit_price\"").equals(null)){
					unitPrice = Double.valueOf((String.valueOf(jsonProd.get("unit_price\""))));
			}
			Products product = new Products(title,amount,unitPrice);
			productsList.add(product);
		}
		return productsList;
	}
}
