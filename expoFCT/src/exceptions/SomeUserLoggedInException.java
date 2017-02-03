package exceptions;

public class SomeUserLoggedInException extends Exception {
	public SomeUserLoggedInException() {
		super("There is a user logged in.");
	}

	SomeUserLoggedInException(String errMsg) {
		super(errMsg);
	}
}
