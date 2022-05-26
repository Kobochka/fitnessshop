package com.example.fitnessshop.bean;

import java.util.Date;

public class Order {

	private int id;
	private int addressId;
	private int paymentId;
	private Date date;
	private float price;
	
	public Order() {}
	
	public Order(int addressId, int paymentId, Date date) {
		super();
		this.addressId = addressId;
		this.paymentId = paymentId;
		this.date = date;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	
	
}
