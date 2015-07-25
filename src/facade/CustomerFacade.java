package facade;

import interfaces.CouponClientFacadeDAO;

import java.sql.SQLException;
import java.util.Collection;

import javax.swing.text.DefaultEditorKit.CutAction;

import beans.Coupon;
import beans.CouponType;
import dao.CompanyDBDAO;
import dao.CouponDBDAO;
import dao.CustomerDBDAO;
import exception.DuplicateNameException;
import exception.WrongCredentialsException;

public class CustomerFacade implements CouponClientFacadeDAO {
	
	private CompanyDBDAO company = new CompanyDBDAO();
	private CustomerDBDAO customer = new CustomerDBDAO();
	private CouponDBDAO coupon = new CouponDBDAO();
	private long customerId;
	
	private CustomerFacade(){
		super();
	}
	
	public CustomerFacade login(long id, String password ) throws WrongCredentialsException, SQLException  {
		
		if(company.login(id, password)){
			
			customerId = id;
			return new CustomerFacade();
			
		}else{
			throw new WrongCredentialsException();
		}
	}
	
	public void purchaseCoupon(long couponId) throws DuplicateNameException, SQLException{
		
		
		customer.insertCustomerToCoupon(customerId, couponId);
		
	}
	
	public Collection<Coupon> getAllPurchaseHistory() throws SQLException{
		
		return null ;
		
	}
	
	public Collection<Coupon> getAllPurchaseCouponByType(CouponType type) throws SQLException{
		
		return coupon.getCustomerCouponByType(customerId, type);
		
	}
	
	

	@Override
	public CouponClientFacadeDAO adminLogin(String name, String password)
			throws WrongCredentialsException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	 

}
