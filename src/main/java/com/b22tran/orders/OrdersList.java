package com.b22tran.orders;

import java.util.ArrayList;
import java.util.Map;

public class OrdersList extends ArrayList{

	private ArrayList<Orders> orders;
	
	public OrdersList(){
		this.orders = new ArrayList<Orders>();
	}
	
	public OrdersList(ArrayList<Orders> orders){
		this.orders = orders;
	}

	public void add(Orders order){
		this.orders.add(order);
	}
	
	public ArrayList<Orders> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Orders> orders) {
		this.orders = orders;
	}
	
	//TODO
	// create method to organize orders list based on cookie req
	
	
	
}
