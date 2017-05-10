package com.b22tran.orders;

import java.util.ArrayList;

public class OrdersList extends ArrayList<Orders>{

	private ArrayList<Orders> orders;
	
	public OrdersList(){
		this.orders = new ArrayList<Orders>();
	}
	
	public OrdersList(ArrayList<Orders> orders){
		this.orders = orders;
	}

	// adds an order to obj
//	@Override
//	public boolean add(Orders order){
//		this.orders.add(order);
//		return this.orders.add(order);
//	}
	
	// method will remove fulfilled orders
	public int removeFulfilledOrders(){
		int counter = 0;
		for(Orders ord : this.orders){
			if(ord.isFulfilled()){
				this.orders.remove(ord);
				counter++;
			}
		}
		return counter;
	}
	
	// method will remove orders with out cookies
	public int removeOrdersWithoutCookies() {
		int counter = 0;
		for (Orders ord : this.orders) {
			boolean haveCookies = false;
			for (Products prod : ord.getProducts()) {
				if (prod.getTitle().toLowerCase().equals("cookies")) {
					haveCookies = true;
				}
			}
			if(!haveCookies){
				this.orders.remove(ord);
				counter++;
			}
		}
		return counter;
	}
	
	// method will sort orderlist based on amount of cookies in order
	public void sortOrdersListBasedOnCookies() {
		for (int i = (this.orders.size() - 1); i >= 0; i--) {
			for (int j = 1; j <= i; j++) {
				Orders ord1 = this.orders.get(j - 1);
				Orders ord2 = this.orders.get(j);
				int prod1 = this.orders.get(j - 1).getProductCookieAmount();
				int prod2 = this.orders.get(j).getProductCookieAmount();
				if (prod1 < prod2) {
					Orders temp = ord1;
					ord1 = ord2;
					ord2 = temp;
				} else if (prod1 == prod2) {
					int id1 = this.orders.get(j - 1).getId();
					int id2 = this.orders.get(j).getId();
					if (id1 < id2) {
						Orders temp = ord1;
						ord1 = ord2;
						ord2 = temp;
					}
				}
			}
		}
	}
	
	// getter
	public ArrayList<Orders> getOrders() {
		return orders;
	}

	// setter
	public void setOrders(ArrayList<Orders> orders) {
		this.orders = orders;
	}
	
	@Override
	public String toString(){
		return "";
	}
}
