package com.aksportal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aksportal.actionDriver.ActionDriver;

public class LoginPage {

	private ActionDriver actionDriver;

	// Define locators using By class

	private By userNameField = By.id("username");
	private By passwordField = By.cssSelector("input[type='password']");
	private By tenantId = By.id("tenantId");
	private By loginButton = By.xpath("//span[text()='LOGIN']");
	private By errorMessage = By.xpath("//div[contains(@class,'toast-message')]");

	//Initialize the ActionDriver object by passing webdriver instance
	public LoginPage(WebDriver driver) {
		this.actionDriver = new ActionDriver(driver);
	}
	
	// Method to perform login
	public void login(String userName, String password, String tenant) {
		actionDriver.enterText(userNameField, userName);
		actionDriver.enterText(passwordField, password);
		actionDriver.enterText(tenantId, tenant);

		actionDriver.click(loginButton);
	}

	// Method to check if error message is displayed
	public boolean isErrorMessageDisplayed() {
		return actionDriver.isDisplayed(errorMessage);
	}

	// Method to get text from the error message
	public void getErrorMessageText() {
		actionDriver.getText(errorMessage);
	}

	// Method to verify error message is correct or not
	public void verifyErrorMessage(String expectedError) {
		actionDriver.compareText(errorMessage, expectedError);
	}

	// Method to verify error toast message is correct or not
	public void verifyErrortoastMessage(String expectedError) {
		actionDriver.toastMessage(errorMessage, expectedError);
	}

}
