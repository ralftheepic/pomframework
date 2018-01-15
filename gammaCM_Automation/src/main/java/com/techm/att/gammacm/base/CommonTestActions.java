package com.techm.att.gammacm.base;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/* 
 * @author ChandraShekhar.G
 *
 */

public class CommonTestActions implements IConstants {


	public WebDriver driver;
	public ExtentTest reportsLogger;
	public static ExtentReports reports;
	public static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	public static final ThreadLocal<ExtentTest> extentReport = new ThreadLocal<ExtentTest>();


	private static Logger logger = LoggerFactory.getLogger(CommonTestActions.class);


	public static ExtentReports getReports(){
		return reports;
	}

	public WebDriver getDriver(){
		return webDriver.get();
	}

	public void setDriver(WebDriver driver){
		webDriver.set(driver);
	}
	

	public void setCurrentLogger(ExtentTest test) {
		extentReport.set(test);
	}

	public ExtentTest getCurrentLogger() {
		return extentReport.get();
	}

	public synchronized void startTest(String testName) {
		ExtentTest test = reports.startTest(testName);
		setCurrentLogger(test);
	}



	public void scrollBarDown(String scrollLength, int sleepSecs){
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0," + scrollLength + ")", "");
		try {
			Thread.sleep(sleepSecs);
		} catch (InterruptedException e) {
			logger.error("Exception Caught : " + e.getMessage());
		}
	}
	
	public void scrollToView(WebElement ele) throws Exception{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", ele);
		Thread.sleep(3000);
	}
	
	public void scrollBarUp(String scrollLength, int sleepSecs){
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0," + scrollLength + ")", "");
		try {
			Thread.sleep(sleepSecs);
		} catch (InterruptedException e) {
			logger.error("Exception Caught : " + e.getMessage());
		}
	}
	
	
	public void clickButton(WebElement ele) throws InterruptedException {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele);
	}
	

	public void moveToElement(WebElement element) throws InterruptedException {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
	}

	
	public void scrollIntoView(WebElement element) throws InterruptedException {
		WebElement banner = driver.findElement(By.xpath("html/body/div[2]/top-nav-bar/div/div/div"));
		int height = banner.getSize().getHeight()*-1;
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(2000);
		((JavascriptExecutor)driver).executeScript("window.scrollBy(0," + height + ")", "");
	}

	public void selectItemByText(WebElement ele,String option,String CustomMessage) {
		Select select = new Select(ele);
		select.selectByVisibleText(option);
		String itemSelected = select.getFirstSelectedOption().getText().trim();
		if(itemSelected.isEmpty()){
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("var select = arguments[0]; for(var i = 0; i < select.options.length; i++){ if(select.options[i].text == arguments[1]){ select.options[i].selected = true; } }", ele, option);
			logger.info("Selected '" + option + "' Option from " + CustomMessage + " dropdown");
			reportsLogger.log(LogStatus.INFO, "Selected '" + option + "' Option from " + CustomMessage + " dropdown");
		}else{
			logger.info("Selected '" + option + "' Option from " + CustomMessage + " dropdown");
			reportsLogger.log(LogStatus.INFO, "Selected '" + option + "' Option from " + CustomMessage + " dropdown");
		}
	}


	public void selectItemByIndex(WebElement ele,int itemIndex,String customMessage){
		try{
			Select select = new Select(ele);
			select.selectByIndex(itemIndex);
			logger.info("Selected item at index: " + itemIndex + " From " + customMessage);
			reportsLogger.log(LogStatus.INFO, "Selected item at index: " + itemIndex + " From " + customMessage);
		}catch(NoSuchElementException e){
			reportsLogger.log(LogStatus.FAIL, "Unable to Select item at index: " + itemIndex + " From " + customMessage);
			logger.error(e.getMessage());
			throw e; 
		} 
	}


	public void selectItemByValue(WebElement ele,String value,String customMessage){
		try{
			Select select = new Select(ele);
			select.selectByValue(value);
			logger.info("Selected '" + value + "' From " + customMessage);
			reportsLogger.log(LogStatus.INFO, "Selected '" + value + "' From " + customMessage); 
		}catch(NoSuchElementException e){
			reportsLogger.log(LogStatus.ERROR, "Unable to Select '" + value + "' From " + customMessage);
			logger.error(e.getMessage());
			throw e; 
		} 
	}


	/*
	 * Utility method find an element with an explicit wait time. This is useful for pagination scenarios 
	 */
	public WebElement findElement(By locator) {
		return findElement(locator, DEFAULT_EXPLICIT_WAIT_TIME);
	}

	/*
	 * Utility method find an element with an explicit wait time. This is useful for pagination scenarios 
	 */
	public WebElement findElement(By locator, long timeOutInSeconds) {
		return (new WebDriverWait(this.driver, timeOutInSeconds)).until(ExpectedConditions.presenceOfElementLocated(locator));
	}


	public WebElement findElementWithClickableExplicitWait(WebElement element, long timeOutInSeconds) {
		return (new WebDriverWait(this.driver, timeOutInSeconds)).until(ExpectedConditions.elementToBeClickable(element));
	}

	public void commonMouseHoverAction(WebElement element,String customMessage) {
		try{
			(new WebDriverWait(driver, DEFAULT_EXPLICIT_WAIT_TIME)).until(ExpectedConditions.elementToBeClickable(element));
			Actions action = new Actions(this.driver);
			action.moveToElement(element).build().perform();
			reportsLogger.log(LogStatus.INFO, "Mouse Hover '" + customMessage + "'");
		}catch(Exception e){
			logger.error(e.getMessage());
			reportsLogger.log(LogStatus.ERROR, "Not able to Mouse Hover '" + customMessage + "'");
			throw e;
		}
	}

	public boolean isElementPresent(By locator)  {
		try{
			new WebDriverWait(this.driver, DEFAULT_EXPLICIT_WAIT_TIME).until(ExpectedConditions.presenceOfElementLocated(locator));
			this.driver.findElement(locator);
			return true;
		}catch(NoSuchElementException e){
			logger.error("The Exception is :: "  + e);
			return false;
		}catch(Exception e){
			logger.error("The Exception is :: "  + e);
			return false;
		}
	}	


	/*
	 * Utility method test for presence of an element with an explicit wait time. This is useful for pagination scenarios 
	 */
	public boolean isElementPresent(By locator, long timeOutInSeconds)  {

		try{
			(new WebDriverWait(this.driver, timeOutInSeconds)).until(ExpectedConditions.presenceOfElementLocated(locator));
			return true;
		}catch(Exception e){
			return false;
		}
	}	


	public boolean isWebElementDisplayed(WebElement element,String msg) {
		if(element != null){
			logger.info(msg + " is displayed");
			return true;
		}else{
			logger.info(msg +" is not displayed");
			return false;
		}
	}


	public  boolean isLinkDisplayed(String link){
		try{
			if(driver.findElement(By.partialLinkText(link)).isDisplayed()){
				logger.info(link + " link is displayed");
				return driver.findElement(By.partialLinkText(link)).isDisplayed();
			}else{
				return false;
			}
		}catch(NoSuchElementException exp){
			return false;
		}
	} 


	public void waitForElementToBeVisible(WebElement ele, long timeOutInSeconds) {
		try{
			(new WebDriverWait(this.driver, timeOutInSeconds)).until(ExpectedConditions.visibilityOf(ele));
			Assert.assertTrue(true,"Element is Present and Visible");
		}catch(Exception e){
			Assert.assertTrue(false,"Element is Present but not Visible");
		}
	}


	public void waitForElementToBePresent(By locator, long timeOutInSeconds) {
		try{
			(new WebDriverWait(this.driver, timeOutInSeconds)).until(ExpectedConditions.presenceOfElementLocated(locator));
			Assert.assertTrue(true,"Element is Present");
		}catch(Exception e){
			Assert.assertTrue(false,"Element is not Present");
		}
	}


	public boolean isWebElementDisplayed(WebElement element){
		try{
			if(element.isDisplayed()){
				logger.info(element.getText()+ " is displayed");
				reportsLogger.log(LogStatus.INFO, element.getText() +" Element Visible");
				return element.isDisplayed();
			}else{
				reportsLogger.log(LogStatus.ERROR, "Element is Not Visible");
				return false;
			}
		}catch(NoSuchElementException exp){
			return false;
		}
	}


	public int genRandomNum(int endRange){
		Random randomGenerator = new Random();
		int randIndex = randomGenerator.nextInt(endRange-1);
		if (randIndex == 0)
			randIndex = randIndex + 1;
		return randIndex;
	}























}































