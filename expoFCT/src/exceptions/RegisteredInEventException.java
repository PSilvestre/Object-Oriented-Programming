package exceptions;

public class RegisteredInEventException extends Exception {
	public RegisteredInEventException() {
		super("Registered in event.");
	}

	RegisteredInEventException(String errMsg) {
		super(errMsg);
	}
}
