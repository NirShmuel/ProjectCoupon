package facade;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import beans.Coupon;
import beans.CouponType;
import dao.CompanyDBDAO;
import dao.CouponDBDAO;
import dao.CustomerDBDAO;
import dao.CustomerHistoryDBDAO;
import exception.DoesNotExistException;
import exception.DuplicateNameException;
import exception.PropertiesFileMissingException;
import exception.WrongCredentialsException;
import exception.outOfCouponException;

public class CustomerFacade {
	
	private CompanyDBDAO company;
	private CustomerDBDAO customer;
	private CouponDBDAO coupon;
	private CustomerHistoryDBDAO history;
	private long customerId;
	
	private CustomerFacade() throws SQLException, IOException, PropertiesFileMissingException{
		super();
		company = new CompanyDBDAO();
		customer = new CustomerDBDAO();
		coupon = new CouponDBDAO();
		history = new CustomerHistoryDBDAO();
	}
	
	public CustomerFacade login(long id, String password ) throws WrongCredentialsException, SQLException, IOException, PropertiesFileMissingException  {
		
		if(company.login(id, password)){
			
			customerId = id;
			return new CustomerFacade();
			
		}else{
			throw new WrongCredentialsException();
		}
	}
	
	public void purchaseCoupon(long couponId) throws  outOfCouponException, SQLException, DuplicateNameException, DoesNotExistException{
		
		
		if (coupon.CheckAmount(couponId)){
			
			customer.insertCustomerToCoupon(customerId, couponId);
			coupon.upDateAmount(couponId);
			history.upDateHistory(customerId, couponId);
		}
		
	}
	
	public Collection<Coupon> getAllPurchaseHistory() throws SQLException{
		
		return null ;
		
	}
	
	public Collection<Coupon> getAllPurchaseCouponByType(CouponType type) throws SQLException{
		
		return coupon.getCustomerCouponByType(customerId, type);
		
	}
	
	

	
	 

}
