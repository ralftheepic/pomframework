package com.techm.att.gammacm.tests;

import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.techm.att.gammacm.base.TestBase;
import com.techm.att.gammacm.pages.DigicelWorklist;

public class DigicelWorklistTest extends TestBase{

	

	//Verify user successfully able to create the Bulk CR when user filled all the mandatory field
	@DataProvider
	public Object[][] getData_test_Verify_DigicelLogin() {
		return xlsReader.getTestData("TestData","test_Verify_DigicelLogin"); 
	}

	@Test(dataProvider = "getData_test_Verify_DigicelLogin",priority=1)
	public void test_Verify_DigicelLogin(HashMap<String, String> testdata) throws Exception {	
		startTest("test_Verify_DigicelLogin");
		validateIfTestCaseAllowed(new Object() {}.getClass().getEnclosingMethod().getName());
		DigicelWorklist dw = new DigicelWorklist(getDriver(), getCurrentLogger());
		dw.login();
		dw.enterID("gs00478160@techmahindra.com");
		dw.enterPassword("12345678");
		dw.clickonLogin();
		
	}
}
