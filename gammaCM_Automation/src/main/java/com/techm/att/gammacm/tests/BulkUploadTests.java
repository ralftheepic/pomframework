package com.techm.att.gammacm.tests;

import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.techm.att.gammacm.base.TestBase;
import com.techm.att.gammacm.pages.BulkLoadPage;

public class BulkUploadTests extends TestBase{

	//Verify user successfully able to create the Bulk CR when user filled all the mandatory field
	@DataProvider
	public Object[][] getData_test_Verify_BulkCreation_Page() {
		return xlsReader.getTestData("TestData","test_Verify_BulkCreation_Page"); 
	}

	@Test(dataProvider = "getData_test_Verify_BulkCreation_Page",priority=1)
	public void test_Verify_BulkCreation_Page(HashMap<String, String> testdata) throws Exception {	
		startTest("test_Verify_BulkCreation_Page");
		validateIfTestCaseAllowed(new Object() {}.getClass().getEnclosingMethod().getName());
		BulkLoadPage createpage = new BulkLoadPage(getDriver(),getCurrentLogger());
		login(testdata.get("UserName"),testdata.get("Password"));
		String Ticket=createpage.verifyBulkUpload("\\Bulkupload\\GammaCM_BulkTemplate_ManageServices.xlsx");
		createpage.verifyPagination(Ticket);
		createpage.verifyCRCount();

	}
	//Verify user successfully able to create the Bulk CR when user keep optional field blank
	@DataProvider
	public Object[][] getData_test_Verify_mandatoryfields_Page() {
		return xlsReader.getTestData("TestData","test_Verify_mandatoryfields_Page"); 
	}

	@Test(dataProvider = "getData_test_Verify_mandatoryfields_Page",priority=1)
	public void test_Verify_mandatoryfields_Page(HashMap<String, String> testdata) throws Exception {	
		startTest("test_Verify_mandatoryfields_Page");
		validateIfTestCaseAllowed(new Object() {}.getClass().getEnclosingMethod().getName());
		BulkLoadPage createpage = new BulkLoadPage(getDriver(),getCurrentLogger());
		login(testdata.get("UserName"),testdata.get("Password"));
		String Ticket=createpage.verifyBulkUpload("\\Bulkupload\\ValidateOptionalFields.xlsx");
		createpage.verifyPagination(Ticket);
		createpage.verifyCRCount();
	}

	//Verify user not able to create the Bulk CR when user keep all the mandatory field blank
	@DataProvider
	public Object[][] getData_test_Verify_RemoveMandatoryFields_Report_Page() {
		return xlsReader.getTestData("TestData","test_Verify_RemoveMandatoryFields_Report_Page"); 
	}

	@Test(dataProvider = "getData_test_Verify_RemoveMandatoryFields_Report_Page",priority=1)
	public void test_Verify_RemoveMandatoryFields_Report_Page(HashMap<String, String> testdata) throws Exception {	
		startTest("test_Verify_RemoveMandatoryFields_Report_Page");
		validateIfTestCaseAllowed(new Object() {}.getClass().getEnclosingMethod().getName());
		BulkLoadPage createpage = new BulkLoadPage(getDriver(),getCurrentLogger());
		login(testdata.get("UserName"),testdata.get("Password"));
		String Ticket=createpage.verifyBulkUpload("\\Bulkupload\\RemoveAllfields.xlsx");
		createpage.verifyPagination(Ticket);
		createpage.verifyCR();
	}
	//Verify message display in Bulk Load Report Details when user enter the Planned Start Date & Time greater than Planned End Date & Time
	@DataProvider
	public Object[][] getData_test_Verify_date_Report_Page() {
		return xlsReader.getTestData("TestData","test_Verify_date_Report_Page"); 
	}

	@Test(dataProvider = "getData_test_Verify_date_Report_Page",priority=1)
	public void test_Verify_date_Report_Page(HashMap<String, String> testdata) throws Exception {	
		startTest("test_Verify_date_Report_Page");
		validateIfTestCaseAllowed(new Object() {}.getClass().getEnclosingMethod().getName());
		BulkLoadPage createpage = new BulkLoadPage(getDriver(),getCurrentLogger());
		login(testdata.get("UserName"),testdata.get("Password"));
		String Ticket=createpage.verifyBulkUpload("\\Bulkupload\\VerifyPastStartDate.xlsx");
		createpage.verifyPagination(Ticket);
		createpage.verifyCR();
	}
	
	//Verify Bulk load Dashboard after uplaoding the Bulk CR
	@DataProvider
	public Object[][] getData_test_Create_BulkID_Verify_reportsfields_Page() {
		return xlsReader.getTestData("TestData","test_Create_BulkID_Verify_reportsfields_Page"); 
	}

	@Test(dataProvider = "getData_test_Create_BulkID_Verify_reportsfields_Page",priority=1)
	public void test_Create_BulkID_Verify_reportsfields_Page(HashMap<String, String> testdata) throws Exception {	
		startTest("test_Create_BulkID_Verify_reportsfields_Page");
		validateIfTestCaseAllowed(new Object() {}.getClass().getEnclosingMethod().getName());
		BulkLoadPage createpage = new BulkLoadPage(getDriver(),getCurrentLogger());
		login(testdata.get("UserName"),testdata.get("Password"));
		createpage.verifyBulkUpload("\\Bulkupload\\GammaCM_BulkTemplate_ManageServices.xlsx");
		createpage.verifyReportfields();
		//createpage.verifyCRCount();
	}



	//37:Verify user is able to view report of number ticket created via bulk load and created manually based on start date and end date.
	@DataProvider
	public Object[][] getData_test_Verify_ChangeTicket_OnReport_Page() {
		return xlsReader.getTestData("TestData","test_Verify_ChangeTicket_OnReport_Page"); 
	}

	@Test(dataProvider = "getData_test_Verify_ChangeTicket_OnReport_Page",priority=1)
	public void test_Verify_ChangeTicket_OnReport_Page(HashMap<String, String> testdata) throws Exception {	
		startTest("test_Verify_ChangeTicket_OnReport_Page");
		validateIfTestCaseAllowed(new Object() {}.getClass().getEnclosingMethod().getName());
		BulkLoadPage createpage = new BulkLoadPage(getDriver(),getCurrentLogger());
		login(testdata.get("UserName"),testdata.get("Password"));
		createpage.verifyChangeTicket();
	}


}
