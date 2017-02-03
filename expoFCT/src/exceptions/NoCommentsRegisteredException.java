package exceptions;

public class NoCommentsRegisteredException extends Exception {
	public NoCommentsRegisteredException() {
		super("No comments registed.");
	}

	NoCommentsRegisteredException(String errMsg) {
		super(errMsg);
	}
}
