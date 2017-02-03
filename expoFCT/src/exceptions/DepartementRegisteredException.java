package exceptions;

public class DepartementRegisteredException extends Exception {
	public DepartementRegisteredException() {
		super("Departement registered.");
	}

	DepartementRegisteredException(String errMsg) {
		super(errMsg);
	}
}
