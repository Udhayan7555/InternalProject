package com.aksportal.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass {

	protected static Properties prop;
	protected static WebDriver driver;

	@BeforeSuite
	// Load the configuration file
	public void loadConfig() throws IOException {

		prop = new Properties();
		FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
		prop.load(fis);
	}

	@BeforeMethod
	public void setup() throws IOException {

		System.out.println("Setting up the WebDriver for:" + this.getClass().getSimpleName());
		launchBrowser();
		configureBrowser();
		staticWait(2);

	}

	/*
	 * Initialize the WebDriver based on browser defined in config.properties file
	 */
	private void launchBrowser() {

		String browser = prop.getProperty("browser");

		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			throw new IllegalArgumentException("Browser Not Supported:" + browser);
		}
	}

	/*
	 * Configure Browser setting such as wait, maximize and navigate to URL
	 */
	private void configureBrowser() {

		// Implicit Wait
		int implicitWait = Integer.parseInt(prop.getProperty("implicitwait"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

		// Maximize the Driver
		driver.manage().window().maximize();

		// Navigate to URL
		try {
			driver.get(prop.getProperty("url"));
		} catch (Exception e) {
			System.out.println("Failed to load the URL:" + e.getMessage());
		}
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			try {
				driver.quit();
			} catch (Exception e) {
				System.out.println("Unable to quit the Driver:" + e.getMessage());
			}
		}
	}
	
	//Getter method for prop
	public static Properties getProp() {
		return prop;
	}
	
	
	//Getter method for driver
	public WebDriver getDriver() {
		return driver;
	}
	
	//Setter method for driver
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	// Static wait for pause
	public void staticWait(int seconds) {

		LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
	}

}
