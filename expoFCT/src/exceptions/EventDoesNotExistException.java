package exceptions;

public class EventDoesNotExistException extends Exception {
	public EventDoesNotExistException() {
		super("Event does not exist.");
	}

	EventDoesNotExistException(String errMsg) {
		super(errMsg);
	}
}
