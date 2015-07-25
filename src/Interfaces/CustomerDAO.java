package interfaces;

import java.sql.SQLException;
import java.util.Collection;

import beans.Coupon;
import beans.Customer;
import exception.DoesNotExistException;
import exception.DuplicateNameException;
import exception.NoUpdateException;

public interface CustomerDAO {
	
	public void createCustomer(Customer cust) throws DuplicateNameException, SQLException;
	public void removeCustomer(long id) throws SQLException, DoesNotExistException;
	public void upDateCustomer(Customer cust) throws SQLException, NoUpdateException;
	public Customer getCustomer(long id) throws SQLException, DoesNotExistException;
	public Collection<Customer> getAllCustomer() throws SQLException;
	public Collection<Coupon> getCustomerCoupons(long id) throws SQLException;
	public void insertCustomerToCoupon(long customerID, long couponId) throws DuplicateNameException, SQLException;
	public boolean login(String custName, String password);
	
	
}
