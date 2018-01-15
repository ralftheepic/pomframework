package com.techm.att.gammacm.base;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import com.techm.att.gammacm.utilities.ConfigDetails;
import com.techm.att.gammacm.utilities.ReadConfigFile;
import com.techm.att.gammacm.utilities.XlsReader;


/*
 * @author ChandraShekhar.G
 *
 */

public class TestBase extends CommonTestActions{

	public static String browserType = null;
	public static String date = new SimpleDateFormat("dd-MMM-yyy").format(Calendar.getInstance().getTime());
	public static String dateAndTime = new SimpleDateFormat("dd-MMM-yyy hh-mm a z").format(Calendar.getInstance().getTime());
	public static String appURL = null;
	public static String configPath = null; 
	public static String suiteType = null;
	public static String gridURL; 
	public static String gridFlag;
	DesiredCapabilities desiredCap = null;
	public static ArrayList<String> TestCases = new ArrayList<String>();
	public static XlsReader xlsReader = null;
	public static ConfigDetails configDtls = null;
	public static HashMap<String, String> sqlQueries = new HashMap<String, String>();
	public static String screenshotsDirectory = System.getProperty("user.dir") +  "\\Screenshots\\Screenshots"+"_"+ dateAndTime;
	public static String ticketNumber_D1_PR = null;
	public static String ticketNumber_D2_PR = null;
	public static String ticketNumber_D1_CRPR = null;
	public static String ticketNumber_D2_CRPR = null;
	public static String ticketNumber_D1_Bulk_PR = null;
	public static String ticketNumber_D2_Bulk_PR = null;

	
	
	private static Logger logger = LoggerFactory.getLogger(TestBase.class);

	@BeforeSuite
	public void initializeSuite(){
		try {
			//Creates a testResult template
			reports = new ExtentReports(System.getProperty("user.dir") + "\\TestResults\\Gamma_CM_TestAutomationReport.html");
			//Reading the Config details info
			configDtls  = ReadConfigFile.readConfigDetails();
			browserType = configDtls.getBrowser();
			appURL = configDtls.getAppUrl();
			suiteType = configDtls.getTestSuiteName();
			gridURL = configDtls.getGridUrl();
			gridFlag = configDtls.getGridFlag();
			//Reading the External Spreadsheet
			xlsReader= new XlsReader(System.getProperty("user.dir") + "\\" + suiteType + "_Suite.xlsx");
			//Reading the Executable Test Cases		    
			TestCases = xlsReader.getExecutableTestCases();
		} catch (Exception e) {
			logger.error("Error in initializeSuite Method: ", e.getMessage());
			System.out.println(e.getMessage());
			
		}	
	}


	@BeforeMethod
	public void initDriver() {
		try {		
			String browser = System.getProperty("browser.type") != null
					&& !System.getProperty("browser.type").isEmpty() ? System
							.getProperty("browser.type") : browserType;		
							logger.info("Test suite is executing on " + browser + " browser");
							switch (browser.toLowerCase()) {
							case "chrome":
								initChromeDriver(appURL);
								break;
							case "firefox":
								initFirefoxDriver(appURL);
								break;
							case "ie":
								initIEDriver(appURL);
								break;
							case "headless":
								//initPhantomDriver(appURL);
								break;
							}
		} catch (Exception e) {
			logger.error("Error in initDriver Method: ", e.getMessage());
			e.printStackTrace();
		}
	}


	private void initChromeDriver(String appURL) {
		logger.info("Launching Google Chrome browser......");
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		setDriver(driver);
		getDriver().manage().window().maximize();
		getDriver().get(appURL);
		getDriver().manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
		logger.info("Launched URL : " + appURL);
	}


	private void initFirefoxDriver(String appURL) throws InterruptedException {
		logger.info("Launching FireFox browser......");
		//System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\drivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		setDriver(driver);
		getDriver().manage().window().maximize();
		getDriver().get(appURL);
		getDriver().manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
		logger.info("Launched URL : " + appURL);
	}


