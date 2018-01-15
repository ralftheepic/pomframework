package com.techm.att.gammacm.utilities;

import java.util.ArrayList;
import java.util.List;
import org.testng.TestNG;


public class RunTestNG {

	public static void main(String[] args) {
		TestNG runner=new TestNG();
		List<String> suitefiles=new ArrayList<String>();
		suitefiles.add("D:\\VC00478545\\ATT\\eclipseWorkspace_97\\gammaCM_Automation\\testng.xml");
		runner.setTestSuites(suitefiles);
		runner.run();
	}
}