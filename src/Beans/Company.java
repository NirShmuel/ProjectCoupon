package Beans;

import java.util.Collection;

public class Company {
	
	private long id;
	private String compName;
	private String password;
	private String email;
	private Collection<Coupon> coupons;
	
	public Company(){
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getCompName() {
		return compName;
	}


	public void setCompName(String compName) {
		this.compName = compName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Collection<Coupon> getCoupons() {
		return coupons;
	}


	public void setCoupons(Collection<Coupon> coupons) {
		this.coupons = coupons;
	}


	@Override
	public String toString() {
		return "Company [id=" + id + ", compName=" + compName + ", password="
				+ password + ", email=" + email + ", coupons=" + coupons + "]";
	}
	

	

}
