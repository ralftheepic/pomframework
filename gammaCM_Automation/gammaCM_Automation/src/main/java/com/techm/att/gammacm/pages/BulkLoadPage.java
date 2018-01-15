package com.techm.att.gammacm.pages;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BulkLoadPage extends HeaderPage{



	private static Logger logger=LoggerFactory.getLogger(BulkLoadPage.class);

	@FindBy(xpath=".//*[@class='container-fluid']/div[2]/div/ul/li[2]")
	public WebElement lnkBulkUpload;

	@FindBy(xpath=".//*[@class='container-fluid']/div[2]/div/ul/li[1]")
	public WebElement lnkDashboard;

	@FindBy(xpath="(//file-uploader/button[2])[1]")
	public WebElement txtFileUpload;

	@FindBy(xpath="//div[@name='SelectFile']//file-uploader/button[1]")
	public WebElement btnupload;

	
	@FindBy(xpath=".//div[@class='refreshsection']/span[2]")
	public WebElement btnRefresh;

	@FindBy(xpath=".//*[@id='jobRunDate']/a")
	public WebElement txtRunDate;

	@FindBy(xpath=".//*[@id='requestorID']/a")
	public WebElement txtRequestorID;

	@FindBy(xpath=".//*[@id='requestorNAME']/a")
	public WebElement txtRequestorName;

	@FindBy(xpath=".//*[@id='fileNAME']/a")
	public WebElement txtfileName;

	@FindBy(xpath=".//*[@id='completionPer']/a")
	public WebElement txtcompletionper;

	@FindBy(xpath=".//*[@id='jobRunId']/a")
	public WebElement txtRunId;

	@FindBy(xpath=".//*[@id='myNavbar']/ul/li[4]/a")
	public WebElement lnkReport;

	@FindBy(id="Section0_Section3_TicketDetails_PlannedStartDate")
	public WebElement PlannedStartDate;

	@FindBy(id="Section0_Section3_TicketDetails_PlannedEndDate")
	public WebElement PlannedEndDate;

	@FindBy(id="Section0_Section3_TicketDetails_RunReport")
	public WebElement btnRunReport;

	@FindBy(xpath="(.//*[@id='button'])[1]")
	public WebElement testbtn;

	@FindBy(xpath=".//*[@id='Section0_Section1_HeaderSection_HeaderA_HeaderWorklist']")
	public WebElement DefaultWorkList ;

	public BulkLoadPage(WebDriver driver, ExtentTest reportsLogger) {
		super(driver, reportsLogger);
		this.driver = driver;
		this.reportsLogger = reportsLogger;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
	}


	public String verifyBulkUpload(String filePath) throws Exception
	{
		clickButton(bulkUpdateCreateTab);
		Thread.sleep(5000);
		lnkBulkUpload.isDisplayed();
		logger.info("Bulkupload link is displayed");
		reportsLogger.log(LogStatus.INFO, "Bulkupload link is displayed");
		lnkDashboard.isDisplayed();
		logger.info("Dashboard link is displayed");
		reportsLogger.log(LogStatus.INFO, "Dashboardlink is displayed");
		clickButton(lnkBulkUpload);
		Thread.sleep(5000);
		txtFileUpload.click();
		Thread.sleep(1000);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(5000);
		StringSelection selection = new StringSelection(System.getProperty("user.dir")+filePath);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, null);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(2000); 
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.delay(2000);
		robot.keyPress(KeyEvent.VK_V);
		robot.delay(2000);
		robot.keyRelease(KeyEvent.VK_V);
		robot.delay(2000);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(2000);
		robot.keyRelease(KeyEvent.VK_ENTER);   
		Thread.sleep(3000);
		clickButton(btnupload);
		waitForElementToBeVisible(alert, 7000);
		alert.isDisplayed();
		String alertStatus = txtStatus.getText().trim();
		if(alertStatus.equalsIgnoreCase("Success!")){
			String ticket = alertMessage.getText().split(" ")[6].replace("#", "").trim();
			logger.info("Bulk Load Create Ticket Process initiated for "+ ticket);
			reportsLogger.log(LogStatus.INFO, "Bulk Load Create Ticket Process initiated for "+ ticket);
			Thread.sleep(2000);
			clickButton(lnkDashboard);
			Thread.sleep(5000);
			clickButton(btnRefresh);
			Thread.sleep(5000);
			txtRunId.click();
			Thread.sleep(5000);		
			return ticket;

		}else{
			logger.error("Failed to Create Ticket");
			reportsLogger.log(LogStatus.FAIL, "Failed to Create Ticket");
			throw new Exception("Failed to Create Ticket");
		}		

	}
	public void verifyPagination(String Ticket) throws Exception
	{
		boolean rowFound=false;
		boolean colMatch=false;
		int next=1;
		logger.info("Ticket to be Searched"+Ticket);
		WebElement nextPage=driver.findElement(By.xpath(".//*[@id='Section0_Section2_Section3_BulkUploadCR1_TicketWorklist']/div[3]/ul[2]/li[8]/a"));
		int headercount=driver.findElements(By.xpath("//div[@id='TicketWorklist']//thead/tr/th")).size();
		for(int headercol=1;headercol<=headercount;headercol++){
			String strColName=driver.findElement(By.xpath("//div[@id='TicketWorklist']//thead/tr/th["+headercol+"]")).getText();
			System.out.println(strColName);
			if(strColName.equalsIgnoreCase("JobRunId")){
				colMatch=true;
				logger.info("Job Run ID column present ");
				break;
			}

		}

		do{
			if(colMatch)
			{
				int rowCount=driver.findElements(By.xpath("//div[@id='TicketWorklist']//tbody/tr")).size();
				for(int row=1;row<=rowCount;row++){
					WebElement CRNO=driver.findElement(By.xpath("//div[@id='TicketWorklist']//tbody/tr["+row+"]//a"));
					String strCRNO=CRNO.getText().trim();
					if(strCRNO.equalsIgnoreCase(Ticket))	
					{
						logger.info("CRNO is present"+Ticket);
						reportsLogger.log(LogStatus.INFO,"CRNO is present "+Ticket );
						rowFound=true;
						CRNO.click();
						break;
					}
				}
				if(rowFound==false)
				{
					Thread.sleep(3000);
					clickButton(nextPage);
					next++;
					logger.info("Clicked on next button");
					reportsLogger.log(LogStatus.INFO, "Clicked on next button");
					Thread.sleep(5000);	
				}	
				else
					break;

			}

		}while(nextPage.isEnabled());
	}

	public void verifyCRCount() throws Exception{

//		WebElement CR=driver.findElement(By.xpath("//div[@id='addedDeviceTableViewTicketPageGammaCm1']//tbody/tr/td[2]"));
		try{
		if(null!=driver.findElement(By.xpath("//div[@id='addedDeviceTableViewTicketPageGammaCm1']//tbody/tr/td[2]")))
		{
			WebElement CR=driver.findElement(By.xpath("//div[@id='addedDeviceTableViewTicketPageGammaCm1']//tbody/tr/td[2]"));
			String strCRNO=CR.getText().trim();
			if(strCRNO.isEmpty() || strCRNO==null)
			{
				logger.info("Cr is not  created");
				reportsLogger.log(LogStatus.INFO, "Cr is not created");
				throw new Exception("Cr not created");
			}
			else
			{
				logger.info("Cr is  created");
				reportsLogger.log(LogStatus.INFO, "Cr is  created");
			}
		}
		}catch(Exception e){
			logger.info("Cr is not  created");
			reportsLogger.log(LogStatus.FAIL, "Cr is not created");
			throw new Exception("Cr not created");
		}
	}
	
	public void verifyCR() throws Exception{

//		WebElement CR=driver.findElement(By.xpath("//div[@id='addedDeviceTableViewTicketPageGammaCm1']//tbody/tr/td[2]"));
		try{
		if(null!=driver.findElement(By.xpath("//div[@id='addedDeviceTableViewTicketPageGammaCm1']//tbody/tr/td[2]")))
		{
			WebElement CR=driver.findElement(By.xpath("//div[@id='addedDeviceTableViewTicketPageGammaCm1']//tbody/tr/td[2]"));
			String strCRNO=CR.getText().trim();
			if(strCRNO.isEmpty() || strCRNO==null)
			{
				logger.info("Cr is not  created");
				reportsLogger.log(LogStatus.INFO, "Cr is not created");
				
			}
			else
			{
				logger.info("Cr is  created");
				reportsLogger.log(LogStatus.FAIL, "Cr is  created");
				throw new Exception("Cr is created");
			}
		}
		}catch(Exception e){
			logger.info("Cr is not  created");
			reportsLogger.log(LogStatus.PASS, "Cr is not created");
		}
	}
	public void verifyReportfields() throws Exception
	{
		//clickButton(driver.findElement(By.xpath(".//*[@id='Section0_Section2_Section3_AssetDetails_search']")));
		Thread.sleep(5000);
		clickButton(lnkDashboard);
		Thread.sleep(3000);
		txtcompletionper.isDisplayed();
		logger.info("completion percentage is displayed");
		reportsLogger.log(LogStatus.INFO,"completion percentage is displayed");
		Thread.sleep(3000);
		txtRunId.isDisplayed();
		logger.info("RunId is displayed");
		reportsLogger.log(LogStatus.INFO, "RunId is displayed");
		Thread.sleep(3000);
		txtRunDate.isDisplayed();
		logger.info("RunDate is displayed");
		reportsLogger.log(LogStatus.INFO, "RunDate is displayed");
		Thread.sleep(3000);
		txtRequestorName.isDisplayed();
		logger.info("RequestorName is displayed");
		reportsLogger.log(LogStatus.INFO, "RequestorName is displayed");
		Thread.sleep(3000);
		txtfileName.isDisplayed();
		logger.info("fileName is displayed");
		reportsLogger.log(LogStatus.INFO, "fileName is displayed");

	}
	public void verifyChangeTicket() throws Exception
	{
		boolean rowFound=false;
		boolean colMatch=false;
		String JOBRunDate,StartDate=null,ChangeTicket = null,endDate=null;
		clickButton(bulkUpdateCreateTab);
		logger.info("Clicked on Bulk Update Tab");
		reportsLogger.log(LogStatus.INFO, "Clicked on Bulk Update Tab");
		Thread.sleep(5000);
		lnkDashboard.isDisplayed();
		Thread.sleep(1000);
		logger.info("link Dashboard is displayed");
		clickButton(lnkDashboard);
		logger.info("link Dashboard is clicked");
		reportsLogger.log(LogStatus.INFO, "Clicked On Dashboard");
		Thread.sleep(2000);
		int rowcount=driver.findElements(By.xpath(".//*[@id='TicketWorklist']//tbody/tr/td[2]/makeelement/a")).size();
		for(int row=1;row<=rowcount;row++)
		{
			txtRunDate.click();
			logger.info("Clicked On Job Run Date");
			reportsLogger.log(LogStatus.INFO, "Clicked On Job Run Id");
			/*Thread.sleep(5000);
			txtRunDate.click();
			logger.info("Clicked On Job Run Date");
			reportsLogger.log(LogStatus.INFO, "Clicked On Job Run Id");*/
			Thread.sleep(2000);
			driver.findElement(By.xpath(".//*[@id='TicketWorklist']//tbody/tr["+row+"]/td[2]/makeelement/a")).click();
			Thread.sleep(2000);
			ChangeTicket=driver.findElement(By.xpath("//div[@id='addedDeviceTableViewTicketPageGammaCm1']//tbody/tr[1]/td[2]/makeelement/span")).getText().trim();
			if(ChangeTicket.isEmpty())
			{
				Thread.sleep(3000);
				clickButton(driver.findElement(By.xpath(".//*[@id='Section0_Section2_Section3_AssetDetails_search']")));
				Thread.sleep(10000);
				continue;	
			}
			JOBRunDate=driver.findElement(By.xpath(".//*[@id='Section0_Section2_Section3_AssetDetails_jobIdDate']")).getText();
			if(JOBRunDate!=null)
			{
				clickButton(driver.findElement(By.xpath(".//*[@id='Section0_Section2_Section3_AssetDetails_search']")));
				Thread.sleep(3000);
				break;
			}
		}	
		clickButton(defaultWorkList);
		logger.info("Clicked On Deafult Worklist");
		reportsLogger.log(LogStatus.INFO, "Clicked On Deafult Worklist");
		Thread.sleep(5000);
		clickButton(btnSearchAll);
		logger.info("Clicked On Search");
		reportsLogger.log(LogStatus.INFO, "Clicked On Search");
		Thread.sleep(3000);
		txtSearchAll.sendKeys(ChangeTicket);
		logger.info("Searched Ticket"+ChangeTicket);
		reportsLogger.log(LogStatus.INFO, "Searched Ticket"+ChangeTicket);
		Thread.sleep(5000);
		rowcount=driver.findElements(By.xpath("//div[@id='TicketWorkList']//thead/tr/th")).size();
		for(int row=1;row<=rowcount;row++)
		{
			String cellValue=driver.findElement(By.xpath("//div[@id='TicketWorkList']//thead/tr/th["+row+"]")).getText().trim();
			if(cellValue.equalsIgnoreCase("Planned Start Date"))
			{
				StartDate=driver.findElement(By.xpath("//div[@id='TicketWorkList']//tbody/tr/td["+row+"]/makeelement/span")).getText();
				logger.info("Start Date :"+StartDate);
				reportsLogger.log(LogStatus.INFO, "Start Date :"+StartDate);
			}
			else if(cellValue.equalsIgnoreCase("Planned End Date"))
			{
				endDate=driver.findElement(By.xpath("//div[@id='TicketWorkList']//tbody/tr/td["+row+"]/makeelement/span")).getText();
				logger.info("End Date :"+endDate);
				reportsLogger.log(LogStatus.INFO, "End Date :"+endDate);
			}
			else
			{
				continue;
			}
		}
		Thread.sleep(2000);
		lnkReport.click();
		logger.info("Clicked on link Report");
		reportsLogger.log(LogStatus.INFO, "Clicked On Link Report");
		Thread.sleep(5000);
		PlannedStartDate.clear();
		Thread.sleep(1000);
		logger.info("planned start date cleared");
		reportsLogger.log(LogStatus.INFO, "planned start date cleared");
		PlannedEndDate.clear();
		Thread.sleep(5000);
		logger.info("planned emd date cleared");
		reportsLogger.log(LogStatus.INFO, "planned end date cleared");
		PlannedStartDate.sendKeys(StartDate);
		reportsLogger.log(LogStatus.INFO, "Entered Start Date"+StartDate);
		Thread.sleep(3000);
		logger.info("Planned Start Date Entered");
		reportsLogger.log(LogStatus.INFO, "Planned Start Date Entered");
		PlannedEndDate.sendKeys(endDate);
		reportsLogger.log(LogStatus.INFO, "Entered End Date"+endDate);
		Thread.sleep(3000);
		Thread.sleep(3000);
		logger.info("Planned End Date Entered");
		reportsLogger.log(LogStatus.INFO, "Planned End Date Entered");
		Thread.sleep(3000);
		btnRunReport.click();
		logger.info("Clicked On Run Report");
		reportsLogger.log(LogStatus.INFO, "Clicked On Run Report");
		Thread.sleep(7000);
		scrollBarDown("1000", 3000);
		int headercount=driver.findElements(By.xpath("//div[@id='searchDeviceReportPageGammaCm']//thead/tr/th")).size();
		int col,next=1;
		WebElement nextPage=driver.findElement(By.xpath(".//*[@id='Section0_Section7_Report_searchDeviceReportPageGammaCm']/div[3]/ul[2]/li[8]/a"));
		for(col=1;col<=headercount;col++)
		{
			String headercol=driver.findElement(By.xpath("//div[@id='searchDeviceReportPageGammaCm']//thead/tr/th["+col+"]/a")).getText().trim();
			if(headercol.equalsIgnoreCase("Change #"))
			{
				colMatch=true;
				break;
			}		

		}
		do{
			if(colMatch)
			{
				int rowCount=driver.findElements(By.xpath("//div[@id='searchDeviceReportPageGammaCm']//tbody/tr/td["+col+"]")).size();
				for(int row=1;row<=rowCount;row++){
					WebElement CRNO=driver.findElement(By.xpath("//div[@id='searchDeviceReportPageGammaCm']//tbody/tr["+row+"]/td["+col+"]/makeelement/span"));
					String strCRNO=CRNO.getText().trim();
					if(strCRNO.equalsIgnoreCase(ChangeTicket))	
					{
						logger.info("CRNO is present"+ChangeTicket);
						reportsLogger.log(LogStatus.INFO,"CRNO is present"+ChangeTicket );
						rowFound=true;
						break;
					}
				}
				if(rowFound==false)
				{
					Thread.sleep(3000);
					clickButton(nextPage);
					next++;
					//System.out.println("Page No "+next);
					Thread.sleep(5000);	
				}	
				else
					break;

			}

		}while(nextPage.isEnabled());

	}
}




