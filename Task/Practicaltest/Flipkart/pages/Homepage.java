package pages;

import static org.testng.Assert.assertTrue;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.cucumber.datatable.DataTable;
import utilities.Baseclass;
import utilities.CommonMethods;

public class Homepage extends CommonMethods{

	public Homepage() {
		PageFactory.initElements(Baseclass.driver, this);
	}
	
	
	@FindBy(xpath = "//input[@class='Pke_EE']")
	public WebElement txt_serchBar;
	
	@FindBy(xpath = "//button[@class='_2iLD__']")
	public WebElement btn_search;
	
	@FindBy(xpath = "//picture[@title='Flipkart']")
	public WebElement tite_homepage;
	
	
	
	
	public void verifyHomePage() {
		waitForVisiblityOfElement(tite_homepage);
		assertTrue(tite_homepage.isDisplayed());
	}
	
	public void searchProduct(DataTable data) {
		List<List<String>> product = data.asLists();
		waitForVisiblityOfElement(txt_serchBar);
		waitForClicableElement(txt_serchBar);
		txt_serchBar.click();
		txt_serchBar.sendKeys(product.get(0).get(0));
		btn_search.click();
	}
	
}