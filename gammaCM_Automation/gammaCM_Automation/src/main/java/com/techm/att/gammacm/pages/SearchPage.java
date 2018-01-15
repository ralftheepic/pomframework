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

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SearchPage extends HeaderPage{

	private static Logger logger= LoggerFactory.getLogger(SearchPage.class);
	

	
	@FindBy(xpath=".//*[@class='table_paging_footer']/span")
	public WebElement txtSearchCount;
	
	@FindBy(xpath=".//*[@class='navbar navbar-inverse']/div/ul/li[2]/a")
	public WebElement btnAdvSearch;

	@FindBy(id="Section0_Section2_AdvancedSearchSlider_TicketNumberInputTxt")
	public WebElement txtadvCR;

	@FindBy(id="Section0_Section2_AdvancedSearchSlider_BtnComp_search")
	public WebElement btnSearch;
	
	@FindBy(xpath=".//*[@id='Section0_Section2_AdvancedSearchSlider_category']/div/button")
	public WebElement btnCategory;

	@FindBy(xpath=".//*[@id='Section0_Section2_AdvancedSearchSlider_category']/div/ul/li[8]/a/label/input")
	public WebElement btnManagedServices;
	
	@FindBy(xpath=".//*[@id='Section0_Section2_AdvancedSearchSlider_category']/div/ul/li[4]/a/label/input")
	public WebElement btnCategoryAGN;

	@FindBy(xpath=".//*[@id='Section0_Section2_AdvancedSearchSlider_category']/div/ul/li[5]/a/label/input")
	public WebElement btnCategoryBIS;

	@FindBy(xpath=".//*[@id='Section0_Section2_AdvancedSearchSlider_category']/div/ul/li[6]/a/label/input")
	public WebElement btnCategoryFRAME;

	@FindBy(xpath=".//*[@id='Section0_Section2_AdvancedSearchSlider_category']/div/ul/li[7]/a/label/input")
	public WebElement btnCategoryIPAG;
	
	@FindBy(xpath=".//*[@id='Section0_Section2_AdvancedSearchSlider_TicketState']/div/button")
	public WebElement btnStatus;
	
	@FindBy(xpath=".//*[@id='Section0_Section2_AdvancedSearchSlider_TicketState']/div/ul/li[4]/a/label/input")
	public WebElement btnStatusAssigned;
	
	@FindBy(xpath=".//*[@id='Section0_Section2_AdvancedSearchSlider_TicketType']/div/button")
	public WebElement btnUrgency;
	
	@FindBy(xpath=".//*[@id='Section0_Section2_AdvancedSearchSlider_TicketType']/div/ul/li[2]/a/input")
	public WebElement btnUrgencySelectAll;
	
	@FindBy(name="Section0_Section2_AdvancedSearchSlider_PlannedDate_PlannedStartDate")
	public WebElement datePlannedStart;
	
	@FindBy(name="Section0_Section2_AdvancedSearchSlider_PlannedDate_PlannedEndDate")
	public WebElement datePlannedEnd;

	
	public SearchPage(WebDriver driver, ExtentTest reportsLogger) {
		super(driver, reportsLogger);
		this.driver = driver;
		this.reportsLogger = reportsLogger;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
	}

	public void verifySearchAll() throws Exception
	{
		defaultWorkList.isDisplayed();
		logger.info("Default worklist is displayed");
		reportsLogger.log(LogStatus.INFO, "Default worklist is displayed");
		btnSearchAll.isDisplayed();
		logger.info("Search button displayed");
		reportsLogger.log(LogStatus.INFO, "Search button is displayed");
		clickButton(btnSearchAll);
		Thread.sleep(2000);
		
	}
	public void ColumnFirstRow(String columnName)
	{
		int rowcount=driver.findElements(By.xpath(".//div[@id='TicketWorkList']//thead/tr/th")).size();
		for(int row=1;row<=rowcount;row++)
		{
			String cellValue=driver.findElement(By.xpath(".//div[@id='TicketWorkList']//thead/tr/th["+row+"]")).getText();
			if(cellValue.equalsIgnoreCase(columnName))
			{
				if(columnName.equalsIgnoreCase("Change Number"))
				{
				String CRNo=driver.findElement(By.xpath(".//div[@id='TicketWorkList']//tbody/tr[1]/td["+row+"]/makeelement/a")).getText().trim();
				logger.info(CRNo+" is selected for"+columnName);
				reportsLogger.log(LogStatus.INFO, CRNo+" is selected for"+columnName);
				txtSearchAll.sendKeys(CRNo);
				break;
				}
				else
				{
					String rowValue=driver.findElement(By.xpath(".//div[@id='TicketWorkList']//tbody/tr[1]/td["+row+"]/makeelement/span")).getText().trim();
					logger.info(rowValue+" is selected for"+columnName);
					reportsLogger.log(LogStatus.INFO, rowValue+" is selected for"+columnName);
					break;
				}
			}
		}
		
	}
	public void rowCount() throws Exception
	{
		Thread.sleep(3000);
		if(txtSearchCount.getText().equalsIgnoreCase("View 1 - 1 of 1"))
		{
			logger.info("Data found successfully");
			reportsLogger.log(LogStatus.INFO, "Data found successfully");
		}
		else
		{
			logger.info("Data not found ");
			reportsLogger.log(LogStatus.INFO, "Data not found ");
			throw  new Exception("no data found");
		}
	}
	public void validateData() throws Exception
	{
		clickButton(btnAdvSearch);
		Thread.sleep(2000);
		txtadvCR.sendKeys("54563431");
		Thread.sleep(2000);
		clickButton(btnSearch);
		Thread.sleep(10000);
		alert.isDisplayed();
		logger.info("Alert is displayed");
		reportsLogger.log(LogStatus.INFO, "Alert is displayed");
		String alertMessage = driver.findElement(By.xpath("html/body/div[1]/div[1]/strong")).getText().trim();
		if(alertMessage.equalsIgnoreCase("Error!")){
			logger.info("please check the ticketno");
			reportsLogger.log(LogStatus.INFO, "please check the ticketno");


		}else{
			logger.error("data found");
			reportsLogger.log(LogStatus.FAIL, "data found");
			throw new Exception("data found");
		}	

	}


	public void searchforData() throws Exception
	{
		clickButton(btnAdvSearch);
		Thread.sleep(3000);
		clickButton(btnCategory);
		Thread.sleep(3000);
		clickButton(btnManagedServices);
		Thread.sleep(3000);
		clickButton(btnSearch);
		Thread.sleep(10000);
		scrollBarDown("200", 2000);
		int headercount=driver.findElements(By.xpath("//div[@id='TicketWorkList']//thead/tr/th")).size();
		for(int col=1;col<=headercount;col++)
		{
			String headercol=driver.findElement(By.xpath("//div[@id='TicketWorkList']//thead/tr/th["+col+"]/a")).getText().trim();
			WebElement headercolumn=driver.findElement(By.xpath("//div[@id='TicketWorkList']//thead/tr/th["+col+"]/a"));
			if(headercol.equalsIgnoreCase("Category"))
			{
				scrollToView(headercolumn);
				for(int row=1;row<=10;row++)
				{
					String category=driver.findElement(By.xpath("//div[@id='TicketWorkList']//tbody/tr["+row+"]/td["+col+"]/makeelement/span")).getText().trim();
					if(category.equalsIgnoreCase("Managed Services"))
					{
						logger.info("data is validated for Managed Services ");
						reportsLogger.log(LogStatus.INFO, "data is validated for Managed Services ");
						
					}
					else
					{
						logger.info("Search is not working as expected");
						reportsLogger.log(LogStatus.ERROR, "Search is not working as expected");
						throw new Exception("Search is not working as expected");
					}
					Thread.sleep(2000);
					
				}
				break;
			}
			
		}

	}
	public void verifySearchCoreMaintainance() throws Exception
	{
		clickButton(btnAdvSearch);
		Thread.sleep(2000);
		logger.info("Clicked on Advance Search Button");
		reportsLogger.log(LogStatus.INFO, "Clicked on Advanced Search Button");
		clickButton(btnCategory);
		Thread.sleep(2000);
		clickButton(btnCategoryAGN);
		Thread.sleep(2000);
		logger.info("AGN is selected for Category");
		reportsLogger.log(LogStatus.INFO, "AGN is selected for Category");
		clickButton(btnCategoryBIS);
		Thread.sleep(2000);
		logger.info("BIS is selected for Category");
		reportsLogger.log(LogStatus.INFO, "BIS is selected for Category");
		clickButton(btnCategoryFRAME);
		Thread.sleep(2000);
		logger.info("FRAME is selected for Category");
		reportsLogger.log(LogStatus.INFO, "FRAME is selected for Category");
		clickButton(btnCategoryIPAG);
		Thread.sleep(2000);
		logger.info("IPAG is selected for Category");
		reportsLogger.log(LogStatus.INFO, "IPAG is selected for Category");
		clickButton(btnStatus);
		Thread.sleep(2000);
		clickButton(btnStatusAssigned);
		Thread.sleep(2000);
		logger.info("Assigned is selected for Status");
		reportsLogger.log(LogStatus.INFO, "Assigned is selected for Status");
		clickButton(btnUrgency);
		Thread.sleep(2000);
		clickButton(btnUrgencySelectAll);
		Thread.sleep(2000);
		logger.info("High is selected for Urgency");
		reportsLogger.log(LogStatus.INFO, "High is selected for Urgency");
		clickButton(btnSearch);
		Thread.sleep(20000);
		if(txtSearchCount.getText().equalsIgnoreCase("View 0 - 0 of 0"))
		{
			logger.info("no data found, Please check the filter data  ");
			reportsLogger.log(LogStatus.ERROR, "no data found, Please check the filter data ");
		}
		else
		{
			logger.info("Report Details are getting populated");
			reportsLogger.log(LogStatus.INFO, "Report Details are getting populated");
		}
		
		
		
	}
}
