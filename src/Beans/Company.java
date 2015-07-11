package beans;

import java.util.Collection;

public class Company {
	
	private long id;
	private String compName;
	private String password;
	private String email;
	private Collection<Coupon> coupons;
	
	
	
	public Company(long id, String compName, String password, String email,
			Collection<Coupon> coupons) {
		this.id = id;
		this.compName = compName;
		this.password = password;
		this.email = email;
		this.coupons = coupons;
	}

	public Company(){
	}

	public long getId() {
		return id;
	}


	public Company setId(long id) {
		this.id = id;
		return this;
	}


	public String getCompName() {
		return compName;
	}


	public Company setCompName(String compName) {
		this.compName = compName;
		return this;
	}


	public String getEmail() {
		return email;
	}


	public Company setEmail(String email) {
		this.email = email;
		return this;
	}


	public String getPassword() {
		return password;
	}


	public Company setPassword(String password) {
		this.password = password;
		return this;
	}


	public Collection<Coupon> getCoupons() {
		return coupons;
	}


	public Company setCoupons(Collection<Coupon> coupons) {
		this.coupons = coupons;
		return this;
	}


	@Override
	public String toString() {
		return "Company [id=" + id + ", compName=" + compName + ", password="
				+ password + ", email=" + email + ", coupons=" + coupons + "]";
	}
	

	

}
