package exceptions;

public class CommentRegisteredException extends Exception {
	public CommentRegisteredException() {
		super("Comment registered.");
	}

	CommentRegisteredException(String errMsg) {
		super(errMsg);
	}
}