package com.b22tran.orders;

import java.util.ArrayList;
import java.util.Map;

public class Orders {

	private int id;
	private boolean fulfilled;
	private String customerEmail;
	private ArrayList<Products> products;
	
	public Orders(int id, boolean fulfilled, String customerEmail, ArrayList<Products> products){
		this.id = id;
		this.fulfilled = fulfilled;
		this.customerEmail = customerEmail;
		this.products = products;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isFulfilled() {
		return fulfilled;
	}

	public void setFulfilled(boolean fulfilled) {
		this.fulfilled = fulfilled;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public ArrayList<Products> getProducts() {
		return products;
	}
	
	public int getProductCookieAmount(){
		int cookieAmount = 0;
		for (Products prod : this.products) {
			if (prod.getTitle().toLowerCase().equals("cookie")) {
				cookieAmount = prod.getAmount();
			}
		}
		return cookieAmount;
	}

	public void setProducts(ArrayList<Products> products) {
		this.products = products;
	}
	
	

}
