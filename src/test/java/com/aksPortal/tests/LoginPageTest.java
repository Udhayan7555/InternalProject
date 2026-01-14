package com.aksPortal.tests;

import java.sql.Driver;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aksportal.base.BaseClass;
import com.aksportal.pages.HomePage;
import com.aksportal.pages.LoginPage;

public class LoginPageTest extends BaseClass{

	private LoginPage loginPage;
	private HomePage homePage;
	
	@BeforeMethod
	public void setUpPages() {
		loginPage = new LoginPage(getDriver());
		homePage = new HomePage(getDriver());
	}
	
	@Test
	public void verifyLoginTest() {
		loginPage.login("admin", "TestingOnly1", "0");
		staticWait(4);
		Assert.assertEquals(homePage.isCreditCardTabVisible(), true);
		homePage.signOut();
		staticWait(2);
	}
}
