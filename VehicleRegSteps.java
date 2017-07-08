package com.cucumber.steps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.cucumber.AbstractClass;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class VehicleRegSteps extends AbstractClass {

	WebDriver driver = getDriver();
	AbstractClass ac = new AbstractClass();

	@Given("^I navigate to DVLA website$")
	public void i_navigate_to_DVLA_website() throws Throwable {
		driver.navigate().to("https://www.gov.uk/get-vehicle-information-from-dvla");
		String title = driver.getTitle();
		System.out.println(title);
	}

	@When("^I click on start now button$")
	public void clickStartNowButton() throws Throwable {
		driver.findElement(By.xpath(".//*[@id='get-started']/a")).click();

	}

	@When("^I enter my vehicle registration to verify make and color$")
	public void enterMyVehicleRegistrationToVerifyMakeAndColor() throws Throwable {
		
		File dir = new File("C:\\workspace\\VehicleDetailsCSV");

		String fileName = "";

		for (File file : dir.listFiles()) {
			fileName = file.getName();
			System.out.println("File Name: " + fileName);
			System.out.println("File Size: " + file.length());
			System.out.println("File Mime Type: " + Files.probeContentType(file.toPath()));
			System.out.println("File Extension: " + fileName.substring(fileName.lastIndexOf(".") + 1));

			System.out.println("file path " + file.getPath());

			if ("csv".equals(fileName.substring(fileName.lastIndexOf(".") + 1))) {

				BufferedReader br = null;
				String line = "";
				String cvsSplitBy = ",";
				try {

					br = new BufferedReader(new FileReader(file.getPath()));
					while ((line = br.readLine()) != null) {

						// use comma as separator
						String[] vehicleDtls = line.split(cvsSplitBy);

						System.out.println("vehicle number " + vehicleDtls[0]);
						driver.findElement(By.id("Vrm")).sendKeys(vehicleDtls[0]);
						driver.findElement(By.name("Continue")).click();

						Thread.sleep(1500);
						String make = driver.findElement(By.xpath(".//*[@id='pr3']/div/ul/li[2]/span[2]/strong"))
								.getText();
						String color = driver.findElement(By.xpath(".//*[@id='pr3']/div/ul/li[3]/span[2]/strong"))
								.getText();
						System.out.println(make);
						System.out.println(color);
						Assert.assertEquals(make, vehicleDtls[1], "Make doesn't match");
						Assert.assertEquals(color, vehicleDtls[2], "Color doesn't match");
						Thread.sleep(1500);
						ac.captureScreenShot(driver);
						driver.findElement(By.xpath(".//*[@id='content']/div[2]/a")).click();

					}

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (br != null) {
						try {
							br.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}

			}

		}

	}

	@Then("^I close the browser$")
	public void verifyVehicleMakeAndColor() throws Throwable {
		driver.manage().deleteAllCookies();
		driver.quit();

	}

}
