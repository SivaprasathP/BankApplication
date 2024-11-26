package vangi.respository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import vangi.dto.Admin;
import vangi.dto.Customer;
import vangi.dto.User;

public class Bank {
	private static Bank bank;
	
	private List<User> users;
	private int feesCollected;
	private int customerCount;
	private int adminCount;
	
	private Bank() {
		this.users=new ArrayList<>();
		this.feesCollected=0;
		this.customerCount=0;
		this.adminCount=0;
	}
	
	public static Bank getInstance() {
		if(bank==null) {
			bank=new Bank();
			bank.init();
		}
		return bank;
	}

	private void init() {
		users.add(new Customer(11,11011,"customer","Kumar",10000,encrpt("kumar")));
		users.add(new Customer(22,22022,"customer","Madhu",20000,encrpt("madhu")));
		users.add(new Customer(33,33033,"customer","Rahul",30000,encrpt("rahul")));
		users.add(new Customer(44,44044,"customer","Robin",40000,encrpt("robin")));
		
		users.add(new Admin(1,"admin","admin1",encrpt("xyz")));
	}

	public String encrpt(String string) {
		StringBuilder sb=new StringBuilder();
		for(char c:string.toCharArray()) {
			if(c=='9') {
				sb.append('0');
			}else if(c=='0') {
				sb.append('1');
			}else if(c=='Z') {
				sb.append('A');
			}else if(c=='z') {
				sb.append('a');
			}else {
				sb.append((char)(c+1));
			}
		}
		return sb.toString();
	}

	public User getUserFromBank(int userID, String password) {
		String encrptedPassword=encrpt(password);
		for(User user:users) {
			if(user.getUserID()==userID && user.getEncryptedPassword().equals(encrptedPassword)) {
				return user;
			}
		}
		return null;
	}

	public List<User> getUsers() {
		return users;
	}

	public int getFeesCollected() {
		return feesCollected;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void setFeesCollected(int feesCollected) {
		this.feesCollected = feesCollected;
	}

	public int getCustomerCount() {
		return customerCount;
	}

	public int getAdminCount() {
		return adminCount;
	}

	public void setCustomerCount(int customerCount) {
		this.customerCount = customerCount;
	}

	public void setAdminCount(int adminCount) {
		this.adminCount = adminCount;
	}

	public Customer getUserByAccount(int accountNo) {
		for(User user:users) {
			if(user.getUserType().equals("customer")) {
				Customer c=(Customer)user;
				if(c.getAccountNo()==accountNo) {
					return c;
				}
			}
		}
		return null;
	}

	public void addNewUser(Customer user) {
		users.add(user);
	}

	public List<Customer> topNCustomers(int n) {
		PriorityQueue<Customer> pq=new PriorityQueue<>(new Comparator<>() {
			public int compare(Customer a,Customer b) {
				return b.getBalance()-a.getBalance();
			}
		});
		
		for(User user:users) {
			if(user.getUserType().equals("customer")) {
				Customer c=(Customer)user;
				pq.add(c);
			}
		}
		
		List<Customer> topN=new ArrayList<>();
		while(n-->0 && !pq.isEmpty()) {
			topN.add(pq.poll());
		}
		
		return topN;
	}

	public boolean checkIsNewPasswordPresent(int userId,String newPassword) {
		for(User user:users) {
			if(user.getUserType()=="customer" && user.getUserID()==userId) {
				Customer customer=(Customer)user;
				for(String password:customer.getPasswords()) {
					if(password.equals(newPassword)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public Customer getCustomer(int userId) {
		for(User user:users) {
			if(user.getUserType()=="customer" && user.getUserID()==userId) {
				return (Customer)user;
			}
		}
		return null;
	}
	
}
