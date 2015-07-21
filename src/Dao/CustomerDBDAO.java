package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import connectionPool.ConnectionPoolSingleton;
import exception.DoesNotExistException;
import exception.DuplicateNameException;
import exception.NoUpdateException;
import beans.Company;
import beans.Coupon;
import beans.CouponType;
import beans.Customer;
import interfaces.CustomerDAO;

public class CustomerDBDAO implements CustomerDAO {
	/**
	 * Create a new customer entry in the Database by passing a customer object.
	 * @param comp - A {@link Customer} object containing the relevant information for creating a new entry.
	 * @throws SQLException 
	 * @throws  DuplicateNameException - if there is already a customer by that name
	 * 
	 */
	@Override
	public void createCustomer(Customer cust) throws DuplicateNameException, SQLException {
		Connection con = null;
		
		try{
			con = ConnectionPoolSingleton.getInstance().getConnection();
			
			Statement stat = con.createStatement();
			
			String sql = "INSERT INTO Customer ( CUST_NAME, PWD) VALUES ('" +
					cust.getCustName()		+ "','"	+
					cust.getPassword()		+ "')";
			
			stat.execute(sql, Statement.RETURN_GENERATED_KEYS );
			
			ResultSet set = stat.getGeneratedKeys(); 
			set.next();
			
			cust.setId(set.getLong(1));
			
			
		}catch (SQLException e) {
			if ( e.getMessage().contains("Duplicate")){
				throw new DuplicateNameException("Duplicate customer name.");
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
		 * Remove a customer entry in the Database by passing a customer id.
		 * @param id - The customer id number that wish to be remove.
		 * @throws SQLException 
		 * @throws DoesNotExistException - if there is not a customer with that id.
		 * 
		 */
	@Override
	public void removeCustomer(long id) throws SQLException, DoesNotExistException {
			//TODO: try adding cascade  
		Connection con = null;
		try {
			con = ConnectionPoolSingleton.getInstance().getConnection();
			Statement stat = con.createStatement();
					
			String sql = "DELETE FROM Customer WHERE ID = '" + id + "'";

			stat.execute(sql);
			
			if ( stat.getUpdateCount() == 0 ) {
				throw new DoesNotExistException("customer with this ID does not exist in the database.");
			}


		} catch (SQLException e) {
			throw e;
		}finally {
			if ( con != null ){
				ConnectionPoolSingleton.getInstance().releaseConnection(con);
			}
		}
		
	}
	/**
	 * Update a customer entry in the DB by passing an updated customer object <br>
	 * (The objects id attribute must contain the id of the entry to be changed) 
	 * @throws SQLException , NoUpdateException
	 * @throws NoUpdateException - if an update failed
	 */
	@Override
	public void upDateCustomer(Customer cust) throws SQLException, NoUpdateException {
		
		Connection con = null;
		String sql = null;
	try{
		con = ConnectionPoolSingleton.getInstance().getConnection();
		Statement stat = con.createStatement();
		
		 sql = "UPDATE Customer SET"+
				 "`CUST_NAME` = '" 	+ 	cust.getCustName() 	+ 
				 "', `PWD` = '" 	+  	cust.getPassword()	+
				 "' WHERE ID = '"	+ 	cust.getId() 		+ "'";
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
	 * Returns a "Customer" object for a given id.
	 * @throws SQLException , DoesNotExistException
	 * @throws DoesNotExistException - If a company with this Id did not exist
	 */
	@Override
	public Customer getCustomer(long id) throws SQLException, DoesNotExistException {
		Connection con = null;
		Customer cust = new Customer();
	
	try {
		con = ConnectionPoolSingleton.getInstance().getConnection();
		Statement stat = con.createStatement();
				
		String sql = "SELECT * FROM Customer WHERE ID ='" + id + "'";
		stat.execute(sql);
		ResultSet set = stat.getResultSet();
		
		if  ( set.next() ){
			cust.setCustName( set.getString("CUST_NAME") );
			cust.setPassword( set.getString("PWD") );
			cust.setId( id );
			
		} else {
			throw new DoesNotExistException("Customer does not exist!");
		}
		


	}  catch (SQLException e) {
			throw e;		
	}finally {
		if ( con != null ){
			ConnectionPoolSingleton.getInstance().releaseConnection(con);
		}
	}
		return cust;
	}
	/**
	 * 
	 * @return returns a collection of customers if they are present, otherwise returns null.
	 * @throws SQLException
	 * 
	 */
	@Override
	public Collection<Customer> getAllCustomer() throws SQLException {
		Customer cust = null;
		Connection con = null;
		Collection<Customer> customers = new ArrayList<Customer>();
		String sql;
		
		try{
			con = ConnectionPoolSingleton.getInstance().getConnection();
			
			Statement stat = con.createStatement();
			
			sql = "SELECT * FROM Customer";
			stat.execute(sql);
			ResultSet set = stat.getResultSet();
						
			while (set.next()){
				cust = new Customer();
				cust.setCustName( set.getString("CUST_NAME") );
				cust.setPassword( set.getString("PWD") );
				cust.setId( set.getLong("ID"));
				customers.add(cust);

			}
			if ( customers.size() == 0){
				customers = null;
			}
			
			
			
		}catch (SQLException e) {
			throw e;
		}finally {
			if ( con != null ){
				ConnectionPoolSingleton.getInstance().releaseConnection(con);
			}
		}
		
		return customers;
		
	}
	/**
	 * Returns all the coupons for a given customer id.
	 * @return returns a collection of coupons if they are present, otherwise returns null.
	 * @throws SQLException
	 * 
	 */
	@Override
	public Collection<Coupon> getCustomerCoupons(long id) throws SQLException {
		Coupon coupon = new Coupon();
		Connection con = null;
		String sql;
		Collection<Coupon> coupons = new ArrayList<Coupon>();

		try{
			con = ConnectionPoolSingleton.getInstance().getConnection();
			Statement stat = con.createStatement();

			sql = "SELECT * FROM Coupon WHERE ID IN ( SELECT COUPON_ID FROM Customer_Coupon WHERE CUST_ID = " + id + ") ";
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
	public boolean login(String custName, String password) {
		return false;
	}
	@Override
	public void removeCustomerCoupons() {
		// TODO Auto-generated method stub
		
	}

}
