package main;

import interfaces.CompanyDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import system.DBTableCreator;
import beans.Company;
import dao.CompanyDBDAO;
import exception.DuplicateCompanyNameException;
import exception.ProjectCouponException;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Company a = new Company();
		a.setCompName("tre").setPassword("123").setEmail("a@b.c");
		
		CompanyDAO cDao = new CompanyDBDAO();
		
		try {
			cDao.createCompany(a);
		} catch (DuplicateCompanyNameException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		System.out.println(a);
////		
////		a.setCompName("b");
////		
		cDao.removeCompany( 43563456 );
//		
		
		/*
		Class.forName("com.mysql.jdbc.Driver");

		String s = "jdbc:mysql://109.67.36.73:3306/projectcoupon";
		
		Connection con = DriverManager.getConnection(s,"Java","nir");
		
		Statement stat = con.createStatement();
			*/
		
		DBTableCreator.createTablesMySql();
		
		
		
	}

}
