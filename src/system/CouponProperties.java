package system;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.Properties;

import exception.PropertiesFileMissingException;

public class CouponProperties {

	private static CouponProperties instance;
	private Properties props;
	
	
	private CouponProperties() throws IOException, PropertiesFileMissingException{
		
		try {
			if ( !Files.exists( Paths.get("Properties.xml") ,LinkOption.NOFOLLOW_LINKS)){
				props = getDefaults();
				props.storeToXML(new FileOutputStream("Properties.xml"), "This is my properties files.");
			} else {
				props.loadFromXML(new FileInputStream("Properties.xml"));
			}
		} catch ( FileNotFoundException e){
			throw new PropertiesFileMissingException();
		}


	}
	
	
	public static CouponProperties getInstance() throws IOException, PropertiesFileMissingException{
		if ( instance == null ) {
			instance = new CouponProperties();
		}
		
		return instance;
	}
	
	

	
	public void saveProperties() throws FileNotFoundException, IOException{
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
