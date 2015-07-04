package system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import exception.SqlException;

public class DBTableCreator {
	
	public static void createTablesMySql(){
		try {
		Class.forName("com.mysql.jdbc.Driver");

		String s = "jdbc:mysql://109.67.36.73:3306/projectcoupon";
		
		Connection con = DriverManager.getConnection(s,"Java","nir");
		
		Statement stat = con.createStatement();
			
		String sql = "CREATE TABLE IF NOT EXISTS Company (" + 
				"ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "COMP_NAME VARCHAR(50) NOT NULL UNIQUE,"
				+ "PSW VARCHAR(20) NOT NULL,"
				+ "EMAIL VARCHAR(50) NOT NULL)";
		
		
		
		
		stat.execute(sql);
		
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
