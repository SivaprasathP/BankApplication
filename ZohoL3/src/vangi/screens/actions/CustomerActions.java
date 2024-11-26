package vangi.screens.actions;

import java.util.Scanner;

import vangi.dto.Customer;
import vangi.dto.Transaction;
import vangi.dto.User;
import vangi.respository.Bank;

public class CustomerActions {
	private Customer user;
	
	public CustomerActions(Customer user) {
		this.user=user;
		this.init();
	}

	private void init() {
		Scanner scanner=new Scanner(System.in);
		
		while(true) {
			System.out.println("1.ATM Withdrawal\n2.Cash Deposit\n3.Account Transfer\n4.Transaction History\n5.Balance\n6.Exit\n7.Encrpted Password");
			System.out.println("Enter your choice:");
			int choice=scanner.nextInt();
			
			if(choice==1) {
				System.out.println("Enter the withdrawing amount:");
				int withdrawAmount=scanner.nextInt();
				
				if(user.getBalance()-withdrawAmount<1000) {
					System.out.println("Sorry you must maintain a minimum balance of Rs.1000");
				}else {
					user.setBalance(user.getBalance()-withdrawAmount);
					Transaction t=new Transaction(user.getTransactions().size()+1,"ATMWithDrawal",withdrawAmount,user.getBalance());
					user.getTransactions().add(t);
					
					System.out.println("Current Balance:"+user.getBalance());
				}
			}else if(choice==2) {
				System.out.println("Enter the Depositing amount");
				int deposit=scanner.nextInt();
				
				user.setBalance(deposit+user.getBalance());
				
				Transaction t=new Transaction(user.getTransactions().size()+1,"CashDepsoit",deposit,user.getBalance());
				user.getTransactions().add(t);
				
				System.out.println("Current Balance:"+user.getBalance());
			}else if(choice==3) {
				System.out.println("Enter the target account no:");
				int accountNo=scanner.nextInt();
				
				System.out.println("Enter the transferring amount:");
				int amount=scanner.nextInt();
				
				if(user.getBalance()-amount<1000) {
					System.out.println("Sorry you must maintain a minimum balance of Rs.1000");
				}else {
					Customer target=Bank.getInstance().getUserByAccount(accountNo);
					if(target==null) {
						System.out.println("Sorry this account does not exists");
					}else {
						user.setBalance(user.getBalance()-amount);
						target.setBalance(target.getBalance()+amount);
						
						Transaction t=new Transaction(user.getTransactions().size()+1,"TranferTo "+ accountNo,amount,user.getBalance());
						user.getTransactions().add(t);
						
						Transaction t2=new Transaction(target.getTransactions().size()+1,"TransferFrom "+ user.getAccountNo(),amount,target.getBalance());
						target.getTransactions().add(t2);
						
						System.out.println("Current Balance:"+user.getBalance());
					}
				}
			}else if(choice==4) {
				for(Transaction t:user.getTransactions()) {
					System.out.println(t);
				}
			}else if(choice==5) {
				System.out.println("Balance: "+user.getBalance());
			}else if(choice==6){
				break;
			}else if(choice==7) {
				System.out.println(user.getEncryptedPassword());
			}else {
				System.out.println("Invalid choice");
			}
		}
	}
	
	
}
