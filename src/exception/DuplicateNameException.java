package exception;

public class DuplicateNameException extends Exception {

	private static final long serialVersionUID = 8997789234084729112L;

	public DuplicateNameException() {
		super("Duplicate names");
	}

	public DuplicateNameException(String message) {
		super(message);
	}

	
	
}
