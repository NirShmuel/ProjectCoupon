	package beans;

import java.util.Collection;

public class Customer {
	
	private long  id;
	private String custName;
	private String password;
	private Collection<Coupon> coupons;

	public Customer(long id, String custName, String password,
			Collection<Coupon> coupons) {
		this.id = id;
		this.custName = custName;
		this.password = password;
		this.coupons = coupons;
	}

	public Customer() {
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getCustName() {
		return custName;
	}

	public String setCustName(String custName) {
		return this.custName = custName;
	}

	public String getPassword() {
		return password;
	}

	public Customer setPassword(String password) {
		this.password = password;
		return this;
	}

	public Collection<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(Collection<Coupon> coupons) {
		this.coupons = coupons;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", custName=" + custName + ", password="
				+ password + ", coupons=" + coupons + "]";
	}
	
	
}
