package com.techm.att.gammacm.tests;

import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.techm.att.gammacm.base.TestBase;
import com.techm.att.gammacm.pages.ViewUpdatePage;
import com.techm.att.gammacm.pages.WorkListPage;

public class ViewUpdateTests extends TestBase{

	//	Verify success message when application successfully update the ticket
	//	Verify failure message when application not able update the ticket

	@DataProvider
	public Object[][] getData_test_Verify_updateTicket_feature() {
		return xlsReader.getTestData("TestData","test_Verify_updateTicket_feature"); 
	}

	@Test(dataProvider = "getData_test_Verify_updateTicket_feature",priority=1)
	public void test_Verify_updateTicket_feature(HashMap<String, String> testdata) throws Exception {	
		startTest("test_Verify_updateTicket_feature");
		validateIfTestCaseAllowed(new Object() {}.getClass().getEnclosingMethod().getName());
		ViewUpdatePage viewpage = new ViewUpdatePage(getDriver(),getCurrentLogger());
		WorkListPage workpage = new WorkListPage(getDriver(),getCurrentLogger());
		login(testdata.get("UserName"),testdata.get("Password"));
		viewpage.updateTicket();
		workpage.verifyAlert();
		//viewpage.verifyUpdateTicket(Ticket);

	}
	//Verify user is able to view Ticket and Asset associated with group by start date and end date and comment:Report 1
	//Verify user is  able to view report of number ticket created group by start date and end date and comment:Report 2
	@DataProvider
	public Object[][] getData_test_Verify_Report1Report2Detail_feature() {
		return xlsReader.getTestData("TestData","test_Verify_Report1Report2Detail_feature"); 
	}

	@Test(dataProvider = "getData_test_Verify_Report1Report2Detail_feature",priority=1)
	public void test_Verify_Report1Report2Detail_feature(HashMap<String, String> testdata) throws Exception {	
		startTest("test_Verify_Report1Report2Detail_feature");
		validateIfTestCaseAllowed(new Object() {}.getClass().getEnclosingMethod().getName());
		ViewUpdatePage viewpage = new ViewUpdatePage(getDriver(),getCurrentLogger());
		login(testdata.get("UserName"),testdata.get("Password"));
		viewpage.verifyReport1Report2Details();

	}

	//Verify user is not allowed to update the CreatedBy, CreatedDate, category field
	@DataProvider
	public Object[][] getData_test_Verify_alterfields_feature() {
		return xlsReader.getTestData("TestData","test_Verify_alterfields_feature"); 
	}

	@Test(dataProvider = "getData_test_Verify_alterfields_feature",priority=1)
	public void test_Verify_alterfields_feature(HashMap<String, String> testdata) throws Exception {	
		startTest("test_Verify_alterfields_feature");
		validateIfTestCaseAllowed(new Object() {}.getClass().getEnclosingMethod().getName());
		ViewUpdatePage viewpage = new ViewUpdatePage(getDriver(),getCurrentLogger());
		login(testdata.get("UserName"),testdata.get("Password"));
		viewpage.verifyalterdate();

	}


	//Verify user successfully able to add the logs to the existing ticket
	//Verify each log when user added the logs in the Add logs section
	@DataProvider
	public Object[][] getData_test_Verify_AddLogs_feature() {
		return xlsReader.getTestData("TestData","test_Verify_AddLogs_feature"); 
	}

	@Test(dataProvider = "getData_test_Verify_AddLogs_feature",priority=1)
	public void test_Verify_AddLogs_feature(HashMap<String, String> testdata) throws Exception {	
		startTest("test_Verify_AddLogs_feature");
		validateIfTestCaseAllowed(new Object() {}.getClass().getEnclosingMethod().getName());
		ViewUpdatePage viewpage = new ViewUpdatePage(getDriver(),getCurrentLogger());
		login(testdata.get("UserName"),testdata.get("Password"));
		viewpage.verifyManagedServices();
		viewpage.verifyAddLogs();

	}


}
