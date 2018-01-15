package com.techm.att.gammacm.tests;

import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.techm.att.gammacm.base.TestBase;
import com.techm.att.gammacm.pages.WorkListPage;

public class WorkListTests extends TestBase{

	//Verify default column displayed on the Worklist page
	//Verify user successfully landed to the user worklist page
	//Verify error message when No Records found after successful logon
	@DataProvider
	public Object[][] getData_test_Verify_WorkList_Page() {
		return xlsReader.getTestData("TestData","test_Verify_WorkList_Page"); 
	}

	@Test(dataProvider = "getData_test_Verify_WorkList_Page",priority=1)
	public void test_Verify_WorkList_Page(HashMap<String, String> testdata) throws Exception {	
		startTest("test_Verify_WorkList_Page");
		validateIfTestCaseAllowed(new Object() {}.getClass().getEnclosingMethod().getName());
		WorkListPage workpage = new WorkListPage(getDriver(),getCurrentLogger());
		login(testdata.get("UserName"),testdata.get("Password"));
		workpage.verifyWorklistPage();

	}
	//Verify user is having facility to change the default column
	@DataProvider
	public Object[][] getData_test_Verify_WorkList_feature() {
		return xlsReader.getTestData("TestData","test_Verify_WorkList_feature"); 
	}

	@Test(dataProvider = "getData_test_Verify_WorkList_feature",priority=1)
	public void test_Verify_WorkList_feature(HashMap<String, String> testdata) throws Exception {	
		startTest("test_Verify_WorkList_feature");
		validateIfTestCaseAllowed(new Object() {}.getClass().getEnclosingMethod().getName());
		WorkListPage workpage = new WorkListPage(getDriver(),getCurrentLogger());
		login(testdata.get("UserName"),testdata.get("Password"));
		workpage.verifyColumnlist();

	}
	
	// Verify user is able to search the CR records based on the column search
		@DataProvider
		public Object[][] getData_test_Verify_Filter_feature() {
			return xlsReader.getTestData("TestData","test_Verify_Filter_feature"); 
		}

		@Test(dataProvider = "getData_test_Verify_Filter_feature",priority=1)
		public void test_Verify_Filter_feature(HashMap<String, String> testdata) throws Exception {	
			startTest("test_Verify_Filter_feature");
			validateIfTestCaseAllowed(new Object() {}.getClass().getEnclosingMethod().getName());
			WorkListPage workpage = new WorkListPage(getDriver(),getCurrentLogger());
			login(testdata.get("UserName"),testdata.get("Password"));
			workpage.verifyFilter();

		}
		//Verify closed or cancelled records are not getting displayed on the worklist
		@DataProvider
		public Object[][] getData_test_Verify_statusClosed_feature() {
			return xlsReader.getTestData("TestData","test_Verify_statusClosed_feature"); 
		}

		@Test(dataProvider = "getData_test_Verify_statusClosed_feature",priority=1)
		public void test_Verify_statusClosed_feature(HashMap<String, String> testdata) throws Exception {	
			startTest("test_Verify_statusClosed_feature");
			validateIfTestCaseAllowed(new Object() {}.getClass().getEnclosingMethod().getName());
			WorkListPage workpage = new WorkListPage(getDriver(),getCurrentLogger());
			login(testdata.get("UserName"),testdata.get("Password"));
			workpage.verifyStatusClosed();

		}
		
		@DataProvider
		public Object[][] getData_test_Verify_createTicket_feature() {
			return xlsReader.getTestData("TestData","test_Verify_createTicket_feature"); 
		}

		@Test(dataProvider = "getData_test_Verify_createTicket_feature",priority=1)
		public void test_Verify_createTicket_feature(HashMap<String, String> testdata) throws Exception {	
			startTest("test_Verify_createTicket_feature");
			validateIfTestCaseAllowed(new Object() {}.getClass().getEnclosingMethod().getName());
			WorkListPage workpage = new WorkListPage(getDriver(),getCurrentLogger());
			login(testdata.get("UserName"),testdata.get("Password"));
			workpage.verifyCreateTicket();
			workpage.selectStartDate();
			workpage.selectEndDate();
			workpage.EnterSummary();
			workpage.verifyAlert();
		}
}
