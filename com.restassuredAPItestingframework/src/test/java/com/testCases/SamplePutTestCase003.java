/**
 * 
 */
package com.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * @author Krishnamoorthy H
 *
 */
public class SamplePutTestCase003 extends TestBase{

	
	RequestSpecification httpRequest;
	Response response;
	String firstName = RestUtils.firstName();
	String lastName = RestUtils.lastName();
	String additionalneeds = RestUtils.additionalComments();
	int totalPrice = RestUtils.generateTotalPrice();

	@BeforeClass
	public void putReqeuestTest(){
		
		
		logger.info("*************Started SamplePutTestCase003*************");
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";

		RequestSpecification httpRequest = RestAssured.given();

		// we have created data which we can send along with the post req
		JSONObject requestParams = new JSONObject();
		JSONObject anotherObject = new JSONObject();

		requestParams.put("firstname", firstName);
		requestParams.put("lastname", lastName);
		requestParams.put("totalprice", totalPrice);
		requestParams.put("depositpaid", true);

		anotherObject.put("checkin", "2022-05-10");
		anotherObject.put("checkout", "2022-05-23");

		requestParams.put("bookingdates", anotherObject);
		requestParams.put("additionalneeds", additionalneeds);

		// Add a header stating the the request body us JSON
		httpRequest.header("Content-Type", "application/json");
		httpRequest.header("Cookie","token=8fc3450268496e3");
		httpRequest.header("Accept","application/json");

		// Add json body to request
		httpRequest.body(requestParams.toJSONString());

		System.out.println(requestParams.toJSONString());

		// Send post request
		response = httpRequest.request(Method.PUT, "/booking/2");
		

		
	}

	

	@Test
	public void checkResponseBody(){
		logger.info("****************Checking Response Body****************");
		logger.info(response.getBody().asString());
		Assert.assertNotNull(response.getBody().asString().contains(firstName));
		Assert.assertNotNull(response.getBody().asString().contains(lastName));
		Assert.assertNotNull(response.getBody().asString().contains(Integer.toString(totalPrice)));
		Assert.assertNotNull(response.getBody().asString().contains(additionalneeds));
	}
	

	@Test
	public void checkStausCode() {

		logger.info("**************Status Code verification Test Started***********");
		int statusCode = response.getStatusCode();
		logger.info("The status code is" + statusCode);
		Assert.assertEquals(statusCode, 200);

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

		Assert.assertTrue(response.getStatusLine().contains("200 OK"));

	}

	
}
