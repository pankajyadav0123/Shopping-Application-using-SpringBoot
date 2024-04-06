package com.shopping.model;

public class Order {
    private int orderId;
    private int userId;
    private int quantity;
    private int amount;
    private String coupon;
    // Constructors, getters, and setters
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getCoupon() {
		return coupon;
	}
	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}
	
	//constructor
	public Order(int orderId, int userId, int quantity, int amount, String coupon) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.quantity = quantity;
		this.amount = amount;
		this.coupon = coupon;
	}
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
