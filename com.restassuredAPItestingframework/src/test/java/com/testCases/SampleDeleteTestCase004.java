/**
 * 
 */
package com.testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * @author Krishnamoorthy H
 *
 */
public class SampleDeleteTestCase004 extends TestBase{

	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	public void deleteTestCase(){
		
		logger.info("*************Started SampleDeleteTestCase004*************");
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";

		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/booking");
		
		// Get the jsonpath Object from the response
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		// capture the first booking id to delete it
		int firstBookingIdFromResponse =jsonPathEvaluator.get("[0].bookingid");
		System.out.println(firstBookingIdFromResponse);
		System.out.println(response.getBody().asString());
		
		// Add a header stating the the request body us JSON
				httpRequest.header("Content-Type", "application/json");
				httpRequest.header("Cookie","token=8fc3450268496e3");
				httpRequest.header("Accept","application/json");
		
		// Send post request
		response = httpRequest.request(Method.DELETE,"/booking/"+firstBookingIdFromResponse);	
	}
	
	@Test
	public void checkResponse(){
		
		logger.info("*********************Response verificaion Starred***********" );
	
		Assert.assertTrue(response.getBody().asString().contains("Created"));
	
		}
	
	
	
	
	@Test
	public void checkStausCode() {

		logger.info("**************Status Code verification Test Started***********");
		int statusCode = response.getStatusCode();
		logger.info("The status code is" + statusCode);
		Assert.assertEquals(statusCode, 201);

	}
	
	@Test
	public void checkResponseTime() {

		logger.info("**************Checking Response Time**************");
		long responseTime = response.getTime();
		logger.info("Response Time is " +responseTime);
		if (responseTime > 2000) {
			logger.warn("Response time is greater than 2000");

		}

		Assert.assertTrue(responseTime < 10000);
	}

	@Test
	public void checkStatusLine() {

		logger.info("***************Status Line Verification Strated***********");

		Assert.assertTrue(response.getStatusLine().contains("HTTP/1.1 201 Created"));

	}


}
