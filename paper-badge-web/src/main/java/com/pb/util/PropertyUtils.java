package com.pb.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertyUtils {
	
	public static final String FILE_PATH = "D:/Work/MozillaScience/paper-badge-web/config/";
	
	public static void addProperty(String file, String key, String val) throws IOException {
		
		Properties prop = getPropertyFile(file);
		OutputStream output = null;
		 
		try {
	 
			output = new FileOutputStream(FILE_PATH+file);
	 
			prop.setProperty(key, val);
			prop.store(output, null);
	 
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	 
		}
	}
	
	public static String getProperty(String file, String key) throws IOException {
		Properties prop = getPropertyFile(file);
		return (String) prop.get(key);
	}
	
	private static Properties getPropertyFile(String file) throws IOException {
		Properties prop = new Properties();
		InputStream input = null;
		input = new FileInputStream(FILE_PATH+file);
		prop.load(input);
		return prop;
	}
}
