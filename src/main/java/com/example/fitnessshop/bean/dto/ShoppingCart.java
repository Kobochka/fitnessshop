package com.example.fitnessshop.bean.dto;

import java.util.Enumeration;
import java.util.Vector;

public class ShoppingCart {
	
	// vector to store the shipping cart items
    private Vector<ItemCart> vectors;

	public ShoppingCart() {
		this.vectors = new Vector<ItemCart>();
	}
    
	
	/**
	 * add an itemCart to the shopping cart.
	 * 
	 * @param item item to store
	 */
	public synchronized void addItem(ItemCart item) {
		
		// get all itemCarts from the vectors
		Enumeration<ItemCart> items = vectors.elements();
		
		int index = vectors.indexOf(item);
		
		if(index >-1) {
			vectors.get(index).setQuantity(vectors.get(index).getQuantity() + item.getQuantity());
			
			return;
		
		// loop in oder to check if the item already exist in the shopping cart
		/*while(items.hasMoreElements()) {
			ItemCart itemTmp = (ItemCart)items.nextElement();
			
			if(itemTmp.equals(item)) {
				System.out.println("There are the same product");
				// the item already exists then update quantity and leave
				int index = vectors.
				
				item.setQuantity(itemTmp.getQuantity() + item.getQuantity());
				return;
			}*/
		}
		
		// the item don't exists then add it to the shopping cart
		vectors.add(item);
		
	}
	
	
	/**
	 * remove an itemCart from the shopping cart.
	 * 
	 * @param id id of the item to update
	 * @param quantity
	 */
	public synchronized void updateItemById(int id, int quantity) {
		
		// check vector to update quantity item
		for(ItemCart item: vectors) {
			if(item.getId() == id) {
				// get the current index
				int index = vectors.indexOf(item);
				
				if(quantity > 0) {
					//the quantity is more than zero then update quantity
					vectors.get(index).setQuantity(quantity);
					return;
				}
				
				// the quantity is zero then delete item
				vectors.remove(index);
				return;
			}
		}
	}


	public Vector<ItemCart> getVectors() {
		return vectors;
	}


	public void setVectors(Vector<ItemCart> vectors) {
		this.vectors = vectors;
	}
	
	
		
    
    
    
    
    
}
