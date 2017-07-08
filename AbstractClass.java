package com.cucumber;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AbstractClass {

	protected static WebDriver driver;

	protected WebDriver getDriver() {
		if (driver == null) {
			System.setProperty("webdriver.gecko.driver", "C:\\WebDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		return driver;
	}

	public static void captureScreenShot(WebDriver ldriver) {

		// Take screenshot and store as a file format
		File src = ((TakesScreenshot) ldriver).getScreenshotAs(OutputType.FILE);
		try {

			FileUtils.copyFile(src, new File("C:/workspace/VehicleDetailsCSV/" + System.currentTimeMillis() + ".png"));
		}

		catch (IOException e)

		{

			System.out.println(e.getMessage());

		}

	}

}
