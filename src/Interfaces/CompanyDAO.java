package interfaces;

import java.sql.SQLException;
import java.util.Collection;

import beans.Company;
import beans.Coupon;
import exception.DoesNotExistException;
import exception.DuplicateNameException;
import exception.NoUpdateException;
import exception.WrongCredentialsException;

public interface CompanyDAO {
	
	public void createCompany(Company comp) throws DuplicateNameException, SQLException;
	public void removeCompany(long id) throws SQLException, DoesNotExistException;
	public void updateCompany(Company comp) throws SQLException, NoUpdateException;
	public Company getCompany(long id) throws  SQLException, DoesNotExistException;
	public Collection<Company> getAllCompanies() throws SQLException;
	public Collection<Coupon> getCompanyCoupons(long id) throws SQLException;
	public boolean login(long id, String password) throws SQLException, WrongCredentialsException;
	public void insertCompanyToCoupon(long companyID, long couponId) throws DuplicateNameException, SQLException;

	
}
