package testcases.stepdefinitions;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import pages.Homepage;
import utilities.Baseclass;

public class HomepageStepdefinition extends Baseclass{

	Homepage homepage;
	
	@Given("I open URL")
	public void openUrl() {
		
		driverInitialize();
		homepage = new Homepage();
	}

	@When("verify homepage")
	public void verify_homepage() {
		homepage.verifyHomePage();
	}
	
	@When("click on serchbar and search product")
	public void searchProduct(DataTable data) {
		homepage.searchProduct(data);
	}

	
}
