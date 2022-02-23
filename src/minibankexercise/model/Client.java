package minibankexercise.model;

public class Client {
	
	private String cpr;
	private String name;
	private String street;
	private String postalCode;
	private String status;
	
	
	public Client(String cpr, String name, String street, String postalCode, String status) {
		this.setCpr(cpr);
		this.setName(name);
		this.setStreet(street);
		this.setPostalCode (postalCode);
		this.setStatus(status);
	}

	public String getCpr() {
		return cpr;
	}

	public void setCpr(String cpr) {
		this.cpr = cpr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String toString() {
		return "[CLIENT] cpr: " + getCpr() + ", name: " + getName() + ", street: " + getStreet()
				+ ", postalcode: " + getPostalCode() + ", status:" + getStatus();
	}
}