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

/**
 * @author Krishnamoorthy H
 *
 */
public class SampleGetTestCase001 extends TestBase {

	@BeforeClass
	public void getAllUsers() {

		logger.info("*************Started SampleTestCase001*************");
		RestAssured.baseURI = "https://gorest.co.in/public/v2";
		httpRequest = RestAssured.given();

		response = httpRequest.request(Method.GET, "/users");

	}
	
	@Test
	public void checkResponseBody(){
		logger.info("****************Checking Response Body****************");
		Assert.assertNotNull(response.getBody().toString());
		
		
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
