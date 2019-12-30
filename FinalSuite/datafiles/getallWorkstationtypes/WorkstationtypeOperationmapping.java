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

public class WorkstationtypeOperationmapping {

	/**
	 * It generates a two dimensional array object for all the test methods
	 * 
	 * @param method test name
	 * @return two dimensional array object
	 * @throws Exception
	 */
	Map<String, JSONObject> responsebody = new HashMap<String, JSONObject>();
	String r;

	@DataProvider(name = "sampleDataProviderget")
	public Object[][] sampleDataProvider(Method method) throws Exception {
		Map<String, String> mapForPathProvider = new HashMap<String, String>();
		mapForPathProvider = TestAutomationUtil.generateFileNames(method.getName());
		TestCaseInfo testcaseinfo = TestAutomationUtil.updatJsonWithTestDataMaster(responsebody, mapForPathProvider.get("inputcsvpathkey"),
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
	@DataProvider(name="sampleDataProvider")
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
	@Test(dataProvider = "sampleDataProvider", priority = 1)
	public void getallWorkstationtypes(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
			Map<String, String> responseAttributePaths) throws Exception {
	 
		ResponseBody response = TestAutomationUtil.methodForPost("http://192.168.0.33:4000",
				"workstation-types/getAllWorkstationTypes", requestJsonObject);
		System.out.println("POST Response\n" + response.asString());
		
		
		//System.out.println(.getJSONArray("data").getJSONObject(0));
		responsebody.put(testCase, (JSONObject) (TestAutomationUtil.getJsonObject(response).getJSONArray("data").getJSONObject(0)));
		//TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
	}
	
	
	
	
	@SuppressWarnings("rawtypes")
	@Test(dataProvider = "sampleDataProvider", priority = 2)
	public void createWorkstationTypeOpmapping(String testCase, JSONObject requestJsonObject, Map<String, Object> expectedAttributeValues,
			Map<String, String> responseAttributePaths) throws Exception {
		requestJsonObject.put("workstation_type_id",responsebody.get(testCase).get("workstation_type_id"));
		ResponseBody response = TestAutomationUtil.methodForPost("http://192.168.0.33:4000",
				"workstation-type-operations-mapping/createWorkstationTypeOperationsMapping", requestJsonObject);
		
		//responsebody.put(testCase, (JSONObject) (TestAutomationUtil.getJsonObject(response)).get("data"));
		TestAutomationUtil.verifyResponse(response, expectedAttributeValues, responseAttributePaths);
		
	}
	
	

			
}