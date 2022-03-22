/**
 * 
 */
package com.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * @author Krishnamoorthy H
 *
 */
public class TestBase {
	
	public static RequestSpecification httpRequest;
	public static Response response;
	public Logger logger;
	public String empID="4146";
	
	@BeforeClass
	public void setup(){
		
		logger = Logger.getLogger("Sample Test");
		PropertyConfigurator.configure("Log4j.properties");
		logger.setLevel(Level.DEBUG);
	}
	

	@AfterClass
	public void tearDown(){
		
		logger.info("*****************Test Execution is Completed******************");
		
	}
	
}
