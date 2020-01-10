package com.schemaxtech.testAutomationFrameworkTest;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.JsonArray;
import com.schemaxtech.testAutomationFramework.TestAutomationUtil;
import com.schemaxtech.testAutomationFramework.TestCaseInfo;

import au.com.bytecode.opencsv.CSVReader;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PPS {

	/**
	 * It generates a two dimensional array object for all the test methods
	 * 
	 * @param method test name
	 * @return two dimensional array object
	 * @throws Exception
	 */
	Object po_number;
	Object master_po;
	Object  master_po_details_i;
	Object style="N16202AB";
	Object color="WHITE";

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
	@Test(dataProvider="sampleDataProviderget",priority=1)
	public void CreatePO(String testCase, JSONObject requestJsonObject,Map<String, Object> expectedAttributeValues,Map<String,String> responseAttributePaths) throws Exception {
		  
	    
		ResponseBody response= TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("PPS"), "MasterProductionOrderController/createMasterProductionOrder",requestJsonObject);

		TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
		master_po=((JSONObject) (TestAutomationUtil.getJsonObject(response)).get("data")).get("master_po_number");
		TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
		//System.out.println("The response is "+response.asString());
		//master_po_details_i=((JSONObject) (TestAutomationUtil.getJsonObject(response)).get("data")).getJSONArray("moDetails").optString(0, "style");	
	//	master_po_details_i=((JSONObject) (TestAutomationUtil.getJsonObject(response)).get("data")).getJSONArray("moDetails").getJSONObject(0).get("mo_quantity");

		System.out.println("The master_po is "+master_po);

		}
		
	 @SuppressWarnings("rawtypes")
	@Test(dataProvider = "sampleDataProviderget", priority = 2)
	public void getPo(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
			Map<String, String> responseAttributePaths) throws Exception {

		ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("PPS"),
				"MasterProductionOrderController/getMasterProductionOrder", requestJsonObject.put("master_po_number", master_po));
		TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
			}
	
		@SuppressWarnings("rawtypes")
		@Test(dataProvider = "sampleDataProviderget", enabled=false)
		public void deleteMasterProductionOrder(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
				Map<String, String> responseAttributePaths) throws Exception {

			ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("PPS"),
					"MasterProductionOrderController/deleteMasterProductionOrder", requestJsonObject.put("master_po_number", master_po));
			TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
				}
	
	
		@SuppressWarnings("rawtypes")
		@Test(dataProvider = "sampleDataProviderget", priority = 3)
		public void getColorsForStyle(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
				Map<String, String> responseAttributePaths) throws Exception {

			ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("PPS"),
					"MasterProductionOrderController/getColorsForStyle", requestJsonObject.put("style", style));
			TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
				}
	
		
		
		
		@SuppressWarnings("rawtypes")
		@Test(dataProvider = "sampleDataProviderget", priority = 4)
		public void getUnhandledManufacturingOrders(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
				Map<String, String> responseAttributePaths) throws Exception {

			ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("PPS"),
					"MasterProductionOrderController/getColorsForStyle", requestJsonObject.put("style", style).put("color", color));
			TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
				}	
		
		
		
//////////////////////////////////////////	 MasterPoMaxPlies        //////////////////////////////////////////////
	
	
	
/*	
	@SuppressWarnings("rawtypes")
	@Test(dataProvider="sampleDataProviderget",priority=3)
	public void getMasterPoMaxPlies(String testCase, JSONObject requestJsonObject,Map<String, Object> expectedAttributeValues,Map<String,String> responseAttributePaths) throws Exception {
		  
		ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("PPS"),
				"master-po-max-plies/getMasterPoMaxPlies", requestJsonObject.put("master_po_number", master_po));
		TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
		//System.out.println("The response is master_po_details_i "+response.asString());
	//	responsebody.put(testCase, (JSONObject) (TestAutomationUtil.getJsonObject(response)).get("data"));
	}
	
	@SuppressWarnings("rawtypes")
	@Test(dataProvider="sampleDataProviderget",enabled = false)
	public void UpdateMasterPoMaxPlies(String testCase, JSONObject requestJsonObject,Map<String, Object> expectedAttributeValues,Map<String,String> responseAttributePaths) throws Exception {
		  
		ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("PPS"),
				"master-po-max-plies/getMasterPoMaxPlies", requestJsonObject.put("master_po_number", master_po));
		TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
			responsebody.put(testCase, (JSONObject) (TestAutomationUtil.getJsonObject(response)).get("data"));
	}

	
@SuppressWarnings("rawtypes")
	@Test(dataProvider = "sampleDataProviderget", enabled = false)
	public void deleteMasterPoMaxPlies(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
			Map<String, String> responseAttributePaths) throws Exception {

		ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("PPS"),
				"master-po-max-plies/deleteMasterPoMaxPlies", requestJsonObject.put("master_po_number", master_po));
		TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
	}
	
*/
	
