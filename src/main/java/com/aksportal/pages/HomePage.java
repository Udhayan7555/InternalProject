package com.aksportal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aksportal.actionDriver.ActionDriver;

public class HomePage {

	private ActionDriver actionDriver;
	
	//Define locators for home page
	private By creditCardTab = By.xpath("//a[text()=' CreditCard ']");
	private By userIdButton = By.xpath("//button[contains(@class, 'bg-white-800')]");
	private By signOutOption = By.xpath("//li[text()=' Sign out ']");
	private By agilysysLogo = By.xpath("//img[@src='assets/icons/logo1.webp']");
	
	
	//Initialize the ActionDriver object by passing webdriver instance
	public HomePage(WebDriver driver) {
		this.actionDriver = new ActionDriver(driver);
	}
	
	//Method to verify if Credit card tab is visible
	public boolean isCreditCardTabVisible() {
		return actionDriver.isDisplayed(creditCardTab);
	}
	
	//method to verify if agilysys logo is visible
	public boolean verifyAgilysysLogo() {
		return actionDriver.isDisplayed(agilysysLogo);
	}
	
	//Method to perform sign out operation
	public void signOut() {
		actionDriver.click(userIdButton);
		actionDriver.click(signOutOption);
	}
}
