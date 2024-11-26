package vangi.dto;

import vangi.respository.Bank;

public class Admin extends User{
	public Admin(int userID,String userType,String userName,String encryptedPassword) {
		super(userID,userType,userName,encryptedPassword);
		Bank.getInstance().setAdminCount(Bank.getInstance().getAdminCount()+1);
	}
}
