package testcases.stepdefinitions;

import io.cucumber.java.After;
import utilities.Baseclass;

public class Hooks {

	@After
	public void closeBrowser() {
		Baseclass.driver.quit();
	}
	
}
