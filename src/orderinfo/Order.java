package orderinfo;

import java.io.Serializable;
//import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;

public class Order implements Serializable {

	private ArrayList<OrderItem> orderItem;
	private int [] quantity;
	//	private OrderItem quantity;
	//items from OrderItem Class forms an order
	private long orderTime;//use as orderId
	private String remarks;
	private String status;
	private float totalPrice = 0;

	public Order (ArrayList<OrderItem> orderItem, int[] quantity, long orderTime, String remarks, String status){
		this.orderItem = orderItem;
		this.quantity = quantity;
		this.orderTime = System.currentTimeMillis();
		this.remarks = remarks;
		this.status = status;

	}
	//	public Order(OrderItem itemName, OrderItem quantity,long orderTime, String remarks, String status){
//		this.itemName = itemName;
//		this.quantity = quantity;
//		this.orderTime = System.currentTimeMillis(); 
//		this.remarks = remarks;
//		this.status = status;
//	}
	public void printOrderItemList() {
		for (int i=0; i<orderItem.size();i++){
			System.out.println(orderItem.get(i)+" "+orderItem.get(i).getDescription()+" "+orderItem.get(i).getItemPrice()+"\n");
		}
	}
	public ArrayList<OrderItem> getOrderItemList(){
		return orderItem;
	}
	public int[] getQuantity(){
		return quantity;
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
	public long getOrderTime(){
		return orderTime;
	}
	public float getTotalPrice(){
		totalPrice = 0;
		for (int i=0;i<orderItem.size();i++){

			totalPrice += (orderItem.get(i).getItemPrice()*quantity[i]);
		}
		return totalPrice;
	}
	public String toString(){
		totalPrice = 0;
		for (int j=0;j<orderItem.size();j++){
			totalPrice += (orderItem.get(j).getItemPrice()*quantity[j]);
		}

		String s = "==============Order Detail==============\n";
		for (int i=0;i<orderItem.size();i++){
			s += (i+1) +  ".Name:         "+ orderItem.get(i).getItemName() +"\n"
					+                "  Unit Price:   $"+ orderItem.get(i).getItemPrice() +"\n"
					+				 "  Quantity:     "+ quantity[i]+ "\n"
					+				 "  Subtotal:     $"+ orderItem.get(i).getItemPrice()*quantity[i]+"\n";
		}
		s +=        "----------------------------------------\n";
		s += "Remark: "+ remarks+"\n";
		s += "Status: "+ status+"\n";
		s += "Time Stamp: "+ orderTime+"\n";
		s += "Order Price: $"+ totalPrice+"\n";
		return s;
	}
}
