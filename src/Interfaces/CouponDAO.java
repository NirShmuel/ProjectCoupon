package Interfaces;

import java.util.Collection;

import Beans.Coupon;
import Beans.CouponType;

public interface CouponDAO {
	
	public void createCoupon(Coupon coup);
	public void removeCoupon(Coupon coup);
	public void upDateCoupon(Coupon coup);
	public Coupon getCoupon(long id);
	public Collection<Coupon> getAllCoupon();
	public Collection<Coupon> getCouponByType(CouponType type);

}