//		productionOrderCreation
		
		/////////////////////   ProductionOrderCreationController //////////////////////////////
		
		

			
		@SuppressWarnings("rawtypes")
		@Test(dataProvider = "sampleDataProviderget", priority = 4)
		public void productionOrderCreation(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
				Map<String, String> responseAttributePaths) throws Exception {
			requestJsonObject.getJSONArray("po_details_size_quantities").getJSONObject(0).put("master_po_details_id", master_po_details_i);
			requestJsonObject.getJSONArray("color_ratios").getJSONObject(0).put("master_po_details_id", master_po_details_i);
			requestJsonObject.put("master_po_details_id", master_po_details_i);
			requestJsonObject.put("master_po_number", master_po);
			requestJsonObject.remove("po_number");
			ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("PPS"),
					"ProductionOrderCreationController/productionOrderCreation", requestJsonObject);
				//System.out.println("the chcick3"+requestJsonObject.getJSONArray("po_details_size_quantities"));
			//po_number=((JSONObject) (TestAutomationUtil.getJsonObject(response)).getJSONArray("sub_pos").getJSONObject(0).get("po_number"));
			

			TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
			JSONObject po=(JSONObject) ((JSONObject) (TestAutomationUtil.getJsonObject(response)).get("data")).get("data");
			po_number=po.getJSONArray("sub_pos").getJSONObject(0).get("po_number");

		}	
		
		
		
		
		
		@SuppressWarnings("rawtypes")
		@Test(dataProvider = "sampleDataProviderget", priority = 5)
		public void updateProductionOrder(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
				Map<String, String> responseAttributePaths) throws Exception {
			requestJsonObject.getJSONArray("po_details_size_quantities").getJSONObject(0).put("master_po_details_id", master_po_details_i);
			requestJsonObject.getJSONArray("color_ratios").getJSONObject(0).put("master_po_details_id", master_po_details_i);
			requestJsonObject.put("master_po_number", master_po);
			requestJsonObject.put("po_number",po_number);
			ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("PPS"),
					"ProductionOrderCreationController/updateProductionOrder", requestJsonObject);
				

			TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
			System.out.println("The chcick is"+response.asString());
		}	
			
			
			
			
			
		@SuppressWarnings("rawtypes")
		@Test(dataProvider = "sampleDataProviderget", priority = 6)
		public void getMasterAndSubPoSizeWiseQuantitiesByMasterPo(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
				Map<String, String> responseAttributePaths) throws Exception {

			ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("PPS"),
					"ProductionOrderCreationController/getMasterAndSubPoSizeWiseQuantitiesByMasterPo", requestJsonObject.put("master_po_number", master_po));
			TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
				
		}
		
		
		
		
		@SuppressWarnings("rawtypes")
		@Test(dataProvider = "sampleDataProviderget", priority = 7)
		public void getMasterPoDetails(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
				Map<String, String> responseAttributePaths) throws Exception {

			ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("PPS"),
					"ProductionOrderCreationController/getMasterPoDetails", requestJsonObject.put("master_po_number", master_po));
			TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
				
		}
		
		
		
		
		@SuppressWarnings("rawtypes")
		@Test(dataProvider = "sampleDataProviderget", priority = 8)
		public void getsubPoDetailsWithMasterPo(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
				Map<String, String> responseAttributePaths) throws Exception {
			requestJsonObject.put("master_po_number", master_po);
			requestJsonObject.put("po_number",po_number);
			ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("PPS"),
					"ProductionOrderCreationController/getsubPoDetailsWithMasterPo", requestJsonObject);
			TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
				
		}
			
			
			
			
		
		
		@SuppressWarnings("rawtypes")
		@Test(dataProvider = "sampleDataProviderget", priority = 9)
		public void createSubProductionOrder(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
				Map<String, String> responseAttributePaths) throws Exception {
			requestJsonObject.put("master_po_number", master_po);
			requestJsonObject.put("po_number",po_number);
			ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("PPS"),
					"ProductionOrderCreationController/createSubProductionOrder", requestJsonObject);
			TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
				
		}
			
			
		@SuppressWarnings("rawtypes")
		@Test(dataProvider = "sampleDataProviderget", priority = 9)
		public void getMasterPoStyleColorsDetails(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
				Map<String, String> responseAttributePaths) throws Exception {
			requestJsonObject.put("master_po_number", master_po);
			ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("PPS"),
					"ProductionOrderCreationController/getMasterPoStyleColorsDetails", requestJsonObject);
			TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
				
		}
			
