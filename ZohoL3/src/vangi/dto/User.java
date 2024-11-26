package vangi.dto;

public abstract class User {
	private int userID;
	private String userType;
	private String userName;
	private String encryptedPassword;
	
	public User(int userID, String userType, String userName,String encryptedPassword) {
		this.userID = userID;
		this.userName=userName;
		this.userType = userType;
		this.encryptedPassword = encryptedPassword;
	}

	public int getUserID() {
		return userID;
	}

	public String getUserType() {
		return userType;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		encryptedPassword = encryptedPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
