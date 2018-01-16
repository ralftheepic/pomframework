package com.techm.att.gammacm.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/*
 * @author ChandraShekhar.G
 *
 */

public class ReadConfigFile {

	private static Logger logger = LoggerFactory.getLogger(ReadConfigFile.class);
	
	
	/*
	 * Method to get the config details from config.properties file
	 */
	public static ConfigDetails readConfigDetails() {		
		ConfigDetails configDtls = new ConfigDetails();
		// get the test environment
		String testEnv = System.getProperty("gammacm.env");	
		String configPath = System.getProperty("user.dir") + "\\config-uat.properties";
		if(testEnv != null && !testEnv.isEmpty()) {			
			logger.info("Test suite is executing on " + testEnv + " environment");
			switch(testEnv.toUpperCase()) {
			case "UAT" :
				configPath = System.getProperty("user.dir") + "\\config-uat.properties";
				break;
			case "PROD" : 
				configPath = System.getProperty("user.dir") + "\\config-prod.properties";
				break;
			}
		}
		logger.info("Property file : " + configPath);
		FileInputStream inputStream =null;
		Properties prop = new Properties();

		try{
			inputStream = new FileInputStream (configPath);
		}
		catch (FileNotFoundException e){
			logger.error("File(" + configPath + ") not found : ", e);
		}

		try {
			prop.load(inputStream);
		} catch (IOException e) {
			logger.error("Exception in reading properties file : ", e);
		}	
		configDtls.setTestSuiteName(prop.getProperty("gammacm.test.suiteName").trim());
		configDtls.setAppUrl(prop.getProperty("gammacm.application.Url").trim());
		configDtls.setBrowser(prop.getProperty("gammacm.test.browserType").trim());
		configDtls.setGridUrl(prop.getProperty("gammacm.grid.Flag").trim());
		configDtls.setGridFlag(prop.getProperty("gammacm.grid.Url").trim());
		return configDtls;
	}

}

