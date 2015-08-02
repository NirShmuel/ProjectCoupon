package interfaces;

import java.sql.SQLException;
import java.util.Collection;

import javax.xml.crypto.Data;

import exception.DoesNotExistException;
import exception.DuplicateNameException;
import exception.NoUpdateException;
import exception.outOfCouponException;
import beans.Coupon;
import beans.CouponType;

public interface CouponDAO {
	
	public void createCoupon(Coupon coup) throws DuplicateNameException, SQLException;
	public void removeCoupon(long id) throws SQLException, DoesNotExistException;
	public void upDateCoupon(Coupon coup) throws SQLException, NoUpdateException;
	public Coupon getCoupon(long id) throws SQLException, DoesNotExistException;
	public Collection<Coupon> getAllCoupon() throws SQLException;
	public Collection<Coupon> getCompanyCouponByType(long companyId , CouponType type) throws SQLException;
	public Collection<Coupon> getCustomerCouponByType(long customerId , CouponType type) throws SQLException;
	public void removeAllCompanyCoupons(long Companyid) throws SQLException;
	public Collection<Coupon> getCouponByPrice(long companyId , long price) throws SQLException;
	public Collection<Coupon> getCouponByDate(long companyId , Data date);
	public void upDateAmount(long couponId) throws SQLException, outOfCouponException;
	public boolean CheckAmount(long couponId) throws SQLException, outOfCouponException;
}
