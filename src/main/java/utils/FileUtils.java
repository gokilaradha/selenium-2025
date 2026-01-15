package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtils {
	
	public static String readPropertiesFile(String filePath,String key) {
		String value=null;
		System.out.println("Reading properties file from: " + filePath);
		Properties properties =new Properties();
		try {
			FileInputStream input=new FileInputStream(filePath);
			properties.load(input);
			value=properties.getProperty(key);
			System.out.println("Value for key  '" + key + "' : " +value);
		}catch(IOException e) {
			System.err.println("Error reading properties file: " + e.getMessage());
		}
		return value;
	}

	public static boolean isLoginHistoryFileDownloaded() {
	    String downloadPath = System.getProperty("user.home") + File.separator + "Downloads";
	    File dir = new File(downloadPath);

	    File[] files = dir.listFiles();
	    if (files == null || files.length == 0) {
	        return false;
	    }

	    for (File file : files) {
	        if (file.getName().startsWith("LoginHistory") && file.getName().endsWith(".csv")) {
	            return true;
	        }
	    }
	    return false;
	}

}