//////////////////////////////////////////    MasterPoMaxPlies        //////////////////////////////////////////////

		
	/*	@SuppressWarnings("rawtypes")
		@Test(dataProvider = "sampleDataProviderget", priority = 2)
		public void getOperation(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
				Map<String, String> responseAttributePaths) throws Exception {

			ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("PPS"),
					"operations-list/getOperation", requestJsonObject);
			TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
				}		
		
		@SuppressWarnings("rawtypes")
		@Test(dataProvider = "sampleDataProviderget", priority = 2)
		public void getOperationDetailsByStyleColor(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
				Map<String, String> responseAttributePaths) throws Exception {

			ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("PPS"),
					"operations-list/getOperationDetailsByStyleColor", requestJsonObject);
			TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
				}	
		
		
	
	
	@SuppressWarnings("rawtypes")
	@Test(dataProvider = "sampleDataProviderget", priority = 2)
	public void getPo(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
			Map<String, String> responseAttributePaths) throws Exception {

		ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("PPS"),
				"ProductionOrderCreationController/getMasterPoDetails", requestJsonObject.put("master_po_number", master_po));
		TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
		System.out.println("The response is "+response.asString());

		master_po_details_i=((JSONObject) (TestAutomationUtil.getJsonObject(response)).get("data")).getJSONArray("master_po_size_quantities").getJSONObject(0).get("master_po_details_id");

		System.out.println("The master_po_details_i is "+master_po_details_i);
	
	}
	*/	
	
	
	/////////////////////////     MasterPoPackingMethodController /////////////////////////////////////////
	
	
	
	
	@SuppressWarnings("rawtypes")
	@Test(dataProvider = "sampleDataProviderget", priority =4)
	public void addCartonDefinition(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
			Map<String, String> responseAttributePaths) throws Exception {
		JSONArray testarray = requestJsonObject.getJSONArray("masterCartonInfo");
		
		for(int i=0; i < testarray.length(); i++) {
			JSONObject itemArr = (JSONObject)testarray.get(i);
			if(itemArr.get("master_po_number").toString().equals("string")){
				itemArr.put("master_po_number", master_po);
				itemArr.put("master_po_details_id", master_po_details_i);
				
			}
			JSONArray polybagTypes = ((JSONObject) itemArr.getJSONArray("polybagTypes").get(i)).getJSONArray("polybagTypeDetails");
			//polybagTypes.put("master_po_details_id", 123456);

			if(polybagTypes.length() >1) {
				
			}else {
				//JSONArray itemArr1 =((JSONObject) polybagTypes.get(i)).getJSONArray("polybagTypeDetails");
				//polybagTypes.put("master_po_details_id", 123456);
			}
			System.out.println("itemarray is"+itemArr);
		}
	//	System.out.println("First"+((JSONObject)requestJsonObject.getJSONObject("masterCartonInfo")).put("master_po_number", 123));
		//System.out.println("second"+requestJsonObject.getJSONArray("polybagTypes"));

	ResponseBody response = TestAutomationUtil.methodForPost(TestAutomationUtil.getPropertyByName("PPS"),
			"MasterPoPackingMethodController/addCartonDefinition", requestJsonObject);
		TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);

	
	}
	
	

		
		
		
		
		///
	
	
	
}
