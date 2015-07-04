package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import Beans.Company;
import Dao.CompanyDBDAO;
import Interfaces.CompanyDAO;

public class Main {

	public static void main(String[] args) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			String s = "jdbc:sqlserver://127.0.0.1:53369;databaseName=CouponProject";
			
			Connection con = DriverManager.getConnection(s,"Java","nir");
			
			Statement stat = con.createStatement();
			String sql = "CREATE TABLE Test(" + 
			" hello char(50) not null)  ";
			
			stat.execute(sql);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
