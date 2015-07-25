package interfaces;

import java.sql.Date;
import java.sql.SQLException;


public interface CustomerHistoryDAO {
	
	public void upDateHistory(long customerId, String title, Date date) throws SQLException;

}
