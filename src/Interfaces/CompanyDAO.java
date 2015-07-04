package Interfaces;

import java.util.Collection;

import Beans.Company;
import Beans.Coupon;

public interface CompanyDAO {
	
	public void createCompany(Company comp);
	public void removeCompany(Company comp);
	public void upDateCompany(Company comp);
	public Company getCompany(long id);
	public Collection<Company> getAllCompanies();
	public Collection<Coupon> getCoupons();
	public boolean login(String name, String password);
	
}
