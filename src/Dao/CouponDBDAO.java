package dao;

import interfaces.CouponDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.crypto.Data;

import beans.Coupon;
import beans.CouponType;
import connectionPool.ConnectionPoolSingleton;
import exception.DoesNotExistException;
import exception.DuplicateNameException;
import exception.NoUpdateException;

public class CouponDBDAO implements CouponDAO {

	/**
	 * Create a new coupon entry in the Database by passing a coupon object.
	 * @param comp - A {@link Coupon} object containing the relevant information for creating a new entry.
	 * @throws SQLException 
	 * @throws  DuplicateNameException - if there is already a coupon by that name
	 * 
	 */
	@Override
	public void createCoupon(Coupon coup) throws DuplicateNameException, SQLException {
		Connection con = null;
		
		try {
			con = ConnectionPoolSingleton.getInstance().getConnection();
		
			Statement stat = con.createStatement();
			
			
			
				
			String sql = "INSERT INTO Coupon ( TITLE, START_DATE, END_DATE, AMOUNT, TYPE, MESSAGE, PRICE, IMAGE) VALUES ('" +
					coup.getTitle()			+ "','"	+
					coup.getStartDate()		+ "','"	+
					coup.getEndDate()		+ "','" +
					coup.getAmount()		+ "','"	+
					coup.getType()			+ "','" +
					coup.getMessage()		+ "','" +
					coup.getPrice()			+ "','" +
					coup.getImage()			+ "')";
			
			// When running execute() with a second parameter set to RETURN_GENERATED_KEYS you can get the PK valuse generated by the sql-server
			stat.execute(sql, Statement.RETURN_GENERATED_KEYS );
			
			ResultSet set = stat.getGeneratedKeys();
			set.next();
			
			// Setting the Id of the coupon to the one received from the server.
			coup.setId( set.getLong(1) );
			
			
			

		} catch (SQLException e) {
			if ( e.getMessage().contains("Duplicate")){
				throw new DuplicateNameException("Duplicate coupon title.");
			} else {
				throw e;
			}
		} finally {
			if ( con != null ){
				ConnectionPoolSingleton.getInstance().releaseConnection(con);
			}
		}
		
		
	}
	 /**
	 * Remove a coupon entry in the Database by passing a coupon id.
	 * @param id - The coupon id number that wish to be remove.
	 * @throws SQLException 
	 * @throws DoesNotExistException - if there is not a coupon with that id.
	 * 
	 */
	@Override
	public void removeCoupon(long id) throws SQLException, DoesNotExistException {
		Connection con = null;
		try {
			con = ConnectionPoolSingleton.getInstance().getConnection();
			
			Statement stat = con.createStatement();
					
			String sql = "DELETE FROM Coupon WHERE ID = '" + id + "'";

			stat.execute(sql);
			
			if ( stat.getUpdateCount() == 0 ) {
				throw new DoesNotExistException("Coupon with this ID does not exist in the database.");
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if ( con != null ){
				ConnectionPoolSingleton.getInstance().releaseConnection(con);
			}
		}
		
		
	}
	/**
	 * Update a coupon entry in the DB by passing an updated coupon object <br>
	 * (The objects id attribute must contain the id of the entry to be changed) 
	 * @throws SQLException , NoUpdateException
	 * @throws NoUpdateException - if an update failed
	 */
	@Override
	public void upDateCoupon(Coupon coup) throws SQLException, NoUpdateException {
		
		Connection con = null;
		String sql = null;
	try{
		con = ConnectionPoolSingleton.getInstance().getConnection();
		Statement stat = con.createStatement();
		
		 sql = "UPDATE Coupon SET `END_DATE` = '" + coup.getEndDate()
				+ "', `PRICE` = '" +  coup.getPrice()
				+ "' WHERE ID = '" + coup.getId() + "'";
		 
		 stat.execute(sql);
		
		 if(stat.getUpdateCount() == 0 ){
			throw new NoUpdateException();
		 }
		
	}  catch (SQLException e) {
		throw e;
	}finally {
		if ( con != null ){
			ConnectionPoolSingleton.getInstance().releaseConnection(con);
		}
	}
		
		
		
	}
	/**
	 * Returns a "Coupon" object for a given id.
	 * @throws SQLException , DoesNotExistException
	 * @throws DoesNotExistException - If a coupon with this Id did not exist
	 */
	@Override
	public Coupon getCoupon(long id) throws SQLException, DoesNotExistException {
		Connection con = null;
		String sql;
		Coupon coupon = new Coupon();
		try{
			con = ConnectionPoolSingleton.getInstance().getConnection();
			Statement stat = con.createStatement();
			
			sql="SELECT * FROM Coupon WHERE ID = '" + id + "'";
			
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
				ConnectionPoolSingleton.getInstance().releaseConnection(con);
			}
		}
		
		
		return coupon;
	}
	/**
	 * 
	 * @return returns a collection of coupons if they are present, otherwise returns null.
	 * @throws SQLException
	 * 
	 */
	@Override
	public Collection<Coupon> getAllCoupon() throws SQLException {
		Coupon coupon = null;
		Connection con = null;
		String sql;
		Collection<Coupon> coupons = new ArrayList<Coupon>();
		
		try{
			con = ConnectionPoolSingleton.getInstance().getConnection();
			
			Statement stat = con.createStatement();
			
			sql = "SELECT * FROM Coupon";
			
			stat.execute(sql);
			ResultSet set = stat.getResultSet();
			
			while ( set.next() ){
				coupon = new Coupon();
				
				coupon.setId		(	set.getLong		("ID") 			);
				coupon.setTitle		(	set.getString	("TITLE") 		);
				coupon.setStartDate	(	set.getDate		("START_DATE")	);
				coupon.setEndDate	(	set.getDate		("END_DATE") 	);
				coupon.setAmount	(	set.getInt		("AMOUNT")		);
				coupon.setType(CouponType.valueOf(set.getString("TYPE")));
				coupon.setMessage	(	set.getString	("MESSAGE")		);
				coupon.setPrice		(	set.getDouble	("PRICE")		);
				coupon.setImage		(	set.getString	("IMAGE")		);
				
				coupons.add(coupon);

			}
			if ( coupons.size() == 0){
				coupons = null;
			}
			
		}catch (SQLException e) {
			throw e;
		}finally {
			if ( con != null ){
				ConnectionPoolSingleton.getInstance().releaseConnection(con);
			}
		}
		return coupons;
	}
	
	
	
