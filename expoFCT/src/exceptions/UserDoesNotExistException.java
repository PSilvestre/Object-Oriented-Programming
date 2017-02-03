package exceptions;

public class UserDoesNotExistException extends Exception {
	public UserDoesNotExistException() {
		super("User does not exist.");
	}

	UserDoesNotExistException(String errMsg) {
		super(errMsg);
	}
}
