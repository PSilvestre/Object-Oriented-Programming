package user;

public class AdminClass extends AbstractUserClass implements Admin {
	private static final String USERTYPE = "ADMIN";
	
	public static int numberOfAdmins = 0;

	public AdminClass(String email, String password) {
		super(email, password);
		numberOfAdmins++;
	}
	
	public String toString(){
		return super.getEmail() + "; " + USERTYPE;
	}

}
