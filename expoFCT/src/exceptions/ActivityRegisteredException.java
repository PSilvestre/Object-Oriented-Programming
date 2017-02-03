package exceptions;

public class ActivityRegisteredException extends Exception {
	public ActivityRegisteredException() {
		super("Activity registered.");
	}

	ActivityRegisteredException(String errMsg) {
		super(errMsg);
	}
}
