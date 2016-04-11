package orderinfo;

import java.util.Scanner;

public class OrderTest {
	public static void main(String[] args) {
		OrderManager manager = OrderManager.getInstance();
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		
		do {
		
			System.out.println("What would you like to do?");
			System.out.println("[1] Add OrderItem");
			System.out.println("[2] Update OrderItem");
			System.out.println("[3] Remove OrderItem");
			
			System.out.println("[4] Add Order");
			System.out.println("[5] Update Order detail");
			
			System.out.println("[6] Print OrderItemList");
			System.out.println("[7] Print OrderList");
			
			
			choice = scan.nextInt();
			
			switch (choice) {
			case 1:
				manager.addOrderItem();
				break;
			case 2:
				manager.updateOrderItem();
				break;
			case 3: 
				manager.removeOrderItem();
				break;
			case 4:
				manager.addOrder();
				break;
			case 5:
				manager.updateOrder();
				break;
			case 6: 
				manager.printOrderItemList();
				break;
			case 7:
				manager.printOrderList();
				break;
				
			}
			
		}while (choice<10);
		
		
	}

}
