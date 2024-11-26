package vangi.screens.login;

import vangi.dto.Customer;
import vangi.dto.User;
import vangi.respository.Bank;

class LoginViewModel {
	private Login login;

	public LoginViewModel(Login login) {
		this.login=login;
	}

	public User getUser(int userID, String password) {
		return Bank.getInstance().getUserFromBank(userID,password);
	}

	public Customer createUser(String userName, String password) {
		int accountNo=(Bank.getInstance().getCustomerCount()+1)*11011;
		int balance=10000;
		int userId=(Bank.getInstance().getCustomerCount()+1)*11;
		return new Customer(userId,accountNo,"customer",userName,balance,Bank.getInstance().encrpt(password));
	}

}
