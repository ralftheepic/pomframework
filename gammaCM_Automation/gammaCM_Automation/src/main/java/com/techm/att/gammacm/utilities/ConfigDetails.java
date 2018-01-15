package com.techm.att.gammacm.utilities;


/**
 * 
 * @author ChandraShekhar.G
 *
 */

public class ConfigDetails {


	private String testSuiteName;
	private String browser;
	private String dbHost;
	private String dbUsername;
	private String dbPassword;
	private String appURL;
	private String dbPort;
	private String dbSchemaName;
	private String gridUrl;
	private String gridFlag;
	private String testDataScriptPath;
	private String isTestDataEnable; 

	
		
	public String getDbPort() {
		return dbPort;
	}

	public void setDbPort(String dbPort) {
		this.dbPort = dbPort;
	}

	public String getDbSchemaName() {
		return dbSchemaName;
	}

	public void setDbSchemaName(String dbSchemaName) {
		this.dbSchemaName = dbSchemaName;
	}

	public String getTestSuiteName() {
		return testSuiteName;
	}

	public void setTestSuiteName(String testSuiteName) {
		this.testSuiteName = testSuiteName;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getAppUrl() {
		return appURL;
	}

	public void setAppUrl(String appURL) {
		this.appURL = appURL; 
	}

	public String getDbHost() {
		return dbHost;
	}

	public void setDbHost(String dbHost) {
		this.dbHost = dbHost;
	}

	public String getDbUsername() {
		return dbUsername;
	}

	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}
	
	public String getGridUrl() {
		return gridUrl;
	}

	public void setGridUrl(String gridUrl) {
		this.gridUrl = gridUrl;
	}
	
	public String getGridFlag() {
		return gridFlag;
	}

	public void setGridFlag(String gridFlag) {
		this.gridUrl = gridFlag;
	}

	public String getTestDataScriptPath() {
		return testDataScriptPath;
	}

	public void setTestDataScriptPath(String testDataScriptPath) {
		this.testDataScriptPath = testDataScriptPath;
	}

	public String getIsTestDataEnable() {
		return isTestDataEnable;
	}

	public void setIsTestDataEnable(String isTestDataEnable) {
		this.isTestDataEnable = isTestDataEnable;
	}
}