	/**
	 * 
	 * @return returns a collection of coupons for a given type, otherwise returns null.
	 * @throws SQLException
	 * 
	 */
	@Override
	public Collection<Coupon> getCompanyCouponByType(long companyId , CouponType type) throws SQLException {
		Coupon coupon = null;
		Connection con = null;
		String sql;
		Collection<Coupon> coupons = new ArrayList<Coupon>();
		try{
			con = ConnectionPoolSingleton.getInstance().getConnection();
			
			Statement stat = con.createStatement();
			
			//sql="SELECT * FROM Coupon WHERE TYPE = '" + type +"'";
			sql = "SELECT * FROM Coupon "
					+ "WHERE ID IN ( SELECT COUP_ID FROM Company_Coupon "
					+ "WHERE COMP_ID ='" + 		companyId 
					+ "') AND  TYPE = '" + 		type  
					+ "'";
			
			stat.execute(sql);
			ResultSet set = stat.getResultSet();
			
			while ( set.next() ){
				coupon = new Coupon();
				
				coupon.setId		(	set.getLong		("ID") 			);
				coupon.setTitle		(	set.getString	("TITLE") 		);
				coupon.setStartDate	(	set.getDate		("START_DATE")	);
				coupon.setEndDate	(	set.getDate		("END_DATE") 	);
				coupon.setAmount	(	set.getInt		("AMOUNT")		);
				coupon.setType(CouponType.valueOf(set.getString("TYPE")));
				coupon.setMessage	(	set.getString	("MESSAGE")		);
				coupon.setPrice		(	set.getDouble	("PRICE")		);
				coupon.setImage		(	set.getString	("IMAGE")		);
				
				coupons.add(coupon);

			}
			if ( coupons.size() == 0){
				coupons = null;
			}
			
		}catch (SQLException e) {
			throw e;
		}finally {
			if ( con != null ){
				ConnectionPoolSingleton.getInstance().releaseConnection(con);
			}
		}
		return coupons;
	}

