package com.techm.att.gammacm.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class WorkListPage extends HeaderPage{

	private static Logger logger=LoggerFactory.getLogger(WorkListPage.class);

	@FindBy(xpath=".//*[@id='TicketWorkList']//thead/tr/th[1]/a")
	public WebElement lblWorkChangeno;

	@FindBy(xpath=".//*[@id='TicketWorkList']//thead/tr/th[2]/a")
	public WebElement lblWorkCreateBy;

	@FindBy(xpath=".//*[@id='TicketWorkList']//thead/tr/th[3]/a")
	public WebElement lblWorkStatus;

	@FindBy(xpath=".//*[@id='TicketWorkList']//thead/tr/th[4]/a")
	public WebElement lblWorkCategory;

	@FindBy(xpath=".//*[@id='TicketWorkList']//thead/tr/th[5]/a")
	public WebElement lblWorkPlannedStartDate;

	@FindBy(xpath=".//*[@id='TicketWorkList']//thead/tr/th[6]/a")
	public WebElement lblWorkPlannedEndDate;

	@FindBy(xpath=".//*[@id='TicketWorkList']//thead/tr/th[7]/a")
	public WebElement lblWorkExternalSystemName;

	@FindBy(xpath=".//*[@id='TicketWorkList']//thead/tr/th[8]/a")
	public WebElement lblWorkExternalChangeId;

	@FindBy(xpath=".//*[@id='TicketWorkList']//thead/tr/th[9]/a")
	public WebElement lblWorkUpdatedDate;

	@FindBy(xpath=".//*[@id='TicketWorkList']//thead/tr/th[10]/a")
	public WebElement lblWorkUpdatedBy;

	@FindBy(xpath=".//*[@id='TicketWorkList']//thead/tr/th[11]/a")
	public WebElement lblWorkUrgency;

	@FindBy(xpath=".//*[@id='Section0_Section2_TicketWorkList']/div[1]/div/span/dropdown-multiselect/div/button")
	public WebElement lblColumnlist;

	@FindBy(xpath=".//*[@class='row GammaCmTWHeaderMargin']/div[3]/div/div/span//div/ul/li[5]/a/span")
	public WebElement chkboxExternalSystemName;

	@FindBy(xpath=".//*[@class='row GammaCmTWHeaderMargin']/div[3]/div/div/span//div/ul/li[1]/a/i")
	public WebElement chkboxcheckall;	

	@FindBy(xpath=".//*[@class='row GammaCmTWHeaderMargin']/div[3]/div/div/span//div/ul/li[2]/a/i")
	public WebElement chkboxDecheckall;	

	@FindBy(xpath=".//*[@id='Section0_Section2_TicketWorkList']/div[1]/div/span/span[2]")
	public WebElement IcnFilter;

	@FindBy(xpath=".//*[@id='changeId']/span[3]/input")
	public WebElement txtFilterChangeNumber;

	@FindBy(xpath=".//*[@id='requesterName']/span[3]/input")
	public WebElement txtFilterCreatedBy;

	@FindBy(xpath=".//*[@id='status']/span[3]/input")
	public WebElement txtFilterStatus;

	@FindBy(xpath=".//*[@id='category']/span[3]/input")
	public WebElement txtFilterCategory;

	@FindBy(xpath=".//*[@id='plannedStartDate']/span[3]/input")
	public WebElement txtFilterPlannedStartDate;

	@FindBy(xpath=".//*[@id='plannedEndDate']/span[3]/input")
	public WebElement txtFilterPlannedEndDate;

	@FindBy(xpath=".//*[@class='navbar navbar-inverse']/div/div[2]/ul/li[3]/a")
	public WebElement IcnCreateTicket;

	@FindBy(xpath=".//*[@class='container-fluid']/div[3]/div/div/div/div[5]/select")
	public WebElement ddCreateTicStatus;

	@FindBy(id="Section0_Section4_Section5_DashboardGIElement_GeneralInformationCm_Urgency")
	public WebElement ddCreateTicUrgency;

	@FindBy(xpath=".//*[@id='Section0_Section4_Section5_DashboardGIElement_GeneralInformationCm']/div[7]/input")//(id="Section0_Section4_Section5_DashboardGIElement_GeneralInformationCm_PlannedStartDate")
	public WebElement datePlannedStartDate;

	@FindBy(xpath=".//*[@id='Section0_Section4_Section5_DashboardGIElement_GeneralInformationCm']/div[8]/input")//(id="Section0_Section4_Section5_DashboardGIElement_GeneralInformationCm_PlannedEndDate")
	public WebElement datePlannedEndDate;

	@FindBy(id="Section0_Section6_Section7_TicketDetails_gammaCMViewTicketPageSummary")
	public WebElement txtSummary;

	@FindBy(xpath=".//*[@class='container-fluid']/div[3]/div/div/div[2]//button")
	public WebElement btnCreateTicket;

	@FindBy(xpath=".//*[@id='Section0_Section6_Section7_TicketDetails']/div[1]/h3")
	public WebElement txtChangeInfo;

	public WorkListPage(WebDriver driver, ExtentTest reportsLogger) {
		super(driver, reportsLogger);
		this.driver = driver;
		this.reportsLogger = reportsLogger;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
	}

	public void verifyDefaultWorkList() throws Exception
	{
		defaultWorkList.isDisplayed();
		logger.info("Default WorkList is displayed");
		reportsLogger.log(LogStatus.INFO, "Default WorkList is displayed");
		if(driver.findElements(By.xpath("//div[@id='TicketWorkList']//tbody/tr/td")).isEmpty())
		{
			logger.info("no Data found");
			reportsLogger.log(LogStatus.ERROR, "No data found");
			throw new Exception(" no records found");
		}
	}
	public void verifyWorklistPage() throws Exception
	{
		verifyDefaultWorkList();
		Thread.sleep(2000);
		lblWorkChangeno.isDisplayed();
		logger.info("Changeno column is displayed");
		reportsLogger.log(LogStatus.INFO, "Changeno column is displayed");
		Thread.sleep(2000);
		lblWorkCreateBy.isDisplayed();
		logger.info("CreateBy column is displayed");
		reportsLogger.log(LogStatus.INFO, "CreateBy column is displayed");
		Thread.sleep(2000);
		lblWorkStatus.isDisplayed();
		logger.info("WorkStatus column is displayed");
		reportsLogger.log(LogStatus.INFO, "WorkStatus column is displayed");
		Thread.sleep(2000);
		lblWorkCategory.isDisplayed();
		logger.info("Category column is displayed");
		reportsLogger.log(LogStatus.INFO, "Category column is displayed");
		Thread.sleep(2000);
		lblWorkPlannedStartDate.isDisplayed();
		logger.info("PlannedStartDate column is displayed");
		reportsLogger.log(LogStatus.INFO, "PlannedStartDate column is displayed");
		Thread.sleep(2000);
		lblWorkPlannedEndDate.isDisplayed();
		logger.info("PlannedEndDate column is displayed");
		reportsLogger.log(LogStatus.INFO, "PlannedEndDate column is displayed");
		Thread.sleep(2000);
		lblWorkExternalSystemName.isDisplayed();
		logger.info("ExternalSystemName column is displayed");
		reportsLogger.log(LogStatus.INFO, "ExternalSystemName column is displayed");
		Thread.sleep(2000);
		lblWorkExternalChangeId.isDisplayed();
		logger.info("ExternalChangeId column is displayed");
		reportsLogger.log(LogStatus.INFO, "ExternalChangeId column is displayed");
		Thread.sleep(2000);
		lblWorkUpdatedDate.isDisplayed();
		logger.info("UpdatedDate column is displayed");
		reportsLogger.log(LogStatus.INFO, "UpdatedDate column is displayed");
		Thread.sleep(2000);
		lblWorkUpdatedBy.isDisplayed();
		logger.info("UpdatedBy column is displayed");
		reportsLogger.log(LogStatus.INFO, "UpdatedBy column is displayed");
		Thread.sleep(2000);
		lblWorkUrgency.isDisplayed();
		logger.info("Urgency column is displayed");
		reportsLogger.log(LogStatus.INFO, "Urgency column is displayed");


	}

	public void verifyColumnlist() throws Exception
	{
		boolean flag=false;
		verifyDefaultWorkList();
		lblWorkChangeno.isDisplayed();
		logger.info("Changeno column is displayed");
		reportsLogger.log(LogStatus.INFO, "Changeno column is displayed");
		lblWorkCreateBy.isDisplayed();
		logger.info("CreateBy column is displayed");
		reportsLogger.log(LogStatus.INFO, "CreateBy column is displayed");
		lblWorkStatus.isDisplayed();
		logger.info("WorkStatus column is displayed");
		reportsLogger.log(LogStatus.INFO, "WorkStatus column is displayed");
		lblWorkCategory.isDisplayed();
		logger.info("Category column is displayed");
		reportsLogger.log(LogStatus.INFO, "Category column is displayed");
		lblWorkPlannedStartDate.isDisplayed();
		logger.info("PlannedStartDate column is displayed");
		reportsLogger.log(LogStatus.INFO, "PlannedStartDate column is displayed");
		lblWorkPlannedEndDate.isDisplayed();
		logger.info("PlannedEndDate column is displayed");
		reportsLogger.log(LogStatus.INFO, "PlannedEndDate column is displayed");
		Thread.sleep(1000);
		lblColumnlist.isDisplayed();
		logger.info("Ticket attribute Icon is displayed");
		reportsLogger.log(LogStatus.INFO, "Ticket attribute Icon is displayed");
		waitForElementToBeVisible(lblColumnlist, 30);
		clickButton(lblColumnlist);
		Thread.sleep(2000);
		String classProperties=chkboxExternalSystemName.getAttribute("class");
		if(classProperties.equalsIgnoreCase("icon-ok pull-right"))
		{
			clickButton(chkboxExternalSystemName);
		}
		else
		{
			clickButton(chkboxExternalSystemName);
			Thread.sleep(2000);
			clickButton(chkboxExternalSystemName);
		}
		Thread.sleep(2000);
		int headerCount = driver.findElements(By.xpath("//*[@id='TicketWorklist']//thead/tr/th")).size();
		for(int col=1 ; col <= headerCount ; col++){
			String colName = driver.findElement(By.xpath("//*[@id='TicketWorklist']//thead/tr/th["+col+"]/a")).getText().trim();
			if(colName.equalsIgnoreCase("External System Name")){
				logger.error("External System Name is displayed");
				reportsLogger.log(LogStatus.FAIL, "External System Name column is displayed");
				throw new Exception("External System Name is displayed");
			}else
			{
				logger.info("External System Name column is not displayed");
				reportsLogger.log(LogStatus.INFO, "External System Name column is not displayed");
			}
		}
		Thread.sleep(2000);
		logger.info("Testing for UnCheck All options");
		reportsLogger.log(LogStatus.INFO, "Testing for UnCheck All options");
		waitForElementToBeVisible(chkboxDecheckall, 10);
		chkboxDecheckall.click();
		Thread.sleep(5000);
		int count=6;
		headerCount = driver.findElements(By.xpath("//*[@id='TicketWorkList']//thead/tr/th")).size();
		System.out.println(headerCount);
		if(headerCount==count)
		{
			logger.info("Columns are not displayed, Uncheck all working properly");
			reportsLogger.log(LogStatus.INFO, "Columns are not displayed, Uncheck all working properly");
		}
		else
		{
			throw new Exception("Uncheck All is not working properly");
		}		
		Thread.sleep(2000);
		logger.info("Testing for Check All options");
		reportsLogger.log(LogStatus.INFO, "Testing for Check All options");
		clickButton(chkboxcheckall);
		if(headerCount>count)
		{
			logger.info("check all working properly");
			reportsLogger.log(LogStatus.INFO, "check all working properly");
		}
		else
		{
			throw new Exception("check All is not working properly");
		}
	}

	public void verifyFilter() throws Exception
	{
		verifyDefaultWorkList();
		clickButton(IcnFilter);
		Thread.sleep(5000);
		logger.info("Filter icon is clicked");
		reportsLogger.log(LogStatus.INFO, "Filter icon is clicked");
		txtFilterCategory.isDisplayed();
		logger.info("Category feild is displayed");
		reportsLogger.log(LogStatus.INFO, "Category field is displayed");
		txtFilterChangeNumber.isDisplayed();
		logger.info("Change Number field is displayed");
		reportsLogger.log(LogStatus.INFO, "Change Number field is displayed");
		txtFilterCreatedBy.isDisplayed();
		logger.info("Created By field is displayed");
		reportsLogger.log(LogStatus.INFO, "Created By field is displayed");
		txtFilterPlannedEndDate.isDisplayed();
		logger.info("PlannedEndDate field is displayed");
		reportsLogger.log(LogStatus.INFO, "PlannedEndDate field is displayed");
		txtFilterPlannedStartDate.isDisplayed();
		logger.info("PlannedStartDate field is displayed");
		reportsLogger.log(LogStatus.INFO, "PlannedStartDate field is displayed");
		txtFilterStatus.isDisplayed();
		logger.info("Status field is displayed");
		reportsLogger.log(LogStatus.INFO, "Status field is displayed");
		Thread.sleep(3000);
		List<WebElement>list=driver.findElements(By.xpath(".//div[@id='TicketWorkList']//tbody/tr//makeelement/a"));
		String CRNO=list.get(0).getText();
		txtFilterChangeNumber.sendKeys(CRNO);
		Thread.sleep(10000);
		logger.info("CRNO:"+CRNO);
		reportsLogger.log(LogStatus.INFO, "CRNO:"+CRNO);
		if(txtSearchCount.getText().equalsIgnoreCase("View 1 - 1 of 1"))
		{
			logger.info("Filter is working");
			reportsLogger.log(LogStatus.INFO, "Filter is working");
		}
		else
		{
			logger.info("Data not found ");
			reportsLogger.log(LogStatus.INFO, "Filter is not working ");
			throw  new Exception("Filter is not working");
		}
	}

	public void verifyStatusClosed() throws Exception
	{
		verifyDefaultWorkList();
		Thread.sleep(2000);
		clickButton(IcnFilter);
		Thread.sleep(10000);
		txtFilterStatus.sendKeys("cancel");
		Thread.sleep(8000);
		logger.info(txtSearchCount.getText());
		if(txtSearchCount.getText().equalsIgnoreCase("View 0 - 0 of 0"))
		{
			logger.info("no records found");
			reportsLogger.log(LogStatus.INFO, "no records found");
		}
		else
		{
			logger.info("records found");
			reportsLogger.log(LogStatus.INFO, " records found");
			throw new Exception("Data found , please raised defect");
		}
		Thread.sleep(5000);
		txtFilterStatus.clear();
		Thread.sleep(2000);
		txtFilterStatus.sendKeys("Closed");
		Thread.sleep(8000);
		if(txtSearchCount.getText().equalsIgnoreCase("View 0 - 0 of 0"))
		{
			logger.info("no records found");
			reportsLogger.log(LogStatus.INFO, "no records found");
		}
		else
		{
			logger.info("records found");
			reportsLogger.log(LogStatus.INFO, " records found");
			throw new Exception("Data found , please raised defect");
		}
	}
	public void verifyCreateTicket() throws Exception
	{
		IcnCreateTicket.click();
		Thread.sleep(5000);
		selectItemByText(ddCreateTicStatus, "New", "for status");
		Thread.sleep(2000);
		logger.info("New is selected for status");
		reportsLogger.log(LogStatus.INFO, "New is selected for status");
		selectItemByText(ddCreateTicUrgency, "High", " for Urgency");
		Thread.sleep(2000);
		logger.info("High is selected for Urgency");
		reportsLogger.log(LogStatus.INFO, "High is selected for Urgency");

	}
	public void selectStartDate() throws Exception{
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String today = dateFormat.format(date).split("/")[1];
		if(Integer.parseInt(today) < 10){
			today = String.valueOf(today).substring(1);
		}
		Thread.sleep(3000);
		datePlannedStartDate.click();
		Thread.sleep(10000);								
		WebElement dateWidget = driver.findElement(By.xpath(".//*[@id='Section0_Section4_Section5_DashboardGIElement_GeneralInformationCm']/div[7]/div/div/div[1]/div[1]/table"));
		List<WebElement> columns = dateWidget.findElements(By.tagName("td"));  
		for (WebElement cell : columns){
			if (cell.getText().equals(today)){
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", cell);
				break;
			}
		}
		Thread.sleep(5000);
		txtChangeInfo.click();
		logger.info("Planned Start Date entered Successfully");
		reportsLogger.log(LogStatus.INFO, "Planned Start Date entered Successfully");
	}
	public void selectEndDate() throws Exception{
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String nextDay = dateFormat.format(date. getTime()+(1*24*60*60*1000)).split("/")[1];
		if(Integer.parseInt(nextDay) < 10){
			nextDay = String.valueOf(nextDay).substring(1);
		}
		Thread.sleep(5000);
		datePlannedEndDate.click();
		Thread.sleep(10000);
		driver.findElement(By.xpath(".//*[@id='Section0_Section4_Section5_DashboardGIElement_GeneralInformationCm']/div[8]/div/div/div[1]/div[1]/table/thead/tr[1]/th[3]/span")).click();
		Thread.sleep(5000);
		WebElement dateWidget = driver.findElement(By.xpath(".//*[@id='Section0_Section4_Section5_DashboardGIElement_GeneralInformationCm']/div[8]/div/div/div[1]/div[1]/table/tbody"));
		List<WebElement> columns = dateWidget.findElements(By.tagName("td"));  
		for (WebElement cell : columns){
			if (cell.getText().equals(nextDay)){				
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", cell);
				break;
			}
		}
		Thread.sleep(5000);
		txtChangeInfo.click();
		logger.info("Planned End Date entered Successfully");
		reportsLogger.log(LogStatus.INFO, "Planned End Date entered Successfully");
	}

	public void EnterSummary()throws Exception
	{
		Thread.sleep(3000);
		txtSummary.sendKeys("Testing IGNORE IT");
		Thread.sleep(3000);
		clickButton(btnCreateTicket);
		Thread.sleep(5000);
	}

	public String verifyAlert() throws Exception
	{
		String ticket = "";
		waitForElementToBeVisible(alert, 10);
		String alertStatus = txtStatus.getText().trim();
		System.out.println(alertStatus);
		if(alertStatus.equalsIgnoreCase("Success!")){
			ticket = alertMessage.getText().split(" ")[0].replace("#", "").trim();
			logger.info("Successfully Updated or Created Ticket : "+ ticket);
			reportsLogger.log(LogStatus.INFO, "Successfully  Ticket for "+ ticket);
			return ticket;

		}
		else if(alertMessage.getText().contains("Can't update before 10 minutes")){
			logger.error("Can't update before 10 minutes");
			reportsLogger.log(LogStatus.PASS, "Can't update before 10 minutes of PlannedEndDate");
		}
		else
		{
			logger.error("Failed to Update Ticket");
			reportsLogger.log(LogStatus.FAIL, "Failed to Update Ticket");
			throw new Exception("Failed to Update Ticket");
		}
		return ticket;
	}

}



