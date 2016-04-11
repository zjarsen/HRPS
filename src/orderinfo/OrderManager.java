package orderinfo;

import java.util.ArrayList;
import java.util.Scanner;


public class OrderManager {
	
	private static OrderManager singleInstance;
	private OrderController orderController;
	private OrderItemController orderItemController;
	
	public OrderManager(){
		orderController = OrderController.getInstance();
		orderItemController = OrderItemController.getInstance();
	}
	public static OrderManager getInstance(){
		if (singleInstance == null)
			singleInstance = new OrderManager();
		return singleInstance;
	}
	
	public void addOrderItem(){
		Scanner scan = new Scanner(System.in);
		System.out.print("OrderItem Name: ");
		String itemName = scan.nextLine();
		System.out.print("OrderItem Description: ");
		String description = scan.nextLine();
		System.out.print("OrderItem Price: ");
		float orderItemPrice = scan.nextFloat();
		OrderItem newitem = new OrderItem(itemName,description,orderItemPrice);
		ArrayList<OrderItem> orderItemList = orderItemController.getOrderItem();
		orderItemList.add(newitem);//add element to arraylist
		if (orderItemController.saveChanges())
			System.out.println("New OrderItem added successfully\n");
		else
			System.out.println("New OrderItem not added\n");
		
		for (int i=0;i<orderItemList.size();i++){
			System.out.println((i+1)+". "+orderItemList.get(i).getItemName()+"\n"); //print item info
		}
		
	}
	public void updateOrderItem(){
		ArrayList<OrderItem> orderItemList = orderItemController.getOrderItem();
		Scanner scan = new Scanner(System.in);
		if (orderItemList!=null){
			System.out.println("Please select an OrderItem to update:");
			for (int i=0;i<orderItemList.size();i++){
				System.out.println((i+1)+". "+orderItemList.get(i).getItemName()+" "+orderItemList.get(i).getDescription()+" "+orderItemList.get(i).getItemPrice()+"\n");	
			}
			int choice = 0;
			choice = scan.nextInt();
			
			System.out.print("OrderItem Name: ");
			scan.nextLine();
			String itemName = scan.nextLine();
			scan.nextLine();
			System.out.print("OrderItem Description: ");
			String description = scan.nextLine();
			System.out.print("OrderItem Price: ");
			float orderItemPrice = scan.nextFloat();
			orderItemList.remove(choice-1);
			OrderItem newitem = new OrderItem(itemName,description,orderItemPrice);
			//ArrayList<OrderItem> orderItemList2 = orderItemController.getOrderItem();
			orderItemList.add(newitem);//add element to arraylist
			if (orderItemController.saveChanges())
				System.out.println("New OrderItem updated successfully\n");
			else
				System.out.println("New OrderItem not updated\n");
		}
	}
	public void removeOrderItem(){
		ArrayList<OrderItem> orderItemList = orderItemController.getOrderItem();
		System.out.println("Please select the item that you want to remove.(input number)\n");
		Scanner scan = new Scanner(System.in);
		for (int i=0;i<orderItemList.size();i++){
			System.out.println((i+1)+". "+orderItemList.get(i).getItemName()+"\n"); //print item info
		}
		int choice = 0;
		choice = scan.nextInt();
		orderItemList.remove(choice-1);
		if (orderItemController.saveChanges())
			System.out.println("New OrderItem removed successfully\n");
		else
			System.out.println("New OrderItem not removed\n");
		
		for (int i=0;i<orderItemList.size();i++){
			System.out.println((i+1)+". "+orderItemList.get(i).getItemName()+"\n"); //print item info
		}
	}
	public void printOrderItemList(){
		ArrayList<OrderItem> orderItemList = orderItemController.getOrderItem();
		for (int i=0;i<orderItemList.size();i++){
			System.out.println((i+1)+". "+orderItemList.get(i).getItemName()+" "+orderItemList.get(i).getDescription()+" "+orderItemList.get(i).getItemPrice()+"\n");	
		}
		
	}
	public void addOrder(){
		Scanner scan = new Scanner(System.in);
		int option = 0;
		int count=0;//count of types of items
		ArrayList<OrderItem> tempOrder = new ArrayList<OrderItem>();
		int[] tempQuantity = new int[10];
		tempOrder.clear();
		tempQuantity = new int [10];
		
		ArrayList<OrderItem> orderItemList = orderItemController.getOrderItem();
		
		for (int i=0;i<orderItemList.size();i++){
			System.out.println((i+1)+". "+orderItemList.get(i).getItemName()+" "+orderItemList.get(i).getDescription()+" "+orderItemList.get(i).getItemPrice()+"\n");	
		}
		do{
			System.out.print("Please select an item by number ");
			int choice = 0;
			choice = scan.nextInt();
			
			tempOrder.add(orderItemList.get(choice-1));
			
			System.out.print("Please input the quantity ");
			tempQuantity[count] = scan.nextInt(); 
			
			System.out.print("Please input 1 to order more items, 2 to continue ");
			scan.nextLine();
			option = scan.nextInt();
			count++;
		} while (option == 1);
		
		System.out.print("Please input the remark and submit order ");
		scan.nextLine();
		String remarks = scan.nextLine();
		long orderTime = System.currentTimeMillis();
		String status = "Confirmed";
		
		Order newOrder = new Order(tempOrder, tempQuantity, orderTime, remarks, status);
		
		ArrayList<Order> orderList = orderController.getOrders();
		orderList.add(newOrder);
		
		
		if (orderController.saveChanges()){
			System.out.println("Order added successfully");
		}
		else
			System.out.println("Failed to add order");
	}
	
	public void updateOrder(){
		ArrayList<Order> orderList = orderController.getOrders();
		System.out.println("Please choose which order to update (by number)");
		
		for (int i=0;i<orderList.size();i++){
			System.out.println("Order"+(i+1)+":\n");
//			for (int j=0;j<orderList.get(i).getQuantity().length;j++){
//				System.out.println("  "+(j+1)+". "+orderList.get(i).getOrderItemList().get(j).getItemName()+" x "+orderList.get(i).getQuantity()[j]+"\n");	
//			}
			System.out.println(orderList.get(i).getOrderTime()+" "+orderList.get(i).getremarks()+" "+orderList.get(i).getStatus()+" "+orderList.get(i).getTotalPrice()+" "+"\n");
		}
		
	}

	public void printOrderList(){
		ArrayList<Order> orderList = orderController.getOrders();
		
		for (int i=0;i<orderList.size();i++){
			System.out.println("Order"+(i+1)+":\n");
			int j=0;
			do {
				System.out.println("  "+(j+1)+". "+orderList.get(i).getOrderItemList().get(j).getItemName()+" x "+orderList.get(i).getQuantity()[j]+"\n");
				j++;
			}
			while (orderList.get(i).getQuantity()[j]!=0);
			System.out.println(orderList.get(i).getOrderTime()+" "+orderList.get(i).getremarks()+" "+orderList.get(i).getStatus()+" "+orderList.get(i).getTotalPrice()+" "+"\n");
		}
	}
}
