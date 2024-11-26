package vangi.screens.login;

import java.util.Scanner;

import vangi.dto.Admin;
import vangi.dto.Customer;
import vangi.dto.User;
import vangi.respository.Bank;
import vangi.screens.actions.AdminActions;
import vangi.screens.actions.CustomerActions;

public class Login {
	private LoginViewModel loginViewModel;
	
	public Login() {
		this.loginViewModel=new LoginViewModel(this);
	}

	public void init() {
		while(true) {
			Scanner scanner=new Scanner(System.in);
			System.out.println("1.Login \n2.Create New Account \n3.Exit \n4.Change password");
			System.out.println("Enter your choice");
			
			int choice=scanner.nextInt();
			
			if(choice==1) {
				System.out.println("Enter user id:");
				int userID=scanner.nextInt();
				System.out.println("Enter your password");
				String password=scanner.next();
				
				User user=loginViewModel.getUser(userID,password);
				
				if(user==null) {
					System.out.println("ERROR:User does not exists");
				}else {
					System.out.println("Logged In Successfully");
					
					
					
					if(user.getUserType().equals("admin")) {
						AdminActions a=new AdminActions((Admin) user);
					}else {
						CustomerActions c=new CustomerActions((Customer)user);
					}
				}
			}else if(choice==2) {
				System.out.println("Enter user name:");
				String userName=scanner.next();
				String password,password2="";
				
				int count=1;
				do{	
					if(count>1) {
						System.out.println("Password Mismatch,Please enter them correctly");
					}
					System.out.println("Enter your password");
					password=scanner.next();
					System.out.println("Re-confirm your password");
					password2=scanner.next();
					count++;
				}while(!password.equals(password2));
				
				Customer user=loginViewModel.createUser(userName,password);
				Bank.getInstance().addNewUser(user);
				
				System.out.println("Customer ID: "+user.getUserID()+"\nAccount No:"+user.getAccountNo()+"\nName:"+user.getUserName()+"\nBalance:"+user.getBalance());
				
				if(user.getUserType().equals("customer")) {
					CustomerActions customerActions=new CustomerActions(user);
				}
				
			}else if(choice==3) {
				break;
			}else if(choice==4){
				System.out.println("Enter user id:");
				int userId=scanner.nextInt();
				
				System.out.println("Enter your current Password:");
				String currentPassword="";
				
				boolean isFirst=true;
				
				Customer customer=null;
				
				do {
					if(!isFirst) {
						System.out.println("Wrong Password,Please enter the correct password");
					}
					
					currentPassword=scanner.next();
					isFirst=false;
					customer=(Customer)Bank.getInstance().getUserFromBank(userId, currentPassword);
				}while(customer==null);
				
				System.out.println("Enter New Password:");
				String newPassword=scanner.next();
				
				if(!Bank.getInstance().checkIsNewPasswordPresent(userId,newPassword)) {
					customer.setEncryptedPassword(Bank.getInstance().encrpt(newPassword));
					customer.storeNewPassword(newPassword);
				}else {
					System.out.println("PLease Enter a new password different from your last 3 passwords");
				}
			}else {
			
				System.out.println("Invalid choice");
			}
		}
	}

}
