package facade;


import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;



import beans.Company;
import beans.Coupon;
import beans.CouponType;
import dao.CompanyDBDAO;
import dao.CouponDBDAO;
import exception.DoesNotExistException;
import exception.DuplicateNameException;
import exception.NoUpdateException;
import exception.PropertiesFileMissingException;
import exception.WrongCredentialsException;

public class CompanyFacade {
	
	private CompanyDBDAO company;
	private CouponDBDAO coupon;
	private long companyId;
	
	private CompanyFacade(long id) throws SQLException, IOException{
		super();
		company = new CompanyDBDAO();
		coupon = new CouponDBDAO();
		companyId = id;
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
	public static  CompanyFacade login(long id, String password ) throws WrongCredentialsException, SQLException, IOException  {
		CompanyDBDAO company = new CompanyDBDAO();
		if(company.login(id, password)){
			return new CompanyFacade(id);
		}else{
			throw new WrongCredentialsException();
		}
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 * @throws DoesNotExistException
	 */
	
	public Coupon getCoupon(long id) throws SQLException, DoesNotExistException{
		return coupon.getCoupon(id);
	}
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Company getDetails() throws SQLException{
		
		try {
			return company.getCompany(companyId);
		} catch (DoesNotExistException e) {
			// Should not ever get here unless there is a problem with the system
			return null;
		}
		
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
		
		if (company.isCouponBelongsToCompany(companyId, id)){
		coupon.removeCoupon(id);
		}else {
		throw new DoesNotExistException("The coupon is not exist");
		}
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
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Collection<Coupon> getAllCoupons() throws SQLException{
		
	 	return company.getCompanyCoupons(companyId);
		
	}
	
	/**
	 * 
	 * @param type
	 * @return
	 * @throws SQLException
	 */
	public Collection<Coupon> getCouponByType(CouponType type) throws SQLException{
		
		return coupon.getCompanyCouponByType(companyId, type);
		
	}
	
	/**
	 * 
	 * @param price
	 * @return
	 * @throws SQLException
	 */
	public Collection<Coupon> getCouponByPrice(double price) throws SQLException{
		
		return coupon.getCouponByPrice( companyId , price);
		
	}
	
	public Collection<Coupon> getCouponByDate(Date date) throws SQLException{
		
		return coupon.getCouponByDate(companyId, date);
		
	}


}
