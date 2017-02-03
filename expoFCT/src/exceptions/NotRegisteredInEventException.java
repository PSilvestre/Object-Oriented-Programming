package exceptions;

public class NotRegisteredInEventException extends Exception {
	public NotRegisteredInEventException() {
		super("Not registered in the event.");
	}

	NotRegisteredInEventException(String errMsg) {
		super(errMsg);
	}
}
