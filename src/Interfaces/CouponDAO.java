package interfaces;

import java.sql.SQLException;
import java.util.Collection;

import exception.DoesNotExistException;
import exception.DuplicateNameException;
import exception.NoUpdateException;
import beans.Coupon;
import beans.CouponType;

public interface CouponDAO {
	
	public void createCoupon(Coupon coup) throws DuplicateNameException, SQLException;
	public void removeCoupon(long id) throws SQLException, DoesNotExistException;
	public void upDateCoupon(Coupon coup) throws SQLException, NoUpdateException;
	public Coupon getCoupon(long id) throws SQLException, DoesNotExistException;
	public Collection<Coupon> getAllCoupon() throws SQLException;
	public Collection<Coupon> getCouponByType(CouponType type) throws SQLException;
	public void removeAllCompanyCoupons(long Companyid) throws SQLException;

}
