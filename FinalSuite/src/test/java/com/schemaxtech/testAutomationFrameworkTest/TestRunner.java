package com.schemaxtech.testAutomationFrameworkTest;

import org.testng.TestNG;

import com.schemaxtech.testAutomationFrameworkTest.*;

public class TestRunner {
	static TestNG testng;

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		testng = new TestNG();
		testng.setTestClasses(new Class[] { Company.class });
		testng.setTestClasses(new Class[] { Plant.class });
		testng.setTestClasses(new Class[] { Cluster.class });
		testng.setTestClasses(new Class[] { Reasons.class });
		testng.setTestClasses(new Class[] { WorkstationT.class });
		testng.run();

	}

}