	private void initIEDriver(String appURL) {
		logger.info("Launching Internet Explorer browser......");
		System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\drivers\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		setDriver(driver);
		getDriver().manage().window().maximize();
		getDriver().get(appURL);
		getDriver().manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
		logger.info("Launched URL : " + appURL);
	}	
/*
	private void initPhantomDriver(String appURL) {
		logger.info("Launching browser in headless mode via PhantomJS......");
		System.setProperty("phantomjs.binary.path", System.getProperty("user.dir")+"\\drivers\\phantomjs.exe");
		driver = new PhantomJSDriver();
		setDriver(driver);
		getDriver().manage().window().maximize();
		getDriver().get(appURL);
		getDriver().manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
		logger.info("Launched URL : " + appURL);
	}
*/
	public void login(String strUsername,String strPassword) throws InterruptedException {
		getDriver().findElement(By.name("userid")).sendKeys(strUsername.trim());
		getDriver().findElement(By.name("password")).sendKeys(strPassword.trim());
		Thread.sleep(3000);
		getDriver().findElement(By.name("btnSubmit")).click();
		Thread.sleep(3000);
		getDriver().findElement(By.name("successOK")).click();;
		Thread.sleep(5000);
		logger.info("Successfully Logged into Application");
		getCurrentLogger().log(LogStatus.INFO, "Successfully Logged into Application");
	}	

	public void logout() throws InterruptedException {
		getDriver().findElement(By.id("Section0_Section1_HeaderSection_HeaderC_LogoutIcon")).click();
		Thread.sleep(12000);
		logger.info("Successfully Logged out from Application");
		getCurrentLogger().log(LogStatus.INFO, "Successfully Logged out from Application");
	}

	//Validates whether the test case is allowed to run
	public void validateIfTestCaseAllowed(String methodName) throws IOException {
		if(TestCases.contains(methodName.trim())){
			logger.info("Executing the test : " + methodName );
			getCurrentLogger().log(LogStatus.INFO, "Executing the test : " + methodName );
		}
		else {
			logger.info("Skipping the test '" + methodName + "' as testcase Runmode is set to: NO");
			getCurrentLogger().log(LogStatus.INFO, "Skipping the test '" + methodName + "' as testcase Runmode is set to: NO" );
			throw new SkipException("Skipping the test '" + methodName
					+ "' as testcase Runmode is set to: NO");
		}	
	}


	public String captureScreenshot(WebDriver driver, String screenShotName){
		logger.info("screenshotsDirectory:"+screenshotsDirectory);
		String dateAndTime = new SimpleDateFormat("dd-MMM-yyy hh-mm a z").format(Calendar.getInstance().getTime());
		logger.info("dateAndTime:"+dateAndTime);
		try {
			TakesScreenshot screenShot = (TakesScreenshot)driver;
			File source = screenShot.getScreenshotAs(OutputType.FILE);
			String dest = screenshotsDirectory + "\\"+ screenShotName +"_"+dateAndTime+ ".png";
			File destination = new File(dest);
			FileUtils.copyFile(source, destination);
			return dest;
		} catch (IOException e) {
			logger.error("Error in captureScreenshot Method: ", e.getMessage());
			return e.getMessage();
		}

	}


	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) throws InterruptedException{
		if(reports != null && getCurrentLogger() !=null){
			if(result.getStatus() == ITestResult.SKIP){	
				getCurrentLogger().log(LogStatus.SKIP, "Skipped the Test Successfully");
			}else if (result.getStatus() == ITestResult.FAILURE) {
				//Retry retry = new Retry();
				//boolean status = retry.retry(result);
				//if(status == false){
				String screenShotPath = captureScreenshot(getDriver(), result.getMethod().getMethodName());
				String image = getCurrentLogger().addScreenCapture(screenShotPath);
				getCurrentLogger().log(LogStatus.ERROR, "Exception", result.getThrowable().toString());
				getCurrentLogger().log(LogStatus.FAIL, "Test is Failed", image);
				logger.error("Exception",result.getThrowable());
				//}else{
				//	logger.info(result.getName() + " Test Script is Passed when re-Excuted");
				//	getCurrentLogger().log(LogStatus.INFO,  result.getName() + " Test Script is Passed when re-Excuted");
				//}
			}else if (result.getStatus() == ITestResult.SUCCESS) {
				getCurrentLogger().log(LogStatus.PASS, "Test Execution is Successful");
			}

			reports.endTest(getCurrentLogger());
			reports.flush();
			// logout user from application
			//logout();
			getDriver().quit();
		}
	}


	@AfterSuite
	public void tearDownSuite(){
		if (reports != null){
			reports.close();
		}
	}





}
