package system;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import connectionPool.ConnectionPoolSingleton;

public class DBTableCreator {

	public static void createTablesMySql() throws SQLException{
		Connection con = null;
		try {
			con = ConnectionPoolSingleton.getInstance().getConnection();

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
					+ "TITLE VARCHAR(50) NOT NULL UNIQUE,"
					+ "START_DATE DATE NOT NULL,"
					+ "END_DATE DATE NOT NULL,"
					+ "AMOUNT INT(10) NOT NULL,"
					+ "TYPE VARCHAR(50) NOT NULL,"
					+ "MESSAGE VARCHAR(50) NOT NULL,"
					+ "PRICE DOUBLE NOT NULL,"
					+ "IMAGE VARCHAR(50) NOT NULL"
					+ ")ENGINE=INNODB";

			stat.execute(sql);

			sql = "CREATE TABLE IF NOT EXISTS Company_Coupon ("
					+ "COMP_ID BIGINT NOT NULL,"
					+ "COUP_ID BIGINT NOT NULL PRIMARY KEY,"
					+ "FOREIGN KEY (COUP_ID) REFERENCES Coupon(ID) ON DELETE CASCADE"
					+ ")ENGINE=INNODB";

			stat.execute(sql);

			sql = "CREATE TABLE IF NOT EXISTS Customer_Coupon (\n"
					+ "CUST_ID BIGINT NOT NULL,\n"
					+ "COUPON_ID BIGINT NOT NULL,\n"
					+ "PRIMARY KEY (CUST_ID,COUPON_ID),\n"
					+ "FOREIGN KEY (CUST_ID) REFERENCES Customer(ID) ON DELETE CASCADE,\n"
					+ "FOREIGN KEY (COUPON_ID) REFERENCES Coupon(ID) ON DELETE CASCADE\n"
					+ ")ENGINE=INNODB";

			stat.execute(sql);


		}catch (SQLException e) {
			throw e;
		}finally{
			if ( con != null)
				ConnectionPoolSingleton.getInstance().releaseConnection(con);;
		}
	}

}
