package exception;

public class SqlException extends Exception {
	
	public SqlException(){
		super("Company with this name exists");
	}
	
	public SqlException(String message){
		super(message);
	}
}
