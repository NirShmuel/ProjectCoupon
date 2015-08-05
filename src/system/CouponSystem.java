package system;

import java.io.IOException;
import java.sql.SQLException;

import exception.PropertiesFileMissingException;
import exception.WrongCredentialsException;
import facade.AdminFacade;
import facade.CompanyFacade;
import facade.CustomerFacade;
import interfaces.CouponClientFacadeDAO;

public class CouponSystem implements CouponClientFacadeDAO {
	
	private CouponSystem instance;
	private AdminFacade admin;
	private CustomerFacade customer;
	private CompanyFacade company;



	@Override
	public AdminFacade adminLogin(String name, String password) throws WrongCredentialsException, SQLException, IOException, PropertiesFileMissingException {
		
		return admin.login(name, password);
		
	}

	@Override
	public CompanyFacade companylogin(long id, String password) throws WrongCredentialsException, SQLException, IOException, PropertiesFileMissingException {
		return company.login(id, password);
	}

	@Override
	public CustomerFacade custLogin(long id, String password) throws WrongCredentialsException, SQLException, IOException, PropertiesFileMissingException {
		return customer.login(id, password);
	}
	
	

}
