package com.example.fitnessshop.bean;

public class Payment {
	
	private int id;
    private String cardNumber;
    private String cardExpiration;
    private String cardCVCode;
    
	public Payment(String cardNumber, String cardExpiration, String cardCVCode) {
		super();
		this.cardNumber = cardNumber;
		this.cardExpiration = cardExpiration;
		this.cardCVCode = cardCVCode;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardExpiration() {
		return cardExpiration;
	}
	public void setCardExpiration(String cardExpiration) {
		this.cardExpiration = cardExpiration;
	}
	public String getCardCVCode() {
		return cardCVCode;
	}
	public void setCardCVCode(String cardCVCode) {
		this.cardCVCode = cardCVCode;
	}
    
    
    
    
}
