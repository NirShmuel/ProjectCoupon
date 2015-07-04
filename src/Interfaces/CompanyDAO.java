package interfaces;

import java.sql.SQLException;
import java.util.Collection;

import exception.DuplicateCompanyNameException;
import exception.ProjectCouponException;
import exception.SqlException;
import beans.Company;
import beans.Coupon;

public interface CompanyDAO {
	
	public void createCompany(Company comp) throws DuplicateCompanyNameException, SQLException;
	public void removeCompany(long id);
	public void updateCompany(Company comp);
	public Company getCompany(long id) throws DuplicateCompanyNameException, SQLException;
	public Collection<Company> getAllCompanies();
	public Collection<Coupon> getCoupons();
	public boolean login(String name, String password);
	
}
