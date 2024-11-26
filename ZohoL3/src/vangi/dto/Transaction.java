package vangi.dto;

public class Transaction {
	private int transactionID;
	private String transactionType;
	private int amount;
	private int currentBalance;
	
	public Transaction(int transactionID, String transactionType, int amount, int currentBalance) {
		this.transactionID = transactionID;
		this.transactionType = transactionType;
		this.amount = amount;
		this.currentBalance = currentBalance;
	}

	public int getTransactionID() {
		return transactionID;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public int getAmount() {
		return amount;
	}

	public int getCurrentBalance() {
		return currentBalance;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setCurrentBalance(int currentBalance) {
		this.currentBalance = currentBalance;
	}

	@Override
	public String toString() {
		return "[transactionID=" + transactionID + ", transactionType=" + transactionType + ", amount="
				+ amount + ", currentBalance=" + currentBalance + "]";
	}
	
	
}
