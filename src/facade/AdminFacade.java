package facade;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import beans.Company;
import beans.Customer;
import dao.CompanyDBDAO;
import dao.CouponDBDAO;
import dao.CustomerDBDAO;
import exception.DoesNotExistException;
import exception.DuplicateNameException;
import exception.NoUpdateException;
import exception.PropertiesFileMissingException;

public class AdminFacade {
	
	private CompanyDBDAO company;
	private CustomerDBDAO customer;
	private CouponDBDAO coupon;
	
	
	private AdminFacade() throws SQLException, IOException, PropertiesFileMissingException{
		super();
		company = new CompanyDBDAO();
		customer = new CustomerDBDAO();
		coupon = new CouponDBDAO();
	}
	
	public AdminFacade login(String name, String password) throws SQLException, IOException, PropertiesFileMissingException {
		if(name == "admin" && password == "1234" ){
			return new AdminFacade();
		}
		return null;
	}
		
	public void createCompany(Company comp) throws DuplicateNameException, SQLException {
		
		company.createCompany(comp);
		
	}
	
	
	public void removeConpany(long id) throws SQLException, DoesNotExistException{
		
		coupon.removeAllCompanyCoupons(id);
		
		company.removeCompany(id);
		
	}
	
	public void updateCompany(Company comp) throws SQLException, NoUpdateException{
		
		company.updateCompany(comp);
		
	}
	
	
	public Company getCompany(long id) throws SQLException, DoesNotExistException{
		
		return company.getCompany(id);
		
	}
	
	public Collection<Company> getAllCompanies() throws SQLException{
		
		return company.getAllCompanies();
		
	}
	
	public void createCustomer(Customer cust) throws DuplicateNameException, SQLException{
		
		customer.createCustomer(cust);
		
	}
	
	public void removeCustomer(long id) throws SQLException, DoesNotExistException{
		
		customer.removeCustomer(id);
		
	}
	
	public void updateCustomer(Customer cust) throws SQLException, NoUpdateException{
		
		customer.upDateCustomer(cust);
		
	}
	
	public Customer getCustomer(long id) throws SQLException, DoesNotExistException{
		
		return customer.getCustomer(id);
		
	}
	
	public Collection<Customer> getAllCustomer() throws SQLException{
		return customer.getAllCustomer();
	}
}
