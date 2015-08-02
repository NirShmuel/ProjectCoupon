package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.Coupon;
import beans.CouponType;
import connectionPool.ConnectionPoolSingleton;
import exception.DoesNotExistException;
import exception.DuplicateNameException;
import exception.NoUpdateException;
import exception.PropertiesFileMissingException;
import interfaces.CustomerHistoryDAO;

public class CustomerHistoryDBDAO implements CustomerHistoryDAO {
	
	private ConnectionPoolSingleton connpool;
	
	public CustomerHistoryDBDAO() throws SQLException, IOException, PropertiesFileMissingException{
		connpool = ConnectionPoolSingleton.getInstance();
	}

	@Override
	public void upDateHistory(long customerId, long couponId) throws SQLException, DoesNotExistException {

		Connection con = null;
		String sql = null;
		Coupon coupon = new Coupon();

		try{
			con = connpool.getConnection();
			Statement stat = con.createStatement();
			coupon = getCoupon(couponId);

			sql = "INSERT INTO Customer_history ( CUST_ID, COUPON_ID, COUPON_TITLE, END_DATE, PURCHASE_DATE, PRICE) VALUES ('" +
					customerId							+ "','"	+
					couponId							+ "','"	+
					coupon.getTitle()					+ "','" +
					coupon.getEndDate()					+ "','"	+
					new Date(System.currentTimeMillis())+ "','" +
					coupon.getPrice()					+ "')";

			stat.execute(sql);

		

		}  catch (SQLException e) {
			throw e;
		}finally {
			if ( con != null ){
				connpool.releaseConnection(con);
			}
		}



	}

	@Override
	public Coupon getCoupon(long couponId) throws DoesNotExistException, SQLException {
		Connection con = null;
		String sql;
		Coupon coupon = new Coupon();
		try{
			con = connpool.getConnection();
			Statement stat = con.createStatement();
			
			sql="SELECT * FROM Coupon WHERE ID = '" + couponId + "'";
			
			stat.execute(sql);
			
			ResultSet set = stat.getResultSet();
			
			if ( set.next() ){
				coupon = new Coupon();
				
				coupon.setId		(	set.getLong		("ID") 			);
				coupon.setTitle		(	set.getString	("TITLE") 		);
				coupon.setStartDate	(	set.getDate		("START_DATE")	);
				coupon.setEndDate	(	set.getDate		("END_DATE") 	);
				coupon.setAmount	(	set.getInt		("AMOUNT")		);
				coupon.setType(CouponType.valueOf(set.getString("TYPE")));
				coupon.setMessage	(	set.getString	("MASSAGE")		);
				coupon.setPrice		(	set.getDouble	("PRICE")		);
				coupon.setImage		(	set.getString	("IMAGE")		);
				
			}else {
				throw new DoesNotExistException("Coupon with this ID does not exist in the database.");
			}
			
			
			
		}catch(SQLException e){
			throw e;
		}finally {
			if ( con != null ){
				connpool.releaseConnection(con);
			}
		}
		
		
		return coupon;
	}



}
