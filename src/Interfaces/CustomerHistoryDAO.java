package interfaces;

import java.sql.SQLException;

import exception.DoesNotExistException;
import beans.Coupon;


public interface CustomerHistoryDAO {
	
	public void upDateHistory(long customerId, long couponId) throws DoesNotExistException, SQLException ;
	public Coupon getCoupon(long couponId) throws DoesNotExistException, SQLException;
}
