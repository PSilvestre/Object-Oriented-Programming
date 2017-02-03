package exceptions;

public class EventRegisteredException extends Exception {
	public EventRegisteredException() {
		super("Event registered.");
	}

	EventRegisteredException(String errMsg) {
		super(errMsg);
	}
}
