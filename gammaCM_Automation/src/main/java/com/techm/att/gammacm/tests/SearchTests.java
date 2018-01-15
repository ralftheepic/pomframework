package com.techm.att.gammacm.tests;

import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.techm.att.gammacm.base.TestBase;
import com.techm.att.gammacm.pages.SearchPage;

public class SearchTests extends TestBase{

	//Verify user is able to search among the worklist using Search All option
	@DataProvider
	public Object[][] getData_test_Verify_SearchAll_feature() {
		return xlsReader.getTestData("TestData","test_Verify_SearchAll_feature"); 
	}

	@Test(dataProvider = "getData_test_Verify_SearchAll_feature",priority=1)
	public void test_Verify_SearchAll_feature(HashMap<String, String> testdata) throws Exception {	
		startTest("test_Verify_SearchAll_feature");
		validateIfTestCaseAllowed(new Object() {}.getClass().getEnclosingMethod().getName());
		SearchPage searchpage = new SearchPage(getDriver(),getCurrentLogger());
		login(testdata.get("UserName"),testdata.get("Password"));
		searchpage.verifySearchAll();
		searchpage.ColumnFirstRow("Change Number");
		searchpage.rowCount();

	}
	//Validate the Advance search function for an invalid search.
	@DataProvider
	public Object[][] getData_test_Verify_advSearch_feature() {
		return xlsReader.getTestData("TestData","test_Verify_advSearch_feature"); 
	}

	@Test(dataProvider = "getData_test_Verify_advSearch_feature",priority=1)
	public void test_Verify_advSearch_feature(HashMap<String, String> testdata) throws Exception {	
		startTest("test_Verify_advSearch_feature");
		validateIfTestCaseAllowed(new Object() {}.getClass().getEnclosingMethod().getName());
		SearchPage searchpage = new SearchPage(getDriver(),getCurrentLogger());
		login(testdata.get("UserName"),testdata.get("Password"));
		searchpage.validateData();

	}
	//Verify successfully able to search the CR for managed services when enter device and name along with other field
	@DataProvider
	public Object[][] getData_test_Verify_Category_feature() {
		return xlsReader.getTestData("TestData","test_Verify_Category_feature"); 
	}

	@Test(dataProvider = "getData_test_Verify_Category_feature",priority=1)
	public void test_Verify_Category_feature(HashMap<String, String> testdata) throws Exception {	
		startTest("test_Verify_Category_feature");
		validateIfTestCaseAllowed(new Object() {}.getClass().getEnclosingMethod().getName());
		SearchPage searchpage = new SearchPage(getDriver(),getCurrentLogger());
		login(testdata.get("UserName"),testdata.get("Password"));
		searchpage.searchforData();

	}
	
	//Verify successfully able to search the CR for core maintenance tickets when enter device and name along with other field
		@DataProvider
		public Object[][] getData_test_Verify_CoreMaintainance_feature() {
			return xlsReader.getTestData("TestData","test_Verify_CoreMaintainance_feature"); 
		}

		@Test(dataProvider = "getData_test_Verify_CoreMaintainance_feature",priority=1)
		public void test_Verify_CoreMaintainance_feature(HashMap<String, String> testdata) throws Exception {	
			startTest("test_Verify_CoreMaintainance_feature");
			validateIfTestCaseAllowed(new Object() {}.getClass().getEnclosingMethod().getName());
			SearchPage searchpage = new SearchPage(getDriver(),getCurrentLogger());
			login(testdata.get("UserName"),testdata.get("Password"));
			searchpage.verifySearchCoreMaintainance();

		}
}
