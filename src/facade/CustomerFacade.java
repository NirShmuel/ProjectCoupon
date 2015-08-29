package facade;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import beans.CouponType;
import beans.CustomerHistory;
import dao.CouponDBDAO;
import dao.CustomerDBDAO;
import dao.CustomerHistoryDBDAO;
import exception.DoesNotExistException;
import exception.DuplicateNameException;
import exception.WrongCredentialsException;
import exception.outOfCouponException;

public class CustomerFacade {
	
	private CustomerDBDAO customer;
	private CouponDBDAO coupon;
	private CustomerHistoryDBDAO history;
	private long customerId;
	
	private CustomerFacade(long id) throws SQLException, IOException{
		super();
		customer = new CustomerDBDAO();
		coupon = new CouponDBDAO();
		history = new CustomerHistoryDBDAO();
		customerId = id;
	}
	
	public static CustomerFacade login(long id, String password ) throws WrongCredentialsException, SQLException, IOException  {
		CustomerDBDAO customer = new CustomerDBDAO();
		if(customer.login(id, password)){
			return new CustomerFacade(id);
		}else{
			throw new WrongCredentialsException();
		}
	}
	
	public Long customerId(){
		return customerId;
	}
	
	public String getCustomerName(long customerId) throws SQLException, DoesNotExistException{
		
		return customer.getCustomer(customerId).getCustName();
		
	}
	
	public void purchaseCoupon(long couponId) throws  outOfCouponException, SQLException, DuplicateNameException, DoesNotExistException{
		
		
		if (coupon.CheckAmount(couponId)){
			
			customer.insertCustomerToCoupon(customerId, couponId);
			coupon.upDateAmount(couponId);
			history.upDateHistory(customerId, couponId);
		}
		
	}
	
	public List<CustomerHistory> getAllPurchaseHistory() throws SQLException{
		
		return history.getAllCustomerCoupons(customerId) ;
		
	}
	
	public List<CustomerHistory> getAllPurchaseCouponByType(CouponType type) throws SQLException{
		
		return history.getAllCustomerCouponsByType(customerId, type);
		
	}
	
	 

}
