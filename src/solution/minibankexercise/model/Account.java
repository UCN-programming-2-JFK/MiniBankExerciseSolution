package solution.minibankexercise.model;

public class Account {
	private int accNo;
	private float balance;
	private float inRate;
	private String clientNo;

	public Account(int accNo, float balance, float inRate, String clientNo) {
		super();
		this.setAccNo(accNo);
		this.setBalance(balance);
		this.setInRate(inRate);
		this.setClientNo(clientNo);
	}

	public int getAccNo() {
		return accNo;
	}

	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public float getInRate() {
		return inRate;
	}

	public void setInRate(float inRate) {
		this.inRate = inRate;
	}

	public String getClientNo() {
		return clientNo;
	}

	public void setClientNo(String clientNo) {
		this.clientNo = clientNo;
	}

	public String toString() {
		return "[ACCOUNT] accountNumber: " + getAccNo() + ", balance: " + getBalance() + ", inRate: " + getInRate()
				+ ", clientNumber:" + getClientNo();
	}
}