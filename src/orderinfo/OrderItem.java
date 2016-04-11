package orderinfo;

import java.io.Serializable;

public class OrderItem implements Serializable{
	private String itemName;
	private String description;
	private float itemPrice; // to differentiate from room price(instead of calling it price)
	
	public OrderItem(String itemName, String description, float itemPrice){
		this.itemName = itemName;
		this.description= description;
		this.itemPrice = itemPrice;
	}
//	public OrderItem(String itemName,int quantity){
//		this.itemName = itemName;
//		this.quantity = quantity;
//	}
	
	public String getItemName(){
		return itemName;
	}
	public void setItemName(String itemName){
		this.itemName = itemName;
	}
	public String getDescription(){
		return description;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public float getItemPrice(){
		return itemPrice;		
	}
	public void setItemPrice(float itemPrice){
		this.itemPrice = itemPrice;
	}
	
	
}
