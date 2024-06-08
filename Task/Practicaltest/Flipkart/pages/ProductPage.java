package pages;

import static org.testng.Assert.assertTrue;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.Baseclass;
import utilities.CommonMethods;

public class ProductPage extends CommonMethods{

	public ProductPage() {
		PageFactory.initElements(Baseclass.driver, this);
	}
	
	@FindBy(xpath = "//option[@value='Min']/parent::select")
	public WebElement select_minimumPrice;
	
	@FindBy(xpath = "//option[@value='Max']/parent::select")
	public WebElement select_maximumPrice;
	
	@FindBy(className = "XPD6hh")
	public WebElement txt_brandFilter;
	
	@FindBy(css = "div[title='SAMSUNG']")
	public WebElement checkbox_brand;
	
	@FindBy(xpath = "//div[text()='Brand']//ancestor::section//div[@class='ewzVkT _3DvUAf']")
	public List<WebElement> list_brand;
	
	@FindBy(xpath = "//div[text()='Price -- Low to High']")
	public WebElement link_priceLowToHigh;
	
	@FindBy(xpath = "//div[text()='Price -- High to Low']")
	public WebElement link_priceHighToLow;
	
	@FindBy(xpath = "//div[@class='DOjaWF gdgoEp']//div[@class='cPHDOP col-12-12']")
	public List<WebElement> list_price;
	
	@FindBy(xpath = "//nav[@class='WSL9JP']/a")
	public List<WebElement> list_pages;
	
	@FindBy(className =  "_9QVEpD")
	public WebElement lastPage;
	
	@FindBy(xpath =  "//div[text()='RAM']")
	public WebElement label_RAM;
	
	@FindBy(xpath =  "//a[@rel='noopener noreferrer']")
	public List<WebElement> lnk_listofDevices;
	
	@FindBy(xpath =  "//button[text()='Add to cart']")
	public WebElement btn_addToCart;
	
	@FindBy(css =  "span[class='WMMwb0']")
	public WebElement txt_priceDetails;
	
	@FindBy(xpath = "//div[@class='eGXlor pk3Guc']")
	public WebElement link_item;
	
	@FindBy(xpath = "//div[contains(text(),'Price')]/parent::div/following-sibling::div")
	public WebElement txt_price;
	
	@FindBy(xpath = "//div[text()='Discount']/parent::div/following-sibling::div")
	public WebElement txt_discount;
	
	@FindBy(xpath = "//div[@class='PWd9A7 xvz6eC']//span")
	public WebElement txt_totalAmount;
	
	@FindBy(xpath = "//div[text()='Remove']")
	public WebElement btn_remove;
	
	@FindBy(xpath = "//div[@class='gRTtwM f-DWwy']/div[text()='Remove']")
	public WebElement btn_removePopup;
	
	@FindBy(xpath = "//div[@class='eIDgeN']")
	public WebElement msg_successfullyremove;
	
	
	
	
	public void setPriceFilter() {
		Select selectPrice = new Select(select_minimumPrice);
		selectPrice.selectByVisibleText("₹20000");
		
		selectPrice = new Select(select_maximumPrice);
		selectPrice.selectByVisibleText("₹30000+");
		
//		JavascriptExecutor je = (JavascriptExecutor) Baseclass.driver;
//		je.executeScript("arguments[0].scrollIntoView(true)",list_pages.get(0));
//		waitForVisiblityOfElement(list_pages.get(0));
//		waitForClicableElement(list_pages.get(0));
//		waitForVisiblityOfElement(list_price.get(0));
//		je.executeScript("arguments[0].scrollIntoView(true)",list_price.get(list_price.size()-1));
//		System.out.println("Highest price : "+list_price.get(list_price.size()-1).findElement(By.xpath(".//child::div[@class='Nx9bqj _4b5DiR']")).getText());
//		
		
	}
	
