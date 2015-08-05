package beans;

import java.util.Date;

public class CustomerHistory {

	private long customerId;
	private long couponId ;
	private CouponType type;
	private String couponTitle;
	private Date endDate;
	private Date purchaseDate;
	private double price;
	
	public CustomerHistory(){
		
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long tempCustomerId) {
		customerId = tempCustomerId;
	}

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}
	public CouponType getType() {
		return type;
	}

	public void setType(CouponType type) {
		this.type = type;
	}


	public String getCouponTitle() {
		return couponTitle;
	}

	public void setCouponTitle(String couponTitle) {
		this.couponTitle = couponTitle;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "CustomerHistory [customerId=" + customerId + ", couponId="
				+ couponId + ", type=" + type + ", couponTitle=" + couponTitle
				+ ", endDate=" + endDate + ", purchaseDate=" + purchaseDate
				+ ", price=" + price + "]";
	}
	
	


}
