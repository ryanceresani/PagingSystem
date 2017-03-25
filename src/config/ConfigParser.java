package config;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigParser extends Properties {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4819161035935624884L;
	String result = "";
	InputStream inputStream;
	
	public ConfigParser() throws IOException{
		getPropValues();
		
	}
	public void getPropValues() throws IOException {	
		try {
			String propFileName = "config.properties";

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				this.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found.");
			}

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}		
	}

	public int get (String key){
		return Integer.parseInt(this.getProperty(key));
	}
	
}