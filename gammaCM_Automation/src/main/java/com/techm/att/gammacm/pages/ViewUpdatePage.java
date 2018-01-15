package com.techm.att.gammacm.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ViewUpdatePage extends HeaderPage{

	private static Logger logger=LoggerFactory.getLogger(ViewUpdatePage.class);

	@FindBy(xpath=".//*[@class='container-fluid']/div[3]/div/div/div/div[5]/select")
	public WebElement ddCreateTicStatus;

	@FindBy(xpath=".//*[@class='container-fluid']/div[3]/div/div/div/div[6]/select")
	public WebElement ddCreateTicUrgency;

	@FindBy(xpath=".//*[@class='refreshsection']/span/span[4]")
	public WebElement btnSearchAll;

	@FindBy(xpath=".//*[@class='refreshsection']/span/span/input")
	public WebElement txtSearchAll;

	@FindBy(id="Section0_Section4_Section5_DashboardGIElement_GeneralInformationCm_CreateButton")
	public WebElement btnSave;

	@FindBy(xpath="html/body/div[1]/div[1]")
	public WebElement alert;

	@FindBy(xpath="html/body/div[1]/div[1]/Strong")
	public WebElement txtStatus;

	@FindBy(xpath="html/body/div[1]/div[1]/span")
	public WebElement txtMsg;

	@FindBy (xpath=".//*[@id='myNavbar']/ul/li[4]/a")//(id="Section0_Section1_HeaderSection_HeaderA_HeaderReports")
	public WebElement IcnReport;

	@FindBy(id="Section0_Section3_TicketDetails_PlannedStartDate")
	public WebElement dateRepPlannedstart;

	@FindBy(id="Section0_Section3_TicketDetails_PlannedEndDate")
	public WebElement dateRepPlannedend;

	@FindBy(id="Section0_Section3_TicketDetails_RunReport")//(id="Section0_Section4_RunReport")
	public WebElement btnRunReport;

	@FindBy(xpath=".//*[@id='Section0_Section3_TicketDetails']/div[2]/div[6]/div[2]")//(xpath=".//*[@id='Section0_Section4_TicketDetails']/div[2]/div[6]/div[2]")
	public WebElement lblReportDescription;

	@FindBy(id="Section0_Section4_Section5_DashboardGIElement_GeneralInformationCm_Category")
	public WebElement txtCategory;

	@FindBy(id="Section0_Section4_Section5_DashboardGIElement_GeneralInformationCm_RequesterName")
	public WebElement txtCreatedBy;

	@FindBy(xpath=".//*[@id='Section0_Section9_Section10_ViewLogsDashboardElement_LogsAccordionElement']/li[2]")
	public WebElement lnkAddLogs;

	@FindBy(xpath=".//*[@id='Section0_Section9_Section10_ViewLogsDashboardElement_LogsAccordionElement']/li[1]")
	public WebElement lnkViewLogs;

	@FindBy(id="Section0_Section9_Section10_ViewLogsDashboardElement_AddLog_WriteTo")
	public WebElement ddWriteTo;

	@FindBy(id="Section0_Section9_Section10_ViewLogsDashboardElement_AddLog_Comments")
	public WebElement txtComments;

	@FindBy(id="Section0_Section9_Section10_ViewLogsDashboardElement_AddLog_BtnComp_saveLog")
	public WebElement btnSaveLog;

	@FindBy(id="Section0_Section9_Section10_ViewLogsDashboardElement_ViewLogs_LogType")
	public WebElement ddLogType;

	@FindBy(id="Section0_Section9_Section10_ViewLogsDashboardElement_ViewLogs_From")
	public WebElement dateFrom;

	@FindBy(id="Section0_Section9_Section10_ViewLogsDashboardElement_ViewLogs_To")
	public WebElement dateTo;

	@FindBy(id="Section0_Section9_Section10_ViewLogsDashboardElement_ViewLogs_saveLog_dummmy")
	public WebElement btnViewLogs;

	@FindBy(xpath=".//*[@id='Section0_Section3_TicketDetails_RequestorGroup']/div/ul/li[2]/a/input")//(xpath=".//*[@id='Section0_Section4_RequestorGroup']/div/ul/li[2]/a/input")
	public WebElement btnSelectAll;


	public ViewUpdatePage(WebDriver driver, ExtentTest reportsLogger) {
		super(driver, reportsLogger);
		this.driver = driver;
		this.reportsLogger = reportsLogger;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
	}

	public String verifyUpdateTicket(String Ticket) throws Exception
	{
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@class='container-fluid']/div/header/nav/div/div[2]/ul[1]/li[1]/a")).click();
		Thread.sleep(5000);
		clickButton(btnSearchAll);
		Thread.sleep(2000);
		txtSearchAll.sendKeys(Ticket);
		Thread.sleep(5000);
		driver.findElement(By.xpath(".//div[@id='TicketWorklist']//tbody/tr//makeelement/a")).click();
		Thread.sleep(3000);
		selectItemByText(ddCreateTicStatus, "Assigned", "Assigned is selected for status");
		Thread.sleep(2000);
		selectItemByText(ddCreateTicUrgency, "Medium", "Low is selected for Urgency");
		Thread.sleep(2000);
		clickButton(btnSave);
		Thread.sleep(80000);
		waitForElementToBeVisible(alert, 6000);
		String alertMessage = txtStatus.getText().trim();
		if(alertMessage.equalsIgnoreCase("Success!")){
			logger.info("Ticket is updated successfully");
			reportsLogger.log(LogStatus.INFO, "Ticket is updated successfully");
			return Ticket;

		}else{
			logger.error("Failed to update Ticket");
			reportsLogger.log(LogStatus.FAIL, "Failed to update Ticket");
			throw new Exception("Failed to update Ticket");
		}	
	}

	public void verifyReport1Report2Details() throws Exception
	{
		clickButton(IcnReport);
		Thread.sleep(5000);
		logger.info("Clicked on Icn Report");
		reportsLogger.log(LogStatus.INFO, "Clicked on Icn Report");
		dateRepPlannedstart.sendKeys("10/02/2017 08:28");
		Thread.sleep(2000);
		logger.info("planned start date entered");
		reportsLogger.log(LogStatus.INFO, "planned start date entered");
		dateRepPlannedend.sendKeys("10/06/2017 08:28");
		Thread.sleep(2000);
		logger.info("planned end date entered");
		reportsLogger.log(LogStatus.INFO, "planned end date entered");
		clickButton(btnSelectAll);
		Thread.sleep(2000);
		logger.info("Selected all value for requestor group");
		reportsLogger.log(LogStatus.INFO, "Selected all value for requestor group");
		clickButton(btnRunReport);
		Thread.sleep(10000);
		logger.info("Clicked on run Report button");
		reportsLogger.log(LogStatus.INFO, "Clicked on run Report button");
		lblReportDescription.isDisplayed();
		Thread.sleep(1000);
		logger.info("Report 1 is displayed");
		reportsLogger.log(LogStatus.INFO, "Report 1 is displayed");
		scrollBarDown("8000", 2000);
		int colCount=driver.findElements(By.xpath("//*[@id='searchDeviceReportPageGammaCm']//thead/tr/th")).size();
		for(int col=1;col<=colCount;col++){
			String cellValue=driver.findElement(By.xpath("//*[@id='searchDeviceReportPageGammaCm']//thead/tr/th["+col+"]/a")).getText();
			logger.info(cellValue+" "+"is Displayed");
			reportsLogger.log(LogStatus.INFO, cellValue+" "+"is Displayed");
		}
		waitForElementToBeVisible(txtSearchCount, 60);
		logger.info(txtSearchCount.getText());
		reportsLogger.log(LogStatus.INFO, "data populated "+txtSearchCount.getText());	
		if(txtSearchCount.getText().equalsIgnoreCase("View 0 - 0 of 0"))
		{
			logger.info("no data found, for data to be populated please create ticket");
			reportsLogger.log(LogStatus.INFO, "no data found,for data to be populated please create ticket");
		}
		else
		{
			logger.info("Report 2 Details are getting populated");
			reportsLogger.log(LogStatus.INFO, "Report 2 Details are getting populated"); 
		}


	}
	public void verifyalterdate() throws Exception
	{

		defaultWorkList.isDisplayed();
		logger.info("Default WorkList is displayed");
		reportsLogger.log(LogStatus.INFO, "Default WorkList is displayed");
		List<WebElement>list=driver.findElements(By.xpath(".//div[@id='TicketWorkList']//tbody/tr//makeelement/a"));
		list.get(0).click();
		Thread.sleep(5000);
		Assert.assertFalse(txtCategory.isEnabled(),"Category test fields disabled");
		Thread.sleep(2000);
		logger.info("Category fields is disabled");
		reportsLogger.log(LogStatus.INFO, "Category fields is disabled");
		Assert.assertFalse(txtCreatedBy.isEnabled(),"CreatedBy test fields disabled");
		Thread.sleep(2000);
		logger.info("CreatedBy fields is disabled");
		reportsLogger.log(LogStatus.INFO, "CreatedBy fields is disabled");
	}



	public void verifyManagedServices() throws Exception
	{
		boolean flag = false;
		int headerCount = driver.findElements(By.xpath("//div[@id='TicketWorkList']//thead/tr/th")).size();
		int rowCount = driver.findElements(By.xpath("//div[@id='TicketWorkList']//tbody/tr")).size();
		for(int row=1 ; row<= rowCount ; row++){
			for(int col=1 ; col <= headerCount ; col++){
				String cellValue = driver.findElement(By.xpath("//div[@id='TicketWorkList']//tbody/tr["+row+"]/td["+col+"]")).getText().trim();
				if(cellValue.equalsIgnoreCase("Managed Services")){
						Thread.sleep(2000);
						driver.findElement(By.xpath("//div[@id='TicketWorkList']//tbody/tr["+row+"]//makeelement/a")).click();
						flag=true;
						break;								
				}
			}
			if(flag)
				break;
			else
				continue;
		}
		

	}
	public void verifyAddLogs() throws Exception
	{
		boolean flag=false;
		Thread.sleep(2000);
		scrollBarDown("400", 2000);
		clickButton(lnkAddLogs);
		Thread.sleep(2000);
		selectItemByText(ddWriteTo, "External", "External is selected for Logging");
		Thread.sleep(2000);
		txtComments.sendKeys("Testing for gammaCM123");
		Thread.sleep(2000);
		clickButton(btnSaveLog);
		Thread.sleep(20000);
		logger.info("Log added successfully");	
		reportsLogger.log(LogStatus.INFO,"Log added successfully");
		scrollBarDown("400", 2000);
		clickButton(lnkViewLogs);
		Thread.sleep(2000);
		selectItemByText(ddLogType, "External", "External is selected for LogType");
		Thread.sleep(2000);
		dateFrom.sendKeys("10/06/2017 12:00 PM");
		Thread.sleep(5000);
		dateTo.sendKeys("10/09/2018 03:00 PM");
		Thread.sleep(5000);
		clickButton(btnViewLogs);
		scrollBarDown("400", 2000);
		Thread.sleep(10000);
		List<WebElement>comments=driver.findElements(By.xpath(".//*[@id='Section0_Section6_Section7_ViewLogsDashboardElement_ViewLogs_searchdiv']/div/div/div"));
		for(int i=1;i<=comments.size();i++)
		{
			String comment=	driver.findElement(By.xpath(".//*[@id='Section0_Section6_Section7_ViewLogsDashboardElement_ViewLogs_searchdiv']/div/div/div["+i+"]/span")).getText().trim();
			if(comment.equalsIgnoreCase("Testing for gammaCM123"))
			{
				flag=true;
				break;
			}
		}
		if(flag)
		{
			logger.info("View logs successfully");
			reportsLogger.log(LogStatus.INFO, "View logs successfully");
		}else
		{
			logger.info("View logs are not displayed");
			reportsLogger.log(LogStatus.INFO, "View logs are not displayed");
			throw new Exception("View logs are not displayed");
		}

	}

	public void updateTicket() throws Exception {
		WebElement Ticket=driver.findElement(By.xpath(".//*[@id='TicketWorkList']/table/tbody/tr[1]//a"));
		String strTicket=Ticket.getText();
		clickButton(Ticket);
		logger.info("Ticket Clicked"+strTicket);
		reportsLogger.log(LogStatus.INFO,"Ticket Clicked"+strTicket );
		Thread.sleep(5000);
		selectItemByText(ddCreateTicUrgency, "Medium", "Low is selected for Urgency");
		Thread.sleep(2000);
		logger.info("Medium is selected for Urgency");
		reportsLogger.log(LogStatus.INFO,"Medium is selected for Urgency" );
		clickButton(btnSave);
		Thread.sleep(5000);
		logger.info("Clicked on button Save");
		reportsLogger.log(LogStatus.INFO, "Clicked on button Save");
	}

}
