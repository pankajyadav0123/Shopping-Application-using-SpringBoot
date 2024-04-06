package com.shopping.model;

public class Inventory {
    private int ordered;
    private int price;
    private int available;
    
    // getters, and setters

	public int getOrdered() {
		return ordered;
	}
	public void setOrdered(int ordered) {
		this.ordered = ordered;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	
	//constructor
	public Inventory(int ordered, int price, int available) {
		super();
		this.ordered = ordered;
		this.price = price;
		this.available = available;
	}
	public Inventory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
    
	
    
    
    
    
}
