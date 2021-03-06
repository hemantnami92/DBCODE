package com.schemaxtech.testAutomationFrameworkTest;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.schemaxtech.testAutomationFramework.TestAutomationUtil;
import com.schemaxtech.testAutomationFramework.TestCaseInfo;

import io.restassured.response.ResponseBody;

public class Deactivates {

	/**
	 * It generates a two dimensional array object for all the test methods
	 * 
	 * @param method test name
	 * @return two dimensional array object
	 * @throws Exception
	 */

	Map<String, JSONObject> responsebody = new LinkedHashMap<String, JSONObject>();

	@DataProvider(name = "sampleDataProvider")
	public Object[][] sampleDataProvider(Method method) throws Exception {
		Map<String, String> mapForPathProvider = new HashMap<String, String>();
		mapForPathProvider = TestAutomationUtil.generateFileNames(method.getName());
		TestCaseInfo testcaseinfo = TestAutomationUtil.updatJsonWithTestDataMaster(responsebody , mapForPathProvider.get("inputcsvpathkey"),
						mapForPathProvider.get("mapperpathkey"), mapForPathProvider.get("resultcsvpathkey"),
						mapForPathProvider.get("expectedmapperpathkey"));

		Object[][] data = new Object[testcaseinfo.requestJsonObject.size()][4];
		int i = 0;
		for (String testcase : testcaseinfo.requestJsonObject.keySet()) {

			data[i][0] = testcase;
			data[i][1] = testcaseinfo.requestJsonObject.get(testcase);
			data[i][2] = testcaseinfo.expectedAttributeValues.get(testcase);
			data[i][3] = testcaseinfo.responseAttributePaths;
			i++;
		}

		return data;
	}
	@DataProvider(name="sampleDataProviderget")
	public  Object[][] sampleDataProvider1(Method method) throws Exception {
		
		Map<String,String> mapForPathProvider=new HashMap<String,String>();
		mapForPathProvider=TestAutomationUtil.generateFileNames(method.getName());
		TestCaseInfo testcaseinfo=TestAutomationUtil.updatJsonWithTestDataMaster(mapForPathProvider.get("inputJsonpathkey"), mapForPathProvider.get("inputcsvpathkey"),  mapForPathProvider.get("mapperpathkey"), mapForPathProvider.get("resultcsvpathkey"),  mapForPathProvider.get("expectedmapperpathkey"));
		Object[][] data = new Object[testcaseinfo.requestJsonObject.size()][4];
		int i=0;
		for (String testcase: testcaseinfo.requestJsonObject.keySet())
		{
			
			data[i][0] = testcase;
			data[i][1] = testcaseinfo.requestJsonObject.get(testcase);
			data[i][2] = testcaseinfo.expectedAttributeValues.get(testcase);
			data[i][3] = testcaseinfo.responseAttributePaths;
			i++;
		}
		
		return data;
	}

	/**
	 * Sample Test method
	 * 
	 * @param testCase
	 * @param requestJsonObject
	 * @param expectedAttributeValues
	 * @param responseAttributePaths
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@Test(dataProvider = "sampleDataProviderget", priority=3)
	public void deaCompany(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
			Map<String, String> responseAttributePaths) throws Exception {

		ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("PMS"),
				"company/deActivateCompany", requestJsonObject);

		TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
	}

	@SuppressWarnings("rawtypes")
	@Test(dataProvider = "sampleDataProviderget", priority=3)
	public void deacCluster(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
			Map<String, String> responseAttributePaths) throws Exception {

		ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("PMS"), "cluster/getCluster",
				requestJsonObject);

		TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
	}
	@SuppressWarnings("rawtypes")
	@Test(dataProvider = "sampleDataProviderget", priority = 4)
	public void DeactivateDepartment(String testCase, JSONObject requestJsonObject,
			Map<String, Object> expectedAttributeValues, Map<String, String> responseAttributePaths) throws Exception {

		ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("PMS"),
				"departments/deActivateDepartment", requestJsonObject);

		TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
	}
	
	@SuppressWarnings("rawtypes")
	@Test(dataProvider = "sampleDataProviderget", priority=3)
	public void deacPlant(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
			Map<String, String> responseAttributePaths) throws Exception {

		ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("PMS"), "plant/deActivatePlant",
				requestJsonObject);

		TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
	}
	
	
	@Test(dataProvider = "sampleDataProviderget", priority = 3)
	public void deactRe(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
			Map<String, String> responseAttributePaths) throws Exception {

		ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("PMS"),
				"reasons/deactivateReason", requestJsonObject);

		TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
	}
	
	@SuppressWarnings("rawtypes")
	@Test(dataProvider = "sampleDataProviderget", priority = 4)
	public void DeactivateSection(String testCase, JSONObject requestJsonObject,
			Map<String, Object> expectedAttributeValues, Map<String, String> responseAttributePaths) throws Exception {

		ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("PMS"),
				"section/deActivateSection", requestJsonObject);

		TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
	}
	@SuppressWarnings("rawtypes")
	@Test(dataProvider = "sampleDataProvider", priority = 4)
	public void deActivateWorkstation(String testCase, JSONObject requestJsonObject,
			Map<String, Object> expectedAttributeValues, Map<String, String> responseAttributePaths) throws Exception {

		ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("PMS"),
				"workstation/deActivateWorkstation", requestJsonObject);

		TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
	}
	

}
