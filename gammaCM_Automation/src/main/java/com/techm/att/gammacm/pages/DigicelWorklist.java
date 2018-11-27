package com.techm.att.gammacm.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DigicelWorklist extends HeaderPage {

	private static Logger logger = LoggerFactory.getLogger(DigicelWorklist.class);

	public DigicelWorklist(WebDriver driver, ExtentTest reportsLogger) {
		super(driver, reportsLogger);// TODO Auto-generated constructor stub
		this.driver = driver;
		this.reportsLogger = reportsLogger;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
	}

	@FindBy(xpath = "//a[@class='dropdown-toggle bgUser']")
	public WebElement IcnAccountuser;

	@FindBy(id = "loginemail")
	public WebElement txtuserID;

	@FindBy(id = "loginpassword")
	public WebElement txtuserPass;
	
	@FindBy(xpath="//button[contains(.,'JE ME CONNECTE')]")
	public WebElement btnLogin;
	
	
	public void login() throws InterruptedException {
		moveToElement(IcnAccountuser);
		logger.info("cursor is hover to login icn");
		reportsLogger.log(LogStatus.INFO, "cursor is hover to login icn");
	}

	public void enterID(String ID) {

		txtuserID.sendKeys(ID);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	public void enterPassword(String pass) {

		txtuserPass.sendKeys(pass);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	public void clickonLogin(){
		
		btnLogin.click();
		logger.info("click on login btn");
		reportsLogger.log(LogStatus.INFO, "click on login btn");
	}
}
