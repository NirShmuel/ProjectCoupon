package interfaces;

import java.sql.SQLException;
import java.util.List;

import exception.DoesNotExistException;
import beans.Coupon;
import beans.CouponType;
import beans.CustomerHistory;


public interface CustomerHistoryDAO {
	
	public void upDateHistory(long customerId, long couponId) throws DoesNotExistException, SQLException ;
	public Coupon getCoupon(long couponId) throws DoesNotExistException, SQLException;
	public List<CustomerHistory> getAllCustomerCoupons(long id) throws SQLException;
	public List<CustomerHistory> getAllCustomerCouponsByType(long id, CouponType type ) throws SQLException;
}
