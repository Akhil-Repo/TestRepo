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

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

public class ModifyProducts extends Reusables {
	public static ExtentTest test = extent.createTest("Modify Product", "Trying Modify a product");

	@Given("User gets data from Excel {string} & {int}")
	public static void User_gets_data_from_Excel(String SheetName, int RowNum) {
		//clicking(Catalog);
		clicking(Products);
		Sheet= SheetName;
		RowNo= RowNum;

	}
	@When("User selects product to modify")
	public static void User_selects_product_to_modify() throws IOException {
		driver.findElement(ProductSearchName).sendKeys(readExcel(Sheet, RowNo, "ProductSearchName"));
		clicking(SearchButton);

		String ProductSearchName = readExcel(Sheet, RowNo, "ProductSearchName");

		driver.findElement(By.xpath("//td[text()='"+ProductSearchName+"']/following::a[contains(@href,'Edit')]")).click();
	}
	@And("User selects Basic Toggle")
	public static void User_selects_Basic_Toggle() {
		try {		Boolean flag = IsElementDisplayed(GiftCard);

		if (flag)

		{
			clicking(Toggle);

		}

		else {System.out.println("Basic");}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	@And("Modify Product data")
	public static void Modify_Product_data() throws IOException {

		try {	Boolean flag = IsElementDisplayed(ProductInfo);

		if (flag){

			clicking(ProductInfo);

		}

		else {

			clicking(ProductTab);

		}

		driver.findElement(ProductInfo).clear();
		driver.findElement(ProductInfo).sendKeys(readExcel(Sheet, RowNo, "ProductName"));
		driver.findElement(ShortDesc).clear();
		driver.findElement(ShortDesc).sendKeys(readExcel(Sheet, RowNo, "ShortDesc"));


		driver.switchTo().frame("FullDescription_ifr");
		driver.findElement(FullDesc).clear(); System.out.println(readExcel(Sheet,
				RowNo, "FullDescription"));
		driver.findElement(FullDesc).sendKeys(readExcel(Sheet,
				RowNo,"FullDescription")); driver.switchTo().defaultContent();

				driver.findElement(Sku).clear();
				driver.findElement(Sku).sendKeys(readExcel(Sheet, RowNo, "Sku"));
				test.pass("Able to modify product");
		}
		catch (Exception e) {
			test.fail("failed modifying product");
			e.printStackTrace();
		}
	}


	@And("Modify price")
	public static void Modify_price() throws IOException {
		try {
			Boolean flag1 = IsElementDisplayed(PriceTag);
			JavascriptExecutor js= (JavascriptExecutor)driver;

			if (flag1){
				System.out.println("clciking field");
				driver.findElement(PriceInfo).clear();
				System.out.println("field clicked");

			}

			else{
				System.out.println("clciking tab");

				clicking(PricesTab);
				driver.findElement(PriceInfo).clear();
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
			test.pass("Price info modified");
		}
		catch (Exception e) {
			test.pass("Price info modification failed");
			e.printStackTrace();
		}
	}

	@And("Modify ShippingInfo")
	public static void Modify_ShippingInfo() throws IOException {
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

		driver.findElement(Weight).clear();
		js.executeScript("document.getElementById('Weight').value="+Weight1+"");	
		driver.findElement(Length).clear();
		js.executeScript("document.getElementById('Length').value="+Length1+"");
		driver.findElement(Width).clear();
		js.executeScript("document.getElementById('Width').value="+Width1+"");		
		driver.findElement(Height).clear();
		js.executeScript("document.getElementById('Height').value="+Height1+"");
		test.pass("Shipping info modified");
		}
		catch (Exception e) {
			e.printStackTrace();
			test.fail("Shipping info modification failed");
		}
	}


	@And("Add new picture")
	public static void Add_new_picture() throws IOException,InterruptedException {
		//clicking(UploadImg);
		driver.findElement(UploadImg).sendKeys(readExcel(Sheet, RowNo,"Img"));

		Boolean flag1= IsElementDisplayed(ImageTitle);

		if(flag1) {
			clicking(ProductPic);	}


	}

	@Then("Save the Changes")
	public static void Save_the_Changes() {

		//		Boolean flag1= IsElementDisplayed(Image);

		//		if(flag1) {
		clicking(Save);
		//		}
		//clicking(Save);
		Assert.assertEquals(true, driver.findElement(SuccessMsg).isDisplayed());
		System.out.println("success");
	}

}
