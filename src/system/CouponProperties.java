package system;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.Properties;

public class CouponProperties {

	private static CouponProperties instance;
	private Properties props;
	
	
	private CouponProperties() throws IOException{
		

		if ( !Files.exists( Paths.get("Properties.xml") ,LinkOption.NOFOLLOW_LINKS)){
			props = getDefaults();
			props.storeToXML(new FileOutputStream("Properties.xml"), "This is my properties files.");
		} else {
			props = new Properties();
			props.loadFromXML(new FileInputStream("Properties.xml"));
		}



	}
	
	
	public static CouponProperties getInstance() throws IOException{
		if ( instance == null ) {
			instance = new CouponProperties();
		}
		
		return instance;
	}
	
	

	
	public void saveProperties() throws IOException{
		props.storeToXML(new FileOutputStream("Properties.xml"), "This is my properties files.");
	}
	
	public String getProperty(String p){
		return props.getProperty(p);	
	}
	
	public void setProperty(String key, String value){
		props.setProperty(key, value);
	}
	
	
	private Properties getDefaults(){
		Properties prop = new Properties();
		
		prop.setProperty("user", "Java");
		prop.setProperty("password", "nir");
		prop.setProperty("driver", "com.mysql.jdbc.Driver");
		prop.setProperty("dbUrl", "jdbc:mysql://localhost:3306/projectcoupon");
	//	prop.setProperty("dbUrl", "jdbc:mysql://conkuk.no-ip.org:3306/projectcoupon");
		prop.setProperty("maxConnections", "30");
		
		return prop;
	}
	
}
