package orderinfo;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
/**
 * To add a new item
 */
	public void addOrderItem(){
		Scanner scan = new Scanner(System.in);
		System.out.print("Please key in Menu Item Name: ");
		String itemName = scan.nextLine();
		System.out.print("Please key in Menu Item Description (how it is prepared): ");
		String description = scan.nextLine();
		System.out.print("Please key in Menu Item Price: ");
		float orderItemPrice = scan.nextFloat();
		OrderItem newitem = new OrderItem(itemName,description,orderItemPrice);
		ArrayList<OrderItem> orderItemList = orderItemController.getOrderItem();
		orderItemList.add(newitem);//add element to arraylist
		if (orderItemController.saveChanges()){
			System.out.println("New Menu Item "+ itemName +" is added successfully!\n");
			System.out.println("====================New Menu Item===================");
			System.out.println(" Name:           "+itemName);
			System.out.println(" Description:    "+description);
			System.out.println(" Price:          "+orderItemPrice);
			System.out.println("====================================================");
		}
		else
			System.out.println("New Menu Item not added\n");
	}

	/**
	 * To update an item.
	 * able to choose a field to update
	 */
	public void updateOrderItem(){
		ArrayList<OrderItem> orderItemList = orderItemController.getOrderItem();
		Scanner scan = new Scanner(System.in);
		if (orderItemList!=null){
			System.out.println("Please select a Menu Item to update:\n");
			System.out.println("========================Menu=====================");
			for (int i=0;i<orderItemList.size();i++){
				System.out.println((i+1)+". Name:           "+orderItemList.get(i).getItemName());
				System.out.println("   Description:    "+orderItemList.get(i).getDescription());
				System.out.println("   Price:          "+orderItemList.get(i).getItemPrice());
				System.out.println("-------------------------------------------------");
			}
			int choice = 0;
			choice = scan.nextInt();

			System.out.println("You are updating "+orderItemList.get(choice-1).getItemName()+":");
			System.out.println("-------------------------------------------------");
			System.out.println("   Name:           "+orderItemList.get(choice-1).getItemName());
			System.out.println("   Description:    "+orderItemList.get(choice-1).getDescription());
			System.out.println("   Price:          "+orderItemList.get(choice-1).getItemPrice());
			System.out.println("-------------------------------------------------");

			System.out.println("Please select a field to update:");
			System.out.println("1. Name");
			System.out.println("2. Description");
			System.out.println("3. Price");
			System.out.println("4. Return");
			int field = scan.nextInt();


			switch (field){
				case 1:
					System.out.print("New Menu Item Name: ");
					scan.nextLine();
					String itemName = scan.nextLine();
					orderItemList.get(choice-1).setItemName(itemName);

					if (orderItemController.saveChanges()) {
						System.out.println("New Menu Item Name updated successfully\n");
						printOrderItemList();
					}
					else
						System.out.println("New Menu Item not updated\n");
					break;
				case 2:
					System.out.print("New Menu Item Description: ");
					scan.nextLine();
					String description = scan.nextLine();
					orderItemList.get(choice-1).setDescription(description);

					if (orderItemController.saveChanges()) {
						System.out.println("New Menu Item Description updated successfully\n");
						printOrderItemList();
					}
					else
						System.out.println("New Menu Item not updated\n");
					break;
				case 3:
					System.out.print("New Menu Item Price: ");
					float price=0;
					boolean incorrectInput = true;
					while (incorrectInput) {
					    if (scan.hasNextFloat()) {
					        price = scan.nextFloat();
					        incorrectInput = false;
					    	}
					     else {
					    	 System.out.println("Please key in a valid number for price.");
					    	 scan.next();
					     }
					}
					if (!incorrectInput) {
						orderItemList.get(choice-1).setItemPrice(price);
						if (orderItemController.saveChanges()){
							System.out.println("New Menu Item Price updated successfully\n");
							printOrderItemList();
						}	
						else System.out.println("New Menu Item not updated\n");
						break;
					}
				case 4:
					System.out.println("");
					break;
				default:
					System.out.println("Please key in a number from 1 to 3, exiting...");
			}
		}
	}
	/**
	 * To remove an item
	 */
	public void removeOrderItem(){
		ArrayList<OrderItem> orderItemList = orderItemController.getOrderItem();
		System.out.println("Please select the Item that you want to remove:\n");
		Scanner scan = new Scanner(System.in);

		System.out.println("========================Menu=====================");
		for (int i=0;i<orderItemList.size();i++){
			System.out.println((i+1)+". Name:           "+orderItemList.get(i).getItemName());
			System.out.println("   Description:    "+orderItemList.get(i).getDescription());
			System.out.println("   Price:          "+orderItemList.get(i).getItemPrice());
			System.out.println("-------------------------------------------------");
		}

		int choice = 0;
		choice = scan.nextInt();
		orderItemList.remove(choice-1);

		if (orderItemController.saveChanges())
			System.out.println("Menu Item removed successfully\n");
		else
			System.out.println("Menu Item not removed\n");
	}

	/**
	 * To print the menu
	 */
	public void printOrderItemList(){
		ArrayList<OrderItem> orderItemList = orderItemController.getOrderItem();
		System.out.println("Welcome!");
		System.out.println("========================Menu=====================");
		for (int i=0;i<orderItemList.size();i++){
			System.out.println((i+1)+". Name:           "+orderItemList.get(i).getItemName());
			System.out.println("   Description:    "+orderItemList.get(i).getDescription());
			System.out.println("   Price:          "+orderItemList.get(i).getItemPrice());
			System.out.println("-------------------------------------------------");
		}
	}

	/**
	 * To add an order for a guest
	 */
	public Order addOrder(){
		Scanner scan = new Scanner(System.in);
		int option = 0;
		int count=0;//count of types of items
		ArrayList<OrderItem> tempOrder = new ArrayList<OrderItem>();
		int[] tempQuantity = new int[10];
		tempOrder.clear();
		tempQuantity = new int [10];

		ArrayList<OrderItem> orderItemList = orderItemController.getOrderItem();

		System.out.println("Welcome! \n");
		System.out.println("========================Menu=====================");
		for (int i=0;i<orderItemList.size();i++){
			System.out.println((i+1)+". Name:           "+orderItemList.get(i).getItemName());
			System.out.println("   Description:    "+orderItemList.get(i).getDescription());
			System.out.println("   Price:          "+orderItemList.get(i).getItemPrice());
			System.out.println("-------------------------------------------------");
		}
		do{
			System.out.println("Please select an item. ");
			int choice = 0;
			choice = scan.nextInt();

			tempOrder.add(orderItemList.get(choice - 1));
			System.out.print("How many servings of "+orderItemList.get(choice-1).getItemName()+" would you like? ");
			tempQuantity[count] = scan.nextInt();

			System.out.println("What do you want do next? ");
			System.out.println("1. Order more items ");
			System.out.println("2. Proceed to check out ");
			scan.nextLine();
			option = scan.nextInt();
			count++;
		} while (option == 1);

		System.out.print("Please key in the remark (diet preference) and confirm order. \n");
		scan.nextLine();
		String remarks = scan.nextLine();
		long orderTime = System.currentTimeMillis();
		String status = "Confirmed";

		Order newOrder = new Order(tempOrder, tempQuantity, orderTime, remarks, status);
		ArrayList<Order> orderList = orderController.getOrders();
		orderList.add(newOrder);

		if (orderController.saveChanges()){
			System.out.println("");
			System.out.println("Your order has been confirmed.\n");
			System.out.println(newOrder.toString());
			System.out.println("$"+orderList.get(orderList.size()-1).getTotalPrice()+" will be charged to this room upon checkout.\n");
		}
		else
			System.out.println("Failed to add order.");
		return newOrder;

	}
	/**
	 * To update the status of an order
	 */
	public String updateOrder(long orderTime){
		ArrayList<Order> orderList = orderController.getOrders();
		Scanner scan = new Scanner(System.in);

		System.out.println("Please select a status you want to update.\n");
		System.out.println("1. Confirmed\n");
		System.out.println("2. Preparing\n");
		System.out.println("3. Delivered\n");
		int choice = scan.nextInt();
		String newStatus = "";
	
		for (int j=0; j<orderList.size();j++){
			if (orderTime == orderList.get(j).getOrderTime()){
				if (choice == 1) {
					orderList.get(j).setStatus("Confirmed");
					newStatus = "Confirmed";
				}
				else if (choice == 2) {
					orderList.get(j).setStatus("Preparing");
					newStatus = "Preparing";

				}
				else if (choice == 3) {
					orderList.get(j).setStatus("Delivered");
					newStatus = "Delivered";
				}
			}
		}
		if (orderController.saveChanges()){
			System.out.println("Order status is updated successfully.");
		}
		else
			System.out.println("Failed to add order.");
		return newStatus;
	}
	/**
	 * For testing only
	 * Print a order detail
	 */
	public void printOrder(long orderTime){ // print one order specified by orderTime
		ArrayList<Order> orderList = orderController.getOrders();
		for (int i=0;i<orderList.size();i++){
			if (orderTime == orderList.get(i).getOrderTime()){
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
	/**
	 * For testing only
	 * Print all order details
	 */
	public void printOrderList(){
		ArrayList<Order> orderList = orderController.getOrders();

		for (int i=0;i<orderList.size();i++){
			System.out.println(orderList.get(i).toString());
		}
	}

}
