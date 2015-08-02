package exception;

public class outOfCouponException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3424310408501269392L;

	public outOfCouponException(){
		super("Sorry...out of coupons");
	}
	
	public outOfCouponException(String message){
		super();
	}

}
