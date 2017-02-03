package user;

public abstract class AbstractUserClass implements User {

	private String email;
	private String password;

	public AbstractUserClass(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
}
