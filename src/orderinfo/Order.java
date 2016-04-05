package orderinfo;

import java.io.Serializable;
//import java.sql.Time;
import java.util.ArrayList;

public class Order implements Serializable {
	
	private ArrayList<OrderItem> orderItem;
	//items from OrderItem Class forms an order
	private long time;//use as orderId
	private String remarks;
	private String status;
	private float totalPrice;
	
	public Order (ArrayList<OrderItem> orderItem, long time, String remarks, String status, float totalPrice){
		this.orderItem = orderItem;
		this.time = System.currentTimeMillis(); 
		this.remarks = remarks;
		this.status = status;
		this.totalPrice = totalPrice;
				
	}
	
	public void printOrderItem() {
		for (int i=0; i<orderItem.size();i++){
			System.out.println(orderItem.get(i));
		}
	}
	public String getremarks(){
		return this.remarks;
	}
	public void setRemarks(String remarks){
		this.remarks = remarks;
	}
	public String getStatus(){
		return this.status;
	}
	public void setStatus(String status){
		this.status = status;
	}
	public float getTotalPrice(){
		return this.totalPrice;
	}
	
	
	
}
