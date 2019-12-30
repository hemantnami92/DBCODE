package com.schemaxtech.testAutomationFrameworkTest;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.schemaxtech.testAutomationFramework.TestAutomationUtil;
import com.schemaxtech.testAutomationFramework.TestCaseInfo;

import io.restassured.response.ResponseBody;

public class WorkstationT {

	/**
	 * It generates a two dimensional array object for all the test methods
	 * 
	 * @param method test name
	 * @return two dimensional array object
	 * @throws Exception
	 */
 Object workstation_type_id;
	Map<String, JSONObject> responsebody = new HashMap<String, JSONObject>();

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
	@Test(dataProvider = "sampleDataProviderget", priority = 1)
	public void createWorkstationT(String testCase, JSONObject requestJsonObject,
			Map<String, Object> expectedAttributeValues, Map<String, String> responseAttributePaths) throws Exception {

		ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("PMS"),
				"workstation-types/createWorkstationType", requestJsonObject);

		TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
		workstation_type_id=((JSONObject) (TestAutomationUtil.getJsonObject(response)).get("data")).get("workstation_type_id");

	}

	@Test(dataProvider = "sampleDataProviderget", priority = 2)
	public void updateWorkstationT(String testCase, JSONObject requestJsonObject,
			Map<String, Object> expectedAttributeValues, Map<String, String> responseAttributePaths) throws Exception {

		ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("PMS"),
				"workstation-types/updateWorkstationType", requestJsonObject);

		TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
	}

	// workstation type operation mapping//
	
	@Test(dataProvider = "sampleDataProviderget", priority = 3)
	public void createWtom(String testCase, JSONObject requestJsonObject,
			Map<String, Object> expectedAttributeValues, Map<String, String> responseAttributePaths) throws Exception {

		ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("PMS"),
				"workstation-type-operations-mapping/createWorkstationTypeOperationsMapping", requestJsonObject.put("workstation_type_id", workstation_type_id));

		TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
	}
	@Test(dataProvider = "sampleDataProviderget", priority = 4)
	public void deacWtom(String testCase, JSONObject requestJsonObject,
			Map<String, Object> expectedAttributeValues, Map<String, String> responseAttributePaths) throws Exception {

		ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("PMS"),
				"workstation-type-operations-mapping/deactivateWorkstationTypeOperationsMapping", requestJsonObject.put("workstation_type_id", workstation_type_id));

		TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
	}

	
	//workstation
	
	
	@SuppressWarnings("rawtypes")
	@Test(dataProvider = "sampleDataProviderget", priority = 5)
	public void createWorkstation(String testCase, JSONObject requestJsonObject,
			Map<String, Object> expectedAttributeValues, Map<String, String> responseAttributePaths) throws Exception {

		ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("PMS"),
				"workstation/createWorkstation", requestJsonObject.put("workstation_type_id", workstation_type_id));
		TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
	}
	
	@SuppressWarnings("rawtypes")
	@Test(dataProvider = "sampleDataProviderget", priority = 3)
	public void getWorkstationByWorkstaioncode(String testCase, JSONObject requestJsonObject,
			Map<String, Object> expectedAttributeValues, Map<String, String> responseAttributePaths) throws Exception {

		ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("PMS"),
				"workstation/getWorkstationByWorkstaioncode", requestJsonObject.put("workstation_type_id", workstation_type_id));
		TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
	}

	@SuppressWarnings("rawtypes")
	@Test(dataProvider = "sampleDataProviderget", priority = 2)
	public void updateWorkstation(String testCase, JSONObject requestJsonObject,
			Map<String, Object> expectedAttributeValues, Map<String, String> responseAttributePaths) throws Exception {

		ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("PMS"),
				"workstation/updateWorkstation", requestJsonObject);
		TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
	}
	
	
	
	
	
	
	
	
	
	
	

}
