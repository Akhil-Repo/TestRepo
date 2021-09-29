package StepDefinations;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;

public class AddProduct extends Reusables {
	public static ExtentTest test = extent.createTest("Add a Product", "Trying add a product");
	@Given("User select catalog")
	public static void User_select_catalog() {

		clicking(Products);
		
	}
	@When("Clicks on New Product")
	public static void Clicks_on_New_Product() {
		clicking(AddNew);
	}
	@And("User Selects Basic")
	public static void User_Selects_Basic() {
		Boolean flag = IsElementDisplayed(GiftCard);

		if (flag)

		{
			clicking(Toggle);

		}

		else {System.out.println("Basic");}
		
		test.pass("Basic Toggle selected");
	}
	@And("User adds Product info")
	public static void User_adds_Product_info() {

		try {	Boolean flag = IsElementDisplayed(ProductInfo);

		if (flag){

			clicking(ProductInfo);

		}

		else {

			clicking(ProductTab);

		}


		driver.findElement(ProductInfo).sendKeys("Mobile");
		driver.findElement(ShortDesc).sendKeys("ShortDesc");


		driver.switchTo().frame("FullDescription_ifr");

		driver.findElement(FullDesc).sendKeys("Full Desc");
		driver.switchTo().defaultContent();


		driver.findElement(Sku).sendKeys("Sku45345");


		driver.findElement(Categories).sendKeys("Computers");
		clicking(Computers);
		test.pass("Product info added");
		}



		catch (Exception e) {
			test.fail("Product info adding failed");
			e.printStackTrace();
		}

	}

	@And("User adds Price")
	public static void User_adds_Price() {

		try {	Boolean flag1 = IsElementDisplayed(PriceTag);
		JavascriptExecutor js= (JavascriptExecutor)driver;

		if (flag1){
			System.out.println("clciking field");
			clicking(PriceInfo);
			System.out.println("field clicked");

		}

		else{
			System.out.println("clciking tab");

			clicking(PricesTab);
			clicking(PriceInfo);
		}

		js.executeScript("document.getElementById('Price').value='1000'");

		Boolean flag2=driver.findElement(TaxExempt).isSelected();
		System.out.println("flag 2 value is: "+flag2);
		if(flag2)
		{System.out.println("tax exemption is selected");}
		else
		{ 
			Select select = new Select(driver.findElement(TaxCategory));
			select.selectByVisibleText("Electronics & Software");
		}
		test.pass("Price info added");
		}
		catch (Exception e) {
			e.printStackTrace();
			test.fail("Price info adding failed");
		}
	}

	@And("User adds ShippingInfo")
	public static void User_adds_ShippingInfo() {
		try {		Boolean flag1 = IsElementDisplayed(ShipLabel);
		JavascriptExecutor js= (JavascriptExecutor)driver;

		if (flag1){
			System.out.println("User enters shipping detail");

		}

		else{
			System.out.println("clciking tab");

			clicking(Shiptab);	
		}

		clicking(Weight);
		js.executeScript("document.getElementById('Weight').value='10'");	
		clicking(Length);
		js.executeScript("document.getElementById('Length').value='20'");
		clicking(Width);
		js.executeScript("document.getElementById('Width').value='10'");		
		clicking(Height);
		js.executeScript("document.getElementById('Height').value='5'");

		test.pass("Shipping info added");
		}

		catch (Exception e) {
			test.fail("Shipping info adding failed");
			e.printStackTrace();
		}
	}
	@And("Select Inventory options")
	public static void Select_Inventory_options() {

		try {		Boolean flag1 = IsElementDisplayed(InventoryLabel);

		if (flag1){
			System.out.println("User selects Inventory options");

		}

		else{
			System.out.println("clciking tab");

			clicking(InventoryTab);	
		}

		Select select = new Select(driver.findElement(Inventory));
		select.selectByVisibleText("Don't track inventory");
		test.pass("Inventory options added");
		}
		catch (Exception e) {
			test.fail("Inventory options adding failed");
			e.printStackTrace();
		}
	}

	@Then("Check the added product")
	public static void Check_the_added_product() {
		clicking(Save);
		Assert.assertEquals(true, driver.findElement(SuccessMsg).isDisplayed());
		System.out.println("success");
	}

}
