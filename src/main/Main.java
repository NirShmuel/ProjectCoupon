package main;

import interfaces.CompanyDAO;
import interfaces.CustomerDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

import system.DBTableCreator;
import beans.Company;
import beans.Coupon;
import beans.CouponType;
import beans.Customer;
import dao.CompanyDBDAO;
import dao.CouponDBDAO;
import dao.CustomerDBDAO;
import exception.DoesNotExistException;
import exception.DuplicateNameException;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, DuplicateNameException, DoesNotExistException {
		
		
		CompanyDAO companyDao = new CompanyDBDAO();
		CustomerDAO custDao = new CustomerDBDAO();
		CouponDBDAO coup = new CouponDBDAO();
		CouponType type = CouponType.FOOD;
		Coupon co = new Coupon();
		
	//	DBTableCreator.createTablesMySql();
		coup.removeAllCompanyCoupons(3);
		
		//companyDao.createCompany( new Company(0, "jyjygjy", "b", "c", null) );
//		cust.createCustomer(new Customer(0,"","b",null));
//		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.YEAR, 2015);
//		cal.set(Calendar.MONTH, 10);
//		cal.set(Calendar.DATE, 1);
//				
//		coup.createCoupon(new Coupon(0, "k", new Date(System.currentTimeMillis()), new Date( cal.getTimeInMillis() ), 1, type, "", 41, ""));
		
//		
//		Collection<Coupon> coupons= companyDao.getCompanyCoupons(1);
//		
//		for ( Coupon c : coupons ){
//			System.out.println(c);
//		}
//		
//       
//		coupons= custDao.getCustomerCoupons(3);
//		
//		for ( Coupon c : coupons ){
//			System.out.println(c);
//		}
		
//		String t  = CouponType.CAMPING.name();// enum to string
//				
//		CouponType type = CouponType.valueOf( "FOOD" ); // string to enum
//		
//		System.out.println( type );
		

		//DBTableCreator.createTablesMySql();
		
		
		
	}

}
