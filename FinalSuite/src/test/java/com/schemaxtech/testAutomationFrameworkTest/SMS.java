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

public class SMS {

	/**
	 * It generates a two dimensional array object for all the test methods
	 * 
	 * @param method test name
	 * @return two dimensional array object
	 * @throws Exception
	 */
Object garment_category;
Object style;
Object color;
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
	
	/*@SuppressWarnings("rawtypes")
	@Test(dataProvider = "sampleDataProviderget", priority = 1)
	public void createfc(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
			Map<String, String> responseAttributePaths) throws Exception {
	 
		ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("SMS"),
				"styleColors/setGarmentFabricCategoryComponents", requestJsonObject);
		TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);


		garment_category=((JSONObject) (TestAutomationUtil.getJsonObject(response)).get("data")).get("garment_category");
		TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);

	}
	
		@SuppressWarnings("rawtypes")
	@Test(dataProvider = "sampleDataProviderget", priority = 2)
	public void getfc(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
			Map<String, String> responseAttributePaths) throws Exception {
	 
		ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("SMS"),
				"styleColors/getGarmentFabricCategoryComponents", requestJsonObject.put("garment_category", garment_category));
		System.out.println("Hi bro"+garment_category);
		TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);

		}
	
	*/
		@SuppressWarnings("rawtypes")
		@Test(dataProvider = "sampleDataProviderget", priority = 2)
		public void saveRmComponentsMappingForStyleColor(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
				Map<String, String> responseAttributePaths) throws Exception {
		 
			ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("SMS"),
					"styleColors/saveRmComponentsMappingForStyleColor", requestJsonObject);
			style=((JSONObject) (TestAutomationUtil.getJsonObject(response)).get("data")).get("style");
			//color=((JSONObject) (TestAutomationUtil.getJsonObject(response)).get("data")).get("color");

		//	TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);

			} 
	
		/*@SuppressWarnings("rawtypes")
		@Test(dataProvider = "sampleDataProviderget", priority = 2)
		public void getRmComponentsMappingForStyleColor(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
				Map<String, String> responseAttributePaths) throws Exception {
		 
			ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("SMS"),
					"styleColors/getRmComponentsMappingForStyleColor", requestJsonObject.put("style",style).put("color", color));


			TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);

			} 
	*/
		@SuppressWarnings("rawtypes")
		@Test(dataProvider = "sampleDataProviderget", priority = 2)
		public void getStyleCustomerInfo(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
				Map<String, String> responseAttributePaths) throws Exception {
		 
			ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("SMS"),
					"styleColors/getStyleCustomerInfo", requestJsonObject.put("style",style));


			TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);

			} 
		@SuppressWarnings("rawtypes")
		@Test(dataProvider = "sampleDataProviderget", priority = 3)
		public void checkIfCompnentsMappedForStyleRmSkus(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
				Map<String, String> responseAttributePaths) throws Exception {
		 
			ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("SMS"),
					"styleColors/checkIfCompnentsMappedForStyleRmSkus", requestJsonObject.put("style",style));


			TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);

			} 
	
		@SuppressWarnings("rawtypes")
		@Test(dataProvider = "sampleDataProviderget", priority = 3)
		public void saveStyleMaintenanceDetails(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
				Map<String, String> responseAttributePaths) throws Exception {
		 
			ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("SMS"),
					"styleManagement/saveStyleMaintenanceDetails", requestJsonObject.put("style",style));


			TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);

			} 
		
		
		
		
		@SuppressWarnings("rawtypes")
		@Test(dataProvider = "sampleDataProviderget", priority = 3)
		public void getStyleMaintenanceDetails(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
				Map<String, String> responseAttributePaths) throws Exception {
		 
			ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("SMS"),
					"styleManagement/getStyleMaintenanceDetails", requestJsonObject);


			TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);

			} 
		
		
		
		@SuppressWarnings("rawtypes")
		@Test(dataProvider = "sampleDataProviderget", priority = 4)
		public void getColorSizeReferencesForStyle(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
				Map<String, String> responseAttributePaths) throws Exception {
		 
			ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("SMS"),
					"styleManagement/getColorSizeReferencesForStyle", requestJsonObject);


			TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);

			} 
		@SuppressWarnings("rawtypes")
		@Test(dataProvider = "sampleDataProviderget", priority = 5)
		public void getSizeRmDetailsforStyleColor(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
				Map<String, String> responseAttributePaths) throws Exception {
		 
			ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("SMS"),
					"styleManagement/getSizeRmDetailsforStyleColor", requestJsonObject);


			TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);

			} 


		@SuppressWarnings("rawtypes")
		@Test(dataProvider = "sampleDataProviderget", priority = 5)
		public void getColorsForStyles(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
				Map<String, String> responseAttributePaths) throws Exception {
		 
			ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("SMS"),
					"styleManagement/getColorsForStyle", requestJsonObject);


			TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);

			} 


		@SuppressWarnings("rawtypes")
		@Test(dataProvider = "sampleDataProviderget", priority = 5)
		public void setIdentifiedStatusForStyle(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
				Map<String, String> responseAttributePaths) throws Exception {
		 
			ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("SMS"),
					"styleManagement/setIdentifiedStatusForStyle", requestJsonObject);


			TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);

			} 


		@SuppressWarnings("rawtypes")
		@Test(dataProvider = "sampleDataProviderget", priority = 5)
		public void getJobOperationsForStyleColor(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
				Map<String, String> responseAttributePaths) throws Exception {
		 
			ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("SMS"),
					"styleManagement/getJobOperationsForStyleColor", requestJsonObject);


			TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);

			} 


		@SuppressWarnings("rawtypes")
		@Test(dataProvider = "sampleDataProviderget", priority = 5)
		public void saveStyleInfo(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
				Map<String, String> responseAttributePaths) throws Exception {
		 
			ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("SMS"),
					"styleManagement/saveStyleInfo", requestJsonObject);


			TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);

			} 

}
