package exception;

public class WrongCredentialsException extends Exception {
	
	
	private static final long serialVersionUID = 8912329426203834455L;

	public WrongCredentialsException() {
		super();
	}

	public WrongCredentialsException(String message) {
		super(message);
	}
	
	

}
