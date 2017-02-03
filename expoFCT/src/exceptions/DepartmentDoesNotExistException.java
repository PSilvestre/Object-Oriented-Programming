package exceptions;

public class DepartmentDoesNotExistException extends Exception {
	public DepartmentDoesNotExistException() {
		super("Department does not exist.");
	}

	DepartmentDoesNotExistException(String errMsg) {
		super(errMsg);
	}
}
