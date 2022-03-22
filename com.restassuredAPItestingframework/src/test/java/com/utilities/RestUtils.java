/**
 * 
 */
package com.utilities;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author Krishnamoorthy H
 *
 */
public class RestUtils {

	public static String firstName(){
		
	String generatedFirstName = RandomStringUtils.randomAlphabetic(5);
		return ("Ram"+generatedFirstName);
		
	}
	
	public static String lastName(){
		
		String generatedLastName = RandomStringUtils.randomAlphabetic(2);
		return generatedLastName;
	} 
	
	public static String additionalComments(){
		
		String[] list = {"breakfast","lunch","dinner"};
		Random r = new Random();
		return list[r.nextInt(list.length)];
		
	}
	
	
	public static int generateTotalPrice(){
		Random r = new Random();
		return r.nextInt(1000);
		
	}
	
	
	
}
