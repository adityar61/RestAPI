package org.com.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

	Properties prop;
	
	public TestBase() {
	prop = new Properties();
	try {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/config/config.properties");
		prop.load(fis);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
	
}
