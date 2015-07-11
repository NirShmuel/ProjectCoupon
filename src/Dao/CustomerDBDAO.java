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
import beans.Coupon;
import beans.CouponType;
import beans.Customer;
import interfaces.CustomerDAO;

public class CustomerDBDAO implements CustomerDAO {

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

	@Override
	public void removeCustomer(long id) throws SQLException {
		
		Connection con = null;
		try {
			con = ConnectionPoolSingleton.getInstance().getConnection();
			Statement stat = con.createStatement();
					
			String sql = "DELETE FROM Customer WHERE ID = '" + id + "'";

			stat.execute(sql);
			
			if ( stat.getUpdateCount() == 0 ) {
				//throw new 
			}


		} catch (SQLException e) {
			throw e;
		}finally {
			if ( con != null ){
				ConnectionPoolSingleton.getInstance().releaseConnection(con);
			}
		}
		
	}

	@Override
	public void upDateCustomer(Customer cust) throws SQLException, NoUpdateException {
		
		Connection con = null;
		String sql = null;
	try{
		con = ConnectionPoolSingleton.getInstance().getConnection();
		Statement stat = con.createStatement();
		
		 sql = "UPDATE Customer SET `CUST_NAME` = '" + cust.getCustName() + "', `PWD` = '" +  cust.getPassword()
				+ "' WHERE ID = '" + cust.getId() + "'";
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
			
			
			
		}catch (SQLException e) {
			throw e;
		}finally {
			if ( con != null ){
				ConnectionPoolSingleton.getInstance().releaseConnection(con);
			}
		}
		
		return customers;
		
	}

	@Override
	public Collection<Coupon> getCoupons(long id) throws SQLException {
		Coupon cou = new Coupon();
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
				cou = new Coupon();
				cou.setId(set.getLong("ID"));
				cou.setTitle( set.getString("TITLE") );
				cou.setStartDate( set.getDate("START_DATE"));
				cou.setEndDate( set.getDate("END_DATE") );
				cou.setAmount( set.getInt("AMOUNT"));
				cou.setType(CouponType.valueOf(set.getString("TYPE")));
				cou.setMessage(set.getString("MESSAGE"));
				cou.setPrice(set.getDouble("PRICE"));
				cou.setImage(set.getString("IMAGE"));
				
				coupons.add(cou);
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

}
