package Dao;

import java.sql.*;
import java.util.Collection;

import Beans.Company;
import Beans.Coupon;
import Interfaces.CompanyDAO;

public class CompanyDBDAO implements CompanyDAO {

	@Override
	public void createCompany(Company comp) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			String s = "jdbc:sqlserver://127.0.0.1:53369;databaseName=CouponProject";
			
			Connection con = DriverManager.getConnection(s);
			
			Statement stat = con.createStatement();
			
			String sql = "INSERT ";
			
			
			
			stat.execute(sql);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeCompany(Company comp) {
		
	}

	@Override
	public void updateCompany(Company comp) {
		
	}

	@Override
	public Company getCompany(long id) {
		return null;
	}

	@Override
	public Collection<Company> getAllCompanies() {
		return null;
	}

	@Override
	public Collection<Coupon> getCoupons() {
		return null;
	}

	@Override
	public boolean login(String name, String password) {
		return false;
	}

}
