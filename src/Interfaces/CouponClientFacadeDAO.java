package interfaces;

import java.sql.SQLException;

import exception.WrongCredentialsException;

public interface CouponClientFacadeDAO {
	
	public CouponClientFacadeDAO login(long id, String password) throws WrongCredentialsException, SQLException;
	public CouponClientFacadeDAO adminLogin(String name, String password) throws WrongCredentialsException, SQLException;
	

}
