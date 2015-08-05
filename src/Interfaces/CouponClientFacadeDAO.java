package interfaces;

import java.io.IOException;
import java.sql.SQLException;

import exception.PropertiesFileMissingException;
import exception.WrongCredentialsException;
import facade.AdminFacade;
import facade.CompanyFacade;
import facade.CustomerFacade;

public interface CouponClientFacadeDAO {

	public AdminFacade adminLogin(String name, String password) throws WrongCredentialsException, SQLException, IOException, PropertiesFileMissingException;
	public CompanyFacade companylogin(long id, String password) throws WrongCredentialsException, SQLException, IOException, PropertiesFileMissingException;
	public CustomerFacade custLogin(long id, String password) throws WrongCredentialsException, SQLException, IOException, PropertiesFileMissingException;

}
