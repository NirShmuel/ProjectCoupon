package dao;

import interfaces.CompanyDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import beans.Company;
import beans.Coupon;
import beans.CouponType;
import connectionPool.ConnectionPoolSingleton;
import exception.DoesNotExistException;
import exception.DuplicateNameException;
import exception.NoUpdateException;

public class CompanyDBDAO implements CompanyDAO {


	
	@Override
	public void createCompany(Company comp) throws DuplicateNameException, SQLException {
		Connection con = null;
		
		try {
			con = ConnectionPoolSingleton.getInstance().getConnection();
		
			Statement stat = con.createStatement();
				
			String sql = "INSERT INTO Company ( COMP_NAME, PWD, EMAIL) VALUES ('" +
					comp.getCompName()		+ "','"	+
					comp.getPassword()		+ "','"	+
					comp.getEmail()			+ "')";
			
			// When running execute() with a second parameter set to RETURN_GENERATED_KEYS you can get the PK valuse generated by the sql-server
			stat.execute(sql, Statement.RETURN_GENERATED_KEYS );
			
			ResultSet set = stat.getGeneratedKeys();
			set.next();
			
			// Setting the Id of the company to the one received from the server.
			comp.setId( set.getLong(1) );
			

		} catch (SQLException e) {
			if ( e.getMessage().contains("Duplicate")){
				throw new DuplicateNameException("Duplicate Company name.");
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
	 * Remove a company by id of the company
	 * @throws SQLException 
	 * @throws DoesNotExistException - If a company with this Id did not exist
	 */
	@Override
	public void removeCompany(long id) throws SQLException, DoesNotExistException {
			Connection con = null;
		try {
			con = ConnectionPoolSingleton.getInstance().getConnection();
			
			Statement stat = con.createStatement();
					
			String sql = "DELETE FROM Company WHERE ID = '" + id + "'";

			stat.execute(sql);
			
			if ( stat.getUpdateCount() == 0 ) {
				throw new DoesNotExistException("Company with this ID does not exist in the database.");
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
	public void updateCompany(Company comp) throws SQLException, NoUpdateException {
			Connection con = null;
			String sql = null;
		try{
			con = ConnectionPoolSingleton.getInstance().getConnection();
			
			Statement stat = con.createStatement();
			
			sql = "UPDATE Company SET `COMP_NAME` = '" + comp.getCompName() + "', `PWD` = '" + comp.getPassword() 
					+ "', `EMAIL` = '" + comp.getEmail() + "' WHERE `ID` = '" + comp.getId() + "'"; 
			
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
	public Company getCompany(long id) throws SQLException, DoesNotExistException {
			Connection con = null;
			Company comp = new Company();
		
		try {
			con = ConnectionPoolSingleton.getInstance().getConnection();
	
			Statement stat = con.createStatement();
					
			String sql = "SELECT * FROM Company WHERE ID ='" + id + "'";
			stat.execute(sql);
			ResultSet set = stat.getResultSet();
			
			if  ( set.next() ){
				comp.setCompName( set.getString("COMP_NAME") );
				comp.setEmail( set.getString("EMAIL") );
				comp.setPassword( set.getString("PWD") );
				comp.setId( id );
				
			} else {
				throw new DoesNotExistException("Company with this ID does not exist in the database.");
			}
			
	

		}  catch (SQLException e) {
				throw e;
			
		}finally {
			if ( con != null ){
				ConnectionPoolSingleton.getInstance().releaseConnection(con);
			}
		}
		
		return comp;
	}
	

	/**
	 * 
	 * @return returns a collection of companies if they are present, otherwise returns null.
	 * @throws SQLException
	 * 
	 */
	@Override
	public Collection<Company> getAllCompanies() throws SQLException {
		
		Company comp = null;
		Connection con = null;
		Collection<Company> companies = new ArrayList<Company>();
		String sql;
		
		try{
			con = ConnectionPoolSingleton.getInstance().getConnection();
			
			Statement stat = con.createStatement();
			
			sql = "SELECT * FROM COMPANY";
			stat.execute(sql);
			ResultSet set = stat.getResultSet();
						
			while (set.next()){
				comp = new Company();
				comp.setCompName( set.getString("COMP_NAME") );
				comp.setEmail( set.getString("EMAIL") );
				comp.setPassword( set.getString("PWD") );
				comp.setId( set.getLong("ID"));
				companies.add(comp);

			}
			if ( companies.size() == 0){
				companies = null;
			}
			
			
		}catch (SQLException e) {
			throw e;
		}finally {
			if ( con != null ){
				ConnectionPoolSingleton.getInstance().releaseConnection(con);
			}
		}
		
		return companies;
	}
	/**
	 * 
	 * @throws SQLException 
	 */
	@Override
	public Collection<Coupon> getCompanyCoupons(long id) throws SQLException {

		Coupon coupon = null;
		Connection con = null;
		String sql;
		Collection<Coupon> coupons = new ArrayList<Coupon>();
		
		try{
			con = ConnectionPoolSingleton.getInstance().getConnection();
			
			Statement stat = con.createStatement();
			
//			sql = "SELECT * FROM Company_Coupon WHERE COMP_ID = '" + id + "' ";////////
			
			sql = "SELECT * FROM Coupon "
					+ "WHERE ID IN ( SELECT COUP_ID FROM Company_Coupon WHERE COMP_ID ='" + id + "')";
			
			/*sql = "SELECT * FROM Coupon "
					+ "INNER JOIN Company_Coupon "
					+ "ON Coupon.ID = Company_Coupon.COUPON_ID "
					+ "WHERE Company_Coupon.COMP_ID = '" + id + "'";*/
			
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
	public boolean login(String name, String password) {
		return false;
	}

}
