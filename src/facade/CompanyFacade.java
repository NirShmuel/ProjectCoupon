package facade;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import beans.Company;
import beans.Coupon;
import beans.CouponType;
import dao.CompanyDBDAO;
import dao.CouponDBDAO;
import dao.CustomerDBDAO;
import exception.DoesNotExistException;
import exception.DuplicateNameException;
import exception.NoUpdateException;
import exception.PropertiesFileMissingException;
import exception.WrongCredentialsException;

public class CompanyFacade {
	
	private CompanyDBDAO company;
	private CouponDBDAO coupon;
	private long companyId;
	
	private CompanyFacade() throws SQLException, IOException, PropertiesFileMissingException{
		super();
		company = new CompanyDBDAO();
		new CustomerDBDAO();
		coupon = new CouponDBDAO();
	}
	/**
	 * 
	 * @param id
	 * @param password
	 * @return
	 * @throws WrongCredentialsException
	 * @throws SQLException
	 * @throws PropertiesFileMissingException 
	 * @throws IOException 
	 */
	public CompanyFacade login(long id, String password ) throws WrongCredentialsException, SQLException, IOException, PropertiesFileMissingException  {
		
		//TODO: 
		if(company.login(id, password)){
			
			companyId = id;
			return new CompanyFacade();
			
		}else{
			throw new WrongCredentialsException();
		}
	}
	
	public Company getDetails() throws SQLException, DoesNotExistException{
		
		return company.getCompany(companyId);
		
	}
	
	/**
	 * 
	 * @param coup
	 * @throws DuplicateNameException
	 * @throws SQLException
	 */
	public void createCoupon(Coupon coup) throws DuplicateNameException, SQLException{
		
		coupon.createCoupon(coup);
		
		company.insertCompanyToCoupon(companyId, coup.getId());
		
	}
	/**
	 * 
	 * @param id
	 * @throws SQLException
	 * @throws DoesNotExistException
	 */
	public void removeCoupon(long id) throws SQLException, DoesNotExistException{
		
		coupon.removeCoupon(id);
		
		
	}
	/**
	 * 
	 * @param coup
	 * @throws SQLException
	 * @throws NoUpdateException
	 */
	public void upDateCoupon(Coupon coup) throws SQLException, NoUpdateException{
		
		coupon.upDateCoupon(coup);
		
	}
	
	public Collection<Coupon> gteAllCoupons() throws SQLException{
		
	 	return company.getCompanyCoupons(companyId);
		
	}
	
	public Collection<Coupon> getCouponByType(CouponType type) throws SQLException{
		
		return coupon.getCompanyCouponByType(companyId, type);
		
	}
	
	public Collection<Coupon> getCouponByPrice(long price) throws SQLException{
		
		return coupon.getCouponByPrice( companyId , price);
		
	}
}
