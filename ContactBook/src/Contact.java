
public class Contact {
	
	private String name;
	private int phone;
	private String email;
	
	public Contact(String name, int phone, String email) {
		this.name = name;
		this.phone= phone;
		this.email= email;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPhone() {
		return phone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPhone(int phone) {
		this.phone = phone;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean equals(Contact otherContact) {
		 return this.getName() == otherContact.getName()
				 && this.getEmail() == otherContact.getEmail()
				 && this.getPhone() == otherContact.getPhone();
	}
}
