package exceptions;

public class EventNameAlreadyTakenException extends Exception {
	public EventNameAlreadyTakenException() {
		super("Event name already exists.");
	}

	EventNameAlreadyTakenException(String errMsg) {
		super(errMsg);
	}
}
