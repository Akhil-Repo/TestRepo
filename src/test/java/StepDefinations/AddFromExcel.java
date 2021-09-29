package StepDefinations;

import java.io.IOException;

import org.openqa.selenium.By;
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

public class AddFromExcel extends Reusables {

	public static ExtentTest test = extent.createTest("Add products from Excel", "Trying adding multiple products info using excel");
	@Given("User Enters data from Excel {string} & {int}")
	public static void User_Enters_data_from_Excel(String SheetName, int RowNum) {
	
		clicking(Products);
		Sheet= SheetName;
		RowNo= RowNum;

	}
	@When("User selects add new Product")
	public static void User_selects_add_new_Product() {
		clicking(AddNew);
	}
	@And("User clicks Basic Toggle")
	public static void User_clicks_Basic_Toggle() {
		Boolean flag = IsElementDisplayed(GiftCard);

		if (flag)

		{
			clicking(Toggle);

		}

		else {System.out.println("Basic");}
		test.pass("Basic Toggle selected");
	}
	@And("Enter Product data")
	public static void Enter_Product_data() throws IOException {

		try {	Boolean flag = IsElementDisplayed(ProductInfo);

		if (flag){

			clicking(ProductInfo);

		}

		else {

			clicking(ProductTab);

		}


		driver.findElement(ProductInfo).sendKeys(readExcel(Sheet, RowNo, "ProductName"));
		driver.findElement(ShortDesc).sendKeys(readExcel(Sheet, RowNo, "ShortDesc"));

		driver.switchTo().frame("FullDescription_ifr");
		System.out.println(readExcel(Sheet, RowNo, "FullDescription"));
		driver.findElement(FullDesc).sendKeys(readExcel(Sheet, RowNo,"FullDescription"));
		driver.switchTo().defaultContent();

		driver.findElement(Sku).sendKeys(readExcel(Sheet, RowNo, "Sku"));

		
		String Catagory= readExcel(Sheet, RowNo,"Categories");

		driver.findElement(Categories).sendKeys(Catagory);

		driver.findElement(By.xpath("//li[@class='k-item k-state-focused' and text()='"+Catagory+"']")).click();
		
		test.pass("Product info added");

		}

		catch (Exception e) {
			test.fail("Product info adding failed");
			e.printStackTrace();
		}
	}

	@And("Enters price")
	public static void Enters_price() throws IOException {
		try {
			Boolean flag1 = IsElementDisplayed(PriceTag);
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
			String Price = readExcel(Sheet, RowNo,"Price");
			js.executeScript("document.getElementById('Price').value="+Price+"");

			Boolean flag2=driver.findElement(TaxExempt).isSelected();
			System.out.println("flag 2 value is: "+flag2);
			if(flag2)
			{System.out.println("tax exemption is selected");}
			else
			{ 
				String option = readExcel(Sheet, RowNo,"TaxCategory");
				Select select = new Select(driver.findElement(TaxCategory));

				select.selectByVisibleText(option);
			}
			test.pass("Price info added");
		}catch (Exception e) {
			e.printStackTrace();
			test.fail("Price info adding failed");
		}
	}

	@And("Enter ShippingInfo")
	public static void Enter_ShippingInfo() throws IOException {
		try {		Boolean flag1 = IsElementDisplayed(ShipLabel);
		JavascriptExecutor js= (JavascriptExecutor)driver;

		if (flag1){
			System.out.println("User enters shipping detail");

		}

		else{
			System.out.println("clciking tab");

			clicking(Shiptab);	
		}
		String Weight1=readExcel(Sheet, RowNo,"Weight");
		String Length1=readExcel(Sheet, RowNo,"Length");
		String Width1=readExcel(Sheet, RowNo,"Width");
		String Height1=readExcel(Sheet, RowNo,"Height");

		clicking(Weight);
		js.executeScript("document.getElementById('Weight').value="+Weight1+"");	
		clicking(Length);
		js.executeScript("document.getElementById('Length').value="+Length1+"");
		clicking(Width);
		js.executeScript("document.getElementById('Width').value="+Width1+"");		
		clicking(Height);
		js.executeScript("document.getElementById('Height').value="+Height1+"");
		test.pass("Shipping info added");
		}

		catch (Exception e) {
			test.fail("Shipping info adding failed");
			e.printStackTrace();
		}}

	@And("Enters Inventory options")
	public static void Enters_Inventory_options() throws IOException {

		try{Boolean flag1 = IsElementDisplayed(InventoryLabel);

		if (flag1){
			System.out.println("User selects Inventory options");

		}

		else{
			System.out.println("clciking tab");

			clicking(InventoryTab);	
		}
		String inventoryOption=readExcel(Sheet, RowNo,"Inventory");
		Select select = new Select(driver.findElement(Inventory));
		select.selectByVisibleText(inventoryOption);
		test.pass("Inventory options added");
		}
		catch (Exception e) {
			test.fail("Inventory options adding failed");
			e.printStackTrace();
		}
	}
	@Then("Save the product")
	public static void Save_the_product() {
		clicking(Save);
		Assert.assertEquals(true, driver.findElement(SuccessMsg).isDisplayed());
		System.out.println("success");
	}

}
