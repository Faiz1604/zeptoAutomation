package browser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

public class Utility {
	
	public static String getProperty(String property) throws IOException {
		
		String path = System.getProperty("user.dir") 
				+ File.separator
				+"src"+File.separator
				+"test"+File.separator
				+"resources"+File.separator
				+"Configure.properties";
		
		Reader file = new FileReader(path);
		Properties p = new Properties();
		p.load(file);
		return p.getProperty(property);
	}

}
