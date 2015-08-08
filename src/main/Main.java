package main;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import system.Common;
import system.CouponSystem;
import beans.Coupon;
import beans.CouponType;
import exception.DoesNotExistException;
import exception.DuplicateNameException;
import exception.PropertiesFileMissingException;
import exception.WrongCredentialsException;
import facade.CompanyFacade;

public class Main {

	public static void main(String[] args) {
		Tester t;
		
		try {
			t = new Tester();
			t.run();
		} catch (SecurityException | IOException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void runTest() {
		
		CouponSystem system = null;
		try {
			system = CouponSystem.getInstance();
			
			try {
				CompanyFacade comp = system.companylogin(3, "b");
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(System.currentTimeMillis());
				cal.add(Calendar.DAY_OF_YEAR, 50);
				
				Coupon c = new Coupon(0, "MINE COUT TEST", new Date(System.currentTimeMillis()), new Date(cal.getTimeInMillis()), 200, CouponType.FOOD, "This is very good deal!", 50, "");
				
				
				
				try { comp.createCoupon(c);}catch (DuplicateNameException e){System.out.println(e.getMessage());}
				
				System.out.println("All coupons for company:" + comp.getDetails().getId());
				Collection<Coupon> allCoupons = comp.getAllCoupons();
				for (Coupon coup : allCoupons){
					System.out.println(coup);
				}
				
				System.out.println(comp.getDetails());
				
				try{ comp.removeCoupon(c.getId()); }catch(SQLException | DoesNotExistException e ){System.out.println(e.getMessage());}
				
				System.out.println("All coupons for company after deletion:" + comp.getDetails().getId());
				allCoupons = comp.getAllCoupons();
				for (Coupon coup : allCoupons){
					System.out.println(coup);
				}
				
				
				System.out.println("All coupons less than 30");
				Collection<Coupon> coupons = comp.getCouponByPrice(30);
				for (Coupon coup : coupons){
					System.out.println(coup);
				}
				
				
			} catch ( WrongCredentialsException e){
				System.out.println(e.getMessage());
			}
			
			
			
			
		} catch (SecurityException|IOException|SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (system != null ){
					system.shutdown();
				}
			} catch (SQLException | IOException
					| PropertiesFileMissingException | InterruptedException e) {
				
				Logger.getLogger(Common.LOGGER).log(Level.SEVERE,"Failed to shut down the system gracefully");
			}
		}
		
	
	}

}
