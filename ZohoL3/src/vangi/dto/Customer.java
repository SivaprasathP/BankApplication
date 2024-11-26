package vangi.dto;

import java.util.ArrayList;
import java.util.List;

import vangi.respository.Bank;

public class Customer extends User{
	private int balance;
	private int accountNo;
	private List<Transaction> transactions;
	private String[] passwords;
	private int passwordChangeIndex;
	
	public Customer(int userID,int accountNo,String userType,String userName,int balance,String encryptedPassword) {
		super(userID,userType,userName,encryptedPassword);
		this.balance=balance;
		this.accountNo=accountNo;
		this.transactions=new ArrayList<>();
		this.transactions.add(new Transaction(1,"Opening",10000,10000));
		this.passwords=new String[3];
		this.passwords[0]=encryptedPassword;
		passwordChangeIndex=1;
		Bank.getInstance().setCustomerCount(Bank.getInstance().getCustomerCount()+1);
	}

	public int getBalance() {
		return balance;
	}

	public int getAccountNo() {
		return accountNo;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	public String[] getPasswords() {
		return this.passwords;
	}

	public void storeNewPassword(String newPassword) {
		passwords[passwordChangeIndex % 3]=newPassword;
		passwordChangeIndex++;
	}
}
