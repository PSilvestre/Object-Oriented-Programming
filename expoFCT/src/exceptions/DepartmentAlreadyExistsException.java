package exceptions;

public class DepartmentAlreadyExistsException extends Exception {
	public DepartmentAlreadyExistsException() {
		super("Department name or id already exists.");
	}

	DepartmentAlreadyExistsException(String errMsg) {
		super(errMsg);
	}
}
