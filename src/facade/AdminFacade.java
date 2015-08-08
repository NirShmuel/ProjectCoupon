package facade;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import system.Common;
import beans.Company;
import beans.Customer;
import dao.CompanyDBDAO;
import dao.CouponDBDAO;
import dao.CustomerDBDAO;
import exception.DoesNotExistException;
import exception.DuplicateNameException;
import exception.NoUpdateException;
import exception.WrongCredentialsException;

public class AdminFacade {
	
	private CompanyDBDAO company;
	private CustomerDBDAO customer;
	private CouponDBDAO coupon;
	
	
	private AdminFacade() throws SQLException, IOException{
		super();
		company = new CompanyDBDAO();
		customer = new CustomerDBDAO();
		coupon = new CouponDBDAO();
	}
	
	public static AdminFacade login(String name, String password) throws SQLException, IOException, WrongCredentialsException {
		if( name.equals("admin") && password.equals("1234") ){
			return new AdminFacade();
		}else{
			throw new WrongCredentialsException("Unauthorized user.");
		}
		
	}
		
	public void createCompany(Company comp) throws DuplicateNameException, SQLException {
		
		company.createCompany(comp);
		Logger.getLogger(Common.LOGGER).log(Level.INFO,"Company was created (id=" + comp.getId() + ")"); 
		
	}
	
	
	public void removeConpany(long id) throws SQLException, DoesNotExistException{
		
		coupon.removeAllCompanyCoupons(id);
		
		company.removeCompany(id);
		Logger.getLogger(Common.LOGGER).log(Level.INFO,"Company was removed (id=" + id + ")");
	}
	
	public void updateCompany(Company comp) throws SQLException, NoUpdateException{
		
		company.updateCompany(comp);
		Logger.getLogger(Common.LOGGER).log(Level.INFO,"Company was updated (id=" + comp.getId() + ")");
		
	}
	
	
	public Company getCompany(long id) throws SQLException, DoesNotExistException{
		
		return company.getCompany(id);
	}
	
	public Collection<Company> getAllCompanies() throws SQLException{
		
		return company.getAllCompanies();
		
	}
	
	public void createCustomer(Customer cust) throws DuplicateNameException, SQLException{
		
		customer.createCustomer(cust);
		Logger.getLogger(Common.LOGGER).log(Level.INFO,"Customer was created (id=" + cust.getId() + ")");
	}
	
	public void removeCustomer(long id) throws SQLException, DoesNotExistException{
		
		customer.removeCustomer(id);
		Logger.getLogger(Common.LOGGER).log(Level.INFO,"Customer was removed (id=" + id + ")");
	}
	
	public void updateCustomer(Customer cust) throws SQLException, NoUpdateException{
		
		customer.upDateCustomer(cust);
		Logger.getLogger(Common.LOGGER).log(Level.INFO,"Customer was updated (id=" + cust.getId() + ")");
	}
	
	public Customer getCustomer(long id) throws SQLException, DoesNotExistException{
		
		return customer.getCustomer(id);
		
	}
	
	public Collection<Customer> getAllCustomer() throws SQLException{
		return customer.getAllCustomer();
	}
	
}
