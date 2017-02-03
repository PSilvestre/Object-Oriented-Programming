package exceptions;

public class UserCannotExecuteCommandException extends Exception {
	public UserCannotExecuteCommandException() {
		super("User cannot execute this command.");
	}

	UserCannotExecuteCommandException(String errMsg) {
		super(errMsg);
	}
}
