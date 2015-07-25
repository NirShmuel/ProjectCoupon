package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connectionPool.ConnectionPoolSingleton;
import exception.DuplicateNameException;
import interfaces.CustomerHistoryDAO;

public class CustomerHistoryDBDAO implements CustomerHistoryDAO {

	@Override
	public void upDateHistory(long customerId, String title, Date date) throws SQLException {
		
		Connection con = null;
		
		try {
			con = ConnectionPoolSingleton.getInstance().getConnection();
		
			Statement stat = con.createStatement();
			
			
			
				
			String sql = "INSERT INTO Customer_History ( CUST_ID, COUPON_TITLE, PURCHASE_DATE ) VALUES ('" +
					customerId			+ "','"	+
					title				+ "','"	+
					date				+ "')";
			
			stat.execute(sql);

		} catch (SQLException e) {
			throw e;
		} finally {
			if ( con != null ){
				ConnectionPoolSingleton.getInstance().releaseConnection(con);
			}
		}
		
	}

}
