package exceptions;

public class UserAlreadyExistsException extends Exception {
	public UserAlreadyExistsException() {
		super("User already exists.");
	}

	UserAlreadyExistsException(String errMsg) {
		super(errMsg);
	}
}
