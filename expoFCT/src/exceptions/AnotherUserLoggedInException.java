package exceptions;

public class AnotherUserLoggedInException extends Exception {
	public AnotherUserLoggedInException() {
		super("Another user is logged in.");
	}

	AnotherUserLoggedInException(String errMsg) {
		super(errMsg);
	}
}
