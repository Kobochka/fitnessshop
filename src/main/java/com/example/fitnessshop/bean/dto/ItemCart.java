package com.example.fitnessshop.bean.dto;

import com.example.fitnessshop.bean.Product;

public class ItemCart extends Product{
	
	private int quantity;
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(((ItemCart)obj).getId()  == this.getId()) return true;
		return false;
		
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ItemCart [id=" + this.getId()+ ", name=" + this.getName() + ", price=" + this.getPrice() + ", description=" + this.getDescription()
				+ ", imgPath=" + this.getImgPath() + ", quantity=" + quantity + "]";
	}
	
	
	
	
	
}
