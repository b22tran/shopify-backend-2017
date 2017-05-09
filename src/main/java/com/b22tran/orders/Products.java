package com.b22tran.orders;

public class Products {
	private String title;
	private int amount;
	private double unit_price;
	
	public Products(String title, int amount, double unitPrice){
		this.title = title;
		this.amount = amount;
		this.unit_price = unitPrice;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}
	
	
	
}
