package exceptions;

public class CommentDoesNotExistException extends Exception {
	public CommentDoesNotExistException() {
		super("Comment does not exist.");
	}

	CommentDoesNotExistException(String errMsg) {
		super(errMsg);
	}
}
