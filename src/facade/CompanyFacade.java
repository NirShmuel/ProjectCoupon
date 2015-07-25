package facade;

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
import exception.WrongCredentialsException;

public class CompanyFacade {
	
	private CompanyDBDAO company = new CompanyDBDAO();
	private CustomerDBDAO customer = new CustomerDBDAO();
	private CouponDBDAO coupon = new CouponDBDAO();
	private long companyId;
	
	private CompanyFacade(){
		super();
	}
	/**
	 * 
	 * @param id
	 * @param password
	 * @return
	 * @throws WrongCredentialsException
	 * @throws SQLException
	 */
	public CompanyFacade login(long id, String password ) throws WrongCredentialsException, SQLException  {
		
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
		
		return coupon.getCouponByType(companyId, type);
		
	}
	
	public Collection<Coupon> getCouponByPrice(long price) throws SQLException{
		
		return coupon.getCouponByPrice( companyId , price);
		
	}
	

}
