package facade;

import java.sql.SQLException;

import beans.Coupon;
import dao.CompanyDBDAO;
import dao.CouponDBDAO;
import dao.CustomerDBDAO;
import exception.DoesNotExistException;
import exception.DuplicateNameException;

public class CompanyFacade {
	
	private CompanyDBDAO company = new CompanyDBDAO();
	private CustomerDBDAO customer = new CustomerDBDAO();
	private CouponDBDAO coupon = new CouponDBDAO();
	
	
	private CompanyFacade(){
		super();
	}
	
	public CompanyFacade login(long id, String password ) throws SQLException{
		
		
		if(company.login(id, password)){
		return new CompanyFacade();
		}else{
			throw new Exception();
		}
	}
	
	public void createCoupon(Coupon coup) throws DuplicateNameException, SQLException{
		
		coupon.createCoupon(coup);
		//TODO: how to get the company id
		company.insertCompanyToCoupon(Compa, coup.getId());
		
	}
	
	public void removeCoupon(long id) throws SQLException, DoesNotExistException{
		
		coupon.removeCoupon(id);
		
	}
	
	
	
	
	

	

}
