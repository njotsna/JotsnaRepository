package com.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features ={"src/com/features"},
		plugin = "html:target/selenium-reports/report.html")

public class RunnerClass {

}