	public void setBrandFilter() {
		txt_brandFilter.sendKeys("SAMSUNG");
		waitForVisiblityOfElement(list_brand.get(0));
		for(int i=0;i<list_brand.size();i++) {
			if(list_brand.get(i).getAttribute("title").equalsIgnoreCase("SAMSUNG")) {
				list_brand.get(i).click();
				break;
			}
		}
		
	}
	
	
	public void selectFiltersForPhone() {
		Wait(1000);
		waitForVisiblityOfElement(label_RAM);
		JavascriptExecutor je = (JavascriptExecutor) Baseclass.driver;
		je.executeScript("arguments[0].scrollIntoView(true)",label_RAM);
		waitForClicableElement(label_RAM.findElement(By.xpath("./parent::div/parent::section//div[contains(@title,'8 GB and Above')]")));
		try{label_RAM.findElement(By.xpath("./parent::div/parent::section//div[contains(@title,'8 GB and Above')]")).click();}
		catch (Exception e) {
			Wait(2000);
			je.executeScript("arguments[0].click()",label_RAM.findElement(By.xpath("./parent::div/parent::section//div[contains(@title,'8 GB and Above')]")));
		}
		Wait(2000);
		
		link_priceHighToLow.click();
		waitForVisiblityOfElement(list_price.get(0));
		Wait(1000);
		System.out.println("\n***************************\nHighest price : "+list_price.get(0).findElement(By.xpath(".//child::div[@class='Nx9bqj _4b5DiR']")).getText().substring(1)+"\n***************************");

		
		link_priceLowToHigh.click();
		waitForVisiblityOfElement(list_price.get(0));
		Wait(1000);
		System.out.println("\n****************************\nLowest price : "+list_price.get(0).findElement(By.xpath(".//child::div[@class='Nx9bqj _4b5DiR']")).getText().substring(1)+"\n***************************\n");

	}
	
	public void selctDevicewithMatchedFilter() {
		String handle = Baseclass.driver.getWindowHandle();
		waitForVisiblityOfElement(lnk_listofDevices.get(0));
		for(int i=0;i<lnk_listofDevices.size();i++) {
			int price=Integer.parseInt(lnk_listofDevices.get(i).findElement(By.xpath(".//div[contains(text(),'₹')]")).getText().substring(1).trim().replace(",", ""));
			boolean priceCheck = price>20000;
			boolean ramCheck = lnk_listofDevices.get(i).findElement(By.xpath(".//li[1]")).getText().contains("8 GB RAM");
			if (lnk_listofDevices.get(i).findElement(By.xpath(".//div[contains(text(),'Only')]")).getText()
					.contains("Only few left") && priceCheck && ramCheck) {
				lnk_listofDevices.get(i).click();
				break;
			}
			else {
				continue;
			}
		}
		Set<String> handles = Baseclass.driver.getWindowHandles();
		for(String s: handles) {
			if(!s.equals(handle)) {
				Baseclass.driver.switchTo().window(s);
				break;
			}
		}
	}
	
	public void verifyProductPageOpened() {
		waitForVisiblityOfElement(btn_addToCart);
		btn_addToCart.click();	
	}
	
	public void verifyCartPageAndDetails() {
		waitForVisiblityOfElement(txt_priceDetails);
		assertTrue(txt_priceDetails.isDisplayed());
		assertTrue(link_item.findElement(By.xpath(".//child::*[@class='T2CNXf QqLTQ-']")).getText().contains("SAMSUNG"));
		assertTrue(link_item.findElement(By.xpath(".//child::*[@class='qmkQop']")).getText().contains("8 GB RAM"));
		
		assertTrue((Integer.valueOf(txt_price.getText().trim().substring(1).trim().replace(",", ""))
				-Integer.valueOf(txt_discount.getText().replace("−","").trim().substring(1).replace(",","")))==Integer.valueOf(txt_totalAmount.getText().trim().substring(1).trim().replace(",", "")));
		System.out.println("\n****************************************************\nActual price "+txt_price.getText().trim().substring(1).trim().replace(",", "")+
				" - Discount "+txt_discount.getText().replace("−","").trim().substring(1).replace(",","")+
				" = "+txt_totalAmount.getText().trim().substring(1).trim().replace(",", "")+"\n****************************************************\n");
	}
	
	public void increaseAndDecreaseQuantity() {
		
		String quantity = link_item.findElement(By.xpath(".//child::input[@class='p6sArZ']")).getAttribute("value");
		link_item.findElement(By.xpath(".//child::button[text()='+']")).click();
		Wait(2000);
		assertTrue((Integer.valueOf(quantity)+1)==Integer.valueOf(link_item.findElement(By.xpath(".//child::input[@class='p6sArZ']")).getAttribute("value")));
		
		quantity = link_item.findElement(By.xpath(".//child::input[@class='p6sArZ']")).getAttribute("value");
		waitForVisiblityOfElement(link_item.findElement(By.xpath(".//child::button[text()='–']")));
		link_item.findElement(By.xpath(".//child::button[text()='–']")).click();
		Wait(2000);
		assertTrue((Integer.valueOf(quantity)-1)==Integer.valueOf(link_item.findElement(By.xpath(".//child::input[@class='p6sArZ']")).getAttribute("value")));
	}
	
	public void removeAndVerify() {
		btn_remove.click();
		waitForVisiblityOfElement(btn_removePopup);
		btn_removePopup.click();
		Wait(1000);
		System.out.println("################## "+msg_successfullyremove.getText());
		assertTrue(msg_successfullyremove.getText().contains("Successfully removed"));
		assertTrue(msg_successfullyremove.getText().contains("from your cart"));
	}
}
//_8LEdIn
