package orderinfo;

import java.io.Serializable;

public class OrderItem implements Serializable{
	private String itemName;
	private String description;
	private float price;
	
	public OrderItem(String itemName, String description, float price){
		this.itemName = itemName;
		this.description= description;
		this.price = price;
	}
	
}
