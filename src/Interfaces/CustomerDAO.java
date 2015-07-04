package interfaces;

import java.util.Collection;

import beans.Company;
import beans.Coupon;
import beans.Customer;

public interface CustomerDAO {
	
	public void createCustomer(Customer cust);
	public void removeCustomer(Customer cust);
	public void upDateCustomer(Customer cust);
	public Customer getCustomer(long id);
	public Collection<Company> getAllCustomer();
	public Collection<Coupon> getCoupons();
	public boolean login(String custName, String password);

}
