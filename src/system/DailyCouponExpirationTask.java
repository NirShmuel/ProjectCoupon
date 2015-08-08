package system;

import interfaces.CouponDAO;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import beans.Coupon;
import dao.CouponDBDAO;
import exception.DoesNotExistException;

public class DailyCouponExpirationTask implements Runnable{
	
	private boolean quit = false;
	private CouponDAO couponDAO;
	
	public DailyCouponExpirationTask() throws SQLException, IOException {
		super();
		couponDAO = new CouponDBDAO();
	}

	@Override
	public void run(){
		Date d;
		Date current;
		while ( !quit ){
			current = new Date(System.currentTimeMillis());
			
			try {
				Collection<Coupon> coupons = couponDAO.getAllCoupon();
				
				if ( coupons != null ){
					for ( Coupon c : coupons){
						d = c.getEndDate();
						if (d.before(current)){
							try{ 
								couponDAO.removeCoupon(c.getId()); 
							} 
							catch (DoesNotExistException e) { }
						}
					}
				}
				
				Thread.sleep(1000*60*60*24);
				
			} catch (SQLException e) {
				Logger.getLogger(Common.LOGGER).log(Level.SEVERE, "Daily expiration task: failed with following error: " + e.getMessage());
				try {
					Logger.getLogger(Common.LOGGER).log(Level.SEVERE, "Daily expiration task: Waiting for retry in 15 minutes.");
					Thread.sleep(1000*60*15); 
				} catch (InterruptedException e1) {
					Logger.getLogger(Common.LOGGER).log(Level.INFO, "Daily expiration task: Retry was intterupted.");
				}
			} catch (InterruptedException e) {
				Logger.getLogger(Common.LOGGER).log(Level.INFO, "Daily expiration task: 24 hours cycle was intterupted.");
			}
		}	
		Logger.getLogger(Common.LOGGER).log(Level.INFO, "Daily expiration task: task terminated.");
	}
	
	public void stopTask(){
		quit = true;
		Logger.getLogger(Common.LOGGER).log(Level.INFO, "Daily expiration task: Task was marked for termination.");
	}
	
	
	
}
