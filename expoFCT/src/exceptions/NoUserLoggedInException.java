package exceptions;

public class NoUserLoggedInException extends Exception {
	public NoUserLoggedInException() {
		super("No user is logged in.");
	}

	NoUserLoggedInException(String errMsg) {
		super(errMsg);
	}
}
