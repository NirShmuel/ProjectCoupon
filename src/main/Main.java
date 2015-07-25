package main;

import interfaces.CompanyDAO;
import interfaces.CouponClientFacadeDAO;
import interfaces.CustomerDAO;
import interfaces.CustomerHistoryDAO;

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
import dao.CustomerHistoryDBDAO;
import exception.DoesNotExistException;
import exception.DuplicateNameException;
import exception.WrongCredentialsException;
import facade.CompanyFacade;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, DuplicateNameException, DoesNotExistException, WrongCredentialsException {
		
		CustomerHistoryDAO history = new CustomerHistoryDBDAO();
		CompanyDAO companyDao = new CompanyDBDAO();
		CustomerDAO custDao = new CustomerDBDAO();
		CouponDBDAO coup = new CouponDBDAO();
		CouponType type = CouponType.FOOD;
		Coupon co = new Coupon();
		
		history.upDateHistory(1, "FOOD", new Date(System.currentTimeMillis()));
	//	DBTableCreator.createTablesMySql();
	//	coup.removeAllCompanyCoupons(3);
//		facadeCompany.login(1, "b");
	//	companyDao.insertCompanyToCoupon(10, 29);
	//	companyDao.createCompany( new Company(0, "11", "b", "c", null) );
//		cust.createCustomer(new Customer(0,"","b",null));
//		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.YEAR, 2015);
//		cal.set(Calendar.MONTH, 10);
//		cal.set(Calendar.DATE, 1);
//				
//		coup.createCoupon(new Coupon(0, "k", new Date(System.currentTimeMillis()), new Date( cal.getTimeInMillis() ), 1, type, "", 41, ""));
		
//		
//		Collection<Coupon> coupons = coup.getCustomerCouponByType(3, type);
//		
//		for ( Coupon c : coupons ){
//			System.out.println(c);
//		}
		
////		
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
