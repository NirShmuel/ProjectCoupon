package system;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import connectionPool.ConnectionPoolSingleton;
import exception.PropertiesFileMissingException;
import exception.WrongCredentialsException;
import facade.AdminFacade;
import facade.CompanyFacade;
import facade.CustomerFacade;


public class CouponSystem  {
	
	private static CouponSystem instance;
	private DailyCouponExpirationTask task;
	private Thread taskThread;
	
	private CouponSystem() throws SecurityException, IOException, SQLException{
		super();
		
		// Initializing a logger for the program.
		FileHandler fh = new FileHandler("CouponSystemLog.log",true);
		Formatter f = new Formatter() {
			@Override
			public String format(LogRecord record) {
				Date d = new Date(record.getMillis());
				return d.toString() + " " + record.getLevel().toString() +": " + record.getMessage() + "\r\n";
			}
		};
			
		fh.setFormatter(f);
		Logger.getLogger(Common.LOGGER).addHandler(fh);
		Logger.getLogger(Common.LOGGER).setUseParentHandlers(false);
		
		Logger.getLogger(Common.LOGGER).log(Level.INFO,"Initializing daily task!");
		// Initializing the daily expiration checker thread.
		task = new DailyCouponExpirationTask();
		taskThread = new Thread(task);
		taskThread.start();
		Logger.getLogger(Common.LOGGER).log(Level.INFO,"Daily task initialized!");
		
		
	}
	
	public static CouponSystem getInstance() throws SecurityException, IOException, SQLException{
		if ( instance == null ){
			instance = new CouponSystem();
		}
		return instance;
	}



	
	public AdminFacade adminLogin(String name, String password) throws WrongCredentialsException, SQLException, IOException{
		return  AdminFacade.login(name, password);
	}

	
	public CompanyFacade companylogin(long id, String password) throws WrongCredentialsException, SQLException, IOException{
		return CompanyFacade.login(id, password);
	}


	public CustomerFacade customerLogin(long id, String password) throws WrongCredentialsException, SQLException, IOException{
		return CustomerFacade.login(id, password);
	}

	public void shutdown() throws SQLException, IOException, PropertiesFileMissingException, InterruptedException{
		task.stopTask();
		taskThread.interrupt();
		taskThread.join();
		ConnectionPoolSingleton.getInstance().shutdown();
	}
	

}
