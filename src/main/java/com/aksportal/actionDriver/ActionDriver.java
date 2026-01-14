package com.aksportal.actionDriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.yaml.snakeyaml.parser.ParserImpl;

import com.aksportal.base.BaseClass;

public class ActionDriver {

	private WebDriver driver;
	private WebDriverWait wait;

	public ActionDriver(WebDriver driver) {
		this.driver = driver;
		int explicitWait = Integer.parseInt(BaseClass.getProp().getProperty("explicitwait"));
		this.wait = WebDriverWait(driver, Duration.ofSeconds(explicitWait));
	}

	// Method to Click an Element
	public void click(By by) {
		try {
			waitForElementToBeClickable(by);
			driver.findElement(by).click();
		} catch (Exception e) {
			System.out.println("Unable to click the Element:" + e.getMessage());
		}
	}

	// Method to enter text into an input field
	public void enterText(By by, String value) {
		try {
			waitForElementToBeVisible(by);
			WebElement element = driver.findElement(by);
			element.clear();
			element.sendKeys(value);
		} catch (Exception e) {
			System.out.println("Unable to enter the value:" + e.getMessage());
		}
	}

	// Method to get text from an input field
	public String getText(By by) {
		try {
			waitForElementToBeVisible(by);
			return driver.findElement(by).getText();
		} catch (Exception e) {
			System.out.println("Unable to get the text:" + e.getMessage());
			return "";
		}
	}

	// Method to compare two texts
	public void compareText(By by, String expectedText) {
		try {
			waitForElementToBeVisible(by);
			String actualText = driver.findElement(by).getText();
			if (expectedText.endsWith(actualText)) {
				System.out.println("Text are matching:" + actualText + " equals " + expectedText);
			} else {
				System.out.println("Text are not matching:" + actualText + " not equals " + expectedText);
			}
		} catch (Exception e) {
			System.out.println("Unable to compare texts:" + e.getMessage());
		}
	}

	// Method to check if an Element is displayed
	public boolean isDisplayed(By by) {
		try {
			waitForElementToBeVisible(by);
			return driver.findElement(by).isDisplayed();
		} catch (Exception e) {
			System.out.println("Element is not Displayed:" + e.getMessage());
			return false;
		}
	}

	// Scroll to an Element
	public void scrollToElement(By by) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(by);
			js.executeScript("arguments[0],scrollntoView(true);", element);
		} catch (Exception e) {
			System.out.println("Unable to locate the element:" + e.getMessage());
		}
	}
	
	//Method to validate toast message
	public void toastMessage(By by, String value) {
		try {
			waitForElementToBeVisible(by);
			String toastText = driver.findElement(by).getText();
			if (toastText.equals(value)) {
				System.out.println("Toast message text is matching:"+toastText+" equals "+value);
			}
			else {
				System.out.println("Toast message text is matching:"+toastText+" not equals "+value);
			}
		} catch (Exception e) {
			System.out.println("Unable to locate Toast message:"+e.getMessage());
		}
		
		}

	// Wait for element to be Clickable
	public void waitForElementToBeClickable(By by) {
		try {
			wait.until(ExpectedConditions.elementToBeSelected(by));
		} catch (Exception e) {
			System.out.println("Element is not clickable:" + e.getMessage());
		}
	}

	// Wait for element to be Visible
	public void waitForElementToBeVisible(By by) {
		try {
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
		} catch (Exception e) {
			System.out.println("Element is not Visible:" + e.getMessage());
		}
	}

}
