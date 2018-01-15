package com.techm.att.gammacm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.techm.att.gammacm.base.CommonTestActions;


public class HeaderPage extends CommonTestActions  {


	@FindBy(id = "Section0_Section1_HeaderSection_HeaderA_HeaderCreate")
	public WebElement createTicketTab;

	@FindBy(xpath = "//*[@id='myNavbar']/ul[1]/li[2]")
	public WebElement viewUpdateTicketTab;

	@FindBy(id = "Section0_Section1_HeaderSection_HeaderA_HeaderWorklist")
	public WebElement worklistTab;

	@FindBy(xpath = ".//*[@id='myNavbar']/ul[1]/li[1]/a")
	public WebElement defaultWorkList;

	@FindBy(xpath=".//*[@id='myNavbar']/ul[1]/li[4]/a")//(id = "Section0_Section1_HeaderSection_HeaderA_HeaderReports")
	public WebElement reportsTab;

	@FindBy(xpath=".//*[@id='myNavbar']/ul/li[5]/a")//(id = "Section0_Section1_HeaderSection_HeaderA_HeaderWorklistBulkUpload")
	public WebElement bulkUpdateCreateTab;

	@FindBy(xpath = "html/body/div[1]/div[1]")
	public WebElement alert;

	@FindBy(xpath = "html/body/div[1]/div[1]/strong")
	public WebElement txtStatus;

	@FindBy(xpath = "html/body/div[1]/div[1]/span")
	public WebElement alertMessage;

	@FindBy(xpath=".//*[@class='table_paging_footer']/span")
	public WebElement txtSearchCount;

	@FindBy(xpath=".//*[@id='Section0_Section2_TicketWorkList']/div[1]/div/span/span[4]")
	public WebElement btnSearchAll;

	@FindBy(xpath=".//*[@id='Section0_Section2_TicketWorkList']/div[1]/div/span/span[3]/input")
	public WebElement txtSearchAll;


	public HeaderPage(WebDriver driver,ExtentTest reportsLogger) {
		this.driver = driver;
		this.reportsLogger = reportsLogger;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
	}


}
