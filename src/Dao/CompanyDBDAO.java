package Dao;

import java.sql.*;

public class CompanyDBDAO {

	public void as() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			String s = "jdbc:sqlserver://127.0.0.1:53369;databaseName=CouponProject";
			
			Connection con = DriverManager.getConnection(s);
			
			Statement stat = con.createStatement();
			String sql = "CREATE TABLE Test(" + 
			" hello chaeacter(50) not null)  ";
			
			stat.execute(sql);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
