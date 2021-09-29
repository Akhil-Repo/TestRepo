package StepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class RetrieveProducts extends Reusables{
	public static ExtentTest test = extent.createTest("Retrieve Product", "Trying retrieve a product info");
	@Given("Get the data from Excel {string} & {int}")
	public static void Get_the_data_from_Excel(String SheetName, int RowNum) {
		//clicking(Catalog);
		clicking(Products);
		Sheet= SheetName;
		RowNo= RowNum;

	}
	@When("User searches Product")
	public static void User_searches_Product() throws IOException {
		driver.findElement(ProductSearchName).sendKeys(readExcel(Sheet, RowNo, "ProductName"));
	}
	@And("User see Search Result")
	public static void User_see_Search_Result() throws IOException {
		clicking(SearchButton);
		//clicking(CheckoutResult);
		String ProductName = readExcel(Sheet, RowNo, "ProductName");
		String SKU = readExcel(Sheet, RowNo, "Sku");
		List <WebElement> Products = driver.findElements(By.xpath("//td[contains(text(),'"+SKU+"')]/preceding::td[contains(text(),'"+ProductName+"')]"));
		
		writeExcel(Products);
		test.pass("Able to retrieve product detail");
		
	}


	@Then("Verify Results")
	public static void Save_the_product() throws IOException {

		System.out.println("success");
	
	}

	
}
