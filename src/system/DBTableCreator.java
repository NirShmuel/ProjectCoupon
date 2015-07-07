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
				+ "PWD VARCHAR(20) NOT NULL,"
				+ "EMAIL VARCHAR(50) NOT NULL"
				+ ") ENGINE=INNODB";
		
		stat.execute(sql);
		
		
		sql = "CREATE TABLE IF NOT EXISTS Customer (" + 
				"ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "CUST_NAME VARCHAR(50) NOT NULL UNIQUE,"
				+ "PWD VARCHAR(20) NOT NULL"
				+ ")ENGINE=INNODB";

		stat.execute(sql);


		

		
		
		sql = "CREATE TABLE IF NOT EXISTS Coupon ("
				+ "ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "TITLE VARCHAR(50) NOT NULL UNIQUE"
				+ "START_DATE DATE NOT NULL,"
				+ "END_DATE DATE NOT NULL,"
				+ "AMOUNT INT(10) NOT NULL,"
				+"TYPE ENUM() NOT NULL,"
				+"MESSAGE VARCHAR(50) NOT NULL,"
				+"PRICE DOUBLE NOT NULL,"
				+"IMAGE VARCHAR(50) NOT NULL,"
				+ ")ENGINE=INNODB";

		stat.execute(sql);

		sql = "CREATE TABLE IF NOT EXISTS Company_Coupon ("
				+ "COMP_ID BIGINT ,"
				+ "COUP_ID BIGINT PRIMARY KEY,"
				//+ "FOREIGN KEY (COMP_ID) REFERENCES Company(ID),"
				+ "FOREIGN KEY (COUP_ID) REFERENCES Coupon(ID),"
				+ ")ENGINE=INNODB";

		stat.execute(sql);
		
		sql = "CREATE TABLE IF NOT EXISTS Customer_Coupon ("
				+ "CUST_ID BIGINT NOT NULL,"
				+ "COUPON_ID BIGINT NOT NULL,"
				+ "FOREIGN KEY (CUST_ID) REFFENCES Customer(ID),"
				+("FOREIGN KEY (COUPON_ID) REFFENCES Coupon(ID),")
				+ ")ENGINE=INNODB";

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