	@Override
	public void removeAllCompanyCoupons(long companyId) throws SQLException {
		Connection con = null;
		String sql;
		
		try{
			con = ConnectionPoolSingleton.getInstance().getConnection();
			
			Statement stat = con.createStatement();
			
			sql = "DELETE FROM Coupon WHERE ID IN ( SELECT COUP_ID FROM Company_Coupon WHERE COMP_ID = " + companyId + ") ";
			
			stat.execute(sql);
			
			
		}catch (SQLException e) {
			throw e;
		}finally {
			if ( con != null ){
				ConnectionPoolSingleton.getInstance().releaseConnection(con);
			}
		}
		
	}
	@Override
	public Collection<Coupon> getCouponByPrice( long companyId , long price) throws SQLException {
		Coupon coupon = null;
		Connection con = null;
		String sql;
		Collection<Coupon> coupons = new ArrayList<Coupon>();
		try{
			con = ConnectionPoolSingleton.getInstance().getConnection();
			
			Statement stat = con.createStatement();
			
			sql = "SELECT * FROM Coupon "
					+ "WHERE ID IN ( SELECT COUP_ID FROM Company_Coupon "
					+ "WHERE COMP_ID ='" + 		companyId 
					+ "') AND  PRICE <= '" + 		price  
					+ "'";
			
			stat.execute(sql);
			ResultSet set = stat.getResultSet();
			
			while ( set.next() ){
				coupon = new Coupon();
				
				coupon.setId		(	set.getLong		("ID") 			);
				coupon.setTitle		(	set.getString	("TITLE") 		);
				coupon.setStartDate	(	set.getDate		("START_DATE")	);
				coupon.setEndDate	(	set.getDate		("END_DATE") 	);
				coupon.setAmount	(	set.getInt		("AMOUNT")		);
				coupon.setType(CouponType.valueOf(set.getString("TYPE")));
				coupon.setMessage	(	set.getString	("MESSAGE")		);
				coupon.setPrice		(	set.getDouble	("PRICE")		);
				coupon.setImage		(	set.getString	("IMAGE")		);
				
				coupons.add(coupon);

			}
			if ( coupons.size() == 0){
				coupons = null;
			}
			
		}catch (SQLException e) {
			throw e;
		}finally {
			if ( con != null ){
				ConnectionPoolSingleton.getInstance().releaseConnection(con);
			}
		}
		return coupons;
	}
	@Override
	public Collection<Coupon> getCouponByDate(long companyId, Data date) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public Collection<Coupon> getCustomerCouponByType(long customerId, CouponType type) throws SQLException {
		Coupon coupon = null;
		Connection con = null;
		String sql;
		Collection<Coupon> coupons = new ArrayList<Coupon>();
		try{
			con = ConnectionPoolSingleton.getInstance().getConnection();
			
			Statement stat = con.createStatement();
			
			sql = "SELECT * FROM Coupon "
					+ "WHERE ID IN ( SELECT COUPON_ID FROM Customer_Coupon "
					+ "WHERE CUST_ID ='" + 		customerId 
					+ "') AND  TYPE = '" + 		type  
					+ "'";
			
			stat.execute(sql);
			ResultSet set = stat.getResultSet();
			
			while ( set.next() ){
				coupon = new Coupon();
				
				coupon.setId		(	set.getLong		("ID") 			);
				coupon.setTitle		(	set.getString	("TITLE") 		);
				coupon.setStartDate	(	set.getDate		("START_DATE")	);
				coupon.setEndDate	(	set.getDate		("END_DATE") 	);
				coupon.setAmount	(	set.getInt		("AMOUNT")		);
				coupon.setType(CouponType.valueOf(set.getString("TYPE")));
				coupon.setMessage	(	set.getString	("MESSAGE")		);
				coupon.setPrice		(	set.getDouble	("PRICE")		);
				coupon.setImage		(	set.getString	("IMAGE")		);
				
				coupons.add(coupon);

			}
			if ( coupons.size() == 0){
				coupons = null;
			}
			
		}catch (SQLException e) {
			throw e;
		}finally {
			if ( con != null ){
				ConnectionPoolSingleton.getInstance().releaseConnection(con);
			}
		}
		return coupons;
	}
	@Override
	public void upDateAmount(long couponId) throws SQLException {
		
		Connection con = null;
		String sql;
		long amount;
		
		try{
			con = ConnectionPoolSingleton.getInstance().getConnection();
			Statement stat = con.createStatement();
			
			sql = "SELECT AMOUNT FROM Coupon"
			
			if (){
			 sql = "UPDATE Coupon SET AMOUNT =  -1" 
					 	+   "'";
			}
		
		}catch (SQLException e) {
			throw e;
		}finally {
			if ( con != null ){
				ConnectionPoolSingleton.getInstance().releaseConnection(con);
			}
		}
		
	}
	
	
	

}
