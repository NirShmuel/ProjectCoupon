package beans;

import java.util.List;

public class CustomerHistory {
	
	private long CustomerId;
	private List<Coupon> coupons;
	public long getCustomerId() {
		return CustomerId;
	}
	public void setCustomerId(long customerId) {
		CustomerId = customerId;
	}
	public List<Coupon> getCoupons() {
		return coupons;
	}
	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

}
