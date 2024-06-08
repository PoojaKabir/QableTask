package runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {"classpath:testcases/features"},
		glue = {"testcases.stepdefinitions","Hooks"},
		tags = "@searchmobile",
		plugin = {"pretty"},
		monochrome = true
)

public class Runner extends AbstractTestNGCucumberTests{

	
	
	
}
