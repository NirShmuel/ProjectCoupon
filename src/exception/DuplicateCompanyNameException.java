package exception;

public class DuplicateCompanyNameException extends Exception {

	private static final long serialVersionUID = 8997789234084729112L;

	public DuplicateCompanyNameException() {
		super("Duplicate names");
	}

	public DuplicateCompanyNameException(String message) {
		super(message);
	}

	
	
}
