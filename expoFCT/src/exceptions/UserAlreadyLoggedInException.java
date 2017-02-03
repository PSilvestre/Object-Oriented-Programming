package exceptions;

public class UserAlreadyLoggedInException extends Exception {
	public UserAlreadyLoggedInException() {
		super("User already logged in.");
	}

	UserAlreadyLoggedInException(String errMsg) {
		super(errMsg);
	}
}
