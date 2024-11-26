package vangi.screens.actions;

import java.util.List;
import java.util.Scanner;

import vangi.dto.Admin;
import vangi.dto.Customer;
import vangi.respository.Bank;

public class AdminActions {
	private Admin admin;
	
	public AdminActions(Admin admin) {
		this.admin=admin;
		this.init();
	}

	private void init() {
		Scanner scanner=new Scanner(System.in);
		
		while(true) {
			System.out.println("1.Get Top N customers\n0.Exit");
			System.out.println("Enter your choice:");
			int choice=scanner.nextInt();
			
			if(choice==1) {
				System.out.println("Enter the value of N");
				int n=scanner.nextInt();
				System.out.println("Top N Customers List");
				List<Customer> topN=Bank.getInstance().topNCustomers(n);
				
				for(Customer c:topN) {
					System.out.println(c.getAccountNo()+" "+c.getUserID()+" "+c.getBalance());
				}
			}
		}
	}

	
}
