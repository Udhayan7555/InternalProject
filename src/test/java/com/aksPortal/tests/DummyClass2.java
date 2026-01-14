package com.aksPortal.tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.aksportal.base.BaseClass;

public class DummyClass2 extends BaseClass{
	
	@Test
	public void dummyTest2() {
		
		String title = driver.getTitle();
		assert title.equals("Pay Onboarding"):"Test Failed - Title is not matching";
		System.out.println("Test Passed - Title is Matching");
		
	}

}
