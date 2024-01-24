package Package;

public class Account {
	private String accountId;
	private String accountName;
	private String password;
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Account(String accountId, String accountName, String password) {
		super();
		this.accountId = accountId;
		this.accountName = accountName;
		this.password = password;
	}
	
	public Account() {
		super();
	}
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountName=" + accountName + ", password=" + password + "]";
	}
	

}
